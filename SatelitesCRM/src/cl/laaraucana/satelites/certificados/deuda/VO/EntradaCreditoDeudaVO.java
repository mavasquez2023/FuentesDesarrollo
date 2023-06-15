package cl.laaraucana.satelites.certificados.deuda.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaCreditoDeudaVO extends AbstractEntradaVO {
	
	private String rut;
	private String fechaColocacion;
	private String periodoRepactacion;
	private String flag1;
	private String flag2;
	private String flag3;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
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

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getFlag3() {
		return flag3;
	}

	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}
	
}