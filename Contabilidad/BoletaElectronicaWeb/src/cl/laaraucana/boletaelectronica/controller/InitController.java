package cl.laaraucana.boletaelectronica.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.boletaelectronica.entities.BoletaBase;
import cl.laaraucana.boletaelectronica.entities.OrigenBoleta;
import cl.laaraucana.boletaelectronica.services.BaseServices;
import cl.laaraucana.boletaelectronica.services.OrigenBoletaService;
import cl.laaraucana.boletaelectronica.utils.Configuraciones;
import cl.laaraucana.boletaelectronica.utils.Utils;
import cl.laaraucana.boletaelectronica.vo.OrigenBoletaVo;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private OrigenBoletaService origenBoletaService;

	@Autowired
	private BaseServices baseService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getboleta(ModelMap model, HttpServletRequest request) {
		try {
			String maximoRegistro = Configuraciones.getConfig("noemitidos.max");
			logger.info("Cantidad maxima de registros a mostrar: " + maximoRegistro);
			String rut_usuario = "";
			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut_usuario = principal.getName();

				logger.debug("rut: " + rut_usuario);
			} else {
				return "redirect:/logout.do";
			}
			request.getSession().setAttribute("rut", rut_usuario);
			List<OrigenBoleta> boletas = origenBoletaService.findAllNoEmitidas();
			List<OrigenBoletaVo> origenList = new ArrayList<OrigenBoletaVo>();
			int i = 0;
			int catidadRegistros = boletas.size();
			if(catidadRegistros > Integer.parseInt(maximoRegistro)) {
				model.addAttribute("mensaje", "Se muestran s�lo las �ltimas " + maximoRegistro + " boletas");
			} else {
				model.addAttribute("mensaje","");
			}
			for (OrigenBoleta origenBoleta : boletas) {
				OrigenBoletaVo origen = new OrigenBoletaVo();
				origen.setFOLIO(origenBoleta.getFOLIO() + "");
				origen.setRUTREC(origenBoleta.getRUTREC());
				origen.setNOMREC(origenBoleta.getNOMREC());
				origen.setMontoDescuento(origenBoleta.getMTODSC());
				origenList.add(origen);
				if (i == Integer.parseInt(maximoRegistro)) {
					break;
				}
				i++;
			}
			String parametro = "NO";
			String rutAraucana = Configuraciones.getConfig("rut.araucana");
			if (rut_usuario.equals(rutAraucana)) {
				parametro = "SI";
			}
			origenList = Utils.processList(origenList);
			if (origenList.size() != 0) {
				//model.addAttribute("boletasSinEmitir", origenList);
				request.getSession().setAttribute("boletasSinEmitir", origenList);
			} else {
				request.getSession().removeAttribute("boletasSinEmitir");
			}
			
			request.getSession().setAttribute("parametro", parametro);
			model.addAttribute("rut", rut_usuario);
			model.addAttribute("parametro", parametro);
		} catch (Exception e) {
			logger.error("Error en inicio ", e);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + e.getMessage());
			return "error-process";
		}
		return "boleta";
	}

	@RequestMapping(value = { "/consulta.do" }, method = RequestMethod.POST)
	public String getNomina(ModelMap model, @ModelAttribute OrigenBoletaVo boleta, HttpServletRequest request) {
		try {
			List<OrigenBoleta> origen = new ArrayList<OrigenBoleta>();
			int j = 0;
			String maximoRegistro = Configuraciones.getConfig("noemitidos.max");
			logger.info("Cantidad maxima de registros a mostrar: " + maximoRegistro);
			if (boleta.getFOLIO().isEmpty()) {
				origen = origenBoletaService.findAllNoEmitidas();
				j = Integer.parseInt(maximoRegistro);
			} else {
				origen = origenBoletaService.getOrigenByNumber(Integer.parseInt(boleta.getFOLIO() + ""));
				j = -1;
			}
			List<OrigenBoletaVo> origenList = new ArrayList<OrigenBoletaVo>();
			int i = 0;
			for (OrigenBoleta origenBoleta : origen) {
				if (baseService.getBoletaByNumberAndEstate(origenBoleta.getFOLIO(), 1).size() == 0) {
					OrigenBoletaVo or = new OrigenBoletaVo();
					or.setFOLIO(origenBoleta.getFOLIO() + "");
					or.setRUTREC(origenBoleta.getRUTREC());
					or.setNOMREC(origenBoleta.getNOMREC());
					or.setMontoDescuento(origenBoleta.getMTODSC());
					origenList.add(or);
					if (i == j) {
						break;
					}
				}
				i++;
			}
			int catidadRegistros = origenList.size();
			logger.info("Registros encontrados = " + catidadRegistros);
			if(catidadRegistros > j) {
				model.addAttribute("mensaje", "Se muestran s�lo las �ltimas " + maximoRegistro + " boletas");
			} else {
				model.addAttribute("mensaje","");
			}
			origenList = Utils.processList(origenList);
			if (origenList.size() != 0) {
				model.addAttribute("boletasSinEmitir", origenList);
			}
			String rut = (String) request.getSession().getAttribute("rut");
			String parametro = (String) request.getSession().getAttribute("parametro");
			model.addAttribute("parametro", parametro);
			model.addAttribute("rut", rut);
		} catch (Exception e) {
			logger.error("Error en inicio ", e);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + e.getMessage());
			return "error-process";
		}
		return "boleta";
	}

	
	@RequestMapping(value = { "/consultaDeta.do" }, method = RequestMethod.POST)
	public String getDeta(ModelMap model, @ModelAttribute OrigenBoletaVo boleta, HttpServletRequest request) {
		try {
			List<BoletaBase> boletasEmitidas = new ArrayList<BoletaBase>();
			long folio = 0;
			long fechaIni = 0;
			long fechaFin = 0;
			if (boleta.getFOLIO() == null || boleta.getFOLIO().isEmpty()) {
				folio = 0;
			} else {
				folio = Long.parseLong(boleta.getFOLIO());
			}
			if (boleta.getFechaInicio() == null || boleta.getFechaInicio().isEmpty()) {
				fechaIni = 0;
			} else {
				String[] fIni = boleta.getFechaInicio().split("-");
				fechaIni = Long.parseLong(fIni[2] + fIni[1] + fIni[0]);
			}
			if (boleta.getFechaFin() == null || boleta.getFechaFin().isEmpty()) {
				fechaFin = 0;
			} else {
				String[] fFin = boleta.getFechaFin().split("-");
				fechaFin = Long.parseLong(fFin[2] + fFin[1] + fFin[0]);
			}
			boletasEmitidas = baseService.findByFecha(fechaIni, fechaFin, folio);
			List<OrigenBoletaVo> origenList = new ArrayList<OrigenBoletaVo>();
			if (boletasEmitidas.size() == 0) {
				logger.debug("No hay boletas emitidas...");
				return "boletasEmitidas";
			}
			List<OrigenBoletaVo> origenListEmitidas = new ArrayList<OrigenBoletaVo>();
			int i = 0;
			for (BoletaBase base : boletasEmitidas) {
				OrigenBoletaVo or = new OrigenBoletaVo();
				or.setFOLIO(base.getFOLDOC() + "");
				or.setNUMBOL(String.valueOf(base.getNUMBOL()));
				or.setRUTREC(base.getRUTREC());
				or.setNOMREC(base.getNOMREC());
				or.setUrlDte(base.getURLACEPTA());
				origenListEmitidas.add(or);
				if (i == 10) {
					break;
				}
				i++;
			}
			if (origenListEmitidas.size() != 0) {
				origenList = Utils.processList(origenListEmitidas);
				model.addAttribute("boletasEmitidas", origenList);
			}
			String rut = (String) request.getSession().getAttribute("rut");
			String parametro = (String) request.getSession().getAttribute("parametro");
			model.addAttribute("parametro", parametro);
			model.addAttribute("rut", rut);
		} catch (Exception e) {
			logger.error("Error en inicio ", e);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + e.getMessage());
			return "error-process";
		}
		return "boletasEmitidas";
	}

	
	@RequestMapping(value = { "/emitidas.do" }, method = RequestMethod.GET)
	public String getBoletaEmitidas(ModelMap model, HttpServletRequest request) {
		try {
			String origen= (String)request.getParameter("origen");
			if(origen!=null && origen.equals("menu")) {
				request.getSession().setAttribute("numeroBoleta", "");
			}
			
			List<OrigenBoletaVo> origenList = new ArrayList<OrigenBoletaVo>();
			List<BoletaBase> boletasEmitidas = baseService.findAllEmitidas();
			if (boletasEmitidas.size() == 0) {
				logger.debug("No hay boletas emitidas...");
				return "boletasEmitidas";
			}
			List<OrigenBoletaVo> origenListEmitidas = new ArrayList<OrigenBoletaVo>();
			for (BoletaBase base : boletasEmitidas) {
				OrigenBoletaVo or = new OrigenBoletaVo();
				or.setFOLIO(base.getFOLDOC() + "");
				or.setNUMBOL(String.valueOf(base.getNUMBOL()));
				or.setRUTREC(base.getRUTREC());
				or.setNOMREC(base.getNOMREC());
				or.setUrlDte(base.getURLACEPTA());
				origenListEmitidas.add(or);
			}
			if (origenListEmitidas.size() != 0) {
				origenList = Utils.processList(origenListEmitidas);
				model.addAttribute("boletasEmitidas", origenList);
			}
			String rut = (String) request.getSession().getAttribute("rut");
			String parametro = (String) request.getSession().getAttribute("parametro");
			model.addAttribute("parametro", parametro);
			model.addAttribute("rut", rut);
		} catch (Exception e) {
			logger.error("Error en inicio ", e);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + e.getMessage());
			return "error-process";
		}
		return "boletasEmitidas";
	}

	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("ibm_security_logout?logoutExitPage=login.jsp");
		return null;
	}
}
