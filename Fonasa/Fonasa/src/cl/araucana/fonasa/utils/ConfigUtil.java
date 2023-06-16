package cl.araucana.fonasa.utils;


import java.util.Properties;

import cl.recursos.EnviarMail;

public class ConfigUtil {
	public static Properties businessConfig = null;
	private static final String FILE_NAME = "etc/config.properties";
	
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
		public static void enviarMail( String[] mailDestinatarios, String adjunto) {
			String subject="";
			try {
				String host= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.host");
				String port= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.port");
				String user= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.user");
				String password= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.password");
				EnviarMail mail= new EnviarMail(host, port, user, password);
				StringBuffer body= new  StringBuffer();
				subject= "Informe Validacion Estado FONASA" ;
				if(!adjunto.equals("")){
					body.append("Estimado (a): se adjunta archivo con información de validación estado FONASA:<BR>");
				}else{
					body.append("Estimado (a): Se ha procesado exitosamente archivo, pero no se han detectado licencias con útlmio estado distinto de 72:<BR>");
				}
				body.append("<br><br>");
				body.append("Saluda atte. a Ud. "+"<BR>");
				body.append("La Araucana - Soluciones Sociales.");
				if(!adjunto.equals("")){
					mail.attach(adjunto);
				}
				mail.mailTo("aplica@laaraucana.cl", mailDestinatarios, null, null , subject, body.toString());

				System.out.println(".............. EMAIL GENERADO .................... " );

			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
		}
		
	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public static void enviarMailError(String[] mailEncargados, String mensaje) {
    	String subject="";
		try {			
			String host= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.host");
			String port= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.port");
			String user= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.user");
			String password= ConstantsUtil.MAIL_PROPERTIES.getString("smtp.password");
			EnviarMail mail= new EnviarMail(host, port, user, password);
		     StringBuffer body= new  StringBuffer();
		     subject= "Error en Validación Formulario WSFonasa" ;
		     body.append("Señor Usuario: Se ha producido un error en Validacion Estado Formulario WS Fonasa. <BR>");
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
    
}
