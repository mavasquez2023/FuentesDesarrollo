package cl.laaraucana.tarjetatam.controller;

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
import cl.laaraucana.tarjetatam.entities.Comuna;
import cl.laaraucana.tarjetatam.entities.Region;
import cl.laaraucana.tarjetatam.entities.Oficina;
import cl.laaraucana.tarjetatam.entities.RegistroTarjetaTAM;
import cl.laaraucana.tarjetatam.entities.ViewComuna;
import cl.laaraucana.tarjetatam.services.ComunasService;
import cl.laaraucana.tarjetatam.services.RegionService;
import cl.laaraucana.tarjetatam.services.OficinaService;
import cl.laaraucana.tarjetatam.services.MailService;
import cl.laaraucana.tarjetatam.services.RegistroTarjetaTAMService;
import cl.laaraucana.tarjetatam.vo.DatosTarjetaVo;
import cl.laaraucana.tarjetatam.vo.RegistroVo;
import cl.laaraucana.tarjetatam.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.tarjetatam.ws.ClienteInfoAfiliado;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private RegionService regionService;

	@Autowired
	private ComunasService comunasService;
	
	@Autowired
	private OficinaService oficinaService;

	@Autowired
	private RegistroTarjetaTAMService dsiniestroService;
	
	@Autowired
	private RegistroTarjetaTAMService regService;
	
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

			Rut rut_format= new Rut(rut.split("-")[0]);
			request.getSession().setAttribute("usuario", rut_format.toString());
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.POST)
	public String datosAfiliado(ModelMap model, @ModelAttribute RegistroTarjetaTAM form, HttpServletRequest request) {

		try {
			String rut=form.getRut();
			rut= rut.replaceAll("\\.", "");
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);
			
			Rut rut_format= new Rut(rut.split("-")[0]);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			request.getSession().setAttribute("rutAfiliado", rut_format.toString());
			
			String region = (String) request.getSession().getAttribute("region");
			if (region == null) {
				region = "13";
			}
			String comuna = (String) request.getSession().getAttribute("comuna");
			if (comuna == null) {
				comuna = "0";
			}
			
			request.getSession().setAttribute("region", region);
			request.getSession().setAttribute("comuna", comuna);
			
			List<Region> regiones = regionService.findAll();

			List<Comuna> comunas = comunasService.findByComunaReg(Integer.parseInt(region));
			
			request.getSession().setAttribute("regiones", regiones);
			request.getSession().setAttribute("comunas", comunas);
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1_2";
	}
	
	@RequestMapping(value = { "paso2.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute DatosTarjetaVo form, HttpServletRequest request) {

		try {
			
			String oficina_default = "000";
			List<Oficina> oficinas = oficinaService.findAll();
			
			request.getSession().setAttribute("oficinas", oficinas);

			request.getSession().setAttribute("oficina", oficina_default);
			
			
			ViewComuna comuna = comunasService.findByComunaSuc(form.getComuna());

			if (comuna!=null) {
				request.getSession().setAttribute("comuna_habilitada", "1");
			} else {
				request.getSession().setAttribute("comuna_habilitada", "0");
			}
			
			request.getSession().setAttribute("data", form);

			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "/volver_paso2.do" }, method = RequestMethod.POST)
	public String getPaso2(ModelMap model, @ModelAttribute DatosTarjetaVo form, HttpServletRequest request) {

		try {

			String oficina = (String) request.getSession().getAttribute("oficina");
			if (oficina == null) {
				oficina = "001";
			}

			List<Oficina> oficinas = oficinaService.findAll();

			request.getSession().setAttribute("oficinas", oficinas);
			request.getSession().setAttribute("data", form);
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "paso3.do" }, method = RequestMethod.POST)
	public String step3(ModelMap model, @ModelAttribute DatosTarjetaVo form, HttpServletRequest request) {

		try {
			DatosTarjetaVo data= (DatosTarjetaVo)request.getSession().getAttribute("data");
			String idOficinaDespacho= form.getOficinaDespacho();
			if(idOficinaDespacho!=null){
				data.setOficinaDespacho(idOficinaDespacho);
			}else{
				String repetir_direccion=form.getRepetirDireccion();
				if(repetir_direccion!=null && repetir_direccion.equals("1")){
					data.setRegionDespacho(data.getRegion());
					data.setComunaDespacho(data.getComuna());
					data.setDireccionDespacho(data.getDireccion());
					data.setNumeroDespacho(data.getNumero());
					data.setDeptoDespacho(data.getDepto());
					data.setReferenciaDespacho(data.getReferencia());
					data.setRepetirDireccion("1");
				}
				if(idOficinaDespacho==null && repetir_direccion==null){
					data.setRegionDespacho(form.getRegionDespacho());
					data.setComunaDespacho(form.getComunaDespacho());
					data.setDireccionDespacho(form.getDireccionDespacho());
					data.setNumeroDespacho(form.getNumeroDespacho());
					data.setDeptoDespacho(form.getDeptoDespacho());
					data.setReferenciaDespacho(form.getReferenciaDespacho());
					data.setRepetirDireccion("0");
				}
			}
			request.getSession().setAttribute("data", data);
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso3";
	}
	
	
	@RequestMapping(value = { "/confirmar.do" }, method = RequestMethod.POST)
	public String confirmar(@ModelAttribute RegistroVo form, Model model, HttpServletRequest request) {
		try {

			Oficina oficina = oficinaService.findByOficina(form.getOficina());

			if (oficina!=null) {
				request.getSession().setAttribute("oficinaVO", oficina);
			} else {
				return "registro_error";
			}

			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			request.getSession().setAttribute("sucursal", form.getOficina());

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");
			
			RegistroTarjetaTAM reg = new RegistroTarjetaTAM();

			reg.setNombre(nombre);
			reg.setEmail(form.getEmail());
			reg.setRut(rut.replace(".", "").split("-")[0]);
			reg.setDvRut(rut.replace(".", "").split("-")[1]);
			reg.setIdOficina(String.valueOf(oficina.getCodigoOficina()));
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
