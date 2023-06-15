package cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO;

import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;

public class ConsultaCreditoPreAprobEntradaVO extends AbstractEntradaVO {

	private String rut;
	private String linea;
	private String fecha;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
