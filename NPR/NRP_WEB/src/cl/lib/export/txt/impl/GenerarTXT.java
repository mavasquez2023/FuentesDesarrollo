package cl.lib.export.txt.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cl.lib.export.xls.impl.ConvertidorCSVaEXCEL;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilLogComun;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.comun.utiles.logs.UtilLogGeneracion;
import cl.liv.comun.utiles.logs.UtilLogQuerys;
import cl.liv.export.comun.dao.ExportDAO;
import cl.liv.export.comun.util.Funciones;
import cl.liv.export.comun.util.IExport;
import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.PropertiesComunUtil;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.comun.util.file.ManejoArchivos;
import cl.liv.export.txt.util.Utiles;
import cl.liv.export.txt.util.UtilesAgrupamiento;
import cl.liv.export.txt.util.xml.LectorTXTConfig;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;

public class GenerarTXT implements IExport{
	
	public String formatoSalida = null;
	public String formatoSalidaOriginal = null;
	public String separador = null;
	private int cantidadRegistrosArchivo = 0;
	public int cantidadIntentosMaximos = 5;
	public int intento = 0;
	public HashMap<String, Object> dataTMP = new HashMap<String, Object>();
	
	public static void main(String[] args) {
		UtilLogComun.debug("Generando con version 20210413 ");
		GenerarTXT instancia = new GenerarTXT();
		
		//instancia.generar("envio_nrp_sap", "", "txt",";", null);
		//System.out.println("salida -->"+ instancia.dataTMP);

		//instancia.generar("NOM_76265736_Provida", "", "txt",";", null);
		//instancia.generar("NOM_HO0016_700", "filtro: and periodo = '202001'  and  CODIGO_HOLDING = 'HO0016'  and OFICINA_CREDITO =700 and rutemp in (76243813, 77085380, 96867130, 76196870, 76258350, 76098502);;group_by: CODIGO_HOLDING ;;;;path_out:/home/desarrollo/git/clillo/resources-nrp/nrp/output/202001/HO0016/NEBCISIN.xls", "xls",";", null);
		instancia.generar("NOM_61533000_IPS", "codigo_entidad:61533000;;agrupar_por:RUTBEN,NUMINS;;SUM:MONDE;;encoding:ISO-8859-1;;;;path_out:/home/desarrollo/git/clillo/resources-nrp/nrp/cfg/export_all/salida/NOM_61533000_IPS_20200331082555 ", "txt","", null);
		
		System.out.println("salida -->"+ instancia.dataTMP);
	}
	
	
	
	public String generar(String txt, String pars, String _formatoSalida, String _separador, PrintWriter archivoResultado) {
		UtilLogGeneracion.debug("txt:"+txt+", pars:"+ pars+", formatoSalida:"+_formatoSalida+",separador:"+separador+", archivo:"+ archivoResultado);
		cantidadRegistrosArchivo = 0;
		separador = _separador;
		formatoSalida = _formatoSalida;
		formatoSalidaOriginal = formatoSalida;
		if(formatoSalida.toLowerCase().equals("xls")){
			formatoSalida = "csv";
		}
		
		Mensajes.info("Nombre Reporte: "+ txt);
		Mensajes.info("Parametros: "+ pars);
		

		
		HashMap<String, Object> conf = null;
		HashMap<String, String> parametros = Funciones.procesarParametros(pars);

		if (SessionUtil.reportes.get(txt) == null) {
			LectorTXTConfig.getDataXML(txt);
		} else {
		}
		conf = SessionUtil.txts.get(txt);

		if (conf == null) {
			return "";
		}
		
			
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddhhmmss");
		
		
		String rutaTXTGenerado = "";
		
		if(parametros.get("path_out")!= null){
			rutaTXTGenerado = parametros.get("path_out");
				
		}
		else{
			rutaTXTGenerado = PropertiesComunUtil
				.getProperty("export.path.output") + txt +"_"+formatoFecha.format(new Date());
		}
		
		
		if(formatoSalidaOriginal.toLowerCase().equals("xls")){
			rutaTXTGenerado = rutaTXTGenerado + "_tmp";
		}
		
		
		ManejoArchivos fileManager = new ManejoArchivos();
		
		String encoding = null;
		
		if(parametros.get("encoding")!= null){
			encoding = parametros.get("encoding");
		}
		
		BufferedWriter writer = fileManager.openFileToWrite(rutaTXTGenerado, encoding);
		
		for (HashMap<String, String> query : ((ArrayList<HashMap<String, String>>)conf.get("querys"))) {
			if( ! ( query.get("ejecutar")!= null && query.get("ejecutar").equals("false") ) ){
				guardarDataEnArchivo(conf,query,parametros,fileManager, writer,false,null);
			}
		}

		fileManager.closeFileOpened(writer);
		
		
		if(formatoSalidaOriginal.toLowerCase().equals("xls")){
			String rutaCSV = rutaTXTGenerado;
			rutaTXTGenerado = ConvertidorCSVaEXCEL.convertir(rutaCSV, rutaTXTGenerado.split("_tmp")[0], _separador);
			new File(rutaCSV).delete();
		}
		
		if(conf.get("output") !=  null && conf.get("output").toString().length()>0){
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		if(archivoResultado != null){
			long largo = -1;
			if(new File(rutaTXTGenerado).exists()){
				largo = new File(rutaTXTGenerado).length();
			}
			
			ManejoArchivoTXT.putLineFromFileOpened(archivoResultado, ""+rutaTXTGenerado +";"+ cantidadRegistrosArchivo+";i:"+intento+";"+largo  );
			
			if(largo <= 0 && intento <= cantidadIntentosMaximos){
				intento++;
				rutaTXTGenerado = generar( txt,  pars,  _formatoSalida,  _separador,  archivoResultado); 
			}
		}
		return rutaTXTGenerado;
	}
	
	public HashMap indices = new HashMap();
	
	private void guardarDataEnArchivo(HashMap<String, Object> conf, HashMap<String, String> query, HashMap<String, String> parametros,ManejoArchivos fileManager, BufferedWriter writer, boolean enIteracion,HashMap<String, Object> registroParent){
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		
		if(query.get("datasource").toString().equals("java")){
			String[] partes = query.get("data").toString().split(";");
			String clase = partes[0];
			String metodo = partes[1];
			Object[] pars = new Object[]{ parametros };
			Class[] paramTypes = new Class[]{ HashMap.class };
			
			data = (ArrayList<HashMap<String, Object>>) UtilReflectionImpl.executeReflection(clase, metodo, paramTypes, pars);
		}
		else{
			String queryAEjecutar = query.get("sql");
			Mensajes.info("query configurada -> "+ queryAEjecutar);
			if(queryAEjecutar.contains("props:")){
				
				String[]  props = queryAEjecutar.split("props:");
				for(int i=0;i<props.length; i++){
					if(props[i].length() > 0 && props[i].contains(":")){
						try{
						String partes[] = props[i].split(":");
						
						String keyAReemplazar = "props:"+partes[0]+":"+partes[1];
						String valor = SessionUtil.getProperty(partes[0], partes[1]);
						
						queryAEjecutar = queryAEjecutar.replaceAll(keyAReemplazar, valor);
	
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				
			}
			String queryConParametros = Funciones.agregarParametrosAQuery(queryAEjecutar, parametros);
			if(enIteracion && registroParent != null){
				queryConParametros = Funciones.agregarParametrosParentAQuery(queryConParametros, registroParent);
			}
			
			//
			
			if(parametros.get("agrupar_por")!= null && queryConParametros.contains("#count_agrupado#")){
				String sql = queryConParametros;
				
				String sqlAgrupado = sql.substring(sql.indexOf("FROM"))  ;
				
				sqlAgrupado = ", (select count(1) from ( select count(1) " + sqlAgrupado + " group by "+ parametros.get("agrupar_por") +") a ) CANTIDAD_REGISTROS";
				
				queryConParametros = queryConParametros.replaceAll("#count_agrupado#", sqlAgrupado);
				
			}
					
					
			UtilLogQuerys.debug("\n\n" + queryConParametros );
			data = ExportDAO.ejecutarSelect(queryConParametros, "comun/datasources/"+query.get("datasource"));
		}
		if(data != null){
			try{
				if(parametros.get("agrupar_por")!= null){
					MiHashMap parametrosGeneracion = new MiHashMap().toMiHashMap(parametros);
					ArrayList<HashMap<String, Object>> dataAux = new ArrayList<HashMap<String,Object>>();
					for(int i=0; i<data.size(); i++){
						try{
							String[] agrupados =  parametrosGeneracion.get("agrupar_por").toString().split(",");
							String key = "";
							for(int j=0; j< agrupados.length; j++){
								if(agrupados[j]!= null){
									key = key + ((HashMap)data.get(i)).get( agrupados[j]  ).toString()+"_";
								}
							}
							dataAux = UtilesAgrupamiento.agrupar(this, key, parametrosGeneracion, data, dataAux, i);
						}
						catch(Exception e){
							System.err.println(e.getMessage());
							//e.printStackTrace();
						}
					}
					data = dataAux;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				UtilLogErrores.error("No se pudo agrupar: "+ parametros);
			}
		}
		
		ArrayList<HashMap<String, String>> columnas = (ArrayList<HashMap<String, String>>)( ((HashMap<String, HashMap>) conf.get("mapeos")).get(query.get("mapeo")).get("columnas"));
		
		if(formatoSalidaOriginal != null && formatoSalidaOriginal.toLowerCase().equals("xls")){
			
			String lineaHeader = "";
			for(int j=0; j< columnas.size();j++){
				HashMap<String, String> columna = columnas.get(j);
				boolean incluirHeader = true;
				if( columna.get("no_incluir") != null && columna.get("no_incluir").toString().equals("true")){
					incluirHeader = false;
				}
				else {
					lineaHeader+= columna.get("header");
				}
				
				
				if(j < columnas.size()-1){
					if(separador == null || separador.trim().length()==0){
						separador = ";";
					}
					if(incluirHeader) {
						lineaHeader+= separador;
					}
				}
			}
			
			fileManager.addLineToFileOpened(writer, lineaHeader);
			
		}

		UtilLogQuerys.debug("registros: " + data.size() );
		cantidadRegistrosArchivo = cantidadRegistrosArchivo + data.size();
		for(int i=0; i< data.size();i++){

			HashMap<String, Object> registro = (HashMap<String, Object>) data.get(i);
			String linea = "";
			for(int j=0; j< columnas.size();j++){
				HashMap<String, String> columna = columnas.get(j);
				boolean incluirColumna = true;
				if(formatoSalida.toLowerCase().equals("txt") || formatoSalida.toLowerCase().equals("csv") ){
					if( columna.get("no_incluir") != null && columna.get("no_incluir").toString().equals("true")){
						incluirColumna = false;
						Utiles.ejecutarMetodo(this, columna, registro);
					}
					else if( columna.get("nombre") != null && columna.get("nombre").toString().equals("row_count")){
						linea += Utiles.obtenerDataProcesada(columna,registro, (i+1)+"");
					}
					else if( columna.get("nombre") != null && columna.get("nombre").toString().startsWith("sys.")){
						linea += Utiles.obtenerDataProcesada(columna,registro, UtilesComunes.getVariable(columna.get("nombre").toString()));
					}
					else if(getDataHash(registro, columna.get("nombre"))!= null){
						linea += Utiles.obtenerDataProcesada(columna,registro, getDataHash(registro, columna.get("nombre")).toString());
					}
					else{
						if( columna.get("valor_default")!= null && columna.get("valor_default").toString().length()>0){
	
							linea += Utiles.obtenerDataProcesada(columna,registro, columna.get("valor_default").toString());
						}
						else{
							linea += Utiles.obtenerDataProcesada(columna,registro, "");
						}
					}
					
					if(formatoSalida != null && formatoSalida.toLowerCase().equals("csv") && j < columnas.size()-1){
						if(separador == null || separador.trim().length()==0){
							separador = ";";
						}
						if(incluirColumna) {
							linea+= separador;
						}
					}
				}
			}
						

			if( 	!(query.get("guardar_en_archivo")!= null && query.get("guardar_en_archivo").equalsIgnoreCase("false")) 
					|| ( enIteracion && query.get("guardar_solo_en_iteracion").equalsIgnoreCase("true")) ){
				fileManager.addLineToFileOpened(writer, linea);
			}
			
		}
		

	}
	
	public static Object getDataHash(HashMap<String, Object> hash, String key){
		
		Object retorno = hash.get(key.toLowerCase());
		if(retorno == null)
			retorno = hash.get(key.toUpperCase());
		return retorno;	
	}


	public String generar(String txt, String pars) {
		return null;
	}
}