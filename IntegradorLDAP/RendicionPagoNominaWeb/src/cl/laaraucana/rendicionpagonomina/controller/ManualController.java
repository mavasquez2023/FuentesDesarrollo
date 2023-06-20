package cl.laaraucana.rendicionpagonomina.controller;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.exception.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.exception.MiException;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDao;
import cl.laaraucana.rendicionpagonomina.ibatis.dao.ConvenioDaoImpl;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.DetalleCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ProductoEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ResumenCargaPagoManualVo;
import cl.laaraucana.rendicionpagonomina.services.BancoService;
import cl.laaraucana.rendicionpagonomina.services.BeneficioService;
import cl.laaraucana.rendicionpagonomina.services.CabeceraManualService;
import cl.laaraucana.rendicionpagonomina.services.CabeceraService;
import cl.laaraucana.rendicionpagonomina.services.MailService;
import cl.laaraucana.rendicionpagonomina.services.ParametrosService;
import cl.laaraucana.rendicionpagonomina.services.DetalleManualService;
import cl.laaraucana.rendicionpagonomina.services.DetalleService;
import cl.laaraucana.rendicionpagonomina.services.MandatoAS400Service;
import cl.laaraucana.rendicionpagonomina.services.ProcesaManualService;
import cl.laaraucana.rendicionpagonomina.services.ProcesaRendicionBESService;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;
import cl.laaraucana.rendicionpagonomina.utils.DescripcionCodigo;
import cl.laaraucana.rendicionpagonomina.utils.Estados;
import cl.laaraucana.rendicionpagonomina.utils.Utils;
import cl.laaraucana.rendicionpagonomina.vo.CargaManualVO;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;
import cl.liv.export.comun.util.file.ManejoArchivos;



@Controller
public class ManualController {

	private static final Logger logger = Logger.getLogger(ManualController.class);
	
	@Autowired
	ParametrosService parametroService;
	
	@Autowired
	ProcesaManualService manualServices;
	
	@Autowired
	MandatoAS400Service mandatoService;
	
	@Autowired
	CabeceraManualService cabeceraManualService;
	
	@Autowired
	DetalleManualService detalleManualService;
	
	@Autowired
	private CabeceraService cabeceraService;
	
	@Autowired
	private DetalleService detalleService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	BancoService bancoService;
	
	private ConvenioDao convenioDAO= new ConvenioDaoImpl();
	
	@RequestMapping(value = { "/cargamanual.do" }, method = RequestMethod.GET)
	public String enviar(ModelMap model, HttpServletRequest request) {

		try {
			String rol= (String)request.getSession().getAttribute("rol");
			if(rol==null){
				return "redirect:/init.do";
			}
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			request.getSession().setAttribute("convenios", null);
			if(rol!=null && (rol.equals("cargaManual") || rol.equals("admin"))){
				List<ConvenioEntity> listaConvenios= parametroService.consultaConveniosActivosManual(idUsuario);

				request.getSession().setAttribute("convenios", listaConvenios);
			}
			request.getSession().setAttribute("sinmandato", null);
			request.getSession().setAttribute("resumen", null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "cargar_manuales";
	}

	@RequestMapping(value = { "/subir.do" }, method = RequestMethod.POST)
	public String subirCSV(@ModelAttribute CargaManualVO form, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String c1= request.getParameter("convenio");
			String c2= (String)request.getAttribute("convenio");
			String path = Configuraciones.getConfig("path.manual").trim();
			String convenio = form.getConvenio();
			String codigo_convenio=convenio.split("_")[0];
			String desc_convenio=convenio.split("_")[1].trim();
			
			String producto = form.getProducto();
			String codigo_producto=producto.split("_")[0];
			String desc_producto=producto.split("_")[1].trim();

			//Se genera resumen de la carga
			HashMap<String, String> params= new HashMap<String, String>();
			params.put("convenio", codigo_convenio);
			params.put("producto", codigo_producto);
			List<BeneficioEntity> beneficios= parametroService.consultaBeneficiosByParams(params);
			List<String> cod_beneficios= new ArrayList<String>();
			for (Iterator iterator = beneficios.iterator(); iterator.hasNext();) {
				BeneficioEntity beneficioEntity = (BeneficioEntity) iterator
						.next();
				cod_beneficios.add(beneficioEntity.getCodigoBeneficio());
			}
			
			String file_subido= form.getManual().getOriginalFilename();
			ResumenCargaPagoManualVo resumen= manualServices.cargaPagoManual(form.getManual(), cod_beneficios);
			
			if(resumen!=null && resumen.getListaNomina().size()>0){

				//Se busca los mandatos de cada afiliado
				int sinmandato=0;
				List<DetalleCargaPagoManualVo> AfiliadosSinMandato= new ArrayList<DetalleCargaPagoManualVo>();
				for (Iterator iterator = resumen.getListaNomina().iterator(); iterator.hasNext();) {
					DetalleCargaPagoManualVo pagoafiliado = (DetalleCargaPagoManualVo) iterator.next();
					List<MandatoAS400Vo> mandatos= mandatoService.consultaMandatos(pagoafiliado.getRutAfiliado());
					if(mandatos!=null && mandatos.size()>0){
						MandatoAS400Vo mandato= mandatos.get(0);
						pagoafiliado.setCodbanco(mandato.getCodbanco());
						pagoafiliado.setIdtipcta(mandato.getIdtipcta());
						pagoafiliado.setNumcuenta(mandato.getNumcuenta());
						pagoafiliado.setEmail(mandato.getEmail());
					}else{
						sinmandato++;
						AfiliadosSinMandato.add(pagoafiliado);
					}
				}
				resumen.setSinmandato(sinmandato);
				resumen.setConvenio(codigo_convenio);
				resumen.setProducto(codigo_producto);

				byte[] manual = form.getManual().getBytes();

				if (manual.length > 0) {
					File file = new File(path + desc_convenio);
					if (!file.exists()) {
						file.mkdir();
					}
					
					String nombre_archivo="Convenio_" + codigo_convenio + "_"  + codigo_producto + "_" + file_subido;
					logger.info("Guardando archivo: "  + nombre_archivo);
					Utils.descargar(file.getAbsolutePath().trim(),
							nombre_archivo,
							manual);
					resumen.setNombreArchivo(nombre_archivo);
				}
				if(AfiliadosSinMandato.size()>0){
					request.getSession().setAttribute("sinmandato", AfiliadosSinMandato);
				}
				
			}
			if(resumen.getListaErrores().size()>0){
				request.getSession().setAttribute("conerrores", resumen.getListaErrores());
			}
			request.getSession().setAttribute("resumen", resumen);
			request.getSession().setAttribute("archivo_manual", file_subido);
		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);
			model.addAttribute("mensaje", e.getMessage());
			return "error-process";
		}

		return "cargar_manuales";
	}
	
	@RequestMapping(value = "productos.do", method = RequestMethod.GET)
	public @ResponseBody List<ProductoEntity> productosxConvenio(
			@RequestParam(value = "convenio", required = true) String convenio) {

		List<ProductoEntity> productos = null;

		try {
			if(convenio!=null && !convenio.equals("")){
				convenio= convenio.split("_")[0];
				productos = parametroService.consultaProductosByConvenioCargaManual(Integer.parseInt(convenio));
			}else{
				logger.warn("Convenio no válido para obtener Productos, valor convenio: " + convenio);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error traer productos, mensaje: ", e);
		}
		return productos;
	}
	
	@RequestMapping(value = "sinmandato.do", method = RequestMethod.GET)
	public @ResponseBody void sinMandato(HttpServletRequest request, HttpServletResponse response) {

		
		try {
			List<DetalleCargaPagoManualVo> AfiliadosSinMandato= (List<DetalleCargaPagoManualVo>)request.getSession().getAttribute("sinmandato");
			StringBuffer sinmandato= new StringBuffer();
			for (Iterator iterator = AfiliadosSinMandato.iterator(); iterator
					.hasNext();) {
				DetalleCargaPagoManualVo archivoCargaPagoManualVo = (DetalleCargaPagoManualVo) iterator
						.next();
				sinmandato.append(archivoCargaPagoManualVo.toString());
				
			}
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=sin_mandato.csv");

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(sinmandato.toString().getBytes());
			ouputStream.flush();
			ouputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error download sin_mandato", e);
		}
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
	
	@RequestMapping(value = "confirmar.do", method = RequestMethod.POST)
	public String grabarNomina(Model model, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		
		try {
			ResumenCargaPagoManualVo resumen= (ResumenCargaPagoManualVo)request.getSession().getAttribute("resumen");
			CabeceraManualEntity cabecera= new CabeceraManualEntity();
			cabecera.setConvenio(resumen.getConvenio());
			cabecera.setEstado(Estados.NOMINA_PENDIENTE);
			cabecera.setFechaCarga(new Date());
			cabecera.setFechaPago(null);
			cabecera.setFechaCreacion(new Date());
			//cabecera.setMontoPendiente(resumen.getMontoPendiente());
			//La primera vez el monto pendiente es igual al monto de la nómina
			cabecera.setMontoPendiente(resumen.getMontoNomina());
			cabecera.setProducto(resumen.getProducto());
			cabecera.setTotalMonto(resumen.getMontoNomina());
			//La primera vez la cantidad de pendientse es igual al de la nómina
			//cabecera.setTotalPendientes(resumen.getSinmandato());
			cabecera.setTotalPendientes(resumen.getCantidadRegistros());
			cabecera.setTotalRegistros(resumen.getCantidadRegistros());
			cabecera.setUsuarioCreacion("USRTEF");
			cabecera= cabeceraManualService.save(cabecera);
			
			for(DetalleCargaPagoManualVo detallesPM : resumen.getListaNomina()){
				DetalleManualEntity detalle= new DetalleManualEntity();
				detalle.setDescripcionPago(detallesPM.getDescripcionPago());
				detalle.setDescripcionRechazo("");
				detalle.setDvAfiliado(detallesPM.getDvAfiliado());
				detalle.setEmail(detallesPM.getEmail());
				detalle.setEstado(Estados.PAGO_PENDIENTE_PROCESO);
				detalle.setFechaTransferencia(null);
				detalle.setFolioNomina(0);
				detalle.setFechaTransferencia(null);
				detalle.setIdCabecera(cabecera.getIdCabecera());
				detalle.setMontoPago(detallesPM.getMontoPago());
				detalle.setNombreAfiliado(detallesPM.getNombreAfiliado());
				//detalle.setBancoAfiliado(String.valueOf(detallesPM.getCodbanco()));
				detalle.setBancoAfiliado(null);
				//detalle.setNumeroCuenta(detallesPM.getNumcuenta());
				detalle.setNumeroCuenta(null);
				detalle.setRutAfiliado(detallesPM.getRutAfiliado());
				detalle.setTipoCuenta(detallesPM.getIdtipcta());
				detalle.setBeneficio(detallesPM.getCodBeneficio());
				detalle.setReferencia1(detallesPM.getReferencia1());
				detalle.setReferencia2(detallesPM.getReferencia2());
				detalleManualService.save(detalle);
			}
			model.addAttribute("cargado", "OK");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error cargar archivo cabecera y detalle manual", e);
			model.addAttribute("cargado", "NOK");
		}
		request.getSession().setAttribute("sinmandato", null);
		request.getSession().setAttribute("resumen", null);
		return "cargar_manuales";
	}
	
	@RequestMapping(value = { "/seguimientoManual.do" }, method = RequestMethod.GET)
	public String menu_seguimiento(@RequestParam(value = "accion", required = false) String accion, ModelMap model, NominaManualVo form, HttpServletRequest request) {
		if(accion==null){
			request.getSession().setAttribute("fechaConsulta", null);
			request.getSession().setAttribute("estadoConsulta", null);
			request.getSession().setAttribute("convenioConsulta", null);
			request.getSession().setAttribute("productoConsulta", null);
		}
		if(request.getSession().getAttribute("params")==null){
			try {
				parametroService.setParams();
				request.getSession().setAttribute("params", "OK");
			} catch (Exception e) {
				return "redirect:/init.do";
			}
		}
		seguimiento(model, form, request);
		return "seguimiento_manuales";
	}
	
	@RequestMapping(value = { "/seguimientoManual.do" }, method = RequestMethod.POST)
	public String seguimiento(ModelMap model, NominaManualVo form, HttpServletRequest request) {
		
		try {
			if(request.getSession().getAttribute("params")==null){
				try {
					parametroService.setParams();
					request.getSession().setAttribute("params", "OK");
				} catch (Exception e) {
					return "redirect:/init.do";
				}
			}
			String idUsuario= (String)request.getSession().getAttribute("usuario");
			form.setIdUsuario(idUsuario);
			
			List<ConvenioEntity> listaConvenios= parametroService.consultaConveniosActivosManual(idUsuario);
			request.getSession().setAttribute("convenios", listaConvenios);
			
			//Fecha Consulta
			String fechaConsulta=form.getFechaCarga();
			if(fechaConsulta==null){
				fechaConsulta= (String)request.getSession().getAttribute("fechaConsulta");
			}
			if(fechaConsulta!=null){
				form.setFechaCarga(fechaConsulta);
			}
			
			//Estado
			String estadoConsulta=form.getEstado();
			if(estadoConsulta==null){
				estadoConsulta= (String)request.getSession().getAttribute("estadoConsulta");
			}
			if(estadoConsulta!=null){
				form.setEstado(estadoConsulta);
			}
			
			//Convenio
			String convenioConsulta=form.getConvenio();
			if(convenioConsulta==null){
				convenioConsulta= (String)request.getSession().getAttribute("convenioConsulta");
			}
			if(convenioConsulta!=null){
				form.setConvenio(convenioConsulta);
			}
			
			//Producto
			String productoConsulta=form.getProducto();
			if(productoConsulta==null){
				productoConsulta= (String)request.getSession().getAttribute("productoConsulta");
			}
			if(productoConsulta!=null){
				form.setProducto(productoConsulta);
			}
			
			//dd-MM-yyyy
			String fechaHoy= Utils.dateToString(new Date());
			
			if(form.getFechaCarga()==null && form.getConvenio()==null && form.getProducto()==null && form.getEstado()==null){
				form.setFechaCarga(Utils.dateToString(new Date()));
				form.setConvenio("");
				form.setProducto("");
				form.setEstado("");
				model.addAttribute("fechaConsulta", fechaHoy);
			}else{
				request.getSession().setAttribute("fechaConsulta", form.getFechaCarga());
				request.getSession().setAttribute("estadoConsulta", form.getEstado());
				request.getSession().setAttribute("convenioConsulta", form.getConvenio());
				request.getSession().setAttribute("productoConsulta", form.getProducto());
			}
			String descr_conv= DescripcionCodigo.getConvenio(form.getConvenio());
			
			List<CabeceraManualEntity> cabeceras_manual= cabeceraManualService.findByParams(form);
			request.getSession().setAttribute("cabeceras_manual", cabeceras_manual);
			
			List<EstadoEntity> estados_activos= parametroService.consultaEstadosActivos();
			model.addAttribute("estados_activos", estados_activos);
			
			List<EstadoEntity> estados= parametroService.consultaEstadosNomina();
			request.getSession().setAttribute("estados", estados);
			/*
			request.getSession().setAttribute("sinmandato", null);
			request.getSession().setAttribute("resumen", null);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en seguimiento Pago ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "seguimiento_manuales";
	}
	
	@RequestMapping(value = { "/detalleManual.do" }, method = RequestMethod.GET)
	public String verDetalle(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		
		try {
			CabeceraManualEntity cabecera= (CabeceraManualEntity)cabeceraManualService.findById(Long.parseLong(id));
			request.getSession().setAttribute("cabecera", cabecera);
			List<DetalleManualEntity> detalles_manual= detalleManualService.findByIdCabecera(Integer.parseInt(id));
			request.getSession().setAttribute("detalles_manual", detalles_manual);
			
			List<EstadoEntity> estadospago= parametroService.consultaEstadosPago();
			request.getSession().setAttribute("estadospago", estadospago);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en inicio ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_manuales";
	}
	
	@RequestMapping(value = { "/detalleAfiliadoManual.do" }, method = RequestMethod.GET)
	public String verDetalleAfiliado(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		
		try {
			DetalleManualEntity detalle_afiliado_manual= detalleManualService.findByIdDetalle(Long.parseLong(id));
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado_manual);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en detalleAfiliadoManual ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "error-process";
		}

		return "detalle_afiliado_manuales";
	}
	
	@RequestMapping(value = { "/detalleAfiliadoManual.do" }, method = RequestMethod.POST)
	public String buscarAfiliado(@RequestParam(value = "rutAfiliado", required = true) String rutAfiliado, ModelMap model, HttpServletRequest request) {
		
		try {
			rutAfiliado= rutAfiliado.replaceAll("\\.", "");
			CabeceraManualEntity cabecera= (CabeceraManualEntity)request.getSession().getAttribute("cabecera");
			HashMap<String, Long> params= new HashMap<String, Long>();
			params.put("idCabecera", cabecera.getIdCabecera());
			params.put("rutAfiliado", Long.parseLong(rutAfiliado.split("-")[0]));
			DetalleManualEntity detalle_afiliado_manual= detalleManualService.findByRut(params);
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado_manual);
			model.addAttribute("rutAfiliado", rutAfiliado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en detalleAfiliadoManual ", e);

			model.addAttribute("mensaje", "A ocurrido un error, mensaje" + e.getMessage());

			return "error-process";
		}

		return "detalle_afiliado_manuales";
	}
	
	@RequestMapping(value = { "/anularPago.do" }, method = RequestMethod.GET)
	public String anularPago(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		
		try {
			DetalleManualEntity detalle_afiliado_manual= detalleManualService.findByIdDetalle(Long.parseLong(id));
			CabeceraManualEntity cabeceraManual= cabeceraManualService.findById(detalle_afiliado_manual.getIdCabecera());
			if(detalle_afiliado_manual.getEstado()==Estados.PAGO_PENDIENTE_PROCESO || detalle_afiliado_manual.getEstado()==Estados.PAGO_RECHAZADO){
				detalle_afiliado_manual.setEstado(Estados.PAGO_ANULADO);
				detalleManualService.saveOrupdate(detalle_afiliado_manual);
				//Se rebaja monto del total cabecera
				cabeceraManual.setTotalMonto(cabeceraManual.getTotalMonto()-detalle_afiliado_manual.getMontoPago());
				cabeceraManual.setTotalRegistros(cabeceraManual.getTotalRegistros()- 1);
				//Se rebaja monto de pendientes cabecera
				cabeceraManual.setMontoPendiente(cabeceraManual.getMontoPendiente()-detalle_afiliado_manual.getMontoPago());
				cabeceraManual.setTotalPendientes(cabeceraManual.getTotalPendientes()- 1);
				
				cabeceraManualService.update(cabeceraManual);
				request.getSession().setAttribute("cabecera", cabeceraManual);
				model.addAttribute("detalle_exito", "1");
			}
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado_manual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en anularPago ", e);

			model.addAttribute("mensaje", "A ocurrido un error.");

			return "detalle_afiliado_manuales";
		}

		return "detalle_afiliado_manuales";
	}
	
	@RequestMapping(value = { "/pendientePago.do" }, method = RequestMethod.GET)
	public String pendientePago(@RequestParam(value = "id", required = true) String id, ModelMap model, HttpServletRequest request) {
		
		try {
			DetalleManualEntity detalle_afiliado_manual= detalleManualService.findByIdDetalle(Long.parseLong(id));
			CabeceraManualEntity cabeceraManual= cabeceraManualService.findById(detalle_afiliado_manual.getIdCabecera());
			if(detalle_afiliado_manual.getEstado()==Estados.PAGO_ANULADO){
				detalle_afiliado_manual.setEstado(Estados.PAGO_PENDIENTE_PROCESO);
				detalleManualService.saveOrupdate(detalle_afiliado_manual);
				//Se rebaja monto del total cabecera
				cabeceraManual.setTotalMonto(cabeceraManual.getTotalMonto()+detalle_afiliado_manual.getMontoPago());
				cabeceraManual.setTotalRegistros(cabeceraManual.getTotalRegistros()+ 1);
				//Se rebaja monto de pendientes cabecera
				cabeceraManual.setMontoPendiente(cabeceraManual.getMontoPendiente()+detalle_afiliado_manual.getMontoPago());
				cabeceraManual.setTotalPendientes(cabeceraManual.getTotalPendientes()+ 1);
				
				cabeceraManualService.update(cabeceraManual);
				request.getSession().setAttribute("cabecera", cabeceraManual);
				model.addAttribute("detalle_exito", "1");
			}
			request.getSession().setAttribute("detalle_afiliado", detalle_afiliado_manual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error en pendientePago ", e);

			model.addAttribute("mensaje", "A ocurrido un error, mensaje: " + e.getMessage());

			return "detalle_afiliado_manuales";
		}

		return "detalle_afiliado_manuales";
	}
	
	@RequestMapping(value = { "/transferencia.do" }, method = RequestMethod.GET)
	public String transferecnaiManual(ModelMap model, HttpServletRequest request) {
		List<String> archivos= new ArrayList<String>();
		try {
			
			List<ArchivoManualVO> listaConveniosTransferencia= parametroService.consultaConveniosTransferencia();
			
			//Se obtiene mapa de los bancos para poder obtener la descripción
			Map<String, BancoEntity> mapBancos= bancoService.getMapBancos();
			
			for (Iterator iterator = listaConveniosTransferencia.iterator(); iterator
					.hasNext();) {
				ArchivoManualVO archivoManualVO = (ArchivoManualVO) iterator.next();
				
				long montototal=0;
				int cantidadtotal=0;
				
				//Se actualiza mandatos para detalleManual
				List<DetalleManualEntity> registrosxprod= detalleManualService.findByConvenio_Producto(archivoManualVO.getIdConvenio(), archivoManualVO.getIdProducto());
				if(registrosxprod!=null && registrosxprod.size()>0){
					List<DetalleManualEntity> detallesTEF= new ArrayList<DetalleManualEntity>();
					List<Long> listaCabecerasIncluida= new ArrayList<Long>();
					int maximoNomina=Integer.parseInt(Configuraciones.getConfig("cantidad.maxima.nomina.bci"));
					for (Iterator iterator2 = registrosxprod.iterator(); iterator2
							.hasNext();) {
						DetalleManualEntity detalleManualEntity = (DetalleManualEntity) iterator2
								.next();
						List<MandatoAS400Vo> mandatos= mandatoService.consultaMandatos(detalleManualEntity.getRutAfiliado());
						if(mandatos!=null && mandatos.size()>0){
							detalleManualEntity.setBancoAfiliado(String.valueOf(mandatos.get(0).getCodbanco()));
							detalleManualEntity.setTipoCuenta(mandatos.get(0).getIdtipcta());
							detalleManualEntity.setNumeroCuenta(mandatos.get(0).getNumcuenta());
							detalleManualEntity.setEmail(mandatos.get(0).getEmail());
							cantidadtotal++;
							montototal+=detalleManualEntity.getMontoPago();
							detallesTEF.add(detalleManualEntity);
							
							if(!listaCabecerasIncluida.contains(detalleManualEntity.getIdCabecera())){
								listaCabecerasIncluida.add(detalleManualEntity.getIdCabecera());
							}

						}else{
							detalleManualEntity.setBancoAfiliado(null);
							detalleManualEntity.setTipoCuenta(0);
							detalleManualEntity.setNumeroCuenta(null);
							detalleManualEntity.setEmail("");
							
						}
						detalleManualService.updateMandatoDetalle(detalleManualEntity);
						
						if(cantidadtotal==maximoNomina){
							break;
						}
					}
					String ruta=null;
					if(cantidadtotal>0){
						//Se genera nómina 
						ruta= manualServices.generaArchivoNomina(archivoManualVO);

						if(ruta!=null && !ruta.equals("")){
							
							File file = new File(ruta);
							byte[] fileInBytes = FileUtils.readFileToByteArray(file);
							
							//Se actualiza detalle a En Proceso
							detalleManualService.updatePendientes(archivoManualVO);

							//Se actualiza el monto pendiente en cada cabecera encontrada
							for (Iterator iterator2 = listaCabecerasIncluida.iterator(); iterator2
									.hasNext();) {
								Long idCabecera = (Long) iterator2.next();
								cabeceraManualService.updateMontoPendiente(idCabecera);
							}

							//Se genera cabecera y detalle NominasTef
							CabeceraEntity cabeceratef= new CabeceraEntity();
							cabeceratef.setCantidad(cantidadtotal);
							cabeceratef.setCantidadPagado(0);
							cabeceratef.setCantidadRechazado(0);
							cabeceratef.setCantidadDevuelto(0);
							cabeceratef.setCodigoNomina(0);
							cabeceratef.setCodigoRechazoEnvio(0);
							cabeceratef.setCodigoRechazoRendicion(0);
							cabeceratef.setConvenio(archivoManualVO.getIdConvenio());
							cabeceratef.setEstadoNomina(Estados.NOMINA_PENDIENTE);
							cabeceratef.setFechaEnvio(null);
							cabeceratef.setFechaCreacion(new Date());
							cabeceratef.setFechaRendicion(null);
							cabeceratef.setCodigoNomina(0);
							cabeceratef.setGlosaRechazoEnvio("");
							cabeceratef.setGlosaRechazoRendicion("");
							cabeceratef.setMonto(montototal);
							cabeceratef.setNombreNomina(archivoManualVO.getNombreArchivo());
							cabeceratef.setPendientes(0);
							cabeceratef.setPlano(fileInBytes);
							cabeceratef.setProducto(archivoManualVO.getIdProducto());
							cabeceratef.setTotalPagado(0);
							cabeceratef.setTotalRechazado(0);
							cabeceratef.setTotalDevuelto(0);
							cabeceratef.setCrc(null);

							//Se guarda cabecera TEF
							CabeceraEntity cabeceraTEF= cabeceraService.save(cabeceratef);
							
							//Se obtiene el convenio asociado para obtener banco convenio y ejecutivo 
							ConvenioEntity convenio = convenioDAO.getConvenio(Integer.parseInt(cabeceratef.getConvenio() ) );

							for (Iterator iterator2 = detallesTEF.iterator(); iterator2
									.hasNext();) {
								DetalleManualEntity detalleManualEntity2 = (DetalleManualEntity) iterator2
										.next();
								DetalleEntity detalleTEF= new DetalleEntity();
								detalleTEF.setIdCabecera(cabeceraTEF.getIdCabecera());
								detalleTEF.setCodigoBeneficio(detalleManualEntity2.getBeneficio());

								detalleTEF.setDescripcionEstadoPago("PENDIENTE_PROCESO");
								detalleTEF.setDescripcionPago(detalleManualEntity2.getDescripcionPago());
								detalleTEF.setRutAfiliado(detalleManualEntity2.getRutAfiliado());
								detalleTEF.setDvAfiliado(detalleManualEntity2.getDvAfiliado());
								detalleTEF.setNombres(detalleManualEntity2.getNombreAfiliado());
								detalleTEF.setCodigoBanco(detalleManualEntity2.getBancoAfiliado());
								String descripcionBanco="";
								if(convenio.getCodigoBanco().equals("BCI")){
									descripcionBanco= mapBancos.get(detalleManualEntity2.getBancoAfiliado()).getDescripcionBCI();
								}else if(convenio.getCodigoBanco().equals("BES")){
									descripcionBanco= mapBancos.get(detalleManualEntity2.getBancoAfiliado()).getDescripcionBES();
								}
								detalleTEF.setDescripcionBanco(descripcionBanco);
								detalleTEF.setNumerocuenta(Long.parseLong(detalleManualEntity2.getNumeroCuenta().trim()));
								detalleTEF.setTipoCuenta(detalleManualEntity2.getTipoCuenta());
								detalleTEF.setEmail(detalleManualEntity2.getEmail());
								detalleTEF.setCodigoFormaPago("");
								detalleTEF.setDescripcionFormaPago("");
								detalleTEF.setMonto((int)detalleManualEntity2.getMontoPago());
								detalleTEF.setEstadoPago(Estados.PAGO_PENDIENTE_PROCESO);
								detalleTEF.setDescripcionEstadoPago("");
								detalleTEF.setReferencia1(detalleManualEntity2.getReferencia1());
								detalleTEF.setReferencia2(String.valueOf(detalleManualEntity2.getIdDetalle()));
								detalleTEF.setFechaCambio(new Date());					
								detalleTEF.setIdReferencia(detalleManualEntity2.getIdDetalle());
								detalleService.save(detalleTEF);
							}

							//renombrando archivo
							String rutasalida=Configuraciones.getConfig("archivo.salida.ejecutivo");
							rutasalida= rutasalida.replaceAll("#banco#", archivoManualVO.getIdBanco());
							String pathSalida = Configuraciones.getConfig("archivo.salida.transferencia");
							pathSalida= pathSalida.replaceAll("#banco#", archivoManualVO.getIdBanco());
							String idcabtef= String.valueOf(((long)10000000000.0 + cabeceraTEF.getIdCabecera()));
							String prefijo= archivoManualVO.getNombreArchivo();
							if(prefijo.length()>6){
								prefijo= prefijo.substring(0, 6);
							}
							String nombreArchvio  = prefijo  + idcabtef.substring(1) + ".txt";

							ManejoArchivos.copyFileUsingChannel(new File(ruta), new File(pathSalida + nombreArchvio));
							logger.info("generación exitosa");
							rutasalida+= nombreArchvio;
							cabeceraTEF.setNombreNomina(nombreArchvio);
							cabeceraService.update(cabeceraTEF);
							archivos.add(rutasalida);
							
							//Se busca email ejecutivo para informar nómina subida
							if(convenio.getEmailEjecutivo() != null && convenio.getEmailEjecutivo().length()>5) {
								logger.debug("Enviando Mail a ejecutivo ["+convenio.getEmailEjecutivo()+"]");
								String body= Configuraciones.getConfig("envio.mail.body");
								body= body.replaceAll("#nombre_nomina#", nombreArchvio);
								body= body.replaceAll("#producto#", cabeceratef.getProducto());
								body= body.replaceAll("#monto#", String.valueOf(cabeceratef.getMonto()));
								body= body.replaceAll("#cantidad#", String.valueOf(cabeceratef.getCantidad()));
								String subject= Configuraciones.getConfig("envio.mail.subject");
								mailService.sendEmail(convenio.getEmailEjecutivo(), subject, body);
								
							}
						}else{
							logger.error("Error en generación Archivo");
							detalleManualService.rollbackTransferencia(archivoManualVO);
						}
					}
				}
			}
			
			model.addAttribute("files", archivos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("mensaje", "A ocurrido un error, mensaje: " + e.getMessage());
			return "error-process";
		}
		return "seguimiento_manuales";
	}
	
}
