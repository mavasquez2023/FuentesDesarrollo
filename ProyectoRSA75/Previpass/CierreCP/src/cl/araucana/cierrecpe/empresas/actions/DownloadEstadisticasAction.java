/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.actions;


import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cierrecpe.empresas.business.GenerarPlanillas;
import cl.araucana.cierrecpe.empresas.business.ResumenCierreCP;
import cl.araucana.cierrecpe.empresas.dao.ComprobantesCPDAO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.GeneratorXLS;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DownloadEstadisticasAction extends Action {
	private static Logger logger = LogManager.getLogger();
	final int cierre=0;
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionForward forward = new ActionForward();
		try{
			logger.fine("Entrando a Download EstadisticasAction");
			HttpSession sesion= request.getSession();
			ResumenCierreCP generar= new ResumenCierreCP();
			List estadisticas= (List)generar.generarEstadisticasComprobantes();
			if (estadisticas!= null){
				String filename= (String)request.getParameter("filename");
				if (filename== null || filename.equals("")){
					filename= "download_estadisticas_comprobantes.xls";  
				}
				logger.finer("Se solicita descargar excel: " + filename);
				
				//Despliegue de excel
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=" + filename);
				//response.setContentLength();
				
				//Generando la salida
				ServletOutputStream out = response.getOutputStream();
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);
				
				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"periodo", "cantidadComprobantesMixto", "cantidadComprobantesSPL", "numeroTrabajadoresSPL", "numeroTrabajadoresMixto", "totalPagoMixto", "totalPagoSPL"};
				String[] titulos={"", "N° Comprobantes Mixto", "N° Comprobantes SPL", "N° Trabjadores Mixto", "N° Trabjadores SPL", "Total Pago Mixto($)", "Total Pago SPL($)"};
				xls.generarXLSfromCollection(estadisticas, columnas, titulos, "000077");
				
				forward = null;
			}else{
				logger.warning("Se solicita descargar excel, sin embargo, lista estadisticas es null");
				forward = mapping.findForward("NOTOK");
			}
			generar.close();
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			System.out.println("DownloadEstadisticasAction, error en la descarga del archivo excel");
			e.printStackTrace();
		}
		return forward;
	}
}
