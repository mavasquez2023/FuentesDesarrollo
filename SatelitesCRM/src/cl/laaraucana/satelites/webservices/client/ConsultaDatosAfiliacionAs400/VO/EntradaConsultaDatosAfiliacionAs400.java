package cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaConsultaDatosAfiliacionAs400 extends AbstractEntradaVO{
	
	private String rut;
	private String flag1 = "0";
	private String flag2 = "0";
	private String flag3 = "0";
	
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getFlag1() {
		return flag1;
	}
	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	public String getFlag2() {
		return flag2;
	}
	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}
	public String getFlag3() {
		return flag3;
	}
	public void setFlag3(String flag3) {
		this.flag3 = flag3;
	}
	
}
