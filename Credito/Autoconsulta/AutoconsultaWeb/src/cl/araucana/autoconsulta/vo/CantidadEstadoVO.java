package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CantidadEstadoVO implements Serializable {
	
	private int cantidad=0;
	private String estado=null;


	/**
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param i
	 */
	public void setCantidad(int i) {
		cantidad = i;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

}
