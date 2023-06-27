package cl.araucana.cp.certificadoAnual;

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

import utilPub.UtilPub;

/**
 * Servlet implementation class for Servlet: CertificadoAnualData
 *
 */
 public class CertificadoAnualData extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CertificadoAnualData() {
		super();
	}   	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			UtilPub util = new UtilPub();

			String year = request.getParameter("years");
			String ruts = request.getParameter("RutEmpresa");
			String holding = request.getParameter("holdingA");
			holding = holding.replaceAll(" ",",");
			String procesos = "'R','G','A'";
			
			String rutSplit[] = ruts.trim().split(" ");
			ruts="";
			for(int i=0;i<rutSplit.length;i++){
				ruts = ruts + rutSplit[i] + ","; 
			}
			ruts = ruts.substring(0,ruts.length()-1);
			System.out.println(ruts);
			
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			
			String query = "SELECT pwccrutem as rut,pwccdigem as digito,pwccrazso as rsocial FROM PWDTAD.PWF5000 WHERE pwccrutem IN("+ruts+") AND PWCCCDHOL IN ("+holding+") AND TRIM(PWCCTIPRO) IN ("+procesos+") AND PWCCCONV>0 AND PWCCCOPRO >= "+year+"01 AND PWCCCOPRO <= "+year+"12 group by pwccrutem,pwccdigem,pwccrazso order by pwccrutem asc";

			System.out.println(query);
			ResultSet result = stmt.executeQuery(query);
			
			year = "";
			String rut = "";
			ArrayList list = new ArrayList();
			
			while(result.next()){
				if(rut.lastIndexOf(result.getObject("rut").toString()) == -1){
					Map params = new HashMap();

					params.put("rut",result.getObject("rut").toString());
					params.put("digito",result.getObject("digito").toString());
					params.put("rsocial",result.getObject("rsocial").toString());
					list.add(params);
					rut = rut + result.getObject("rut").toString() + " ";
				}
			}

			result.close();

			request.setAttribute("empresas", list);
			request.setAttribute("year", request.getParameter("years"));
			request.setAttribute("holding", request.getParameter("holdingA"));
			
			getServletContext().getRequestDispatcher("certificadoAnualData.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
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