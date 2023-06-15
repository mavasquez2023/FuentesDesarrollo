package cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaCreditoARepactarVO extends AbstractEntradaVO {
	
	protected String fechaColocacion;
    protected String periodoRepactacion;
    protected String rut;
	private String flagTipoCredito;

	public String getFlagTipoCredito() {
		return flagTipoCredito;
	}

	public void setFlagTipoCredito(String flagTipoCredito) {
		this.flagTipoCredito = flagTipoCredito;
	}

	public String getFechaColocacion() {
		return fechaColocacion;
	}

	public void setFechaColocacion(String fechaColocacion) {
		this.fechaColocacion = fechaColocacion;
	}

	public String getPeriodoRepactacion() {
		return periodoRepactacion;
	}

	public void setPeriodoRepactacion(String periodoRepactacion) {
		this.periodoRepactacion = periodoRepactacion;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}