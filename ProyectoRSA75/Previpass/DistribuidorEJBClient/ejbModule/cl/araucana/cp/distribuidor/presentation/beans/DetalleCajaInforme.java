package cl.araucana.cp.distribuidor.presentation.beans;

public class DetalleCajaInforme implements java.io.Serializable{
	
	public AporteInforme aporteInforme;
	
	public CreditoInforme creditoInforme ;
	
	public LeasingInforme leasingInforme ;

	public AporteInforme getAporteInforme() {
		return aporteInforme;
	}

	public void setAporteInforme(AporteInforme aporteInforme) {
		this.aporteInforme = aporteInforme;
	}

	public CreditoInforme getCreditoInforme() {
		return creditoInforme;
	}

	public void setCreditoInforme(CreditoInforme creditoInforme) {
		this.creditoInforme = creditoInforme;
	}

	public LeasingInforme getLeasingInforme() {
		return leasingInforme;
	}

	public void setLeasingInforme(LeasingInforme leasingInforme) {
		this.leasingInforme = leasingInforme;
	}
	
	

}
