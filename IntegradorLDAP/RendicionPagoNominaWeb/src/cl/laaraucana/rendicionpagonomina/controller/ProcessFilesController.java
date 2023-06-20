package cl.laaraucana.rendicionpagonomina.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.services.BeneficioService;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionBESService;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;
import cl.laaraucana.rendicionpagonomina.vo.ParamRendicionVO;

@Controller
public class ProcessFilesController {

	private static final Logger logger = Logger.getLogger(ProcessFilesController.class);

	@Autowired
	private ProcesaRendicionBESService processfileService;
	
	@Autowired
	ProcesaRendicionesBCI procesaRendicionesBCIService;
	
	@RequestMapping(value = { "/rendicionBCI.do" }, method = RequestMethod.GET)
	public String processPNOL(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para rendicion ");
		int resultadoOperacion = 0;
		try {
			resultadoOperacion = procesaRendicionesBCIService.execute();
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
		}
		logger.debug("En el proceso automatizado, estado operacion para rencicón bci:["+resultadoOperacion+"]");
		return "redirect:/init.do";
	}
	
	@RequestMapping(value = { "/processNomina.do" }, method = RequestMethod.POST)
	public String process(ModelMap model,  @ModelAttribute ParamRendicionVO form, HttpServletRequest request) {

		try {
			String numNomina= form.getNumNomina();
			if(numNomina==null){
				numNomina="";
			}
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			processfileService.procesoxNomina(numNomina, idUsuario, null);
			//processfileService.proceso2();
			// processfileService.sendNomina();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en el proceso ", e);
			model.addAttribute("mensaje", "Hubo errores en el proceso");

			return "error-process";
		}

		model.addAttribute("mensaje", "Datos procesados exitosamente.");

		return "process-success";

	}

	@RequestMapping(value = { "/processFecha.do" }, method = RequestMethod.POST)
	public String process2(ModelMap model, @ModelAttribute ParamRendicionVO form, HttpServletRequest request) {

		try {
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			String fechaDesde= form.getFechaDesde();
			fechaDesde=fechaDesde.replaceAll("-", "/");
			if(fechaDesde.equals("dd-mm-aaaa")){
				fechaDesde="";
			}
			String fechaHasta= form.getFechaHasta();
			fechaHasta=fechaHasta.replaceAll("-", "/");
			if(fechaHasta.equals("dd-mm-aaaa")){
				fechaHasta="";
			}
			
			Date fechaInicial= Utils.stringToDateBES(fechaDesde);
			Date fechaFinal= Utils.stringToDateBES(fechaHasta);
			Date fechaConsulta= Utils.stringToDateBES(fechaDesde);
			
			
			while (fechaFinal.compareTo(fechaConsulta)>=0) {
				String fechaParam=Utils.dateToStringBES(fechaConsulta);
				logger.info("Consultando Rendición por fecha:" + fechaParam);
				processfileService.procesoxFecha(fechaParam, fechaParam, idUsuario, null);
				fechaConsulta= Utils.sumDays(fechaConsulta, 1);
			}
			
			
			// processfileService.processFirstFiles();
			// processfileService.sendNomina();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en el proceso " +  e.getMessage());
			model.addAttribute("mensaje", "Hubo errores en el proceso, detalle " +  e.getMessage());

			return "error-process";
		}

		model.addAttribute("mensaje", "Datos procesados exitosamente.");

		return "process-success";

	}
	
	@RequestMapping(value = { "/processNominayFecha.do" }, method = RequestMethod.POST)
	public String processAll(ModelMap model, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			processfileService.procesoxNomina("", idUsuario, null);
			processfileService.procesoxFecha(sdf.format(new Date()), sdf.format(new Date()), idUsuario, null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en el proceso " +  e.getMessage());
			model.addAttribute("mensaje", "Hubo errores en el proceso, detalle " +  e.getMessage());

			return "error-process";
		}

		model.addAttribute("mensaje", "Datos procesados exitosamente.");

		return "process-success";

	}
}
