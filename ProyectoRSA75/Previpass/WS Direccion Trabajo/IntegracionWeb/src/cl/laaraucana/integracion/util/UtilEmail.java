package cl.laaraucana.integracion.util;

import java.util.ArrayList;
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

//clase que permite enviar email
public class UtilEmail {

	//este metodo permite enviar un email con mas de un destinatario,
	//y más de una copia
	 public static boolean sendEmail(Session mailSession, ArrayList to,
			 		ArrayList cc, String subject,
			 		String bodyContent)  throws Exception
	 {

		 try
		 {
				Message msg = new MimeMessage(mailSession);
				msg.setSubject(subject);
				msg.setSentDate(new Date());
                for (int i = 0; i < to.size(); i++) {
                    msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String)to.get(i)));
                }
                for (int i = 0; i < cc.size(); i++) {
                    msg.addRecipient(Message.RecipientType.CC, new InternetAddress((String)cc.get(i)));
                }

				Multipart multipart = new MimeMultipart("related");
				BodyPart htmlPart = new MimeBodyPart();
				htmlPart.setContent(bodyContent, "text/html");//cuerpo del email en html
				multipart.addBodyPart(htmlPart);
				msg.setContent(multipart);
				Transport.send(msg);
				System.out.println("Correo enviado exitosamente!!");
				return true;
		 }
		 catch(Exception e){
			 throw new Exception("problemas en el envio de email, "+e.getMessage());
		 }

	 }

	//este metodo permite enviar un email con mas de un destinatario
	public static boolean sendEmail(Session mailSession, ArrayList to,
		 		 String subject,String bodyContent)  throws Exception
	 {

		 try
		 {
			 Message msg = new MimeMessage(mailSession);
			 msg.setSubject(subject);
			 msg.setSentDate(new Date());
			 for (int i = 0; i < to.size(); i++) {
	             msg.addRecipient(Message.RecipientType.TO, new InternetAddress((String)to.get(i)));
	         }

			 Multipart multipart = new MimeMultipart("related");
			 BodyPart htmlPart = new MimeBodyPart();
			 htmlPart.setContent(bodyContent, "text/html");//cuerpo del email en html
			 multipart.addBodyPart(htmlPart);
			 msg.setContent(multipart);
			 Transport.send(msg);
			 System.out.println("Correo enviado exitosamente!!");
			 return true;
		 }
		 catch(Exception e)
		 {
			 throw new Exception("problemas en el envio de email, "+e.getMessage());
		 }
	 }

	  //este metodo permite enviar un email con un destinatario
	 public static boolean sendEmail(Session mailSession, String to,
		 		String subject,	String bodyContent)  throws Exception
	 {

		 try
		 {
			 Message msg = new MimeMessage(mailSession);
			 msg.setSubject(subject);
			 msg.setSentDate(new Date());
			 msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			 Multipart multipart = new MimeMultipart("related");
			 BodyPart htmlPart = new MimeBodyPart();
			 htmlPart.setContent(bodyContent, "text/html");//cuerpo del email en html
			 multipart.addBodyPart(htmlPart);
			 msg.setContent(multipart);
			 Transport.send(msg);
			 return true;
		 }
		 catch(Exception e)
		 {
			 throw new Exception("problemas en el envio de email, "+e.getMessage());
		 }
	 }

 }
