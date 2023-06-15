package cl.jfactory.planillas.negocio.post.carga;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.jfactory.planillas.service.helper.ResumenesHelper;
import cl.jfactory.planillas.service.util.CargaNoBatch;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EstadosWorkflow;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.MemoriaUtil;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.liv.persitencia.jdbc.util.JDBCUtil;

public class PostCargaSINACAFF implements IPostCarga{
	boolean insertarIncobrable = true;
	static boolean ejecutarBatch = false;
	static String idCargaNoBatch = "POST_CARGA_SINACAFF";
	static String tablaCargaNoBatch =  PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.post.carga.sinacaff.tabla");
	static HashMap entidadesPagadoras = new HashMap();
	public boolean execute(final HashMap session,HashMap parametros, HashMap configs) {
		
		// TODO Auto-generated method stub
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+Utiles.getPeriodoActual()+"/";
		CargaNoBatch cargaNoBatch = new CargaNoBatch(idCargaNoBatch, tablaCargaNoBatch);
		
		try{
			String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
			JSONObject json = new JSONObject(dataBlocked);
			JSONArray _procs = (JSONArray)json.get("procesos");
			for(int j=0; j< _procs.length(); j++){
				if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (HashMap) configs ).get("CODIGO")  )  ){
					(  _procs.getJSONObject(j) ).put("estado", "Comienza proceso carga consolidación...");
				}
			}
			json.put("procesos", _procs);
			UtilesWorkflow.crearArchivoConDataJson(pathCompleto+"blocked.txt", json);
		} catch(Exception e){e.printStackTrace(); UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
		
		}
		
		entidadesPagadoras = new HashMap();
		limpiarPagos();
		
		final MiHashMap config = new MiHashMap();
		config.toMiHashMap(configs);
		UtilLogWorkflow.info("ejecutando post proceso SINACAFF "+ config);
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		ArrayList data;
		try {

			Connection conn = null; //JDBCUtil.getConnection();
			
			int contadorRegistros = 0;
			Statement stmt = null;
			config.put("PERIODO", Utiles.getPeriodoActual());
			MiHashMap configuraciones = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerIdMinimoCargado", config);
			UtilLogWorkflow.info("config-> "+configuraciones);
			int desde = Utiles.getInt(configuraciones.get("DESDE"));
			int hasta = Utiles.getInt(configuraciones.get("HASTA"));
			int cantidadRegistros = Integer.parseInt( configuraciones.get("CANTIDAD_REGISTROS_A_PROCESAR").toString() );
			String periodoAnterior = UtilesComunes.reemplazarVariables("sys.periodo.anterior");
			config.put("PERIODO_ANTERIOR", periodoAnterior);
			UtilLogWorkflow.info("procesando desde ID["+desde+"]["+hasta+"]");
			
			while(desde < hasta){
					if(contadorRegistros ==0){
						if(ejecutarBatch){
							UtilLogWorkflow.debug("a crear statement...");
							stmt = conn.createStatement();		
							conn.setAutoCommit(false);
							stmt.clearBatch();
						}
						else{
							//No implementa batch
						}
					}
					
					

					config.put("ID_DESDE", new Integer(desde));
					config.put("ID_HASTA", new Integer(desde+ cantidadRegistros));
					config.put("CANTIDAD_REGISTROS_A_PROCESAR", new Integer(cantidadRegistros));

					try{
						prepararTablaAnexos(sqlMap,config);
					}catch(Exception e){
						e.printStackTrace();
						UtilLogCargas.debug("Problemas al limpiar tabla anexo");
					}
					
					
					UtilLogWorkflow.debug("parametros: "+ config);
					data = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerDataMirrorSINACAFF", config);
					
					if(data != null){
						for(int i=0;i<data.size(); i++){
							HashMap registro = (HashMap)data.get(i);
							UtilLogWorkflow.debug("****************\n\ninsertando registro data_nomina: "+ registro);
							registro.put("RUT_PAGADOR","-1" );
							registro.put("DV_PAGADOR","1" );
							registro.put("RUT_EMPRESA","-1");
							registro.put("DV_EMPRESA", "1");
							registro.put("RENTAS",0+"");
							
							
							if(registro != null && registro.get("RUT_PAGADOR_COMPLETO")!= null && registro.get("RUT_PAGADOR_COMPLETO").toString().trim().length()>0 && !registro.get("RUT_PAGADOR_COMPLETO").toString().equals("-1")){
								registro.put("RUT_PAGADOR",registro.get("RUT_PAGADOR_COMPLETO").toString().split("-")[0] );
								registro.put("DV_PAGADOR",registro.get("RUT_PAGADOR_COMPLETO").toString().split("-")[1] );
							}
							if(registro != null && registro.get("RUT_EMPRESA_COMPLETO")!= null  && registro.get("RUT_EMPRESA_COMPLETO").toString().trim().length()>0 && !registro.get("RUT_EMPRESA_COMPLETO").toString().equals("-1")){
								registro.put("RUT_EMPRESA",registro.get("RUT_EMPRESA_COMPLETO").toString().split("-")[0] );
								registro.put("DV_EMPRESA",registro.get("RUT_EMPRESA_COMPLETO").toString().split("-")[1] );
							}
							if(registro != null && registro.get("NOMBRE_EMPRESA")!= null){
								registro.put("NOMBRE_EMPRESA", registro.get("NOMBRE_EMPRESA").toString());
							}
							else if(registro != null && registro.get("NOMBRE_EMPRESA_PUBLICA")!= null){
								registro.put("NOMBRE_EMPRESA", registro.get("NOMBRE_EMPRESA_PUBLICA").toString());
							}
							else if(registro != null && registro.get("NOMBRE_EMPRESA_PRIVADA")!= null){
								registro.put("NOMBRE_EMPRESA", registro.get("NOMBRE_EMPRESA_PRIVADA").toString());
							}
							/*else{
								//Puede ser entidad pagadora
								UtilLogWorkflow.debug("entidades pagadoras -> "+ entidadesPagadoras.get(registro.get("RUT_EMPRESA")));
								if(entidadesPagadoras.get(registro.get("RUT_EMPRESA")) == null ){
									MiHashMap entidadPagadora = (MiHashMap)sqlMap.queryForObject("carga_SAP.obtenerNombreEntidadPagadora", registro);
									if(entidadPagadora != null && entidadPagadora.get("MERASO")!=null){
										entidadesPagadoras.put(registro.get("RUT_EMPRESA"),entidadPagadora.get("MERASO"));
										registro.put("NOMBRE_EMPRESA", entidadesPagadoras.get(registro.get("RUT_EMPRESA")));
									}
									UtilLogWorkflow.debug("entidad_pagadora -> "+ entidadPagadora);
								}
								else{
									registro.put("NOMBRE_EMPRESA", entidadesPagadoras.get(registro.get("RUT_EMPRESA")));
								}
								
							}*/
						
							
							if(registro == null || (registro.get("NRO_MESES_MOROSOS")!= null && registro.get("NRO_MESES_MOROSOS").toString().length()==0)){
								registro.put("NRO_MESES_MOROSOS", 0+"");
							}
							if(registro != null && registro.get("ULTIMO_DIA_PERIODO")!= null){
								registro.put("FECHA_VCTO_NOMINA", registro.get("ULTIMO_DIA_PERIODO").toString().replaceAll("-", ""));
								registro.put("FECHA_VCTO_CUOTA", registro.get("ULTIMO_DIA_PERIODO").toString().replaceAll("-", ""));
							}
							if(registro != null && registro.get("DATOS_AFP")!= null){
								String[] partes = registro.get("DATOS_AFP").toString().split("::");
								registro.put("APELLIDO_PATERNO",partes[0]);
								registro.put("APELLIDO_MATERNO",partes[1]);
								registro.put("NOMBRE_DEUDOR",partes[2]);
								registro.put("RENTAS",partes[3]);
								
							}
							
							registro.put("CODIGO_NOMINA", "1");
							registro.put("SUCURSAL_CCLA", "1");
							registro.put("OFICINA_PAGO", "1");
							if(registro != null && registro.get("ANEXO_NOMINA_SAP")!=null){
								registro.put("CODIGO_NOMINA", registro.get("ANEXO_NOMINA_SAP"));
							}
							else{
								MiHashMap anexo = getAnexoNomina(registro.get("RUT_EMPRESA").toString(), registro.get("RUT_PAGADOR").toString());
								if(anexo != null){
									registro.put("CODIGO_NOMINA", anexo.get("ANEXO_NOMINA"));
									registro.put("SUCURSAL_CCLA", anexo.get("SUCURSAL"));
									registro.put("OFICINA_PAGO", anexo.get("OFICINA"));
								}
							}
							
							if( registro.get("CODIGO_NOMINA") != null && registro.get("CODIGO_NOMINA").toString().trim().length() < 3){
								String codigoNomina = cl.liv.export.txt.util.Utiles.rellenarTexto(registro.get("CODIGO_NOMINA").toString().trim(), 3, "0", "derecha");
								registro.put("CODIGO_NOMINA",codigoNomina);
							}
							
							Utiles.asignaValorNoNulo(registro,"PERIODO","0");
							Utiles.asignaValorNoNulo(registro,"TIPO_NOMINA","0");
							Utiles.asignaValorNoNulo(registro,"OFICINA_PAGO","0");
							Utiles.asignaValorNoNulo(registro,"FECHA_VCTO_CUOTA","0");
							Utiles.asignaValorNoNulo(registro,"NRO_CUOTA","0");
							Utiles.asignaValorNoNulo(registro,"CANTIDAD_CUOTAS","0");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_CUOTA_MONEDA_TX","0");
							Utiles.asignaValorNoNulo(registro,"CODIGO_MONEDA_TX","CLP");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_CUOTA_MONEDA_LOCAL","0");
							Utiles.asignaValorNoNulo(registro,"IMPORTE_GRAVE_MONEDA_LOCAL","0");
							Utiles.asignaValorNoNulo(registro,"COTIZACION_MONEDA_TX","0000000000001");
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
							Utiles.asignaValorNoNulo(registro,"APELLIDO_PATERNO"," ");
							Utiles.asignaValorNoNulo(registro,"APELLIDO_MATERNO"," ");
							Utiles.asignaValorNoNulo(registro,"NOMBRE_DEUDOR"," ");
							
							if(registro.get("CODIGO_HOLDING") != null && registro.get("CODIGO_HOLDING").toString().trim().equals("0")) {
								registro.put("CODIGO_HOLDING","");
							}
							
							String idRegistroPago = registro.get("RUT_PAGADOR")+"_"+registro.get("RUT_EMPRESA");

							
							if(registro.get("SUMA_DEUDA_SAP") != null || getPagoAcumulado( idRegistroPago )>0 ){
								//int maximoACobrar = Integer.parseInt(registro.get("RENTA_LIQUIDA").toString()) / 100 * (Integer.parseInt(registro.get("PORCENTAJE_CARGA_FINANC").toString()));
								int maximoACobrar = Integer.parseInt(registro.get("CARGA_MAXIMA").toString());
								BigDecimal deudaAcumulada = new BigDecimal( Integer.parseInt(registro.get("SUMA_DEUDA_SAP").toString()) + getPagoAcumulado( idRegistroPago ) ) ; 
								UtilLogWorkflow.debug("cuota: 			"+ ( new Integer(registro.get("MONTO_COBRAR").toString()).intValue()));
								UtilLogWorkflow.debug("deudaAcumulada: 	"+ deudaAcumulada.intValue());
								UtilLogWorkflow.debug("maximo: 		"+ maximoACobrar);
								UtilLogWorkflow.debug("deuda + cuota : 	"+(deudaAcumulada.intValue()+( new Integer(registro.get("MONTO_COBRAR").toString()).intValue())));
								
								boolean excedeTotalmente = ( ( deudaAcumulada.intValue() >= maximoACobrar ) );
								
								boolean excedeParcialmente = ( ( deudaAcumulada.intValue() < maximoACobrar ) &&
									(deudaAcumulada.intValue() + new Integer(registro.get("MONTO_COBRAR").toString()).intValue() > maximoACobrar)	
									);
								UtilLogWorkflow.debug("excedeTotalmente?: 	"+ excedeTotalmente );
								UtilLogWorkflow.debug("excedeParcialmente?:"+ excedeParcialmente );

								boolean incobrable = false;
								if(excedeParcialmente){
									UtilLogWorkflow.debug("EXCEDE...");
									int montoCobrable = maximoACobrar - deudaAcumulada.intValue() ;
									UtilLogWorkflow.debug("montoCobrable: "+ montoCobrable);
									int montoIncobrable = new Integer(registro.get("MONTO_COBRAR").toString()).intValue() - montoCobrable;
									UtilLogWorkflow.debug("monto incobrable: "+ montoIncobrable);
									
									MiHashMap pars = new MiHashMap();
									pars.putAll(registro);
									pars.put("TIPO_COBRO","I");
									pars.put("TIPO_ERROR","SIN CAPACIDAD FINANCIERA");
									pars.put("MONTO_COBRADO", montoCobrable+"");
									pars.put("ORIGEN", config.get("codigo"));
									pars.put("ID_ARCHIVO", registro.get("ID_DATA_ARCHIVO"));
									UtilLogWorkflow.debug("parametros: "+ pars);
									if(insertarIncobrable){
										try{ insertarIncobrable(sqlMap, pars); } catch (Exception e) {}
									}
									registro.put("MONTO_COBRAR",montoCobrable+"");

									//agregarPago(idRegistroPago, Integer.parseInt(registro.get("MONTO_COBRAR").toString()));
								} 
								
								else if( excedeTotalmente ) {
									//#TIPO_COBRO#,#MONTO_COBRAR#,#TIPO_ERROR#
									incobrable = true;
									UtilLogWorkflow.debug("NO COBRABLE");
									MiHashMap pars = new MiHashMap();
									pars.putAll(registro);
									pars.put("TIPO_COBRO","I");
									
									String mensaje = "SIN CAPACIDAD FINANCIERA";
									if(maximoACobrar == 0){
										mensaje = "SIN RENTA";
									}
									
									pars.put("TIPO_ERROR",mensaje);
									pars.put("MONTO_COBRADO", "0");

									pars.put("ORIGEN", config.get("codigo"));
									pars.put("ID_ARCHIVO", registro.get("ID_DATA_ARCHIVO"));
									if(insertarIncobrable){
										try{ insertarIncobrable(sqlMap, pars); } catch (Exception e) {}
									}
								}
								
								if(!incobrable){
									registro.put("CUOTON", determinaSiEsCuoton(registro,  new Integer( configuraciones.get("CUOTON_SAP").toString().replaceAll("\\.", "") )    ) );
				
									
									//sqlMap.insert("carga_SAP.insertar_data_nomina", data.get(i));
									config.put("PERIODO", registro.get("PERIODO"));
									
									MiHashMap o = new MiHashMap();
									o.toMiHashMap((HashMap)data.get(i));
									String query = JDBCUtil.getQueryCompleta("query.jdbc.insert.data.nomina."+config.get("codigo"), o);
									
									
									if(ejecutarBatch){
										stmt.addBatch(query);
										UtilLogWorkflow.debug("["+i+"]"+ query);
										
										agregarPago(idRegistroPago, Integer.parseInt(registro.get("MONTO_COBRAR").toString()));
										
										if( MemoriaUtil.isMemoriaCritica()  ){
											UtilLogWorkflow.info("ejecutando statement...cantidad registros: ["+cantidadRegistros+"]");
											int[] result = stmt.executeBatch();
											conn.commit();
											stmt.clearBatch();
											contadorRegistros = 0;	
										}
									}
									else{
										//Implementa No batch
										
										String insert = JDBCUtil.getQueryCompleta("query.jdbc.registro.15f1.SINACAFF", o);
										
										String[] partes = insert.split("#");
										for(int j=0; j< partes.length; j++){
											if(j%2 == 1){
												insert = insert.replaceAll("#"+partes[j]+"#", "");
											}
											
										}
										insert = insert.replaceAll("'","");

										cargaNoBatch.agregarRegistro(insert);
										agregarPago(idRegistroPago, Integer.parseInt(registro.get("MONTO_COBRAR").toString()));
										
									}
									contadorRegistros++;
								}
							}
						
					}
					desde = desde + cantidadRegistros;
					//desde = hasta;
				}
			}
			
			
			UtilLogWorkflow.debug("Pagos");
			Iterator it = pagos.keySet().iterator();
			while(it.hasNext()){
				String k = (String)it.next();
				UtilLogWorkflow.debug("["+k+"]: "+ pagos.get(k));
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
				//Implementa no batch
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

			anexos.clear();
			
			JSONObject dataProceso = ResumenesHelper.obtenerResumenesProceso(config);
				
			session.put(config.get("CODIGO"), dataProceso);
					
				
			try{
				String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
				JSONObject json = new JSONObject(dataBlocked);
				JSONArray _procs = (JSONArray)json.get("procesos");
				for(int j=0; j< _procs.length(); j++){
					if(  (  _procs.getJSONObject(j) ).get("codigo").equals( ( (HashMap) configs ).get("CODIGO")  )  ){
						(  _procs.getJSONObject(j) ).put("estado", "Término proceso carga consolidacion. Sin Errores");
					}
				}
				json.put("procesos", _procs);
				UtilesWorkflow.crearArchivoConDataJson(pathCompleto+"blocked.txt", json);
			} catch(Exception e){UtilLogErrores.error(e);e.printStackTrace();
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			
			}
			

			entidadesPagadoras = new HashMap();
			
			return true;
		} catch (SQLException e) {
			UtilLogErrores.error(e);
			e.printStackTrace();
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			
		} 
		
		return false;
	}

	
	private static void insertarIncobrable(SqlMapClient sqlMap, HashMap pars) throws SQLException{
		sqlMap.insert("carga_SAP.insertarMontoIncobrable", pars);
	}
	
	private static String determinaSiEsCuoton(HashMap registro, Integer limiteCuoton){
		String cuoton = "";
		
		if( new Integer( registro.get("MONTO_COBRAR").toString()).intValue() > limiteCuoton.intValue() ){
			cuoton = "X";
		}
		
		return cuoton;
	}
	
	static HashMap pagos = new HashMap();
	
	private static void limpiarPagos(){
		pagos.clear();
		
	}
	
	
	
	private static void agregarPago(String key, int monto){
		Integer montoAsignado = (Integer)pagos.get(key);
		if(montoAsignado == null){
			montoAsignado = new Integer(0);
		}
		montoAsignado = new Integer( (montoAsignado.intValue() + monto)+"");
		pagos.put(key,montoAsignado);
		
	}
	private static int getPagoAcumulado(String key){
		Integer montoAsignado = (Integer)pagos.get(key);
		if(montoAsignado == null){
			montoAsignado = new Integer(0);
		}
		return montoAsignado.intValue();
		
	}
	
	ArrayList anexos = new ArrayList();
	public void prepararTablaAnexos(SqlMapClient sqlMap, MiHashMap params) throws SQLException{
		anexos.clear();
		anexos = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerRegistrosTablaTMPAnexos", params);
		UtilLogWorkflow.debug("cantida de anexos: "+ anexos.size());
		UtilLogWorkflow.debug("anexo: "+ getAnexoNomina("70083600", "8574549"));
	}
	
	
	public MiHashMap getAnexoNomina(String rutEmpresa, String rutPagador){
		if(rutEmpresa.contains("8574549")){
			UtilLogWorkflow.debug(rutEmpresa);
		}
		for(int i=0; i< anexos.size(); i++){
			MiHashMap item = (MiHashMap)anexos.get(i);
			//UtilLogWorkflow.debug(rutEmpresa +":"+ item.get("CMNA").toString() + " || "+ item.get("SE5FAJC").toString()  +":"+  rutPagador);
			/*if(item != null && rutEmpresa.equals(item.get("CMNA").toString())){
				UtilLogWorkflow.debug("empresa encontrada");
			}
			if(item != null && rutPagador.equals(item.get("SE5FAJC").toString())){
				UtilLogWorkflow.debug("rut encontrado");
			}*/
			if(item != null && rutEmpresa.equals(item.get("CMNA").toString()) && rutPagador.equals(item.get("SE5FAJC").toString())){
				return item;
			}
		}
		
		return null;
	}
}
