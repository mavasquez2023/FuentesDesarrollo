package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class CoberturaEspecialVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long codigo = 0;
	private String descripcion = null;
	private double porcentajeCobertura=0;
	
	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return
	 */
	public double getPorcentajeCobertura() {
		return porcentajeCobertura;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param d
	 */
	public void setPorcentajeCobertura(double d) {
		porcentajeCobertura = d;
	}

}
