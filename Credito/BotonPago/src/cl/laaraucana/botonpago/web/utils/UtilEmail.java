package cl.laaraucana.botonpago.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

//clase que permite enviar email
public class UtilEmail {
	private static Session mailSession;

	public static void init() throws Exception {
		String jndiSessionEmail = Constantes.getInstancia().MAIL_SESSION;
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	// este metodo permite enviar un email con mas de un destinatario,
	// y más de una copia
	public static boolean sendEmail(List<?> to, List<?> cc, String subject, String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String) to.get(i)));
			}
			for (int i = 0; i < cc.size(); i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress((String) cc.get(i)));
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
			throw new Exception("problemas en el envio de email, " + e.getMessage());
		}
	}

	// este metodo permite enviar un email con mas de un destinatario
	public static boolean sendEmail(List<?> to, String subject, String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String) to.get(i)));
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
			throw new Exception("problemas en el envio de email, " + e.getMessage());
		}
	}

	/**
	 * Permite enviar un email con un destinatario
	 * @param to: dirección de correo de destino
	 * @param subject: asunto
	 * @param bodyContent: cuerpo del email
	 * @return
	 * @throws Exception
	 */
	public static boolean sendEmail(String to, String subject, String bodyContent) throws Exception {
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

	/**
	 * Envia correo con banner adjunto
	 * @param mailSession
	 * @param to
	 * @param subject
	 * @param bodyContent
	 * @return
	 * @throws Exception
	 */
	// este metodo permite enviar un email con un destinatario
	public static boolean sendEmailAdjunto(String to, String subject, String bodyContent) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart("related");
			//parte html
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en html
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);

			// agregando la parte de la imagen

			//File sigFile = new File(UtilEmail.class.getResource("bannerCorreo.jpg").getPath());
			File sigFile = new File(Constantes.getInstancia().IMG_LOGO_REDUCIDO);
			byte[] bytes = read(sigFile);
			MimeBodyPart attachment = new MimeBodyPart();
			attachment.setFileName(sigFile.getName());
			ByteArrayDataSource src = new ByteArrayDataSource(bytes, "image/jpg");
			attachment.setDataHandler(new DataHandler(src));
			attachment.setContentID("<banner>");
			attachment.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(attachment);
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("problemas en el envio de email, " + e.getMessage());
			//return false;
		}
	}

	public static byte[] read(File file) throws IOException {

		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte[] buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			ios = new FileInputStream(file);
			int read = 0;
			while ((read = ios.read(buffer)) != -1) {
				ous.write(buffer, 0, read);
			}
		} finally {
			try {
				if (ous != null)
					ous.close();
			} catch (IOException e) {
			}

			try {
				if (ios != null)
					ios.close();
			} catch (IOException e) {
			}
		}
		return ous.toByteArray();
	}

}
