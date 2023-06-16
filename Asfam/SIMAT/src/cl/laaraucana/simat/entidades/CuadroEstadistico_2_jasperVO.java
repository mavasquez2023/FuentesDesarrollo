package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_2_jasperVO {

	/*
	 * Número de subsidios iniciados según tipo de subsidio
	 * 
	 */

	//tipos de subsidios 
	//numero de subsidios iniciados por cada tipo de subsidios.
	private String periodo;
	private String entidad;
	private String codEntidad;

	private String sub_Reposo_Prenatal;
	private String sub_Reposo_Postnatal;
	private String sub_Permiso_Postnatal_Parental;
	private String sub_Permiso_enferm_Grave_niño_menor;

	private String totales_NumSubs_iniciados;

	public CuadroEstadistico_2_jasperVO(CuadroEstadistico_2_VO ce2) {
		this.periodo = ce2.getPeriodo();
		this.entidad = ce2.getEntidad();
		this.codEntidad = ce2.getCodEntidad();
		this.sub_Reposo_Prenatal = this.formatMil(String.valueOf(ce2.getSub_Reposo_Prenatal()));
		this.sub_Reposo_Postnatal = this.formatMil(String.valueOf(ce2.getSub_Reposo_Postnatal()));
		this.sub_Permiso_Postnatal_Parental = this.formatMil(String.valueOf(ce2.getSub_Permiso_Postnatal_Parental()));
		this.sub_Permiso_enferm_Grave_niño_menor = this.formatMil(String.valueOf(ce2.getSub_Permiso_enferm_Grave_niño_menor()));
		this.totales_NumSubs_iniciados = this.formatMil(String.valueOf(ce2.getTotales_NumSubs_iniciados()));
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

	public String getSub_Permiso_enferm_Grave_niño_menor() {
		return sub_Permiso_enferm_Grave_niño_menor;
	}

	public String getSub_Permiso_Postnatal_Parental() {
		return sub_Permiso_Postnatal_Parental;
	}

	public String getSub_Reposo_Postnatal() {
		return sub_Reposo_Postnatal;
	}

	public String getSub_Reposo_Prenatal() {
		return sub_Reposo_Prenatal;
	}

	public String getTotales_NumSubs_iniciados() {
		return totales_NumSubs_iniciados;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}

}
