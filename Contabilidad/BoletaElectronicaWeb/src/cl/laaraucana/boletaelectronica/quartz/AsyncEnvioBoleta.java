package cl.laaraucana.boletaelectronica.quartz;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;
import cl.laaraucana.boletaelectronica.services.AceptaService;
import cl.laaraucana.boletaelectronica.services.BaseServices;
import cl.laaraucana.boletaelectronica.services.MigrateService;
import cl.laaraucana.boletaelectronica.services.OrigenBoletaService;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

@Service
public class AsyncEnvioBoleta {

	private static final Logger logger = Logger.getLogger(AsyncEnvioBoleta.class);

	@Autowired
	private OrigenBoletaService origenBoletaService;

	@Autowired
	private BaseServices baseService;

	@Autowired
	private AceptaService aceptaService;

	@Autowired
	private MigrateService migrateService;

	@Scheduled(cron = "${cronExpression}")
	public void enviarBoleta() {
		try {
			logger.info("Cron boleta starting...");
			//Se buscan en tabla origen DTF630 todos las boletas no emitidas (numbol=0)
			List<OrigenBoleta> origenMain = origenBoletaService.findAllNoEmitidas();
			logger.info("Se han encontrado " + origenMain.size() + " boletas sin emitir.");
			for (OrigenBoleta element : origenMain) {
				//Se graba cabecera y detalles por cada boleta origen
				migrateService.migrate(String.valueOf(element.getFOLIO()));
			}
			//Se buscan todas las cabeceras con estado 0
			List<BoletaBase> base = baseService.getBoleta(0);
			if (base.size() > 0) {
				//se manda a Acepta todas las boletas encontradas de una vez y se genera una lista
				logger.info("Se invoca webServices acepta, a enviar " + base.size() + " boletas");
				List<OrigenBoletaVo> origenListEmitidas = aceptaService.wsAcepta(base);
				if(origenListEmitidas == null) {
					logger.error("Error en servicio web ACEPTA");
				}
				else if (origenListEmitidas.size() > 0) {
					//Por cada cabecera se busca en la lista de ws acepta el mismo folio y se actualiza datos de cabecera y de boleta origen
					for (BoletaBase cabe : base) {
						for (OrigenBoletaVo origenBoletaVo : origenListEmitidas) {
							if (cabe.getFOLDOC() == Integer.parseInt(origenBoletaVo.getFOLIO())) {
								logger.info("Actualizando folio " + cabe.getFOLDOC() + " con número boleta: " + origenBoletaVo.getNUMBOL());
								cabe.setURLACEPTA(origenBoletaVo.getUrlDte());
								cabe.setESTADO(1);
								cabe.setNUMBOL(Long.parseLong(origenBoletaVo.getNUMBOL()));
								//Se actualiza tabla de origen DTF630
								origenBoletaService.updateOrigen(Long.parseLong(origenBoletaVo.getNUMBOL()),
										Integer.parseInt(origenBoletaVo.getFOLIO() + ""));
								logger.info("Se actualizó tabla origen DTF630");
								//Se actualiza cabecera BOLECABE
								baseService.updateBase(cabe);
								logger.info("Se actualizó tabla cabecera BOLECABE");
							}
						}
					}
				}
			}
			logger.info("Cron boleta ends...");
		} catch (
		Exception e) {
			logger.error("Error cron ", e);
		}
	}
}
