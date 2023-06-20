package cl.laaraucana.imed.clientews.vo;


import java.util.List;

import cl.laaraucana.imed.clientews.model.AbstractSalidaVO;




public class SalidaImedVO extends AbstractSalidaVO{
	private int estado;
	private String mensaje;
	private String codigoTransaccion;
	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
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
	 * @return the codigoTransaccion
	 */
	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}
	/**
	 * @param codigoTransaccion the codigoTransaccion to set
	 */
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	
	
	}
