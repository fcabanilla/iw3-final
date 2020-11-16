package iua.edu.ar.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iua.edu.ar.business.IOrdenBusiness;
import iua.edu.ar.business.exception.BusinessException;
import iua.edu.ar.business.exception.NotFoundException;
import iua.edu.ar.business.exception.PasswordException;
import iua.edu.ar.model.Orden;

@RestController
@RequestMapping(value = Constantes.URL_ORDENES)
public class OrdenRestController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IOrdenBusiness ordenBusiness;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Orden> load(@PathVariable("id") Long id) {

		try {
			return new ResponseEntity<Orden>(ordenBusiness.load(id), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Orden>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Orden>> list() {
		try {
			log.debug("GetMapping: Una lista de orden ");
			return new ResponseEntity<List<Orden>>(ordenBusiness.list(), HttpStatus.OK);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<List<Orden>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Orden orden) {
		try {
			ordenBusiness.add(orden);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_ORDENES + '/' + orden.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {

			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Orden orden) {
		try {
			ordenBusiness.update(orden);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			// e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			// e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			ordenBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/check-password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkPassword(@RequestBody Orden orden){
		try {
			ordenBusiness.checkPassword(orden);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} catch (PasswordException e) {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
	}
}
