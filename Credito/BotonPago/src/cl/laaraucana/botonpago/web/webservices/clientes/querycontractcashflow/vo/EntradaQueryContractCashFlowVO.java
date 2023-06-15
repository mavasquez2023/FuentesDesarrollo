package cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;


public class EntradaQueryContractCashFlowVO extends AbstractEntradaVO {
	private String folioCredito;
	private String incluyeEPO;
	
	public String getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}
	/**
	 * @return the incluyeEPO
	 */
	public String getIncluyeEPO() {
		return incluyeEPO;
	}
	/**
	 * @param incluyeEPO the incluyeEPO to set
	 */
	public void setIncluyeEPO(String incluyeEPO) {
		this.incluyeEPO = incluyeEPO;
	}
	
	
}
