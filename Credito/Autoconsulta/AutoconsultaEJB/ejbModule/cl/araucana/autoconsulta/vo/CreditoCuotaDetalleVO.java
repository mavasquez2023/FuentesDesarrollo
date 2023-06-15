package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

public class CreditoCuotaDetalleVO implements Serializable {
	private static final long serialVersionUID = -6373722368551085932L;

	private long creditoNumero; // folio 
	private String oficicina;
	private int cuotaNumero;
	private Date fechaVencimiento;
	private double capital;
	private float interes;
	private float monto;
	private float abono;
	private float estado;
	
	public CreditoCuotaDetalleVO(long creditoNumero, String oficicina, int cuotaNumero, Date fechaVencimiento, double capital, float interes, float monto, float abono, float estado) {
		super();
		this.creditoNumero = creditoNumero;
		this.oficicina = oficicina;
		this.cuotaNumero = cuotaNumero;
		this.fechaVencimiento = fechaVencimiento;
		this.capital = capital;
		this.interes = interes;
		this.monto = monto;
		this.abono = abono;
		this.estado = estado;
	}

	public CreditoCuotaDetalleVO() {
		
	}
	
	public float getAbono() {
		return abono;
	}
	public void setAbono(float abono) {
		this.abono = abono;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
	}
	public long getCreditoNumero() {
		return creditoNumero;
	}
	public void setCreditoNumero(long creditoNumero) {
		this.creditoNumero = creditoNumero;
	}
	public int getCuotaNumero() {
		return cuotaNumero;
	}
	public void setCuotaNumero(int cuotaNumero) {
		this.cuotaNumero = cuotaNumero;
	}
	public float getEstado() {
		return estado;
	}
	public void setEstado(float estado) {
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
		return oficicina;
	}
	public void setOficicina(String oficicina) {
		this.oficicina = oficicina;
	}
	
	public void printThis() {
		
		System.out.println ("CreditoCuotaDetalleVO printThis ...." );
		System.out.println ("creditoNumero " + this.creditoNumero);

		System.out.println ("oficicina " + this.oficicina);
		System.out.println ("cuotaNumero " + this.cuotaNumero);
		System.out.println ("capital " + this.capital);
		System.out.println ("interes " + this.interes);
		System.out.println ("monto " + this.monto);
		System.out.println ("abono " + this.abono);
		System.out.println ("estado " + this.estado);


	}
	
	
}
