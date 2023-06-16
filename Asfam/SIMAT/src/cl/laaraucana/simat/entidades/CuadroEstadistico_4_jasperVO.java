package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_4_jasperVO {

	private String periodo;
	private String entidad;
	private String codEntidad;
	/*
	 * Subsidios por permiso postnatal parental según extensión del permiso
	 * 
	 */
	//ordenados por fila
	private String sub_PostPar_Iniciados_Jornada_Completa;
	private String sub_PostPar_Iniciados_Jornada_Parcial;
	private String sub_PostPar_Iniciados_Total;

	private String sub_PostPar_Traspasados_Jornada_Completa;
	private String sub_PostPar_Traspasados_Jornada_Parcial;
	private String sub_PostPar_Traspasados_Total;

	//tipo extensiones
	private String sub_PostPar_Pagados_Jornada_Completa;
	private String sub_PostPar_Pagados_Jornada_Parcial;
	private String sub_PostPar_Pagados_Total;

	public CuadroEstadistico_4_jasperVO(CuadroEstadistico_4_VO ce4) {
		super();
		this.periodo = ce4.getPeriodo();
		this.entidad = ce4.getEntidad();
		this.codEntidad = ce4.getCodEntidad();
		this.sub_PostPar_Iniciados_Jornada_Completa = this.formatMil(String.valueOf(ce4.getSub_PostPar_Iniciados_Jornada_Completa()));
		this.sub_PostPar_Iniciados_Jornada_Parcial = this.formatMil(String.valueOf(ce4.getSub_PostPar_Iniciados_Jornada_Parcial()));
		this.sub_PostPar_Iniciados_Total = this.formatMil(String.valueOf(ce4.getSub_PostPar_Iniciados_Total()));
		this.sub_PostPar_Traspasados_Jornada_Completa = this.formatMil(String.valueOf(ce4.getSub_PostPar_Traspasados_Jornada_Completa()));
		this.sub_PostPar_Traspasados_Jornada_Parcial = this.formatMil(String.valueOf(ce4.getSub_PostPar_Traspasados_Jornada_Parcial()));
		this.sub_PostPar_Traspasados_Total = this.formatMil(String.valueOf(ce4.getSub_PostPar_Traspasados_Total()));
		this.sub_PostPar_Pagados_Jornada_Completa = this.formatMil(String.valueOf(ce4.getSub_PostPar_Pagados_Jornada_Completa()));
		this.sub_PostPar_Pagados_Jornada_Parcial = this.formatMil(String.valueOf(ce4.getSub_PostPar_Pagados_Jornada_Parcial()));
		this.sub_PostPar_Pagados_Total = this.formatMil(String.valueOf(ce4.getSub_PostPar_Pagados_Total()));
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

	public String getSub_PostPar_Iniciados_Jornada_Completa() {
		return sub_PostPar_Iniciados_Jornada_Completa;
	}

	public String getSub_PostPar_Iniciados_Jornada_Parcial() {
		return sub_PostPar_Iniciados_Jornada_Parcial;
	}

	public String getSub_PostPar_Iniciados_Total() {
		return sub_PostPar_Iniciados_Total;
	}

	public String getSub_PostPar_Pagados_Jornada_Completa() {
		return sub_PostPar_Pagados_Jornada_Completa;
	}

	public String getSub_PostPar_Pagados_Jornada_Parcial() {
		return sub_PostPar_Pagados_Jornada_Parcial;
	}

	public String getSub_PostPar_Pagados_Total() {
		return sub_PostPar_Pagados_Total;
	}

	public String getSub_PostPar_Traspasados_Jornada_Completa() {
		return sub_PostPar_Traspasados_Jornada_Completa;
	}

	public String getSub_PostPar_Traspasados_Jornada_Parcial() {
		return sub_PostPar_Traspasados_Jornada_Parcial;
	}

	public String getSub_PostPar_Traspasados_Total() {
		return sub_PostPar_Traspasados_Total;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}
}
