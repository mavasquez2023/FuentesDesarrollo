package cl.araucana.prestamo.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CuotaVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private String rut=null;
	private int tipoPrestamo=0;
	private int numeroCuotasTotales=0;
	private int cuotaActual=0;
	private int monto=0;
	private Date fecha=null;
	private String tipoFinanciamiento;
	private String cuentaContable;


	/**
	 * @return
	 */
	public int getCuotaActual() {
		return cuotaActual;
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
	 * @return
	 */
	public int getNumeroCuotasTotales() {
		return numeroCuotasTotales;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return
	 */
	public int getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * @param i
	 */
	public void setCuotaActual(int i) {
		cuotaActual = i;
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

	/**
	 * @param i
	 */
	public void setNumeroCuotasTotales(int i) {
		numeroCuotasTotales = i;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @param i
	 */
	public void setTipoPrestamo(int i) {
		tipoPrestamo = i;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public String getTipoFinanciamiento() {
		return tipoFinanciamiento;
	}

	public void setTipoFinanciamiento(String tipoFinanciamiento) {
		this.tipoFinanciamiento = tipoFinanciamiento;
	}



}
