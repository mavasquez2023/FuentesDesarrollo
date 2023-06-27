package cl.araucana.cp.certificadoAnual;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPub.UtilPub;

import cl.araucana.cp.distribuidor.presentation.beans.DTOcertificado;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servlet implementation class for Servlet: CertificadoAnualPDF
 *
 */
 public class CertificadoAnualPDF extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private String rutaPdf=null;
	private String rutaJasper=null;
	private String rutaZip=null;
	private String rutaBarra=null;
	 
	private static final long serialVersionUID = 1L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CertificadoAnualPDF() {
		super();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try{
			System.out.println("en certificado anual pdf");
			JRBeanCollectionDataSource dataSource;
			Map params = new HashMap();
			String rutEmp = request.getParameter("rut");
			String holding = request.getParameter("holding");
			String procesos = "'R','G','A'";
			int vo = 0;
			
			UtilPub util = new UtilPub();

			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement();
			String numeroComprobante = null;
			
			System.out.println("DATOS DE CABECERA QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			ResultSet result =  stmt.executeQuery("select * from PWDTAD.PWF5000 where PWCCRUTEM="+rutEmp+" AND PWCCCOPRO>0 AND PWCCTIPRO IN ("+procesos+") AND PWCCCDHOL>-1 AND PWCCCONV>0  order by PWCCCOPRO desc, PWCCCONV asc FETCH FIRST 1 ROWS ONLY");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("DATOS DE CABECERA BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			while(result.next())
			{
				params.put("rut", result.getObject("PWCCRUTEM") + "-" + result.getObject("PWCCDIGEM"));
				params.put("domicilioPostal", result.getObject("PWCCDOMEM") != null ? result.getObject("PWCCDOMEM"):"");
				params.put("correo", "");
				params.put("razonSocial", result.getObject("PWCCRAZSO"));
				params.put("comuna", result.getObject("PWCCCOMEM") != null ? result.getObject("PWCCCOMEM"):"");
				params.put("fono", result.getObject("PWCCTELEM"));
				params.put("region", result.getObject("PWCCREGEM"));
				numeroComprobante = result.getObject("PWCCNUMCO").toString();
			}
			result.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			params.put("year", request.getParameter("year"));
			
			String year = request.getParameter("year");
			String periodo=request.getParameter("year")+"01";
			
			System.out.println("DATOS DE PREVISION QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			ResultSet result2 = stmt.executeQuery("SELECT A.PWDCNOMEM AS NOMBRE , (SELECT Z.PWFRUTENTAFP ||'-'|| Z.PWFDVAFP FROM PWDTAD.PWFENTAFP Z WHERE PWFNOMENTAFP=A.PWDCNOMEM ) AS RUT,SUM((A.PWDCMONPEN*C.PWPCMONPEN) + (PWDCMONSIS*PWPCMONSIS)  + (PWDCMONAHO*PWPCMONAHO) + (PWDCMONCES*PWPCMONCES) + 	(PWDCMONTP*PWPCMONTP)) AS TOTAL FROM PWDTAD.PWF5100 A ,PWDTAD.PWF5300 C, PWDTAD.PWF5000 B WHERE B.PWCCNUMCO = A.PWDCNUMCO AND B.PWCCRUTEM="+rutEmp+" AND B.PWCCCDHOL IN ("+holding+")  AND B.PWCCTIPRO IN ("+procesos+")  AND B.PWCCCOPRO >= "+year+"01 AND B.PWCCCOPRO <= "+year+"12 AND B.PWCCCONV>0 AND A.PWDCTIPEM = 'AFP' AND A.PWDCNOMEM IS NOT NULL AND A.PWDCCONCE IS NOT NULL AND C.PWPCCOPRO='"+year+"01' GROUP BY A.PWDCNOMEM ,(SELECT Z.PWFRUTENTAFP ||'-'|| Z.PWFDVAFP FROM PWDTAD.PWFENTAFP Z WHERE Z.PWFNOMENTAFP=A.PWDCNOMEM)");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("DATOS DE PREVISION BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			Collection list = new ArrayList();
			long total = 0;
			
			for(;result2.next();)
			{
				long totalItem = Long.parseLong(result2.getObject("TOTAL").toString());
				
				list.add(new DTOcertificado(
						"PREVISIÓN",
						result2.getString("NOMBRE"),
						result2.getString("RUT").split("-")[0],
						result2.getString("RUT").split("-")[1],
						"$"+formatNum(String.valueOf(totalItem))
						));
			}
			result2.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("DATOS DE PENSION QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			//IPS pensión
			ResultSet pension = stmt.executeQuery("select PWDCNOMEM as nombre, PWDTAD.PWFENTAFP.PWFRUTENTAFP as rut, PWDTAD.PWFENTAFP.PWFDVAFP as dv,sum(PWDCMONTO) as total from pwdtad.pwf5100,PWDTAD.PWFENTAFP where PWDCTIPEM = 'IPS' and LOWER(PWDCCONCE)='pensión' and PWDCNUMCO IN(SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCCONV>0) AND PWDCNOMEM = PWFNOMENTAFP GROUP BY PWDCNOMEM,PWDTAD.PWFENTAFP.PWFRUTENTAFP,PWDTAD.PWFENTAFP.PWFDVAFP");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("DATOS DE PENSION BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			for(;pension.next();)
			{
				list.add(new DTOcertificado(
						"PREVISIÓN",
						pension.getString("nombre"),
						pension.getString("rut"),
						pension.getString("dv"),
						"$"+formatNum(pension.getObject("total").toString())
						));
			}
			pension.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("CCAF QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			ResultSet ccaf = stmt.executeQuery("SELECT SUM(PWDCMONTO) as ccaf FROM PWDTAD.PWF5100 WHERE PWDCTIPEM = 'CCAF' AND PWDCNUMCO IN(SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCCONV>0) AND PWDCCONCE LIKE '0.%'");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("CCAF BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			Object resultCcaf = null;
			while(ccaf.next()){
				resultCcaf=ccaf.getObject("ccaf");
			}
			ccaf.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("SALUD QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			System.out.println("SELECT PWDCNOMEM as nombre, B.PWFRU00001 as rut, B.PWFDV00001 as dv, sum(PWDCMONTO) as total FROM PWDTAD.PWF5100 A, PWDTAD.PWFENTISA B WHERE PWDCNUMCO IN (SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCNUMCO>0 AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND PWCCCONV>0) AND PWDCTIPEM = 'ISAPRE' AND lower(PWDCNOMEM) = lower(PWFNO00001) GROUP BY PWDCNOMEM, B.PWFRU00001, B.PWFDV00001");
			ResultSet result3 = stmt.executeQuery("SELECT PWDCNOMEM as nombre, B.PWFRU00001 as rut, B.PWFDV00001 as dv, sum(PWDCMONTO) as total FROM PWDTAD.PWF5100 A, PWDTAD.PWFENTISA B WHERE PWDCNUMCO IN (SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCNUMCO>0 AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND PWCCCONV>0) AND PWDCTIPEM = 'ISAPRE' AND lower(PWDCNOMEM) = lower(PWFNO00001) GROUP BY PWDCNOMEM, B.PWFRU00001, B.PWFDV00001");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("salid: SELECT PWDCNOMEM as nombre, B.PWFRU00001 as rut, B.PWFDV00001 as dv, sum(PWDCMONTO) as total FROM PWDTAD.PWF5100 A, PWDTAD.PWFENTISA B WHERE PWDCNUMCO IN (SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCNUMCO>0 AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND PWCCCONV>0) AND PWDCTIPEM = 'ISAPRE' AND lower(PWDCNOMEM) = lower(PWFNO00001) GROUP BY PWDCNOMEM, B.PWFRU00001, B.PWFDV00001");
			
			System.out.println("SALUD BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			for(;result3.next();)
			{
				String totalItem = result3.getObject("total").toString();

				
				list.add(new DTOcertificado(
						"SALUD",
						result3.getString("nombre"),
						result3.getString("rut"),
						result3.getString("dv"),
						"$"+formatNum(totalItem)
				));
			}
			result3.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			
			System.out.println("FONASA QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			ResultSet fonasa = stmt.executeQuery("SELECT PWDCNOMEM as nombre, B.PWFRU00001 as rut, B.PWFDV00001 as dv,sum(PWDCMONTO) as total FROM PWDTAD.PWF5100 A, PWDTAD.PWFENTISA B WHERE PWDCNUMCO IN (SELECT PWCCNUMCO FROM PWDTAD.PWF5000 WHERE PWCCNUMCO>0 AND (PWCCCOPRO BETWEEN "+year+"01 AND "+year+"12) AND PWCCRUTEM="+rutEmp+" AND PWCCCDHOL IN ("+holding+") AND PWCCTIPRO in("+procesos+") AND PWCCCONV>0) AND PWDCTIPEM = 'IPS' AND lower(PWFNO00001) = lower(PWDCCONCE) GROUP BY PWDCNOMEM, B.PWFRU00001, B.PWFDV00001");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("FONASA BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			for(;fonasa.next();)
			{
				String totalItem = fonasa.getObject("total").toString();
				long totalConCcaf=0;

				if(resultCcaf != null)
					totalConCcaf = Long.parseLong(totalItem) + Long.parseLong(resultCcaf.toString());
				else
					totalConCcaf = Long.parseLong(totalItem);
				
				list.add(new DTOcertificado(
						"SALUD",
						fonasa.getString("nombre"),
						fonasa.getString("rut"),
						fonasa.getString("dv"),
						"$"+formatNum(String.valueOf(totalConCcaf))
						));
			}
			fonasa.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
									
			list.add(new DTOcertificado("","lastrow","","",null));
			
			dataSource = new JRBeanCollectionDataSource(list);
	
			
			System.out.println("RENTAS IMPONIBLES QUERY");
			System.out.println("INCIO: "+fechaHora()[2]);
			System.out.println("query rentas: "+"SELECT PWCCCOPRO AS PERIODO,ROUND(SUMPWCCREMIM * IFNULL(VALUE,1),0) AS MONTO FROM (SELECT  PWCCCOPRO,PWCCRUTEM,SUM( PWCCREMIM) AS SUMPWCCREMIM FROM PWDTAD.PWF5000 WHERE PWCCRUTEM="+rutEmp+" AND INT(PWCCCOPRO/100)="+year+" AND PWCCCDHOL IN("+holding+") AND PWCCTIPRO in("+procesos+") GROUP  BY PWCCCOPRO,PWCCRUTEM ORDER BY PWCCCOPRO ) AS A LEFT OUTER JOIN (SELECT INT(PWCCYEAR||'01') AS PERIODO,PWCCENE AS  VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'02') AS PERIODO,PWCCFEB AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'03') AS PERIODO,PWCCMAR AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'04') AS PERIODO,PWCCABR AS  VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'05') AS PERIODO,PWCCMAY AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'06') AS PERIODO,PWCCJUN AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'07') AS PERIODO,PWCCJUL AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'08') AS PERIODO,PWCCAGO AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'09') AS PERIODO,PWCCSEP AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'10') AS PERIODO,PWCCOCT AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'11') AS PERIODO,PWCCNOV AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'12') AS PERIODO,PWCCDIC AS VALUE FROM PWDTAD.PWF5500)  B ON A.PWCCCOPRO=B.PERIODO ORDER BY PWCCCOPRO");
			ResultSet month = stmt.executeQuery("SELECT PWCCCOPRO AS PERIODO,ROUND(SUMPWCCREMIM * IFNULL(VALUE,1),0) AS MONTO FROM (SELECT  PWCCCOPRO,PWCCRUTEM,SUM( PWCCREMIM) AS SUMPWCCREMIM FROM PWDTAD.PWF5000 WHERE PWCCRUTEM="+rutEmp+" AND INT(PWCCCOPRO/100)="+year+" AND PWCCCDHOL IN("+holding+") AND PWCCTIPRO in("+procesos+") GROUP  BY PWCCCOPRO,PWCCRUTEM ORDER BY PWCCCOPRO ) AS A LEFT OUTER JOIN (SELECT INT(PWCCYEAR||'01') AS PERIODO,PWCCENE AS  VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'02') AS PERIODO,PWCCFEB AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'03') AS PERIODO,PWCCMAR AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'04') AS PERIODO,PWCCABR AS  VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'05') AS PERIODO,PWCCMAY AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'06') AS PERIODO,PWCCJUN AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'07') AS PERIODO,PWCCJUL AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'08') AS PERIODO,PWCCAGO AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'09') AS PERIODO,PWCCSEP AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'10') AS PERIODO,PWCCOCT AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'11') AS PERIODO,PWCCNOV AS VALUE FROM PWDTAD.PWF5500 UNION SELECT INT(PWCCYEAR||'12') AS PERIODO,PWCCDIC AS VALUE FROM PWDTAD.PWF5500)  B ON A.PWCCCOPRO=B.PERIODO ORDER BY PWCCCOPRO");
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			System.out.println("RENTAS IMPONIBLES BUCLE");
			System.out.println("INCIO: "+fechaHora()[2]);
			Object value = null;
			
			for(;month.next();){
				value = formatNum(Math.round(month.getDouble("MONTO")));

				value = value.toString().replaceAll(",", ".");
				
				if(month.getInt("PERIODO") == Integer.parseInt(year+"01")){
					params.put("enero",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"02")){
					params.put("febrero",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"03")){
					params.put("marzo",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"04")){
					params.put("abril",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"05")){
					params.put("mayo",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"06")){
					params.put("junio",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"07")){
					params.put("julio",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") ==Integer.parseInt(year+"08")){
					params.put("agosto",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"09")){
					params.put("septiembre",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"10")){
					params.put("octubre",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"11")){
					params.put("noviembre",(value != null ? value:"0"));
				}else if(month.getInt("PERIODO") == Integer.parseInt(year+"12")){
					params.put("diciembre",(value != null ? value:"0"));
				}
				
				total = total + (value != null ? Long.parseLong(value.toString().replaceAll("\\.","")):0);
			}
			month.close();
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			params.put("totalanual", "$"+formatNum(total));
			
			System.out.println("CREACION DE LA FECHA");
			System.out.println("INCIO: "+fechaHora()[2]);
		    //creación de la fecha
		    SimpleDateFormat formateador = new SimpleDateFormat("'Santiago, 'dd 'de' MMMM 'de' yyyy", new Locale("es","CL"));   
		    Date fechaDate = new Date();   
		    String fecha = formateador.format(fechaDate);
		    params.put("fecha", fecha);
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
		    
		    //obtenemos la ruta del archivo xml jasper
			final ServletContext context = getServletContext();
			final URL configFileURL = context.getResource("/PDFJasper/CertificadoSence.jrxml");
			//System.out.println(configFileURL.getPath());
			
			/*System.out.println("COMPILANDO JASPER");
			System.out.println("INCIO: "+fechaHora()[2]);
			String jReport = JasperCompileManager.compileReportToFile("/Publicacion/PDFJasper/CertificadoSence.jrxml");			
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");*/
			
			System.out.println("LLENANDO REPORTE CON DATOS");
			System.out.println("INCIO: "+fechaHora()[2]);
			JasperPrint jPrint = JasperFillManager.fillReport("/Publicacion/PDFJasper/CertificadoSence.jasper",params,dataSource);
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			
			
			System.out.println("PARAMETROS DE CABECERA");
			System.out.println("INCIO: "+fechaHora()[2]);
			response.addHeader("Content-Type", "application/pdf");
			//response.addHeader("Content-Disposition", "attachment; filename=\"CertificadoSenceAnual.pdf\"");
			response.setHeader("Content-Disposition","inline; filename=\"CertificadoSenceAnual.pdf\""); 
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "No-cache"); 
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");
			 
			/*OutputStream OutStream = response.getOutputStream();
			OutStream.write(bytes, 0, bytes.length);
			OutStream.flush();
			OutStream.close();*/
			
			System.out.println("EXPORT REPORT TO PDF");
			System.out.println("INCIO: "+fechaHora()[2]);
			JasperExportManager.exportReportToPdfStream(jPrint, response.getOutputStream());
			System.out.println("FIN  : "+fechaHora()[2]);
			System.out.println("");

			System.out.println("Done");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
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
	
    public String formatDate(String date)
    {
    	return date.substring(date.length()-2, date.length())+"/"+date.substring(4,6)+"/"+date.substring(0, 4);
    }
    
    public Object formatNum(String number)
    {
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		return formateador.format(Long.valueOf(number));
    }
    
    public Object formatNum(long number)
    {
    	Locale.setDefault(new Locale("es","CL"));
    	
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		
		return formateador.format(number);
    }
    
	
	public void init() throws ServletException {
	    getServletContext().log("getinit init");
	    // Get the value of an initialization parameter
	     rutaJasper = getServletConfig().getInitParameter("rutaJasper");
	     rutaPdf=getServletConfig().getInitParameter("rutaPdf");
	     rutaZip=getServletConfig().getInitParameter("rutaZip");
	     rutaZip=getServletConfig().getInitParameter("rutaBarra"); 
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