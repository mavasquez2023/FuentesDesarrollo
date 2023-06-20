package cl.laaraucana.copagocredito.controller;

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

import cl.laaraucana.copagocredito.entities.BitacoraEntiti;
import cl.laaraucana.copagocredito.entities.CreditoEntiti;
import cl.laaraucana.copagocredito.model.CreditoVo;
import cl.laaraucana.copagocredito.model.FolioVo;
import cl.laaraucana.copagocredito.reportDao.ReportDao;
import cl.laaraucana.copagocredito.services.BitacoraService;
import cl.laaraucana.copagocredito.services.CreditoService;
import cl.laaraucana.copagocredito.services.MailService;
import cl.laaraucana.copagocredito.services.ReporteService;
import cl.laaraucana.copagocredito.util.Utils;

@Controller
public class ReporteController {

	private static final Logger logger = Logger.getLogger(ReporteController.class);

	@Autowired
	private ReporteService reportService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BitacoraService bitaService;

	@Autowired
	private CreditoService creService;

	@Autowired
	private ReportDao dao;

	@RequestMapping(value = { "/report.do" }, method = RequestMethod.POST)
	public String report(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			if(credito==null){
				return "login";
			}
			//Se genera PDF para descargar por página a cliente
			String ruta = reportService.generarReport(request, response, credito, dao.getConnection(), true);

			/*if (credito!=null && !credito.getCorreo().trim().equals("") && ruta!=null) {

				mailService.sendEmail(credito.getCorreo(), "Autorización Programa Copago de Crédito - La Araucana",
						Utils.emailCliente(credito), credito.getRutCliente(), ruta);

			}*/


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

		}
		return null;
	}

	@RequestMapping(value = { "/descargar.do" }, method = RequestMethod.POST)
	public String step4(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			if(credito==null){
				return "login";
			}
			String email = (String) request.getSession().getAttribute("emailDescarga");

			String ruta = reportService.generarReport(request, response, credito, dao.getConnection(), true);

			if (email != null && !email.equals("")) {
				logger.info("Se envia mail con Autorización a " + email);
				mailService.sendEmail(email, "Autorización Programa Copago de Crédito - La Araucana",
						Utils.emailCliente(credito), credito.getRutCliente(), ruta);

			}


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 4 ", e);

		}
		return null;
	}

	@RequestMapping(value = { "/rechazo.do" }, method = RequestMethod.POST)
	public String denied(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			if(credito==null){
				return "login";
			}
			List<CreditoEntiti> cre = creService.findAllByRut(Long.parseLong(credito.getRutCliente().split("-")[0]));

			for (CreditoEntiti cred : cre) {

				BitacoraEntiti en = new BitacoraEntiti();

				en.setAutorizado("NO");
				en.setCorreo(form.getEmailRechazo());
				en.setDvCliente(credito.getRutCliente().split("-")[1]);
				en.setFecha(new Date());
				en.setnCredito(cred.getnCredito());
				en.setValorCuota(cred.getValorCuota());
				en.setNcuota(cred.getNcuota());
				en.setRutCliente(cred.getRutCliente());
				en.setMontoBeneficio(cred.getMontoBeneficio());

				bitaService.save(en);

				logger.debug("Credito Rechazo RUT " + cred.getRutCliente() + " guardados con folio " + en.getnCredito());

			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso rechazo ", e);

		}

		return "proceso-rechazo";

	}

}
