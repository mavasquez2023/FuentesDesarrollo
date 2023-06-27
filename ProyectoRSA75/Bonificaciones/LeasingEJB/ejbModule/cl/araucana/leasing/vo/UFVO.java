/*
 * Creado el 22-07-2008
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.leasing.vo;

import java.io.Serializable;

/**
 * @author USIST28
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class UFVO implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private float valor;
	private String fecha;
	
	public UFVO(){
	}

	/**
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param string
	 */
	public void setFecha(String string) {
		fecha = string;
	}

	/**
	 * @return
	 */
	public float getValor() {
		return valor;
	}

	/**
	 * @param f
	 */
	public void setValor(float f) {
		valor = f;
	}

}
