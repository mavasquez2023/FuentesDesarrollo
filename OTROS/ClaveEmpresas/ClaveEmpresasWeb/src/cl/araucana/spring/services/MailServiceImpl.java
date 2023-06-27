package cl.araucana.spring.services;

 

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
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
import org.springframework.stereotype.Service;

import cl.araucana.spring.utils.Configuraciones;

 

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = Logger.getLogger(MailServiceImpl.class);



	
	
	private final Properties properties = new Properties();

	// private String password;
	private final ResourceBundle prop = ResourceBundle.getBundle("etc/properties");

	 
	
private static Session mailSession;
	
	private  void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
		
		properties.put("mail.smtp.ssl.trust", prop.getString("host-email"));
		properties.put("mail.smtp.host", prop.getString("host-email"));
		// properties.put("mail.smtp.socketFactory.class",
		// "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", prop.getString("port-email"));
		properties.put("mail.smtp.mail.sender", prop.getString("mail-sender"));
		properties.put("mail.smtp.user", prop.getString("user-email"));
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.required", "true");
	}

	 

	 
	
	public  boolean sendEmail(String rut, String to, String bodyContent, String ruta) throws Exception {
		init();
		try {
			MimeMultipart multiParte = new MimeMultipart("related");
			// Se compone la parte del texto
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(bodyContent, "text/html");
			
			multiParte.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(getClass().getResource("/resources/img/Header.jpg").getFile());

			messageBodyPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			messageBodyPart.setHeader("Content-ID", "<image>");

			multiParte.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			DataSource fds2 = new FileDataSource(getClass().getResource("/resources/img/footer.jpg").getFile());

			messageBodyPart.setDataHandler(new DataHandler(fds2));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			messageBodyPart.setHeader("Content-ID", "<image2>");

			multiParte.addBodyPart(messageBodyPart);
			// Se compone el adjunto con la imagen
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
			adjunto.setFileName("Sol_CLAVE_" + rut + ".pdf");

			// Una MultiParte para agrupar texto e imagen.
			multiParte.addBodyPart(adjunto);

			
			Message msg = new MimeMessage(mailSession);
			msg.setSubject("Solicitud clave " + rut);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setContent(multiParte);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("Error al enviar el email " + e.getMessage());
		}
	}
	
	
	
	
	 
}
