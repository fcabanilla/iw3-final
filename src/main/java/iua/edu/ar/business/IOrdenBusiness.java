package iua.edu.ar.business;

import java.util.List;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.DatoCarga;
import iua.edu.ar.model.Orden;

public interface IOrdenBusiness {

	public Orden load(Long id) throws NotFoundException, BusinessException;

	public List<Orden> list() throws BusinessException;

	public Orden add(Orden orden) throws BusinessException;

	public Orden update(Orden orden) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;

	// Extras
	// Sin uso actualmente
//	public void checkPassword(Orden orden) throws NotFoundException, BusinessException, PasswordException;
//
//	public void cargaDatos(DatoCarga datosCarga, Long id) throws NotFoundException, BusinessException;

}
