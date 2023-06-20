package cl.laaraucana.rendicionpagonomina.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.services.ProcesaArchivoGenericoTEF;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;
import cl.laaraucana.rendicionpagonomina.vo.EnvioNominaRespuestaVo;

@Controller
public class EnvioNominasController {

	private static final Logger logger = Logger.getLogger(EnvioNominasController.class);

	@Autowired
	ProcesaRendicionesBCI procesaRendicionesBCIService;
	
	
	@Autowired
	ProcesaArchivoGenericoTEF procesaArchivoGenericoTEF;
	
	@RequestMapping(value = { "/enviarFilesBancos.do" }, method = RequestMethod.POST)
	public String enviarFilesBancos(ModelMap model, HttpServletRequest request) {
	
		logger.debug("enviando files "+ request.getParameter("nominasSelected"));
		String fechaConsulta= (String)request.getSession().getAttribute("fechaConsulta");
		ArrayList<HashMap<String, Object>> files = new ArrayList<HashMap<String, Object>>();
		String[] archivos = request.getParameter("nominasSelected").split(";");
		if(archivos.length>0) {
			for (String input : archivos) {
				if(input.trim().length()>0 && input.trim().contains("::")) {
					HashMap<String, Object> file = new HashMap<String, Object>();
					file.put("file", input.trim().split("::")[0]);
					file.put("type", input.trim().split("::")[1]);
					Long idCabecera = new Long(file.get("file").toString());
					try {
						EnvioNominaRespuestaVo respuesta_envio= procesaArchivoGenericoTEF.sendFiles(idCabecera);
						request.getSession().setAttribute("codigo_respuesta", respuesta_envio.getCodigoRetorno());
						request.getSession().setAttribute("mensaje", respuesta_envio.getGlsErrorEnvio());
						request.getSession().setAttribute("archivo", respuesta_envio.getNombreNomina());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	
		return "licencias-result-envio";
	}
	
	
	
	@RequestMapping(value = { "/all-test.do" }, method = RequestMethod.GET)
	public String allTest(ModelMap model, HttpServletRequest request) throws MiException {
	
		logger.debug("testeando creditos...");
		
		procesaArchivoGenericoTEF.loadData();
		
		logger.debug("saliendo testeo creditos...");
		
		return "licencias-x";
	}
	
	
	@RequestMapping(value = { "/rendicion-test.do" }, method = RequestMethod.GET)
	public String rendicionTest(ModelMap model, HttpServletRequest request) throws MiException {
	
		logger.debug("testeando creditos...");
		
		procesaRendicionesBCIService.execute();
		
		logger.debug("saliendo testeo creditos...");
		
		return "licencias-x";
	}
	
	
}
