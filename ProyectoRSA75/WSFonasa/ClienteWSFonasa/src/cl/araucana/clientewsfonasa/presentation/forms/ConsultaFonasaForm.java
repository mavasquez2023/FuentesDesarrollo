package cl.araucana.clientewsfonasa.presentation.forms;

import org.apache.struts.action.ActionForm;

public class ConsultaFonasaForm extends ActionForm{
	private String rut;
	
	public ConsultaFonasaForm(){}
	
	public ConsultaFonasaForm(String rut){
		this.rut = rut;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	
}
