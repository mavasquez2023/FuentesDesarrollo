package cl.laaraucana.rendicionpagonomina.vo;

public class RespuestaBanco {

	private String codigoRetorno;
	private String numeroNomina;
	private String fechaPagoDesde;
	private String fechaPagoHasta;
	private String glosaError;
	private String archivo;
	private String metodo;
	private String convenio;
	private String plantillaRendicion;

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getNumeroNomina() {
		return numeroNomina;
	}

	public void setNumeroNomina(String numeroNomina) {
		this.numeroNomina = numeroNomina;
	}

	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}

	public String getGlosaError() {
		return glosaError;
	}

	public void setGlosaError(String glosaError) {
		this.glosaError = glosaError;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getPlantillaRendicion() {
		return plantillaRendicion;
	}

	public void setPlantillaRendicion(String plantillaRendicion) {
		this.plantillaRendicion = plantillaRendicion;
	}
	
	
	

}
