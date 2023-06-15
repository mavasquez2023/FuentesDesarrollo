package cl.laaraucana.simulacion.actions.simuladoracuerdo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;
import cl.laaraucana.simulacion.webservices.client.DataServiceUtil;

public class IngresarParametrosAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		request.setAttribute("rutAfiliado", request.getParameter("rutConsulta"));

		forward = mapping.findForward("ingresarParametros");
		
		return forward;
	}
}
