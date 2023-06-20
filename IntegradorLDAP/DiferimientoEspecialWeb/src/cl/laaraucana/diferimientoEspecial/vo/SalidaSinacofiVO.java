package cl.laaraucana.diferimientoEspecial.vo;


import java.util.List;

import cl.laaraucana.diferimientoEspecial.sinacofi.Detalle;




public class SalidaSinacofiVO extends AbstractSalidaVO{
	private String codigoRetorno;
	private String existeDetalle;
	private String cedulaVigente;
	private String numeroRegistros;
	private Detalle[] detalle;
	/**
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	/**
	 * @param codigoRetorno the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	/**
	 * @return the existeDetalle
	 */
	public String getExisteDetalle() {
		return existeDetalle;
	}
	/**
	 * @param existeDetalle the existeDetalle to set
	 */
	public void setExisteDetalle(String existeDetalle) {
		this.existeDetalle = existeDetalle;
	}
	/**
	 * @return the cedulaVigente
	 */
	public String getCedulaVigente() {
		return cedulaVigente;
	}
	/**
	 * @param cedulaVigente the cedulaVigente to set
	 */
	public void setCedulaVigente(String cedulaVigente) {
		this.cedulaVigente = cedulaVigente;
	}
	/**
	 * @return the numeroRegistros
	 */
	public String getNumeroRegistros() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	/**
	 * @return the detalle
	 */
	public Detalle[] getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(Detalle[] detalle) {
		this.detalle = detalle;
	}

	
	}
