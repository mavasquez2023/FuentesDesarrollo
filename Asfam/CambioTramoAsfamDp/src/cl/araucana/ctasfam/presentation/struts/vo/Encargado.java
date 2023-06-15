package cl.araucana.ctasfam.presentation.struts.vo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cl.araucana.ctasfam.resources.util.Padder;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;

public class Encargado {

	private String rut;

	private String fullName;

	private Map empresas = null;

	public Encargado() {
		super();
	}

	public Encargado(String rut, String fullName, List empresas) {
		this.rut = rut;
		this.fullName = fullName;
		this.empresas = new TreeMap();
		Iterator iterator = empresas.iterator();
		while (iterator.hasNext()) {
			EmpresaTO empresaTO = (EmpresaTO) iterator.next();
			RutTO rutEmpresa = empresaTO.getRut();
			String nombreEmpresa = empresaTO.getNombre();
			Empresa empresa = new Empresa(rutEmpresa.getRut() + "-"
					+ rutEmpresa.getDv(), nombreEmpresa);
			this.empresas.put(new Integer(empresa.getRut()), empresa);
		}
	}

	public String getFullRut() {
		return rut;
	}

	public int getRut() {
		String auxRut = getFullRut();
		int index = auxRut.indexOf("-");
		if (index > 0) {
			return Integer.parseInt(auxRut.substring(0, index));
		}
		return Integer.parseInt(auxRut);
	}

	public char getDV() {
		String auxRut = getFullRut();
		return auxRut.charAt(auxRut.length() - 1);
	}

	public String getFormattedRut() {
		int rut = getRut();
		return Padder.padSeparators(rut, '.') + "-" + getDV();
	}

	public String getFullName() {
		return fullName;
	}

	public void addEmpresa(Empresa empresa) {
		empresas.put(new Integer(empresa.getRut()), empresa);
	}

	public Collection getEmpresas() {
		return empresas.values();
	}

	public int getEmpresasCount() {
		return empresas.size();
	}
}
