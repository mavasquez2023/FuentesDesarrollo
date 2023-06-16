package cl.araucana.cotfonasa.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

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

import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;
import cl.araucana.cotfonasa.vo.MailVO;


public class MailConexion {
//    private Logger log = Logger.getLogger(MailConexionImpl.class);
    
	private String SESSION_MAIL;
	private String SMTP_AUTH_USER;
	

    public MailConexion() {
		super();
		
		
		try {
			
			Properties props = new Properties();
			props.load(MailConexion.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			
			SESSION_MAIL= props.getProperty("SESSION_MAIL");
			SMTP_AUTH_USER = props.getProperty("SMTP_AUTH_USER");
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
		
	}





	public void sendMail(MailVO mail) throws MessagingException, NamingException{
        try {
//            log.debug("Estableciendo la conexion con el proveedor de correos");
            InitialContext ic = new InitialContext();

            Session session = (Session) ic.lookup(SESSION_MAIL.trim());
           
            session.setDebug(true);
            
//            log.debug("Creado el mail para ser enviado");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_AUTH_USER));
           
            
            for(int i = 0; i < mail.getPara().length; i++){
                if(!mail.getPara()[i].trim().equals("")){
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getPara()[i].trim()));
                }
            }
            if(mail.getCopia() != null){
                for(int i = 0; i < mail.getCopia().length; i++){
                    if(!mail.getCopia()[i].trim().equals("")){
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail.getCopia()[i].trim()));
                    }
                }
            }
            
            message.setSubject(mail.getAsunto());
            message.setContent(mail.getMensaje(), "text/html");
            Transport.send(message, message.getAllRecipients());
            
            /*
            MimeMultipart contMail = new MimeMultipart();
            BodyPart cuerpoMail = new MimeBodyPart();
            cuerpoMail.setText(mail.getMensaje());
            contMail.addBodyPart(cuerpoMail);
            
            /*if(mail.getAdjuntos() != null){
                for(int i = 0; i < mail.getAdjuntos().length && i < 10; i++){
                    File file = mail.getAdjuntos()[i];
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
                    adjunto.setFileName(file.getName());
                    contMail.addBodyPart(adjunto);
                }
            }*/

        } catch (MessagingException ex) {
//            log.error("Codigo 0081: Ocurrio un problema al enviar el email");
            ex.printStackTrace();
            
        } catch (NamingException e) {
            
//            log.error("Codigo 0082: Ocurrio un problema al enviar el email");
            e.printStackTrace();
           
        }
    }

}