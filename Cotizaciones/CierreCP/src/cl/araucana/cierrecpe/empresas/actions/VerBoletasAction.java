/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
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
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class VerBoletasAction extends Action {
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
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			//int periodo= paramTO.getPeriodoSistema();
			String periodo= String.valueOf(paramTO.getPeriodoSistema());
			logger.finer("Entrando Action VerBoletas, periodo: " + periodo);
			
			request.setAttribute("periodo", periodo);

			boleta= new Boletas();
			//Invocando verDetalleCheques");
			Collection collboletas= boleta.verBoletas(Integer.parseInt(periodo));
			//se guardan folios de ingreso asociados en sesion
			request.setAttribute("boletas", collboletas);
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
