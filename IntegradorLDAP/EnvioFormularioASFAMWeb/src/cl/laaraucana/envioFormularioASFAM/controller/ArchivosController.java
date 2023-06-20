package cl.laaraucana.envioFormularioASFAM.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cl.laaraucana.envioFormularioASFAM.entities.SucAsfam;
import cl.laaraucana.envioFormularioASFAM.entities.RegistroEntiti;
import cl.laaraucana.envioFormularioASFAM.entities.Sucursales;
import cl.laaraucana.envioFormularioASFAM.model.FicherosVo;
import cl.laaraucana.envioFormularioASFAM.model.RegistroFormularioVo;
import cl.laaraucana.envioFormularioASFAM.services.MailService;
import cl.laaraucana.envioFormularioASFAM.services.RegistroService;
import cl.laaraucana.envioFormularioASFAM.services.SucursalService;
import cl.laaraucana.envioFormularioASFAM.util.Configuraciones;
import cl.laaraucana.envioFormularioASFAM.util.Utils;
import cl.laaraucana.envioFormularioASFAM.vo.SalidainfoAfiliadoVO;

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
			
			logger.info("Guardando solicitud en carpeta: " + pathCorreo);
			
			solicitud = form.getSolicitud().getBytes();

			if (solicitud.length > 0) {
				String nombreArchivo= rut.replaceAll("\\.", "").replace("-", "")
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
			//Archivos que se muestran en la página paso 4				
			List<String> archivos = new ArrayList<String>();
			//Archivos para la lista del correo
			List<String> listaArchivos =(List<String>) request.getSession().getAttribute("listaarchivos");
			if(listaArchivos==null && request.getSession().getAttribute("pendiente")!=null){
				listaArchivos=new ArrayList<String>();
			}
			if ((List<String>) request.getSession().getAttribute("archivos") != null) {

				archivos = (List<String>) request.getSession().getAttribute("archivos");
			}

			if (certificado.length > 0) {
				String nombreArchivo= rut.replaceAll("\\.", "").split("-")[0] + "_" + form.getCertificado().getOriginalFilename();
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
	public String stepDoc(Model model, HttpServletRequest request, HttpServletResponse response) {

		try {

			String rut = (String) request.getSession().getAttribute("rutLdap");
			
			logger.info("Doc. Pendiente, verificando si existe registro previo para rut :"  + rut);
			List<RegistroEntiti> entity = regService.findByRut(Long.parseLong(rut.split("-")[0]));

			SalidainfoAfiliadoVO sal = (SalidainfoAfiliadoVO) request.getSession().getAttribute("CRM");
			request.getSession().setAttribute("pendiente", "SI");
			
			if (entity.size() == 0) {
				logger.info("Sin regstro previo, mostrando paso 2");
				List<Sucursales> sucursales = sucService.findAllSucursal();
				request.getSession().setAttribute("sucursales", sucursales);
				return "paso-paso2";

			} else {
				request.getSession().setAttribute("bitacora", "SI");
				request.getSession().setAttribute("registroAsfam", entity.get(0));
				request.getSession().setAttribute("sucursal", String.valueOf(entity.get(0).getSucursal()));
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
			String bitacora= (String)request.getSession().getAttribute("bitacora");
			
			RegistroEntiti reg = (RegistroEntiti) request.getSession().getAttribute("registroAsfam");
			if(reg==null){
				return "redirect:/init.do";
			}
			if(bitacora==null){
				regService.save(reg);
				logger.info("Bitacora grabados para id " + reg.getId());
			}
			
			pathCorreo = pathCorreo +sucursal_descripcion.trim() + "\\" + rut.replace(".", "").replace("-", "") + "\\";
					
			List<String> listaArchivos =(List<String>) request.getSession().getAttribute("listaarchivos");
			
			logger.info("Buscando analistas sucursal " + sucursal);
			List<SucAsfam> analistas = sucService.findAnalistaByIdSucursal(sucursal);

			List<String> correos = new ArrayList<String>();

			for (SucAsfam corr : analistas) {

				correos.add(corr.getEmail());
				//logger.info("Correo analista de sucursl " + sucursal + ": " + reg.getEmail());
			}
			
			// Envìo mail a Afiliado
			logger.info("Enviando correo cliente: " + reg.getEmail());
			mailService.sendEmail(reg.getEmail(),
					"Ingreso de tramitación Asignación Familiar : " + rut.replace(".", ""), Utils.bodyClient());

			// Envìo mail a Ejecutivo
			logger.info("Enviando correo ejecutivos: " + correos.toString());
			mailService.sendEmailEjec(correos, null,
					"Notificación de recepción de Asignación Familiar : " + rut.replace(".", ""),
					Utils.getbodyEjec(reg.getRutAfi() + "-" + reg.getDvAfi(), pathCorreo, reg.getId() + "",
							reg.getTelefono(), reg.getEmail(), listaArchivos));

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
