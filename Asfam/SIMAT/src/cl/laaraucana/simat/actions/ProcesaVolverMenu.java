package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;

public class ProcesaVolverMenu extends AbstractAction {

	public ActionForward mostrarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = mapping.findForward("successMenu");
		ActionErrors errors = new ActionErrors();

		String msgEP = null;
		String periodo = null;
		HttpSession sesionActual = null;
		//obtiene estado Validacion del periodo
		System.out.println("start try");
		try {
			sesionActual = request.getSession(true);
			if (sesionActual.getAttribute("nombre") == null) {
				// User is not logged in.  
				forward = mapping.findForward("errorLogin");
			} else {
				periodo = (String) sesionActual.getAttribute("periodo");
				if (periodo == null) {
					forward = mapping.findForward("errorPeriodo");
				} else {
					ProcesaPeriodos pp = new ProcesaPeriodos();
					String keyProcesoCarga = null;
					String keyProcesoValidacion = null;
					//evaluamos estado validacion del periodo
					keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);
					//evaluamos estado proceso carga del periodo	
					keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);
					//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
					request.setAttribute("keyProcesoCarga", keyProcesoCarga);
					//agregar atribute para respuesta sobre estado validacion periodo
					request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
					request.setAttribute("fechaPeriodo", periodo);
					//forward					
					forward = mapping.findForward("successMenu");
				}
			}
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorLogin");
		}
		return forward;
	}//end 
}//end class