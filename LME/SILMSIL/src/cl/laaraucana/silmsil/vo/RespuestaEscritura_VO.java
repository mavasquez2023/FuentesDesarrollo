package cl.laaraucana.silmsil.vo;

import java.util.HashMap;

/**
 * Clase que soporta estructura de respuesta para los procesos que 
 * escriben(local y remoto) y descargan archivos. * 
 *
 * **/
public class RespuestaEscritura_VO {
	
	private String nombreArchivo;
	private String rutaArchivo;
	private String carpetaInterna;
	private String periodo;
	private String proceso;
	private String msgEscritura;
	private boolean escritoLocal;
	private boolean escritoIFS;
			
	public RespuestaEscritura_VO() {}
	
	public RespuestaEscritura_VO(String nombreArchivo, String rutaArchivo,
			String carpetaInterna, String periodo, String proceso,
			String msgEscritura, boolean escritoLocal, boolean escritoIFS) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.rutaArchivo = rutaArchivo;
		this.carpetaInterna = carpetaInterna;
		this.periodo = periodo;
		this.proceso = proceso;
		this.msgEscritura = msgEscritura;
		this.escritoLocal = escritoLocal;
		this.escritoIFS = escritoIFS;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public String getCarpetaInterna() {
		return carpetaInterna;
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getProceso() {
		return proceso;
	}

	public String getMsgEscritura() {
		return msgEscritura;
	}

	public boolean isEscritoLocal() {
		return escritoLocal;
	}

	public boolean isEscritoIFS() {
		return escritoIFS;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public void setCarpetaInterna(String carpetaInterna) {
		this.carpetaInterna = carpetaInterna;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public void setMsgEscritura(String msgEscritura) {
		this.msgEscritura = msgEscritura;
	}

	public void setEscritoLocal(boolean escritoLocal) {
		this.escritoLocal = escritoLocal;
	}

	public void setEscritoIFS(boolean escritoIFS) {
		this.escritoIFS = escritoIFS;
	}	
}//end class RespuestaEscritura_VO
