package cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO;

import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;

public class DetalleMontoQueryLoanBalanceOut extends AbstractSalidaVO {

	private String Concepto;
	private String Monto;
	private String Gravamen;

	public String getConcepto() {
		return Concepto;
	}

	public void setConcepto(String concepto) {
		Concepto = concepto;
	}

	public String getMonto() {
		return Monto;
	}

	public void setMonto(String monto) {
		Monto = monto;
	}

	public String getGravamen() {
		return Gravamen;
	}

	public void setGravamen(String gravamen) {
		Gravamen = gravamen;
	}

}
