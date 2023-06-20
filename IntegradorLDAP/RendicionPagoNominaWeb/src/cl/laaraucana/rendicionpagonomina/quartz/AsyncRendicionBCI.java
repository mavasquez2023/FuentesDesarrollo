package cl.laaraucana.rendicionpagonomina.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;

public class AsyncRendicionBCI {
	private static final Logger logger = Logger.getLogger(AsyncRendicionBCI.class);


	@Autowired
	ProcesaRendicionesBCI procesaRendicionesBCIService;
	
	@Scheduled(cron = "${config.cron.bci.rendicion}")
	public void asyncProcess() throws Exception {
		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para rendicion ");
		int resultadoOperacion = 0;
		try {
			resultadoOperacion = procesaRendicionesBCIService.execute();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
		}
		logger.debug("En el proceso automatizado, estado operacion para rencicón bci:["+resultadoOperacion+"]");
	}
}
