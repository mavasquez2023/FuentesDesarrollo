package cl.laaraucana.muvu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import cl.araucana.core.util.Rut;
import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.services.BitacoraService;
import cl.laaraucana.muvu.services.MailService;
import cl.laaraucana.muvu.services.ProcesosMuvu;
import cl.laaraucana.muvu.services.ResumenService;
import cl.laaraucana.muvu.services.URLConnection;
import cl.laaraucana.muvu.util.Configuraciones;
import cl.laaraucana.muvu.util.Utils;
import cl.laaraucana.muvu.util.UtilsFecha;
import cl.laaraucana.muvu.vo.AfiliadoVo;
import cl.laaraucana.muvu.vo.LoginVO;
import cl.laaraucana.muvu.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.muvu.ws.ClienteInfoAfiliado;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private URLConnection urlConnection;
	
	@Autowired
	private BitacoraService bitacoraService;
	
	@Autowired
	private ResumenService resumenService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	ProcesosMuvu procesosMuvu;
	
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		return "ingreso";
	}
	
	@RequestMapping(value = { "/ejecutarCronta.do" }, method = RequestMethod.GET)
	public String cronta(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String periodo= request.getParameter("periodo");
			if(procesosMuvu.procesarAltas(periodo)){
				return "ingreso";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value = { "/inscripcion.do" }, method = RequestMethod.GET)
	public String inscripcionConvenio(ModelMap model, @RequestParam("correo") String correo, HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Ingresando inscripción");
			request.getSession().setAttribute("email", correo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Muvu ", e);
			request.getSession().setAttribute("msgError", e.getMessage());
			return "soporte";
		}

		return "solicitud";
	}
	
	@RequestMapping(value = { "/solicitud.do" }, method = RequestMethod.GET)
	public String login(ModelMap model, @RequestParam("rut") String rut, HttpServletRequest request, HttpServletResponse response) {
		
		AfiliadoVo data_afiliado= new AfiliadoVo();
		
		try {
			boolean exito=true;
			request.getSession().setAttribute("errorMsg", "");
			
			rut = rut.replaceAll("\\.", "").toUpperCase();
			if(rut.indexOf("-")==-1){
				rut= rut.substring(0, rut.length()-1) + "-" + rut.substring(rut.length()-1);
			}
			
			String email= (String)request.getSession().getAttribute("email");
			//seteando datos afiliado
			data_afiliado.setRut(rut);
			data_afiliado.setEmail(email);
			
			//Conectando a CRM
			logger.info("Validando calidad afiliado en CRM para " + rut);
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);
			
			//seteando nombre afiliado
			if(salida.getNombreCompleto()!=null){
				data_afiliado.setNombre(salida.getNombreCompleto());
			}
			
			//seteando Causa error de existir
			String causa="";
			String descripcion_causa="";
			if(salida.isDeudordirecto()){
				logger.info("Rut es deudor directo " + rut);
				causa="noafiliado";
				descripcion_causa= Configuraciones.getConfig("mensaje.error." + causa);
				exito=false;
			}if(salida.isPensionado()){
				logger.info("Rut es pensionado" + rut);
				causa="sinrol";
				descripcion_causa= Configuraciones.getConfig("mensaje.error." + causa);
				exito=false;
			}
			if(exito){
				//Validando que correo no está asociado a otro Rut
				Resumen resumen= resumenService.findByRut(Integer.parseInt(rut.split("-")[0]));
				if(resumen!=null){
					if(!resumen.getEmail().equals(email)){
						causa="duplicado";
						descripcion_causa= Configuraciones.getConfig("mensaje.error." + causa);
						exito=false;
					}
					if(resumen.getFechaBaja()!=null){
						String meses_baja= Configuraciones.getConfig("meses.baja");
						Date Xmeses_atras= UtilsFecha.sumMonths(new Date(), Integer.parseInt(meses_baja)*-1);
						Date fecha_baja= (Date)resumen.getFechaBaja();
						if(Xmeses_atras.compareTo(fecha_baja)<0){
							causa="carencia";
							descripcion_causa= Configuraciones.getConfig("mensaje.error." + causa);
							exito=false;
						}
					}
				}
			}
			//Se setea descripcion causa en caso de error
			data_afiliado.setCausa(descripcion_causa);
			//Afiliado en esta etapa queda siempre No Vigente
			data_afiliado.setEstado("0");
			request.getSession().setAttribute("data", data_afiliado);
			
			JSONObject salidaJSON = new JSONObject();
			if(exito){
				salidaJSON.put("nombre", salida.getNombreCompleto());
				salidaJSON.put("rut", new Rut(rut.split("-")[0]).toString());
			}else{
				salidaJSON.put("causa", causa);
			}
			String html = salidaJSON.toJSONString();
			registrarSalida(response, html);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Muvu ", e);
			data_afiliado.setCausa("Servicio No Disponible");
			request.getSession().setAttribute("data", data_afiliado);
		}

		return "soporte";
	}
	
	public void registrarSalida(HttpServletResponse response, String result) {
		response.setCharacterEncoding("iso-8859-1");
		response.setContentType("text/html");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/aprobar.do" }, method = RequestMethod.POST)
	public String bienvenida(ModelMap model, @ModelAttribute LoginVO form, HttpServletRequest request, HttpServletResponse response) {
		
		AfiliadoVo data_afiliado= (AfiliadoVo)request.getSession().getAttribute("data");
		try {
			
			logger.info("Ingresando aprobacion");
			String fechaNacimiento = form.getFecha_nacimiento();
			data_afiliado.setFechaNacimiento(UtilsFecha.stringToDate2(fechaNacimiento));
			//Afiliado en esta etapa queda siempre No Vigente
			data_afiliado.setEstado("0");
			
			if(UtilsFecha.isAdultoMayor(fechaNacimiento)){
				String identificador="adultomayor";
				String causa= Configuraciones.getConfig("mensaje.error." + identificador);
				data_afiliado.setCausa(causa);
				request.getSession().setAttribute("data", data_afiliado);	
				
				//Insertar Bitácora
				//logger.info("Insertando bitácora MUVU NOK");
				//bitacoraService.insertBitacora(data_afiliado);
				
				return "soporte";
			}
			
			//Invocando servicio REST
			String url_token= Configuraciones.getConfig("token.convenio.muvu");
			String url_muvu= Configuraciones.getConfig("url.convenio.muvu");
			
			Map<String, String> autenticacion= new HashMap<String, String>();
			autenticacion.put("email", data_afiliado.getEmail());
			autenticacion.put("document_type", "rut");
			autenticacion.put("document_number", data_afiliado.getRut());
			
			String params_json= urlConnection.setParams(autenticacion);
			
			//Se obtiene TOKEN
			Properties token= new Properties();
			token.setProperty("token", url_token);
			String resultado= urlConnection.post(params_json, url_muvu, token);
			
			if(resultado.equals("OK")){
				logger.info("Registro en MUVU exitoso!, insertando tabla resumen");
				//Insert Resumen
				resumenService.updateResumen(data_afiliado);
				
				//ENVIANDO mail
				logger.info("Enviando contrato por correo a " + data_afiliado.getEmail());
				mailService.sendEmailClie(data_afiliado.getEmail(), Configuraciones.getConfig("mail.asunto.cliente"),
						Utils.emailCliente(data_afiliado.getNombre(), Configuraciones.getConfig("url.cliente.condiciones")), data_afiliado.getRut(), null);
			}else{
				data_afiliado.setCausa(resultado);
			}
			
			//Insertar Bitácora
			logger.info("Insertando bitácora MUVU OK");
			bitacoraService.insertBitacora(data_afiliado);
			
			if(!resultado.equals("OK")){
				return "soporte";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error MUVU ", e);
			data_afiliado.setCausa("Servicio No Disponible");
			request.getSession().setAttribute("data", data_afiliado);
			return "soporte";
		}

		return "bienvenida";
	}
	
	@RequestMapping(value = { "/soporte.do" }, method = RequestMethod.GET)
	public String soporte(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
			
		AfiliadoVo data_afiliado= (AfiliadoVo)request.getSession().getAttribute("data");
		try {
			logger.info("Ingresando soporte");
			data_afiliado= (AfiliadoVo)request.getSession().getAttribute("data");
			String identificador= request.getParameter("causa");
			String causa= Configuraciones.getConfig("mensaje.error." + identificador);
			data_afiliado.setCausa(causa);
			//Afiliado en esta etapa queda siempre No Vigente
			data_afiliado.setEstado("0");
			
			request.getSession().setAttribute("data", data_afiliado);
			
			//Insertar Bitácora
			//logger.info("Insertando bitácora MUVU");
			//bitacoraService.insertBitacora(data_afiliado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error Sura ", e);
			data_afiliado.setCausa("Servicio No Disponible");
			request.getSession().setAttribute("data", data_afiliado);
		}

		return "soporte";
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

			return "soporte";
		}

		return null;
	}
	

}
