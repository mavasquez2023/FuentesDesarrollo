package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_5_jasperVO {
	/*
	 * Licencias médicas tramitadas por subsidios a mujeres sin contrato vigente según 
	 * tipo de licencia médica	
	 * 
	 */

	/*
	 * OBSERVACIONES: 
	 * La CCAF La Araucana no trabaja con subsidios a mujeres sin contrato vigente 
	 * por lo cual todos los valores del cuadro 5 corresponden a cero.
	 * */

	//tipos de licencia

	private String periodo;
	private String entidad;
	private String codEntidad;

	private String sub_Reposo_Prenatal_Autorizadas;
	private String sub_Reposo_Prenatal_Rechazadas;
	private String sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
	private String total_Licencias_Reposo_Prenatal;

	private String sub_Reposo_Postnatal_Autorizadas;
	private String sub_Reposo_Postnatal_Rechazadas;
	private String sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
	private String total_Licencias_Reposo_Postnatal;

	//totales numeros de licencias
	private String totales_Autorizadas;
	private String totales_Rechazadas;
	private String totales_Autorizadas_Reconsideradas;

	//total
	private String totales_Total;

	public CuadroEstadistico_5_jasperVO(CuadroEstadistico_5_VO ce5) {
		super();
		this.periodo = ce5.getPeriodo();
		this.entidad = ce5.getEntidad();
		this.codEntidad = ce5.getCodEntidad();
		this.sub_Reposo_Prenatal_Autorizadas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Prenatal_Autorizadas()));
		this.sub_Reposo_Prenatal_Rechazadas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Prenatal_Rechazadas()));
		this.sub_Reposo_Prenatal_Autorizadas_Reconsideradas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Prenatal_Autorizadas_Reconsideradas()));
		this.total_Licencias_Reposo_Prenatal = this.formatMil(String.valueOf(ce5.getTotal_Licencias_Reposo_Prenatal()));
		this.sub_Reposo_Postnatal_Autorizadas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Postnatal_Autorizadas()));
		this.sub_Reposo_Postnatal_Rechazadas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Postnatal_Rechazadas()));
		this.sub_Reposo_Postnatal_Autorizadas_Reconsideradas = this.formatMil(String.valueOf(ce5.getSub_Reposo_Postnatal_Autorizadas_Reconsideradas()));
		this.total_Licencias_Reposo_Postnatal = this.formatMil(String.valueOf(ce5.getTotal_Licencias_Reposo_Postnatal()));
		this.totales_Autorizadas = this.formatMil(String.valueOf(ce5.getTotales_Autorizadas()));
		this.totales_Rechazadas = this.formatMil(String.valueOf(ce5.getTotales_Rechazadas()));
		this.totales_Autorizadas_Reconsideradas = this.formatMil(String.valueOf(ce5.getTotales_Autorizadas_Reconsideradas()));
		this.totales_Total = this.formatMil(String.valueOf(ce5.getTotales_Total()));
	}

	public String getCodEntidad() {
		return codEntidad;
	}

	public String getEntidad() {
		return entidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getSub_Reposo_Postnatal_Autorizadas() {
		return sub_Reposo_Postnatal_Autorizadas;
	}

	public String getSub_Reposo_Postnatal_Autorizadas_Reconsideradas() {
		return sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
	}

	public String getSub_Reposo_Postnatal_Rechazadas() {
		return sub_Reposo_Postnatal_Rechazadas;
	}

	public String getSub_Reposo_Prenatal_Autorizadas() {
		return sub_Reposo_Prenatal_Autorizadas;
	}

	public String getSub_Reposo_Prenatal_Autorizadas_Reconsideradas() {
		return sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
	}

	public String getSub_Reposo_Prenatal_Rechazadas() {
		return sub_Reposo_Prenatal_Rechazadas;
	}

	public String getTotal_Licencias_Reposo_Postnatal() {
		return total_Licencias_Reposo_Postnatal;
	}

	public String getTotal_Licencias_Reposo_Prenatal() {
		return total_Licencias_Reposo_Prenatal;
	}

	public String getTotales_Autorizadas() {
		return totales_Autorizadas;
	}

	public String getTotales_Autorizadas_Reconsideradas() {
		return totales_Autorizadas_Reconsideradas;
	}

	public String getTotales_Rechazadas() {
		return totales_Rechazadas;
	}

	public String getTotales_Total() {
		return totales_Total;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}
}
