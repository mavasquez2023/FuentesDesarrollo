package cl.laaraucana.credito.to;

import java.util.Date;

public class CuotaTO extends CreditoBaseTO {
	private int cuotaNumero;
	private Date fechaVencimiento;
	private float capital;
	private float interes;
	private float monto;
	private float abono;
	
	public float getAbono() {
		return abono;
	}
	public void setAbono(float abono) {
		this.abono = abono;
	}
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

	public int getEstado() {
		return estado;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public void imprimeThis() {
		System.out.println("" + oficina + " - " + folio + " - " + cuotaNumero + " - " + monto + " - " + fechaVencimiento + " - " + capital + " - " + interes);
		
	}
}
