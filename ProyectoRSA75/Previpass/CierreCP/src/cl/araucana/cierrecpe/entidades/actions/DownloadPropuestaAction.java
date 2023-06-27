/*
 * Creado el 09-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
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
import cl.recursos.GeneratorXLS;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DownloadPropuestaAction extends Action {
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
			logger.info("Entrando DownloadPropuestaAction");
			HttpSession sesion= request.getSession();
			List propuesta= (List)sesion.getAttribute("cheques");
			if (propuesta!= null){
				String filename= (String)request.getParameter("filename");
				if (filename== null || filename.equals("")){
					filename= "download_excel.xls"; 
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
				String[] columnas={"cierre", "descripcionSeccion", "razonSocial", "rut", "tipoDetalle", "montoTotal", "tipoNomina", "folioEgreso", "conceptoTesoreria", "deposito", "codigoBanco", "cuentaCorriente"};
				String[] titulos={"Cierre", "Sección", "Entidad", "Rut", "Código Entidad", "Monto", "Nómina", "Folio Egreso", "Concepto Egreso", "Tipo Depósito", "Código Banco", "Cuenta Corriente"};
				xls.generarXLSfromCollection(propuesta, columnas, titulos, "006777");
				
				forward = null;
			}else{
				logger.warning("Se solicita descargar excel, sin embargo, Propuesta en sesion es null");
				forward = mapping.findForward("NOTOK");
			}
			
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			e.printStackTrace();
		}
		return forward;
	}
}
