package iua.edu.ar.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.DatoCarga;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.OrdenDetalle;
import iua.edu.ar.model.UltimoDatoCarga;
import iua.edu.ar.model.persistence.OrdenDetalleRepository;
import iua.edu.ar.model.persistence.OrdenRepository;

@Service
public class OrdenBusiness implements IOrdenBusiness {

	@Autowired
	private OrdenRepository ordenDAO;
	@Autowired
	private OrdenDetalleRepository ordenDetalleDAO;

	@Autowired
	OrdenDetalleBusiness ordenDetalleBusiness;

	@Override
	public Orden load(Long id) throws NotFoundException, BusinessException {
		Optional<Orden> op;
		try {
			op = ordenDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Orden con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<Orden> list() throws BusinessException {
		try {
			return ordenDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Orden add(Orden orden) throws BusinessException {
		try {
			if (orden.checkBasicData() && orden.getEstado() == 0)
				orden.setEstado(1);

			return ordenDAO.save(orden);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			ordenDAO.deleteById(id);
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + id + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public Orden update(Orden orden) throws NotFoundException, BusinessException {
		Orden ordenDB = load(orden.getId());

		ordenDAO.save(ordenDB);

		// ordenDB.setUltimosDatosCarga(ultimosDatosCarga);

		orden.partialUpdate(ordenDB);

		if (orden.checkBasicData())
			orden.setEstado(1);

		return ordenDAO.save(orden);

	}

	@Override
	public void checkPassword(Orden orden) throws NotFoundException, BusinessException, PasswordException {
		Orden ordenDB = load(orden.getId());
		if (orden.checkPassword(ordenDB.getPassword()) && ordenDB.getEstado() == 2) {
			return;
		}

		throw new PasswordException("La contrasenia no es correcta");
	}

	@Override
	public void cargaDatos(DatoCarga datosCarga, Long id) throws NotFoundException, BusinessException {
		Orden ordenDB;
		try {
			ordenDB = load(id);
			if (ordenDB.getEstado() != 2)
				throw new BusinessException("Estado incorrecto");

			UltimoDatoCarga ultimosDatosCarga = new UltimoDatoCarga(datosCarga.getMasaAcumulada(),
					datosCarga.getDensidadProducto(), datosCarga.getTemperaturaProducto(), datosCarga.getCaudal());
			ordenDB.setUltimosDatosCarga(ultimosDatosCarga);
			add(ordenDB);

			OrdenDetalle ordenDetalle = new OrdenDetalle();

			List<OrdenDetalle> test = ordenDetalleDAO.findByOrdenId(id);
			ordenDetalle.setCaudal(datosCarga.getCaudal());
			ordenDetalle.setDensidadProducto(datosCarga.getDensidadProducto());
			ordenDetalle.setMasaAcumulada(datosCarga.getMasaAcumulada());
			ordenDetalle.setOrden(ordenDB);
			ordenDetalle.setTemperaturaProducto(datosCarga.getTemperaturaProducto());
			if (test.isEmpty()) {
				ordenDetalleDAO.save(ordenDetalle);
				return;
			}

			OrdenDetalle test2 = ordenDetalleDAO.findFirstByOrdenIdOrderByFecha(id);

			Date date1 = test2.getFecha();

			Date date2 = new Date();

			long segundos = getDateDiff(date1, date2, TimeUnit.SECONDS);
			if (segundos >= ordenDB.getFecuencia()) {
				ordenDetalleDAO.save(ordenDetalle);
			}

		} catch (Exception e) {
			throw new BusinessException(e);
		}

		return;

	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1    the oldest date
	 * @param date2    the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public void addPesajeInicial(Orden orden) throws NotFoundException, BusinessException {
		try {
			Orden ordenDb = load(orden.getId());
			if (ordenDb.getEstado() == 1) {

				ordenDb.setPesoInicial(orden.getPesoInicial());
				ordenDb.setFechaRecepcionPesajeInicial(orden.getFechaRecepcionPesajeInicial());
				ordenDb.setEstado(2);
				String pass = "";
				for (int i = 0; i < 5; i++) {
					pass = (int) (Math.random() * 9) + 1 + pass;
				}
				ordenDb.setPassword(pass);
				ordenDAO.save(ordenDb);
			} else {
				throw new BusinessException("El estado de la orden es distinto a 1");
			}
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + orden.getId() + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Override
	public void addPesajeFinal(Orden orden) throws NotFoundException, BusinessException {
		try {
			Orden ordenDb = load(orden.getId());
			if (ordenDb.getEstado() == 3) {

				ordenDb.setPesoFinal(orden.getPesoFinal());
				ordenDb.setFechaRecepcionPesajeFinal(orden.getFechaRecepcionPesajeFinal());
				ordenDb.setEstado(4);
				ordenDAO.save(ordenDb);

			} else {
				throw new BusinessException("El estado de la orden es distinto a 1");
			}
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden con id = " + orden.getId() + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Void cierreOrden(Orden orden) throws BusinessException, NotFoundException {
		Orden ordenDB = load(orden.getId());

		if (ordenDB.getEstado() == 2) {
			ordenDB.setEstado(3);
			ordenDAO.save(ordenDB);
			return null;
		}

		throw new BusinessException("El estado no se encuentra en estado 2, no se cambiara el valor");

	}

}
