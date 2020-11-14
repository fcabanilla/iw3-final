package iua.edu.ar.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.model.SistemaExterno;
import iua.edu.ar.model.dto.MensajeRespuesta;
import iua.edu.ar.model.dto.RespuestaGenerica;
import iua.edu.ar.model.persistence.SistemaExternoRepository;

@Service
public class SistemaExternoBusiness implements ISistemaExternoBusiness {
			

	@Autowired
	private IOrdenBusiness ordenService;
	
	@Autowired
	private SistemaExternoRepository sistemaExternoDAO;

	@Override
	public RespuestaGenerica<SistemaExterno> recibir(SistemaExterno sistemaExterno) throws BusinessException {

		MensajeRespuesta m = new MensajeRespuesta();
		RespuestaGenerica<SistemaExterno> r = new RespuestaGenerica<SistemaExterno>(sistemaExterno, m);

		String mensajeCheck = sistemaExterno.checkBasicData();
		if (mensajeCheck != null) {
			m.setCodigo(-1);
			m.setMensaje(mensajeCheck);
			return r;
		}

		try {
			sistemaExterno.setOrden(ordenService.asegurarOrden(sistemaExterno.getOrden()));
			
			sistemaExternoDAO.save(sistemaExterno);
		} catch (Exception e) {
			throw new BusinessException(e);
		}

		return r;
	}

}