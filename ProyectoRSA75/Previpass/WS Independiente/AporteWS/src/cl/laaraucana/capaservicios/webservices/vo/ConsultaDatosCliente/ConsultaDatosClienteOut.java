package cl.laaraucana.capaservicios.webservices.vo.ConsultaDatosCliente;

import cl.laaraucana.capaservicios.webservices.vo.Log;

public class ConsultaDatosClienteOut {
	
	private ClienteVO datosCliente;
	private Log log;
	
	public ClienteVO getDatosCliente() {
		return datosCliente;
	}
	public void setDatosCliente(ClienteVO datosCliente) {
		this.datosCliente = datosCliente;
	}
	public Log getLog() {
		return log;
	}
	public void setLog(Log log) {
		this.log = log;
	}
}
