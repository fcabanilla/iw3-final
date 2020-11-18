package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ultimos_datos_carga")
public class UltimoDatoCarga extends DatoCarga implements Serializable {

	public UltimoDatoCarga() {
		super();
	}
	
	public UltimoDatoCarga(Double masaAcumulada, Double densidadProducto, Double temperaturaProducto,Double caudal) {
		super(masaAcumulada, densidadProducto, temperaturaProducto, caudal);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -3470409984466313024L;

	


	
}
