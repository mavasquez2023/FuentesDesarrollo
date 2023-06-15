package cl.araucana.tupla2.struts.jdbcDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cl.araucana.core.business.BusinessException;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.tupla2.struts.bussiness.TO.ActualizaTuplaVO;
import cl.araucana.tupla2.struts.bussiness.TO.CamposXmlTO;
import cl.araucana.tupla2.struts.bussiness.TO.DatosExtinsionTO;
import cl.araucana.tupla2.struts.bussiness.TO.DatosValidacionTO;
import cl.araucana.tupla2.struts.bussiness.TO.RetornoTO;
import cl.araucana.tupla2.struts.bussiness.TO.SqlParametersTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramoTO;
import cl.araucana.tupla2.struts.bussiness.TO.TramosTO;
import cl.araucana.tupla2.struts.bussiness.TO.TuplaTO;
import cl.araucana.tupla2.struts.utils.CustomDataSource;
import cl.araucana.util.Cache;
import cl.araucana.wssiagf.WSSIAGFException;
import cl.araucana.wssiagf.vo.BusinessLogicConfig;
import cl.araucana.wssiagf.vo.RangoTramos;
import cl.araucana.wssiagf.vo.Tramo;

public class Araucanajdbcdao {

	private static String tramosHistoricos;
	private static String tramosHistoricosAfiliado;
	private static String rentaHistoricaAfiliado;
	private static String rentaHistoricaAfiliado2;
	private static long valorMaximoRenta;
	private static int maxTramosRetroactivos;
	private static int numTramoDefault;

	public static final String PROPERTIES_FILE = "wssiagf.properties";

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static Cache cache;
	private static String tramosKey = "tramos";
	private static String valoresTramosDefault = "valoresTramoDefault";
	private static String periodoTramoVigenteKey = "periodoTramoVigente";
	private static Date fIniTramoVig = null;

	private static CustomDataSource ds = new CustomDataSource();

	public Araucanajdbcdao() {
		if (cache == null) {
			cache = new Cache();
			try {

				//parametros de configuración
				BusinessLogicConfig blC = new BusinessLogicConfig(PROPERTIES_FILE);

				tramosHistoricos = blC.getTramosHistoricos();
				tramosHistoricosAfiliado = blC.getTramosHistoricosAfiliado();
				rentaHistoricaAfiliado = blC.getRentaHistoricaAfiliado();
				rentaHistoricaAfiliado2 = blC.getRentaHistoricaAfiliado2();
				valorMaximoRenta = blC.getValorMaximoRenta();
				maxTramosRetroactivos = blC.getMaxTramosRetroactivos();
				numTramoDefault = blC.getNumTramoDefault();
				//Cache
				List tramos = getTramosHistoricos();
				cache.put(tramosKey, tramos);
				List valoresTramoDefault = getValoresDefaultTramosHistoricos(numTramoDefault);
				cache.put(valoresTramosDefault, valoresTramoDefault);
				String periodoTramoVigente = getPeriodoTramoVigente();
				cache.put(periodoTramoVigenteKey, periodoTramoVigente);
			} catch (Exception e) {
				cache = null;
				e.printStackTrace();
			}
		}
		List tramos = (List) cache.get(tramosKey);
		if (tramos.size() > 0) {
			RangoTramos t = (RangoTramos) tramos.get(0);
			fIniTramoVig = t.getFechaInicio();
		}
	}

	/*	static{
			//inicialización de código Estático.
			
		
			cache = new Cache();
			try {
				 
				//parametros de configuración
				BusinessLogicConfig blC = new BusinessLogicConfig(PROPERTIES_FILE);
				 
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
		}*/

	private static List getValoresDefaultTramosHistoricos(int numTramoDefault) throws BusinessException {

		ResultSet rs = null;
		List tramos = new ArrayList();

		//		Connection connection = null;

		Statement getTramosHistoricosStmt = null;
		try {
			////ds.openConnection();
			//			connection = ds.getConnection();

			getTramosHistoricosStmt = ds.getConnection().createStatement();
			String query = "SELECT DISTINCT AF2HA tramo, AF2IA rentaDesde , AF2KA montoTramo, " + "af2ga fechaInicio, afda fechaTermino, " + "YEAR(af2ga) anio " + "FROM " + tramosHistoricos + " where af2ha = " + numTramoDefault + " " + "ORDER BY afda desc";

			rs = getTramosHistoricosStmt.executeQuery(query);

			while (rs.next()) {
				Tramo tramo = new Tramo();
				tramo.setNumTramo(rs.getInt(1));
				tramo.setRentaDesde(rs.getLong(2));
				tramo.setMontoUnitarioBeneficio(rs.getInt(3));
				tramo.setFechaInicio(rs.getDate(4));
				tramo.setFechaTermino(rs.getDate(5));
				tramo.setAño(rs.getInt(6));
				tramos.add(tramo);
			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return tramos;
	}

	private static List getTramosHistoricos() throws BusinessException {

		ResultSet rs = null;
		List tramos = new ArrayList();

		Connection connection = null;
		//	CustomDataSource ds = new CustomDataSource();
		Statement getTramosHistoricosStmt = null;
		try {

			////ds.openConnection();
			connection = ds.getConnection();

			getTramosHistoricosStmt = connection.createStatement();
			String query = "SELECT DISTINCT af2ga fechaInicio, afda fechaTermino, YEAR(af2ga) anio \n" + "FROM " + tramosHistoricos + " ORDER BY afda desc";

			rs = getTramosHistoricosStmt.executeQuery(query);

			while (rs.next()) {
				RangoTramos tramo = new RangoTramos();
				tramo.setFechaInicio(rs.getDate(1));
				tramo.setFechaTermino(rs.getDate(2));
				tramo.setAño(rs.getInt(3));
				tramos.add(tramo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tramos;
	}

	private List getFechaTramosHistoricosCorrespondientes(Date fInicioRango, Date fTerminoRango) {

		List listaTramosValidos = new ArrayList();

		List tramos = (List) cache.get(tramosKey);

		AbsoluteDate absFInicioRango = new AbsoluteDate(fInicioRango);
		AbsoluteDate absFTerminoRango = new AbsoluteDate(fTerminoRango);
		AbsoluteDate absFInicioTramoVig = new AbsoluteDate(fIniTramoVig);
		boolean vigente = true;
		int cantTramosProcesados = 0;
		//2012-08-01 < 2012-07-01
		//absFInicioRango < absFInicioTramoVig
		if (absFInicioRango.compareTo(absFInicioTramoVig) < 0) {
			//Solo se calculan si la fecha de inicio de tramo corresponde a uno retroactivo.
			for (Iterator i = tramos.iterator(); i.hasNext();) {
				if (vigente) {
					i.next();
					vigente = false;
					continue;
				}

				cantTramosProcesados++;
				RangoTramos t = (RangoTramos) i.next();
				AbsoluteDate absFInicioTramo = new AbsoluteDate(t.getFechaInicio());
				new AbsoluteDate(t.getFechaTermino());

				if (absFInicioTramo.compareTo(absFInicioRango) <= 0 || cantTramosProcesados >= maxTramosRetroactivos) {
					listaTramosValidos.add(t.getFechaInicio());
					break;
				} else if (absFInicioTramo.compareTo(absFTerminoRango) <= 0)
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

		return listaTramosValidos;
	}

	private String getFechaTramosHistoricosCorrespondientes(String fechaInicioRango, String fechaTerminoRango) {

		StringBuffer r = new StringBuffer("");
		try {
			Date fInicioRango = sdf.parse(fechaInicioRango);
			Date fTerminoRango = sdf.parse(fechaTerminoRango);

			List listaTramosValidos = getFechaTramosHistoricosCorrespondientes(fInicioRango, fTerminoRango);

			int cont = 0;
			for (Iterator i = listaTramosValidos.iterator(); i.hasNext(); cont++) {
				Date d = (Date) i.next();

				if (cont == 0)
					r.append("'" + sdf.format(d) + "'");
				else
					r.append(" ,'" + sdf.format(d) + "'");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return r.toString();
	}

	public List getTramosRetroactivos(String rutAfiliado, String fechaInicio, String fechaTermino) throws Exception {

		ResultSet rs = null;
		List tramosRetroactivos = new ArrayList();

		long t1 = System.currentTimeMillis();

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		Statement getTramoHistoricoAfiliadoStmt = null;

		String fechaInicioTramos = getFechaTramosHistoricosCorrespondientes(fechaInicio, fechaTermino);
		//	String fechaInicioTramos = getFechaTramosHistoricosCorrespondientes("2010-01-01","2011-10-01");
		if (fechaInicioTramos.equals(""))
			return tramosRetroactivos;

		try {
			////ds.openConnection();
			con = ds.getConnection();

			getTramoHistoricoAfiliadoStmt = con.createStatement();
			String query = "SELECT \n" + "AF2GA fechaInicioTramo, \n" + "AF2HA tramo, \n" + "COALESCE( \n" + "			( \n" + "				SELECT AF27A renta \n" + "				FROM " + rentaHistoricaAfiliado + " \n" + "				WHERE SE5FAJC = tramoH.SE5FAJC and \n" + "				AF2GA = tramoH.AF2GA \n" + "			), \n" + "			( \n"
					+ "				SELECT avg(RENTAS) renta \n" + "				FROM " + rentaHistoricaAfiliado2 + "  \n" + "				WHERE AFIRUT =" + rutAfiliado
					+ " and  \n"
					+ //OBS: antes decia 'AFIRUT =tramoH.SE5FAJC', esto aumentaba considerablemente el tiempo de ejecución de la Query. (No se investigó en profundidad, favor considerar) 
					"				PERIODO between \n" + "				CONCAT(VARCHAR(YEAR(tramoH.AF2GA)), '01') \n" + "				and \n" + "				CONCAT(VARCHAR(YEAR(tramoH.AF2GA)), '06')\n"
					+ "			 ),\n"
					+ //Donde periodo es un entero con formato MMMMDD
					"			(\n" + "				SELECT AF2JA renta \n" + "				FROM " + tramosHistoricos + " \n" + "				WHERE AF2GA = tramoH.AF2GA \n" + "				and AF2HA = tramoH.AF2HA\n" + "			), \n" + "		  -1) as ingresoPromedio, \n" + "COALESCE( \n" + "			( \n" + "			 SELECT AF2KA montoUnitarioBeneficio \n"
					+ "			 FROM " + tramosHistoricos + " \n" + "			 WHERE AF2HA = tramoH.AF2HA and \n" + "			 AF2GA <= tramoH.AF2GA \n" + "			 and AFDA >= tramoH.AF2GA \n" + "			),\n" + "			-1 \n" + "		   ) as montoUnitarioBeneficio, \n" + "			YEAR(tramoH.AF2GA) anio \n" + "FROM (SELECT * FROM "
					+ tramosHistoricosAfiliado + " \n" + "WHERE SE5FAJC = " + rutAfiliado + " and \n" +
					//"AF2GA between '"+fechaInicio+"' and '"+fechaTermino+"' \n"+
					"AF2GA in (" + fechaInicioTramos + ") \n" + "ORDER BY AF2GA DESC \n " + "FETCH FIRST " + maxTramosRetroactivos + " ROWS ONLY) tramoH";
			//System.out.println(query);

			rs = getTramoHistoricoAfiliadoStmt.executeQuery(query);

			//Este SQL trae solo los años de los tramos retroactivos.

			while (rs.next()) {
				TramoTO oTramo = new TramoTO();
				oTramo.setFecIniTramo(rs.getDate(1));
				oTramo.setNumTramo(String.valueOf(rs.getInt(2)));
				oTramo.setIngPromedio(String.valueOf(rs.getInt(3)));
				oTramo.setMontoUnitarioBeneficio(String.valueOf(rs.getInt(4)));
				oTramo.setPeriodo(String.valueOf(rs.getInt(5)));
				tramosRetroactivos.add(oTramo);

				//Ajuste debido a máximo valor en SIAGF.
				if (Integer.parseInt(oTramo.getIngPromedio()) > 999999) {
					oTramo.setIngPromedio("999999");
				}
			}
			rs.close();
			getTramoHistoricoAfiliadoStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar tramos retroactivos");
		}

		return tramosRetroactivos;
	}

	/**
	 * Obtiene el tramo actual del afiliado desde la informacion de La Araucana
	 * @param rutAfiliado Rut del afiliado (beneficiario) sin digito ni puntos
	 * @param fechaInicio Fecha de inicio (AAAA-MM-DD)
	 * @param fechaTermino Fecha de termino (AAAA-MM-DD)
	 * @return Objeto TramoTO que contiene: rutCausante, periodo, tramo, ingreso promedio y monto del beneficio
	 * @throws Exception 
	 */
	public TramoTO getTramoActual(String rutAfiliado, String fechaInicio, String fechaTermino) throws Exception {
		TramoTO oTramo = null;
		ResultSet rs = null;

		try {
			AbsoluteDate absFTerminoRango = new AbsoluteDate(sdf.parse(fechaTermino));
			AbsoluteDate fInicioTramoActual = new AbsoluteDate(fIniTramoVig);
			System.out.println("Entre al tramo actual");

			if (absFTerminoRango.compareTo(fInicioTramoActual) >= 0) {
				
				String periodoTramoVigente = (String) cache.get(periodoTramoVigenteKey);
				
				sdf.format(fIniTramoVig);
				//System.out.println(absFTerminoRango);
				System.out.println("Entre al tramo actual");
				System.out.println(fInicioTramoActual);

				//				CustomDataSource ds = new CustomDataSource();
				Connection con = null;
				Statement getTramoHistoricoAfiliadoStmt = null;

				////ds.openConnection();
				con = ds.getConnection();

				getTramoHistoricoAfiliadoStmt = con.createStatement();
				String query = "SELECT \n" + "AF2GA fechaInicioTramo, \n" + "AF2HA tramo, \n" + "COALESCE( \n" + "			( \n" + "				SELECT AF27A renta \n" + "			FROM " + rentaHistoricaAfiliado + " \n" + "				WHERE SE5FAJC = tramoH.SE5FAJC and \n" + "				AF2GA = tramoH.AF2GA \n" + "			), \n"
						+ "			( \n" + "				SELECT AF27A renta \n" + "				FROM " + rentaHistoricaAfiliado + " \n" + "				WHERE SE5FAJC = tramoH.SE5FAJC and \n" + "				AF2GA = tramoH.AF2GA \n"
						+ "			 ),\n"
						+ //Donde periodo es un entero con formato MMMMDD
						"			(\n" + "				SELECT AF2JA renta \n" + "				FROM " + tramosHistoricos + " \n" + "				WHERE AF2GA = tramoH.AF2GA \n" + "				and AF2HA = tramoH.AF2HA\n" + "			), \n" + "		  -1) as ingresoPromedio, \n" + "COALESCE( \n" + "			( \n"
						+ "			 SELECT AF2KA montoUnitarioBeneficio \n" + "			 FROM " + tramosHistoricos + " \n" + "			 WHERE AF2HA = tramoH.AF2HA and \n" + "			 AF2GA <= tramoH.AF2GA \n" + "			 and AFDA >= tramoH.AF2GA \n" + "			),\n" + "			-1 \n" + "		   ) as montoUnitarioBeneficio, \n"
						+ "			YEAR(tramoH.AF2GA) anio \n" + "FROM " + tramosHistoricosAfiliado + " tramoH \n" + "WHERE SE5FAJC = " + rutAfiliado + " and \n" +
						//"AF2GA between '"+fechaInicio+"' and '"+fechaTermino+"' \n"+
						"AF2GA = '"+periodoTramoVigente+"' \n ORDER BY tramoH.AF2GA DESC \n" + "FETCH FIRST ROW ONLY";

				System.out.println("QUERY: " + query);
				rs = getTramoHistoricoAfiliadoStmt.executeQuery(query);
				//System.out.println(rs);

				//Este SQL trae solo los años de los tramos retroactivos.
				if (rs.next()) {

					oTramo = new TramoTO();
					//t.set(rs.getDate(1));
					oTramo.setNumTramo(String.valueOf(rs.getInt(2)));
					oTramo.setIngPromedio(String.valueOf(rs.getInt(3)));
					oTramo.setMontoUnitarioBeneficio(String.valueOf(rs.getInt(4)));
					oTramo.setPeriodo(String.valueOf(rs.getInt(5)));

					//Ajuste debido a máximo valor en SIAGF.
					if (Integer.parseInt(oTramo.getIngPromedio()) > 999999) {
						oTramo.setIngPromedio("999999");
					}
				} else {
					System.out.println("tramo 5");
					oTramo = new TramoTO();
					//t.set(rs.getDate(1));
					oTramo.setNumTramo("5");
					oTramo.setIngPromedio("0");
					oTramo.setMontoUnitarioBeneficio("0");
					oTramo.setPeriodo("2014");
				}
				rs.close();
				getTramoHistoricoAfiliadoStmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar Tramo actual");
		}

		return oTramo;
	}
	
	public Map<String, TramosTO> getTramosAsfam() throws Exception {
		ResultSet rs = null;
		Statement stm= null;
		Map<String, TramosTO> tramos= new HashMap<String, TramosTO>();
		try {
			

				Connection con = null;

				////ds.openConnection();
				con = ds.getConnection();
				stm= con.createStatement();
				String query = "SELECT AF2GA as fechaTramo, Year(AF2GA) as anio, case when Month(AF2GA)<7 Then '1' else '2' end as mes, AF2HA as tramo, AF2KA as valor, AF2IA as rentaMinima, AF2JA as rentaMaxima from " + tramosHistoricos + " where AF2GA>='2000-01-01'";

				//System.out.println("QUERY: " + query);
				rs = stm.executeQuery(query);
				//System.out.println(rs);

				//Este SQL trae solo los años de los tramos retroactivos.
				while (rs.next()) {

					TramosTO oTramo= new TramosTO();
					Integer periodo=0;
					int anio= rs.getInt("anio");
					int mes= rs.getInt("mes");
					if(anio>= 2016){
						periodo= anio*100 + mes;
					}else{
						periodo= anio;
					}
					oTramo.setTramo(rs.getInt("tramo"));
					oTramo.setValor(rs.getInt("valor"));
					oTramo.setRentaMinima(rs.getInt("rentaMinima"));
					oTramo.setRentaMaxima(rs.getInt("rentaMaxima"));
					java.sql.Date fechaTramo= rs.getDate("fechaTramo");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					oTramo.setFechaTramo(sdf.format(fechaTramo));
					tramos.put(periodo + "" + rs.getInt("tramo"), oTramo);
				} 
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar Tramos Asfam");
		}finally{
			if (rs!=null) rs.close();
			if (stm!=null) stm.close();
		}
		
		return tramos;
	}
	
	public int getRentaPromedio(int rut, String fechaTramo) throws Exception {
		ResultSet rs = null;
		PreparedStatement stm= null;
		int renta=0;
		try {
			
				Connection con = null;

				////ds.openConnection();
				con = ds.getConnection();
				String sql = "SELECT AF27A as renta FROM " + rentaHistoricaAfiliado + " WHERE SE5FAJC = ? and AF2GA='" + fechaTramo + "'";
				stm= con.prepareStatement(sql);
				stm.setInt(1, rut);
				//System.out.println("QUERY: " + sql);
				rs = stm.executeQuery();

				if (rs.next()) {
					renta= rs.getInt("renta");
				} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (rs!=null) rs.close();
			if (stm!=null) stm.close();
		}
		
		return renta;
	}
	
	public Vector getConsulta(SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		Vector xml = new Vector();

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select  RUTCONSULTA,DVCONSULTA,ID from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " where ID > " + oSql.getMaxid() + " and estado = 0 ";
			if(oSql.getMinid()!=null && !oSql.getMinid().equals("")){
				sql= sql + " and ID < " + oSql.getMinid();
			}
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			while (rs.next()) {
				xml.add(String.valueOf(rs.getString("ID").trim() + "|" + rs.getInt("RUTCONSULTA")) + "-" + rs.getString("DVCONSULTA").trim());

			}

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

		return xml;
	}
	
	public int updateConsulta(SqlParametersTO oSql, int rut, int estado, String mensaje) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		int respuesta=0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "update " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " set estado= ?, mensaje= ? where rutconsulta = ? ";
			//System.out.println("Query update= " + sql);
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			press.setInt(1, estado);
			press.setString(2, mensaje);
			press.setInt(3, rut);
			respuesta= press.executeUpdate();

			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

		return respuesta;
	}
	
	public Vector getXML(SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		Vector xml = new Vector();

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select  AFSIRUBE,DVBENE,ID from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " where ID > " + oSql.getMaxid();
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			while (rs.next()) {
				xml.add(String.valueOf(rs.getString("ID").trim() + "|" + rs.getInt("AFSIRUBE")) + "-" + rs.getString("DVBENE").trim());

			}

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

		return xml;
	}

	public List getvalidacion(SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		DatosValidacionTO oValida = new DatosValidacionTO();
		List lista = new ArrayList();

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select  ID, " + "AFSIRUBE," + "DVBENE," + "AFSIFEAU," + "AFFEEXTC," + "CODCAUSANTE," + "TRAMO," + "MONTO," + "RUTBENEFICIARIO," + "DVBENEFICIARIO," + "EXISTENCIASIAGF," + "IDTUPLA," + "CANTIDADTRAMO," + "ESTADOTRAMO," + "ESTADOCAUSANTE," + "ESTADOFINAL,"
					+ "ESTADOBENEFICIARIO," + "CODIGOENTIDAD from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " where ID > " + oSql.getMaxid();
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			while (rs.next()) {

				oValida = new DatosValidacionTO();

				oValida.setID(rs.getString("ID"));
				oValida.setAFSIRUBE(rs.getString("AFSIRUBE"));
				oValida.setDVBENE(rs.getString("DVBENE"));
				oValida.setAFSIFEAU(rs.getString("AFSIFEAU"));
				oValida.setAFFEEXTC(rs.getString("AFFEEXTC"));
				oValida.setCODCAUSANTE(rs.getString("CODCAUSANTE"));
				oValida.setTRAMO(rs.getString("TRAMO"));
				oValida.setMONTO(rs.getString("MONTO"));
				oValida.setRUTBENEFICIARIO(rs.getString("RUTBENEFICIARIO"));
				oValida.setDVBENEFICIARIO(rs.getString("DVBENEFICIARIO"));
				oValida.setEXISTENCIASIAGF(rs.getString("EXISTENCIASIAGF"));
				oValida.setIDTUPLA(rs.getString("IDTUPLA"));
				oValida.setIDTUPLA(rs.getString("CANTIDADTRAMO"));
				oValida.setESTADOTRAMO(rs.getString("ESTADOTRAMO"));
				oValida.setESTADOCAUSANTE(rs.getString("ESTADOCAUSANTE"));
				oValida.setESTADOFINAL(rs.getString("ESTADOFINAL"));

				lista.add(oValida);
			}
			rs.close();
			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

		return lista;
	}

	public boolean updateOrigen(SqlParametersTO oSql, DatosValidacionTO oDatos) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;

		if (oDatos.getEXISTENCIASIAGF() == null)
			oDatos.setEXISTENCIASIAGF("");
		if (oDatos.getCANTIDADTRAMO() == null)
			oDatos.setCANTIDADTRAMO("0");
		if (oDatos.getESTADOTRAMO() == null)
			oDatos.setESTADOTRAMO("");
		if (oDatos.getESTADOCAUSANTE() == null)
			oDatos.setESTADOCAUSANTE("");
		if (oDatos.getESTADOFINAL() == null)
			oDatos.setESTADOFINAL("");
		if (oDatos.getESTADOBENEFICIARIO() == null)
			oDatos.setESTADOBENEFICIARIO("");
		if (oDatos.getCODIGOENTIDAD() == null)
			oDatos.setCODIGOENTIDAD("");

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = " UPDATE " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " SET EXISTENCIASIAGF= '" + oDatos.getEXISTENCIASIAGF() + "', CANTIDADTRAMO = '" + oDatos.getCANTIDADTRAMO() + "', ESTADOTRAMO = '" + oDatos.getESTADOTRAMO() + "', ESTADOCAUSANTE = '"
					+ oDatos.getESTADOCAUSANTE() + "' , ESTADOFINAL = '" + oDatos.getESTADOFINAL() + "'" + ",ESTADOBENEFICIARIO= '" + oDatos.getESTADOBENEFICIARIO() + "'" + ",CODIGOENTIDAD = '" + oDatos.getCODIGOENTIDAD() + "'" + " where AFSIRUBE =" + oDatos.getAFSIRUBE();
			//System.out.println(sql);
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			press.execute();

			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		} finally {

		}

		return true;
	}

	/**
	 * Para obtener datos de Reconocimiento
	 * @param oSql
	 * @return
	 */
	public List getdatosxml(SqlParametersTO oSql) {
		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		CamposXmlTO oCampos = null;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select ID2, FECHAEMISION," + "TIPOCAUSANTE," + "SEXOCAUSANTE," + "RUTCAUSANTE," + "NOMCAUSANTE," + "TIPOBENEFICIARIO," + "RUTBENEFICIARIO," + "NOMBENEFICIARIO," + "RUTEMPLEADOR," + "NOMEMPLEADOR," + "ACTECO," + "REGIONEMPLEADOR," + "COMUNAEMPLEADOR," + "CODENTIDADADM,"
					+ "IDTIPOBENEFICIO," + "FECRECCAUSANTE," + "FECPAGOBENEFICIO," + "FECEXTCAUSANTE, " + "FNACCAUSANTE from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() +
					//" where ID2 = " +oSql.getMaxid(); 
					" where ID2 > " + oSql.getMaxid();
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			while (rs.next()) {
				oCampos = new CamposXmlTO();
				oCampos.setId(rs.getString("ID2"));
				oCampos.setFechaEmision(rs.getString("FECHAEMISION"));
				oCampos.setTipoCausante(rs.getString("TIPOCAUSANTE"));
				oCampos.setSexoCausante(rs.getString("SEXOCAUSANTE"));
				oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
				oCampos.setNomCausante(rs.getString("NOMCAUSANTE"));
				oCampos.setTipoBeneficiario(rs.getString("TIPOBENEFICIARIO"));
				oCampos.setRutBeneficiario(rs.getString("RUTBENEFICIARIO"));
				oCampos.setNomBeneficiario(rs.getString("NOMBENEFICIARIO"));
				oCampos.setRutEmpleador(rs.getString("RUTEMPLEADOR"));
				oCampos.setNomEmpleador(rs.getString("NOMEMPLEADOR"));
				oCampos.setActeco(rs.getString("ACTECO"));
				oCampos.setRegionEmpleador(rs.getString("REGIONEMPLEADOR"));
				oCampos.setComunaEmpleador(rs.getString("COMUNAEMPLEADOR"));
				oCampos.setCodEntidadAdm(rs.getString("CODENTIDADADM"));
				oCampos.setIdTipoBeneficio(rs.getString("IDTIPOBENEFICIO"));
				oCampos.setFecRecCausante(rs.getString("FECRECCAUSANTE"));
				oCampos.setFecPagoBeneficio(rs.getString("FECPAGOBENEFICIO"));
				oCampos.setFecExtCausante(rs.getString("FECEXTCAUSANTE"));
				oCampos.setfNacCausante(rs.getString("FNACCAUSANTE"));

				lista.add(oCampos);
			}
			rs.close();
			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return lista;
	}
	
	public void insertDatosEmpresa(SqlParametersTO oSql) throws SQLException{
		
		Connection con = null;
		CallableStatement ctmt= null;;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("delete from " + oSql.getEsquemaorigen() + ".EMPRESA ");
			ctmt = con.prepareCall(sql.toString());
			ctmt.executeUpdate();
			
			sql= new  StringBuffer();
			sql.append("insert into " + oSql.getEsquemaorigen() + ".EMPRESA ");
			sql.append("select distinct d.se5fajc as RUTBENBEF, b.dirrut as RUTEMP, cm.CMOA as DVRUTEMP, a.afsicore as REGION_EMP, a.afsicoco as COMUNA_EMP, c.cm5asii as ACTECO, cm.cmpa as NOMBRE_EMP ");
			sql.append("from afdta.afp51f1 a join  beexp.bef2000 b ");
			sql.append("on a.bccodcom= b.codcom ");
			sql.append("join cmdta.cm44a1 c ");
			sql.append("on b.dirrut= c.cmna ");
			sql.append("and b.oficod= c.cmba ");
			sql.append("and b.empsuc= c.cm13a ");
			sql.append("join afdta.af03f1 d ");
			sql.append("on d.cmna= b.dirrut ");
			sql.append("and d.cmba= b.oficod ");
			sql.append("and d.cm13a= b.empsuc ");
			sql.append("join cmdta.cm02f1 cm ");
			sql.append("on cm.cmna= d.cmna ");
			sql.append("where d.se5fajc in (select distinct rutbenef from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + ") ");
			//sql.append("and b.dircod=1 ");
			ctmt = con.prepareCall(sql.toString());
			ctmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ctmt.close();
		}
	}
	
public void insertDatosBeneficiario(SqlParametersTO oSql) throws SQLException{
		
		Connection con = null;
		CallableStatement ctmt= null;;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("delete from " + oSql.getEsquemaorigen() + ".BENEF2 ");
			ctmt = con.prepareCall(sql.toString());
			ctmt.executeUpdate();
			
			sql= new  StringBuffer();
			sql.append("insert into " + oSql.getEsquemaorigen() + ".BENEF2 ");
			sql.append("select a.bcclrut as RUTBENEF, b.afsicore as REGION_BEN, b.afsicoco as COMUNA_BEN ");
			sql.append("from bcdta.bc07f1 a join afdta.afp51f1 b ");
			sql.append("on a.bccodcom= b.bccodcom ");
			sql.append("where a.bcclrut in (select distinct rutbenef from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + ") ");
			
			ctmt = con.prepareCall(sql.toString());
			ctmt.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			ctmt.close();
		}
	}

	/**
	 * Para obtener datos de Reconocimiento
	 * @param oSql
	 * @return
	 */
	public List getDatosxmlIngreso(SqlParametersTO oSql) {
		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		CamposXmlTO oCampos = null;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("select distinct current_date as FECHAEMISION, a.id as ID, a.rutcausa || '-' || upper(a.dvcausa) as RUTCAUSANTE, a.nomcausa as NOMCAUSANTE, case when b.af8ma='I' then 'F' else b.af8ma end as SEXOCAUSANTE, a.codtcau as TIPOCAUSANTE, ");
			sql.append("'10105' as CODENTIDADADM, a.finiben as FECRECCAUSANTE, a.fterben as FECEXTCAUSANTE, 1 as IDTIPOBENEFICIO, d.periodo_rechazo as PERIODO_RECHAZO, a.codtramo as TRAMO, ");
			sql.append("a.rutbenef || '-' ||  upper(a.dvbenef) as RUTBENEFICIARIO, a.nombenef as NOMBENEFICIARIO, coalesce(ben2.region_ben, '13'), coalesce(ben2.comuna_ben, '13101') as REGIONBENEFICIARIO, a.monbenef as MONTO_BENEFICIO, '1' as TIPOBENEFICIARIO, ");
			sql.append("a.rutemple || '-' || a.dvemple as RUTEMPLEADOR, a.nomemple as NOMEMPLEADOR, coalesce(emp.region_emp, '13') as REGIONEMPLEADOR, coalesce(emp.comuna_emp, '13101') as COMUNAEMPLEADOR, coalesce(emp.acteco, '111111') as ACTECO, ");
			sql.append("case when d.causante=1 and (d.periodo + d.tramo + d.monto)=0 then 1 else 0 end as CAUSANTE, d.periodo as PERIODO, COALESCE(e.AF27A, 0) as RENTA_PROMEDIO, b.af8ja as FNACCAUSANTE ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " d "); //marcarechazo
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a "); //totrechazt o liqrevisa
			sql.append("on d.id= a.id ");
			sql.append("join afdta.af05f1 b ");
			sql.append("on b.af8ha= a.rutcausa ");
			sql.append("left join AFDTA.AFP68F1	e ");
			sql.append("on e.SE5FAJC = a.rutbenef ");
			sql.append("and e.AF2GA= d.fechatramo ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".EMPRESA emp ");
			sql.append("on emp.rutemp= a.rutemple ");
			sql.append("and emp.RUTBENBEF= a.rutbenef ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".benef2 ben2 ");
			sql.append("on ben2.rutbenef= a.rutbenef ");
			sql.append("where (d.tupla)=1 ");
			sql.append("and (beneficiario+empleador)=0 ");
			sql.append("and d.periodo_rechazo between ? and ?  ");
			sql.append("and a.codtcau not in(21, 22, 0) ");
			sql.append("and b.af8ka!='M' ");
			sql.append("and d.codigo='99' ");
			sql.append("UNION ");
			sql.append("select distinct current_date as FECHAEMISION, a.id as ID, a.rutcausa || '-' || upper(a.dvcausa) as RUTCAUSANTE, a.nomcausa as NOMCAUSANTE, case when b.af8ma='I' then 'F' else b.af8ma end as SEXOCAUSANTE, a.codtcau as TIPOCAUSANTE, ");
			sql.append("'10105' as CODENTIDADADM, a.finiben as FECRECCAUSANTE, a.fterben as FECEXTCAUSANTE, 2 as IDTIPOBENEFICIO, d.periodo_rechazo as PERIODO_RECHAZO, a.codtramo as TRAMO, ");
			sql.append("a.rutbenef || '-' ||  upper(a.dvbenef) as RUTBENEFICIARIO, a.nombenef as NOMBENEFICIARIO, coalesce(ben2.region_ben, '13'), coalesce(ben2.comuna_ben, '13101') as REGIONBENEFICIARIO, a.monbenef as MONTO_BENEFICIO, '1' as TIPOBENEFICIARIO, ");
			sql.append("a.rutemple || '-' || a.dvemple as RUTEMPLEADOR, a.nomemple as NOMEMPLEADOR, coalesce(emp.region_emp, '13') as REGIONEMPLEADOR, coalesce(emp.comuna_emp, '13101') as COMUNAEMPLEADOR, coalesce(emp.acteco, '111111') as ACTECO, ");
			sql.append("case when d.causante=1 and (d.periodo + d.tramo + d.monto)=0 then 1 else 0 end as CAUSANTE, d.periodo as PERIODO, COALESCE(e.AF27A, 0) as RENTA_PROMEDIO, b.af8ja as FNACCAUSANTE ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " d "); //marcarechazo
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a "); //totrechazt
			sql.append("on d.id= a.id ");
			sql.append("join afdta.af05f1 b ");
			sql.append("on b.af8ha= a.rutcausa ");
			sql.append("left join AFDTA.AFP68F1	e ");
			sql.append("on e.SE5FAJC = a.rutbenef ");
			sql.append("and e.AF2GA= d.fechatramo ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".EMPRESA emp ");
			sql.append("on emp.rutemp= a.rutemple ");
			sql.append("and emp.RUTBENBEF= a.rutbenef ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".benef2 ben2 ");
			sql.append("on ben2.rutbenef= a.rutbenef ");
			sql.append("where (d.tupla)=1 ");
			sql.append("and (beneficiario+empleador)=0 ");
			sql.append("and d.periodo_rechazo between ? and ?  ");
			sql.append("and a.codtcau in(21, 22) ");
			//sql.append("and b.af8ka='M' ");
			sql.append("and d.codigo='99' ");
			
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql.toString());
			press.setInt(1, oSql.getMinperiodo());
			press.setInt(2, oSql.getMaxperiodo());
			press.setInt(3, oSql.getMinperiodo());
			press.setInt(4, oSql.getMaxperiodo());
			rs = press.executeQuery();
			while (rs.next()) {
				oCampos = new CamposXmlTO();
				oCampos.setId(rs.getString("ID"));
				java.sql.Date fecha= rs.getDate("FECHAEMISION");
				AbsoluteDate fechaabs = new AbsoluteDate(fecha);
				String fechastr= fechaabs.toString("-");
				String aaaaammdd= fechaabs.getYear() + "-" + ((fechaabs.getMonth()<10)?"0"+fechaabs.getMonth():""+fechaabs.getMonth() )+ "-" + ((fechaabs.getDay()<10)?"0"+fechaabs.getDay():""+fechaabs.getDay()); 
				oCampos.setFechaEmision(aaaaammdd);
				oCampos.setTipoCausante(rs.getString("TIPOCAUSANTE"));
				oCampos.setSexoCausante(rs.getString("SEXOCAUSANTE"));
				oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
				oCampos.setNomCausante(rs.getString("NOMCAUSANTE"));
				oCampos.setTipoBeneficiario(rs.getString("TIPOBENEFICIARIO"));
				oCampos.setRutBeneficiario(rs.getString("RUTBENEFICIARIO"));
				oCampos.setNomBeneficiario(rs.getString("NOMBENEFICIARIO"));
				oCampos.setRutEmpleador(rs.getString("RUTEMPLEADOR"));
				oCampos.setNomEmpleador(rs.getString("NOMEMPLEADOR").replaceAll("&", "Y"));
				oCampos.setActeco(rs.getString("ACTECO"));
				oCampos.setRegionEmpleador(rs.getString("REGIONEMPLEADOR"));
				oCampos.setComunaEmpleador(rs.getString("COMUNAEMPLEADOR"));
				oCampos.setCodEntidadAdm(rs.getString("CODENTIDADADM"));
				oCampos.setIdTipoBeneficio(rs.getString("IDTIPOBENEFICIO"));
				String fecharec= rs.getString("FECRECCAUSANTE");
				fecharec= fecharec.substring(0, 4) + "-" + fecharec.substring(4, 6) + "-" + fecharec.substring(6, 8);
				oCampos.setFecRecCausante(fecharec);
				oCampos.setFecPagoBeneficio(fecharec);
				String fechaext= rs.getString("FECEXTCAUSANTE");
				fechaext= fechaext.substring(0, 4) + "-" + fechaext.substring(4, 6) + "-" + fechaext.substring(6, 8);
				oCampos.setFecExtCausante(fechaext);
				oCampos.setPeriodo(rs.getString("PERIODO_RECHAZO"));
				oCampos.setIngPromedio(rs.getString("RENTA_PROMEDIO"));
				oCampos.setTramoAsigFam(rs.getString("TRAMO"));
				oCampos.setMontoUnitarioBeneficio(rs.getString("MONTO_BENEFICIO"));
				java.sql.Date fechanac= rs.getDate("FNACCAUSANTE");
				if (fechanac!=null && !oCampos.getTipoCausante().equals("21") && !oCampos.getTipoCausante().equals("22")){
					AbsoluteDate fechanacabs = new AbsoluteDate(fechanac);
					String fechanacstr= fechaabs.toString("-");
					String fnaaaaammdd= fechanacabs.getYear() + "-" + ((fechanacabs.getMonth()<10)?"0"+fechanacabs.getMonth():""+fechanacabs.getMonth() )+ "-" + ((fechanacabs.getDay()<10)?"0"+fechanacabs.getDay():""+fechanacabs.getDay()); 
					oCampos.setfNacCausante(fnaaaaammdd);
				}else{
					oCampos.setfNacCausante("");
				}

				lista.add(oCampos);
			}
			rs.close();
			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return lista;
	}
	
	/**
	 * Para obtener datos de Reconocimiento
	 * @param oSql
	 * @return
	 */
	public List getDatosXmlIngresoMensual(SqlParametersTO oSql) {
		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		CamposXmlTO oCampos = null;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("select distinct current_date as FECHAEMISION, a.id as ID, d.idtupla as IDTUPLA, a.rutcausa || '-' || upper(a.dvcausa) as RUTCAUSANTE, trim(b.af8ga) || ' ' || trim(b.af8ea) || ' ' || trim(b.af8fa) as NOMCAUSANTE, b.af8ma as SEXOCAUSANTE, a.codtcau as TIPOCAUSANTE, ");
			sql.append("'10105' as CODENTIDADADM, a.finiben as FECRECCAUSANTE, a.fterben as FECEXTCAUSANTE, '01' as IDTIPOBENEFICIO, d.periodo_rechazo as PERIODO_RECHAZO, af9.af2ha as TRAMO, a.codtramo as TRAMOAUX, ");
			sql.append("a.rutbenef || '-' ||  upper(afi.se5fbh3) as RUTBENEFICIARIO, trim(afi.se5fbio) || ' ' || trim(afi.se5fbim) || ' ' || trim(afi.se5fbik) as NOMBENEFICIARIO, coalesce(ben2.region_ben, '13') as REGIONBENEFICIARIO, coalesce(ben2.comuna_ben, '13101') as COMUNABENEFICIARIO, '1' as TIPOBENEFICIARIO, ");
			sql.append("emp.rutemp || '-' || emp.dvrutemp as RUTEMPLEADOR, emp.nombre_emp as NOMEMPLEADOR, coalesce(emp.region_emp, '13') as REGIONEMPLEADOR, coalesce(emp.comuna_emp, '13101') as COMUNAEMPLEADOR, coalesce(emp.acteco, '000000') as ACTECO, ");
			sql.append("case when d.causante=1 and (d.periodo + d.tramo + d.monto)=0 then 1 else 0 end as CAUSANTE, d.periodo as PERIODO, COALESCE(e.AF27A, 0) as RENTA_PROMEDIO, b.af8ja as FNACCAUSANTE ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " d "); //marcarechazo
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a "); //liqrevisa
			sql.append("on d.id= a.id ");
			sql.append("join afdta.af05f1 b ");
			sql.append("on b.af8ha= a.rutcausa ");
			sql.append("and b.se5fajc= a.rutbenef ");
			sql.append("join afdta.af02f1 afi ");
			sql.append("on afi.se5fajc= a.rutbenef ");
			sql.append("left join afdta.af09f1 af9 ");
			sql.append("on a.rutbenef = af9.se5fajc ");
			sql.append("and d.fechatramo= af9.af2ga ");
			sql.append("left join AFDTA.AFP68F1	e ");
			sql.append("on e.SE5FAJC = a.rutbenef ");
			sql.append("and e.AF2GA= d.fechatramo ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".EMPRESA emp ");
			sql.append("on emp.RUTBENBEF= a.rutbenef ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".benef2 ben2 ");
			sql.append("on ben2.rutbenef= a.rutbenef ");
			sql.append("where (d.tupla)=1 ");
			sql.append("and (beneficiario+empleador)=0 ");
			sql.append("and d.periodo_rechazo between ? and ?  ");
			sql.append("and a.codtcau not in(21, 22) ");
			sql.append("and b.af8ka!='M' ");
			sql.append("and d.codigo='99' ");
			sql.append("UNION ");
			sql.append("select distinct current_date as FECHAEMISION, a.id as ID, d.idtupla as IDTUPLA, a.rutcausa || '-' || upper(a.dvcausa) as RUTCAUSANTE, trim(b.af8ga) || ' ' || trim(b.af8ea) || ' ' || trim(b.af8fa) as NOMCAUSANTE, 'F' as SEXOCAUSANTE, a.codtcau as TIPOCAUSANTE, ");
			sql.append("'10105' as CODENTIDADADM, a.finiben as FECRECCAUSANTE, a.fterben as FECEXTCAUSANTE, '02' as IDTIPOBENEFICIO, d.periodo_rechazo as PERIODO_RECHAZO, af9.af2ha as TRAMO, a.codtramo as TRAMOAUX, ");
			sql.append("a.rutbenef || '-' ||  upper(afi.se5fbh3) as RUTBENEFICIARIO, trim(afi.se5fbio) || ' ' || trim(afi.se5fbim) || ' ' || trim(afi.se5fbik) as NOMBENEFICIARIO, coalesce(ben2.region_ben, '13')  as REGIONBENEFICIARIO, coalesce(ben2.comuna_ben, '13101') as COMUNABENEFICIARIO, '1' as TIPOBENEFICIARIO, ");
			sql.append("emp.rutemp || '-' || emp.dvrutemp as RUTEMPLEADOR, emp.nombre_emp as NOMEMPLEADOR, coalesce(emp.region_emp, '13') as REGIONEMPLEADOR, coalesce(emp.comuna_emp, '13101') as COMUNAEMPLEADOR, coalesce(emp.acteco, '000000') as ACTECO, ");
			sql.append("case when d.causante=1 and (d.periodo + d.tramo + d.monto)=0 then 1 else 0 end as CAUSANTE, d.periodo as PERIODO, COALESCE(e.AF27A, 0) as RENTA_PROMEDIO, b.af8ja as FNACCAUSANTE ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " d "); //marcarechazo
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a "); //totrechazt
			sql.append("on d.id= a.id ");
			sql.append("join afdta.af05f1 b ");
			sql.append("on b.af8ha= a.rutcausa ");
			sql.append("and b.se5fajc= a.rutbenef ");
			sql.append("join afdta.af02f1 afi ");
			sql.append("on afi.se5fajc= a.rutbenef ");
			sql.append("left join afdta.af09f1 af9 ");
			sql.append("on a.rutbenef = af9.se5fajc ");
			sql.append("and d.fechatramo= af9.af2ga ");
			sql.append("left join AFDTA.AFP68F1	e ");
			sql.append("on e.SE5FAJC = a.rutbenef ");
			sql.append("and e.AF2GA= d.fechatramo ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".EMPRESA emp ");
			sql.append("on emp.RUTBENBEF= a.rutbenef ");
			sql.append("left join " + oSql.getEsquemaorigen() + ".benef2 ben2 ");
			sql.append("on ben2.rutbenef= a.rutbenef ");
			sql.append("where (d.tupla)=1 ");
			sql.append("and (beneficiario+empleador)=0 ");
			sql.append("and d.periodo_rechazo between ? and ?  ");
			sql.append("and a.codtcau in(21, 22) ");
			//sql.append("and b.af8ka='M' ");
			sql.append("and d.codigo='99' ");
			
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql.toString());
			press.setInt(1, oSql.getMinperiodo());
			press.setInt(2, oSql.getMaxperiodo());
			press.setInt(3, oSql.getMinperiodo());
			press.setInt(4, oSql.getMaxperiodo());
			rs = press.executeQuery();
			System.out.println("Registros a procesar mensual:");
			while (rs.next()) {
				oCampos = new CamposXmlTO();
				oCampos.setId(rs.getString("ID"));
				oCampos.setTupla(rs.getString("IDTUPLA"));
				java.sql.Date fecha= rs.getDate("FECHAEMISION");
				AbsoluteDate fechaabs = new AbsoluteDate(fecha);
				String fechastr= fechaabs.toString("-");
				String aaaaammdd= fechaabs.getYear() + "-" + ((fechaabs.getMonth()<10)?"0"+fechaabs.getMonth():""+fechaabs.getMonth() )+ "-" + ((fechaabs.getDay()<10)?"0"+fechaabs.getDay():""+fechaabs.getDay()); 
				oCampos.setFechaEmision(aaaaammdd);
				oCampos.setTipoCausante(rs.getString("TIPOCAUSANTE"));
				String sexo= rs.getString("SEXOCAUSANTE");
				if(!sexo.trim().equals("M") && !sexo.trim().equals("F")){
					sexo="F";
				}
				oCampos.setSexoCausante(sexo);
				oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
				oCampos.setNomCausante(rs.getString("NOMCAUSANTE"));
				oCampos.setTipoBeneficiario(rs.getString("TIPOBENEFICIARIO"));
				oCampos.setRutBeneficiario(rs.getString("RUTBENEFICIARIO"));
				oCampos.setNomBeneficiario(rs.getString("NOMBENEFICIARIO"));
				oCampos.setRegionBeneficiario(rs.getString("REGIONBENEFICIARIO"));
				oCampos.setComunaBeneficiario(rs.getString("COMUNABENEFICIARIO"));
				oCampos.setRutEmpleador(rs.getString("RUTEMPLEADOR"));
				if(rs.getString("NOMEMPLEADOR")!=null){
					oCampos.setNomEmpleador(rs.getString("NOMEMPLEADOR").replaceAll("&", "Y"));
				}else{
					oCampos.setNomEmpleador("");
				}
				oCampos.setActeco(rs.getString("ACTECO"));
				oCampos.setRegionEmpleador(rs.getString("REGIONEMPLEADOR"));
				oCampos.setComunaEmpleador(rs.getString("COMUNAEMPLEADOR"));
				oCampos.setCodEntidadAdm(rs.getString("CODENTIDADADM"));
				oCampos.setIdTipoBeneficio(rs.getString("IDTIPOBENEFICIO"));
				String fecharec= rs.getString("FECRECCAUSANTE");
				fecharec= fecharec.substring(0, 4) + "-" + fecharec.substring(4, 6) + "-" + fecharec.substring(6, 8);
				oCampos.setFecRecCausante(fecharec);
				oCampos.setFecPagoBeneficio(fecharec);
				String fechaext= rs.getString("FECEXTCAUSANTE");
				fechaext= fechaext.substring(0, 4) + "-" + fechaext.substring(4, 6) + "-" + fechaext.substring(6, 8);
				oCampos.setFecExtCausante(fechaext);
				oCampos.setPeriodo(rs.getString("PERIODO_RECHAZO"));
				oCampos.setIngPromedio(rs.getString("RENTA_PROMEDIO"));
				String tramo= rs.getString("TRAMO");
				String tramoaux= rs.getString("TRAMOAUX");
				if(tramo==null || !tramo.equals(tramoaux)){
					tramo= tramoaux;
				}
				oCampos.setTramoAsigFam(tramo);
				//oCampos.setMontoUnitarioBeneficio(rs.getString("MONTO_BENEFICIO"));
				java.sql.Date fechanac= rs.getDate("FNACCAUSANTE");
				if (fechanac!=null){
					AbsoluteDate fechanacabs = new AbsoluteDate(fechanac);
					String fechanacstr= fechaabs.toString("-");
					String fnaaaaammdd= fechanacabs.getYear() + "-" + ((fechanacabs.getMonth()<10)?"0"+fechanacabs.getMonth():""+fechanacabs.getMonth() )+ "-" + ((fechanacabs.getDay()<10)?"0"+fechanacabs.getDay():""+fechanacabs.getDay()); 
					oCampos.setfNacCausante(fnaaaaammdd);
				}else{
					oCampos.setfNacCausante("");
				}

				lista.add(oCampos);
			}
			rs.close();
			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return lista;
	}
	
	
	/**
	 * Consulta en los sistemas DB2 de La Araucana la lista de causantes a extingir.
	 * NOTA: Se asume que la causa de la extincion es siempre 21, por indicacion de Felipe Riquelme.
	 * @param oSql
	 * @return Lista de objetos DatosExtinsionTO
	 * @throws SQLException
	 */
	public List getDatosExtincion(SqlParametersTO oSql) throws SQLException {
		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		DatosExtinsionTO oCampos = null;

		////ds.openConnection();
		con = ds.getConnection();
		String sql = "select ID2," + "RUTCAUSANTE," + "FECHAEMISION," + "FECEXTCAUSANTE, FECRECCAUSANTE from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() +
		//" where ID2 = "+ oSql.getMaxid();
				" where ID2 > " + oSql.getMaxid();
		//System.out.println(sql);
		PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
		rs = press.executeQuery();
		while (rs.next()) {
			oCampos = new DatosExtinsionTO();
			oCampos.setId(rs.getString("ID2"));
			oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
			oCampos.setFechaEmision(rs.getString("FECHAEMISION"));
			oCampos.setFechaExtincion(rs.getString("FECEXTCAUSANTE"));
			oCampos.setFechaRec(rs.getString("FECRECCAUSANTE"));
			
			if(oCampos.getFechaEmision() != null && oCampos.getFechaEmision().equals(oCampos.getFechaExtincion())){
				oCampos.setCodigoExtincion("22");
			}else{
				oCampos.setCodigoExtincion("11");
			}
			lista.add(oCampos);
		}
		rs.close();
		press.close();
		return lista;
	}

	public List getDatosActualizacion(SqlParametersTO oSql) throws SQLException {
		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		CamposXmlTO oCampos = null;

		////ds.openConnection();
		con = ds.getConnection();
		String sql = "select ID2, FECHAEMISION," + "TIPOCAUSANTE," + "SEXOCAUSANTE," + "RUTCAUSANTE," + "NOMCAUSANTE," + "TIPOBENEFICIARIO," + "RUTBENEFICIARIO," + "NOMBENEFICIARIO," + "RUTEMPLEADOR," + "NOMEMPLEADOR," + "ACTECO," + "REGIONEMPLEADOR," + "COMUNAEMPLEADOR," + "CODENTIDADADM,"
				+ "IDTIPOBENEFICIO," + "FECRECCAUSANTE," + "FECPAGOBENEFICIO," + "FECEXTCAUSANTE, " + "FNACCAUSANTE from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() +
				//" where ID2 ="+ oSql.getMaxid();
				" where ID2 > " + oSql.getMaxid();
		PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
		//System.out.println(sql);
		rs = press.executeQuery();
		while (rs.next()) {
			oCampos = new CamposXmlTO();
			oCampos.setId(rs.getString("ID2"));
			oCampos.setFechaEmision(rs.getString("FECHAEMISION"));
			oCampos.setTipoCausante(rs.getString("TIPOCAUSANTE"));
			oCampos.setSexoCausante(rs.getString("SEXOCAUSANTE"));
			oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
			oCampos.setNomCausante(rs.getString("NOMCAUSANTE"));
			oCampos.setTipoBeneficiario(rs.getString("TIPOBENEFICIARIO"));
			oCampos.setRutBeneficiario(rs.getString("RUTBENEFICIARIO"));
			oCampos.setNomBeneficiario(rs.getString("NOMBENEFICIARIO"));
			oCampos.setRutEmpleador(rs.getString("RUTEMPLEADOR"));
			oCampos.setNomEmpleador(rs.getString("NOMEMPLEADOR"));
			oCampos.setActeco(rs.getString("ACTECO"));
			oCampos.setRegionEmpleador(rs.getString("REGIONEMPLEADOR"));
			oCampos.setComunaEmpleador(rs.getString("COMUNAEMPLEADOR"));
			oCampos.setCodEntidadAdm(rs.getString("CODENTIDADADM"));
			oCampos.setIdTipoBeneficio(rs.getString("IDTIPOBENEFICIO"));
			oCampos.setFecRecCausante(rs.getString("FECRECCAUSANTE"));
			oCampos.setFecPagoBeneficio(rs.getString("FECPAGOBENEFICIO"));
			oCampos.setFecExtCausante(rs.getString("FECEXTCAUSANTE"));
			oCampos.setfNacCausante(rs.getString("FNACCAUSANTE"));

			lista.add(oCampos);
		}
		rs.close();
		press.close();
		return lista;
	}

	public List getdatosvalida(SqlParametersTO oSql, String rutCausante) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		List lista = new ArrayList();
		CamposXmlTO oCampos = null;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select  " + "ID," + "FECHAEMISION," + "TUPLA," + "RUTCAUSANTE," + "RUTBENEFICIARIO," + "FECRECCAUSANTE," + "FECEXTCAUSANTE," + "CODTIPOCAUSANTE" + "  from " + oSql.getEsquemadestino() + "." + oSql.getTabladestino() + " where RUTCAUSANTE = '" + rutCausante + "'";
			//System.out.println(sql);
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			while (rs.next()) {
				oCampos = new CamposXmlTO();
				oCampos.setId(rs.getString("ID"));
				oCampos.setFechaEmision(rs.getString("FECHAEMISION"));
				oCampos.setTupla(rs.getString("TUPLA"));
				oCampos.setRutCausante(rs.getString("RUTCAUSANTE"));
				oCampos.setRutBeneficiario(rs.getString("RUTBENEFICIARIO"));
				oCampos.setFecRecCausante(rs.getString("FECRECCAUSANTE"));
				oCampos.setFecExtCausante(rs.getString("FECEXTCAUSANTE"));
				oCampos.setCodtipocausante(rs.getString("CODTIPOCAUSANTE"));

				lista.add(oCampos);
			}

			rs.close();
			press.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

		}

		return lista;
	}

	/**
	 * Metodo Deprecado
	 * @param oSql
	 * @param rut
	 * @return
	 * @throws SQLException
	 */
	public DatosExtinsionTO getdatosExtinsion(SqlParametersTO oSql, String rut) throws SQLException {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		DatosExtinsionTO oDatos = new DatosExtinsionTO();

		////ds.openConnection();
		con = ds.getConnection();
		String sql = "select ID, FECHAEMISION,FECHAINICIO,FECHATERMINO,CODIGOEXTINSION " + " from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " where RUTCAUSANTE = " + rut;

		PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
		rs = press.executeQuery();
		rs.next();

		oDatos = new DatosExtinsionTO();
		oDatos.setId(rs.getString("ID"));
		oDatos.setFechaEmision(rs.getString("FECHAEMISION"));
		//oDatos.setFechaReconocimiento(rs.getString("FECHAINICIO"));
		oDatos.setFechaExtincion(rs.getString("FECHATERMINO"));
		oDatos.setCodigoExtincion(rs.getString("CODIGOEXTINSION"));

		rs.close();
		press.close();

		return oDatos;
	}

	public int getEstadoA(int rut, String FECHAINI, String FECHAFIN, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;

		int result = 0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = " SELECT COUNT ( * ) as contador  FROM " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " WHERE AFSIRUBE = " + rut + " AND '" + FECHAINI + "' <= AFSIFEAU AND '" + FECHAFIN + "' >= AFFEEXTC";
			PreparedStatement ctmt = (PreparedStatement) con.prepareStatement(sql);

			rs = ctmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("contador");
			}

			rs.close();
			ctmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}

	public int getEstadoB(int rut, String FECHAINI, String FECHAFIN, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;

		int result = 0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "SELECT COUNT ( * ) as contador FROM " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " WHERE	AFSIRUBE = " + rut + " AND '" + FECHAINI + "' >= AFSIFEAU AND '" + FECHAINI + "' <= AFFEEXTC ";
			PreparedStatement ctmt = (PreparedStatement) con.prepareStatement(sql);
			rs = ctmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("contador");
			}

			rs.close();
			ctmt.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}

	public int getEstadoC(int rut, String FECHAINI, String FECHAFIN, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;

		int result = 0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "SELECT COUNT ( * ) as contador FROM  " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " WHERE AFSIRUBE = " + rut + " AND '" + FECHAFIN + "' > AFSIFEAU AND '" + FECHAFIN + "' <= AFFEEXTC ";
			PreparedStatement ctmt = (PreparedStatement) con.prepareStatement(sql);
			rs = ctmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("contador");
			}

			rs.close();
			ctmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}

	public int getEstadoD(int rut, String FECHAINI, String FECHAFIN, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;

		int result = 0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "SELECT COUNT ( * ) as contador FROM  " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " WHERE AFSIRUBE = " + rut + " AND '" + FECHAINI + "' > AFSIFEAU AND '" + FECHAFIN + "' < AFFEEXTC ";
			PreparedStatement ctmt = (PreparedStatement) con.prepareStatement(sql);
			rs = ctmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("contador");
			}

			rs.close();
			ctmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}

	public int getEstadoE(int rut, String FECHAINI, String FECHAFIN, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;

		int result = 0;

		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "SELECT COUNT ( * ) as contador FROM " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " WHERE AFSIRUBE = " + rut + " AND  ('" + FECHAFIN + "' < AFSIFEAU ) OR ( '" + FECHAINI + "' > AFFEEXTC )";
			PreparedStatement ctmt = (PreparedStatement) con.prepareStatement(sql);
			rs = ctmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("contador");
			}

			rs.close();
			ctmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return result;
	}

	public String getRut(int id, SqlParametersTO oSql) {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		String rut = null;
		String esquema = oSql.getEsquemaorigen();
		String tabla = oSql.getTablaorigen();
		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select RUTCAUSANTE from " + esquema + "." + tabla + " where id=" + id;
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			rs.next();

			rut = rs.getString("RUTCAUSANTE");

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return rut;
	}
	
	public String guardarTupla(TuplaTO dato, SqlParametersTO oSql) {
		try{
			guardarCabeceraTupla(dato, oSql);
			guardarCausanteTupla(dato, oSql);
			guardarBeneficiarioTupla(dato, oSql);
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
	/**
	 * 
	 * @param dato
	 * @param oSql
	 * @return Si graba ok, retorna NULL. Caso contrario, retorna el mensaje de error
	 * @throws SQLException 
	 */
	public void guardarCabeceraTupla(TuplaTO dato, SqlParametersTO oSql) throws SQLException {

		//		CustomDataSource cds = new CustomDataSource();

		Connection conexion = null;

			////ds.openConnection();
			CallableStatement ctmt=null;
			try {
				conexion = ds.getConnection();
				String sql = "INSERT INTO " + oSql.getEsquemadestino() + "." + oSql.getTablatupla() + "( RUTCAUSANT, IDTUPLA, RUTBENEFIC, FECRECCAUS, FECPAGBENE, FECEXTCAUS, CODENTADM, CODTIPBENE, CODTIPCAUS, CODEXTCAUS, RUTEMPBENE, CODESTTUPL	) " 
						+ "	VALUES " + "	( ?,?,?,?,?,?,?,?,?,?,?,?)";	    	
				ctmt = conexion.prepareCall(sql);
				
				ctmt.setString(1, dato.getRutCausante());
				ctmt.setInt(2, Integer.parseInt(dato.getId()));
				ctmt.setString(3, dato.getRutBeneficiario());
				ctmt.setString(4, dato.getFecRecCausante().replace("-", ""));
				ctmt.setString(5, dato.getFecPagoBeneficio().replace("-", ""));
				ctmt.setString(6, dato.getFecExtCausante().replace("-", ""));
				ctmt.setInt(7, Integer.parseInt(dato.getCodEntidadAdm()));
				ctmt.setInt(8, Integer.parseInt(dato.getCodTipoBeneficio()));
				ctmt.setInt(9, Integer.parseInt(dato.getCodTipoCausante()));
				ctmt.setString(10, dato.getCausaExtCausante().trim());
				ctmt.setString(11, dato.getRutEmpleador());
				ctmt.setInt(12, Integer.parseInt(dato.getCodEstadoTupla()));

				ctmt.execute();
			} catch (NumberFormatException e) {
				throw new SQLException(e.getMessage());
			}finally{
				ctmt.close();
			}
		
	}
	
	/**
	 * 
	 * @param dato
	 * @param oSql
	 * @return Si graba ok, retorna NULL. Caso contrario, retorna el mensaje de error
	 * @throws SQLException 
	 */
	public void guardarCausanteTupla(TuplaTO dato, SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
		
		////ds.openConnection();
		//	CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTUPLA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");	    	
		CallableStatement ctmt= null;;
		try {
			conexion = ds.getConnection();
			String sql = "INSERT INTO " + oSql.getEsquemadestino() + "." + oSql.getTablacausante() + "(RUTCAUSANT, IDTUPLA, DVCAUSANTE, NOMCAUSANT, SEXCAUSANT, FECNACCAUS, CODREGCAUS, NOMREGCAUS, CODCOMCAUS, NOMCOMCAUS )" 
					+ "	VALUES " + "	( ?,?,?,?,?,?,?,?,?,?)";
			ctmt = conexion.prepareCall(sql);

			ctmt.setString(1, dato.getRutCausante());
			ctmt.setInt(2, Integer.parseInt(dato.getId()));
			ctmt.setString(3, dato.getDVCausante());
			if (dato.getNomCausante().trim().length() > 99)
				ctmt.setString(4, dato.getNomCausante().trim().substring(0, 99));
			else
				ctmt.setString(4, dato.getNomCausante().trim());

			ctmt.setString(5, dato.getSexoCausante());
			ctmt.setString(6, dato.getFecNacCausante().replace("-", ""));
			ctmt.setInt(7, Integer.parseInt(dato.getCodRegionCausante()));
			if (dato.getNomRegionCausante().trim().length() > 50)
				ctmt.setString(8, dato.getNomRegionCausante().trim().substring(0, 50));
			else
				ctmt.setString(8, dato.getNomRegionCausante().trim());

			ctmt.setInt(9, Integer.parseInt(dato.getCodComunaCausante()));
			if (dato.getNomComunaCausante().trim().length() > 99)
				ctmt.setString(10, dato.getNomComunaCausante().trim().substring(0, 99));
			else
				ctmt.setString(10, dato.getNomComunaCausante().trim());

			ctmt.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}finally{
			ctmt.close();
		}
		
	}
	
	/**
	 * 
	 * @param dato
	 * @param oSql
	 * @return Si graba ok, retorna NULL. Caso contrario, retorna el mensaje de error
	 * @throws SQLException 
	 */
	public void guardarBeneficiarioTupla(TuplaTO dato, SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;

			////ds.openConnection();
			//	CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTUPLA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");	    	
			CallableStatement ctmt=null;
			try {
				conexion = ds.getConnection();
				String sql = "INSERT INTO " + oSql.getEsquemadestino() + "." + oSql.getTablabeneficiario() + "(RUTBENEFIC, RUTCAUSANT, IDTUPLA, DVBENEFIC, NOMBENEFIC, CODTIPBENF, NOMTIPBENF, CODREGBENF, NOMREGBENF, CODCOMBENF, NOMCOMBENF, INGPROMED, RUTEMPL, DVEMPL, NOMEMPL, ACTECO, CODREGEMPL, NOMREGEMPL, CODCOMEMPL, NOMCOMEMPL) " 
						+ "	VALUES " + "	( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ctmt = conexion.prepareCall(sql);
				
				ctmt.setString(1, dato.getRutBeneficiario());
				ctmt.setString(2, dato.getRutCausante());
				ctmt.setInt(3, Integer.parseInt(dato.getId()));
				
				ctmt.setString(4, dato.getDVBeneficiario());
				if (dato.getNomBeneficiario().trim().length() > 99)
					ctmt.setString(5, dato.getNomBeneficiario().trim().substring(0, 99));
				else
					ctmt.setString(5, dato.getNomBeneficiario().trim());
				ctmt.setInt(6, Integer.parseInt(dato.getCodTipoBeneficiario()));
				if (dato.getNomTipoBeneficiario().trim().length() > 30)
					ctmt.setString(7, dato.getNomTipoBeneficiario().trim().substring(0, 30));
				else
					ctmt.setString(7, dato.getNomTipoBeneficiario().trim());
					
				ctmt.setInt(8, Integer.parseInt(dato.getCodRegionBeneficiario()));

				if (dato.getNomRegionBeneficiario().trim().length() > 50)
					ctmt.setString(9, dato.getNomRegionBeneficiario().trim().substring(0, 50));
				else
					ctmt.setString(9, dato.getNomRegionBeneficiario().trim());

				ctmt.setInt(10, Integer.parseInt(dato.getCodComunaBeneficiario()));
				if (dato.getNomComunaBeneficiario().trim().length() > 30)
					ctmt.setString(11, dato.getNomComunaBeneficiario().trim().substring(0, 30));
				else
					ctmt.setString(11, dato.getNomComunaBeneficiario().trim());

				ctmt.setInt(12, Integer.parseInt(dato.getIngPromedio().trim()));
				ctmt.setString(13, dato.getRutEmpleador());
				ctmt.setString(14, dato.getDVEmpleador());
				
				if (dato.getNomEmpleador().trim().length() > 50)
					ctmt.setString(15, dato.getNomEmpleador().trim().substring(0, 50));
				else
					ctmt.setString(15, dato.getNomEmpleador().trim());

				ctmt.setInt(16, Integer.parseInt(dato.getActeco()));
				ctmt.setInt(17, Integer.parseInt(dato.getCodRegionEmpleador()));

				if (dato.getNomRegionEmpleador().trim().length() > 50)
					ctmt.setString(18, dato.getNomRegionEmpleador().trim().substring(0, 50));
				else
					ctmt.setString(18, dato.getNomRegionEmpleador().trim());

				ctmt.setInt(19, Integer.parseInt(dato.getCodComunaEmpleador()));
				if (dato.getNomComunaEmpleador().trim().length() > 50)
					ctmt.setString(20, dato.getNomComunaEmpleador().trim().substring(0, 50));
				else
					ctmt.setString(20, dato.getNomComunaEmpleador().trim());

				ctmt.execute();
			} catch (SQLException e) {
				throw new SQLException(e.getMessage());
			}finally{
				ctmt.close();
			}

	}
	
	
	public List consultaRechazoTuplas(SqlParametersTO oSql, String opciones){
		Connection conexion = null;
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		ActualizaTuplaVO data= null;
		List rechazos= new ArrayList();
		try {

			conexion = ds.getConnection();
			StringBuffer buf= new StringBuffer();
			buf.append("select distinct a.id, b.idtupla, a.rutcausa, ");
			if(opciones.indexOf("P")>-1){
				buf.append("c.periodo as Periodo_Tramo, ");
				buf.append("(case when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) < 7  THEN int(left(finiben, 4))-1 ");
				buf.append("	 when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ");
				buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) < 8 THEN int(left(finiben, 4))-1 ");
				buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) >= 8 THEN left(finiben, 4) ");
				buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) < 7 THEN left(finiben, 4) || '01' ");
				buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ||'02' ");
				buf.append("end) as Periodo_Rechazo, ");
			}
			if(opciones.indexOf("C")>-1){
				buf.append("b.codtipcaus, a.codtcau as Causante_Rechazo, ");
			}
			buf.append("b.codentadm, '10105' as Entidad_Rechazo, ");
			if(opciones.indexOf("B")>-1){
				buf.append("b.rutbeneficiario, a.rutbenef as RutBeneficiario_Rechazo, ");
			}
			if(opciones.indexOf("T")>-1){
				buf.append("c.numtramo as Tramo, a.codtramo as Tramo_Rechazo, ");
			}
			if(opciones.indexOf("M")>-1){
				buf.append("c.montobeneficio as Monto, a.monbenef as Monto_Rechazo, ");
			}
			if(opciones.indexOf("E")>-1){
				buf.append("b.rutempbene as RutEmpleador, a.rutempl as RutEmpleador_Rechazo,  ");
			}
			buf.append("b.fecreccaus, b.codtipbene, b.codesttupl ");
			buf.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a left join " + oSql.getEsquemadestino() + "." + oSql.getTabladestino() + " b ");
			buf.append("on a.rutcausa= b.rutcausante ");
			//buf.append("and a.codtcau= b.codtipcaus ");
			buf.append("and (a.finiben between b.fecreccaus and b.fecextcaus ");
			buf.append("or a.fterben between b.fecreccaus and b.fecextcaus) ");
			buf.append("and (b.codtipcaus is null or b.codtipcaus not in (21, 22)) ");
			buf.append("left join " + oSql.getEsquemadestino() + "." + oSql.getTablatramo() + " c ");
			buf.append("on b.rutcausante= c.rutcausante ");
			buf.append("and b.idtupla= c.idtupla ");
			buf.append("and c.periodo=  ");
			buf.append("(case when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) < 7  THEN int(left(finiben, 4))-1 ");
			buf.append("when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ");
			buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) < 8 THEN int(left(finiben, 4))-1 ");
			buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) >= 8 THEN left(finiben, 4) ");
			buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) < 7 THEN left(finiben, 4) || '01' ");
			buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ||'02' ");
			buf.append("end) ");
			
			if(opciones.indexOf("C")>-1){
				buf.append("where a.codtcau not in (21, 22) ");
				if(!oSql.getMaxid().equals("0")){
					buf.append("and a.id >= " + oSql.getMaxid() + " ");
				}
			}else{
				if(!oSql.getMaxid().equals("0")){
					buf.append("where a.id >= " + oSql.getMaxid() + " ");
				}
			}
			
			buf.append("UNION ");
			buf.append("select distinct a.id, b.idtupla, a.rutcausa, ");
			if(opciones.indexOf("P")>-1){
				buf.append("c.periodo as Periodo_Tramo, ");
				buf.append("(case when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) < 7  THEN int(left(finiben, 4))-1 ");
				buf.append("	 when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ");
				buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) < 8 THEN int(left(finiben, 4))-1 ");
				buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) >= 8 THEN left(finiben, 4) ");
				buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) < 7 THEN left(finiben, 4) || '01' ");
				buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ||'02' ");
				buf.append("end) as Periodo_Rechazo, ");
			}
			if(opciones.indexOf("C")>-1){
				buf.append("b.codtipcaus, a.codtcau as Causante_Rechazo, ");
			}
			buf.append("b.codentadm, '10105' as Entidad_Rechazo, ");
			if(opciones.indexOf("B")>-1){
				buf.append("b.rutbeneficiario, a.rutbenef as RutBeneficiario_Rechazo, ");
			}
			if(opciones.indexOf("T")>-1){
				buf.append("c.numtramo as Tramo, a.codtramo as Tramo_Rechazo, ");
			}
			if(opciones.indexOf("M")>-1){
				buf.append("c.montobeneficio as Monto, a.monbenef as Monto_Rechazo, ");
			}
			if(opciones.indexOf("E")>-1){
				buf.append("b.rutempbene as RutEmpleador, a.rutempl as RutEmpleador_Rechazo,  ");
			}
			buf.append("b.fecreccaus, b.codtipbene, b.codesttupl ");
			buf.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a left join " + oSql.getEsquemadestino() + "." + oSql.getTabladestino() + " b ");
			buf.append("on a.rutcausa= b.rutcausante ");
			//buf.append("and a.codtcau= b.codtipcaus ");
			buf.append("and (a.finiben between b.fecreccaus and b.fecextcaus ");
			buf.append("or a.fterben between b.fecreccaus and b.fecextcaus) ");
			buf.append("and (b.codtipcaus is null or b.codtipcaus in (21, 22)) ");
			buf.append("left join " + oSql.getEsquemadestino() + "." + oSql.getTablatramo() + " c ");
			buf.append("on b.rutcausante= c.rutcausante ");
			buf.append("and b.idtupla= c.idtupla ");
			buf.append("and c.periodo=  ");
			buf.append("(case when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) < 7  THEN int(left(finiben, 4))-1 ");
			buf.append("when finiben<20160101 and left(finiben, 4)!=2010 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ");
			buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) < 8 THEN int(left(finiben, 4))-1 ");
			buf.append("when left(finiben, 4)=2010 and int(substr(finiben, 5, 2)) >= 8 THEN left(finiben, 4) ");
			buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) < 7 THEN left(finiben, 4) || '01' ");
			buf.append("when finiben>=20160101 and int(substr(finiben, 5, 2)) >= 7 THEN left(finiben, 4) ||'02' ");
			buf.append("end) ");
			
			if(opciones.indexOf("C")>-1){
				buf.append("where a.codtcau  in (21, 22) ");
				if(!oSql.getMaxid().equals("0")){
					buf.append("and a.id >= " + oSql.getMaxid()  + " ");
				}
			}else{
				if(!oSql.getMaxid().equals("0")){
					buf.append("where a.id >= " + oSql.getMaxid() + " ");
				}
			}
			String sql= buf.toString();
			
			pstmt = (PreparedStatement) conexion.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				data= new ActualizaTuplaVO();
				data.setId(rs.getInt("id"));
				data.setIdTupla(rs.getString("idtupla"));
				data.setRutCausante(rs.getInt("rutcausa"));
				data.setEstadoTupla(rs.getString("codesttupl"));
				if(opciones.indexOf("C")>-1){
					data.setCodigoCausante(rs.getString("codtipcaus"));
					data.setCodigoCausanteRechazo(rs.getString("Causante_Rechazo"));
				}
				data.setCodigoEntidad(rs.getString("codentadm"));
				data.setCodigoEntidadRechazo(rs.getString("Entidad_Rechazo"));
				data.setFechaReconocimiento(rs.getString("fecreccaus"));
				if(opciones.indexOf("B")>-1){
					data.setRutBeneficiario(rs.getString("rutbeneficiario"));
					data.setRutBeneficiarioRechazo(rs.getString("RutBeneficiario_Rechazo"));
				}
				data.setCodigoBeneficio(rs.getString("codtipbene"));
				if(opciones.indexOf("P")>-1){
					data.setPeriodoTramo(rs.getString("Periodo_Tramo"));
					data.setPeriodoRechazo(rs.getString("Periodo_Rechazo"));
				}
				if(opciones.indexOf("T")>-1){
					data.setTramoTupla(rs.getString("Tramo"));
					data.setTramoRechazo(rs.getString("Tramo_Rechazo"));
				}
				if(opciones.indexOf("M")>-1){
					data.setMontoTramo(rs.getString("Monto"));
					data.setMontoTramoRechazo(rs.getString("Monto_Rechazo"));
				}
				if(opciones.indexOf("E")>-1){
					data.setRutEmpleador(rs.getString("RutEmpleador"));
					data.setRutEmpleadorRechazo(rs.getString("RutEmpleador_Rechazo"));
				}
				rechazos.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs!= null){
					rs.close();
				}
				if(pstmt!= null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rechazos;	
	}
	
	public List selectedTramosRechazo(SqlParametersTO oSql, String opciones, String acciones){
		Connection conexion = null;
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		List<CamposXmlTO> rechazo_tramos= new ArrayList<CamposXmlTO>();
		try {
			StringBuffer condiciones= new StringBuffer();
			conexion = ds.getConnection();
			StringBuffer buf= new StringBuffer();
			buf.append("select a.id, d.idtupla, a.rutcausa, upper(a.dvcausa) as dvcausa, b.codentadm, b.fecreccaus, b.codtipbene, d.periodo_rechazo, c.numtramo as Tramo, ");
			buf.append("case when b.codesttupl= '3' then b.codtipcaus else a.codtcau end as CodigoCausante, ");
			
			if(opciones.indexOf("T")>-1){
				//correctivo (stock y flujo si incluyen tramo)
				buf.append("a.codtramo as Tramo_Rechazo, ");
			}else{
				//mensual
				buf.append("af9.af2ha as Tramo_Rechazo, ");
			}
			
			buf.append("a.rutbenef, ben.dvbeneficiario, ");
			if(opciones.indexOf("M")>-1){
				buf.append("a.monbenef as Monto_Beneficio, ");
			}
			if (acciones.indexOf("SV")>-1){
				buf.append("a.codarchi, ");
			}
			buf.append("case when d.causante=1 and (d.periodo + d.tramo + d.monto)=0 then 1 else 0 end as causante, ");
			buf.append("d.periodo, COALESCE(e.AF27A, 0) as Renta_Promedio, b.codesttupl ");
			buf.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo() + " d ");
			buf.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen()+ " a "); //a es TOTRECHAZT
			buf.append("on d.id= a.id ");
			buf.append("join " + oSql.getEsquemadestino() + "." + oSql.getTabladestino() + " b "); //b es Tuplas
			buf.append("on d.idtupla= b.idtupla ");
			buf.append("left join " + oSql.getEsquemadestino() + "." + oSql.getTablatramo() + " c ");
			buf.append("on d.idtupla= c.idtupla ");
			buf.append("and d.periodo_rechazo= c.periodo ");
			buf.append("left join AFDTA.AFP68F1	e ");
			buf.append("on e.SE5FAJC = a.rutbenef ");
			buf.append("and e.AF2GA= d.fechatramo ");
			if(opciones.indexOf("T")==-1){
				buf.append("left join afdta.af09f1 af9 ");
				buf.append("on a.rutbenef = af9.se5fajc ");
				buf.append("and d.fechatramo= af9.af2ga ");
			}
			buf.append("join " + oSql.getEsquemadestino() + ".BENEFICIARIO ben "); 
			buf.append("on ben.rutbeneficiario= a.rutbenef ");
			buf.append("and ben.idtupla= d.idtupla ");
			buf.append("where (");
			
			if(opciones.indexOf("P") >-1){
				condiciones.append("d.periodo+");
			}
			if(opciones.indexOf("T") >-1){
				condiciones.append("d.tramo+");
			}
			if(opciones.indexOf("M") >-1){
				condiciones.append("d.monto+");
			}
			if(opciones.indexOf("C") >-1){
				condiciones.append("d.causante+");
			}
			buf.append(condiciones.toString().substring(0, condiciones.length()-1));
			
			buf.append(")>=1 ");
			buf.append("and (beneficiario + empleador)=0 ");
			buf.append("and d.codigo= '99' ");
			buf.append("and d.periodo_rechazo between ? and ? ");

			String sql= buf.toString();
			System.out.println("Query Casos Actualización: " + sql);
			pstmt = (PreparedStatement) conexion.prepareStatement(sql);
			pstmt.setInt(1, oSql.getMinperiodo());
			pstmt.setInt(2, oSql.getMaxperiodo());
			rs = pstmt.executeQuery();
			while(rs.next()){
				CamposXmlTO oCampo = new CamposXmlTO();
				oCampo.setId(rs.getString("id"));
				oCampo.setTupla(rs.getString("idtupla"));
				if (acciones.indexOf("SV")>-1){
					oCampo.setCodigoArchivo(rs.getString("codarchi"));
				}
				oCampo.setRutCausante(rs.getInt("rutcausa") + "-" + rs.getString("dvcausa"));
				oCampo.setCodtipocausante(rs.getString("CodigoCausante"));
				oCampo.setCodEntidadAdm(rs.getString("codentadm"));
				oCampo.setFecRecCausante(rs.getString("fecreccaus"));
				oCampo.setRutBeneficiario(rs.getString("rutbenef") + "-" + rs.getString("dvbeneficiario"));
				oCampo.setIdTipoBeneficio(rs.getString("codtipbene"));
				oCampo.setPeriodo(rs.getString("periodo_rechazo"));
				String tramo_tupla= rs.getString("Tramo");
				if(tramo_tupla==null){ // No existe el periodo
					tramo_tupla="5";
					oCampo.setActualizaSIAGF(true);
				}
				int j=0;
				oCampo.setTramoAsigFam(tramo_tupla);
				String tramoRechazo= rs.getString("Tramo_Rechazo");
				if(tramoRechazo== null || Integer.parseInt(tramoRechazo)==0){
					tramoRechazo="5";
				}
				if(Integer.parseInt(tramo_tupla)>Integer.parseInt(tramoRechazo)){
					oCampo.setActualizaSIAGF(true);
					oCampo.setTramoAsigFam(tramoRechazo);
				}
				if(rs.getInt("periodo")==1){
					oCampo.setActualizaSIAGF(true);
				}
				if(opciones.indexOf("M")>-1){
					oCampo.setMontoUnitarioBeneficio(rs.getString("Monto_Beneficio"));
				}
				oCampo.setCausante(rs.getInt("causante")==1?true:false);
				oCampo.setIngPromedio(rs.getString("Renta_Promedio"));
				oCampo.setEstadoTupla(rs.getString("codesttupl"));
				rechazo_tramos.add(oCampo);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				if(rs!= null){
					rs.close();
				}
				if(pstmt!= null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rechazo_tramos;	
	}
		
	public boolean guardarCausaRechazo(ActualizaTuplaVO dato, SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
		
		////ds.openConnection();
		//	CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTUPLA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");	    	
		CallableStatement ctmt= null;;
		int datopos=0;
		try {
			conexion = ds.getConnection();
			String sql = "INSERT INTO " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo() + "(ID, IDTUPLA, PERIODO_RECHAZO, FECHATRAMO, ENTIDAD, TUPLA, PERIODO, TRAMO, MONTO, CAUSANTE, BENEFICIARIO, EMPLEADOR )" 
					+ "	VALUES " + "	( ?,?,?,?,?,?,?,?,?,?,?,?)";
			ctmt = conexion.prepareCall(sql);
			datopos++;
			ctmt.setInt(1, dato.getId());
			datopos++;
			ctmt.setInt(2, Integer.parseInt(dato.getIdTupla()));
			datopos++;
			ctmt.setInt(3, Integer.parseInt(dato.getPeriodoRechazo()));
			datopos++;
			java.sql.Date sqlDate = java.sql.Date.valueOf( getFechaTramo(dato.getPeriodoRechazo()));
			ctmt.setDate(4, sqlDate);
			datopos++;
			ctmt.setInt(5, dato.isEntidad()?1:0);
			datopos++;
			ctmt.setInt(6, dato.isTupla()?1:0);
			datopos++;
			ctmt.setInt(7, dato.isPeriodo()?1:0);
			datopos++;
			ctmt.setInt(8, dato.isTramo()?1:0);
			datopos++;
			ctmt.setInt(9, dato.isMonto()?1:0);
			datopos++;
			ctmt.setInt(10, dato.isCausante()?1:0);
			datopos++;
			ctmt.setInt(11, dato.isBeneficiario()?1:0);
			datopos++;
			ctmt.setInt(12, dato.isEmpleador()?1:0);
			
			ctmt.execute();
			return true;
		} catch (SQLException e) {
			//throw new SQLException(e.getMessage());
			e.printStackTrace();
			System.out.println("Posible Campo error:" + datopos);
			return false;
		}finally{
			ctmt.close();
			
		}
		
	}
	
	public int update011_012(List<CamposXmlTO> lista, SqlParametersTO oSql, String tabla){
		Connection conexion = null;
		int[] count=null;
		int upds=0;
		PreparedStatement pstmt= null;
		try {

			conexion = ds.getConnection();
			StringBuffer buf= new StringBuffer();
			if(tabla.equals("11")){
				buf.append("update  " + oSql.getEsquemaorigen() + "." + oSql.getTabla011()  + " " );
			}else{
				buf.append("update  " + oSql.getEsquemaorigen() + "." + oSql.getTabla012()  + " " );
			}
			buf.append("set codtramo=? , monbenef=?, codtcau=? ");
			buf.append("where id = ? ");			
			
			String sql= buf.toString();
			pstmt = (PreparedStatement) conexion.prepareStatement(sql);
			
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				CamposXmlTO oCampo = (CamposXmlTO) iterator.next();
				if(oCampo.getCodigoArchivo().equals(tabla)){

					int valor= Integer.parseInt(oCampo.getMontoUnitarioBeneficio());
					if(oCampo.getDiferencia()>=0){
						valor=Integer.parseInt(oCampo.getValorTramo());
					}

					pstmt.setInt(1, Integer.parseInt(oCampo.getTramoAsigFam()));
					pstmt.setInt(2, valor);
					pstmt.setInt(3, Integer.parseInt(oCampo.getCodtipocausante()));
					pstmt.setInt(4, Integer.parseInt(oCampo.getId()));
					pstmt.addBatch();
				}
			}
			// Ejecutamos el batch, devuelve un array de forma que cada posicion
	         // contiene
	         // el numero de filas afectadas por cada insert.
	         count = pstmt.executeBatch();
	         for (int i = 0; i < count.length; i++) {
	        	 upds+= count[i];
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				
				if(pstmt!= null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return upds;
	}
	
	public int delete011_012(CamposXmlTO oCampo, SqlParametersTO oSql){
		Connection conexion = null;
		int count=0;
		PreparedStatement pstmt= null;
		try {

			conexion = ds.getConnection();
			String tabla= oCampo.getCodigoArchivo();
			if(tabla.equals("11")){
				tabla= oSql.getTabla011();
			}else{
				tabla= oSql.getTabla012();
			}
			String sql= "delete from  " + oSql.getEsquemaorigen() + "." + tabla  + " where id = ? " ;		
			
			pstmt = (PreparedStatement) conexion.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(oCampo.getId()));
			count= pstmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				
				if(pstmt!= null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int updateDiferencia(List<CamposXmlTO> lista, SqlParametersTO oSql){
		Connection conexion = null;
		int[] count;
		int upds=0;
		PreparedStatement pstmt= null;
		try {

			conexion = ds.getConnection();
			StringBuffer buf= new StringBuffer();
			buf.append("update  " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo() + " " );
			buf.append("set diferencia=? , codigo= ? ");
			buf.append("where id = ? and idtupla= ? ");			
			
			String sql= buf.toString();
			pstmt = (PreparedStatement) conexion.prepareStatement(sql);
			
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				CamposXmlTO oCampo = (CamposXmlTO) iterator.next();
				pstmt.setInt(1, oCampo.getDiferencia());
				pstmt.setString(2, "0");
				pstmt.setInt(3, Integer.parseInt(oCampo.getId()));
				pstmt.setInt(4, Integer.parseInt(oCampo.getTupla()));
				pstmt.addBatch();
			}
			
			count= pstmt.executeBatch();
			
			for (int i = 0; i < count.length; i++) {
				upds+= count[i];
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try {
				
				if(pstmt!= null){
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return upds;
	}
	
	/**
	 * 
	 * @param dato
	 * @param oSql
	 * @return Si graba ok, retorna NULL. Caso contrario, retorna el mensaje de error
	 */
	public String saveTupla(TuplaTO dato, SqlParametersTO oSql) {

		//		CustomDataSource cds = new CustomDataSource();

		Connection conexion = null;

		try {

			////ds.openConnection();
			conexion = ds.getConnection();
			String sql = "INSERT INTO " + oSql.getEsquemadestino() + "." + oSql.getTablatupla() + "(	ID , " + "	FECEMISION ," + "	TUPLA , " + "	TRACKID , " + "	CODESTTUPL , " + "	NOMESTTUPL , " + "	CODTIPCAUS , " + "	NOMTIPCAUS , " + "	RUTCAUSANT , " + "	NOMCAUSANT , "
					+ "	SEXCAUSANT , " + "	FECNACCAUS , " + "	CODREGCAUS , " + "	NOMREGCAUS , " + "	CODCOMCAUS , " + "	NOMCOMCAUS , " + "	CODTIPBENF , " + "	NOMTIPBENF , " + "	RUTBENEFIC , " + "	NOMBENEFIC , "
					+ "	CODREGBENF ," + "	NOMREGBENF , " + "	CODCOMBENF , " + "	NOMCOMBENF , " + "	INGPROMED , " + "	RUTEMPLDOR , " + "	NOMEMPLDOR , " + "	ACTECO , " + "	CODREGEMPL ," + "	NOMREGEMPL , " + "	CODCOMEMPL , "
					+ "	NOMCOMEMPL , " + "	CODENTADM , " + "	NOMENTADM , " + "	CODTIPBENE , " + "	NOMTIPBENE , " + "	FECRECCAUS , " + "	FECPAGBENE , " + "	MTOBENEFIC ," + "	PJEFICHPRO , " + "	TRAMOASFAM , " + "	FECEXTCAUS , "
					+ "	CAUEXTCAUS , " + "	GLOEXTCAUS, " + "	ESTADO , " + "	AFSIRUBE , " + "	DVBENE , " + "	AFILIADO , " + "	DVAFILIADO , " 
					+ " TRAMO1 , " + " TRAMO2 , " + " TRAMO3 , " + " TRAMO4 " + " 	) " 
					+ "	VALUES " + "	( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//	CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTUPLA (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");	    	
			CallableStatement ctmt = conexion.prepareCall(sql);

			//	ctmt.setString(1, oSql.getEsquemadestino());
			//	ctmt.setString(2, oSql.getTablatupla());
			ctmt.setInt(1, Integer.parseInt(dato.getId()));
			ctmt.setString(2, dato.getFechaEmision().replace("-", ""));
			ctmt.setInt(3, Integer.parseInt(dato.getTupla()));
			ctmt.setInt(4, Integer.parseInt(dato.getTrackID()));
			ctmt.setInt(5, Integer.parseInt(dato.getCodEstadoTupla()));
			if (dato.getNomEstadoTupla().trim().length() > 98)
				ctmt.setString(6, dato.getNomEstadoTupla().trim().substring(0, 99));
			else
				ctmt.setString(6, dato.getNomEstadoTupla().trim());
			ctmt.setInt(7, Integer.parseInt(dato.getCodTipoCausante()));
			if (dato.getNomTipoCausante().trim().length() > 98)
				ctmt.setString(8, dato.getNomTipoCausante().trim().substring(0, 99));
			else
				ctmt.setString(8, dato.getNomTipoCausante().trim());

			ctmt.setString(9, dato.getRutCausante());
			if (dato.getNomCausante().trim().length() > 98)
				ctmt.setString(10, dato.getNomCausante().trim().substring(0, 99));
			else
				ctmt.setString(10, dato.getNomCausante().trim());

			ctmt.setString(11, dato.getSexoCausante());
			ctmt.setString(12, dato.getFecNacCausante().replace("-", ""));
			ctmt.setInt(13, Integer.parseInt(dato.getCodRegionCausante()));
			if (dato.getNomRegionCausante().trim().length() > 98)
				ctmt.setString(14, dato.getNomRegionCausante().trim().substring(0, 98));
			else
				ctmt.setString(14, dato.getNomRegionCausante().trim());

			ctmt.setInt(15, Integer.parseInt(dato.getCodComunaCausante()));
			if (dato.getNomComunaCausante().trim().length() > 98)
				ctmt.setString(16, dato.getNomComunaCausante().trim().substring(0, 99));
			else
				ctmt.setString(16, dato.getNomComunaCausante().trim());
			ctmt.setInt(17, Integer.parseInt(dato.getCodTipoBeneficiario()));
			if (dato.getNomTipoBeneficiario().trim().length() > 98)
				ctmt.setString(18, dato.getNomTipoBeneficiario().trim().substring(0, 99));
			else
				ctmt.setString(18, dato.getNomTipoBeneficiario().trim());

			ctmt.setString(19, dato.getRutBeneficiario());
			if (dato.getNomBeneficiario().trim().length() > 98)
				ctmt.setString(20, dato.getNomBeneficiario().trim().substring(0, 99));
			else
				ctmt.setString(20, dato.getNomBeneficiario().trim());

			ctmt.setInt(21, Integer.parseInt(dato.getCodRegionBeneficiario()));

			if (dato.getNomRegionBeneficiario().trim().length() > 98)
				ctmt.setString(22, dato.getNomRegionBeneficiario().trim().substring(0, 99));
			else
				ctmt.setString(22, dato.getNomRegionBeneficiario().trim());

			ctmt.setInt(23, Integer.parseInt(dato.getCodComunaBeneficiario()));
			if (dato.getNomComunaBeneficiario().trim().length() > 98)
				ctmt.setString(24, dato.getNomComunaBeneficiario().trim().substring(0, 99));
			else
				ctmt.setString(24, dato.getNomComunaBeneficiario().trim());

			ctmt.setInt(25, Integer.parseInt(dato.getIngPromedio().trim()));
			ctmt.setString(26, dato.getRutEmpleador());

			if (dato.getNomEmpleador().trim().length() > 98)
				ctmt.setString(27, dato.getNomEmpleador().trim().substring(0, 99));
			else
				ctmt.setString(27, dato.getNomEmpleador().trim());

			ctmt.setInt(28, Integer.parseInt(dato.getActeco()));
			ctmt.setInt(29, Integer.parseInt(dato.getCodRegionEmpleador()));

			if (dato.getNomRegionEmpleador().trim().length() > 98)
				ctmt.setString(30, dato.getNomRegionEmpleador().trim().substring(0, 99));
			else
				ctmt.setString(30, dato.getNomRegionEmpleador().trim());

			ctmt.setInt(31, Integer.parseInt(dato.getCodComunaEmpleador()));
			if (dato.getNomComunaEmpleador().trim().length() > 98)
				ctmt.setString(32, dato.getNomComunaEmpleador().trim().substring(0, 99));
			else
				ctmt.setString(32, dato.getNomComunaEmpleador().trim());

			ctmt.setInt(33, Integer.parseInt(dato.getCodEntidadAdm()));
			if (dato.getNomEntidadAdm().trim().length() > 98)
				ctmt.setString(34, dato.getNomEntidadAdm().trim().substring(0, 99));
			else
				ctmt.setString(34, dato.getNomEntidadAdm().trim());

			ctmt.setInt(35, Integer.parseInt(dato.getCodTipoBeneficio()));
			if (dato.getNomTipoBeneficio().trim().length() > 98)
				ctmt.setString(36, dato.getNomTipoBeneficio().trim().substring(0, 99));
			else
				ctmt.setString(36, dato.getNomTipoBeneficio().trim());

			ctmt.setString(37, dato.getFecRecCausante().replace("-", ""));
			ctmt.setString(38, dato.getFecPagoBeneficio().replace("-", ""));
			ctmt.setInt(39, Integer.parseInt(dato.getMontoUnitarioBeneficio()));
			ctmt.setInt(40, Integer.parseInt(dato.getPuntajeFichaProtSocial()));
			ctmt.setInt(41, Integer.parseInt(dato.getTramoAsigFam()));
			ctmt.setString(42, dato.getFecExtCausante().replace("-", ""));
			if (dato.getCausaExtCausante().trim().length() > 98)
				ctmt.setString(43, dato.getCausaExtCausante().trim().substring(0, 99));
			else
				ctmt.setString(43, dato.getCausaExtCausante().trim());
			if (dato.getGlosaExtCausante().trim().length() > 98)
				ctmt.setString(44, dato.getGlosaExtCausante().trim().substring(0, 99));
			else
				ctmt.setString(44, dato.getGlosaExtCausante().trim());
			ctmt.setString(45, dato.getEstado());
			
			ctmt.setInt(46, Integer.parseInt(dato.getAfsiRube().trim()));
			ctmt.setString(47, dato.getDvBene().trim());
			ctmt.setInt(48, Integer.parseInt(dato.getAfiliado().trim()));
			ctmt.setString(49, dato.getDvAfiliado().trim());
			
			ctmt.setInt(50, Integer.parseInt(dato.getTramo1().trim()));
			ctmt.setInt(51, Integer.parseInt(dato.getTramo2().trim()));
			ctmt.setInt(52, Integer.parseInt(dato.getTramo3().trim()));
			ctmt.setInt(53, Integer.parseInt(dato.getTramo4().trim()));

			ctmt.execute();

			ctmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();

		}

		return null;
	}

	/**
	 * 
	 * @param tramo
	 * @param oSql
	 * @return Null si es exitoso. En caso contrario, retorna el mensaje de error
	 */
	public String saveTramo(TramoTO tramo, SqlParametersTO oSql) {

		//	CustomDataSource ds = new CustomDataSource();

		Connection conexion = null;

		try {

			////ds.openConnection();
			conexion = ds.getConnection();
			String sql = "INSERT	INTO " + oSql.getEsquemadestino() + "." + oSql.getTablatramo() + "(ID ,TUPLA ,RUTCAUSANTE ,PERIODO ,NUMTRAMO ,INGPROMEDIO ,MONTOUNITARIOBENEFICIO)" + "VALUES	(?,?,?,?,?,?,?)";

			//CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTRAMO (?,?,?,?,?,?,?,?,?) }");	    	
			CallableStatement ctmt = conexion.prepareCall(sql);

			if (tramo.getPeriodo() == null)
				tramo.setPeriodo("0");
			if (tramo.getNumTramo() == null)
				tramo.setNumTramo("0");

			if (tramo.getIngPromedio() == null)
				tramo.setIngPromedio("0");

			if (tramo.getMontoUnitarioBeneficio() == null)
				tramo.setMontoUnitarioBeneficio("0");

			//  	ctmt.setString(1, oSql.getEsquemadestino());
			// 	ctmt.setString(2, oSql.getTablatramo());
			ctmt.setInt(1, Integer.parseInt(tramo.getId()));
			ctmt.setInt(2, Integer.parseInt(tramo.getTupla()));
			ctmt.setString(3, tramo.getRutCausante());
			ctmt.setInt(4, Integer.parseInt(tramo.getPeriodo().trim()));
			ctmt.setInt(5, Integer.parseInt(tramo.getNumTramo().trim()));
			ctmt.setInt(6, Integer.parseInt(tramo.getIngPromedio().trim()));
			ctmt.setInt(7, Integer.parseInt(tramo.getMontoUnitarioBeneficio().trim()));

			ctmt.execute();

			ctmt.close();
		} catch (Exception e) {

			//e.printStackTrace();
			return e.getMessage();

		}

		return null;
	}
	
	/**
	 * 
	 * @param tramo
	 * @param oSql
	 * @return Null si es exitoso. En caso contrario, retorna el mensaje de error
	 */
	public String guardarTramo(TramoTO tramo, SqlParametersTO oSql) {

		//	CustomDataSource ds = new CustomDataSource();

		Connection conexion = null;
		CallableStatement ctmt= null;
		try {

			////ds.openConnection();
			conexion = ds.getConnection();
			String sql = "INSERT	INTO " + oSql.getEsquemadestino() + "." + oSql.getTablatramo() + "(RUTCAUSANTE , IDTUPLA, PERIODO ,NUMTRAMO ,INGPROMEDIO ,MONTOBENEFICIO)" + "VALUES	(?,?,?,?,?,?)";

			//CallableStatement ctmt = conexion.prepareCall("{ call TUPLA.USR_SETTRAMO (?,?,?,?,?,?,?,?,?) }");	    	
			ctmt = conexion.prepareCall(sql);

			if (tramo.getPeriodo() == null)
				tramo.setPeriodo("0");
			if (tramo.getNumTramo() == null)
				tramo.setNumTramo("0");

			if (tramo.getIngPromedio() == null)
				tramo.setIngPromedio("0");

			if (tramo.getMontoUnitarioBeneficio() == null)
				tramo.setMontoUnitarioBeneficio("0");

			//  	ctmt.setString(1, oSql.getEsquemadestino());
			// 	ctmt.setString(2, oSql.getTablatramo());
			//System.out.println(tramo.getId());
			//System.out.println(tramo.getTupla());

			ctmt.setInt(1, Integer.parseInt(tramo.getRutCausante()));
			ctmt.setInt(2, Integer.parseInt(tramo.getId().trim()));
			ctmt.setInt(3, Integer.parseInt(tramo.getPeriodo().trim()));
			ctmt.setInt(4, Integer.parseInt(tramo.getNumTramo().trim()));
			ctmt.setInt(5, Integer.parseInt(tramo.getIngPromedio().trim()));
			ctmt.setInt(6, Integer.parseInt(tramo.getMontoUnitarioBeneficio().trim()));


			ctmt.execute();

			
		} catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();

		}
		finally{
			try {
				ctmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public boolean saveRetornoActualiza(RetornoTO retorno, SqlParametersTO oSql) {

		//	CustomDataSource ds = new CustomDataSource();

		Connection conexion = null;

		try {

			////ds.openConnection();
			conexion = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("UPDATE " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo());
			sql.append(" SET CODIGO=? ,MENSAJE=? ");
			if(retorno.getCodigo().equals("0")){
				sql.append(", PERIODO=0, TRAMO=0, MONTO=0 ");
			}
			sql.append("WHERE ID=? ");
			if(retorno.getIdtupla()!=null){
				sql.append("AND IDTUPLA=? ");
			}
			CallableStatement ctmt = conexion.prepareCall(sql.toString());

			if (retorno.getCodigo() == null)
				retorno.setCodigo("0");
			if (retorno.getMensaje() == null)
				retorno.setMensaje("");

			//if (retorno.getCodigoxml() == null)
			//	retorno.setCodigoxml("");

			//  	ctmt.setString(1, oSql.getEsquemadestino());
			// 	ctmt.setString(2, oSql.getTablatramo());
			
			ctmt.setString(1, retorno.getCodigo());
			ctmt.setString(2, retorno.getMensaje());
			//ctmt.setString(3, retorno.getCodigoxml());
			ctmt.setInt(3, Integer.parseInt(retorno.getId()));
			if(retorno.getIdtupla()!=null){
				ctmt.setInt(4, Integer.parseInt(retorno.getIdtupla()));
			}
			ctmt.execute();

			ctmt.close();
		} catch (Exception e) {

			e.printStackTrace();
			return false;

		}

		return true;
	}
	
	public boolean saveRetornoEnvio(RetornoTO retorno, SqlParametersTO oSql) {

		//	CustomDataSource ds = new CustomDataSource();

		Connection conexion = null;

		try {

			////ds.openConnection();
			conexion = ds.getConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("UPDATE " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo());
			sql.append(" SET CODIGO=? ,MENSAJE=? ");
			if(retorno.getCodigo().equals("0")){
				sql.append(", TUPLA=0 ");
			}
			sql.append("WHERE ID=? ");
			if(retorno.getIdtupla()!=null){
				sql.append("AND IDTUPLA=? ");
			}
			CallableStatement ctmt = conexion.prepareCall(sql.toString());

			if (retorno.getCodigo() == null)
				retorno.setCodigo("0");
			if (retorno.getMensaje() == null)
				retorno.setMensaje("");

			//if (retorno.getCodigoxml() == null)
			//	retorno.setCodigoxml("");

			//  	ctmt.setString(1, oSql.getEsquemadestino());
			// 	ctmt.setString(2, oSql.getTablatramo());
			
			ctmt.setString(1, retorno.getCodigo());
			ctmt.setString(2, retorno.getMensaje());
			//ctmt.setString(3, retorno.getCodigoxml());
			ctmt.setInt(3, Integer.parseInt(retorno.getId()));
			if(retorno.getIdtupla()!=null){
				ctmt.setInt(4, Integer.parseInt(retorno.getIdtupla()));
			}
			ctmt.execute();

			ctmt.close();
		} catch (Exception e) {

			e.printStackTrace();
			return false;

		}

		return true;
	}

	public HashMap getMaxIds(SqlParametersTO oSql) {
		//	CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		HashMap salida = new HashMap();
		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "select max(idtupla) as MAXIMO from " + oSql.getEsquemaorigen() + "." + oSql.getTablatupla();
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			if (rs.next() && rs.getString("MAXIMO") != null) {
				salida.put("TUPLA", rs.getString("MAXIMO").trim());
			} else
				salida.put("TUPLA", "0");

			/*sql = "select max(id) as MAXIMO from " + oSql.getEsquemaorigen() + "." + oSql.getTablatramo();
			press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			if (rs.next() && rs.getString("MAXIMO") != null) {
				salida.put("TRAMO", rs.getString("MAXIMO").trim());
			} else*/
				salida.put("TRAMO", "0");
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salida;
	}
	
	
	public String getPeriodoTramoVigente() {

		//		CustomDataSource ds = new CustomDataSource();
		Connection con = null;
		ResultSet rs = null;
		String periodo = null;
		try {
			////ds.openConnection();
			con = ds.getConnection();
			String sql = "SELECT CHAR(max(AF2GA),ISO) AF2GA FROM " + tramosHistoricos;
			//String sql = "select RUTCAUSANTE from " + esquema + "." + tabla + " where id=" + id;
			PreparedStatement press = (PreparedStatement) con.prepareStatement(sql);
			rs = press.executeQuery();
			rs.next();

			periodo = rs.getString("AF2GA");

			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return periodo;
	}

	public void disconect() {
		try {
			ds.getConnection().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<java.sql.Date> getTramosByAno(int ano, int rutAfil) throws Exception {

		ResultSet rs = null;
		List<java.sql.Date> fecIniTramos = new ArrayList<java.sql.Date>();

		Connection con = null;
		Statement getTramosByAnoStmt = null;

		try {
			con = ds.getConnection();

			getTramosByAnoStmt = con.createStatement();
			String query = "select t1.af2ga " 
					+ "from " + tramosHistoricos + " t1 "   
					//+ "left join " + tramosHistoricosAfiliado + " t2 on t1.af2ga = t2.af2ga and t1.af2ha = t2.af2ha and t2.se5fajc = " + rutAfil + " " 
					+ "where substr(char(t1.af2ga, ISO), 1, 4) = '" + ano + "' "
					+ "and t1.af2ha = 1 "  
					+ "order by t1.af2ga asc";

			rs = getTramosByAnoStmt.executeQuery(query);

			while (rs.next()) {
				fecIniTramos.add(rs.getDate(1));
			}
			rs.close();
			getTramosByAnoStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar tramos retroactivos");
		}

		return fecIniTramos;
	}
	
	public void insertASF011(SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
			    	
		CallableStatement ctmt= null;;
		try {
			conexion = ds.getConnection();
			
			StringBuffer sql= new StringBuffer(); 
			sql.append("INSERT INTO  " + oSql.getEsquemaorigen() + "." + oSql.getTabla011()  + " ");
			sql.append("select A.ID, MESIF,CODENTID,CODARCHI,MESRECAUD,MESREMUN,TIPODECL,NDECLARA,RUTEMPLE,DVEMPLE,NOMEMPLE,A.RUTBENEF,DVBENEF, ");
			sql.append("NOMBENEF,CODTBEN,CODTBE2,RUTCAUSA,DVCAUSA,NOMCAUSA,CODTCAU,FINIBEN,FTERBEN,NDIASAF,CODTRAMO,MONBENEF,TIPEMISI, ");
			sql.append("CTIPEGR,MODPAGO,MONDOCUM,NUMSERIE,NUMDOCUM,FEMIDOC,CODBANCO ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a ");
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " b ");
			sql.append("on a.id= b.id ");
			sql.append("where (periodo + tramo + monto + causante)>=1  ");
			sql.append("and (beneficiario + empleador)=0 ");
			sql.append("and codarchi=11 ");
			sql.append("and b.periodo_rechazo between ? and ? ");
			
			ctmt = conexion.prepareCall(sql.toString());
			ctmt.setInt(1, oSql.getMinperiodo());
			ctmt.setInt(2, oSql.getMaxperiodo());
			ctmt.execute();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}finally{
			ctmt.close();
		}
	}
	
	public void insertASF012(SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
			    	
		CallableStatement ctmt= null;;
		try {
			conexion = ds.getConnection();

			StringBuffer sql= new StringBuffer(); 
			sql.append("INSERT INTO  " + oSql.getEsquemaorigen() + "." + oSql.getTabla012() + " ");
			sql.append("select A.ID, MESIF,CODENTID,CODARCHI,MESRECAUD,MESREMUN,TIPODECL,NDECLARA,RUTEMPLE,DVEMPLE,NOMEMPLE,A.RUTBENEF,DVBENEF, ");
			sql.append("NOMBENEF,CODTBEN,CODTBE2,RUTCAUSA,DVCAUSA,NOMCAUSA,CODTCAU,FINIBEN,FTERBEN,NDIASAF,CODTRAMO,MONBENEF,1,TIPEMISI, ");
			sql.append("CTIPEGR,MONDOCUM,MODPAGO,FEMIDOC,NUMSERIE,NUMDOCUM,CODBANCO,'' ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a ");
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " b ");
			sql.append("on a.id= b.id ");
			sql.append("where (periodo + tramo + monto + causante)>=1  ");
			sql.append("and (beneficiario + empleador)=0 ");
			sql.append("and codarchi=12 ");
			sql.append("and b.periodo_rechazo between ? and ? ");
			
			ctmt = conexion.prepareCall(sql.toString());
			ctmt.setInt(1, oSql.getMinperiodo());
			ctmt.setInt(2, oSql.getMaxperiodo());
			ctmt.execute();
			
		} catch (SQLException e) {
			//throw new SQLException(e.getMessage());
		}finally{
			ctmt.close();
		}
	}
	
	public void insertASF011SinMarca(SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
			    	
		CallableStatement ctmt= null;;
		try {
			conexion = ds.getConnection();
			
			StringBuffer sql= new StringBuffer(); 
			sql.append("INSERT INTO  " + oSql.getEsquemaorigen() + "." + oSql.getTabla011()  + " ");
			sql.append("select A.ID, MESIF,CODENTID,CODARCHI,MESRECAUD,MESREMUN,TIPODECL,NDECLARA,RUTEMPLE,DVEMPLE,NOMEMPLE,A.RUTBENEF,DVBENEF, ");
			sql.append("NOMBENEF,CODTBEN,CODTBE2,RUTCAUSA,DVCAUSA,NOMCAUSA,CODTCAU,FINIBEN,FTERBEN,NDIASAF,CODTRAMO,MONBENEF,TIPEMISI, ");
			sql.append("CTIPEGR,MODPAGO,MONDOCUM,NUMSERIE,NUMDOCUM,FEMIDOC,CODBANCO ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a ");
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " b ");
			sql.append("on a.id= b.id ");
			sql.append("where (periodo + tupla + tramo + monto + entidad + causante + beneficiario + empleador)=0  ");
			sql.append("and codarchi=11 ");
			sql.append("and b.periodo_rechazo between ? and ? ");
			
			ctmt = conexion.prepareCall(sql.toString());
			ctmt.setInt(1, oSql.getMinperiodo());
			ctmt.setInt(2, oSql.getMaxperiodo());
			ctmt.execute();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}finally{
			ctmt.close();
		}
	}
	
	public void insertASF012SinMarca(SqlParametersTO oSql) throws SQLException {

		Connection conexion = null;
			    	
		CallableStatement ctmt= null;
		try {
			conexion = ds.getConnection();

			StringBuffer sql= new StringBuffer(); 
			sql.append("INSERT INTO  " + oSql.getEsquemaorigen() + "." + oSql.getTabla012() + " ");
			sql.append("select A.ID, MESIF,CODENTID,CODARCHI,MESRECAUD,MESREMUN,TIPODECL,NDECLARA,RUTEMPLE,DVEMPLE,NOMEMPLE,A.RUTBENEF,DVBENEF, ");
			sql.append("NOMBENEF,CODTBEN,CODTBE2,RUTCAUSA,DVCAUSA,NOMCAUSA,CODTCAU,FINIBEN,FTERBEN,NDIASAF,CODTRAMO,MONBENEF,1,TIPEMISI, ");
			sql.append("CTIPEGR,MONDOCUM,MODPAGO,FEMIDOC,NUMSERIE,NUMDOCUM,CODBANCO,'' ");
			sql.append("from " + oSql.getEsquemaorigen() + "." + oSql.getTablaorigen() + " a ");
			sql.append("join " + oSql.getEsquemaorigen() + "." + oSql.getTablamarcarechazo()  + " b ");
			sql.append("on a.id= b.id ");
			sql.append("where (periodo + tupla + tramo + monto + entidad + causante + beneficiario + empleador)=0  ");
			sql.append("and codarchi=12 ");
			sql.append("and b.periodo_rechazo between ? and ? ");
			
			ctmt = conexion.prepareCall(sql.toString());
			ctmt.setInt(1, oSql.getMinperiodo());
			ctmt.setInt(2, oSql.getMaxperiodo());
			ctmt.execute();
			
		} catch (SQLException e) {
			//throw new SQLException(e.getMessage());
		}finally{
			ctmt.close();
		}
	}
	
	private String getFechaTramo(String periodo){
		String fechatramo="";
		if(Integer.parseInt(periodo)<201601){
			if(periodo.equals("2010")){
				fechatramo= periodo + "-08-01";
			}else{
				fechatramo= periodo + "-07-01";
			}
		}else{
			if(periodo.substring(4, 6).equals("01")){
				fechatramo= periodo.substring(0, 4) + "-01-01";
			}else{
				fechatramo= periodo.substring(0, 4) + "-07-01";
			}
		}
		return fechatramo;
	}
}
