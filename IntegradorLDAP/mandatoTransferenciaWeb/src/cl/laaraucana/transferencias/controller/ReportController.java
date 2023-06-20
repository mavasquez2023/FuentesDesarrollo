package cl.laaraucana.transferencias.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cl.laaraucana.transferencias.services.MailService;
import cl.laaraucana.transferencias.services.MandatoAS400Service;
import cl.laaraucana.transferencias.services.ReporteService;
import cl.laaraucana.transferencias.util.Utils;
import cl.laaraucana.transferencias.vo.DatosMandatoVo;

@Controller
public class ReportController {

	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	private ReporteService reporteService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private MandatoAS400Service mandatoas400Service;
	
	@RequestMapping(value = { "/report.do" }, method = RequestMethod.POST)
	public void report(ModelMap model, @ModelAttribute DatosMandatoVo form, HttpServletRequest request,
			HttpServletResponse response) {

		DatosMandatoVo mandato = (DatosMandatoVo) request.getSession().getAttribute("datos");

		try {
			String ruta = reporteService.generarReport(request, response, mandato);
			logger.info("Enviando Mandato por correo a " + mandato.getEmail());
			mailService.sendEmailMandato(mandato.getEmail(), "Mandato único para transferencia - La Araucana",
					Utils.emailCliente(mandato), mandato.getRut().replace(".", "").replace("-", ""), ruta);


		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

	}

	@RequestMapping(value = { "/reporte.do" }, method = RequestMethod.GET)
	public String report(ModelMap model, @RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) {

		try {
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
			logger.info("Generando PDF mandato revocado id: " + id);
			reporteService.generarReportRevocado(request, response, Long.parseLong(id));

		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "lista_mandato";
	}
	


}
