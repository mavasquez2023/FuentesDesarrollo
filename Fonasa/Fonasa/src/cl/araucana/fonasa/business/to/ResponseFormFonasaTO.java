package cl.araucana.fonasa.business.to;


public class ResponseFormFonasaTO {

	private short estado;
    private String glosaEstado;
    private short codEstado;
    private String comentario;
    private String fechaEvento;

    
    public ResponseFormFonasaTO(){}
    
    public ResponseFormFonasaTO(short estado, String gloEstado, short codEstado, String comentario) {
		this.estado = estado;
		this.glosaEstado = gloEstado;
		this.codEstado = codEstado;
		this.comentario = comentario;
	}
    
	public short getEstado() {
		return estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public String getGlosaEstado() {
		return glosaEstado;
	}

	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

	public short getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(short codEstado) {
		this.codEstado = codEstado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
}
