package cl.laaraucana.mandatopublico.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cl.laaraucana.mandatopublico.ibatis.dao.CuentaDao;
import cl.laaraucana.mandatopublico.ibatis.dao.CuentaDaoImpl;
import cl.laaraucana.mandatopublico.ibatis.vo.RegMandatoPublicoVo;
import cl.laaraucana.mandatopublico.services.ReporteService;


@Controller
public class ReportController {

	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	private ReporteService reporteService;

	CuentaDao dao = new CuentaDaoImpl();

	@RequestMapping(value = { "/reporte.do" }, method = RequestMethod.GET)
	public String report(ModelMap model, @RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String rut = (String) request.getSession().getAttribute("rut");

			RegMandatoPublicoVo vo = new RegMandatoPublicoVo();

			vo.setRut(Long.parseLong(rut.split("-")[0]));
			vo.setDv(rut.split("-")[1]);
			vo.setFecha(new Date());
			vo.setTipo("vigente");

			dao.insertMandatoPublico(vo);

			logger.debug("insertado registro rut " + rut);

			logger.info("Generando PDF mandato id: " + id);
			reporteService.generarReport(request, response, Long.parseLong(id));

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "lista_mandato";
	}

	@RequestMapping(value = { "/reporteRev.do" }, method = RequestMethod.GET)
	public String reportRev(ModelMap model, @RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String rut = (String) request.getSession().getAttribute("rut");

			RegMandatoPublicoVo vo = new RegMandatoPublicoVo();

			vo.setRut(Long.parseLong(rut.split("-")[0]));
			vo.setDv(rut.split("-")[1]);
			vo.setFecha(new Date());
			vo.setTipo("revocado");

			dao.insertMandatoPublico(vo);

			logger.debug("insertado registro rut " + rut);

			logger.info("Generando PDF mandato revocado id: " + id);
			reporteService.generarReportRevocado(request, response, Long.parseLong(id));

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "lista_mandato";
	}

}
