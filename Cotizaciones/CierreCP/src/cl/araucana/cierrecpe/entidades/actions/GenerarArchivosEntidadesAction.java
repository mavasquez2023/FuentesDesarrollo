/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;

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
import cl.araucana.cierrecpe.entidades.threads.GenerarArchivosEntidadesThreads;
import cl.araucana.cierrecpe.entidades.to.ArchivoEntidadTO;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarArchivosEntidadesAction extends Action {
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
			logger.info("Entrando Action Generar Archivo Entidades");
			HttpSession sesion= request.getSession();
			
			//rescatando parámetros request
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String periodo= (String)request.getParameter("periodo");
			cierre= (String)request.getParameter("cierre");
			String formapago= (String)request.getParameter("formapago");
			String[] tipoSeccion= request.getParameterValues("tipoSeccion");
			String emails= paramTO.getEmailUsuario();
			String fechaDeposito= (String)request.getParameter("fechaDeposito");
			fechaDeposito= fechaDeposito.replaceAll("/", "");
			fechaDeposito= fechaDeposito.replaceAll("-", "");
			logger.info("Generar Archivo Entidades, Periodo= " + periodo + ", cierre=" + cierre + ", Fecha Depósito:" + fechaDeposito);
			if (cierre!=null && periodo!=null){
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
				
//				Invocando una hebra para Generar Archivos
				if(!paramTO.getProcesosActivos().containsKey("ARCHIVO:" + cierre)){
					GenerarArchivosEntidadesThreads archivoThreads= new GenerarArchivosEntidadesThreads(Integer.parseInt(periodo), Integer.parseInt(cierre), formapago, fechaDeposito, secciones, emails);
					paramTO.addProceso("ARCHIVO:" + cierre, Today.getFecha_Hora());
					paramTO.addThread("ARCHIVO:" + cierre, archivoThreads);
					archivoThreads.start();
					forward = mapping.findForward("OK");
				}else{
					forward = mapping.findForward("BUSY");
				}
			}
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Fin GenerarArchivosEntidadesAction, forward= "+ forward);
		request.setAttribute("accion", "Archivo Entidades, cierre " + cierre);
		request.setAttribute("origen", "ENT");
		
		return forward;
	}
}
