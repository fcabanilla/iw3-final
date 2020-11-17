package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DatoCarga implements Serializable {
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = -8975778939793530631L;
		
	public DatoCarga(long masaAcumulada, long densidadProducto, long temperaturaProducto,
			long caudal) {
//		super();
		this.masaAcumulada = masaAcumulada;
		this.densidadProducto = densidadProducto;
		this.temperaturaProducto = temperaturaProducto;
		this.caudal = caudal;
	}
	
	public DatoCarga() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@Column(name ="fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
	protected Date fecha;

	@Column(length = 100)
	protected long masaAcumulada;

	@Column(length = 100)
	protected long densidadProducto;

	@Column(length = 100)
	protected long temperaturaProducto;

	@Column(length = 100)
	protected long caudal;

	/*
	 * GETTERS AND SETTERS
	 */
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getCaudal() {
		return caudal;
	}

	public void setCaudal(long caudal) {
		this.caudal = caudal;
	}

}
