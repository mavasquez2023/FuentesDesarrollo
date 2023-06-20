package cl.laaraucana.mandato.controller;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.araucana.ldap.api.ProxyLDAP;
import cl.laaraucana.mandato.ibatis.vo.BancoVo;
import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandato.ibatis.vo.RechazoVo;
import cl.laaraucana.mandato.ibatis.vo.ResumenCargaRechazoVo;
import cl.laaraucana.mandato.ibatis.vo.TipoCuentaVo;
import cl.laaraucana.mandato.quartz.AsyncRechazoMandato;
import cl.laaraucana.mandato.services.BancoService;
import cl.laaraucana.mandato.services.MandatoAS400Service;
import cl.laaraucana.mandato.services.ProcessFilesService;
import cl.laaraucana.mandato.services.RechazoService;
import cl.laaraucana.mandato.util.Configuraciones;
import cl.laaraucana.mandato.util.Utils;
import cl.laaraucana.mandato.vo.CargaRechazoVO;
import cl.laaraucana.mandato.vo.DatosMandatoVo;
import cl.laaraucana.mandato.vo.EjecutivoVo;
import cl.laaraucana.mandato.vo.SalidaSinacofiVO;
import cl.laaraucana.mandato.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.mandato.ws.ClienteInfoAfiliado;
import cl.laaraucana.mandato.ws.ClienteSinacofi;
import cl.laaraucana.mandato.ws.ConstantesRespuestasWS;
import cl.laaraucana.mandato.services.TelefonoService;



@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);
	
	@Autowired
	ProcessFilesService filesServices;
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private MandatoAS400Service mandatoas400Service;
	
	@Autowired
	private RechazoService rechazoService;
	
	@Autowired
	private TelefonoService telefonoService;
	
	@RequestMapping(value = { "/cronta.do" }, method = RequestMethod.GET)
	public String executeCronta(ModelMap model, HttpServletRequest request) {
		
		/*AsyncEnvioArchivo cronta= new AsyncEnvioArchivo();
		cronta.enviarArchivoSAP();*/
		
		AsyncRechazoMandato cronta2= new AsyncRechazoMandato();
		cronta2.deshabilitarMandato();
		return "ejecutivo";

	}
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.POST)
	public String validaRutSerie(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request, HttpServletResponse response) {
		String retorno=null;
		String mensaje = "OK";
		try {
			SalidainfoAfiliadoVO salida = null;
			String rut= form.getRutAfiliado().replace(".", "").toUpperCase();
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();

			salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);

			logger.debug(rut + "Es Deudor Directo:" + salida.isDeudordirecto());

			if (salida.isDeudordirecto() && salida.getNombreCompleto()==null) {

				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRutAfiliado());
				model.addAttribute("serie", form.getSerie());

				return "ejecutivo";
			}

			ClienteSinacofi cli = new ClienteSinacofi();
			
			SalidaSinacofiVO respSina = (SalidaSinacofiVO) cli.call((String) rut.replace("-", ""), (String) form.getSerie());
			mensaje=respSina.getMensaje();
			if (respSina == null || respSina.getCodigoError() != ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS || respSina.getCodigoRetorno().equals("00000")) {
				if(respSina!=null){
					logger.info("Respuesta Sinacofi, codigo retorno= " + respSina.getCodigoRetorno() + ", Cedula Vigente=" + respSina.getCedulaVigente() + ", mensaje: " + respSina.getMensaje());
				}
				model.addAttribute("errorMsg", "servicio_error");
				return "ejecutivo";
			}else if (!respSina.getCodigoRetorno().equals("10000") || respSina.getCedulaVigente().trim().equals("NO")) {
					logger.info("mensaje: " + respSina.getMensaje());
					model.addAttribute("errorMsg", "serie_error");
					model.addAttribute("rut", form.getRutAfiliado().toUpperCase());
					model.addAttribute("serie", form.getSerie());
					return "ejecutivo";
			}
			
			
			request.getSession().setAttribute("rut", rut);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			form.setRutAfiliado(rut);
			form.setNombre(salida.getNombreCompleto());
			form.setRutEjecutivo((String)request.getSession().getAttribute("rutEjecutivo"));
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
			mensaje= e.getMessage();
			return "ejecutivo";

		}
		model.addAttribute("mensaje", mensaje);
		return retorno;
	}
	
	@RequestMapping(value = { "/ejecutivo.do" }, method = RequestMethod.GET)
	public String getInitEjecutivo(ModelMap model, HttpServletRequest request) {
		
		EjecutivoVo ejecutivo = new EjecutivoVo();
		List listroles=null;
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			ejecutivo.setRutEjecutivo(principal.getName());
			request.getSession().setAttribute("rutEjecutivo", principal.getName());
			logger.info("Ingreso Mandato ejecutivo: " + principal.getName());
			
			//Se busca rol de usuario
			listroles= (List)ProxyLDAP.getRolesUserinApp(principal.getName(), "IntegradorLDAP");
		}
		logger.info("Roles encontrados:" + listroles.toString());
		request.getSession().setAttribute("resumen", null);
		if(listroles!=null && listroles.size()>0){
			return "ejecutivo";
		}else{
			logger.warn("Rut ingreso no es ejecutivo:" + ejecutivo.getRutEjecutivo());
			return "redirect:/exit.do";
		}
		
	}
	
	@RequestMapping(value = { "lista.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute EjecutivoVo form, HttpServletRequest request) {

		try {
			String rut= form.getRutAfiliado();
			if(rut==null || rut.equals("")){
				return "redirect:/ejecutivo.do";
			}
			rut= rut.replace(".", "").toUpperCase();


			int rutint = Integer.parseInt(rut.split("-")[0]);

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

					request.getSession().setAttribute("rut", form.getRutAfiliado());
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
			
			List<String> lista_prefijos_telefono= telefonoService.getPrefijoTelefono();
			List<String> lista_prefijos_celular= telefonoService.getPrefijoCelular();
			lista_prefijos_telefono.add(0, "");
			lista_prefijos_celular.add(0, "");
			lista_prefijos_telefono.add("");
			lista_prefijos_celular.add("");
			
			model.addAttribute("bancos", bancos);
			model.addAttribute("cuentas", cuentas);
			model.addAttribute("nombre_completo", ejecutivo.getNombre());
			model.addAttribute("rut", rut);
			
			request.getSession().setAttribute("prefijostel", lista_prefijos_telefono);
			request.getSession().setAttribute("prefijoscel", lista_prefijos_celular);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error inicio ", e);
		}

		return "main";

	}


	@RequestMapping(value = { "/paso2.do" }, method = RequestMethod.POST)
	public String step2(ModelMap model, @ModelAttribute DatosMandatoVo form, HttpServletRequest request) {

		try {

			EjecutivoVo ejecutivo = (EjecutivoVo) request.getSession().getAttribute("ejecutivo");

			String rut = "";
			String mensaje = "";
			rut = ejecutivo.getRutAfiliado();
			
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
			mandatos.setSajkm94(ejecutivo.getRutEjecutivo());
			mandatos.setIdtippro(Integer.parseInt(Configuraciones.getConfig("codigo.canal.ejecutivo")));
			


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
			logger.info("Cerrando sesión ejecutivo");
			
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=index.jsp");
			
		} catch (Exception e) {

			logger.error("Error Ejecutivo Salir", e);

		}
		return null;
	}
	
	@RequestMapping(value = { "/rechazo.do" }, method = RequestMethod.GET )
	public String rechazo(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Rechazo , subir archivo");
			
		} catch (Exception e) {

			logger.error("Error Ejecutivo Salir", e);

		}
		return "cargar_rechazo";
	}
	
	@RequestMapping(value = { "/subirRechazo.do" }, method = RequestMethod.POST)
	public String subirRechazo(ModelMap model, @ModelAttribute CargaRechazoVO form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String path = Configuraciones.getConfig("mandato.archivo.rechazos");
			ResumenCargaRechazoVo resumen= filesServices.cargaArchivoRechazo(form.getRechazo());
			
			if(resumen!=null && resumen.getListaRechazo().size()>0){

				//Se graba registros en tabla de rechazos
				for (Iterator iterator = resumen.getListaRechazo().iterator(); iterator
						.hasNext();) {
					RechazoVo rechazo = (RechazoVo) iterator.next();
					rechazoService.insertRechazo(rechazo);
				}
				
				byte[] rechazo = form.getRechazo().getBytes();
				if (rechazo.length > 0) {
					String nombre_archivo="Rechazo_" + form.getRechazo().getOriginalFilename();
					File file = new File(path);
					if (!file.exists()) {
						file.mkdir();
					}
					logger.info("Guardando archivo: "  + nombre_archivo);
					Utils.descargar(path,
							nombre_archivo,
							rechazo);
				}
				
			}
			if(resumen.getListaErrores().size()>0){
				request.getSession().setAttribute("conerrores", resumen.getListaErrores());
			}
			request.getSession().setAttribute("resumen", resumen);
		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);
			model.addAttribute("mensaje", e.getMessage());
			request.setAttribute("mensaje", e.getMessage());
			return "error-process";
		}

		return "cargar_rechazo";
	}
	
	@RequestMapping(value = "conerrores.do", method = RequestMethod.GET)
	public @ResponseBody void conErrores(HttpServletRequest request, HttpServletResponse response) {

		
		try {
			List<String> AfiliadosConErrores= (List<String>)request.getSession().getAttribute("conerrores");
			StringBuffer conerrores= new StringBuffer();
			for (Iterator iterator = AfiliadosConErrores.iterator(); iterator
					.hasNext();) {
				String linea = (String) iterator.next();
				conerrores.append(linea + "\n");
			}
			
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=con_errores.csv");

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(conerrores.toString().getBytes());
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error download con_errores", e);
		}
	}
	
}
