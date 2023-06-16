/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.entidades.threads.GenerarChequesThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarChequesAction extends Action {
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * 
	 */
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		String cierre="";
		try{
			forward = mapping.findForward("NOTOK");
			logger.info("Entrando CargarChequeAction.");
			HttpSession sesion= request.getSession();
			
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			//int periodo= paramTO.getPeriodoSistema();
			String periodo= (String)request.getParameter("periodo");
			cierre= (String)request.getParameter("cierre");
			String emails= paramTO.getEmailUsuario();
			String option= (String)request.getParameter("cleanTE");
			
			//Invocando una hebra para Generar Cheques
			if(!paramTO.getProcesosActivos().containsKey("CHEQUE:" + cierre)){
				GenerarChequesThreads chequeThreads= new GenerarChequesThreads(Integer.parseInt(periodo), Integer.parseInt(cierre), option, emails);
				paramTO.addProceso("CHEQUE:" + cierre, Today.getFecha_Hora());
				paramTO.addThread("CHEQUE:" + cierre, chequeThreads);
				chequeThreads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Fin CargarChequesAction, forward= "+ forward);
		request.setAttribute("accion", "Cheques Tesorería, cierre " + cierre);
		request.setAttribute("origen", "ENT");
		return forward;
	}
}
