package cl.jfactory.planillas.service.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.mail.impl.MailImpl;

public class Utiles {

	public static int getInt(Object data){
		if(data instanceof BigDecimal){
			return ((BigDecimal)data).intValue();
		}
		else if(data instanceof BigInteger){
			return ((BigInteger)data).intValue();
			
		} 
		return -1;
	}
	
	
	

	public static String getStringConData(String texto, MiHashMap data){
		String salida = "";
		String[] partes = texto.split("#");
		
		
		for(int i=0; i< partes.length; i++){
			String valor = partes[i];
			if(i % 2 == 1){
				if(data.get(valor.trim()) != null)
					valor = data.get(valor.trim()).toString();
				else
					valor = "#" + valor + "#" ;
			}
			
			salida = salida + valor ;
			
		}
		
		return salida;
		
	}
	public static String getStringConData(String texto, MiHashMap data, String marcadorData){
		String salida = "";
		String[] partes = texto.split(marcadorData);
		
		
		for(int i=0; i< partes.length; i++){
			String valor = partes[i];
			if(i % 2 == 1){
				if(data.get(valor.trim()) != null) {
					valor = data.get(valor.trim()).toString();
				}
				else {
					//valor = marcadorData + valor + marcadorData ;
					valor = "" ;
				}
			}
			
			salida = salida + valor ;
			
		}
		
		return salida;
		
	}
	
	
	public static String obtenerTextoMailNominas(MiHashMap data, String tipoNomina){
		String texto = PropertiesUtil.configuracionesMail.getString("mail.config.envio.nomina."+tipoNomina);
		
		ArrayList nominasGeneradas = (ArrayList) data.get("nominas");
		String textoNominasGeneradas = "";
		if(nominasGeneradas!= null && nominasGeneradas.size() > 0) {
			for(int i=0; i< nominasGeneradas.size(); i++) {
				MiHashMap item = (MiHashMap) nominasGeneradas.get(i);
				String template = PropertiesUtil.configuracionesMail.getString("mail.config.envoio.template.item.nomina")+"<br/>";
				String cantidadRegistros = "0";
				String montoNomina = "$0";
				if(item.get("CANTIDAD_REGISTROS") != null){
					cantidadRegistros = UtilesWorkflow.formatoCantidad(item.get("CANTIDAD_REGISTROS").toString());
				}
				if( item.get("MONTO_NOMINA") != null ){
					montoNomina = UtilesWorkflow.formatoMonto(item.get("MONTO_NOMINA").toString());
				}
				item.put("CANTIDAD_REGISTROS", cantidadRegistros );
				item.put("MONTO_NOMINA", montoNomina );
				item.put("NOMBRE_ARCHIVO", item.get("RUTA_ARCHIVO").toString().substring( item.get("RUTA_ARCHIVO").toString().lastIndexOf("/")+1 ));
				textoNominasGeneradas = textoNominasGeneradas + getStringConData(template, item, "##") ;
			}
		}
		
		data.put("nominas_generadas",textoNominasGeneradas);
		return getStringConData(texto, data,"##");
		
	}
	
	
	public static String ifNullOrEmpty(Object data, String value){
		if(data == null){
			return value;
		}
		else if(data.toString().trim().length() == 0){
			return value;
		}
		return data.toString();
	}
	
	public static void asignaValorNoNulo(HashMap registro, String key, String valor){
		if(registro != null)
			registro.put(key,Utiles.ifNullOrEmpty(				registro.get(key), valor));
		
	}
	
	public static String getPeriodoActual(){
		String periodo = UtilesComunes.reemplazarVariables("sys.YearMonth");
		return periodo;
	}
	
	public static void callURL(String url){
		try {
			URL miUrl = new URL(url);
			URLConnection miUrlCon = miUrl.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(miUrlCon.getInputStream()));
		} catch (Exception e) {
			UtilLogProcesos.error("Error al invocar URL de ResultadoNRP:" + url);
			e.printStackTrace();
		} 
	}
}
