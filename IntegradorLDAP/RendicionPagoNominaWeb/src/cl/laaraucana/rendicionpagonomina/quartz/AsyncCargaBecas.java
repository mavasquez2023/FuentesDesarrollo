package cl.laaraucana.rendicionpagonomina.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBecasVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.services.BecasService;
import cl.laaraucana.rendicionpagonomina.services.BeneficioService;
import cl.laaraucana.rendicionpagonomina.services.ProcesaArchivoGenericoTEF;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;

public class AsyncCargaBecas {
	private static final Logger logger = Logger.getLogger(AsyncCargaBecas.class);


	@Autowired
	BecasService becasService;
	
	@Scheduled(cron = "${config.cron.quartz.cargaBecas}")
	public void asyncProcess() throws Exception {
		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para carga Becas");
		int totalCabecerasBES=0;
		
		//CARGANDO BECAS BES
		
		try {
			List<ResumenCargaPagoBecasVo>  resgistrosBecas= becasService.leerTablaBecas("BES");
			if(resgistrosBecas.size()>0){
				becasService.cargarDatosmandato(resgistrosBecas);
				totalCabecerasBES= becasService.cargarTablasTEF(resgistrosBecas, "BES");
				logger.info("Total Cabeceras cargadas Becas BES: " + totalCabecerasBES);
			}else{
				logger.info("No hay registros para procesar");
			}
				
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Becas para BES, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		logger.info("Fin proceso automatizado becas, total nóminas generadas: " + (totalCabecerasBES));
	}
}
