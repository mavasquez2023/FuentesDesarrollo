package cl.laaraucana.rendicionpagonomina.services;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;



@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = Logger.getLogger(MailServiceImpl.class);
	
	private static Session mailSession;

	
	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception {
		logger.debug("Enviando mail to["+to+"], subject["+subject+"]");
		logger.debug("body ->"+ bodyContent);
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
			logger.debug("preparando mail...");
			String PATH_RESOURCES= Configuraciones.getConfig("mail.path.resources");
			//DataSource fds = new FileDataSource(getClass().getResource("/resources/img/piedepagina.jpg").getFile());
			DataSource fds = new FileDataSource(PATH_RESOURCES + "logo.jpg");
			
			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(htmlPart);

			msg.setContent(multipart);
			logger.debug("Enviando mail...");
			Transport.send(msg);
			logger.debug("mail enviado");
			return true;
		} catch (Exception e) {
			logger.fatal(e);
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}

	public boolean sendEmailAdj(String to, String subject, String bodyContent) throws Exception {
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
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}

}
