package cl.laaraucana.simulacion.utils;

import java.util.Date;
import java.util.List;

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

//clase que permite enviar email
public class UtilEmail__ {
	private static Session mailSession;
	
	public static void init() throws NamingException {
		String jndiSessionEmail = Configuraciones.getConfig("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	// este metodo permite enviar un email con mas de un destinatario,
	// y más de una copia
	public static boolean sendEmail(List to, List cc, String subject,
			String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						(String) to.get(i)));
			}
			for (int i = 0; i < cc.size(); i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
						(String) cc.get(i)));
			}

			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en
															// html
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("problemas en el envio de email, "
					+ e.getMessage());
		}
	}

	// este metodo permite enviar un email con mas de un destinatario
	public static boolean sendEmail(List to, String subject, String bodyContent)
			throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						(String) to.get(i)));
			}

			Multipart multipart = new MimeMultipart("related");
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en
															// html
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			throw new Exception("problemas en el envio de email, "
					+ e.getMessage());
		}
	}

	// Este metodo permite enviar un email con un destinatario
	public static boolean sendEmail(String to, String subject,
			String bodyContent) throws Exception {
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
