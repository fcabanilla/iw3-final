package iua.edu.ar.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iua.edu.ar.business.ISistemaExternoBusiness;
import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.model.SistemaExterno;
import iua.edu.ar.model.dto.MensajeRespuesta;

@RestController
@RequestMapping(value = Constantes.URL_SISTEMA_EXTERNO)
public class SistemaExternoRestController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISistemaExternoBusiness seBusiness;
	
	@PostMapping(value = "/integracion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MensajeRespuesta> load(@RequestBody SistemaExterno sistemaExterno) {

		try {
			MensajeRespuesta r = seBusiness.recibir(sistemaExterno).getMensaje();
			if (r.getCodigo() == 0) {
				return new ResponseEntity<MensajeRespuesta>(r, HttpStatus.OK);
			} else {
				return new ResponseEntity<MensajeRespuesta>(r, HttpStatus.BAD_REQUEST);

			}
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<MensajeRespuesta>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}