package cl.laaraucana.muvu.quartz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.muvu.services.ProcesosMuvu;


public class AsyncAltasMuvu {

	private static final Logger logger = Logger.getLogger(AsyncAltasMuvu.class);
	
	@Autowired
	private ProcesosMuvu procesosMuvu;

	@Scheduled(cron = "${cronExpression}")
	public void asyncProcess() {

		try {
			logger.info("Ejecutando Cronta Altas Muvu");
			procesosMuvu.procesarAltas(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
