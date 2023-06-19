package cl.araucana.spring.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.araucana.spring.entities.Formulario;
import cl.araucana.spring.services.ClaveEmpresasService;
import cl.araucana.spring.services.MailService;
import cl.araucana.spring.utils.Configuraciones;
import cl.araucana.spring.utils.Utils;

@Controller
@RequestMapping(value = { "/" })
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	//private final ResourceBundle prop = ResourceBundle.getBundle("etc/properties");
	
	@Autowired
	private ClaveEmpresasService claveEmpresasService;

	@Autowired
	private MailService mailService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {

		return "index";

	}

	@RequestMapping(value = "/paso2", method = RequestMethod.POST)
	public String paso2(@ModelAttribute Formulario form1, Model model, HttpServletRequest request) {
		form1.setApePat(Utils.sacaAcentos(form1.getApePat()));
		form1.setApeMat(Utils.sacaAcentos(form1.getApeMat()));
		form1.setNombre(Utils.sacaAcentos(form1.getNombre()));
		request.getSession().setAttribute("paso2", null);
		request.getSession().setAttribute("paso2", form1);

		return "index--paso2";
	}

	@RequestMapping(value = "/paso3", method = RequestMethod.POST)
	public String paso3(@ModelAttribute Formulario form2, Model model, HttpServletRequest request) {
		form2.setNomEmp(Utils.sacaAcentos(form2.getNomEmp()));
		request.getSession().setAttribute("paso3", null);
		request.getSession().setAttribute("paso3", form2);

		return "index--paso3";
	}

	@RequestMapping(value = { "/paso4" }, method = RequestMethod.POST)
	public String paso4(@ModelAttribute Formulario form3, Model model, HttpServletRequest request) {

		try {
			/*if(form3.getFichero().getBytes().length==0){
				request.getSession().setAttribute("formulario", form3);
				return "index--paso3";
			}*/
			Formulario form1 = (Formulario) request.getSession().getAttribute("paso2");
			Formulario form2 = (Formulario) request.getSession().getAttribute("paso3");
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			 
			 
			// model.addAttribute("captcha", prop.get("secretWeb"));

			// String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			// System.out.println(gRecaptchaResponse);

			// boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

			// if (verify) {

			// File file = Utils.grabarFicheroTemporal(formularioBean.getFichero());

			String[] rut = form1.getRut().replace(".", "").split("-");
			String[] rutEmpresa = form2.getRutemp().replace(".", "").split("-");
			String[] rutRepresentante = form3.getRutRepr().replace(".", "").split("-");

			Formulario form = new Formulario();
			form.setArchivoAdj(form3.getFichero().getBytes());
			form.setRut(rut[0]);
			form.setApeMat(form1.getApeMat() == null ? "" : form1.getApeMat());
			form.setApeMatRepr(form3.getApeMatRepr() == null ? "" : Utils.sacaAcentos(form3.getApeMatRepr()));
			form.setApePat(form1.getApePat());
			form.setApePatRepr(Utils.sacaAcentos(form3.getApePatRepr()));
			form.setCelular(form1.getCelular().replaceAll("\\+", ""));
			form.setDv(rut[1]);
			form.setDvEmp(rutEmpresa[1]);
			form.setDvRepr(rutRepresentante[1]);
			form.setEmail(form1.getEmail());
			form.setEmailEmp(form2.getEmailEmp());
			form.setMensaje(form3.getMensaje() == null ? "" : form3.getMensaje());
			form.setNombre(form1.getNombre());
			form.setNomEmp(form2.getNomEmp());
			form.setNomRepr(Utils.sacaAcentos(form3.getNomRepr()));
			form.setRutemp(rutEmpresa[0]);
			form.setRutRepr(rutRepresentante[0]);
			form.setTelefono(form1.getTelefono().replaceAll("\\+", ""));
			form.setTelEmp(String.valueOf(form2.getTelEmp()).trim().equals("") ? null : form2.getTelEmp().replaceAll("\\+", ""));
			form.setFechaEnvio(new Date());

			claveEmpresasService.saveClaveEmpresa(form);

			String ruta = System.getProperty("java.io.tmpdir") + format.format(new Date()) + form.getRutemp() + ".pdf";

			FileUtils.writeByteArrayToFile(new File(ruta), form3.getFichero().getBytes());

			mailService.sendEmail(rut[0] + rut[1], form1.getEmail(), Utils.mensajeCliente(form), ruta);
			String mail_ejecutivo= Configuraciones.getConfig("mail-araucana");
		    mailService.sendEmail(rut[0] + rut[1], mail_ejecutivo, Utils.mensajeSucursal(form, rut[0] + rut[1]),
					ruta);
			// model.addAttribute("archivo",
			// formularioBean.getFichero().getFileItem().getName());

			return "index--exito";

			 
		} catch (Exception e) {
			logger.error("Error en el proceso.", e);

			return "index--error";

		}

	}

	/*
	@RequestMapping(value = { "/getFile" }, method = RequestMethod.GET)
	public void descargarFichero(Model model, HttpServletResponse response) {

		try {

			Properties prop = new Properties();

			prop.load(new FileInputStream(getClass().getResource("/resources/prop/properties.properties").getFile()));

			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-disposition", "attachment; filename=fichero.pdf");
			ServletOutputStream stream = response.getOutputStream();
			FileInputStream input = new FileInputStream(prop.get("file").toString());
			BufferedInputStream buf = new BufferedInputStream(input);
			int readBytes = 0;

			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}

			stream.flush();
			stream.close();
			buf.close();

		} catch (Exception e) {

			logger.error(e.getMessage());
		}

	}
*/
}
