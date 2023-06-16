/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.business.LogCierre;
import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.cierrecpe.to.ProcesosActivosTO;
import cl.araucana.cierrecpe.threads.ControllerThreads;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerLogProcesosAction extends Action{
	private static Logger logger = LogManager.getLogger();
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		List listaproc= new ArrayList();
		try{
			forward = mapping.findForward("NOTOK");
			HttpSession sesion= request.getSession();
			
			logger.finer("ver Log Procesos Ejecutados de Cierre...");
			//rescatando parametros de la instancia Singleton a nivel de ear.
			LogCierre log= LogCierre.getInstance();
			listaproc= (List)log.verLog();

			forward = mapping.findForward("OK");
		}catch (Exception e) {
			logger.severe("Error en Ver Procesos Ejecutados: mensaje= " + e.getMessage());
			System.out.println("Error en Ver Procesos Ejecutados: mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			request.setAttribute("accion", "Ver Procesos Ejecutados");
			request.setAttribute("logprocesos", listaproc);
		}
		return forward;
	}
}
