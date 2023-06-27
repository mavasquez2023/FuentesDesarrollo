package cl.araucana.cp.comprobantePago;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilPub.UtilPub;

import cl.araucana.cp.distribuidor.presentation.beans.DTOcomprobante;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servlet implementation class for Servlet: ComprobantePagoPDF
 *
 */
 public class ComprobantePagoPDF extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {


	public ComprobantePagoPDF() {
		super();
	} 
	 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try {
			Collection listaFinal = new ArrayList();
			JRBeanCollectionDataSource dataSource;
			
			//InputStream inputStream = this.getClass().getResourceAsStream("/comprobantePago.properties");
			InputStream inputStream = this.getClass().getResourceAsStream("/comprobantePago.properties");
			Properties p = new Properties();
			p.load(inputStream);
			
			//nos conectamos a la BD para obtener los datos de cabecera de la Empresa
			UtilPub util = new UtilPub();
			Connection conexion = util.getConnection();
			Statement stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			//String queryProperty = p.getProperty("head");
			//ResultSet result =  stmt.executeQuery(queryProperty.replaceAll("\\{code\\}", request.getParameter("code")));
			ResultSet result =  stmt.executeQuery("select * from PWDTAD.PWF5000 where PWCCNUMCO = "+request.getParameter("code"));
			
			Map params = new HashMap();
			while(result.next())
			{
				//primero le damos formato a la fecha del comprobante (dd-mm-yyyy)
				String fecha = result.getObject("PWCCFECHA").toString().trim();
				fecha = fecha.split(" ")[0].split("-")[2] + "-" + fecha.split(" ")[0].split("-")[1] + "-" + fecha.split(" ")[0].split("-")[0] + " " + fecha.split(" ")[1];
				fecha = fecha.substring(0,fecha.length()-2);
				
				//ahora seteamos el tipo de proceso
				String tipoProceso = "";
				if(result.getObject("pwcctipro").toString().equals("R")){
					tipoProceso = "REMUNERACIÓN";
				}else if(result.getObject("pwcctipro").toString().equals("G")){
					tipoProceso = "GRATIFICACIÓN";
				}else if(result.getObject("pwcctipro").toString().equals("D")){
					tipoProceso = "DEPÓSITOS CONVENIDOS";
				}else if(result.getObject("pwcctipro").toString().equals("A")){
					tipoProceso = "RELIQUIDACIÓN";
				}
				
				params.put("grupoConvenio", result.getObject("PWCCCDHOL").toString());
				params.put("tipoProceso", tipoProceso);
				params.put("rut", result.getObject("PWCCRUTEM") + "-" + result.getObject("PWCCDIGEM"));
				params.put("ciudad", result.getObject("PWCCCIUEM"));
				params.put("razonSocial", result.getObject("PWCCRAZSO"));
				params.put("region", result.getObject("PWCCREGEM").toString());
				params.put("convenio", result.getObject("PWCCCONV").toString());
				params.put("telefono", result.getObject("PWCCTELEM"));
				params.put("rutSinGuion", result.getObject("PWCCRUTEM").toString());
				params.put("periodo", result.getObject("PWCCCOPRO").toString());
				params.put("code", result.getObject("PWCCNUMCO").toString());
				params.put("totalPago","$"+formatNum(result.getObject("PWCCTOTPA").toString()));
				params.put("numTrabajadores",result.getObject("PWCCTOTTR").toString());
				params.put("fechaComprobante",fecha);
				params.put("fechaPago",formatDate(result.getObject("PWCCFEPAG").toString()));
				params.put("folioTesoreria",result.getObject("PWCCFOLTES").toString());
				params.put("rentaImponible",formatNum(result.getObject("PWCCREMIM").toString()));
				params.put("representanteLegal",result.getObject("PWCCNOMRE").toString().trim());
			}
			
			result.close();
			
			//queryProperty = p.getProperty("detail");
			
			//ResultSet result2 =  stmt.executeQuery(queryProperty.replaceAll("\\{code\\}", request.getParameter("code")));
			//clillo 28/10/14 Se agrega TP en la query
			ResultSet result2 =  stmt.executeQuery("SELECT pwdctipem as Entidad, pwdcnomem  as Nombre, CASE pwdctipem when 'AFP' then 0 when 'ISAPRE' then 1 when 'IPS' then 2 when 'MUTUAL' then 3  when 'CCAF' then 4 when 'APV' then 5 END as orden_seccion, CASE when pwdctipem='IPS' then CASE pwdcconce when 'S.F.E.' then 0 when 'S.F.I.' then 1 when 'Pensión' then 2 when 'Fonasa' then 3 when 'Accidente del Trabajo' then 4 when 'Asignación Familiar' then 5 END END as orden_inp, CASE when pwdctipem='CCAF' then CASE pwdcconce when 'S.F.E.' then 0 when 'S.F.I.' then 1 when '0.6%' then 2 when 'Asignación Familiar' then 3 when 'Créditos' then 4 when 'Leasing' then 5 when 'Convenios Dentales' then 6 when 'Seguros de Vida' then 7 when 'Otros Caja' then 8 END END as orden_ccaf, TRIM(PWDCCONCE) as Concep, SUM(PWDCMONPEN) as Pension, SUM(PWDCMONAHO) as Ahorro, SUM(PWDCMONSIS) as SIS, SUM(PWDCMONCES) as AFC, SUM(PWDCMONTP) as TP, SUM(PWDCMONTO) as TOTAL, SUM(PWDCNUMTR) as Trabajadores FROM pwdtad.pwf5100 WHERE pwdcnumco= "+request.getParameter("code")+" GROUP BY PWDCTIPEM, PWDCNOMEM, PWDCCONCE ORDER BY orden_seccion, orden_inp, orden_ccaf, pwdcnomem");
			result2.last();
			int numeroFilas = result2.getRow();
			result2.beforeFirst();
			
			params.put("numeroFilas", String.valueOf(numeroFilas));
			
			List list = new ArrayList();
			List ccaf = new ArrayList();
			String detailMutual = "";
			String tfija="",tadicional="";
			String nombreMutual = "";
			String ccafNomTotal = "";
			Object valueCcafCFIoCFE = null;
			String ccafCFIoCFE = "";
			Integer ccafTR = null;
			Object valueSfiCFIoCFE = null;
			String SfiCFIoCFE = "";
			Integer ipsTR = null;
			
			if(result2 != null && numeroFilas > 0)
			{ 
				for(;result2.next();)
				{
					String trabajdores = result2.getString("Trabajadores") == null ? "0":result2.getString("Trabajadores");
					String total = result2.getObject("TOTAL") == null ? "0":result2.getObject("TOTAL").toString();
	
					if(result2.getString("Entidad").trim().equals("AFP")){
						//clillo 28/10/14 Se agrega TP a la pensionyahorro
						//sumamos pensión + ahorro + tp
						int pensionyahorro = Integer.parseInt(result2.getObject("Pension").toString())+Integer.parseInt(result2.getObject("Ahorro").toString()) +Integer.parseInt(result2.getObject("TP").toString());

						list.add(new DTOcomprobante(
									result2.getString("Nombre"),
									result2.getString("Entidad"),
									formatNum(String.valueOf(pensionyahorro)),
									formatNum(result2.getObject("SIS").toString()),
									formatNum(result2.getObject("AFC").toString()),
									formatNum(total),
									Integer.valueOf(trabajdores)
									)
								);
					}else{
						if(result2.getString("Entidad").trim().equals("MUTUAL")){
							if(result2.getString("Concep").trim().equals("Tasa Fija")){
								float t = (float)Integer.parseInt(total) / 100;
	
								tfija = String.valueOf(t);
							}else if(result2.getString("Concep").trim().equals("Tasa Adicional")){ 
								float t = (float)Integer.parseInt(total) / 100;
	
								tadicional = String.valueOf(t);
							}
							
							nombreMutual = result2.getString("Nombre").trim();
						}else if(result2.getString("Entidad").trim().equals("CCAF")){
							if(!result2.getString("Concep").trim().equals("S.F.E.") || (result2.getString("Concep").trim().equals("S.F.E.") && !total.equals("0")))
							{
								ccaf.add(new DTOcomprobante(
									result2.getString("Concep"),
									result2.getString("Entidad"),
									"",
									"",
									"",
									formatNum(total),
									Integer.valueOf(trabajdores)
									)
								);
							}
	
							ccafNomTotal = result2.getString("Nombre").trim();
						}else if(result2.getString("Entidad").trim().equals("IPS")){
							if(!result2.getString("Concep").trim().equals("S.F.E.") || (result2.getString("Concep").trim().equals("S.F.E.") && !total.equals("0")))
							{
								list.add(new DTOcomprobante(
										result2.getString("Concep"),
										result2.getString("Entidad"),
										"",
										"",
										"",
										formatNum(total),
										Integer.valueOf(trabajdores)
										)
									);
							}
						}else{
							list.add(new DTOcomprobante(
									result2.getString("Nombre"),
									result2.getString("Entidad"),
									"",
									"",
									"",
									formatNum(total),
									Integer.valueOf(trabajdores)
									)
								);
						}
					}
				}
				
				result2.close();
	
				if(!tfija.equals("") && !tadicional.equals("")){
					detailMutual = "Tasa Fija "+tfija+" Tasa Adicional "+tadicional;
					list.add(new DTOcomprobante(detailMutual,"MUTUAL","","","","-1",new Integer(0)));
				}
				
				Iterator iccaf = ccaf.iterator();
				for(;iccaf.hasNext();){
					list.add(iccaf.next());
				}
	
				//obtenemos los totales de mutual
				String totalMutual=null;
				String totalTrabajadoresMutual=null;
				
				//queryProperty = p.getProperty("mutual");
				//ResultSet mutual = stmt.executeQuery(queryProperty.replaceAll("\\{code\\}", request.getParameter("code")));
				ResultSet mutual = stmt.executeQuery("SELECT SUM(a.PWTCMONTO) AS TOTAL, SUM(a.PWTCNUMTR) AS TRABAJADORES FROM PWDTAD.PWF5200 a WHERE PWTCNUMCO = "+request.getParameter("code")+" AND TRIM(PWTCTIPEM) = 'MUTUAL'");
				
				while(mutual.next()){
					totalMutual = mutual.getObject("TOTAL") != null ? mutual.getObject("TOTAL").toString():null;
					totalTrabajadoresMutual = mutual.getObject("TRABAJADORES") != null ? mutual.getObject("TRABAJADORES").toString():null;
				}
				
				//obtenemos el SFI de CCAF y IPS
				//queryProperty = p.getProperty("sfi");
				//ResultSet sfi = stmt.executeQuery(queryProperty.replaceAll("\\{code\\}", request.getParameter("code")));
				ResultSet sfi = stmt.executeQuery("select PWTCTIPEM as TIPO, PWTCMONTO as MONTO, PWTCNUMTR as TR from pwdtad.pwf5200 where pwtcnumco = "+request.getParameter("code")+" and (PWTCTIPEM='CCAF' or PWTCTIPEM='IPS')");
				System.out.println("select PWTCTIPEM as TIPO, PWTCMONTO as MONTO, PWTCNUMTR as TR from pwdtad.pwf5200 where pwtcnumco = "+request.getParameter("code")+" and (PWTCTIPEM='CCAF' or PWTCTIPEM='IPS')");
				while(sfi.next()){
					if(sfi.getObject("TIPO").toString().trim().equals("CCAF")){
						valueCcafCFIoCFE=formatNum(sfi.getObject("MONTO").toString());
						ccafCFIoCFE="(SFI)";
						ccafTR = sfi.getObject("TR") != null ? Integer.valueOf(sfi.getObject("TR").toString()):null;
					}else if(sfi.getObject("TIPO").toString().trim().equals("IPS")){
						valueSfiCFIoCFE=formatNum(sfi.getObject("MONTO").toString());
						SfiCFIoCFE="(SFI)";
						ipsTR = sfi.getObject("TR") != null ? Integer.valueOf(sfi.getObject("TR").toString()):null;
					}
				}
				/********************************************************************/
				Collections.sort(list);
				
				//ahora que tenemos ordenado el arreglo por entidad, calculamos los totales
				Collection listaTotales = new ArrayList();
				String entidad=null;
				Integer totalPension=new Integer(0);
				Integer totalSIS=new Integer(0);
				Integer totalAFC=new Integer(0);
				Integer totalTrabajadores=new Integer(0);
				Integer total=new Integer(0);
				Iterator it = list.iterator();
				
				/**********************ORDENAMIENTO DE LAS SECCIONES*******************************/
				List lafp,lisapre,lips,lmutual,lccaf,lapv;
				lafp=new ArrayList();
				lisapre=new ArrayList();
				lips=new ArrayList();
				lmutual=new ArrayList();
				lccaf=new ArrayList();
				lapv = new ArrayList();
				
				for(;it.hasNext();){
					String value = it.next().toString();
					String[] sp = value.split(",");

					if(sp[1].trim().equals("AFP")){
						lafp.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}else if(sp[1].trim().equals("ISAPRE")){
						lisapre.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}else if(sp[1].trim().equals("IPS")){
						lips.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}else if(sp[1].trim().equals("MUTUAL")){
						lmutual.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}else if(sp[1].trim().equals("CCAF")){
						lccaf.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}else if(sp[1].trim().equals("APV")){
						lapv.add(new DTOcomprobante(sp[0],sp[1],sp[2],sp[3],sp[4],sp[5],Integer.valueOf(sp[6])));
					}
				}
				
				List temp = new ArrayList();
				
				for(Iterator i=lafp.iterator();i.hasNext();)
					temp.add(i.next());
				for(Iterator i=lisapre.iterator();i.hasNext();)
					temp.add(i.next());
				for(Iterator i=lips.iterator();i.hasNext();)
					temp.add(i.next());
				for(Iterator i=lmutual.iterator();i.hasNext();)
					temp.add(i.next());
				for(Iterator i=lccaf.iterator();i.hasNext();)
					temp.add(i.next());
				for(Iterator i=lapv.iterator();i.hasNext();)
					temp.add(i.next());
				
				list = temp;
				/******************************************************************/
	
				Iterator val = list.iterator();
				
				for(;val.hasNext();)
				{
					String value = val.next().toString();
						
					if(entidad!= null && !entidad.equals(value.split(",")[1]))
					{
						if(entidad.trim().equals("AFP"))
							listaTotales.add(new DTOcomprobante("",entidad,formatNum(String.valueOf(totalPension)),formatNum(String.valueOf(totalSIS)),formatNum(String.valueOf(totalAFC)),formatNum(String.valueOf(total)),totalTrabajadores,true));
						else if(entidad.trim().equals("CCAF")){
							if(valueCcafCFIoCFE.toString().trim().equals("0")) ccafCFIoCFE="";
							listaTotales.add(new DTOcomprobante(ccafNomTotal,entidad,"","","",ccafCFIoCFE+"  "+valueCcafCFIoCFE.toString(),ccafTR,true));
						}else if(entidad.trim().equals("MUTUAL"))
							listaTotales.add(new DTOcomprobante(nombreMutual,entidad,"","","",(totalMutual != null ? formatNum(totalMutual):""),(totalTrabajadoresMutual != null ? Integer.valueOf(totalTrabajadoresMutual):new Integer(0)),true));
						else if(entidad.trim().equals("IPS")){
							if(valueSfiCFIoCFE.toString().trim().equals("0")) SfiCFIoCFE="";
							listaTotales.add(new DTOcomprobante("",entidad,"","","",SfiCFIoCFE+"  "+valueSfiCFIoCFE.toString(),ipsTR,true));
						}else
							listaTotales.add(new DTOcomprobante("",entidad,"","","",formatNum(total.toString()),totalTrabajadores,true));
						
						totalPension = totalSIS = totalAFC = totalTrabajadores = total = new Integer(0);
					}
	
					entidad = value.split(",")[1];
	
					if(!value.split(",")[2].equals("")){
						totalPension = new Integer(totalPension.intValue()+Integer.parseInt(value.split(",")[2].replaceAll("[\\.]", "")));
					}if(!value.split(",")[3].equals("")){
						totalSIS = new Integer(totalSIS.intValue()+Integer.parseInt(value.split(",")[3].replaceAll("[\\.]", "")));
					}if(!value.split(",")[4].equals("")){
						totalAFC = new Integer(totalAFC.intValue()+Integer.parseInt(value.split(",")[4].replaceAll("[\\.]", "")));
					}
	
					totalTrabajadores = new Integer(totalTrabajadores.intValue()+Integer.parseInt(value.split(",")[6]));
					total = new Integer(total.intValue()+Integer.parseInt(value.split(",")[5].replaceAll("[\\.]", "")));
				}
				
				//con esto imprimimos la última linea que muestra el total, esto es una copia del código del if que está dentro del for anterior
				if(entidad.trim().equals("AFP"))
					listaTotales.add(new DTOcomprobante("",entidad,formatNum(String.valueOf(totalPension)),formatNum(String.valueOf(totalSIS)),formatNum(String.valueOf(totalAFC)),formatNum(String.valueOf(total)),totalTrabajadores,true));
				else if(entidad.trim().equals("CCAF")){
					if(valueCcafCFIoCFE.toString().trim().equals("0")) ccafCFIoCFE="";
					listaTotales.add(new DTOcomprobante(ccafNomTotal,entidad,"","","",ccafCFIoCFE+"  "+valueCcafCFIoCFE.toString(),ccafTR,true));
				}else if(entidad.trim().equals("MUTUAL"))
					listaTotales.add(new DTOcomprobante(nombreMutual,entidad,"","","",(totalMutual != null ? formatNum(totalMutual):""),(totalTrabajadoresMutual != null ? Integer.valueOf(totalTrabajadoresMutual):new Integer(0)),true));
				else if(entidad.trim().equals("IPS")){
					if(valueSfiCFIoCFE.toString().trim().equals("0")) SfiCFIoCFE="";
					listaTotales.add(new DTOcomprobante("",entidad,"","","",SfiCFIoCFE+"  "+valueSfiCFIoCFE.toString(),ipsTR,true));
				}else
					listaTotales.add(new DTOcomprobante("",entidad,"","","",formatNum(total.toString()),totalTrabajadores,true));
				
				totalPension = totalSIS = totalAFC = totalTrabajadores = total = new Integer(0);
		
				//llenamos la lista final que será presentada en el PDF, con este bucle
				//le agregamos a la lista final de la primera lista y lo de la listaTotales
				entidad = "";
				Integer cont=new Integer(0);
				Integer limit=new Integer(0);
				Iterator itTotales = listaTotales.iterator();
				Iterator val2 = list.iterator();
				
				for(;val2.hasNext();)
				{
					Object value = val2.next();
	
					if(!entidad.equals(value.toString().split(",")[1]))
					{
						for(;itTotales.hasNext();)
						{
							if(cont.intValue() == limit.intValue()){
								listaFinal.add(itTotales.next());
								limit=new Integer(limit.intValue()+1);
								break;
							}
							cont=new Integer(cont.intValue()+1);
						}
						cont=new Integer(0);
					}
					listaFinal.add(value);
					entidad = value.toString().split(",")[1];
				}
			}
			
			//indicamos el final del registro
			listaFinal.add(new DTOcomprobante("lastrow",null,null,null,null,null,null));

			dataSource = new JRBeanCollectionDataSource(listaFinal);

			//String jReport = JasperCompileManager.compileReportToFile("/Publicacion/PDFJasper/comprobantePago.jrxml");	
			System.out.println("COMPILADO");
	
			JasperPrint jPrint = JasperFillManager.fillReport("/Publicacion/PDFJasper/comprobantePago.jasper", params, dataSource);
			//JasperPrint jPrint = JasperFillManager.fillReport(jReport, params, dataSource);
			System.out.println("FILL REPORT");
			
			response.addHeader("Content-Type", "application/pdf");
			response.setHeader("Content-Disposition","inline; filename=\"ComprobantePago.pdf\""); 
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "No-cache"); 
			
			JasperExportManager.exportReportToPdfStream(jPrint, response.getOutputStream());

			System.out.println("DONE");
		} catch (Exception e) {
			System.out.println("Error en ComprobantePagoPDF");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOGET");
		processRequest(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DOPOST");
		processRequest(request, response);
	}   	  	  
	
	
	public Collection getData()
	{
		Collection list = new ArrayList();
		return list;
	}
	
    public Object formatNum(String number)
    {
		//NumberFormat formateador = DecimalFormat.getCurrencyInstance(new Locale("fr"));
    	Locale.setDefault(new Locale("es","CL"));
		//DecimalFormat formateador = new DecimalFormat("###,###.##", new DecimalFormatSymbols(new Locale("es","CL")));
    	DecimalFormat formateador = new DecimalFormat("###,###.##");
    	
		return formateador.format(Long.parseLong(number));
    }
    
    public String formatDate(String date)
    {
    	return date.substring(date.length()-2, date.length())+"/"+date.substring(4,6)+"/"+date.substring(0, 4);
    }
}