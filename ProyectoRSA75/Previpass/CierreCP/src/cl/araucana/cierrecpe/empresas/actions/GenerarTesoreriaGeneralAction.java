/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.actions;

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
import cl.araucana.cierrecpe.empresas.threads.GenerarTesoreriaGeneralThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 * 
 * * Clase CADUCADA por REQ-7435 Nuevo Formato Servicio TGR que apunta directo a tablas de Centralización
 */
public class GenerarTesoreriaGeneralAction extends Action {
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
		try{
			forward = mapping.findForward("NOTOK");
			logger.info("Entrando Action Generar Tesorería General de la Republica");
			HttpSession sesion= request.getSession();
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			int periodo= paramTO.getPeriodoSistema();
			cierre= (String)request.getParameter("cierre");
			String emails= paramTO.getEmailUsuario();
			
//			Invocando una hebra para Generar Cheques
			if(!paramTO.getProcesosActivos().containsKey("TGR:" + cierre)){
				GenerarTesoreriaGeneralThreads tgrThreads= new GenerarTesoreriaGeneralThreads(periodo, Integer.parseInt(cierre), emails);
				paramTO.addProceso("TGR:" + cierre, Today.getFecha_Hora());
				paramTO.addThread("TGR:" + cierre, tgrThreads);
				tgrThreads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Fin GenerarTesoreriaGeneralAction, forward= "+ forward);
		request.setAttribute("accion", "Tesoreria General, cierre " + cierre);
		request.setAttribute("origen", "EMP");
		return forward;
	}
}
