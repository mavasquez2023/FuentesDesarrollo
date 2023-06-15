package cl.laaraucana.botonpago.web.vo;

public class CuotasVO {

	private String ncuota;
	private String fechaVencimiento;
	private String estadoCuota;
	private String montoDeuda;
	private String montoCapital;
	private String montoSeguro;
	private String montoIntereses;
	private String montoAbono;
	private String montoDescuento;
	private String gastosCobranza;

	
	
	public String getMontoAbono() {
		return montoAbono;
	}

	public void setMontoAbono(String montoAbono) {
		this.montoAbono = montoAbono;
	}

	public String getNcuota() {
		return ncuota;
	}

	public void setNcuota(String ncuota) {
		this.ncuota = ncuota;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getEstadoCuota() {
		return estadoCuota;
	}

	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	public String getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(String montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public String getMontoCapital() {
		return montoCapital;
	}

	public void setMontoCapital(String montoCapital) {
		this.montoCapital = montoCapital;
	}

	public String getMontoSeguro() {
		return montoSeguro;
	}

	public void setMontoSeguro(String montoSeguro) {
		this.montoSeguro = montoSeguro;
	}

	public String getMontoIntereses() {
		return montoIntereses;
	}

	public void setMontoIntereses(String montoIntereses) {
		this.montoIntereses = montoIntereses;
	}

	public String getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(String montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getGastosCobranza() {
		return gastosCobranza;
	}

	public void setGastosCobranza(String gastosCobranza) {
		this.gastosCobranza = gastosCobranza;
	}

}
