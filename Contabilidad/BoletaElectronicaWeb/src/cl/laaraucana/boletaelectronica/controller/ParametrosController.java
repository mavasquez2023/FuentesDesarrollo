package cl.laaraucana.boletaelectronica.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import cl.laaraucana.boletaelectronica.entities.Parametros;
import cl.laaraucana.boletaelectronica.services.ParametrosService;

@Controller
public class ParametrosController {

	private static final Logger logger = Logger.getLogger(ParametrosController.class);

	@Autowired
	private ParametrosService parametrosService;

	
	@RequestMapping(value = { "/parametros.do" }, method = RequestMethod.GET)
	public String getParametros(ModelMap model) {
		try {
			List<Parametros> parametros = parametrosService.getParametros(1);
			if (parametros != null) {
				model.addAttribute("parametros", parametros);
			}
		} catch (Exception ex) {
			logger.error("Error parametros: ", ex);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + ex.getMessage());
			return "error-process";
		}
		return "parametros";
	}

	
	@RequestMapping(value = { "/parametro.do" }, method = RequestMethod.GET)
	public String getParametrosById(@ModelAttribute("id") String id, ModelMap model) {
		try {
			long idPar = parametrosService.getParamByCode(Integer.parseInt(id)).getIdpar();
			Parametros parametro = parametrosService.getParametrosById(idPar);
			if (parametro != null) {
				model.addAttribute("parametro", parametro);
			}
		} catch (Exception ex) {
			logger.error("Error parametros: ", ex);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + ex.getMessage());
			return "error-process";
		}
		return "modificarParametro";
	}

	
	@RequestMapping(value = { "/delete.do" }, method = RequestMethod.GET)
	public String deleteParametro(@ModelAttribute("id") String id, ModelMap model) {
		try {
			long idPar = parametrosService.getParamByCode(Integer.parseInt(id)).getIdpar();
			Parametros param = parametrosService.getParametrosById(idPar);
			param.setESTADO(0);
			parametrosService.updateParametro(param);
		} catch (Exception ex) {
			logger.error("Error parametros: ", ex);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + ex.getMessage());
			return "error-process";
		}
		return "redirect:/parametros.do";
	}

	
	@RequestMapping(value = { "/update.do" }, method = RequestMethod.POST)
	public String updateParametro(@ModelAttribute Parametros parametro, ModelMap model) {
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss", new Locale("Cl"));
		SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy", new Locale("Cl"));
		try {
			if (parametro.getIdpar() != 0) {
				// Actualizar
				Parametros parametros = parametrosService.getParametrosById(parametro.getIdpar());
				parametro.setFECCRE(parametros.getFECCRE());
				parametro.setHORACRE(parametros.getHORACRE());
				parametro.setESTADO(1);
				parametro.setFECMOD(fecha.format(new Date()));
				parametro.setHORAMOD(hora.format(new Date()));
				parametrosService.updateParametro(parametro);
			} else {
				// grabar
				Parametros param = parametrosService.getLastParam();
				parametro.setFECCRE(fecha.format(new Date()));
				parametro.setFECMOD(fecha.format(new Date()));
				parametro.setHORACRE(hora.format(new Date()));
				parametro.setHORAMOD(hora.format(new Date()));
				parametro.setESTADO(1);
				parametro.setCODIGO(param.getCODIGO() + 1);
				parametrosService.saveParametro(parametro);
			}
		} catch (Exception ex) {
			logger.error("Error parametros: ", ex);
			model.addAttribute("mensaje", "Hubo un error en el proceso " + ex.getMessage());
			return "error-process";
		}
		return "redirect:/parametros.do";
	}
}
