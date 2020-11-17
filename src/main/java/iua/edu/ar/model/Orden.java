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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Orden", description = "Planilla de carga")
@Entity
@Table(name = "ordenes")
public class Orden implements Serializable {

	private static final long serialVersionUID = -4828422833183668198L;
	
	@ApiModelProperty(notes = "Identificador de la orden, clave autogenerada", required = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ApiModelProperty(notes = "Fecha/Hora en la que el camion tiene turno", required = false)
	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcion;
	
	@ApiModelProperty(notes = "Fecha/Hora en que se llevo acabo el pesaje inicial con el camion vacio", required = false)
	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeInicial;
	
	@ApiModelProperty(notes = "Fecha/Hora en el que se deberia cargar el camion", required = false)
	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaPrevistaCarga;

	@ApiModelProperty(notes = "Fecha/Hora en la cual dejo de cargarse el camion", required = false)
	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaFinCarga;
	
	@ApiModelProperty(notes = "Fecha/Hora en la cual se peso el camion tras finalizar la carga", required = false)
	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeFinal;
	
	@ApiModelProperty(notes = "Cliente que paga el servicio", required = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ApiModelProperty(notes = "Producto que se cargara", required = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@ApiModelProperty(notes = "Vehiculo al cual se le realizara la carga", required = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camion_id")
	private Camion camion;
	
	@ApiModelProperty(notes = "Conducto del camion al que se le cargara el producto", required = false)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "chofer_id")
	private Chofer chofer;
	
	@ApiModelProperty(notes = "Fase del proceso de carga en la que se encuentra", required = false)
	@Column(length = 100)
	private int estado;
	
	@ApiModelProperty(notes = "Ultimo dato de carga", required = false)
	@Column(length = 100, nullable = true)
	private DatosCarga ultimosDatosCarga;
	
	@ApiModelProperty(notes = "Promedio de carga en un lapso de tiempo", required = false)
	@Column(length = 100, nullable = true)
	private DatosCarga promedioDatosCarga;
	
	@ApiModelProperty(notes = "Contraseña autogenerada para cada orden", required = false)
	@Column(length = 100, nullable = true)
	private int password;
	
	@ApiModelProperty(notes = "Frecuencia en la que se registraran los datos de carga", required = false)
	@Column(length = 100, nullable = true)
	private int fecuencia;
	
	@ApiModelProperty(notes = "Peso del camion vacio", required = false)
	@Column(length = 100, nullable = true)
	private Double pesoInicial;
	
	@ApiModelProperty(notes = "Peso del camion luego de la carga", required = false)
	@Column(length = 100, nullable = true)
	private Double pesoFinal;

	/*
	 * Cambiar el modelado del preset
	 */
	@ApiModelProperty(notes = "Limite a cargar en el camion", required = false)
	@Column(length = 100, nullable = true)
	private double preset;
	

	// Getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaRecepcionPesajeInicial() {
		return fechaRecepcionPesajeInicial;
	}

	public void setFechaRecepcionPesajeInicial(Date fechaRecepcionPesajeInicial) {
		this.fechaRecepcionPesajeInicial = fechaRecepcionPesajeInicial;
	}

	public Date getFechaPrevistaCarga() {
		return fechaPrevistaCarga;
	}

	public void setFechaPrevistaCarga(Date fechaPrevistaCarga) {
		this.fechaPrevistaCarga = fechaPrevistaCarga;
	}

	public Date getFechaFinCarga() {
		return fechaFinCarga;
	}

	public void setFechaFinCarga(Date fechaFinCarga) {
		this.fechaFinCarga = fechaFinCarga;
	}

	public Date getFechaRecepcionPesajeFinal() {
		return fechaRecepcionPesajeFinal;
	}

	public void setFechaRecepcionPesajeFinal(Date fechaRecepcionPesajeFinal) {
		this.fechaRecepcionPesajeFinal = fechaRecepcionPesajeFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public DatosCarga getUltimosDatosCarga() {
		return ultimosDatosCarga;
	}

	public void setUltimosDatosCarga(DatosCarga ultimosDatosCarga) {
		this.ultimosDatosCarga = ultimosDatosCarga;
	}

	public DatosCarga getPromedioDatosCarga() {
		return promedioDatosCarga;
	}

	public void setPromedioDatosCarga(DatosCarga promedioDatosCarga) {
		this.promedioDatosCarga = promedioDatosCarga;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getFecuencia() {
		return fecuencia;
	}

	public void setFecuencia(int fecuencia) {
		this.fecuencia = fecuencia;
	}

	public double getPreset() {
		return preset;
	}

	public void setPreset(double preset) {
		this.preset = preset;
	}

	
	public Double getPesoInicial() {
		return pesoInicial;
	}

	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	
	public Double getPesoFinal() {
		return pesoFinal;
	}

	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

}
