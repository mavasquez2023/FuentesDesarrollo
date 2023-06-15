package cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaConsultaFolioFormulario24 extends AbstractSalidaVO{
	
	private String fechaOriginacionDeCredito;
	private String folioFormulario24;
	
	public String getFechaOriginacionDeCredito() {
		return fechaOriginacionDeCredito;
	}
	public void setFechaOriginacionDeCredito(String fechaOriginacionDeCredito) {
		this.fechaOriginacionDeCredito = fechaOriginacionDeCredito;
	}
	public String getFolioFormulario24() {
		return folioFormulario24;
	}
	public void setFolioFormulario24(String folioFormulario24) {
		this.folioFormulario24 = folioFormulario24;
	}

}
