package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class DetalleCartolaVO implements Serializable {

	private String descripcionDetalle=null;
	private String fechaDetalle=null;
	private double cuotas=0;
	private int totalValor=0;
	private int codigoMovimiento=0;
	private int estadoMovimiento=0;
	private float valorCuotaActual=0;
	private String fechaBDUltValorCuota=null;
	
	/**
	 * Si el codigo de movimiento es menor a 20
	 * entonces es un depósito y por lo tanto devuelve
	 * el valor de totalValor, en caso contrario devuelve null
	 * @return String
	 */
	public int getDeposito() {
		if(codigoMovimiento<20)
			return  totalValor;
		else
			return 0;
	}
	
	/**
	 * Si el codigo de movimiento es mayor a 20
	 * entonces es un Cargo y por lo tanto devuelve
	 * el valor de totalValor, en caso contrario devuelve null
	 * @return String
	 */
	public int getCargo() {
		if(codigoMovimiento>20)
			return  totalValor;
		else
			return 0;
	}
	
	/**
	 * Si el codigo de movimiento es mayor a 20
	 * entonces es un cargo y por lo tanto devuelve
	 * el valor de cuotas con signo negativo, en caso contrario
	 * devuelve el mismo valor de cuotas
	 * @return String
	 */
	public double getCuotaDespliegue() {
		if(codigoMovimiento<20)
			return  cuotas;
		else
			return -cuotas;
	}

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
	public int getTotalValor() {
		return totalValor;
	}

	/**
	 * @return
	 */
	public float getValorCuotaActual() {
		return valorCuotaActual;
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
	 * @param i
	 */
	public void setTotalValor(int i) {
		totalValor = i;
	}

	/**
	 * @param f
	 */
	public void setValorCuotaActual(float f) {
		valorCuotaActual = f;
	}

	/**
	 * @return
	 */
	public String getFechaDetalle() {
		return fechaDetalle;
	}

	/**
	 * @param string
	 */
	public void setFechaDetalle(String string) {
		fechaDetalle = string;
	}

	/**
	 * @return
	 */
	public String getFechaBDUltValorCuota() {
		return fechaBDUltValorCuota;
	}

	/**
	 * @param string
	 */
	public void setFechaBDUltValorCuota(String string) {
		fechaBDUltValorCuota = string;
	}

}
