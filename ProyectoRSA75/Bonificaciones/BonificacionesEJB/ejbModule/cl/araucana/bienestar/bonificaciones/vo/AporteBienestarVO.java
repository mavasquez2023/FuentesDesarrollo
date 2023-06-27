package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author asepulveda
 *
 */
public class AporteBienestarVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private double montoTotal=0;
	private ArrayList detalleAporteBienestar=new ArrayList(); //DetalleAporteBienestarVO


	/**
	 * @return
	 */
	public ArrayList getDetalleAporteBienestar() {
		return detalleAporteBienestar;
	}

	/**
	 * @return
	 */
	public double getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @param list
	 */
	public void setDetalleAporteBienestar(ArrayList list) {
		detalleAporteBienestar = list;
	}

	/**
	 * @param d
	 */
	public void setMontoTotal(double d) {
		montoTotal = d;
	}

}
