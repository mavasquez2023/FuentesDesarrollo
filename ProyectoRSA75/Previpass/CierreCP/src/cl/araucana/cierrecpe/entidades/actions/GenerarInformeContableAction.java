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
import cl.araucana.cierrecpe.entidades.threads.GenerarInformeContableThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarInformeContableAction extends Action {
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
		int periodo=0;
		try{
			forward = mapping.findForward("NOTOK");
			logger.info("Entrando Action Cargar Informe Contable");
			HttpSession sesion= request.getSession();
			
			//rescatando parámetros de la página
			String option= (String)request.getParameter("clean");
			
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String periodoparam= (String)request.getParameter("periodo");
			periodo= Integer.parseInt(periodoparam);
			String emails= paramTO.getEmailUsuario();
			
			//Invocando una hebra para Generar Informe Contable
			if(!paramTO.getProcesosActivos().containsKey("INFORME:" + periodo)){
				GenerarInformeContableThreads informeThreads= new GenerarInformeContableThreads(periodo, option, emails);
				paramTO.addProceso("INFORME:" + periodo, Today.getFecha_Hora());
				paramTO.addThread("INFORME:" + periodo, informeThreads);
				informeThreads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}

		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Fin CargarChequesAction, forward= "+ forward);
		request.setAttribute("accion", "Informe Contable, periodo " + periodo);
		request.setAttribute("origen", "ENT");
		return forward;
	}
}
