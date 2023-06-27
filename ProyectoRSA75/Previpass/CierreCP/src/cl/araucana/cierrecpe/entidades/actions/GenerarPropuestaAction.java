/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
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
import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.cierrecpe.entidades.threads.GenerarPropuestaThreads;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.Today;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GenerarPropuestaAction extends Action {
	private static Logger logger = LogManager.getLogger();
	protected Properties properties;
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
		PropuestaPagoEntidades propuesta=null;
		String cierre="", cierreIndependiente="", cierre_manual="", cierreind_manual="";
		int primerCierre;
		try{
			forward = mapping.findForward("NOTOK");
			HttpSession sesion= request.getSession();
			loadProperties("/etc/pwf.properties");
			primerCierre= Integer.parseInt(properties.getProperty("PrimerCierre"));
			cierre= request.getParameter("cierre_manual");
			if(cierre==null || cierre.equals("")){
				cierre= request.getParameter("cierre");
			}
			cierreIndependiente= request.getParameter("cierreind_manual");
			if(cierreIndependiente==null || cierreIndependiente.equals("")){
				cierreIndependiente= request.getParameter("cierreind");
			}
			
			String opcion= request.getParameter("optioncierre");
			logger.finer("Opción de cierre= " + opcion);
			String formapago= request.getParameter("formapago");
			logger.finer("Forma de Pago= " + formapago);
			String deposito= request.getParameter("deposito");
			logger.finer("Deposito= " + deposito);
			String tipoEmpresa= request.getParameter("tipoEmpresa");
			logger.finer("Tipo Empresa= " + tipoEmpresa);
			System.out.println("GenerarCheques cierre: " + cierre);
			logger.info("GenerarCheques cierre: " + cierre);
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			int periodo= paramTO.getPeriodoSistema();
			int periodoIndependiente= paramTO.getPeriodoSistemaIndependiente();
			String emails= paramTO.getEmailUsuario();
			//Se obtiene el prefijo de código Barra
			String prefijoCodigoBarra= paramTO.getCodigoBarra();
			if (cierre== null){
				propuesta= new PropuestaPagoEntidades(true);
				Integer cierrePendiente= propuesta.getCierrePendiente(periodo);
				int max= propuesta.getMaxCierre(periodo);
				if(max==0){
					max= primerCierre;
				}
				int maxIndep= propuesta.getMaxCierre(periodoIndependiente);
				if(maxIndep==0){
					maxIndep= primerCierre;
				}
				List cierres= (List)propuesta.getListCierres(periodo);
				List cierresIndependiente= (List)propuesta.getListCierres(periodoIndependiente);
				//Se rescata Los comprobantes asociados a Forma de Pago que incluye Cuadraturas contra Tesorería
				List formas= (List) propuesta.getListFormasPago(prefijoCodigoBarra);
				sesion.setAttribute("nextCierre", new Integer(max));
				sesion.setAttribute("nextCierreIndependiente", new Integer(maxIndep));
				String listcierres=",";
				for (Iterator iter = cierres.iterator(); iter.hasNext();) {
					Integer element = (Integer) iter.next();
					listcierres+=element + ",";
				}
				String listcierresIndep=",";
				for (Iterator iter = cierresIndependiente.iterator(); iter.hasNext();) {
					Integer element = (Integer) iter.next();
					listcierresIndep+=element + ",";
				}
				sesion.setAttribute("listCierre", listcierres);
				sesion.setAttribute("listCierreIndep", listcierresIndep);
				sesion.setAttribute("cierrePendiente", cierrePendiente);
				sesion.setAttribute("listFormasPago", formas);
				forward = mapping.findForward("PARAM");				
				
				
			}else{
				if(!paramTO.getProcesosActivos().containsKey("PROPUESTA:" + cierre)){
					if(formapago.indexOf('3')>-1 || formapago.indexOf('4')>-1 ){
						cierre= cierreIndependiente;
						periodo= periodoIndependiente;
					}
					GenerarPropuestaThreads generarTheads= new GenerarPropuestaThreads(periodo, Integer.parseInt(cierre), formapago, deposito, tipoEmpresa, opcion, emails);
					paramTO.addProceso("PROPUESTA:" + cierre, Today.getFecha_Hora());
					paramTO.addThread("PROPUESTA:" + cierre, generarTheads);
					generarTheads.start();
					forward = mapping.findForward("OK");
				}else{
					forward = mapping.findForward("BUSY");
				}
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
		}
		finally{
			//Cerrando conexión BD
			if(propuesta!=null){
				propuesta.close();
			}
		}
		request.setAttribute("accion", "Propuesta Pago, cierre " + cierre);
		request.setAttribute("origen", "ENT");
		return forward;
	}
	private void loadProperties(String fileproperties){
		PropertiesLoader propertiesloader = new PropertiesLoader();
		try
		{
			properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.GenerarPlanillas.class);
		}
		catch(Exception eproperties)
		{
			logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
			eproperties.printStackTrace();
		}
	}
}
