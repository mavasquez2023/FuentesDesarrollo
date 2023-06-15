package cl.laaraucana.compromisototal.webservice.models;

public abstract class AbstractSalidaVO {
	private String codigoError;
	private String mensaje;

	public AbstractSalidaVO() {
		super();
		codigoError = "";
		mensaje = "";
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
