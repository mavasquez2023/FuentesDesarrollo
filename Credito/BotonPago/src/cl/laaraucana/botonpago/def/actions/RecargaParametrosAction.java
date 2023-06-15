package cl.laaraucana.botonpago.def.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * @version 	1.0
 * @author
 */
public class RecargaParametrosAction extends Action

{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Constantes.kill();
		request.setAttribute("tipo", Constantes.getInstancia().MSG_TIPO_ALERTA);
		request.setAttribute("titulo", "Mensaje");
		request.setAttribute("mensaje", "Kill ejecutado!");

		return mapping.findForward("resp");
	}
}
