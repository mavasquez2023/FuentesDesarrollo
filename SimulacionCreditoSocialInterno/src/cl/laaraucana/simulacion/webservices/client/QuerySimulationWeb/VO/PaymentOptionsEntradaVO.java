package cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO;

import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;

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
