package cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;

public class EntradaLoanPaymentVO extends AbstractEntradaVO {

	private String amount;
	private String arrearsAmount;
	private String arrearsAmountsource;
	private String arrearsContabtype;
	private String arrearsCurrency;
	private String arrearsCurrencysource;
	private String arrearsId;
	private String arrearsIscred;
	private String currency;
	private String folioTesoreria;
	private String id;
	private String installment;
	private String itemNumber;
	private String oficinaPago;
	private String piType;
	private String postingDate;
	private String pviRut;
	private String valueDate;
	private String compExterno;

	public String getAmount() {
		return amount;
	}

	public String getArrearsAmount() {
		return arrearsAmount;
	}

	public String getArrearsAmountsource() {
		return arrearsAmountsource;
	}

	public String getArrearsContabtype() {
		return arrearsContabtype;
	}

	public String getArrearsCurrency() {
		return arrearsCurrency;
	}

	public String getArrearsCurrencysource() {
		return arrearsCurrencysource;
	}

	public String getArrearsId() {
		return arrearsId;
	}

	public String getArrearsIscred() {
		return arrearsIscred;
	}

	public String getCurrency() {
		return currency;
	}

	public String getFolioTesoreria() {
		return folioTesoreria;
	}

	public String getId() {
		return id;
	}

	public String getInstallment() {
		return installment;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public String getOficinaPago() {
		return oficinaPago;
	}

	public String getPiType() {
		return piType;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public String getPviRut() {
		return pviRut;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setArrearsAmount(String arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}

	public void setArrearsAmountsource(String arrearsAmountsource) {
		this.arrearsAmountsource = arrearsAmountsource;
	}

	public void setArrearsContabtype(String arrearsContabtype) {
		this.arrearsContabtype = arrearsContabtype;
	}

	public void setArrearsCurrency(String arrearsCurrency) {
		this.arrearsCurrency = arrearsCurrency;
	}

	public void setArrearsCurrencysource(String arrearsCurrencysource) {
		this.arrearsCurrencysource = arrearsCurrencysource;
	}

	public void setArrearsId(String arrearsId) {
		this.arrearsId = arrearsId;
	}

	public void setArrearsIscred(String arrearsIscred) {
		this.arrearsIscred = arrearsIscred;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setFolioTesoreria(String folioTesoreria) {
		this.folioTesoreria = folioTesoreria;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setOficinaPago(String oficinaPago) {
		this.oficinaPago = oficinaPago;
	}

	public void setPiType(String piType) {
		this.piType = piType;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public void setPviRut(String pviRut) {
		this.pviRut = pviRut;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getCompExterno() {
		return compExterno;
	}

	public void setCompExterno(String compExterno) {
		this.compExterno = compExterno;
	}

}
