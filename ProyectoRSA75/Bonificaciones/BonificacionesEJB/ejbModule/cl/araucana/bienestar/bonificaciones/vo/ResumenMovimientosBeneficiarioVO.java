package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ResumenMovimientosBeneficiarioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoCobertura = 0;
	private String nombreCobertura = null;
	private String tipoTope = null;
	private double tope = 0;
	private double valorReferencial = 0;
	private double aporteBienestarAcumulado = 0;
	
	
	public double getDisponible() {
		return tope - aporteBienestarAcumulado;
	}
	
	
	
	/**
	 * @return
	 */
	public double getAporteBienestarAcumulado() {
		return aporteBienestarAcumulado;
	}

	/**
	 * @return
	 */
	public long getCodigoCobertura() {
		return codigoCobertura;
	}

	/**
	 * @return
	 */
	public String getNombreCobertura() {
		return nombreCobertura;
	}

	/**
	 * @return
	 */
	public String getTipoTope() {
		return tipoTope;
	}

	/**
	 * @return
	 */
	public double getTope() {
		return tope;
	}

	/**
	 * @param d
	 */
	public void setAporteBienestarAcumulado(double d) {
		aporteBienestarAcumulado = d;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param string
	 */
	public void setNombreCobertura(String string) {
		nombreCobertura = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTope(String string) {
		tipoTope = string;
	}

	/**
	 * @param d
	 */
	public void setTope(double d) {
		tope = d;
	}

	/**
	 * @return
	 */
	public double getValorReferencial() {
		return valorReferencial;
	}

	/**
	 * @param d
	 */
	public void setValorReferencial(double d) {
		valorReferencial = d;
	}

}
