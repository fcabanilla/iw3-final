package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")

public class OrdenDetalle implements Serializable {
	
	private static final long serialVersionUID = 4551249436451185765L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column()
	private double masaAcumulada;
	@Column()
	private double densidad;
	@Column()
	private double temperatura;
	@Column()
	private double caudal;
	@Column()
	private Date fechaHoraMedicion;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id_orden")
	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	private Orden orden;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMasaAcumulada() {
		return masaAcumulada;
	}

	public void setMasaAcumulada(double masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}

	public double getDensidad() {
		return densidad;
	}

	public void setDensidad(double densidad) {
		this.densidad = densidad;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public double getCaudal() {
		return caudal;
	}

	public void setCaudal(double caudal) {
		this.caudal = caudal;
	}

	public Date getFechaHoraMedicion() {
		return fechaHoraMedicion;
	}

	public void setFechaHoraMedicion(Date fechaHoraMedicion) {
		this.fechaHoraMedicion = fechaHoraMedicion;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public OrdenDetalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenDetalle(long id, double masaAcumulada, double densidad, double temperatura, double caudal,
			Date fechaHoraMedicion, Orden orden) {
		super();
		this.masaAcumulada = masaAcumulada;
		this.densidad = densidad;
		this.temperatura = temperatura;
		this.caudal = caudal;
		this.fechaHoraMedicion = fechaHoraMedicion;
		this.orden = orden;
	}

	public String checkBasicData(Orden orden) {
		if (orden.getEstado() == 3)
			return "El camion esta cargado";
		if (orden.getEstado() != 2)
			return "Para comenzar a cargar el camion, la orden debe estar en estado 2";
		if (getCaudal() == 0)
			return "El atributo caudal es obligatorio";
		if (getCaudal() < 0)
			return "El caudal debe ser > 0";
		if (getCaudal() < 3000 || getCaudal() > 5000)
			return "El caudal debe estar entre 3000 y 5000 kg/h";
		if (getMasaAcumulada() == 0)
			return "El atributo masa acumulada es obligatorio";
//		if (getMasaAcumulada() < orden.getUltimaMasaAcumulada())
//			return "La masa acumulada debe ser creciente";
		if (getDensidad() == 0)
			return "El atributo densidad es obligatorio";
		if (getDensidad() < 0 || getDensidad() > 1)
			return "La densidad debe ser entre 0 y 1";
		if (getTemperatura() == 0)
			return "El atributo temperatura es obligatorio";
		if (getTemperatura() < 0 || getTemperatura() > 40)
			return "La temperatura debe ser mayor a 0 y menor que 40 grados.";
		if (orden.getPreset() <= getMasaAcumulada())
			return "Camion cargado";
		return "Cargando camion";

	}
	
	
	
}
