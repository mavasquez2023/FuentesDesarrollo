package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class DoubleVO implements Serializable {
	
	private double valor=0;


	/**
	 * @return
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param d
	 */
	public void setValor(double d) {
		valor = d;
	}

}
