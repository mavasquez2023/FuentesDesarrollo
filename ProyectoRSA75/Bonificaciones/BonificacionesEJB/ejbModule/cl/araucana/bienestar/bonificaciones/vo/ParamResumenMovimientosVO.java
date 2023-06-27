package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class ParamResumenMovimientosVO implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoConvenio=0;
	private String rutSocio=null;
	private String rutCarga=null;
	private Date fechaInicio=null;
	private Date fechaFin=null;
	

	/**
	 * @return
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return
	 */
	public String getRutCarga() {
		return rutCarga;
	}

	/**
	 * @return
	 */
	public String getRutSocio() {
		return rutSocio;
	}

	/**
	 * @param date
	 */
	public void setFechaFin(Date date) {
		fechaFin = date;
	}

	/**
	 * @param date
	 */
	public void setFechaInicio(Date date) {
		fechaInicio = date;
	}

	/**
	 * @param string
	 */
	public void setRutCarga(String string) {
		rutCarga = string;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

}
