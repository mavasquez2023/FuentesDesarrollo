package cl.araucana.leasing.vo;

import java.io.Serializable;

/**
 * Value Object para el despliege de listas de datos (caso especial: un solo valor) 
 * <p>Title: CDDTA</p>
 * <p>Description: Sistema de Créditos</p>
 * <p>Copyright: Para uso exclusivo de La Araucana</p>
 * <p>Company: schema ltda.</p>
 * @author asepulveda
 */

public class DetalleMovimientosCuentaVO implements Serializable  {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	public DetalleMovimientosCuentaVO() {
	}

	private String descripcionDetalle=null;
	private String fechaDetalle=null;
	private double cuotas=0;
	private int totalValor=0;
	private int codigoMovimiento=0;
	private int estadoMovimiento=0;
	private String numeroCuenta=null;
	private String folioFormulario=null;

	/**
	 * @return
	 */
	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}

	/**
	 * @return
	 */
	public double getCuotas() {
		return cuotas;
	}

	/**
	 * @return
	 */
	public String getDescripcionDetalle() {
		return descripcionDetalle;
	}

	/**
	 * @return
	 */
	public int getEstadoMovimiento() {
		return estadoMovimiento;
	}

	/**
	 * @return
	 */
	public String getFechaDetalle() {
		return fechaDetalle;
	}

	/**
	 * @return
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @return
	 */
	public int getTotalValor() {
		return totalValor;
	}

	/**
	 * @param i
	 */
	public void setCodigoMovimiento(int i) {
		codigoMovimiento = i;
	}

	/**
	 * @param d
	 */
	public void setCuotas(double d) {
		cuotas = d;
	}

	/**
	 * @param string
	 */
	public void setDescripcionDetalle(String string) {
		descripcionDetalle = string;
	}

	/**
	 * @param i
	 */
	public void setEstadoMovimiento(int i) {
		estadoMovimiento = i;
	}

	/**
	 * @param string
	 */
	public void setFechaDetalle(String string) {
		fechaDetalle = string;
	}

	/**
	 * @param string
	 */
	public void setNumeroCuenta(String string) {
		numeroCuenta = string;
	}

	/**
	 * @param i
	 */
	public void setTotalValor(int i) {
		totalValor = i;
	}

	/**
	 * @return
	 */
	public String getFolioFormulario() {
		return folioFormulario;
	}

	/**
	 * @param string
	 */
	public void setFolioFormulario(String string) {
		folioFormulario = string;
	}

}
