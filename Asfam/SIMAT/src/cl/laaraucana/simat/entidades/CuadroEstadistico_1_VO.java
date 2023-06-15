package cl.laaraucana.simat.entidades;

public class CuadroEstadistico_1_VO {

	private String periodo;
	private String entidad;
	private String codEntidad;
	//Licencias medicas tramitadas segun tipo de licencia medica

	//numero licencias tramitadas
	//tipos de licencia

	//individuales autorizadas 
	private int reposoPrenatal_Lic_SinModificacion;
	private int reposoPrenatal_Lic_Modificadas;
	private int reposoPrenatal_Lic_Reconsideradas;

	private int reposoPostnatal_Lic_SinModificacion;
	private int reposoPostnatal_Lic_Modificadas;
	private int reposoPostnatal_Lic_Reconsideradas;

	private int enferGravNiñoMenor1_Lic_SinModificacion;
	private int enferGravNiñoMenor1_Lic_Modificadas;
	private int enferGravNiñoMenor1_Lic_Reconsideradas;

	//totales autorizadas
	private long totales_Lic_SinModificacion;
	private long totales_Lic_Modificadas;
	private long totales_Lic_Reconsideradas;

	//rechazadas

	private int reposoPrenatal_Lic_Rechazadas;
	private int reposoPostnatal_Lic_Rechazadas;
	private int enferGravNiñoMenor1_Lic_Rechazadas;

	private long totales_Lic_Rechazadas;

	//totales por tipo de licencia

	private int reposoPrenatal_Lic_total;
	private int reposoPostnatal_Lic_total;
	private int enferGravNiñoMenor1_Lic_total;

	private long totales_Lic_total;

	//numero dias Licencias tramitados
	//autorizados por tipo licencia
	private int reposoPrenatal_Dias_Autorizados;
	private int reposoPrenatal_Dias_Reconsiderados;
	private int reposoPrenatal_Dias_Rechazados;

	private int reposoPostnatal_Dias_Autorizados;
	private int reposoPostnatal_Dias_Reconsiderados;
	private int reposoPostnatal_Dias_Rechazados;

	private int enferGravNiñoMenor1_Dias_Autorizados;
	private int enferGravNiñoMenor1_Dias_Reconsiderados;
	private int enferGravNiñoMenor1_Dias_Rechazados;

	//totales dias tramitados
	private long totales_Dias_Autorizados;
	private long totales_Dias_Reconsiderados;
	private long totales_Dias_Rechazados;

	public CuadroEstadistico_1_VO() {
		super();
	}

	public CuadroEstadistico_1_VO(String periodo, String entidad, String codEntidad, int reposoPrenatal_Lic_SinModificacion, int reposoPrenatal_Lic_Modificadas, int reposoPrenatal_Lic_Reconsideradas,
			int reposoPostnatal_Lic_SinModificacion, int reposoPostnatal_Lic_Modificadas, int reposoPostnatal_Lic_Reconsideradas, int enferGravNiñoMenor1_Lic_SinModificacion,
			int enferGravNiñoMenor1_Lic_Modificadas, int enferGravNiñoMenor1_Lic_Reconsideradas, long totales_Lic_SinModificacion, long totales_Lic_Modificadas, long totales_Lic_Reconsideradas,
			int reposoPrenatal_Lic_Rechazadas, int reposoPostnatal_Lic_Rechazadas, int enferGravNiñoMenor1_Lic_Rechazadas, long totales_Lic_Rechazadas, int reposoPrenatal_Lic_total,
			int reposoPostnatal_Lic_total, int enferGravNiñoMenor1_Lic_total, long totales_Lic_total, int reposoPrenatal_Dias_Autorizados, int reposoPrenatal_Dias_Reconsiderados,
			int reposoPrenatal_Dias_Rechazados, int reposoPostnatal_Dias_Autorizados, int reposoPostnatal_Dias_Reconsiderados, int reposoPostnatal_Dias_Rechazados,
			int enferGravNiñoMenor1_Dias_Autorizados, int enferGravNiñoMenor1_Dias_Reconsiderados, int enferGravNiñoMenor1_Dias_Rechazados, long totales_Dias_Autorizados,
			long totales_Dias_Reconsiderados, long totales_Dias_Rechazados) {
		super();
		this.periodo = periodo;
		this.entidad = entidad;
		this.codEntidad = codEntidad;
		this.reposoPrenatal_Lic_SinModificacion = reposoPrenatal_Lic_SinModificacion;
		this.reposoPrenatal_Lic_Modificadas = reposoPrenatal_Lic_Modificadas;
		this.reposoPrenatal_Lic_Reconsideradas = reposoPrenatal_Lic_Reconsideradas;
		this.reposoPostnatal_Lic_SinModificacion = reposoPostnatal_Lic_SinModificacion;
		this.reposoPostnatal_Lic_Modificadas = reposoPostnatal_Lic_Modificadas;
		this.reposoPostnatal_Lic_Reconsideradas = reposoPostnatal_Lic_Reconsideradas;
		this.enferGravNiñoMenor1_Lic_SinModificacion = enferGravNiñoMenor1_Lic_SinModificacion;
		this.enferGravNiñoMenor1_Lic_Modificadas = enferGravNiñoMenor1_Lic_Modificadas;
		this.enferGravNiñoMenor1_Lic_Reconsideradas = enferGravNiñoMenor1_Lic_Reconsideradas;
		this.totales_Lic_SinModificacion = totales_Lic_SinModificacion;
		this.totales_Lic_Modificadas = totales_Lic_Modificadas;
		this.totales_Lic_Reconsideradas = totales_Lic_Reconsideradas;
		this.reposoPrenatal_Lic_Rechazadas = reposoPrenatal_Lic_Rechazadas;
		this.reposoPostnatal_Lic_Rechazadas = reposoPostnatal_Lic_Rechazadas;
		this.enferGravNiñoMenor1_Lic_Rechazadas = enferGravNiñoMenor1_Lic_Rechazadas;
		this.totales_Lic_Rechazadas = totales_Lic_Rechazadas;
		this.reposoPrenatal_Lic_total = reposoPrenatal_Lic_total;
		this.reposoPostnatal_Lic_total = reposoPostnatal_Lic_total;
		this.enferGravNiñoMenor1_Lic_total = enferGravNiñoMenor1_Lic_total;
		this.totales_Lic_total = totales_Lic_total;
		this.reposoPrenatal_Dias_Autorizados = reposoPrenatal_Dias_Autorizados;
		this.reposoPrenatal_Dias_Reconsiderados = reposoPrenatal_Dias_Reconsiderados;
		this.reposoPrenatal_Dias_Rechazados = reposoPrenatal_Dias_Rechazados;
		this.reposoPostnatal_Dias_Autorizados = reposoPostnatal_Dias_Autorizados;
		this.reposoPostnatal_Dias_Reconsiderados = reposoPostnatal_Dias_Reconsiderados;
		this.reposoPostnatal_Dias_Rechazados = reposoPostnatal_Dias_Rechazados;
		this.enferGravNiñoMenor1_Dias_Autorizados = enferGravNiñoMenor1_Dias_Autorizados;
		this.enferGravNiñoMenor1_Dias_Reconsiderados = enferGravNiñoMenor1_Dias_Reconsiderados;
		this.enferGravNiñoMenor1_Dias_Rechazados = enferGravNiñoMenor1_Dias_Rechazados;
		this.totales_Dias_Autorizados = totales_Dias_Autorizados;
		this.totales_Dias_Reconsiderados = totales_Dias_Reconsiderados;
		this.totales_Dias_Rechazados = totales_Dias_Rechazados;
	}

	public String getCodEntidad() {
		return codEntidad;
	}

	public int getEnferGravNiñoMenor1_Dias_Autorizados() {
		return enferGravNiñoMenor1_Dias_Autorizados;
	}

	public int getEnferGravNiñoMenor1_Dias_Rechazados() {
		return enferGravNiñoMenor1_Dias_Rechazados;
	}

	public int getEnferGravNiñoMenor1_Dias_Reconsiderados() {
		return enferGravNiñoMenor1_Dias_Reconsiderados;
	}

	public int getEnferGravNiñoMenor1_Lic_Modificadas() {
		return enferGravNiñoMenor1_Lic_Modificadas;
	}

	public int getEnferGravNiñoMenor1_Lic_Rechazadas() {
		return enferGravNiñoMenor1_Lic_Rechazadas;
	}

	public int getEnferGravNiñoMenor1_Lic_Reconsideradas() {
		return enferGravNiñoMenor1_Lic_Reconsideradas;
	}

	public int getEnferGravNiñoMenor1_Lic_SinModificacion() {
		return enferGravNiñoMenor1_Lic_SinModificacion;
	}

	public int getEnferGravNiñoMenor1_Lic_total() {
		return enferGravNiñoMenor1_Lic_total;
	}

	public String getEntidad() {
		return entidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public int getReposoPostnatal_Dias_Autorizados() {
		return reposoPostnatal_Dias_Autorizados;
	}

	public int getReposoPostnatal_Dias_Rechazados() {
		return reposoPostnatal_Dias_Rechazados;
	}

	public int getReposoPostnatal_Dias_Reconsiderados() {
		return reposoPostnatal_Dias_Reconsiderados;
	}

	public int getReposoPostnatal_Lic_Modificadas() {
		return reposoPostnatal_Lic_Modificadas;
	}

	public int getReposoPostnatal_Lic_Rechazadas() {
		return reposoPostnatal_Lic_Rechazadas;
	}

	public int getReposoPostnatal_Lic_Reconsideradas() {
		return reposoPostnatal_Lic_Reconsideradas;
	}

	public int getReposoPostnatal_Lic_SinModificacion() {
		return reposoPostnatal_Lic_SinModificacion;
	}

	public int getReposoPostnatal_Lic_total() {
		return reposoPostnatal_Lic_total;
	}

	public int getReposoPrenatal_Dias_Autorizados() {
		return reposoPrenatal_Dias_Autorizados;
	}

	public int getReposoPrenatal_Dias_Rechazados() {
		return reposoPrenatal_Dias_Rechazados;
	}

	public int getReposoPrenatal_Dias_Reconsiderados() {
		return reposoPrenatal_Dias_Reconsiderados;
	}

	public int getReposoPrenatal_Lic_Modificadas() {
		return reposoPrenatal_Lic_Modificadas;
	}

	public int getReposoPrenatal_Lic_Rechazadas() {
		return reposoPrenatal_Lic_Rechazadas;
	}

	public int getReposoPrenatal_Lic_Reconsideradas() {
		return reposoPrenatal_Lic_Reconsideradas;
	}

	public int getReposoPrenatal_Lic_SinModificacion() {
		return reposoPrenatal_Lic_SinModificacion;
	}

	public int getReposoPrenatal_Lic_total() {
		return reposoPrenatal_Lic_total;
	}

	public long getTotales_Dias_Autorizados() {
		return totales_Dias_Autorizados;
	}

	public long getTotales_Dias_Rechazados() {
		return totales_Dias_Rechazados;
	}

	public long getTotales_Dias_Reconsiderados() {
		return totales_Dias_Reconsiderados;
	}

	public long getTotales_Lic_Modificadas() {
		return totales_Lic_Modificadas;
	}

	public long getTotales_Lic_Rechazadas() {
		return totales_Lic_Rechazadas;
	}

	public long getTotales_Lic_Reconsideradas() {
		return totales_Lic_Reconsideradas;
	}

	public long getTotales_Lic_SinModificacion() {
		return totales_Lic_SinModificacion;
	}

	public long getTotales_Lic_total() {
		return totales_Lic_total;
	}

	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad.trim();
	}

	public void setEnferGravNiñoMenor1_Dias_Autorizados(int enferGravNiñoMenor1_Dias_Autorizados) {
		this.enferGravNiñoMenor1_Dias_Autorizados = enferGravNiñoMenor1_Dias_Autorizados;
	}

	public void setEnferGravNiñoMenor1_Dias_Rechazados(int enferGravNiñoMenor1_Dias_Rechazados) {
		this.enferGravNiñoMenor1_Dias_Rechazados = enferGravNiñoMenor1_Dias_Rechazados;
	}

	public void setEnferGravNiñoMenor1_Dias_Reconsiderados(int enferGravNiñoMenor1_Dias_Reconsiderados) {
		this.enferGravNiñoMenor1_Dias_Reconsiderados = enferGravNiñoMenor1_Dias_Reconsiderados;
	}

	public void setEnferGravNiñoMenor1_Lic_Modificadas(int enferGravNiñoMenor1_Lic_Modificadas) {
		this.enferGravNiñoMenor1_Lic_Modificadas = enferGravNiñoMenor1_Lic_Modificadas;
	}

	public void setEnferGravNiñoMenor1_Lic_Rechazadas(int enferGravNiñoMenor1_Lic_Rechazadas) {
		this.enferGravNiñoMenor1_Lic_Rechazadas = enferGravNiñoMenor1_Lic_Rechazadas;
	}

	public void setEnferGravNiñoMenor1_Lic_Reconsideradas(int enferGravNiñoMenor1_Lic_Reconsideradas) {
		this.enferGravNiñoMenor1_Lic_Reconsideradas = enferGravNiñoMenor1_Lic_Reconsideradas;
	}

	public void setEnferGravNiñoMenor1_Lic_SinModificacion(int enferGravNiñoMenor1_Lic_SinModificacion) {
		this.enferGravNiñoMenor1_Lic_SinModificacion = enferGravNiñoMenor1_Lic_SinModificacion;
	}

	public void setEnferGravNiñoMenor1_Lic_total(int enferGravNiñoMenor1_Lic_total) {
		this.enferGravNiñoMenor1_Lic_total = enferGravNiñoMenor1_Lic_total;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad.trim();
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo.trim();
	}

	public void setReposoPostnatal_Dias_Autorizados(int reposoPostnatal_Dias_Autorizados) {
		this.reposoPostnatal_Dias_Autorizados = reposoPostnatal_Dias_Autorizados;
	}

	public void setReposoPostnatal_Dias_Rechazados(int reposoPostnatal_Dias_Rechazados) {
		this.reposoPostnatal_Dias_Rechazados = reposoPostnatal_Dias_Rechazados;
	}

	public void setReposoPostnatal_Dias_Reconsiderados(int reposoPostnatal_Dias_Reconsiderados) {
		this.reposoPostnatal_Dias_Reconsiderados = reposoPostnatal_Dias_Reconsiderados;
	}

	public void setReposoPostnatal_Lic_Modificadas(int reposoPostnatal_Lic_Modificadas) {
		this.reposoPostnatal_Lic_Modificadas = reposoPostnatal_Lic_Modificadas;
	}

	public void setReposoPostnatal_Lic_Rechazadas(int reposoPostnatal_Lic_Rechazadas) {
		this.reposoPostnatal_Lic_Rechazadas = reposoPostnatal_Lic_Rechazadas;
	}

	public void setReposoPostnatal_Lic_Reconsideradas(int reposoPostnatal_Lic_Reconsideradas) {
		this.reposoPostnatal_Lic_Reconsideradas = reposoPostnatal_Lic_Reconsideradas;
	}

	public void setReposoPostnatal_Lic_SinModificacion(int reposoPostnatal_Lic_SinModificacion) {
		this.reposoPostnatal_Lic_SinModificacion = reposoPostnatal_Lic_SinModificacion;
	}

	public void setReposoPostnatal_Lic_total(int reposoPostnatal_Lic_total) {
		this.reposoPostnatal_Lic_total = reposoPostnatal_Lic_total;
	}

	public void setReposoPrenatal_Dias_Autorizados(int reposoPrenatal_Dias_Autorizados) {
		this.reposoPrenatal_Dias_Autorizados = reposoPrenatal_Dias_Autorizados;
	}

	public void setReposoPrenatal_Dias_Rechazados(int reposoPrenatal_Dias_Rechazados) {
		this.reposoPrenatal_Dias_Rechazados = reposoPrenatal_Dias_Rechazados;
	}

	public void setReposoPrenatal_Dias_Reconsiderados(int reposoPrenatal_Dias_Reconsiderados) {
		this.reposoPrenatal_Dias_Reconsiderados = reposoPrenatal_Dias_Reconsiderados;
	}

	public void setReposoPrenatal_Lic_Modificadas(int reposoPrenatal_Lic_Modificadas) {
		this.reposoPrenatal_Lic_Modificadas = reposoPrenatal_Lic_Modificadas;
	}

	public void setReposoPrenatal_Lic_Rechazadas(int reposoPrenatal_Lic_Rechazadas) {
		this.reposoPrenatal_Lic_Rechazadas = reposoPrenatal_Lic_Rechazadas;
	}

	public void setReposoPrenatal_Lic_Reconsideradas(int reposoPrenatal_Lic_Reconsideradas) {
		this.reposoPrenatal_Lic_Reconsideradas = reposoPrenatal_Lic_Reconsideradas;
	}

	public void setReposoPrenatal_Lic_SinModificacion(int reposoPrenatal_Lic_SinModificacion) {
		this.reposoPrenatal_Lic_SinModificacion = reposoPrenatal_Lic_SinModificacion;
	}

	public void setReposoPrenatal_Lic_total(int reposoPrenatal_Lic_total) {
		this.reposoPrenatal_Lic_total = reposoPrenatal_Lic_total;
	}

	public void setTotales_Dias_Autorizados(long totales_Dias_Autorizados) {
		this.totales_Dias_Autorizados = totales_Dias_Autorizados;
	}

	public void setTotales_Dias_Rechazados(long totales_Dias_Rechazados) {
		this.totales_Dias_Rechazados = totales_Dias_Rechazados;
	}

	public void setTotales_Dias_Reconsiderados(long totales_Dias_Reconsiderados) {
		this.totales_Dias_Reconsiderados = totales_Dias_Reconsiderados;
	}

	public void setTotales_Lic_Modificadas(long totales_Lic_Modificadas) {
		this.totales_Lic_Modificadas = totales_Lic_Modificadas;
	}

	public void setTotales_Lic_Rechazadas(long totales_Lic_Rechazadas) {
		this.totales_Lic_Rechazadas = totales_Lic_Rechazadas;
	}

	public void setTotales_Lic_Reconsideradas(long totales_Lic_Reconsideradas) {
		this.totales_Lic_Reconsideradas = totales_Lic_Reconsideradas;
	}

	public void setTotales_Lic_SinModificacion(long totales_Lic_SinModificacion) {
		this.totales_Lic_SinModificacion = totales_Lic_SinModificacion;
	}

	public void setTotales_Lic_total(long totales_Lic_total) {
		this.totales_Lic_total = totales_Lic_total;
	}

}
