package cl.laaraucana.muvu.quartz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.muvu.services.ProcesosMuvu;
import cl.laaraucana.muvu.services.ProcesosSura;
import cl.laaraucana.muvu.util.UtilsFecha;

//TODO: renombra a nombre genérico. Require también arreglar referencia en applicationContext.xml.
public class AsyncProcessMuvu {

	private static final Logger logger = Logger.getLogger(AsyncProcessMuvu.class);
	
	@Autowired
	private ProcesosMuvu procesosMuvu;

	@Autowired
	private ProcesosSura procesosSura;
	
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
	
	@Scheduled(cron = "${cronExpresionMensual}")
	public void sincronizacionMensual() {

		try {
			logger.info("Ejecutando Crontab Stock Muvu, Altas y bajas Sura");
			String periodo = UtilsFecha.getFechaPeriodo();
			logger.info("Ejecutando sincronización periodo : "+ periodo);
			// ----------- STOCK MUVU -----------------
			procesosMuvu.procesarStock(periodo);
			procesosMuvu.sincronizacionUsuarios(periodo);
			// ------------------ ALTAS SURA  -------------------------
			procesosSura.procesarAltas(periodo);
			// ------------------ BAJAS SURA  -------------------------
			procesosSura.procesarBajas(periodo);
			// ----------- BAJAS MUVU -----------------
			procesosMuvu.procesarBajas(periodo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
