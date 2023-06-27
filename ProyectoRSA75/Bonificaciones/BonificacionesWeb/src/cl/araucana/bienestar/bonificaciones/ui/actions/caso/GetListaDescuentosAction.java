package cl.araucana.bienestar.bonificaciones.ui.actions.caso;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaDescuentosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Caso caso=(Caso)request.getSession(false).getAttribute("caso");							

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();

		caso=delegate.getCuotasCaso(caso);

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("caso",caso);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaDescuentos");
		return (forward);

	}
}
