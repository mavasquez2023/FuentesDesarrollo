package cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class SalidaInfoProtestVO extends AbstractSalidaVO {

	private String contractNumber;
	private String amount;
	private String flagProtesto;

	
	
	public SalidaInfoProtestVO() {
	}

	public SalidaInfoProtestVO(String contractNumber, String amount, String flagProtesto) {
		super();
		this.contractNumber = contractNumber;
		this.amount = amount;
		this.flagProtesto = flagProtesto;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFlagProtesto() {
		return flagProtesto;
	}

	public void setFlagProtesto(String flagProtesto) {
		this.flagProtesto = flagProtesto;
	}

}
