package cl.laaraucana.mandatocesantia.controller;

import java.text.DecimalFormat;
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
import cl.laaraucana.mandatocesantia.entities.BitacoraEntiti;
import cl.laaraucana.mandatocesantia.model.CesantiaVo;
import cl.laaraucana.mandatocesantia.reportDao.ReportDao;
import cl.laaraucana.mandatocesantia.services.BitacoraService;
import cl.laaraucana.mandatocesantia.services.CreditoService;
import cl.laaraucana.mandatocesantia.services.MailService;
import cl.laaraucana.mandatocesantia.services.ReporteService;
import cl.laaraucana.mandatocesantia.util.ConstantesRespuestasWS;
import cl.laaraucana.mandatocesantia.util.Utils;
import cl.laaraucana.mandatocesantia.vo.SalidaSinacofiVO;
import cl.laaraucana.mandatocesantia.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.mandatocesantia.ws.ClienteInfoAfiliado;
import cl.laaraucana.mandatocesantia.ws.ClienteSinacofi;


@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CreditoService creService;

	@Autowired
	private BitacoraService bitaService;

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
	public String validaRutSerie(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		String mensaje="OK";
		try {

			SalidainfoAfiliadoVO salida = null;
			String rut= form.getRutCliente().replace(".", "").toUpperCase();
			request.getSession().setAttribute("rut", rut);
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
			model.addAttribute("mensaje", mensaje);
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
		
			
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			form.setRutCliente(rut);
			form.setNombreCliente(salida.getNombreCompleto());
			CesantiaVo cesantia= new CesantiaVo();
			cesantia.setRutCliente(rut.split("-")[0]);
			cesantia.setDvCliente(rut.split("-")[1]);
			cesantia.setNombreCliente(salida.getNombreCompleto());
			request.getSession().setAttribute("data", cesantia);
			
			retorno= getMandatoCesantia(model, form, request, response);
		} catch (Exception e) {
			// TODO: handle exception
			mensaje= e.getMessage();
			logger.error("Error login serie ", e);

			return "login_ns";

		}
		model.addAttribute("mensaje", mensaje);
		return retorno;
	}
	
	@RequestMapping(value = { "/initMandato.do" }, method = RequestMethod.POST)
	public String validaRutClave(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
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
			CesantiaVo cesantia= new CesantiaVo();
			cesantia.setRutCliente(rut.split("-")[0]);
			cesantia.setDvCliente(rut.split("-")[1]);
			cesantia.setNombreCliente(salida.getNombreCompleto());
			request.getSession().setAttribute("data", cesantia);
			
			retorno= getMandatoCesantia(model, form, request, response);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login clave ", e);

			retorno= "login_error";

		}

		return retorno;
	}
	
	@RequestMapping(value = { "/cesantia.do" }, method = RequestMethod.GET)
	public String getMandatoCesantia(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		try {
			DecimalFormat df = new DecimalFormat("###,###");
			
			String rut = form.getRutCliente();

			if(rut==null || rut.equals("")){
				return "login";
			}
			
			rut = rut.replace(".", "");
			
			String existe = "NO";
			int rut_int= Integer.parseInt(rut.split("-")[0]);
			List<BitacoraEntiti> bitacora_autorizada= bitaService.findAllByRutBita(rut_int);
			if(bitacora_autorizada!=null && bitacora_autorizada.size()>0){
				
				BitacoraEntiti bitacora= bitacora_autorizada.get(0);
				CesantiaVo cesantiaVO= new CesantiaVo();
				cesantiaVO.setRutCliente(String.valueOf(bitacora.getRutCliente()));
				cesantiaVO.setDvCliente(bitacora.getDvCliente());
				cesantiaVO.setNombreCliente(bitacora.getNombre());
				cesantiaVO.setEmail(bitacora.getEmail());
				String celular= bitacora.getCelular();
				if(celular.length()>2 && celular.substring(0, 2).equals("56")){
					celular= "+" + celular;
				}
				cesantiaVO.setCelular(celular);
				String telefono= bitacora.getTelefono();
				if(telefono.length()>2 && telefono.substring(0, 2).equals("56")){
					telefono= "+" + telefono;
				}
				cesantiaVO.setTelefono(bitacora.getTelefono());
				cesantiaVO.setFecha(bitacora.getFecha());
				request.getSession().setAttribute("data", cesantiaVO);
				
				if(bitacora_autorizada.get(0).getAutorizado().equals("SI")){
					existe="SI";
				}
				retorno= "index-paso2";
			}else{
				boolean credito_con_cesantia = creService.findCreditoCesantiaByRut(rut);
				logger.info("Crédito con cesantía:" + credito_con_cesantia);

				if (credito_con_cesantia) {

					retorno= "index-paso2";
				}else{
					retorno= "sin_data";
				}	
			}
			request.getSession().setAttribute("existe", existe);
			

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 1  ", e);

			retorno= "proceso_error";
		}

		return retorno;
	}

	 
	
	
	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.POST)
	public String step3(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request) {

		try {


			CesantiaVo cesantia = (CesantiaVo) request.getSession().getAttribute("data");
			
			if(cesantia==null ){
				return "login";
			}
			
			cesantia.setEmail(form.getEmail());
			cesantia.setCelular(form.getCelular().replaceAll("\\+", ""));
			cesantia.setTelefono(form.getTelefono().replaceAll("\\+", ""));
			
			//Se graba Bitacora
			logger.info("Se graba bitácora");
			
			
			
			List<BitacoraEntiti> bitacora= bitaService.findAllByRutBita(Integer.parseInt(cesantia.getRutCliente()));
			if(bitacora!= null && bitacora.size()>0){
				String autorizado= bitacora.get(0).getAutorizado().toUpperCase();
				if( autorizado.equals("NO")){
					logger.info("Afiliado con rechazo previo " +  cesantia.getRutCliente() + "-" + cesantia.getDvCliente()  + ", se actualiza a Autorizado");
					BitacoraEntiti bita= bitacora.get(0);
					bita.setAutorizado("SI");
					bita.setFecha(new Date());
					bitaService.update(bita);
				}
			}else{
				logger.info("registro afiliado  " + cesantia.getRutCliente() + "-" + cesantia.getDvCliente() + " no existe en bitácora, se graba registro");
				BitacoraEntiti en = new BitacoraEntiti();

				en.setAutorizado("SI");
				en.setRutCliente(Long.parseLong(cesantia.getRutCliente()));
				en.setDvCliente(cesantia.getDvCliente());
				en.setNombre(cesantia.getNombreCliente());
				en.setEmail(cesantia.getEmail());
				en.setCelular(cesantia.getCelular());
				en.setTelefono(cesantia.getTelefono());
				en.setFecha(new Date());
				bitaService.save(en);
			}

			
			//Se genera report PDF para enviar solo por correo
			logger.info("Se genera Reporte Autorizacion Mandato Cesantía para RUT " + cesantia.getRutCliente() + "-" + cesantia.getDvCliente()); 
			String ruta = reportService.generarReport(request, null, cesantia, dao.getConnection(), false);

			if (cesantia.getEmail()!=null && !cesantia.getEmail().trim().equals("")) {
				logger.debug("Enviando Autorización por correo a " +  cesantia.getEmail());
				mailService.sendEmail(cesantia.getEmail(), "Autorización Mandato Cesantía - La Araucana",
						Utils.emailCliente(cesantia), cesantia.getRutCliente(), ruta);

			}
			
			request.getSession().setAttribute("data", cesantia);
			


		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);
			e.printStackTrace();
			return "proceso_error";
		}

		return "proceso-exito";
	}
	
	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.GET)
	public String volverStep3(ModelMap model, @ModelAttribute CesantiaVo form, HttpServletRequest request) {

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
