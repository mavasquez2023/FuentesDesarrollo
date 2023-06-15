package cl.laaraucana.ventafullweb.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;

import cl.laaraucana.servicios.simulaCredito.Response;
import cl.laaraucana.ventafullweb.dao.ComunasDao;
import cl.laaraucana.ventafullweb.dao.RegionesDao;
import cl.laaraucana.ventafullweb.dao.SucursalesDao;
import cl.laaraucana.ventafullweb.dto.ComunasDto;
import cl.laaraucana.ventafullweb.dto.RegionesDto;
import cl.laaraucana.ventafullweb.dto.SucursalesDto;
import cl.laaraucana.ventafullweb.services.AfiliadoService;
import cl.laaraucana.ventafullweb.services.AgendaService;
import cl.laaraucana.ventafullweb.services.BitacoraService;
import cl.laaraucana.ventafullweb.services.MailService;
import cl.laaraucana.ventafullweb.services.ReporteService;
import cl.laaraucana.ventafullweb.singleton.CotizacionUFSingleton;
import cl.laaraucana.ventafullweb.util.AfiliadoUtils;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.util.GoogleRecaptcha;
import cl.laaraucana.ventafullweb.util.Utils;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;
import cl.laaraucana.ventafullweb.vo.AgendaVo;
import cl.laaraucana.ventafullweb.vo.AnexoAfiliadoVO;
import cl.laaraucana.ventafullweb.vo.AprobadoOpcionSeleccionadaVo;
import cl.laaraucana.ventafullweb.vo.FormCrearOfertaVO;
import cl.laaraucana.ventafullweb.vo.FormLoginVO;
import cl.laaraucana.ventafullweb.vo.SalidaCreaOfertaVO;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;
import cl.laaraucana.ventafullweb.vo.SalidaSinacofiVO;
import cl.laaraucana.ventafullweb.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.ventafullweb.vo.SolicitanteVo;
import cl.laaraucana.ventafullweb.ws.ClientRESGenesys;
import cl.laaraucana.ventafullweb.ws.ClienteCotizacion;
import cl.laaraucana.ventafullweb.ws.ClienteCotizacionImpl;
import cl.laaraucana.ventafullweb.ws.ClienteCreaOferta;
import cl.laaraucana.ventafullweb.ws.ClienteEvaluadorModeloAIS;
import cl.laaraucana.ventafullweb.ws.ClienteInfoAfiliado;
import cl.laaraucana.ventafullweb.ws.ClienteOfertasVigentes;
import cl.laaraucana.ventafullweb.ws.ClienteSimulaCredito;
import cl.laaraucana.ventafullweb.ws.ClienteSinacofi;
import cl.laaraucana.ventafullweb.ws.ClienteValidaCredito;
import cl.laaraucana.ventafullweb.ws.ConstantesRespuestasWS;


@Controller
public class InitController {
	
	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	private AfiliadoService afiliadoService;
	
	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private ReporteService reporteService;
	
	@Autowired
	private BitacoraService bitacoraService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getLogin (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Venta de créditos full Web");
		
		AprobadoOpcionSeleccionadaVo opcionSeleccionada = null;		
		request.getSession().setAttribute("aprobadoConOpcionesSeleccion", opcionSeleccionada);
		
		SalidaEvaluadorModeloAISVo respuestaAIS = null;		
		request.getSession().setAttribute("respuestaMotorAIS", respuestaAIS);
		request.getSession().setAttribute("montoMaximoAIS", respuestaAIS);
		
		return "login";
	}
	
	/*
	@RequestMapping(value = { "/validaAfiliado.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getValidaAfiliado (
			@RequestBody FormLoginVO data,
			Model model,
			HttpServletRequest request, 
			HttpServletResponse response
		) {
		
		HashMap<String,String> respuesta = new HashMap<String,String>();
		
		logger.info("Iniciando ValidacionAfiliado");
		
		String celular = data.getCelularFormulario();
		String rutAfiliado = data.getRutAfiliadoFormulario();
		String serie = data.getSerieFormulario();
		String captcha = data.getCaptchaFormulario();
		
		boolean captchaValido = false;
		
		try {
			captchaValido = GoogleRecaptcha.isValid(captcha);
		} catch (IOException | ParseException | JSONException e1) {
			e1.printStackTrace();
		}
		
		if(captchaValido) {
			try {
				SolicitanteVo solicitante =new SolicitanteVo();
				solicitante.setCelular(celular.replaceAll("\\+", ""));
				solicitante.setRut(rutAfiliado.replaceAll("\\.", ""));
				solicitante.setNumeroSerie(serie);
				solicitante.setCaptcha(captcha);
		
				AfiliadoVo afiliado= afiliadoService.getDataAfiliado(solicitante);
				
				String saltar= validaAfiliado(model, afiliado);
				if(saltar.equals("paso1")) {
					String url_iframe= Configuraciones.getConfig("url.laaraucana.iframe");
					String encoded=solicitante.getCelular()+":"+solicitante.getRut()+":"+solicitante.getNumeroSerie();
					String token= Base64.getEncoder().encodeToString(encoded.getBytes());
					String url = url_iframe+"?token=" + token;
					
					respuesta.put("codigoRespuesta", "1");
					respuesta.put("descRespuesta", url);
					
					return respuesta;
				}else {
					respuesta.put("codigoRespuesta", "2");
					respuesta.put("descRespuesta", "login");
					
					return respuesta;
				}
			} catch (Exception e) {
				String error = "Error al obtener informacion del afiliado";
				logger.info(error + ": " + e.toString());
				
				respuesta.put("codigoRespuesta", "2");
				respuesta.put("descRespuesta", error);
				
				return respuesta;
			}
		} else {
			String error = "Error al validar el captcha, intente recargando la p&aacute;gina.";
			logger.info(error);
			
			respuesta.put("codigoRespuesta", "2");
			respuesta.put("descRespuesta", error);
			
			return respuesta;
		}
				
		
	}
	*/
	
	@RequestMapping(value = { "/validaAfiliado.do" }, method = RequestMethod.POST)
	public String validaAfiliado (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando ValidacionAfiliado");
		String celular = request.getParameter("celular-login");
		String rutAfiliado = request.getParameter("rut-credito-social");
		String serie = request.getParameter("serie");
		String captcha = request.getParameter("g-recaptcha-response");
		
		// Recaptcha v3
		/* boolean captchaValido = false;
		
		try {
			captchaValido = GoogleRecaptcha.isValid(captcha);
		} catch (IOException | ParseException | JSONException e1) {
			e1.printStackTrace();
		}
		
		if(captchaValido) {
		*/
			try {
				SolicitanteVo solicitante =new SolicitanteVo();
				solicitante.setCelular(celular.replaceAll("\\+", ""));
				solicitante.setRut(rutAfiliado.replaceAll("\\.", ""));
				solicitante.setNumeroSerie(serie);
				solicitante.setCaptcha(captcha);
		
				AfiliadoVo afiliado= afiliadoService.getDataAfiliado(solicitante);
				
				String saltar= validaAfiliado(model, afiliado);
				if(saltar.equals("paso1")) {
					String url_iframe= Configuraciones.getConfig("url.laaraucana.iframe");
					String encoded=solicitante.getCelular()+":"+solicitante.getRut()+":"+solicitante.getNumeroSerie();
					String token= Base64.getEncoder().encodeToString(encoded.getBytes());
					response.sendRedirect(url_iframe+"?token=" + token);
					return null;
				}else {
					return "login";
				}
			} catch (Exception e) {
				String error = "Error al obtener informacion del afiliado";
				logger.info(error + ": " + e.toString());
				model.addAttribute("mensajeError",error);
				return "login";
			}
		/*
		} else {
			String error = "Error al validar el captcha, intente recargando la p&aacute;gina.";
			logger.info(error);
			model.addAttribute("mensajeError",error);
			return "login";
		}
		*/		
		
	}
	
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.GET)
	public String getPaso1 (@RequestParam("token") String token, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		SolicitanteVo solicitante =null;
		try {
			logger.info("Iniciando Paso 1");
			
			AprobadoOpcionSeleccionadaVo opcionSeleccionada = null;		
			request.getSession().setAttribute("aprobadoConOpcionesSeleccion", opcionSeleccionada);
			
			SalidaEvaluadorModeloAISVo respuestaAIS = null;
			request.getSession().setAttribute("respuestaMotorAIS", respuestaAIS);
			
			//AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
			//if(afiliado==null && token!=null) {
			solicitante = new SolicitanteVo();
			String[] decoded= new String(Base64.getDecoder().decode(token)).split(":");
			solicitante.setCelular(decoded[0]);
			solicitante.setRut(decoded[1]);
			solicitante.setNumeroSerie(decoded[2]);

			//Obteniendo data adicional de campaña y crédito afiliado
			AfiliadoVo afiliado= afiliadoService.getDataAfiliado(solicitante);

			//Llamada a WS WSValidaCredito
			logger.info("Consultando WSValidaCredito para " + afiliado.getRutAfiliado());
			ClienteValidaCredito clienteValidaCredito = new ClienteValidaCredito();
			cl.laaraucana.servicios.validaCredito.Response resp = null;
			
			try {
				resp = clienteValidaCredito.getValidaCredito(afiliado);
				afiliado = AfiliadoUtils.agregaInfoCreditoAfiliado(afiliado, resp);
			} catch(Exception e) {
				logger.error("Error al ejecutar WS ValidaCredito. " + e);
				return null;
			}
					
			logger.info("Verificando si afiliado tiene campaña vigente");
			model.addAttribute("montoaSimular", "40.000");
			if(afiliado.getCampagnaVigente() == 1) {
				logger.info("Tiene campaña vigente");
				if( afiliado.getFechaVigencia()!=null && afiliado.getMontoCampagna()!=0) {
					model.addAttribute("campagna", "");
					model.addAttribute("montoaSimular", AfiliadoUtils.formateaVal(afiliado.getMontoCampagna()));
					model.addAttribute("montoCampagna", AfiliadoUtils.formateaVal(afiliado.getMontoCampagna()));
					model.addAttribute("fechaCampagna", AfiliadoUtils.formateaFecha(afiliado.getFechaVigencia()));
				} else {
					logger.info("No hay información de campaña disponible");	
					model.addAttribute("campagna", "hidden='true'");
				}
			} else {
				logger.info("No tiene campaña vigente");
				model.addAttribute("campagna", "hidden='true'");
			}			
			
			String montoMaximo = (String) request.getSession().getAttribute("montoMaximoAIS");
			
			if(montoMaximo == null) {
				montoMaximo = "35000000";
			}	
			
			logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Paso 1 - Simulación", "Valida Credito", "Codigo respuesta: " + resp.getCodigoRespuesta());
			
			SucursalesDao sucursalesDao = new SucursalesDao();
			List<SucursalesDto> sucursales = sucursalesDao.getSucursales();
			model.addAttribute("sucursales",sucursales);
			request.getSession().setAttribute("afiliado", afiliado);
			model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado());
			model.addAttribute("montoMaximo", montoMaximo);
			model.addAttribute("montoMaximoLabel", AfiliadoUtils.formateaVal(montoMaximo));
			
			return "paso1";
		} catch (Exception e) {
			String error = "Error al obtener informacion del afiliado";
			logger.info(error + ": " + e.toString());
			model.addAttribute("mensajeError",error);
			model.addAttribute("celular-login", solicitante.getCelular());
			model.addAttribute("rut-credito-social", solicitante.getRut());
			model.addAttribute("serie", solicitante.getNumeroSerie());
			return "login";
		}
	}
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.POST)
	public String volverASimular (Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.info("Volver a simular Paso 1");
			
			AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
			
			logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Paso 1 - Simulación", "--", "El afiliado simula nuevo monto.");
			
			AprobadoOpcionSeleccionadaVo opcionSeleccionada = null;		
			request.getSession().setAttribute("aprobadoConOpcionesSeleccion", opcionSeleccionada);
			
			SalidaEvaluadorModeloAISVo respuestaAIS = null;
			request.getSession().setAttribute("respuestaMotorAIS", respuestaAIS);
			
			logger.info("Verificando si afiliado tiene campaña vigente");
			model.addAttribute("montoaSimular", "40.000");
			if(afiliado.getCampagnaVigente() == 1) {
				logger.info("Tiene campaña vigente");
				if( afiliado.getFechaVigencia()!=null && afiliado.getMontoCampagna()!=0) {
					model.addAttribute("campagna", "");
					model.addAttribute("montoaSimular", AfiliadoUtils.formateaVal(afiliado.getMontoCampagna()));
					model.addAttribute("montoCampagna", AfiliadoUtils.formateaVal(afiliado.getMontoCampagna()));
					model.addAttribute("fechaCampagna", AfiliadoUtils.formateaFecha(afiliado.getFechaVigencia()));
				} else {
					logger.info("No hay información de campaña disponible");	
					model.addAttribute("campagna", "hidden='true'");
				}
			} else {
				logger.info("No tiene campaña vigente");
				model.addAttribute("campagna", "hidden='true'");
			}
			
			String montoMaximo = (String) request.getSession().getAttribute("montoMaximoAIS");
			
			if(montoMaximo == null) {
				montoMaximo = "35000000";
			}	
			
			SucursalesDao sucursalesDao = new SucursalesDao();
			List<SucursalesDto> sucursales = sucursalesDao.getSucursales();
			model.addAttribute("sucursales",sucursales);
			request.getSession().setAttribute("afiliado", afiliado);
			model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado());	
			model.addAttribute("montoMaximo", montoMaximo);
			model.addAttribute("montoMaximoLabel", AfiliadoUtils.formateaVal(montoMaximo));
			
			return "paso1";
		} catch (Exception e) {
			String error = "Error al volver a simular ";
			logger.warn(error + ": " + e.toString());
			return "login";
		}
	}

	
	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String getPaso2 (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		logger.info("Iniciando Paso 2");
		
		AprobadoOpcionSeleccionadaVo opcionSeleccionada = (AprobadoOpcionSeleccionadaVo) request.getSession().getAttribute("aprobadoConOpcionesSeleccion");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		String monto = null;
		String cuotas = null;
		String sucursal = null;
		String check = null;
		
		if(opcionSeleccionada == null) {
			monto = request.getParameter("valor");
			cuotas = request.getParameter("monto");
			sucursal = request.getParameter("sucursal");
			check = request.getParameter("seguro-cesantia");
		} else {
			monto = opcionSeleccionada.getOpcionMonto();
			cuotas = opcionSeleccionada.getOpcionPlazo();
			sucursal = afiliado.getSucursal();
		}	
		
		afiliado.setMontoSimulacion(Integer.parseInt(monto.replace(",", "").replace(".", "")));
		afiliado.setCuotas(Integer.parseInt(cuotas));
		afiliado.setSucursal(sucursal);
		afiliado.setSeguroCesantia(check!=null?"X":"");
		afiliado.setSeguroDesgravamen("X"); //Juan Cubillos 11 nov 2022, 12:58
		ClienteSimulaCredito simulador = new ClienteSimulaCredito();
		Response resp = simulador.getValidaCredito(afiliado);  
		String respuesta = resp.getRESULT_CODE();
		if(respuesta.equals("1")) {
			afiliado = AfiliadoUtils.agregaInfoAfiliado(afiliado, resp);
		}
		
		try {
			logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Paso 2 - Resultado simulación", "Simula crédito", "Codigo respuesta: " + respuesta);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ClienteValidaCredito clienteValidaCredito = new ClienteValidaCredito();	
		cl.laaraucana.servicios.validaCredito.Response respValidaCredito = clienteValidaCredito.getValidaCredito(afiliado); 
		
		afiliado = AfiliadoUtils.agregaInfoCreditoAfiliado(afiliado, respValidaCredito);
		
		String declaracionJurada = respValidaCredito.getDECLARACIONJURADA();
		afiliado.setDeclaracionJurada(declaracionJurada);
		
		ClienteInfoAfiliado cia = new ClienteInfoAfiliado();
		SalidainfoAfiliadoVO resCia = null;
		
		try {
			resCia = cia.getDataAfiliado(afiliado.getRutAfiliado());
		} catch (AxisFault e1) {
			e1.printStackTrace();
		}
		
		List<AnexoAfiliadoVO> arrayAnexos = resCia.getAnexos();
		
		int contTipoAnexo = 0;
		for (AnexoAfiliadoVO anexoAfiliadoVO : arrayAnexos) {
			int tipoAnexo = anexoAfiliadoVO.getTipoAnexo();	
			if(tipoAnexo == 1) {
				contTipoAnexo++;
			}
		}
		
		request.getSession().setAttribute("afiliado", afiliado); 
		String contactar = Utils.esHorarioHabil()?"":"hidden='hidden'";
		model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado()); 
		model.addAttribute("monto", monto);
		model.addAttribute("cuotas", cuotas);
		model.addAttribute("montoCuota", AfiliadoUtils.formateaVal(afiliado.getMontoCuota()));
		model.addAttribute("tasaInteresMensual", afiliado.getTasaInteresMensual());
		model.addAttribute("tasaInteresAnual", afiliado.getTasaInteresAnual());
		model.addAttribute("cae", afiliado.getCae());
		model.addAttribute("seguroDesgravamen", AfiliadoUtils.formateaVal(afiliado.getCostoTotalDesgravamen()));
		model.addAttribute("costoTotal", AfiliadoUtils.formateaVal(afiliado.getCostoTotalCredito()));
		model.addAttribute("impuesto", AfiliadoUtils.formateaVal(afiliado.getImpuesto()));
		model.addAttribute("gastoNotaria", AfiliadoUtils.formateaVal(afiliado.getGastoNotaria()));
		model.addAttribute("valorMensualSeguroCesantia", AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroCesantia()));
		model.addAttribute("valorMensualSeguroDesgravamen", AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroDesgravamen()));
		model.addAttribute("montoCuotaSinSeguro", AfiliadoUtils.formateaVal(afiliado.getMontoCuota() - afiliado.getValorMensualSeguroDesgravamen()));
		model.addAttribute("pagoPrimeraCuota", afiliado.getPagoPrimeraCuota());
		model.addAttribute("razonSocial", afiliado.getRazonSocial());
		model.addAttribute("inscripcionPension", afiliado.getInscripcionPension());
		model.addAttribute("contactar", contactar);
		
		if(resCia.isPensionado()== true && resCia.isTrabajador() == true) {
			model.addAttribute("dobleAfiliacion", true);
			try {
				logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Paso 2 - Resultado simulación", "--", "Derivado a click to call por doble afiliación");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else {
			model.addAttribute("dobleAfiliacion", false);
		}
		
		if(contTipoAnexo > 1) {
			model.addAttribute("relacionLaboral", true);
			try {
				logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Paso 2 - Resultado simulación", "--", "Derivado a click to call por tener más de una relación laboral");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else {
			model.addAttribute("relacionLaboral", false);
		}
		
		//Se guarda simulación en Bitacora
		try {
			bitacoraService.saveBitacoraSimulacion(afiliado);
		}catch (Exception e) {
			logger.warn(e.getMessage());
			e.printStackTrace();
		}
		return "paso2";
	}
	
	
	@RequestMapping(value = { "/agenda.do" }, method = RequestMethod.GET)
	public String getAgenda (
			@ModelAttribute SolicitanteVo form, 
			@RequestParam("autorizar") String autorizar, 
			@RequestParam("mens") String mensajeContacto,
			Model model, 
			HttpServletRequest request, 
			HttpServletResponse response
		) {
		logger.info("Iniciando Agenda");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		afiliado.setAutorizar((autorizar.equals("1") ?"SI":"NO"));
		
		List<AgendaVo> agenda = Utils.obtieneAgenda("");
		String mes = agenda.get(0).getFecha();
		mes = Utils.nombreMes(mes);
		String annio="";
		String ultimaFecha="";
		String primeraFecha="";
		for(AgendaVo agen : agenda) {
			//mes = Utils.nombreMes(agen.getFecha());
			annio = agen.getFecha().substring(6);
			ultimaFecha = agen.getFecha();
			if(primeraFecha.length()==0) {
				primeraFecha = agen.getFecha();
			}
		}
		
		String mensaje = "";		
		
		if(mensajeContacto.equals("1")) {
			mensaje = "Te llamaremos al +" + afiliado.getCelular() +" para continuar, ind&iacute;canos d&iacute;a y hora";			
		} else if(mensajeContacto.equals("2")) {
			mensaje = "Tu cr&eacute;dito es factible de ser entregado, por favor selecciona d&iacute;a y rango horario para concretar.";
		}
		
		String contactar = Utils.esHorarioHabil()?"":"hidden='hidden'";
		
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("telefono", afiliado.getCelular());
		model.addAttribute("agenda", agenda);
		model.addAttribute("fecha", mes + " " + annio);
		model.addAttribute("ultimaFecha", ultimaFecha);
		model.addAttribute("primeraFecha", primeraFecha);
		model.addAttribute("contactar", contactar);
		
		request.getSession().setAttribute("afiliado", afiliado);		
		
		return "agenda";
	}
	
	
	@RequestMapping(value = { "/agendar.do" }, method = RequestMethod.GET)
	public String agendarLlamada (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Agendamiento");
		String mes="";
		String annio="";
		String ultimaFecha="";
		String primeraFecha="";
		String fechaSeleccionada="";
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		SalidaEvaluadorModeloAISVo respuestaMotorAIS = (SalidaEvaluadorModeloAISVo) request.getSession().getAttribute("respuestaMotorAIS");
		OfertasVigentes_DT respuestaWSOfertasVigentes = (OfertasVigentes_DT) request.getSession().getAttribute("respuestaWSOfertasVigentes");
		String fechaInicio = request.getParameter("primerDiaAgenda");
		String horario = request.getParameter("horario");
		String selected = request.getParameter("diaAgenda");
		String reintento = (String)request.getSession().getAttribute("reintento");
		if(selected.length() == 0) {
			request.getSession().removeAttribute("reintento");
			model.addAttribute("texto1", "Te devolveremos el llamado a m&aacute;s tardar en 24 hrs h&aacute;biles");
			model.addAttribute("texto2", "");
			return "contacto";
		}
		List<AgendaVo> agenda = Utils.obtieneAgenda(fechaInicio);
		int numDia = Integer.parseInt(selected.substring(3));
		int aux = 1;
		for(AgendaVo agen : agenda) {
			//mes = Utils.nombreMes(agen.getFecha());
			annio = agen.getFecha().substring(6);
			ultimaFecha = agen.getFecha();
			if(primeraFecha.length()==0) {
				primeraFecha = agen.getFecha();
			}
			if(aux == numDia) {
				fechaSeleccionada = agen.getFecha();
			}
			aux++;
		}
		
		mes = agenda.get(0).getFecha();
		mes = Utils.nombreMes(mes);
		
		logger.info("fechaSeleccionada: " + fechaSeleccionada);
		logger.info("Horario: " + horario);
		model.addAttribute("fecha", mes + " " + annio);
		model.addAttribute("telefono", afiliado.getCelular());
		model.addAttribute("fechaSeleccionada", Utils.nombreFecha(fechaSeleccionada));
		model.addAttribute("rangoHorario", horario.equals("AM")?"09:00 y las 12:00":"13:00 y las 18:00");
		
		//Se invoca servicio de Genesys
		String id = agendaService.asignaCasoGenesys(afiliado, respuestaMotorAIS, respuestaWSOfertasVigentes, fechaSeleccionada, horario);
		if(id != null) {
			logger.info("Se agendo correctamente con Genesys.");
			
            try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Agendamiento", "Asigna Caso Genesys", "Se agendo correctamente a genesys con el id: " + id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "agendaFinalContactar";
		} else {
			if(reintento == null || reintento.equals("si")) {
				if(reintento == null) {
					request.getSession().setAttribute("reintento", "si");
				} else {
					request.getSession().setAttribute("reintento", "no");
				}
				String contactar = Utils.esHorarioHabil()?"":"hidden='hidden'";
				
				model.addAttribute("ultimaFecha", ultimaFecha);
				model.addAttribute("primeraFecha", primeraFecha);
				model.addAttribute("agenda", agenda);
				model.addAttribute("telefono", "");
				model.addAttribute("mensaje", "Se ha producido un error, por favor intenta agendar nuevamente");
				model.addAttribute("contactar", contactar);
				
				return "agenda";
			} else {
				agendaService.campagnaOutbaund("afiliado");
				request.getSession().removeAttribute("reintento");
				model.addAttribute("texto1", "Te devolveremos el llamado a m&aacute;s tardar en 24 hrs h&aacute;biles");
				model.addAttribute("texto2", "");
				return "contacto";
			}
		}
	}
	
	
	@RequestMapping(value = { "/paginacion.do" }, method = RequestMethod.GET)
	public String getPagina (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Paginacion");
		String fecha = request.getParameter("ultimaFecha");	
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		List<AgendaVo> agenda = Utils.obtieneAgenda(fecha);
		String mes="";
		String annio="";
		String ultimaFecha="";
		String primeraFecha="";
		for(AgendaVo agen : agenda) {
			//mes = Utils.nombreMes(agen.getFecha());
			annio = agen.getFecha().substring(6);
			ultimaFecha = agen.getFecha();
			if(primeraFecha.length()==0) {
				primeraFecha = agen.getFecha();
			}
		}
		
		mes = agenda.get(0).getFecha();
		mes = Utils.nombreMes(mes);
		
		String contactar = Utils.esHorarioHabil()?"":"hidden='hidden'";
		
		model.addAttribute("mensaje", "Te llamaremos al +" + afiliado.getCelular() +" para continuar, ind&iacute;canos d&iacute;a y hora");
		model.addAttribute("telefono", afiliado.getCelular());
		model.addAttribute("agenda", agenda);
		model.addAttribute("fecha", mes + " " + annio);
		model.addAttribute("primeraFecha", primeraFecha);
		model.addAttribute("ultimaFecha", ultimaFecha);
		model.addAttribute("paginacion" , "si");
		model.addAttribute("contactar", contactar);
		
		return "agenda";		
	}
	
	
	@RequestMapping(value = { "/contacto.do" }, method = RequestMethod.GET)
	public String getContacto (@ModelAttribute SolicitanteVo form, Model model, 
			@RequestParam("autorizar") String autorizar,
			HttpServletRequest request,
			HttpServletResponse response) {
		boolean ok = false;
		logger.info("Iniciando Contacto");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		SalidaEvaluadorModeloAISVo respuestaMotorAIS = (SalidaEvaluadorModeloAISVo) request.getSession().getAttribute("respuestaMotorAIS");
		OfertasVigentes_DT respuestaWSOfertasVigentes = (OfertasVigentes_DT) request.getSession().getAttribute("respuestaWSOfertasVigentes");
		afiliado.setAutorizar((autorizar.equals("1") ?"SI":"NO"));
		
		String id = agendaService.asignaCasoGenesys(afiliado, respuestaMotorAIS, respuestaWSOfertasVigentes, null, null);
		if(id!= null) {
			logger.info("Ejecutivo Id Conversation " + id);
	
            try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Agendamiento", "Asigna Caso Genesys", "Se agendo correctamente a genesys con el id: " + id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ok = true;
		} else {
			logger.info("No se encuentra ejecutivo disponible");
		}
		if(ok) {
			model.addAttribute("texto1", "Nos comunicaremos contigo al n&uacute;mero " + afiliado.getCelular());
			model.addAttribute("texto2", "Por favor debes estar atento a tu celular");
		} else {
			model.addAttribute("texto1", "Te devolveremos el llamado a m&aacute;s tardar en 24 hrs h&aacute;biles");
			model.addAttribute("texto2", "");
		}
		return "contacto";
	}
	
	
	@RequestMapping(value = { "/contactoPopup.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> getContactoPopup (
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response
	) {	
		HashMap<String,String> respuesta = new HashMap<String,String>();
		boolean ok = false;
		logger.info("Iniciando Contacto");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		SalidaEvaluadorModeloAISVo respuestaMotorAIS = (SalidaEvaluadorModeloAISVo) request.getSession().getAttribute("respuestaMotorAIS");
		OfertasVigentes_DT respuestaWSOfertasVigentes = (OfertasVigentes_DT) request.getSession().getAttribute("respuestaWSOfertasVigentes");
		afiliado.setAutorizar("SI");
		
		String id = agendaService.asignaCasoGenesys(afiliado, respuestaMotorAIS, respuestaWSOfertasVigentes, null, null);
	
		if(id!= null) {
			logger.info("Ejecutivo Id Conversation " + id);
			ok = true;
		} else {
			logger.info("No se encuentra ejecutivo disponible");
		}
		
		
		if(ok) {
			respuesta.put("tipoRespuesta", "1");
			respuesta.put("texto", "Nos comunicaremos contigo al n&uacute;mero +" + afiliado.getCelular());
			
		} else {
			respuesta.put("tipoRespuesta", "2");
			//respuesta.put("texto", "Te devolveremos el llamado a m&aacute;s tardar en 24 hrs h&aacute;biles");
			respuesta.put("texto", "Hemos tenido un problema al asignarle un ejecutivo, intente nuevamente.");
			
		}
		return respuesta;
		
	}
	
	@RequestMapping(value = { "/evaluarCredito.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getEvaluacionCredito (
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response
	) {
		HashMap<String,String> respuesta = new HashMap<String,String>();
		logger.info("Iniciando Evaluación del crédito.");
		
		ClienteEvaluadorModeloAIS clienteEvalModelo = new ClienteEvaluadorModeloAIS();
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		int montoSolicitado = afiliado.getMontoSimulacion();	
		
		int UF_Service = CotizacionUFSingleton.getInstance().getCotizacionUf();
		int UF_Multiplicador = Integer.parseInt(Configuraciones.getConfig("cotizacion.uf.multiplicador"));
		int valor50Uf = UF_Service * UF_Multiplicador;
		
		if(valor50Uf == 0) {
			logger.info("Error al ejecutar el WS de cotizaciones.");
			respuesta.put("tipoRespuesta", "-1");
			respuesta.put("descripcionTipoRespuesta", "WS Cotizaciones no responde");
			respuesta.put("textoRespuesta", "");
		} else if (montoSolicitado > valor50Uf) {
			respuesta.put("tipoRespuesta", "1");
			respuesta.put("descripcionTipoRespuesta", "Mayor a 50 UF");
			respuesta.put("textoRespuesta", "En conformidad con la Ley n° 21.389 Caja de Compensaci&oacute;n La Araucana est&aacute; obligada a consultar el Registro Nacional de Deudores de Pensiones de Alimentos, para lo cual ser&aacute; contactado por un Ejecutivo.");
			
			try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Derivado a Click to call", "Regla de negocio", "Mayor a 50 uf");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (afiliado.getSuperaVecesRenta().equals("SI")) {
			respuesta.put("tipoRespuesta", "6");
			respuesta.put("descripcionTipoRespuesta", "Supera Veces Renta.");
			respuesta.put("textoRespuesta", "");
			
			try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Derivado a Click to call", "Regla de negocio", "Supera Veces Renta");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(afiliado.getSuperaMaxEndeudNorm().equals("2")) {
			respuesta.put("tipoRespuesta", "7");
			respuesta.put("descripcionTipoRespuesta", "Supera Maximo enduedamiento normativo.");
			respuesta.put("textoRespuesta", "");
			
			try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Derivado a Click to call", "Regla de negocio", "Supera Maximo enduedamiento normativo");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(afiliado.getRentaPromedio().equals("0")) {
			respuesta.put("tipoRespuesta", "8");
			respuesta.put("descripcionTipoRespuesta", "Sin renta cotizada");
			respuesta.put("textoRespuesta", "");
			
			try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Derivado a Click to call", "Regla de negocio", "Afiliado no tiene renta cotizada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JSONObject respuestaJSON = clienteEvalModelo.getRespuestaWSEvalModelo(afiliado);							
			
			SalidaEvaluadorModeloAISVo respuestaAIS = mapearAIS(respuestaJSON);
			
			request.getSession().setAttribute("respuestaMotorAIS", respuestaAIS);
			request.getSession().setAttribute("montoMaximoAIS", Integer.toString(respuestaAIS.getMaxMonto()));
			
			int AISDictamen = respuestaAIS.getDictamen();
			//int AISDictamen = 8;
			
            try {
            	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Evaluacion AIS", "Motor AIS", "El Dictamen o codigo de respuesta del servicio es: " + AISDictamen);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				logger.info("Iniciando guardado del resultado en bitacora.");
				bitacoraService.saveBitacoraEvaluadorAIS(respuestaAIS);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			if(AISDictamen == 1){
				respuesta.put("tipoRespuesta", "2");
				respuesta.put("descripcionTipoRespuesta", "Dictamen1: Aprobado");
				respuesta.put("textoRespuesta", "");
			} else if(AISDictamen == 2) {
				respuesta.put("tipoRespuesta", "3");
				respuesta.put("descripcionTipoRespuesta", "Dictamen2: Aprobado Con Opciones");
				respuesta.put("textoRespuesta", "");
			} else if(AISDictamen == 3) {
				respuesta.put("tipoRespuesta", "4");
				respuesta.put("descripcionTipoRespuesta", "Dictamen3: Rechazado");
				respuesta.put("textoRespuesta", "");
			} else if(AISDictamen == 4) {
				respuesta.put("tipoRespuesta", "5");
				respuesta.put("descripcionTipoRespuesta", "Dictamen4: Derivado");
				respuesta.put("textoRespuesta", "");
			} else if(AISDictamen == 0) {
				respuesta.put("tipoRespuesta", "0");
				respuesta.put("descripcionTipoRespuesta", "Dictamen0: Alerta");
				respuesta.put("textoRespuesta", "");
			} else {
				respuesta.put("tipoRespuesta", "-2");
				respuesta.put("descripcionTipoRespuesta", "Error");
				respuesta.put("textoRespuesta", "Servicio no disponible, favor intente nuevamente");
			}
		}		
		
		return respuesta;
	}
	
	@RequestMapping(value = { "/paso3.do" }, method = RequestMethod.GET)
	public String getPaso3 (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Paso 3");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		logger.info(afiliado.getRutAfiliado());
		model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado());
		model.addAttribute("monto", AfiliadoUtils.formateaVal(afiliado.getMontoSimulacion()));
		model.addAttribute("cuotas", afiliado.getCuotas());
		model.addAttribute("montoCuota", AfiliadoUtils.formateaVal(afiliado.getMontoCuota()));
		model.addAttribute("tasaInteresMensual", afiliado.getTasaInteresMensual());
		model.addAttribute("cae", afiliado.getCae());
		model.addAttribute("seguroDesgravamen", AfiliadoUtils.formateaVal(afiliado.getCostoTotalDesgravamen()));
		model.addAttribute("costoTotal", AfiliadoUtils.formateaVal(afiliado.getCostoTotalCredito()));
		model.addAttribute("impuesto", AfiliadoUtils.formateaVal(afiliado.getImpuesto()));
		model.addAttribute("gastoNotaria", AfiliadoUtils.formateaVal(afiliado.getGastoNotaria()));
		model.addAttribute("valorMensualSeguroCesantia", AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroCesantia()));
		model.addAttribute("valorMensualSeguroDesgravamen", AfiliadoUtils.formateaVal(afiliado.getValorMensualSeguroDesgravamen()));
		model.addAttribute("pagoPrimeraCuota", afiliado.getPagoPrimeraCuota());
		return "paso3";
	}
	
	
	@RequestMapping(value = { "/paso4.do" }, method = RequestMethod.GET)
	public String getPaso4 (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Paso 4");

		return "paso4";
	}
	
	
	@RequestMapping(value = { "/paso5.do" }, method = RequestMethod.GET)
	public String getPaso5 (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Iniciando Paso 5");
		return "paso5";
	}
	
	
	@RequestMapping(value = { "/cursaCredito.do" }, method = RequestMethod.GET)
	public String cursaCredito (@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Cursando crédito");
		return "exito";
	}
	
	
	@RequestMapping(value = { "/report.do" }, method = RequestMethod.GET)
	public String generaReporte(@ModelAttribute SolicitanteVo form, Model model, HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		logger.info("Generando reporte");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=resultadoSimulacion.pdf");
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		reporteService.generarReport(response, afiliado);
		return null;
	}
	
	
	private String validaAfiliado(Model model, AfiliadoVo afiliado) {
		logger.info("Validando Afiliado RUT: " + afiliado.getRutAfiliado());
		try {
			if(afiliado==null) {
				String error = "Error en la conectividad, intente mas tarde";
				logger.info(error);
				model.addAttribute("mensajeError",error);
				return "login";
			}
			logger.info("Comprobando si es afiliado");
			boolean esAfiliado = afiliadoService.isAfiliadoVigente(afiliado);
			if(!esAfiliado) {
				String error = "El RUT no se encuentra afiliado a La Araucana";
				logger.info(error);
				model.addAttribute("mensajeError",error);
				return "login";
			} else {
				logger.info("El rut corresponde a un afiliado");
			}
			logger.info("Comprobando si esta fallecido");
			boolean fallecido = afiliadoService.isFallecido(afiliado);
			if(fallecido) {
				String error = "El RUT no se encuentra afiliado a La Araucana";
				logger.info(error);
				model.addAttribute("mensajeError",error);
				return "login";
			} else {
				logger.info("El RUT pertenece a un afiliado vivo");
			}
			
			logger.info("Verificando numero de serie de cedula");
			
			String rut= afiliado.getRutAfiliado().replace(".", "").toUpperCase();
			String serie = afiliado.getSerieCedula();
			model.addAttribute("rut", rut);
			model.addAttribute("serie", serie);
			
			String sinacofi = Configuraciones.getConfig("servicios.sinacofi");
			
			if(sinacofi.equals("true")) {
				ClienteSinacofi cli = new ClienteSinacofi();
				//SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.callPojo(rut, serie);
				SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, serie);
				if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
					if(respSina!=null){
						logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
					}
					model.addAttribute("mensajeError", "servicio_error");
					return "login";
				}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
						logger.info("Mensaje: " + respSina.getMensaje());
						model.addAttribute("mensajeError", "Su cédula se encuentra vencida por lo que no puede continuar con la solicitud");
						return "login";
				} else {
					logger.info("Numero de serie valido");
				}
			}
			
			logger.info("Verificando si el afiliado está vigente");
			if(afiliado.getAfiliadoVigente() != 1) {
				String mensaje = "El afiliado no está vigente";
				logger.info(mensaje);
				model.addAttribute("mensajeError",mensaje);
				return "login";
			} else {
				logger.info("El afiliado está vigente");
			}
			
			
			return "paso1";
		} catch (Exception e) {
			String error = "Error al obtener informacion del afiliado";
			logger.info(error + ": " + e.toString());
			model.addAttribute("mensajeError",error);
			return "login";
		}
	}
	
	@RequestMapping(value = { "/rechazarOferta.do" }, method = RequestMethod.GET)
	public String getRechazarOferta (Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Cliente rechaza oferta ofrecida.");
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		try {
        	logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Rechazar Oferta", "--", "El afiliado rechazo la oferta.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "rechazaOferta";
	}
	
	@RequestMapping(value = { "/formularioCrearOferta.do" }, method = RequestMethod.GET)
	public String getAceptarOferta (Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Cliente acepta oferta ofrecida, se redirige a formulario.");
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		RegionesDao regiones = new RegionesDao();	
		List<RegionesDto> listaRegiones = regiones.getRegiones();
		
		ComunasDao comunas = new ComunasDao();
		List<ComunasDto> listaComunas = comunas.getComunas();
		
		String fechaNacimiento = afiliado.getFechaNacimiento();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date date;
	    String output = null;
	    
		try {
			date = inputFormat.parse(fechaNacimiento);
			output = outputFormat.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
        	logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Formulario crear oferta", "", "El afiliado ingresa al formulario para crear oferta.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("regiones", listaRegiones);
		model.addAttribute("comunas", listaComunas);
		
		model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado());
		model.addAttribute("monto", AfiliadoUtils.formateaVal(afiliado.getMontoSimulacion()));
		model.addAttribute("cuotas", afiliado.getCuotas());
		model.addAttribute("razonSocial", afiliado.getRazonSocial());
		
		model.addAttribute("fechaNacimiento", output);
		model.addAttribute("estadoCivil", afiliado.getEstadoCivil());
		model.addAttribute("tipoContrato", afiliado.getTipoContrato());
		model.addAttribute("celular", (afiliado.getCelular().length() == 0)?"":"+" + afiliado.getCelular());
		model.addAttribute("email", afiliado.getCorreo());
		model.addAttribute("telefono", (afiliado.getTelefono().length() == 0)?"":"+56" + afiliado.getTelefono());
		model.addAttribute("direccion", afiliado.getDireccion());
		model.addAttribute("nroDirecion", afiliado.getNumeroDireccion());
		model.addAttribute("villaPoblacion", afiliado.getVillaPoblacion());
		model.addAttribute("codRegion", afiliado.getCodigoRegion());
		model.addAttribute("codProvincia", afiliado.getCodigoProvincia());
		model.addAttribute("codComuna", afiliado.getCodigoComuna());

		return "formularioAfiliado";
	}
	
	@RequestMapping(value = { "/crearOferta.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getCreaOferta (
			@RequestBody FormCrearOfertaVO data,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response
	) {
		HashMap<String,String> respuesta = new HashMap<String,String>();
		logger.info("Iniciando Creación de oferta.");
		
		ClienteCreaOferta clienteCreaOferta = new ClienteCreaOferta();
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");	
		
		SalidaCreaOfertaVO respuestaService = new SalidaCreaOfertaVO();
		
		try {
			respuestaService = clienteCreaOferta.getRespuestaWSCreaOferta(afiliado, data);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		respuesta.put("Estado_Aprob_Cliente", respuestaService.getESTADO_APROB_CLIENTE());
		respuesta.put("Estado_Oferta", respuestaService.getESTADO_OFERTA());
		respuesta.put("Folio_Generado", respuestaService.getFOLIO_GENERADO());
		respuesta.put("Mensaje", respuestaService.getMENSAJE());
		respuesta.put("Nro_Oferta_Generada", respuestaService.getNRO_OFERTA_GENERADA());
		respuesta.put("Rut_Afiliado", respuestaService.getRUT_AFILIADO());
		respuesta.put("Sucursal_Oferta", respuestaService.getSUCURSAL_OFERTA());
		
		try {
        	logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Crear Oferta", "WSCreaOferta", "Nro. oferta generada: " + respuestaService.getNRO_OFERTA_GENERADA());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Date myDate = new Date();
		
		String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
		
		if(respuestaService.getESTADO_APROB_CLIENTE().equals("1")) {
			try {
				mailService.sendEmail(
					data.getEmailFormulario(), 
					"Oferta Aprobada", 
					Utils.getbodyAfiliado(
						afiliado.getNombreAfiliado(), 
						fechaActual, 
						String.valueOf(afiliado.getMontoSimulacion()), 
						String.valueOf(afiliado.getMontoCuota()),
						String.valueOf(afiliado.getCuotas())
					)
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}
	
	@RequestMapping(value = { "/agendaFinalContactar.do" }, method = RequestMethod.GET)
	public String getAgendaFinalContactar (Model model, HttpServletRequest request, HttpServletResponse response) {
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");	
		
		model.addAttribute("telefono", "+" + afiliado.getCelular());
		
		return "agendaFinalContactar";
	}
	
	@RequestMapping(value = { "/ofertaEnCurso.do" }, method = RequestMethod.GET)
	public String getOfertaEnCurso (Model model, HttpServletRequest request, HttpServletResponse response) {		
		logger.info("Iniciando WSOfertasVigentes.");
		
		ClienteOfertasVigentes clienteOfertasVigentes = new ClienteOfertasVigentes();
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		OfertasVigentes_DT[] respuestaWS = null;
		
		try {
			respuestaWS = clienteOfertasVigentes.getRespuestaWSOfertasVigentes(afiliado.getRutAfiliado());			
		} catch (AxisFault e) {
			e.printStackTrace();
		}	
		
		try {
			if(respuestaWS[0].getMAIL_AGENTE() != null && respuestaWS[0].getMAIL_EJEC_EVALUADOR() != null) {
				mailService.sendEmail(
					respuestaWS[0].getMAIL_AGENTE(), 
					"Contactar a afiliado por oferta " + respuestaWS[0].getNRO_OFERTA_GENERADA(), 
					Utils.getbodyAgenteYEjecutivo(
						respuestaWS[0].getNRO_OFERTA_GENERADA(), 
						afiliado.getNombreAfiliado(), 
						afiliado.getRutAfiliado(), 
						respuestaWS[0].getDESC_ESTADO_ACTUAL(), 
						afiliado.getCelular(), 
						respuestaWS[0].getNOM_AGENTE()
					)						
				);
				
				mailService.sendEmail(
					respuestaWS[0].getMAIL_EJEC_EVALUADOR(), 
					"Contactar a afiliado por oferta " + respuestaWS[0].getNRO_OFERTA_GENERADA(), 
					Utils.getbodyAgenteYEjecutivo(
						respuestaWS[0].getNRO_OFERTA_GENERADA(), 
						afiliado.getNombreAfiliado(), 
						afiliado.getRutAfiliado(), 
						respuestaWS[0].getDESC_ESTADO_ACTUAL(), 
						afiliado.getCelular(), 
						respuestaWS[0].getNOM_EJEC_EVALUADOR()
					)
				);				
				
			} else if(respuestaWS[0].getMAIL_AGENTE() != null) {
				mailService.sendEmail(
					respuestaWS[0].getMAIL_AGENTE(), 
					"Contactar a afiliado por oferta " + respuestaWS[0].getNRO_OFERTA_GENERADA(), 
					Utils.getbodyAgenteYEjecutivo(
						respuestaWS[0].getNRO_OFERTA_GENERADA(), 
						afiliado.getNombreAfiliado(), 
						afiliado.getRutAfiliado(), 
						respuestaWS[0].getDESC_ESTADO_ACTUAL(), 
						afiliado.getCelular(), 
						respuestaWS[0].getNOM_AGENTE()
					)						
				);
			} else if (respuestaWS[0].getMAIL_EJEC_EVALUADOR() != null) {
				mailService.sendEmail(
					respuestaWS[0].getMAIL_EJEC_EVALUADOR(), 
					"Contactar a afiliado por oferta " + respuestaWS[0].getNRO_OFERTA_GENERADA(), 
					Utils.getbodyAgenteYEjecutivo(
						respuestaWS[0].getNRO_OFERTA_GENERADA(), 
						afiliado.getNombreAfiliado(), 
						afiliado.getRutAfiliado(), 
						respuestaWS[0].getDESC_ESTADO_ACTUAL(), 
						afiliado.getCelular(), 
						respuestaWS[0].getNOM_EJEC_EVALUADOR()
					)
				);
			} else {
				// Caso de que no hay email asociado a la respuesta
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String fechaCreacionOferta = respuestaWS[0].getFCH_CREACION();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	    Date date;
	    String output = null;
	    
		try {
			date = inputFormat.parse(fechaCreacionOferta);
			output = outputFormat.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("fechaCreacion", output);
		model.addAttribute("montoSolicitado", AfiliadoUtils.formateaVal(respuestaWS[0].getMONTO_SOLICITADO()));
		model.addAttribute("cuotas", respuestaWS[0].getPLAZO_CREDITO());
		
		return "ofertaEnCurso";
	}
	
	@RequestMapping(value = { "/ofertasVigentes.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getOfertasVigentes (
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response
	) {
		HashMap<String,String> respuesta = new HashMap<String,String>();
		logger.info("Iniciando WSOfertasVigentes.");
		
		ClienteOfertasVigentes clienteOfertasVigentes = new ClienteOfertasVigentes();
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		OfertasVigentes_DT[] respuestaWS = null;
		
		try {
			respuestaWS = clienteOfertasVigentes.getRespuestaWSOfertasVigentes(afiliado.getRutAfiliado());
		} catch (AxisFault e) {
			e.printStackTrace();
		}	
		
		if(respuestaWS.length == 0) {
			respuesta.put("ID_ESTADO_ACTUAL", "0");
			respuesta.put("DESC_ESTADO_ACTUAL", "No tiene ofertas");
			
			try {
	        	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Ofertas Vigentes", "WSOfertasVigentes", "No tiene ofertas vigentes.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			
			OfertasVigentes_DT ofertaVigente = (Utils.getOfertaVigente(respuestaWS)==null?respuestaWS[0]:Utils.getOfertaVigente(respuestaWS));			
			
			request.getSession().setAttribute("respuestaWSOfertasVigentes", ofertaVigente);
			
			respuesta.put("APELL_AGENTE", (ofertaVigente.getAPELL_AGENTE()==null)?"":ofertaVigente.getAPELL_AGENTE());
			respuesta.put("APELL_EJEC_EVALUADOR", (ofertaVigente.getAPELL_EJEC_EVALUADOR()==null)?"":ofertaVigente.getAPELL_EJEC_EVALUADOR());
			respuesta.put("NRO_OFERTA_GENERADA", (ofertaVigente.getNRO_OFERTA_GENERADA()==null)?"":ofertaVigente.getNRO_OFERTA_GENERADA());
			respuesta.put("ID_ESTADO_ACTUAL", (ofertaVigente.getID_ESTADO_ACTUAL()==null)?"":ofertaVigente.getID_ESTADO_ACTUAL());
			respuesta.put("DESC_ESTADO_ACTUAL", (ofertaVigente.getDESC_ESTADO_ACTUAL()==null)?"":ofertaVigente.getDESC_ESTADO_ACTUAL());
			respuesta.put("FCH_ESTADO_ACTUAL", (ofertaVigente.getFCH_ESTADO_ACTUAL()==null)?"":ofertaVigente.getFCH_ESTADO_ACTUAL());
			respuesta.put("ID_SUC_OFERTA", (ofertaVigente.getID_SUC_OFERTA()==null)?"":ofertaVigente.getID_SUC_OFERTA());
	        respuesta.put("MAIL_AGENTE", (ofertaVigente.getMAIL_AGENTE()==null)?"":ofertaVigente.getMAIL_AGENTE());
	        respuesta.put("MAIL_EJEC_EVALUADOR", (ofertaVigente.getMAIL_EJEC_EVALUADOR()==null)?"":ofertaVigente.getMAIL_EJEC_EVALUADOR());
	        respuesta.put("NOM_AGENTE", (ofertaVigente.getNOM_AGENTE()==null)?"":ofertaVigente.getNOM_AGENTE());
	        respuesta.put("NOM_EJEC_EVALUADOR", (ofertaVigente.getNOM_EJEC_EVALUADOR()==null)?"":ofertaVigente.getNOM_EJEC_EVALUADOR());
	        respuesta.put("DESC_SUC_OFERTA", (ofertaVigente.getDESC_SUC_OFERTA()==null)?"":ofertaVigente.getDESC_SUC_OFERTA());
	        respuesta.put("RUT_AGENTE", (ofertaVigente.getRUT_AGENTE()==null)?"":ofertaVigente.getRUT_AGENTE());
	        respuesta.put("RUT_EJEC_EVALUADOR", (ofertaVigente.getRUT_EJEC_EVALUADOR()==null)?"":ofertaVigente.getRUT_EJEC_EVALUADOR());	
			
	        try {
	        	logger.info("Guardando datos en la bitacora de seguimiento");
				bitacoraService.saveBitacoraSeguimiento(afiliado, "Ofertas Vigentes", "WSOfertasVigentes", "Estado actual de la oferta del afiliado: " + respuestaWS[0].getDESC_ESTADO_ACTUAL());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return respuesta;
	}
	
	@RequestMapping(value = { "/aprobadoConOpciones.do" }, method = RequestMethod.GET)
	public String getAprobadoConOpciones (Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Iniciando vista de aprobado con opciones");
		
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");		
		SalidaEvaluadorModeloAISVo respuestaMotorAIS = (SalidaEvaluadorModeloAISVo) request.getSession().getAttribute("respuestaMotorAIS");
				
		model.addAttribute("nombreCompleto",afiliado.getNombreAfiliado()); 
		model.addAttribute("monto", AfiliadoUtils.formateaVal(afiliado.getMontoSimulacion()));
		model.addAttribute("cuotas", afiliado.getCuotas());
		model.addAttribute("razonSocial", afiliado.getRazonSocial());
		model.addAttribute("tasaInteresMensual", afiliado.getTasaInteresMensual());
	    model.addAttribute("cae", afiliado.getCae());
	    model.addAttribute("seguroDesgravamen", AfiliadoUtils.formateaVal(afiliado.getCostoTotalDesgravamen()));
	    model.addAttribute("sucursal", afiliado.getSucursal());
		
		model.addAttribute("montoTotal1", AfiliadoUtils.formateaVal(respuestaMotorAIS.getOpcionMonto1()));
	    model.addAttribute("cuotas1", AfiliadoUtils.formateaVal(respuestaMotorAIS.getOpcionPlazo1()));
	    model.addAttribute("valorCuota1", AfiliadoUtils.formateaVal(respuestaMotorAIS.getMontoCuota12()));
	    model.addAttribute("costoTotal1", AfiliadoUtils.formateaVal((respuestaMotorAIS.getOpcionPlazo1()*respuestaMotorAIS.getMontoCuota12())));

	    model.addAttribute("montoTotal2", AfiliadoUtils.formateaVal(respuestaMotorAIS.getOpcionMonto2()));
	    model.addAttribute("cuotas2", respuestaMotorAIS.getOpcionPlazo2());
	    model.addAttribute("valorCuota2", AfiliadoUtils.formateaVal(respuestaMotorAIS.getMontoCuota24()));
	    model.addAttribute("costoTotal2", AfiliadoUtils.formateaVal((respuestaMotorAIS.getOpcionPlazo2()*respuestaMotorAIS.getMontoCuota24())));

	    model.addAttribute("montoTotal3", AfiliadoUtils.formateaVal(respuestaMotorAIS.getOpcionMonto3()));
	    model.addAttribute("cuotas3", respuestaMotorAIS.getOpcionPlazo3());
	    model.addAttribute("valorCuota3", AfiliadoUtils.formateaVal(respuestaMotorAIS.getMontoCuota36()));
	    model.addAttribute("costoTotal3", AfiliadoUtils.formateaVal((respuestaMotorAIS.getOpcionPlazo3()*respuestaMotorAIS.getMontoCuota36())));
		
	    try {
        	logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Aprobado con opciones", "", "Afiliado obtiene 3 opciones para elegir un credito valido.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return "aprobadaConOpciones";
	}
	
	@RequestMapping(value = { "/paginaEnDesarrollo.do" }, method = RequestMethod.GET)
	public String getPaginaEnDesarrollo (Model model, HttpServletRequest request, HttpServletResponse response) {
		
		return "paginaEnDesarrollo";
	}
	
	@RequestMapping(value = { "/obtenerOpcionSeleccionada.do" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> getOpcionSeleccionada (
			@RequestBody AprobadoOpcionSeleccionadaVo data,
			Model model, 
			HttpServletRequest request,
			HttpServletResponse response
	) {
		logger.info("Obteniendo opciones del motor AIS.");
		
		HashMap<String,String> respuesta = new HashMap<String,String>();
		
		AprobadoOpcionSeleccionadaVo opcionSeleccionada = new AprobadoOpcionSeleccionadaVo();
		
		opcionSeleccionada.setMontoCuota(data.getMontoCuota());
		opcionSeleccionada.setOpcionMonto(data.getOpcionMonto());
		opcionSeleccionada.setOpcionPlazo(data.getOpcionPlazo());
		
		request.getSession().setAttribute("aprobadoConOpcionesSeleccion", opcionSeleccionada);
		
		respuesta.put("CodigoRespuesta", "1");
		
		return respuesta;
		
	}
	
	@RequestMapping(value = { "/creditoRechazado.do" }, method = RequestMethod.GET)
	public String getCreditoRechazado (Model model, HttpServletRequest request, HttpServletResponse response) {
		AfiliadoVo afiliado = (AfiliadoVo) request.getSession().getAttribute("afiliado");
		
		logger.info("Credito rechazado por el Motor AIS.");
		
		try {
        	logger.info("Guardando datos en la bitacora de seguimiento");
			bitacoraService.saveBitacoraSeguimiento(afiliado, "Credito Rechazado", "--", "Credito rechazado por el Motor AIS");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return "creditoRechazado";
	}
	
	@RequestMapping(value = "/comunas.do", method = RequestMethod.GET)
    public @ResponseBody List<ComunasDto> comunasPrRegion(
            @RequestParam(value = "regionNombre", required = true) String region) {

        ComunasDao comunas = new ComunasDao();
        List<ComunasDto> listaComunas = null;
        ComunasDto comuna = new ComunasDto();
        comuna.setIdcomuna("1");
        comuna.setNombre("");
        comuna.setRegion(region);
        
		try {
			listaComunas = comunas.getComunasByRegion(comuna);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return listaComunas;
    }
	
	private SalidaEvaluadorModeloAISVo mapearAIS (JSONObject respuestaJSON) {
		SalidaEvaluadorModeloAISVo salidaAIS = new SalidaEvaluadorModeloAISVo();
		
		try {
			String RutAfiliado = respuestaJSON.getString("RUTAFILIADO");
			String RutEmpresa = respuestaJSON.getString("RUTEMPRESA");
			//int Dictamen = respuestaJSON.getInt("DICTAMEN");
			Object Dictamen = respuestaJSON.get("DICTAMEN");
			String TipoDictamen = Dictamen.getClass().getSimpleName();
			if(TipoDictamen.equals("Integer")) {
				Dictamen = respuestaJSON.getInt("DICTAMEN");
			} else {
				Dictamen = 0;
			}
			
			//int MontoAprobado = respuestaJSON.getInt("MONTOAPROBADO");
			Object MontoAprobado = respuestaJSON.get("MONTOAPROBADO");
			String TipoMontoAprobado = MontoAprobado.getClass().getSimpleName();
			if(TipoMontoAprobado.equals("Integer")) {
				MontoAprobado = respuestaJSON.getInt("MONTOAPROBADO");
			} else {
				MontoAprobado = 0;
			}
			
			//int PlazoAprobado = respuestaJSON.getInt("PLAZOAPROBADO");
			Object PlazoAprobado = respuestaJSON.get("PLAZOAPROBADO");
			String TipoPlazoAprobado = PlazoAprobado.getClass().getSimpleName();
			if(TipoPlazoAprobado.equals("Integer")) {
				PlazoAprobado = respuestaJSON.getInt("PLAZOAPROBADO");
			} else {
				PlazoAprobado = 0;
			}
			
			//int OpcionMonto1 = respuestaJSON.getInt("OPCIONMONTO1");
			Object OpcionMonto1 = respuestaJSON.get("OPCIONMONTO1");
			String TipoOpcionMonto1 = OpcionMonto1.getClass().getSimpleName();
			if(TipoOpcionMonto1.equals("Integer")) {
				OpcionMonto1 = respuestaJSON.getInt("OPCIONMONTO1");
			} else {
				OpcionMonto1 = 0;
			}
			
			//int OpcionPlazo1 = respuestaJSON.getInt("OPCIONPLAZO1");
			Object OpcionPlazo1 = respuestaJSON.get("OPCIONPLAZO1");
			String TipoOpcionPlazo1 = OpcionPlazo1.getClass().getSimpleName();
			if(TipoOpcionPlazo1.equals("Integer")) {
				OpcionPlazo1 = respuestaJSON.getInt("OPCIONPLAZO1");
			} else {
				OpcionPlazo1 = 0;
			}
			
			//int OpcionMonto2 = respuestaJSON.getInt("OPCIONMONTO2");
			Object OpcionMonto2 = respuestaJSON.get("OPCIONMONTO2");
			String TipoOpcionMonto2 = OpcionMonto2.getClass().getSimpleName();
			if(TipoOpcionMonto2.equals("Integer")) {
				OpcionMonto2 = respuestaJSON.getInt("OPCIONMONTO2");
			} else {
				OpcionMonto2 = 0;
			}
			
			//int OpcionPlazo2 = respuestaJSON.getInt("OPCIONPLAZO2");
			Object OpcionPlazo2 = respuestaJSON.get("OPCIONPLAZO2");
			String TipoOpcionPlazo2 = OpcionPlazo2.getClass().getSimpleName();
			if(TipoOpcionPlazo2.equals("Integer")) {
				OpcionPlazo2 = respuestaJSON.getInt("OPCIONPLAZO2");
			} else {
				OpcionPlazo2 = 0;
			}
			
			//int OpcionMonto3 = respuestaJSON.getInt("OPCIONMONTO3");
			Object OpcionMonto3 = respuestaJSON.get("OPCIONMONTO3");
			String TipoOpcionMonto3 = OpcionMonto3.getClass().getSimpleName();
			if(TipoOpcionMonto3.equals("Integer")) {
				OpcionMonto3 = respuestaJSON.getInt("OPCIONMONTO3");
			} else {
				OpcionMonto3 = 0;
			}
			
			//int OpcionPlazo3 = respuestaJSON.getInt("OPCIONPLAZO3");
			Object OpcionPlazo3 = respuestaJSON.get("OPCIONPLAZO3");
			String TipoOpcionPlazo3 = OpcionPlazo3.getClass().getSimpleName();
			if(TipoOpcionPlazo3.equals("Integer")) {
				OpcionPlazo3 = respuestaJSON.getInt("OPCIONPLAZO3");
			} else {
				OpcionPlazo3 = 0;
			}
			
			String EqEstadoCivil = respuestaJSON.getString("EQESTADOCIVIL");
			//int EqFechaNacimiento = respuestaJSON.getInt("EQFECHANACIMIENT");
			Object EqFechaNacimiento = respuestaJSON.get("EQFECHANACIMIENT");
			String TipoEqFechaNacimiento = EqFechaNacimiento.getClass().getSimpleName();
			if(TipoEqFechaNacimiento.equals("Integer")) {
				EqFechaNacimiento = respuestaJSON.getInt("EQFECHANACIMIENT");
			} else {
				EqFechaNacimiento = 0;
			}
			
			String EqNacionalidad = respuestaJSON.getString("EQNACIONALIDAD");
			String Politicas = respuestaJSON.getString("POLITICAS");
			String ReglasNegocio = respuestaJSON.getString("REGLASNEGOCIO");
			String Alerta = respuestaJSON.getString("ALERTA");
			String AAPerfilRiesEmpr = respuestaJSON.getString("AAPERFILRIESEMPR");
			String AASegmento = respuestaJSON.getString("AASEGMENTO");
			String Segmento3 = respuestaJSON.getString("SEGMENTO3");
			String VCPerfilPersona = respuestaJSON.getString("VCPERFILPERSONA");
			
			//int Priorizacion = respuestaJSON.getInt("PRIORIZACION");
			Object Priorizacion = respuestaJSON.get("PRIORIZACION");
			String TipoPriorizacion = Priorizacion.getClass().getSimpleName();
			if(TipoPriorizacion.equals("Integer")) {
				Priorizacion = respuestaJSON.getInt("PRIORIZACION");
			} else {
				Priorizacion = 0;
			}
			
			//int VecesRenta = respuestaJSON.getInt("VECESRENTA");
			Object VecesRenta = respuestaJSON.get("VECESRENTA");
			String TipoVecesRenta = VecesRenta.getClass().getSimpleName();
			if(TipoVecesRenta.equals("Integer")) {
				VecesRenta = respuestaJSON.getInt("VECESRENTA");
			} else {
				VecesRenta = 0;
			}
			
			int Renta = respuestaJSON.getInt("RENTA");
			
			//int MaxMonto = respuestaJSON.getInt("MAXMONTO");
			Object MaxMonto = respuestaJSON.get("MAXMONTO");
			String TipoMaxMonto = MaxMonto.getClass().getSimpleName();
			if(TipoMaxMonto.equals("Integer")) {
				MaxMonto = respuestaJSON.getInt("MAXMONTO");
			} else {
				MaxMonto = 0;
			}
			
			//int MontoSimulado = respuestaJSON.getInt("MONTOSIMULADO");
			Object MontoSimulado = respuestaJSON.get("MONTOSIMULADO");
			String TipoMontoSimulado = MontoSimulado.getClass().getSimpleName();
			if(TipoMontoSimulado.equals("Integer")) {
				MontoSimulado = respuestaJSON.getInt("MONTOSIMULADO");
			} else {
				MontoSimulado = 0;
			}
			
			//int PlazoSimulado = respuestaJSON.getInt("PLAZOSIMULADO");
			Object PlazoSimulado = respuestaJSON.get("PLAZOSIMULADO");
			String TipoPlazoSimulado = PlazoSimulado.getClass().getSimpleName();
			if(TipoPlazoSimulado.equals("Integer")) {
				PlazoSimulado = respuestaJSON.getInt("PLAZOSIMULADO");
			} else {
				PlazoSimulado = 0;
			}
			
			//int Cotizacion = respuestaJSON.getInt("COTIZACION");
			Object Cotizacion = respuestaJSON.get("COTIZACION");
			String TipoCotizacion = Cotizacion.getClass().getSimpleName();
			if(TipoCotizacion.equals("Integer")) {
				Cotizacion = respuestaJSON.getInt("COTIZACION");
			} else {
				Cotizacion = 0;
			}
			
			int MaxPorcDesc = respuestaJSON.getInt("MAXPORCDESC");
			
			//int Monto = respuestaJSON.getInt("MONTO");
			Object Monto = respuestaJSON.get("MONTO");
			String TipoMonto = Monto.getClass().getSimpleName();
			if(TipoMonto.equals("Integer")) {
				Monto = respuestaJSON.getInt("MONTO");
			} else {
				Monto = 0;
			}
			
			//int MontoCuotaSol = respuestaJSON.getInt("MONTOCUOTASOL");
			Object MontoCuotaSol = respuestaJSON.get("MONTOCUOTASOL");
			String TipoMontoCuotaSol = MontoCuotaSol.getClass().getSimpleName();
			if(TipoMontoCuotaSol.equals("Integer")) {
				MontoCuotaSol = respuestaJSON.getInt("MONTOCUOTASOL");
			} else {
				MontoCuotaSol = 0;
			}
			
			//int MontoCuotaSim = respuestaJSON.getInt("MONTOCUOTASIM");
			Object MontoCuotaSim = respuestaJSON.get("MONTOCUOTASIM");
			String TipoMontoCuotaSim = MontoCuotaSim.getClass().getSimpleName();
			if(TipoMontoCuotaSim.equals("Integer")) {
				MontoCuotaSim = respuestaJSON.getInt("MONTOCUOTASIM");
			} else {
				MontoCuotaSim = 0;
			}
			
			//int MontoCuota12 = respuestaJSON.getInt("MONTOCUOTA12");
			Object MontoCuota12 = respuestaJSON.get("MONTOCUOTA12");
			String TipoMontoCuota12 = MontoCuota12.getClass().getSimpleName();
			if(TipoMontoCuota12.equals("Integer")) {
				MontoCuota12 = respuestaJSON.getInt("MONTOCUOTA12");
			} else {
				MontoCuota12 = 0;
			}
			
			//int MontoCuota24 = respuestaJSON.getInt("MONTOCUOTA24");	
			Object MontoCuota24 = respuestaJSON.get("MONTOCUOTA24");
			String TipoMontoCuota24 = MontoCuota24.getClass().getSimpleName();
			if(TipoMontoCuota24.equals("Integer")) {
				MontoCuota24 = respuestaJSON.getInt("MONTOCUOTA24");
			} else {
				MontoCuota24 = 0;
			}
			
			//int MontoCuota36 = respuestaJSON.getInt("MONTOCUOTA36");
			Object MontoCuota36 = respuestaJSON.get("MONTOCUOTA36");
			String TipoMontoCuota36 = MontoCuota36.getClass().getSimpleName();
			if(TipoMontoCuota36.equals("Integer")) {
				MontoCuota36 = respuestaJSON.getInt("MONTOCUOTA36");
			} else {
				MontoCuota36 = 0;
			}	
			
			//int MontoCuota48 = respuestaJSON.getInt("MONTOCUOTA48");
			Object MontoCuota48 = respuestaJSON.get("MONTOCUOTA48");
			String TipoMontoCuota48 = MontoCuota48.getClass().getSimpleName();
			if(TipoMontoCuota48.equals("Integer")) {
				MontoCuota48 = respuestaJSON.getInt("MONTOCUOTA48");
			} else {
				MontoCuota48 = 0;
			}
			
			//int MontoCuota60 = respuestaJSON.getInt("MONTOCUOTA60");
			Object MontoCuota60 = respuestaJSON.get("MONTOCUOTA60");
			String TipoMontoCuota60 = MontoCuota60.getClass().getSimpleName();
			if(TipoMontoCuota60.equals("Integer")) {
				MontoCuota60 = respuestaJSON.getInt("MONTOCUOTA60");
			} else {
				MontoCuota60 = 0;
			}
			
			//int MaxDescuentoSol = respuestaJSON.getInt("MAXDESCUENTOSOL");
			Object MaxDescuentoSol = respuestaJSON.get("MAXDESCUENTOSOL");
			String TipoMaxDescuentoSol = MaxDescuentoSol.getClass().getSimpleName();
			if(TipoMaxDescuentoSol.equals("Integer")) {
				MaxDescuentoSol = respuestaJSON.getInt("MAXDESCUENTOSOL");
			} else {
				MaxDescuentoSol = 0;
			}	
			
			//int MaxDescuentoSim = respuestaJSON.getInt("MAXDESCUENTOSIM");
			Object MaxDescuentoSim = respuestaJSON.get("MAXDESCUENTOSIM");
			String TipoMaxDescuentoSim = MaxDescuentoSim.getClass().getSimpleName();
			if(TipoMaxDescuentoSim.equals("Integer")) {
				MaxDescuentoSim = respuestaJSON.getInt("MAXDESCUENTOSIM");
			} else {
				MaxDescuentoSim = 0;
			}	
			
			//int MaxDescuento12 = respuestaJSON.getInt("MAXDESCUENTO12");
			Object MaxDescuento12 = respuestaJSON.get("MAXDESCUENTO12");
			String TipoMaxDescuento12 = MaxDescuento12.getClass().getSimpleName();
			if(TipoMaxDescuento12.equals("Integer")) {
				MaxDescuento12 = respuestaJSON.getInt("MAXDESCUENTO12");
			} else {
				MaxDescuento12 = 0;
			}
			
			//int MaxDescuento24 = respuestaJSON.getInt("MAXDESCUENTO24");
			Object MaxDescuento24 = respuestaJSON.get("MAXDESCUENTO24");
			String TipoMaxDescuento24 = MaxDescuento24.getClass().getSimpleName();
			if(TipoMaxDescuento24.equals("Integer")) {
				MaxDescuento24 = respuestaJSON.getInt("MAXDESCUENTO24");
			} else {
				MaxDescuento24 = 0;
			}
			
			//int MaxDescuento36 = respuestaJSON.getInt("MAXDESCUENTO36");
			Object MaxDescuento36 = respuestaJSON.get("MAXDESCUENTO36");
			String TipoMaxDescuento36 = MaxDescuento36.getClass().getSimpleName();
			if(TipoMaxDescuento36.equals("Integer")) {
				MaxDescuento36 = respuestaJSON.getInt("MAXDESCUENTO36");
			} else {
				MaxDescuento36 = 0;
			}
			
			//int MaxDescuento48 = respuestaJSON.getInt("MAXDESCUENTO48");
			Object MaxDescuento48 = respuestaJSON.get("MAXDESCUENTO48");
			String TipoMaxDescuento48 = MaxDescuento48.getClass().getSimpleName();
			if(TipoMaxDescuento48.equals("Integer")) {
				MaxDescuento48 = respuestaJSON.getInt("MAXDESCUENTO48");
			} else {
				MaxDescuento48 = 0;
			}
			
			//int MaxDescuento60 = respuestaJSON.getInt("MAXDESCUENTO60");
			Object MaxDescuento60 = respuestaJSON.get("MAXDESCUENTO60");
			String TipoMaxDescuento60 = MaxDescuento60.getClass().getSimpleName();
			if(TipoMaxDescuento60.equals("Integer")) {
				MaxDescuento60 = respuestaJSON.getInt("MAXDESCUENTO60");
			} else {
				MaxDescuento60 = 0;
			}
			
			String AAPensionadoPBS = respuestaJSON.getString("AAPENSIONADOPBS");
			String AASexo = respuestaJSON.getString("AASEXO");
			
			String codigoError = respuestaJSON.getString("CodigoError");
			
			salidaAIS.setRutAfiliado(RutAfiliado);
			salidaAIS.setRutEmpresa(RutEmpresa);
			salidaAIS.setDictamen((int) Dictamen);
			salidaAIS.setMontoAprobado((int) MontoAprobado);
			salidaAIS.setPlazoAprobado((int) PlazoAprobado);
			salidaAIS.setOpcionMonto1((int) OpcionMonto1);
			salidaAIS.setOpcionPlazo1((int) OpcionPlazo1);
			salidaAIS.setOpcionMonto2((int) OpcionMonto2);
			salidaAIS.setOpcionPlazo2((int) OpcionPlazo2);
			salidaAIS.setOpcionMonto3((int) OpcionMonto3);
			salidaAIS.setOpcionPlazo3((int) OpcionPlazo3);
			salidaAIS.setEqEstadoCivil(EqEstadoCivil);
			salidaAIS.setEqFechaNacimient((int) EqFechaNacimiento);
			salidaAIS.setEqNacionalidad(EqNacionalidad);
			salidaAIS.setPoliticas(Politicas);
			salidaAIS.setReglasNegocio(ReglasNegocio);
			salidaAIS.setAlerta(Alerta);
			salidaAIS.setAAPerfilRiesEmpr(AAPerfilRiesEmpr);
			salidaAIS.setAASegmento(AASegmento);
			salidaAIS.setSegmento3(Segmento3);
			salidaAIS.setVCPerfilPersona(VCPerfilPersona);
			salidaAIS.setPriorizacion((int) Priorizacion);
			salidaAIS.setVecesRenta((int) VecesRenta);
			salidaAIS.setRenta((int) Renta);
			salidaAIS.setMaxMonto((int) MaxMonto);
			salidaAIS.setMontoSimulado((int) MontoSimulado);
			salidaAIS.setPlazoSimulado((int) PlazoSimulado);
			salidaAIS.setCotizacion((int) Cotizacion);
			salidaAIS.setMaxPorcDesc((int) MaxPorcDesc);
			salidaAIS.setMonto((int) Monto);
			salidaAIS.setMontoCuotaSol((int) MontoCuotaSol);
			salidaAIS.setMontoCuotaSim((int) MontoCuotaSim);
			salidaAIS.setMontoCuota12((int) MontoCuota12);
			salidaAIS.setMontoCuota24((int) MontoCuota24);
			salidaAIS.setMontoCuota36((int) MontoCuota36);
			salidaAIS.setMontoCuota48((int) MontoCuota48);
			salidaAIS.setMontoCuota60((int) MontoCuota60);
			salidaAIS.setMaxDescuentoSol((int) MaxDescuentoSol);
			salidaAIS.setMaxDescuentoSim((int) MaxDescuentoSim);
			salidaAIS.setMaxDescuento12((int) MaxDescuento12);
			salidaAIS.setMaxDescuento24((int) MaxDescuento24);
			salidaAIS.setMaxDescuento36((int) MaxDescuento36);
			salidaAIS.setMaxDescuento48((int) MaxDescuento48);
			salidaAIS.setMaxDescuento60((int) MaxDescuento60);
			salidaAIS.setAAPensionadoPBS(AAPensionadoPBS);
			salidaAIS.setAASexo(AASexo);	
			salidaAIS.setCodigoError(codigoError);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return salidaAIS;
	}
	
	
	
}
