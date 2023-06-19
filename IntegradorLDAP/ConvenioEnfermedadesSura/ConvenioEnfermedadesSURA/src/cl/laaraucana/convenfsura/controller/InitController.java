package cl.laaraucana.convenfsura.controller;

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
import cl.laaraucana.convenfsura.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.convenfsura.ibatis.vo.FormSuraVo;
import cl.laaraucana.convenfsura.services.BitacoraService;
import cl.laaraucana.convenfsura.services.ContactoDHWServicesImpl;
import cl.laaraucana.convenfsura.services.ContactoDWHServices;
import cl.laaraucana.convenfsura.services.TelefonoService;
import cl.laaraucana.convenfsura.services.URLConnection;
import cl.laaraucana.convenfsura.util.Configuraciones;
import cl.laaraucana.convenfsura.vo.AfiliadoVo;
import cl.laaraucana.convenfsura.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.convenfsura.ws.ClienteInfoAfiliado;




@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private URLConnection urlConnection;
	
	@Autowired
	private ContactoDWHServices contactoDHW;
	
	@Autowired
	private TelefonoService telefonoService;
	
	@Autowired
	private BitacoraService bitacoraService;
	
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
				AfiliadoVo data_afiliado= new AfiliadoVo();
				data_afiliado.setRut(rut);
				data_afiliado.setNombre(salida.getNombreCompleto());
				
				data_afiliado= contactoDHW.obtenerDatosContacto(data_afiliado);
				
				request.getSession().setAttribute("data", data_afiliado);
				
				List<String> lista_prefijos_telefono= telefonoService.getPrefijoTelefono();
				List<String> lista_prefijos_celular= telefonoService.getPrefijoCelular();
				lista_prefijos_telefono.add(0, "");
				lista_prefijos_celular.add(0, "");
				lista_prefijos_telefono.add("");
				lista_prefijos_celular.add("");
				
				request.getSession().setAttribute("prefijostel", lista_prefijos_telefono);
				request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Sura ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
			return "registro-error";
		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String ingresoDatos(ModelMap model, @ModelAttribute AfiliadoVo form, HttpServletRequest request, HttpServletResponse response) {

		try {
			AfiliadoVo data_afiliado= (AfiliadoVo)request.getSession().getAttribute("data");
			
			String celular= form.getCelular();
			String telefono=form.getTelefono();
			String email=form.getEmail();
			
			String usuario= Configuraciones.getConfig("url.usuario.sura");
			String clave= Configuraciones.getConfig("url.clave.sura");
			String url_token= Configuraciones.getConfig("url.token.sura");
			String url_venta= Configuraciones.getConfig("url.venta.sura");
			
			Map<String, String> autenticacion= new HashMap<String, String>();
			autenticacion.put("nombreUsuario", usuario);
			autenticacion.put("clave", clave);
			
			String params_token= urlConnection.setParams(autenticacion);
			
			//Se obtiene TOKEN
			String token= urlConnection.post(params_token, url_token);
			//String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IkNDTEEiLCJuYmYiOjE2MDk4NjI5OTgsImV4cCI6MTYwOTg2NDc5OCwiaWF0IjoxNjA5ODYyOTk4LCJpc3MiOiJodHRwczovL2Rlc2Euc2VndXJvc3N1cmEuY2wiLCJhdWQiOiJodHRwczovL2Rlc2Euc2VndXJvc3N1cmEuY2wifQ.wJRzHIGl5UCTrbF44ERil3e7C0mQ4ytKrJeZEGFjodY";
			//String data= urlConnection.doPostRequest(url_token, url_params);
			logger.info("Token devuelto: " + token);
			
			String rut= data_afiliado.getRut().split("-")[0];
			String dv= data_afiliado.getRut().split("-")[1];
			FormSuraVo formSura= new FormSuraVo();
			formSura.setRut(rut);
			formSura.setDv(dv);
			formSura.setCelular(celular);
			formSura.setEmail(email);
			formSura.setCanal(usuario);
			formSura.setToken(token);
			
			logger.info("Parámetros a enviar, Rut:"  + rut + ", dv:" + dv + ", celular:" + celular + ", email:" + email + ", canal:" + usuario + ", token:" + token);
			request.getSession().setAttribute("venta", formSura);
			if(!celular.equals(data_afiliado.getCelular())){
				data_afiliado.setCelular(celular);
				logger.info("Insertando celular en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "MOB");
			}
			if(!telefono.equals(data_afiliado.getTelefono())){
				data_afiliado.setTelefono(telefono);
				logger.info("Insertando teléfono en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "TEL");
			}
			if(!email.equals(data_afiliado.getEmail())){
				data_afiliado.setEmail(email);
				logger.info("Insertando email en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "EMAIL");
			}
			//Insertar Bitácora
			logger.info("Insertando bitácora Sura");
			bitacoraService.insertBitacora(data_afiliado);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Sura ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
			return "registro-error";
		}

		return "venta";
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
