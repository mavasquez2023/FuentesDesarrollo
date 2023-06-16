package cl.araucana.estasfam.web.controllers;

import java.io.File;
import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.araucana.estasfam.app.business.enums.EstadisticasEnum;
import cl.araucana.estasfam.app.business.services.DescargaArchivoService;
import cl.araucana.estasfam.app.business.services.EstadisticaManagerService;
import cl.araucana.estasfam.common.util.FechaUtil;
import cl.araucana.estasfam.web.forms.EstadisitcasAsfamForm;

@Controller
public class EstadisticasAsfamController {
	
	@Resource
	private EstadisticaManagerService estManagerServ;
	
	@Resource
	private DescargaArchivoService descargaArchivoServ;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(HttpServletRequest request) throws Exception {
		try {
			HttpSession sesion = request.getSession(true);
			Principal userPrincipal = request.getUserPrincipal();
			
			if(userPrincipal!=null){
				String user =  userPrincipal.getName();
				System.out.println("usuario autenticado: "+user);
				sesion.setAttribute("loginUser", user);
				request.setAttribute("user", user);
				return "redirect:/secure/home";
			}else{
				return "redirect:/LoginError.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/LoginError.jsp";
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView estAsfamHome(HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("estadisticas_asfam_home");
		
		//Setea estado de generacion de cada estadisticas
		EstadisitcasAsfamForm form = new EstadisitcasAsfamForm();
		form.setEstASI5490(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5490.getCodigo()));
		form.setEstASI5491(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5491.getCodigo()));
		form.setEstASI5460(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5460.getCodigo()));
		form.setEstASI4580(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI4580.getCodigo()));
		form.setEstASI4560(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI4560.getCodigo()));
		form.setEstCUADRO8(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.CUADRO8.getCodigo()));
		form.setEstCUADRO10(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.CUADRO10.getCodigo()));
		
		form.setMesPeriodo(FechaUtil.getDescripcionMes(FechaUtil.restarMeses(new Date(), 1)));
		form.setAnoPeriodo(FechaUtil.getAno(FechaUtil.restarMeses(new Date(), 1)).toString());
		
		model.addObject("generadorEstAsfam", form);
		
		return model; 
	}
	
	@RequestMapping(value = "/generar_estadisitcas", method = RequestMethod.POST)
	public String generarEstadisticas(HttpServletRequest request, @ModelAttribute EstadisitcasAsfamForm genEstAsfamForm) throws Exception {
		//Bloqua la generacion de estadistica
		estManagerServ.bloquarGeneracionEstadisticas(genEstAsfamForm.getCodigoEstadistica());
		
		//Genera las estadisticas solicitadas
		estManagerServ.generarEstadisticas(genEstAsfamForm.getCodigoEstadistica());
		
		return "redirect:/secure/home";
	}
	
	@RequestMapping(value = "/descargar", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String archivo = ServletRequestUtils.getStringParameter(request, "ruta_archivo");
			descargaArchivoServ.descargarArchivo(response, archivo);
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/recargar_estados", method = RequestMethod.POST)
	public ModelAndView recargarEstadosEstadisticas(HttpServletRequest request, @ModelAttribute EstadisitcasAsfamForm genEstAsfamForm) throws Exception{
		ModelAndView model = new ModelAndView("estados_estadisticas_asfam");
		
		genEstAsfamForm.setEstASI5490(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5490.getCodigo()));
		genEstAsfamForm.setEstASI5491(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5491.getCodigo()));
		genEstAsfamForm.setEstASI5460(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI5460.getCodigo()));
		genEstAsfamForm.setEstASI4580(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI4580.getCodigo()));
		genEstAsfamForm.setEstASI4560(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.ASI4560.getCodigo()));
		genEstAsfamForm.setEstCUADRO8(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.CUADRO8.getCodigo()));
		genEstAsfamForm.setEstCUADRO10(estManagerServ.validarEstadoGeneracionEstadisticas(EstadisticasEnum.CUADRO10.getCodigo()));
		
		genEstAsfamForm.setMesPeriodo(FechaUtil.getDescripcionMes(FechaUtil.restarMeses(new Date(), 1)));
		genEstAsfamForm.setAnoPeriodo(FechaUtil.getAno(FechaUtil.restarMeses(new Date(), 1)).toString());
		
		model.addObject("generadorEstAsfam", genEstAsfamForm);
		return model;
	}
	
	@RequestMapping(value = "/removerLocks", method = RequestMethod.GET)
	public String removerLocks(HttpServletRequest request, HttpServletResponse response) {
		File ASI5490 = new File(EstadisticasEnum.ASI5490.getCodigo() + ".lock");
		if(ASI5490.exists()) ASI5490.delete();
		
		File ASI5491 = new File(EstadisticasEnum.ASI5491.getCodigo() + ".lock");
		if(ASI5491.exists()) ASI5491.delete();
		
		File ASI5460 = new File(EstadisticasEnum.ASI5460.getCodigo() + ".lock");
		if(ASI5460.exists()) ASI5460.delete();
		
		File ASI4580 = new File(EstadisticasEnum.ASI4580.getCodigo() + ".lock");
		if(ASI4580.exists()) ASI4580.delete();
		
		File ASI4560 = new File(EstadisticasEnum.ASI4560.getCodigo() + ".lock");
		if(ASI4560.exists()) ASI4560.delete();
		
		File CUADRO8 = new File(EstadisticasEnum.CUADRO8.getCodigo() + ".lock");
		if(CUADRO8.exists()) CUADRO8.delete();
		
		File CUADRO10 = new File(EstadisticasEnum.CUADRO10.getCodigo() + ".lock");
		if(CUADRO10.exists()) CUADRO10.delete();
		
		return "redirect:/secure/home";
	}
	

}
