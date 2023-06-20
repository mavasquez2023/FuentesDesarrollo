package cl.laaraucana.diferimientoEspecial.controller;

import java.util.Date;
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

import cl.laaraucana.diferimientoEspecial.entities.BitaEspecialEntiti;
import cl.laaraucana.diferimientoEspecial.entities.CreditoEntiti;
import cl.laaraucana.diferimientoEspecial.model.CreditoVo;
import cl.laaraucana.diferimientoEspecial.reportDao.ReportDao;
import cl.laaraucana.diferimientoEspecial.services.BitaEspecialService;
import cl.laaraucana.diferimientoEspecial.services.MailService;
import cl.laaraucana.diferimientoEspecial.services.ReporteService;
import cl.laaraucana.diferimientoEspecial.util.Utils;

 

@Controller
public class ReporteController {

	private static final Logger logger = Logger.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reportService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BitaEspecialService bitaService;

	@Autowired
	private ReportDao dao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/report.do" }, method = RequestMethod.POST)
	public void report(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			boolean grabado = false;

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			List<CreditoEntiti> cre = (List<CreditoEntiti>) request.getSession().getAttribute("credito");

			for (CreditoEntiti cred : cre) {

				BitaEspecialEntiti en = new BitaEspecialEntiti();

				en.setAutorizado("SI");
				en.setCorreo(credito.getEmail());
				en.setDvAfi(credito.getRutCliente().replace(".", "").split("-")[1]);
				en.setDvEmp(cred.getDvEmpresa());
				en.setFecAutorizacion(new Date());
				en.setFecVencActual(cred.getFechaVencActual());
				en.setFecVencNuevo(cred.getFechaVencNuevo());
				en.setFoliocre(cred.getFolioCredito());
				en.setMontocuota(cred.getMontoCuota());
				en.setSerie(Utils.seriex(credito.getSerie()));
				en.setNumcuota(Integer.parseInt(String.valueOf(cred.getCuotaDiferir())));
				en.setRutAfi(Long.parseLong(credito.getRutCliente().replace(".", "").split("-")[0]));
				en.setRutEmp(cred.getRutEmpresa());

				if (bitaService.findAllByNotAutorized(cred.getFolioCredito(),
						Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() > 0) {

					BitaEspecialEntiti bitacora = bitaService.findAllByNotAutorized(cred.getFolioCredito(),
							Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).get(0);

					bitacora.setAutorizado("SI");
					bitacora.setCorreo(credito.getEmail());
					bitacora.setFecAutorizacion(new Date());
				
					bitaService.update(bitacora);

					logger.debug("Datos updateados con folio " + en.getFoliocre());
					
					grabado = true;
					
				} else {
					if (bitaService.findAllByAutorized(cred.getFolioCredito(),
							Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() == 0) {

						bitaService.save(en);

						grabado = true;

						logger.debug("Datos guardados con folio " + en.getFoliocre());
					}
				}
			}

			if (grabado) {

				String ruta = reportService.generarReport(request, response, credito, dao.getConnection());
				
				logger.debug("Email: " + credito.getEmail());

				if (!credito.getEmail().trim().equals("")) {

					mailService.sendEmail(credito.getEmail(), "Autorización de diferimiento de cuotas - La Araucana",
							Utils.emailCliente(credito), credito.getRutCliente(), ruta);

				}

			}

			request.getSession().invalidate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

		}

	}

	@RequestMapping(value = { "/descargar.do" }, method = RequestMethod.POST)
	public void step4(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request, HttpServletResponse response) {

		try {

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			String email= (String)request.getSession().getAttribute("emailDescarga");
			String ruta = reportService.generarReport(request, response, credito, dao.getConnection());

			logger.debug("Email: " + form.getEmail());
			
			if (email != null && !email.equals("")) {

				mailService.sendEmail(email, "Autorización de diferimiento de cuotas - La Araucana",
						Utils.emailCliente(credito), credito.getRutCliente(), ruta);

			}

			request.getSession().invalidate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 4 ", e);

		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/rechazo.do" }, method = RequestMethod.POST)
	public String denied(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request, HttpServletResponse response) {

		try { 
			
			
			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			List<CreditoEntiti> cre = (List<CreditoEntiti>) request.getSession().getAttribute("credito");

			for (CreditoEntiti cred : cre) {

				BitaEspecialEntiti en = new BitaEspecialEntiti();

				en.setAutorizado("NO");
				en.setCorreo(form.getEmailRechazo());
				en.setDvAfi(credito.getRutCliente().replace(".", "").split("-")[1]);
				en.setDvEmp(cred.getDvEmpresa());
				en.setFecAutorizacion(new Date());
				en.setFecVencActual(cred.getFechaVencActual());
				en.setFecVencNuevo(cred.getFechaVencNuevo());
				en.setFoliocre(cred.getFolioCredito());
				en.setMontocuota(cred.getMontoCuota());
				en.setSerie(Utils.seriex(credito.getSerie()));
				en.setNumcuota(Integer.parseInt(String.valueOf(cred.getCuotaDiferir())));
				en.setRutAfi(Long.parseLong(credito.getRutCliente().replace(".", "").split("-")[0]));
				en.setRutEmp(cred.getRutEmpresa());

				if (bitaService.findAllByAutorized(cred.getFolioCredito(),
						Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() == 0) {

					bitaService.save(en);

					logger.debug("Datos guardados con folio " + en.getFoliocre());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso rechazo ", e);

		}

		return "proceso-rechazo";

	}

}
