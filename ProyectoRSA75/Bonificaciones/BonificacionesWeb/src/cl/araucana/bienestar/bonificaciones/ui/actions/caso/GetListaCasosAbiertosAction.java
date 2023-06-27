package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;

/**
 * @version 	1.0
 * @author
 */
public class GetListaCasosAbiertosAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		DynaValidatorActionForm dynaValidatorActionForm =
				(DynaValidatorActionForm) form;

		ServicesCasosDelegate delegate = new ServicesCasosDelegate();

		ArrayList listaCasos=delegate.getCasosNoBonificados((String)dynaValidatorActionForm.get("tipoCaso"));
/*		String [] check=new String[listaCasos.size()];
		for(int t=0;t<check.length;t++){
			check[t]=String.valueOf(t);
		}
		dynaValidatorActionForm.set("indice",check);
*/		ArrayList opciones=new ArrayList();

		ArrayList opcionesValores=new ArrayList();
		if (userinformation.hasAccess("opeGeneraBonificacion")) {
			opciones.add("Calcular Bonificacion");
			opcionesValores.add("1");
		}
		
//		dynaValidatorActionForm.set("opcion",check);
		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("opciones",opciones);
		request.getSession(false).setAttribute("opciones.valores",opcionesValores);
		request.getSession(false).setAttribute("lista.casos",listaCasos);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("listaCasosNoBonificados");
		return (forward);

	}
}
