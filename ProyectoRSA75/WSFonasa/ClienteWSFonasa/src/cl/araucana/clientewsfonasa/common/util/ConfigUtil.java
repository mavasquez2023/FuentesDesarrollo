package cl.araucana.clientewsfonasa.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cl.recursos.EnviarMail;

public class ConfigUtil {
	public static Properties businessConfig = null;
	private static final String FILE_NAME = "cl/araucana/clientewsfonasa/resources/properties/AplicationConfig.properties";
	
	private static void loadProperties(){
		if(businessConfig == null){
			businessConfig = new Properties();
    		try {
    			businessConfig.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getValor(String llave){
		loadProperties();
		if(businessConfig != null){
			String valor = businessConfig.getProperty(llave);
			return (valor != null && valor.equals(""))? null: valor;
		}
		return null;
	}
	
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public static void enviarMail(String[] mailEncargados, String servicio, String mensaje) {
    	String subject="";
		try {			
		     EnviarMail mail= new EnviarMail("aplica", "aplica");
		     StringBuffer body= new  StringBuffer();
		     subject= "Error " + servicio + " de ClienteWSFonasa" ;
		     body.append("Señor Usuario: Se ha producido un error en Cliente WS Fonasa. <BR>");
		     body.append("Mensaje:<BR>" + mensaje);
			
			body.append("<br><br>");
			body.append("Saluda atte. a Ud. "+"<BR>");
			body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("aplica@laaraucana.cl", mailEncargados, null, null , subject, body.toString());
		  	
		  	System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
    
    public static String[] split(String s, String delimiter) {
    	List tokens = new ArrayList();
    	int fromIndex = 0;
    	int index;
    	int length = s.length();
    	int delimiterLength = delimiter.length();

    	while (fromIndex < length) {
    		index = s.indexOf(delimiter, fromIndex);
    		if (index < 0) {
    			tokens.add(s.substring(fromIndex));
    			break;
    		}
    		tokens.add(s.substring(fromIndex, index).trim());
    		fromIndex = index + delimiterLength;
    	}
    	return (String[]) tokens.toArray(new String[0]);
    }
}
