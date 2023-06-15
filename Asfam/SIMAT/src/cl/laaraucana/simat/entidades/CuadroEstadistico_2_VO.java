package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_2_VO {

	/*
	 * Número de subsidios iniciados según tipo de subsidio
	 * 
	 */

	//tipos de subsidios 
	//numero de subsidios iniciados por cada tipo de subsidios.
	private String periodo;
	private String entidad;
	private String codEntidad;

	private int sub_Reposo_Prenatal;
	private int sub_Reposo_Postnatal;
	private int sub_Permiso_Postnatal_Parental;
	private int sub_Permiso_enferm_Grave_niño_menor;

	private int totales_NumSubs_iniciados;

	public CuadroEstadistico_2_VO() {
	}

	public CuadroEstadistico_2_VO(String periodo, String entidad, String codEntidad, int sub_Reposo_Prenatal, int sub_Reposo_Postnatal, int sub_Permiso_Postnatal_Parental,
			int sub_Permiso_enferm_Grave_niño_menor, int totales_NumSubs_iniciados) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.sub_Reposo_Prenatal = sub_Reposo_Prenatal;
		this.sub_Reposo_Postnatal = sub_Reposo_Postnatal;
		this.sub_Permiso_Postnatal_Parental = sub_Permiso_Postnatal_Parental;
		this.sub_Permiso_enferm_Grave_niño_menor = sub_Permiso_enferm_Grave_niño_menor;
		this.totales_NumSubs_iniciados = totales_NumSubs_iniciados;
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

	public int getSub_Permiso_enferm_Grave_niño_menor() {
		return sub_Permiso_enferm_Grave_niño_menor;
	}

	public int getSub_Permiso_Postnatal_Parental() {
		return sub_Permiso_Postnatal_Parental;
	}

	public int getSub_Reposo_Postnatal() {
		return sub_Reposo_Postnatal;
	}

	public int getSub_Reposo_Prenatal() {
		return sub_Reposo_Prenatal;
	}

	public int getTotales_NumSubs_iniciados() {
		return totales_NumSubs_iniciados;
	}

	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad.trim();
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad.trim();
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo.trim();
	}

	public void setSub_Permiso_enferm_Grave_niño_menor(int sub_Permiso_enferm_Grave_niño_menor) {
		this.sub_Permiso_enferm_Grave_niño_menor = sub_Permiso_enferm_Grave_niño_menor;
	}

	public void setSub_Permiso_Postnatal_Parental(int sub_Permiso_Postnatal_Parental) {
		this.sub_Permiso_Postnatal_Parental = sub_Permiso_Postnatal_Parental;
	}

	public void setSub_Reposo_Postnatal(int sub_Reposo_Postnatal) {
		this.sub_Reposo_Postnatal = sub_Reposo_Postnatal;
	}

	public void setSub_Reposo_Prenatal(int sub_Reposo_Prenatal) {
		this.sub_Reposo_Prenatal = sub_Reposo_Prenatal;
	}

	public void setTotales_NumSubs_iniciados(int totales_NumSubs_iniciados) {
		this.totales_NumSubs_iniciados = totales_NumSubs_iniciados;
	}

}
