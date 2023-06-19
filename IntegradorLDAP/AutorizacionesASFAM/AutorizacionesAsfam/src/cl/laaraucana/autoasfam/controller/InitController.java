package cl.laaraucana.autoasfam.controller;

import java.util.ArrayList;
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

import cl.laaraucana.autoasfam.dto.CabeceraAUTDto;
import cl.laaraucana.autoasfam.dto.CabeceraMODDto;
import cl.laaraucana.autoasfam.dto.DatosDto;
import cl.laaraucana.autoasfam.persistence.Dao;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private Dao dao;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {

			List<CabeceraAUTDto> cabeceras = new ArrayList<CabeceraAUTDto>();

			model.addAttribute("cabeceras", cabeceras);

			request.getSession().setAttribute("cabecera", cabeceras);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error ", e);
		}

		return "inicio";
	}

	@RequestMapping(value = { "/datos.do" }, method = RequestMethod.POST)
	public String getDatos(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {
		String retorno="inicio";
		try {
			if (!result.hasErrors()) {

				if (!datos.getRutEmpresa().isEmpty() && !datos.getPeriodo().isEmpty()) {
					if(datos.getTipomov().equals("AUT")){


						String rutempresas= datos.getRutEmpresa().trim();
						rutempresas= rutempresas.replaceAll(" ", ",");
						rutempresas= rutempresas.replaceAll(",,", ",");
						List<CabeceraAUTDto> cabecerasAUT = dao.getAllCabeceraAUT(datos.getPeriodo(), rutempresas ); // "70016160");
						if(cabecerasAUT.size()==0){
							String mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
							model.addAttribute("mensaje", mensaje);
						}
						//model.addAttribute("cabeceras", cabecerasAUT);
						request.setAttribute("cabeceras", cabecerasAUT);
						request.getSession().setAttribute("cabecera", cabecerasAUT.get(0));
					}else{
						retorno= getDatosMod(model, datos, result, request);
					}
					request.setAttribute("periodo", datos.getPeriodo());
					request.setAttribute("rutEmpresa", datos.getRutEmpresa());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error ", e);
		}

		return retorno;
	}
	
	public String getDatosMod(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {

		try {
			
			String rutempresas= datos.getRutEmpresa().trim();
			rutempresas= rutempresas.replaceAll(" ", ",");
			rutempresas= rutempresas.replaceAll(",,", ",");
			List<CabeceraMODDto> cabecerasMOD = dao.getCabeceraMOD(datos.getPeriodo(), rutempresas ); // "70016160");
			if(cabecerasMOD.size()==0){
				String mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
				model.addAttribute("mensaje", mensaje);
			}

			request.setAttribute("cabeceras", cabecerasMOD);
			request.getSession().setAttribute("cabecera", cabecerasMOD.get(0));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error ", e);
		}

		return "iniciomod";
	}
}
