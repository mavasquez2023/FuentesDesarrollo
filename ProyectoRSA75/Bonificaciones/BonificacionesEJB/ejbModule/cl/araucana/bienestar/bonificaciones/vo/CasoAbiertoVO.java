package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CasoAbiertoVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private long casoId=0;
	private String estadoActual=null;
	private String estadoNuevo=null;
	private Date fechaEstadoNuevo=null;
	/**
	 * INICIO NUEVO
	 */
	private double monto;
	private Date ingreso=null; 
	/**
	 * FIN NUEVO
	 */
	
	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @return
	 */
	public String getEstadoActual() {
		return estadoActual;
	}

	/**
	 * @return
	 */
	public String getEstadoNuevo() {
		return estadoNuevo;
	}

	/**
	 * @return
	 */
	public Date getFechaEstadoNuevo() {
		return fechaEstadoNuevo;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param string
	 */
	public void setEstadoActual(String string) {
		estadoActual = string;
	}

	/**
	 * @param string
	 */
	public void setEstadoNuevo(String string) {
		estadoNuevo = string;
	}

	/**
	 * @param date
	 */
	public void setFechaEstadoNuevo(Date date) {
		fechaEstadoNuevo = date;
	}

	/**
	 * @return
	 */
	public Date getIngreso() {
		return ingreso;
	}

	/**
	 * @return
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param date
	 */
	public void setIngreso(Date date) {
		ingreso = date;
	}

	/**
	 * @param d
	 */
	public void setMonto(double d) {
		monto = d;
	}

}
