package cl.laaraucana.simat.entidades;

public class RutaRepositorioVO {
	/*
	 * contiene el nombre del aplano al cual hace referencia.
	 * */
	private String nombrePlano;
	/*
	 * contiene la url del lugra del archivo plano
	 * */
	private String urlPlano;
	/*
	 * contiene el periodo al cual pertenece 
	 * */
	private String periodo;
	/*
	 * contiene la ruta completa.
	 * */
	private String rutaPrincipal;

	public RutaRepositorioVO() {
		super();
	}

	public RutaRepositorioVO(String nombrePlano, String urlPlano, String periodo, String rutaPrincipal) {
		super();
		this.nombrePlano = nombrePlano;
		this.urlPlano = urlPlano;
		this.periodo = periodo;
		this.rutaPrincipal = rutaPrincipal;
	}

	public String getNombrePlano() {
		return nombrePlano;
	}

	public void setNombrePlano(String nombrePlano) {
		this.nombrePlano = nombrePlano;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getRutaPrincipal() {
		return rutaPrincipal;
	}

	public void setRutaPrincipal(String rutaPrincipal) {
		this.rutaPrincipal = rutaPrincipal;
	}

	public String getUrlPlano() {
		return urlPlano;
	}

	public void setUrlPlano(String urlPlano) {
		this.urlPlano = urlPlano;
	}

}
