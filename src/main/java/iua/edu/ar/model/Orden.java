package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import iua.edu.ar.model.dto.OrdenDTO;

@Entity
@Table(name = "ordenes")

@NamedNativeQuery(name = "Orden.findByOrdenConciliacion", query = "", resultSetMapping = "ordenMap")

@SqlResultSetMapping(name = "ordenMap", classes = {
		@ConstructorResult(columns = { 
				@ColumnResult(name = "Peso_inicial", type = Double.class),
				@ColumnResult(name = "Peso_final", type = Double.class),
				@ColumnResult(name = "Masa_acumulada", type = long.class),
				@ColumnResult(name = "Promedio_temperatura", type = Double.class),
				@ColumnResult(name = "Promedio_densidad", type = Double.class),
				@ColumnResult(name = "Promedio_caudal", type = Double.class) }, targetClass = OrdenDTO.class) })
public class Orden implements Serializable {

	private static final long serialVersionUID = -4828422833183668198L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaRecepcion;

	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaRecepcionPesajeInicial;

	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaFinCarga;

	@Column(length = 100)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaRecepcionPesajeFinal;

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
	private int estado = 0;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_detalle_id")
	private OrdenDetalle ordenDetalle;

	@OneToOne(cascade = CascadeType.ALL)
	private DatoCarga ultimosDatosCarga;

	@OneToOne(cascade = CascadeType.ALL)
	private DatoCarga promedioDatosCarga;

	@Column(length = 100, nullable = true)
	private int password;

	@Column(length = 100)
	private int fecuencia;

	@Column(length = 100, nullable = true)
	private Double pesoInicial;

	@Column(length = 100, nullable = true)
	private Double pesoFinal;

	/*
	 * Cambiar el modelado del preset
	 */

	@Column(length = 100, nullable = true)
	private int preset;

	@Column(length = 50, nullable = true, unique = true)
	private String codigoExterno;

	public void partialUpdate(Orden ordenDB) {
		if (this.getCamion() == null)
			this.setCamion(ordenDB.getCamion());

		if (this.getChofer() == null)
			this.setChofer(ordenDB.getChofer());

		if (this.getCliente() == null)
			this.setCliente(ordenDB.getCliente());

		if (this.getCodigoExterno() == null)
			this.setCodigoExterno(ordenDB.getCodigoExterno());

		if (this.getFechaRecepcion() == null)
			this.setFechaRecepcion(ordenDB.getFechaRecepcion());

		if (this.getPreset() == 0)
			this.setPreset(ordenDB.getPreset());

		if (this.getProducto() == null)
			this.setProducto(ordenDB.getProducto());
	}

	public boolean checkBasicData() {
		if (this.getCamion() == null)
			return false;

		if (this.getChofer() == null)
			return false;

		if (this.getCliente() == null)
			return false;

		if (this.getCodigoExterno() == null)
			return false;

		if (this.getFechaRecepcion() == null)
			return false;

		if (this.getPreset() == 0)
			return false;

		if (this.getProducto() == null)
			return false;

		return true;
	}

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

	public DatoCarga getUltimosDatosCarga() {
		return ultimosDatosCarga;
	}

	public void setUltimosDatosCarga(DatoCarga ultimosDatosCarga) {
		this.ultimosDatosCarga = ultimosDatosCarga;
	}

	public DatoCarga getPromedioDatosCarga() {
		return promedioDatosCarga;
	}

	public OrdenDetalle getOrdenDetalle() {
		return ordenDetalle;
	}

	public void setOrdenDetalle(OrdenDetalle ordenDetalle) {
		this.ordenDetalle = ordenDetalle;
	}

	public void setPromedioDatosCarga(DatoCarga promedioDatosCarga) {
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

	public int getPreset() {
		return preset;
	}

	public void setPreset(int preset) {
		this.preset = preset;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
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
