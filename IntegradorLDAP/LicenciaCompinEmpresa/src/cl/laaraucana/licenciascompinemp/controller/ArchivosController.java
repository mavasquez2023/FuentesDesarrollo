package cl.laaraucana.licenciascompinemp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.licenciascompinemp.entities.RegistroLicencias;
import cl.laaraucana.licenciascompinemp.services.MailService;
import cl.laaraucana.licenciascompinemp.services.RegistroLicenciasService;
import cl.laaraucana.licenciascompinemp.util.Configuraciones;
import cl.laaraucana.licenciascompinemp.util.Utils;
import cl.laaraucana.licenciascompinemp.vo.FicherosVo;

@Controller
public class ArchivosController {

	private static final Logger logger = Logger.getLogger(ArchivosController.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private RegistroLicenciasService regService;

	@RequestMapping(value = { "/files.do" }, method = RequestMethod.POST)
	public String step4(@ModelAttribute FicherosVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String path = Configuraciones.getConfig("conpin.carpeta");

			byte[] renta4 = null;
			byte[] renta5 = null;
			byte[] renta6 = null;
			byte[] medica = null;
			byte[] zonac = null;
			byte[] renta1 = null;
			byte[] renta2 = null;
			byte[] renta3 = null;

			String sucursal = (String) request.getSession().getAttribute("sucursal");
			String folio = (String) request.getSession().getAttribute("folio");
			String rut = (String) request.getSession().getAttribute("rut");

			File file = new File(path + sucursal.trim());

			if (!file.exists()) {

				file.mkdir();
			}

			File file2 = new File(
					path + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_" + folio);
			logger.info("Ruta a guardar archivos: " + path + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_" + folio);
			if (!file2.exists()) {

				file2.mkdir();
			}

			medica = form.getMedica().getBytes();
			zonac = form.getZonac().getBytes();
			renta1 = form.getRenta1().getBytes();
			renta2 = form.getRenta2().getBytes();
			renta3 = form.getRenta3().getBytes();

			String esMaternal = "N";
			if (form.isMaternal()) {
				renta4 = form.getRenta4().getBytes();
				renta5 = form.getRenta5().getBytes();
				renta6 = form.getRenta6().getBytes();
				esMaternal = "S";
			}

			if (medica.length > 0) {
				String nombre_archivo=  "\\" + rut.replace(".", "").split("-")[0] + "_" + folio
						+ "_Licencia." + Utils.getExtencion(form.getMedica().getOriginalFilename());
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, medica);
			}
			if (zonac.length > 0) {
				String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_" + folio
						+ "_Autorización." + Utils.getExtencion(form.getZonac().getOriginalFilename());
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, zonac);
			}
			if (renta1.length > 0) {
				String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_" + folio
						+ "_Liq1." + Utils.getExtencion(form.getRenta1().getOriginalFilename());
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta1);
			}
			if (renta2.length > 0) {
				String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_" + folio
						+ "_Liq2." + Utils.getExtencion(form.getRenta2().getOriginalFilename());
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta2);
			}
			if (renta3.length > 0) {
				String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_" + folio
						+ "_Liq3." + Utils.getExtencion(form.getRenta3().getOriginalFilename());
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta3);
			}
			if (form.isMaternal()) {

				if (renta4.length > 0) {
					String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_"
							+ folio + "_Liq4." + Utils.getExtencion(form.getRenta4().getOriginalFilename());
					logger.info("Guardando archivo: "  + nombre_archivo);
					Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta4);
				}
				if (renta5.length > 0) {
					String nombre_archivo= "\\" + rut.replace(".", "").split("-")[0] + "_"
							+ folio + "_Liq5." + Utils.getExtencion(form.getRenta5().getOriginalFilename());
					logger.info("Guardando archivo: "  + nombre_archivo);
					Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta5);
				}
				if (renta6.length > 0) {
					String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_"
							+ folio + "_Liq6." + Utils.getExtencion(form.getRenta6().getOriginalFilename());
					logger.info("Guardando archivo: "  + nombre_archivo);
					Utils.descargar(file2.getAbsolutePath().trim(), nombre_archivo, renta6);
				}
			}

			RegistroLicencias reg = (RegistroLicencias) request.getSession().getAttribute("registroLicencias");
			reg.setEsMaternal(esMaternal);
			request.setAttribute("registroLicencias", reg);

		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

		return "paso-paso4";
	}

	@RequestMapping(value = { "/files-add.do" }, method = RequestMethod.POST)
	public String step5(@ModelAttribute FicherosVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String path = Configuraciones.getConfig("conpin.carpeta");

			byte[] adicional = null;

			String sucursal = (String) request.getSession().getAttribute("sucursal");
			String folio = (String) request.getSession().getAttribute("folio");
			String rut = (String) request.getSession().getAttribute("rut");

			File file = new File(path + "\\" + sucursal.trim());

			if (!file.exists()) {

				file.mkdir();
			}

			File file2 = new File(
					path + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_" + folio);
			logger.info("Ruta a guardar archivo adicional: " + path + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_" + folio);
			if (!file2.exists()) {

				file2.mkdir();
			}

			adicional = form.getAdicional().getBytes();

			List<String> archivos = new ArrayList<String>();

			if ((List<String>) request.getSession().getAttribute("archivos") != null) {

				archivos = (List<String>) request.getSession().getAttribute("archivos");
			}

			if (adicional.length > 0) {
				String nombre_archivo="\\" + rut.replace(".", "").split("-")[0] + "_" + form.getAdicional().getOriginalFilename();
				logger.info("Guardando archivo: "  + nombre_archivo);
				Utils.descargar(file2.getAbsolutePath().trim(),
						nombre_archivo,
						adicional);

				if (!archivos.contains(form.getAdicional().getOriginalFilename())) {

					archivos.add(form.getAdicional().getOriginalFilename());
					logger.info("Cantidad archivos acumulados: " + archivos.size() );
				}
			}

			request.getSession().setAttribute("archivos", archivos);

			List<String> temp = (List<String>) request.getSession().getAttribute("archivos");

			model.addAttribute("files", temp);

		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

		return "paso-paso4";
	}

	@RequestMapping(value = { "/success.do" }, method = RequestMethod.POST)
	public String stepSuccess(Model model, HttpServletRequest request, HttpServletResponse response) {

		try {

			String correo = (String) request.getSession().getAttribute("correo");
			String sucursal = (String) request.getSession().getAttribute("sucursal");
			String folio = (String) request.getSession().getAttribute("folio");
			String rut = (String) request.getSession().getAttribute("rut");
			String rutemp = (String) request.getSession().getAttribute("rutemp");
			String razonSocial = ((Map<String, String>) request.getSession().getAttribute("empresas")).get(rutemp);
			String empresa = rutemp + " " + razonSocial;

			String pathCorreo = Configuraciones.getConfig("correo.carpeta");

			RegistroLicencias reg = (RegistroLicencias) request.getSession().getAttribute("registroLicencias");
			regService.save(reg);

			logger.info("Datos grabados para folio " + reg.getFolioLicencia());

			pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_"
					+ folio + "\\";
			logger.info("Ruta a dejar PDF: +  pathCorreo");
			
			// Se envía mail a encargado empresa
			logger.info("Se envía correo a encargado empresa a : " + reg.getEmail());
			mailService.sendEmail(reg.getEmail(),
					"Ingreso de Licencia Médica " + rut.replace(".", "").replace("-", "") + "-" + folio,
					Utils.bodyEbcargadoEmpresa());
			// (folio, pathCorreo, rut.replace(".", ""), reg.getTelefono(), reg.getEmail(),
			// empresa));

			// Se envía mail a Ejecutivo
			if (correo != null && !correo.equals("")) {
				logger.info("Se envía correo ejecutivo  a : " + correo);
				mailService.sendEmail(correo,
						"Notificación de recepción de licencia: " + rut.replace(".", "").replace("-", "") + "-" + folio,
						Utils.getbodyEjec(folio, rut, pathCorreo, String.valueOf(reg.getId()), reg.getTelefono(),
								reg.getEmail(), empresa));
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "registro-exito";
	}

}
