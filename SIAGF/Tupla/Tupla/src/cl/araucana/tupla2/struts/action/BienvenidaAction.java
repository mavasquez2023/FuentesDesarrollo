package cl.araucana.tupla2.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.tupla2.struts.utils.Utiles;

public class BienvenidaAction extends Action {

	public ActionForward execute(ActionMapping arg0, ActionForm arg1, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String periodo= Utiles.getPeriodo();
		request.getSession().setAttribute("periodoActual", periodo);
		return arg0.findForward("onSuccess");
	}
	


}
