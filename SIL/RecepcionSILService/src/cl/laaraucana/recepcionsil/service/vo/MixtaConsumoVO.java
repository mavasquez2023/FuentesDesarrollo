package cl.laaraucana.recepcionsil.service.vo;

public class MixtaConsumoVO {
	private String xmlBase64;
	private String codigoRespuesta;
	private String glosa;

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getXmlBase64() {
		return xmlBase64;
	}

	public void setXmlBase64(String xmlBase64) {
		this.xmlBase64 = xmlBase64;
	}
}
