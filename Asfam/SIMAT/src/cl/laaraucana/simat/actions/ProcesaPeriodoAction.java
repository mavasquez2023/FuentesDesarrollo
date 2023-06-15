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
import cl.laaraucana.simat.forms.PeriodoForm;

/**
 * @version 	1.0
 * @author
 */
public class ProcesaPeriodoAction extends AbstractAction {
	public ActionForward getEstadoPeriodo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {

			//recuperar session
			HttpSession sesionActual = request.getSession(true);
			System.out.println("sesiongetPEriodo : " + sesionActual.getAttribute("nombre"));
			System.out.println("sesiongetPEriodo : " + sesionActual.getAttribute("clave"));

			//obtener periodo a evaluar
			PeriodoForm periodoForm = (PeriodoForm) form;
			if (periodoForm == null || (periodoForm.getPeriodo().equals(""))) {
				forward = mapping.findForward("errorPeriodo");
			} else {
				System.out.println("periodo form: " + periodoForm.getPeriodo());

				ProcesaPeriodos pp = new ProcesaPeriodos();

				String periodo = null;

				String keyProcesoCarga = null;
				String keyProcesoValidacion = null;

				periodo = periodoForm.getPeriodo();
				sesionActual.setAttribute("periodo", periodo);

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
				forward = mapping.findForward("successPeriodo");
			}

		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorPeriodo");
		}
		return forward;
	}

	public ActionForward cambiarPeriodo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		try {
			System.out.println("ir a cambio periodo");
			forward = mapping.findForward("successCambioPeriodo");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			forward = mapping.findForward("errorPeriodo");
		}
		return forward;
	}
}
