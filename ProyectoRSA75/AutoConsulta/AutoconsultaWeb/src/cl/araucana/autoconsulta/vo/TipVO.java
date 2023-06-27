/*
 * Creado el 05-12-2005
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
public class TipVO implements Serializable{
	long tipAno = 0;
	long tipMes = 0;
	String tipValor ="0";
	String tipPropia = "0";


	/**
	 * @return
	 */
	public long getTipAno() {
		return tipAno;
	}

	/**
	 * @return
	 */
	public long getTipMes() {
		return tipMes;
	}

	/**
	 * @return
	 */
	public String getTipPropia() {
		return tipPropia;
	}

	/**
	 * @return
	 */
	public String getTipValor() {
		return tipValor;
	}

	/**
	 * @param l
	 */
	public void setTipAno(long l) {
		tipAno = l;
	}

	/**
	 * @param l
	 */
	public void setTipMes(long l) {
		tipMes = l;
	}

	/**
	 * @param d
	 */
	public void setTipPropia(String d) {
		tipPropia = d;
	}

	/**
	 * @param d
	 */
	public void setTipValor(String d) {
		tipValor = d;
	}

}
