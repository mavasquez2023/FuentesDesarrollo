package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_5_VO {
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

	private int sub_Reposo_Prenatal_Autorizadas;
	private int sub_Reposo_Prenatal_Rechazadas;
	private int sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
	private int total_Licencias_Reposo_Prenatal;

	private int sub_Reposo_Postnatal_Autorizadas;
	private int sub_Reposo_Postnatal_Rechazadas;
	private int sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
	private int total_Licencias_Reposo_Postnatal;

	//totales numeros de licencias
	private int totales_Autorizadas;
	private int totales_Rechazadas;
	private int totales_Autorizadas_Reconsideradas;

	//total
	private int totales_Total;

	public CuadroEstadistico_5_VO() {
	}

	public CuadroEstadistico_5_VO(String periodo, String entidad, String codEntidad, int sub_Reposo_Prenatal_Autorizadas, int sub_Reposo_Prenatal_Rechazadas,
			int sub_Reposo_Prenatal_Autorizadas_Reconsideradas, int total_Licencias_Reposo_Prenatal, int sub_Reposo_Postnatal_Autorizadas, int sub_Reposo_Postnatal_Rechazadas,
			int sub_Reposo_Postnatal_Autorizadas_Reconsideradas, int total_Licencias_Reposo_Postnatal, int totales_Autorizadas, int totales_Rechazadas, int totales_Autorizadas_Reconsideradas,
			int totales_Total) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.sub_Reposo_Prenatal_Autorizadas = sub_Reposo_Prenatal_Autorizadas;
		this.sub_Reposo_Prenatal_Rechazadas = sub_Reposo_Prenatal_Rechazadas;
		this.sub_Reposo_Prenatal_Autorizadas_Reconsideradas = sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
		this.total_Licencias_Reposo_Prenatal = total_Licencias_Reposo_Prenatal;
		this.sub_Reposo_Postnatal_Autorizadas = sub_Reposo_Postnatal_Autorizadas;
		this.sub_Reposo_Postnatal_Rechazadas = sub_Reposo_Postnatal_Rechazadas;
		this.sub_Reposo_Postnatal_Autorizadas_Reconsideradas = sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
		this.total_Licencias_Reposo_Postnatal = total_Licencias_Reposo_Postnatal;
		this.totales_Autorizadas = totales_Autorizadas;
		this.totales_Rechazadas = totales_Rechazadas;
		this.totales_Autorizadas_Reconsideradas = totales_Autorizadas_Reconsideradas;
		this.totales_Total = totales_Total;
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

	public int getSub_Reposo_Postnatal_Autorizadas() {
		return sub_Reposo_Postnatal_Autorizadas;
	}

	public void setSub_Reposo_Postnatal_Autorizadas(int sub_Reposo_Postnatal_Autorizadas) {
		this.sub_Reposo_Postnatal_Autorizadas = sub_Reposo_Postnatal_Autorizadas;
	}

	public int getSub_Reposo_Postnatal_Autorizadas_Reconsideradas() {
		return sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
	}

	public void setSub_Reposo_Postnatal_Autorizadas_Reconsideradas(int sub_Reposo_Postnatal_Autorizadas_Reconsideradas) {
		this.sub_Reposo_Postnatal_Autorizadas_Reconsideradas = sub_Reposo_Postnatal_Autorizadas_Reconsideradas;
	}

	public int getSub_Reposo_Postnatal_Rechazadas() {
		return sub_Reposo_Postnatal_Rechazadas;
	}

	public void setSub_Reposo_Postnatal_Rechazadas(int sub_Reposo_Postnatal_Rechazadas) {
		this.sub_Reposo_Postnatal_Rechazadas = sub_Reposo_Postnatal_Rechazadas;
	}

	public int getSub_Reposo_Prenatal_Autorizadas() {
		return sub_Reposo_Prenatal_Autorizadas;
	}

	public void setSub_Reposo_Prenatal_Autorizadas(int sub_Reposo_Prenatal_Autorizadas) {
		this.sub_Reposo_Prenatal_Autorizadas = sub_Reposo_Prenatal_Autorizadas;
	}

	public int getSub_Reposo_Prenatal_Autorizadas_Reconsideradas() {
		return sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
	}

	public void setSub_Reposo_Prenatal_Autorizadas_Reconsideradas(int sub_Reposo_Prenatal_Autorizadas_Reconsideradas) {
		this.sub_Reposo_Prenatal_Autorizadas_Reconsideradas = sub_Reposo_Prenatal_Autorizadas_Reconsideradas;
	}

	public int getSub_Reposo_Prenatal_Rechazadas() {
		return sub_Reposo_Prenatal_Rechazadas;
	}

	public void setSub_Reposo_Prenatal_Rechazadas(int sub_Reposo_Prenatal_Rechazadas) {
		this.sub_Reposo_Prenatal_Rechazadas = sub_Reposo_Prenatal_Rechazadas;
	}

	public int getTotal_Licencias_Reposo_Postnatal() {
		return total_Licencias_Reposo_Postnatal;
	}

	public void setTotal_Licencias_Reposo_Postnatal(int total_Licencias_Reposo_Postnatal) {
		this.total_Licencias_Reposo_Postnatal = total_Licencias_Reposo_Postnatal;
	}

	public int getTotal_Licencias_Reposo_Prenatal() {
		return total_Licencias_Reposo_Prenatal;
	}

	public void setTotal_Licencias_Reposo_Prenatal(int total_Licencias_Reposo_Prenatal) {
		this.total_Licencias_Reposo_Prenatal = total_Licencias_Reposo_Prenatal;
	}

	public int getTotales_Autorizadas() {
		return totales_Autorizadas;
	}

	public void setTotales_Autorizadas(int totales_Autorizadas) {
		this.totales_Autorizadas = totales_Autorizadas;
	}

	public int getTotales_Autorizadas_Reconsideradas() {
		return totales_Autorizadas_Reconsideradas;
	}

	public void setTotales_Autorizadas_Reconsideradas(int totales_Autorizadas_Reconsideradas) {
		this.totales_Autorizadas_Reconsideradas = totales_Autorizadas_Reconsideradas;
	}

	public int getTotales_Rechazadas() {
		return totales_Rechazadas;
	}

	public void setTotales_Rechazadas(int totales_Rechazadas) {
		this.totales_Rechazadas = totales_Rechazadas;
	}

	public int getTotales_Total() {
		return totales_Total;
	}

	public void setTotales_Total(int totales_Total) {
		this.totales_Total = totales_Total;
	}

}
