package cl.laaraucana.RentasPrevired.async;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cl.laaraucana.RentasPrevired.services.FTPProcesoService;

@Service
public class AsyncSheduledService {

	private static final Logger logger = Logger.getLogger(AsyncSheduledService.class);

	@Autowired
	private FTPProcesoService ftpProcessService;

	@Scheduled(cron = "${cron.expression}")
	public void asyncFtpProcess() {

		try {
			ftpProcessService.processFTP();

		} catch (Exception e) {

			logger.error("Error proceso respuesta FTP", e);
		}
	}
}
