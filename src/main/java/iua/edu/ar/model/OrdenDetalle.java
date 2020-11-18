package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle implements Serializable {

	
	public OrdenDetalle() {
		
	}
	

	public OrdenDetalle(Orden orden, 
			Double masaAcumulada, 
			Double densidadProducto,
			Double temperaturaProducto, 
			Double caudal) {
		super();
		this.orden = orden;
		this.masaAcumulada = masaAcumulada;
		this.densidadProducto = densidadProducto;
		this.temperaturaProducto = temperaturaProducto;
		this.caudal = caudal;
	}

	private static final long serialVersionUID = 1463037298087273309L;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "orden_id")
	private Orden orden;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
	
	@Column(name ="fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
	protected Date fecha;

	@Column(length = 100)
	protected Double masaAcumulada;

	@Column(length = 100)
	protected Double densidadProducto;

	@Column(length = 100)
	protected Double temperaturaProducto;

	@Column(length = 100)
	protected Double caudal;
	
	/*
	 * GETTERS AND SETTERS
	 */

	public Orden getOrden() {
		return orden;
	}


	public void setOrden(Orden orden) {
		this.orden = orden;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Double getMasaAcumulada() {
		return masaAcumulada;
	}


	public void setMasaAcumulada(Double masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}


	public Double getDensidadProducto() {
		return densidadProducto;
	}


	public void setDensidadProducto(Double densidadProducto) {
		this.densidadProducto = densidadProducto;
	}


	public Double getTemperaturaProducto() {
		return temperaturaProducto;
	}


	public void setTemperaturaProducto(Double temperaturaProducto) {
		this.temperaturaProducto = temperaturaProducto;
	}


	public Double getCaudal() {
		return caudal;
	}


	public void setCaudal(Double caudal) {
		this.caudal = caudal;
	}


	
	
	

	
	
}
