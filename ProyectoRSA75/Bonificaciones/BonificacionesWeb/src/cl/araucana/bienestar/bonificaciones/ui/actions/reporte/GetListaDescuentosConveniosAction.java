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
public class GetListaDescuentosConveniosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList listaDescuentosConvenios=delegate.getDescuentosConvenios();
		String target="listaDescuentosConvenios";
		request.getSession(false).setAttribute("lista",listaDescuentosConvenios);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}
