/**
 * 
 */
package cl.laaraucana.resultadonrp.business;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.util.Constantes;
import cl.recursos.EnviarMail;

/**
 * @author IBM Software Factory
 *
 */
public class EnviarMailProcesos {
	protected static Logger logger = Logger.getLogger(EnviarMailProcesos.class);
//	Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
	public static boolean enviarMail( String[] mailDestinatarios, String periodo, String proceso, boolean exito, String adjunto) {

		try {
			String host= Constantes.MAIL_PROPERTIES.getString("smtp.host");
			String port= Constantes.MAIL_PROPERTIES.getString("smtp.port");
			String user= Constantes.MAIL_PROPERTIES.getString("smtp.user");
			String password= Constantes.MAIL_PROPERTIES.getString("smtp.password");
			String from= Constantes.MAIL_PROPERTIES.getString("smtp.from");
			EnviarMail mail= new EnviarMail(host, port, user, password);

			String estado_exito= Constantes.MAIL_PROPERTIES.getString("mail.resumen.estado.exito");
			String estado_error= Constantes.MAIL_PROPERTIES.getString("mail.resumen.estado.error");
			String mensaje_exito= Constantes.MAIL_PROPERTIES.getString("mail.resumen.mensaje.exito");
			String mensaje_error= Constantes.MAIL_PROPERTIES.getString("mail.resumen.mensaje.error");
			String estado= exito?estado_exito:estado_error;
			String mensaje= exito?mensaje_exito:mensaje_error;

			String subject= Constantes.MAIL_PROPERTIES.getString("mail.resumen.subject").replaceAll("#proceso#", proceso).replaceAll("#periodo#", periodo).replaceAll("#estado#", estado);
			String body= Constantes.MAIL_PROPERTIES.getString("mail.resumen.formato").replaceAll("#proceso#", proceso).replaceAll("#periodo#", periodo).replaceAll("#estado#", estado).replaceAll("#mensaje#", mensaje);
			if(adjunto!=null){
				mail.attach(adjunto);
			}
			mail.mailTo(from, mailDestinatarios, null, null , subject, body.toString());

			logger.info(".............. EMAIL GENERADO .................... " );
			return true;
		}catch(Exception e) {
			logger.error("CAI EN MAIL, mensaje: " + e.getMessage() );
			e.printStackTrace();
			return false;
		}
	}
}
