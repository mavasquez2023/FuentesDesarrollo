package cl.laaraucana.credito.to;

import java.util.Date;

public class PagoCuotaTO  extends CreditoBaseTO {
	private int cuotaNumero;
	private Date fechaPago;
	private String oficicinaPago;
	private String documentoPago;
	private String tipoDocumentoPago;
	private float capital;
	private float interes;
	private float monto;
	
	public float getCapital() {
		return capital;
	}
	public void setCapital(float capital) {
		this.capital = capital;
	}
	public long getCreditoNumero() {
		return folio;
	}
	public void setCreditoNumero(long creditoNumero) {
		this.folio = creditoNumero;
	}
	public int getCuotaNumero() {
		return cuotaNumero;
	}
	public void setCuotaNumero(int cuotaNumero) {
		this.cuotaNumero = cuotaNumero;
	}
	public String getDocumentoPago() {
		return documentoPago;
	}
	public void setDocumentoPago(String documentoPago) {
		this.documentoPago = documentoPago;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public float getInteres() {
		return interes;
	}
	public void setInteres(float interes) {
		this.interes = interes;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public String getOficicina() {
		return oficina;
	}
	public void setOficicina(String oficicina) {
		this.oficina = oficicina;
	}
	public String getOficicinaPago() {
		return oficicinaPago;
	}
	public void setOficicinaPago(String oficicinaPago) {
		this.oficicinaPago = oficicinaPago;
	}
	public String getTipoDocumentoPago() {
		return tipoDocumentoPago;
	}
	public void setTipoDocumentoPago(String tipoDocumentoPago) {
		this.tipoDocumentoPago = tipoDocumentoPago;
	}
	
	public void imprimeThis() {
		System.out.println("" + oficina + " - " + folio + " - " + cuotaNumero + " - " + monto + " - " + fechaPago + " - " + capital + " - " + interes);

		
	}

}
