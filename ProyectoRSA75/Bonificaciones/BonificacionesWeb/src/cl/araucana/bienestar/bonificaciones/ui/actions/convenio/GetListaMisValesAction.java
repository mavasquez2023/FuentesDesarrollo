package cl.araucana.bienestar.bonificaciones.ui.actions.convenio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaMisValesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		Convenio convenio=(Convenio)request.getSession(false).getAttribute("convenio");							
		ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
	
		ArrayList listaVales=delegate.getValesByConvenio(convenio.getCodigo());
	
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.vales",listaVales);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaVales");
		return (forward);

	}
}
