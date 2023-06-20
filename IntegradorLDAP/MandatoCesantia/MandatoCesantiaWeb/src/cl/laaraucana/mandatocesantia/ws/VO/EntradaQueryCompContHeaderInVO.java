package cl.laaraucana.mandatocesantia.ws.VO;

import cl.laaraucana.mandatocesantia.vo.AbstractEntradaVO;


public class EntradaQueryCompContHeaderInVO extends AbstractEntradaVO {
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