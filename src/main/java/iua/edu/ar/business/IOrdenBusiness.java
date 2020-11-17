package iua.edu.ar.business;

import java.util.List;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.dto.OrdenDTO;

public interface IOrdenBusiness {

	public Orden load(Long id) throws NotFoundException, BusinessException;

	public List<Orden> list() throws BusinessException;

	public Orden add(Orden orden) throws BusinessException;

	public Orden update(Orden orden) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;

	// Extras

	public Void cierreOrden(Orden orden) throws BusinessException, NotFoundException;

	public List<Orden> listOrdenConciliacion(Orden orden) throws BusinessException, NotFoundException;

	// Sin uso actualmente
	
	public void addPesajeInicial(Orden orden) throws NotFoundException, BusinessException;

	public void addPesajeFinal(Orden orden) throws NotFoundException, BusinessException;
	
	public List<OrdenDTO> findByOrdenConciliacion(OrdenDTO ordenDTO) throws BusinessException;

}
