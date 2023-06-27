package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class RangoCoberturaSinFormatoVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigo = 0;
	private long codigoCobertura = 0;
	private Date inicioVigencia=null;
	private Date finVigencia=null;
	private double rangoID = 0;
	private double rangoInicio = 0;
	private double rangoFin = 0;
	private double rangoPorcentajeCobertura = 0;	


	/**
	 * @return
	 */
	public long getCodigo() {
		return codigo;
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
	public Date getFinVigencia() {
		return finVigencia;
	}

	/**
	 * @return
	 */
	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	/**
	 * @return
	 */
	public double getRangoFin() {
		return rangoFin;
	}

	/**
	 * @return
	 */
	public double getRangoID() {
		return rangoID;
	}

	/**
	 * @return
	 */
	public double getRangoInicio() {
		return rangoInicio;
	}

	/**
	 * @return
	 */
	public double getRangoPorcentajeCobertura() {
		return rangoPorcentajeCobertura;
	}

	/**
	 * @param l
	 */
	public void setCodigo(long l) {
		codigo = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param date
	 */
	public void setFinVigencia(Date date) {
		finVigencia = date;
	}

	/**
	 * @param date
	 */
	public void setInicioVigencia(Date date) {
		inicioVigencia = date;
	}

	/**
	 * @param d
	 */
	public void setRangoFin(double d) {
		rangoFin = d;
	}

	/**
	 * @param d
	 */
	public void setRangoID(double d) {
		rangoID = d;
	}

	/**
	 * @param d
	 */
	public void setRangoInicio(double d) {
		rangoInicio = d;
	}

	/**
	 * @param d
	 */
	public void setRangoPorcentajeCobertura(double d) {
		rangoPorcentajeCobertura = d;
	}

}
