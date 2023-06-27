package cl.araucana.cp.comprobantePago;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPub.UtilPub;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import cl.araucana.cp.distribuidor.presentation.beans.DTOcomprobanteData;


/**
 * Servlet implementation class for Servlet: ComprobantePagoDataPDF
 *
 */
 public class ComprobantePagoDataPDF extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	private static final long serialVersionUID = 1L; 
	 
	public ComprobantePagoDataPDF() {
		super();
	}   
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		JasperPrint jPrint = null;
		try{
			JRBeanCollectionDataSource dataSource;
			Map params = new HashMap();
			String periodoDesde = request.getParameter("FechaProceso");
			String periodoHasta = request.getParameter("FechaProceso2");
			String rutEmpresa = request.getParameter("RutEmpresa");
			String comprobante = request.getParameter("NumeroComprobante");
			String convenio = request.getParameter("Convenio");
			String holding = request.getParameter("holding");
			String tipoProceso = request.getParameter("TipoProceso");
			System.out.println("tipoProceso: "+tipoProceso);
			
			System.out.println("FECHAINI: "+ periodoDesde);
			System.out.println("FECHAEND: "+ periodoHasta);
			System.out.println("ruts: "+ rutEmpresa);
			System.out.println("COMPROBANTE: "+ comprobante);
			System.out.println("CONVENIO: "+ convenio);
			
			//FORMATEO DE RUT PARA LA QUERY
			String rutSplit[] = rutEmpresa.trim().split(",");
			rutEmpresa="";
			for(int i=0;i<rutSplit.length;i++){
				rutEmpresa = rutEmpresa + "'" + rutSplit[i] + "',"; 
			}
			rutEmpresa = rutEmpresa.substring(0,rutEmpresa.length()-1);
			System.out.println(rutEmpresa);
			/**************************************/
			
			//FORMATEO DE CONVENIOS PARA LA QUERY
			String convSplit[] = convenio.trim().split(",");
			convenio="";
			for(int i=0;i<convSplit.length;i++){
				convenio = convenio + "'" + convSplit[i] + "',"; 
			}
			convenio = convenio.substring(0,convenio.length()-1);
			System.out.println(convenio);
			/**************************************/
			
			UtilPub util = new UtilPub();

			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			ResultSet result = null;
			
			System.out.println("Apunto de ejecutar las query");
			if(comprobante==null || "".equals(comprobante)){ //si el número de comprobante viene vacio
				System.out.println("QUERY: "+"SELECT pwcccopro as fecha,pwcctipro as proceso,pwccrutem as rut,pwccdigem as digito,pwccrazso as rsocial,pwccconv as convenio,pwccnumco as code FROM PWDTAD.PWF5000 where pwcccopro >= "+periodoDesde+" AND pwcccopro <= "+periodoHasta+" AND pwccrutem IN("+rutEmpresa+") AND pwccconv IN("+convenio+") AND pwcccdhol in("+holding+") "+(!tipoProceso.equals("") && tipoProceso!=null ? "AND pwcctipro = '"+tipoProceso+"'":"")+" ORDER BY pwccrutem ASC");
				result =  stmt.executeQuery("SELECT pwcccopro as fecha,pwcctipro as proceso,pwccrutem as rut,pwccdigem as digito,pwccrazso as rsocial,pwccconv as convenio,pwccnumco as code FROM PWDTAD.PWF5000 where pwcccopro >= "+periodoDesde+" AND pwcccopro <= "+periodoHasta+" AND pwccrutem IN("+rutEmpresa+") AND pwccconv IN("+convenio+") AND pwcccdhol in("+holding+") "+(!tipoProceso.equals("") && tipoProceso!=null ? "AND pwcctipro = '"+tipoProceso+"'":"")+" ORDER BY pwccrutem ASC");
			}else{
				result =  stmt.executeQuery("SELECT pwccnumco as code,pwcccopro as fecha,pwcctipro as proceso,pwccrutem as rut,pwccdigem as digito,pwccrazso as rsocial,pwccconv as convenio FROM PWDTAD.PWF5000 where pwcccopro >= "+periodoDesde+" AND pwcccopro <= "+periodoHasta+" AND pwccrutem IN("+rutEmpresa+") AND pwccconv IN("+convenio+") AND pwcccdhol in("+holding+") "+(!tipoProceso.equals("") && tipoProceso!=null ? "AND pwcctipro = '"+tipoProceso+"'":"")+" AND pwccnumco='"+comprobante+"' ORDER BY pwccrutem ASC");				
			}
			ArrayList list = new ArrayList();
			int cont=0;
	
			params.put("pPeriodo", periodoDesde+" al "+periodoHasta);
			
			while(result.next())
			{
				if(cont>=250) break;
				list.add(new DTOcomprobanteData(
						result.getObject("fecha").toString(),
						result.getObject("proceso").toString(),
						result.getObject("rut").toString()+"-"+result.getObject("digito").toString(),
						result.getObject("rsocial").toString(),
						result.getObject("convenio").toString(),
						result.getObject("code").toString()
				));
				cont++;
			}
			
			result.close();
			
			dataSource = new JRBeanCollectionDataSource(list);
			
			System.out.println("seteados");
			//JasperCompileManager.compileReportToFile("/Publicacion/PDFJasper/comprobanteData.jrxml","/Publicacion/PDFJasper/comprobanteData.jasper");			
			System.out.println("compilado");
			jPrint = JasperFillManager.fillReport("/Publicacion/PDFJasper/comprobanteData.jasper",params,dataSource);
			System.out.println("llenado");
			
			response.addHeader("Content-Type", "application/pdf");
			//response.addHeader("Content-Disposition", "attachment; filename=\"CertificadoSenceAnual.pdf\"");
			response.setHeader("Content-Disposition","inline; filename=\"ComprobanteData.pdf\""); 
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "No-cache"); 
			
			
			JasperExportManager.exportReportToPdfStream(jPrint, response.getOutputStream());

			System.out.println("Done");
		}catch(Exception e){
			System.out.println("Error en DATAPDF: "+e.getMessage());
			try {
				JasperExportManager.exportReportToPdfStream(jPrint, response.getOutputStream());
			} catch (JRException e1) {
				// TODO Bloque catch generado automáticamente
				e1.printStackTrace();
			}
		}
	}
	
   
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}  	  	    
}