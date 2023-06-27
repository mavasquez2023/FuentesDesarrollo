package cl.laaraucana.capaservicios.webservices.client.SolicitudSMS.vo;

import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;

public class SolicitudSMSEntradaVO extends AbstractEntradaVO {
	
	private String numero;
	private String mensaje;
	
	public SolicitudSMSEntradaVO() {
	}
	
	public SolicitudSMSEntradaVO(String nro, String mensaje) {
		setNumero(nro);
		setMensaje(mensaje);
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
