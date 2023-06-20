package cl.laaraucana.EnvioASFAMEmpresa.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.EnvioASFAMEmpresa.model.FicherosVo;
import cl.laaraucana.EnvioASFAMEmpresa.model.RegistroFormularioVo;
import cl.laaraucana.EnvioASFAMEmpresa.services.MailService;
import cl.laaraucana.EnvioASFAMEmpresa.services.RegistroService;
import cl.laaraucana.EnvioASFAMEmpresa.services.SucursalService;
import cl.laaraucana.EnvioASFAMEmpresa.util.Configuraciones;
import cl.laaraucana.EnvioASFAMEmpresa.util.Utils;
import cl.laaraucana.EnvioASFAMEmpresa.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.EnvioASFAMEmpresa.ws.ClientBPQueryStatus;
import cl.laaraucana.EnvioASFAMEmpresa.entities.RegistroEntiti;
import cl.laaraucana.EnvioASFAMEmpresa.entities.SucAsfam;
import cl.laaraucana.EnvioASFAMEmpresa.entities.Sucursales;

@Controller
public class ArchivosController {

	private static final Logger logger = Logger.getLogger(ArchivosController.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private RegistroService regService;

	@Autowired
	private SucursalService sucService;

	@RequestMapping(value = { "/paso3.do" }, method = RequestMethod.POST)
	public String step3(Model model, @ModelAttribute FicherosVo form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			List<String> files= new ArrayList<String>();
			String path = Configuraciones.getConfig("asfam.carpeta");

			String pathCorreo = Configuraciones.getConfig("correo.carpeta");

			byte[] solicitud = null;

			String suc = (String) request.getSession().getAttribute("sucursal");
			String rut = (String) request.getSession().getAttribute("rut");

			Sucursales sucursalVO= sucService.findByIdSucursal(suc);
			String sucursal = sucursalVO.getCodigo() + "-" +  sucursalVO.getDescripcion();
			request.getSession().setAttribute("sucursal_descripcion", sucursal);
			
			File file = new File(path + "\\" + sucursal.trim());

			if (!file.exists()) {

				file.mkdir();
			}

			File file2 = new File(path + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", ""));

			if (!file2.exists()) {

				file2.mkdir();
			}

			pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "\\";
			logger.info("Guardando documentos en carpeta: " + pathCorreo);
			
			solicitud = form.getSolicitud().getBytes();

			if (solicitud.length > 0) {
				String nombreArchivo= rut.replace(".", "").replace("-", "")
						+ "-Formulario." + Utils.getExtencion(form.getSolicitud().getOriginalFilename());
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + nombreArchivo, solicitud);
				files.add(nombreArchivo);
			}
			request.getSession().setAttribute("listaarchivos", files);
		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

		return "paso-paso4";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/paso4.do" }, method = RequestMethod.POST)
	public String step4(Model model, @ModelAttribute FicherosVo form, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String path = Configuraciones.getConfig("asfam.carpeta");

			String pathCorreo = Configuraciones.getConfig("correo.carpeta");

			byte[] certificado = null;

			String suc = (String) request.getSession().getAttribute("sucursal");
			String rut = (String) request.getSession().getAttribute("rut");

			Sucursales sucursalVO= sucService.findByIdSucursal(suc);
			String sucursal = sucursalVO.getCodigo() + "-" +  sucursalVO.getDescripcion();
			request.getSession().setAttribute("sucursal_descripcion", sucursal);
			
			File file = new File(path + "\\" + sucursal.trim());

			if (!file.exists()) {

				file.mkdir();
			}

			File file2 = new File(path + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", ""));

			if (!file2.exists()) {

				file2.mkdir();
			}

			pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "\\";
			logger.info("Guardando documentos en carpeta: " + pathCorreo);
			
			certificado = form.getCertificado().getBytes();

			List<String> archivos = new ArrayList<String>();
			List<String> listaArchivos =(List<String>) request.getSession().getAttribute("listaarchivos");
			if(listaArchivos==null && request.getSession().getAttribute("pendiente")!=null){
				listaArchivos=new ArrayList<String>();
			}
			
			if ((List<String>) request.getSession().getAttribute("archivos") != null) {

				archivos = (List<String>) request.getSession().getAttribute("archivos");
			}

			if (certificado.length > 0) {
				String nombreArchivo= rut.replace(".", "").split("-")[0] + "_" + form.getCertificado().getOriginalFilename();
				Utils.descargar(file2.getAbsolutePath().trim(),
						"\\" + nombreArchivo,
						certificado);

				if (!archivos.contains(form.getCertificado().getOriginalFilename())) {
					listaArchivos.add(nombreArchivo);
					archivos.add(form.getCertificado().getOriginalFilename());
				}
			}

			request.getSession().setAttribute("archivos", archivos);
			request.getSession().setAttribute("listaarchivos", listaArchivos);
			
			List<String> temp = (List<String>) request.getSession().getAttribute("archivos");

			model.addAttribute("files", temp);
			model.addAttribute("enviar", "1");

		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

		return "paso-paso4";
	}

	@RequestMapping(value = { "/documentacion.do" }, method = RequestMethod.POST)
	public String stepDoc(Model model, HttpServletRequest request, @ModelAttribute RegistroFormularioVo form, HttpServletResponse response) {

		try {
			
			String rut = form.getRutTrabajador();
			rut= rut.replaceAll("\\.", "");
			request.getSession().setAttribute("rut", rut.toUpperCase());
			
			String rutEmpresa= (String)request.getSession().getAttribute("rutemp");
			
			ClientBPQueryStatus clienteBP= new ClientBPQueryStatus();
			SalidainfoAfiliadoVO salida= clienteBP.obtenerEstadoAfiliacionCRM(rut, rutEmpresa);
			
			
			if(salida.getEstado()==-1){
				model.addAttribute("errorMsg", "rut_inactivo");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==0){
				model.addAttribute("errorMsg", "rut_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}else if(salida.getEstado()==-9){
				model.addAttribute("errorMsg", "servicio_error");
				model.addAttribute("rut", form.getRut());

				return "index-paso1";
			}
			request.getSession().setAttribute("nombre", salida.getNombreCompleto());
			
			logger.info("Doc. Pendiente, verificando si existe registro previo para rut :"  + rut);
			List<RegistroEntiti> entity = regService.findByRut(Long.parseLong(rut.split("-")[0]));

			SalidainfoAfiliadoVO sal = (SalidainfoAfiliadoVO) request.getSession().getAttribute("CRM");
			request.getSession().setAttribute("pendiente", "SI");
			
			if (entity.size() == 0) {

				List<Sucursales> sucursales = sucService.findAllSucursal();
				request.getSession().setAttribute("sucursales", sucursales);

				return "paso-paso2";

			} else {
				request.getSession().setAttribute("bitacora", "SI");
				request.getSession().setAttribute("sucursal", String.valueOf(entity.get(0).getSucursal()));
				request.getSession().setAttribute("registroAsfam", entity.get(0));

				return "paso-paso4";

			}

		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

	}

	@RequestMapping(value = { "/success.do" }, method = RequestMethod.POST)
	public String stepSuccess(Model model, HttpServletRequest request, HttpServletResponse response) {

		try {

			String pathCorreo = Configuraciones.getConfig("correo.carpeta");

			String sucursal = (String) request.getSession().getAttribute("sucursal");
			String sucursal_descripcion = (String) request.getSession().getAttribute("sucursal_descripcion");
			String rut = (String) request.getSession().getAttribute("rut");
			String rutemp = (String) request.getSession().getAttribute("rutemp");
			String razonSocial = ((Map<String, String>) request.getSession().getAttribute("empresas")).get(rutemp);
			String empresa = rutemp + " " + razonSocial;
			String bitacora= (String)request.getSession().getAttribute("bitacora");
			
			pathCorreo = pathCorreo +sucursal_descripcion.trim() + "\\" + rut.replace(".", "").replace("-", "") + "\\";
			
			List<String> listaArchivos =(List<String>) request.getSession().getAttribute("listaarchivos");
			
			RegistroEntiti reg = (RegistroEntiti) request.getSession().getAttribute("registroAsfam");
			
			if(reg==null){
				return "redirect:/init.do";
			}
			if(bitacora==null){
				regService.save(reg);
				logger.info("Bitacora grabada para id " + reg.getId());
			}

			List<SucAsfam> analistas = sucService.findAnalistaByIdSucursal(sucursal);

			List<String> correos = new ArrayList<String>();

			for (SucAsfam corr : analistas) {

				correos.add(corr.getEmail());
			}
			
			logger.info("Enviando correo cliente: " + reg.getEmail());
			mailService.sendEmail(reg.getEmail(),
					"Ingreso de tramitación Asignación Familiar : " + rut.replace(".", ""),
					Utils.getbodyEncargadoEmpresa());

			logger.info("Enviando correo ejecutivos: " + correos.toString());
			mailService.sendEmailEjec(correos, null,
					"Notificación de recepción de Asignación Familiar : " + rut.replace(".", ""),
					Utils.getbodyEjec(rut, pathCorreo, String.valueOf(reg.getId()), reg.getTelefono(),
								reg.getEmail(), empresa, listaArchivos));
			request.getSession().setAttribute("listaarchivos", new ArrayList<String>());

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error success ", e);

			return "registro_error";
		}

		return "registro-exito";
	}

	@RequestMapping(value = { "/volver.do" }, method = RequestMethod.POST)
	public String back(Model model, @ModelAttribute FicherosVo form, HttpServletRequest request,
			HttpServletResponse response) {

		try {
		

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("volver error ", e);
			return "registro_error";
		}

		return "paso-paso2";
	}

}
