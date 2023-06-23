package cl.laaraucana.capaservicios.util.email;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import cl.laaraucana.capaservicios.util.Constantes;


public class Email {
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
	public static boolean sendEmail(String mailSession, String to, String subject, String bodyContent, List adjuntos) throws Exception {
		Context env = null;
		env = (Context) new InitialContext();
		Session session = (Session) env.lookup(mailSession);
		/*try {*/
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
			// agregando la parte de la imagen
			
			File sigFile = new File(Constantes.RUTA_IMG + "bannerCorreo.jpg");
			byte[] bytes = read(sigFile);
			MimeBodyPart attachment = new MimeBodyPart();
			//System.out.println(sigFile.getName());
			attachment.setFileName(sigFile.getName());
			ByteArrayDataSource src = new ByteArrayDataSource(bytes, AdjuntoVO.IMG_TYPE);
			attachment.setDataHandler(new DataHandler(src));
			attachment.setContentID("<banner>");
			attachment.setDisposition(MimeBodyPart.INLINE);
			multipart.addBodyPart(attachment);
			
			//Adjuntar archivos
			if(adjuntos!=null){
				for(int i=0; i<adjuntos.size();i++){
					AdjuntoVO archivo =  (AdjuntoVO)adjuntos.get(i);
					BodyPart adjunto4 = new MimeBodyPart();
					adjunto4.setDataHandler(new DataHandler(new ByteArrayDataSource(archivo.getArchivo(), archivo.getTipoArchivo())));
					adjunto4.setFileName(archivo.getNombreArchivo());
					multipart.addBodyPart(adjunto4);
				}
			}
			
			msg.setContent(multipart);
			Transport.send(msg);
			return true;
/*		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Problemas en el envio de email, "+ e.getMessage());
			//return false;
		}*/
	}
	
	private static byte[] read(File file) throws IOException {
	    ByteArrayOutputStream ous = null;
	    InputStream ios = null;
	    try {
	        byte[] buffer = new byte[4096];
	        ous = new ByteArrayOutputStream();
	        ios = new FileInputStream(file);
	        int read = 0;
	        while ( (read = ios.read(buffer)) != -1 ) {
	            ous.write(buffer, 0, read);
	        }
	    } finally { 
	        try {
	             if ( ous != null ) 
	                 ous.close();
	        } catch ( IOException e) {
	        }

	        try {
	             if ( ios != null ) 
	                  ios.close();
	        } catch ( IOException e) {
	        }
	    }
	    return ous.toByteArray();
	}

}
