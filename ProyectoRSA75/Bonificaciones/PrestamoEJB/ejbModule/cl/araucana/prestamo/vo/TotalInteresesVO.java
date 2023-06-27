/*
 * Creado el 23-07-2008
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.prestamo.vo;

import java.io.Serializable;

/**
 * @author USIST28
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class TotalInteresesVO implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private long montoTotal;
	private int mes;
	private int anio;
	
	public TotalInteresesVO(){
		
	}
	/**
	 * @return
	 */
	public long getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param l
	 */
	public void setMontoTotal(long l) {
		montoTotal = l;
	}
	

	/**
	 * @return
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @return
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param i
	 */
	public void setAnio(int i) {
		anio = i;
	}

	/**
	 * @param i
	 */
	public void setMes(int i) {
		mes = i;
	}

}
