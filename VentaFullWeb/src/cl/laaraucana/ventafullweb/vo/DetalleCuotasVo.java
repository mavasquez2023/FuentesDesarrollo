package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;

public class DetalleCuotasVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numCuota;
	private String vencimiento;
	private String saldoCapital;
	private String montoInteres;
	private String seguroDesgravamen;
	private String seguroCesantia;
	private String valorCuota;
	private String capitalCuota;
	
	
	public String getCapitalCuota() {
		return capitalCuota;
	}
	public void setCapitalCuota(String capitalCuota) {
		this.capitalCuota = capitalCuota;
	}
	public String getNumCuota() {
		return numCuota;
	}
	public void setNumCuota(String numCuota) {
		this.numCuota = numCuota;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public String getMontoInteres() {
		return montoInteres;
	}
	public void setMontoInteres(String montoInteres) {
		this.montoInteres = montoInteres;
	}
	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}
	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}
	public String getSeguroCesantia() {
		return seguroCesantia;
	}
	public void setSeguroCesantia(String seguroCesantia) {
		this.seguroCesantia = seguroCesantia;
	}
	public String getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}
	
	
}
