package iua.edu.ar.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle extends DatoCarga implements Serializable {

	
	public OrdenDetalle() {
		super();
	}
	public OrdenDetalle(long masaAcumulada, long densidadProducto, long temperaturaProducto, long caudal) {
		super(masaAcumulada, densidadProducto, temperaturaProducto, caudal);
		// TODO Auto-generated constructor stub
	}
	

	private static final long serialVersionUID = 1463037298087273309L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orden_id")
	private Orden orden;

}
