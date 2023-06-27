package cl.araucana.bienestar.bonificaciones.ui.actions.caso;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.prestamo.vo.ImpuestoVO;

/**
 * @version 	1.0
 * @author
 */
public class SetFichaImpuestoAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		// Objeto de Permisos de la Aplicación
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		ImpuestoVO impuesto=null;
		request.getSession(false).setAttribute("impuesto",impuesto);
		
		ArrayList opciones=new ArrayList();
		ArrayList opcionesValores=new ArrayList();		

		if (userinformation.hasAccess("casImpuesto")) {
			opciones.add("Calcular Impuesto");
			opcionesValores.add("1");	
		}

		request.getSession(false).setAttribute("caso.opciones",opciones);
		request.getSession(false).setAttribute("caso.opciones.valores",opcionesValores);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("impuesto");
		this.saveToken(request);
		return (forward);
	}
}
