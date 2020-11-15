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

@Entity
@Table(name = "ordenes")
public class Orden implements Serializable {

	private static final long serialVersionUID = -4828422833183668198L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcion;

	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeInicial;

	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaPrevistaCarga;

	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaFinCarga;

	@Column(length = 100, nullable = true)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date fechaRecepcionPesajeFijal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "camion_id")
	private Camion camion;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "chofer_id")
	private Chofer chofer;

	@Column(length = 100)
	private int estado;

	// Revisar relaciones esquema con bucle de relacion en EE Schema workbench

	/*
	 * @OneToOne(cascade = CascadeType.ALL) private DatosCarga ultimosDatosCarga;
	 * 
	 * @OneToOne(cascade = CascadeType.ALL) private DatosCarga promedioDatosCarga;
	 */

	@Column(length = 100, nullable = true)
	private String password;

	@Column(length = 100, nullable = true)
	private int fecuencia;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFecuencia() {
		return fecuencia;
	}

	public void setFecuencia(int fecuencia) {
		this.fecuencia = fecuencia;
	}

	/*
	 * Cambiar el modelado del preset
	 */

	@Column(length = 100, nullable = true)
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

	// Getters and setters

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
