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

import cl.araucana.cierrecpe.business.Parametros;
import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerPropuestaAction extends Action {
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
		PropuestaPagoEntidades propuesta=null;
		try{
			HttpSession sesion= request.getSession();
			//rescatando parametros de la instancia Singleton a nivel de ear.
			ParametrosCPTO paramTO= Parametros.getInstance().getParam();
			int periodoSistema= paramTO.getPeriodoSistema();
			
			//se rescatan los parámetros periodo y cierre
			String periodo= (String)request.getParameter("periodo");
			String cambiaPeriodo= (String)request.getParameter("cambiaPeriodo");
			String cierre= (String)request.getParameter("cierre");
			String order= (String)request.getParameter("order");
			if(order== null){
				order="SECCION";
			}
			propuesta= new PropuestaPagoEntidades(true);
			
			//se genera lista de c/u y se muestra último periodo y cierre disponible
			if (periodo==null){
				List periodos= (List)propuesta.getListPeriodos();
				sesion.setAttribute("listPeriodos", periodos);
				Iterator listper= periodos.iterator();
				if(listper.hasNext()) {
					Integer perInt= (Integer)listper.next();
					periodo= String.valueOf(perInt.intValue());
				}else{
					periodo= String.valueOf(paramTO.getPeriodoSistema());
				}
			}
			if (cambiaPeriodo==null){
				cambiaPeriodo="";
			}
			if (cierre==null || cambiaPeriodo.equals("1")){
				cierre= "0";
				List cierres= (List)propuesta.getListCierres(Integer.parseInt(periodo));
				sesion.setAttribute("listCierres", cierres);
				if(Integer.parseInt(periodo) == paramTO.getPeriodoSistema()){
					Iterator listcierres= cierres.iterator();
					if(listcierres.hasNext()) {
						Integer cierreInt= (Integer)listcierres.next();
						cierre= String.valueOf(cierreInt.intValue());
					}
				}
			}
			//se guardan los parámetros en el request
			request.setAttribute("periodo", periodo);
			request.setAttribute("cierre", cierre);
			request.setAttribute("periodoSistema", String.valueOf(periodoSistema));
			request.setAttribute("conPlanillas", "true");
			request.setAttribute("fechaDeposito", new AbsoluteDate());
			request.setAttribute("fechaPago", new AbsoluteDate());
			//request.setAttribute("comparaFecha", new Integer(paramTO.getCompareFechaCierre_Today()));
			
			//Solicitando verCheques, periodo y cierre especificados
			Collection colcheques= propuesta.verPropuestaPago(Integer.parseInt(periodo), Integer.parseInt(cierre), order);
			
			//Se verifica si para el periodo y cierre ya se ha generado la publicación en tablas PWF, sino, 
			//se inhabilita botón de Generar Archivo Entidades
			if(!propuesta.isConPlanillas(Integer.parseInt(periodo), Integer.parseInt(cierre))){
				//System.out.println("******seteando atributo conPlanillas");
				request.setAttribute("conPlanillas", "false");
			}
			sesion.setAttribute("cheques", colcheques);
			if(order.equals("DEPOSITO")){
				forward = mapping.findForward("PARAMDEPO");
			}else{
				forward = mapping.findForward("PARAM");
			}
			
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
//			Cerrando conexión BD
			if(propuesta!=null){
				propuesta.close();
			}
		}
		return forward;
	}
}
