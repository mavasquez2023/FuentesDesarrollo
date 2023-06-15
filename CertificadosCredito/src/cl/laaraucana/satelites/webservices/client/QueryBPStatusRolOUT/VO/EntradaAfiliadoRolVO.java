package cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO;

import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;

public class EntradaAfiliadoRolVO extends AbstractEntradaVO{
	private String rut;
	private String rol;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}
