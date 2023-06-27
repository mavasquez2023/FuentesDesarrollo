package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CuentaAhorroVO implements Serializable {
	
	public static final int STD_NO_ACTIVA4=4;
	public static final int STD_NO_ACTIVA5=5;
	public static final int STD_NO_ACTIVA6=6;
	
	private String direccion=null;
	private String numeroCuenta=null;
	private String dvNumeroCuenta=null;
	private String comuna=null;
	private String ciudad=null;
	private String fechaUltCartola=null;
	private double ultSaldoCuotas=0;
	private float ultValorCuota=0;
	private int estadoCuenta=0;
	private int tipoCuenta=0;
	private int indicadorPromesaArriendo=0;
	
	/**
	 * Devuelve el ultimo saldo en pesos a partir de:
	 * 	ultSaldoCuotas y ultValorCuota
	 * @return
	 */
	public int getUltSaldoPesos() {
		return (int)Math.round(ultSaldoCuotas * ultValorCuota);
	}
	
	/**
	 * Devuelve el nuemro de cuenta incluyendo el dv de la cuenta
	 * @return String
	 */
	public String getFullNumeroCuenta(){
		return numeroCuenta+"-"+dvNumeroCuenta;
	}



	/**
	 * @return
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @return
	 */
	public String getComuna() {
		return comuna;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

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
	public String getFechaUltCartola() {
		return fechaUltCartola;
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
	public double getUltSaldoCuotas() {
		return ultSaldoCuotas;
	}

	/**
	 * @return
	 */
	public float getUltValorCuota() {
		return ultValorCuota;
	}

	/**
	 * @param string
	 */
	public void setCiudad(String string) {
		ciudad = string;
	}

	/**
	 * @param string
	 */
	public void setComuna(String string) {
		comuna = string;
	}

	/**
	 * @param string
	 */
	public void setDireccion(String string) {
		direccion = string;
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
	 * @param string
	 */
	public void setFechaUltCartola(String string) {
		fechaUltCartola = string;
	}

	/**
	 * @param string
	 */
	public void setNumeroCuenta(String string) {
		numeroCuenta = string;
	}

	/**
	 * @param d
	 */
	public void setUltSaldoCuotas(double d) {
		ultSaldoCuotas = d;
	}

	/**
	 * @param f
	 */
	public void setUltValorCuota(float f) {
		ultValorCuota = f;
	}

	/**
	 * @return
	 */
	public int getIndicadorPromesaArriendo() {
		return indicadorPromesaArriendo;
	}

	/**
	 * @return
	 */
	public int getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param i
	 */
	public void setIndicadorPromesaArriendo(int i) {
		indicadorPromesaArriendo = i;
	}

	/**
	 * @param i
	 */
	public void setTipoCuenta(int i) {
		tipoCuenta = i;
	}

}
