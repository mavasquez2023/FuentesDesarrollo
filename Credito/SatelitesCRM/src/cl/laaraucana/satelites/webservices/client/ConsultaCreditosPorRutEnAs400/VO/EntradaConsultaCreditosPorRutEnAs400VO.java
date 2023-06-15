package cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaConsultaCreditosPorRutEnAs400VO extends AbstractEntradaVO {
	private String rut;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public EntradaConsultaCreditosPorRutEnAs400VO(String rut) {
		super();
		this.rut = rut;
	}

	public EntradaConsultaCreditosPorRutEnAs400VO() {
		super();
	}
	
	
	
}
