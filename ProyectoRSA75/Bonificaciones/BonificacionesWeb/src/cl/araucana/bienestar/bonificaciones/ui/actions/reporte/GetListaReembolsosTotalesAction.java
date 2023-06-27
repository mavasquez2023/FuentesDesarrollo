package cl.araucana.bienestar.bonificaciones.ui.actions.reporte;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaReembolsosTotalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList listaReembolsosTotales=delegate.getReembolsosTotales();
		String target="listaReembolsosTotales";
		request.getSession(false).setAttribute("lista",listaReembolsosTotales);
		request.getSession(false).removeAttribute("procesoCarga");
		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}
