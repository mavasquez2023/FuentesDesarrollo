package cl.laaraucana.ventafullweb.vo;


import cl.laaraucana.ventafullweb.vo.DetalleVo;

public class SalidaSinacofiVO extends AbstractSalidaVO{
	private String codigoRetorno;
	private String existeDetalle;
	private String cedulaVigente;
	private String numeroRegistros;
	private String mensaje;
	private DetalleVo[] detalle;
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
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	/**
	 * @return the detalle
	 */
	public DetalleVo[] getDetalle() {
		return detalle;
	}
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(DetalleVo[] detalle) {
		this.detalle = detalle;
	}
	
	
	}
