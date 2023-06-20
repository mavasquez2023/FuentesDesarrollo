package cl.laaraucana.envioFormularioASFAM.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.envioFormularioASFAM.entities.SucAsfam;
import cl.laaraucana.envioFormularioASFAM.entities.RegistroEntiti;
import cl.laaraucana.envioFormularioASFAM.entities.Sucursales;
import cl.laaraucana.envioFormularioASFAM.model.RegistroFormularioVo;
import cl.laaraucana.envioFormularioASFAM.services.SucursalService;
import cl.laaraucana.envioFormularioASFAM.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.envioFormularioASFAM.ws.ClienteInfoAfiliado;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private SucursalService sucService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
			String rut = "";
			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut = principal.getName();
			} else {
				 return "redirect:/exit.do";
			}

			SalidainfoAfiliadoVO salida = null;
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();

			salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut.toUpperCase());

			if (salida.isDeudordirecto() && salida.getNombreCompleto() == null) {
				logger.info(rut + " es Deudor Directo: " + salida.isDeudordirecto());
				return "redirect:/logout.do";
			}

			

			request.getSession().setAttribute("archivos", null);

			request.getSession().setAttribute("rutLdap", rut);

			request.getSession().setAttribute("CRM", salida);

			request.getSession().setAttribute("rut", rut);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}

	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step1(ModelMap model, HttpServletRequest request) {

		try {
			
			List<Sucursales> sucursales = sucService.findAllSucursal();
			request.getSession().setAttribute("sucursales", sucursales);
			request.getSession().setAttribute("pendiente", null);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2";
	}

	@RequestMapping(value = { "/registrar.do" }, method = RequestMethod.POST)
	public String step3(@ModelAttribute RegistroFormularioVo form, Model model, HttpServletRequest request) {

		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

		try {

			request.getSession().setAttribute("folio", form.getFolio());
			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			request.getSession().setAttribute("sucursal", form.getSucursal());
			request.getSession().setAttribute("form", form);

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");

			RegistroEntiti reg = new RegistroEntiti();

			reg.setNomafi(nombre);
			reg.setEmail(form.getEmail());
			reg.setRutAfi(Long.parseLong(rut.replaceAll("\\.", "").split("-")[0]));
			reg.setDvAfi(rut.replaceAll("\\.", "").split("-")[1]);
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setFECCRE(new Date());
			reg.setViaIngreso("A");
			reg.setSucursal(form.getSucursal());

			request.getSession().setAttribute("registroAsfam", reg);
			
			String pendiente= (String)request.getSession().getAttribute("pendiente");
			if(pendiente==null){
				return "paso-paso3";
			}else{
				return "paso-paso4";
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso3 ", e);

			return "registro_error";

		}

		
	}

	@RequestMapping(value = { "/logout.do" }, method = RequestMethod.GET)
	public String closeSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().removeAttribute("datos");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=logout.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}

}
