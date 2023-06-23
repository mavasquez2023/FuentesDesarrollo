package cl.araucana.spl.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class Folio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal numero;
	private BigDecimal   monto;
	private String detalle;
	
	
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public String toString(){
		StringBuffer sf = new StringBuffer("FolioBean=[");
		
		sf.append("numero=").append(numero);
		sf.append(",monto=").append(monto);
		sf.append(",detalle=").append(detalle);
		sf.append("]");
		
		return sf.toString();
	}
	public BigDecimal getNumero() {
		return numero;
	}
	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}

}
