package cl.laaraucana.ventaremota.controller;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
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
import com.lautaro.xi.CRM.WEB_Mobile.Oa_CargaFirmaWebFS_DT;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.util.UserPrincipal;
import cl.laaraucana.ventaremota.CRM.ws.ClienteInfoAfiliado;
import cl.laaraucana.ventaremota.CRM.ws.ClienteSinacofi;
import cl.laaraucana.ventaremota.CRM.ws.ConstantesRespuestasWS;
import cl.laaraucana.ventaremota.entities.BitacoraContratoCREntiti;
import cl.laaraucana.ventaremota.entities.BitacoraEntiti;
import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.ibatis.vo.AutenticacionVO;
import cl.laaraucana.ventaremota.ibatis.vo.OpcionIngresoVo;
import cl.laaraucana.ventaremota.ibatis.vo.RespuestaVO;
import cl.laaraucana.ventaremota.ibatis.vo.ResultadosBitacoraVO;
import cl.laaraucana.ventaremota.model.CreditoViewVo;
import cl.laaraucana.ventaremota.model.PreguntaRespuestas;
import cl.laaraucana.ventaremota.model.RespuestasVO;
import cl.laaraucana.ventaremota.model.UsuarioVo;
import cl.laaraucana.ventaremota.model.WsCargaCrm;
import cl.laaraucana.ventaremota.services.AutenticacionService;
import cl.laaraucana.ventaremota.services.BitacoraService;
import cl.laaraucana.ventaremota.services.CreditoService;
import cl.laaraucana.ventaremota.services.FTPService;
import cl.laaraucana.ventaremota.services.PreguntasService;
import cl.laaraucana.ventaremota.util.CodigosSingleton;
import cl.laaraucana.ventaremota.util.Configuraciones;
import cl.laaraucana.ventaremota.util.Utils;
import cl.laaraucana.ventaremota.vo.LoginVO;
import cl.laaraucana.ventaremota.vo.SalidaSinacofiVO;
import cl.laaraucana.ventaremota.vo.SalidainfoAfiliadoVO;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CreditoService creditoService;

	@Autowired
	private BitacoraService bitaService;

	@Autowired
	private FTPService ftpservice;

	@Autowired
	private PreguntasService preguntasService;

	@Autowired
	private AutenticacionService autenticacionService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String controllerInit(ModelMap model, HttpServletRequest request, @ModelAttribute() OpcionIngresoVo form,
			HttpServletResponse response) {

		String opcion = form.getOpcion();

		if (opcion == null) {
			return "ingreso";
		}

		if (opcion.equals("0")) {

			request.getSession().setAttribute("sesion", "ok");

			return "ldap";

		} else {

			request.getSession().setAttribute("sesion", "ok");

			return "serie";
		}

	}

	@RequestMapping(value = { "/login.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String autenticacionLDAP(ModelMap model, @ModelAttribute LoginVO form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			request.getSession().setAttribute("errorMsg", "");
			String rut = form.getRutAfiliado();
			if (rut == null || rut.equals("")) {
				return "login_error";
			}
			rut = rut.replaceAll("\\.", "").toUpperCase();
			request.getSession().setAttribute("rutLdap", rut);

			String password = form.getPassword();

			logger.info("Validando rut y clave de " + rut);
			rut = Integer.parseInt(rut.split("-")[0]) + "-" + rut.split("-")[1];
			UserPrincipal user = new UserPrincipal(rut, password);
			UserRegistryConnection urConnection = new UserRegistryConnection(user);
			logger.info("Clave OK: " + password);

			ClienteInfoAfiliado info = new ClienteInfoAfiliado();

			SalidainfoAfiliadoVO res = info.getDataAfiliado(rut);
			if (res.getNombreCompleto() == null) {
				logger.info("RUT " + rut + " no es afiliado, se muestra mensaje de error al ejecutivo");
				model.addAttribute("errorMsg", "rut_sininfo");
				return "ldap";
			}

			request.getSession().setAttribute("nombre", res.getNombreCompleto());
		} catch (Exception e) {
			// TODO: handle exception

			logger.warn("Error validación Rut " + form.getRutAfiliado(), e);
			request.getSession().setAttribute("errorMsg", "rut_error");
			return "ldap";
		}

		return "redirect:/creditos.do";
	}

	@RequestMapping(value = { "/validaCedula.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String validacionCedula(ModelMap model, HttpServletRequest request, @ModelAttribute() UsuarioVo user,
			HttpServletResponse response) {

		// DecimalFormat df = new DecimalFormat("###,###");

		try {
			request.getSession().setAttribute("errorMsg", "");
			if (request.getSession().getAttribute("sesion") == null) {
				return "redirect:/init.do";
			}

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

			/*if (!validaCedula(rut_usuario.replace("-", ""),
					user.getSerie()).equals("OK")) {
				logger.warn("Respuesta no satisfactoria de Sinacofi:");
				model.addAttribute("errorMsg", "serie_error");
				model.addAttribute("serie", user.getSerie());
				return "serie"; 
			}*/
			 

			request.getSession().setAttribute("nombre", res.getNombreCompleto());

			request.getSession().setAttribute("serie", "OK");
			request.getSession().setAttribute("serieNum", user.getSerie());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error Validación Numero Serie:  ", e);
			request.getSession().setAttribute("errorMsg", "operacion_error");
			return "serie";
		}
		request.getSession().setAttribute("respuestasValidas", -1);

		return "redirect:/creditos.do";
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

			/*
			 * String host = Configuraciones.getConfig("hostFTP"); String port =
			 * Configuraciones.getConfig("portFTP"); String user =
			 * Configuraciones.getConfig("usuarioFTP"); String clave =
			 * Configuraciones.getConfig("claveFTP"); String FTPCarpeta =
			 * Configuraciones.getConfig("FTPCarpeta");
			 */
			String path = Configuraciones.getConfig("carpeta.local");

			// ftpservice.connectToFTP(host, Integer.parseInt(port), user, clave);

			// String file = ftpservice.downloadFileFromFTP(FTPCarpeta, id);
			String file = id;
			logger.debug("Archivo " + file);

			FileInputStream archivo = new FileInputStream(path + file + ".pdf");
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			archivo.read(datos);
			archivo.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=documento_venta_" + file + ".pdf");

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(datos);
			ouputStream.flush();
			ouputStream.close();
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error al traer el archivo ", e);
			return null;
		}

	}

	@RequestMapping(value = { "/creditos.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String getCreditosAprobar(ModelMap model, HttpServletRequest request, @ModelAttribute() UsuarioVo user,
			HttpServletResponse response) {

		DecimalFormat df = new DecimalFormat("###,###");

		try {

			String rut_usuario = (String) request.getSession().getAttribute("rutLdap");

			// String[] rut = user.getRut().replace(".", "").split("-");
			String[] rut = rut_usuario.split("-");
			String vacio = "";

			List<CreditoEntiti> creditos = creditoService.getAllCreditByRut(Long.parseLong(rut[0]));

			List<CreditoViewVo> listaCreditos = new ArrayList<CreditoViewVo>();

			for (CreditoEntiti cred : creditos) {
				List<BitacoraEntiti> listaRechazados = creditoService.getAllRechazadoByRut(Long.parseLong(rut[0]),
						cred.getNumeroOferta());
				
				if ((listaRechazados==null || listaRechazados.size() < 2) && cred.getEstado().equals(Configuraciones.DISPONIBLE)) {
					CreditoViewVo vo = new CreditoViewVo();

					vo.setIdCredito(String.valueOf(cred.getIdCredito()));
					vo.setFechaOtorgamiento(cred.getFechaOtorgamiento());
					vo.setMontoCuota(df.format(cred.getMontoCuota()));
					vo.setMontoSolicitado(df.format(cred.getMontoSolicitado()));
					vo.setNumeroCuotas(String.valueOf(cred.getNumeroCuotas()));
					vo.setTipoCreditoNormal(cred.getTipoCreditoNormal());
					vo.setUrl(cred.getNumeroDocumento());
					vo.setName(cred.getNumeroDocumento() + ".pdf");

					listaCreditos.add(vo);
				}
			}

			if (creditos.size() > 0) {

				model.addAttribute("tipoCredito", creditos.get(0).getTipoCreditoNormal().trim());
				model.addAttribute("cesantia", creditos.get(0).getSeguroCesantia());
				model.addAttribute("formaPago", creditos.get(0).getFormaPago());
				model.addAttribute("contratoCR", creditos.get(0).getContratoCR());
				model.addAttribute("deudorAlimenticio", creditos.get(0).getDeudorAlimenticio());
				model.addAttribute("montoCC", creditos.get(0).getMontoCompraCartera());
				String tipoReprogramacion= creditos.get(0).getTipoReprogramacion();
				if(tipoReprogramacion==null){
					tipoReprogramacion="0";
				}
				model.addAttribute("tipoReprogramacion", tipoReprogramacion.trim());
			}

			request.getSession().setAttribute("creditos", creditos);
			// request.getSession().setAttribute("sinacofi", res);
			request.getSession().setAttribute("rutUser", rut_usuario);

			if (listaCreditos.size() == 0) {

				vacio = "SI";
			} else {
				vacio = "NO";
			}

			request.getSession().setAttribute("vacio", vacio);
			request.getSession().setAttribute("lista_creditos", listaCreditos);
			// request.getSession().setAttribute("nombre", res.getNombreCompleto());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 2  ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}

	@RequestMapping(value = { "/paso3.do" }, method = RequestMethod.POST)
	public String getStep3(ModelMap model, HttpServletRequest request, @RequestParam("credito") String id,
			HttpServletResponse response) {

		DecimalFormat df = new DecimalFormat("###,###");
		DecimalFormat df2 = new DecimalFormat("###,###.00");

		try {
			String ipremota= request.getParameter("ipremota");
			request.getSession().setAttribute("ipremota", ipremota);
			logger.debug("choose " + id);

			CreditoEntiti credito = creditoService.getCreditById(Long.parseLong(id));

			model.addAttribute("folio", credito.getFolioCredito());
			model.addAttribute("montoCuota", df.format(credito.getMontoCuota()));
			model.addAttribute("montoSolicitado", df.format(credito.getMontoSolicitado()));
			model.addAttribute("numCuotas", credito.getNumeroCuotas());
			model.addAttribute("montoTotal", df.format(credito.getMontoTotalSolicitado()));
			model.addAttribute("interes", credito.getTasaInteresMensual().trim().replace(',', '.'));
			model.addAttribute("montoComision", df.format(credito.getMontoComision()));
			model.addAttribute("cae", credito.getCae().trim().replace(',', '.'));
			model.addAttribute("montoLiquido", df.format(credito.getMontoLiquido()));
			model.addAttribute("impuesto", df.format(credito.getImpuesto()));
			model.addAttribute("formaPago", credito.getFormaPago());
			model.addAttribute("gastoNotarial", df.format(credito.getGastoNotarial()));
			model.addAttribute("montoRenegociado", df.format(credito.getMontoRenegociado()));
			model.addAttribute("seguroCesantia", df.format(credito.getSeguroCesantia()));
			model.addAttribute("montoMora", df.format(credito.getMontoPagoMora()));
			model.addAttribute("seguroDesgravamen", df.format(credito.getSeguroDesgravamen()));
			model.addAttribute("fechaOtorgamiento", credito.getFechaOtorgamiento());
			model.addAttribute("montoCartera", df.format(credito.getMontoCompraCartera()));
			
			model.addAttribute("tipoCredito", credito.getTipoCreditoNormal().trim());

			request.getSession().setAttribute("credito", credito);
			

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3  ", e);

			return "registro_error";
		}

		return "paso-paso3";
	}

	@RequestMapping(value = { "/aprobar.do" }, method = RequestMethod.POST)
	public String AprobacionCredito(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {

			CreditoEntiti credito = (CreditoEntiti) request.getSession().getAttribute("credito");
			String idChallenge = (String) request.getSession().getAttribute("idChallenge");
			
			//UPDATE CREDITO EN SQLSERVER
			logger.info("aprobando crédito, update estado a '02'");
			creditoService.updateCredit(credito.getIdCredito(), Configuraciones.APROBADO);
			
			//INVOCANDO CRM
			WsCargaCrm crm = new WsCargaCrm();
			crm.setEstado(Configuraciones.APROBADO);
			crm.setNumeroOferta(String.valueOf(credito.getNumeroOferta()));
			logger.info("invocando WS CargaCRM para oferta:" + crm.getNumeroOferta() + ", etado" + crm.getEstado());
			Oa_CargaFirmaWebFS_DT res = crm.wsCargaCrm(crm);
			logger.info("Respuesta ws CargaCRM oferta "  + res.getNUM_OFERTA() + ": " + res.getESTADO_OFERTA());
			
			//GRABANDO BITACORA VENTA REMOTA
			BitacoraEntiti bita = new BitacoraEntiti();
			bita.setRutcliente(credito.getRutCliente());
			bita.setDvcliente(credito.getDvCliente());
			bita.setFechaAprobRech(new Date());
			bita.setFolioCredito(credito.getFolioCredito());
			bita.setNumeroOferta(credito.getNumeroOferta());
			bita.setIdAprobRech("02");
			bita.setIdChallenge(idChallenge);
			String ipremota= (String)request.getSession().getAttribute("ipremota");
			bita.setIpAcceso(ipremota);
			bita.setNumeroOfertaCrm(res.getNUM_OFERTA());
			bita.setEstadoOfertaCrm(res.getESTADO_OFERTA());
			logger.info("guardando bitácora para Oferta: " + bita.getNumeroOferta() + ", from ip:" + bita.getIpAcceso());
			bitaService.save(bita);
			
			//GRABANDO BITACORA CONTRATO CANALES REMOTO
			if(credito.getContratoCR().equals("NO")){
				//GRABANDO BITACORA
				BitacoraContratoCREntiti bitacr = new BitacoraContratoCREntiti();
				bitacr.setRutCliente(credito.getRutCliente());
				bitacr.setDvCliente(credito.getDvCliente());
				bitacr.setFechaAprobacion(new Date());
				bitacr.setIdChallenge("");
				bitacr.setIpAcceso(ipremota);
				bitacr.setCodigoRetorno("");
				bitacr.setResultadoValidacion("");

				logger.info("guardando bitácora Contrato Canales Remotos para Rut: " + credito.getRutCliente()+"-"+credito.getDvCliente());
				bitaService.save(bitacr);
			}
			
			model.addAttribute("tipoCredito", credito.getTipoCreditoNormal().trim());
			request.setAttribute("mesage", "");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 4  ", e);

			return "registro_error";
		}

		return "registro-exito";
	}

	@RequestMapping(value = { "/rechazar.do" }, method = RequestMethod.POST)
	public String RechazoCredito(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			CreditoEntiti credito) {

		try {
			credito = (CreditoEntiti) request.getSession().getAttribute("credito");
			String max_intentos= request.getParameter("max_intentos");
			
			String tipo_rechazo=Configuraciones.RECHAZADO;
			if(max_intentos!=null && max_intentos.equals("1")){
				tipo_rechazo=Configuraciones.MAX_INTENTOS;
			}
			request.setAttribute("tipo_rechazo", tipo_rechazo);
			
			//UPDATE CREDITO EN SQLSERVER
			logger.info("Rechazando crédito " + credito.getIdCredito() + " asociado a Rut: " + credito.getRutCliente()
					+ "-" + credito.getDvCliente() + ", update estado a " + tipo_rechazo);
			creditoService.updateCredit(credito.getIdCredito(), tipo_rechazo);
			
			//INVOCANDO CRM
			WsCargaCrm crm = new WsCargaCrm();
			crm.setEstado(tipo_rechazo);
			crm.setNumeroOferta(String.valueOf(credito.getNumeroOferta()));
			logger.info("invocando WS CargaCRM para oferta:" + crm.getNumeroOferta() + ", etado" + crm.getEstado());
			Oa_CargaFirmaWebFS_DT res = crm.wsCargaCrm(crm);
			logger.info("Respuesta WS CargaCRM oferta "  + res.getNUM_OFERTA() + ": " + res.getESTADO_OFERTA());
			
			//GRABANDO BITACORA
			BitacoraEntiti bita = new BitacoraEntiti();
			bita.setRutcliente(credito.getRutCliente());
			bita.setDvcliente(credito.getDvCliente());
			bita.setFechaAprobRech(new Date());
			bita.setFolioCredito(credito.getFolioCredito());
			bita.setNumeroOferta(credito.getNumeroOferta());
			bita.setIdAprobRech(tipo_rechazo);
			String ipremota= (String)request.getSession().getAttribute("ipremota");
			bita.setIpAcceso(ipremota);
			bita.setEstadoOfertaCrm(res.getESTADO_OFERTA());
			bita.setNumeroOfertaCrm(res.getNUM_OFERTA());
			logger.info("guardando bitácora oferta" + credito.getNumeroOferta());
			bitaService.save(bita);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 5  ", e);

			return "registro_error";
		}

		return "registro-rechazo";
	}

	@RequestMapping(value = { "/volver.do" }, method = RequestMethod.POST)
	public String volver(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		DecimalFormat df = new DecimalFormat("###,###");

		SalidainfoAfiliadoVO res = (SalidainfoAfiliadoVO) request.getSession().getAttribute("sinacofi");

		String vacio = "";

		try {

			@SuppressWarnings("unchecked")
			List<CreditoEntiti> creditos = (List<CreditoEntiti>) request.getSession().getAttribute("creditos");

			List<CreditoViewVo> listaCreditos = new ArrayList<CreditoViewVo>();

			for (CreditoEntiti cred : creditos) {

				CreditoViewVo vo = new CreditoViewVo();

				vo.setIdCredito(String.valueOf(cred.getIdCredito()));
				vo.setFechaOtorgamiento(cred.getFechaOtorgamiento());
				vo.setMontoCuota(df.format(cred.getMontoCuota()));
				vo.setMontoSolicitado(df.format(cred.getMontoSolicitado()));
				vo.setNumeroCuotas(String.valueOf(cred.getNumeroCuotas()));
				vo.setTipoCreditoNormal(cred.getTipoCreditoNormal());
				vo.setUrl(cred.getNumeroDocumento());
				vo.setName(cred.getNumeroDocumento() + ".pdf");

				listaCreditos.add(vo);
			}
			
			if (creditos.size() > 0) {

				model.addAttribute("tipoCredito", creditos.get(0).getTipoCreditoNormal().trim());
				model.addAttribute("cesantia", creditos.get(0).getSeguroCesantia());
			}

			request.getSession().setAttribute("creditos", creditos);
			request.getSession().setAttribute("sinacofi", res);

			if (listaCreditos.size() == 0) {

				vacio = "SI";
			} else {
				vacio = "NO";
			}

			model.addAttribute("vacio", vacio);
			model.addAttribute("lista_creditos", listaCreditos);
			//model.addAttribute("nombre", res.getNombreCompleto());

		} catch (Exception e) { // TODO: handle exception

			logger.error("Error volver ", e);

			return "registro_error";
		}

		return "paso-paso2";
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
