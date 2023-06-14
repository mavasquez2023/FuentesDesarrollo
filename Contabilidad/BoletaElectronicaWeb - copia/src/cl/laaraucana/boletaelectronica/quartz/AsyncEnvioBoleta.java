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

			logger.debug("Cron boleta starting...");

			List<OrigenBoleta> origenMain = origenBoletaService.findAllNoEmitidas();

			for (OrigenBoleta element : origenMain) {

				migrateService.migrate(String.valueOf(element.getFOLIO()));

			}

			List<BoletaBase> base = baseService.getBoleta(0);

			if (base.size() > 0) {

				List<OrigenBoletaVo> origenListEmitidas = aceptaService.wsAcepta(base);

				if (origenListEmitidas.size() > 0) {

					for (BoletaBase cabe : base) {

						for (OrigenBoletaVo origenBoletaVo : origenListEmitidas) {

							if (cabe.getFOLDOC() == Integer.parseInt(origenBoletaVo.getFOLIO())) {

								if (baseService
										.getBoletaByNumberAndEstate(Integer.parseInt(origenBoletaVo.getFOLIO()), 0)
										.size() > 0) {

									cabe.setURLACEPTA(origenBoletaVo.getUrlDte());
									cabe.setESTADO(1);
									cabe.setNUMBOL(Long.parseLong(origenBoletaVo.getNUMBOL()));

									origenBoletaService.updateOrigen(Long.parseLong(origenBoletaVo.getNUMBOL()),
											Integer.parseInt(origenBoletaVo.getFOLIO() + ""));

									baseService.updateBase(cabe);
								}
							}
						}
					}
				}
			}

			logger.debug("Cron boleta ends...");

		} catch (

		Exception e) {
			// TODO: handle exception

			logger.error("Error cron ", e);

		}

	}
}
