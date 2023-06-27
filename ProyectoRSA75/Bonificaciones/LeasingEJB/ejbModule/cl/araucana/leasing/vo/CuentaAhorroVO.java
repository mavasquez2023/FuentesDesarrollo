package cl.araucana.leasing.vo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Value Object para el despliege de listas de datos (caso especial: un solo valor) 
 * <p>Title: CDDTA</p>
 * <p>Description: Sistema de Créditos</p>
 * <p>Copyright: Para uso exclusivo de La Araucana</p>
 * <p>Company: schema ltda.</p>
 * @author asepulveda
 */

public class CuentaAhorroVO implements Serializable  {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	public CuentaAhorroVO() {
	}
	
	private String numeroCuenta=null;
	private String dvNumeroCuenta=null;
	private int estadoCuenta=0;
	private Date fechaAperturaCuenta=null;

	/**
	 * @return
	 */
	public String getDvNumeroCuenta() {
		return dvNumeroCuenta;
	}

	/**
	 * @return
	 */
	public int getEstadoCuenta() {
		return estadoCuenta;
	}

	/**
	 * @return
	 */
	public Date getFechaAperturaCuenta() {
		return fechaAperturaCuenta;
	}

	/**
	 * @return
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param string
	 */
	public void setDvNumeroCuenta(String string) {
		dvNumeroCuenta = string;
	}

	/**
	 * @param i
	 */
	public void setEstadoCuenta(int i) {
		estadoCuenta = i;
	}

	/**
	 * @param date
	 */
	public void setFechaAperturaCuenta(Date date) {
		fechaAperturaCuenta = date;
	}

	/**
	 * @param string
	 */
	public void setNumeroCuenta(String string) {
		numeroCuenta = string;
	}

}
