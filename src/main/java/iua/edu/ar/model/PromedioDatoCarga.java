package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ultimos_datos_carga")
public class PromedioDatoCarga extends DatoCarga implements Serializable {

	public PromedioDatoCarga(long masaAcumulada, long densidadProducto, long temperaturaProducto, long caudal) {
		super(masaAcumulada, densidadProducto, temperaturaProducto, caudal);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -3470409984466313024L;
	
}