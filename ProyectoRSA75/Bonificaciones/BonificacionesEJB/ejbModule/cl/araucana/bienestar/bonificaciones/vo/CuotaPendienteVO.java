package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CuotaPendienteVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private int cuota=0;
	private int monto=0;
	private Date fecha=null;

	/**
	 * @return
	 */
	public int getCuota() {
		return cuota;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @param i
	 */
	public void setCuota(int i) {
		cuota = i;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

}
