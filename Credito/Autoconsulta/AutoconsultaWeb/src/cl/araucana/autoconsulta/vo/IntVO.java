package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class IntVO implements Serializable {

	private int valor=0;

	/**
	 * @return
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * @param i
	 */
	public void setValor(int i) {
		valor = i;
	}

}
