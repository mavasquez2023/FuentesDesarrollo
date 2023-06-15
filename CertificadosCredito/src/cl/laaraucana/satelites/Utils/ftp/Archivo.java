package cl.laaraucana.satelites.Utils.ftp;

import java.util.Calendar;
import java.util.Date;

public class Archivo {
	private String nombre;
	private String tipoArchivo;
	private long tamano;
	private Date fechaModificacion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public long getTamano() {
		return tamano;
	}
	public void setTamano(long tamano) {
		this.tamano = tamano;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion.getTime();
	}
	
}
