package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

public class CreditoCuotaPagoVO implements Serializable {
	private static final long serialVersionUID = 4280833966421579363L;
	
	private PersonaVO oficicina;
	private long creditoNumero; // folio 
	private int cuotaNumero;
	private int secuencia;
	private Date fechaPago;
	private String documentoPago;
	private float monto;
	private int estado;
	
	public CreditoCuotaPagoVO() {
		
	}
	

	public CreditoCuotaPagoVO(PersonaVO oficicina, long creditoNumero, int cuotaNumero, int secuencia, Date fechaPago, String documentoPago, float monto, int estado) {
		super();
		this.oficicina = oficicina;
		this.creditoNumero = creditoNumero;
		this.cuotaNumero = cuotaNumero;
		this.secuencia = secuencia;
		this.fechaPago = fechaPago;
		this.documentoPago = documentoPago;
		this.monto = monto;
		this.estado = estado;
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
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public PersonaVO getOficicina() {
		return oficicina;
	}
	public void setOficicina(PersonaVO oficicina) {
		this.oficicina = oficicina;
	}

	public int getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

}
