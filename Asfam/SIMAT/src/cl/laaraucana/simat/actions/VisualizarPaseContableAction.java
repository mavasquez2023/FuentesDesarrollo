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
import cl.laaraucana.simat.entidades.ResultadoPaseContable;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.mannagerDB2.PaseContableManager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class VisualizarPaseContableAction extends Action {

	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Despliega por pantalla la tabla de cuentas y totales para generar el pase contable
	 * Si los montos (debito y credio) no cuadran, no se muestra la opción 'Procesar'
	 * Las cuentas se almacenan en sesión, ya que son recibidas posteriormente por ProcesarPaseContableAction
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesionActual = request.getSession(true);
		String mensaje ="";
		String codigo = "5";
		try{
			
			if(sesionActual == null || sesionActual.getAttribute("nombre")==null){
				response.sendRedirect(request.getServletContext().getInitParameter("urlMenuDinamico"));
				return null;
			}
			
			if(sesionActual.getAttribute("periodo")==null){
				//codigo = "5";
				//mensaje = "Error: No se ha seleccionado un periodo para trabajar";
				return mapping.findForward("seleccionPeriodo");
			}else{
				String periodo = (String) sesionActual.getAttribute("periodo");
				
				//Validar estado de ejecución de proceso requisito
				ProcesaPeriodos procPeriod = new ProcesaPeriodos();
				ProcesoPeriodo estCarga= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_CARGA, periodo);
				ProcesoPeriodo estValid= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_VALIDACION, periodo);
				
				request.setAttribute("estCarga", estCarga);
				request.setAttribute("estValid", estValid);
				if(estCarga.getCodEstado()==null || 
					estValid.getCodEstado()==null ||
					!estCarga.getCodEstado().equals("T") || 
					!estValid.getCodEstado().equals("T")){
					codigo = "5";
					mensaje = "Primero debe ejecutar los procesos de carga y validación.";
					sesionActual.setAttribute("paseContable", null);
				}else{
					if(sesionActual.getAttribute("cuadrePreBalance")!=null && (Boolean) sesionActual.getAttribute("cuadrePreBalance")){
						PaseContableManager paseMgr = new PaseContableManager();
						ResultadoPaseContable res = paseMgr.obtenerPaseContable(periodo);
						if(res.getCodigo().equals("3")){
							sesionActual.setAttribute("cuadrePaseContable", true);
						}
						mensaje = res.getMensaje();
						codigo = res.getCodigo();
						
						sesionActual.setAttribute("paseContable", res);
					}else{
						//mensaje = "No se ha generado el Pre Balance o existen cuentas sin cuadrar";
						//codigo = "5";
						//Obliga a generar el pase desde el comienzo
						return mapping.findForward("validarPreBalance");
					}
				}
				request.setAttribute("periodoText", ManejoHoraFecha.getPeriodoString(periodo));
				
				//Consultar el estado de ejecución del pase contable
				ProcesoPeriodo estPase= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_PASE_CONTABLE, periodo);
				request.setAttribute("estPase", estPase);
			}
		} catch (Exception e) {
			log.error("LOG catch: ", e);
			codigo = "5";
			mensaje = "Se produjo un problema al procesar su solicitud, causa: " + e.getCause();
		}
		
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("tipoMensaje", codigo);
		forward = mapping.findForward("success");
		return (forward);
	}
}