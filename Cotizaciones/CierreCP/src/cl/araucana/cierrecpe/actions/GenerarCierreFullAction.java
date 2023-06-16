/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.actions;

import java.util.ArrayList;
import java.util.Arrays;
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
import cl.araucana.cierrecpe.entidades.to.ArchivoEntidadTO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.cierrecpe.threads.ControllerThreads;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarCierreFullAction extends Action{
	private static Logger logger = LogManager.getLogger();
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception 
	{	
		String cierre=null;
		String periodo=null;
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		try{
			System.out.println("Ejecutando Cierre Full, cierre:" + cierre);
			forward = mapping.findForward("NOTOK");
			HttpSession sesion= request.getSession();
			periodo= request.getParameter("periodo");
			cierre= request.getParameter("cierre");
			String formapago= request.getParameter("formapago");
			logger.finer("Forma de Pago= " + formapago);
			String fechaDeposito= (String)request.getParameter("fechaDeposito");
			logger.finer("Fecha Depósito= " + fechaDeposito);
			String fechaPago= (String)request.getParameter("fechaPago");
			logger.finer("Fecha Pago= " + fechaPago);
			String optionCentralizacion= (String)request.getParameter("optioncentral");
			logger.finer("Opcion Centralizacion= " + optionCentralizacion);
			String optionTE= (String)request.getParameter("cleanTE");
			logger.finer("Opcion Centralizacion= " + optionCentralizacion);
			String[] tipoSeccion= request.getParameterValues("tipoSeccion");
			
			logger.finer("GenerarCheques cierre: " + cierre);
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String emails= paramTO.getEmailUsuario();
			List secciones= new ArrayList();
			for (int i = 0; i < tipoSeccion.length; i++) {
				ArchivoEntidadTO archivoTO= new ArchivoEntidadTO();
				archivoTO.setTipoSeccion(tipoSeccion[i]);
				if(tipoSeccion[i].equals("CAJA")){
					List CCAFs= Arrays.asList(request.getParameterValues("entidadCAJA"));
					archivoTO.setTipoDetalle(CCAFs);
				}
				if(tipoSeccion[i].equals("APV")){
					List APVs= Arrays.asList(request.getParameterValues("entidadAPV"));
					archivoTO.setTipoDetalle(APVs);
				}
				secciones.add(archivoTO);
			}
			paramTO.delProceso("CIERRE:" + cierre);
			//Se invoca Threads Contrladora de los subprocesos
			if(!paramTO.getProcesosActivos().containsKey("CIERRE" + cierre)){
				ControllerThreads controllerTheads= new ControllerThreads(Integer.parseInt(periodo), Integer.parseInt(cierre), formapago, fechaDeposito, fechaPago, optionCentralizacion, optionTE, secciones, emails);
				paramTO.addProceso("CIERRE:" + cierre, Today.getFecha_Hora());
				controllerTheads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}
		}catch (Exception e) {
			logger.severe("Error en proceso Cierre Full: " + cierre + ", mensaje= " + e.getMessage());
			System.out.println("GenerarCierreFull, error en proceso Cierre: " + cierre);
			e.printStackTrace();
		}
		finally{
			request.setAttribute("accion", "Cierre completo, cierre " + cierre);
		}
		return forward;
	}
}
