package cl.laaraucana.pubnominas.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.pubnominas.dto.asfam.CabeceraAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.CabeceraMODDto;
import cl.laaraucana.pubnominas.dto.asfam.DatosDto;
import cl.laaraucana.pubnominas.persistence.DaoAsfam;
import cl.laaraucana.pubnominas.utils.Utils;


@Controller
public class AutorizacionesController {

	private static final Logger logger = Logger.getLogger(AutorizacionesController.class);

	@Autowired
	private DaoAsfam dao;


	@RequestMapping(value = { "/autorizaciones.do" }, method = RequestMethod.POST)
	public String getAutorizaciones(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {
		String retorno="nominaAutorizaciones";
		String mensaje="";
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			if (!result.hasErrors()) {
				if (!datos.getRutEmpresa().isEmpty() && !datos.getPeriodo().isEmpty()) {
					if(datos.getTipomov()==null || datos.getTipomov().equals("AUT")){

						String rutempresas= datos.getRutEmpresa().trim();
						rutempresas= rutempresas.replaceAll(" ", ",");
						rutempresas= rutempresas.replaceAll(",,", ",");
						String periodo_anterior= Utils.dateToperiodo(Utils.sumMonths(Utils.periodoToDate(datos.getPeriodo()), -1));
						logger.info("Consultando Autorizaciones con periodo anterior: " + periodo_anterior);
						List<CabeceraAUTDto> cabecerasAUT = dao.getAllCabeceraAUT(periodo_anterior, rutempresas ); // "70016160");
						for (Iterator iterator = cabecerasAUT.iterator(); iterator
								.hasNext();) {
							CabeceraAUTDto cabeceraAUTDto = (CabeceraAUTDto) iterator
									.next();
							cabeceraAUTDto.setPeriodo_actual(datos.getPeriodo());
						}
						logger.info("Total Cabeceras Autorizaciones:" + cabecerasAUT.size() + ", empresas" + rutempresas);
						if(cabecerasAUT.size()==0){
							mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
							model.addAttribute("mensaje", mensaje);
						}
						//model.addAttribute("cabeceras", cabecerasAUT);
						request.setAttribute("cabeceras", cabecerasAUT);
						if(cabecerasAUT.size()>0){
							request.getSession().setAttribute("cabecera", cabecerasAUT.get(0));
						}
					}else{
						retorno= getModificaciones(model, datos, result, request);
					}
					//request.setAttribute("periodo", datos.getPeriodo());
					//request.setAttribute("rutEmpresa", datos.getRutEmpresa());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje="Ups, ha ocurrido un error, favor intente nuevamente.";
			model.addAttribute("mensaje", mensaje);
			logger.error("Error ", e);
		}

		return retorno;
	}
	
	public String getModificaciones(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {
		String mensaje="";
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			
			String rutempresas= datos.getRutEmpresa().trim();
			rutempresas= rutempresas.replaceAll(" ", ",");
			rutempresas= rutempresas.replaceAll(",,", ",");
			String periodo_anterior= Utils.dateToperiodo(Utils.sumMonths(Utils.periodoToDate(datos.getPeriodo()), -1));
			logger.info("Consultando Modificaciones con periodo anterior: " + periodo_anterior);
			List<CabeceraMODDto> cabecerasMOD = dao.getCabeceraMOD(periodo_anterior, rutempresas ); // "70016160");
			for (Iterator iterator = cabecerasMOD.iterator(); iterator
					.hasNext();) {
				CabeceraMODDto cabeceraMODDto = (CabeceraMODDto) iterator
						.next();
				cabeceraMODDto.setPeriodo_actual(datos.getPeriodo());
			}
			if(cabecerasMOD.size()==0){
				mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
			}

			request.setAttribute("cabeceras", cabecerasMOD);
			if(cabecerasMOD.size()>0){
				request.getSession().setAttribute("cabecera", cabecerasMOD.get(0));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje="Ups, ha ocurrido un error, favor intente nuevamente.";
			//mensaje=e.getMessage();
			logger.error("Error ", e);
		}
		model.addAttribute("mensaje", mensaje);
		return "nominaModificaciones";
	}
}
