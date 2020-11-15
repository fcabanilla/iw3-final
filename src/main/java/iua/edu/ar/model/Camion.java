package iua.edu.ar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "camiones")
public class Camion implements Serializable {

	private static final long serialVersionUID = -1970690932122582652L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 100)
	private String patente;

	@Column(length = 100, nullable = true)
	private String descripcion;

	@Column(length = 100)
	private int cisternado;

	@OneToMany(targetEntity = Orden.class, mappedBy = "camion", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Orden> ordenList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCisternado() {
		return cisternado;
	}

	public void setCisternado(int cisternado) {
		this.cisternado = cisternado;
	}

	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

}
