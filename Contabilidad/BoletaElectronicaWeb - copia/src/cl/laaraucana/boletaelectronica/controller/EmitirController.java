package cl.laaraucana.boletaelectronica.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;
import cl.laaraucana.boletaelectronica.services.AceptaService;
import cl.laaraucana.boletaelectronica.services.BaseServices;
import cl.laaraucana.boletaelectronica.services.MigrateService;
import cl.laaraucana.boletaelectronica.services.OrigenBoletaService;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

@Controller
public class EmitirController {

	private static final Logger logger = Logger.getLogger(EmitirController.class);

	@Autowired
	private BaseServices baseService;

	@Autowired
	private AceptaService aceptaService;

	@Autowired
	private MigrateService migrateService;

	@Autowired
	private OrigenBoletaService origenService;

	@RequestMapping(value = { "/emitir.do" }, method = RequestMethod.GET)
	public String emitir(ModelMap model, @ModelAttribute("id") String id, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			if (baseService.getBoletaByNumberAndEstate(Long.parseLong(id), 1).size() == 0) {

				migrateService.migrate(id);

				List<BoletaBase> base = baseService.getBoletaByFolio(Long.parseLong(id));

				List<OrigenBoletaVo> origenListEmitidas = aceptaService.wsAcepta(base);

				if (origenListEmitidas.size() > 0) {

					for (OrigenBoletaVo origenBoletaVo : origenListEmitidas) {

						base.get(0).setURLACEPTA(origenBoletaVo.getUrlDte());
						base.get(0).setESTADO(1);
						base.get(0).setNUMBOL(Long.parseLong(origenBoletaVo.getNUMBOL()));

						origenService.updateOrigen(Long.parseLong(origenBoletaVo.getNUMBOL()),
								Integer.parseInt(origenBoletaVo.getFOLIO() + ""));

						baseService.updateBase(base.get(0));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error al crear el reporte ", e);

		}

		return "redirect:/emitidas.do";
	}

}
