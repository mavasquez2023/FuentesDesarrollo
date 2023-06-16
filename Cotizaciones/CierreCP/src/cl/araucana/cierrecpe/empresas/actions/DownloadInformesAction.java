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
import cl.araucana.cierrecpe.empresas.business.InformesCierreCP;
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
public class DownloadInformesAction extends Action {
	private static Logger logger = LogManager.getLogger();
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
			throws Exception 
			{	
		ActionForward forward = new ActionForward();
		InformesCierreCP generar=null;
		try{
			logger.fine("Entrando a Download InformesAction");
			HttpSession sesion= request.getSession();
			generar= new InformesCierreCP();
			System.out.println("Periodo=" + request.getParameter("periodo"));
			int periodo= Integer.parseInt(request.getParameter("periodo"));
			int opcion= Integer.parseInt(request.getParameter("recurso"));
			String tipo="";
			if(opcion==1){
				tipo="EMPRESAS NUEVAS ";
			}else if(opcion==2){
				tipo="NO PAGARON ";
			}else if(opcion==3){
				tipo="EMPRESAS ACTIVAS ";
			}else if(opcion==4){
				tipo="FORMA DE PAGO ";
			}
			String filename= tipo + periodo + ".xls";
			
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
			
			List informe=null;
			if(opcion==1){
				informe= (List)generar.generarInformeEmpresasNuevas(periodo);
//				Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"server", "grupoConvenio", "rutEmpresa", "convenio", "tipoNomina", "razonSocial", "total", "numtra", "telefono", "nombreRL", "domicilio", "comuna", "ciudad", "caja" };
				String[] titulos={"Server", "Grupo Convenio", "Rut Empresa", "Convenio", "Tipo Proceso", "Razon Social", "Total Pago($)", "N° Trab.", "Telefono", "Nombre Rep. Legal", "Domicilio E.", "Comuna E.", "Ciudad E.", "Caja"};
				xls.generarXLSfromCollection(informe, columnas, titulos, "000077");
			}else if(opcion==2){
				informe= (List)generar.generarInformeEmpresasNoPagaron(periodo);
//				Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"server", "grupoConvenio", "rutEmpresa", "convenio", "tipoNomina", "razonSocial", "total", "numtra", "telefono", "nombreRL", "domicilio", "comuna", "ciudad", "caja" };
				String[] titulos={"Server", "Grupo Convenio", "Rut Empresa", "Convenio", "Tipo Proceso", "Razon Social", "Total Pago($)", "N° Trab.", "Telefono", "Nombre Rep. Legal", "Domicilio E.", "Comuna E.", "Ciudad E.", "Caja"};
				xls.generarXLSfromCollection(informe, columnas, titulos, "000077");
			}else if(opcion==3){
				informe= (List)generar.generarInformeEmpresasActivas(periodo);
//				Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"server", "grupoConvenio", "rutEmpresa", "convenio", "tipoNomina", "razonSocial", "domicilio", "comuna", "region", "telefono", "nombreRL", "total", "numtra", "caja"};
				String[] titulos={"Server", "Grupo Convenio", "Rut Empresa", "Convenio", "Tipo Proceso", "Razon Social", "Domicilio E.", "Comuna E.", "Region E.", "Telefono", "Nombre Rep. Legal", "Total Pagado", "N° Trab.", "Caja"};
				xls.generarXLSfromCollection(informe, columnas, titulos, "000077");
			}else if(opcion==4){
				informe= (List)generar.generarInformeFormasDePago(periodo);
//				Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"server", "cierre", "grupoConvenio", "rutEmpresa", "razonSocial", "total", "fechaPago", "folio", "afp", "isapre", "ips", "mutual", "ccaf", "asfam", "aporte", "credito", "leasing", "vida", "dental", "sfe", "caja", "formaPago", "nombreBanco", "codigoBanco", "cuentaBanco", "fechaContable", "tipoRegistro", "montoConsolidado"};
				String[] titulos={"Server", "Cierre", "Grupo Convenio", "Rut Empresa", "Razon Social", "Total Pagado", "Fecha Pago", "Folio Tesoreria", "Total AFP", "Total Isapre", "Total IPS", "Total Mutual", "Total CCAF", "Asfam", "Aporte", "Crédito", "Leasing", "Seguro Vida", "Conv. Dental", "S.F.E.", "Caja", "Forma de Pago", "Nombre Banco", "Codigo Banco", "Cuenta Banco", "Fecha Contable", "Tipo Registro", "Monto Consolidado"};
				xls.generarXLSfromCollection(informe, columnas, titulos, "000077");
			}
			
			if (informe!= null){
				forward = null;
			}else{
				logger.warning("Se solicita descargar excel, sin embargo, informe es null");
				forward = mapping.findForward("NOTOK");
			}
			
			
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			forward = mapping.findForward("NOTOK");
			System.out.println("DownloadInformesAction, error en la descarga del archivo excel");
			e.printStackTrace();
		}
		finally{
			if(generar != null){
				generar.close();
			}
		}
		return forward;
	}
}
