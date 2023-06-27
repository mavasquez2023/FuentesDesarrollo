package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ResumenMovimientosConvenioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private double aporteBienestarAcumulado = 0;
	private long codigoProducto = 0;
	private String nombreProducto = null;
	
	

	/**
	 * @return
	 */
	public double getAporteBienestarAcumulado() {
		return aporteBienestarAcumulado;
	}


	/**
	 * @return
	 */
	public long getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @return
	 */
	public String getNombreProducto() {
		return nombreProducto;
	}


	/**
	 * @param d
	 */
	public void setAporteBienestarAcumulado(double d) {
		aporteBienestarAcumulado = d;
	}

	/**
	 * @param l
	 */
	public void setCodigoProducto(long l) {
		codigoProducto = l;
	}

	/**
	 * @param string
	 */
	public void setNombreProducto(String string) {
		nombreProducto = string;
	}
}
