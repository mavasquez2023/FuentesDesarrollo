package cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO;

import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;

public class QuerySimulationEntradaVO extends AbstractEntradaVO {

	private String orgId;
	private String startDate;
	private String endDate;
	private String productId;
	private String creditAmount;
	private String interestRate;
	private String amountZnot;
	private String amountZlte;
	private String amountZsde;
	private String amountZsce;
	private String pensionado;
	private List<PaymentOptionsEntradaVO> paymentOptionsEntradaList = new ArrayList<PaymentOptionsEntradaVO>();

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getAmountZnot() {
		return amountZnot;
	}

	public void setAmountZnot(String amountZnot) {
		this.amountZnot = amountZnot;
	}

	public String getAmountZlte() {
		return amountZlte;
	}

	public void setAmountZlte(String amountZlte) {
		this.amountZlte = amountZlte;
	}

	public String getAmountZsde() {
		return amountZsde;
	}

	public void setAmountZsde(String amountZsde) {
		this.amountZsde = amountZsde;
	}

	public String getAmountZsce() {
		return amountZsce;
	}

	public void setAmountZsce(String amountZsce) {
		this.amountZsce = amountZsce;
	}

	public String getPensionado() {
		return pensionado;
	}

	public void setPensionado(String pensionado) {
		this.pensionado = pensionado;
	}

	public List<PaymentOptionsEntradaVO> getPaymentOptionsEntradaList() {
		return paymentOptionsEntradaList;
	}

	public void setPaymentOptionsEntradaList(List<PaymentOptionsEntradaVO> paymentOptionsEntradaList) {
		this.paymentOptionsEntradaList = paymentOptionsEntradaList;
	}

}
