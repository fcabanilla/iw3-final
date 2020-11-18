package iua.edu.ar.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import iua.edu.ar.model.dto.*;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Orden", description = "Planilla de carga")
@Entity
@DynamicUpdate
@Table(name = "ordenes")
@NamedNativeQuery(name = "Orden.findByOrdenConciliacion", query = "SELECT " + "o.peso_inicial, \r\n"
        + "o.peso_final,\r\n" + "pdc.promedio_caudal,\r\n" + "pdc.promedio_temperatura,\r\n"
        + "pdc.promedio_densidad\r\n"
        + "FROM ordenes o INNER JOIN promedios_datos_carga pdc ON o.id = pdc.id;", resultSetMapping = "ordenMap")

@SqlResultSetMapping(name = "ordenMap", classes = {
        @ConstructorResult(columns = { @ColumnResult(name = "o.peso_inicial", type = Double.class),
                @ColumnResult(name = "o.peso_final", type = Double.class),
                @ColumnResult(name = "pdc.promedio_temperatura", type = Double.class),
                @ColumnResult(name = "pdc.promedio_densidad", type = Double.class),
                @ColumnResult(name = "pdc.promedio_caudal", type = Double.class) }, targetClass = ConciliacionDTO.class) })


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
	private int estado = 0;
  
	@ApiModelProperty(notes = "Ultimo dato de carga", required = false)
	@JoinColumn(nullable = true)
	@OneToOne(cascade =  CascadeType.ALL)
	private UltimoDatoCarga ultimosDatosCarga;

  	@ApiModelProperty(notes = "Promedio de carga en un lapso de tiempo", required = false)
	@JoinColumn(nullable = true)
	@OneToOne(cascade =  CascadeType.ALL)
	private PromedioDatoCarga promedioDatosCarga;


	
	@ApiModelProperty(notes = "Contraseña autogenerada para cada orden", required = false)
	@Column(length = 100, nullable = true)
	private String password;
	
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

  private int preset;

	@Column(length = 50, nullable = true, unique = true)
	private String codigoExterno;
	

	public void partialUpdate(Orden ordenDB) {
		if(this.getCamion() == null) 
			this.setCamion(ordenDB.getCamion());
		
		if(this.getChofer() == null) 
			this.setChofer(ordenDB.getChofer());
		
		if(this.getCliente() == null) 
			this.setCliente(ordenDB.getCliente());
		
		if(this.getCodigoExterno() == null) 
			this.setCodigoExterno(ordenDB.getCodigoExterno());
		
		if(this.getFechaRecepcion() == null) 
			this.setFechaRecepcion(ordenDB.getFechaRecepcion());
		
		if(this.getPreset() == 0)
			this.setPreset(ordenDB.getPreset());
		
		if(this.getProducto() == null)
			this.setProducto(ordenDB.getProducto());
		
	}
	
	public Boolean checkBasicData() {
		if(this.getCamion() == null) 
			return false;
		
		if(this.getChofer() == null) 
			return false;
		
		if(this.getCliente() == null) 
			return false;
		
		if(this.getCodigoExterno() == null) 
			return false;
		
		if(this.getFechaRecepcion() == null) 
			return false;
		
		if(this.getPreset() == 0)
			return false;
		
		if(this.getProducto() == null)
			return false;
		
		return true;
	}

	public Boolean checkPassword(String password) {
		if(this.getPassword().toString().equals(password))
			return true;
		return false;
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

	public UltimoDatoCarga getUltimosDatosCarga() {
		return ultimosDatosCarga;
	}

	public void setUltimosDatosCarga(UltimoDatoCarga ultimosDatosCarga) {
		this.ultimosDatosCarga = ultimosDatosCarga;
	}

	public PromedioDatoCarga getPromedioDatosCarga() {
		return promedioDatosCarga;
	}

	public void setPromedioDatosCarga(PromedioDatoCarga promedioDatosCarga) {
		this.promedioDatosCarga = promedioDatosCarga;
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

	public int getPreset() {
		return preset;
	}

	public void setPreset(int preset) {
		this.preset = preset;
	}

	
	public Double getPesoInicial() {
		return pesoInicial;
	}

	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	
	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	public Double getPesoFinal() {
		return pesoFinal;
	}

	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}
	
}
