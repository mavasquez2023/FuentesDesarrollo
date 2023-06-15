package cl.jfactory.planillas.service.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibm.as400.access.AS400;

import cl.jfactory.planillas.service.util.BlockedUtil;
import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EjecutarComandoAS400;
import cl.jfactory.planillas.service.util.EstadosWorkflow;
import cl.jfactory.planillas.service.util.ITerminadorHebra;
import cl.jfactory.planillas.service.util.MailUtil;
import cl.jfactory.planillas.service.util.NRPHebra;
import cl.jfactory.planillas.service.util.PoolHebras;
import cl.jfactory.planillas.service.util.ResultadosUtil;
import cl.jfactory.planillas.service.util.UtilInteger;
import cl.jfactory.planillas.service.util.UtilLogProcesos;
import cl.jfactory.planillas.service.util.UtilLogThread;
import cl.jfactory.planillas.service.util.UtilLogTiempos;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.jfactory.planillas.service.util.Utiles;
import cl.jfactory.planillas.service.util.UtilesWorkflow;
import cl.jfactory.planillas.service.util.ValidacionProcesoGeneracion;
import cl.jfactory.planillas.service.util.ValidacionResultadosGeneracionUtil;
import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.archivos.as400.impl.ArchivosAS400;
import cl.liv.archivos.comun.ArchivosUtiles;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.cargas.masivas.util.UtilLogCargas;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilLogComun;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.comun.utiles.logs.UtilLogGeneracion;
import cl.liv.export.comun.util.Funciones;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;
import liv.compresor.impl.CompresorZIP;

public class GeneradorNominasHelper {

	
	static PrintWriter archivoResultado = null;
	
	static HashMap dataNombre = null;
	
	static int cantidadHebras = Integer.parseInt ( PropertiesUtil.workflowProperties.getString("config.cantidad.hebras.generacion").toString());
	
	public static ArrayList comandosGeneracion = new ArrayList();
	
	public static boolean generar(String reporte, String params){
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();

		HashMap parametrosGeneracion = Funciones.procesarParametros(params);
		String parametrosAux = params;
		pars.put("nombre_config", reporte);
		try {
			
			HashMap configuracion = (HashMap) sqlMap.queryForObject("carga_SAP.obtenerConfiguracionesNominas", pars);
			String nombreArchivo  = configuracion.get("nombre_salida").toString();
			nombreArchivo = UtilesComunes.reemplazarVariables(nombreArchivo);
			
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			
			HashMap parametros = Funciones.procesarParametros(params);
			String codigoEntidad = parametros.get("codigo_entidad").toString();
			
			String sqlCodigoEntidad = "";
			if(codigoEntidad != null && codigoEntidad.toLowerCase().startsWith("h")){
				sqlCodigoEntidad = " CODIGO_HOLDING ";
			}
			else{
				sqlCodigoEntidad = " RUT_EMPRESA ";
			}
			params = "group_by:"+sqlCodigoEntidad+";;";
			
			
			if(configuracion.get("tipo")!= null && configuracion.get("tipo").toString().toLowerCase().equals("normal")){
				
				params = "filtro: and periodo = '"+periodo+"' and "+getFiltroPorCodigoEntidad(codigoEntidad)+" ;;"+ params ;
				
				if(parametrosGeneracion.get("agrupar_por")!=null){
					params = params +";;"+ "agrupar_por:"+ parametrosAux.split("agrupar_por:")[1];
				}
				
				String nuevoNombre = obtenerNombreArchivo(nombreArchivo, "and rut_empresa = '"+codigoEntidad+"' ", codigoEntidad);
				if(nuevoNombre == null) {
					return false;
				}
				agregarComandoGeneracion(configuracion, periodo, codigoEntidad, params, nuevoNombre);
				
				
			}
			else if(configuracion.get("tipo")!= null && configuracion.get("tipo").toString().toLowerCase().equals("filtro")){
				
				params = "filtro: and periodo = '"+periodo+"'  and "+getFiltroPorCodigoEntidad(codigoEntidad)+" "+configuracion.get("data_adicional").toString() +";;"+ params ;
				
				String filtro =  " and periodo = '"+periodo+"' and "+getFiltroPorCodigoEntidad(codigoEntidad)+" " +configuracion.get("data_adicional").toString();
				
				String nuevoNombre = obtenerNombreArchivo(nombreArchivo, filtro , codigoEntidad);

				if(parametrosGeneracion.get("agrupar_por")!=null){
					params = params +";;"+ "agrupar_por:"+ parametrosAux.split("agrupar_por:")[1];
				}
				
				if(nuevoNombre == null) {
					return false;
				}
				agregarComandoGeneracion(configuracion, periodo, codigoEntidad, params, nuevoNombre);
				
				
			}
			else if(configuracion.get("tipo")!= null && configuracion.get("tipo").toString().toLowerCase().equals("java")){
				
				String[] partes = configuracion.get("data_adicional").toString().split(";");
				
				Class[] tiposParametros = new Class[1];
				tiposParametros[0] = HashMap.class;
				
				Object[] parametrosMetodo = new Object[1];
				parametrosMetodo[0] = parametros;
				
				String[] sql = (String[]) UtilReflectionImpl.executeReflection(partes[0], partes[1], tiposParametros, parametrosMetodo);
				
				params = "sql_header:"+sql[0] +";;sql_detalle:"+sql[1] +";;sql_footer:"+sql[2] +";;"+ params ;
				
				String nuevoNombre = obtenerNombreArchivo(nombreArchivo, "and rut_empresa = '"+codigoEntidad+"' ", codigoEntidad);
				if(nuevoNombre == null) {
					return false;
				}

				if(parametrosGeneracion.get("agrupar_por")!=null){
					params = params +";;"+ "agrupar_por:"+ parametrosAux.split("agrupar_por:")[1];
				}
				
				agregarComandoGeneracion(configuracion, periodo, codigoEntidad, params, nuevoNombre);
				
			}

			else if(configuracion.get("tipo")!= null && configuracion.get("tipo").toString().toLowerCase().equals("agrupada")){
				
				String dataAdicional = configuracion.get("data_adicional").toString();
				
				MiHashMap parametrosAgrupacion = new MiHashMap();
				parametrosAgrupacion.put("agrupacion", dataAdicional);
				parametrosAgrupacion.put("filtro", "and "+ getFiltroPorCodigoEntidad(codigoEntidad));
				ArrayList grupos = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerAgrupacion", parametrosAgrupacion);
				String paramsAux = params;
				if(grupos != null && grupos.size() >0){
					for(int i=0; i< grupos.size(); i++){
						HashMap grupo = (HashMap)grupos.get(i);
						//obtenerInformacionParaNombre
						String filtro = " and periodo = '"+periodo+"' and "+dataAdicional+"='"+grupo.get(dataAdicional)+"'";
						paramsAux = "filtro:  and periodo = '"+periodo+"'  and "+getFiltroPorCodigoEntidad(codigoEntidad)+"  "+ filtro + ";;"+ params ;
						String nuevoNombre = obtenerNombreArchivo(nombreArchivo, filtro, codigoEntidad);
						if(nuevoNombre == null) {
							//
						}
						else{
				
							if(parametrosGeneracion.get("agrupar_por")!=null){
								params = params +";;"+ "agrupar_por:"+ parametrosAux.split("agrupar_por")[1];
							}
							
							String ruta = agregarComandoGeneracion(configuracion, periodo, codigoEntidad, paramsAux, nuevoNombre);
							
								
						}
						
					}
				}
				
				
			}
			
			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_GENERACION, reporte +";;"+params);
		} catch (IOException e) {
			e.printStackTrace();
			ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_GENERACION, reporte +";;"+params);
		}
		
		return false;
	}
	
	public synchronized static String obtenerNombreArchivo(String nombreArchivo, String filtro, String codigoEntidad) throws SQLException{
		String nuevoNombre = "";
		
		
		if(nombreArchivo.contains("db.")){
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
			
			MiHashMap parametrosNombre = new MiHashMap();
			//parametrosNombre.put("filtro", filtro +" "+ "and "+ getFiltroPorCodigoEntidad(codigoEntidad));
			parametrosNombre.put("filtro", filtro);
			
			String query = "carga_SAP.obtenerInformacionParaNombre";
			
			dataNombre = (HashMap) sqlMap.queryForObject(query, parametrosNombre);

			if(dataNombre != null){
				String[] partes = nombreArchivo.split("db.");
				for(int j=0; j<partes.length; j++){
					if(partes[j].length()>0){
						UtilLogWorkflow.debug("key->"+partes[j]);
						if(dataNombre.get(partes[j].split(";")[0]) != null){
							String valor = dataNombre.get(partes[j].split(";")[0]).toString();
							if(partes[j].split(";").length>1){
								valor = valor + partes[j].substring(partes[j].indexOf(";")+1) ;
							}

							nuevoNombre = nuevoNombre + valor;
						}
						else{
							nuevoNombre = nuevoNombre + partes[j];
						}
					}
				}
			}
			else{
				return null;
			}
			if(nuevoNombre.length()>0){
				nombreArchivo = nuevoNombre;
			}
		}
		
		if(nombreArchivo.contains("dbpef.")){
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
			
			MiHashMap parametrosNombre = new MiHashMap();
			parametrosNombre.put("filtro"," and emprut = rut_empresa "+ filtro +" "+ " and "+ getFiltroPorCodigoEntidad(codigoEntidad));
			String query = "carga_SAP.obtenerInformacionParaNombrePef";
			
			dataNombre = (HashMap) sqlMap.queryForObject(query, parametrosNombre);

			if(dataNombre != null){
				String[] partes = nombreArchivo.split("dbpef.");
				for(int j=0; j<partes.length; j++){
					if(partes[j].length()>0){
						UtilLogWorkflow.debug("key->"+partes[j]);
						if(dataNombre.get(partes[j].split(";")[0]) != null){
							String valor = dataNombre.get(partes[j].split(";")[0]).toString().trim();
							if(partes[j].split(";").length>1){
								valor = valor + partes[j].substring(partes[j].indexOf(";")+1).trim() ;
								UtilLogWorkflow.debug("["+partes[j].substring(partes[j].indexOf(";")+1).trim()+"]");
							}

							nuevoNombre = nuevoNombre + valor.trim();
						}
						else{
							nuevoNombre = nuevoNombre + partes[j].trim();
						}
					}
				}
			}
			else{
				return null;
			}
			if(nuevoNombre.length()>0){
				nombreArchivo = nuevoNombre;
			}
		}
		
		String nombreArchivoVariable = "";
		if(nombreArchivo.contains("var.")){
			if(PropertiesUtil.propertiesVariables.getString("config.variables.sistema") != null){
				String[] partes = nombreArchivo.split("var.");
				for(int j=0; j<partes.length; j++){
					String valor = null;
					if(partes[j].length()>0){
						String key = partes[j];

						if(key.contains(";")){
							key = key.split(";")[0];
						}
						key = ";"+key+":";
						if(PropertiesUtil.propertiesVariables.getString("config.variables.sistema").contains(key)){
							valor = PropertiesUtil.propertiesVariables.getString("config.variables.sistema").split(key)[1].split(";")[0];
							
							valor = UtilesComunes.getVariable(valor);
						}
						if(partes[j].split(";").length>1){
							if(partes[j].indexOf(";")==0){
								valor =  partes[j].substring(partes[j].indexOf(";")+1) ;
							}
							else{
								valor =  partes[j];
							}
						}
					}
					if(valor != null){
						nombreArchivoVariable = nombreArchivoVariable + valor.replaceAll(";", "");
					}
					else{
						nombreArchivoVariable = nombreArchivoVariable + partes[j].replaceAll(";", "");
					}
				}
			}
			
			
		}
		
		if(nombreArchivoVariable.length()>0){
			return nombreArchivoVariable;
		}
		else if( nuevoNombre.length()>0){
			return nuevoNombre;
		}
		return nombreArchivo;
		
	}
		
	public static String agregarComandoGeneracion(HashMap configuracion, String periodo, String codigoEntidad, String params, String nombreArchivo ) throws IOException{
		UtilLogTiempos.debug("contador: "+ comandosGeneracion.size());contadorNominas++;
		UtilLogTiempos.debug("comando agregado : "+ comandosGeneracion.size());

		ejecutarComandoGeneracion(configuracion, periodo, codigoEntidad, params, nombreArchivo);
		return "";
	}
	
	public static String ejecutarComandoGeneracion(HashMap configuracion, String periodo, String codigoEntidad, String params, String nombreArchivo ) throws IOException{
		codigoEntidad = codigoEntidad.trim();
		HashMap comando = new HashMap();
		comando.put("periodo", periodo);
		comando.put("codigo_entidad", codigoEntidad);
		comando.put("params", params);
		comando.put("nombre_archivo", nombreArchivo);
		if(configuracion.get("formato_salida") != null){
			String[] formatos = configuracion.get("formato_salida").toString().split(";");
			for(int i=0; i< formatos.length; i++){
				String rutaReporteGenerado = null;
				String formato = formatos[i];
				if(!new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad).exists()){
					File f =new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad);
					f.setWritable(true);
					f.setReadable(true);
					f.setExecutable(true);
					f.mkdirs();
				}
				
				GenerarTXT generador = new GenerarTXT();
				
				if(formato != null && formato.toString().equals("txt")){
					

					String pathDestino = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/"+nombreArchivo+".txt";
					params = params+";;path_out:"+pathDestino;
					Date fechaInicio = new Date();
					
					rutaReporteGenerado = generador.generar(configuracion.get("formato_nomina").toString(), params,"txt","", archivoResultado);
					UtilLogTiempos.debug("tiempo de generación nomina txt ["+codigoEntidad+"]: "+ ( new Date().getTime() - fechaInicio.getTime()));
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_REG_CMD_EJEC, codigoEntidad+";"+ nombreArchivo +";"+params );
					
					long largo = 0;
					int estado = 0;
					if(new File(rutaReporteGenerado).exists()){
						largo = new File(rutaReporteGenerado).length();

					}
					else{
						largo = -1;
					}
					EstadisticasHelper.actualizacionEstado(codigoEntidad, configuracion.get("nombre_config").toString(), estado, largo, rutaReporteGenerado, ( new Date().getTime() - fechaInicio.getTime()), generador.dataTMP );
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_STATUS_GENERACION, codigoEntidad+";;txt;;largo:"+largo );
				}
				else if(formato != null && formato.toString().equals("csv")){
					String pathDestino = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/"+nombreArchivo+".csv";
					params = params+";;path_out:"+pathDestino;
					Date fechaInicio = new Date();
					rutaReporteGenerado = generador.generar(configuracion.get("formato_nomina").toString(), params,"csv",";",archivoResultado);
					UtilLogTiempos.debug("tiempo de generación nomina csv ["+codigoEntidad+"]: "+ ( new Date().getTime() - fechaInicio.getTime()));
					
					long largo = 0;
					int estado = 0;
					if(new File(rutaReporteGenerado).exists()){
						largo = new File(rutaReporteGenerado).length();

					}
					else{
						largo = -1;
					}
					
					EstadisticasHelper.actualizacionEstado(codigoEntidad, configuracion.get("nombre_config").toString(), estado, largo, rutaReporteGenerado ,( new Date().getTime() - fechaInicio.getTime()), generador.dataTMP);
						
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_STATUS_GENERACION, codigoEntidad+";;txt;;largo:"+largo );
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_REG_CMD_EJEC, codigoEntidad+";"+ nombreArchivo +";"+params );
					
				}
				else if(formato != null && formato.toString().equals("xls")){
					

					String pathDestino = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+codigoEntidad+"/"+nombreArchivo+".xls";
					params = params+";;path_out:"+pathDestino;
					Date fechaInicio = new Date();
					rutaReporteGenerado = generador.generar(configuracion.get("formato_nomina").toString(), params,"xls",";",archivoResultado);
					UtilLogTiempos.debug("tiempo de generación nomina xls ["+codigoEntidad+"]: "+ ( new Date().getTime() - fechaInicio.getTime()));
					long largo = 0;
					int estado = 0;
					if(new File(rutaReporteGenerado).exists()){
						largo = new File(rutaReporteGenerado).length();

					}
					else{
						largo = -1;
					}
					
					EstadisticasHelper.actualizacionEstado(codigoEntidad, configuracion.get("nombre_config").toString(), estado, largo, rutaReporteGenerado, ( new Date().getTime() - fechaInicio.getTime()), generador.dataTMP );
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_STATUS_GENERACION, codigoEntidad+";;txt;;largo:"+largo );
					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_REG_CMD_EJEC, codigoEntidad+";"+ nombreArchivo +";"+params );
				}
				else if(formato != null && formato.toString().equals("pdf")){
					//No implementado
				}
				else if(formato != null && formato.toString().equals("car")){
					//No implementado

					SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
					MiHashMap parametros = new MiHashMap();
					parametros.put("CODIGO_ENTIDAD", codigoEntidad);
					parametros.put("PERIODO", periodo);
					parametros.put("ESTADO", new Integer(0));
					try {
						sqlMap.insert("carga_SAP.registrarEnvioCarta", parametros);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		return "";
	}
	
	static ArrayList configuracionesEspeciales = new ArrayList();
	public static ArrayList obtenerConfiguracionesPorEntidad(HashMap pars){
		ArrayList salida = new ArrayList();
		ArrayList especiales = new ArrayList();
		
		
		try{
			if(configuracionesEspeciales != null && configuracionesEspeciales.size() == 0){
				SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
				configuracionesEspeciales = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerConfiguracionesNominasEspeciales", pars);
				
			}	
			boolean esHolding = false;
			if(pars.get("codigo").toString().toLowerCase().startsWith("h")){
				esHolding = true;
			}
			for(int i=0; i< configuracionesEspeciales.size(); i++){
				MiHashMap registro = (MiHashMap)configuracionesEspeciales.get(i);
				if(registro != null){
					if(registro.get("rut_empresa") == null && registro.get("codigo_holding") == null){
						salida.add(registro);
					}
					if(esHolding && registro.get("codigo_holding")!=null && registro.get("codigo_holding").toString().trim().toLowerCase().equals(pars.get("codigo").toString().trim().toLowerCase()) ){
						especiales.add(registro);
					}
					
					if(!esHolding && registro.get("rut_empresa")!=null && registro.get("rut_empresa").toString().trim().toLowerCase().equals(pars.get("codigo").toString().trim().toLowerCase()) ){
						especiales.add(registro);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(especiales.size() == 0)
			return salida;
		else
			return especiales;
	}
	
	public static boolean generarTodas( String params){
		
	
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			HashMap parametros = Funciones.procesarParametros(params);
			
			String exclusiones = PropertiesUtil.workflowProperties.getString("config.excluir.entidad.proceso.masivo");
			
			if(parametros.get("codigo_entidad")!=null && parametros.get("codigo_entidad").toString().toLowerCase().startsWith("h") && exclusiones.indexOf(parametros.get("codigo_entidad").toString()+";") >= 0){
				UtilLogCargas.debug("Se excluye la entidad '"+parametros.get("codigo_entidad")+"'");
				UtilLogProcesos.debug("Se excluye la entidad '"+parametros.get("codigo_entidad")+"'");
				return true;
			}
			
			pars.put("codigo", parametros.get("codigo_entidad"));
			ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_REG_ENTIDAD, parametros.get("codigo_entidad").toString() );

			//final ArrayList configuraciones = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerConfiguracionesNominas", pars);
			final ArrayList configuraciones = obtenerConfiguracionesPorEntidad(pars);
			
			if(configuraciones.size() == 2){
				for(int i=configuraciones.size() - 1;i>=0; i--){
					if( ((MiHashMap)configuraciones.get(i)).get("nombre_config").toString().toLowerCase().contains("trabajadores") && esPensionado(parametros, parametros.get("codigo_entidad").toString())){
						configuraciones.remove(i);
					} 
					else if( ((MiHashMap)configuraciones.get(i)).get("nombre_config").toString().toLowerCase().contains("pensionados") && !esPensionado(parametros, parametros.get("codigo_entidad").toString())){
						configuraciones.remove(i);
					} 
							
				}
			}
			if(configuraciones.size()>1){
				for(int i=configuraciones.size() - 1;i>0; i--){
					if( ((MiHashMap)configuraciones.get(i)).get("rut_empresa") == null &&
							((MiHashMap)configuraciones.get(i)).get("codigo_holding") == null ){
						configuraciones.remove(i);
					} 
							
				}
			}
			

			for(int i=0; i< configuraciones.size(); i++){
				String paramsAux = params;
				final int aux = i;
				if(((HashMap )configuraciones.get(aux)).get("PAR_GENERACION")!=null && ((HashMap )configuraciones.get(aux)).get("PAR_GENERACION").toString().trim().length()>0){
					paramsAux = paramsAux +";;"+((HashMap )configuraciones.get(aux)).get("PAR_GENERACION");
				}
				String comandoCompleto = parametros.get("codigo_entidad")+";"+ ((HashMap )configuraciones.get(aux)).get("nombre_config").toString() +";"+paramsAux;
				EstadisticasHelper.agregarComando(comandoCompleto);
				ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_REG_CMD, parametros.get("codigo_entidad")+";"+ ((HashMap )configuraciones.get(aux)).get("nombre_config").toString() +";"+paramsAux );
				comandosGeneracion.add(new String[]{((HashMap )configuraciones.get(aux)).get("nombre_config").toString(), paramsAux});
				UtilLogThread.debug("comando agregado: "+ comandosGeneracion.size());
				//generar( ((HashMap )configuraciones.get(aux)).get("nombre_config").toString(), params);
			}
			
			
			return true;
		
		}  catch (Exception e) {
			e.printStackTrace();
			ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_CMD_GENERACION, params+";;"+ e.getMessage());
		}
		
		return false;
	}
	final static UtilInteger contadorGeneracion = new UtilInteger();
	final static UtilInteger contadorHebrasActivasPublicacion = new UtilInteger();
	
	public static boolean contienePatron(String nombre, String[] patrones){
		if(patrones != null && patrones.length > 0){
			for(int i=0; i<patrones.length; i++){
					if(nombre.toLowerCase().contains(patrones[i].toLowerCase())){
						return true;
					}
			}
		}
		
		return false;
	}
	
	public static boolean enviarTodas(String params){
		String[] patronesExclusiones = ResourceBundle.getBundle("etc/workflow_configuraciones").getString("config.excluir.disponibilizacion.archivos.comienzan.con").split(";");
		System.out.println("enviando ************************************"+ params);
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		MiHashMap pars = new MiHashMap();
		try {
			HashMap parametros = Funciones.procesarParametros(params);
			pars.put("codigo", parametros.get("codigo_entidad"));
			MiHashMap envio = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerConfiguracionesEnvio", pars);
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			
			String rutaFiles = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+parametros.get("codigo_entidad");
			String pathExtranet = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.path.nominas");
			try{
				if(envio  == null){
					if(new File(rutaFiles).exists()){
						File[] nominas = new File(rutaFiles).listFiles();
						for(int i=0; i< nominas.length; i++){
							if(nominas[i].getName().contains(".txt") && ! contienePatron(nominas[i].getName(), patronesExclusiones)  ){
								AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.pub.password"));
								ArchivosAS400.copiarArchivoConEncoding( conexion  , nominas[i].getAbsolutePath(), pathExtranet+"/"+nominas[i].getName());
								conexion.disconnectAllServices();
								
							}
							
						}
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
				ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_DISPONIBILIZACION, params+";;"+"upload_file;;"+e.getMessage());
			}
			
			if(envio !=null && envio.get("tipo")!= null  && envio.get("tipo").toString().toLowerCase().equals("mail")){
			
				if( ConfiguradorHelper.nominasEnviadas.get(parametros.get("codigo_entidad").toString()) != null) {
					System.out.println("**************************** YA SE ENVIO ["+parametros.get("codigo_entidad")+"]  ***********************");
					return true;
				}
				ConfiguradorHelper.nominasEnviadas.put(parametros.get("codigo_entidad").toString(), Boolean.TRUE);
				
				String rutaInput = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+parametros.get("codigo_entidad").toString().trim()+"/";
				final String rutaOutput = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path") +periodo+"_"+ new Double((Math.random() * 1000000)).intValue() +".zip";
				CompresorZIP.comprimirDirectorio(rutaInput, rutaOutput);
				new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(120000);
							new File(rutaOutput).delete();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();;
				
				HashMap att = new HashMap();
				att.put("ruta_file", rutaOutput);
				ArrayList atts = new ArrayList();
				atts.add(att);

				
				MiHashMap parsData = new MiHashMap();
				try {
					new Integer(parametros.get("codigo_entidad").toString());
					parsData.put("rut_empresa", parametros.get("codigo_entidad"));
				}
				catch(Exception e) {
					parsData.put("codigo_holding", parametros.get("codigo_entidad").toString().trim());
				}
				parsData.put("PERIODO", periodo);
				try {
					MiHashMap data = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerDataEnvioNomina",parsData);
					if(data != null){
						
						ArrayList nominasGeneradas = (ArrayList)sqlMap.queryForList("carga_SAP.obtenerDatosNomina", parsData);
						
						String TIPO_NOMINA_TRABAJADORES = "1";
						String TIPO_NOMINA_PENSIONADOS = "4";
						
						ArrayList trabajadores = getNominasPorTipoNomina(nominasGeneradas, TIPO_NOMINA_TRABAJADORES);
						ArrayList pensionados = getNominasPorTipoNomina(nominasGeneradas, TIPO_NOMINA_PENSIONADOS);
						
						if(trabajadores != null && trabajadores.size() > 0){
							parsData.put("tipo_nomina", TIPO_NOMINA_TRABAJADORES);
							data = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerDataEnvioNomina",parsData);
							data.put("TIPO_NOMINA", TIPO_NOMINA_TRABAJADORES);
							data.put("nominas",trabajadores);
							data.put("fecha_vencimiento", UtilesWorkflow.formatearFecha(data.get("fecha_vencimiento").toString(), "yyyyMMdd", "dd/MM/yyyy")  );
							
							String mes = data.get("fecha_vencimiento").toString().substring(3, 5);
							String mesDescriptivo = UtilesComunes.obtenerDescripcionMes(Integer.parseInt(mes));
							
							data.put("mes_vencimiento_nomina", mesDescriptivo );
							MailUtil mail = new MailUtil();
							mail.init( data, envio, atts);
							mail.send();
						}
						
						if(pensionados != null && pensionados.size() > 0){
							parsData.put("tipo_nomina", TIPO_NOMINA_PENSIONADOS);
							data = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerDataEnvioNomina",parsData);
							data.put("TIPO_NOMINA", TIPO_NOMINA_PENSIONADOS);
							data.put("nominas",pensionados);
							data.put("fecha_vencimiento", UtilesWorkflow.formatearFecha(data.get("fecha_vencimiento").toString(), "yyyyMMdd", "dd/MM/yyyy")  );
							
							String mes = data.get("fecha_vencimiento").toString().substring(3, 5);
							String mesDescriptivo = UtilesComunes.obtenerDescripcionMes(Integer.parseInt(mes));
							
							data.put("mes_vencimiento_nomina", mesDescriptivo );
							MailUtil mail = new MailUtil();
							mail.init( data, envio, atts);
							mail.send();
						}
						
						
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

					ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_DISPONIBILIZACION, params+";;"+"send_file;;"+e.getMessage());
				}
				
				
				
			}
			else if(envio !=null && envio.get("tipo")!= null  && envio.get("tipo").toString().toLowerCase().equals("ftp")){
				String rutaInput = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/"+parametros.get("codigo_entidad")+"/";
				final String rutaOutput = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path") +periodo+"_"+ new Double((Math.random() * 1000000)).intValue() +".zip";
				CompresorZIP.comprimirDirectorio(rutaInput, rutaOutput);
				ArchivosUtiles.guardarArchivoPorRutaFTP("FTP;"+envio.get("data_adicional").toString(), "FILE;"+periodo+"_"+parametros.get("codigo_entidad")+".zip", rutaOutput);
				new Thread(new Runnable() {
					
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(30000);
							new File(rutaOutput).delete();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			ResultadosUtil.addRegistro(ConstantesUtiles.ID_KEY_ERR_DISPONIBILIZACION, params+";;"+"upload_ftp_file;;"+e.getMessage());
		}
		return false;
	}
	private static ArrayList getNominasPorTipoNomina(ArrayList nominas, String tipoNomina){
		ArrayList retorno = new ArrayList();
		
		if(nominas != null && nominas.size()>0){
			for(int i=0; i< nominas.size(); i++){
				MiHashMap nomina = (MiHashMap) nominas.get(i);
				if(nomina != null && nomina.get("TIPO_NOMINA")!= null && tipoNomina.equals( nomina.get("TIPO_NOMINA").toString() ) ){
					retorno.add(nomina);
				}
			}
		}
		
		return retorno;
	}
	
	
	public static boolean ejecutarProcesoGeneracion(){
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			MiHashMap parametros = new MiHashMap();
			
			parametros.put("PERIODO", periodo);
			
			try{
				UtilLogGeneracion.debug(" <limpiandoTablaProcesos>");
				sqlMap.delete("carga_SAP.eliminarTablaProcesos", parametros);
				UtilLogGeneracion.debug(" </limpiandoTablaProcesos>");
			}catch(Exception e){
				e.printStackTrace();
			}
			UtilLogGeneracion.debug(" <CreandoTablaProcesos>");
			sqlMap.insert("carga_SAP.crearTablaProcesos", parametros);
			UtilLogGeneracion.debug(" </CreandoTablaProcesos>");
			UtilLogGeneracion.debug(" </CreandoTablaProcesos>");
			UtilLogGeneracion.debug(" <limpiandoCarpetaPublicacion>");
			new WorkFlowHelper().limpiarCarpetaPublicacion(null, null);
			UtilLogGeneracion.debug(" </limpiandoCarpetaPublicacion>");
			ejecutarProcesoPorEntidad();
			
			return true;
		
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void ejecutarProcesoPorEntidad() throws SQLException{
		UtilLogGeneracion.debug(" <ejecutandoProcesoPorEntidad>");
		inicializar();
		ArrayList procesos = new ArrayList();
		HashMap procComandos = new HashMap();
		procComandos.put("codigo", "Comandos");
		procComandos.put("estado", "No Iniciado");
		procesos.add(procComandos);
		HashMap procGeneracion = new HashMap();
		procGeneracion.put("codigo", "Generacion");
		procGeneracion.put("estado", "No Iniciado");
		procesos.add(procGeneracion);
		BlockedUtil.agregarProcesoAlBloqueo(procesos);
		UtilLogGeneracion.debug(" <getEntidadesInicial>");
		String[] entidades = GeneradorNominasHelper.obtenerCodigoEntidadAProcesar(new Boolean(false));
		if(entidades!= null) {
			UtilLogGeneracion.debug(" entidades.length: "+ entidades.length);
		}
		else {
			UtilLogGeneracion.debug(" entidades == null ");
		}
		UtilLogGeneracion.debug(" </getEntidadesInicial>");
		int intento=0;
		while(entidades != null){
			
			for(int i=0; i< entidades.length; i++){
				UtilLogCargas.debug("contador entidad ["+contadorProceso+"]");contadorProceso++;
				final String entidad = entidades[i];
				
				if(empresasAgregadas.get(entidad) ==null){
					empresasAgregadas.put(entidad, Boolean.TRUE);
					generarTodas( "codigo_entidad:"+entidad);
					
					GeneradorNominasHelper.guardarAvanceGeneracion("Comandos","Agregando comando Generaci&oacute;n", false);
					
					UtilLogCargas.debug("nominas generadas para ["+entidades[i]+"]");
					
				}
				if(WorkFlowHelper.isHebraMuerta()){
					UtilLogGeneracion.debug("deteniendo hebra carga...["+Thread.currentThread().getName()+"]");
					UtilLogCargas.debug("deteniendo hebra carga...["+Thread.currentThread().getName()+"]");
					return ;
				}
				
			}
			//Nueva entidad
			entidades = GeneradorNominasHelper.obtenerCodigoEntidadAProcesar(new Boolean(false));
			if(entidades!= null) {
				UtilLogGeneracion.debug("<getEntidades>["+intento+"] entidades.length: "+ entidades.length +"</getEntidades>");
			}
			else {
				UtilLogGeneracion.debug("<getEntidades>["+intento+"] entidades=null </getEntidades>");
			}
			intento++;
		}
	
		new PoolHebras(GeneradorNominasHelper.cantidadHebras, "cl.jfactory.planillas.service.helper.GeneradorNominasHelper", "ejecutarHiloGeneracion", new Class[0], new Object[0], new ITerminadorHebra() {
			
			public void notificarCierre(int tipo) {
				

				UtilLogGeneracion.debug("Finalizando el proceso de Generación...");
				UtilLogProcesos.debug("Finalizando el proceso de Generación...");
				UtilLogGeneracion.debug("notificar cierre Generacio");
				UtilLogThread.debug("notificar cierre Generacion");
				UtilLogGeneracion.debug("<validandoGeneracion>");
				boolean estadoProcesoGeneracion = validarProcesoGeneracion();
				UtilLogThread.debug("status ="+ estadoProcesoGeneracion);
				UtilLogGeneracion.debug("</validandoGeneracion>");
				if(!estadoProcesoGeneracion ) {
					UtilLogProcesos.debug("Terminando Con error");
					EstadisticasHelper.terminar();
					BlockedUtil.actualizarEstadoProcesoBloqueado("Generacion", "Terminado con Errores !");
					//BlockedUtil.terminarBloqueo("Proceso de Generaci&oacute;n Terminado Con errores.",3);
					//ConfiguradorHelper.cerrarProcesoBloqueado("3", "Terminado con Errores");					
					return;
				}
				else {
					EstadisticasHelper.terminar();
					UtilLogProcesos.debug("Validación Superada...");	
				}
				ConfiguradorHelper.cerrarProcesoBloqueado("3", "Fin prceso generaci&oacute;n");
				AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ANTES_GENERACION_NOMINA, EstadosWorkflow.DESPUES_GENERACION_NOMINA);
				UtilLogProcesos.debug(" Terminando Generación de nóminas ");
				BlockedUtil.terminarBloqueo("Proceso de Generaci&oacute;n Terminado.",3);
				
				long fecha = new Date().getTime();
				ResultadosUtil.registrarResultados(ConstantesUtiles.ID_KEY_REG_CMD_EJEC, PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+Utiles.getPeriodoActual()+"_"+ConstantesUtiles.ID_KEY_REG_CMD_EJEC+"_"+fecha);
				ResultadosUtil.registrarResultados(ConstantesUtiles.ID_KEY_ERR_CMD_GENERACION, PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+Utiles.getPeriodoActual()+"_"+ConstantesUtiles.ID_KEY_ERR_CMD_GENERACION+"_"+fecha);
				
				EstadisticasHelper.terminar();
				//new ValidacionResultadosGeneracionUtil().ejecutarProcesoValidacion();
				if(archivoResultado != null){
					ManejoArchivoTXT.closeFileToWrite(archivoResultado);
				}
				new ValidacionProcesoGeneracion().init();

				UtilLogThread.debug("<callURLResultadosGeneracion>");
				ConfiguradorHelper.callURLResultadosGeneracion("generacion");
				UtilLogThread.debug("</callURLResultadosGeneracion>");
			}
		});
		
	}
	
	public static boolean validarProcesoGeneracion() {
		
		UtilLogProcesos.debug("Validando generacion ...");
		boolean resultado = false;
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			MiHashMap parametros = new MiHashMap();
			SimpleDateFormat formatoYearMonth = new SimpleDateFormat("yyyyMM");

	        Calendar gc = GregorianCalendar.getInstance();
	        gc.add(Calendar.MONTH, -1);

			String periodoAnterior = formatoYearMonth.format(gc.getTime());
			parametros.put("PERIODO", periodo);
			parametros.put("PERIODO_ANTERIOR", periodoAnterior);
			try{
				UtilLogGeneracion.debug("consultando estado validacion ->["+parametros+"]");
				MiHashMap result = (MiHashMap)sqlMap.queryForObject("carga_SAP.validarEstadoFinalGeneracion", parametros);
				UtilLogGeneracion.debug("resultado validacion ->"+result);
				UtilLogProcesos.debug("Validando generacion resultado-> query ["+parametros+"] :"+ result);
				if(result != null) {
					resultado = true;
				}
			}catch(Exception e){
				e.printStackTrace();
				UtilLogGeneracion.debug("Validando generacion resultado-> err"+ e.getMessage());
			}
			
			UtilLogGeneracion.debug("Validando generacion resultado->"+ resultado);
			UtilLogProcesos.debug("Validando generacion resultado->"+ resultado);
			return resultado;
	}
	
	public static void ejecutarHiloGeneracion() throws InterruptedException{
		
		Object comando = getComando();
		int intento= 0;
		
		while(comando != null){
			
			long inicio = new Date().getTime();
			try{
				UtilLogGeneracion.debug("<generarTodas>["+intento+"]["+getEmpresasProcesadas()+"] reporte:"+((String[])comando)[0]+", params: "+ ((String[])comando)[1]+"</generarTodas>");
				intento++;
				generar( ((String[])comando)[0], ((String[])comando)[1] );
			} catch(Exception e){
				e.printStackTrace();
				UtilLogErrores.error(e);
				String descripcion = ((String[])comando)[0]+";;"+ ((String[])comando)[1] +";;" ;
				if(e.getStackTrace() != null && e.getStackTrace().length>0){
					descripcion = descripcion + ";;"+ e.toString() + " "+ e.getStackTrace()[0];
				}
				ResultadosUtil.addRegistro( ConstantesUtiles.ID_KEY_ERR_GENERACION , descripcion);
			}
			GeneradorNominasHelper.guardarAvanceGeneracion("Generacion","Generando n&oacute;minas", true);
			UtilLogThread.debug("generado en : "+  (int)(new Date().getTime() - inicio)  +" s  -> "+ ((String[])comando)[1]);
			comando = getComando();
		}
		
		ResultadosUtil.registrarResultados(ConstantesUtiles.ID_KEY_ERR_GENERACION, PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+Utiles.getPeriodoActual()+"_"+ConstantesUtiles.ID_KEY_ERR_GENERACION);
		ResultadosUtil.registrarResultados(ConstantesUtiles.ID_KEY_STATUS_GENERACION, PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+Utiles.getPeriodoActual()+"_"+ConstantesUtiles.ID_KEY_STATUS_GENERACION);
		
	}
	private static Object getComando(){
		if(comandosGeneracion.size()>0){
			Object comando = comandosGeneracion.get(0);
			comandosGeneracion.remove(0);
			return comando;
		}
		return null;
	}
	
	public static boolean ejecutarProcesoEnvio(){
		inicializar();
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
			MiHashMap parametros = new MiHashMap();
			
			parametros.put("PERIODO", periodo);
			try{
				sqlMap.delete("carga_SAP.inicializarEstadosEnvio", parametros);
			}catch(Exception e){
				e.printStackTrace();
			}
			for(int i=0; i< cantidadHebras; i++){
				new Thread( new Runnable() {
					
					public void run() {
						try {
							contadorProceso = 0;
							contadorHebrasActivasPublicacion.increment();
							ejecutarProcesoEnvioPorEntidad();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			return true;
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	static int contadorProceso = 0;
	
	
	public static void ejecutarProcesoEnvioPorEntidad() throws SQLException{
		String[] entidades = obtenerCodigoEntidadAProcesar(new Boolean(true));
		while(entidades != null){
			
			for(int i=0; i< entidades.length; i++){
				UtilLogCargas.debug("contador entidad ["+contadorProceso+"]");contadorProceso++;
				enviarTodas( "codigo_entidad:"+entidades[i]);
				UtilLogCargas.debug("nominas enviadas para ["+entidades[i]+"]");
				
				if(WorkFlowHelper.isHebraMuerta()){
					UtilLogCargas.debug("deteniendo hebra carga...["+Thread.currentThread().getName()+"]");
					contadorHebrasActivasPublicacion.decrement();
					return ;
				}
			}
			//Nueva entidad
			entidades = obtenerCodigoEntidadAProcesar(new Boolean(true));
		}
		
		contadorHebrasActivasPublicacion.decrement();
		
		if(contadorHebrasActivasPublicacion.intValue()==0){
			UtilLogCargas.debug("fin proceso disponibilizacion ");
			
			String comandoPublicacion = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.comando.publicacion.nominas");
			if(comandoPublicacion != null && comandoPublicacion.length()>0){
				comandoPublicacion = UtilesComunes.reemplazarVariables(comandoPublicacion);
				AS400 conexion = new AS400(PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.user"), PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.password"));EjecutarComandoAS400.ejecutar(conexion,comandoPublicacion);
				conexion.disconnectAllServices();
			}
			
			ConfiguradorHelper.cerrarProcesoBloqueado("4", "Proceso de env&iacuteo finalizado.");
			UtilLogProcesos.debug(" Terminando Generación de nóminas ");
			AlertasHelper.procesarAlertaCambioEstado(EstadosWorkflow.ANTES_ENVIO_NOMINAS, EstadosWorkflow.DESPUES_ENVIO_NOMINAS);
			
			ConfiguradorHelper.callURLResultadosGeneracion("disponibilizacion");
			
			if(ConfiguradorHelper.sessionDisponibilizacion != null){
				UtilLogProcesos.debug("Cerrando proceso..");
				String key = UtilesComunes.reemplazarVariables("sys.YearMonth");
				final String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+key+"/";
				HashMap resultados = new HashMap();
				resultados.put("status", Boolean.TRUE);
				((HashMap)ConfiguradorHelper.sessionDisponibilizacion).put("resultados", resultados);
				UtilLogProcesos.debug("Creando archivo proceso");
				UtilesWorkflow.crearArchivoConDataJson(ConfiguradorHelper.sessionDisponibilizacion, ConfiguradorHelper.parametrosDisponibilizacion, pathCompleto+"/4.txt");
				ConfiguradorHelper.sessionDisponibilizacion = null;
				UtilLogProcesos.debug("Archivo disponibilización cerrado");
			}
	
			ResultadosUtil.registrarResultados(ConstantesUtiles.ID_KEY_ERR_DISPONIBILIZACION, PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos")+Utiles.getPeriodoActual()+"_"+ConstantesUtiles.ID_KEY_ERR_DISPONIBILIZACION);
		}
	}
	static ArrayList empresas = new ArrayList();
	static HashMap empresasAgregadas = new HashMap();
	static boolean quedanHolding = true;
	static SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
	static int contadorNominas = 0;
	public static void inicializar(){
		empresas = new ArrayList();
		quedanHolding = true;
		comandosGeneracion = new ArrayList();
		contadorNominas = 0;
		configuracionesEspeciales = new ArrayList();
		empresasAgregadas.clear();
		String pathResultados =  PropertiesUtil.workflowProperties.getString("config.path.resultados.procesos");
		UtilLogGeneracion.debug(" <limpiandoGenerados>");
		File directorio = new File(pathResultados);
		if(directorio.exists()){
			File[] archivos = directorio.listFiles();
			for(int i=0; i< archivos.length; i++){
				archivos[i].delete();
			}
		}
		UtilLogGeneracion.debug(" </limpiandoGenerados>");
		
	}
	public static synchronized String[] obtenerCodigoEntidadAProcesar(Boolean envio) throws SQLException{

		UtilLogCargas.debug("obteniendo entidad...["+Thread.currentThread().getName()+"]");
		if(sqlMap == null){
			sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		}
	
		MiHashMap parametros = new MiHashMap();
		parametros.put("PERIODO", UtilesComunes.reemplazarVariables("sys.YearMonth"));
		parametros.put("envio", envio);
		MiHashMap holding = null;
		
		if(quedanHolding){
			UtilLogCargas.debug("quedan holding...["+Thread.currentThread().getName()+"]");
			holding = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerHoldingPendientes", parametros);
		}

		if(holding != null){
			UtilLogCargas.debug("marco quedan holding");
			quedanHolding =true;
		}
		else{
			UtilLogCargas.debug("no quedan holding");
			quedanHolding = false;
		}
		
		if(holding != null && holding.get("codigo_holding")!= null && holding.get("codigo_holding").toString().length()>0){
			ArrayList configuracionEspecial = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerFormatosPorHolding", holding);
			holding.put("PERIODO", UtilesComunes.reemplazarVariables("sys.YearMonth"));
			//Si existe una configuracion para el holding
			if(configuracionEspecial != null && configuracionEspecial.size()>=1){
				holding.put("envio", envio);
				int resultado = sqlMap.update("carga_SAP.marcarHoldingComoTomado", holding);
				UtilLogCargas.debug("retornando entidad...["+Thread.currentThread().getName()+"]");
				return new String[]{holding.get("CODIGO_HOLDING").toString()};
			}
			//obtengo las empresas
			else{
				ArrayList empresas = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerEmpresasPorHolding", holding);
				String[] empresasArray = new String[empresas.size()];
				for(int i=0; i< empresas.size(); i++){
					MiHashMap empresa = (MiHashMap)empresas.get(i);
					empresasArray[i]=empresa.get("RUT_EMPRESA").toString()+";;tipo_nomina:"+empresa.get("TIPO_NOMINA").toString();
				}
				holding.put("envio", envio);
				int resultado = sqlMap.update("carga_SAP.marcarHoldingComoTomado", holding);
				UtilLogCargas.debug("retornando entidad...["+Thread.currentThread().getName()+"]");
				return empresasArray;
			}
		}
		else{
			if(empresas != null && empresas.size() == 0){
				UtilLogCargas.debug("cargando nuevas empresas");
				empresas = (ArrayList)sqlMap.queryForList("carga_SAP.obtenerEmpresasPendientes", parametros);
			}
			UtilLogCargas.debug("empresas en la lista ["+empresas.size()+"]");
			
			if(empresas.size()>0){

				UtilLogCargas.debug("obteniendo empresa estaticas empresas");
				final MiHashMap empresa = (MiHashMap) empresas.get(0);
				if(empresa != null){
					empresa.put("PERIODO", UtilesComunes.reemplazarVariables("sys.YearMonth"));
					empresa.put("envio", envio);
					new Thread(new Runnable() {
						
						public void run() {
							// TODO Auto-generated method stub
							try {
								int resultado = sqlMap.update("carga_SAP.marcarEmpresaComoTomada", empresa);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).run();
					empresas.remove(0);
					UtilLogCargas.debug("retornando entidad...["+Thread.currentThread().getName()+"]");
					return new String[]{empresa.get("RUT_EMPRESA").toString()+";;tipo_nomina:"+empresa.get("TIPO_NOMINA").toString()};
				}
			}
			
		}
		return null;
	}
	
	public static String getFiltroPorCodigoEntidad(String codigoEntidad){
		String filtro = "";
		if(codigoEntidad != null && codigoEntidad.startsWith("H")){
			filtro = " CODIGO_HOLDING = '"+codigoEntidad+"' ";
		}
		else{
			filtro = " RUT_EMPRESA = '"+codigoEntidad+"' ";
		}
		return filtro;
	}
	
	public static boolean esPensionado(HashMap parametros, String codigoEmpresa){
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		try {
			
			
			if(parametros != null){
				if(parametros.get("tipo_nomina") != null){
					if(parametros.get("tipo_nomina").toString().equals("4")){
						return true;
					}
					else{
						return false;
					}
				}
			}
			
			if(codigoEmpresa != null && codigoEmpresa.toLowerCase().startsWith("h")){
				return false;
			}
			MiHashMap pars = new MiHashMap();
			pars.put("codigo_entidad", codigoEmpresa);
			Object data = sqlMap.queryForObject("carga_SAP.validarSiEsPensionado", pars);
			if(data != null){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

	public static int getEmpresasProcesadas() {
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		File folder = new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/");
		if(folder != null && folder.isDirectory() && folder.list() != null){
			return  folder.list().length;
		}
		return 0;
	}
	public static void limpiarArchivosGeneradosPorEmpresa() {
		UtilLogGeneracion.debug("<limpiandoCarpetasGeneracion>");
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		File folder = new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/");
		if(folder != null && folder.isDirectory() && folder.list() != null){
			if(folder.exists()){
				File[] archivos = folder.listFiles();
				for(int i=0; i< archivos.length; i++){
					
					if(archivos[i].isDirectory() && archivos[i].listFiles().length>0) {
						File[] nominas = archivos[i].listFiles();
						for(int j=0; j<nominas.length; j++) {
							try {
								nominas[0].delete();
							}catch (Exception e) {
								UtilLogGeneracion.debug(" Error al eliminar :"+ e.getMessage());
							}
						}
					}
					archivos[i].delete();
					if(i % 200 == 0) {
						UtilLogGeneracion.debug("directorios eliminados ["+i+"]");
					}
					
				}
			}
		}
		UtilLogGeneracion.debug("</limpiandoCarpetasGeneracion>");
		
	}
	
	public static void guardarAvanceGeneracion(String idProceso, String proceso, boolean generar){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		File folder = new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/");
		if(generar){
			if(folder != null && folder.isDirectory() && folder.list() != null){
				int cantidadEntidades = folder.list().length;
				UtilLogCargas.debug(proceso +"-> "+ cantidadEntidades);
				UtilLogComun.debug(proceso +"-> "+ cantidadEntidades);
				UtilLogTiempos.debug(proceso +"-> "+ cantidadEntidades);
			
				BlockedUtil.actualizarEstadoProcesoBloqueado(idProceso, cantidadEntidades+" Empresas procesadas. ");
				
			}
		}
		else{
			int cantidadEntidades = comandosGeneracion.size();
			UtilLogCargas.debug(proceso +"-> "+ cantidadEntidades);
			UtilLogComun.debug(proceso +"-> "+ cantidadEntidades);
			UtilLogTiempos.debug(proceso +"-> "+ cantidadEntidades);
		

			BlockedUtil.actualizarEstadoProcesoBloqueado(idProceso, cantidadEntidades+" Comandos Agregados. ");
		}
		
	}

	public void notificarCierre(int tipo) {
		// TODO Auto-generated method stub
		UtilLogThread.debug("terminando hebras..");
		UtilLogWorkflow.debug("************* terminando hebras ************");
	}

}
