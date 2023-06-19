package cl.laaraucana.certificadodiferimiento.controller;

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

import cl.laaraucana.certificadodiferimiento.entities.BitacoraEntiti;
import cl.laaraucana.certificadodiferimiento.entities.CreditoEntiti;
import cl.laaraucana.certificadodiferimiento.model.CreditoVo;
import cl.laaraucana.certificadodiferimiento.model.FolioVo;
import cl.laaraucana.certificadodiferimiento.services.BitacoraService;
import cl.laaraucana.certificadodiferimiento.services.CreditoService;
import cl.laaraucana.certificadodiferimiento.services.MailService;
import cl.laaraucana.certificadodiferimiento.services.ReporteService;
import cl.laaraucana.certificadodiferimiento.util.Utils;
import cl.laaraucana.certificadodiferimiento.vo.AfiliadoVo;
import cl.laaraucana.rendicionpagonomina.reportDao.ReportDao;

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
	private ReportDao dao;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/report.do" }, method = RequestMethod.POST)
	public void report(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			boolean grabado = false;

			List<CreditoEntiti> cre = (List<CreditoEntiti>) request.getSession().getAttribute("credito");
			AfiliadoVo dataAfiliado= (AfiliadoVo)request.getSession().getAttribute("data");
			
			for (CreditoEntiti cred : cre) {

				BitacoraEntiti en = new BitacoraEntiti();
				logger.info("Autorizado folio: " + cred.getFolioCredito() + ", cuota:" + cred.getCuotaDiferir());
				en.setAutorizado("SI");
				en.setCorreo(dataAfiliado.getEmail());
				en.setRutAfiliado(Long.parseLong(dataAfiliado.getRut().replace(".", "").split("-")[0]));
				en.setDvAfiliado(dataAfiliado.getRut().replace(".", "").split("-")[1]);
				en.setDvEmpresa(cred.getDvEmpresa());
				en.setFechaAutorizacion(new Date());
				en.setFechaVencActual(cred.getFechaVencActual());
				en.setFechaVencNuevo(cred.getFechaVencNuevo());
				en.setFolioCredito(cred.getFolioCredito());
				en.setMontoCuota(cred.getMontoCuota());
				en.setnSerie(Utils.seriex(dataAfiliado.getNumeroSerie()));
				en.setNumCuotaDiferir(Integer.parseInt(String.valueOf(cred.getCuotaDiferir())));
				
				en.setRutEmpresa(cred.getRutEmpresa());
				en.setCelular(dataAfiliado.getCelular());
				en.setCodigoVerificacion(dataAfiliado.getCodigoVerificacion());
				en.setIpacceso(dataAfiliado.getIp());

				if (bitaService.findAllByNotAutorized(cred.getFolioCredito(),
						Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() > 0) {

					BitacoraEntiti bitacora = bitaService.findAllByNotAutorized(cred.getFolioCredito(),
							Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).get(0);

					bitacora.setAutorizado("SI");
					bitacora.setCorreo(dataAfiliado.getEmail());
					bitacora.setFechaAutorizacion(new Date());
				
					bitaService.update(bitacora);

					logger.debug("Datos updateados con folio " + en.getFolioCredito());
					
					grabado = true;
					
				} else {
					if (bitaService.findAllByAutorized(cred.getFolioCredito(),
							Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() == 0) {

						bitaService.save(en);

						grabado = true;

						logger.debug("Datos guardados con folio " + en.getFolioCredito());
					}
				}
			}

			if (grabado) {

				String ruta = reportService.generarReport(request, response, dataAfiliado, dao.getConnection());

				if (!dataAfiliado.getEmail().trim().equals("")) {

					mailService.sendEmail(dataAfiliado.getEmail(), "Autorización de diferimiento de cuotas - La Araucana",
							Utils.emailCliente(dataAfiliado), dataAfiliado.getRut(), ruta);

				}

			}

			request.getSession().invalidate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

		}

	}

	@RequestMapping(value = { "/descargar.do" }, method = RequestMethod.POST)
	public void step4(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			AfiliadoVo dataAfiliado = (AfiliadoVo) request.getSession().getAttribute("data");
			String email= (String)request.getSession().getAttribute("emailDescarga");
			String ruta = reportService.generarReport(request, response, dataAfiliado, dao.getConnection());

			if (email != null && !email.equals("")) {

				mailService.sendEmail(email, "Autorización de diferimiento de cuotas - La Araucana",
						Utils.emailCliente(dataAfiliado), dataAfiliado.getRut(), ruta);

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

				BitacoraEntiti en = new BitacoraEntiti();

				en.setAutorizado("NO");
				en.setCorreo(form.getEmailRechazo());
				en.setDvAfiliado(credito.getRutCliente().replace(".", "").split("-")[1]);
				en.setDvEmpresa(cred.getDvEmpresa());
				en.setFechaAutorizacion(new Date());
				en.setFechaVencActual(cred.getFechaVencActual());
				en.setFechaVencNuevo(cred.getFechaVencNuevo());
				en.setFolioCredito(cred.getFolioCredito());
				en.setMontoCuota(cred.getMontoCuota());
				en.setnSerie(Utils.seriex(credito.getSerie()));
				en.setNumCuotaDiferir(Integer.parseInt(String.valueOf(cred.getCuotaDiferir())));
				en.setRutAfiliado(Long.parseLong(credito.getRutCliente().replace(".", "").split("-")[0]));
				en.setRutEmpresa(cred.getRutEmpresa());

				if (bitaService.findAllByAutorized(cred.getFolioCredito(),
						Integer.parseInt(String.valueOf(cred.getCuotaDiferir()))).size() == 0) {

					bitaService.save(en);

					logger.debug("Datos guardados con folio " + en.getFolioCredito());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso rechazo ", e);

		}

		return "proceso-rechazo";

	}

}
