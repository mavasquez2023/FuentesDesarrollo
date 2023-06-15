package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroILF4501_jasperVO {

	private String periodo;
	private String entidad;

	private String rep_Prenatal_Lic_autorizadas;
	private String rep_Prenatal_Subs_Iniciados;
	private String rep_Prenatal_Dias_subPagados;
	private String rep_Prenatal_Personas_Subsidiadas;
	private String rep_Prenatal_Monto_Pagado_Subs;

	private String rep_Postnatal_madre_Lic_autorizadas;
	private String rep_Postnatal_madre_Subs_Iniciados;
	private String rep_Postnatal_madre_Dias_subPagados;
	private String rep_Postnatal_madre_Personas_Subsidiadas;
	private String rep_Postnatal_madre_Monto_Pagado_Subs;

	private String rep_Postnatal_padre_Lic_autorizadas;
	private String rep_Postnatal_padre_Subs_Iniciados;
	private String rep_Postnatal_padre_Dias_subPagados;
	private String rep_Postnatal_padre_Personas_Subsidiadas;
	private String rep_Postnatal_padre_Monto_Pagado_Subs;

	private String per_Parental_madre_Lic_autorizadas;
	private String per_Parental_madre_Subs_Iniciados;
	private String per_Parental_madre_Dias_subPagados;
	private String per_Parental_madre_Personas_Subsidiadas;
	private String per_Parental_madre_Monto_Pagado_Subs;

	private String per_Parental_padre_Lic_autorizadas;
	private String per_Parental_padre_Subs_Iniciados;
	private String per_Parental_padre_Dias_subPagados;
	private String per_Parental_padre_Personas_Subsidiadas;
	private String per_Parental_padre_Monto_Pagado_Subs;

	private String per_EnfHijoMenor1_madre_Lic_autorizadas;
	private String per_EnfHijoMenor1_madre_Subs_Iniciados;
	private String per_EnfHijoMenor1_madre_Dias_subPagados;
	private String per_EnfHijoMenor1_madre_Personas_Subsidiadas;
	private String per_EnfHijoMenor1_madre_Monto_Pagado_Subs;

	private String per_EnfHijoMenor1_padre_Lic_autorizadas;
	private String per_EnfHijoMenor1_padre_Subs_Iniciados;
	private String per_EnfHijoMenor1_padre_Dias_subPagados;
	private String per_EnfHijoMenor1_padre_Personas_Subsidiadas;
	private String per_EnfHijoMenor1_padre_Monto_Pagado_Subs;

	private String total_Lic_autorizadas;
	private String total_Subs_Iniciados;
	private String total_Dias_subPagados;
	private String total_Personas_Subsidiadas;
	private String total_Monto_Pagado_Subs;

	public CuadroILF4501_jasperVO(CuadroILF4501VO cILF4501) {
		super();
		this.periodo = cILF4501.getPeriodo();
		this.entidad = cILF4501.getEntidad();
		this.rep_Prenatal_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getRep_Prenatal_Lic_autorizadas()));
		this.rep_Prenatal_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getRep_Prenatal_Subs_Iniciados()));
		this.rep_Prenatal_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getRep_Prenatal_Dias_subPagados()));
		this.rep_Prenatal_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getRep_Prenatal_Personas_Subsidiadas()));
		this.rep_Prenatal_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getRep_Prenatal_Monto_Pagado_Subs()));
		this.rep_Postnatal_madre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_madre_Lic_autorizadas()));
		this.rep_Postnatal_madre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_madre_Subs_Iniciados()));
		this.rep_Postnatal_madre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_madre_Dias_subPagados()));
		this.rep_Postnatal_madre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_madre_Personas_Subsidiadas()));
		this.rep_Postnatal_madre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_madre_Monto_Pagado_Subs()));
		this.rep_Postnatal_padre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_padre_Lic_autorizadas()));
		this.rep_Postnatal_padre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_padre_Subs_Iniciados()));
		this.rep_Postnatal_padre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_padre_Dias_subPagados()));
		this.rep_Postnatal_padre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_padre_Personas_Subsidiadas()));
		this.rep_Postnatal_padre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getRep_Postnatal_padre_Monto_Pagado_Subs()));
		this.per_Parental_madre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getPer_Parental_madre_Lic_autorizadas()));
		this.per_Parental_madre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getPer_Parental_madre_Subs_Iniciados()));
		this.per_Parental_madre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getPer_Parental_madre_Dias_subPagados()));
		this.per_Parental_madre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getPer_Parental_madre_Personas_Subsidiadas()));
		this.per_Parental_madre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getPer_Parental_madre_Monto_Pagado_Subs()));
		this.per_Parental_padre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getPer_Parental_padre_Lic_autorizadas()));
		this.per_Parental_padre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getPer_Parental_padre_Subs_Iniciados()));
		this.per_Parental_padre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getPer_Parental_padre_Dias_subPagados()));
		this.per_Parental_padre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getPer_Parental_padre_Personas_Subsidiadas()));
		this.per_Parental_padre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getPer_Parental_padre_Monto_Pagado_Subs()));
		this.per_EnfHijoMenor1_madre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_madre_Lic_autorizadas()));
		this.per_EnfHijoMenor1_madre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_madre_Subs_Iniciados()));
		this.per_EnfHijoMenor1_madre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_madre_Dias_subPagados()));
		this.per_EnfHijoMenor1_madre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_madre_Personas_Subsidiadas()));
		this.per_EnfHijoMenor1_madre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_madre_Monto_Pagado_Subs()));
		this.per_EnfHijoMenor1_padre_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_padre_Lic_autorizadas()));
		this.per_EnfHijoMenor1_padre_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_padre_Subs_Iniciados()));
		this.per_EnfHijoMenor1_padre_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_padre_Dias_subPagados()));
		this.per_EnfHijoMenor1_padre_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_padre_Personas_Subsidiadas()));
		this.per_EnfHijoMenor1_padre_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getPer_EnfHijoMenor1_padre_Monto_Pagado_Subs()));
		this.total_Lic_autorizadas = this.formatMil(String.valueOf(cILF4501.getTotal_Lic_autorizadas()));
		this.total_Subs_Iniciados = this.formatMil(String.valueOf(cILF4501.getTotal_Subs_Iniciados()));
		this.total_Dias_subPagados = this.formatMil(String.valueOf(cILF4501.getTotal_Dias_subPagados()));
		this.total_Personas_Subsidiadas = this.formatMil(String.valueOf(cILF4501.getTotal_Personas_Subsidiadas()));
		this.total_Monto_Pagado_Subs = this.formatMil(String.valueOf(cILF4501.getTotal_Monto_Pagado_Subs()));
	}

	public String getEntidad() {
		return entidad;
	}

	public String getPer_EnfHijoMenor1_madre_Dias_subPagados() {
		return per_EnfHijoMenor1_madre_Dias_subPagados;
	}

	public String getPer_EnfHijoMenor1_madre_Lic_autorizadas() {
		return per_EnfHijoMenor1_madre_Lic_autorizadas;
	}

	public String getPer_EnfHijoMenor1_madre_Monto_Pagado_Subs() {
		return per_EnfHijoMenor1_madre_Monto_Pagado_Subs;
	}

	public String getPer_EnfHijoMenor1_madre_Personas_Subsidiadas() {
		return per_EnfHijoMenor1_madre_Personas_Subsidiadas;
	}

	public String getPer_EnfHijoMenor1_madre_Subs_Iniciados() {
		return per_EnfHijoMenor1_madre_Subs_Iniciados;
	}

	public String getPer_EnfHijoMenor1_padre_Dias_subPagados() {
		return per_EnfHijoMenor1_padre_Dias_subPagados;
	}

	public String getPer_EnfHijoMenor1_padre_Lic_autorizadas() {
		return per_EnfHijoMenor1_padre_Lic_autorizadas;
	}

	public String getPer_EnfHijoMenor1_padre_Monto_Pagado_Subs() {
		return per_EnfHijoMenor1_padre_Monto_Pagado_Subs;
	}

	public String getPer_EnfHijoMenor1_padre_Personas_Subsidiadas() {
		return per_EnfHijoMenor1_padre_Personas_Subsidiadas;
	}

	public String getPer_EnfHijoMenor1_padre_Subs_Iniciados() {
		return per_EnfHijoMenor1_padre_Subs_Iniciados;
	}

	public String getPer_Parental_madre_Dias_subPagados() {
		return per_Parental_madre_Dias_subPagados;
	}

	public String getPer_Parental_madre_Lic_autorizadas() {
		return per_Parental_madre_Lic_autorizadas;
	}

	public String getPer_Parental_madre_Monto_Pagado_Subs() {
		return per_Parental_madre_Monto_Pagado_Subs;
	}

	public String getPer_Parental_madre_Personas_Subsidiadas() {
		return per_Parental_madre_Personas_Subsidiadas;
	}

	public String getPer_Parental_madre_Subs_Iniciados() {
		return per_Parental_madre_Subs_Iniciados;
	}

	public String getPer_Parental_padre_Dias_subPagados() {
		return per_Parental_padre_Dias_subPagados;
	}

	public String getPer_Parental_padre_Lic_autorizadas() {
		return per_Parental_padre_Lic_autorizadas;
	}

	public String getPer_Parental_padre_Monto_Pagado_Subs() {
		return per_Parental_padre_Monto_Pagado_Subs;
	}

	public String getPer_Parental_padre_Personas_Subsidiadas() {
		return per_Parental_padre_Personas_Subsidiadas;
	}

	public String getPer_Parental_padre_Subs_Iniciados() {
		return per_Parental_padre_Subs_Iniciados;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getRep_Postnatal_madre_Dias_subPagados() {
		return rep_Postnatal_madre_Dias_subPagados;
	}

	public String getRep_Postnatal_madre_Lic_autorizadas() {
		return rep_Postnatal_madre_Lic_autorizadas;
	}

	public String getRep_Postnatal_madre_Monto_Pagado_Subs() {
		return rep_Postnatal_madre_Monto_Pagado_Subs;
	}

	public String getRep_Postnatal_madre_Personas_Subsidiadas() {
		return rep_Postnatal_madre_Personas_Subsidiadas;
	}

	public String getRep_Postnatal_madre_Subs_Iniciados() {
		return rep_Postnatal_madre_Subs_Iniciados;
	}

	public String getRep_Postnatal_padre_Dias_subPagados() {
		return rep_Postnatal_padre_Dias_subPagados;
	}

	public String getRep_Postnatal_padre_Lic_autorizadas() {
		return rep_Postnatal_padre_Lic_autorizadas;
	}

	public String getRep_Postnatal_padre_Monto_Pagado_Subs() {
		return rep_Postnatal_padre_Monto_Pagado_Subs;
	}

	public String getRep_Postnatal_padre_Personas_Subsidiadas() {
		return rep_Postnatal_padre_Personas_Subsidiadas;
	}

	public String getRep_Postnatal_padre_Subs_Iniciados() {
		return rep_Postnatal_padre_Subs_Iniciados;
	}

	public String getRep_Prenatal_Dias_subPagados() {
		return rep_Prenatal_Dias_subPagados;
	}

	public String getRep_Prenatal_Lic_autorizadas() {
		return rep_Prenatal_Lic_autorizadas;
	}

	public String getRep_Prenatal_Monto_Pagado_Subs() {
		return rep_Prenatal_Monto_Pagado_Subs;
	}

	public String getRep_Prenatal_Personas_Subsidiadas() {
		return rep_Prenatal_Personas_Subsidiadas;
	}

	public String getRep_Prenatal_Subs_Iniciados() {
		return rep_Prenatal_Subs_Iniciados;
	}

	public String getTotal_Dias_subPagados() {
		return total_Dias_subPagados;
	}

	public String getTotal_Lic_autorizadas() {
		return total_Lic_autorizadas;
	}

	public String getTotal_Monto_Pagado_Subs() {
		return total_Monto_Pagado_Subs;
	}

	public String getTotal_Personas_Subsidiadas() {
		return total_Personas_Subsidiadas;
	}

	public String getTotal_Subs_Iniciados() {
		return total_Subs_Iniciados;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}

}
