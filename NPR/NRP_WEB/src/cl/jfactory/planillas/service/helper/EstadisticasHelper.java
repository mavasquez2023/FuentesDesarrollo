package cl.jfactory.planillas.service.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class EstadisticasHelper {

	public static ArrayList comandos = new ArrayList();
	
	public static boolean terminado = false;
	
	
	public static void inicializar(final boolean inicializar){
		comandos.clear();
		if(inicializar){
			inicializarTabla();
		}
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
				procesar();
			}
		}).start();
		
	}
	
	public static void terminar(){
		terminado = true;
	}

	public static void agregarComando(final String comando){
		comandos.add(comando);		
	}
	
	public static void procesar(){
		int contador = 0;
		int contadorParaLog = 30;
		while( ! terminado ){
			contador++;
			if(comandos != null && comandos.size()>0){
				String cmd = (String)comandos.get(0);
				comandos.remove(0);
				procesarComando(cmd);
			}
			else{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(contador >= contadorParaLog) {
					contador = 0;
					UtilLogWorkflow.debug("esperando por comandos...");
				}
			}
		}
	}
	
	public static SqlMapClient sqlMap = null;
	public static SqlMapClient getSqlMap(){
		if(sqlMap == null){
			sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		}
		return sqlMap;
	}
	
	public static void procesarComando(String comando){
		MiHashMap parametros = new MiHashMap();
		SqlMapClient sqlMap = getSqlMap();
		String[] datos = comando.split(";");
		parametros.put("PERIODO", Utiles.getPeriodoActual());
		parametros.put("entidad", datos[0]);
		parametros.put("nomina", datos[1]);
		parametros.put("comando", comando);
		parametros.put("estado", new Integer(0));
		parametros.put("largo_archivo", new Integer(0));
		
		try {
			sqlMap.insert("carga_SAP.insert_cmd", parametros);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void actualizacionEstado(String entidad, String nomina, int estado, long largoArchivo, String rutaArchivo, long tiempoGeneracion, HashMap adicionales){

		MiHashMap parametros = new MiHashMap();
		parametros.put("PERIODO", Utiles.getPeriodoActual());
		parametros.put("entidad", entidad);
		parametros.put("nomina", nomina);
		parametros.put("estado", new Integer(estado));
		parametros.put("largo_archivo", new Long(largoArchivo));
		parametros.put("ruta_archivo", rutaArchivo);
		parametros.put("tiempo_generacion", new Long(tiempoGeneracion));
		parametros.putAll(adicionales);
		SqlMapClient sqlMap = getSqlMap();
		
		try {
			sqlMap.insert("carga_SAP.update_cmd",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			String partes[] = rutaArchivo.substring( rutaArchivo.lastIndexOf("/")+1 ).split("-");
			parametros.put("rut_entidad", new Long(partes[0]));
			parametros.put("oficina", partes[1]);
			parametros.put("codigo_nomina", partes[2].split("\\.")[0]);

		}
		catch(Exception e){
			
		}
		
		try {
			Thread.sleep(10);
			sqlMap.insert("carga_SAP.insert_files",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	public static void inicializarTabla(){
		MiHashMap parametros = new MiHashMap();
		parametros.put("PERIODO", Utiles.getPeriodoActual());
		SqlMapClient sqlMap = getSqlMap();
		
		try {
			sqlMap.insert("carga_SAP.eliminarTablaComandos",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sqlMap.insert("carga_SAP.eliminarTablaFiles",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sqlMap.insert("carga_SAP.crearTablaComandos",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			sqlMap.insert("carga_SAP.crearTablaFiles",parametros);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
}
