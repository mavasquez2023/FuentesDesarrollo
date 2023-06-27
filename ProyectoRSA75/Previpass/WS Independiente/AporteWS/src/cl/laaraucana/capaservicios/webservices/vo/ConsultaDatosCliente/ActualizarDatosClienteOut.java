package cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente;

import cl.laaraucana.capaservicios.webservices.vo.Log;

public class ActualizarDatosClienteOut {
	private boolean actualizado;
	private Log log;
	
	public boolean isActualizado() {
		return actualizado;
	}
	public void setActualizado(boolean actualizado) {
		this.actualizado = actualizado;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
}
