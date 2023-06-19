package cl.laaraucana.benef.services;

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

import cl.laaraucana.benef.utils.Configuraciones;


public class MailServiceImpl implements MailService {

	private static Session mailSession;

	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	@Override
	public boolean sendEmailClie(String to, String subject, String bodyContent) throws Exception {
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
			
			//Se agrega imagen banner
			htmlPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(getClass().getResource("/img/cabecera.jpg").getFile());

			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(htmlPart);
			
			//se agrega imagen pie de pagina
			htmlPart = new MimeBodyPart();

			DataSource fds2 = new FileDataSource(getClass().getResource("/img/piedepagina.gif").getFile());

			htmlPart.setDataHandler(new DataHandler(fds2));
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


}
