/*#################################################################*/
/*---- FECHA CREACION: 1-08-2013 ----------------------------------*/
/*---- NOMBRE: Matias Salas S. ------------------------------------*/
/*---- EMPRESA: IBM -----------------------------------------------*/
/*#################################################################*/

package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;

/**
 * @version 	1.0
 * @author
 */
// clase que genera los archivos planos para simat.

public class ProcedimientoValidarAction extends AbstractAction {

	public ActionForward ProcedimientoValidar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		HttpSession sesionActual = null;
		String user;
		//obtencion periodo
		try {
			//					recuperar session
			sesionActual = request.getSession(true);
			periodo = (String) sesionActual.getAttribute("periodo");
			user = (String) sesionActual.getAttribute("nombre");

			if (periodo == null) {
				forward = mapping.findForward("errorPeriodo");
			} else {
				System.out.println("* * * * * [ACTION: procesoValidarArchivos] * * * * *");
				System.out.println("* * * * * [periodo: " + periodo + "] * * * * *");
				System.out.println("* * * * * [user   :" + user + "] * * * * *");
				ProcesaPeriodos pp = new ProcesaPeriodos();
				String keyProcesoCarga = null;
				String keyProcesoValidacion = null;
				//keyProcesoValidacion=pp.callProcesoValidar(periodo, user);
				keyProcesoValidacion = pp.callProcesoValidar2(periodo, user);
				System.out.println("* * * * * [END callProcesoValidar : " + keyProcesoValidacion + "] * * * * *");
				//solicitamos estado proceso carga del periodo	
				keyProcesoCarga = pp.getEstadoProcesoCarga(periodo);
				System.out.println("* * * * * [getEstadoProcesoCarga : " + keyProcesoCarga + "] * * * * *");
				/*
				//solicitamos estado validacion del periodo
				keyProcesoValidacion=pp.getEstadoProcesoValidacion(periodo);
				*/
				//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
				request.setAttribute("keyProcesoCarga", keyProcesoCarga);

				//agregar atribute para respuesta sobre estado validacion periodo
				request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
				request.setAttribute("fechaPeriodo", periodo);
			}
		} catch (Exception e) {
			msgEP = "a ocurrido un error en el proceso de validacion: " + ", catch: " + e;
			e.printStackTrace();
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}
		//llamado a procedimiento de almacenado para validacion de tablas SIMATDTA,

		return (forward = mapping.findForward("validacionOK"));
		//forward = mapp
	}//end

}
