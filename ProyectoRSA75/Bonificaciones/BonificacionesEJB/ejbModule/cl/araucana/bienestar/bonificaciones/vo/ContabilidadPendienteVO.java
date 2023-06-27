package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ContabilidadPendienteVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private Date fechaDescuento=null;
	private long codigoDescuento=0;
	private String usuario = null;


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

	/**
	 * @return
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param string
	 */
	public void setUsuario(String string) {
		usuario = string;
	}

}
