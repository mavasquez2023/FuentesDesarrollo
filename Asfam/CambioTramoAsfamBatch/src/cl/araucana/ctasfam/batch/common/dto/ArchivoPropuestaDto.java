package cl.araucana.ctasfam.batch.common.dto;

public class ArchivoPropuestaDto {
	
	private String tipo;
	private byte [] contenido;
	
	
	public ArchivoPropuestaDto(String tipo, byte [] contenido) {
		super();
		this.tipo = tipo;
		this.contenido = contenido;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public byte [] getContenido() {
		return contenido;
	}
	public void setContenido(byte [] contenido) {
		this.contenido = contenido;
	}
	
	
}
