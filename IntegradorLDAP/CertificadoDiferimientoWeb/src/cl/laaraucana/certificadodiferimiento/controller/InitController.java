package cl.laaraucana.certificadodiferimiento.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.ResponseBody;

import cl.araucana.ws.ServicioSMSInternoProxy;
import cl.laaraucana.certificadodiferimiento.entities.BitacoraEntiti;
import cl.laaraucana.certificadodiferimiento.entities.CreditoEntiti;
import cl.laaraucana.certificadodiferimiento.entities.PrefijoTelefonoEntity;
import cl.laaraucana.certificadodiferimiento.model.CodigoVo;
import cl.laaraucana.certificadodiferimiento.model.CreditoVo;
import cl.laaraucana.certificadodiferimiento.services.BitacoraService;
import cl.laaraucana.certificadodiferimiento.services.ContactoDWHServices;
import cl.laaraucana.certificadodiferimiento.services.CreditoService;
import cl.laaraucana.certificadodiferimiento.services.TelefonoService;
import cl.laaraucana.certificadodiferimiento.util.Configuraciones;
import cl.laaraucana.certificadodiferimiento.vo.AfiliadoVo;
import cl.laaraucana.certificadodiferimiento.vo.SalidaSinacofiVO;
import cl.laaraucana.certificadodiferimiento.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.certificadodiferimiento.ws.ClienteInfoAfiliado;
import cl.laaraucana.certificadodiferimiento.ws.ClienteSMSService;
import cl.laaraucana.certificadodiferimiento.ws.ClienteSinacofi;
import cl.laaraucana.certificadodiferimiento.ws.ConstantesRespuestasWS;
import cl.laaraucana.sms.ws.MessageOutput;



 

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CreditoService creService;

	@Autowired
	private BitacoraService bitaService;
	
	@Autowired
	private TelefonoService telefonoService;
	
	@Autowired
	private ContactoDWHServices contactoDHW;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
			request.getSession().setAttribute("error","");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 1  ", e);

			return "proceso_error";
		}

		return "index-paso1";
	}

	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step2(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		DecimalFormat df = new DecimalFormat("###,###");

		try {
			String rut= form.getRutCliente().replaceAll("\\.", "").toUpperCase();
			String numeroSerie= form.getSerie();
			
			model.addAttribute("rut", form.getRutCliente());
			model.addAttribute("serie", form.getSerie());
			
			ClienteInfoAfiliado info = new ClienteInfoAfiliado();

			logger.info("ClienteInfo rut: " + rut);

			SalidainfoAfiliadoVO res = info.getDataAfiliado(rut);

			if (res.getNombreCompleto()==null || res.getNombreCompleto().equals("") ) {

				model.addAttribute("errorMsg", "rut_error");

				return "index-paso1";
			}

			ClienteSinacofi cli = new ClienteSinacofi();
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, form.getSerie());
			
			if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
				if(respSina!=null){
					logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
				}
				model.addAttribute("errorMsg", "servicio_error");
				return "index-paso1";
			}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
					logger.info("mensaje: " + respSina.getMensaje());
					model.addAttribute("errorMsg", "serie_error");
					return "index-paso1";
			}

			//Datos de contacto desde DWH
			AfiliadoVo data_afiliado= new AfiliadoVo();
			data_afiliado.setRut(rut);
			data_afiliado.setNombre(res.getNombreCompleto());
			data_afiliado.setIp(request.getRemoteAddr());
			data_afiliado.setNumeroSerie(numeroSerie);
			
			//Se busca celular en bitacora
			List<BitacoraEntiti> listabita= bitaService.findAllByRutBita(Integer.parseInt(rut.split("-")[0]));
			if(listabita!=null && listabita.size()>0){
				data_afiliado.setCelular(listabita.get(0).getCelular());
				logger.info("celular bitácora: " + data_afiliado.getCelular());
			}else{
				//se busca celular en DWH
				data_afiliado= contactoDHW.obtenerDatosContacto(data_afiliado);
				logger.info("celular DWH: " + data_afiliado.getCelular());
			}
			
			request.getSession().setAttribute("data", data_afiliado);
			
			List<PrefijoTelefonoEntity> listapc= telefonoService.getPrefijoCelular();
			List<String> lista_prefijos_celular= new ArrayList<String>();
			for (Iterator iterator = listapc.iterator(); iterator
					.hasNext();) {
				PrefijoTelefonoEntity prefijoEntity = (PrefijoTelefonoEntity) iterator.next();
				lista_prefijos_celular.add(prefijoEntity.getPrefijo());
				
			}
			
			lista_prefijos_celular.add(0, "");
			lista_prefijos_celular.add("");
			request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);

			request.getSession().setAttribute("error","");

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 2 ", e);

			return "proceso_error";
		}

		return "index-paso2";
	}
	
	@RequestMapping(value = { "/enviarSMS.do" }, method = RequestMethod.POST)
	public @ResponseBody String enviarSMS(@RequestParam  String celular, ModelMap model, HttpServletRequest request) {

		try {
			boolean e_sms=false;
			AfiliadoVo dataAfiliado= (AfiliadoVo)request.getSession().getAttribute("data");
			
			String mensaje_sms= Configuraciones.getConfig("app.envio.sms.mensaje");
			
			logger.info("Celular desde formulario: " + celular);
			if( celular!=null && !celular.equals("")){
				dataAfiliado.setCelular(celular);
				//ServicioSMSInternoProxy proxy= new ServicioSMSInternoProxy();
				//proxy.setEndpoint(urlsms);
				
				String codigo= generarCodigo();
				String msgsms= mensaje_sms.replaceAll("#codigo#", codigo);
				logger.info("enviando codigo " + codigo + " vía SMS a " + celular);
				//e_sms= proxy.enviarSMS(celular, msgsms, "1", codigo_negocio);
				ClienteSMSService clientesms= new ClienteSMSService();
				MessageOutput output= clientesms.sendMesage(dataAfiliado.getRut(), dataAfiliado.getCelular(), msgsms);
				if(output!=null & output.getStatusCode().equals("SENT")){
					e_sms=true;
				}
				logger.info("Mensaje SMS enviado: + " + e_sms);
				
				request.getSession().setAttribute("codigo", codigo);
				request.getSession().setAttribute("data", dataAfiliado);
			}else{
				return "vacio";
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error enviar SMS  ", e);

			return "error";
		}
		
		return "exito";
	}
	
	private String generarCodigo() {
		// define the range
        int max = 999999;
        int min = 100000;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
		return String.valueOf(rand);
	}

	@RequestMapping(value = { "/paso3.do" }, method = RequestMethod.POST)
	public String step3(ModelMap model, @ModelAttribute CodigoVo form, HttpServletRequest request) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		DecimalFormat df = new DecimalFormat("###,###");

		try {
			AfiliadoVo dataAfiliado= (AfiliadoVo)request.getSession().getAttribute("data");
			String rut= dataAfiliado.getRut();
			String nombre= dataAfiliado.getNombre();
			String codigo_sesion= (String)request.getSession().getAttribute("codigo");
			if(codigo_sesion==null){
				codigo_sesion="";
			}
			String codigo_request= form.getCodigosms();
			
			if(!codigo_sesion.equals(codigo_request) || codigo_request.equals("")){
				request.getSession().setAttribute("error", "Código verificación no corresponde, intenta nuevamente");
				return "index-paso2";
			}
			
			dataAfiliado.setCodigoVerificacion(codigo_request);
			
			String existe = "NO";

			List<CreditoVo> listaCreditos = new ArrayList<CreditoVo>();
			List<CreditoEntiti> credito = creService
					.findAllByRut(Long.parseLong(rut.split("-")[0]));
			logger.info("Número de créditos encontrados: " + credito.size());
			int isIn = 0;

			for (CreditoEntiti cre : credito) {

				if (bitaService.findAllByAutorized(cre.getFolioCredito(),
						Integer.parseInt(String.valueOf(cre.getCuotaDiferir()))).size() == 0) {

					isIn++;

				}
			}

			if (isIn == 0) {

				existe = "SI";
			}

			String emptyTable = "notEmptyTable";

			if (credito.size() == 0) {

				emptyTable = "emptyTable";
			}

			for (CreditoEntiti creditoEntiti : credito) {

				CreditoVo vo = new CreditoVo();

				vo.setIdCredito(Long.parseLong(creditoEntiti.getFolioCredito()));
				vo.setCuotaDiferir(String.valueOf(creditoEntiti.getCuotaDiferir()));
				vo.setMontoCuota(String.valueOf(df.format(creditoEntiti.getMontoCuota())));
				vo.setFechaVencActual(sdf.format(creditoEntiti.getFechaVencActual()));
				vo.setFechaVencNuevo(sdf.format(creditoEntiti.getFechaVencNuevo()));

				listaCreditos.add(vo);
			}

			
			model.addAttribute("existe", existe);
			model.addAttribute("emptyTable", emptyTable);
				
			model.addAttribute("credito", listaCreditos);
			
			request.getSession().setAttribute("data", dataAfiliado);
			request.getSession().setAttribute("credito", credito);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 2 ", e);

			return "proceso_error";
		}

		return "index-paso3";
	}
	
	@RequestMapping(value = { "/aprobacion.do" }, method = RequestMethod.POST)
	public String aprobacion(ModelMap model, @ModelAttribute CreditoVo form, HttpServletRequest request) {

		try {
			request.getSession().setAttribute("error","");
			
			AfiliadoVo dataAfiliado = (AfiliadoVo) request.getSession().getAttribute("data");

			logger.info("Email para notificar: " + form.getEmail());
			
			dataAfiliado.setEmail(form.getEmail());

			request.getSession().setAttribute("data", dataAfiliado);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3 ", e);

			return "proceso_error";
		}

		return "proceso-exito";
	}

	@RequestMapping(value = { "/procesado.do" }, method = RequestMethod.POST)
	public String step4_1(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String email= request.getParameter("emailDescarga");
			request.getSession().setAttribute("emailDescarga", email);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en procesado ", e);

			return "proceso_error";

		}
		return "index-aprobado";
	}

	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET)
	public String cerrarSesion(ModelMap model, HttpServletRequest request) {

		try {
			request.getSession().setAttribute("telefono", null);
			request.getSession().setAttribute("email", null);
			request.getSession().invalidate();
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "salir";
	}
}
