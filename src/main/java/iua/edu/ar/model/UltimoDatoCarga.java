package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ultimos_datos_carga")
public class UltimoDatoCarga extends DatoCarga implements Serializable {

	private static final long serialVersionUID = -3470409984466313024L;

}
