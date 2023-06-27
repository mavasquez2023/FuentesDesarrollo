package cl.laaraucana.capaservicios.webservices.client.SolicitudSMS.vo;

import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;


public class SolicitudSMSSalidaVO extends AbstractSalidaVO{
	private String status;
	private String destino;
	private String fechaHoraEnvio;
	private String cuotaRestante;
	
	private String nroCorto; //Nro de origen con que se envía el mensaje
	private String idMensaje; //Id con que quedó registrado el mensaje
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getFechaHoraEnvio() {
		return fechaHoraEnvio;
	}
	public void setFechaHoraEnvio(String fechaHoraEnvio) {
		this.fechaHoraEnvio = fechaHoraEnvio;
	}
	public String getCuotaRestante() {
		return cuotaRestante;
	}
	public void setCuotaRestante(String cuotaRestante) {
		this.cuotaRestante = cuotaRestante;
	}
	public String getNroCorto() {
		return nroCorto;
	}
	public void setNroCorto(String nroCorto) {
		this.nroCorto = nroCorto;
	}
	public String getIdMensaje() {
		return idMensaje;
	}
	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
}
