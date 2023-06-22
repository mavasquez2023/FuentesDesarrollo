package cl.araucana.cotcarserv.utils;

import java.util.ResourceBundle;

public class FormatoMail {

	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
		
	public static String obtenerTextoMailCotizaciones(String Empresa, String periodo){
		String html = mailProperties.getString("mail.formato.empresas.cotizaciones");
		html = html.replaceAll("#empresa#", Empresa);
		html = html.replaceAll("#periodo#", periodo);
		
		return html;
	}
	public static String obtenerTextoMailCargas(String Empresa, String periodo){
		String html = mailProperties.getString("mail.formato.empresas.cargas");
		html = html.replaceAll("#empresa#", Empresa);
		html = html.replaceAll("#periodo#", periodo);
		
		return html;
	}
	

	public static String obtenerTextoMailCronta_Cot(String username, String mensaje){
		String html = mailProperties.getString("mail.formato.cronta.cotizaciones.error");
		html = html.replaceAll("#username#", username);
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	
	public static String obtenerTextoMailCronta_Cargas(String username, String mensaje){
		String html = mailProperties.getString("mail.formato.cronta.cargas.error");
		html = html.replaceAll("#username#", username);
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}
	
	public static String obtenerTextoMailCronta_Sap(String username, String mensaje){
		String html = mailProperties.getString("mail.formato.cronta.sap.error");
		html = html.replaceAll("#username#", username);
		html = html.replaceAll("#mensaje#", mensaje);
		
		return html;
	}

}
