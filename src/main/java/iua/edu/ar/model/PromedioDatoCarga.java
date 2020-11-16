package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "promedios_datos_carga")
public class PromedioDatoCarga extends DatoCarga implements Serializable {

	private static final long serialVersionUID = 5851375279067905799L;

}
