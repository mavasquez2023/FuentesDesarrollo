package cl.laaraucana.satelites.certificados.finiquito.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaFiniquitoVO extends AbstractEntradaVO{
	
	private String rut;
	private String flagTipoCredito;
	
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getFlagTipoCredito() {
		return flagTipoCredito;
	}
	public void setFlagTipoCredito(String flagTipoCredito) {
		this.flagTipoCredito = flagTipoCredito;
	}
	
	public String getRutSinGuion(){
		String[] array = rut.split("-");
		return array[0];
	}
	
}
