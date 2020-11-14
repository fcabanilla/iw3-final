package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datos_carga")
public class DatosCarga implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8975778939793530631L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 100)
	private long masaAcumulada;
	
	@Column(length = 100)
	private long densidadProducto;
	
	@Column(length = 100)
	private long temperaturaProducto;
	
	@Column(length = 100)
	private long caudal;
	
	
	
/*
 * 	GETTERS AND SETTERS
 * */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMasaAcumulada() {
		return masaAcumulada;
	}

	public void setMasaAcumulada(long masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}

	public long getDensidadProducto() {
		return densidadProducto;
	}

	public void setDensidadProducto(long densidadProducto) {
		this.densidadProducto = densidadProducto;
	}

	public long getTemperaturaProducto() {
		return temperaturaProducto;
	}

	public void setTemperaturaProducto(long temperaturaProducto) {
		this.temperaturaProducto = temperaturaProducto;
	}

	public long getCaudal() {
		return caudal;
	}

	public void setCaudal(long caudal) {
		this.caudal = caudal;
	}
	
	
	
}
