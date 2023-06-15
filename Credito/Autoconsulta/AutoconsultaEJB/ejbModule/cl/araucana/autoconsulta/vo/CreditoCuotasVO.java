package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CreditoCuotasVO implements Serializable {
	
	private long folio=0;
	private int cuotasImpagas=0;
	
	/**
	 * @return
	 */
	public int getCuotasImpagas() {
		return cuotasImpagas;
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
	public void setCuotasImpagas(int i) {
		cuotasImpagas = i;
	}

	/**
	 * @param l
	 */
	public void setFolio(long l) {
		folio = l;
	}

}
