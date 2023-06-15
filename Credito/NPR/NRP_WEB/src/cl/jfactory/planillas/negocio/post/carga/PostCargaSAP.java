package cl.jfactory.planillas.negocio.post.carga;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.jfactory.planillas.service.helper.ResumenesHelper;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.jfactory.planillas.service.util.CargaNoBatch;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EstadosWorkflow;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.MemoriaUtil;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilLogComun;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.comun.utiles.logs.UtilLogMemoria;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.liv.persitencia.jdbc.util.JDBCUtil;

public class PostCargaSAP implements IPostCarga{
	
	static boolean ejecutarBatch = false;
	static String idCargaNoBatch = "POST_CARGA_SAP";
	static String tablaCargaNoBatch = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.post.carga.sap.tabla");
	static HashMap entidadesPagadoras = new HashMap();
	int porcionInsert = 50000;
	public boolean execute(final HashMap session,HashMap parametros, HashMap configs) {
		
		// TODO Auto-generated method stub
		String key = WorkFlowHelper.formato.format(new Date());
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		CargaNoBatch cargaNoBatch = new CargaNoBatch(idCargaNoBatch, tablaCargaNoBatch);
		
		try{
			String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
			JSONObject json = new JSONObject(dataBlocked);
			JSONArray _procs = (JSONArray)json.get("procesos");
			for(int j=0; j< _procs.length(); j++){
				if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (HashMap) configs ).get("CODIGO")  )  ){
					(  _procs.getJSONObject(j) ).put("estado", "Comienza proceso carga consolidaci�n...");
				}
			}
			json.put("procesos", _procs);
			UtilesWorkflow.crearArchivoConDataJson(pathCompleto+"blocked.txt", json);
		} catch(Exception e){
			UtilLogErrores.error(e);e.printStackTrace();
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
				
		}
		
		entidadesPagadoras = new HashMap();
		final MiHashMap config = new MiHashMap();
		config.toMiHashMap(configs);
		UtilLogWorkflow.info("ejecutando post proceso SAP "+ config);
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		ArrayList data;
		try {

			Connection conn = JDBCUtil.getConnection();
			
			String periodoAnterior = UtilesComunes.reemplazarVariables("sys.periodo.anterior");
			int indexInsert = 0;
			int indexInsertPorcion = 0;
			int contadorRegistros = 0;
			Statement stmt = null;
			config.put("PERIODO", key);
			MiHashMap configuraciones = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerIdMinimoCargado", config);
			UtilLogWorkflow.info("config-> "+configuraciones);
			int desde = Utiles.getInt(configuraciones.get("DESDE"));
			int hasta = Utiles.getInt(configuraciones.get("HASTA"));
			int cantidadRegistros = Integer.parseInt( configuraciones.get("CANTIDAD_REGISTROS_A_PROCESAR").toString() );
			config.put("PERIODO_ANTERIOR", periodoAnterior);
			UtilLogWorkflow.info("procesando desde ID["+desde+"]["+hasta+"]");
			int contador = 0;
			while(desde < hasta){
				if(conn != null){
					if(contadorRegistros ==0){
						if(ejecutarBatch){
							stmt = conn.createStatement();		
							conn.setAutoCommit(false);
							stmt.clearBatch();
						}
						else{
							//No Batch
						}
					}
					config.put("ID_DESDE", new Integer(desde));
					config.put("ID_HASTA", new Integer(desde+ cantidadRegistros));
					config.put("CANTIDAD_REGISTROS_A_PROCESAR", new Integer(cantidadRegistros));
					UtilLogWorkflow.debug("parametros: "+ config);
					data = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerDataMirrorSAP", config);
					
					if(data != null){
						for(int i=0;i<data.size(); i++){
							contador ++;
							HashMap registro = (HashMap)data.get(i);
							
							//registro.put("APELLIDO_PATERNO","");
							//registro.put("APELLIDO_MATERNO","");
							//registro.put("NOMBRE_DEUDOR","");
							registro.put("RENTAS",0+"");
							
							
							
							if(registro != null && registro.get("RUT_PAGADOR_COMPLETO")!= null){
								registro.put("RUT_PAGADOR",registro.get("RUT_PAGADOR_COMPLETO").toString().split("-")[0] );
								registro.put("DV_PAGADOR",registro.get("RUT_PAGADOR_COMPLETO").toString().split("-")[1] );
							}
							if(registro != null && registro.get("RUT_EMPRESA_COMPLETO")!= null){
								registro.put("RUT_EMPRESA",registro.get("RUT_EMPRESA_COMPLETO").toString().split("-")[0] );
								registro.put("DV_EMPRESA",registro.get("RUT_EMPRESA_COMPLETO").toString().split("-")[1] );
							}
							
							/*if(registro != null && registro.get("NOMBRE_EMPRESA_PUBLICA")!= null){
								registro.put("NOMBRE_EMPRESA", registro.get("NOMBRE_EMPRESA_PUBLICA").toString());
							}
							else if(registro != null && registro.get("NOMBRE_EMPRESA_PRIVADA")!= null){
								registro.put("NOMBRE_EMPRESA", registro.get("NOMBRE_EMPRESA_PRIVADA").toString());
							}
							else{
								//Puede ser entidad pagadora
								if(entidadesPagadoras.get(registro.get("RUT_EMPRESA")) == null ){
									MiHashMap entidadPagadora = (MiHashMap)sqlMap.queryForObject("carga_SAP.obtenerNombreEntidadPagadora", registro);
									if(entidadPagadora != null && entidadPagadora.get("MERASO")!=null){
										entidadesPagadoras.put(registro.get("RUT_EMPRESA"),entidadPagadora.get("MERASO"));
										registro.put("NOMBRE_EMPRESA", entidadesPagadoras.get(registro.get("RUT_EMPRESA")));
									}
								}
								else{
									registro.put("NOMBRE_EMPRESA", entidadesPagadoras.get(registro.get("RUT_EMPRESA")));
								}
								
							}*/
							
							
							
							if(registro == null || registro.get("NRO_MESES_MOROSOS") == null || (registro.get("NRO_MESES_MOROSOS").toString().length()==0)){
								registro.put("NRO_MESES_MOROSOS", 0+"");
							}
							if(registro != null && (registro.get("NUMERO_CONTRATO") != null && registro.get("NUMERO_CONTRATO").toString().length()>0)){
								registro.put("OFICINA_CREDITO", registro.get("NUMERO_CONTRATO").toString().substring(0, 3));
								registro.put("NUMERO_CONTRATO", registro.get("NUMERO_CONTRATO").toString().substring( 3));
							}
							if(registro != null && registro.get("ULTIMO_DIA_PERIODO")!= null){
								registro.put("FECHA_VCTO_NOMINA", registro.get("ULTIMO_DIA_PERIODO").toString().replaceAll("-", ""));
							}
							if(registro != null && registro.get("TIPO_NOMINA")!= null  && registro.get("TIPO_NOMINA").equals("4")){
								registro.put("FECHA_VCTO_NOMINA", registro.get("ULTIMO_DIA_SIGUIENTE_PERIODO").toString().replaceAll("-", ""));
							}
							//ULTIMO_DIA_SIGUIENTE_PERIODO
							/*if(registro != null && registro.get("DATOS_AFP")!= null){
								String[] partes = registro.get("DATOS_AFP").toString().split("::");
								registro.put("APELLIDO_PATERNO",partes[0]);
								registro.put("APELLIDO_MATERNO",partes[1]);
								registro.put("NOMBRE_DEUDOR",partes[2]);
								registro.put("RENTAS",partes[3]);
								
							}*/
							registro.put("CUOTON", determinaSiEsCuoton(registro,  new Integer( configuraciones.get("CUOTON_SAP").toString().replaceAll("\\.", "") )    ) );
							if(indexInsert%5000 == 0){
								UtilLogWorkflow.debug("****************\n\ninsertando registro ["+indexInsert+" | "+contador+"] ");
							}
							
							if(indexInsertPorcion >= porcionInsert) {

								UtilLogWorkflow.debug("\n\n\n******************** Insertando porcion de ["+indexInsertPorcion+", "+cargaNoBatch.contador+"] *************");

								try {
									cargaNoBatch.ejecutarComando();
									cargaNoBatch.terminarCarga();
									cargaNoBatch = new CargaNoBatch(idCargaNoBatch, tablaCargaNoBatch);
									indexInsertPorcion = 0;
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
							Utiles.asignaValorNoNulo(registro,"PERIODO","0");
							Utiles.asignaValorNoNulo(registro,"TIPO_NOMINA","0");
							Utiles.asignaValorNoNulo(registro,"OFICINA_PAGO","0");
							Utiles.asignaValorNoNulo(registro,"FECHA_VCTO_CUOTA","0");
							Utiles.asignaValorNoNulo(registro,"NRO_CUOTA","0");
							Utiles.asignaValorNoNulo(registro,"CANTIDAD_CUOTAS","0");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_CUOTA_MONEDA_TX","0");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_CUOTA_MONEDA_LOCAL","0");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_GRAVE_MONEDA_LOCAL","0");
							Utiles.asignaValorNoNulo(registro,"COTIZACION_MONEDA_TX","0");
							Utiles.asignaValorNoNulo(registro,"RUT_PAGADOR","0");
							Utiles.asignaValorNoNulo(registro,"RUT_EMPRESA","0");
							Utiles.asignaValorNoNulo(registro,"ID_BP_SAP","0");
							Utiles.asignaValorNoNulo(registro,"CAJA_PREVISION","0");
							Utiles.asignaValorNoNulo(registro,"GRUPO_PAGO","0");
							Utiles.asignaValorNoNulo(registro,"NUMERO_BENEFICIARIO","0");
							Utiles.asignaValorNoNulo(registro,"ANEXO_NOMINA","0");
							Utiles.asignaValorNoNulo(registro,"SUCURSAL_CCLA","0");
							Utiles.asignaValorNoNulo(registro,"CCAF","0");
							Utiles.asignaValorNoNulo(registro,"TIPO_OPERACION","0");
							Utiles.asignaValorNoNulo(registro,"NRO_MESES_MOROSOS","0");
							Utiles.asignaValorNoNulo(registro,"RENTAS","0");
							Utiles.asignaValorNoNulo(registro,"RENTA_LIQUIDA","0");
							Utiles.asignaValorNoNulo(registro,"MONTO_TOTAL_NOMINA","0");
							Utiles.asignaValorNoNulo(registro,"FOLIO_NOMINA","0");
							Utiles.asignaValorNoNulo(registro,"CODIGO_CAJA_ORIGEN","0");
							Utiles.asignaValorNoNulo(registro,"ID_DATA_ARCHIVO","0");
							Utiles.asignaValorNoNulo(registro,"CUOTON","");
							//Utiles.asignaValorNoNulo(registro,"CODIGO_HOLDING","");
							//Utiles.asignaValorNoNulo(registro,"APELLIDO_PATERNO","");
							//Utiles.asignaValorNoNulo(registro,"APELLIDO_MATERNO","");
							//Utiles.asignaValorNoNulo(registro,"NOMBRE_DEUDOR","");
							//Utiles.asignaValorNoNulo(registro,"NOMBRE_EMPRESA",".");
							//sqlMap.insert("carga_SAP.insertar_data_nomina", data.get(i));
							config.put("PERIODO", registro.get("PERIODO"));
							
							if(ejecutarBatch){
								MiHashMap o = new MiHashMap();
								o.toMiHashMap((HashMap)data.get(i));
								//UtilLogWorkflow.debug("data to nrp15f1 -> "+ o);
								String query = JDBCUtil.getQueryCompleta("query.jdbc.insert.data.nomina."+config.get("codigo"), o);
								
								stmt.addBatch(query);
								//UtilLogWorkflow.debug("["+i+"]"+ query);
								
								if( MemoriaUtil.isMemoriaCritica()  ){
									try{
										UtilLogMemoria.debug("cantidad registros: "+ cantidadRegistros);
										UtilLogWorkflow.info("ejecutando statement...cantidad registros: ["+cantidadRegistros+"]");
										UtilLogMemoria.debug("memoria: "+ MemoriaUtil.isMemoriaCritica() +", "+ Runtime.getRuntime().freeMemory());				
										int[] result = stmt.executeBatch();
										conn.commit();
										stmt.clearBatch();
										contadorRegistros = 0;	
										System.gc();
										UtilLogMemoria.debug("memoria luego de ejecutar: "+ MemoriaUtil.isMemoriaCritica() +" , "+ Runtime.getRuntime().freeMemory());
										
									} catch (Exception e){
										UtilLogErrores.debug("query -> " + query);
										e.printStackTrace();
	
										UtilLogErrores.error(e);
										AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
									}
								}
								contadorRegistros++;
							}
							else{
								//Implementacion no batch
								MiHashMap o = new MiHashMap();
								o.toMiHashMap((HashMap)data.get(i));

								//UtilLogWorkflow.debug("data to nrp15f1 -> "+ o);
								String insert = JDBCUtil.getQueryCompleta("query.jdbc.registro.15f1.SAP", o);
								//UtilLogWorkflow.debug("insert to nrp15f1 -> "+ insert);
								String[] partes = insert.split("#");
								for(int j=0; j< partes.length; j++){
									if(j%2 == 1){
										insert = insert.replaceAll("#"+partes[j]+"#", "");
									}
									
								}
								insert = insert.replaceAll("'","");
								if(indexInsert%100 == 0){
									System.out.print(".");
								}

								//UtilLogWorkflow.debug("insert to nrp15f1 -> "+ insert);
								cargaNoBatch.agregarRegistro(insert);
								indexInsert++;
								indexInsertPorcion++;
								
								
								
							}
						}
					}
					desde = desde + cantidadRegistros;
					//desde = hasta;
					
					
				}
				
				
			}
			if(ejecutarBatch){
				if(stmt != null){
					int[] result = stmt.executeBatch();
					UtilLogWorkflow.debug("The number of rows inserted: "+ result.length);
					conn.commit();
					stmt.clearBatch();
					stmt.close();
					contadorRegistros = 0;	
				}
			}
			else{
				//No batch
				try {
					cargaNoBatch.ejecutarComando();
					cargaNoBatch.terminarCarga();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			config.put("cantidad_registros_consolidados", new Integer(0));
			config.put("cantidad_cuotones", new Integer(0));

			JSONObject dataProceso = ResumenesHelper.obtenerResumenesProceso(config);
		
			session.put(config.get("CODIGO"), dataProceso);
	
			
			try{
				String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
				JSONObject json = new JSONObject(dataBlocked);
				JSONArray _procs = (JSONArray)json.get("procesos");
				for(int j=0; j< _procs.length(); j++){
					if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (HashMap) configs ).get("CODIGO")  )  ){
						(  _procs.getJSONObject(j) ).put("estado", "Término proceso carga consolidación. Sin Errores");
					}
				}
				json.put("procesos", _procs);
				UtilesWorkflow.crearArchivoConDataJson(pathCompleto+"blocked.txt", json);
			} catch(Exception e){
				UtilLogErrores.error(e);
				e.printStackTrace();
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
					
			}
			

			entidadesPagadoras = new HashMap();
			
			return true;
			
		} catch (SQLException e) {
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
		}
		
		return false;
	}

	
	private static String determinaSiEsCuoton(HashMap registro, Integer limiteCuoton){
		String cuoton = "";
		
		if( registro.get("IMPORTE_CUOTA_MONEDA_LOCAL") != null && new Integer( registro.get("IMPORTE_CUOTA_MONEDA_LOCAL").toString()).intValue() > limiteCuoton.intValue() ){
			cuoton = "X";
		}
		
		return cuoton;
	}
	
	public static void main(String[] args) {
		System.out.println(new Integer(2).compareTo(new Integer(1)));
	}
	
}
