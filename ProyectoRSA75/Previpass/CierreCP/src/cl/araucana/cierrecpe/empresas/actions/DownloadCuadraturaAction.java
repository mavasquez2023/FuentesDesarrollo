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
public class DownloadCuadraturaAction extends Action {
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
			HttpSession sesion= request.getSession();
			String clave= request.getParameter("seccion");
			String filename= (String)request.getParameter("filename");
			List cuadraturas= (List)sesion.getAttribute("cuadratura" + clave);
			if (cuadraturas!= null){
				if (filename== null || filename.equals("")){
					filename= "download_cuadraturas_comprobantes.xls";  
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
				
				//Configurando columnas a desplegar y titulos de estas por cada tipo entidad
				//String[] columnas={"nombreEntidad", "rutEmpresa", "tipoNomina", "m2", "m3", "m12", "m6", "m7", "m9", "m11"};
				//String[] titulos= {"Nombre Entidad", "Rut Empresa", "Tipo Nomina", "Obligatorio($)", "Ahorro($)", "SIS($)", "AFC Tra($)", "AFC Emp($)", "TP($)", "Total"};
				String[] columnas=null;
				String[] titulos= null;
				if(clave.equals("AFP")){
					columnas= new String[11];
					titulos= new String[11];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m2";
					columnas[5]= "m3";
					columnas[6]= "m12";
					columnas[7]= "m6";
					columnas[8]= "m7";
					columnas[9]= "m9";
					columnas[10]= "m11";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="Obligatorio($)";
					titulos[5]="Ahorro($)";
					titulos[6]="SIS($)";
					titulos[7]="AFC Tra($)";
					titulos[8]="AFC Emp($)";
					titulos[9]="TP($)";
					titulos[10]="Total($)";
				}else if(clave.equals("ISAPRE")){
					columnas= new String[7];
					titulos= new String[7];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m2";
					columnas[5]= "m3";
					columnas[6]= "m4";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="Obligatorio($)";
					titulos[5]="Adicional($)";
					titulos[6]="Total($)";
				}else if(clave.equals("INP")){
					columnas= new String[13];
					titulos= new String[13];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m2";
					columnas[5]= "m3";
					columnas[6]= "m4";
					columnas[7]= "m5";
					columnas[8]= "m6";
					columnas[9]= "m7";
					columnas[10]= "m8";
					columnas[11]= "m9";
					columnas[12]= "m10";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="Pension($)";
					titulos[5]="Fonasa($)";
					titulos[6]="Accidente($)";
					titulos[7]="Desahucio($)";
					titulos[8]="Total Pagos($)";
					titulos[9]="Asfam($)";
					titulos[10]="Ley 15.386($)";
					titulos[11]="Total Rebajas";
					titulos[12]="Total";
				}else if(clave.equals("MUTUAL")){
					columnas= new String[7];
					titulos= new String[7];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m1";
					columnas[5]= "m2";
					columnas[6]= "m3";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="Tasa(%)";
					titulos[5]="Imponible($)";
					titulos[6]="Total($)";
				}else if(clave.equals("CCAF")){
					columnas= new String[12];
					titulos= new String[12];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m1";
					columnas[5]= "m2";
					columnas[6]= "m4";
					columnas[7]= "m5";
					columnas[8]= "m6";
					columnas[9]= "m7";
					columnas[10]= "m3";
					columnas[11]= "m8";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="Aporte 0,6($)";
					titulos[5]="Asfam($)";
					titulos[6]="Credito($)";
					titulos[7]="Leasing($)";
					titulos[8]="S.Vida($)";
					titulos[9]="C.Dental($)";
					titulos[10]="Compensado($)";
					titulos[11]="Total($)";
				}else if(clave.equals("APV")){
					columnas= new String[7];
					titulos= new String[7];
					columnas[0]= "nombreEntidad";
					columnas[1]= "rutEmpresa";
					columnas[2]= "convenio";
					columnas[3]= "tipoNomina";
					columnas[4]= "m3";
					columnas[5]= "m1";
					columnas[6]= "m2";
					titulos[0]="Nombre Entidad";
					titulos[1]="Rut Empresa";
					titulos[2]="Convenio";
					titulos[3]="Tipo Nomina";
					titulos[4]="APV($)";
					titulos[5]="Deposito C.($)";
					titulos[6]="Aporte I.($)";
				}
				
				xls.generarXLSfromCollection(cuadraturas, columnas, titulos, "000077");
				out.close();
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
		return forward;
	}
}
