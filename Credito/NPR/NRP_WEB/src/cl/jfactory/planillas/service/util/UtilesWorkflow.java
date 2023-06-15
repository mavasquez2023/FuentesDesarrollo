package cl.jfactory.planillas.service.util;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;

public class UtilesWorkflow {
	
	
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String formatoMonto(String monto){
		monto = monto.replace(".", "");
		String montoFormateado = "";
		for(int i=0; i<monto.length() ; i++){
			if(i>0 && i % 3 == 0){
				montoFormateado = "." + montoFormateado ;
			}
			montoFormateado = monto.charAt( (monto.length()-1)-i ) + montoFormateado ; 
		}
		
		return "$" +montoFormateado;
	}
	public static String formatoCantidad(String cantidad){
		cantidad = cantidad.replace(".", "");
		String cantidadFormateada = "";
		for(int i=0; i<cantidad.length() ; i++){
			if(i>0 && i % 3 == 0){
				cantidadFormateada = "." + cantidadFormateada ;
			}
			cantidadFormateada = cantidad.charAt( (cantidad.length()-1)-i ) + cantidadFormateada ; 
		}
		
		return cantidadFormateada;
	}
	
	public static String getFechaFormateada(Date fecha, String formato){
		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(fecha);
	}
	
	public static String getFechaDespachoSugerida(){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(new Date().getTime());
		gc.add(GregorianCalendar.DAY_OF_YEAR, 3);
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		
		return formato.format(gc.getTime());
	}
	
	
	public static boolean crearArchivoConDataJson(Object session, Object parameters, String pathCompleto) {
		JSONObject json = new JSONObject();
		String nombreArchivo = pathCompleto.substring(pathCompleto.lastIndexOf("/")+1);
		String key = "guarda_archivo_json."+nombreArchivo;
		String data = PropertiesUtil.propertiesGuardaArchivoJSON.getString(key);
		String[] parametros = data.split(";");
		if(parametros != null ){
			for(int i=0; i<parametros.length; i++){
				if(parametros[i].trim().length()>3){
					String[] partes = parametros[i].split(":");
					String valor = "";
					if(partes.length>=2 && partes[1].contains("sys.")){
						valor = UtilesComunes.reemplazarVariables(partes[1]);
					}
					else if(partes.length>=2 &&  partes[1].contains("parameters.")){
						String keyData = partes[1].substring(partes[1].indexOf(".")+1);
						valor = ((HashMap)parameters).get(keyData)+"";
					}
					else if(partes.length>=2 &&  partes[1].contains("session.")){
						String[] partesSession = partes[1].split("\\.");
						if(session != null && 
						((HashMap)session).get(partesSession[1])!= null){
							valor = ((HashMap)((HashMap)session).get(partesSession[1])).get(partesSession[2])+"";
						}
					}
					try {
						json.put(partes[0], valor);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	
		return crearArchivoConDataJson(pathCompleto, json);
	}
	
	public static boolean validaPermisoEscritura(String rutaArchivo) throws Exception{

		
		if(!new File(rutaArchivo).canWrite()){
			new File(rutaArchivo).setWritable(true, false);
		}
		
		return true;
	}
	
	public static String formatearFecha(String fecha, String formatoEntrada, String formatoSalida){
		String salida = "";
		String dia = "";
		String mes = "";
		String year = "";
		if(fecha != null){
			if(formatoEntrada != null){
				if(formatoEntrada.equals("dd/MM/yyyy")){
					
				}
				if(formatoEntrada.equals("dd-MM-yyyy")){
					
				}
				else if(formatoEntrada.equals("ddMMyyyy")){
					
				}
				else if(formatoEntrada.equals("yyyyMMdd")){
					dia = fecha.substring(6);
					mes = fecha.substring(4, 6);
					year = fecha.substring(0,4);
				}
				else if(formatoEntrada.equals("yyyy/MM/dd")){
					
				}
				else if(formatoEntrada.equals("yyyy-MM-dd")){
					
				}
			
			}
			
			if(formatoSalida != null){
				if(formatoSalida.equals("dd/MM/yyyy")){
					return dia +"/"+ mes +"/"+ year;
				}
			}
			
		}
		
		return salida;
	}
	
	
	public static boolean crearArchivoConDataJson(String rutaArchivo, JSONObject json){
		HashMap salida = new HashMap();
		try {
			validaPermisoEscritura(rutaArchivo);
			
			PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(rutaArchivo);
			if(pw == null)
				return false;
			String jsonStr = json.toString();

			jsonStr = jsonStr.toString().replaceAll("\"\\[", "[");
			jsonStr = jsonStr.toString().replaceAll("\\]\"", "]");
			jsonStr = jsonStr.toString().replaceAll("\\\\", "");
			
			ManejoArchivoTXT.putLineFromFileOpened(pw, jsonStr);
			ManejoArchivoTXT.closeFileToWrite(pw);
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	

}
