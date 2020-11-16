package iua.edu.ar.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
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
	private OrdenDetalleRepository ordenDetalleDAO;


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
			if (orden.checkBasicData())
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
	// Sin uso actualmente
	public Orden load(String codigoExterno) throws NotFoundException, BusinessException {
		Optional<Orden> op;
		try {
			op = ordenDAO.findFirstByCodigoExterno(codigoExterno);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("La orden con código externo " + codigoExterno + " no se encuentra en la BD");
		}
		return op.get();
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
		Orden ordenDB = load(id);

		UltimoDatoCarga ultimosDatosCarga = new UltimoDatoCarga(
				datosCarga.getMasaAcumulada(),
				datosCarga.getDensidadProducto(), 
				datosCarga.getTemperaturaProducto(), 
				datosCarga.getCaudal()
		);
		
		ordenDB.setUltimosDatosCarga(ultimosDatosCarga);
		ordenDAO.save(ordenDB);

		
		try {
			Optional<OrdenDetalle> lista = ordenDetalleDAO.findById((long) 1);
			System.out.print(lista);
		} catch (Exception e) {
			System.out.print(e);
			throw new BusinessException(e);
		}
		
		
		Orden ordenTmp = new Orden();
		ordenTmp.setId(id);
		List<OrdenDetalle> test = ordenDetalleDAO.findAllOrdenDetalleByOrden(ordenTmp);
		
//		if (ordenDetalleDAO.findByOrdenId(id).isEmpty()) {
//			OrdenDetalle ordenDetalle = new OrdenDetalle(
//					datosCarga.getMasaAcumulada(),
//					datosCarga.getDensidadProducto(), 
//					datosCarga.getTemperaturaProducto(), 
//					datosCarga.getCaudal()
//			);
//			ordenDetalleDAO.save(ordenDetalle);
//		}
		
//		Date date1 = ordenDetalleDAO.findByOrdenIdOrderByFecha(id).get(0).getFecha();
//		
//		Date date2 = new Date();
//		
//		long dias = getDateDiff(date1, date2, TimeUnit.DAYS);
		
		

//		ordenDB.setUltimosDatosCarga(datosCarga);
//		ordenDB.setPromedioDatosCarga(datosCarga);
		return;

	}
	
	/**
	 * Get a diff between two dates
	 * @param date1 the oldest date
	 * @param date2 the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

}
