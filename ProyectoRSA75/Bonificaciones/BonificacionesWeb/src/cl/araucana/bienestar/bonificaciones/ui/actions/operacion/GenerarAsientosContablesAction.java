package cl.araucana.bienestar.bonificaciones.ui.actions.operacion;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesDelegate;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadPendienteVO;

/**
 * @version 	1.0
 * @author
 */
public class GenerarAsientosContablesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		DynaValidatorActionForm dynaValidatorActionForm =(DynaValidatorActionForm) form;
		
		cl.araucana.common.ui.UserInformation userinformation = (cl.araucana.common.ui.UserInformation)request.getSession(false).getAttribute("application.userinformation");
		if (userinformation==null) userinformation = new cl.araucana.common.ui.UserInformation();

		String codigo=(String) dynaValidatorActionForm.get("codigo");
		ServicesOperacionesDelegate delegate = new ServicesOperacionesDelegate();
		ArrayList lista=(ArrayList)request.getSession(false).getAttribute("lista");
		ContabilidadPendienteVO contabilidad = (ContabilidadPendienteVO)lista.get(Integer.parseInt(codigo));
		
		contabilidad.setUsuario(userinformation.getUsuario());
		delegate.generarAsientosContables((ContabilidadPendienteVO)lista.get(Integer.parseInt(codigo)));
		String referer="/setFichaAsientosContables.do";
		String target="success";

		// Guardar en memoria el resultado
		request.getSession(false).setAttribute("referer",referer);

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);

	}
}
