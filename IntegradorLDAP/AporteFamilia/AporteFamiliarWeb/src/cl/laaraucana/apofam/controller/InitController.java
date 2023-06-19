package cl.laaraucana.apofam.controller;


import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.apofam.entities.Cargas;
import cl.laaraucana.apofam.services.BitacoraService;
import cl.laaraucana.apofam.services.CargasService;
import cl.laaraucana.apofam.services.MailService;
import cl.laaraucana.apofam.services.ReporteService;
import cl.laaraucana.apofam.util.Configuraciones;
import cl.laaraucana.apofam.util.Utils;
import cl.laaraucana.apofam.vo.DescargaVO;


@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);


	@Autowired
	private BitacoraService bitacoraService;

	@Autowired
	private CargasService cargasService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReporteService reporteService;



	@RequestMapping(value = { "/ejecutivo.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return "ejecutivo";
	}


	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String inicio(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.info("Ingresando Aporte Cliente");
			request.getSession().setAttribute("rol", "cliente");
			String rut_usuario = "";

			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut_usuario = principal.getName();
				logger.info("rut: " + rut_usuario);
				
				int rut= Integer.parseInt(rut_usuario.split("-")[0]);
				//Consultando Datos de cargas de afiliado
				Cargas data= cargasService.findByRut(rut);
				if(data!= null){
					request.getSession().setAttribute("data", data);
					return "descarga";
				}else{
					return "sindata";
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Aporte Familiar ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
		}

		return "error";
	}
	
	@RequestMapping(value = { "/buscarAfiliado.do" }, method = RequestMethod.POST)
	public String buscarAfiliado(ModelMap model, @ModelAttribute DescargaVO form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			logger.info("Buscando Aporte Cliente");
			request.getSession().setAttribute("rol", "ejecutivo");
			request.getSession().setAttribute("errorMsg", "");
			
			String rut_usuario = form.getRut().replaceAll("\\.", "");
			logger.info("rut: " + rut_usuario);

			int rut= Integer.parseInt(rut_usuario.split("-")[0]);
			//Consultando Datos de cargas de afiliado
			Cargas data= cargasService.findByRut(rut);
			request.getSession().setAttribute("data", data);
			
			if(data==null){
				request.getSession().setAttribute("errorMsg", "rut_error");
			}

			return "ejecutivo";
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Aporte Familiar ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
		}

		return "error";
	}


	@RequestMapping(value = { "/descargar.do" }, method = RequestMethod.POST)
	public String descargar(ModelMap model, @ModelAttribute DescargaVO form, HttpServletRequest request,
			HttpServletResponse response) {

		Cargas data_afiliado = (Cargas) request.getSession().getAttribute("data");
		String rol= (String)request.getSession().getAttribute("rol");
		try {
			String opcion= form.getOpcion();
			String email= form.getEmail();
			request.getSession().setAttribute("opcion", opcion);
			logger.info("Ingresando descarga");
			String path= Configuraciones.getConfig("aporte.familiar.carpeta");
			
			
			if(opcion.equals("email")){
				logger.info("Generando PDF para enviar por correo");
				String rutaPDF= reporteService.generarReport(data_afiliado, path, null);
				logger.info("Enviando certificado a  " + email);
				mailService.sendEmailClie(email, Configuraciones.getConfig("mail.asunto.cliente"),
						Utils.emailCliente(data_afiliado.getNombreAfiliado()), data_afiliado.getRutAfiliado()+"-" + data_afiliado.getDvAfiliado(), rutaPDF);
			}else{
				logger.info("Generando PDF para descargar");
				//descarga por web
				String rutaPDF= reporteService.generarReport(data_afiliado, path, response);
			}


			// Insertar Bitácora
			logger.info("Insertando bitácora Aporte Familiar");
			bitacoraService.insertBitacora(data_afiliado, rol);
			
			//Cuando se descarga el PDF no toma el return pero si es por email si.
			if(opcion.equals("email")){
				if(rol.equals("ejecutivo")){
					return "ejecutivo";
				}else{
					return "finalizar";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Aporte ", e);
			request.getSession().setAttribute("data", data_afiliado);
			return "error";
		}

		return null;
	}
	
	@RequestMapping(value = { "/finalizar.do" }, method = RequestMethod.GET)
	public String finalizar(HttpServletRequest request, HttpServletResponse response) {
		String rol= (String)request.getSession().getAttribute("rol");
		if(rol!= null && rol.equals("ejecutivo")){
			return "ejecutivo";
		}else {
			return "finalizar"; 
		}
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "soporte";
		}

		return null;
	}

}
