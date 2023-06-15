package cl.araucana.estasfam.common.exceptions;

public class DaoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826893066344811231L;
	private String codigo;
	private String mensaje;
	
	public DaoException(){}
	
	public DaoException(String codigo, String mensaje) {
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
