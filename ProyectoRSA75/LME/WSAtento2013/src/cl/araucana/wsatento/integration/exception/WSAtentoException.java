package cl.araucana.wsatento.integration.exception;

public class WSAtentoException extends Exception{
	private String codigo;
	private String mensaje;
	
	public WSAtentoException(){}
	
	public WSAtentoException(String codigo, String mensaje) {
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
