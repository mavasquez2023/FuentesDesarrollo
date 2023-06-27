package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class DetalleAporteBienestarVO implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoCobertura=0;
	private String descripcion=null;
	private double monto=0;
	
	/**
	 * @return
	 */
	public long getCodigoCobertura() {
		return codigoCobertura;
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
	public double getMonto() {
		return monto;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
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
	public void setMonto(double d) {
		monto = d;
	}
}
