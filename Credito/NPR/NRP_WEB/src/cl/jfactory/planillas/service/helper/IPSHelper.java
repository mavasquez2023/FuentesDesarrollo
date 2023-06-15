package cl.jfactory.planillas.service.helper;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mysql.jdbc.log.LogUtils;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.UtilLogProcesos;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.nrp.nominas.metodos.java.NominaIPS;
import cl.liv.nrp.nominas.metodos.java.NominaNovedadesIPS;
import cl.liv.nrp.nominas.metodos.java.NominaResumenIPS;
import cl.liv.nrp.nominas.metodos.java.NominaResumenNovedadesIPS;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class IPSHelper {

	public static String NAMESPACE_IPS = "ips.";
	
	private HashMap copyHash(HashMap origen, HashMap destino) {
		
		if(destino != null) {
			Iterator it = origen.keySet().iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				if(key != null && origen.get(key)!= null) {
					destino.put(key, origen.get(key));
				}
			}
		}
		
		return destino;
	}
	
	public HashMap getDataCuadraturaIPS(Object session, Object parameters) throws JSONException, IOException, InterruptedException {

		HashMap salida = new HashMap();

	    SimpleDateFormat formatoYearMonth = new SimpleDateFormat("yyyyMM");
		Calendar gc = GregorianCalendar.getInstance();
        gc.add(Calendar.MONTH, -1);
        String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String periodoAnterior = formatoYearMonth.format(gc.getTime());
		MiHashMap parametros = new MiHashMap();
		parametros.put("periodo", periodo);
		parametros.put("periodo_anterior", periodoAnterior);
		

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		MiHashMap result = null;
		
		
		//dropTableTotalNovedades
		
		try {
			sqlMap.delete(NAMESPACE_IPS+"dropTableTotalNovedades");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sqlMap.insert(NAMESPACE_IPS+"createTableTotalNovedades");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			sqlMap.insert(NAMESPACE_IPS+"crearHistoricoMesAnterior", parametros);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_mes_anterior_sin_agrupar", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_mes_anterior_agrupada", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_totales_sap", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			
			
			
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_terminados_normal", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_terminados_anticipados", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_modificados", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_iguales", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_creados", parametros);
			if(result != null) {
				copyHash(result, salida);
			}
			
			//result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_novedades", parametros);
			//if(result != null) {
			//	copyHash(result, salida);
			//}
			result = (MiHashMap)sqlMap.queryForObject(NAMESPACE_IPS+"row_agrupadas", parametros);
			if(result != null) {
				copyHash(result, salida);
			}

			
			long rModificados = 0l;
			long rIguales =  0l;
			long rCreados =  0l;
			long mModificados = 0l;
			long mIguales =  0l;
			long mCreados =  0l;
			long rTerminadosAnticipados = 0l;
			long mTerminadosAnticipados = 0l;
			
			
			
			if(salida.get("R_TERMINADOS_ANTICIPADOS") != null) {
				rTerminadosAnticipados = new Long(salida.get("R_TERMINADOS_ANTICIPADOS").toString()).longValue();
			}
			if(salida.get("M_TERMINADOS_ANTICIPADOS") != null) {
				mTerminadosAnticipados = new Long(salida.get("M_TERMINADOS_ANTICIPADOS").toString()).longValue();
			}
			if(salida.get("R_MODIFICADOS") != null) {
				rModificados = new Long(salida.get("R_MODIFICADOS").toString()).longValue();
			}
			if(salida.get("R_IGUALES") != null) {
				rIguales = new Long(salida.get("R_IGUALES").toString()).longValue();
			}
			if(salida.get("R_CREADOS") != null) {
				rCreados = new Long(salida.get("R_CREADOS").toString()).longValue();
			}
			if(salida.get("M_MODIFICADOS") != null) {
				mModificados = new Long(salida.get("M_MODIFICADOS").toString()).longValue();
			}
			if(salida.get("M_IGUALES") != null) {
				mIguales = new Long(salida.get("M_IGUALES").toString()).longValue();
			}
			if(salida.get("M_CREADOS") != null) {
				mCreados = new Long(salida.get("M_CREADOS").toString()).longValue();
			}
			
			
			
			BigInteger rTotalProceso = BigInteger.valueOf(rModificados + rIguales +rCreados) ;
			BigInteger mTotalProceso = BigInteger.valueOf( mModificados + mIguales + mCreados);
			
			BigInteger rTotalNovedades = BigInteger.valueOf( rCreados + rModificados + rTerminadosAnticipados) ;
			BigInteger mTotalNovedades = BigInteger.valueOf( mCreados + mModificados + mTerminadosAnticipados);
			result.put("R_TOTAL_PROCESO", rTotalProceso);
			result.put("M_TOTAL_PROCESO", mTotalProceso);
			result.put("R_TOTAL_NOVEDADES", rTotalNovedades);
			result.put("M_TOTAL_NOVEDADES", mTotalNovedades);
			if(result != null) {
				copyHash(result, salida);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//r_creados, r_modificados, r_terminados_anticipados -> r_total_novedades
				
	    return salida;
	}
	
	public static void limpiarTablasIPS() {
		UtilLogProcesos.debug("Limpieza tabla IPS init...");
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String periodoAnterior = UtilesComunes.reemplazarVariables("sys.periodo.anterior2");
		MiHashMap parametros = new MiHashMap();
		parametros.put("periodo", periodo);
		parametros.put("periodo_anterior", periodoAnterior);


		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			sqlMap.delete(NAMESPACE_IPS+"limpiarDatos15IPS", parametros);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			sqlMap.delete(NAMESPACE_IPS+"borrarTablaIPSa");
		} catch (SQLException e1) {}
		try {
			sqlMap.delete(NAMESPACE_IPS+"borrarTablaIPSb");
		} catch (SQLException e1) {}
		
		
		try {
			sqlMap.insert(NAMESPACE_IPS+"crearTablaIPSa", parametros);
			sqlMap.insert(NAMESPACE_IPS+"crearTablaIPSb", parametros);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		UtilLogProcesos.debug("Limpieza tabla IPS terminada.");
		
	}
	
	public static void cargarHistoricoIPS() {
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			MiHashMap parametros = new MiHashMap();
			parametros.put("periodo", periodo);
			sqlMap.insert(NAMESPACE_IPS +"agregarDataHistoricaIPSh",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
	}
	
	
	public HashMap cargarDatosTabla15IPS(Object session, Object parameters) throws JSONException, IOException, InterruptedException {

		
		UtilLogProcesos.debug("cargarDatosTabla15IPS...");
		IPSHelper.limpiarTablasIPS();
	  
        	    
		HashMap salida = new HashMap();
		salida.put("status", Boolean.FALSE);
		salida.put("description", "Iniciando...");
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String periodoAnterior = UtilesComunes.reemplazarVariables("sys.periodo.anterior2");
		
		MiHashMap parametros = new MiHashMap();
		parametros.put("periodo", periodo);
		parametros.put("periodo_anterior", periodoAnterior);


		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		

		UtilLogProcesos.debug("borrando tablas IPS...");
		
		try {
			sqlMap.delete(NAMESPACE_IPS+"borrarTablaIPSa");
		} catch (SQLException e1) {}
		try {
			sqlMap.delete(NAMESPACE_IPS+"borrarTablaIPSb");
		} catch (SQLException e1) {}
		
		
		UtilLogProcesos.debug("creando tablas IPS...");
		try {
			sqlMap.insert(NAMESPACE_IPS+"crearTablaIPSa", parametros);
			sqlMap.insert(NAMESPACE_IPS+"crearTablaIPSb", parametros);
		} catch (SQLException e1) {
			salida.put("status", Boolean.FALSE);
			salida.put("description", "No Pudo generar las tablas de trabajo : "+ e1.getMessage());
			return salida;
		}
		
	
		try {

			UtilLogProcesos.debug("limpiando datos IPS...");
			sqlMap.delete(NAMESPACE_IPS+"limpiarDatos15IPS", parametros);
			HashMap count = (HashMap)sqlMap.queryForObject(NAMESPACE_IPS+"countDatos15IPS", parametros);
			UtilLogProcesos.debug("cargarDatosIPS count ->"+count);
		
			UtilLogProcesos.debug("cargarDatosIPSCondicion1...");
			sqlMap.insert(NAMESPACE_IPS+"cargarDatosIPSCondicion1", parametros);
			count = (HashMap)sqlMap.queryForObject(NAMESPACE_IPS+"countDatos15IPS", parametros);
			UtilLogProcesos.debug("cargarDatosIPS count ->"+count);
			
			UtilLogProcesos.debug("cargarDatosIPSCondicion2...");
			sqlMap.insert(NAMESPACE_IPS+"cargarDatosIPSCondicion2", parametros);
			count = (HashMap)sqlMap.queryForObject(NAMESPACE_IPS+"countDatos15IPS", parametros);
			UtilLogProcesos.debug("cargarDatosIPS count ->"+count);
			
			UtilLogProcesos.debug("cargarDatosIPSCondicion3...");
			sqlMap.insert(NAMESPACE_IPS+"cargarDatosIPSCondicion3", parametros);
			count = (HashMap)sqlMap.queryForObject(NAMESPACE_IPS+"countDatos15IPS", parametros);
			UtilLogProcesos.debug("cargarDatosIPS count ->"+count);
			
			UtilLogProcesos.debug("cargarDatosIPSCondicion4...");
			sqlMap.insert(NAMESPACE_IPS+"cargarDatosIPSCondicion4", parametros);
			count = (HashMap)sqlMap.queryForObject(NAMESPACE_IPS+"countDatos15IPS", parametros);
			UtilLogProcesos.debug("cargarDatosIPS count ->"+count);
			
		} catch (SQLException e) {
			UtilLogProcesos.debug("Error IPS -> "+ e.getMessage());
			e.printStackTrace();
		}
		
		UtilLogProcesos.debug("generarNominasIPS()...");
		generarNominasIPS();
		
		salida.put("status", Boolean.TRUE);
		salida.put("description", "Terminado");
	
		return salida;
	}
	
	public static boolean generarNominasIPS() {
		boolean resultado = false;
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String codigoEntidad = "61533000";
		HashMap configuracion = new HashMap();
		configuracion.put("FORMATO_SALIDA".toLowerCase(),"txt");
		configuracion.put("FORMATO_NOMINA".toLowerCase(),"NOM_61533000_IPS");
		configuracion.put("TABLA_PRINCIPAL", "nrpdta.nrp15ips");
		configuracion.put("PERIODO", periodo);
		configuracion.put("CODIGO_ENTIDAD", codigoEntidad);
		configuracion.put("PATH_DESTINO", PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/");
		
		UtilLogProcesos.debug("generar81...");
		generar81(configuracion);
		UtilLogProcesos.debug("generar83...");
		generar83(configuracion);
		UtilLogProcesos.debug("generar41...");
		generar41(configuracion);
		UtilLogProcesos.debug("generarResumen...");
		generarResumen(configuracion);
		
		
		return resultado;
	}
	
	public static boolean generar81(HashMap configuracion) {
		boolean resultado = false;
		String ID = "81";
		configuracion.put("NOMBRE_CONFIG".toLowerCase(),"CONF_61533000_"+ID);
		configuracion.put("FORMATO_NOMINA".toLowerCase(), "NOM_61533000_IPS_NOVEDADES");
		String[] sql = NominaIPS.obtenerQuerysNomina81(configuracion);
		String params = "";
		params = "sql_header:"+sql[0] +";;sql_detalle:"+sql[1] +";;sql_footer:"+sql[2] +";;"+ params ;
		String nombreSalida = "FU225"+ID+UtilesComunes.reemplazarVariables("sys.periodo.MMAAAA.siguiente");
		
		String filePath = null;
		try {
			filePath = GeneradorNominasHelper.ejecutarComandoGeneracion(configuracion, configuracion.get("PERIODO").toString(), configuracion.get("CODIGO_ENTIDAD").toString(), params, nombreSalida);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String pathDestino = configuracion.get("PATH_DESTINO").toString()+"/"+nombreSalida;
		
		if(pathDestino != null && new File(pathDestino).exists()) {
			resultado = true;
		}
		
		
		return resultado;
	}
	public static boolean generar83(HashMap configuracion) {
		boolean resultado = false;
		String ID = "83";
		configuracion.put("NOMBRE_CONFIG".toLowerCase(),"CONF_61533000_"+ID);
		configuracion.put("FORMATO_NOMINA".toLowerCase(), "NOM_61533000_IPS_NOVEDADES");
		String[] sql = NominaIPS.obtenerQuerysNomina83(configuracion);
		String params = "";
		params = "sql_header:"+sql[0] +";;sql_detalle:"+sql[1] +";;sql_footer:"+sql[2] +";;"+ params ;
		String nombreSalida = "FU225"+ID+UtilesComunes.reemplazarVariables("sys.periodo.MMAAAA.siguiente");
		
		String filePath = null;
		try {
			filePath = GeneradorNominasHelper.ejecutarComandoGeneracion(configuracion, configuracion.get("PERIODO").toString(), configuracion.get("CODIGO_ENTIDAD").toString(), params, nombreSalida);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String pathDestino = configuracion.get("PATH_DESTINO").toString()+"/"+nombreSalida;
		
		if(pathDestino != null && new File(pathDestino).exists()) {
			resultado = true;
		}
		
		return resultado;
	}
	public static boolean generar41(HashMap configuracion) {
		boolean resultado = false;
		String ID = "41";
		configuracion.put("NOMBRE_CONFIG".toLowerCase(),"CONF_61533000_"+ID);
		configuracion.put("FORMATO_NOMINA".toLowerCase(), "NOM_61533000_IPS_NOVEDADES");
		String[] sql = NominaIPS.obtenerQuerysNomina41(configuracion);
		String params = "";
		params = "sql_header:"+sql[0] +";;sql_detalle:"+sql[1] +";;sql_footer:"+sql[2] +";;"+ params ;
		String nombreSalida = "FU225"+ID+UtilesComunes.reemplazarVariables("sys.periodo.MMAAAA.siguiente");
		
		String filePath = null;
		try {
			filePath = GeneradorNominasHelper.ejecutarComandoGeneracion(configuracion, configuracion.get("PERIODO").toString(), configuracion.get("CODIGO_ENTIDAD").toString(), params, nombreSalida);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		String pathDestino = configuracion.get("PATH_DESTINO").toString()+"/"+nombreSalida;
		
		if(pathDestino != null && new File(pathDestino).exists()) {
			resultado = true;
		}
		
		return resultado;
	}
	public static boolean generarResumen(HashMap configuracion) {
		boolean resultado = false;
		
		try {
			//new NominaResumenNovedadesIPS().obtenerResumenesIPS(null);
			//GeneradorNominasHelper.generar("", "codigo_entidad:61533000");
			//{

			HashMap<String, Object> configGeneracion = new HashMap();
			configGeneracion.put("CODIGO_HOLDING".toLowerCase()		,"");
			configGeneracion.put("DATA_ADICIONAL".toLowerCase()		,"");
			configGeneracion.put("NOMBRE_SALIDA_TMP".toLowerCase()	,""); 
			configGeneracion.put("RUT_EMPRESA".toLowerCase()		,"61533000");
			configGeneracion.put("TIPO".toLowerCase()				,"NORMAL");
			configGeneracion.put("NOMBRE_SALIDA".toLowerCase()		,"RESUMEN"); 
			configGeneracion.put("FORMATO_NOMINA".toLowerCase()		,"NOM_61533000_RESUMEN_NOVEDADES"); 
			configGeneracion.put("PAR_GENERACION".toLowerCase()		,""); 
			configGeneracion.put("AGRUPACION".toLowerCase()			,"");
			configGeneracion.put("FORMATO_SALIDA".toLowerCase()		,"xls"); 
			configGeneracion.put("NOMBRE_CONFIG".toLowerCase()		,"");
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			String codigoEntidad = "61533000";
			String params = "";
			String nombreArchivo = "RESUMEN";
			
			//public static String ejecutarComandoGeneracion(HashMap configuracion, String periodo, String codigoEntidad, String params, String nombreArchivo ) throws IOException{
			GeneradorNominasHelper.ejecutarComandoGeneracion(configGeneracion, periodo, codigoEntidad, params, nombreArchivo);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	public static void main(String[] args) {
		
		generarResumen(null);
		
		//String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		//sys.periodo.anterior
		//String periodoAnterior = UtilesComunes.reemplazarVariables("sys.periodo.anterior2");
		
		//System.out.println(periodo);
		//System.out.println(periodoAnterior);
	}
		
}
