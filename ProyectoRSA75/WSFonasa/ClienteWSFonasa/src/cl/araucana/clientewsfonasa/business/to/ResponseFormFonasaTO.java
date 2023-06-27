package cl.araucana.clientewsfonasa.business.to;


public class ResponseFormFonasaTO {

	private Short estado;
    private String glosaEstado;
    private Short resultado;
    private String glosaResultado;
    
    public ResponseFormFonasaTO(){}
    
	public ResponseFormFonasaTO(Short estado, String gloEstado, Short resultado, String glosaResultado) {
		this.estado = estado;
		this.glosaEstado = gloEstado;
		this.resultado = resultado;
		this.glosaResultado = glosaResultado;
	}

	/**
	 * @return the estado
	 */
	public Short getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Short estado) {
		this.estado = estado;
	}

	/**
	 * @return the glosaEstado
	 */
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * @param glosaEstado the glosaEstado to set
	 */
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

	/**
	 * @return the resultado
	 */
	public Short getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(Short resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the glosaResultado
	 */
	public String getGlosaResultado() {
		return glosaResultado;
	}

	/**
	 * @param glosaResultado the glosaResultado to set
	 */
	public void setGlosaResultado(String glosaResultado) {
		this.glosaResultado = glosaResultado;
	}
	
}
