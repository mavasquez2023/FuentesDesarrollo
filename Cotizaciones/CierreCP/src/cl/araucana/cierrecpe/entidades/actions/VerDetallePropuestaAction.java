/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
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
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerDetallePropuestaAction extends Action {
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
			String folio= request.getParameter("folio");
			logger.finer("Entrando Action VerDetallePropuesta, folio: " + folio);
			
			request.setAttribute("folioEgreso", folio);
			//Generando nueva instacia de ChequeEntidad");
			propuesta= new PropuestaPagoEntidades(true);
			//Invocando verDetalleCheques");
			Collection folios= propuesta.verDetallePropuesta(Integer.parseInt(folio));
			
			//se guardan folios de ingreso asociados en sesion
			sesion.setAttribute("folios", folios);
			forward = mapping.findForward("OK");
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
//			Cerrando conexión BD
			if(propuesta!=null){
				propuesta.close();
			}
		}
		return forward;
	}
}
