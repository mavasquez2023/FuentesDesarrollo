package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class TalonarioVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoTalonario = 0;
	private Date fechaIngreso = null;
	private long inicioSecuencia = 0;
	private long finSecuencia = 0;
	private long valeDisponible = 0;
	private String estado = null;
	private long codigoConvenio = 0;
	private String nombreConvenio = null;
	private String estadoConvenio = null;
	



	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public long getCodigoTalonario() {
		return codigoTalonario;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @return
	 */
	public long getFinSecuencia() {
		return finSecuencia;
	}

	/**
	 * @return
	 */
	public long getInicioSecuencia() {
		return inicioSecuencia;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
	}

	/**
	 * @return
	 */
	public long getValeDisponible() {
		return valeDisponible;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoTalonario(long l) {
		codigoTalonario = l;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param date
	 */
	public void setFechaIngreso(Date date) {
		fechaIngreso = date;
	}

	/**
	 * @param l
	 */
	public void setFinSecuencia(long l) {
		finSecuencia = l;
	}

	/**
	 * @param l
	 */
	public void setInicioSecuencia(long l) {
		inicioSecuencia = l;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param l
	 */
	public void setValeDisponible(long l) {
		valeDisponible = l;
	}

	/**
	 * @return
	 */
	public String getEstadoConvenio() {
		return estadoConvenio;
	}

	/**
	 * @param string
	 */
	public void setEstadoConvenio(String string) {
		estadoConvenio = string;
	}

}
