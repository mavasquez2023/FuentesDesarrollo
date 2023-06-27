package cl.araucana.cp.comprobantePago;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class for Servlet: ComprobantePagoData
 *
 */
 public class ComprobantePagoData extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6239531807761364397L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ComprobantePagoData() {
		super();
	}  
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			UtilPub util = new UtilPub();
			
			String periodoDesde = request.getParameter("FechaProceso");
			String periodoHasta = request.getParameter("FechaProceso2");
			String rutEmpresa = request.getParameter("RutEmpresa");
			String comprobante = request.getParameter("NumeroComprobante");
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
			/**************************************/

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
			
			while(result.next()){
				Map params = new HashMap();
				params.put("fecha",result.getObject("fecha").toString());
				params.put("proceso",result.getObject("proceso").toString());
				params.put("rut",result.getObject("rut").toString());
				params.put("digito",result.getObject("digito").toString());
				params.put("rsocial",result.getObject("rsocial").toString());
				params.put("convenio",result.getObject("convenio").toString());
				params.put("comprobante",result.getObject("code").toString());
				list.add(params);
			}
			
			result.close();

			request.setAttribute("comprobantes", list);
			
			getServletContext().getRequestDispatcher("comprobanteData.jsp").forward(request, response);
		
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
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();
	} 

	public static String [] fechaHora(){
		
		  java.util.Date utilDate = new java.util.Date(); //fecha actual
		  long lnMilisegundos = utilDate.getTime();
		  java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
		  java.sql.Time sqlTime = new java.sql.Time(lnMilisegundos);
		  java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		  
		  // System.out.println("util.Date: "+utilDate);
		  // System.out.println("sql.Date: "+sqlDate);
		  // System.out.println("sql.Time: "+sqlTime);
		  // System.out.println("sql.Timestamp: "+sqlTimestamp);
		  
		  String [] salida =  new String[4]; 
		  
		  salida [0] =  utilDate.toString();
		  salida [1] =  sqlDate.toString();
		  salida [2] =  sqlTime.toString();
		  salida [3] =  sqlTimestamp.toString();
		  
		  return salida;

		}
}