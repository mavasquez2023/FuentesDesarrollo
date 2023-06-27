package cl.araucana.lme.jcrontab;



import org.apache.log4j.Logger;
import cl.araucana.lme.job.JobControllerService;
import cl.araucana.lme.svc.exception.SvcException;
import cl.araucana.lme.util.BdConstants;
import cl.araucana.lme.util.EndPointUtil;
import cl.araucana.util.email.Email;



public class CronDevolucionValidacionLME {
	private static Logger log = Logger.getLogger(CronDevolucionValidacionLME.class);
	public static void main(String[] args) throws Exception {
		String mailerror="";
		try {
			BdConstants.getInstance().cargarParametros();
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			//Se revisa si está activa la ejecución de cronta
			boolean estado_cronta= instanciaEndPoint.isEstado();
			System.out.println(" Estado de cronta:" + estado_cronta);
			log.info("Estado de cronta:" + estado_cronta);
			
			if(estado_cronta){
				
				//Se rescata lista de correo usuarios en caso de error
				//mailerror = mailProperties.getString("app.mail.usuario.error");
				
				// reinicia errores
				instanciaEndPoint.reCargarParametrosErrores();
				
				log.info("Ejecutando Cronta DEVOLUCIÓN y VALIDACIÓN");
				long inicio = System.currentTimeMillis();
				
				JobControllerService job= new JobControllerService();
				job.consumirEstadosLME();
				
				long termino = System.currentTimeMillis();
				log.info("TERMINO Cronta DEVOLUCIÓN y VALIDACIÓN ["+ (termino-inicio) + " Milisegundos]");
			}
		}catch (SvcException e) {
			log.error("Problemas en cargar parametros proceso CronDevolucionValidacionLME, mensaje:", e);
			e.printStackTrace();
			Email mail= new Email(BdConstants.getInstance().MAIL_SESSION);
			mail.sendEmail(BdConstants.getInstance().DESTINO_CORREO_NOTIF, BdConstants.getInstance().ASUNTO_CORREO_NOTIF, BdConstants.getInstance().CUERPO_CORREO_NOTIF);
		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronDevolucionValidacionLME, mensaje:", e);
			e.printStackTrace();
			//EnviaMail.enviarMail("Error en creación de cuenta LDAP. ",mailerror, null,FormatoMail.obtenerTextoMailLdapCrontaError(e.getMessage()));
			Email mail= new Email(BdConstants.getInstance().MAIL_SESSION);
			mail.sendEmail(BdConstants.getInstance().DESTINO_CORREO_NOTIF, BdConstants.getInstance().ASUNTO_CORREO_NOTIF, BdConstants.getInstance().CUERPO_CORREO_NOTIF);
		}
	}
}