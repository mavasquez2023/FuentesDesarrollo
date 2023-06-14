package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CantidadChequesVO implements Serializable {
	
	private long folio=0;
	private int cantidad=0;


	/**
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @return
	 */
	public long getFolio() {
		return folio;
	}

	/**
	 * @param i
	 */
	public void setCantidad(int i) {
		cantidad = i;
	}

	/**
	 * @param l
	 */
	public void setFolio(long l) {
		folio = l;
	}

}
