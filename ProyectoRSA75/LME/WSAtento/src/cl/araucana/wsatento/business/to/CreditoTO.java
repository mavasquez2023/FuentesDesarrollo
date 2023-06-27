package cl.araucana.wsatento.business.to;

import java.util.Date;

public class CreditoTO {
	private Date fechaPago;
	
	public CreditoTO(){}
	
	public CreditoTO(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
}
