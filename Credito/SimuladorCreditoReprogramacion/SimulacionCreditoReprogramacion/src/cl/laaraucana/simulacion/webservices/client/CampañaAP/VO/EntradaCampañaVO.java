package cl.laaraucana.simulacion.webservices.client.Campa�aAP.VO;

import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;


public class EntradaCampa�aVO extends AbstractEntradaVO{
	private String rut;
	private String contrato;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

}
