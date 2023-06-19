package cl.laaraucana.contratocr.controller;


import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lautaro.xi.CRM.WEB_Mobile.Ia_CargaFirmaWebFS_DT;
import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;

import cl.laaraucana.contratocr.ws.ClienteContratoCR;
import cl.laaraucana.contratocr.ws.ClienteInfoAfiliado;
import cl.laaraucana.contratocr.ws.ClienteSinacofi;
import cl.laaraucana.contratocr.ws.ConstantesRespuestasWS;
import cl.laaraucana.contratocr.entities.BitacoraEntiti;

import cl.laaraucana.contratocr.model.UsuarioVo;
import cl.laaraucana.contratocr.model.WsCargaCrm;
import cl.laaraucana.contratocr.services.BitacoraService;
import cl.laaraucana.contratocr.services.MailService;
import cl.laaraucana.contratocr.services.ReporteService;
import cl.laaraucana.contratocr.util.Configuraciones;
import cl.laaraucana.contratocr.util.UtilsPDF;
import cl.laaraucana.contratocr.vo.SalidaSinacofiVO;
import cl.laaraucana.contratocr.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.contratocr.vo.ValidacionVO;
import functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTO;
import functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTOResponse;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private BitacoraService bitaService;
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String controllerInit(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {

		return "serie";

	}


	@RequestMapping(value = { "/validaCedula.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String validacionCedula(ModelMap model, HttpServletRequest request, @ModelAttribute() UsuarioVo user,
			HttpServletResponse response) {

		// DecimalFormat df = new DecimalFormat("###,###");

		try {
			request.getSession().setAttribute("errorMsg", "");

			String rut_usuario = user.getRut();
			rut_usuario = rut_usuario.replaceAll("\\.", "").toUpperCase();
			request.getSession().setAttribute("rutLdap", rut_usuario);
			logger.info("validando Número Serie Rut: " + rut_usuario );
			try {
				rut_usuario = Integer.parseInt(rut_usuario.split("-")[0]) + "-" + rut_usuario.split("-")[1];
			} catch (Exception e) {
				model.addAttribute("errorMsg", "serie_error");
				return "serie";
			}

			ClienteInfoAfiliado info = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO res = info.getDataAfiliado(rut_usuario);

			if (res.getNombreCompleto() == null) {
				logger.info("RUT " + user.getRut() + " no es afiliado, se muestra mensaje de error al ejecutivo");
				model.addAttribute("errorMsg", "rut_sininfo");
				return "serie";
			}

			if (!validaCedula(rut_usuario.replace("-", ""),
					user.getSerie()).equals("OK")) {
				logger.warn("Respuesta no satisfactoria de Sinacofi:");
				model.addAttribute("errorMsg", "serie_error");
				model.addAttribute("serie", user.getSerie());
				return "serie"; 
			}
			 

			request.getSession().setAttribute("nombre", res.getNombreCompleto());

			request.getSession().setAttribute("serie", "OK");
			request.getSession().setAttribute("serieNum", user.getSerie());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error Validación Numero Serie:  ", e);
			request.getSession().setAttribute("errorMsg", "operacion_error");
			return "serie";
		}

		return "redirect:/contrato.do";
		
	}

	private static String validaCedula(String rut, String serie) throws Exception {

		// SINACOFI
		logger.info("Se valida cotra Sinacofi");
		ClienteSinacofi cli = new ClienteSinacofi();
		String mensaje = "";
		SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut.replace(".", "").replace("-", ""), serie);
		if (respSina != null && respSina.getCodigoError() == ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS) {
			String codigoSina = respSina.getCodigoRetorno();
			logger.info("Respuesta Sinacofi para Rut " + rut + ", codigo retorno= " + codigoSina);
			if (respSina.getCodigoRetorno().equals("10000")) {
				logger.info("Cedula Vigente=" + respSina.getCedulaVigente() + ".");
				if (respSina.getCedulaVigente().equals("NO")) {
					mensaje = "serie_error";
				} else {
					mensaje = "OK";
				}
			} else {
				if (respSina.getCodigoRetorno().equals("10001")) {
					mensaje = "Error en parámetros de entrada";
				} else if (codigoSina.equals("10002")) {
					mensaje = "Error interno del servicio";
				} else if (codigoSina.equals("10003")) {
					mensaje = "Error en la autenticación del usuario";
				} else if (codigoSina.equals("10004")) {
					mensaje = "Error de permiso";
				} else if (codigoSina.equals("10005")) {
					mensaje = "Error RUT inválido";
				}
				logger.warn("Respuesta de error Sinacofi:" + mensaje);
			}
		}

		return mensaje;

		// FIN SINACOFI
	}

	@RequestMapping(value = { "/file.do" }, method = RequestMethod.GET)
	public String getFile(ModelMap model, HttpServletRequest request, @RequestParam("id") String id,
			HttpServletResponse response) {

		try {
			String rut_usuario = (String) request.getSession().getAttribute("rutLdap");
			if(rut_usuario==null){
				return "redirect:/init.do";
			}
			String nombre= (String)request.getSession().getAttribute("nombre");
			String PDF= reporteService.generarReport(request, response, rut_usuario, nombre);
			request.getSession().setAttribute("PDF", PDF);
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al descargar el archivo ", e);
			return null;
		}

	}

	@RequestMapping(value = { "/contrato.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String getContrato(ModelMap model, HttpServletRequest request, @ModelAttribute() UsuarioVo user,
			HttpServletResponse response) {

		DecimalFormat df = new DecimalFormat("###,###");

		try {

			String rut_usuario = (String) request.getSession().getAttribute("rutLdap");
			if(rut_usuario==null){
				return "redirect:/init.do";
			}
			// String[] rut = user.getRut().replace(".", "").split("-");
			String[] rut = rut_usuario.split("-");
			String vacio = "SI";

			List<BitacoraEntiti> contrato = bitaService.getCotratoByRut(Long.parseLong(rut[0]));

			if (contrato.size()==0) {
				model.addAttribute("id_ccr", rut_usuario.replaceAll("-", ""));
				vacio = "NO";
			}else{
				request.getSession().setAttribute("contrato", contrato.get(0));
			}
			request.getSession().setAttribute("rutUser", rut_usuario);
			request.getSession().setAttribute("vacio", vacio);
			

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 2  ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}


	@RequestMapping(value = { "/aprobar.do" }, method = RequestMethod.POST)
	public String AprobacionCredito(ModelMap model, @ModelAttribute() UsuarioVo user, HttpServletRequest request, HttpServletResponse response) {

		try {
			String email= user.getEmail();
			String rut_usuario= (String) request.getSession().getAttribute("rutLdap");
			String nombre= (String) request.getSession().getAttribute("nombre");
			String rutaPDF= (String) request.getSession().getAttribute("PDF");
			logger.info("Enviando contrato a CRM para Rut: " +  rut_usuario);
			if(rut_usuario==null){
				return "redirect:/init.do";
			}
			long rutlong= Long.parseLong(rut_usuario.split("-")[0]);
			String dv= rut_usuario.split("-")[1];
			
			ValidacionVO validaVO= (ValidacionVO)request.getSession().getAttribute("validaVO");
			
			//INVOCANDO CRM
			ClienteContratoCR clienteCCR= new ClienteContratoCR();
			String enviado_CRM= clienteCCR.declaraContrato(rut_usuario);
			logger.info("Contrato enviado a CRM: " + enviado_CRM);
			
			//GRABANDO BITACORA
			String ipremota= (String)request.getRemoteAddr();
			bitaService.insertBitacora(rutlong, dv, validaVO.getIdChallenge(), validaVO.getCodigoRetorno(), validaVO.getDescrpcionSinacofi(), ipremota);
			
			//ENVIANDO mail
			logger.info("Enviando contrato por correo a " + email);
			mailService.sendEmailMandato(email, "Contrato Uso Canales Remotos - La Araucana",
					UtilsPDF.emailCliente(nombre), rut_usuario, rutaPDF);

			request.setAttribute("mesage", "");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 4  ", e);

			return "registro_error";
		}

		return "registro-exito";
	}

	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().removeAttribute("sesion");
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
