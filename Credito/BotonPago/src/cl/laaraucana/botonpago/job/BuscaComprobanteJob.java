package cl.laaraucana.botonpago.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.laaraucana.botonpago.web.manager.ManagerTesoreriaYCupon;

public class BuscaComprobanteJob implements Job {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * El metodo execute, realiza la llamada al generador de correos de alerta
	 */
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			log.info("---------------------> INICIO DEL JOB DE BUSCA COMPROBANTE");
			/**
			 * actualiza los cupones generados por caja y que se encuentren cursados en tesoreria
			 * @throws Exception
			 */
			int n = ManagerTesoreriaYCupon.cursaCuponesPorCajaDesdeTesoreria();
			log.info("se cursaron " + n + " cupones");
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		log.info("---------------------> FIN DEL JOB BUSCA COMPROBANTE");

	}
}
