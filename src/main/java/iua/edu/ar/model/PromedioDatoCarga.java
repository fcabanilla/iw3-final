package iua.edu.ar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "promedios_datos_carga")
public class PromedioDatoCarga extends DatoCarga implements Serializable {

	private static final long serialVersionUID = 5851375279067905799L;

	@Column
	private Double promedioTemperatura;

	@Column
	private Double promedioDensidad;

	@Column
	private Double promedioCaudal;

	public Double getPromedioTemperatura() {
		return promedioTemperatura;
	}

	public void setPromedioTemperatura(Double promedioTemperatura) {
		this.promedioTemperatura = promedioTemperatura;
	}

	public Double getPromedioDensidad() {
		return promedioDensidad;
	}

	public void setPromedioDensidad(Double promedioDensidad) {
		this.promedioDensidad = promedioDensidad;
	}

	public Double getPromedioCaudal() {
		return promedioCaudal;
	}

	public void setPromedioCaudal(Double promedioCaudal) {
		this.promedioCaudal = promedioCaudal;
	}

}
