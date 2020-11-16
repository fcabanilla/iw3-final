package iua.edu.ar.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.Orden;
import iua.edu.ar.model.persistence.OrdenRepository;

@Service
public class OrdenBusiness implements IOrdenBusiness {

	@Autowired
	private OrdenRepository ordenDAO;

	@Override
	public Orden load(Long id) throws NotFoundException, BusinessException {
		Optional<Orden> op;
		try {
			op = ordenDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("El Producto con el id " + id + " no se encuentra en la BD");
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
			if(orden.checkBasicData())
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
		
		//ordenDB.setUltimosDatosCarga(ultimosDatosCarga);
		
		orden.partialUpdate(ordenDB);
		
		if(orden.checkBasicData())
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
	public Boolean checkPassword(Orden orden) throws NotFoundException, BusinessException, PasswordException {
		Orden ordenDB = load(orden.getId());
		if (orden.checkPassword(ordenDB.getPassword()) && ordenDB.getEstado() == 2) {
			return true;
		}

		throw new PasswordException("La contrasenia no es correcta");
	}

}
