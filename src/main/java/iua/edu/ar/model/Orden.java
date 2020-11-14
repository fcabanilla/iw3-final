package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ordenes")
public class Orden implements Serializable {

	private static final long serialVersionUID = -4828422833183668198L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@Column(length = 100, nullable = false)
//	private double numeroOrden;

	@Column(length = 100, nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcion;
	
	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeInicial;

	@Column(length = 100, nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaPrevistaCarga;
	
	@Column(length = 100, nullable = false)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaFinCarga;
	
	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeFijal;
	

	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@OneToOne(cascade = CascadeType.ALL)
	private Producto producto;

	@OneToOne(cascade = CascadeType.ALL)
	private Camion camion;

	@OneToOne(cascade = CascadeType.ALL)
	private Chofer chofer;

	@Column(length = 100)
	private int estado;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DatosCarga ultimosDatosCarga;

	@OneToOne(cascade = CascadeType.ALL)
	private DatosCarga promedioDatosCarga;
	
	@Column(length = 100)
	private String password;
	
	@Column(length = 100)
	private String fecuencia;

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public Date getFechaFinCarga() {
		return fechaFinCarga;
	}

	public void setFechaFinCarga(Date fechaFinCarga) {
		this.fechaFinCarga = fechaFinCarga;
	}

	public Date getFechaRecepcionPesajeFijal() {
		return fechaRecepcionPesajeFijal;
	}

	public void setFechaRecepcionPesajeFijal(Date fechaRecepcionPesajeFijal) {
		this.fechaRecepcionPesajeFijal = fechaRecepcionPesajeFijal;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFecuencia() {
		return fecuencia;
	}

	public void setFecuencia(String fecuencia) {
		this.fecuencia = fecuencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * Cambiar el modelado del preset
	 * */
	@Column(length = 100, nullable = false)
	private double preset;

	@Column(length = 50, nullable = true, unique = true)
	private String codigoExterno;

	
	
	public String checkBasicData() {

		if (getChofer() == null)
			return "El atributo 'Chofer' es obligatorio";

		if (getCamion() == null)
			return "El atributo 'Camion' es obligatorio";

		if (getCliente() == null)
			return "El atributo 'Cliente' es obligatorio";

		if (getProducto() == null)
			return "El atributo 'Producto' es obligatorio";

		if (getCodigoExterno() == null || getCodigoExterno().trim().length() == 0)
			return "El atributo 'orden.codigoExterno' es obligatorio";

		return null;

	}
/*
	public Orden(Orden orden) {
		this.codigoExterno = orden.codigoExterno;

//		this.numeroOrden = orden.numeroOrden;
		this.camion = orden.getCamion();
		this.chofer = orden.getChofer();
		this.cliente = orden.getCliente();
		this.producto = orden.getProducto();
		this.fechaPrevistaCarga = orden.fechaPrevistaCarga;
		this.preset = orden.preset;
		this.estado = 0;

	}
*/
	// Getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public double getNumeroOrden() {
//		return numeroOrden;
//	}
//
//	public void setNumeroOrden(double numeroOrden) {
//		this.numeroOrden = numeroOrden;
//	}

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

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public double getPreset() {
		return preset;
	}

	public void setPreset(double preset) {
		this.preset = preset;
	}

}
