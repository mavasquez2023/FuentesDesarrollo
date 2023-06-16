package cl.araucana.ctasfam.presentation.struts.vo;

import java.util.List;

import cl.araucana.ctasfam.business.to.ProcesoBashTO;

public class EmpresaComprobante {
	private String rut;
	private String razonSocial;
	private List<ProcesoBashTO> listProp;
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public List<ProcesoBashTO> getListProp() {
		return listProp;
	}
	public void setListProp(List<ProcesoBashTO> listProp) {
		this.listProp = listProp;
	}
	
	
}
