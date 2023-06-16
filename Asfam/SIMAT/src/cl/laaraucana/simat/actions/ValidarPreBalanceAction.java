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
import cl.laaraucana.simat.entidades.ResultadoPreBalance;
import cl.laaraucana.simat.estadoPeriodo.ProcesaPeriodos;
import cl.laaraucana.simat.mannagerDB2.PreBalanceMannager;
import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class ValidarPreBalanceAction extends Action {
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
			String periodo = (String) sesionActual.getAttribute("periodo");
			sesionActual.setAttribute("cuadrePreBalance", false);
			
			if(sesionActual == null || sesionActual.getAttribute("nombre")==null){
				response.sendRedirect(request.getServletContext().getInitParameter("urlMenuDinamico"));
				return null;
			}
			
			if(sesionActual.getAttribute("periodo")==null){
				//codigo = "5";
				//mensaje = "Error: No se ha seleccionado un periodo para trabajar";
				return mapping.findForward("seleccionPeriodo");
			}else{
			
				//Validar estado de ejecución de proceso requisito
				ProcesaPeriodos procPeriod = new ProcesaPeriodos();
				ProcesoPeriodo estCarga= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_CARGA, periodo);
				ProcesoPeriodo estValid= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_VALIDACION, periodo);
				ProcesoPeriodo estPase= procPeriod.getEstadoProceso(ProcesoPeriodo.PROCESO_PASE_CONTABLE, periodo);
				
				request.setAttribute("estCarga", estCarga);
				request.setAttribute("estValid", estValid);
				request.setAttribute("estPase", estPase);
				if(estCarga.getCodEstado()==null || 
						estValid.getCodEstado()==null ||
						!estCarga.getCodEstado().equals("T") || 
						!estValid.getCodEstado().equals("T")){
						codigo = "5";
						mensaje = "Primero debe ejecutar los procesos de carga y validación.";
						sesionActual.setAttribute("cuentasPreBalance", null);
				}else{
					try{
						PreBalanceMannager preBalance = new PreBalanceMannager();
						ResultadoPreBalance result = preBalance.validarPreBalance();
						
						mensaje = result.getMensaje();
						codigo = result.getCodigo();
						if(result.getCodigo().equals("3")){
							sesionActual.setAttribute("cuadrePreBalance", true);
						}
						
						sesionActual.setAttribute("cuentasPreBalance",result.getResultPreBalance());
					} catch (Exception e) {
						codigo = "5";
						mensaje ="Se produjo un problema al procesar su solicitud, causa: " + e.getCause();
						log.error("LOG catch: ", e);
					}
				}
				request.setAttribute("periodoText", ManejoHoraFecha.getPeriodoString(periodo));
			}
			request.setAttribute("tipoMensaje", codigo);
			request.setAttribute("mensaje", mensaje);
			forward = mapping.findForward("success");
			return (forward);
	}
}