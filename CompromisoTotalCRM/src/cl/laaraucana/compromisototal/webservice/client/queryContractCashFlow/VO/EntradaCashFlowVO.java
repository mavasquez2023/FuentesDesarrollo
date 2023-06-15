package cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;

public class EntradaCashFlowVO extends AbstractEntradaVO {

	private String numeroCuenta;

	/**
	 * @return el numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            el numeroCuenta a establecer
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

}
