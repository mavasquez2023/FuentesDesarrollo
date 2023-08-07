 package cl.laaraucana.pagoenexceso.persistencia.vo;
 
 public class ConsultaPagoEnExcesoOut
 {
	 private PagoEnExcesoDTO pagoEnExceso;
	 private String codRespuesta;
	 private String mensaje;
	 
	public PagoEnExcesoDTO getPagoEnExceso() {
		return pagoEnExceso;
	}
	public void setPagoEnExceso(PagoEnExcesoDTO pagoEnExceso) {
		this.pagoEnExceso = pagoEnExceso;
	}
	public String getCodRespuesta() {
		return codRespuesta;
	}
	public void setCodRespuesta(String codRespuesta) {
		this.codRespuesta = codRespuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
 }

