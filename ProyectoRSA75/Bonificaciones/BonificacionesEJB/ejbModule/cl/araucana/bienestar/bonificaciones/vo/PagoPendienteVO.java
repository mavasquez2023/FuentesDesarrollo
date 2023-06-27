package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class PagoPendienteVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private Date fechaDescuento=null;
	private long codigoDescuento=0;


	/**
	 * @return
	 */
	public long getCodigoDescuento() {
		return codigoDescuento;
	}

	/**
	 * @return
	 */
	public Date getFechaDescuento() {
		return fechaDescuento;
	}

	/**
	 * @param l
	 */
	public void setCodigoDescuento(long l) {
		codigoDescuento = l;
	}

	/**
	 * @param date
	 */
	public void setFechaDescuento(Date date) {
		fechaDescuento = date;
	}

}
