package iua.edu.ar.business;

import java.util.List;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.DatoCarga;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.OrdenDetalle;

public interface IOrdenDetalleBusiness {

	public OrdenDetalle load(Long id) throws NotFoundException, BusinessException;

	public List<OrdenDetalle> list() throws BusinessException;

	public OrdenDetalle add(OrdenDetalle ordenDetalle) throws BusinessException;

	public OrdenDetalle update(OrdenDetalle ordenDetalle) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;

//	void cargaDatos(DatoCarga datosCarga, Long id) throws NotFoundException, BusinessException;

	// Extras
	// Sin uso actualmente


}
