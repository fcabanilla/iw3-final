package iua.edu.ar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sistema_externo")
public class SistemaExterno {

	public String checkBasicData() {

		if (getOrden() == null)
			return "El atributo 'Orden' es obligatorio";

		if (getOrden().getChofer() == null)
			return "El atributo 'Chofer' es obligatorio";

		if (getOrden().getCamion() == null)
			return "El atributo 'Camion' es obligatorio";

		if (getOrden().getCliente() == null)
			return "El atributo 'Cliente' es obligatorio";

		if (getOrden().getProducto() == null)
			return "El atributo 'Producto' es obligatorio";

		if (getOrden().getCodigoExterno() == null || getOrden().getCodigoExterno().trim().length() == 0)
			return "El atributo 'orden.codigoExterno' es obligatorio";

		return null;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "orden_id")
	private Orden orden;

	@ManyToOne
	@JoinColumn(name = "camion_id")
	private Camion camion;

	@ManyToOne
	@JoinColumn(name = "chofer_id")
	private Chofer chofer;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Column(length = 100, nullable = false)
	private double preset;

	@Column(length = 100)
	private int estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
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

	public double getPreset() {
		return preset;
	}

	public void setPreset(double preset) {
		this.preset = preset;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
