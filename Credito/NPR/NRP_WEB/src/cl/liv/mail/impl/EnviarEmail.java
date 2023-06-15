package cl.liv.mail.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.PropertiesUtil;
 
public class EnviarEmail {
 
	
	public static boolean enviar(final ArrayList destinatario, final String subject, final String mensaje, final ArrayList attachments,final ArrayList imagenes, final String responsable, final boolean eliminarAttachments) {
		new Thread(new Runnable() {
			
			public void run() {
				String mensajeStr = mensaje;
				// TODO Auto-generated method stub
				
				UtilLogWorkflow.debug("adjuntos: "+ attachments);
				
				final String username = PropertiesUtil.configuracionesMail.getString("mail.smtp.user");
				final String password = PropertiesUtil.configuracionesMail.getString("mail.smtp.password");
				
				Properties props = new Properties();
				props.put("mail.smtp.auth", PropertiesUtil.configuracionesMail.getString("mail.smtp.auth"));
				props.put("mail.smtp.starttls.enable", PropertiesUtil.configuracionesMail.getString("mail.smtp.starttls.enable"));
				props.put("mail.smtp.host", PropertiesUtil.configuracionesMail.getString("mail.smtp.server"));
				props.put("mail.smtp.port", PropertiesUtil.configuracionesMail.getString("mail.smtp.port"));
		 
				Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(username, password);
					}
				  });
		 
				try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(PropertiesUtil.configuracionesMail.getString("mail.smtp.from"), PropertiesUtil.configuracionesMail.getString("mail.smtp.from.title")));
					
					
					Address[] destinatariosTMP = new Address[destinatario.size()];
					
					int contador = 0;
					for(int i=0; i< destinatario.size() ; i++){
						try{
							if( ((HashMap)destinatario.get(i)).get("email") != null && ((HashMap)destinatario.get(i)).get("email").toString().length() >0){
								destinatariosTMP[contador] = new InternetAddress(((HashMap)destinatario.get(i)).get("email")+"");
								contador++;
							}
						} catch(Exception e){
							e.printStackTrace();
							
						}
					}
					
					
					String footer = PropertiesUtil.configuracionesMail.getString("mail.smtp.firma.footer");
					
					Address[] destinatarios = new Address[contador];
					
					for (int i=0; i< contador ; i++) {
						destinatarios[i] = destinatariosTMP[i];
					}
					
					message.setRecipients(Message.RecipientType.TO,destinatarios);
					message.setSubject(new String(subject.getBytes("utf-8"), "utf-8"));
					message.setText(mensajeStr);
		 
					
					Multipart multipart = new MimeMultipart();
					
					
					
					
					
					
					mensajeStr = mensajeStr + footer;
					
					MimeBodyPart messageBody = new MimeBodyPart();
					messageBody.setContent(mensajeStr,"text/html");
			        multipart.addBodyPart(messageBody);
			        
			      
			        if(attachments != null && attachments.size()>0 ){
						for(int i=0; i< attachments.size(); i++){
							HashMap attachment = (HashMap)attachments.get(i);
							if(attachment.get("ruta_file") != null && (attachment.get("ruta_file")+"").length() > 0){
									MimeBodyPart messageBodyPart = new MimeBodyPart();
									messageBodyPart = new MimeBodyPart();
									DataSource source = new FileDataSource(attachment.get("ruta_file")+"");
									messageBodyPart.setDataHandler(new DataHandler(source));
									messageBodyPart.setFileName(source.getName());
									multipart.addBodyPart(messageBodyPart);
							}
						}
					}
					if(imagenes != null && imagenes.size() > 0){
						for(int i=0; i<imagenes.size(); i++){
							HashMap imagen = (HashMap)imagenes.get(i);
							UtilLogWorkflow.debug("procesando imagenes "+ imagenes);
							if(imagen.get("ruta_file") != null && (imagen.get("ruta_file")+"").length() > 0){

								UtilLogWorkflow.debug("procesando imagen "+ imagen);
								MimeBodyPart messageBodyPart = new MimeBodyPart();
								messageBodyPart = new MimeBodyPart();
								DataSource source = new FileDataSource(imagen.get("ruta_file")+"");
								messageBodyPart.setDataHandler(new DataHandler(source));
								messageBodyPart.setHeader("Content-ID", "<"+imagen.get("id_image")+">");
								messageBodyPart.setFileName(source.getName());
								multipart.addBodyPart(messageBodyPart);
							}
						}
					}
			        
			        message.setContent(multipart);
					UtilLogWorkflow.debug("a enviar...");
								
					Transport.send(message);
										if(eliminarAttachments){
						if(attachments != null && attachments.size()>0){
							for(int i=0; i< attachments.size(); i++){
								HashMap attachment = (HashMap)attachments.get(i);
								if(attachment.get("ruta_file") != null && (attachment.get("ruta_file")+"").length() > 0){
										new File(attachment.get("ruta_file").toString()).delete();
								}
							}
						}
					}

					
					
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();
		return false;
	}


	
}
