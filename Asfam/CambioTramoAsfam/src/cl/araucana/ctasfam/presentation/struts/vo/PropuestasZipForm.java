package cl.araucana.ctasfam.presentation.struts.vo;

import org.apache.struts.action.ActionForm;

public class PropuestasZipForm extends ActionForm{
	
	public PropuestasZipForm(){
		
	}

	private String propuestas;
	private String serviceName;
	private String rutt;
	 
	 

	public String getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(String propuestas) {
		this.propuestas = propuestas;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	 

	public String getRutt() {
		return rutt;
	}

	public void setRutt(String rutt) {
		this.rutt = rutt;
	}

	 	
	
}
