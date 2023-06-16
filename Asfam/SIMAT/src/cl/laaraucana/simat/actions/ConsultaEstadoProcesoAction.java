package cl.laaraucana.simat.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.simat.entidades.ProcesoPeriodo;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;

public class ConsultaEstadoProcesoAction extends Action {
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Valida si se está ejecutando pase contable
	 * Ejecuta programa AS400 que genera pase contable
	 * Obtiene desde sesión el listado de cuentas y sus importes
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ActionForward forward = new ActionForward(); // return value
			HttpSession sesionActual = request.getSession(true);
			String mensaje ="";
			String codigo = "5";
			
			try{
				if(sesionActual.getAttribute("periodo")==null){
					codigo = "5";
					mensaje = "Error: No se ha seleccionado un periodo para trabajar";
				}else{
					String periodo = (String) sesionActual.getAttribute("periodo");
					
					//Validar estado de ejecución de proceso requisito
					ProcesaPeriodos procPeriod = new ProcesaPeriodos();
					ProcesoPeriodo estCarga= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_CARGA, periodo);
					ProcesoPeriodo estValid= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_VALIDACION, periodo);
					//Consultar el estado de ejecución del pase contable
					ProcesoPeriodo estPase= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_PASE_CONTABLE, periodo);
					request.setAttribute("estPase", estPase);
					request.setAttribute("estCarga", estCarga);
					request.setAttribute("estValid", estValid);
					codigo = "3";
				}
			} catch (Exception e) {
				codigo = "5";
				mensaje ="Se produjo un problema al procesar su solicitud, causa: " + e.getCause();
				log.error("LOG catch: ", e);
			}
			request.setAttribute("tipoMensaje", codigo);
			request.setAttribute("mensaje", mensaje);
			forward = mapping.findForward("success");
			return (forward);
	}
}