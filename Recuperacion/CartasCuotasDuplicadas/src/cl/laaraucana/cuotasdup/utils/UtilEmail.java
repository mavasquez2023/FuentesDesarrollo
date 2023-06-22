package cl.laaraucana.cuotasdup.utils;

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

//clase que permite enviar email
public class UtilEmail{
	private static Session mailSession;
	
	public static void init() throws NamingException {
		String jndiSessionEmail = ParamConfig.RES_CONFIG.getString("mail.session");
		Context env = (Context) new InitialContext();
		mailSession = (Session) env.lookup(jndiSessionEmail);
	}

	// este metodo permite enviar un email con mas de un destinatario,
	// y más de una copia
	public static boolean sendEmail(List<String> to, List<String> cc, String subject,
			String bodyContent, String filename) throws Exception {
		init();
		try {
			Message msg = new MimeMessage(mailSession);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			for (int i = 0; i < to.size(); i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						(String) to.get(i)));
			}
			if(cc!=null){
				for (int i = 0; i < cc.size(); i++) {
					msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
							(String) cc.get(i)));
				}
			}
			Multipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(bodyContent, "text/html");// cuerpo del email en html
			//Setear mensaje de texto
			multipart.addBodyPart(messageBodyPart);
			
			if(filename!=null){
				//agregar attachment
				BodyPart attachPart = new MimeBodyPart();
				DataSource source = new FileDataSource(filename);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(filename);
				multipart.addBodyPart(attachPart);
			} 
			
			//agregar logo
			{
				BodyPart logoPart = new MimeBodyPart();
				DataSource source = new FileDataSource(UtilEmail.class.getResource("/img/logo_reducido.jpg").getFile());
				logoPart.setDataHandler(new DataHandler(source));
				logoPart.setHeader("Content-ID", "<imagen>");
				multipart.addBodyPart(logoPart);
				
			}
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
 }
