package iua.edu.ar.model.dto;

import java.io.Serializable;

import iua.edu.ar.model.PromedioDatoCarga;
import iua.edu.ar.model.UltimoDatoCarga;

public class ConciliacionDTO implements Serializable {

    private static final long serialVersionUID = 8012327743097632921L;

    public ConciliacionDTO(Double pesoInicial, Double pesoFinal, PromedioDatoCarga promedioDatoCarga, UltimoDatoCarga ultimoDatoCarga) {
        super();
        this.pesoInicial = pesoInicial;
        this.pesoFinal = pesoFinal;
        this.promedioTemperatura = promedioDatoCarga.getTemperaturaProducto();
        this.promedioCaudal = promedioDatoCarga.getCaudal();
        this.promedioDensidad = promedioDatoCarga.getDensidadProducto();
        this.productoCargado = ultimoDatoCarga.getMasaAcumulada();
        this.netoPorBalanza = pesoFinal - pesoInicial;
        this.diferenciaEntreBalanzaCaudalímetro = netoPorBalanza - productoCargado;
    }

    private Double pesoInicial;

    private Double pesoFinal;

    private Double productoCargado;

    private Double netoPorBalanza;

    private Double diferenciaEntreBalanzaCaudalímetro;

    private Double promedioTemperatura;

    private Double promedioDensidad;

    private Double promedioCaudal;

    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public Double getPesoInicial() {
		return pesoInicial;
	}

	public Double getProductoCargado() {
		return productoCargado;
	}

	public void setProductoCargado(Double productoCargado) {
		this.productoCargado = productoCargado;
	}

	public Double getNetoPorBalanza() {
		return netoPorBalanza;
	}

	public void setNetoPorBalanza(Double netoPorBalanza) {
		this.netoPorBalanza = netoPorBalanza;
	}

	public Double getDiferenciaEntreBalanzaCaudalímetro() {
		return diferenciaEntreBalanzaCaudalímetro;
	}

	public void setDiferenciaEntreBalanzaCaudalímetro(Double diferenciaEntreBalanzaCaudalímetro) {
		this.diferenciaEntreBalanzaCaudalímetro = diferenciaEntreBalanzaCaudalímetro;
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

	public Double getPromedioTemperatura() {
		return promedioTemperatura;
	}

	public void setPromedioTemperatura(Double promedioTemperatura) {
		this.promedioTemperatura = promedioTemperatura;
	}

	public Double getPromedioCaudal() {
		return promedioCaudal;
	}

	public void setPromedioCaudal(Double promedioCaudal) {
		this.promedioCaudal = promedioCaudal;
	}

	public Double getPromedioDensidad() {
		return promedioDensidad;
	}

	public void setPromedioDensidad(Double promedioDensidad) {
		this.promedioDensidad = promedioDensidad;
	}

	
    
    
}