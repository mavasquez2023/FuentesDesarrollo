package cl.laaraucana.dsiniestro.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;
import java.util.List;
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

import cl.araucana.core.util.Rut;
import cl.laaraucana.dsiniestro.entities.Sucursal;
import cl.laaraucana.dsiniestro.entities.RegistroDSiniestro;
import cl.laaraucana.dsiniestro.services.SucursalService;
import cl.laaraucana.dsiniestro.services.MailService;
import cl.laaraucana.dsiniestro.services.RegistroDSiniestroService;
import cl.laaraucana.dsiniestro.util.Utils;
import cl.laaraucana.dsiniestro.vo.RegistroDocPendienteVo;
import cl.laaraucana.dsiniestro.vo.RegistroVo;
import cl.laaraucana.dsiniestro.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.dsiniestro.ws.ClienteInfoAfiliado;
import cl.laaraucana.dsiniestro.ws.Configuraciones;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private SucursalService sucursalService;

	@Autowired
	private RegistroDSiniestroService dsiniestroService;
	
	@Autowired
	private RegistroDSiniestroService regService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
			String rut="";
			Principal principal = request.getUserPrincipal();
			if(principal!=null){
				rut= principal.getName();
			}
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);
			
			Rut rut_format= new Rut(rut.split("-")[0]);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			request.getSession().setAttribute("rut", rut_format.toString());
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}

	@RequestMapping(value = { "paso2.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute RegistroDSiniestro form, HttpServletRequest request) {

		try {

			String opcion= form.getOpcion();
			
			String sucursal = "000";
			List<Sucursal> sucursales = sucursalService.findAll();
			
			model.addAttribute("sucursales", sucursales);

			request.getSession().setAttribute("sucursal", sucursal);
			
			if(opcion.equals("DP")){
				getDataDP(model, form, request);
				return "paso-paso2-DocP";
			}

			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "/volver_paso2.do" }, method = RequestMethod.POST)
	public String getPaso2(ModelMap model, @ModelAttribute RegistroDSiniestro form, HttpServletRequest request) {

		try {			
			String opcion= form.getOpcion();
			String sucursal = (String) request.getSession().getAttribute("sucursal");
			if (sucursal == null) {
				sucursal = "001";
			}

			List<Sucursal> sucursales = sucursalService.findAll();

			model.addAttribute("sucursales", sucursales);

			
			if(opcion.equals("DP")){
				return "index-paso1";
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "paso3DocP.do" }, method = RequestMethod.POST)
	public String getDataDP(ModelMap model, @ModelAttribute RegistroDSiniestro form, HttpServletRequest request) {

		try {

			String rut= (String)request.getSession().getAttribute("rut");
			rut= rut.replace(".", "").split("-")[0];
			List<RegistroDSiniestro> registros= dsiniestroService.findByRut(rut);
			
			String sucursal="000";
			if(registros!=null && registros.size()>0){
				sucursal= String.valueOf(registros.get(0).getIdSucursal());
			}
			List<Sucursal> sucursales = sucursalService.findAll();

			model.addAttribute("sucursales", sucursales);
			
			
			String email="";
			String telefono="";
			request.getSession().removeAttribute("registro_save");
			if(registros!=null && registros.size()>0){
				//se guarda en sesion que existe registro para no volver a guardar si se sube documento pendiente
				request.getSession().setAttribute("registro_save", "1");
				RegistroDSiniestro registro_dsiniestro= registros.get(0);
				request.getSession().setAttribute("registroDSiniestro", registro_dsiniestro);
				sucursal= String.valueOf(registro_dsiniestro.getIdSucursal());
				email= registro_dsiniestro.getEmail();
				telefono= "+56" + registro_dsiniestro.getTelefono();
			}
			
			request.getSession().setAttribute("sucursal", sucursal);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("telefono", telefono);

			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2-DocP";
	}
	

	@RequestMapping(value = { "/registrar.do" }, method = RequestMethod.POST)
	public String step3(@ModelAttribute RegistroVo form, Model model, HttpServletRequest request) {
		try {

			Sucursal sucursal = sucursalService.findBySucursal(form.getSucursal());

			if (sucursal!=null) {
				request.getSession().setAttribute("sucursalVO", sucursal);
			} else {
				return "registro_error";
			}

			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			request.getSession().setAttribute("sucursal", form.getSucursal());

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");
			
			RegistroDSiniestro reg = new RegistroDSiniestro();

			reg.setNombre(nombre);
			reg.setEmail(form.getEmail());
			reg.setRut(rut.replace(".", "").split("-")[0]);
			reg.setDvRut(rut.replace(".", "").split("-")[1]);
			reg.setIdSucursal(sucursal.getCodigoSucursal());
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setFechacre(new Date());
			reg.setTipoAfiliado("A");
			
			request.getSession().setAttribute("registroDSiniestro", reg);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso3 ", e);

			return "registro_error";

		}

		return "paso-paso3";
	}
	
	@RequestMapping(value = { "/registrarDP.do" }, method = RequestMethod.POST)
	public String step3DP(@ModelAttribute RegistroDocPendienteVo form, Model model, HttpServletRequest request) {

		try {

			List<Sucursal> sucursales = sucursalService.findAll();

			
			model.addAttribute("sucursales", sucursales);
			
			Sucursal sucursal = sucursalService.findBySucursal(form.getSucursal());

			
			String correo="";

			if (sucursal != null) {
				correo=sucursal.getEmailEjecutivo();
				request.getSession().setAttribute("correo", correo);
				request.getSession().setAttribute("sucursal", sucursal.getCodigoSucursal());

			} else {
				return "registro_error";
			}

			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			request.getSession().setAttribute("sucursal", form.getSucursal());

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");
			
			//Se obtiene adjunto
			String path = Configuraciones.getConfig("pdfs.carpeta");
			String pathCorreo = Configuraciones.getConfig("correo.carpeta");
			
			String sucursal_carpeta=sucursal.getCodigoSucursal().trim() + "-" + sucursal.getDescripcion().trim();
			byte[] documento = null;
			File file = new File(path + "\\" + sucursal_carpeta);

			if (!file.exists()) {
				file.mkdir();
			}
			File file2 = new File(
					path + "\\" + sucursal_carpeta + "\\" + rut.replace(".", "").replace("-", "") );
			if (!file2.exists()) {
				file2.mkdir();
			}

			//pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_"
			//		+ folio + "\\";
			
			String nombre_archivo="";
			documento = form.getDocumento().getBytes();
			if (documento.length > 0) {
				nombre_archivo= rut.replace(".", "").split("-")[0] + "_"
						+  form.getDocumento().getOriginalFilename();
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + nombre_archivo, documento);
			}
			
			//RegistroDSiniestro reg = null;
			String rutafi= "";
			String telefono="";
			String email="";
			long id=0;
			if(request.getSession().getAttribute("registro_save")==null){
				
				RegistroDSiniestro reg = new RegistroDSiniestro();
				reg.setNombre(nombre);
				reg.setEmail(form.getEmail());
				reg.setRut(rut.replace(".", "").split("-")[0]);
				reg.setDvRut(rut.replace(".", "").split("-")[1]);
				reg.setIdSucursal(sucursal.getCodigoSucursal());
				reg.setTelefono(form.getTelefono().replace("+56", ""));
				reg.setFechacre(new Date());
				reg.setTipoAfiliado("A");
				regService.save(reg);
				telefono= reg.getTelefono();
				email= reg.getEmail();
				id=reg.getId();
				rutafi=reg.getRut();
				request.getSession().setAttribute("registroDSiniestro", reg);
			}else{
				RegistroDSiniestro registroDS= (RegistroDSiniestro)request.getSession().getAttribute("registroDSiniestro");
				telefono= registroDS.getTelefono();
				email= registroDS.getEmail();
				id=registroDS.getId();
				rutafi=registroDS.getRut();
				request.getSession().setAttribute("registroDSiniestro", registroDS);
				
			}
			
			request.getSession().setAttribute("registro_save", "1");
			request.setAttribute("mensaje", "1");
			
			//Se envía mail a afiliado
		
			mailService.sendEmailClie(email, "Ingreso documentación pendiente tramitación Siniestro La Araucana"
					, Utils.bodyClient(form.getDocumento().getOriginalFilename()), rutafi);
			
			//se envía mail a Ejecutivo
			pathCorreo =  pathCorreo + "\\" + sucursal_carpeta + "\\" + rutafi  + "\\";
			if(correo!=null && !correo.equals("")){
				mailService.sendEmailEjec(correo, "Notificación documentación pendiente por Siniestro: " + rutafi
						,
						Utils.getbodyEjecDocP(rut, pathCorreo, String.valueOf(id), telefono, email, nombre_archivo));
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso3 ", e);

			return "registro_error";

		}

		return "paso-paso2-DocP";
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
