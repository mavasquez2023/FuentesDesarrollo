package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Validaciones de simulación en calculadora
 * <ul>
 * <li>tipoTrabajador - [Tipo de afiliado: 1: Afiliado, 2:Pensionado]
 * <li>tipoProducto - [Credito especial, social, educacional]
 * <li>oficina - [Codigo de oficina]
 * <li>monto - [Monto solicitado]
 * <li>cuotas - [Cantidad de cuotas]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class SimularEmpresaForm extends ActionForm

{

	private static final long serialVersionUID = -1042863742320580833L;
	private String rutEmpleado;


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setRutEmpleado("");
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		HttpSession sesion = request.getSession();
		//Si es empresa
		if(sesion.getAttribute("esEmpresa")!= null){
			rutEmpleado = rutEmpleado.replace(".", "");
			if(rutEmpleado == null || rutEmpleado.trim().isEmpty()){
				errors.add("rut", new ActionMessage("errors.rut.invalido"));
			}
		}
		
		return errors;
	}

	public String getRutEmpleado() {
		return rutEmpleado;
	}

	public void setRutEmpleado(String rutEmpleado) {
		this.rutEmpleado = rutEmpleado;
	}
}
