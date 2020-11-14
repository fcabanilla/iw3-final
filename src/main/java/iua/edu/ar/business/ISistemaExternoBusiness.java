package iua.edu.ar.business;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.model.SistemaExterno;
import iua.edu.ar.model.dto.RespuestaGenerica;

public interface ISistemaExternoBusiness {

	public RespuestaGenerica<SistemaExterno> recibir(SistemaExterno sistemaExterno) throws BusinessException;

}
