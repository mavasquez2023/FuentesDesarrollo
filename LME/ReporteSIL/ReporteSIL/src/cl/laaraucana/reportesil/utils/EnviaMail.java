package cl.laaraucana.reportesil.utils;


import cl.recursos.EnviarMail;


public class EnviaMail {


	public static boolean enviarMail(String subject, String destinatario, String copia, String texto, String adjunto) {

		try {
			String host= Configuraciones.getConfig("smtp.host");
			String port= Configuraciones.getConfig("smtp.port");
			String user= Configuraciones.getConfig("smtp.user");
			String pass= Configuraciones.getConfig("smtp.password");
			
			EnviarMail mail = new EnviarMail(host, port, user, pass);
			String[] lista_to = destinatario.split(";");
			
			String[] lista_copy = null;
			if(copia != null && copia.trim().length()>5){
				lista_copy = copia.split(";");
			}
			mail.attach(adjunto);
			boolean enviado= mail.mailTo(user+ "@laaraucana.cl", lista_to, lista_copy, null,
					subject, texto);
			if(enviado){
				System.out.println(".............. EMAIL GENERADO .................... ");
				return true;
			}else{
				System.out.println(".............. ERROR ENVIO MAIL .................... ");
				return false;
			}
		} catch (Exception e) {
			System.out.println("CAI EN MAIL  ");
			e.printStackTrace();
			return false;
		}	}
}
