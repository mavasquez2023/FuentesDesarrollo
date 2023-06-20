package cl.laaraucana.ventaremota.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import cl.laaraucana.ventaremota.services.CreditoService;

@Service
public class AsyncCaducar {

	private static final Logger logger = Logger.getLogger(AsyncCaducar.class);

	@Autowired
	private CreditoService credService;

	@Scheduled(cron = "${cronExpression}")
	public void caducar() {

		try {

			logger.debug("start cron...");

			credService.caducar();
			
		 
			logger.debug("end Cron...");

		} catch (

		Exception e) {
			// TODO: handle exception

			logger.error("Error cron ", e);

		}

	}
}
