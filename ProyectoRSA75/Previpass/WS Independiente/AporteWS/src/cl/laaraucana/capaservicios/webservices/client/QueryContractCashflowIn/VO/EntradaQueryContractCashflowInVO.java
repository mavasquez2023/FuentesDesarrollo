package cl.laaraucana.capaservicios.webservices.client.QueryContractCashflowIn.VO;

import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;

public class EntradaQueryContractCashflowInVO extends AbstractEntradaVO {
	private String folioCredito;
	
	public String getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}
	
}
