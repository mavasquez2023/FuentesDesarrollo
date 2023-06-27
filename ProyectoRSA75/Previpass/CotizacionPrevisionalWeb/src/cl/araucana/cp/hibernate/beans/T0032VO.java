package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class T0032VO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int folioNomina;
	private int rutAhorrante;
	private int valorCuota;
	private int numeroCuenta;


	
	public T0032VO(){
		super();
	}
	
	public T0032VO( int folioNomina, int rutAhorrante, int numeroCuenta, int valorCuota)
	{
		super();

		this.folioNomina=folioNomina;
		this.rutAhorrante=rutAhorrante;
		this.numeroCuenta= numeroCuenta;
		this.valorCuota=valorCuota;

	}

	
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * @return el folioNomina
	 */
	public int getFolioNomina() {
		return folioNomina;
	}

	/**
	 * @param folioNomina el folioNomina a establecer
	 */
	public void setFolioNomina(int folioNomina) {
		this.folioNomina = folioNomina;
	}

	/**
	 * @return el rutAhorrante
	 */
	public int getRutAhorrante() {
		return rutAhorrante;
	}

	/**
	 * @param rutAhorrante el rutAhorrante a establecer
	 */
	public void setRutAhorrante(int rutAhorrante) {
		this.rutAhorrante = rutAhorrante;
	}

	/**
	 * @return el valorCuota
	 */
	public int getValorCuota() {
		return valorCuota;
	}

	/**
	 * @param valorCuota el valorCuota a establecer
	 */
	public void setValorCuota(int valorCuota) {
		this.valorCuota = valorCuota;
	}

	/**
	 * @return el numeroCuenta
	 */
	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta el numeroCuenta a establecer
	 */
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	

}
