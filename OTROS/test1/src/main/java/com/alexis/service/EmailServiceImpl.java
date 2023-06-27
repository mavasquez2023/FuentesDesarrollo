package com.alexis.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements MailService{

	
	private final Properties properties = new Properties();
	
	//private String password;
 
	private Session session;
 
	private void init() {
		properties.put("mail.smtp.ssl.trust", "hrlxdeveloper.com");
		properties.put("mail.smtp.host", "hrlxdeveloper.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",25);
		properties.put("mail.smtp.mail.sender","alexis.mendez@hrlxdeveloper.com");
		properties.put("mail.smtp.user", "alexis.mendez@hrlxdeveloper.com");
		properties.put("mail.smtp.auth", "true");
 
		session = Session.getDefaultInstance(properties);
	}
	
	
	public void sendMail(String email, String password, String nombre) {
		
		
		    String mensaje = "<span style='font-family: 'Courier New', Courier, monospace; font-size: 20px; color: dargb(85, 85, 85);'>Estimado " + nombre + " su nuevo password es <b>" + password + "</b></span>";
	 
			init();
			try{
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				message.setSubject("Su password");
				message.setContent(mensaje, "text/html; charset=utf-8");
				Transport t = session.getTransport("smtp");
				t.connect((String)properties.get("mail.smtp.user"), "Alexis2539");
				t.sendMessage(message, message.getAllRecipients());
				t.close();
			}catch (MessagingException me){
	                        
				me.printStackTrace();
			
		}
		
	}

}
