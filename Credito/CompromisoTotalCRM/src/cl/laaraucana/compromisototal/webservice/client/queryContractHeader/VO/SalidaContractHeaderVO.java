package cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaContractHeaderVO extends AbstractSalidaVO {
	
	
	private String ContractNumbrer;
	private String comercialLine;
	private String contractEnd;
	private String contractCurrency;
	private String status;
	private String monthlyInterestRate;
	private String lteAmount;
	private String notarialCharge;
	private String interestAmount;
	private String lifeInsurance;
	private String unemploymentInsur;
	private String phonoAsistance;
	private String unpaidInstAmount;
	private String arrearsAmount;
	private String waiverRate;
	private String unpaidCharge;
	private String quantityActiveInst;
	private String quantityUnpaidInst;
	private String remainingBalance;
	private String companyRUT;
	
	
	
	
	public SalidaContractHeaderVO() {
	}




	public SalidaContractHeaderVO(String contractNumbrer, String comercialLine,
			String contractEnd, String contractCurrency, String status,
			String monthlyInterestRate, String lteAmount,
			String notarialCharge, String interestAmount, String lifeInsurance,
			String unemploymentInsur, String phonoAsistance,
			String unpaidInstAmount, String arrearsAmount, String waiverRate,
			String unpaidCharge, String quantityActiveInst,
			String quantityUnpaidInst, String remainingBalance,
			String companyRUT) {
		super();
		ContractNumbrer = contractNumbrer;
		this.comercialLine = comercialLine;
		this.contractEnd = contractEnd;
		this.contractCurrency = contractCurrency;
		this.status = status;
		this.monthlyInterestRate = monthlyInterestRate;
		this.lteAmount = lteAmount;
		this.notarialCharge = notarialCharge;
		this.interestAmount = interestAmount;
		this.lifeInsurance = lifeInsurance;
		this.unemploymentInsur = unemploymentInsur;
		this.phonoAsistance = phonoAsistance;
		this.unpaidInstAmount = unpaidInstAmount;
		this.arrearsAmount = arrearsAmount;
		this.waiverRate = waiverRate;
		this.unpaidCharge = unpaidCharge;
		this.quantityActiveInst = quantityActiveInst;
		this.quantityUnpaidInst = quantityUnpaidInst;
		this.remainingBalance = remainingBalance;
		this.companyRUT = companyRUT;
	}




	public String getContractNumbrer() {
		return ContractNumbrer;
	}




	public void setContractNumbrer(String contractNumbrer) {
		ContractNumbrer = contractNumbrer;
	}




	public String getComercialLine() {
		return comercialLine;
	}




	public void setComercialLine(String comercialLine) {
		this.comercialLine = comercialLine;
	}




	public String getContractEnd() {
		return contractEnd;
	}




	public void setContractEnd(String contractEnd) {
		this.contractEnd = contractEnd;
	}




	public String getContractCurrency() {
		return contractCurrency;
	}




	public void setContractCurrency(String contractCurrency) {
		this.contractCurrency = contractCurrency;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getMonthlyInterestRate() {
		return monthlyInterestRate;
	}




	public void setMonthlyInterestRate(String monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}




	public String getLteAmount() {
		return lteAmount;
	}




	public void setLteAmount(String lteAmount) {
		this.lteAmount = lteAmount;
	}




	public String getNotarialCharge() {
		return notarialCharge;
	}




	public void setNotarialCharge(String notarialCharge) {
		this.notarialCharge = notarialCharge;
	}




	public String getInterestAmount() {
		return interestAmount;
	}




	public void setInterestAmount(String interestAmount) {
		this.interestAmount = interestAmount;
	}




	public String getLifeInsurance() {
		return lifeInsurance;
	}




	public void setLifeInsurance(String lifeInsurance) {
		this.lifeInsurance = lifeInsurance;
	}




	public String getUnemploymentInsur() {
		return unemploymentInsur;
	}




	public void setUnemploymentInsur(String unemploymentInsur) {
		this.unemploymentInsur = unemploymentInsur;
	}




	public String getPhonoAsistance() {
		return phonoAsistance;
	}




	public void setPhonoAsistance(String phonoAsistance) {
		this.phonoAsistance = phonoAsistance;
	}




	public String getUnpaidInstAmount() {
		return unpaidInstAmount;
	}




	public void setUnpaidInstAmount(String unpaidInstAmount) {
		this.unpaidInstAmount = unpaidInstAmount;
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




	public String getQuantityActiveInst() {
		return quantityActiveInst;
	}




	public void setQuantityActiveInst(String quantityActiveInst) {
		this.quantityActiveInst = quantityActiveInst;
	}




	public String getQuantityUnpaidInst() {
		return quantityUnpaidInst;
	}




	public void setQuantityUnpaidInst(String quantityUnpaidInst) {
		this.quantityUnpaidInst = quantityUnpaidInst;
	}




	public String getRemainingBalance() {
		return remainingBalance;
	}




	public void setRemainingBalance(String remainingBalance) {
		this.remainingBalance = remainingBalance;
	}




	public String getCompanyRUT() {
		return companyRUT;
	}




	public void setCompanyRUT(String companyRUT) {
		this.companyRUT = companyRUT;
	}


	
	
}
