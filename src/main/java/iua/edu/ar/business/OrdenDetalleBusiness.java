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
public class OrdenDetalleBusiness implements IOrdenDetalleBusiness {

	@Autowired
	private OrdenDetalleRepository ordenDetalleDAO;
	
	
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
			throw new NotFoundException("El Orden con el id " + id + " no se encuentra en la BD");
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
	public OrdenDetalle add(OrdenDetalle ordenDetalle) throws BusinessException {
		try {

			return ordenDetalleDAO.save(ordenDetalle);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public OrdenDetalle update(OrdenDetalle ordenDetalle) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Long id) throws NotFoundException, BusinessException {
		// TODO Auto-generated method stub
		
	}

	/*
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
		
		
		Orden ordenTmp = new Orden();
		ordenTmp.setId(id);
//		ordenTmp.setId(id);
		
		List<OrdenDetalle> test = ordenDetalleDAO.findAllOrdenDetalleByOrdenId(id);
		
		if (test.isEmpty()) {
			OrdenDetalle ordenDetalle = new OrdenDetalle(
					ordenTmp,
					datosCarga.getDensidadProducto(),
					datosCarga.getTemperaturaProducto(),
					datosCarga.getCaudal(),
					datosCarga.getMasaAcumulada()
					);
			
			ordenDetalleDAO.save(ordenDetalle);
//			ordenDetalleBusiness.add(ordenDetalle);
		}
		
		OrdenDetalle test2 = ordenDetalleDAO.findFirstByOrdenIdOrderByFecha(id);

//		Date date1 = ordenDetalleDAO.findFirstByOrdenIdOrderByFecha(id);
		
		Date date2 = new Date();
		
//		long dias = getDateDiff(date1, date2, TimeUnit.DAYS);
		
		

//		ordenDB.setUltimosDatosCarga(datosCarga);
//		ordenDB.setPromedioDatosCarga(datosCarga);
		return;

	}
	*/



}
