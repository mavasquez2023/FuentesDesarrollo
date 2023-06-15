package cl.jfactory.planillas.service.helper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibm.as400.access.AS400;

import cl.jfactory.planillas.negocio.post.carga.IPostCarga;
import cl.jfactory.planillas.service.util.CargaNoBatch;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EjecutarComandoAS400;
import cl.jfactory.planillas.service.util.EstadosWorkflow;
import cl.jfactory.planillas.service.util.PoolHebras;
import cl.jfactory.planillas.service.util.ProcesoNoInvalidanteUtil;
import cl.jfactory.planillas.service.util.UtilLogProcesos;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.archivos.as400.impl.ArchivosAS400;
import cl.liv.archivos.as400.impl.ProxyAS400;
import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.impl.CargaMasivaFromBDImpl;
import cl.liv.cargas.masivas.impl.CargaMasivaImpl;
import cl.liv.cargas.masivas.service.ICargaMasivaService;
import cl.liv.comun.utiles.MemoriaUtil;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.comun.utiles.logs.UtilLogMemoria;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.liv.persitencia.jdbc.util.JDBCUtil;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class WorkFlowHelper {

	
	static int contadorRegistrosOK = 0;
	static int contadorRegistrosNOOK = 0;
	
	static Statement stmt = null;
	public static boolean ejecutarBatch = true;
	public static int contadorRegistros = 0;
	public static int cantidadRegistrosAInsertar = 5000;
	
	public static SimpleDateFormat formato = new SimpleDateFormat("yyyyMM");
	public static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd hhmmss");
	
	public Boolean validarServer(Object session, Object parameters) {
		return Boolean.TRUE;
	}
	
	public static String nombreHebraAEliminar = "";
	
	public HashMap generaEstructuraWorklow(Object session, Object parameters) {
		HashMap salida = new HashMap();
		String key = Utiles.getPeriodoActual();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		
		if( ! ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto) ){
			if(ArchivosUtiles.crearDirectorioPorRuta(pathCompleto)){
				ArchivosUtiles.crearArchivoPorRuta(pathCompleto+"0.txt");
				if(ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto+"0.txt")){
					if(UtilesWorkflow.crearArchivoConDataJson(session, parameters, pathCompleto+"/0.txt")){
						salida.put("descripcion", "Archivo listo para ejecutar carga  ["+key+"]");
						salida.put("codigo", "OK");
						salida.put("tipo", "MENSAJE");
						AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.NO_INICIADO, EstadosWorkflow.ESTRUCTURA_CREADA);
						//guardarDataHistorica();
						limpiarTablasPeriodo();
					}
					else{
						salida.put("descripcion", "Problemas al escribir en el archivo [0.txt] en ["+key+"]");
						salida.put("codigo", "E003");
						salida.put("tipo", "ERROR");
					}

				}
				else{
					salida.put("descripcion", "Problemas al crear el archivo 0.txt en ["+key+"]");
					salida.put("codigo", "E002");
					salida.put("tipo", "ERROR");
				}
				
			}
			else{
				salida.put("descripcion", "Problemas al crear directorio ["+pathCompleto+"]");
				salida.put("codigo", "E001");
				salida.put("tipo", "ERROR");
			}
		}
		else{
			salida.put("warning", "La estructura ya ha sido creada ["+key+"]");
			salida.put("codigo", "W001");
			salida.put("tipo", "WARNING");
		}
		
		
		return salida;
	}
	
	
	public HashMap obtenerEstadoWorklow(Object session, Object parameters) {
		HashMap salida = new HashMap();

		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+"/";
		String rutaWF = pathCompleto + ((HashMap)parameters).get("periodo");
		boolean salidaCorrecta = false;
		while(!salidaCorrecta){
			try{
				File[] archivos = new File(rutaWF).listFiles();
				if(archivos != null && archivos.length>0){
					for(int i=0; i< archivos.length; i++){
						String contenido = ManejoArchivoTXT.getFileContentAsString(archivos[i].getAbsolutePath());
						String estado = archivos[i].getName().split(".txt")[0];
						salida.put(estado, new JSONObject(contenido));
						
					}
				}
				salidaCorrecta = false;
			}
			catch(Exception e){
				
			}
			finally {
				salidaCorrecta = true;
			}
		}
		
		return salida;
	}
	
	public HashMap validaArchivosCargados(Object session, Object parameters) {

		UtilLogProcesos.debug("Iniciando proceso certificación de recursos ["+((HashMap)session).get("data_session")+"]...");
		HashMap salida = new HashMap();

		String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		if(ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto+"0.txt")){
			//O sea, no se ha leido el archivo SAP
			if(ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto+"1.txt")){
				ArchivosUtiles.eliminarArchivoPorRuta(pathCompleto+"1.txt");
			}
			boolean origenesValidados = true;
			int cantidadProcesos = 0;
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
			try {
				ArrayList procesos = new ArrayList();
				MiHashMap pars = new MiHashMap();
				pars.put("estado", 1+"");
				ArrayList origenes = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerProcesosCarga", pars);
				if(origenes != null && origenes.size()>0){
					for(int i=0; i<origenes.size(); i++){

						UtilLogProcesos.debug(" - Iniciando proceso ["+ ((HashMap) origenes.get(i)).get("CODIGO") +"]");
						ICargaMasivaService carga = (ICargaMasivaService) UtilReflectionImpl.getInstance( ((((HashMap) origenes.get(i)).get("CLASE_IMPLEMENTACION")).toString()) );
						cantidadProcesos++;
						boolean estado = carga.validate( (MiHashMap) origenes.get(i));
						
						
						if( ((MiHashMap) origenes.get(i)).get("invalidante").toString().equals("1") ){
							origenesValidados = origenesValidados && estado;
						}
						JSONObject proceso = new JSONObject();
						proceso.put("codigo",((HashMap) origenes.get(i)).get("CODIGO"));
						proceso.put("estado",estado);
						procesos.add(proceso);

						UtilLogProcesos.debug(" - Terminando proceso ["+ ((HashMap) origenes.get(i)).get("CODIGO") +"]");
					}
				}
				
				HashMap resultados = new HashMap();
				resultados.put("procesos", procesos);
				resultados.put("status", origenesValidados+"");
				resultados.put("cantidad_procesos", cantidadProcesos+"");
				((HashMap)session).put("run_resultados", resultados);
				UtilesWorkflow.crearArchivoConDataJson(session, parameters, pathCompleto+"/1.txt");

				AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ESTRUCTURA_CREADA, EstadosWorkflow.RECURSOS_CERTIFICADOS);
			} catch (Exception e) {
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
				e.printStackTrace();
				
			}
			
		}
		else{
			salida.put("descripcion", "No se encuentra el archivo 0.txt ["+key+"]");
			salida.put("codigo", "E004");
			salida.put("tipo", "ERROR");
		}
		

		UtilLogProcesos.debug("Terminando proceso certificación de recursos ["+((HashMap)session).get("data_session")+"]...");
		return salida;
	}


	
	public HashMap ejecutarProcesoConsolidacion(final Object session, final Object parameters) throws Exception{

		final HashMap salida = new HashMap();
		
		UtilLogProcesos.info("Iniciando proceso carga / consolidación ["+((HashMap)session).get("data_session")+"]...");
		

		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
				try{


					String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
					String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
					String rutaCompletaArchivoSAP = "";
					final String rutaFileBlocked = pathCompleto+"blocked.txt";
					if(!ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
						
						rollbackProcesoCarga(session, parameters);
						
						
						JSONObject dataFile = new JSONObject();
						try {
							dataFile.put("fecha_creacion", formatoFecha.format(new Date()));
							dataFile.put("autor", ((HashMap)parameters).get("email"));
							dataFile.put("status_blocked", "2");
							dataFile.put("descripcion", "Ejecutando Proceso Consolidación...");
							dataFile.put("reintentar_en", new Integer(ConstantesUtiles.TIEMPO_ESTANDAR_REFRESCO_BLOQUEO));
							UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
							
							UtilLogWorkflow.debug("Haciendo rollback antes de comenzar ");
			
							boolean origenesEjecutados = true;
							int cantidadProcesos = 0;
							SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
							try {
								ArrayList procesos = new ArrayList();
								MiHashMap pars = new MiHashMap();
								pars.put("estado", 1+"");
								ArrayList origenes = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerProcesosCarga", pars);
								UtilLogWorkflow.info("obtener origenes configurados: "+ origenes);
								
								
								
								String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
								ArrayList procs = new ArrayList();
								JSONObject json = new JSONObject(dataBlocked);
								if(origenes != null && origenes.size()>0){
									for(int i=0; i<origenes.size(); i++){
										HashMap proc = new HashMap();
										proc.put("codigo", ((HashMap) origenes.get(i)).get("CODIGO"));
										proc.put("estado", "No Iniciado");
										procs.add(proc);
									}
								}
								json.put("procesos", procs);
								UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
								boolean estado = true;
								if(origenes != null && origenes.size()>0){
									for(int i=0; i<origenes.size(); i++){
										if(!origenesEjecutados){ 
											UtilLogWorkflow.info("Saliendo de los procesos por origenEjecutados ["+origenesEjecutados+"] ");
											return;}
										dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
										json = new JSONObject(dataBlocked);
										JSONArray _procs = (JSONArray)json.get("procesos");
										for(int j=0; j< _procs.length(); j++){
											if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (MiHashMap) origenes.get(i)  ).get("CODIGO")  )  ){
												(  _procs.getJSONObject(j) ).put("estado", "Comienza proceso carga...");
											}
										}
										json.put("procesos", _procs);
										json.put("time_inicio_carga", new Date().getTime());
										UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);

										UtilLogProcesos.debug(" - Ejecutando proceso ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
										
										
										ICargaMasivaService carga = (ICargaMasivaService) UtilReflectionImpl.getInstance( ((((MiHashMap) origenes.get(i)).get("CLASE_IMPLEMENTACION")).toString()) );
	
										UtilLogProcesos.debug(" - Proceso ejecutado ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
										
										cantidadProcesos++;
										try{
											int estadoProceso = carga.execute( (MiHashMap) origenes.get(i));
											estado = (estadoProceso == 1);
										} catch(Exception e){
											
										}
	
										dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
										json = new JSONObject(dataBlocked);
										_procs = (JSONArray)json.get("procesos");
										for(int j=0; j< _procs.length(); j++){
											if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (MiHashMap) origenes.get(i)  ).get("CODIGO")  )  ){
												(  _procs.getJSONObject(j) ).put("estado", "Término proceso carga...");
											}
										}
										json.put("time_termino_carga", new Date().getTime());
										UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
										
										
										
										if(estado && ((HashMap) origenes.get(i)).get("CLASE_POST_EXEC")!= null ){
											if(((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC") != null && ((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC").toString().trim().length() > 3){
											
												dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
												json = new JSONObject(dataBlocked);
												_procs = (JSONArray)json.get("procesos");
												for(int j=0; j< _procs.length(); j++){
													if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (MiHashMap) origenes.get(i)  ).get("CODIGO")  )  ){
														(  _procs.getJSONObject(j) ).put("estado", "Inicio proceso consolidación...");
													}
												}
												json.put("time_inicio_consolidacion", new Date().getTime());
												UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
												
												UtilLogProcesos.debug(" - Ejecutando Post Proceso ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
												
												IPostCarga postCarga = (IPostCarga) UtilReflectionImpl.getInstance( ((((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC")).toString()) );	
												
												UtilLogProcesos.debug(" - Post Proceso Ejecutado ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
												
												estado = postCarga.execute((HashMap)session,(HashMap) parameters, (MiHashMap) origenes.get(i));
											
												dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
												json = new JSONObject(dataBlocked);
												_procs = (JSONArray)json.get("procesos");
												for(int j=0; j< _procs.length(); j++){
													if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (MiHashMap) origenes.get(i)  ).get("CODIGO")  )  ){
														(  _procs.getJSONObject(j) ).put("estado", "Término proceso consolidación...");
													}
												}
												json.put("time_termino_consolidacion", new Date().getTime());
												UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
												
											}
										}
										
										dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
										_procs = (JSONArray)json.get("procesos");
										for(int j=0; j< _procs.length(); j++){
											if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (MiHashMap) origenes.get(i)  ).get("CODIGO")  )  ){
												if(estado){
													(  _procs.getJSONObject(j) ).put("estado", "Terminado satisfactoriamente.");
												}
												else{
													(  _procs.getJSONObject(j) ).put("estado", "Terminado con errores.");
												}
											}
										}
										json.put("procesos", _procs);
										json.put("time_termino", new Date().getTime());
										UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
										
										//cambia estado solo si es invalidante
										if( ((MiHashMap) origenes.get(i)).get("invalidante").toString().equals("1") ){
											origenesEjecutados = origenesEjecutados && estado;
										}
										
										UtilLogWorkflow.info("estado proceso ["+ ((MiHashMap) origenes.get(i)).get("CODIGO") +"]:"+estado);
										UtilLogProcesos.info("estado proceso ["+ ((MiHashMap) origenes.get(i)).get("CODIGO") +"]:"+estado);
										JSONObject proceso = new JSONObject();
										proceso.put("codigo",((MiHashMap) origenes.get(i)).get("CODIGO"));
										proceso.put("estado",estado);
										proceso.put("detalle", ((HashMap)session).get(((MiHashMap) origenes.get(i)).get("CODIGO")));
										
										UtilLogWorkflow.info("data proceso-> "+ proceso);
										procesos.add(proceso);
									}
								}
								
								
								
								if(origenesEjecutados){
							
									
									UtilLogProcesos.debug(" Iniciando asignando Folios ["+((HashMap)session).get("data_session")+"]...");
									JSONObject jsonFoliacion = new JSONObject();
									((JSONArray)json.get("procesos")).put(jsonFoliacion);
									jsonFoliacion.put("codigo", "ASIGNACIÓN FOLIOS");
									
									jsonFoliacion.put("estado", "Asignando Folios...");
									UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
									
									
									boolean resultado = EjecutarComandoAS400.ejecutar( PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.comando.generacion.folio") );
									if(resultado){									
										jsonFoliacion.put("estado", "Asignación de Folios Terminada.");
										
										UtilLogProcesos.debug(" Terminando Asignación de Folios ["+((HashMap)session).get("data_session")+"]...");
									}
									else{

										jsonFoliacion.put("estado", "Asignación de Folios Terminada, con errores");
										
										UtilLogProcesos.debug(" Terminando Asignación de Folios ["+((HashMap)session).get("data_session")+"] con errores");
									}
									
									UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
									
								}
								
								dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
								ConfiguradorHelper.cerrarProcesoBloqueado("2", "Fin proceso generaci&oacuten");
								
								
								UtilLogWorkflow.info("estado procesos: "+ procesos);
								UtilLogWorkflow.info("origenesEjecutados -> "+ origenesEjecutados);
								HashMap resultados = new HashMap();
								resultados.put("procesos", procesos);
								resultados.put("status", origenesEjecutados+"");
								resultados.put("cantidad_procesos", cantidadProcesos+"");
								((HashMap)session).put("run_resultados", resultados);
								UtilesWorkflow.crearArchivoConDataJson(session, parameters, pathCompleto+"/2.txt");
								AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.RECURSOS_CERTIFICADOS, EstadosWorkflow.DATA_CONSOLIDADA);
								UtilLogProcesos.debug("Terminando proceso carga / consolidación ["+((HashMap)session).get("data_session")+"]...");
								ConfiguradorHelper.callURLResultadosGeneracion("consolidacion");
							} catch (Exception e) {
								UtilLogErrores.error(e);
								AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
								e.printStackTrace();
								
							}
							
													
						} catch (JSONException e) {
							UtilLogErrores.error(e);
							AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
							e.printStackTrace();
							salida.put("descripcion", "Problemas al escribir en el archivo blocked.txt en ["+key+"]");
							salida.put("codigo", "E009");
							salida.put("tipo", "ERROR");
						} catch (Exception e) {
							UtilLogErrores.error(e);
							AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
							e.printStackTrace();
							salida.put("descripcion", "Problemas al mover el archivo SAP en ["+key+"]");
							salida.put("codigo", "E011");
							salida.put("tipo", "ERROR");
						}
					}
					else{
						salida.put("descripcion", "Proceso Blokeado ["+key+"]");
						salida.put("codigo", "E010");
						salida.put("tipo", "ERROR");
					}
				}catch(Exception e){
					e.printStackTrace();
					UtilLogErrores.error(e);
					AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
				}
			}
		}).start();
		
		return salida;
	}

	

	public HashMap ejecutarProcesoConsolidacionUnitario(Object session, Object parameters) {
		HashMap salida = new HashMap();
		boolean estado = false;	
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		MiHashMap pars = new MiHashMap();
		pars.put("estado", 1+"");
		pars.put("codigo", ((HashMap)parameters).get("codigo_proceso") );
		try {
			ArrayList origenes = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerProcesosCarga", pars);
			if(origenes != null && origenes.size() == 1){
				for(int i=0; i<origenes.size(); i++){
					UtilLogProcesos.debug(" - Ejecutando proceso unitario ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
					
					if(( (MiHashMap) origenes.get(i)  ).get("PRE_EJECUCION")!= null){		
						if(( (MiHashMap) origenes.get(i)  ).get("PRE_EJECUCION").toString().startsWith("java_reflection")){
							try{
								String[] partes = ( (MiHashMap) origenes.get(i)  ).get("PRE_EJECUCION").toString().split(";");
								UtilReflectionImpl.executeReflection(partes[1], partes[2]);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						
					}
					
					ICargaMasivaService carga = (ICargaMasivaService) UtilReflectionImpl.getInstance( ((((MiHashMap) origenes.get(i)).get("CLASE_IMPLEMENTACION")).toString()) );

					UtilLogProcesos.debug(" - Proceso ejecutado unitario ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
					try{
						int estadoProceso = carga.execute( (MiHashMap) origenes.get(i));
						estado = (estadoProceso == 1);
					} catch(Exception e){
						
					}

					if(estado && ((HashMap) origenes.get(i)).get("CLASE_POST_EXEC")!= null ){
						if(((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC") != null && ((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC").toString().trim().length() > 3){
						
						
							UtilLogProcesos.debug(" - Ejecutando Post Proceso Unitario ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
							
							IPostCarga postCarga = (IPostCarga) UtilReflectionImpl.getInstance( ((((MiHashMap) origenes.get(i)).get("CLASE_POST_EXEC")).toString()) );	
							
							UtilLogProcesos.debug(" - Post Proceso Ejecutado Unitario ["+( (MiHashMap) origenes.get(i)  ).get("CODIGO") +"]...");
							
							estado = postCarga.execute((HashMap)session,(HashMap) parameters, (MiHashMap) origenes.get(i));	
							
						}
					}
					salida.put("estado", estado+"");
				}
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			salida.put("estado", estado+"");
		return salida;
	}


	public HashMap rollbackProcesoCarga(Object session, Object parameters) {
		UtilLogWorkflow.info("rollback proceso:");
		HashMap salida = new HashMap();

		String key = ((HashMap)parameters).get("periodo").toString();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+"/";
		String rutaWF = pathCompleto + ((HashMap)parameters).get("periodo");

		UtilLogWorkflow.info("eliminando blocked.txt ?" + new File(rutaWF +"/blocked.txt").delete());
		UtilLogWorkflow.info("eliminando 2.txt ?" + new File(rutaWF +"/2.txt").delete());
		UtilLogWorkflow.info("ruta -> "+ rutaWF +"2.txt");	
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			MiHashMap pars = new MiHashMap();
			pars.put("periodo", ((HashMap)parameters).get("periodo"));

			CargaNoBatch.limpiarArchivosTemporales();
			PoolHebras.eliminarHebras(null);
			
			int deleteMagallanes = sqlMap.delete("carga_SAP.deleteDataMagallanes",pars);
			UtilLogWorkflow.info("data eliminada Mag: "+ deleteMagallanes);
			int deleteMirror = sqlMap.delete("carga_SAP.deleteDataMirror",pars);
			UtilLogWorkflow.info("data eliminada 10f1: "+ deleteMirror);
			int deleteMirrorAnexo = sqlMap.delete("carga_SAP.deleteDataSAPAnexoAll",key);
			UtilLogWorkflow.info("data eliminada 10f1a: "+ deleteMirrorAnexo);
 			int deleteNominas = sqlMap.delete("carga_SAP.deleteDataNomina",pars);
			UtilLogWorkflow.info("data eliminada 15f1: "+ deleteNominas);
			int deleteErrores = sqlMap.delete("carga_SAP.deleteErrorCargaSinacaff",pars);
			UtilLogWorkflow.info("data eliminada Sinacaff: "+ deleteErrores);
			
			PoolHebras.eliminarHebras(null);
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			contadorRegistrosNOOK++;
		}	
		return salida;
	}
	
	
	public void limpiarTablasPeriodo(){
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			MiHashMap pars = new MiHashMap();
			
			int deleteMagallanes = sqlMap.delete("carga_SAP.deleteDataMagallanes",pars);
			UtilLogWorkflow.info("data eliminada Mag: "+ deleteMagallanes);
			int deleteMirror = sqlMap.delete("carga_SAP.deleteDataMirror",pars);
			UtilLogWorkflow.info("data eliminada: "+ deleteMirror);
			int deleteNominas = sqlMap.delete("carga_SAP.deleteDataNomina",pars);
			UtilLogWorkflow.info("data eliminada: "+ deleteNominas);
			int deleteErrores = sqlMap.delete("carga_SAP.deleteErrorCargaSinacaff",pars);
			UtilLogWorkflow.info("data eliminada: "+ deleteErrores);
			int deleteBP = sqlMap.delete("carga_SAP.deleteDataBP",pars);
			UtilLogWorkflow.info("data eliminada: "+ deleteBP);
			int deleteDataPBS = sqlMap.delete("carga_SAP.deleteDataPBS",pars);
			UtilLogWorkflow.info("data eliminada: "+ deleteDataPBS);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			contadorRegistrosNOOK++;
		}	
		
		IPSHelper.limpiarTablasIPS();
	}
	
	static int cantidadMaximaRegistros = Integer.parseInt( PropertiesUtil.propertiesMemoria.getString("config.memoria.cantidad.maxima.insert")  );
	
	static boolean ejecutarConsolidacionConBatch= false;
	static CargaNoBatch cargaNoBatch = null;
	
	
	public static void procesandoRegistro(HashMap o1){
	
		try{
			if(ejecutarConsolidacionConBatch){

					Connection conn = JDBCUtil.getConnection();
					if(contadorRegistros ==0){
						if(ejecutarBatch){
						stmt = conn.createStatement();		
						conn.setAutoCommit(false);
						}
					}
					MiHashMap o = new MiHashMap();
					o.toMiHashMap(o1);
					
					try{
						if(o.get("RUT_EMPRESA") != null && Integer.parseInt(o.get("RUT_EMPRESA").toString())==0){
							o.put("RUT_EMPRESA","0-0");
						}
					
					}
					catch(Exception e){}
					
					String query = JDBCUtil.getQueryCompleta("query.jdbc.insert.data.archivo."+o.get("__origen"), o);
				
					String[] partes = query.split("#");
					for(int i=0; i< partes.length; i++){
						if(i%2 == 1){
							query = query.replaceAll("#"+partes[i]+"#", "");
						}
						
					}
					query = query.replaceAll("'","");
					
					stmt.addBatch(query);
					//ManejoArchivoTXT.putLineFromFileOpened(archivoQuerys, query);
					contadorRegistrosOK++;
					contadorRegistros++;
					if(contadorRegistros % 1000 == 0){
						UtilLogWorkflow.debug(contadorRegistros+"");
					}
					
					if( ejecutarBatch){
						if( MemoriaUtil.isMemoriaCritica()  ){
							UtilLogMemoria.debug("cantidad de registros acumulados ("+cantidadRegistrosAInsertar+") memoria ["+(int)Runtime.getRuntime().totalMemory()/(1024*1024)+"MB]["+(int)Runtime.getRuntime().freeMemory()/(1024*1024)+"MB]["+contadorRegistros+"]");
							UtilLogMemoria.debug("memoria: "+ MemoriaUtil.isMemoriaCritica() +", "+ Runtime.getRuntime().freeMemory());
							int[] result = stmt.executeBatch();
							conn.commit();
							stmt.clearBatch();
							contadorRegistros = 0;	
							System.gc();
							UtilLogMemoria.debug("memoria luego de ejecutar: "+ MemoriaUtil.isMemoriaCritica() +" , "+ Runtime.getRuntime().freeMemory());
							
						}
					}
				}
				else{

					//Nueva implementacion			
					MiHashMap o = new MiHashMap();
					o.toMiHashMap(o1);
					if(cargaNoBatch == null){
						if(o.get("__origen").toString().equals("SAP")){
							cargaNoBatch = new CargaNoBatch("CARGA_SAP", "nrpdta/nrp10f1");
							cargaNoBatch.setIndice((new Date().getTime()/1000)-1600000000);
						}
						else if(o.get("__origen").toString().equals("SINACAFF")){
							cargaNoBatch = new CargaNoBatch("CARGA_SAINACAFF", "nrpdta/nrp10f1");
							cargaNoBatch.setIndice((new Date().getTime()/1000)-1600000000);
						}
					}
					
					
					try{
						o.put("RUT_EMPRESA",Utiles.ifNullOrEmpty(						o.get("RUT_EMPRESA"), "0-0"));
						
						o.put("PERIODO",Utiles.ifNullOrEmpty(						o.get("PERIODO"), "0"));
						o.put("TIPO_NOMINA",Utiles.ifNullOrEmpty(					o.get("TIPO_NOMINA"), "0"));
						o.put("OFICINA_PAGO",Utiles.ifNullOrEmpty(					o.get("OFICINA_PAGO"), "0"));
						o.put("FECHA_VCTO",Utiles.ifNullOrEmpty(					o.get("FECHA_VCTO"), "0"));
						o.put("NUMERO_CUOTA",Utiles.ifNullOrEmpty(					o.get("NUMERO_CUOTA"), "0"));
						o.put("CANTIDAD_CUOTAS",Utiles.ifNullOrEmpty(				o.get("CANTIDAD_CUOTAS"), "0"));
						o.put("IMPORTE_CUOTA_MONEDA_TX",Utiles.ifNullOrEmpty(		o.get("IMPORTE_CUOTA_MONEDA_TX"), "0"));
						o.put("IMPORTE_CUOTA_MONEDA_LOCAL",Utiles.ifNullOrEmpty(	o.get("IMPORTE_CUOTA_MONEDA_LOCAL"), "0"));
						o.put("IMPORTE_GRAVAMEN_MONEDA_LOCAL",Utiles.ifNullOrEmpty(	o.get("IMPORTE_GRAVAMEN_MONEDA_LOCAL"), "0"));
						o.put("CODIGO_MONEDA_LOCAL",Utiles.ifNullOrEmpty(			o.get("CODIGO_MONEDA_LOCAL"), "0"));
						o.put("COTIZACION_MONEDA_TX",Utiles.ifNullOrEmpty(			o.get("COTIZACION_MONEDA_TX"), "0"));
						o.put("BP_ANEXO_SAP",Utiles.ifNullOrEmpty(					o.get("BP_ANEXO_SAP"), "0"));
						o.put("CAJA_PREVIS",Utiles.ifNullOrEmpty(					o.get("CAJA_PREVIS"), "0"));
						o.put("GRUPO_PAGO",Utiles.ifNullOrEmpty(					o.get("GRUPO_PAGO"), "0"));
						o.put("NUM_BENEF",Utiles.ifNullOrEmpty(						o.get("NUM_BENEF"), "0"));
						o.put("SUCUR_CCLA",Utiles.ifNullOrEmpty(					o.get("SUCUR_CCLA"), "0"));
						o.put("CCAF",Utiles.ifNullOrEmpty(							o.get("CCAF"), "0"));
						o.put("TIPOOPERACION",Utiles.ifNullOrEmpty(					o.get("TIPOOPERACION"), "0"));
						o.put("N_MESES_MOROSOS",Utiles.ifNullOrEmpty(				o.get("N_MESES_MOROSOS"), "0"));
						o.put("CODIGO_CAJA_ORIGEN",Utiles.ifNullOrEmpty(			o.get("CODIGO_CAJA_ORIGEN"), "0"));
						o.put("CODIGO_CAJA_DESTINO",Utiles.ifNullOrEmpty(			o.get("CODIGO_CAJA_DESTINO"), "0"));
						o.put("MONTO_COBRAR",Utiles.ifNullOrEmpty(					o.get("MONTO_COBRAR"), "0"));
						o.put("MONTO_PAGADO",Utiles.ifNullOrEmpty(					o.get("MONTO_PAGADO"), "0"));
						o.put("RENTA_LIQUIDA",Utiles.ifNullOrEmpty(					o.get("RENTA_LIQUIDA"), "0"));
						o.put("PORCENTAJE_CARGA_FINANCIERA",Utiles.ifNullOrEmpty(	o.get("PORCENTAJE_CARGA_FINANCIERA"), "0"));
						o.put("CARGA_MAXIMA",Utiles.ifNullOrEmpty(					o.get("CARGA_MAXIMA"), "0"));
						o.put("ID_SERIAL",Utiles.ifNullOrEmpty(						o.get("ID_SERIAL"), "0"));
						o.put("ID_CODIGO_HOLDING",Utiles.ifNullOrEmpty(				o.get("ID_CODIGO_HOLDING"), "0"));
						
						o.put("CODIGO_MONEDA_LOCAL",Utiles.ifNullOrEmpty(				o.get("CODIGO_MONEDA_LOCAL"), "0"));
						o.put("COTIZACION_MONEDA_TX",Utiles.ifNullOrEmpty(				o.get("COTIZACION_MONEDA_TX"), "0"));
						o.put("BP_ANEXO_SAP",Utiles.ifNullOrEmpty(				o.get("BP_ANEXO_SAP"), "0"));
						cargaNoBatch.setIndice(cargaNoBatch.getIndice()+1);
						o.put("ID_REGISTRO", cargaNoBatch.getIndice());
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
					String registro = JDBCUtil.getQueryCompleta("query.jdbc.registro.10f1."+o.get("__origen"), o);
					
					String[] partes = registro.split("#");
					for(int i=0; i< partes.length; i++){
						if(i%2 == 1){
							registro = registro.replaceAll("#"+partes[i]+"#", "");
						}
						
					}
					registro = registro.replaceAll("'","");
					registro = registro.replaceAll("                      ;", ";");
					registro = registro.replaceAll("                     ;", ";");
					registro = registro.replaceAll("                    ;", ";");
					registro = registro.replaceAll("                   ;", ";");
					registro = registro.replaceAll("                  ;", ";");
					registro = registro.replaceAll("                 ;", ";");
					registro = registro.replaceAll("                ;", ";");
					registro = registro.replaceAll("               ;", ";");
					registro = registro.replaceAll("              ;", ";");
					registro = registro.replaceAll("             ;", ";");
					registro = registro.replaceAll("            ;", ";");
					registro = registro.replaceAll("           ;", ";");
					registro = registro.replaceAll("          ;", ";");
					registro = registro.replaceAll("         ;", ";");
					registro = registro.replaceAll("        ;", ";");
					registro = registro.replaceAll("       ;", ";");
					registro = registro.replaceAll("      ;", ";");
					registro = registro.replaceAll("     ;", ";");
					registro = registro.replaceAll("    ;", ";");
					registro = registro.replaceAll("   ;", ";");
					registro = registro.replaceAll("  ;", ";");
					registro = registro.replaceAll(" ;", ";");
					cargaNoBatch.agregarRegistro(registro);
					
				}
			
		}catch(Exception e){

			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			e.printStackTrace();
		}
	}
	

	static boolean terminado = false;
	public void validarUltimosRegistros() throws InterruptedException{
		
		if(ejecutarConsolidacionConBatch){
		
			UtilLogWorkflow.info("validando ultimos registros...");
			Thread.sleep(10000);
			Connection conn = JDBCUtil.getConnection();
			try{
				if(conn != null && stmt != null){
					int[] result = stmt.executeBatch();
					UtilLogWorkflow.info("The number of rows inserted: "+ result.length);
					conn.commit();
					stmt.clearBatch();
				}
			}catch(Exception e){
		
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
				e.printStackTrace();
			}
			
		}
		else{
			try {
				cargaNoBatch.ejecutarComando();
				cargaNoBatch.terminarCarga();
				cargaNoBatch = null;
			} catch (IOException e) {
				e.printStackTrace();
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
				
			}

		}
	}
	
	static int contadorRegistrosMagallanes= 0;
	public static void procesandoRegistroMagallanes(HashMap data) throws SQLException{
		contadorRegistrosMagallanes++;
		/*if(contadorRegistrosMagallanes % 20 == 0) {
			System.out.print(","+contadorRegistrosMagallanes);
		}*/
		if(contadorRegistrosMagallanes % 100 == 0) {
			UtilLogWorkflow.debug(","+contadorRegistrosMagallanes);
		}
		data.put("FICHA", ((Double)data.get("EST")).intValue()+"");

		String periodo =  ((Double)data.get("YEAR")).intValue() +""+ cl.liv.export.txt.util.Utiles.rellenarTexto(((Double)data.get("MES")).intValue()+"", 2, "0", "derecha") ;
		data.put("PERIODO", periodo);
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try{
			sqlMap.insert("carga_SAP.agregarRegistroMagallanes", data);
		}
		catch(Exception e){
		}
	}
	
	public static void limpiarTablaMagallanes() {
		if(contadorRegistrosMagallanes % 10 == 0) {
			UtilLogWorkflow.debug("limpiando tabla magallanes...");
		}
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try{
			sqlMap.insert("carga_SAP.deleteDataMagallanes");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Boolean validarUltimosRegistrosMagallanes() throws Exception{
		System.out.println("terminando carga magallanes...");
		UtilLogWorkflow.info("terminando carga magallanes...");
		contadorRegistrosMagallanes = 0;
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		Object data = (Object)sqlMap.queryForObject("carga_SAP.validarDataMagallanesCargada");
		UtilLogWorkflow.info("cantidad registros magallanes: " + Integer.parseInt( ((MiHashMap) data).get("CONTADOR").toString() ));
		if(data == null || (data != null && Integer.parseInt( ((MiHashMap) data).get("CONTADOR").toString() ) == 0)){
			UtilLogWorkflow.info("Validación erronea");
			return Boolean.FALSE;
		}
		UtilLogWorkflow.info("Validación exitosa");
		return Boolean.TRUE;
	}
	
	public HashMap obtenerEstadoAvance(Object session, Object parameters) throws JSONException {
		UtilLogWorkflow.info("rollback proceso:");
		HashMap salida = new HashMap();


		String key = formato.format(new Date());
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		String rutaCompletaArchivoSAP = "";
		String rutaFileBlocked = pathCompleto+"blocked.txt";
		
		
		String data = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
		JSONObject json = new JSONObject(data);
		
		salida.put("data", json);
		
		return salida;
	}
	
	public HashMap desbloquearProceso(Object session, Object parameters) throws JSONException {
		UtilLogWorkflow.info("desbloqueando proceso:");
		PoolHebras.eliminarHebras(null);
		HashMap salida = new HashMap();
		String key = formato.format(new Date());
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		salida.put("estado", new Boolean(ArchivosUtiles.eliminarArchivoPorRuta(pathCompleto+"blocked.txt")));
		return salida;
	}
	public HashMap obtenerDetalleEtapaProceso(Object session, Object parameters) throws JSONException {
		UtilLogWorkflow.info("rollback proceso:");
		HashMap salida = new HashMap();
		
		String periodo = ((HashMap)parameters).get("periodo").toString();
		String etapa = ((HashMap)parameters).get("etapa").toString();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+periodo+"/";
		String rutaArchivo = pathCompleto+etapa+".txt";
		if(ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaArchivo)){
			String contenido = ManejoArchivoTXT.getFileContentAsString(rutaArchivo);
			salida.put("contenido_archivo", new JSONObject(contenido) );
		}
		else{
			salida.put("contenido_archivo", "archivo no encontrado" );
		}
		return salida;
	}
	

	public HashMap obtenerArbolDeProcesos(Object session, Object parameters) throws JSONException {
		
		HashMap salida = new HashMap();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio");

		File f = new File(pathCompleto);
		if(f.exists() && f.isDirectory()){
			File[] directorios = f.listFiles();
			
			ArrayList nombres = new ArrayList();
			for(int i=0; i< directorios.length; i++){
				nombres.add(directorios[i].getAbsolutePath());
			}
			Collections.sort(nombres, new Comparator() {

				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					return ((String)o1).compareTo((String)o2 )*-1;
				}
			});
			JSONArray procesos = new JSONArray();
			for(int i=0; i< nombres.size(); i++){
				File directorio = new File((String)nombres.get(i));
				if(directorio.isDirectory()){
					JSONObject json = new JSONObject();
					json.put("periodo", directorio.getName());
					File[] archivos = directorio.listFiles();
	
					long fechaModificacion = new Date().getTime();
					String ultimoArchivo = "";
					for(int j=0; j<archivos.length; j++){
						if(archivos[j].lastModified() < fechaModificacion ) {
							ultimoArchivo =archivos[j].getName(); 
						}
						
						if(archivos[j].getName().contains("blocked")){
							json.put("bloqueado", true);
						}
					}
					if(ultimoArchivo.length()>0){
						json.put("ultimo_archivo", ultimoArchivo);
					}
					procesos.put(json);
				}
			}
			JSONObject out = new JSONObject();
			out.put("procesos",procesos);
			salida.put("procesos", out);
		}
		return salida;
	}

	public HashMap crearEstructuraSiNoExisteYBloqueaAntiguos(Object session, Object parameters) throws JSONException {
		
		UtilLogProcesos.debug("Iniciando proceso crear Estructura ["+((HashMap)session).get("data_session")+"]...");
		
		HashMap salida = new HashMap();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio");
		String key = formato.format(new Date());
		if(((HashMap)parameters).get("periodo")!=null){
			key = ((HashMap)parameters).get("periodo").toString();
		}
		
		File[] directorios = new File(pathCompleto).listFiles();
		boolean directorioCreado = false;
		for(int i=0; i< directorios.length; i++){
			if(!directorios[i].getName().equals(key)){
				if( !ArchivosUtiles.existeArchivoPorRutaAsBoolean(directorios[i].getAbsolutePath()+"/blocked.txt") ){
					JSONObject json = new JSONObject();
					json.put("fecha_creacion", formatoFecha.format(new Date()));
					json.put("autor", "AutomÃ¡tico");
					json.put("descripcion_corta", "Fuera de Plazo");
					json.put("status_blocked", "-1");
					json.put("descripcion", "Proceso bloqueado. Proceso Fuera de Fecha");
					UtilesWorkflow.crearArchivoConDataJson(directorios[i].getAbsolutePath()+"/blocked.txt", json);
					
				}
			}
			else{
				directorioCreado = true;
			}
		}
		
		if(!directorioCreado){
			HashMap parametros = new HashMap();
			parametros.put("periodo", key);
			generaEstructuraWorklow(session, parametros);
		}
		
		salida.put("estado", ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto)+"/"+key+"/0.txt");
		
		UtilLogProcesos.debug("Terminando proceso crear Estructura ["+((HashMap)session).get("data_session")+"]");
		return salida;
	}
	
	public HashMap limpiarEstructuraProceso(Object session, Object parameters) throws JSONException {
		
		HashMap salida = new HashMap();
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio");
		String key = formato.format(new Date());
		try{
			rollbackProcesoCarga(session, parameters);
		}catch(Exception e){
			
		}
		
		UtilLogWorkflow.info("eliminando blocked.txt ?" + new File(pathCompleto+"/"+key +"/blocked.txt").delete());
		UtilLogWorkflow.info("eliminando 2.txt ?" + new File(pathCompleto+"/"+key +"/2.txt").delete());
		UtilLogWorkflow.info("eliminando 1.txt ?" + new File(pathCompleto+"/"+key +"/1.txt").delete());
		
		salida.put("estado", !ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto)+"/"+key+"/0.txt");
		
		return salida;
	}
	
	
	public HashMap eliminarProceso(Object session, Object parameters) throws JSONException {

		String key = ((HashMap)parameters).get("periodo").toString();

		final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		final String rutaFileBlocked = pathCompleto+"blocked.txt";
		if(!ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
			JSONObject dataFile = new JSONObject();
			try {
				dataFile.put("fecha_creacion", WorkFlowHelper.formatoFecha.format(new Date()));
				dataFile.put("autor", ((HashMap)parameters).get("email"));
				dataFile.put("status_blocked", "1");
				dataFile.put("descripcion", "Eliminando proceso");
				dataFile.put("reintentar_en", ConstantesUtiles.TIEMPO_ESTANDAR_REFRESCO_BLOQUEO);
				UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
			}
			catch(Exception e){
				
			}
		}
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		if(key.trim().length()>0){
			ArchivosUtiles.eliminarDirectorioRecursivo(pathCompleto);
			try{
				rollbackProcesoCarga(session, parameters);
			}catch(Exception e){
				
			}
			salida.put("estado", !ArchivosUtiles.existeArchivoPorRutaAsBoolean(pathCompleto)+"/"+key+"/0.txt");
			ArchivosUtiles.eliminarDirectorioRecursivo(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+key+"/");
		}
		ArchivosUtiles.eliminarArchivoPorRuta(rutaFileBlocked);
		
		return salida;
	}
	
	
	public HashMap enviarNominaSAP(final Object session, final Object parameters) throws JSONException, SQLException, InterruptedException {
		UtilLogProcesos.debug(" Iniciando Enviando nómina SAP ["+((HashMap)session).get("data_session")+"]...");
		
		final String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
		final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
		
		HashMap salida = new HashMap();
		salida.put("estado", Boolean.FALSE);
		final String rutaFileBlocked = pathCompleto+"blocked.txt";
		if(!ArchivosUtiles.existeArchivoPorRutaAsBoolean(rutaFileBlocked)){
			JSONObject dataFile = new JSONObject();
			try {
				dataFile.put("fecha_creacion", WorkFlowHelper.formatoFecha.format(new Date()));
				dataFile.put("autor", ((HashMap)parameters).get("email"));
				dataFile.put("status_blocked", "");
				dataFile.put("descripcion", "Comienza el proceso de env&iacute;o a SAP");
				dataFile.put("reintentar_en", ConstantesUtiles.TIEMPO_ESTANDAR_REFRESCO_BLOQUEO);
				UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, dataFile);
			}
			catch(Exception e){
				
			}
		}

		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				
				SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
				
				MiHashMap dataNomina;
				try {
					dataNomina = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerMinimoMaximoData");
					int minimo = Integer.parseInt(dataNomina.get("minimo").toString());
					int maximo = Integer.parseInt(dataNomina.get("maximo").toString());
					
					int desde = minimo -1 ;
					int hasta = 0;
					int porcion = Integer.parseInt(PropertiesUtil.workflowProperties.getString("config.porcion.generacion.sap"));
					boolean quedan = true;
					int generado = 0;
					ArrayList rutaArchivos = new ArrayList();
					while( quedan ){
						hasta = desde + porcion;
						String condicion = " where id_data_archivo > "+ desde + " and id_data_archivo <="+ hasta;
						String ruta = new GenerarTXT().generar("envio_nrp_sap", "condicion:"+condicion,"csv",";", null);
						rutaArchivos.add(ruta);
						desde = hasta;
						if(hasta < maximo){
							quedan = true;
						}
						else{
							quedan = false;
						}
						generado++;
					}
					
					String rutaConsolidado = ArchivosUtiles.sumarArchivos(rutaArchivos, PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+key+"/SAP_"+key);
					HashMap resultados = new HashMap();
					String rutaUpload = "";
					HashMap salida = new HashMap();
					salida.put("estado", Boolean.FALSE);
					if(rutaConsolidado != null){
						MiHashMap config = (MiHashMap)sqlMap.queryForObject("carga_SAP.obtenerInformacionCargaSAP");
						if(config != null){
							String comando =config.get("COMANDO").toString();
							String archivo = config.get("ARCHIVO").toString();
							
							//rutaUpload = ArchivosUtiles.guardarArchivoPorRutaFTP(PropertiesUtil.workflowProperties.getString("config.envio.sap.comando"), PropertiesUtil.workflowProperties.getString("config.envio.sap.file.name"), rutaConsolidado);
							rutaUpload = ArchivosUtiles.guardarArchivoPorRutaFTP(comando, archivo, rutaConsolidado);
							if(rutaUpload != null){
								if(rutaUpload.indexOf("error:")==0){
									salida.put("estado", Boolean.FALSE);
									salida.put("error", rutaUpload);
								}
								else{
									salida.put("estado", Boolean.TRUE);
									salida.put("success", "Archivo Subido Correctamente " );
									salida.put("error", null);
									guardarDataHistorica();
								}
							}
						}
					}
					else{
						salida.put("estado", Boolean.FALSE);
						salida.put("error", "error al generar consolidado");
						salida.put("success", null);
					}
					if(rutaArchivos != null && rutaArchivos.size()>0){
						try{
							for(int i=0; i< rutaArchivos.size(); i++){
								new File(rutaArchivos.get(i).toString()).delete();
							}
							new File(rutaConsolidado).delete();
						}catch(Exception e){
						}
					}
					
					
					resultados.put("status", salida.get("estado"));
					resultados.put("error", salida.get("error"));
					resultados.put("success", salida.get("success"));
					resultados.put("ruta_upload", rutaUpload);
					((HashMap)session).put("resultados", resultados);
					UtilesWorkflow.crearArchivoConDataJson(session, parameters, pathCompleto+"/5.txt");
					if(salida.get("estado").equals(Boolean.FALSE)){
						ConfiguradorHelper.cerrarProcesoBloqueado("5", "Fin proceso env&iacute; a SAP");
						AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ANTES_ENVIO_SAP, EstadosWorkflow.ANTES_ENVIO_SAP);
						UtilLogProcesos.debug(" Terminando Enviando nómina SAP ["+((HashMap)session).get("data_session")+"]...");
							
					}
					else {
						ConfiguradorHelper.cerrarProcesoBloqueado("5", "Fin proceso env&iacute; a SAP");
						AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ANTES_ENVIO_SAP, EstadosWorkflow.DESPUES_ENVIO_SAP);
						UtilLogProcesos.debug(" Terminando Enviando nómina SAP ["+((HashMap)session).get("data_session")+"]...");
						AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.PROCESO_TERMINADO, EstadosWorkflow.PROCESO_TERMINADO);
						
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}).start();;
		
		return salida;
	}
	
	
	public static void guardarDataHistorica(){

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			sqlMap.insert("carga_SAP.guardarDataHistorica");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPSHelper.cargarHistoricoIPS();
		
		
		
	}
	
	public HashMap asignarFolios(Object session, Object parameters) throws JSONException, SQLException {
		
		HashMap salida = new HashMap();

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		ArrayList dataNominas = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerDataNominasParaFolio");
		
		for(int i=0; i<dataNominas.size(); i++){
			if(dataNominas.get(i) !=null){
				((MiHashMap)dataNominas.get(i)).put("FOLIO_ASIGNADO", ProxyAS400.getInstancia().obtenerFolio());
				sqlMap.update("carga_SAP.setearFolio",dataNominas.get(i));
			}
		}
		return salida;
	}
	
	
	
	public HashMap eliminarArchivosExtranet(Object session, Object parameters) throws JSONException, SQLException, IOException {
		
		HashMap salida = new HashMap();
		String pathExtranet = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.path.nominas");
		AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.password"));
		ArchivosAS400.eliminarArchivosExistentes(conexion, pathExtranet);
		conexion.disconnectAllServices();
		return salida;
	}
	
	
	public HashMap detenerCargas(Object session, Object parameters) throws JSONException, SQLException, IOException {
		
		HashMap salida = new HashMap();
		CargaMasivaFromBDImpl.detenerProceso =true;
		CargaMasivaImpl.detenerProceso =true;
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Thread.sleep(120000);

					CargaMasivaFromBDImpl.detenerProceso =false;
					CargaMasivaImpl.detenerProceso =false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		salida.put("carga_masiva_bd", new Boolean( !CargaMasivaFromBDImpl.detenerProceso ));
		salida.put("carga_masiva", new Boolean( !CargaMasivaImpl.detenerProceso ));
				
		
		return salida;
	}
	
	public HashMap habilitarCargas(Object session, Object parameters) throws JSONException, SQLException, IOException {
		
		HashMap salida = new HashMap();

		CargaMasivaFromBDImpl.detenerProceso =false;
		CargaMasivaImpl.detenerProceso =false;
		
		salida.put("carga_masiva_bd", new Boolean( !CargaMasivaFromBDImpl.detenerProceso ));
		salida.put("carga_masiva", new Boolean( !CargaMasivaImpl.detenerProceso ));
				
		return salida;
	}
	
	public HashMap statusCargas(Object session, Object parameters) throws JSONException, SQLException, IOException {
		
		HashMap salida = new HashMap();

		salida.put("carga_masiva_bd", new Boolean( !CargaMasivaFromBDImpl.detenerProceso ));
		salida.put("carga_masiva", new Boolean( !CargaMasivaImpl.detenerProceso ));
				
		return salida;
	}
	
	
	
	public HashMap refrescarConfiguraciones(Object session, Object parameters) throws JSONException, SQLException, IOException {
		
		HashMap salida = new HashMap();

		PropertiesUtil.cargar();
		SessionUtil.txts.clear();
		
		return salida;
	}
	
	
	
	public HashMap generarComprobantes(final Object session, final Object parameters) throws IOException, JSONException, SQLException  {
		HashMap salida = new HashMap();

		EjecutarComandoAS400.ejecutar( PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.comando.generacion.comprobantes") );
		
		return salida;
	}
	
	
	public static boolean eliminarComprobantes(String proceso){
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		pars.put("codigo_proceso", proceso);
		try {
			sqlMap.delete("carga_SAP.eliminarComprobantesGenerados", pars);
			sqlMap.delete("carga_SAP.eliminarDetalleComprobantesGenerados", pars);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			UtilLogErrores.debug("Error eliminación comprobantes: "+e.getMessage());
			
		}
		return true;
	}	
	
	
	
	public static HashMap obtenerProcesosNoInvalidantes(Object session, Object parameters){

		HashMap salida = new HashMap();
		ArrayList procesosInvalidantes = new ArrayList();
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			ArrayList procesos = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerProcesosNoInvalidantes");
			if(procesos != null && procesos.size()>0){
				for(int i=0; i< procesos.size(); i++){
					MiHashMap proceso = new MiHashMap();
					proceso.put("codigo", ((MiHashMap)procesos.get(i)).get("CODIGO") );
					proceso.put("nombre", ((MiHashMap)procesos.get(i)).get("NOMBRE") );
					proceso.put("estado", "");
					proceso.put("fecha_inicio", "");
					proceso.put("fecha_termino", "");
					
					JSONObject data = ProcesoNoInvalidanteUtil.getInfoProcesoNoInvalidante(((MiHashMap)procesos.get(i)).get("CODIGO").toString() );
					if(data != null){
						try {
							proceso.put("estado", data.get("estado"));
							proceso.put("fecha_inicio", data.get("fecha_inicio"));
							proceso.put("fecha_termino", data.get("fecha_termino"));
							ArrayList historico = new ArrayList();
							
							if(data.has("historico")){
								if(data.getJSONArray("historico").length()==0){
									HashMap item = new HashMap();
									item.put("estado", data.get("estado") );
									item.put("fecha_inicio", data.get("fecha_inicio") );
									item.put("fecha_termino", data.get("fecha_termino") );
									historico.add(item);
								}
								else{
									for(int j=data.getJSONArray("historico").length() - 1; j>=0; j--){
										HashMap item = new HashMap();
										JSONObject dataProceso = data.getJSONArray("historico").getJSONObject(j);
										item.put("estado", dataProceso.get("estado") );
										item.put("fecha_inicio", dataProceso.get("fecha_inicio") );
										item.put("fecha_termino", dataProceso.get("fecha_termino") );
										historico.add(item);
									}
								}
							}
							
							proceso.put("historico", historico);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					procesosInvalidantes.add(proceso);
				}
				
			}
			salida.put("procesos", procesosInvalidantes);
		} catch (SQLException e) {
			UtilLogErrores.debug("Error eliminación comprobantes: "+e.getMessage());
			
		}
		return salida;
	}	

	
	public  HashMap ejecutarProcesosNoInvalidantes(Object session, Object parameters){

		HashMap salida = new HashMap();
		ArrayList procesosSalida = new ArrayList();
		String procesos = ((HashMap)parameters).get("procesos").toString();
		
		String[] procesosAEjecutar = procesos.split(";");
		
		if(procesosAEjecutar != null && procesosAEjecutar.length>0){
			for(int i=0; i< procesosAEjecutar.length; i++){
				((HashMap)parameters).put("codigo_proceso", procesosAEjecutar[i]);
				ProcesoNoInvalidanteUtil.guardarEsperandoProceso(procesosAEjecutar[i]);
				
			}
			for(int i=0; i< procesosAEjecutar.length; i++){
				((HashMap)parameters).put("codigo_proceso", procesosAEjecutar[i]);
				ProcesoNoInvalidanteUtil.guardarInicioProceso(procesosAEjecutar[i]);
				HashMap out = ejecutarProcesoConsolidacionUnitario(session, parameters);
				out.put("codigo", procesosAEjecutar[i]);
				procesosSalida.add(out);
				ProcesoNoInvalidanteUtil.guardarTerminoProceso(procesosAEjecutar[i]);
			}
		}
		
		
		salida.put("procesos", procesosSalida);
		return salida;
	}	

	public HashMap detenerHebra(Object session, Object parameters) throws JSONException, SQLException, IOException, InterruptedException {
		
		HashMap salida = new HashMap();
		salida.put("estado", false+"");
		if(parameters != null && ((HashMap)parameters).get("id") != null ){
			nombreHebraAEliminar =  ((HashMap)parameters).get("id").toString();
			int tiempoAEsperar = 30000;
			try{  tiempoAEsperar = Integer.parseInt(((HashMap)parameters).get("ttl").toString()); }catch(Exception e){}
			Thread.sleep(tiempoAEsperar);
			if(nombreHebraAEliminar.equals("eliminada")){
				salida.put("estado", true+"");
			}
		}
		return salida;
	}
	



	public HashMap limpiarCarpetaGeneracion(Object session, Object parameters) throws JSONException, SQLException, IOException, InterruptedException {
		
		HashMap salida = new HashMap();
		salida.put("estado", false+"");
		if(parameters != null && ((HashMap)parameters).get("codigo_entidad") != null  &&  ((HashMap)parameters).get("periodo") != null ){
			String codigoEntidad = ((HashMap)parameters).get("codigo_entidad").toString();
			String periodo = ((HashMap)parameters).get("periodo").toString();
			String path = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad;
			
			File directorio = new File(path);
			if(directorio.exists() && directorio.isDirectory()){
				File[] archivos = directorio.listFiles();
				for(int i=0; i< archivos.length; i++){
					archivos[i].delete();
				}
			}
			
		}
		return salida;
	}
	
	public HashMap limpiarCarpetaPublicacion(Object session, Object parameters) throws JSONException, SQLException, IOException, InterruptedException {
		
		UtilLogProcesos.debug("Iniciando limpieza de carpeta de publicacion ["+PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.server")+":"+PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.path.nominas")+"]");
		UtilLogProcesos.debug("autenticando...");
		AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.password"));
		UtilLogProcesos.debug("Ejecutando Limpieza....");
		ArchivosAS400.eliminarArchivosExistentes(conexion, PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.path.nominas"));
		UtilLogProcesos.debug("Limpieza Ejecutada");	
		conexion.disconnectAllServices();
		
		return null;
	}
	

	public HashMap testGeneracion(Object session, Object parameters) throws JSONException, SQLException, IOException, InterruptedException {
		
		HashMap salida = new HashMap();
		
		new GenerarTXT().generar("envio_nrp_sap", "", "txt", ";", null);
		
		return salida;
	}
	
	public static boolean isHebraMuerta(){
		try{
			if( Thread.currentThread().getName().equals(nombreHebraAEliminar) ){
				nombreHebraAEliminar = "eliminada";
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public synchronized HashMap hebraTest(Object session, Object parameters) throws JSONException, SQLException, IOException, InterruptedException {
		
		HashMap salida = new HashMap();
		
		Thread.sleep(10000);
		salida.put("ttl", 10+"");
		if(isHebraMuerta()) return salida;
		Thread.sleep(10000);
		salida.put("ttl", 20+"");
		if(isHebraMuerta()) return salida;
		Thread.sleep(10000);
		salida.put("ttl", 30+"");
		if(isHebraMuerta()) return salida;
		UtilLogWorkflow.debug("thread["+Thread.currentThread().getName()+"] 30 s");
		Thread.sleep(10000);
		salida.put("ttl", 40+"");
		if(isHebraMuerta()) return salida;
		UtilLogWorkflow.debug("thread["+Thread.currentThread().getName()+"] 40 s");
		Thread.sleep(10000);
		salida.put("ttl", 50+"");
		if(isHebraMuerta()) return salida;
		UtilLogWorkflow.debug("thread["+Thread.currentThread().getName()+"] 50 s");
		Thread.sleep(10000);
		salida.put("ttl", 60+"");
		if(isHebraMuerta()) return salida;
		UtilLogWorkflow.debug("thread["+Thread.currentThread().getName()+"] 60 s");
		return salida;
	}
	
	
}
