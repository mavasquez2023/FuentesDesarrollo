package cl.laaraucana.botonpago.web.vo;

public class SalidaDeudor {

	private String codError;
	private String mensaje;
	private boolean deudor;

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isDeudor() {
		return deudor;
	}

	public void setDeudor(boolean deudor) {
		this.deudor = deudor;
	}

}
