package cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class SalidaCompContHeaderVO extends AbstractSalidaVO {

	private String contractNumber;
	private String terminated;
	private String whthExtintion;
	private String role;
	private String payer = "";
	private String contractAmount;
	private String contractBegin;
	private String contractEnd;
	private String installmentAmount;
	private String installmentQuantity;
	private String repacta;
	private String reprogramac;
	private String contractCurrency;
	//J-Factory 18-01-21
	private String castigo;

	private String bpAnexo;
	private String rutEmpresa;

	private String nroInscripcion;
	private String insolvencia;

	public SalidaCompContHeaderVO(String contractNumber, String terminated,
			String whthExtintion, String role, String payer,
			String contractAmount, String contractBegin, String contractEnd,
			String installmentAmount, String installmentQuantity,
			String repacta, String reprogramac, String contractCurrency, String castigo,
			String bpAnexo, String rutEmpresa, String nroInscripcion, String insolvencia) {
		super();
		this.contractNumber = contractNumber;
		this.terminated = terminated;
		this.whthExtintion = whthExtintion;
		this.role = role;
		this.payer = payer;
		this.contractAmount = contractAmount;
		this.contractBegin = contractBegin;
		this.contractEnd = contractEnd;
		this.installmentAmount = installmentAmount;
		this.installmentQuantity = installmentQuantity;
		this.repacta = repacta;
		this.reprogramac = reprogramac;
		this.contractCurrency = contractCurrency;
		this.castigo = castigo;
		this.bpAnexo = bpAnexo;
		this.rutEmpresa = rutEmpresa;
		this.nroInscripcion = nroInscripcion;
		this.insolvencia = insolvencia;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getTerminated() {
		return terminated;
	}

	public void setTerminated(String terminated) {
		this.terminated = terminated;
	}

	public String getWhthExtintion() {
		return whthExtintion;
	}

	public void setWhthExtintion(String whthExtintion) {
		this.whthExtintion = whthExtintion;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getContractBegin() {
		return contractBegin;
	}

	public void setContractBegin(String contractBegin) {
		this.contractBegin = contractBegin;
	}

	public String getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(String contractEnd) {
		this.contractEnd = contractEnd;
	}

	public String getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(String installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public String getInstallmentQuantity() {
		return installmentQuantity;
	}

	public void setInstallmentQuantity(String installmentQuantity) {
		this.installmentQuantity = installmentQuantity;
	}

	public String getRepacta() {
		return repacta;
	}

	public void setRepacta(String repacta) {
		this.repacta = repacta;
	}

	public String getReprogramac() {
		return reprogramac;
	}

	public void setReprogramac(String reprogramac) {
		this.reprogramac = reprogramac;
	}

	public String getContractCurrency() {
		return contractCurrency;
	}

	public void setContractCurrency(String contractCurrency) {
		this.contractCurrency = contractCurrency;
	}

	public String getBpAnexo() {
		return bpAnexo;
	}

	public void setBpAnexo(String bpAnexo) {
		this.bpAnexo = bpAnexo;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	public String getNroInscripcion() {
		return nroInscripcion;
	}

	public void setNroInscripcion(String nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	/**
	 * @return the castigo
	 */
	public String getCastigo() {
		return castigo;
	}

	/**
	 * @param castigo the castigo to set
	 */
	public void setCastigo(String castigo) {
		this.castigo = castigo;
	}

	/**
	 * @return the insolvencia
	 */
	public String getInsolvencia() {
		return insolvencia;
	}

	/**
	 * @param insolvencia the insolvencia to set
	 */
	public void setInsolvencia(String insolvencia) {
		this.insolvencia = insolvencia;
	}
	
	
}
