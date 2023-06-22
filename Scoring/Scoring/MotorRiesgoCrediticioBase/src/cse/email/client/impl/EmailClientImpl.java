package cse.email.client.impl;

import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import cse.email.client.EmailClient;
import cse.model.service.impl.CondicionesOtorgamientoServiceImpl;

public class EmailClientImpl implements EmailClient {

	private static Logger logger = Logger.getLogger(EmailClient.class.getName());
	
	public boolean sendMail(String message, String subject, String from, String to, Properties p) {

		Session session = Session.getDefaultInstance(p, null);
		MimeMessage mess = new MimeMessage(session);

		StringTokenizer tokenizer = new StringTokenizer(to, ",");

		try {
			// Desde que direccion
			mess.setFrom(new InternetAddress(from));
			// Hacia que direccion(es)
			while (tokenizer.hasMoreTokens()) {
				mess.addRecipient(RecipientType.TO, new InternetAddress(tokenizer.nextToken()));
			}
			mess.setSubject(subject);
			mess.setText(message);
			Transport.send(mess);

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
