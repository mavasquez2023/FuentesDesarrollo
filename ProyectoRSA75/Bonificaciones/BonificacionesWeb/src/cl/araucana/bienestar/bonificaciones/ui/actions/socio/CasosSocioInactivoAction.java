package cl.araucana.bienestar.bonificaciones.ui.actions.socio;

import java.util.ArrayList;

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
public class CasosSocioInactivoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String rutSocio = (String)request.getParameter("socio");
		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
		Caso caso = new Caso();
		
		caso.setRutSocio(rutSocio);
		caso.setEstado(Caso.STD_ACTIVO);
		ArrayList listaCasos = delegate.getCasosSocioInactivo(caso);
		
		String target="listaCasos";
		request.getSession(false).setAttribute("lista.casos",listaCasos);
		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		this.resetToken(request);

		return (forward);
	}
}
