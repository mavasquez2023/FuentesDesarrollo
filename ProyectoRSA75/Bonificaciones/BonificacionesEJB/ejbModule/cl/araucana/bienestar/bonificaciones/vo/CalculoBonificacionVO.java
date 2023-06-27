package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class CalculoBonificacionVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private int aporteBienestar=0;
	private int montoBonificableNuevamente=0;
	

	/**
	 * @return
	 */
	public int getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @return
	 */
	public int getMontoBonificableNuevamente() {
		return montoBonificableNuevamente;
	}

	/**
	 * @param i
	 */
	public void setAporteBienestar(int i) {
		aporteBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setMontoBonificableNuevamente(int i) {
		montoBonificableNuevamente = i;
	}

}
