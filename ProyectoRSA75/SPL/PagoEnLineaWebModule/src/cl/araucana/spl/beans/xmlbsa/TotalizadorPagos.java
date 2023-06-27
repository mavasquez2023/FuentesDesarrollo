package cl.araucana.spl.beans.xmlbsa;

public class TotalizadorPagos {
	private String numeroPagos;
	private String montoTotal;
	
	public String toString() {
		return new StringBuffer("[TotalizadorPagos::numeroPagos=").append(numeroPagos)
			.append(",montoTotal=").append(montoTotal)
			.append("]").toString();
	}	
	
	public String getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getNumeroPagos() {
		return numeroPagos;
	}
	public void setNumeroPagos(String numeroPagos) {
		this.numeroPagos = numeroPagos;
	}
}
