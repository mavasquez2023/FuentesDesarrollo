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

import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.dto.pex.DatosDto;
import cl.laaraucana.pubnominas.dto.pex.OficinaDto;
import cl.laaraucana.pubnominas.persistence.DaoPex;
import cl.laaraucana.pubnominas.utils.Utils;



@Controller
public class PagosEnExcesoController {

	private static final Logger logger = Logger.getLogger(PagosEnExcesoController.class);

	@Autowired
	private DaoPex dao;


	@RequestMapping(value = { "/oficinaspex.do" }, method = RequestMethod.POST)
	public String getOficinasPex(ModelMap model, @Validated @ModelAttribute DatosDto datos, BindingResult result,
			HttpServletRequest request) {
		String mensaje="";
		try {
			if (!result.hasErrors()) {

				if (!datos.getRutEmpresa().isEmpty()) {
					if(datos.getCodigoOficina()!= null && datos.getCodigoOficina().equals("")){
						datos.setCodigoOficina(null);
					}
					if(datos.getCodigoSucursal()!= null && datos.getCodigoSucursal().equals("")){
						datos.setCodigoSucursal(null);
					}
					if(datos.getRazonSocial()!= null && datos.getRazonSocial().equals("")){
						datos.setRazonSocial(null);
					}
					String rutempresas= datos.getRutEmpresa().trim();
					rutempresas= rutempresas.replaceAll(" ", ",");
					rutempresas= rutempresas.replaceAll(",,", ",");
					logger.info("Consultando PEX,  Empresa:" + rutempresas);
					List<OficinaDto> cabeceras = dao.getOficinas(rutempresas , datos.getRazonSocial(), datos.getCodigoOficina(), datos.getCodigoSucursal()); // "70016160");
					if(cabeceras.size()==0){
						mensaje= "No existe información para la(s) empresa(s) " + rutempresas + " consultada(s)";
					}
					model.addAttribute("cabeceras", cabeceras);

					request.getSession().setAttribute("cabecera", cabeceras);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//mensaje=e.getMessage();
			mensaje="Ups, ha ocurrido un error, favor intente nuevamente.";
			logger.error("Error ", e);
		}
		model.addAttribute("mensaje", mensaje);
		return "nominaPEX";
	}
	
	@RequestMapping(value = { "/cuotaspex.do" }, method = RequestMethod.GET)
	public String getCuotasPex(@ModelAttribute("rutemp") String rutEmpresa,
			@ModelAttribute("oficina") String oficina, @ModelAttribute("sucursal") String sucursal, 
			ModelMap model, BindingResult result,
			HttpServletRequest request) {
		String mensaje="";
		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			if (!result.hasErrors()) {

				if (!rutEmpresa.isEmpty()) {
					
					logger.info("Consultando Detalle PEX,  Empresa:" + rutEmpresa + ", Oficina: " + oficina);
					List<CuotaPEXDto> cuotas = dao.getTrabajadores(rutEmpresa, oficina, sucursal);
					if(cuotas.size()==0){
						mensaje= "No existe información de cuotas para la empresa " + rutEmpresa + " consultada";
					}
					model.addAttribute("detalle", cuotas);
					if(cuotas.size()>0){
						request.getSession().setAttribute("cabecera", cuotas.get(0));
						request.getSession().setAttribute("fechaCreacion", Utils.pasaFechaInforme(cuotas.get(0).getFechaCreacion()));
					}
					request.getSession().setAttribute("trabajadores", cuotas);
					request.getSession().setAttribute("fechaEmision", Utils.fechaWebSlash());
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mensaje="Ups, ha ocurrido un error, favor intente nuevamente.";
			//mensaje=e.getMessage();
			logger.error("Error ", e);
		}
		model.addAttribute("mensaje", mensaje);
		return "cuotasPEX";
	}
}
