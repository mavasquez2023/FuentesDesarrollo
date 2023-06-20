package cl.laaraucana.pubnominas.controller;

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

import cl.laaraucana.pubnominas.dto.cotizacion.CabeceraDto;
import cl.laaraucana.pubnominas.dto.cotizacion.DatosDto;
import cl.laaraucana.pubnominas.persistence.DaoCotizacion;
import cl.laaraucana.pubnominas.services.BitacoraService;



@Controller
public class CotizacionesController {

	private static final Logger logger = Logger.getLogger(CotizacionesController.class);

	@Autowired
	private DaoCotizacion dao;
	
	@RequestMapping(value = { "/cotizaciones.do" }, method = RequestMethod.POST)
	public String getCotizaciones(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {
		String mensaje="";
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
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
					logger.info("Consultando Cotizaciones con periodo: " + datos.getPeriodo() +", Empresas" + rutempresas);
					List<CabeceraDto> cabeceras = dao.getAllCabecera(datos.getPeriodo(), rutempresas , datos.getCodigoOficina(), datos.getCodigoSucursal()); // "70016160");
					if(cabeceras.size()==0){
						mensaje= "No existe información para el periodo " + datos.getPeriodo() + " consultado";
					}
					model.addAttribute("cabeceras", cabeceras);

					request.getSession().setAttribute("cabecera", cabeceras);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje="Ups, ha ocurrido un error, favor intente nuevamente.";
			logger.error("Error ", e);
		}
		model.addAttribute("mensaje", mensaje);
		return "nominaCotizaciones";
	}
}
