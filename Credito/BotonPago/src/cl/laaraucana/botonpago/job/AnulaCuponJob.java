package cl.laaraucana.botonpago.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.laaraucana.botonpago.web.manager.ManagerTesoreriaYCupon;
import cl.laaraucana.botonpago.web.utils.Constantes;

public class AnulaCuponJob implements Job {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * se va a buscar a la tabla de cupones los cupones en estado generados, 
	 * luego se recorren y se anulan con el servicio de tesoreria y a la vez se anulan en la tabla propia.
	 */
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {
			log.info("---------------------> INICIO DEL JOB DE ANULA CUPON");
			/**
			 * cambia el estado de tesoreria segun el estado que se encuentra en tesoreria 
			 * y en la tabla de cupones.
			 */
			int nteso = ManagerTesoreriaYCupon.AnulaEstadoTesoreria();
			log.info("se anularon " + nteso + " cupones de tesoreria");

			/**
			 * cambia el estado del cupon segun otro estado.
			 */
			int nbpag = ManagerTesoreriaYCupon.cambiaEstadoCuponSegunEstado(Constantes.getInstancia().ESTADO_IMPRESO, Constantes.getInstancia().ESTADO_ANULADO,
					Constantes.getInstancia().GLOSA_ANULA_DEMONIO);
			log.info("se anularon " + nbpag + " cupones en BPAGF002");

		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		log.info("---------------------> FIN DEL JOB ANULA CUPON");

	}
}
