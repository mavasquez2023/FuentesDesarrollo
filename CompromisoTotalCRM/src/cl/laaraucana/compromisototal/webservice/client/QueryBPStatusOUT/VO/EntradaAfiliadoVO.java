package cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO;

import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;

public class EntradaAfiliadoVO extends AbstractEntradaVO{
	private String rut;
	private String flag;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
