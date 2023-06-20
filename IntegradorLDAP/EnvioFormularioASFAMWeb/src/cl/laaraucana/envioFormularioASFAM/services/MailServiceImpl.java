package cl.laaraucana.envioFormularioASFAM.services;

import java.io.File;
import java.util.Date;
import java.util.List;

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

import cl.laaraucana.envioFormularioASFAM.util.Configuraciones;

@Service
public class MailServiceImpl implements MailService {

	private static Session mailSession;

	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	public boolean sendEmail(String to, String subject, String bodyContent) throws Exception {
		init();
		try {
			
			String path = Configuraciones.getConfig("asfam.carpeta");
			
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en

			multipart.addBodyPart(htmlPart);

			htmlPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(new File(path + "\\" + "piedepagina.gif"));

			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

			multipart.addBodyPart(htmlPart);

			// Se compone el adjunto con la imagen
			/*
			 * BodyPart adjunto = new MimeBodyPart(); adjunto.setDataHandler(new
			 * DataHandler(new FileDataSource(ruta))); adjunto.setFileName("diferimiento_" +
			 * rut + ".pdf");
			 * 
			 * // Una MultiParte para agrupar texto e imagen.
			 * multipart.addBodyPart(adjunto);
			 */
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
			
			String path = Configuraciones.getConfig("asfam.carpeta");
			
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en

			multipart.addBodyPart(htmlPart);

			htmlPart = new MimeBodyPart();

			DataSource fds = new FileDataSource(new File(path + "\\" + "piedepagina.gif"));

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

	public boolean sendEmailEjec(List<String> to, List<String> cc, String subject, String bodyContent)
			throws Exception {
		init();
		try {
			
			String path = Configuraciones.getConfig("asfam.carpeta");
			
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String) to.get(i)));
			}
			/*
			 * for (int i = 0; i < cc.size(); i++) {
			 * msg.addRecipient(Message.RecipientType.CC, new InternetAddress( (String)
			 * cc.get(i))); }
			 */
			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();

			htmlPart.setContent(bodyContent, "text/html");
			multipart.addBodyPart(htmlPart);
			htmlPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(new File(path + "\\" + "piedepagina.gif"));

			htmlPart.setDataHandler(new DataHandler(fds));
			// messageBodyPart.setDisposition(MimeBodyPart.INLINE);
			htmlPart.setHeader("Content-ID", "<image>");

		
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("problemas en el envio de email, " + e.getMessage());
		}
	}

}
