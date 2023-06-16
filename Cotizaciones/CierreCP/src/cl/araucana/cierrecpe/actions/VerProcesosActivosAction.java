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
public class VerProcesosActivosAction extends Action{
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
			
			logger.finer("Procesos Activos de Cierre...");
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			int periodo= paramTO.getPeriodoSistema();
			request.setAttribute("periodo", String.valueOf(periodo));
			
			//Se elimina proceso en Mapa si viene parámetro en request.
			String eliminarproceso= (String)request.getParameter("clave");
	
			if (eliminarproceso!= null){
				paramTO.delProceso(eliminarproceso);
				paramTO.killThread(eliminarproceso);
			}
			
			//Se buscan todos los procesos en ejecución
			Map procesos= paramTO.getProcesosActivos();
			Collection claves= procesos.keySet();
			if(claves.isEmpty()){
				request.setAttribute("sinprocesos", "No hay Procesos en ejecución.");
			}else{
				for (Iterator iter = claves.iterator(); iter.hasNext();) {
					String clave = (String) iter.next();
					logger.info("Proceso:" + clave);
					int pos= clave.indexOf(":");
					String proceso, cierre;
					if(pos>-1){
						proceso= clave.substring(0, pos);
						cierre= clave.substring(pos+1);
					}else{
						proceso= clave.substring(0, clave.length()-1);
						cierre= clave.substring(clave.length()-1);
					}
					ProcesosActivosTO activosTO= new ProcesosActivosTO();
					activosTO.setClave(proceso);
					activosTO.setCierre(Integer.parseInt(cierre));
					activosTO.setFechahora(procesos.get(clave).toString());
					listaproc.add(activosTO);
				}
			}
			forward = mapping.findForward("OK");
		}catch (Exception e) {
			logger.severe("Error en Ver Procesos Activos: mensaje= " + e.getMessage());
			System.out.println("Error en Ver Procesos Activos: mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			request.setAttribute("accion", "Ver Procesos Activos");
			request.setAttribute("activos", listaproc);
		}
		return forward;
	}
}
