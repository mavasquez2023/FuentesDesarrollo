package cl.laaraucana.conveniosura.controller;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.araucana.core.util.Rut;
import cl.laaraucana.conveniosura.util.Configuraciones;
import cl.laaraucana.conveniosura.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.conveniosura.ws.ClienteInfoAfiliado;




@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String rut="";
			Principal principal = request.getUserPrincipal();
			if(principal!=null){
				rut= principal.getName();
			}
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);
			if(salida.isDeudordirecto()){
				logger.info("Rut es deudor directo " + rut);
				return "sin_informacion";
			}else{
				
				request.getSession().setAttribute("rut", rut);
				String url= Configuraciones.getConfig("url.convenio.sura");
				//url= url.replaceAll("#RUT#", ""+rut.replaceAll("-", ""));
				response.sendRedirect(url);
				logger.info("Invocando url " + url);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Sura ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
			return "registro-error";
		}

		return "exito";
	}
	
	
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	

}
