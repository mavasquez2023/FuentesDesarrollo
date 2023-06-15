package cl.laaraucana.simat.entidades;

/**
 * clase que permite retornar el resultado de una escritura de archivos
 * con su nombre de archivo escrito y ruta en donde se escribio
 * 
 * **/
public class RespuestaEscrituraVO {

	private boolean estado;
	private String nombreArchivo;
	private String rutaArchivo;

	public RespuestaEscrituraVO() {
	}

	public boolean isEstado() {
		return estado;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

}//end class RespuestaEscrituraVO
