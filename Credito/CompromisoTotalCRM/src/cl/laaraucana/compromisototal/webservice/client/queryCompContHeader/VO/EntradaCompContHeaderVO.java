package cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;

public class EntradaCompContHeaderVO extends AbstractEntradaVO {

	private String rut;
	private String creditStatus;

	/**
	 * @return el rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param rut
	 *            el rut a establecer
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return el creditStatus
	 */
	public String getCreditStatus() {
		return creditStatus;
	}

	/**
	 * @param creditStatus
	 *            el creditStatus a establecer
	 */
	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

}
