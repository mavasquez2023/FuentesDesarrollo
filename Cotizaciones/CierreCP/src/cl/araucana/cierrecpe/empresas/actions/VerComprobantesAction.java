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
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerComprobantesAction extends Action {
	private static Logger logger = LogManager.getLogger();
	private CPDAO cpDAO=null;
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		try{
			HttpSession sesion= request.getSession();
			String cierre= request.getParameter("cierreDetalle");
			String periodo= request.getParameter("periodo");
			String tipoNomina= request.getParameter("tipoNomina");
			int periodoSistema= Parametros.getInstance().getParam().getPeriodoSistema();
			String recurso= request.getParameter("recurso");
			logger.info("Entrando VerComprobantesAction, cierre: " + cierre + ", recurso:" + recurso);
			request.setAttribute("cierreDetalle", cierre);
			request.setAttribute("periodo", periodo);
			request.setAttribute("periodoSistema", String.valueOf(periodoSistema));
			request.setAttribute("tipoNomina", tipoNomina);
			cpDAO= new CPDAO();
			ResumenCierreCPDAO resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
			Collection detalle_comprobantes=null;
			if(recurso.equals("PLANILLAS")){
				detalle_comprobantes= resumenDAO.selectComprobantesSinPlanillas(Integer.parseInt(periodo), Integer.parseInt(cierre));
			}else if(recurso.equals("TGR")){
				detalle_comprobantes= resumenDAO.selectComprobantesSinTGR(Integer.parseInt(periodo), Integer.parseInt(cierre));
			}else if(recurso.equals("COMPROBANTES")){
					detalle_comprobantes= resumenDAO.selectComprobantesResumen(Integer.parseInt(periodo), Integer.parseInt(cierre), tipoNomina);
			}
			cpDAO.closeConnectionDAO();
			//System.out.println("Numero de comprobantes cierre " + cierre + " =" + sinplanillas.size());
			sesion.setAttribute("detalle", detalle_comprobantes);
			forward = mapping.findForward("OK");
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		finally{
			if (cpDAO!= null){
				cpDAO.closeConnectionDAO();
			}
		}
		return forward;
	}
}
