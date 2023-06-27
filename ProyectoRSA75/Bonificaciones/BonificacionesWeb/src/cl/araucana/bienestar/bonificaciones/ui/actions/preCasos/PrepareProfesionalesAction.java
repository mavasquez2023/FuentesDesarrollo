package cl.araucana.bienestar.bonificaciones.ui.actions.preCasos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Pablo Palacios 
 */
public class PrepareProfesionalesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		String target=null;
		String destino=request.getParameter("destino");
		if(destino.equals("profesional")){
			target="profesional";
		}
		else if(destino.equals("carga")){
			target="carga";
		}
		else if(destino.equals("convenio")){
			target="convenio";
		}
		else if(destino.equals("vale")){
			target="vale";
		}

		// Write logic determining how the user should be forwarded.
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(target);
		return (forward);
	}
}
