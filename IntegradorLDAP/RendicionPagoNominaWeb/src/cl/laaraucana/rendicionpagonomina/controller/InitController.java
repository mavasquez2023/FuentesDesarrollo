package cl.laaraucana.rendicionpagonomina.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.araucana.ldap.api.ProxyLDAP;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBecasVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoBenefVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.services.BancoService;
import cl.laaraucana.rendicionpagonomina.services.BecasService;
import cl.laaraucana.rendicionpagonomina.services.BeneficioService;
import cl.laaraucana.rendicionpagonomina.services.CabeceraService;
import cl.laaraucana.rendicionpagonomina.services.DetalleService;
import cl.laaraucana.rendicionpagonomina.services.ParametrosService;
import cl.laaraucana.rendicionpagonomina.services.ProcesaArchivoGenericoTEF;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionesBCI;
import cl.laaraucana.rendicionpagonomina.utils.CodigoNominaComparator;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.utils.FechaCargaComparator;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.EstadosVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;
import cl.laaraucana.rendicionpagonomina.vo.NominaVo;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private CabeceraService cabeceraService;

	@Autowired
	private DetalleService detalleService;
	
	@Autowired
	ParametrosService parametroService;
	
	@Autowired
	ProcesaRendicionesBCI procesaRendicionesBCIService;
	
	@Autowired
	ProcesaArchivoGenericoTEF procesaArchivoGenericoTEF;
	
	@Autowired
	BancoService bancoService;
	
	@Autowired
	BeneficioService beneficiosService;
	
	@Autowired
	BecasService becasService;

	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String rol="";
		String app= Configuraciones.getConfig("app.autorizacion.ldap");
		String rol_cargaManual= Configuraciones.getConfig("rol.cargamanual.ldap");
		String rol_admin= Configuraciones.getConfig("rol.admin.ldap");
		String rol_operador= Configuraciones.getConfig("rol.operador.ldap");
		
		try {

			String rut_usuario = "";

			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut_usuario = principal.getName();

				logger.info("rut: " + rut_usuario);
				//Se busca rol de usuario
				
				
				List listroles= (List)ProxyLDAP.getRolesUserinApp(principal.getName(), app);
				
				if(listroles!=null && listroles.size()>0){
					if(listroles.contains(rol_admin)){
						rol=rol_admin;
						
					}else if(listroles.contains(rol_operador)){
						rol=rol_operador;
						
					}else if(listroles.contains(rol_cargaManual)){
						rol=rol_cargaManual;
						
					}
				}
				logger.info("rol usuario:" + rol);
			} else {

				return "redirect:/logout.do";
			}
			
			request.getSession().setAttribute("rol", rol);
			request.getSession().setAttribute("usuario", rut_usuario);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}
		if(rol.equals(rol_operador) || rol.equals(rol_admin)){
			envio(model, form, request);
			return "seguimiento_envio";
			
		}else{
			consulta(model, form, request);
			return "seguimiento_consulta";
		}
	}
	
	@RequestMapping(value = { "/envio.do" }, method = RequestMethod.GET)
	public String envio(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String rol= (String)request.getSession().getAttribute("rol");
		if(rol==null){
			return "redirect:/init.do";
		}
		
		request.getSession().setAttribute("operacion", "envio");
		String subaccion= request.getParameter("subaccion");
		if(subaccion==null){
			request.getSession().setAttribute("codigo_respuesta", "");
			request.getSession().setAttribute("mensaje", "");
		}
		menu_seguimiento(null, model, form, request);
		return "seguimiento_envio";
	}
	
	@RequestMapping(value = { "/crontaEnvio.do" }, method = RequestMethod.GET)
	public String crontaenvio(ModelMap model, HttpServletRequest request) {
		String mensajeSalida = "";
		try {
			int totalProcesados = procesaArchivoGenericoTEF.loadData();
			//mensaje seteado en jsp
			request.getSession().setAttribute("codigo_respuesta", "2");
			request.getSession().setAttribute("totalProcesados", totalProcesados);
		} catch (MiException e) {
			mensajeSalida = e.getMessage();
			request.getSession().setAttribute("codigo_respuesta", "-2");
		}
		request.setAttribute("mensaje", mensajeSalida);
		logger.info("En el proceso invocado de cronta envío, estado operacion :["+mensajeSalida+"]");
		return "seguimiento_envio";
	}
	
	@RequestMapping(value = { "/rendicion.do" }, method = RequestMethod.GET)
	public String rendicion(@RequestParam(value = "order", required = false) String order, ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String rol= (String)request.getSession().getAttribute("rol");
		if(rol==null){
			return "redirect:/init.do";
		}
		request.getSession().setAttribute("operacion", "rendicion");
		menu_seguimiento(null, model, form, request);
		return "seguimiento_rendicion" ;
	}
	
	@RequestMapping(value = { "/consulta.do" }, method = RequestMethod.GET)
	public String consulta(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String rol= (String)request.getSession().getAttribute("rol");
		if(rol==null){
			return "redirect:/init.do";
		}
		request.getSession().setAttribute("operacion", "consulta");
		menu_seguimiento(null, model, form, request);
		return "seguimiento_consulta";
	}
		
	@RequestMapping(value = { "/seguimientoTEF.do" }, method = RequestMethod.GET)
	public String menu_seguimiento(@RequestParam(value = "accion", required = false) String accion, ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		if(accion==null){
			request.getSession().setAttribute("fechaConsulta", null);
			request.getSession().setAttribute("estadoConsulta", null);
			request.getSession().setAttribute("convenioConsulta", null);
			request.getSession().setAttribute("productoConsulta", null);
			request.getSession().setAttribute("bancoConsulta", null);
			request.getSession().setAttribute("codigoConsulta", null);
			request.getSession().setAttribute("rutConsulta", null);
			request.getSession().setAttribute("nombreConsulta", null);
		}
		
		if(request.getSession().getAttribute("params")==null){
			
			try {
				parametroService.setParams();
			} catch (Exception e) {
				return "redirect:/init.do";
			}
		}
		seguimiento(model, form, request);
		return "seguimiento_" + operacion;
	}
	
	@RequestMapping(value = { "/seguimientoTEF.do" }, method = RequestMethod.POST)
	public String seguimiento(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			parametroService.setParams();
			request.getSession().setAttribute("params", "OK");
			
			//Fecha Consulta
			String fechaConsulta=form.getFechaCarga();
			if(fechaConsulta==null){
				fechaConsulta= (String)request.getSession().getAttribute("fechaConsulta");
			}else if(fechaConsulta.equals("")){
				fechaConsulta= null;
			}
			if(fechaConsulta!=null){
				form.setFechaCarga(fechaConsulta);
			}
			//request.getSession().setAttribute("fechaConsulta", form.getFechaCarga());	
			
			//Estado
			String estadoConsulta=form.getEstado();
			if(estadoConsulta==null){
				estadoConsulta= (String)request.getSession().getAttribute("estadoConsulta");
			}
			if(estadoConsulta!=null){
				form.setEstado(estadoConsulta);
			}
			List<EstadoEntity> listaEstados=parametroService.consultaEstadosNomina();
			
			//Convenio
			String convenioConsulta=form.getConvenio();
			if(convenioConsulta==null){
				convenioConsulta= (String)request.getSession().getAttribute("convenioConsulta");
			}
			if(convenioConsulta!=null){
				form.setConvenio(convenioConsulta);
			}
			List<ConvenioEntity> listaConvenios= parametroService.consultaConveniosActivos(idUsuario);			
			
			//Producto
			String productoConsulta=form.getProducto();
			if(productoConsulta==null){
				productoConsulta= (String)request.getSession().getAttribute("productoConsulta");
			}
			if(productoConsulta!=null){
				form.setProducto(productoConsulta);
			}
			
			//Bancos
			String bancoConsulta=form.getBanco();
			if(bancoConsulta==null){
				bancoConsulta= (String)request.getSession().getAttribute("bancoConsulta");
			}
			if(bancoConsulta!=null){
				form.setBanco(bancoConsulta);
			}
			List<String> listaBancos= bancoService.consultaBancosConvenio();
			
			//CódigoNomina
			String codigoConsulta=form.getCodigoNomina();
			if(codigoConsulta==null){
				codigoConsulta= (String)request.getSession().getAttribute("codigoConsulta");
			}else if(codigoConsulta.equals("")){
				codigoConsulta= null;
			}
			if(codigoConsulta!=null){
				form.setCodigoNomina(codigoConsulta);
			}
			
			//RutAfiliado
			String rutConsulta=form.getRutAfiliado();
			if(rutConsulta==null){
				rutConsulta= (String)request.getSession().getAttribute("rutConsulta");
			}else if(rutConsulta.equals("")){
				rutConsulta= null;
			}
			if(rutConsulta!=null){
				rutConsulta= rutConsulta.replaceAll("\\.", "");
				form.setRutAfiliado(rutConsulta);
			}
			
			//NombreAfiliado
			String nombreConsulta=form.getNombreAfiliado();
			if(nombreConsulta==null){
				nombreConsulta= (String)request.getSession().getAttribute("nombreConsulta");
			}else if(nombreConsulta.equals("")){
				nombreConsulta= null;
			}
			if(nombreConsulta!=null){
				form.setNombreAfiliado(nombreConsulta);
			}
			
			//dd-MM-yyyy
			String fechaHoy= Utils.dateToString(new Date());
			HashMap<String, String> params= new HashMap<String, String>();
			
			params.put("idUsuario", idUsuario);
			
			if(form.getFechaCarga()==null && form.getConvenio()==null && form.getProducto()==null && form.getEstado()==null && form.getCodigoNomina()==null && form.getBanco()==null && form.getRutAfiliado()==null && form.getNombreAfiliado()==null){
				form.setFechaCarga(fechaHoy);
				form.setConvenio("");
				form.setProducto("");
				form.setEstado("");
				form.setBanco("");
				form.setCodigoNomina("");
				form.setRutAfiliado("");
				form.setNombreAfiliado("");
				model.addAttribute("fechaConsulta", fechaHoy);
			}else{
				request.getSession().setAttribute("fechaConsulta", form.getFechaCarga());
				request.getSession().setAttribute("estadoConsulta", form.getEstado());
				request.getSession().setAttribute("convenioConsulta", form.getConvenio());
				request.getSession().setAttribute("productoConsulta", form.getProducto());
				request.getSession().setAttribute("bancoConsulta", form.getBanco());
				request.getSession().setAttribute("rutConsulta", form.getRutAfiliado());
				request.getSession().setAttribute("nombreConsulta", form.getNombreAfiliado());
				request.setAttribute("codigoConsulta", form.getCodigoNomina());
				
				if(form.getEstado()!=null && !form.getEstado().equals("")){
					params.put("estadoNomina", form.getEstado());
				}
				if(form.getConvenio()!=null && !form.getConvenio().equals("")){
					params.put("convenio", form.getConvenio());
				}
				if(form.getProducto()!=null && !form.getProducto().equals("")){
					params.put("producto", form.getProducto());
				}
				if(form.getBanco()!=null && !form.getBanco().equals("")){
					params.put("banco", form.getBanco());
				}
				if(form.getCodigoNomina()!=null && !form.getCodigoNomina().equals("")){
					params.put("codigoNomina", form.getCodigoNomina());
				}
				if(form.getRutAfiliado()!=null && !form.getRutAfiliado().equals("")){
					params.put("rutAfiliado", form.getRutAfiliado().split("-")[0]);
				}
				if(form.getNombreAfiliado()!=null && !form.getNombreAfiliado().equals("")){
					params.put("nombreAfiliado", form.getNombreAfiliado().trim());
				}
			}
			
			if(form.getFechaCarga()!=null && !form.getFechaCarga().equals("")){
				params.put("fechaCarga", form.getFechaCarga());
			}
			
		
			
			/*NominaVo nomina = new NominaVo();
			nomina.setRut("");
			nomina.setEstado(""+Estados.PENDIENTE);
			nomina.setNomina("");*/
			
			//model.addAttribute("nomina", nomina);
			request.getSession().setAttribute("convenios", listaConvenios);
			request.getSession().setAttribute("estados", listaEstados);
			request.getSession().setAttribute("bancos", listaBancos);

			//Valores por default (opciones menú)
			if(operacion!=null && estadoConsulta==null){
				if(operacion.equals("envio")){
					params.put("estado1", ""+Estados.NOMINA_PENDIENTE);
					params.put("estado2", ""+Estados.NOMINA_PENDIENTE);
					request.getSession().setAttribute("estadoConsulta", Estados.NOMINA_PENDIENTE);
				}else if(operacion.equals("rendicion")){
					params.put("estado1", ""+Estados.NOMINA_ENPROCESO);
					params.put("estado2", ""+Estados.NOMINA_ENPROCESO);
					request.getSession().setAttribute("estadoConsulta", Estados.NOMINA_ENPROCESO);
				}else if(operacion.equals("consulta")){
					params.put("estado1", ""+Estados.NOMINA_CERRADA);
					params.put("estado2", ""+Estados.NOMINA_CERRADA);
					request.getSession().setAttribute("estadoConsulta", Estados.NOMINA_CERRADA);
				}
				
			}
			
			if(request.getParameter("status")!= null) {
				params.put("estadoNomina", request.getParameter("status").toString());
				
				params.put("rutAfiliado", null );
				params.put("nombreAfiliado", null );
				params.put("estado1", null );
				params.put("estado2", null );
				params.put("codigoNomina", null );
				params.put("producto", null );
				params.put("convenio", null );
				params.put("banco", null );
				params.put("rutAfiliado", null );
				params.put("nombreAfiliado", null );
			}
			
			//logger.info("Busncando nóminas TEF");
			List<CabeceraEntity> resultado = cabeceraService.findNominasTEF(params);
			
			request.getSession().setAttribute("nominasCabecera", resultado);
			
			ArrayList<HashMap<String, Object>> nominasBCI = new ArrayList<HashMap<String,Object>>();
			try {
				/*if("envio".equals(operacion)) {
					nominasBCI = procesaLicenciasBCIService.getAvailablesFiles();
				}
				else*/ 
				if("rendicion".equals(operacion)) {
					nominasBCI = procesaRendicionesBCIService.getAvailablesFiles();
				}
			} catch (MiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("nominasBCI", nominasBCI);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}
		return "seguimiento_" + operacion;
	}
	
	@RequestMapping(value = { "/rendicionBCI.do" }, method = RequestMethod.POST)
	public String rendicionBCI(ModelMap model, HttpServletRequest request) {
	
		
		ArrayList<HashMap<String, Object>> files = new ArrayList<HashMap<String, Object>>();
		String[] archivos = request.getParameter("nominasBCISelected").split(";");
		if(archivos.length>0) {
			for (String input : archivos) {
				if(input.trim().length()>0 && input.trim().contains("::")) {
					HashMap<String, Object> file = new HashMap<String, Object>();
					file.put("file", input.trim().split("::")[0]);
					file.put("type", input.trim().split("::")[1]);
					files.add(file);
				}
			}
		}
		
		if(files.size()>0) {
			try {
				procesaRendicionesBCIService.executeByFiles(files);
			} catch (MiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return "licencias-result-rendicion";
	}
	
	@RequestMapping(value = { "/ordenarLista.do" }, method = RequestMethod.GET)
	public String ordenarLista(@RequestParam(value = "order", required = true) String order, ModelMap model, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			
			List<CabeceraEntity> listaResultado=  (List<CabeceraEntity>)request.getSession().getAttribute("nominasCabecera");
			boolean asc=false;
			String order_= "order_" + order.split(":")[0];
			String tipo_= order.split(":")[1];
			if(tipo_.equals("asc")){
				asc=true;
			}
			if(order_.equals("order_FechaCreacion")){
				Collections.sort(listaResultado, new FechaCargaComparator(asc));
			}else if(order_.equals("order_CodigoNomina")){
				Collections.sort(listaResultado, new CodigoNominaComparator(asc));
			}
			request.getSession().setAttribute("nominasCabecera", listaResultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en ordenarLista ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "seguimiento_" + operacion;
	}
	
	@RequestMapping(value = { "/detalleTEF.do" }, method = RequestMethod.GET)
	public String verDetalle(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			CabeceraEntity cabecera= (CabeceraEntity)cabeceraService.findById(Long.parseLong(id));
			request.getSession().setAttribute("cabecera_tef", cabecera);
			List<DetalleEntity> detalles_tef= detalleService.findByIdCabecera(Integer.parseInt(id));
			request.getSession().setAttribute("detalles_tef", detalles_tef);
			
			String fechaCarga= request.getParameter("fechaCarga");
			if(fechaCarga!=null){
				request.getSession().setAttribute("fechaConsulta", fechaCarga);
			}
			String estado= request.getParameter("estado");
			if(estado!=null){
				request.getSession().setAttribute("estadoConsulta", estado);
			}
			
			//List<EstadoEntity> estadospago= parametroService.consultaEstadosPago();
			//request.getSession().setAttribute("estadospago", estadospago);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_" + operacion;
	}
	
	@RequestMapping(value = { "/detalleEstado.do" }, method = RequestMethod.GET)
	public String verDetalleEstado(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "estado", required = true) String estado, ModelMap model, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			HashMap<String, Long> params= new HashMap<String, Long>();
			params.put("idCabecera", Long.parseLong(id));
			params.put("estado", Long.parseLong(estado));
			List<DetalleEntity> detalles=  detalleService.findByEstado(params);
			request.getSession().setAttribute("detalles_tef", detalles);
			model.addAttribute("estadoPago", estado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en detalleAfiliadoManual ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_" + operacion;
	}
	
	@RequestMapping(value = { "/detalleAfiliado.do" }, method = RequestMethod.GET)
	public String verDetalleAfiliado(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			
			DetalleEntity detalle_afiliado= detalleService.findByIdDetalle(Long.parseLong(id));
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado);
			
			if(operacion.equals("afiliado")){
				CabeceraEntity cabecera= (CabeceraEntity)cabeceraService.findById(detalle_afiliado.getIdCabecera());
				request.getSession().setAttribute("cabecera_tef", cabecera);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en detalleAfiliadoManual ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_afiliado";
	}
	
	@RequestMapping(value = { "/detalleAfiliado.do" }, method = RequestMethod.POST)
	public String buscarAfiliado(@RequestParam(value = "rutAfiliado", required = true) String rutAfiliado, ModelMap model, HttpServletRequest request) {
		
		try {
			rutAfiliado= rutAfiliado.replaceAll("\\.", "");
			CabeceraEntity cabecera= (CabeceraEntity)request.getSession().getAttribute("cabecera_tef");
			HashMap<String, Long> params= new HashMap<String, Long>();
			params.put("idCabecera", cabecera.getIdCabecera());
			params.put("rutAfiliado", Long.parseLong(rutAfiliado.split("-")[0]));
			DetalleEntity detalle_afiliado= detalleService.findByRut(params);
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado);
			model.addAttribute("rutAfiliado", rutAfiliado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en detalleAfiliadoManual ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_afiliado";
	}
	
	@RequestMapping(value = { "/seguimientoAfiliado.do" }, method = RequestMethod.GET)
	public String seguimientoAfiliado(@RequestParam(value = "accion", required = false) String accion, ModelMap model, NominaManualVo form, HttpServletRequest request) {
		request.getSession().setAttribute("operacion", "afiliado");

		if(accion==null){
			request.getSession().setAttribute("estadoConsulta", null);
			request.getSession().setAttribute("rutConsulta", null);
			request.getSession().setAttribute("nombreConsulta", null);
		}
		
		if(request.getSession().getAttribute("params")==null){
			try {
				parametroService.setParams();
				request.getSession().setAttribute("params", "OK");
			} catch (Exception e) {
				return "redirect:/init.do";
			}
		}
		seguimientoAfiliado(model, form, request);
		return "seguimiento_afiliado";
	}
	
	@RequestMapping(value = { "/seguimientoAfiliado.do" }, method = RequestMethod.POST)
	public String seguimientoAfiliado(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		String operacion= (String)request.getSession().getAttribute("operacion");
		if(operacion==null){
			return "redirect:/init.do";
		}
		try {
			if(request.getSession().getAttribute("params")==null){
				try {
					parametroService.setParams();
					request.getSession().setAttribute("params", "OK");
				} catch (Exception e) {
					return "redirect:/init.do";
				}
			}
			
					
			//Estado Pago
			String estadoPagoConsulta=form.getEstado();
			if(estadoPagoConsulta==null){
				estadoPagoConsulta= (String)request.getSession().getAttribute("estadoPagoConsulta");
			}
			if(estadoPagoConsulta!=null){
				form.setEstado(estadoPagoConsulta);
			}
			List<EstadoEntity> listaEstados=parametroService.consultaEstadosPago();
			
			//RutAfiliado
			String rutConsulta=form.getRutAfiliado();
			if(rutConsulta==null){
				rutConsulta= (String)request.getSession().getAttribute("rutConsulta");
			}else if(rutConsulta.equals("")){
				rutConsulta= null;
			}
			if(rutConsulta!=null){
				rutConsulta= rutConsulta.replaceAll("\\.", "");
				form.setRutAfiliado(rutConsulta);
			}
			
			//NombreAfiliado
			String nombreConsulta=form.getNombreAfiliado();
			if(nombreConsulta==null){
				nombreConsulta= (String)request.getSession().getAttribute("nombreConsulta");
			}else if(nombreConsulta.equals("")){
				nombreConsulta= null;
			}
			if(nombreConsulta!=null){
				form.setNombreAfiliado(nombreConsulta);
			}
			boolean consulta_valida= false;
			HashMap<String, String> params= new HashMap<String, String>();
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			params.put("idUsuario", idUsuario);
			
			if(form.getEstado()==null && form.getRutAfiliado()==null && form.getNombreAfiliado()==null){
				form.setEstado(""+Estados.PAGO_PROCESO_PAGO);
				form.setRutAfiliado("");
				form.setNombreAfiliado("");

			}
			request.getSession().setAttribute("estadoPagoConsulta", form.getEstado());
			request.getSession().setAttribute("rutConsulta", form.getRutAfiliado());
			request.getSession().setAttribute("nombreConsulta", form.getNombreAfiliado());
			
			if(form.getEstado()!=null && !form.getEstado().equals("")){
				params.put("estadoPago", form.getEstado());
			}

			if(form.getRutAfiliado()!=null && !form.getRutAfiliado().equals("")){
				params.put("rutAfiliado", form.getRutAfiliado().split("-")[0]);
				consulta_valida=true;
			}
			if(form.getNombreAfiliado()!=null && !form.getNombreAfiliado().equals("")){
				params.put("nombreAfiliado", form.getNombreAfiliado().trim());
				consulta_valida=true;
			}
			
			model.addAttribute("estadosPago", listaEstados);
			
			
			//logger.info("Busncando registros detalle TEF");
			List<DetalleEntity> resultado=null;
			if(consulta_valida){
				resultado = detalleService.seguimientoAfiliado(params);
			}else{
				resultado = new ArrayList<DetalleEntity>();
			}
			request.getSession().setAttribute("nominasDetalle", resultado);
			request.getSession().setAttribute("cantidadRegistros", resultado.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}
		return "seguimiento_" + operacion;
	}
	
	@RequestMapping(value = { "/rendicionManual.do" }, method = RequestMethod.GET)
	public String enviar(ModelMap model, HttpServletRequest request) {

		try {

			String rut_usuario = "";

			Principal principal = request.getUserPrincipal();
			if (principal != null) {
				rut_usuario = principal.getName();

				logger.debug("rut: " + rut_usuario);
			} else {

				return "redirect:/logout.do";
			}

			model.addAttribute("usuario", rut_usuario);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "nominas_descargar";
	}

	@RequestMapping(value = { "/consultaCabe.do" }, method = RequestMethod.POST)
	public String getQueryHead(ModelMap model, @ModelAttribute NominaVo nomina, HttpServletRequest request) {

		List<EstadosVo> estadoscab = new ArrayList<EstadosVo>();

		try {

			EstadosVo es = new EstadosVo();
			es.setEstado("TODOS");
			estadoscab.add(es);

			EstadosVo es1 = new EstadosVo();
			es1.setEstado("PAGADO");
			estadoscab.add(es1);

			EstadosVo es2 = new EstadosVo();
			es2.setEstado("PENDIENTE");
			estadoscab.add(es2);

			if (nomina.getEstado().equals("TODOS")) {

				nomina.setEstado("");
			}

			List<CabeceraEntity> cabecera = cabeceraService.getNominasCabecera(nomina);

			model.addAttribute("nomina", nomina);
			model.addAttribute("estadoscab", estadoscab);

			model.addAttribute("nominasCabecera", cabecera);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "nominas";
	}

	@RequestMapping(value = { "/detalle.do" }, method = RequestMethod.GET)
	public String getDetalle(ModelMap model, HttpServletRequest request) {

		try {

			NominaVo nomina = new NominaVo();
			String numNomina= request.getParameter("numNomina");
			
			List<EstadosVo> estados = new ArrayList<EstadosVo>();

			EstadosVo es = new EstadosVo();
			es.setEstado("TODOS");
			estados.add(es);

			EstadosVo es1 = new EstadosVo();
			es1.setEstado("PAGADO");
			estados.add(es1);

			EstadosVo es2 = new EstadosVo();
			es2.setEstado("PENDIENTE DE COBRO");
			estados.add(es2);
			
			EstadosVo es4 = new EstadosVo();
			es4.setEstado("DEVUELTO AL TOMADOR");
			estados.add(es4);
			
			EstadosVo es5 = new EstadosVo();
			es5.setEstado("RECHAZADO");
			estados.add(es5);
			
			EstadosVo es3 = new EstadosVo();
			es3.setEstado("EN DIGITACIÓN");
			estados.add(es3);

			nomina.setRut("");
			nomina.setEstado("");
			nomina.setNomina(numNomina);

			model.addAttribute("nomina", nomina);
			model.addAttribute("estados", estados);

			List<DetalleEntity> detalle = detalleService.getNominasDetalle(nomina);


			model.addAttribute("detalleCabecera", detalle);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "nominas-detalle";
	}

	@RequestMapping(value = { "/service.do" }, method = RequestMethod.GET)
	public String getService(ModelMap model, HttpServletRequest request) {

		return "main";
	}

	@RequestMapping(value = { "/consulta.do" }, method = RequestMethod.POST)
	public String getNomina(ModelMap model, @ModelAttribute NominaVo nomina, HttpServletRequest request) {

		try {

			if (nomina.getEstado().equals("TODOS")) {

				nomina.setEstado("");
			}

			model.addAttribute("detalleCabecera", detalleService.getNominasDetalle(nomina));

			if (nomina.getEstado().isEmpty()) {

				nomina.setEstado("TODOS");
			}

			List<EstadosVo> estados = new ArrayList<EstadosVo>();

			EstadosVo es = new EstadosVo();
			es.setEstado("TODOS");
			estados.add(es);

			EstadosVo es1 = new EstadosVo();
			es1.setEstado("PAGADO");
			estados.add(es1);

			EstadosVo es2 = new EstadosVo();
			es2.setEstado("PENDIENTE DE COBRO");
			estados.add(es2);

			EstadosVo es3 = new EstadosVo();
			es3.setEstado("EN DIGITACIÓN");
			estados.add(es3);

			if (nomina.getEstado().equals("TODOS")) {

				nomina.setEstado("");
			}

			model.addAttribute("estados", estados);
			model.addAttribute("nomina", nomina);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "nominas-detalle";
	}

	@RequestMapping(value = "downloadPendiente.do", method = RequestMethod.GET)
public @ResponseBody void downloadNomina(HttpServletRequest request, HttpServletResponse response,
					@RequestParam(value = "id", required = true) long idCabecera) {

		try {
			ConvenioDao convenioDAO= new ConvenioDaoImpl();
			CabeceraEntity cabecera = cabeceraService.findById(idCabecera);
			String nombreNomina= cabecera.getNombreNomina();
			byte[] targetArray=null;
			if(cabecera != null && cabecera.getPlano().length>0) {
				InputStream inputStream = new ByteArrayInputStream(cabecera.getPlano());
				targetArray = IOUtils.toByteArray(inputStream);
			}
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=" + nombreNomina);

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(targetArray);
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error download con_errores", e);
		}
	}
	
	@RequestMapping(value = { "/logout.do" }, method = RequestMethod.GET)
	public String closeSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().setAttribute("convenios", null);
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=login.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	
	
	@RequestMapping(value = { "/cargarBeneficios.do" }, method = RequestMethod.GET)
	public String cargarBeneficios(ModelMap model, HttpServletRequest request) {

		String mensajeSalida = null;
		logger.debug("Iniciando proceso para carga beneficios");
		int totalCabecerasBCI=0;
		int totalCabecerasBES=0;
		
		//CARGANDO BENEFICIOS BCI
		try {
			List<ResumenCargaPagoBenefVo>  resgistrosBeneficios= beneficiosService.leerTablaBeneficios("BCI");
			if(resgistrosBeneficios.size()>0){
				totalCabecerasBCI= beneficiosService.cargarTablasTEF(resgistrosBeneficios, "BCI");
				logger.info("Total Cabeceras cargadas BCI: " + totalCabecerasBCI);
			}
				
		} catch (Exception e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Beneficios para BCI, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		//CARGANDO BENEFICIOS BES
		
		try {
			List<ResumenCargaPagoBenefVo>  resgistrosBeneficios= beneficiosService.leerTablaBeneficios("BES");
			if(resgistrosBeneficios.size()>0){
				totalCabecerasBES= beneficiosService.cargarTablasTEF(resgistrosBeneficios, "BES");
				logger.info("Total Cabeceras cargadas BES: " + totalCabecerasBES);
			}
				
		} catch (Exception e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Beneficios para BES, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		logger.info("Fin proceso automatizado beneficios, total nóminas generadas: " + (totalCabecerasBCI+totalCabecerasBES));
		return "seguimiento_envio";
	}
	@RequestMapping(value = { "/cargarBecas.do" }, method = RequestMethod.GET)
	public String cargarBecas(ModelMap model, HttpServletRequest request) {

		String mensajeSalida = null;
		logger.debug("Iniciando el proceso automatizado para carga Becas");
		int totalCabecerasBES=0;
		
		//CARGANDO BECAS BES
		
		try {
			List<ResumenCargaPagoBecasVo>  resgistrosBecas= becasService.leerTablaBecas("BES");
			if(resgistrosBecas.size()>0){
				becasService.cargarDatosmandato(resgistrosBecas);
				totalCabecerasBES= becasService.cargarTablasTEF(resgistrosBecas, "BES");
				logger.info("Total Cabeceras cargadas Becas BES: " + totalCabecerasBES);
			}else{
				logger.info("No hay registros para procesar");
			}
				
		} catch (Exception e) {
			mensajeSalida = e.getMessage();
			logger.error("Error en la carga de Becas para BES, mensaje= " + mensajeSalida);
			e.printStackTrace();
		}
		
		logger.info("Fin proceso manual becas, total nóminas generadas: " + (totalCabecerasBES));
		return "seguimiento_envio";
	}
}
