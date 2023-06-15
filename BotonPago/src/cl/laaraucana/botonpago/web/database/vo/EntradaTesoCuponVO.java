package cl.laaraucana.botonpago.web.database.vo;

public class EntradaTesoCuponVO {

	private String estadoProceso;
	private String tipoPago;
	private String estadoProcesoDos;
	private String usuarioApp;
	private String glosaAnula;

	public String getUsuarioApp() {
		return usuarioApp;
	}

	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getEstadoProcesoDos() {
		return estadoProcesoDos;
	}

	public void setEstadoProcesoDos(String estadoProcesoDos) {
		this.estadoProcesoDos = estadoProcesoDos;
	}

	public String getGlosaAnula() {
		return glosaAnula;
	}

	public void setGlosaAnula(String glosaAnula) {
		this.glosaAnula = glosaAnula;
	}

}
