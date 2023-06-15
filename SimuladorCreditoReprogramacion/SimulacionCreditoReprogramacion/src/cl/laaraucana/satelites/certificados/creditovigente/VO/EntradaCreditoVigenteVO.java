package cl.laaraucana.satelites.certificados.creditovigente.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaCreditoVigenteVO extends AbstractEntradaVO {
	private String rut;
	private String flagTipoCredito;

	public String getFlagTipoCredito() {
		return flagTipoCredito;
	}

	public void setFlagTipoCredito(String flagTipoCredito) {
		this.flagTipoCredito = flagTipoCredito;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}