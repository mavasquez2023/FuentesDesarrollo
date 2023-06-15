package ztest;

import java.util.ArrayList;

import cl.liv.mail.impl.MailImpl;

public class TestMail {

	public static void main(String[] args) {
		
		MailImpl.enviarMail("mailer_wf", "Prueba", "luisibacache@gmail.com", "TEst", "test", new ArrayList(), new ArrayList(), true);
		/*
		String subject = PropertiesUtil.configuracionesMail.getString("mail.config.envio.nomina.subject");
		subject = UtilesComunes.reemplazarVariables(subject);
		MailImpl.enviarMail("mailer_wf", PropertiesUtil.configuracionesMail.getString("mail.smtp.from"), "luisibacache@gmail.com", subject, Utiles.obtenerTextoMailNominas(new MiHashMap(),"trabajadores"), new ArrayList(),new ArrayList(), true);
		*/
		
	}
	
}
