package cl.laaraucana.ventaremota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.model.UsuarioVo;
import cl.laaraucana.ventaremota.services.BitacoraService;
import cl.laaraucana.ventaremota.util.CodigosSingleton;
import cl.laaraucana.ventaremota.util.Configuraciones;
import cl.laaraucana.ventaremota.vo.AnswerVo;
import cl.laaraucana.ventaremota.vo.QuestionVo;
import cl.laaraucana.ventaremota.vo.ResultadoVo;
import cl.sinacofi.WebServices.CEDU0702SoapStub;
import cl.sinacofi.WebServices.RespuestaCEDU0702;
import cl.sinacofi.WebServices.RespuestaSNPV1801;
import cl.sinacofi.WebServices.SNPV1801SoapStub;
import cl.sinacofi.wsdl.SDN122REQ.RESPUESTAS;
import cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTAS;
import cl.sinacofi.wsdl.SDN125RES.ArrayOfPREGUNTASPREGUNTASALTERNATIVAS;

@Controller
public class QuestionController {

	private static final Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private BitacoraService bitaService;
	
	@RequestMapping(value = { "/question.do" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String controllerInit(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute() UsuarioVo form) {
		String forward="";
		try {

			String ep = Configuraciones.getConfig("ep.pre");
			String canal = Configuraciones.getConfig("canal.pre");
			String user = Configuraciones.getConfig("pre.usuario");
			String clave = Configuraciones.getConfig("pre.password");


			String rut = (String) request.getSession().getAttribute("rutLdap");
			String serie = form.getSerie();
			if (serie == null) {
				serie = (String) request.getSession().getAttribute("serieNum");
			}

			int nIntentosMax = 10011;
			int nDesafiosMax = 10006;
			String idChallenge = "";
			String expired = "";
			String hide = "show";
			int respuestasValidas=0;
			CEDU0702SoapStub stub = new CEDU0702SoapStub();

			stub._setProperty(CEDU0702SoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);

			logger.info("Invocando servicio preguntas para Rut: " + rut.replace("-", "") + ", serie: " + serie);
			RespuestaCEDU0702 resp = stub.consulta(user, clave, rut.replace("-", ""), serie, canal);
			
			if(resp==null){
				logger.warn("Retorno null en respuesta validación de Sinacofi");
				model.addAttribute("respuestasValidas", -9);
				model.addAttribute("hide", "hide");
				return "preguntas";
			}
			
			model.addAttribute("respuestasValidas", 10);
			
			int codigoRetorno= resp.getCodigoRetorno();
			String descrpcionSinacofi= CodigosSingleton.getInstance().getDescripcion(String.valueOf(codigoRetorno));
			logger.info("Respuesta Sinacofi Desafío: " + codigoRetorno + ":" + descrpcionSinacofi);
			
			CreditoEntiti credito = (CreditoEntiti) request.getSession().getAttribute("credito");
			String ipRemota= request.getParameter("ipremota");
			
			if(resp.getRESUMEN()!=null && resp.getRESUMEN().getIDCHALLENGE()!=null){
				idChallenge = resp.getRESUMEN().getIDCHALLENGE();
			}
			bitaService.insertBitacora(credito, idChallenge, String.valueOf(codigoRetorno), "", ipRemota);
			
			request.setAttribute("mesage", descrpcionSinacofi);
			request.getSession().setAttribute("serieNum", serie);
			
			if (codigoRetorno ==10000){
				idChallenge = resp.getRESUMEN().getIDCHALLENGE();

				List<QuestionVo> preguntas = new ArrayList<QuestionVo>();

				for (ArrayOfPREGUNTASPREGUNTAS el : resp.getDETALLE().getDESAFIO()) {

					List<AnswerVo> respList = new ArrayList<AnswerVo>();

					QuestionVo pre = new QuestionVo();

					pre.setPregunta(el.getPREGUNTA().trim());
					pre.setCodPregunta(el.getCODIGO_PREGUNTA().trim());

					for (ArrayOfPREGUNTASPREGUNTASALTERNATIVAS res : el.getALTERNATIVAS()) {

						AnswerVo re = new AnswerVo();
						re.setRespuesta(res.getRESPUESTA().trim());
						re.setCodRespuesta(res.getCODIGO_RESPUESTA().trim());

						respList.add(re);
					}

					pre.setRespuesta(respList);

					preguntas.add(pre);

				}


				request.getSession().setAttribute("resultados", preguntas);

				request.getSession().setAttribute("_rut", rut);
				request.getSession().setAttribute("idChallenge", idChallenge);
				model.addAttribute("preguntas", preguntas);
				model.addAttribute("hide", hide);
				forward="preguntas";
			}else{
				if (codigoRetorno != nDesafiosMax && codigoRetorno != nIntentosMax) {

					logger.warn("Error en el llamado al servicio");
					request.setAttribute("errorMsg", "serie_error");
					forward= "serie-pregunta";
				}
				
				if (codigoRetorno == nIntentosMax) {

					respuestasValidas = 1;
					hide = "hide";
					logger.warn(descrpcionSinacofi);
					model.addAttribute("respuestasValidas", respuestasValidas);
					model.addAttribute("hide", hide);
					forward= "preguntas";

				}
				if (codigoRetorno == nDesafiosMax) {

					respuestasValidas = 0;
					hide = "hide";
					logger.warn(descrpcionSinacofi + " (o Sobrepasa el número de Desafíos)");
					model.addAttribute("respuestasValidas", respuestasValidas);
					model.addAttribute("hide", hide);
					forward= "preguntas";

				}
			}
			
			return forward;
			

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso 3  ", e);
			model.addAttribute("respuestasValidas", 0);
			model.addAttribute("hide", "hide");
			request.setAttribute("mesage", "Error interno: " + e.getMessage());
			return "preguntas";
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/validate.do" }, method = RequestMethod.POST)
	public String validar(ModelMap model, HttpServletRequest request, @ModelAttribute() ResultadoVo form,
			HttpServletResponse response) {
		String forward="";
		try {
			if(form.getOpcion1()==null){ form.setOpcion1("");}
			if(form.getOpcion2()==null){ form.setOpcion2("");}
			if(form.getOpcion3()==null){ form.setOpcion3("");}
			if(form.getOpcion4()==null){ form.setOpcion4("");}
			
			int nIntentosMax = 10011;
			String ep = Configuraciones.getConfig("ep.res");
			String canal = Configuraciones.getConfig("canal.pre");
			String user = Configuraciones.getConfig("pre.usuario");
			String clave = Configuraciones.getConfig("pre.password");

			List<QuestionVo> result = (List<QuestionVo>) request.getSession().getAttribute("resultados");

			String rut = (String) request.getSession().getAttribute("_rut");

			SNPV1801SoapStub _stub = new SNPV1801SoapStub();

			_stub._setProperty(SNPV1801SoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);

			RESPUESTAS[] res = new RESPUESTAS[4];

			res[0] = new RESPUESTAS(result.get(0).getCodPregunta(), form.getOpcion1());
			res[1] = new RESPUESTAS(result.get(1).getCodPregunta(), form.getOpcion2());
			res[2] = new RESPUESTAS(result.get(2).getCodPregunta(), form.getOpcion3());
			res[3] = new RESPUESTAS(result.get(3).getCodPregunta(), form.getOpcion4());
			
			String idChallenge = (String) request.getSession().getAttribute("idChallenge");

			logger.info("Invocando servicio validación para Rut: " + rut.replace("-", "") + ", id Challenge: "
					+ idChallenge);

			RespuestaSNPV1801 resp = _stub.consulta(user, clave, rut.replace("-", ""), canal, idChallenge, res);
			
			if(resp==null){
				logger.warn("Retorno null en respuesta validación de Sinacofi");
				model.addAttribute("respuestasValidas", -9);
				model.addAttribute("hide", "hide");
				return "preguntas";
			}
			
			String descripcionSinacofi= CodigosSingleton.getInstance().getDescripcion(String.valueOf(resp.getCodigoRetorno()));
			logger.info("Respuesta Sinacofi Validación: " + resp.getCodigoRetorno() + ":" + descripcionSinacofi);
			request.setAttribute("mesage", descripcionSinacofi);
			
			CreditoEntiti credito = (CreditoEntiti) request.getSession().getAttribute("credito");
			String ipRemota= request.getParameter("ipremota");
			
			if(resp.getRESUMEN()!=null && resp.getRESUMEN().getIDCHALLENGE()!=null){
				idChallenge = resp.getRESUMEN().getIDCHALLENGE();
				if(resp.getRESUMEN().getRESULTADO()!= null){
					bitaService.insertBitacora(credito, idChallenge,  String.valueOf(resp.getCodigoRetorno()), resp.getRESUMEN().getRESULTADO(), ipRemota);
				}
			}			
			
			if (resp.getCodigoRetorno() ==10000){
				String resultado = resp.getRESUMEN().getRESULTADO();
				logger.info("Resultado=" + resultado + ".");
				if (resultado.trim().equals("0")) {
					logger.info(descripcionSinacofi);
					//	CreditoEntiti credito = (CreditoEntiti) request.getSession().getAttribute("credito");
					model.addAttribute("tipoCredito", credito.getTipoCreditoNormal().trim());
					forward= "aprobar";

				} else {
					logger.warn("Eror en el Resultado del servicio");
					model.addAttribute("respuestasValidas", -9);
					model.addAttribute("hide", "hide");
					forward= "preguntas";
				}
			}else{
				if (resp.getCodigoRetorno() != 10007 && resp.getCodigoRetorno() != nIntentosMax) {
					request.getSession().setAttribute("serieNum", null);
					request.getSession().setAttribute("errorMsg", "serie_error");
					forward= "serie-pregunta";
				}else
				if (resp.getCodigoRetorno() == 10007) {
					logger.warn("Respuestas erróneas");
					model.addAttribute("respuestasValidas", -1);
					model.addAttribute("hide", "hide");
					forward= "preguntas";
				}else
				if (resp.getCodigoRetorno() == nIntentosMax) {

					logger.warn("Respuestas erróneas, max intentos");
					model.addAttribute("respuestasValidas", 1);
					model.addAttribute("hide", "hide");
					forward= "preguntas";
				}
			}	
			return forward;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 3  ", e);

			return "registro_error";
		}

	}

	@RequestMapping(value = { "/paso.do" }, method = RequestMethod.POST)
	public String paso(ModelMap model, HttpServletRequest request, @ModelAttribute() UsuarioVo user,
			HttpServletResponse response) {

		try {

			String serie = (String) request.getSession().getAttribute("serie");

			logger.debug("serie: " + serie);
		
			if (serie != null) {
				if (serie.equals("OK")) {

					return "redirect:/question.do";
				} else {

					return "serie-pregunta";
				}

			} else {
				return "serie-pregunta";
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error paso 5  ", e);

			return "registro_error";
		}
	}

}
