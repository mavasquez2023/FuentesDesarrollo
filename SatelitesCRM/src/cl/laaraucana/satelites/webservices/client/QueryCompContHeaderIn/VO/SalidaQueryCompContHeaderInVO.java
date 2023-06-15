package cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO;

public class SalidaQueryCompContHeaderInVO {
	
	private String ContractNumber;
	private String Terminated;
	private String WithExtintion;
	private String Role;
	private String Payer;
	private String ContractAmount;
	private String ContractBegin;
	private String ContractEnd;
	private String InstallmentAmount;
	private String InstallmentQuantity;
	private String Repacta;
	private String Reprogramac;
	private String ContractCurrency;
	private String Fecha_ult_pago;
	
	
	public SalidaQueryCompContHeaderInVO() {
		super();
	}
	
	public String getContractNumber() {
		return ContractNumber;
	}
	public void setContractNumber(String contractNumber) {
		ContractNumber = contractNumber;
	}
	public String getTerminated() {
		return Terminated;
	}
	public void setTerminated(String terminated) {
		Terminated = terminated;
	}
	public String getWithExtintion() {
		return WithExtintion;
	}
	public void setWithExtintion(String withExtintion) {
		WithExtintion = withExtintion;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getPayer() {
		return Payer;
	}
	public void setPayer(String payer) {
		Payer = payer;
	}
	public String getContractAmount() {
		return ContractAmount;
	}
	public void setContractAmount(String contractAmount) {
		ContractAmount = contractAmount;
	}
	public String getContractBegin() {
		return ContractBegin;
	}
	public void setContractBegin(String contractBegin) {
		ContractBegin = contractBegin;
	}
	public String getContractEnd() {
		return ContractEnd;
	}
	public void setContractEnd(String contractEnd) {
		ContractEnd = contractEnd;
	}
	public String getInstallmentAmount() {
		return InstallmentAmount;
	}
	public void setInstallmentAmount(String installmentAmount) {
		InstallmentAmount = installmentAmount;
	}
	public String getInstallmentQuantity() {
		return InstallmentQuantity;
	}
	public void setInstallmentQuantity(String installmentQuantity) {
		InstallmentQuantity = installmentQuantity;
	}
	public String getRepacta() {
		return Repacta;
	}
	public void setRepacta(String repacta) {
		Repacta = repacta;
	}
	
	
	public String getReprogramac() {
		return Reprogramac;
	}

	public void setReprogramac(String reprogramac) {
		Reprogramac = reprogramac;
	}

	public String getContractCurrency() {
		return ContractCurrency;
	}
	public void setContractCurrency(String contractCurrency) {
		ContractCurrency = contractCurrency;
	}

	/**
	 * @return the fecha_ult_pago
	 */
	public String getFecha_ult_pago() {
		return Fecha_ult_pago;
	}

	/**
	 * @param fecha_ult_pago the fecha_ult_pago to set
	 */
	public void setFecha_ult_pago(String fecha_ult_pago) {
		Fecha_ult_pago = fecha_ult_pago;
	}


}
