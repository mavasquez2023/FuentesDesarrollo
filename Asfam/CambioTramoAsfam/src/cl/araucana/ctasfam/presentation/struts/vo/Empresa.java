package cl.araucana.ctasfam.presentation.struts.vo;

import cl.araucana.ctasfam.resources.util.Padder;

public class Empresa implements Comparable {
	
	private String rut;
	private String name;
	
	private int flag;
	
	public Empresa() {
		super();
	}
	
	public Empresa(String rut, String name) {
		this.rut = rut;
		this.name = name;
		flag = 0;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRut() {
		String auxRut = getFullRut();
		int index = auxRut.indexOf("-");
		if (index > 0) {
			return Integer.parseInt(auxRut.substring(0, index));
		}
		return Integer.parseInt(auxRut);
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getFullRut() {
		return rut;
	}
	
	public char getDV() {
		String auxRut = getFullRut();
		
		return auxRut.charAt(auxRut.length() - 1);
	}

	public String getFormattedRut() {
		int rut = getRut();
		return Padder.padSeparators(rut, '.') + "-" + getDV();
	}

	public int compareTo(Object o) {
		Empresa empresa = (Empresa) o;
		return getFullRut().compareTo(empresa.getFullRut());
	}
}
