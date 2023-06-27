/*
 * Creado el 26-10-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class AgrupacionCobertura implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;


	private long codigoCobertura=0;
	private long codigoCoberturaMaestra=0;
	private Date fechaRegistro=null;
	
	

	/**
	 * @return
	 */
	public long getCodigoCobertura() {
		return codigoCobertura;
	}

	/**
	 * @return
	 */
	public long getCodigoCoberturaMaestra() {
		return codigoCoberturaMaestra;
	}

	/**
	 * @return
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCoberturaMaestra(long l) {
		codigoCoberturaMaestra = l;
	}

	/**
	 * @param date
	 */
	public void setFechaRegistro(Date date) {
		fechaRegistro = date;
	}

}
