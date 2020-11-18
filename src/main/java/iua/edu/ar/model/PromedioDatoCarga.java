package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "promedio_datos_carga")
public class PromedioDatoCarga extends DatoCarga implements Serializable {

	public PromedioDatoCarga() {
		super();
	}
	
	public PromedioDatoCarga(Double masaAcumulada, Double densidadProducto, Double temperaturaProducto, Double caudal) {
		super(masaAcumulada, densidadProducto, temperaturaProducto, caudal);
		// TODO Auto-generated constructor stub
	}
	
	public PromedioDatoCarga(Double densidadProducto, Double temperaturaProducto, Double caudal) {
		super(densidadProducto, temperaturaProducto, caudal);

	}

	private static final long serialVersionUID = -3470409984466313024L;
	
}
