package cl.laaraucana.mandatocesantia.controller;

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

import cl.laaraucana.mandatocesantia.entities.BitacoraEntiti;
import cl.laaraucana.mandatocesantia.model.CesantiaVo;
import cl.laaraucana.mandatocesantia.reportDao.ReportDao;
import cl.laaraucana.mandatocesantia.services.BitacoraService;
import cl.laaraucana.mandatocesantia.services.CreditoService;
import cl.laaraucana.mandatocesantia.services.MailService;
import cl.laaraucana.mandatocesantia.services.ReporteService;
import cl.laaraucana.mandatocesantia.util.Utils;

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

			CesantiaVo cesantia = (CesantiaVo) request.getSession().getAttribute("data");
			if(cesantia==null){
				return "login_ns";
			}
			//Se genera PDF para descargar por página a cliente
			String ruta = reportService.generarReport(request, response, cesantia, dao.getConnection(), true);

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

			CesantiaVo cesantia = (CesantiaVo) request.getSession().getAttribute("data");
			if(cesantia==null){
				return "login_ns";
			}
			String email = (String) request.getSession().getAttribute("emailDescarga");

			String ruta = reportService.generarReport(request, response, cesantia, dao.getConnection(), true);

			if (email != null && !email.equals("")) {
				logger.info("Se envia mail con Autorización a " + email);
				mailService.sendEmail(email, "Autorización Mandato Cesantía - La Araucana",
						Utils.emailCliente(cesantia), cesantia.getRutCliente(), ruta);

			}


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 4 ", e);

		}
		return null;
	}

	@RequestMapping(value = { "/rechazo.do" }, method = RequestMethod.POST)
	public String denied(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			
			CesantiaVo cesantia = (CesantiaVo) request.getSession().getAttribute("data");
			
			if(cesantia==null ){
				return "login";
			}
			
			List<BitacoraEntiti> bitacora= bitaService.findAllByRutBita(Integer.parseInt(cesantia.getRutCliente()));
			if(bitacora== null || bitacora.size()==0){
				cesantia.setEmail(form.getEmail());
				cesantia.setCelular(form.getCelular().replaceAll("\\+", ""));
				cesantia.setTelefono(form.getTelefono().replaceAll("\\+", ""));
				
				//Se graba Bitacora
				logger.info("Se graba bitácora");
				BitacoraEntiti en = new BitacoraEntiti();

				en.setAutorizado("NO");
				en.setRutCliente(Long.parseLong(cesantia.getRutCliente()));
				en.setDvCliente(cesantia.getDvCliente());
				en.setNombre(cesantia.getNombreCliente());
				en.setEmail(cesantia.getEmail());
				en.setCelular(cesantia.getCelular());
				en.setTelefono(cesantia.getTelefono());
				en.setFecha(new Date());
				
				logger.info("Rechazo afiliado  " + cesantia.getRutCliente() + "-" + cesantia.getDvCliente() + " no existe en bitácora, se graba registro");
				bitaService.save(en);
			}
			
			

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso rechazo ", e);

		}

		return "proceso-rechazo";

	}

}
