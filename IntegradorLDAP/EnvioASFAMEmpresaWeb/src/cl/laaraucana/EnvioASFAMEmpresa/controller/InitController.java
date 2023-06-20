package cl.laaraucana.EnvioASFAMEmpresa.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cl.laaraucana.EnvioASFAMEmpresa.entities.RegistroEntiti;
import cl.laaraucana.EnvioASFAMEmpresa.entities.Sucursales;
import cl.laaraucana.EnvioASFAMEmpresa.model.RegistroFormularioVo;
import cl.laaraucana.EnvioASFAMEmpresa.services.SucursalService;
import cl.laaraucana.EnvioASFAMEmpresa.util.EmpresasLDAP;
import cl.laaraucana.EnvioASFAMEmpresa.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.EnvioASFAMEmpresa.ws.ClientBPQueryStatus;


@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private SucursalService sucService;


	@RequestMapping(value = { "/initemp.do" }, method = RequestMethod.GET)
	public String getEmpresas(ModelMap model, HttpServletRequest request) {
		Map<String, String> listamap= null;
		String rolUsuario="";
		try { 
		Principal principal = request.getUserPrincipal();
			if(principal!=null){
				String username= principal.getName();
				listamap= EmpresasLDAP.getEmpresasLDAP(username);
				if(listamap.size()>0){
					rolUsuario="Encargado";
				}
				request.getSession().setAttribute("empresas", listamap);
			}else{
				return "redirect:/exit.do";
			}
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso0";
	}

	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.POST )
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
			String rutempresa= request.getParameter("rutemp");
			request.getSession().setAttribute("rutemp", rutempresa);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.GET)
	public String getInitGET(ModelMap model, HttpServletRequest request) {

		try {
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}

	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step1(ModelMap model, @ModelAttribute RegistroFormularioVo form, HttpServletRequest request) {

		try {

			String rutEmpresa= request.getSession().getAttribute("rutemp").toString().toUpperCase();
			String rut= form.getRut().replaceAll("\\.", "").toUpperCase();
			ClientBPQueryStatus clienteBP= new ClientBPQueryStatus();
			SalidainfoAfiliadoVO salida= clienteBP.obtenerEstadoAfiliacionCRM(rut, rutEmpresa);
			request.getSession().setAttribute("pendiente", null);
			
			if(salida.getEstado()==-1){
				model.addAttribute("errorMsg", "rut_inactivo");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==0){
				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==-9){
				model.addAttribute("errorMsg", "servicio_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}

		

			logger.debug("Nombre " + salida.getNombreCompleto());
			
			List<Sucursales> sucursales = sucService.findAllSucursal();

			request.getSession().setAttribute("sucursales", sucursales);

			request.getSession().setAttribute("nombre", salida.getNombreCompleto());

			request.getSession().setAttribute("rut", form.getRut().toUpperCase());

			form.setNombre(salida.getNombreCompleto());
			request.getSession().setAttribute("form", form);

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
			reg.setRutAfi(Long.parseLong(rut.replace(".", "").split("-")[0]));
			reg.setDvAfi(rut.replace(".", "").split("-")[1]);
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

	
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().setAttribute("telefono", null);
			request.getSession().setAttribute("email", null);
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
