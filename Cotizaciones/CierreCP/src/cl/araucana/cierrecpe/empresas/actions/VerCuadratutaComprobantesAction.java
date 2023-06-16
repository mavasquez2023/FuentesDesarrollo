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
import cl.araucana.cierrecpe.empresas.dao.ComprobantesCPDAO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class VerCuadratutaComprobantesAction extends Action {
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
			String claveSeccion= request.getParameter("seccion");
			int periodoSistema= Parametros.getInstance().getParam().getPeriodoSistema();
			logger.info("Entrando VerCuadraturaComprobantesAction, cierre: " + cierre + ", tipo: "  + claveSeccion);
			request.setAttribute("cierreDetalle", cierre);
			request.setAttribute("periodo", periodo);
			request.setAttribute("periodoSistema", String.valueOf(periodoSistema));
			request.setAttribute("clave", claveSeccion);
			cpDAO= new CPDAO();
			ComprobantesCPDAO cuadraCPDAO= new ComprobantesCPDAO(cpDAO.getConnection());
			Collection cuadraturaComprobante=null;
			cuadraturaComprobante= cuadraCPDAO.selectCuadraturaxEntidad(cierre, claveSeccion);
			//System.out.println("Numero de comprobantes cierre " + cierre + " =" + sinplanillas.size());
			request.setAttribute("cuadratura"  , cuadraturaComprobante);
			sesion.setAttribute("cuadratura"+ claveSeccion  , cuadraturaComprobante);
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
