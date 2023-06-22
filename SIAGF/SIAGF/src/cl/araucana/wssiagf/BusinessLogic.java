package cl.araucana.wssiagf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.araucana.core.business.BusinessException;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.araucana.utils.Cache;
import cl.araucana.wssiagf.vo.RangoTramos;
import cl.araucana.wssiagf.vo.Tramo;

public class BusinessLogic {
	private static String JNDI_DS; //debiese ser final
	private static String tramosHistoricos;
	private static String tramosHistoricosAfiliado;
	private static String rentaHistoricaAfiliado;
	private static String rentaHistoricaAfiliado2;
	private static long valorMaximoRenta;
	private static int maxTramosRetroactivos;
	private static int numTramoDefault;
	
	private static Logger logger = LogManager.getLogger();
	public static final String PROPERTIES_FILE = "/app/etc/wssiagf.properties";
	private DataSource dataSource;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Cache cache;
	private static String tramosKey = "tramos";
	private static String valoresTramosDefault = "valoresTramoDefault";
	private static Date fIniTramoVig = null;
	
	static{
		//inicialización de código Estático.
		cache = new Cache();
		try {
			//parametros de configuración
			BusinessLogicConfig blC = new BusinessLogicConfig(PROPERTIES_FILE);
			JNDI_DS = blC.getDSJNDIName();
			tramosHistoricos = blC.getTramosHistoricos();
			tramosHistoricosAfiliado = blC.getTramosHistoricosAfiliado();
			rentaHistoricaAfiliado = blC.getRentaHistoricaAfiliado();
			rentaHistoricaAfiliado2 = blC.getRentaHistoricaAfiliado2();
			valorMaximoRenta = blC.getValorMaximoRenta();
			maxTramosRetroactivos = blC.getMaxTramosRetroactivos();
			numTramoDefault = blC.getNumTramoDefault();
			//Cache
			List tramos = getTramosHistoricos();	
			cache.put(tramosKey,tramos);
			List valoresTramoDefault = getValoresDefaultTramosHistoricos(numTramoDefault);
			cache.put(valoresTramosDefault, valoresTramoDefault);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch(WSSIAGFException e){
			e.printStackTrace();
		}

		List tramos = (List) cache.get(tramosKey);

		if(tramos.size() > 0){
			RangoTramos t = (RangoTramos) tramos.get(0);
			fIniTramoVig = t.getFechaInicio();
		}
	}
	
	public BusinessLogic() {
		this.dataSource = getDataSource(JNDI_DS);	
	}


	
	private static List getValoresDefaultTramosHistoricos(int numTramoDefault) throws BusinessException{
		logger.info(">> getValoresDefaultTramosHistoricos");
		ResultSet rs = null;
		List tramos = new ArrayList();
		
		Connection connection = null;
		DataSource ds = null;
		InitialContext ic = null;
		Statement getTramosHistoricosStmt = null;
		try{
			ic = new InitialContext();
			ds = (DataSource)ic.lookup(JNDI_DS); 
			connection = ds.getConnection();
	

			getTramosHistoricosStmt = connection.createStatement();
			String query = "SELECT DISTINCT AF2HA tramo, AF2IA rentaDesde , AF2KA montoTramo, "+
						   "af2ga fechaInicio, afda fechaTermino, "+  
						   "substring(CHAR(af2ga,  ISO), 0, 5) anio "+ 
						   "FROM "+tramosHistoricos+" where af2ha = "+numTramoDefault+" "+
						   "ORDER BY afda desc";
			
			rs = getTramosHistoricosStmt.executeQuery(query);
			logger.finest(query);
			
			while(rs.next()){
					Tramo tramo = new Tramo();
					tramo.setNumTramo(rs.getInt(1));
					tramo.setRentaDesde(rs.getLong(2));
					tramo.setMontoUnitarioBeneficio(rs.getInt(3));
					tramo.setFechaInicio(rs.getDate(4));
					tramo.setFechaTermino(rs.getDate(5));
					tramo.setAño(rs.getInt(6));
					tramos.add(tramo);
			}
			
		}catch(NamingException e){
			logger.log(
					Level.SEVERE, ">< connection to data source failed:", e);
			throw new BusinessException(e);
		}
		catch(SQLException e){
			logger.log(
					Level.SEVERE, ">< connection to data source failed:", e);
			throw new BusinessException(e);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		finally{
			try{
				if(ic != null){
					ic.close();
				}
			}catch(NamingException e){}

			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			if(getTramosHistoricosStmt != null){
				try{
					getTramosHistoricosStmt.close();
				}catch (SQLException e) {}
			}
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException e) {}
			}
		}
		
		logger.info("<< getValoresDefaultTramosHistoricos");
		return tramos;
	}
	
	private static List getTramosHistoricos() throws BusinessException {
		logger.info(">> getTramosHistoricos");
		ResultSet rs = null;
		List tramos = new ArrayList();
		
		Connection connection = null;
		DataSource ds = null;
		InitialContext ic = null;
		Statement getTramosHistoricosStmt = null;
		try{
			ic = new InitialContext();
			//jdbcName = (DataSource)ic.lookup("java:comp/env/"+paramName);
			ds = (DataSource)ic.lookup(JNDI_DS); 
			connection = ds.getConnection();
	

			getTramosHistoricosStmt = connection.createStatement();
			String query = "SELECT DISTINCT af2ga fechaInicio, afda fechaTermino, substring(CHAR(af2ga,  ISO), 0, 5) anio \n" +
						   "FROM "+tramosHistoricos+" ORDER BY afda desc";
			
			rs = getTramosHistoricosStmt.executeQuery(query);
			logger.finest(query);
			
			while(rs.next()){
					RangoTramos tramo = new RangoTramos();
					tramo.setFechaInicio(rs.getDate(1));
					tramo.setFechaTermino(rs.getDate(2));
					tramo.setAño(rs.getInt(3));
					tramos.add(tramo);
			}
			
		}catch(NamingException e){
			logger.log(
					Level.SEVERE, ">< connection to data source failed:", e);
			throw new BusinessException(e);
		}
		catch(SQLException e){
			logger.log(
					Level.SEVERE, ">< connection to data source failed:", e);
			throw new BusinessException(e);
		} 
		finally{
			try{
				if(ic != null){
					ic.close();
				}
			}catch(NamingException e){}

			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			if(getTramosHistoricosStmt != null){
				try{
					getTramosHistoricosStmt.close();
				}catch (SQLException e) {}
			}
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException e) {}
			}
		}
		logger.info("<< getTramosHistoricos");
		return tramos;
	}
	
	private List getTramosByAno(int ano, int rutAfil) {
		logger.info(">> getTramosByAno");
		ResultSet rs = null;
		List fecIniTramos = new ArrayList();
		
		Connection connection = null;
		DataSource ds = null;
		InitialContext ic = null;
		Statement getTramosByAnoStmt = null;
		try{
			ic = new InitialContext();
			//jdbcName = (DataSource)ic.lookup("java:comp/env/"+paramName);
			ds = (DataSource)ic.lookup(JNDI_DS); 
			connection = ds.getConnection();
			getTramosByAnoStmt = connection.createStatement();

			
			String query = "select t1.af2ga " +
				"from "+tramosHistoricos+" t1 " +
				//"inner join "+tramosHistoricosAfiliado+" t2 on t1.af2ga = t2.af2ga and t1.af2ha = t2.af2ha " +
				"where substr(char(t1.af2ga, ISO), 1, 4) = '" + ano + "' " +
				"and t1.af2ha = 1 " +
				//"and se5fajc = " + rutAfil + " " +
				"order by t1.af2ga asc";
			
			rs = getTramosByAnoStmt.executeQuery(query);
			logger.finest(query);
			
			while(rs.next()){
				fecIniTramos.add(rs.getDate(1));
			}
		}catch(NamingException e){
			logger.log(Level.SEVERE, ">< connection to data source failed:", e);
		}
		catch(SQLException e){
			logger.log(Level.SEVERE, ">< connection to data source failed:", e);
		} 
		finally{
			try{
				if(ic != null){
					ic.close();
				}
			}catch(NamingException e){}

			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			if(getTramosByAnoStmt != null){
				try{
					getTramosByAnoStmt.close();
				}catch (SQLException e) {}
			}
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException e) {}
			}
		}
		logger.info("<< getTramosByAno");
		return fecIniTramos;
	}
	
	private String getFechaTramosHistoricosCorrespondientes(String fechaInicioRango, String fechaTerminoRango){
		logger.info(">>getFechaTramosHistoricosCorrespondientes A" );
		StringBuffer r = new StringBuffer("");
		try {
			Date fInicioRango = sdf.parse(fechaInicioRango);
			Date fTerminoRango = sdf.parse(fechaTerminoRango);
			
			List listaTramosValidos = getFechaTramosHistoricosCorrespondientes(fInicioRango, fTerminoRango); 
			
			int cont = 0;
			for(Iterator i = listaTramosValidos.iterator(); i.hasNext(); cont++){
				Date d = (Date) i.next();
				
				if(cont == 0) r.append("'"+sdf.format(d)+"'");
				else r.append(" ,'"+sdf.format(d)+"'");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.info("<<getFechaTramosHistoricosCorrespondientes A " );
		return r.toString();
	}
	
	private Tramo getTramoDefault(int año){
		List tramos = (List) cache.get(valoresTramosDefault);
		Tramo tramo = null;
		
		for(Iterator i = tramos.iterator(); i.hasNext(); ){
			Tramo t = (Tramo) i.next();
			if(t.getAño() == año){
				tramo = t;
				break;
			}
		}
		
		return tramo;
	}
	
	private Tramo getTramoDefault(Date fecIniTramo){
		List tramos = (List) cache.get(valoresTramosDefault);
		Tramo tramo = null;
		
		for(Iterator i = tramos.iterator(); i.hasNext(); ){
			Tramo t = (Tramo) i.next();
			if(sdf.format(t.getFechaInicio()).equals(sdf.format(fecIniTramo))){
				tramo = t;
				break;
			}
		}
		
		return tramo;
	}
	
	private List getFechaTramosHistoricosCorrespondientes(Date fInicioRango, Date fTerminoRango){
		logger.info(">>getFechaTramosHistoricosCorrespondientes B" );
		List listaTramosValidos = new ArrayList();
	
		List tramos = (List) cache.get(tramosKey);

		AbsoluteDate absFInicioRango = new AbsoluteDate(fInicioRango);
		AbsoluteDate absFTerminoRango = new AbsoluteDate(fTerminoRango);
		AbsoluteDate absFInicioTramoVig = new AbsoluteDate(fIniTramoVig);
		boolean vigente = true;
		int cantTramosProcesados = 0;
		if(absFInicioRango.compareTo(absFInicioTramoVig) < 0){
			//Solo se calculan si la fecha de inicio de tramo corresponde a uno retroactivo.
			for(Iterator i = tramos.iterator(); i.hasNext(); ){
				if(vigente){
					i.next();
					vigente = false;
					continue;
				}
				
				cantTramosProcesados++;
				RangoTramos t = (RangoTramos) i.next();
				AbsoluteDate absFInicioTramo = new AbsoluteDate(t.getFechaInicio());
				AbsoluteDate absFTerminoTramo = new AbsoluteDate(t.getFechaTermino());
				
				if(absFInicioTramo.compareTo(absFInicioRango) <= 0 ||
				   cantTramosProcesados >= maxTramosRetroactivos)
				{
					listaTramosValidos.add(t.getFechaInicio());
					break;
				} 
				else
					if(absFInicioTramo.compareTo(absFTerminoRango) <= 0)
						listaTramosValidos.add(t.getFechaInicio());
				/**
				if(!t.getFechaInicio().after(fInicioRango)){
					listaTramosValidos.add(tramoAnterior);
					break;
				}
				else
					if(!t.getFechaInicio().after(fTerminoRango))
						listaTramosValidos.add(t.getFechaInicio());*/
			}
		}
		logger.info("<<getFechaTramosHistoricosCorrespondientes B" );
		
		return listaTramosValidos;
	}
	
	private Connection openConnection() throws SQLException {
		return openConnection(true);
	}

	private DataSource getDataSource(String paramName){
		DataSource jdbcName = null;
		InitialContext ic = null;
		try{
			ic = new InitialContext();
			//jdbcName = (DataSource)ic.lookup("java:comp/env/"+paramName);
			jdbcName = (DataSource)ic.lookup(paramName); 
		}catch(NamingException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(ic != null){
					ic.close();
				}
			}catch(NamingException e){}
		}
		return jdbcName;
	}
	
	private Connection openConnection(boolean logged) throws SQLException {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			if (logged) {
				logger.log(
						Level.SEVERE, ">< connection to data source failed:", e);
				throw e;
			}
		}
		return connection;
	}


	private List getTramosRetroactivos(String rutAfiliado, String fechaInicio, String fechaTermino) throws BusinessException {
		logger.info(">>getTramosRetroactivos");
		ResultSet rs = null;
		List tramosRetroactivos = new ArrayList();

		
		long t1 = System.currentTimeMillis();
		
		Connection connection = null;
		Statement getTramoHistoricoAfiliadoStmt = null;

		String fechaInicioTramos = getFechaTramosHistoricosCorrespondientes(fechaInicio,fechaTermino);
		if(fechaInicioTramos.length() == 0)
			return tramosRetroactivos;
		
		try{
			connection = openConnection();
			
			getTramoHistoricoAfiliadoStmt = connection.createStatement();
			String query = "SELECT \n"+
					      "AF2GA fechaInicioTramo, \n"+
					      "AF2HA tramo, \n"+
					      "COALESCE( \n" +
					      "			( \n" +
					      "				SELECT AF27A renta \n" +
					      "				FROM "+rentaHistoricaAfiliado+" \n" +
					      "				WHERE SE5FAJC = tramoH.SE5FAJC and \n" +
					      "				AF2GA = tramoH.AF2GA \n"+	
					      "			), \n"+
						  "			( \n" +
						  "				SELECT avg(RENTAS) renta \n"+
						  "				FROM "+rentaHistoricaAfiliado2+"  \n"+
						  "				WHERE AFIRUT ="+rutAfiliado+" and  \n"+ //OBS: antes decia 'AFIRUT =tramoH.SE5FAJC', esto aumentaba considerablemente el tiempo de ejecución de la Query. (No se investigó en profundidad, favor considerar) 
						  "				PERIODO between \n"+
						  "				substr(char(tramoH.AF2GA, ISO), 1, 4) || '01' \n"+ 
						  "				and \n"+
						  "				substr(char(tramoH.AF2GA, ISO), 1, 4) || '06'\n"+
						  "			 ),\n"+ //Donde periodo es un entero con formato MMMMDD
						  "			(\n"+
						  "				SELECT AF2JA renta \n"+
						  "				FROM "+tramosHistoricos+" \n"+
						  "				WHERE AF2GA = tramoH.AF2GA \n"+
						  "				and AF2HA = tramoH.AF2HA\n" +
						  "			), \n"+
						  "		  -1) as ingresoPromedio, \n"+
						  "COALESCE( \n" +
						  "			( \n" +
						  "			 SELECT AF2KA montoUnitarioBeneficio \n" +
						  "			 FROM "+tramosHistoricos+" \n" +
						  "			 WHERE AF2HA = tramoH.AF2HA and \n" +
						  "			 AF2GA <= tramoH.AF2GA \n" +
						  "			 and AFDA >= tramoH.AF2GA \n" +
						  "			),\n" +
						  "			-1 \n" +
						  "		   ) as montoUnitarioBeneficio, \n" +
						  "			substr(char(tramoH.AF2GA, ISO), 1, 4) anio \n"+		
					      "FROM "+tramosHistoricosAfiliado+" tramoH \n"+ 
					      "WHERE SE5FAJC = "+rutAfiliado+" and \n"+
						  //"AF2GA between '"+fechaInicio+"' and '"+fechaTermino+"' \n"+
					      "AF2GA in ("+fechaInicioTramos+") \n"+
						  "ORDER BY tramoH.AF2GA DESC \n"+
						  "FETCH FIRST "+maxTramosRetroactivos+" ROWS ONLY \n";
			
			rs = getTramoHistoricoAfiliadoStmt.executeQuery(query);
			logger.info(query);
			
			//Este SQL trae solo los años de los tramos retroactivos.
			
			while(rs.next()){
				TramoBusinessTO t = new TramoBusinessTO();
				t.setFechaInicioTramo(rs.getDate(1));
				t.setTramo(rs.getInt(2));
				t.setIngresoPromedio(rs.getInt(3));
				t.setMontoUnitario(rs.getInt(4));
				t.setYear(rs.getInt(5));
				tramosRetroactivos.add(t);
				
				//Ajuste debido a máximo valor en SIAGF.
				if(t.getIngresoPromedio() > valorMaximoRenta){
					t.setIngresoPromedio(valorMaximoRenta);
				}
			}
			
		} catch (SQLException e) {
			throw new BusinessException(e);
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			if(getTramoHistoricoAfiliadoStmt != null){
				try{
					getTramoHistoricoAfiliadoStmt.close();
				}catch (SQLException e) {}
			}
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException e) {}
			}
		}
		
		long t2 = System.currentTimeMillis() - t1;
		logger.info("total time: "+t2);
		logger.info("<<getTramosRetroactivos");
		return tramosRetroactivos;
	}
	
	private String getFechaReconocimiento(String xmlData){
		int index = xmlData.lastIndexOf("<FecRecCausante>") + 16;
		int offset = xmlData.lastIndexOf("</FecRecCausante>");
		
		return xmlData.substring(index, offset);	
	}
	
	/**
	 * Return FechaExticion Parameter value or ProcessDate, if the first doesn't exists.
	 * @param xmlData
	 * @return
	 */
	private String getFechaExtincion(String xmlData){
		int index = xmlData.lastIndexOf("<FecExtCausante>");

		if(index > -1){
			index += 16;
			int offset = xmlData.lastIndexOf("</FecExtCausante>");
			return xmlData.substring(index, offset);
		}
		else{
			//Si no existe fecExtCausante, se envia fecha de proceso.
			Calendar cal = new GregorianCalendar();
			return sdf.format(cal.getTime());
		}
		
		
	}
	
	/**
	 * Return the RUT Identifier without verifier digit.
	 * @param xmlData
	 * @return
	 */
	private String getRutAfiliado(String xmlData){
		int index = xmlData.lastIndexOf("<RutBeneficiario>") + 17;
		int offset = xmlData.lastIndexOf("</RutBeneficiario>");
		
		String rutCompleto = xmlData.substring(index, offset);
		String[] rutArray = rutCompleto.split("-");
		return 	rutArray[0];
	}
	
	public String generateXMLTramosRetroactivos(List tramosRetroactivos){
		StringBuffer tramosXml = new StringBuffer("");
		if(tramosRetroactivos != null && tramosRetroactivos.size() > 0){
			tramosXml.append("\n\t<Tramos>\n");

			
			for(Iterator i = tramosRetroactivos.iterator(); i.hasNext(); ){
				tramosXml.append("\t\t<Tramo>\n");

				TramoBusinessTO t = (TramoBusinessTO) i.next();
				
				tramosXml.append("\t\t\t<Periodo>"+t.getYear()+"</Periodo>\n");
				tramosXml.append("\t\t\t<NumTramo>"+t.getTramo()+"</NumTramo>\n");
				tramosXml.append("\t\t\t<IngPromedio>"+t.getIngresoPromedio()+"</IngPromedio>\n");
				tramosXml.append("\t\t\t<MontoUnitarioBeneficio>"+t.getMontoUnitario()+"</MontoUnitarioBeneficio>\n");
				tramosXml.append("\t\t</Tramo>\n");
			}
			tramosXml.append("\t</Tramos>\n");
		}
		return tramosXml.toString();
	}
	
	public String generateXMLTramosRetroactivosNew(List tramosRetroactivos, int rutAfil){
		StringBuffer tramosXml = new StringBuffer("");
		if(tramosRetroactivos != null && tramosRetroactivos.size() > 0){
			tramosXml.append("\n\t<Tramos>\n");

			
			for(Iterator i = tramosRetroactivos.iterator(); i.hasNext(); ){
				tramosXml.append("\t\t<Tramo>\n");

				TramoBusinessTO t = (TramoBusinessTO) i.next();
				
				List listTramosRepet = getTramosByAno(t.getYear(), rutAfil);
				if(listTramosRepet.size() > 1){
					int j = 1;
					for(Iterator it2 = listTramosRepet.iterator(); it2.hasNext();){
						Date fecIniTramo = (Date) it2.next();
						
						if(sdf.format(fecIniTramo).equals(sdf.format(t.getFechaInicioTramo()))){
							String indexPeriodo = "0" + j;
							tramosXml.append("\t\t\t<Periodo>"+t.getYear()+ indexPeriodo + "</Periodo>\n");
							break;
						}
						j++;
					}
				}else{
					tramosXml.append("\t\t\t<Periodo>"+t.getYear()+"</Periodo>\n");
				}
				tramosXml.append("\t\t\t<NumTramo>"+t.getTramo()+"</NumTramo>\n");
				tramosXml.append("\t\t\t<IngPromedio>"+t.getIngresoPromedio()+"</IngPromedio>\n");
				tramosXml.append("\t\t\t<MontoUnitarioBeneficio>"+t.getMontoUnitario()+"</MontoUnitarioBeneficio>\n");
				tramosXml.append("\t\t</Tramo>\n");
			}
			tramosXml.append("\t</Tramos>\n");
		}
		return tramosXml.toString();
	}
	
	public String addTramosRetroactivos_IngresoReconocimientoToXML(String xmlData, List tramosRetroactivos){
		int index = xmlData.lastIndexOf("</TramoAsigFam>") + 15;//Ultimo tag obligatorio antes de la estructura de tramos retroactivos
		StringBuffer sb = new StringBuffer(xmlData);
		
		//Se buscan los tag opcionales que vienen despues de tramo Asig Fam.
		int indexAux = xmlData.lastIndexOf("</FecExtCausante>");
		if(indexAux > 0) index = indexAux + 17;
		
		indexAux = xmlData.lastIndexOf("</CausaExtCausante>");
		if(indexAux > 0) index = indexAux + 19;
		
//		String tramosXml = generateXMLTramosRetroactivos(tramosRetroactivos);
		
		String rutAfilStr = getRutAfiliado(xmlData);
		int rutAfil = Integer.parseInt(rutAfilStr.split("-")[0]);
		String tramosXml = generateXMLTramosRetroactivosNew(tramosRetroactivos, rutAfil);
		

		sb.insert(index, tramosXml);
		return sb.toString();
	}
	
	public String addTramosRetroactivos_ActualizarCausanteToXML(String xmlData, List tramosRetroactivos){
		int index = 0;
		StringBuffer sb = new StringBuffer(xmlData);
		
		//Se buscan los tag opcionales que pueden venir a modificar
		int indexAux = xmlData.lastIndexOf("</IngPromedio>");
		if(indexAux > 0) index = indexAux + 14;
		
		indexAux = xmlData.lastIndexOf("</MontoUnitarioBeneficio>");
		if(indexAux > 0) index = indexAux + 25;
		
		indexAux = xmlData.lastIndexOf("</TramoAsigFam>");
		if(indexAux > 0) index = indexAux + 15;
		
		indexAux = xmlData.lastIndexOf("</FecPagoBeneficio>");
		if(indexAux > 0) index = indexAux + 19;
		
//		String tramosXml = generateXMLTramosRetroactivos(tramosRetroactivos);
		
		String rutAfilStr = getRutAfiliado(xmlData);
		int rutAfil = Integer.parseInt(rutAfilStr.split("-")[0]);
		String tramosXml = generateXMLTramosRetroactivosNew(tramosRetroactivos, rutAfil);

		sb.insert(index, tramosXml);
		return sb.toString();
	}
	public String addTramosRetroactivos_ingresoReconocimiento(String xmlData) {
		String xmlProcesado = xmlData;//Asigna a variable de retorno el XML original (cuando no puede informar tramos).
		//Si viene con los tramos retroactivos, no se debe hacer nada
		if(xmlData.indexOf("<Tramos>") <= 0){

			String rutAfiliado = getRutAfiliado(xmlData);
			String fechaInicioBeneficio = getFechaReconocimiento(xmlData);
			String fechaTerminoBeneficio = getFechaExtincion(xmlData); //Incluye tramo ACTUAL.
			
			List tramosR;
			
			
			try {
				/**
				 * 1. 
				 */
				Date fInicioRango =  sdf.parse(fechaInicioBeneficio);
				Date fTerminoRango =  sdf.parse(fechaTerminoBeneficio);
				
				List tramosCorrespondientes = getFechaTramosHistoricosCorrespondientes(fInicioRango, fTerminoRango);
				
				tramosR = getTramosRetroactivos(rutAfiliado, fechaInicioBeneficio, fechaTerminoBeneficio);
				
				int k=0;
				List tramosAux = new ArrayList();
				
				/**
				 * Se crea lista con todos los tramos correspondientes con valores default.
				 */
				for(Iterator i = tramosCorrespondientes.iterator(); i.hasNext(); ){
					Date fechaInicioTramo = (Date) i.next();
//					String fAux = sdf.format(fechaInicioTramo);
//					int año = new Integer(fAux.substring(0, 4)).intValue();
					
					Tramo tAux = getTramoDefault(fechaInicioTramo);
					
					TramoBusinessTO tb = new TramoBusinessTO();
					tb.setTramo(tAux.getNumTramo());
					tb.setMontoUnitario(tAux.getMontoUnitarioBeneficio());
					tb.setIngresoPromedio(tAux.getRentaDesde());
					tb.setFechaInicioTramo(tAux.getFechaInicio());
					tb.setYear(tAux.getAño());
					tramosAux.add(tb);	
				}	
				
				//para tramos encontrados en repositorio, se actualizan los valores del tramo correspondiente (por año)
				for(Iterator j = tramosR.iterator() ; j.hasNext() ; k++){
								
					TramoBusinessTO tramoR = (TramoBusinessTO) j.next();
					
					for(Iterator i = tramosAux.iterator(); i.hasNext(); ){
						TramoBusinessTO tAux = (TramoBusinessTO) i.next();
							
							if(sdf.format(tAux.getFechaInicioTramo()).equals(sdf.format(tramoR.getFechaInicioTramo()))){
								tAux.setFechaInicioTramo(tramoR.getFechaInicioTramo());
								tAux.setIngresoPromedio(tramoR.getIngresoPromedio());
								tAux.setMontoUnitario(tramoR.getMontoUnitario());
								tAux.setTramo(tramoR.getTramo());
								tAux.setYear(tramoR.getYear());
							}
					}
				}
				
				
				xmlProcesado =  addTramosRetroactivos_IngresoReconocimientoToXML(xmlData, tramosAux);
				
				/**
				 * 2. Si tiene periodos retroactivos y fecha de extinción <= fecha inicio periodo vigente, se deben
				 * eliminar ingreso Promedio, tramo y monto unitario beneficio para el tramo VIGENTE. (porque no tiene) 
				 */
				if(fTerminoRango.before(fIniTramoVig))
					xmlProcesado = delTramoVigenteFromXML(xmlProcesado);
					
				
			} catch (BusinessException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			
		}
		return xmlProcesado;	
	}

	
	private String delTramoVigenteFromXML(String xml) {
		logger.info(">> delTramoVigenteFromXML ");
		StringBuffer sb = new StringBuffer(xml);
		int start = sb.indexOf("<IngPromedio>");
		int end = sb.indexOf("</IngPromedio>") +14;
		
		sb = sb.replace(start, end, "");
		
		start = sb.indexOf("<MontoUnitarioBeneficio>");
		end = sb.indexOf("</MontoUnitarioBeneficio>") + 25;
		
		sb = sb.replace(start, end, "");
		
		start = sb.indexOf("<TramoAsigFam>");
		end = sb.indexOf("</TramoAsigFam>") + 15;
		
		sb = sb.replace(start, end, "");
		
		logger.info("<< delTramoVigenteFromXML ");
		return sb.toString();
	}



	public String addTramosRetroactivos_actualizarCausante(String xmlData) {
		String xmlProcesado = xmlData;//Asigna a variable de retorno el XML original (cuando no puede informar tramos).
		//Si viene con los tramos retroactivos, no se debe hacer nada
		if(xmlData.indexOf("<Tramos>") <= 0){
				
			String rutAfiliado = getRutAfiliado(xmlData);
			String fechaInicioTramo = getFechaReconocimiento(xmlData);
			String fechaTerminoTramo = getFechaExtincion(xmlData); //Incluye tramo ACTUAL.
			
			List tramosR;
			
			try {
				tramosR = getTramosRetroactivos(rutAfiliado, fechaInicioTramo, fechaTerminoTramo);
			
				/**
				 * Si trajo datos, se debe validar que esten todos los tramos contenidos en el rango FechaInicio - Termino
				 */
				int year =0, k=0;
				boolean datosCompletos = true;
				for(Iterator i = tramosR.iterator(); i.hasNext(); k++){
					TramoBusinessTO t = (TramoBusinessTO) i.next();
					
					if(k == 0)
						year = t.getYear();
					else{
						if(year - 1 != t.getYear()){
							datosCompletos = false;
							break;
						}
						year--;
					}
				}
				
				if(datosCompletos)
					xmlProcesado =  addTramosRetroactivos_ActualizarCausanteToXML(xmlData, tramosR);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		return xmlProcesado;
	}
}
