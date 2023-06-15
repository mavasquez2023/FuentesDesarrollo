/*
 * Creado el 28-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ConvenioEmpresaVO implements Serializable{
	
	private long nroConvevio = 0;
	private String nombreEmpresa = null;
	private String rutEmpresaFull = null;
	private long rutEmpresa = 0;
	private String dvEmpresa = null;
	private String fondo = null;
	private int tipFija = 0;
	private int tipValor = 0;
	 
	
	 
	/**
	 * @return
	 */
	public String getDvEmpresa() {
		return dvEmpresa;
	}

	/**
	 * @return
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @return
	 */
	public long getNroConvevio() {
		return nroConvevio;
	}

	/**
	 * @return
	 */
	public long getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @return
	 */
	public String getRutEmpresaFull() {
		rutEmpresaFull = getRutEmpresa() + " - " + getDvEmpresa();
		return rutEmpresaFull;
	}

	/**
	 * @param string
	 */
	public void setDvEmpresa(String string) {
		dvEmpresa = string;
	}

	/**
	 * @param string
	 */
	public void setNombreEmpresa(String string) {
		nombreEmpresa = string;
	}

	/**
	 * @param l
	 */
	public void setNroConvevio(long l) {
		nroConvevio = l;
	}

	/**
	 * @param l
	 */
	public void setRutEmpresa(long l) {
		rutEmpresa = l;
	}

	/**
	 * @param string
	 */
	public void setRutEmpresaFull(String string) {
		rutEmpresaFull = string;
	}

	/**
	 * @return
	 */
	public String getFondo() {
		return fondo;
	}

	/**
	 * @param string
	 */
	public void setFondo(String string) {
		fondo = string;
	}

	/**
	 * @return
	 */
	public int getTipFija() {
		return tipFija;
	}

	/**
	 * @return
	 */
	public int getTipValor() {
		return tipValor;
	}

	/**
	 * @param i
	 */
	public void setTipFija(int i) {
		tipFija = i;
	}

	/**
	 * @param i
	 */
	public void setTipValor(int i) {
		tipValor = i;
	}

}
