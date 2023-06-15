package cl.lib.export.xls.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cl.liv.export.comun.dao.ExportDAO;
import cl.liv.export.comun.util.Funciones;
import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.PropertiesComunUtil;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.xls.util.xml.LectorXLSConfig;

public class GenerarXLS {
	
	public static String generar(String xls, String pars) {

		HashMap<String, Object> conf = null;
		HashMap<String, String> parametros = Funciones.procesarParametros(pars);

		if (SessionUtil.reportes.get(xls) == null) {
			Mensajes.info("Cargando data reporte...");
			LectorXLSConfig.getDataXML(xls);
		} else {
			Mensajes.info("Reutilizando data reporte");
		}
		conf = SessionUtil.xlss.get(xls);

		if (conf == null) {
			Mensajes.info("ERROR AL OBENER CONFIGURACION DEL REPORTE.");
			return "";
		}

		
		String rutaCSVGenerado = PropertiesComunUtil
				.getProperty("export.path.output") + xls +"_"+Math.random()+"_.xls";
		
		

		
		int contadorRows = 0;
		HSSFWorkbook libro = new HSSFWorkbook();
		HSSFSheet hoja = libro.createSheet();
		ArrayList<HashMap<String, Object>> querys = ((ArrayList<HashMap<String, Object>>)conf.get("querys"));
		for (HashMap<String, Object> query : querys) {
			if(! query.get("only_for").equals("true") && query.get("tipo").toString().equals("query") ){
				contadorRows = llenarXLS(libro,hoja,querys,query,parametros,conf,contadorRows,false,null);
			}
		}
		
		if(contadorRows == 0){
			try{
				/*
				 XLSHelper.setCeldaValue(libro, hoja, contadorRows, jPosition,columna.get("header") , true, heightDefault);
								largoColumna.put(columna.get("nombre"), (columna.get("header")+"").length()  );
								hoja.setColumnWidth(jPosition, (espacioPorCaracter + 30) *  (columna.get("header")+"").length()  );
							
				 
				*/
				ArrayList<HashMap<String, Object>> columnas = (ArrayList<HashMap<String, Object>>) ((HashMap<String, Object>)conf.get("mapeos")).get("mapeo_visitas");
				int contadorColumna = 0;
	
				short heightDefault = 300;
				int espacioPorCaracter = 270;
				if(columnas != null){
					for (HashMap<String, Object> columna : columnas) {
						
						XLSHelper.setCeldaValue(libro, hoja, contadorRows, contadorColumna ,columna.get("header").toString() , true, heightDefault);
						hoja.setColumnWidth(contadorColumna, (espacioPorCaracter + 30) *  (columna.get("header")+"").length()  );
					
						contadorColumna++;
					}
				}
				XLSHelper.setCeldaValue(libro, hoja, 1, 0 ,"No existe información para mostrar." , false, heightDefault);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		Mensajes.info("Cantidad Registros: "+ contadorRows);
		FileOutputStream elFichero;
		try {
			elFichero = new FileOutputStream(rutaCSVGenerado);
			libro.write(elFichero);
			elFichero.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//ManejoArchivos.editFile(new File(rutaCSVGenerado));
		
		if(new File(rutaCSVGenerado).exists()){
			
			
			
			return rutaCSVGenerado;
		
		
		}
		
		
		return null;
	}
	
	private static int llenarXLS(HSSFWorkbook libro, HSSFSheet hoja, ArrayList<HashMap<String, Object>> querys, HashMap<String, Object> query,HashMap<String, String> parametros,HashMap<String, Object> conf, int contadorRows, boolean en_for,HashMap<String, Object> registroIterado){

			boolean primeraColumna = true;
			String queryCompleta = Funciones.getQueryCompletaConParametrosDinamicos(querys, query, parametros);
			Mensajes.info("query -> "+ query.get("id"));
		
			String queryConParametros = "";
			if(en_for == false)
				queryConParametros = Funciones.agregarParametrosAQuery(queryCompleta, parametros);
			else
				queryConParametros = Funciones.agregarParametrosAQuery(queryCompleta, parametros,registroIterado);
			
			Mensajes.info("\nTEST Query Con Parametros:["+query.get("id")+"] "+ queryConParametros +"\n");
			
			ArrayList<HashMap<String, Object>> data = ExportDAO.ejecutarSelect(queryConParametros, "comun/datasources/"+query.get("datasource"));
			
			ArrayList<HashMap<String, String>> columnas = (ArrayList<HashMap<String, String>>)((HashMap<String, Object>) conf.get("mapeos")).get(query.get("id_mapeo"));
			if(columnas!= null && columnas.size()>0){
				
				short heightDefault = 300;

				int espacioPorCaracter = 270;
				for(int j=0; j< columnas.size();j++){
					HashMap<String, String> columna = columnas.get(j);
					XLSHelper.setCeldaValue(libro, hoja, contadorRows, j,columna.get("header") , true, heightDefault);
					hoja.setColumnWidth(j, (espacioPorCaracter + 30) *  (columna.get("header")+"").length()  );
				}
				
				
				HashMap<String , Integer> largoColumna = new HashMap<String , Integer>();
				for(int i=0; i< data.size();i++){
		
		
					HashMap<String, Object> registro = (HashMap<String, Object>) data.get(i);
					String linea = "";
					
					
					for(int j=0; j< columnas.size();j++){
						HashMap<String, String> columna = columnas.get(j);
						int jPosition = j;
						if(en_for)
							jPosition = j + 1;
						if(largoColumna.get(columna.get("nombre")) == null){
							largoColumna.put(columna.get("nombre"), 0 );
						}
						if(primeraColumna && query.get("mostrar_titulo").toString().equalsIgnoreCase("true")){
							XLSHelper.setCeldaValue(libro, hoja, contadorRows, jPosition,columna.get("header") , true, heightDefault);
							largoColumna.put(columna.get("nombre"), (columna.get("header")+"").length()  );
							hoja.setColumnWidth(jPosition, (espacioPorCaracter + 30) *  (columna.get("header")+"").length()  );
						}
						
						
						XLSHelper.setCeldaValue(libro, hoja, contadorRows+1, jPosition,registro.get(columna.get("nombre"))+"" , false, heightDefault);
						
						
						if(( registro.get(columna.get("nombre"))+"").length() > largoColumna.get(columna.get("nombre"))){
							largoColumna.put(columna.get("nombre"), (registro.get(columna.get("nombre"))+"").length()  );
							hoja.setColumnWidth(jPosition, espacioPorCaracter *  (registro.get(columna.get("nombre"))+"").length()  );
						}
						
					}

					contadorRows++;
					if(query.get("for_query") != null && query.get("for_query").toString().length() >0){
						String[] subQuerys = query.get("for_query").toString().split(";");
						for(int aux = 0; aux < subQuerys.length ; aux++){
							boolean queryEncontrada = false;
							for (HashMap<String, Object> _query :  querys) {
								if(!queryEncontrada && subQuerys[aux].equals(_query.get("id"))){
									queryEncontrada = true;
									if(_query.get("mostrar_titulo") != null && _query.get("mostrar_titulo").equals("true"))
										contadorRows++;
									contadorRows = llenarXLS(libro, hoja, querys, _query, parametros, conf, contadorRows,true,registro);
	
								}
							}
						}
						
					}
					
					primeraColumna = false;
					
				}
	
		}
		return contadorRows ;

	}
	
}
