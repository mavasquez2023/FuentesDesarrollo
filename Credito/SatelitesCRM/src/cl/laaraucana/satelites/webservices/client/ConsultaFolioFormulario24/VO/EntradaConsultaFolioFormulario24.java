package cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaConsultaFolioFormulario24 extends AbstractEntradaVO{
	
	private String fechaOriginacionDeCredito;
	private String flag1 = "000";
	
	public String getFechaOriginacionDeCredito() {
		return fechaOriginacionDeCredito;
	}
	public void setFechaOriginacionDeCredito(String fechaOriginacionDeCredito) {
		this.fechaOriginacionDeCredito = fechaOriginacionDeCredito;
	}
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

}
