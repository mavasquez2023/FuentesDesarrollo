package cl.laaraucana.rendicionpagonomina.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.services.ProcesaArchivoGenericoTEF;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;

public class AsyncCargaBCI {
	private static final Logger logger = Logger.getLogger(AsyncCargaBCI.class);


	@Autowired
	ProcesaArchivoGenericoTEF procesaArchivoGenericoTEF;
	
	@Scheduled(cron = "${config.cron.quartz.carga}")
	public void asyncProcess() throws Exception {
		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para carga ");
		int resultadoOperacion = 0;
		try {
			resultadoOperacion = procesaArchivoGenericoTEF.loadData();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
		}
		logger.debug("En el proceso automatizado, estado operacion para carga :["+resultadoOperacion+"]");
	}
}
