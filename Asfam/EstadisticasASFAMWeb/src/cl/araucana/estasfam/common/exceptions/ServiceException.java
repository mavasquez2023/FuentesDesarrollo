package cl.araucana.estasfam.common.exceptions;

public class ServiceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2331108286245911285L;
	private String codigo;
	private String mensaje;
	
	public ServiceException(){}
	
	public ServiceException(String codigo, String mensaje) {
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
