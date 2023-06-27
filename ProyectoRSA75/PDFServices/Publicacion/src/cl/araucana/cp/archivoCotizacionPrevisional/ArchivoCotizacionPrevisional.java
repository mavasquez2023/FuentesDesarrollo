package cl.araucana.cp.archivoCotizacionPrevisional;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import utilPub.UtilPub;

/**
 * Servlet implementation class for Servlet: CertificadoAnualData
 *
 */
 public class ArchivoCotizacionPrevisional extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ArchivoCotizacionPrevisional() {
		super();
	}   	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			
			HttpSession session = request.getSession();
			session.setAttribute("campos", "");
			String periodoDesde = request.getParameter("FechaProceso");
			String periodoHasta = request.getParameter("FechaProceso2");
			String rutEmpresa = request.getParameter("RutEmpresa");
			String convenio = request.getParameter("Convenio");
			String holding = request.getParameter("holding").replaceAll(" ",",");
			String tipoProceso = request.getParameter("TipoProceso");

			
			//FORMATEO DE RUT PARA LA QUERY
			String rutSplit[] = rutEmpresa.trim().split(" ");
			rutEmpresa="";
			for(int i=0;i<rutSplit.length;i++){
				rutEmpresa = rutEmpresa + "" + rutSplit[i] + ","; 
			}
			rutEmpresa = rutEmpresa.substring(0,rutEmpresa.length()-1); //sacamos la 'coma' que sobra
			System.out.println(rutEmpresa);
			/**************************************/
			
			//FORMATEO DE CONVENIOS PARA LA QUERY
			String convSplit[] = convenio.trim().split(" ");
			convenio="";
			for(int i=0;i<convSplit.length;i++){
				convenio = convenio + "'" + convSplit[i] + "',"; 
			}
			convenio = convenio.substring(0,convenio.length()-1);
			System.out.println(convenio);
			
			ArchivoCotizacionPrevisionalDAO dao = new ArchivoCotizacionPrevisionalDAO();
			
			ArrayList list = new ArrayList();
			
			if (tipoProceso.equals("")) {
				list = dao.GerenaConsultaCabecera("TODOS", rutEmpresa, convenio, periodoDesde, periodoHasta, holding, tipoProceso);}
			else if (tipoProceso.equals("R")) {
				list = dao.GerenaConsultaCabecera("PWF6100", rutEmpresa, convenio, periodoDesde, periodoHasta, holding, "Remuneraciones");}
			else if (tipoProceso.equals("G")) {
				list = dao.GerenaConsultaCabecera("PWF6101", rutEmpresa, convenio, periodoDesde, periodoHasta, holding, "Gratificaciones");}
			else if (tipoProceso.equals("A")) {
				list = dao.GerenaConsultaCabecera("PWF6105", rutEmpresa, convenio, periodoDesde, periodoHasta, holding, "Reliquidaciones");
			}	
			
			for (int i = 0; i < list.size(); i++) {
				Map map = new HashMap();
				map = (HashMap) list.get(i);
				map.put("rsocial", dao.getRazonSocial((String) map.get("rut")));
			}

			request.setAttribute("comprobantes", list);
			
			session.setAttribute("campos", list);
			
			getServletContext().getRequestDispatcher("ArchivoCertificadoCotData.jsp").forward(request, response);
		
		} catch (Exception e) {
			System.out.println("werror: "+e.getMessage());
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