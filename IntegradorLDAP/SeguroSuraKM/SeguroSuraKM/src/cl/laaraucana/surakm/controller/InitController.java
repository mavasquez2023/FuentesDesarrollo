package cl.laaraucana.surakm.controller;

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
import cl.laaraucana.surakm.services.TelefonoService;
import cl.laaraucana.surakm.ibatis.vo.BitacoraSuraVo;
import cl.laaraucana.surakm.ibatis.vo.FormSuraVo;
import cl.laaraucana.surakm.services.BitacoraService;
import cl.laaraucana.surakm.services.ContactoDHWServicesImpl;
import cl.laaraucana.surakm.services.ContactoDWHServices;
import cl.laaraucana.surakm.util.Configuraciones;
import cl.laaraucana.surakm.vo.AfiliadoVo;
import cl.laaraucana.surakm.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.surakm.ws.ClienteInfoAfiliado;




@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	
	@Autowired
	private ContactoDWHServices contactoDHW;
	
	@Autowired
	private BitacoraService bitacoraService;
	
	@Autowired
	private TelefonoService telefonoService;
	
		
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
			data_afiliado.setCelular(celular);
			data_afiliado.setTelefono(telefono);
			data_afiliado.setEmail(email);
			
			String id= Configuraciones.getConfig("url.id.sura");
			String rut= data_afiliado.getRut().split("-")[0];
			String dv= data_afiliado.getRut().split("-")[1];
			
			FormSuraVo formSura= new FormSuraVo();
			formSura.setRut(rut);
			formSura.setDv(dv);
			formSura.setCelular(celular);
			formSura.setEmail(email);
			formSura.setId(id);
			
			/*if(data_afiliado.getCelular().length()==9){
				formSura.setCelular(data_afiliado.getCelular().substring(1));
			}else{
				formSura.setCelular(data_afiliado.getCelular());
			}
			*/
			
			logger.info("Parámetros a enviar, Rut:"  + formSura.getRut() + ", dv:" + formSura.getDv() + ", celular:" + formSura.getCelular() + ", email:" + formSura.getEmail() + ", id=" + formSura.getId() );
			request.getSession().setAttribute("venta", formSura);
			
			//Insertar Bitácora
			logger.info("insertando bitácora en dbo.bitacoraKmSura");
			bitacoraService.insertBitacora(data_afiliado);
			
			request.getSession().setAttribute("venta", formSura);
			if(!celular.equals(data_afiliado.getCelular())){
				logger.info("Insertando celular en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "MOB");
			}
			if(!telefono.equals(data_afiliado.getTelefono()) && !telefono.equals("")){
				logger.info("Insertando teléfono en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "TEL");
			}
			if(!email.equals(data_afiliado.getEmail())){
				logger.info("Insertando email en tabla DWH");
				bitacoraService.insertDatosContacto(data_afiliado, "EMAIL");
			}
			
			
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
