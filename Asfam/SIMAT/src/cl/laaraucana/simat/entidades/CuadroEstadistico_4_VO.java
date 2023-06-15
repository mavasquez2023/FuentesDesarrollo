package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_4_VO {

	private String periodo;
	private String entidad;
	private String codEntidad;
	/*
	 * Subsidios por permiso postnatal parental según extensión del permiso
	 * 
	 */
	//ordenados por fila
	private int sub_PostPar_Iniciados_Jornada_Completa;
	private int sub_PostPar_Iniciados_Jornada_Parcial;
	private int sub_PostPar_Iniciados_Total;

	private int sub_PostPar_Traspasados_Jornada_Completa;
	private int sub_PostPar_Traspasados_Jornada_Parcial;
	private int sub_PostPar_Traspasados_Total;

	//tipo extensiones
	private int sub_PostPar_Pagados_Jornada_Completa;
	private int sub_PostPar_Pagados_Jornada_Parcial;
	private int sub_PostPar_Pagados_Total;

	public CuadroEstadistico_4_VO() {
		super();
	}

	public CuadroEstadistico_4_VO(String periodo, String entidad, String codEntidad, int sub_PostPar_Iniciados_Jornada_Completa, int sub_PostPar_Iniciados_Jornada_Parcial,
			int sub_PostPar_Iniciados_Total, int sub_PostPar_Traspasados_Jornada_Completa, int sub_PostPar_Traspasados_Jornada_Parcial, int sub_PostPar_Traspasados_Total,
			int sub_PostPar_Pagados_Jornada_Completa, int sub_PostPar_Pagados_Jornada_Parcial, int sub_PostPar_Pagados_Total) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.sub_PostPar_Iniciados_Jornada_Completa = sub_PostPar_Iniciados_Jornada_Completa;
		this.sub_PostPar_Iniciados_Jornada_Parcial = sub_PostPar_Iniciados_Jornada_Parcial;
		this.sub_PostPar_Iniciados_Total = sub_PostPar_Iniciados_Total;
		this.sub_PostPar_Traspasados_Jornada_Completa = sub_PostPar_Traspasados_Jornada_Completa;
		this.sub_PostPar_Traspasados_Jornada_Parcial = sub_PostPar_Traspasados_Jornada_Parcial;
		this.sub_PostPar_Traspasados_Total = sub_PostPar_Traspasados_Total;
		this.sub_PostPar_Pagados_Jornada_Completa = sub_PostPar_Pagados_Jornada_Completa;
		this.sub_PostPar_Pagados_Jornada_Parcial = sub_PostPar_Pagados_Jornada_Parcial;
		this.sub_PostPar_Pagados_Total = sub_PostPar_Pagados_Total;
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

	public int getSub_PostPar_Iniciados_Jornada_Completa() {
		return sub_PostPar_Iniciados_Jornada_Completa;
	}

	public void setSub_PostPar_Iniciados_Jornada_Completa(int sub_PostPar_Iniciados_Jornada_Completa) {
		this.sub_PostPar_Iniciados_Jornada_Completa = sub_PostPar_Iniciados_Jornada_Completa;
	}

	public int getSub_PostPar_Iniciados_Jornada_Parcial() {
		return sub_PostPar_Iniciados_Jornada_Parcial;
	}

	public void setSub_PostPar_Iniciados_Jornada_Parcial(int sub_PostPar_Iniciados_Jornada_Parcial) {
		this.sub_PostPar_Iniciados_Jornada_Parcial = sub_PostPar_Iniciados_Jornada_Parcial;
	}

	public int getSub_PostPar_Iniciados_Total() {
		return sub_PostPar_Iniciados_Total;
	}

	public void setSub_PostPar_Iniciados_Total(int sub_PostPar_Iniciados_Total) {
		this.sub_PostPar_Iniciados_Total = sub_PostPar_Iniciados_Total;
	}

	public int getSub_PostPar_Pagados_Jornada_Completa() {
		return sub_PostPar_Pagados_Jornada_Completa;
	}

	public void setSub_PostPar_Pagados_Jornada_Completa(int sub_PostPar_Pagados_Jornada_Completa) {
		this.sub_PostPar_Pagados_Jornada_Completa = sub_PostPar_Pagados_Jornada_Completa;
	}

	public int getSub_PostPar_Pagados_Jornada_Parcial() {
		return sub_PostPar_Pagados_Jornada_Parcial;
	}

	public void setSub_PostPar_Pagados_Jornada_Parcial(int sub_PostPar_Pagados_Jornada_Parcial) {
		this.sub_PostPar_Pagados_Jornada_Parcial = sub_PostPar_Pagados_Jornada_Parcial;
	}

	public int getSub_PostPar_Pagados_Total() {
		return sub_PostPar_Pagados_Total;
	}

	public void setSub_PostPar_Pagados_Total(int sub_PostPar_Pagados_Total) {
		this.sub_PostPar_Pagados_Total = sub_PostPar_Pagados_Total;
	}

	public int getSub_PostPar_Traspasados_Jornada_Completa() {
		return sub_PostPar_Traspasados_Jornada_Completa;
	}

	public void setSub_PostPar_Traspasados_Jornada_Completa(int sub_PostPar_Traspasados_Jornada_Completa) {
		this.sub_PostPar_Traspasados_Jornada_Completa = sub_PostPar_Traspasados_Jornada_Completa;
	}

	public int getSub_PostPar_Traspasados_Jornada_Parcial() {
		return sub_PostPar_Traspasados_Jornada_Parcial;
	}

	public void setSub_PostPar_Traspasados_Jornada_Parcial(int sub_PostPar_Traspasados_Jornada_Parcial) {
		this.sub_PostPar_Traspasados_Jornada_Parcial = sub_PostPar_Traspasados_Jornada_Parcial;
	}

	public int getSub_PostPar_Traspasados_Total() {
		return sub_PostPar_Traspasados_Total;
	}

	public void setSub_PostPar_Traspasados_Total(int sub_PostPar_Traspasados_Total) {
		this.sub_PostPar_Traspasados_Total = sub_PostPar_Traspasados_Total;
	}

}
