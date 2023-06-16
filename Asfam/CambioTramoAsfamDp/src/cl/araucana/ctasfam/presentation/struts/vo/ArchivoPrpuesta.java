package cl.araucana.ctasfam.presentation.struts.vo;

import java.io.Serializable;

import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;

public class ArchivoPrpuesta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ExplorerManagerAs400 manager;

	private String nombreArchivo;

	private String rutaArchivo;

	private String formato;
	
	private String urlDescarga;

	public ArchivoPrpuesta() {
		super();
	}

	public ArchivoPrpuesta(String nombreArchivo, String rutaArchivo, String formato) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.rutaArchivo = rutaArchivo;
		this.formato = formato;
	}
	
	public boolean getExiste() {
		return this.manager.existFile(getRutaArchivo());
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getUrlDescarga() {
		return urlDescarga;
	}

	public void setUrlDescarga(String urlDescarga) {
		this.urlDescarga = urlDescarga;
	}

	public ExplorerManagerAs400 getArchivoAs400() {
		return manager;
	}

	public void setArchivoAs400(ExplorerManagerAs400 archivoAs400) {
		this.manager = archivoAs400;
	}
}
