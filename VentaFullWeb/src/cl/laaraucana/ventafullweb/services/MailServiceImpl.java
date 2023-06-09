package cl.laaraucana.ventafullweb.services;

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

import cl.laaraucana.ventafullweb.util.Configuraciones;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	
	private static Session mailSession;
	
	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	@Override
	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception {
		
		init();
		try {
			String rutaImg = Configuraciones.getConfig("venta.web.email");
			
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");			
			
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");
			multipart.addBodyPart(htmlPart);
			
			htmlPart = new MimeBodyPart();			
			DataSource fdsLogo = new FileDataSource(rutaImg + "emailLogo.png");
			htmlPart.setDataHandler(new DataHandler(fdsLogo));
			htmlPart.setHeader("Content-ID", "<imagelogo>");		
			multipart.addBodyPart(htmlPart);

			htmlPart = new MimeBodyPart();			
			DataSource fdsHeader = new FileDataSource(rutaImg + "emailCabecera.png");
			htmlPart.setDataHandler(new DataHandler(fdsHeader));
			htmlPart.setHeader("Content-ID", "<imagehead>");		
			multipart.addBodyPart(htmlPart);
			
			htmlPart = new MimeBodyPart();			
			DataSource fdsFooter = new FileDataSource(rutaImg + "emailFooter.png");
			htmlPart.setDataHandler(new DataHandler(fdsFooter));
			htmlPart.setHeader("Content-ID", "<imagefooter>");		
			multipart.addBodyPart(htmlPart);

			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
		
	}

}
