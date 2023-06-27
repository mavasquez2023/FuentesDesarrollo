package cl.laaraucana.cuentabancaria.out;

public class OutTokenSalidaVo {

	private int tipoRespuesta;
	private String descripcion;
	private String token;

	public int getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(int tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
