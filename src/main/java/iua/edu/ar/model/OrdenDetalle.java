package iua.edu.ar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle extends DatoCarga implements Serializable {

	private static final long serialVersionUID = 1463037298087273309L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_id")
	private Orden orden;

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}
