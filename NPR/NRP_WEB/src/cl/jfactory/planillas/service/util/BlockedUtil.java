package cl.jfactory.planillas.service.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLogErrores;

public class BlockedUtil {
	
	public static boolean existeArchivoBloqueo(){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+periodo+"/";
		return new File(pathCompleto+"blocked.txt").exists();
	}
	
	public static void actualizarEstadoProcesoBloqueado(String idProceso, String descripcion){

		if(!existeArchivoBloqueo()) return;
		
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+periodo+"/";
		final String rutaFileBlocked = pathCompleto+"blocked.txt";	
		
		String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
		JSONObject json;
		try {
			json = new JSONObject(dataBlocked);

			JSONArray _procs = (JSONArray)json.get("procesos");
			for(int j=0; j< _procs.length(); j++){
				if(  (  _procs.getJSONObject(j) ).get("codigo").equals( idProceso  )  ){
					(  _procs.getJSONObject(j) ).put("estado", descripcion);
				}
			}
			json.put("time_termino_consolidacion", new Date().getTime());
			UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public static void terminarBloqueo(String proceso, int status){

		if(!existeArchivoBloqueo()) return;
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+periodo+"/";
		File folder = new File(PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+periodo+"/");
			try{
				String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
				JSONObject json = new JSONObject(dataBlocked);
				if(json.has("descripcion")){
					json.put("descripcion", proceso );
					json.put("reintentar_en", new Integer(-1));
					json.put("status_blocked", status);
					
				}
				UtilesWorkflow.crearArchivoConDataJson(pathCompleto+"blocked.txt", json);
			} catch(Exception e){UtilLogErrores.error(e);e.printStackTrace();
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			
			}
			return ;
		
	}
	
	
	public static void agregarProcesoAlBloqueo(ArrayList procesos){

		if(!existeArchivoBloqueo()) return;
		
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio")+periodo+"/";
		String dataBlocked = ManejoArchivoTXT.getFileContentAsString(pathCompleto+"blocked.txt");
		String rutaFileBlocked = pathCompleto+"blocked.txt";
		ArrayList procs = new ArrayList();
		JSONObject json;
		try {
			json = new JSONObject(dataBlocked);
			
			procs.addAll(procesos);
			
			json.put("procesos", procs);
			UtilesWorkflow.crearArchivoConDataJson(rutaFileBlocked, json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
}
