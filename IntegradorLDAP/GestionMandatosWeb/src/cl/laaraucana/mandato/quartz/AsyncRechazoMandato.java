/**
 * 
 */
package cl.laaraucana.mandato.quartz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandato.ibatis.vo.RechazoVo;
import cl.laaraucana.mandato.services.MailService;
import cl.laaraucana.mandato.services.MandatoAS400Service;
import cl.laaraucana.mandato.services.RechazoService;
import cl.laaraucana.mandato.util.Utils;


/**
 * @author J-Factory
 *
 */
public class AsyncRechazoMandato {
	private static final Logger logger = Logger.getLogger(AsyncRechazoMandato.class);
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	RechazoService rechazoService;
	
	@Autowired
	private MailService mailService;

	@Scheduled(cron = "${cron.Expression}")
	public void deshabilitarMandato() {

		try {

			logger.info("Cron Rechazo Mandatos...");
			List<RechazoVo> rechazados=  rechazoService.consultaRechazados();
			logger.info("Cantidad rechazos a procesar: " + rechazados.size());
			for (Iterator iterator = rechazados.iterator(); iterator.hasNext();) {
				RechazoVo rechazoVo = (RechazoVo) iterator.next();
				HashMap<String , String> params= new HashMap<String, String>();
				params.put("rutAfiliado", String.valueOf(rechazoVo.getRutAfiliado()));
				params.put("codigoBanco", String.valueOf(rechazoVo.getCodigoBanco()));
				params.put("numeroCuenta", rechazoVo.getNumeroCuenta());
				
				MandatoAS400Vo mandatoxrechazar= mandatoService.consultaMandatoxRechazo(params);
				HashMap<String, Integer> sets= new HashMap<String, Integer>();
				sets.put("rutAfiliado", rechazoVo.getRutAfiliado());
				
				if(mandatoxrechazar!=null){
					mandatoxrechazar.setEstado("1");
					mandatoxrechazar.setObservaciones(rechazoVo.getMotivoRechazo());
					if(mandatoService.rechazaMandato(mandatoxrechazar)){
						sets.put("estado", 1);
						rechazoService.updateRechazoByRut(sets);
						String email_afiliado= mandatoxrechazar.getEmail();
						logger.info("Enviando Mandato Rechazado por correo a " + email_afiliado);
						mailService.sendEmailAdj(email_afiliado.trim(), "Problema en Mandato de transferencia, La Araucana CCAF",
								Utils.emailClienteMandatoRechazo(mandatoxrechazar));
					}
				}else{
					logger.info("Mandato vigente no encontrado para Rut: " + rechazoVo.getRutAfiliado());
					sets.put("estado", -1);
					rechazoService.updateRechazoByRut(sets);
				}
			}
			
			logger.info("Cron Archivo Rechazo Mandatos ends...");
		}catch(Exception e){
			logger.error("Error en Cron Archivo SAP Mandato, mensaje=" + e.getMessage());
			e.printStackTrace();
		}
	}
}
