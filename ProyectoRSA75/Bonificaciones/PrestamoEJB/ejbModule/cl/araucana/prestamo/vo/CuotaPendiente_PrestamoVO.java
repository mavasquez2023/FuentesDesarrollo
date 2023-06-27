package cl.araucana.prestamo.vo;

import java.io.Serializable;


/**
 * @author Guanguali	
 *
 */
public class CuotaPendiente_PrestamoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private long idPrestamo;
	//private int cuota=0;
	private float montoUF=0;

	/**
	 * @return
	 */
	//public int getCuota() {
	//	return cuota;
	//}

	/**
	 * @return
	 */
	public long getIdPrestamo() {
		return idPrestamo;
	}

	/**
	 * @return
	 */
	public float getMontoUF() {
		return montoUF;
	}

	/**
	 * @param i
	 */
	//public void setCuota(int i) {
	//	cuota = i;
	//}

	/**
	 * @param l
	 */
	public void setIdPrestamo(long l) {
		idPrestamo = l;
	}

	/**
	 * @param f
	 */
	public void setMontoUF(float f) {
		montoUF = f;
	}

}
