package cl.laaraucana.licenciascompin.controller;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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

import cl.araucana.core.util.Rut;
import cl.laaraucana.licenciascompin.entities.Comuna;
import cl.laaraucana.licenciascompin.entities.CorreoBalanceo;
import cl.laaraucana.licenciascompin.entities.Region;
import cl.laaraucana.licenciascompin.entities.RegistroDocPendientes;
import cl.laaraucana.licenciascompin.entities.RegistroLicencias;
import cl.laaraucana.licenciascompin.entities.SucLicencias;
import cl.laaraucana.licenciascompin.entities.SucLicenciasDP;
import cl.laaraucana.licenciascompin.ibatis.dao.ConsultaLicenciaDAO;
import cl.laaraucana.licenciascompin.ibatis.dao.ConsultaMandatoDAO;
import cl.laaraucana.licenciascompin.ibatis.vo.ViewLicenciasPendientes;
import cl.laaraucana.licenciascompin.services.ComunasService;
import cl.laaraucana.licenciascompin.services.MailService;
import cl.laaraucana.licenciascompin.services.RegionService;
import cl.laaraucana.licenciascompin.services.RegistroDocPendientesService;
import cl.laaraucana.licenciascompin.services.RegistroLicenciasService;
import cl.laaraucana.licenciascompin.services.SucursalesService;
import cl.laaraucana.licenciascompin.util.Utils;
import cl.laaraucana.licenciascompin.vo.RegistroDocPendienteVo;
import cl.laaraucana.licenciascompin.vo.RegistroVo;
import cl.laaraucana.licenciascompin.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.licenciascompin.ws.ClienteInfoAfiliado;
import cl.laaraucana.licenciascompin.ws.Configuraciones;
import cl.laaraucana.licenciascompin.ws.ConstantesRespuestasWS;

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
	
	@RequestMapping(value = { "/init.do" }, method = RequestMethod.GET)
	public String getInit(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		try {
			String rut="";
			Principal principal = request.getUserPrincipal();
			if(principal!=null){
				rut= principal.getName();
			}
			logger.info(">>Validando si RUT " + rut + " es afiliado" );
			ClienteInfoAfiliado client = new ClienteInfoAfiliado();
			SalidainfoAfiliadoVO salida = (SalidainfoAfiliadoVO) client.getDataAfiliado(rut);
			
			if (salida.isDeudordirecto() && salida.getNombreCompleto()==null) {
				logger.info("RUT " + rut + " no es afiliado");
				closeSesion(request, response);

				return null;
			}
			
			Rut rut_format= new Rut(rut.split("-")[0]);
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			request.getSession().setAttribute("rut", rut_format.toString());
			
			ConsultaMandatoDAO mandatoDao= new ConsultaMandatoDAO();
			logger.info("Consultando si RUT posee mandato vigente");
			boolean ismandato= mandatoDao.existMandato(rut_format.getNumber());
			logger.info("Mandato= " + ismandato);
			request.getSession().setAttribute("mandato", ismandato);
			
						
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

			String opcion= form.getOpcion();
			if(opcion.equals("DP")){
				/**
				 * 
				 *  Requerimiento 8000005176 
				 *  @author J-Factory
				 *  @date  25-01-2021
				 *  
				 * */
				logger.info("Obteniendo rut de la session de usuario");
				String rut= (String)request.getSession().getAttribute("rut");
				
				if(rut == null || rut.equals("")){
					
					logger.error("No esta seteado en sesión el rut del usuario");
					return "registro_error";
				}
				
				String rutFormateado = rut.replace(".", "").split("-")[0];
				
				//Buscando Sucursales Caja
				List<SucLicenciasDP> sucursales =sucService.getAllSucursales();
				request.getSession().setAttribute("sucursales", sucursales);
				request.getSession().setAttribute("sucursal", "000");
				
				ConsultaLicenciaDAO consultaLicenciaDAO = new ConsultaLicenciaDAO();
				logger.info("Consultando licencias pendientes del rut: " + rutFormateado);
				
				ArrayList<ViewLicenciasPendientes> licenciasPendientesList = consultaLicenciaDAO.listaLicenciasPendientes(Integer.parseInt(rutFormateado));
				if(licenciasPendientesList == null){
					
					logger.info("Afiliado sin licencias pendientes");
				}else{
				
					logger.info("Número de Licencias Pendientes del rut: " + licenciasPendientesList.size());
				}			
				
				request.getSession().setAttribute("licenciasPendientesList", licenciasPendientesList);
				
				return "paso-paso2-DocP";
			}else{		
				String region = (String) request.getSession().getAttribute("region");
				if (region == null) {
					region = "13";
				}
				String comuna = (String) request.getSession().getAttribute("comuna");
				if (comuna == null) {
					comuna = "0";
				}

				request.getSession().setAttribute("region", region);
				request.getSession().setAttribute("comuna", comuna);

				List<Region> regiones = regionService.findAll();

				List<Comuna> comunas = comunasService.findByComunaReg(Integer.parseInt(region));

				model.addAttribute("regiones", regiones);
				model.addAttribute("comunas", comunas);
				return "paso-paso2";
			}
			
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error paso2 ", e);

			return "registro_error";

		}

		
	}
	
	@RequestMapping(value = { "/mandato.do" }, method = RequestMethod.GET)
	public String invokeMandato(ModelMap model, @ModelAttribute RegistroLicencias form, HttpServletRequest request) {

		try {			
			String rut= (String)request.getSession().getAttribute("rut");
			request.setAttribute("rutEncode", rut.replaceAll("\\.", ""));
			request.setAttribute("urlRetorno",  Configuraciones.getConfig("url.retorno.licencia"));
			logger.info("RUT " + rut + " invocando Mandato");	
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error invoke Mandato ", e);

			return "registro_error";
		}

		return "main_mandato";
	}
	
	@RequestMapping(value = { "/volver_paso2.do" }, method = RequestMethod.POST)
	public String getPaso2(ModelMap model, @ModelAttribute RegistroLicencias form, HttpServletRequest request) {

		try {			
			String opcion= form.getOpcion();

			logger.info("Obteniendo rut de la session de usuario");
			String rut= (String)request.getSession().getAttribute("rut");
			
			if(rut == null || rut.equals("")){
				
				logger.error("No esta seteado en sesión el rut del usuario");
				return "registro_error";
			}
			
			String rutFormateado = rut.replace(".", "").split("-")[0];
			
			ConsultaLicenciaDAO consultaLicenciaDAO = new ConsultaLicenciaDAO();
			logger.info("Consultando licencias pendientes del rut: " + rutFormateado);
			
			ArrayList<ViewLicenciasPendientes> licenciasPendientesList = consultaLicenciaDAO.listaLicenciasPendientes(Integer.parseInt(rutFormateado));
			if(licenciasPendientesList == null){
				
				logger.info("Afiliado sin licencias pendientes");
			}else{
			
				logger.info("Número de Licencias Pendientes del rut: " + licenciasPendientesList.size());
			}			
			
			request.getSession().setAttribute("licenciasPendientesList", licenciasPendientesList);
			
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

			
			String folio = request.getParameter("folio");
			
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
			
			String email="";
			String telefono="";
			logger.info("DP, buscando datos personales de licencia en SQLServer RUT: " + rut + ", folio:" + folio);
			List<RegistroLicencias> registros= licenciasService.findByRut(rut);
			
			request.getSession().removeAttribute("registro_save");
			if(registros!=null && registros.size()>0){
				
				logger.info("Licencia encontrada, se recupera datos");
				
				//se guarda en sesion que existe registro para no volver a guardar si se sube documento pendiente
				request.getSession().setAttribute("registro_save", "1");
				RegistroLicencias registro_licencia= registros.get(0);
				request.getSession().setAttribute("registroLicencias", registro_licencia);
				//region= String.valueOf(registro_licencia.getRegion());
				//comuna= String.valueOf(registro_licencia.getComuna());
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
			String nomsucursal="";
			if (sucursal.size() > 0) {
				logger.info("Oficina: " + sucursal.get(0).getSucursal() + ", correo encargado " + sucursal.get(0).getEmail());
				request.getSession().setAttribute("correo", sucursal.get(0).getEmail());
				nomsucursal=sucursal.get(0).getSucursal();
				request.getSession().setAttribute("sucursal", nomsucursal);

			} else {
				logger.warn("Oficina no encontrada");
				request.getSession().setAttribute("correo", "");
				nomsucursal= comuna.getDescripcion();
				request.getSession().setAttribute("sucursal", nomsucursal);
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
			reg.setSucursal(nomsucursal);
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setFechacre(new Date());
			reg.setTipoAfiliado("A");
			if(form.getFolioLicencia().indexOf("-")==-1){
				//Manual
				reg.setTipoLicencia("01");
			}else{
				//Full
				reg.setTipoLicencia("03");
			}
			reg.setEstado("0");
			//reg.setSucursal(sucursal.get(0).getSucursal());
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

			reg.setRut(rut.replaceAll("\\.", "").replace("-", ""));
			reg.setNombre(nombre);
			reg.setTelefono(form.getTelefono().replace("+56", ""));
			reg.setEmail(form.getEmail());
			reg.setFolioLicencia(folio);
			reg.setFolioInterno(licenciaPend.getFolioInterno());				
			reg.setFechacre(new Date());
			//reg.setHoracre(sdh.format(new Date()));
			reg.setTipoAfiliado("A");
			reg.setEsMaternal(esMaternal);

			reg.setRutEncargadoEmpresa(""); //Setear solo en app Empresa
			reg.setRutEmpresa(licenciaPend.getRutemp()+"-" + licenciaPend.getRutempdv()); //Setear solo en app Empresa
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

			//}
			/*else{
				Object registroLM= request.getSession().getAttribute("registroLicencias");

				if(registroLM instanceof  RegistroDocPendientes){
					RegistroDocPendientes reg=(RegistroDocPendientes)registroLM;
					telefono= reg.getTelefono();
					email= reg.getEmail();
					id=reg.getId();
					rutafi=reg.getRut();
					request.getSession().setAttribute("registroLicencias", reg);
					
				}
				
			}*/
			
			request.getSession().setAttribute("registro_save", "1");
			request.setAttribute("mensaje", "1");
			
			
			//Envío de correo electrónico a afiliado
			 
			logger.info("Enviando correo a cliente por DP, mail: " + email);
			
			mailService.sendEmailClieDocP(email, "Ingreso documentación pendiente licencia: " + rutafi
					+ "-" + folio, Utils.bodyClient(), rutafi, folio);
	
			
			
			/**
			 * Envío de correo electrónico a Ejecutivo
			 * (Se requiere logica de negocio para la obtencion de correo electrónico de ejecutivo)
			 * (Se debe configurar en WAS la salida de los correos electronicos desde la app
			 */

			//pathCorreo =  pathCorreo + "\\" + sucursal.trim() + "\\" + rutafi + "_" + folio + "\\";
			pathCorreo =  pathCorreo + "\\" + fechaFormateada + "\\" + rutint + "_" + folio + "\\";
			
			if(correoeje!=null && !correoeje.equals("")){
				
				logger.info("Enviando mail a ejecutivo, mail: " + correoeje);
				
				mailService.sendEmailEjec(correoeje, "Notificación documentación pendiente: " + rutafi
						+ "-" + folio,
						Utils.getbodyEjecDocP(folio, rut, pathCorreo, String.valueOf(id), telefono, email, nombre_archivo));
			}
			
			/************************************************************************************************************************/
			
			
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
			request.getSession().removeAttribute("folio");
			request.getSession().removeAttribute("registroLicencias");
			
			/*request.getSession().removeAttribute("rut");
			request.getSession().removeAttribute("nombre");
			request.getSession().removeAttribute("telefono");
			request.getSession().removeAttribute("email");
			*/
			logger.info("Cerrando sesión");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=salir.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}
	
	@RequestMapping(value = { "/logout.do" }, method = RequestMethod.GET )
	public String closeSesion(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getSession().removeAttribute("folio");
			request.getSession().removeAttribute("registroLicencias");
			
			/*request.getSession().removeAttribute("rut");
			request.getSession().removeAttribute("nombre");
			request.getSession().removeAttribute("telefono");
			request.getSession().removeAttribute("email");
			*/
			logger.info("Cerrando sesión para no afiliado");
			request.getSession().invalidate();
			response.sendRedirect("ibm_security_logout?logoutExitPage=logout.jsp");
		} catch (Exception e) {
			// TODO: handle exception

			logger.error("Error Ejecutivo ", e);

			return "registro_error";
		}

		return null;
	}

}
