package cl.laaraucana.monitoreoLogLME.services;

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

import cl.laaraucana.monitoreoLogLME.config.Configuraciones;



public class MailServiceImpl implements MailService{
	
private static Session mailSession;
	
	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}
	
	public boolean sendEmail(String to, String subject,
			String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			to= to.replaceAll(";", ",");
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("fabrizio.barisione@gmail.com", false));
			Multipart multipart = new MimeMultipart("related");
			
			//Se agrega body
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en
			multipart.addBodyPart(htmlPart);
			
			//Se agrega footer image
			//BodyPart footerPart = new MimeBodyPart();
			//DataSource fds = new FileDataSource(getClass().getResource("/img/logo_reducido.jpg").getFile());
			//footerPart.setDataHandler(new DataHandler(fds));
			//footerPart.setHeader("Content-ID", "<image>");
			//multipart.addBodyPart(footerPart);
			
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}

}
