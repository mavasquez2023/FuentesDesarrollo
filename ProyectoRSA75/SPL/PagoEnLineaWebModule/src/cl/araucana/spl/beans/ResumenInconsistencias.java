package cl.araucana.spl.beans;

import java.math.BigDecimal;

public class ResumenInconsistencias {
	private MedioPago medioPago;
	private BigDecimal montoBanco;
	private BigDecimal cantidadBanco;

	private BigDecimal montoPago;
	private BigDecimal cantidadPago;
	private BigDecimal cantidadDescuadre;
	private BigDecimal montoDescuadre;

	public String toString() {
		return new StringBuffer("[RESUMEN::")
			.append(",medioPago=").append(medioPago)
			.append(",montoBanco=").append(montoBanco)
			.append(",cantidadBanco=").append(cantidadBanco)
			.append(",montoPago=").append(montoPago)
			.append(",cantidadPago=").append(cantidadPago)
			.append(",cantidadDescuadre=").append(cantidadDescuadre)
			.append(",montoDescuadre=").append(montoDescuadre)
		.append("]").toString();
	}
	
	public BigDecimal getCantidadBanco() {
		return cantidadBanco;
	}
	public void setCantidadBanco(BigDecimal cantidadBanco) {
		this.cantidadBanco = cantidadBanco;
	}
	public BigDecimal getCantidadPago() {
		return cantidadPago;
	}
	public void setCantidadPago(BigDecimal cantidadPago) {
		this.cantidadPago = cantidadPago;
	}
	public BigDecimal getMontoBanco() {
		return montoBanco;
	}
	public void setMontoBanco(BigDecimal montoBanco) {
		this.montoBanco = montoBanco;
	}
	public BigDecimal getMontoPago() {
		return montoPago;
	}
	public void setMontoPago(BigDecimal montoPago) {
		this.montoPago = montoPago;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public BigDecimal getCantidadDescuadre() {
		return cantidadDescuadre;
	}

	public void setCantidadDescuadre(BigDecimal cantidadDescuadre) {
		this.cantidadDescuadre = cantidadDescuadre;
	}

	public BigDecimal getMontoDescuadre() {
		return montoDescuadre;
	}

	public void setMontoDescuadre(BigDecimal montoDescuadre) {
		this.montoDescuadre = montoDescuadre;
	}
	
}
