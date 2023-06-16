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
// clase que genera los archivos planos (carga la BD SIMAT segun resultado de un programa cobol) para simat.

public class ProcedimientoGenerarArchivosAction extends AbstractAction {

	public ActionForward procesoGenerarArchivos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward(); // return value	
		String msgEP = null;
		String periodo = null;
		String user;
		HttpSession sesionActual = null;

		//obtencion periodo y usuario
		try {
			//recuperar session
			sesionActual = request.getSession(true);
			periodo = (String) sesionActual.getAttribute("periodo");
			user = (String) sesionActual.getAttribute("nombre");
			System.out.println("* * * * * [ACTION: procesoGenerarArchivos] * * * * *");
			System.out.println("* * * * * [periodo: " + periodo + "] * * * * *");
			System.out.println("* * * * * [user   :" + user + "] * * * * *");

			if (periodo == null) {
				forward = mapping.findForward("errorPeriodo");
			} else {
				ProcesaPeriodos pp = new ProcesaPeriodos();
				String keyProcesoCarga = null;
				String keyProcesoValidacion = null;
				//keyProcesoCarga=pp.callProcesoCargar(periodo, user);
				keyProcesoCarga = pp.callProcesoCargar2(periodo, user);
				System.out.println("* * * * * [END callProcesoCargar2 : " + keyProcesoCarga + "] * * * * *");
				/*				
				//solicitamos estado proceso carga del periodo	
					keyProcesoCarga=pp.getEstadoProcesoCarga(periodo);
						*/
				//solicitamos estado validacion del periodo
				keyProcesoValidacion = pp.getEstadoProcesoValidacion(periodo);
				System.out.println("* * * * * [getEstadoProcesoValidacion : " + keyProcesoValidacion + "] * * * * *");
				//agregar atribute para respuesta sobre estado Proceso Generacion BD periodo
				request.setAttribute("keyProcesoCarga", keyProcesoCarga);

				//agregar atribute para respuesta sobre estado validacion periodo
				request.setAttribute("keyProcesoValidacion", keyProcesoValidacion);
				request.setAttribute("fechaPeriodo", periodo);
			}
		} catch (Exception e) {
			msgEP = "a ocurrido un error en el proceso de Carga BD SIMAT: " + ", catch: " + e;
			request.setAttribute("msgEscrituraPlanos", msgEP);
			return (forward = mapping.findForward("escrituraError"));
		}
		return (forward = mapping.findForward("validacionOK"));
		//forward = mapp
	}//end

}
