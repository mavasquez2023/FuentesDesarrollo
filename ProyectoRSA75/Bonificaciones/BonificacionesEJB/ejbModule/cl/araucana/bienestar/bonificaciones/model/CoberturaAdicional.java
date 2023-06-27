package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class CoberturaAdicional implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long codigo = 0;
	private long codigoCoberturaAdicional=0;
	private int orden=0;


	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public long getCodigoCoberturaAdicional() {
		return codigoCoberturaAdicional;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCoberturaAdicional(long l) {
		codigoCoberturaAdicional = l;
	}

	/**
	 * @return
	 */
	public int getOrden() {
		return orden;
	}

	/**
	 * @param i
	 */
	public void setOrden(int i) {
		orden = i;
	}

}
