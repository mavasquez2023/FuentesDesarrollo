package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_3_VO {

	/*
	 * Número de días de subsidio pagados según tipo de subsidio
	 * 
	 */

	//tipos de subsidios 
	//numero de dias subsidios pagados por cada tipo de subsidios.

	private String periodo;
	private String entidad;
	private String codEntidad;

	private int sub_Reposo_Prenatal;
	private int sub_Reposo_Postnatal;
	private int sub_Permiso_Postnatal_Parental;
	private int sub_Permiso_enferm_Grave_niño_menor;
	private long total_Dias_Subsidios_Pagados;

	public CuadroEstadistico_3_VO() {
	}

	public CuadroEstadistico_3_VO(String periodo, String entidad, String codEntidad, int sub_Reposo_Prenatal, int sub_Reposo_Postnatal, int sub_Permiso_Postnatal_Parental,
			int sub_Permiso_enferm_Grave_niño_menor, long total_Dias_Subsidios_Pagados) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.sub_Reposo_Prenatal = sub_Reposo_Prenatal;
		this.sub_Reposo_Postnatal = sub_Reposo_Postnatal;
		this.sub_Permiso_Postnatal_Parental = sub_Permiso_Postnatal_Parental;
		this.sub_Permiso_enferm_Grave_niño_menor = sub_Permiso_enferm_Grave_niño_menor;
		this.total_Dias_Subsidios_Pagados = total_Dias_Subsidios_Pagados;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getSub_Permiso_enferm_Grave_niño_menor() {
		return sub_Permiso_enferm_Grave_niño_menor;
	}

	public void setSub_Permiso_enferm_Grave_niño_menor(int sub_Permiso_enferm_Grave_niño_menor) {
		this.sub_Permiso_enferm_Grave_niño_menor = sub_Permiso_enferm_Grave_niño_menor;
	}

	public int getSub_Permiso_Postnatal_Parental() {
		return sub_Permiso_Postnatal_Parental;
	}

	public void setSub_Permiso_Postnatal_Parental(int sub_Permiso_Postnatal_Parental) {
		this.sub_Permiso_Postnatal_Parental = sub_Permiso_Postnatal_Parental;
	}

	public int getSub_Reposo_Postnatal() {
		return sub_Reposo_Postnatal;
	}

	public void setSub_Reposo_Postnatal(int sub_Reposo_Postnatal) {
		this.sub_Reposo_Postnatal = sub_Reposo_Postnatal;
	}

	public int getSub_Reposo_Prenatal() {
		return sub_Reposo_Prenatal;
	}

	public void setSub_Reposo_Prenatal(int sub_Reposo_Prenatal) {
		this.sub_Reposo_Prenatal = sub_Reposo_Prenatal;
	}

	public long getTotal_Dias_Subsidios_Pagados() {
		return total_Dias_Subsidios_Pagados;
	}

	public void setTotal_Dias_Subsidios_Pagados(long total_Dias_Subsidios_Pagados) {
		this.total_Dias_Subsidios_Pagados = total_Dias_Subsidios_Pagados;
	}

}
