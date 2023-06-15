package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class ValorValidableVO implements Serializable {
	
	private String variable=null;
	private String valor=null;


	/**
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @return
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * @param string
	 */
	public void setValor(String string) {
		valor = string;
	}

	/**
	 * @param string
	 */
	public void setVariable(String string) {
		variable = string;
	}

}
