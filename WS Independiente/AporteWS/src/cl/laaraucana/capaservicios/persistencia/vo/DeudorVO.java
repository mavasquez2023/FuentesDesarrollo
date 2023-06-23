package cl.laaraucana.capaservicios.persistencia.vo;

public class DeudorVO {
	private String periodo;
	private String rutDeudor;
	private String folioCredito;
	private int fechaVencimiento;
	private int montoCuota;
	private int montoGastoCobranza;
	private int montoTotalDescontar;
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getRutDeudor() {
		return rutDeudor;
	}
	public void setRutDeudor(String rutDeudor) {
		this.rutDeudor = rutDeudor;
	}
	public String getFolioCredito() {
		return folioCredito;
	}
	public void setFolioCredito(String folioCredito) {
		this.folioCredito = folioCredito;
	}
	public int getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(int montoCuota) {
		this.montoCuota = montoCuota;
	}
	public int getMontoGastoCobranza() {
		return montoGastoCobranza;
	}
	public void setMontoGastoCobranza(int montoGastoCobranza) {
		this.montoGastoCobranza = montoGastoCobranza;
	}
	public int getMontoTotalDescontar() {
		return montoTotalDescontar;
	}
	public void setMontoTotalDescontar(int montoTotalDescontar) {
		this.montoTotalDescontar = montoTotalDescontar;
	}
	public int getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(int fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
}
