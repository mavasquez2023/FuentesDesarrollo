/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;

import java.util.ArrayList;
import java.util.List;
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
import cl.araucana.cierrecpe.entidades.business.ConcatenarCierres;
import cl.araucana.cierrecpe.entidades.threads.ConcatenarCierresThreads;
import cl.araucana.cierrecpe.entidades.threads.GenerarArchivosEntidadesThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerCierresAction extends Action {
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
		//List cierresDomino= new ArrayList();
		List cierresPrevipass= new ArrayList();
		try{
			logger.info("Entrando Ver Cierres AS400");
			HttpSession sesion= request.getSession();
			
			//rescatando parámetros request
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String periodo= (String)request.getParameter("periodo");
			String[] cierres= request.getParameterValues("opcionCierre");
			String emails= paramTO.getEmailUsuario();
			logger.info("Ver Cierres AS400, Periodo= " + periodo);
			if (cierres==null){
								
//				Ver Cierres
				ConcatenarCierres concatenar= new ConcatenarCierres();
				cierresPrevipass= concatenar.getCierresPrevipass(periodo);
				//cierresDomino= concatenar.getCierresDomino(periodo);
				//request.setAttribute("cierresDomino", cierresDomino);
				request.setAttribute("cierresPrevipass", cierresPrevipass);
				request.setAttribute("periodo", periodo);
				forward = mapping.findForward("VER");
			}else{
//				Invocando una hebra para Concatenar Archivos
				if(!paramTO.getProcesosActivos().containsKey("CONCATENAR:" + periodo)){
					ConcatenarCierresThreads concatenarThreads= new ConcatenarCierresThreads(Integer.parseInt(periodo), cierres, emails);
					paramTO.addProceso("CONCATENAR:" + periodo, Today.getFecha_Hora());
					paramTO.addThread("CONCATENAR:" + periodo, concatenarThreads);
					concatenarThreads.start();
					forward = mapping.findForward("OK");
				}else{
					forward = mapping.findForward("BUSY");
				}
				request.setAttribute("accion", "CONCATENAR CIERRES");
			}
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
			forward = mapping.findForward("NOTOK");
		}
		logger.info("Fin VerCierresAction, forward= "+ forward);
		
		return forward;
	}
}
