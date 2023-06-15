package cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaEarlyPayoffSimulationOUT extends AbstractSalidaVO{
	
	private String contractId;			//  ID Contrato
	private String remainingBalance;	//  Monto Exento (monto insoluto)
	private String paymentDate;			//  Fecha de Pago
	private String montoEpo;			//  Monto EPO
	private String arrearsAmount;		//  Gravámenes (llamar RFC)
	private String waiverRate;			//  Porcentaje de condonación (llamar RFC)
	private String unpaidCharge;		//  Costas de Protesto
	
	public SalidaEarlyPayoffSimulationOUT(){}
	
	public SalidaEarlyPayoffSimulationOUT(String contractId, String remainingBalance, String paymentDate,
			String montoEpo, String arrearsAmount, String waiverRate, String unpaidCharge){
		this.contractId = contractId;
		this.remainingBalance = remainingBalance;
		this.paymentDate = paymentDate;
		this.montoEpo = montoEpo;
		this.arrearsAmount = arrearsAmount;
		this.waiverRate = waiverRate;
		this.unpaidCharge = unpaidCharge;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(String remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getMontoEpo() {
		return montoEpo;
	}

	public void setMontoEpo(String montoEpo) {
		this.montoEpo = montoEpo;
	}

	public String getArrearsAmount() {
		return arrearsAmount;
	}

	public void setArrearsAmount(String arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}

	public String getWaiverRate() {
		return waiverRate;
	}

	public void setWaiverRate(String waiverRate) {
		this.waiverRate = waiverRate;
	}

	public String getUnpaidCharge() {
		return unpaidCharge;
	}

	public void setUnpaidCharge(String unpaidCharge) {
		this.unpaidCharge = unpaidCharge;
	}
}
