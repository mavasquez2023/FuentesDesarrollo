package com.araucana.entity;

public class Empresa {
	private String rut;
	private String dv;
	private String razon;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return "Empresa [rut=" + rut + ", dv=" + dv + ", razon=" + razon + "]";
	}

}
