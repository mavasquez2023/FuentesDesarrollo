package cl.jfactory.planillas.service.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;

public class ProcesoNoInvalidanteUtil {

	static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static JSONObject getInfoProcesoNoInvalidante(String codigo){
		JSONObject data = null;
		String pathFile = PropertiesUtil.workflowProperties.getString("config.path.procesos.no.invalidantes")+"/"+codigo;
		if(new File(pathFile).exists()){
			try {
				data = new JSONObject( ManejoArchivoTXT.getFileContentAsString(pathFile) );
				return data;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void guardarEsperandoProceso(String codigo){
		JSONObject data = null;
		String pathFile = PropertiesUtil.workflowProperties.getString("config.path.procesos.no.invalidantes")+"/"+codigo;
		
		if(!new File(pathFile).exists()){
			try {
				new File(pathFile).createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if(new File(pathFile).exists()){
			try {
				String contenido = ManejoArchivoTXT.getFileContentAsString(pathFile);
				if(contenido.length()>0){
					data = new JSONObject( contenido );
				}
				else{
					data = new JSONObject();
					data.put("historico", new JSONArray());
				}
				data.put("estado", "En Espera...");
				data.put("fecha_inicio", formatoFecha.format(new Date()));
				data.put("fecha_termino", "-");
				
				PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(pathFile);
				ManejoArchivoTXT.putLineFromFileOpened(pw, data.toString());
				ManejoArchivoTXT.closeFileToWrite(pw);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void guardarInicioProceso(String codigo){
		JSONObject data = null;
		String pathFile = PropertiesUtil.workflowProperties.getString("config.path.procesos.no.invalidantes")+"/"+codigo;
		
		if(!new File(pathFile).exists()){
			try {
				new File(pathFile).createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
		if(new File(pathFile).exists()){
			try {
				String contenido = ManejoArchivoTXT.getFileContentAsString(pathFile);
				if(contenido.length()>0){
					data = new JSONObject( contenido );
				}
				else{
					data = new JSONObject();
					data.put("historico", new JSONArray());
				}
				data.put("estado", "Iniciado");
				data.put("fecha_inicio", formatoFecha.format(new Date()));
				data.put("fecha_termino", "-");
				
				PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(pathFile);
				ManejoArchivoTXT.putLineFromFileOpened(pw, data.toString());
				ManejoArchivoTXT.closeFileToWrite(pw);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void guardarTerminoProceso(String codigo){
		JSONObject data = null;
		String pathFile = PropertiesUtil.workflowProperties.getString("config.path.procesos.no.invalidantes")+"/"+codigo;
		
		
		if(new File(pathFile).exists()){
			try {
				String contenido = ManejoArchivoTXT.getFileContentAsString(pathFile);
				if(contenido.length()>0){
					data = new JSONObject( contenido );
				}
				else{
					data = new JSONObject();
					data.put("historico", new JSONArray());
				}
				data.put("estado", "Finalizado");
				data.put("fecha_termino", formatoFecha.format(new Date()));
				
				
				JSONObject item  = new JSONObject();
				item.put("estado", data.get("estado"));
				item.put("fecha_inicio", data.get("fecha_inicio"));
				item.put("fecha_termino", data.get("fecha_termino"));

				data.getJSONArray("historico").put(item);
				
				PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(pathFile);
				ManejoArchivoTXT.putLineFromFileOpened(pw, data.toString());
				ManejoArchivoTXT.closeFileToWrite(pw);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
