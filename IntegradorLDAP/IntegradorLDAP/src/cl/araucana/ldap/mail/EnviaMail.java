package cl.araucana.ldap.mail;

import java.util.Date;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class EnviaMail {
	private static final Logger logger = Logger.getLogger(EnviaMail.class);
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Session mailSession;
	
	public static void init() throws NamingException {
		String jndiSessionEmail = mailProperties.getString("mail.session");
		logger.info("Usando jndi mail: " + jndiSessionEmail);
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}
	
	public static boolean enviarMail(String subject, String to, String copyto, String bodyContent) {
		logger.info("Enviando mail to["+to+"], copyto["+copyto+"], subject["+subject+"]");
		//logger.debug("body ->"+ bodyContent);
		
		try {
			logger.debug("inicializando mailSession");
			init();
			logger.debug("Steando mensaje y destinatarios");
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			if(copyto!=null && !copyto.equals("")){
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copyto, false));
			}
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en

			multipart.addBodyPart(htmlPart);

  			htmlPart = new MimeBodyPart();
  			//Adjuntando imagen logo
			String PATH_RESOURCES= mailProperties.getString("mail.path.resources");
			//DataSource fds = new FileDataSource(getClass().getResource("/resources/img/piedepagina.jpg").getFile());
			DataSource fds = new FileDataSource(PATH_RESOURCES + "logo.jpg");
			
			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(htmlPart);

			msg.setContent(multipart);
			logger.debug("Enviando mail...");
			Transport.send(msg);
			logger.info("mail enviado");
			return true;
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
			//throw new Exception("Error al enviar el email " + e.getMessage());
			return false;
		}
	}
	
	/*public static boolean enviarMaila(String subject, String destinatario,String copia,String texto) {
		
		try {
			
			EnviarMail mail = new EnviarMail(host, port, user, pass);
			String[] lista_to = destinatario.split(";");
			
			String[] lista_copy = null;
			if(copia != null && copia.trim().length()>5){
				lista_copy = copia.split(";");
			}
			StringBuffer body = new StringBuffer();
			body.append(texto);
			
			boolean enviado= mail.mailTo(user+ "@laaraucana.cl", lista_to, lista_copy, null,
					subject, body.toString());
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
		}	
		}*/
	
}
