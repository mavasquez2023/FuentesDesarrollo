package cl.laaraucana.mediopagosilws.service.vo;

public class SalidaVO {

	private DatosVO datos;
	private String codigoRespuesta;
	private String glosaRespuesta;

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getGlosaRespuesta() {
		return glosaRespuesta;
	}

	public void setGlosaRespuesta(String glosaRespuesta) {
		this.glosaRespuesta = glosaRespuesta;
	}

	public DatosVO getDatos() {
		return datos;
	}

	public void setDatos(DatosVO datos) {
		this.datos = datos;
	}
}