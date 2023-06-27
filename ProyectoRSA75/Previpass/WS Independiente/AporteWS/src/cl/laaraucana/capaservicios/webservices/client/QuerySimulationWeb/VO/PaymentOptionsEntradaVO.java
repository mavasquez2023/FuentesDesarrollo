package cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO;

import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;

public class PaymentOptionsEntradaVO extends AbstractEntradaVO {

	private String agTerm;
	private String interestRate;

	public String getAgTerm() {
		return agTerm;
	}

	public void setAgTerm(String agTerm) {
		this.agTerm = agTerm;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

}
