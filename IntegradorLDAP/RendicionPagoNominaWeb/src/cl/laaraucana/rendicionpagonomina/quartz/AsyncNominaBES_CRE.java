package cl.laaraucana.rendicionpagonomina.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionBESService;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;

public class AsyncNominaBES_CRE {
	private static final Logger logger = Logger.getLogger(AsyncNominaBES_CRE.class);
	
	@Value("${config.cron.bes.rendicion.credito.convenio}")
	private String convenios;
	
	@Autowired
	private ProcesaRendicionBESService processfileService;

	@Scheduled(cron = "${config.cron.bes.rendicion.credito}")
	public void asyncProcessNomina() {
		
		String estado = Configuraciones.getConfig("cronta.bes.estado");
		logger.info("Cron working, estado cronta BES CREDITO: " + estado);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if(estado.equals("1")){
				logger.info("Cron leyendo convenios: " + convenios);
				//primer parámetro vacío indica que son todas las nóminas, segundo paráemtro indica que no se ejecuta bajo ningún usuario
				processfileService.procesoxNomina("", null, convenios);
				//logger.info("Cron x Fecha, en ejecución...");
				//processfileService.procesoxFecha(sdf.format(new Date()), sdf.format(new Date()), null, convenios);
			}
			// processfileService.sendNomina();

			logger.debug("Cron ends...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error al ejecutar el cron ", e);
		}

	}
}
