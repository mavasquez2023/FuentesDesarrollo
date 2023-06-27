package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class StringVO implements Serializable {
	
	private String texto=null;

	/**
	 * @return
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param string
	 */
	public void setTexto(String string) {
		texto = string;
	}

}
