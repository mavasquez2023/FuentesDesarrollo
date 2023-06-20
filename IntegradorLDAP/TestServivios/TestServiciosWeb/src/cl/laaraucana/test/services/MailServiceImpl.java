package cl.laaraucana.test.services;

import java.util.Date;

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

import cl.laaraucana.test.util.Configuraciones;


@Service
public class MailServiceImpl implements MailService {

	private static Session mailSession;

	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	public void setSession(String jndiSessionEmail) throws NamingException {
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}
	
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception {
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
			e.printStackTrace();
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}

}
