package cl.araucana.wslme.common.exception;

public class WSLMEException extends Exception{
	private String codigo;
	private String mensaje;
	
	public WSLMEException(){}
	
	public WSLMEException(String codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
