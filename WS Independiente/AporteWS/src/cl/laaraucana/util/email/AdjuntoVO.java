package cl.laaraucana.util.email;

public class AdjuntoVO {
	private String nombreArchivo;
	private String tipoArchivo;
	private byte[] archivo;
	
	public static String PDF_TYPE ="application/pdf";
	public static String IMG_TYPE ="image/jpg";
	
	public AdjuntoVO(){}
	public AdjuntoVO(String nombreArchivo, String tipoArchivo, byte[] archivo) {
		this.nombreArchivo = nombreArchivo;
		this.tipoArchivo = tipoArchivo;
		this.archivo = archivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
}
