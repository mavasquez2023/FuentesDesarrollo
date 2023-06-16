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
public class GenerarBoletaAction extends Action {
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
			logger.finer("Entrando Action Generar Boleta");
			HttpSession sesion= request.getSession();
			String rutempresa= request.getParameter("rutempresa");
			boleta= new Boletas();
			if(rutempresa==null){
				//Invocando verBancos");
				Collection collbancos= boleta.verBancos();
				request.setAttribute("bancos", collbancos);
				forward = mapping.findForward("PARAM");
			}else{
				ParametrosCPTO paramTO= Parametros.getInstance().getParam();
				//int periodo= paramTO.getPeriodoSistema();
				String periodo= String.valueOf(paramTO.getPeriodoSistema());
				rutempresa= rutempresa.substring(0, rutempresa.indexOf('-'));
				String idBanco= request.getParameter("banco");
				String idCuenta= request.getParameter("cuenta");
				String monto= request.getParameter("monto");
				String estado= "0";

				BoletaTO boletaTO= new BoletaTO();
				boletaTO.setPeriodo(Integer.parseInt(periodo));
				boletaTO.setRutEmpresa(new Rut(Integer.parseInt(rutempresa)));			
				boletaTO.setIdBanco(Integer.parseInt(idBanco));
				boletaTO.setIdCuenta(idCuenta);
				boletaTO.setMonto(Long.parseLong(monto));
				boletaTO.setEstado(estado);

				int resultado= boleta.guardarBoleta(boletaTO);

				if(resultado==1){
					forward = mapping.findForward("OK");
				}else{
					forward = mapping.findForward("NOTOK");
				}
			}
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
