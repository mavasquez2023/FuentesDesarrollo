package cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaQueryLoanBalanceOut extends AbstractEntradaVO {

	private String Rut;
	private String NroContrato;
	private String NroCuota;
	private String FlgMoneda;

	public String getRut() {
		return Rut;
	}

	public void setRut(String rut) {
		Rut = rut;
	}

	public String getNroContrato() {
		return NroContrato;
	}

	public void setNroContrato(String nroContrato) {
		NroContrato = nroContrato;
	}

	public String getNroCuota() {
		return NroCuota;
	}

	public void setNroCuota(String nroCuota) {
		NroCuota = nroCuota;
	}

	public String getFlgMoneda() {
		return FlgMoneda;
	}

	public void setFlgMoneda(String flgMoneda) {
		FlgMoneda = flgMoneda;
	}

}
