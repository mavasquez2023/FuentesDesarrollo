package cl.araucana.wsatento.integration.jaxrpc.bean;

import java.util.Date;

public class Credito {
	private Date fechaPago;
	
	public Credito(){}
			
	public Credito(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
}
