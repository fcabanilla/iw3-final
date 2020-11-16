package iua.edu.ar.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.OrdenDetalle;
import iua.edu.ar.model.persistence.OrdenDetalleRepository;
import iua.edu.ar.model.persistence.OrdenRepository;

@Service
public class OrdenBusiness implements IOrdenBusiness {

	@Autowired
	private OrdenRepository ordenDAO;

	@Autowired
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
			throw new NotFoundException("La orden con id " + id + " no se encuentra en la BD");
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
	public Void cierreOrden(Orden orden) throws BusinessException, NotFoundException {
		Orden ordenDB = load(orden.getId());

		if (ordenDB.getEstado() == 2) {
			ordenDB.setEstado(3);
			ordenDAO.save(ordenDB);
			return null;
		}

		throw new BusinessException("El estado no se encuentra en estado 2, no se cambiara el valor");

	}

	@Override
	public List<Orden> listOrdenConciliacion(Orden orden) throws BusinessException, NotFoundException {
		Orden ordenDB = load(orden.getId());

		try {
			if (ordenDB.getEstado() == 4) {
				return ordenDAO.findAll();
			} else {
				throw new BusinessException(
						"El estado no se encuentra en estado 4, no se puede mostrar conciliacion de orden");
			}

		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

}