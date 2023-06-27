/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;

import java.util.Collection;
import java.util.Iterator;
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

import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.cierrecpe.entidades.to.FormasPagoTO;
import cl.araucana.core.util.logging.LogManager;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerComprobantesFormaPagoAction extends Action {
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
		List comprobantesCP=null;
		try{
			HttpSession sesion= request.getSession();
			String reqformapago= request.getParameter("formapago");
			logger.finer("Entrando Action VerComprobantesFormapagoAction, forma pago: " + reqformapago);
			Collection formas= (List) sesion.getAttribute("listFormasPago");
			for (Iterator iter = formas.iterator(); iter.hasNext();) {
				FormasPagoTO formaTO = (FormasPagoTO) iter.next();
				if(formaTO.getFormaPago()==Integer.parseInt(reqformapago)){
					comprobantesCP= (List) formaTO.getComprobantesCP();
				}
			}
			//se guardan folios de ingreso asociados en sesion
			request.setAttribute("formapago", reqformapago);
			request.setAttribute("comprobantes", comprobantesCP);
			forward = mapping.findForward("OK");
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		return forward;
	}
}
