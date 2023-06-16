package cl.ccaf.previpass.mail;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ibm.as400.util.commtrace.Message;

import cl.ccaf.previpass.dao.PrevipassDAO;
import cl.recursos.EnviarMail;
import cl.recursos.SMTPAuthenticator;


public class EnviaMail {
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/config");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		enviarMail("Test","clillo007@gmail.com", "","Clave de autorización para el registro en Previpass:.<BR>");
		//System.out.println("dataMail:"+ PrevipassDAO.obtenerRegistro("obtenerDataMail",null));
	}

	public static void enviarMail(String subject, String destinatario,String copia,String texto) {
		
		try {
			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
			
			String[] lista_to = destinatario.split(";");
			
			String[] lista_copy = null;
			if(copia != null && copia.trim().length()>5){
				lista_copy = copia.split(";");
			}
			StringBuffer body = new StringBuffer();
			body.append(texto);
			
			boolean enviado= mail.mailTo(user + "@laaraucana.cl", lista_to, lista_copy, null,
					subject, body.toString());
			if(enviado){
				System.out.println(".............. EMAIL GENERADO .................... ");
			}else{
				System.out.println(".............. ERROR AL ENVIAR MAIL .................... ");
			}

		} catch (Exception e) {
			System.out.println("CAI EN MAIL  ");
			e.printStackTrace();
		}	}
}
