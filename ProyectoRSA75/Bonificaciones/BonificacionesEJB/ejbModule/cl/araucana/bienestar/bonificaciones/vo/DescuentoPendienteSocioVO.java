package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class DescuentoPendienteSocioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long casoId=0;
	private double montoTotal=0;
	private int cuotas=0;
	private int numeroCuotasBienestar=0;
	private int numeroCuotasConvenio=0;
	private int cuotaPendiente=0;
	private Date fechaCobroCuotaPendiente=null;
	private int cuotaCobrada=0;
	private Date fechaUltimoCobro=null;
	private int saldoPendiente=0;
	private String tipoCaso;
	
	/**
	 * @return
	 */
	public long getCasoId() {
		return casoId;
	}

	/**
	 * @return
	 */
	public int getCuotaCobrada() {
		return cuotaCobrada;
	}

	/**
	 * @return
	 */
	public int getCuotaPendiente() {
		return cuotaPendiente;
	}

	/**
	 * @return
	 */
	public int getCuotas() {
		return cuotas;
	}

	/**
	 * @return
	 */
	public Date getFechaCobroCuotaPendiente() {
		return fechaCobroCuotaPendiente;
	}

	/**
	 * @return
	 */
	public Date getFechaUltimoCobro() {
		return fechaUltimoCobro;
	}

	/**
	 * @return
	 */
	public double getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasBienestar() {
		return numeroCuotasBienestar;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasConvenio() {
		return numeroCuotasConvenio;
	}

	/**
	 * @return
	 */
	public int getSaldoPendiente() {
		return saldoPendiente;
	}

	/**
	 * @param l
	 */
	public void setCasoId(long l) {
		casoId = l;
	}

	/**
	 * @param i
	 */
	public void setCuotaCobrada(int i) {
		cuotaCobrada = i;
	}

	/**
	 * @param i
	 */
	public void setCuotaPendiente(int i) {
		cuotaPendiente = i;
	}

	/**
	 * @param i
	 */
	public void setCuotas(int i) {
		cuotas = i;
	}

	/**
	 * @param date
	 */
	public void setFechaCobroCuotaPendiente(Date date) {
		fechaCobroCuotaPendiente = date;
	}

	/**
	 * @param date
	 */
	public void setFechaUltimoCobro(Date date) {
		fechaUltimoCobro = date;
	}

	/**
	 * @param d
	 */
	public void setMontoTotal(double d) {
		montoTotal = d;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasBienestar(int i) {
		numeroCuotasBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasConvenio(int i) {
		numeroCuotasConvenio = i;
	}

	/**
	 * @param i
	 */
	public void setSaldoPendiente(int i) {
		saldoPendiente = i;
	}

	/**
	 * @return
	 */
	public String getTipoCaso() {
		return tipoCaso;
	}

	/**
	 * @param string
	 */
	public void setTipoCaso(String string) {
		tipoCaso = string;
	}

}
