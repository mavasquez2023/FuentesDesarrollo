package cl.araucana.spl.util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailSender {
	private static final Logger log = Logger.getLogger(MailSender.class);
	static ResourceBundle mailResources=ResourceBundle.getBundle("cl.araucana.spl.resources.mail");
	
	public static void sendError(String mensaje, Exception ex) {
		Renderer render = new Renderer();
		StringBuffer mailBody = new StringBuffer();
		mailBody.append(getString("email.body"));
		mailBody.append("\n");
		mailBody.append("\n");
		mailBody.append("Fecha - Hora: ");
		mailBody.append(render.formatDatetime(new Date()));
		mailBody.append("\n");
		mailBody.append("Nota: ");
		mailBody.append(mensaje);
		mailBody.append("\n");
		mailBody.append("Error: ");
		//mailBody.append(ex.getMessage());
		mailBody.append(MailSender.printStackTrace(ex));
		mailBody.append("\n");
		
		StringBuffer mailSubject = new StringBuffer();
		mailSubject.append(getString("email.subject"));
		MailSender.sendMail(mailBody.toString(), mailSubject.toString());
	}	
	
	
	public static void sendError(String mensaje, Exception ex, String idTransaccion) {
		Renderer render = new Renderer();
		StringBuffer mailBody = new StringBuffer();
		mailBody.append(getString("email.body"));
		mailBody.append("\n");
		mailBody.append("\n");
		mailBody.append("Fecha - Hora: ");
		mailBody.append(render.formatDatetime(new Date()));
		mailBody.append("\n");
		mailBody.append("ID Transaccion (tabla interna): ");
		mailBody.append(idTransaccion);
		mailBody.append("\n");
		mailBody.append("Nota: ");
		mailBody.append(mensaje);
		mailBody.append("\n");
		mailBody.append("Error: ");
		//mailBody.append(ex.getMessage());
		mailBody.append(MailSender.printStackTrace(ex));
		mailBody.append("\n");
		
		StringBuffer mailSubject = new StringBuffer();
		mailSubject.append(getString("email.subject"));
		
		MailSender.sendMail(mailBody.toString(), mailSubject.toString());
		
	}

	public static void sendError(String mensaje, Exception ex, String ordenCompra, String moduloOrigen) {
		sendError(mensaje, ex, ordenCompra, moduloOrigen, null);
	}

	public static void sendError(String mensaje, Exception ex, String ordenCompra, String moduloOrigen, String banco) {
		Renderer render = new Renderer();
		StringBuffer mailBody = new StringBuffer();
		mailBody.append(getString("email.body"));
		mailBody.append("\n");
		mailBody.append("\n");
		mailBody.append("Fecha - Hora: ");
		mailBody.append(render.formatDatetime(new Date()));
		mailBody.append("\n");
		mailBody.append("Orden Compra: ");
		mailBody.append(ordenCompra);
		mailBody.append("\n");
		mailBody.append("Origen: ");
		mailBody.append(moduloOrigen);
		mailBody.append("\n");

		if (banco != null) {
			mailBody.append("Banco: ");
			mailBody.append(banco);
			mailBody.append("\n");
		}

		mailBody.append("Nota: ");
		mailBody.append(mensaje);
		mailBody.append("\n");
		mailBody.append("Error: ");
		//mailBody.append(ex.getMessage());
		mailBody.append(MailSender.printStackTrace(ex));
		mailBody.append("\n");
		
		StringBuffer mailSubject = new StringBuffer();
		mailSubject.append(getString("email.subject"));
		MailSender.sendMail(mailBody.toString(), mailSubject.toString());
	}
	
	/**
	 * Envia mail.
	 * Si se detecta un mail invalido se prosigue con los otros envios. 
	 * @param cuerpoMsg
	 * @param subjectMsg
	 */
	private static void sendMail(String cuerpoMsg, String subjectMsg) {
		
		Transport transport = null;
		try{
			cuerpoMsg = cuerpoMsg + ("\n" + getString("email.firma") + "\n");
			Session session = Session.getDefaultInstance(new Properties(), null);

			List destinatarios = getDestinatarios(getString("email.destinatario"), ',');
			for (int i=0; i<=destinatarios.size()-1; i++){
				try {					
					String destinatario = (String)destinatarios.get(i);
					log.info("Se enviara mail al destinatario: " + destinatario);
	
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(getString("email.remitente")));
					
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
					
					message.setSubject(subjectMsg);
					message.setText(cuerpoMsg);
					message.saveChanges();
					
					transport = session.getTransport(getString("email.protocolo"));
					transport.connect(getString("email.servidor"), getString("email.usuario"), getString("email.password"));
					transport.sendMessage(message, message.getAllRecipients());
					log.info("Mail enviado con exito");
					
				} catch (Exception e) {
					if (e instanceof SendFailedException) {
						SendFailedException sfe =  (SendFailedException) e;
						Address[] invalid = sfe.getInvalidAddresses();
						if  (invalid != null) {
							log.error("Mail invalido: " + invalid[0]);
						}					
					} else {
						throw e;
					}
				}
			}
			
		}catch (Exception e) {
			log.error("Error al enviar un e-mail: " + e.getMessage(), e);
		} finally {
			if (transport!=null) {
				try {
					transport.close();
				} catch (Exception ef) {
					log.error("No es posible cerrar Transport" + ef.getMessage());
				}
			}
				
		}
	}

	
//	private static void sendMail(String cuerpoMsg, String subjectMsg) {
//		try{
//			cuerpoMsg = cuerpoMsg + ("\n" + getString("email.firma") + "\n");
//			Session session = Session.getDefaultInstance(new Properties(), null);
//			
//			MimeMessage message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(getString("email.remitente")));
//			List destinatarios = getDestinatarios(getString("email.destinatario"), ',');
//			
//			for (int i=0; i<=destinatarios.size()-1; i++){
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)destinatarios.get(i)));
//			}
//			message.setSubject(subjectMsg);
//			message.setText(cuerpoMsg);
//			message.saveChanges();
//			
//			Transport transport = session.getTransport(getString("email.protocolo"));
//			transport.connect(getString("email.servidor"), getString("email.usuario"), getString("email.password"));
//			transport.sendMessage(message, message.getAllRecipients());
//			transport.close();
//			
//			
//		}catch (Exception e) {
//			log.error("Error al enviar un e-mail: " + e.getMessage(), e);
//		}
//	}	
	
	
	private static List getDestinatarios(String destinatario, char separator){
		log.info("Destinatario de las propiedades: " + destinatario);
		List destinatarios = new ArrayList();
		String auxDestinatario = "";
		for (int i=0; i<=destinatario.length()-1; i++){
			if ( destinatario.charAt(i) == separator){
				if ( !"".equals(auxDestinatario) ){ 
					destinatarios.add(auxDestinatario.trim());
				}
				auxDestinatario = "";
			}else auxDestinatario = auxDestinatario + destinatario.charAt(i); 
		}
		if ( !"".equals(auxDestinatario) ) destinatarios.add(auxDestinatario.trim());
		return destinatarios;
	}
	
	private static String getString(String key) {
		return mailResources.getString(key);
	}
	private static String printStackTrace(Throwable exception) {
		java.io.StringWriter sw = new java.io.StringWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
}
