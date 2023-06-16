package cl.laaraucana.simat.entidades;

import cl.laaraucana.simat.utiles.ManejoFormatoNumeros;

public class CuadroEstadistico_6_jasperVO {

	/*
	 * Número de subsidios iniciados según tipo de subsidio
	 * 
	 */

	/*
	 * OBSERVACIONES: 
	 * La CCAF La Araucana no trabaja con subsidios a mujeres sin contrato vigente 
	 * por lo cual todos los valores del cuadro 6 corresponden a cero.
	 * 
	 * */

	//tipo subsidios
	private String periodo;
	private String entidad;
	private String codEntidad;

	private String num_sub_Iniciados_Prenatal;
	private String num_sub_Iniciados_Prenatal_Complementario;
	private String num_sub_Iniciados_Postnatal;
	private String num_sub_Iniciados_Permiso_Postnatal_Parental;

	private String num_sub_Pagados_Prenatal;
	private String num_sub_Pagados_Prenatal_Complementario;
	private String num_sub_Pagados_Postnatal;
	private String num_sub_Pagados_Permiso_Postnatal_Parental;

	public CuadroEstadistico_6_jasperVO(CuadroEstadistico_6_VO ce6) {
		super();
		this.periodo = ce6.getPeriodo();
		this.entidad = ce6.getEntidad();
		this.codEntidad = ce6.getCodEntidad();
		this.num_sub_Iniciados_Prenatal = this.formatMil(String.valueOf(ce6.getNum_sub_Iniciados_Prenatal()));
		this.num_sub_Iniciados_Prenatal_Complementario = this.formatMil(String.valueOf(ce6.getNum_sub_Iniciados_Prenatal_Complementario()));
		this.num_sub_Iniciados_Postnatal = this.formatMil(String.valueOf(ce6.getNum_sub_Iniciados_Postnatal()));
		this.num_sub_Iniciados_Permiso_Postnatal_Parental = this.formatMil(String.valueOf(ce6.getNum_sub_Iniciados_Permiso_Postnatal_Parental()));
		this.num_sub_Pagados_Prenatal = this.formatMil(String.valueOf(ce6.getNum_sub_Pagados_Prenatal()));
		this.num_sub_Pagados_Prenatal_Complementario = this.formatMil(String.valueOf(ce6.getNum_sub_Pagados_Prenatal_Complementario()));
		this.num_sub_Pagados_Postnatal = this.formatMil(String.valueOf(ce6.getNum_sub_Pagados_Postnatal()));
		this.num_sub_Pagados_Permiso_Postnatal_Parental = this.formatMil(String.valueOf(ce6.getNum_sub_Pagados_Permiso_Postnatal_Parental()));
	}

	public String getCodEntidad() {
		return codEntidad;
	}

	public String getEntidad() {
		return entidad;
	}

	public String getNum_sub_Iniciados_Permiso_Postnatal_Parental() {
		return num_sub_Iniciados_Permiso_Postnatal_Parental;
	}

	public String getNum_sub_Iniciados_Postnatal() {
		return num_sub_Iniciados_Postnatal;
	}

	public String getNum_sub_Iniciados_Prenatal() {
		return num_sub_Iniciados_Prenatal;
	}

	public String getNum_sub_Iniciados_Prenatal_Complementario() {
		return num_sub_Iniciados_Prenatal_Complementario;
	}

	public String getNum_sub_Pagados_Permiso_Postnatal_Parental() {
		return num_sub_Pagados_Permiso_Postnatal_Parental;
	}

	public String getNum_sub_Pagados_Postnatal() {
		return num_sub_Pagados_Postnatal;
	}

	public String getNum_sub_Pagados_Prenatal() {
		return num_sub_Pagados_Prenatal;
	}

	public String getNum_sub_Pagados_Prenatal_Complementario() {
		return num_sub_Pagados_Prenatal_Complementario;
	}

	public String getPeriodo() {
		return periodo;
	}

	private String formatMil(String number) {
		long n = Long.parseLong(number);
		ManejoFormatoNumeros mfn = new ManejoFormatoNumeros();
		return mfn.getFormatoDecimal(n);
	}
}
