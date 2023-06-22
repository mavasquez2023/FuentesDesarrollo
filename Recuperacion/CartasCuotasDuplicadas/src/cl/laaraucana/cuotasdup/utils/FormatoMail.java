package cl.laaraucana.cuotasdup.utils;

import java.util.ResourceBundle;

public class FormatoMail {

		
	public static String obtenerTextoMail_Ejecutivo_Error(String mensaje){
		String html = ParamConfig.RES_CONFIG.getString("mail.formato.ejecutivo.error");
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	
	public static String obtenerTextoMail_Ejecutivo_Exito(String mensaje){
		String html = ParamConfig.RES_CONFIG.getString("mail.formato.ejecutivo.exito");
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	
	public static String obtenerTextoMail_Trabajador(String nombre, String rut, String periodo){
		String html = ParamConfig.RES_CONFIG.getString("mail.formato.trabajador");
		html = html.replaceAll("#nombre#", nombre);
		html = html.replaceAll("#rut#", rut);
		html = html.replaceAll("#periodo#", periodo);
		
		return html;
	}
	
	

}
