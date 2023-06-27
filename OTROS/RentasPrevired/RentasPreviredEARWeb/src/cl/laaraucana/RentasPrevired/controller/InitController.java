package cl.laaraucana.RentasPrevired.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import cl.laaraucana.RentasPrevired.entities.ArchivoErrorEntity;
import cl.laaraucana.RentasPrevired.entities.PreviredArchivosEntity;
import cl.laaraucana.RentasPrevired.entities.RespuestaAfiliacionEntity;
import cl.laaraucana.RentasPrevired.services.ErrorArchivoService;
import cl.laaraucana.RentasPrevired.services.FTPProcesoService;
import cl.laaraucana.RentasPrevired.services.PreviredProcesoService;
import cl.laaraucana.RentasPrevired.services.RepuestaAfiliadoService;
 

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	MessageSource messageSource;

	@Autowired
	private PreviredProcesoService previredProcesoService;

	@Autowired
	private ErrorArchivoService errorArchivoService;

	@Autowired
	private RepuestaAfiliadoService respuestaAfiliadoService;
	
	 
	@Autowired
	private FTPProcesoService ftpProcesoService;

	@ModelAttribute("erroresResp")
	public List<ArchivoErrorEntity> getErrores() {

		return errorArchivoService.getAll();
	}

	@ModelAttribute("afiliados")
	public List<RespuestaAfiliacionEntity> getAfiliado() {

		return respuestaAfiliadoService.getAllAfi();
	}

	@RequestMapping(value = { "/getError" }, method = RequestMethod.GET)
	public String getError(ModelMap model) {

		actualizar(model);

		model.addAttribute("erroresResp", errorArchivoService.getAll());

		return "previred";

	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage(ModelMap model, HttpServletRequest request) {

		try {

			List<PreviredArchivosEntity> erroresPrevired = previredProcesoService.findByError();

			model.addAttribute("erroresPrevired", erroresPrevired);

		 
			
			HttpSession sesion = request.getSession();
			sesion = request.getSession();

			Principal principal = request.getUserPrincipal();
			String username = principal.getName();

			logger.info("principal " + username);

			sesion.setAttribute("usuario", username);
			// String name= Util.getUser(username).getName() + " " +
			// Util.getUser(username).getFirstName();
			// model.addAttribute("nameuser", name);
			model.addAttribute("nameuser", username);

		} catch (Exception e) {

			logger.error("Error login ", e);
		}

		return "previred";

	}

	@RequestMapping(value = { "/procesarFTP" }, method = RequestMethod.GET)
	public String processFTP(ModelMap model, HttpServletRequest request) {

		try {

			ftpProcesoService.processFTP();

			HttpSession sesion = request.getSession();
			sesion = request.getSession();

			String usuario = (String) sesion.getAttribute("usuario");

			model.addAttribute("nameuser", usuario);

			actualizar(model);
			getAfiliado();

		} catch (Exception e) {

			logger.error("Error al ejecutar el proceso", e);
		}

		return "previred";

	}

	@RequestMapping(value = { "/actualizar" }, method = RequestMethod.GET)
	public String actualizar(ModelMap model) {

		List<PreviredArchivosEntity> erroresPrevired = previredProcesoService.findByError();

		model.addAttribute("erroresPrevired", erroresPrevired);

		return "previred";

	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String inPage() {

		return "previred";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		// session.removeAttribute("userInfo");
		session.invalidate();
		// session = null;
		response.sendRedirect("ibm_security_logout?logoutExitPage=login.jsp");
		return null;

	}

}
