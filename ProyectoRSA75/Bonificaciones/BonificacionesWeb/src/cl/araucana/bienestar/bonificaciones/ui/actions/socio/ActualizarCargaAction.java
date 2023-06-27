package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class ActualizarCargaAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Carga carga=(Carga)request.getSession(false).getAttribute("carga");
		ServicesSociosDelegate delegate = new ServicesSociosDelegate();
		delegate.actualizaCarga(carga);
		String target="success";
		String referer="/getListaCargasSocio.do";
		request.getSession(false).setAttribute("referer",referer);
		
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);

		return (forward);
	}
}
