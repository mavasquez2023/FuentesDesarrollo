package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

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
public class GetListaEventosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		Caso caso=(Caso)request.getSession(false).getAttribute("caso");							

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();
	
		caso=delegate.getEventos(caso);
	
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("casAgregarEvento")) {
			opciones.add("Agregar Evento");
			opcionesValores.add("1");
		}
		
		// Guardar en memoria el resultado
		request.setAttribute("opciones",opciones);
		request.setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("caso",caso);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaEventos");
		this.saveToken(request);
		return (forward);

	}
}
