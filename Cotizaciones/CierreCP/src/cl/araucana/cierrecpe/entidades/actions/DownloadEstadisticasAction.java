/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package cl.araucana.cierrecpe.entidades.actions;


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

import cl.araucana.cierrecpe.entidades.business.PropuestaPagoEntidades;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.GeneratorXLS;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
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
		PropuestaPagoEntidades propuesta=null;
		try{
			logger.info("Entrando a Download EstadisticasAction");
			HttpSession sesion= request.getSession();
			propuesta= new PropuestaPagoEntidades(true);
			List estadisticas= (List)propuesta.generarEstadisticasPago();
			if (estadisticas!= null){
				String filename= (String)request.getParameter("filename");
				if (filename== null || filename.equals("")){
					filename= "download_estadisticas_entidades.xls";  
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
				String[] columnas={"periodo", "cantidadPagosCheque", "cantidadPagosSPL", "totalCheque", "totalSPL"};
				String[] titulos={"", "N� Cheques", "N� Transferencias", "Total Pagos Cheque($)", "Total Pagos Transferencia($)"};
				xls.generarXLSfromCollection(estadisticas, columnas, titulos, "000077");
				
				forward = null;
			}else{
				logger.warning("Se solicita descargar excel, sin embargo, lista estadisticas es null");
				forward = mapping.findForward("NOTOK");
			}
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			System.out.println("DownloadEstadisticasAction, error en la descarga del archivo excel");
			e.printStackTrace();
		}
		finally{
//			Cerrando conexi�n BD
			if(propuesta!=null){
				propuesta.close();
			}
		}
		return forward;
	}
}
