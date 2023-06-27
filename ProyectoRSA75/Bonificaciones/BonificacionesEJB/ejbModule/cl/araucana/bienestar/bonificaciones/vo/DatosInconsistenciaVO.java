package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author asepulveda
 *
 */
public class DatosInconsistenciaVO implements Serializable {	
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private String rutSocio=null;
	private long codigoCobertura=0;
	private String tipoTope=null;
	private int  mes = 0;
	private int  anio = 0;
	private Timestamp fecha=null;


	/**
	 * @return
	 */
	public int getAnio() {
		return anio;
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
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @return
	 */
	public String getRutSocio() {
		return rutSocio;
	}

	/**
	 * @return
	 */
	public String getTipoTope() {
		return tipoTope;
	}

	/**
	 * @param i
	 */
	public void setAnio(int i) {
		anio = i;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param timestamp
	 */
	public void setFecha(Timestamp timestamp) {
		fecha = timestamp;
	}

	/**
	 * @param i
	 */
	public void setMes(int i) {
		mes = i;
	}

	/**
	 * @param string
	 */
	public void setRutSocio(String string) {
		rutSocio = string;
	}

	/**
	 * @param string
	 */
	public void setTipoTope(String string) {
		tipoTope = string;
	}

}
