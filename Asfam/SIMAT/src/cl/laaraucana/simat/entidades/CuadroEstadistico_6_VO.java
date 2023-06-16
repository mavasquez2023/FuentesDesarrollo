package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_6_VO {

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

	private int num_sub_Iniciados_Prenatal;
	private int num_sub_Iniciados_Prenatal_Complementario;
	private int num_sub_Iniciados_Postnatal;
	private int num_sub_Iniciados_Permiso_Postnatal_Parental;

	private int num_sub_Pagados_Prenatal;
	private int num_sub_Pagados_Prenatal_Complementario;
	private int num_sub_Pagados_Postnatal;
	private int num_sub_Pagados_Permiso_Postnatal_Parental;

	public CuadroEstadistico_6_VO() {
	}

	public CuadroEstadistico_6_VO(String periodo, String entidad, String codEntidad, int num_sub_Iniciados_Prenatal, int num_sub_Iniciados_Prenatal_Complementario, int num_sub_Iniciados_Postnatal,
			int num_sub_Iniciados_Permiso_Postnatal_Parental, int num_sub_Pagados_Prenatal, int num_sub_Pagados_Prenatal_Complementario, int num_sub_Pagados_Postnatal,
			int num_sub_Pagados_Permiso_Postnatal_Parental) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.num_sub_Iniciados_Prenatal = num_sub_Iniciados_Prenatal;
		this.num_sub_Iniciados_Prenatal_Complementario = num_sub_Iniciados_Prenatal_Complementario;
		this.num_sub_Iniciados_Postnatal = num_sub_Iniciados_Postnatal;
		this.num_sub_Iniciados_Permiso_Postnatal_Parental = num_sub_Iniciados_Permiso_Postnatal_Parental;
		this.num_sub_Pagados_Prenatal = num_sub_Pagados_Prenatal;
		this.num_sub_Pagados_Prenatal_Complementario = num_sub_Pagados_Prenatal_Complementario;
		this.num_sub_Pagados_Postnatal = num_sub_Pagados_Postnatal;
		this.num_sub_Pagados_Permiso_Postnatal_Parental = num_sub_Pagados_Permiso_Postnatal_Parental;
	}

	public String getCodEntidad() {
		return codEntidad;
	}

	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public int getNum_sub_Iniciados_Permiso_Postnatal_Parental() {
		return num_sub_Iniciados_Permiso_Postnatal_Parental;
	}

	public void setNum_sub_Iniciados_Permiso_Postnatal_Parental(int num_sub_Iniciados_Permiso_Postnatal_Parental) {
		this.num_sub_Iniciados_Permiso_Postnatal_Parental = num_sub_Iniciados_Permiso_Postnatal_Parental;
	}

	public int getNum_sub_Iniciados_Postnatal() {
		return num_sub_Iniciados_Postnatal;
	}

	public void setNum_sub_Iniciados_Postnatal(int num_sub_Iniciados_Postnatal) {
		this.num_sub_Iniciados_Postnatal = num_sub_Iniciados_Postnatal;
	}

	public int getNum_sub_Iniciados_Prenatal() {
		return num_sub_Iniciados_Prenatal;
	}

	public void setNum_sub_Iniciados_Prenatal(int num_sub_Iniciados_Prenatal) {
		this.num_sub_Iniciados_Prenatal = num_sub_Iniciados_Prenatal;
	}

	public int getNum_sub_Iniciados_Prenatal_Complementario() {
		return num_sub_Iniciados_Prenatal_Complementario;
	}

	public void setNum_sub_Iniciados_Prenatal_Complementario(int num_sub_Iniciados_Prenatal_Complementario) {
		this.num_sub_Iniciados_Prenatal_Complementario = num_sub_Iniciados_Prenatal_Complementario;
	}

	public int getNum_sub_Pagados_Permiso_Postnatal_Parental() {
		return num_sub_Pagados_Permiso_Postnatal_Parental;
	}

	public void setNum_sub_Pagados_Permiso_Postnatal_Parental(int num_sub_Pagados_Permiso_Postnatal_Parental) {
		this.num_sub_Pagados_Permiso_Postnatal_Parental = num_sub_Pagados_Permiso_Postnatal_Parental;
	}

	public int getNum_sub_Pagados_Postnatal() {
		return num_sub_Pagados_Postnatal;
	}

	public void setNum_sub_Pagados_Postnatal(int num_sub_Pagados_Postnatal) {
		this.num_sub_Pagados_Postnatal = num_sub_Pagados_Postnatal;
	}

	public int getNum_sub_Pagados_Prenatal() {
		return num_sub_Pagados_Prenatal;
	}

	public void setNum_sub_Pagados_Prenatal(int num_sub_Pagados_Prenatal) {
		this.num_sub_Pagados_Prenatal = num_sub_Pagados_Prenatal;
	}

	public int getNum_sub_Pagados_Prenatal_Complementario() {
		return num_sub_Pagados_Prenatal_Complementario;
	}

	public void setNum_sub_Pagados_Prenatal_Complementario(int num_sub_Pagados_Prenatal_Complementario) {
		this.num_sub_Pagados_Prenatal_Complementario = num_sub_Pagados_Prenatal_Complementario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
