package iua.edu.ar.business;

import java.util.List;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.model.Producto;

public interface IProductoBusiness {

	public Producto load(Long id) throws NotFoundException, BusinessException;

	public List<Producto> list() throws BusinessException;

	public Producto add(Producto producto) throws BusinessException;

	public Producto update(Producto producto) throws NotFoundException, BusinessException;

	public void delete(Long id) throws NotFoundException, BusinessException;
}
