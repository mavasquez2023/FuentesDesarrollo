package cl.laaraucana.tarjetatam.services;

import java.util.Date;

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

import org.springframework.stereotype.Service;

import cl.laaraucana.tarjetatam.util.Configuraciones;





@Service
public class MailServiceImpl implements MailService {

	private static Session mailSession;

	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	@Override
	public boolean sendEmailClie(String to, String subject, String bodyContent, String rut) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en

			multipart.addBodyPart(htmlPart);
			
			//se garega imagen cabecera
			htmlPart = new MimeBodyPart();
			
			DataSource fds = new FileDataSource(Configuraciones.getConfig("pdfs.carpeta") + "cabecera.jpg");
			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(htmlPart);
			
			//se garega imagen pie de página
			htmlPart = new MimeBodyPart();
			
			fds = new FileDataSource(Configuraciones.getConfig("pdfs.carpeta") + "piedepagina.gif");
			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image2>");
			multipart.addBodyPart(htmlPart);

			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}
	
	
	public boolean sendEmailEjec(String to, String subject, String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en

			multipart.addBodyPart(htmlPart);

			htmlPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(Configuraciones.getConfig("pdfs.carpeta") + "logo.jpg");

			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(htmlPart);
		

			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}


}
