package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

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
public class SetFichaPagoConveniosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		//Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList listaPagosPorRealizar=delegate.getPagosPorRealizar();
		String target="listaPagosPorRealizar";
		request.getSession(false).setAttribute("lista",listaPagosPorRealizar);

		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("opeGeneraPagoConvenio")) {
			opciones.add("Generar Pago Convenios");
			opcionesValores.add("1");
		}
		
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);


	}
}
