package cl.laaraucana.rendicionpagonomina.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.services.BeneficioService;
import cl.laaraucana.rendicionpagonomina.services.ProcesaArchivoGenericoTEF;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;

public class AsyncCargaBeneficios {
	private static final Logger logger = Logger.getLogger(AsyncCargaBeneficios.class);


	@Autowired
	BeneficioService beneficiosService;
	
	@Scheduled(cron = "${config.cron.quartz.cargaBeneficios}")
	public void asyncProcess() throws Exception {
		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para carga beneficios");
		int totalCabecerasBCI=0;
		int totalCabecerasBES=0;
		
		//CARGANDO BENEFICIOS BCI
		try {
			List<ResumenCargaPagoBenefVo>  resgistrosBeneficios= beneficiosService.leerTablaBeneficios("BCI");
			if(resgistrosBeneficios.size()>0){
				totalCabecerasBCI= beneficiosService.cargarTablasTEF(resgistrosBeneficios, "BCI");
				logger.info("Total Cabeceras cargadas BCI: " + totalCabecerasBCI);
			}
				
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Beneficios para BCI, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		//CARGANDO BENEFICIOS BES
		
		try {
			List<ResumenCargaPagoBenefVo>  resgistrosBeneficios= beneficiosService.leerTablaBeneficios("BES");
			if(resgistrosBeneficios.size()>0){
				totalCabecerasBES= beneficiosService.cargarTablasTEF(resgistrosBeneficios, "BES");
				logger.info("Total Cabeceras cargadas BES: " + totalCabecerasBES);
			}
				
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Beneficios para BES, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		logger.info("Fin proceso automatizado beneficios, total nóminas generadas: " + (totalCabecerasBCI+totalCabecerasBES));
	}
}
