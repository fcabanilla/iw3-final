package iua.edu.ar.model.dto;

public class MensajeRespuesta {
	private int codigo;
	private String mensaje="OK";

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}