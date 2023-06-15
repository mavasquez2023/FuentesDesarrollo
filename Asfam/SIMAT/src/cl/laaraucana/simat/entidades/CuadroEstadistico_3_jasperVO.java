package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_3_jasperVO {

	/*
	 * Número de días de subsidio pagados según tipo de subsidio
	 * 
	 */
	//tipos de subsidios 
	//numero de dias subsidios pagados por cada tipo de subsidios.
	private String periodo;
	private String entidad;
	private String codEntidad;

	private String sub_Reposo_Prenatal;
	private String sub_Reposo_Postnatal;
	private String sub_Permiso_Postnatal_Parental;
	private String sub_Permiso_enferm_Grave_niño_menor;
	private String total_Dias_Subsidios_Pagados;

	public CuadroEstadistico_3_jasperVO(CuadroEstadistico_3_VO ce3) {
		super();
		this.periodo = ce3.getPeriodo();
		this.entidad = ce3.getEntidad();
		this.codEntidad = ce3.getCodEntidad();
		this.sub_Reposo_Prenatal = this.formatMil(String.valueOf(ce3.getSub_Reposo_Prenatal()));
		this.sub_Reposo_Postnatal = this.formatMil(String.valueOf(ce3.getSub_Reposo_Postnatal()));
		this.sub_Permiso_Postnatal_Parental = this.formatMil(String.valueOf(ce3.getSub_Permiso_Postnatal_Parental()));
		this.sub_Permiso_enferm_Grave_niño_menor = this.formatMil(String.valueOf(ce3.getSub_Permiso_enferm_Grave_niño_menor()));
		this.total_Dias_Subsidios_Pagados = this.formatMil(String.valueOf(ce3.getTotal_Dias_Subsidios_Pagados()));
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

	public String getTotal_Dias_Subsidios_Pagados() {
		return total_Dias_Subsidios_Pagados;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}
}
