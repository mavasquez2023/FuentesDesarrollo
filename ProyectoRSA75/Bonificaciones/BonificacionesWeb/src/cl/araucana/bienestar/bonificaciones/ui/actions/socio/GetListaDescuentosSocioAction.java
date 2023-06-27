package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaDescuentosSocioAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Socio socio=(Socio)request.getSession(false).getAttribute("socio");	

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList listaDescuentos=delegate.getDescuentosAplicadosSocio(socio.getRut());
		String target="listaDescuentos";
		request.getSession(false).setAttribute("lista",listaDescuentos);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}
