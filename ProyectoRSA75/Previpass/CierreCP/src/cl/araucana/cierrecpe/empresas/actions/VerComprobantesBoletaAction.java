/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.actions;

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

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.empresas.business.Boletas;
import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerComprobantesBoletaAction extends Action {
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
		Boletas boleta=null;
		try{
			HttpSession sesion= request.getSession();
		
			logger.finer("Entrando Action VerComprobantesBoletaAction");
			
			String rutempresa= request.getParameter("rutEmpresa");
			String montoBoleta= request.getParameter("montoboleta");
			String idBoleta= request.getParameter("idboleta");
			rutempresa= rutempresa.substring(0, rutempresa.indexOf('-'));
			boleta= new Boletas();

			Collection collempresas= boleta.buscarComprobantes(Integer.parseInt(rutempresa));
			//se guardan folios de ingreso asociados en sesion
			request.setAttribute("comprobantes", collempresas);
			request.setAttribute("boleta", montoBoleta);
			request.setAttribute("idboleta", idBoleta);
			forward = mapping.findForward("OK");
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
			if(boleta!=null){
				boleta.close();
			}
		}
		return forward;
	}
}
