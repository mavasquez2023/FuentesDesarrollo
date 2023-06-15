package cl.laaraucana.satelites.certificados.creditocancelado.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaDetalleCreditoCanceladoVO extends AbstractEntradaVO{
	private String folioCredito;
	private String tipoCredito;
	
	public String getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	
	
}
