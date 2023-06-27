/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.actions;


import java.util.ArrayList;
import java.util.Collection;
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
import cl.araucana.cierrecpe.empresas.business.Boletas;
import cl.araucana.cierrecpe.empresas.to.BoletaTO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.Rut;
import cl.araucana.core.util.logging.LogManager;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CursarComprobantesBoletaAction extends Action {
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
			logger.finer("Entrando Action Cursar Comprobantes Boleta");
			HttpSession sesion= request.getSession();
			String[] ids= request.getParameterValues("idcpago");
			String idBoleta= request.getParameter("idBoleta");
			boleta= new Boletas();
			request.setAttribute("error", new Integer(0));
			
			BoletaTO boletaTO= (BoletaTO)boleta.getBoleta(Integer.parseInt(idBoleta));
			boletaTO.setFolios(ids);
			
			int resultado= boleta.cursarComprobantesBoleta(boletaTO);
			if(resultado>0){
				boleta.estadoBoleta(Integer.parseInt(idBoleta), "1");
				boleta.guardarLibroBanco(boletaTO);
				boleta.estadoBoleta(Integer.parseInt(idBoleta), "2");
			}else{
				request.setAttribute("error", new Integer(boletaTO.getIdBoleta()));
				request.setAttribute("mensajes", boleta.getNo_cursados());
			}
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
