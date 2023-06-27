package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;

public class ChangePasswordActionForm extends ActionForm
{

	private static final long serialVersionUID = -468192642461147540L;
	private String claveActual;
	private String nuevaClave;
	private String confirmacionNuevaClave;
	public String getClaveActual()
	{
		return claveActual;
	}
	public void setClaveActual(String claveActual)
	{
		this.claveActual = claveActual;
	}
	public String getConfirmacionNuevaClave()
	{
		return confirmacionNuevaClave;
	}
	public void setConfirmacionNuevaClave(String confirmacionNuevaClave)
	{
		this.confirmacionNuevaClave = confirmacionNuevaClave;
	}
	public String getNuevaClave()
	{
		return nuevaClave;
	}
	public void setNuevaClave(String nuevaClave)
	{
		this.nuevaClave = nuevaClave;
	}
	
	
	

}
