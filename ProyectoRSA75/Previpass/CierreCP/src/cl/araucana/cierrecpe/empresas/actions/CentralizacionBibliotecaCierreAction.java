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
import cl.araucana.cierrecpe.empresas.threads.CentralizacionBibliotecaCierreThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;
/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CentralizacionBibliotecaCierreAction extends Action {
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
			System.out.println("Entrando Action Centralización Biblioteca Cierre");
			HttpSession sesion= request.getSession();
			
			//rescatando parámetros request
			String periodo= (String)request.getParameter("periodo");
			cierre= (String)request.getParameter("cierre");
			String rutemp= (String)request.getParameter("rutEmpresa");
			int rutEmpresa= Integer.parseInt(rutemp);
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			String emails= paramTO.getEmailUsuario();
			
			logger.info("Generar Centralización Biblioteca Cierre, Periodo= " + periodo + ", cierre=" + cierre);
			//Invocando una hebra para Generar Cheques
			if(!paramTO.getProcesosActivos().containsKey("CENTRALIZAR:" + cierre)){
				CentralizacionBibliotecaCierreThreads centralizaThreads= new CentralizacionBibliotecaCierreThreads(Integer.parseInt(periodo), Integer.parseInt(cierre), rutEmpresa, emails);
				paramTO.addProceso("CENTRALIZAR:" + cierre, Today.getFecha_Hora());
				paramTO.addThread("CENTRALIZAR:" + cierre, centralizaThreads);
				centralizaThreads.start();
				forward = mapping.findForward("OK");
			}else{
				forward = mapping.findForward("BUSY");
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Fin Centralización Biblioteca Cierre, forward= "+ forward);
		request.setAttribute("accion", "Centralización, cierre " + cierre);
		return forward;
	}
}
