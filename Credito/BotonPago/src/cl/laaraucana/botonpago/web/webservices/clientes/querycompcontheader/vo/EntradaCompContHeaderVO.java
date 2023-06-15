package cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;

public class EntradaCompContHeaderVO extends AbstractEntradaVO {
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