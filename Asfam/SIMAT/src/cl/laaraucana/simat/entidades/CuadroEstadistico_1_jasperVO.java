package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_1_jasperVO {

	private String periodo;
	private String entidad;
	private String codEntidad;
	//Licencias medicas tramitadas segun tipo de licencia medica

	//numero licencias tramitadas
	//tipos de licencia

	//individuales autorizadas 
	private String reposoPrenatal_Lic_SinModificacion;
	private String reposoPrenatal_Lic_Modificadas;
	private String reposoPrenatal_Lic_Reconsideradas;

	private String reposoPostnatal_Lic_SinModificacion;
	private String reposoPostnatal_Lic_Modificadas;
	private String reposoPostnatal_Lic_Reconsideradas;

	private String enferGravNiñoMenor1_Lic_SinModificacion;
	private String enferGravNiñoMenor1_Lic_Modificadas;
	private String enferGravNiñoMenor1_Lic_Reconsideradas;

	//totales autorizadas
	private String totales_Lic_SinModificacion;
	private String totales_Lic_Modificadas;
	private String totales_Lic_Reconsideradas;

	//rechazadas

	private String reposoPrenatal_Lic_Rechazadas;
	private String reposoPostnatal_Lic_Rechazadas;
	private String enferGravNiñoMenor1_Lic_Rechazadas;

	private String totales_Lic_Rechazadas;

	//totales por tipo de licencia

	private String reposoPrenatal_Lic_total;
	private String reposoPostnatal_Lic_total;
	private String enferGravNiñoMenor1_Lic_total;

	private String totales_Lic_total;

	//numero dias Licencias tramitados
	//autorizados por tipo licencia
	private String reposoPrenatal_Dias_Autorizados;
	private String reposoPrenatal_Dias_Reconsiderados;
	private String reposoPrenatal_Dias_Rechazados;

	private String reposoPostnatal_Dias_Autorizados;
	private String reposoPostnatal_Dias_Reconsiderados;
	private String reposoPostnatal_Dias_Rechazados;

	private String enferGravNiñoMenor1_Dias_Autorizados;
	private String enferGravNiñoMenor1_Dias_Reconsiderados;
	private String enferGravNiñoMenor1_Dias_Rechazados;

	//totales dias tramitados
	private String totales_Dias_Autorizados;
	private String totales_Dias_Reconsiderados;
	private String totales_Dias_Rechazados;

	public CuadroEstadistico_1_jasperVO(CuadroEstadistico_1_VO ce1) {
		super();
		this.periodo = ce1.getPeriodo();
		this.entidad = ce1.getEntidad();
		this.codEntidad = ce1.getCodEntidad();
		this.reposoPrenatal_Lic_SinModificacion = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Lic_SinModificacion()));
		this.reposoPrenatal_Lic_Modificadas = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Lic_Modificadas()));
		this.reposoPrenatal_Lic_Reconsideradas = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Lic_Reconsideradas()));
		this.reposoPostnatal_Lic_SinModificacion = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Lic_SinModificacion()));
		this.reposoPostnatal_Lic_Modificadas = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Lic_Modificadas()));
		this.reposoPostnatal_Lic_Reconsideradas = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Lic_Reconsideradas()));
		this.enferGravNiñoMenor1_Lic_SinModificacion = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Lic_SinModificacion()));
		this.enferGravNiñoMenor1_Lic_Modificadas = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Lic_Modificadas()));
		this.enferGravNiñoMenor1_Lic_Reconsideradas = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Lic_Reconsideradas()));
		this.totales_Lic_SinModificacion = this.formatMil(String.valueOf(ce1.getTotales_Lic_SinModificacion()));
		this.totales_Lic_Modificadas = this.formatMil(String.valueOf(ce1.getTotales_Lic_Modificadas()));
		this.totales_Lic_Reconsideradas = this.formatMil(String.valueOf(ce1.getTotales_Lic_Reconsideradas()));
		this.reposoPrenatal_Lic_Rechazadas = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Lic_Rechazadas()));
		this.reposoPostnatal_Lic_Rechazadas = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Lic_Rechazadas()));
		this.enferGravNiñoMenor1_Lic_Rechazadas = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Lic_Rechazadas()));
		this.totales_Lic_Rechazadas = this.formatMil(String.valueOf(ce1.getTotales_Lic_Rechazadas()));
		this.reposoPrenatal_Lic_total = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Lic_total()));
		this.reposoPostnatal_Lic_total = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Lic_total()));
		this.enferGravNiñoMenor1_Lic_total = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Lic_total()));
		this.totales_Lic_total = this.formatMil(String.valueOf(ce1.getTotales_Lic_total()));
		this.reposoPrenatal_Dias_Autorizados = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Dias_Autorizados()));
		this.reposoPrenatal_Dias_Reconsiderados = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Dias_Reconsiderados()));
		this.reposoPrenatal_Dias_Rechazados = this.formatMil(String.valueOf(ce1.getReposoPrenatal_Dias_Rechazados()));
		this.reposoPostnatal_Dias_Autorizados = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Dias_Autorizados()));
		this.reposoPostnatal_Dias_Reconsiderados = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Dias_Reconsiderados()));
		this.reposoPostnatal_Dias_Rechazados = this.formatMil(String.valueOf(ce1.getReposoPostnatal_Dias_Rechazados()));
		this.enferGravNiñoMenor1_Dias_Autorizados = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Dias_Autorizados()));
		this.enferGravNiñoMenor1_Dias_Reconsiderados = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Dias_Reconsiderados()));
		this.enferGravNiñoMenor1_Dias_Rechazados = this.formatMil(String.valueOf(ce1.getEnferGravNiñoMenor1_Dias_Rechazados()));
		this.totales_Dias_Autorizados = this.formatMil(String.valueOf(ce1.getTotales_Dias_Autorizados()));
		this.totales_Dias_Reconsiderados = this.formatMil(String.valueOf(ce1.getTotales_Dias_Reconsiderados()));
		this.totales_Dias_Rechazados = this.formatMil(String.valueOf(ce1.getTotales_Dias_Rechazados()));
	}

	public String getCodEntidad() {
		return codEntidad;
	}

	public String getEnferGravNiñoMenor1_Dias_Autorizados() {
		return enferGravNiñoMenor1_Dias_Autorizados;
	}

	public String getEnferGravNiñoMenor1_Dias_Rechazados() {
		return enferGravNiñoMenor1_Dias_Rechazados;
	}

	public String getEnferGravNiñoMenor1_Dias_Reconsiderados() {
		return enferGravNiñoMenor1_Dias_Reconsiderados;
	}

	public String getEnferGravNiñoMenor1_Lic_Modificadas() {
		return enferGravNiñoMenor1_Lic_Modificadas;
	}

	public String getEnferGravNiñoMenor1_Lic_Rechazadas() {
		return enferGravNiñoMenor1_Lic_Rechazadas;
	}

	public String getEnferGravNiñoMenor1_Lic_Reconsideradas() {
		return enferGravNiñoMenor1_Lic_Reconsideradas;
	}

	public String getEnferGravNiñoMenor1_Lic_SinModificacion() {
		return enferGravNiñoMenor1_Lic_SinModificacion;
	}

	public String getEnferGravNiñoMenor1_Lic_total() {
		return enferGravNiñoMenor1_Lic_total;
	}

	public String getEntidad() {
		return entidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getReposoPostnatal_Dias_Autorizados() {
		return reposoPostnatal_Dias_Autorizados;
	}

	public String getReposoPostnatal_Dias_Rechazados() {
		return reposoPostnatal_Dias_Rechazados;
	}

	public String getReposoPostnatal_Dias_Reconsiderados() {
		return reposoPostnatal_Dias_Reconsiderados;
	}

	public String getReposoPostnatal_Lic_Modificadas() {
		return reposoPostnatal_Lic_Modificadas;
	}

	public String getReposoPostnatal_Lic_Rechazadas() {
		return reposoPostnatal_Lic_Rechazadas;
	}

	public String getReposoPostnatal_Lic_Reconsideradas() {
		return reposoPostnatal_Lic_Reconsideradas;
	}

	public String getReposoPostnatal_Lic_SinModificacion() {
		return reposoPostnatal_Lic_SinModificacion;
	}

	public String getReposoPostnatal_Lic_total() {
		return reposoPostnatal_Lic_total;
	}

	public String getReposoPrenatal_Dias_Autorizados() {
		return reposoPrenatal_Dias_Autorizados;
	}

	public String getReposoPrenatal_Dias_Rechazados() {
		return reposoPrenatal_Dias_Rechazados;
	}

	public String getReposoPrenatal_Dias_Reconsiderados() {
		return reposoPrenatal_Dias_Reconsiderados;
	}

	public String getReposoPrenatal_Lic_Modificadas() {
		return reposoPrenatal_Lic_Modificadas;
	}

	public String getReposoPrenatal_Lic_Rechazadas() {
		return reposoPrenatal_Lic_Rechazadas;
	}

	public String getReposoPrenatal_Lic_Reconsideradas() {
		return reposoPrenatal_Lic_Reconsideradas;
	}

	public String getReposoPrenatal_Lic_SinModificacion() {
		return reposoPrenatal_Lic_SinModificacion;
	}

	public String getReposoPrenatal_Lic_total() {
		return reposoPrenatal_Lic_total;
	}

	public String getTotales_Dias_Autorizados() {
		return totales_Dias_Autorizados;
	}

	public String getTotales_Dias_Rechazados() {
		return totales_Dias_Rechazados;
	}

	public String getTotales_Dias_Reconsiderados() {
		return totales_Dias_Reconsiderados;
	}

	public String getTotales_Lic_Modificadas() {
		return totales_Lic_Modificadas;
	}

	public String getTotales_Lic_Rechazadas() {
		return totales_Lic_Rechazadas;
	}

	public String getTotales_Lic_Reconsideradas() {
		return totales_Lic_Reconsideradas;
	}

	public String getTotales_Lic_SinModificacion() {
		return totales_Lic_SinModificacion;
	}

	public String getTotales_Lic_total() {
		return totales_Lic_total;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}
}
