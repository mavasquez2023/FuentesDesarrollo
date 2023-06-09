package cl.laaraucana.ventafullweb.quartz;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.ventafullweb.singleton.CotizacionUFSingleton;
import cl.laaraucana.ventafullweb.ws.ClienteCotizacion;
import cl.laaraucana.ventafullweb.ws.ClienteCotizacionImpl;

public class AsyncProcessVentaFullWeb {

	private static final Logger logger = Logger.getLogger(AsyncProcessVentaFullWeb.class);
	
	@Scheduled(cron = "${cronExpression}")
	public void asyncProcess() {		
		
		ClienteCotizacion clienteCotizacion = new ClienteCotizacionImpl();
		
		
		try {
			logger.info("Ejecutando Crontab Obtener Uf del dia.");
			int UF_hoy = clienteCotizacion.getRespuestaCotizacion();
			CotizacionUFSingleton.getInstance().setCotizacionUf(UF_hoy);
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
