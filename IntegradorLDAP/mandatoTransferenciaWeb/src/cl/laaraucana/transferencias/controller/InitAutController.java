package cl.laaraucana.transferencias.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;
import cl.laaraucana.transferencias.services.BancoService;
import cl.laaraucana.transferencias.services.MandatoAS400Service;
import cl.laaraucana.transferencias.services.TelefonoService;
import cl.laaraucana.transferencias.util.MandatoComparator;
import cl.laaraucana.transferencias.vo.DatosMandatoVo;
import cl.laaraucana.transferencias.vo.EjecutivoVo;
import cl.laaraucana.transferencias.vo.SalidaSinacofiVO;
import cl.laaraucana.transferencias.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.transferencias.ws.ClienteInfoAfiliado;
import cl.laaraucana.transferencias.ws.ClienteSinacofi;
import cl.laaraucana.transferencias.ws.ConstantesRespuestasWS;


@Controller
public class InitAutController {
	private String rol="user";
	
	private static final Logger logger = Logger.getLogger(InitAutController.class);

	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private MandatoAS400Service mandatoas400Service;
	
	@Autowired
	private TelefonoService telefonoService;
	
	@RequestMapping(value = { "/initAu.do" }, method = RequestMethod.POST)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		String rut_usuario = "";
		
		try {
			String url_retorno = (String)request.getSession().getAttribute("urlRetorno");
			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut_usuario = principal.getName();

				logger.debug("rut: " + rut_usuario);
			}else{
				rut_usuario= request.getParameter("rutAfiliado").replaceAll("\\.", "");
			}
			logger.info("Ingreso no autenticado RUT:" + rut_usuario);
			
			if(url_retorno==null){
				url_retorno= request.getParameter("urlRetorno");
				request.getSession().setAttribute("urlRetorno", url_retorno);
			}
			
			logger.info("Url retorno=" + url_retorno);

			String serie= request.getParameter("serie");
			logger.info("Serie cédula=" + serie);
			String Xserie= (String)request.getSession().getAttribute("Xserie");
			if(serie!=null && !serie.equals("")){
				logger.info("Serie seguridad=" + Xserie);
				if(Xserie==null || !serie.equals(Xserie)){
					logger.info("Se valida cotra Sinacofi");
					ClienteSinacofi cli = new ClienteSinacofi();
					String mensaje = "";
					SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli
							.call(rut_usuario.replace("-", ""), serie);
					if (respSina != null && respSina.getCodigoError() == ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS) {
						String codigoSina = respSina.getCodigoRetorno();
						logger.info("Respuesta Sinacofi, codigo retorno= " + codigoSina);
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

					if (!mensaje.equals("OK")) {
						logger.warn("Respuesta no satisfactoria de Sinacofi:");
						model.addAttribute("errorMsg", "serie_error");

						model.addAttribute("rut", rut_usuario);
						model.addAttribute("serie", serie);

						return "login_ns";
					}
					
				}
			}else{
				model.addAttribute("rut", rut_usuario);
				return "login_ns";
			}


			EjecutivoVo form = new EjecutivoVo();

			form.setRutAfiliado(rut_usuario);

			request.getSession().setAttribute("ejecutivo", form);
			
			request.getSession().setAttribute("form", form);
			request.getSession().setAttribute("rut", form.getRutAfiliado());			
			
			ClienteInfoAfiliado info = new ClienteInfoAfiliado();

			logger.debug("Validando si RUT " + form.getRutAfiliado().replace(".", "") + " es afiliado");

			SalidainfoAfiliadoVO res = info.getDataAfiliado(form.getRutAfiliado().replace(".", ""));
			request.getSession().setAttribute("clienteInfo", res);
			
			if (res.isDeudordirecto() && res.getNombreCompleto()==null) {
				request.getSession().setAttribute("errorMsg", "rut_error");
				request.getSession().setAttribute("urlRetorno", "index.jsp");
				logger.info("Cliente no es Afiliado, cerrando sesión");
				return "redirect:/exit.do";
			}
			
			DatosMandatoVo mandato = new DatosMandatoVo();
			mandato.setRut(rut_usuario);
			mandato.setNombre(res.getNombreCompleto());
			request.getSession().setAttribute("data", mandato);
			 

			List<MandatoAS400Vo> vigentes = new ArrayList<MandatoAS400Vo>();
			List<MandatoAS400Vo> revocados = new ArrayList<MandatoAS400Vo>();

			List<MandatoAS400Vo> listaVigentes = mandatoas400Service.consultaMandatos(Integer.parseInt(rut_usuario.split("-")[0]));
			List<MandatoAS400Vo> listaRevocados = mandatoas400Service.consultaMandatosRev(Integer.parseInt(rut_usuario.split("-")[0]));
			
			logger.info("Cantidad Mandatos vigentes: " + listaVigentes.size() + ", revocados: " + listaRevocados.size());
			
			for (MandatoAS400Vo vig : listaVigentes) {

				MandatoAS400Vo vo = new MandatoAS400Vo();

				vo.setIdMandato(vig.getIdMandato());
				vo.setNombreBanco(bancoService.findBancoById(vig.getCodbanco()).getNombre());
				vo.setNumcuenta(vig.getNumcuenta());
				vo.setDesCuenta(bancoService.findAccountkById(vig.getIdtipcta()).getDescripcion());

				vigentes.add(vo);
			}

			for (MandatoAS400Vo rev : listaRevocados) {

				MandatoAS400Vo vo = new MandatoAS400Vo();

				vo.setIdMandato(rev.getIdMandato());
				vo.setNombreBanco(bancoService.findBancoById(rev.getCodbanco()).getNombre());
				vo.setNumcuenta(rev.getNumcuenta());
				vo.setDesCuenta(bancoService.findAccountkById(rev.getIdtipcta()).getDescripcion());

				revocados.add(vo);
			}

			if (vigentes.size() == 0 && revocados.size() == 0) {

				try {
					logger.info("Sin Mandatos, se redireccionará a formulario ingreso:");
					List<BancoVo> bancos= bancoService.getBanco();
					List<TipoCuentaVo> cuentas= bancoService.getTipoCuenta();

					model.addAttribute("bancos", bancos);
					model.addAttribute("cuentas", cuentas);					
					model.addAttribute("datos", mandato);

				} catch (Exception e) {
					logger.error("Error inicio ", e);
					return "mandato_error";
				}
				
				List<String> lista_prefijos_telefono= telefonoService.getPrefijoTelefono();
				List<String> lista_prefijos_celular= telefonoService.getPrefijoCelular();
				lista_prefijos_telefono.add(0, "");
				lista_prefijos_celular.add(0, "");
				lista_prefijos_telefono.add("");
				lista_prefijos_celular.add("");
				
				request.getSession().setAttribute("prefijostel", lista_prefijos_telefono);
				request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);
				
				return "main";
				//return "main-afi";
			}
			
			//Collections.sort(revocados, new MandatoComparator(false));
			
			model.addAttribute("mandatosVig", vigentes);
			model.addAttribute("mandatosRev", revocados);
			request.getSession().setAttribute("nombre", res.getNombreCompleto());
			

		} catch (Exception e) {
			logger.error("Error inicio ", e);
		}

		return "lista_mandato";
	}

	@RequestMapping(value = { "/volver.do" }, method = RequestMethod.POST)
	public String backStep(ModelMap model, HttpServletRequest request) {

		DatosMandatoVo form = (DatosMandatoVo) request.getSession().getAttribute("datos");

		try {

			model.addAttribute("rut", form.getRut());
			model.addAttribute("serie", form.getSerie());
			model.addAttribute("datos", form);
			model.addAttribute("nombre_completo", form.getNombre());
			model.addAttribute("banco", form.getBanco());
			model.addAttribute("cuenta", form.getTipoCuenta());
			
			List<BancoVo> bancos= bancoService.getBanco();
			List<TipoCuentaVo> cuentas= bancoService.getTipoCuenta();
			
			model.addAttribute("bancos", bancos);
			model.addAttribute("cuentas", cuentas);
			model.addAttribute("datos", form);
			
			List<String> lista_prefijos_telefono= telefonoService.getPrefijoTelefono();
			List<String> lista_prefijos_celular= telefonoService.getPrefijoCelular();
			lista_prefijos_telefono.add(0, "");
			lista_prefijos_celular.add(0, "");
			lista_prefijos_telefono.add("");
			lista_prefijos_celular.add("");
			
			request.getSession().setAttribute("prefijostel", lista_prefijos_telefono);
			request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);
			
		} catch (Exception e) {

			logger.error("Volver ", e);

			return "mandato_error";

		}

		return "main";
	}
	
}
