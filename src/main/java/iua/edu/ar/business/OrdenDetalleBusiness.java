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

@Service
public class OrdenDetalleBusiness implements IOrdenDetalleBusiness {

	@Autowired
	private OrdenDetalleRepository ordenDetalleDAO;
	
	@Autowired
	private OrdenBusiness ordenBusiness;

	@Override
	public OrdenDetalle load(Long id) throws NotFoundException, BusinessException {
		Optional<OrdenDetalle> op;
		try {
			op = ordenDetalleDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Orden Detalle con el id " + id + " no se encuentra en la BD");
		}
		return op.get();
	}

	@Override
	public List<OrdenDetalle> list() throws BusinessException {
		try {
			return ordenDetalleDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public OrdenDetalle add(OrdenDetalle ordenDetalle, Long ordenId) throws BusinessException {
		try {
			Orden orden = ordenBusiness.load(ordenId);
			
			ordenDetalle.setOrden(orden);
			
			
			return ordenDetalleDAO.save(ordenDetalle);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public OrdenDetalle update(OrdenDetalle ordenDetalle) throws NotFoundException, BusinessException {
		load(ordenDetalle.getId());
		return ordenDetalleDAO.save(ordenDetalle);
	}

	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		try {
			ordenDetalleDAO.deleteById(id);
		} catch (EmptyResultDataAccessException el) {
			throw new NotFoundException("No se encuentra la orden detalle con id = " + id + ".");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}
}
