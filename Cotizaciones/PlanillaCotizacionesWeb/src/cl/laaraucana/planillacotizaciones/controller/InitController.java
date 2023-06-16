package cl.laaraucana.planillacotizaciones.controller;

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

import cl.laaraucana.planillacotizaciones.dto.CabeceraDto;
import cl.laaraucana.planillacotizaciones.dto.DatosDto;
import cl.laaraucana.planillacotizaciones.persistence.Dao;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private Dao dao;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {

			List<CabeceraDto> cabeceras = new ArrayList<CabeceraDto>();

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

		try {
			if (!result.hasErrors()) {

				if (!datos.getRutEmpresa().isEmpty() && !datos.getPeriodo().isEmpty()) {
					if(datos.getCodigoOficina()!= null && datos.getCodigoOficina().equals("")){
						datos.setCodigoOficina(null);
					}
					if(datos.getCodigoSucursal()!= null && datos.getCodigoSucursal().equals("")){
						datos.setCodigoSucursal(null);
					}
					String rutempresas= datos.getRutEmpresa().trim();
					rutempresas= rutempresas.replaceAll(" ", ",");
					rutempresas= rutempresas.replaceAll(",,", ",");
					List<CabeceraDto> cabeceras = dao.getAllCabecera(datos.getPeriodo(), rutempresas , datos.getCodigoOficina(), datos.getCodigoSucursal()); // "70016160");
					if(cabeceras.size()==0){
						String mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
						model.addAttribute("mensaje", mensaje);
					}
					model.addAttribute("cabeceras", cabeceras);

					request.getSession().setAttribute("cabecera", cabeceras);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error ", e);
		}

		return "inicio";
	}

}
