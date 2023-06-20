package cl.laaraucana.transferencias.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import cl.laaraucana.transferencias.ibatis.vo.BancoVo;
import cl.laaraucana.transferencias.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.transferencias.ibatis.vo.TipoCuentaVo;
import cl.laaraucana.transferencias.services.BancoService;
import cl.laaraucana.transferencias.services.MandatoAS400Service;
import cl.laaraucana.transferencias.services.TelefonoService;
import cl.laaraucana.transferencias.util.Configuraciones;
import cl.laaraucana.transferencias.vo.DatosMandatoVo;
import cl.laaraucana.transferencias.vo.EjecutivoVo;
import cl.laaraucana.transferencias.vo.SalidaSinacofiVO;
import cl.laaraucana.transferencias.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.transferencias.ws.ClienteInfoAfiliado;
import cl.laaraucana.transferencias.ws.ClienteSinacofi;
import cl.laaraucana.transferencias.ws.ConstantesRespuestasWS;

@Controller
public class InitController {
	private String rol="user";
	
	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private MandatoAS400Service mandatoas400Service;
	
	@Autowired
	private TelefonoService telefonoService;
	
	@RequestMapping(value = { "/login.do" }, method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		return "login_ns";

	}
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.POST)
	public String validaRutSerie(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		String mensaje="OK";
		try {
			SalidainfoAfiliadoVO salida = null;
			String rut= form.getRutAfiliado().replace(".", "").toUpperCase();
			model.addAttribute("rut", rut);
			model.addAttribute("serie", form.getSerie());
			
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);

			logger.debug(rut + "Es Deudor Directo:" + salida.isDeudordirecto());

			if (salida.isDeudordirecto() && salida.getNombreCompleto()==null) {
				model.addAttribute("errorMsg", "rut_error");
				return "logout";
			}

			ClienteSinacofi cli = new ClienteSinacofi();
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call(rut, form.getSerie());
			mensaje=respSina.getMensaje();
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
			form.setRutAfiliado(rut);
			form.setNombre(salida.getNombreCompleto());
			request.getSession().setAttribute("ejecutivo", form);
			
			DatosMandatoVo data= new DatosMandatoVo();
			data.setRut(rut);
			data.setNombre(salida.getNombreCompleto());
			request.getSession().setAttribute("datos", data);
			
			retorno= getList(model, form, request);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login serie ", e);
			model.addAttribute("errorMsg", "servicio_error");
			mensaje=e.getMessage();
			return "login_ns";

		}
		model.addAttribute("mensaje", mensaje);
		return retorno;
	}
	
	@RequestMapping(value = { "/initMandato.do" }, method = RequestMethod.POST)
	public String validaRutClave(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		try {
			String rut= form.getRutAfiliado();
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
				model.addAttribute("rut", form.getRutAfiliado());
				logger.info(rut + " no es afiliado");
				return "logout";
			}

			request.getSession().setAttribute("rut", rut);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			form.setRutAfiliado(rut);
			form.setNombre(salida.getNombreCompleto());
			request.getSession().setAttribute("ejecutivo", form);
			
			DatosMandatoVo data= new DatosMandatoVo();
			data.setRut(rut);
			data.setNombre(salida.getNombreCompleto());
			request.getSession().setAttribute("datos", data);
			
			retorno= getList(model, form, request);
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error login clave ", e);

			retorno= "login_error";

		}

		return retorno;
	}
		
	@RequestMapping(value = { "lista.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request) {

		try {
			String rut= form.getRutAfiliado();
			if(rut==null || rut.equals("")){
				return "login_ns";
			}
			rut= rut.replace(".", "").toUpperCase();
			
			if (form.getRutAfiliado() != null) {

				int rutint = Integer.parseInt(form.getRutAfiliado().split("-")[0]);

				List<MandatoAS400Vo> vigentes = new ArrayList<MandatoAS400Vo>();
				List<MandatoAS400Vo> revocados = new ArrayList<MandatoAS400Vo>();
				List<MandatoAS400Vo> rechazados = new ArrayList<MandatoAS400Vo>();

				List<MandatoAS400Vo> listaVigentes = mandatoas400Service.consultaMandatos(rutint);
				List<MandatoAS400Vo> listaRevocados = mandatoas400Service.consultaMandatosRev(rutint);
				List<MandatoAS400Vo> listaRechazados = mandatoas400Service.consultaMandatosRechazados(rutint);
				logger.info("Cantidad Mandatos vigentes: " + listaVigentes.size() + ", revocados: " + listaRevocados.size() + ", rechazados_ " + listaRechazados.size());
				
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
					vo.setObservaciones(rev.getObservaciones());
					
					revocados.add(vo);
				}
				
				for (MandatoAS400Vo recha : listaRechazados) {

					MandatoAS400Vo vo = new MandatoAS400Vo();

					vo.setIdMandato(recha.getIdMandato());
					vo.setNombreBanco(bancoService.findBancoById(recha.getCodbanco()).getNombre());
					vo.setNumcuenta(recha.getNumcuenta());
					vo.setDesCuenta(bancoService.findAccountkById(recha.getIdtipcta()).getDescripcion());
					vo.setObservaciones(recha.getObservaciones());
					rechazados.add(vo);
				}

				if (vigentes.size() == 0 && revocados.size() == 0 && rechazados.size() == 0) {

					// *****
					try {

						logger.debug("Sin Mandatos, se redireccionará a formulario ingreso:");
						List<BancoVo> bancos= bancoService.getBanco();
						List<TipoCuentaVo> cuentas= bancoService.getTipoCuenta();

						//request.getSession().setAttribute("rut", rut);
						//form.setRol("ejecutivo");
						//request.getSession().setAttribute("ejecutivo", form);

						model.addAttribute("bancos", bancos);
						model.addAttribute("cuentas", cuentas);

						request.getSession().setAttribute("form", form);

					} catch (Exception e) {
						// TODO: handle exception

						logger.error("Error listado mandatos ", e);

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
				}

				model.addAttribute("mandatosVig", vigentes);
				model.addAttribute("mandatosRev", revocados);
				model.addAttribute("mandatosRechazado", rechazados);

				//form.setRol("ejecutivo");

				//request.getSession().setAttribute("ejecutivo", form);

			}

		} catch (Exception e) {
			logger.error("Error inicio ", e);
			return "mandato_error";
		}

		return "lista_mandato";
	}

	@RequestMapping(value = { "/mandatos.do" }, method = RequestMethod.GET)
	public String getTransfer(ModelMap model, HttpServletRequest request) {

		try {
			EjecutivoVo ejecutivo = (EjecutivoVo) request.getSession().getAttribute("ejecutivo");
			
			if(ejecutivo==null){
				return "ejecutivo";
			}
			
			String rut = ejecutivo.getRutAfiliado();

			List<BancoVo> bancos= bancoService.getBanco();
			List<TipoCuentaVo> cuentas= bancoService.getTipoCuenta();

			model.addAttribute("bancos", bancos);
			model.addAttribute("cuentas", cuentas);
			model.addAttribute("nombre_completo", ejecutivo.getNombre());
			model.addAttribute("rut", rut);


		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error inicio ", e);
		}

		return "main";

	}

	@RequestMapping(value = { "/mandatosAfi.do" }, method = RequestMethod.GET)
	public String getTransferAfi(ModelMap model, @ModelAttribute DatosMandatoVo form, HttpServletRequest request) {

		try {

			
			List<BancoVo> bancos= bancoService.getBanco();
			List<TipoCuentaVo> cuentas= bancoService.getTipoCuenta();
			List<String> lista_prefijos_telefono= telefonoService.getPrefijoTelefono();
			List<String> lista_prefijos_celular= telefonoService.getPrefijoCelular();
			lista_prefijos_telefono.add(0, "");
			lista_prefijos_celular.add(0, "");
			lista_prefijos_telefono.add("");
			lista_prefijos_celular.add("");
			
			model.addAttribute("bancos", bancos);
			model.addAttribute("cuentas", cuentas);
			request.getSession().setAttribute("prefijostel", lista_prefijos_telefono);
			request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);
			
			request.getSession().setAttribute("Xserie", "X" + Math.floor(Math.random()*100000));

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error inicio ", e);

			return "mandato_error";
		}

		return "main";

	}

	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step2(ModelMap model, @ModelAttribute DatosMandatoVo form, HttpServletRequest request) {

		try {

			EjecutivoVo ejecutivo = (EjecutivoVo) request.getSession().getAttribute("ejecutivo");

			//String rut = "";
			String mensaje = "";
			//rut = (String) request.getSession().getAttribute("rut");
			
			//SalidainfoAfiliadoVO res = (SalidainfoAfiliadoVO) request.getSession().getAttribute("clienteInfo");
			//DatosMandatoVo mandato = (DatosMandatoVo) request.getSession().getAttribute("datos");
			
			BancoVo banco = bancoService.findBancoById(Integer.parseInt(form.getBanco()));

			TipoCuentaVo cuenta = bancoService.findAccountkById(Integer.parseInt(form.getTipoCuenta()));
			
			form.setNameBanco(banco.getNombre());
			form.setNameCuenta(cuenta.getDescripcion());

			//form.setNombre(mandato.getNombre());
			//form.setRut(rut);
			model.addAttribute("datos", form);

			model.addAttribute("nombre_completo", form.getNombre());
			request.getSession().setAttribute("datos", form);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso1 ", e);

			return "mandato_error";

		}

		return "registrar";
	}

	@RequestMapping(value = { "/register.do" }, method = RequestMethod.POST)
	public String registe(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		DatosMandatoVo mandato = (DatosMandatoVo) request.getSession().getAttribute("datos");
		EjecutivoVo ejecutivo= (EjecutivoVo)request.getSession().getAttribute("ejecutivo");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");

		try {

			//String tipoProducto = Configuraciones.getConfig("sil.tipo.producto");

			long idMandato = mandatoas400Service.consultaMandatosGetId();
			
			String strIdMandato = String.valueOf(idMandato);
			int len = strIdMandato.length();

			strIdMandato= "00000000".substring(len) + strIdMandato;

			String id = sdf.format(new Date()) + strIdMandato;

			String[] rutSplit = mandato.getRut().replace(".", "").split("-");

			MandatoAS400Vo mandatos = new MandatoAS400Vo();

			mandatos.setCelular(mandato.getCelular().replaceAll("\\+", ""));
			mandatos.setCodbanco(Integer.parseInt(mandato.getBanco()));
			mandatos.setDvafi(rutSplit[1]);
			mandatos.setIdMandato(Long.parseLong(id));
			mandatos.setEmail(mandato.getEmail());
			mandatos.setFechavig(new Date());
			mandatos.setNombre(mandato.getNombre());
			mandatos.setNumcuenta(mandato.getCuenta());
			mandatos.setRutafi(Long.parseLong(rutSplit[0]));
			mandatos.setTelefono(mandato.getTelefono().replaceAll("\\+", ""));
			mandatos.setIdtipcta(Integer.parseInt(mandato.getTipoCuenta()));
			
			mandatos.setFechater(null);
			mandatos.setSajkm94(ejecutivo.getRutAfiliado());
			mandatos.setIdtippro(Integer.parseInt(Configuraciones.getConfig("codigo.canal.afiliado")));
			


			mandatoas400Service.insertMandato(Integer.parseInt(rutSplit[0]), mandatos);

			logger.debug("Mandato RUT " + mandatos.getRutafi() + " guardado en AS400 con id:" + id );

			mandato.setIdMandato(Long.parseLong(id));

			request.getSession().setAttribute("datos", mandato);


		} catch (Exception e) {
			// TODO Auto-generated catch block

			logger.error("Error al registrar ", e);

			return "mandato_error";
		}

		return "registro-exito";
	}
	
	@RequestMapping(value = { "/delete.do" }, method = RequestMethod.POST)
	public String deleteMandato(ModelMap model, @ModelAttribute DatosMandatoVo form, HttpServletRequest request,
			HttpServletResponse response) {
			long id= form.getIdMandato();
			String rut_usuario= ((String)request.getSession().getAttribute("rut")).replaceAll("\\.", "");
		try {
			logger.info("Borrando Mandato vigente id: " + id + ", asociado a RUT " + rut_usuario);
			boolean resultado= mandatoas400Service.deleteMandatoById(id);
			model.addAttribute("idMandato", id);
			
			List<MandatoAS400Vo> vigentes = new ArrayList<MandatoAS400Vo>();
			List<MandatoAS400Vo> revocados = new ArrayList<MandatoAS400Vo>();
			
			logger.info("Buscando lista de mandatos para refrescar vista"); 
			List<MandatoAS400Vo> listaVigentes = mandatoas400Service.consultaMandatos(Integer.parseInt(rut_usuario.split("-")[0]));
			List<MandatoAS400Vo> listaRevocados = mandatoas400Service.consultaMandatosRev(Integer.parseInt(rut_usuario.split("-")[0]));
			
			logger.info("Cantidad Mandatos vigentes: " + listaVigentes.size());
			logger.info("Cantidad Mandatos revocados: " + listaRevocados.size());
			
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
			
			model.addAttribute("mandatosVig", vigentes);
			model.addAttribute("mandatosRev", revocados);
			
		} catch (Exception ex) {

			logger.error("Error en la generación del reporte ", ex);
		}

		return "lista_mandato";
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			String urlRetorno= (String)request.getSession().getAttribute("urlRetorno");
			
			if(urlRetorno!=null && !urlRetorno.equals("")){
				logger.info("Redirecionado a " + urlRetorno);
				response.sendRedirect(urlRetorno);
				request.getSession().removeAttribute("urlRetorno");
			}else{
				logger.info("Cerrando sesión y Redirecionado a salir.jsp");
				request.getSession().invalidate();
				response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
			}
			
		} catch (Exception e) {

			logger.error("Error Exit: " +  e.getMessage());

		}
		return null;
	}
}
