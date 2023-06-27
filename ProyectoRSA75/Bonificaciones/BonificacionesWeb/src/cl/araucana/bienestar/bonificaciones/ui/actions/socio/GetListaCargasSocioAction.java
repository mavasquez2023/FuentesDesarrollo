package cl.araucana.bienestar.bonificaciones.ui.actions.socio;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCargasSocioAction extends Action {
	Logger logger=Logger.getLogger(this.getClass());

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		Socio socio=(Socio)request.getSession(false).getAttribute("socio");
	
		ServicesSociosDelegate delegate = new ServicesSociosDelegate();
		ArrayList listaCargas=delegate.getCargasSocio(socio.getRut());
		logger.debug("lista.size: "+listaCargas.size());

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("lista.cargas.socio",listaCargas);

		// Write logic determining how the user should be forwarded.

		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaCargasSocio");
		return (forward);
	}
}
