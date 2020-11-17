package iua.edu.ar.model.dto;

import java.io.Serializable;

public class OrdenDTO implements Serializable {

	private static final long serialVersionUID = 8012327743097632921L;

	public OrdenDTO(Double pesoInicial, Double pesoFinal, Double promedioTemperatura, Double promedioDensidad,
			Double promedioCaudal, Long masaAcumulada) {

		this.pesoInicial = pesoInicial;
		this.pesoFinal = pesoFinal;
		this.masaAcumulada = masaAcumulada;
		this.promedioTemperatura = promedioTemperatura;
		this.promedioDensidad = promedioDensidad;
		this.promedioCaudal = promedioCaudal;

	}

	private Double pesoInicial;

	private Double pesoFinal;

	private long masaAcumulada;

	private Double promedioTemperatura;

	private Double promedioDensidad;

	private Double promedioCaudal;

	public Double getPesoInicial() {
		return pesoInicial;
	}

	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}

	public Double getPesoFinal() {
		return pesoFinal;
	}

	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

	public long getMasaAcumulada() {
		return masaAcumulada;
	}

	public void setMasaAcumulada(long masaAcumulada) {
		this.masaAcumulada = masaAcumulada;
	}

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
