package cl.laaraucana.licenciascompinemp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.laaraucana.licenciascompinemp.services.RegistroDocPendientesService;
import cl.laaraucana.licenciascompinemp.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompinemp.ibatis.dao.ConsultaLicenciaDAO;
import cl.laaraucana.licenciascompinemp.ibatis.vo.ViewLicenciasPendientes;
import cl.laaraucana.licenciascompinemp.entities.CorreoBalanceo;
import cl.laaraucana.licenciascompinemp.entities.SucLicenciasDP;
import cl.laaraucana.licenciascompinemp.entities.Comuna;
import cl.laaraucana.licenciascompinemp.entities.Region;
import cl.laaraucana.licenciascompinemp.entities.RegistroLicencias;
import cl.laaraucana.licenciascompinemp.entities.SucLicencias;
import cl.laaraucana.licenciascompinemp.services.ComunasService;
import cl.laaraucana.licenciascompinemp.services.MailService;
import cl.laaraucana.licenciascompinemp.services.RegionService;
import cl.laaraucana.licenciascompinemp.services.RegistroLicenciasService;
import cl.laaraucana.licenciascompinemp.services.SucursalesService;
import cl.laaraucana.licenciascompinemp.util.EmpresasLDAP;
import cl.laaraucana.licenciascompinemp.util.GeneratorXLS;
import cl.laaraucana.licenciascompinemp.util.Utils;
import cl.laaraucana.licenciascompinemp.vo.RegistroDocPendienteVo;
import cl.laaraucana.licenciascompinemp.vo.RegistroVo;
import cl.laaraucana.licenciascompinemp.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.licenciascompinemp.ws.ClientBPQueryStatus;
import cl.laaraucana.licenciascompinemp.ws.Configuraciones;

@Controller
public class InitController {

	private static final Logger logger = Logger.getLogger(InitController.class);

	@Autowired
	private RegionService regionService;

	@Autowired
	private ComunasService comunasService;

	@Autowired
	private SucursalesService sucService;
	
	@Autowired
	private RegistroLicenciasService licenciasService;
	
	@Autowired
	private RegistroLicenciasService regService;
	
	@Autowired
	private RegistroDocPendientesService regDPService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = { "/initemp.do" }, method = RequestMethod.GET)
	public String getEmpresas(ModelMap model, HttpServletRequest request) {
		Map<String, String> listamap= null;
		String rolUsuario="";
		try { 
		Principal principal = request.getUserPrincipal();
			if(principal!=null){
				String username= principal.getName();
				logger.info("Ingreso Ejecutivo " + username);
				listamap= EmpresasLDAP.getEmpresasLDAP(username);
				logger.info("Cantidad empresas autorizadas " + listamap.size());
				if(listamap.size()>0){
					rolUsuario="Encargado";
				}
				request.getSession().setAttribute("empresas", listamap);
				request.getSession().setAttribute("rutEncargado", username);
			}else{
				return "redirect";
			}
			request.getSession().removeAttribute("rut");	
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso0";
	}
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.POST )
	public String getInit(ModelMap model, HttpServletRequest request) {

		try {
			String rutempresa= request.getParameter("rutemp");
			
			if(rutempresa==null){
				return "index";
			}
				
			Map<String, String> listamap=(Map<String, String>)request.getSession().getAttribute("empresas");
			String razonSocial= listamap.get(rutempresa);
			logger.info("Empresa seleccionada" + rutempresa + " " + razonSocial);
			request.getSession().setAttribute("rutemp", rutempresa);
			request.getSession().setAttribute("razonSocial", razonSocial);
			request.getSession().removeAttribute("folio");
			request.getSession().removeAttribute("region");
			request.getSession().removeAttribute("comuna");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}
	
	@RequestMapping(value = { "/paso1.do" }, method = RequestMethod.GET)
	public String getInitGET(ModelMap model, HttpServletRequest request) {

		try {
			String rutEmpresa= (String)request.getSession().getAttribute("rutemp");
			if(rutEmpresa==null){
				return "index";
			}
			request.getSession().removeAttribute("folio");
			request.getSession().removeAttribute("region");
			request.getSession().removeAttribute("comuna");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "index-paso1";
	}
	
	@RequestMapping(value = { "paso2.do" }, method = RequestMethod.POST)
	public String getList(ModelMap model, @ModelAttribute RegistroLicencias form, HttpServletRequest request) {

		try {
			String rutEmpresa= (String)request.getSession().getAttribute("rutemp");
			if(rutEmpresa==null){
				return "index-paso0";
			}
			String rut= form.getRut().replaceAll("\\.", "").toUpperCase();
			String opcion= form.getOpcion();
			rutEmpresa= rutEmpresa.toUpperCase();
			ClientBPQueryStatus clienteBP= new ClientBPQueryStatus();
			logger.info(">>Validando si RUT " + rut +  " es afiliado y pertenece a la empresa");
			SalidainfoAfiliadoVO salida= clienteBP.obtenerEstadoAfiliacionCRM(rut, rutEmpresa);
			
			if(salida.getEstado()==-1){
				logger.info("RUT " + rut + " No activo");
				model.addAttribute("errorMsg", "rut_inactivo");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==0){
				logger.info("RUT " + rut + " No petenece a empresa " + rutEmpresa);
				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==-9){
				model.addAttribute("errorMsg", "servicio_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}
			logger.info("Nombre Afiliado: " + salida.getNombreCompleto());
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			request.getSession().setAttribute("rut", form.getRut().toUpperCase());
			//model.addAttribute("rut", form.getRut());
			//model.addAttribute("nombre", salida.getNombreCompleto());
			
			if(opcion.equals("IL")){
				String region="";
				String comuna="";
				region = (String) request.getSession().getAttribute("region");
				if (region == null) {
					region = "13";
				}
				comuna = (String) request.getSession().getAttribute("comuna");
				if (comuna == null) {
					comuna = "0";
				}

				List<Region> regiones = regionService.findAll();

				List<Comuna> comunas = comunasService.findByComunaReg(Integer.parseInt(region));

				model.addAttribute("regiones", regiones);
				model.addAttribute("comunas", comunas);
				request.getSession().setAttribute("region", region);
				request.getSession().setAttribute("comuna", comuna);
			}
			
			
			
			/**
			 * 
			 *  Requerimiento 8000005176 
			 *  @author J-Factory
			 *  @date  25-01-2021
			 *  
			 * */
			
			String rutFormateado = rut.replace(".", "").split("-")[0];
			//Consultando licencias pendientes afiliado
			
			//Buscando Sucursales Caja
			List<SucLicenciasDP> sucursales =sucService.getAllSucursales();
			request.getSession().setAttribute("sucursales", sucursales);
			request.getSession().setAttribute("sucursal", "000");
			
			ConsultaLicenciaDAO consultaLicenciaDAO = new ConsultaLicenciaDAO();
			logger.info("Consultando licencias pendientes del rut");
			
			ArrayList<ViewLicenciasPendientes> licenciasPendientesList = consultaLicenciaDAO.listaLicenciasPendientes(Integer.parseInt(rutFormateado));
			logger.info("Número de Licencias Pendientes del rut: " + licenciasPendientesList.size());
			
			request.getSession().setAttribute("licenciasPendientesList", licenciasPendientesList);
			
			
			if(opcion.equals("DP")){
				return "paso-paso2-DocP";
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "/volver_paso2.do" }, method = RequestMethod.POST)
	public String getPaso2(ModelMap model, @ModelAttribute RegistroLicencias form, HttpServletRequest request) {

		try {			
			String opcion= form.getOpcion();
			if(opcion.equals("DP")){
				return "paso-paso2-DocP";
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return "paso-paso2";
	}
	
	@RequestMapping(value = { "paso3DocP.do" }, method = RequestMethod.POST)
	public String getDataDP(ModelMap model, @ModelAttribute RegistroLicencias form, HttpServletRequest request) {

		try {

	
			String folio= request.getParameter("folio");
			if(folio==null || folio.equals("")){
				return "registro_error";
			}
			@SuppressWarnings("unchecked")
			ArrayList<ViewLicenciasPendientes> licenciasPendientesList =(ArrayList<ViewLicenciasPendientes>)request.getSession().getAttribute("licenciasPendientesList");
			for (Iterator iterator = licenciasPendientesList.iterator(); iterator
					.hasNext();) {
				ViewLicenciasPendientes viewLicenciaPendiente = (ViewLicenciasPendientes) iterator
						.next();
				if(viewLicenciaPendiente.getFolioLicencia().equals(folio)){
					request.getSession().setAttribute("licenciaPendiente", viewLicenciaPendiente);
					break;
				}
			}
			
			String rut= (String)request.getSession().getAttribute("rut");
			rut= rut.replace(".", "").replace("-", "");
			logger.info("Buscando si existe folio " + folio + " para rut " + rut);
			List<RegistroLicencias> registros= licenciasService.findByRut(rut);			

			String email="";
			String telefono="";
			request.getSession().removeAttribute("registro_save");
			if(registros!=null && registros.size()>0){
				logger.info("Licencia encontrada, se rescatan datos");
				//se guarda en sesion que existe registro para no volver a guardar si se sube documento pendiente
				request.getSession().setAttribute("registro_save", "1");
				RegistroLicencias registro_licencia= registros.get(0);
				request.getSession().setAttribute("registroLicencias", registro_licencia);
				email= registro_licencia.getEmail();
				telefono= registro_licencia.getTelefono();
			}else{
				List<RegistroDocPendientes> registrosPend= regDPService.findByRut(rut);
				if(registrosPend!=null && registrosPend.size()>0){
					request.getSession().setAttribute("registro_save", "1");
					email= registrosPend.get(0).getEmail();
					telefono= registrosPend.get(0).getTelefono();
				}
			}

			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("telefono", telefono);
			request.getSession().setAttribute("folio", folio);
			
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		return "paso-paso3-DocP";
	}
	
	@RequestMapping(value = "comunas.do", method = RequestMethod.GET)
	public @ResponseBody List<Comuna> comunasPrRegion(
			@RequestParam(value = "regionNombre", required = true) String region) {

		List<Comuna> comunas = new ArrayList<Comuna>();

		try {

			comunas = comunasService.findByComunaReg(Integer.parseInt(region));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error traer comunas", e);
		}
		return comunas;
	}

	@RequestMapping(value = { "/registrar.do" }, method = RequestMethod.POST)
	public String step3(@ModelAttribute RegistroVo form, Model model, HttpServletRequest request) {

		SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

		try {

			Comuna comuna = comunasService.findById(Long.parseLong(form.getComuna()));

			Region region = regionService.findById(Long.parseLong(form.getRegion()));
			
			logger.info("Buscando datos de oficina para comuna " +form.getComuna());
			List<SucLicencias> sucursal = sucService.findByComunaSuc(Integer.parseInt(form.getComuna()));

			if (sucursal.size() > 0) {
				logger.info("Oficina: " + sucursal.get(0).getSucursal() + ", correo encargado " + sucursal.get(0).getEmail());
				request.getSession().setAttribute("correo", sucursal.get(0).getEmail());
				request.getSession().setAttribute("sucursal", sucursal.get(0).getSucursal());

			} else {
				logger.warn("Oficina no encontrada");
				request.getSession().setAttribute("correo", "");
				request.getSession().setAttribute("sucursal", comuna.getDescripcion());
			}

			request.getSession().setAttribute("folio", form.getFolioLicencia());
			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			request.getSession().setAttribute("region", form.getRegion());
			request.getSession().setAttribute("comuna", form.getComuna());

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");

			RegistroLicencias reg = new RegistroLicencias();

			reg.setNombre(nombre);
			reg.setEmail(form.getEmail());
			reg.setFolioLicencia(form.getFolioLicencia());
			reg.setRut(rut.replace(".", "").replace("-", ""));
			reg.setComuna(comuna.getId());
			reg.setRegion(region.getId());
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setFechacre(new Date());
			reg.setTipoAfiliado("E");
			reg.setSucursal(sucursal.get(0).getSucursal());
			if(form.getFolioLicencia().indexOf("-")==-1){
				//Manual
				reg.setTipoLicencia("01");
			}else{
				//Full
				reg.setTipoLicencia("03");
			}
			reg.setEstado("0");
			request.getSession().setAttribute("registroLicencias", reg);

		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso3 ", e);

			return "registro_error";

		}

		return "paso-paso3";
	}
	
	@RequestMapping(value = { "/registrarDP.do" }, method = RequestMethod.POST)
	public String step3DP(@ModelAttribute RegistroDocPendienteVo form, Model model, HttpServletRequest request) {
		SimpleDateFormat sdh = new SimpleDateFormat("yyyyMMdd");
		//SimpleDateFormat sdh = new SimpleDateFormat("HH:mm:ss");

		try {
			String folio= (String)request.getSession().getAttribute("folio");
			if(folio==null || folio.equals("")){
				return "index-paso1";
			}

			request.getSession().setAttribute("folio", folio);
			request.getSession().setAttribute("telefono", form.getTelefono());
			request.getSession().setAttribute("email", form.getEmail());
			String sucursal= form.getSucursal();
			request.getSession().setAttribute("sucursal", sucursal);
			
			//request.getSession().setAttribute("region", form.getRegion());
			//request.getSession().setAttribute("comuna", form.getComuna());

			String rut = (String) request.getSession().getAttribute("rut");
			String nombre = (String) request.getSession().getAttribute("nombre");

			/**
			 * Almacenar en disco los documentos pendientes.
			 */

			//Se obtiene adjunto
			String path = Configuraciones.getConfig("conpin.carpeta.dp");
			String pathCorreo = Configuraciones.getConfig("correo.carpeta.dp");

			byte[] documento = null;
			String fechaFormateada = sdh.format(new Date());

			//logger.info("Ruta a guardar archivos: " + path + "\\" + sucursal.trim());
			
			String rutint=rut.replaceAll("\\.", "").split("-")[0];
			logger.info("Ruta a guardar archivos: " + path + "\\" + fechaFormateada  + "\\" + rutint + "_" + folio);

			//File file = new File(path + "\\" + sucursal.trim());
			File file = new File(path + "\\" + fechaFormateada);

			if (!file.exists()) {
				file.mkdir();
			}

			//File file2 = new File(path + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_" + folio);
			File file2 = new File(path + "\\" + fechaFormateada + "\\" + rutint + "_" + folio);

			if (!file2.exists()) {
				file2.mkdir();
			}

			//pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_"
			//		+ folio + "\\";

			String nombre_archivo="";
			documento = form.getDocumento().getBytes();

			if (documento.length > 0) {

				logger.info("Nombre Archivo original" + form.getDocumento().getOriginalFilename());

				/* Se mantiene el nombre del archivo */
				nombre_archivo= rutint + "_"
						+ folio + "_" + form.getDocumento().getOriginalFilename();

				logger.info("Nombre Archivo a guardar: " + nombre_archivo);
				logger.info("Descargando PDF");

				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + nombre_archivo, documento);

			}


			/**
			 * Registro en nueva bitácora de ingreso de documentos pendientes
			 * 
			 */

			//RegistroLicencias reg = null;
			String rutafi= "";
			String telefono="";
			String email="";

			//CorreoBalanceo correoeje= regDPService.getCorreoEjecutivo();
			String correoeje="";
			SucLicenciasDP suc= sucService.getSucursalByCodigo(sucursal);
			if(suc!=null){
				correoeje= suc.getEmailSucursal();
			}

			long id=0;
			//	if(request.getSession().getAttribute("registro_save")==null){
			String esMaternal="N";
			ViewLicenciasPendientes licenciaPend= (ViewLicenciasPendientes)request.getSession().getAttribute("licenciaPendiente");
			if(licenciaPend.getTipoLicencia().equalsIgnoreCase("maternal")){
				esMaternal="S";
			}
			RegistroDocPendientes reg = new RegistroDocPendientes();

			reg.setRut(rut.replace(".", "").replace("-", ""));
			reg.setNombre(nombre);
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setEmail(form.getEmail());
			reg.setFolioLicencia(folio);
			reg.setFolioInterno(licenciaPend.getFolioInterno());				
			reg.setFechacre(new Date());
			//reg.setHoracre(sdh.format(new Date()));
			reg.setTipoAfiliado("E");
			reg.setEsMaternal(esMaternal);

			String rutEncargado= (String)request.getSession().getAttribute("rutEncargado");
			String rutEmpresa= (String)request.getSession().getAttribute("rutemp");
			reg.setRutEncargadoEmpresa(rutEncargado); //Setear solo en app Empresa
			reg.setRutEmpresaSeleccionada(rutEmpresa); //Setear solo en app Empresa
			reg.setNombreArchivoEnviado(nombre_archivo); //Se deja registro de archivo enviado a usuario.
			reg.setEmailEjecutivo(correoeje); //Se requiere lógica de negocio que permita la obtención de correo de encargado de sucursal (incluir balanceo de carga)
			reg.setTipoLicencia(licenciaPend.getTipoLicCRM());
			reg.setEstado("0");
			
			//reg.setSucursal(sucursal.trim());
			//reg.setSucursal("");

			/*******************************************************/

			logger.info("Guardando en bitácora, folio: " + folio);
			regDPService.save(reg);

			telefono= reg.getTelefono();
			email= reg.getEmail();
			id=reg.getId();
			rutafi=reg.getRut();
			request.getSession().setAttribute("registroLicencias", reg);

			//	}
			
			/*else{
				Object registroLM= request.getSession().getAttribute("registroLicencias");

				if(registroLM instanceof RegistroLicencias){
					RegistroLicencias reg=(RegistroLicencias)registroLM;
					telefono= reg.getTelefono();
					email= reg.getEmail();
					id=reg.getId();
					rutafi=reg.getRut();
					request.getSession().setAttribute("registroLicencias", reg);

				}else if(registroLM instanceof  ViewRegistroLicencias){
					ViewRegistroLicencias reg=(ViewRegistroLicencias)registroLM;
					telefono= reg.getTelefono();
					email= reg.getEmail();
					id=reg.getId();
					rutafi=reg.getRut();
					request.getSession().setAttribute("registroLicencias", reg);

				}

			}*/

			request.getSession().setAttribute("registro_save", "1");
			request.setAttribute("mensaje", "1");
			
			//Se envía mail a Encargado Empresa
			logger.info("Enviando correo a cliente por DP, mail: " + email);
			mailService.sendEmail(email, "Ingreso documentación pendiente licencia: " + rutafi
					+ "-" + folio, Utils.bodyEbcargadoEmpresa());
			
			//se envía mail a Ejecutivo
			//pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rutafi + "_" + folio + "\\";
			pathCorreo =  pathCorreo + "\\" + fechaFormateada + "\\" + rutint + "_" + folio + "\\";
			
			if(correoeje!=null && !correoeje.equals("")){
				logger.info("Enviando mail a ejecutivo, mail: " + correoeje);
				mailService.sendEmail(correoeje, "Notificación documentación pendiente: " + rutafi
						+ "-" + folio,
						Utils.getbodyEjecDocP(folio, rut, pathCorreo, String.valueOf(id), telefono, email, nombre_archivo));
			}
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso3 ", e);

			return "registro_error";

		}

		return "paso-paso3-DocP";
	}
	
	@RequestMapping(value = { "/exit.do" }, method = RequestMethod.GET )
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			logger.info("Cerrando sesión");
			request.getSession().setAttribute("telefono", null);
			request.getSession().setAttribute("email", null);
			request.getSession().removeAttribute("folio");
			request.getSession().removeAttribute("registroLicencias");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	
	/**
	 * Método para la creación y posterior descarga de licencias de la empresa
	 * en formato .csv
	 * 
	 * @param request
	 * @param response
	 */
	
	@RequestMapping(value = { "/descargarArchivo.do" }, method = RequestMethod.POST)
	public void descargarArchivoCSV(HttpServletRequest request, HttpServletResponse response){
		
		try
		{  	
			String rutempresa= request.getParameter("rutemp");
			
			if(rutempresa!=null){
				ConsultaLicenciaDAO consultaLicenciaDAO = new ConsultaLicenciaDAO();
				logger.info("Consultando licencias pendientes de rutEmpresa: " + rutempresa);
				
				int rutemp= Integer.parseInt(rutempresa.split("-")[0]);
				ArrayList<ViewLicenciasPendientes> licenciasPendientesList = consultaLicenciaDAO.listaLicenciasPendientesEmpresa(rutemp);
				logger.info("Número de Licencias Pendientes de rutEmpresa: " + licenciasPendientesList.size());
				
				String filename= "Licencias_Pendientes_" + rutempresa + ".csv";
				//Generando la salida
				logger.info("Nombre archivo:" + filename);
				
				//Generando la salida
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/text");
				response.setHeader("Content-Disposition", "inline; filename=" + filename);
				ServletOutputStream out = response.getOutputStream();
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);

				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"rut", "dvAfi", "folioLicencia", "fechaInicio", "fechaTermino", "diasLicencia", "tipoLicencia", "observacion"};
				String[] titulos={"RUT", "DV", "Nro Licencia", "Inicio", "Fin", "Dias", "Tipo Licencia", "Observacion"};

				xls.generarCSVfromCollection(licenciasPendientesList, columnas, titulos);
				logger.info("Archivo ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();
			}
			
			
			
	

		//}
	}catch(Exception e){
		logger.error("Error Ejecutivo ", e);

	}
	}

}
