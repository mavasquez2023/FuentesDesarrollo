package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CartolaAhorroVO extends CuentaAhorroVO implements Serializable {
	

	private Date fecha=null;
	private String fechaBDUltValorCuota=null;
	private float valorCuotaActual=0;
	private int subtotalDepositos=0;
	private double subtotalCuotas=0;
	private int subtotalCargos=0;
	private int depositosValorizar=0;
	private int girosValorizar=0;
	private double saldoCuotas=0;
	private int saldoPesos=0;
	private int rentabilidadPeriodo=0;
	private int totalSaldoContable=0;
	private Collection detalles = new ArrayList(); //DetalleCartolaVO
	private int saldoMaximoGiro =0;
	
	/**
	 * @return
	 */
	public float getValorCuotaActual() {
		return valorCuotaActual;
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
	public int getDepositosValorizar() {
		return depositosValorizar;
	}

	/**
	 * @return
	 */
	public int getGirosValorizar() {
		return girosValorizar;
	}

	/**
	 * @return
	 */
	public int getRentabilidadPeriodo() {
		return rentabilidadPeriodo;
	}

	/**
	 * @return
	 */
	public double getSaldoCuotas() {
		return saldoCuotas;
	}

	/**
	 * @return
	 */
	public int getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * @return
	 */
	public int getSubtotalCargos() {
		return subtotalCargos;
	}

	/**
	 * @return
	 */
	public double getSubtotalCuotas() {
		return subtotalCuotas;
	}

	/**
	 * @return
	 */
	public int getSubtotalDepositos() {
		return subtotalDepositos;
	}

	/**
	 * @return
	 */
	public int getTotalSaldoContable() {
		return totalSaldoContable;
	}

	/**
	 * @param i
	 */
	public void setDepositosValorizar(int i) {
		depositosValorizar = i;
	}

	/**
	 * @param i
	 */
	public void setGirosValorizar(int i) {
		girosValorizar = i;
	}

	/**
	 * @param i
	 */
	public void setRentabilidadPeriodo(int i) {
		rentabilidadPeriodo = i;
	}

	/**
	 * @param d
	 */
	public void setSaldoCuotas(double d) {
		saldoCuotas = d;
	}

	/**
	 * @param i
	 */
	public void setSaldoPesos(int i) {
		saldoPesos = i;
	}

	/**
	 * @param i
	 */
	public void setSubtotalCargos(int i) {
		subtotalCargos = i;
	}

	/**
	 * @param d
	 */
	public void setSubtotalCuotas(double d) {
		subtotalCuotas = d;
	}

	/**
	 * @param i
	 */
	public void setSubtotalDepositos(int i) {
		subtotalDepositos = i;
	}

	/**
	 * @param i
	 */
	public void setTotalSaldoContable(int i) {
		totalSaldoContable = i;
	}

	/**
	 * @return
	 */
	public Collection getDetalles() {
		return detalles;
	}

	/**
	 * @param collection
	 */
	public void setDetalles(Collection collection) {
		detalles = collection;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
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

	/**
	 * @return
	 */
	public int getSaldoMaximoGiro() {
		return saldoMaximoGiro;
	}

	/**
	 * @param i
	 */
	public void setSaldoMaximoGiro(int i) {
		saldoMaximoGiro = i;
	}

}