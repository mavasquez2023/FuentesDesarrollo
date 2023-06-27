package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class ParamAporteEspecialVO implements Serializable {

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long casoID=0;
	private int montoAporte=0;
	private int coPagoSocio=0;
	private long codigoCobertura=0;

	/**
	 * @return
	 */
	public long getCasoID() {
		return casoID;
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
	public int getCoPagoSocio() {
		return coPagoSocio;
	}

	/**
	 * @return
	 */
	public int getMontoAporte() {
		return montoAporte;
	}

	/**
	 * @param l
	 */
	public void setCasoID(long l) {
		casoID = l;
	}

	/**
	 * @param l
	 */
	public void setCodigoCobertura(long l) {
		codigoCobertura = l;
	}

	/**
	 * @param i
	 */
	public void setCoPagoSocio(int i) {
		coPagoSocio = i;
	}

	/**
	 * @param i
	 */
	public void setMontoAporte(int i) {
		montoAporte = i;
	}

}
