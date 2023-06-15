package cl.laaraucana.simulacion.webservices.client.CampañaAP.VO;


import java.util.Date;

import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;


public class SalidaCampañaVO extends AbstractSalidaVO{
	private double condonacionPagoTotal;
	private double condonacionConvenioPago;
	private Date fechaVigencia;
	/**
	 * @return the condonacionPagoTotal
	 */
	public double getCondonacionPagoTotal() {
		return condonacionPagoTotal;
	}
	/**
	 * @param condonacionPagoTotal the condonacionPagoTotal to set
	 */
	public void setCondonacionPagoTotal(double condonacionPagoTotal) {
		this.condonacionPagoTotal = condonacionPagoTotal;
	}
	/**
	 * @return the condonacionConvenioPago
	 */
	public double getCondonacionConvenioPago() {
		return condonacionConvenioPago;
	}
	/**
	 * @param condonacionConvenioPago the condonacionConvenioPago to set
	 */
	public void setCondonacionConvenioPago(double condonacionConvenioPago) {
		this.condonacionConvenioPago = condonacionConvenioPago;
	}
	/**
	 * @return the fechaVigencia
	 */
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	/**
	 * @param fechaVigencia the fechaVigencia to set
	 */
	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	
	
		
}
