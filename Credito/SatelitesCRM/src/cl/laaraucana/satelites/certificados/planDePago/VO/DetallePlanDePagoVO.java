package cl.laaraucana.satelites.certificados.planDePago.VO;

import java.util.ArrayList;
import java.util.List;


public class DetallePlanDePagoVO {

	private String cuota;
	private String fechaVcto;
	private String valorCuota;
	private String capital;
	private String interes;
	private String saldoCapital;
	private String valorADescto;
	
	
	public String getCuota() {
		return cuota;
	}
	public void setCuota(String cuota) {
		this.cuota = cuota;
	}
	public String getFechaVcto() {
		return fechaVcto;
	}
	public void setFechaVcto(String fechaVcto) {
		this.fechaVcto = fechaVcto;
	}
	public String getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(String valorCuota) {
		this.valorCuota = valorCuota;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}
	public String getSaldoCapital() {
		return saldoCapital;
	}
	public void setSaldoCapital(String saldoCapital) {
		this.saldoCapital = saldoCapital;
	}
	public String getValorADescto() {
		return valorADescto;
	}
	public void setValorADescto(String valorADescto) {
		this.valorADescto = valorADescto;
	}
	
	public DetallePlanDePagoVO(){}
	
	public DetallePlanDePagoVO(String cuota,String fechaVcto,String valorCuota,String capital,String interes,
			String saldoCapital,String valorADescto){
		
		this.cuota = cuota;
		this.fechaVcto = fechaVcto;
		this.valorCuota = valorCuota;
		this.capital = capital;
		this.interes= interes;
		this.saldoCapital= saldoCapital;
		this.valorADescto= valorADescto;
	}
	
	public List<DetallePlanDePagoVO> llenaPlanDePago(){
		List<DetallePlanDePagoVO> cuotas = new ArrayList<DetallePlanDePagoVO>();
		DetallePlanDePagoVO cuota0 = null;
		DetallePlanDePagoVO cuota1 = new DetallePlanDePagoVO("1","30/09/2012","45.973","14.403","31.570","854.161","47.681");
		DetallePlanDePagoVO cuota2 = new DetallePlanDePagoVO("2","31/10/2012","45.973","29.069","16.904","839.758","47.681");
		DetallePlanDePagoVO cuota3 = new DetallePlanDePagoVO("3","30/11/2012","45.973","29.654","16.319","810.689","47.681");
		DetallePlanDePagoVO cuota4 = new DetallePlanDePagoVO("4","31/12/2012","45.973","30.251","15.722","781.035","47.681");
		DetallePlanDePagoVO cuota5 = new DetallePlanDePagoVO("5","31/01/2013","45.973","30.860","15.113","750.758","47.681");
		DetallePlanDePagoVO cuota6 = new DetallePlanDePagoVO("6","28/02/2013","45.973","31.481","14.492","719.924","47.681");
		DetallePlanDePagoVO cuota7 = new DetallePlanDePagoVO("7","31/03/2013","45.973","32.115","13.858","688.443","47.681");
		DetallePlanDePagoVO cuota8 = new DetallePlanDePagoVO("8","30/04/2013","45.973","32.761","13.212","656.328","47.681");
		DetallePlanDePagoVO cuota9 = new DetallePlanDePagoVO("9","31/05/2013","45.973","33.421","12.552","623.567","47.681");
		DetallePlanDePagoVO cuota10 = new DetallePlanDePagoVO("10","30/06/2013","45.973","34.093","11.880","590.146","47.681");
		DetallePlanDePagoVO cuota11 = new DetallePlanDePagoVO("11","31/07/2013","45.973","34.780","11.193","556.053","47.681");
		DetallePlanDePagoVO cuota12 = new DetallePlanDePagoVO("12","31/08/2013","45.973","35.480","10.493","521.273","47.681");
		DetallePlanDePagoVO cuota13 = new DetallePlanDePagoVO("13","30/09/2013","45.973","36.194","9.779","485.793","47.681");
		DetallePlanDePagoVO cuota14 = new DetallePlanDePagoVO("14","31/10/2013","45.973","36.923","9.050","449.599","47.681");
		DetallePlanDePagoVO cuota15 = new DetallePlanDePagoVO("15","30/11/2013","45.973","37.666","8.307","412.676","47.681");
		DetallePlanDePagoVO cuota16 = new DetallePlanDePagoVO("16","31/12/2013","45.973","38.424","7.549","375.010","47.681");
		DetallePlanDePagoVO cuota17 = new DetallePlanDePagoVO("17","31/01/2014","45.973","39.198","6.775","336.586","47.681");
		DetallePlanDePagoVO cuota18 = new DetallePlanDePagoVO("18","28/02/2014","45.973","39.987","5.986","297.388","47.681");
		DetallePlanDePagoVO cuota19 = new DetallePlanDePagoVO("19","31/03/2014","45.973","40.792","5.181","257.401","47.681");
		DetallePlanDePagoVO cuota20 = new DetallePlanDePagoVO("20","30/04/2014","45.973","41.613","4.360","216.609","47.681");
		DetallePlanDePagoVO cuota21 = new DetallePlanDePagoVO("21","31/05/2014","45.973","42.450","3.523","174.996","47.681");
		DetallePlanDePagoVO cuota22 = new DetallePlanDePagoVO("22","30/06/2014","45.973","43.305","2.668","132.546","47.681");
		DetallePlanDePagoVO cuota23 = new DetallePlanDePagoVO("23","31/07/2014","45.973","44.177","1.796","89.241","47.681");
		DetallePlanDePagoVO cuota24 = new DetallePlanDePagoVO("24","31/08/2014","45.973","45.064","909","45.064","47.681");
		
		cuotas.add(cuota0);
		cuotas.add(cuota1);
		cuotas.add(cuota2);
		cuotas.add(cuota3);
		cuotas.add(cuota4);
		cuotas.add(cuota5);
		cuotas.add(cuota6);
		cuotas.add(cuota7);
		cuotas.add(cuota8);
		cuotas.add(cuota9);
		cuotas.add(cuota10);
		cuotas.add(cuota11);
		cuotas.add(cuota12);
		cuotas.add(cuota13);
		cuotas.add(cuota14);
		cuotas.add(cuota15);
		cuotas.add(cuota16);
		cuotas.add(cuota17);
		cuotas.add(cuota18);
		cuotas.add(cuota19);
		cuotas.add(cuota20);
		cuotas.add(cuota21);
		cuotas.add(cuota22);
		cuotas.add(cuota23);
		cuotas.add(cuota24);
		return cuotas;
	}

}
