/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package cl.araucana.cierrecpe.entidades.actions;

import java.util.Collection;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class VerSeccionesAction extends Action {
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
		PropuestaPagoEntidades propuesta=null;
		try{
			HttpSession sesion= request.getSession();

			//se rescatan los par�metros periodo y cierre
			String periodo= (String)request.getParameter("periodo");
			
			//se guardan los par�metros en el request
			request.setAttribute("periodo", periodo);

			propuesta= new PropuestaPagoEntidades(true);
			Collection secciones= propuesta.verSeccionesPeriodo(Integer.parseInt(periodo));
			
			sesion.setAttribute("secciones", secciones);
			forward = mapping.findForward("OK");
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
//			Cerrando conexi�n BD
			if(propuesta!=null){
				propuesta.close();
			}
		}
		return forward;
	}
}
