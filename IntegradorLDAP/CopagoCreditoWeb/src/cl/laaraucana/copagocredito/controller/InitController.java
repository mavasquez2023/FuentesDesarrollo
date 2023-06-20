package cl.laaraucana.copagocredito.controller;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.http.HttpUserPrincipal;
import cl.laaraucana.copagocredito.entities.BitacoraEntiti;
import cl.laaraucana.copagocredito.entities.CreditoEntiti;
import cl.laaraucana.copagocredito.entities.MandatosEntity;
import cl.laaraucana.copagocredito.model.CreditoVo;
import cl.laaraucana.copagocredito.reportDao.ReportDao;
import cl.laaraucana.copagocredito.services.BitacoraService;
import cl.laaraucana.copagocredito.services.CreditoService;
import cl.laaraucana.copagocredito.services.MailService;
import cl.laaraucana.copagocredito.services.MandatoService;
import cl.laaraucana.copagocredito.services.ReporteService;
import cl.laaraucana.copagocredito.util.Configuraciones;
import cl.laaraucana.copagocredito.util.Utils;
import cl.laaraucana.copagocredito.vo.EntradaSinacofiVO;
import cl.laaraucana.copagocredito.vo.SalidaSinacofiVO;
import cl.laaraucana.copagocredito.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.copagocredito.ws.ClienteInfoAfiliado;
import cl.laaraucana.copagocredito.ws.ClienteSinacofi;
import cl.laaraucana.copagocredito.ws.ConstantesRespuestasWS;



@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CreditoService creService;

	@Autowired
	private BitacoraService bitaService;

	@Autowired
	private MandatoService mandatoService;
	
	@Autowired
	private ReporteService reportService;

	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReportDao dao;
	
	
	@RequestMapping(value = { "/login.do" }, method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {

		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login ", e);

			return "proceso_error";

		}

		return "login_ns";
	}
		
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.POST)
	public String validaRutSerie(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request, HttpServletResponse response) {
		String mensaje="OK";
		try {

			SalidainfoAfiliadoVO salida = null;
			String rut= form.getRutCliente().replace(".", "").toUpperCase();
			model.addAttribute("rut", rut.toUpperCase());
			model.addAttribute("serie", form.getSerie());
			
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();

			salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);

			logger.debug(rut + "Es Deudor Directo:" + salida.isDeudordirecto());

			if (salida.isDeudordirecto() && salida.getNombreCompleto()==null) {

				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRutCliente());
				model.addAttribute("serie", form.getSerie());

				return "logout";
			}

			ClienteSinacofi cli = new ClienteSinacofi();
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, form.getSerie());
			mensaje= respSina.getMensaje();
			if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
				if(respSina!=null){
					logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
				}
				model.addAttribute("errorMsg", "servicio_error");
				return "login_ns";
			}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
					logger.info("mensaje: " + respSina.getMensaje());
					model.addAttribute("errorMsg", "serie_error");
					return "login_ns";
			}			
		
			request.getSession().setAttribute("rut", rut);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			form.setRutCliente(rut);
			form.setNombreCliente(salida.getNombreCompleto());
			getCopago(model, form, request, response);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login serie ", e);
			mensaje= e.getMessage();
			return "login_ns";

		}
		model.addAttribute("mensaje", mensaje);
		return "index-paso2";
	}
	
	@RequestMapping(value = { "/initCopago.do" }, method = RequestMethod.POST)
	public String validaRutClave(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request, HttpServletResponse response) {

		try {
			String rut= form.getRutCliente();
			if(rut==null || rut.equals("")){
				return "login_error";
			}
			rut= rut.replace(".", "").toUpperCase();
			String password= form.getPassword();
			
			logger.info("Validando rut y clave de " +  rut);
			UserPrincipal user = new UserPrincipal(rut, password);
			UserRegistryConnection urConnection = new UserRegistryConnection(user);
			logger.info("Clave OK: " + password);
			
			SalidainfoAfiliadoVO salida = null;
			
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();

			salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);

			if (salida.isDeudordirecto() && salida.getNombreCompleto()==null) {

				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRutCliente());
				logger.info(rut + " no es afiliado");
				return "logout";
			}

			request.getSession().setAttribute("rut", rut);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			form.setRutCliente(rut);
			form.setNombreCliente(salida.getNombreCompleto());
			
			getCopago(model, form, request, response);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login clave ", e);

			return "login_error";

		}

		return "index-paso2";
	}
	
	@RequestMapping(value = { "/copago.do" }, method = RequestMethod.GET)
	public String getCopago(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request, HttpServletResponse response) {

		try {
			DecimalFormat df = new DecimalFormat("###,###");
			
			String rut = form.getRutCliente();

			if(rut==null || rut.equals("")){
				return "login";
			}
			
			rut = rut.replace(".", "");
			
			List<MandatosEntity> mandatos = mandatoService.findMandatoByRut(Long.parseLong(rut.split("-")[0]));
			logger.info("Mandatos encontrados vigente:" + mandatos.size());
			boolean mandato = false;
			
			if(mandatos.size() > 0) {
				
				mandato = true;
			}
			
			
			logger.debug("mandato: " + mandato);
			
			request.getSession().setAttribute("mandato", mandato);
			request.getSession().setAttribute("rutMandato", rut);
			
			String existe = "NO";

			List<CreditoVo> listaCreditos = new ArrayList<CreditoVo>();
			List<CreditoEntiti> credito = creService.findAllByRut(Long.parseLong(rut.split("-")[0]));
			logger.info("Crédios para Copago encontrados:" + credito.size());
			
			if (credito.size() == 0) {

				return "sin_data";
			}
			
			int isIn = 0;
			
			
			for (CreditoEntiti cre : credito) {

				if (bitaService.findAllByAutorized(cre.getnCredito(), cre.getNcuota()).size() == 0) {

					isIn++;

				}
			}

			if (isIn == 0) {

				existe = "SI";
			}

			logger.info("Verificando si ya tiene los créditos autorizados: " + existe);
			for (CreditoEntiti creditoEntiti : credito) {

				CreditoVo vo = new CreditoVo();

				vo.setnCredito(creditoEntiti.getnCredito());
				vo.setValorCuota(df.format(creditoEntiti.getValorCuota()));
				vo.setMontoBeneficio(df.format(creditoEntiti.getMontoBeneficio()));

				listaCreditos.add(vo);
			}

			//SalidainfoAfiliadoVO res = (SalidainfoAfiliadoVO) request.getSession().getAttribute("crm");

			model.addAttribute("rut", rut);
			model.addAttribute("existe", existe);
			model.addAttribute("credito", listaCreditos);

			request.getSession().setAttribute("datos", form);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 1  ", e);

			return "proceso_error";
		}

		return "index-paso2";
	}

	 
	
	@RequestMapping(value = { "/mandatoCopago.do" }, method = RequestMethod.GET)
	public String invokeMandato(ModelMap model, HttpServletRequest request, String urlRetorno) {

		try {			
			String rut= (String)request.getSession().getAttribute("rutMandato");
			if(rut==null || rut.equals("")){
				return "login";
			}
			logger.info("Invocando Mandato RUT: " + rut);
			request.setAttribute("rutEncode", rut.replaceAll("\\.", ""));
			if(urlRetorno==null){
				request.setAttribute("urlRetorno",  Configuraciones.getConfig("url.retorno.copago"));
			}else{
				request.setAttribute("urlRetorno",  urlRetorno);
			}
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error invoke Mandato ", e);

			return "proceso_error";
		}

		return "main_mandato";
	}

	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.POST)
	public String step3(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		try {

			logger.debug("correo: " + form.getCorreo());

			CreditoVo credito = (CreditoVo) request.getSession().getAttribute("datos");
			
			if(credito==null ){
				return "login";
			}
			
			credito.setCorreo(form.getCorreo());
			
			//Se graba Bitacora
			logger.info("Se obtiene todos los créditos RUT: " + credito.getRutCliente().split("-")[0]);
			List<CreditoEntiti> cre = creService.findAllByRut(Long.parseLong(credito.getRutCliente().split("-")[0]));
			logger.info("Cantidad créditos= " + cre.size());
			
			for (CreditoEntiti cred : cre) {

				BitacoraEntiti en = new BitacoraEntiti();

				en.setAutorizado("SI");
				en.setCorreo(credito.getCorreo());
				en.setDvCliente(credito.getRutCliente().split("-")[1]);
				en.setFecha(new Date());
				en.setnCredito(cred.getnCredito());
				en.setValorCuota(cred.getValorCuota());
				en.setNcuota(cred.getNcuota());
				en.setRutCliente(Long.parseLong(credito.getRutCliente().split("-")[0]));
				en.setMontoBeneficio(cred.getMontoBeneficio());
				
				List<BitacoraEntiti> no_autorizado= bitaService.findAllByNotAutorized(cred.getnCredito(),
						Integer.parseInt(String.valueOf(cred.getNcuota())));
				if(no_autorizado.size()>0){
					en.setAutorizado("SI");
					en.setCorreo(credito.getCorreo());
					en.setFecha(new Date());
					logger.info("Credito con rechazo previo " +  cred.getnCredito()  + ", se actualiza a Autorizado");
					bitaService.update(en);
				}else{
					List<BitacoraEntiti> ya_autorizado= bitaService.findAllByAutorized(cred.getnCredito(),
							Integer.parseInt(String.valueOf(cred.getNcuota())));
					if(ya_autorizado.size()==0){
						logger.info("Credito " + cred.getnCredito() + " no existe en bitácora, se graba registro");
						bitaService.save(en);
					}else{
						logger.info("Credito " + cred.getnCredito() + " ya existía en bitácora, no se modifica");
					}
				}
				

				logger.debug("Datos guardados con folio " + en.getnCredito());
			}
			
			//Se genera report PDF para enviar solo por correo
			logger.debug("Se genera Reporte Aprobación Copago para RUT " + credito.getRutCliente()); 
			String ruta = reportService.generarReport(request, null, credito, dao.getConnection(), false);

			if (credito.getCorreo()!=null && !credito.getCorreo().trim().equals("")) {
				logger.debug("Enviando Autorización por correo a " +  credito.getCorreo());
				mailService.sendEmail(credito.getCorreo(), "Autorización Programa Copago de Crédito - La Araucana",
						Utils.emailCliente(credito), credito.getRutCliente(), ruta);

			}
			
			request.getSession().setAttribute("datos", credito);
			
			if((Boolean)request.getSession().getAttribute("mandato")==false){
				invokeMandato(model, request, Configuraciones.getConfig("url.retorno.aprobacion.copago"));
				return "main_mandato";
			}


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

			return "proceso_error";
		}

		return "proceso-exito";
	}
	
	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.GET)
	public String volverStep3(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		try {


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error volver paso 3 ", e);

			return "proceso_error";
		}

		return "proceso-exito";
	}

	@RequestMapping(value = { "/procesado.do" }, method = RequestMethod.POST)
	public String step4_1(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String email = request.getParameter("emailDescarga");
			request.getSession().setAttribute("emailDescarga", email);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en procesado ", e);

			return "proceso_error";

		}
		return "index-aprobado";
	}

	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {

			/*
			 * request.getSession().removeAttribute("rut");
			 * request.getSession().removeAttribute("nombre");
			 * request.getSession().removeAttribute("telefono");
			 * request.getSession().removeAttribute("email");
			 */
			logger.debug("Invalidando sesión");
			request.getSession().invalidate();
			response.sendRedirect("salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "proceso_error";
		}

		return null;
	}

}
