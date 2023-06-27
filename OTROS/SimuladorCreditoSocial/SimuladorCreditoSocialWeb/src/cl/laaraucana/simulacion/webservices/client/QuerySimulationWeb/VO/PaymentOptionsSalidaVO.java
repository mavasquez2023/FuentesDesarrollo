package cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO;

import java.text.ParseException;

import cl.laaraucana.simulacion.utils.Utils;
import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;

public class PaymentOptionsSalidaVO extends AbstractEntradaVO {

	private String agTerm;
	private String installentAmount;
	private String interestRate;
	private String startDate;
	private String endDate;

	public String getAgTerm() {
		return agTerm;
	}

	public void setAgTerm(String agTerm) {
		this.agTerm = agTerm;
	}

	public String getInstallentAmount() {
		return installentAmount;
	}

	public void setInstallentAmount(String installentAmount) {
		this.installentAmount = installentAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getStartDateFormato() {
		try {
			return Utils.pasaFechaSAPaWEB(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public String getEndDateFormato() {
		try {
			return Utils.pasaFechaSAPaWEB(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	

}
