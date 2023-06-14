package cl.laaraucana.boletaelectronica.vo;

public class OrigenBoletaVo {

	private String NUMBOL;
	private String RUTREC;
	private String NOMREC;
	private String FOLIO;
	private String urlDte;
	private String fechaInicio;
	private String fechaFin;
	private String glosaError;
	private long montoDescuento;

	
	public long getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(long montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getNUMBOL() {
		return NUMBOL;
	}

	public void setNUMBOL(String nUMBOL) {
		NUMBOL = nUMBOL;
	}

	public String getRUTREC() {
		return RUTREC;
	}

	public void setRUTREC(String rUTREC) {
		RUTREC = rUTREC;
	}

	public String getNOMREC() {
		return NOMREC;
	}

	public void setNOMREC(String nOMREC) {
		NOMREC = nOMREC;
	}

	public String getFOLIO() {
		return FOLIO;
	}

	public void setFOLIO(String fOLIO) {
		FOLIO = fOLIO;
	}

	public String getUrlDte() {
		return urlDte;
	}

	public void setUrlDte(String urlDte) {
		this.urlDte = urlDte;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getGlosaError() {
		return glosaError;
	}

	public void setGlosaError(String glosaError) {
		this.glosaError = glosaError;
	}
	
	

}
