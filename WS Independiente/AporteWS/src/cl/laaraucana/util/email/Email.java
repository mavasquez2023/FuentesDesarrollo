package cl.laaraucana.util.email;

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

import org.apache.log4j.Logger;

public class Email {
	Logger log = Logger.getLogger(this.getClass());
	private String mailSession;
	
	public Email(String mailSession){
		this.mailSession = mailSession;
	}
	
	/**
	 * Envía email con archivos adjuntos
	 * @param mailSession
	 * @param to
	 * @param subject
	 * @param bodyContent
	 * @param transf
	 * @param clienteVO
	 * @param certAmortOut
	 * @return
	 * @throws Exception
	 */
	public boolean sendEmail(String to, String subject, String bodyContent, List<AdjuntoVO> adjuntos) throws Exception {
		Context env = null;
		env = (Context) new InitialContext();
		Session session = (Session) env.lookup(mailSession);
		try {
			Message msg = new MimeMessage(session);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));
			Multipart multipart = new MimeMultipart();
			//parte html
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(bodyContent, "text/html");// cuerpo del email en html
			multipart.addBodyPart(htmlPart);
			msg.setContent(multipart);
			
			//Adjuntar archivos
			if(adjuntos!=null){
				for (AdjuntoVO archivo : adjuntos) {
					BodyPart adjunto4 = new MimeBodyPart();
					adjunto4.setDataHandler(new DataHandler(new ByteArrayDataSource(archivo.getArchivo(), archivo.getTipoArchivo())));
					adjunto4.setFileName(archivo.getNombreArchivo());
					multipart.addBodyPart(adjunto4);
				}
			}
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			log.error("Error al enviar email: ", e);
			throw new Exception("Problemas en el envio de email, "+ e.getMessage());
		}
	}

	public void setMailSession(String mailSession) {
		this.mailSession = mailSession;
	}
}
