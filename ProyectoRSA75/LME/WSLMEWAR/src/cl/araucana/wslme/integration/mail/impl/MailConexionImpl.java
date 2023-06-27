package cl.araucana.wslme.integration.mail.impl;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.to.MailTO;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.integration.mail.MailConexion;

public class MailConexionImpl implements MailConexion{
	private Logger log = Logger.getLogger(MailConexionImpl.class);
	
	public Boolean sendMail(MailTO mail) throws WSLMEException{
		try {
			log.info("Estableciendo la conexion con el proveedor de correos");
			InitialContext ic = new InitialContext();
			Session session = (Session) ic.lookup(ConfigUtil.getValorRecursosDeAplicacion("araucana.wslme.mail.datasourcename"));
			session.setDebug(true);
			
			log.info("Creado el mail para ser enviado");
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getDe()));
			
			log.info("Agregando Para");
			for(int i = 0; i < mail.getPara().length; i++){
				if(!mail.getPara()[i].trim().equals("")){
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getPara()[i].trim()));
				}
			}
			if(mail.getConCopia()!=null){
				log.info("Agregando Copy");
				for(int i = 0; i < mail.getConCopia().length; i++){
					if(!mail.getConCopia()[i].trim().equals("")){
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail.getConCopia()[i].trim()));
					}
				}
			}
			log.info("Set Subject");
			message.setSubject(mail.getAsunto());
			
			MimeMultipart contMail = new MimeMultipart();
			BodyPart cuerpoMail = new MimeBodyPart();
			cuerpoMail.setText(mail.getMensaje());
			contMail.addBodyPart(cuerpoMail);
			if(mail.getAdjuntos()!=null){
				for(int i = 0; i < mail.getAdjuntos().length && i < 10; i++){
					File file = mail.getAdjuntos()[i];
					BodyPart adjunto = new MimeBodyPart();
					adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
					adjunto.setFileName(file.getName());
					contMail.addBodyPart(adjunto);
				}
			}
			log.info("Set Content");
			message.setContent(contMail);

			log.info("Enviando mail");
			Transport.send(message, message.getAllRecipients());
		} catch (MessagingException ex) {
			log.error("Codigo 0081: Ocurrio un problema al enviar el email");
			throw new WSLMEException("0121","Error, Ocurrio un problema al enviar el email.");
		} catch (NamingException e) {
			log.error("Codigo 0082: Ocurrio un problema al enviar el email");
			throw new WSLMEException("0121","Error, Ocurrio un problema al enviar el email.");
		}
		return new Boolean(true);
	}

}
