package cl.araucana.lme.liq.jcrontab;



import org.apache.log4j.Logger;

import cl.araucana.lme.liq.job.JobControllerLiquidacion;
import cl.araucana.lme.liq.svc.exception.SvcException;
import cl.araucana.lme.liq.util.BdConstantsLiq;
import cl.araucana.lme.liq.util.EndPointUtilLiq;



public class CronLiquidacionLME {
	private static Logger log = Logger.getLogger(CronLiquidacionLME.class);
	public static void main(String[] args) throws Exception {
		String mailerror="";
		try {
			int jo=0;
			BdConstantsLiq.getInstance().cargarParametros();
			EndPointUtilLiq instanciaEndPoint = EndPointUtilLiq.getInstance();
			//Se revisa si est� activa la ejecuci�n de cronta
			boolean estado_cronta= instanciaEndPoint.isEstado();
			System.out.println(" Estado de cronta Liquidaci�n:" + estado_cronta);
			log.info("Estado de cronta Liquidaci�n:" + estado_cronta);
			
			if(estado_cronta){
				
				//Se rescata lista de correo usuarios en caso de error
				//mailerror = mailProperties.getString("app.mail.usuario.error");
				
				// reinicia errores
				instanciaEndPoint.reCargarParametrosErrores();
				
				log.info("Ejecutando Cronta LIQUIDACI�N");
				long inicio = System.currentTimeMillis();
				
				JobControllerLiquidacion job= new JobControllerLiquidacion();
				job.repararLiquidacionesLME();
				
				long termino = System.currentTimeMillis();
				log.info("TERMINO Cronta LIQUIDACI�N ["+ (termino-inicio) + " Milisegundos]");
			}
		}catch (SvcException e) {
				// TODO Bloque catch generado autom�ticamente
				e.printStackTrace();
		} catch (Exception e) {
			log.error("Problemas en ejecuci�n proceso repararLiquidacionesLME, mensaje:", e);
			e.printStackTrace();
			//EnviaMail.enviarMail("Error en creaci�n de cuenta LDAP. ",mailerror, null,FormatoMail.obtenerTextoMailLdapCrontaError(e.getMessage()));
		}
	}
}