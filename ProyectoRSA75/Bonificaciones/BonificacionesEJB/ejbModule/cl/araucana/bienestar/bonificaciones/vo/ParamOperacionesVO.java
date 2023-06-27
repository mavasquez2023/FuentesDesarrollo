package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class ParamOperacionesVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaInicio=null;
	private Date fechaFin=null;

	/**
	 * @return
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param date
	 */
	public void setFechaFin(Date date) {
		fechaFin = date;
	}

	/**
	 * @param date
	 */
	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}

}
