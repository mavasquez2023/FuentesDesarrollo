package cl.laaraucana.dsiniestro.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cl.laaraucana.dsiniestro.entities.RegistroDSiniestro;
import cl.laaraucana.dsiniestro.entities.Sucursal;
import cl.laaraucana.dsiniestro.services.MailService;
import cl.laaraucana.dsiniestro.services.RegistroDSiniestroService;
import cl.laaraucana.dsiniestro.util.Configuraciones;
import cl.laaraucana.dsiniestro.util.Utils;
import cl.laaraucana.dsiniestro.vo.FicherosVo;

@Controller
public class ArchivosController {

	private static final Logger logger = Logger.getLogger(ArchivosController.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private RegistroDSiniestroService regService;


	@RequestMapping(value = { "/files.do" }, method = RequestMethod.POST)
	public String step4(@ModelAttribute FicherosVo form, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String path = Configuraciones.getConfig("pdfs.carpeta");

			String pathCorreo = Configuraciones.getConfig("correo.carpeta");

			byte[] cedula = null;
			byte[] contrato = null;
			byte[] certafp = null;
			byte[] liqafc = null;
			byte[] certemp = null;

			Sucursal sucursalVO = (Sucursal) request.getSession().getAttribute("sucursalVO");
			String sucursal_carpeta= sucursalVO.getCodigoSucursal().trim() + "-" + sucursalVO.getDescripcion().trim();
			String correo= sucursalVO.getEmailEjecutivo();
			
			String rut = (String) request.getSession().getAttribute("rut");

			File file = new File(path + "\\" + sucursal_carpeta);

			if (!file.exists()) {

				file.mkdir();
			}

			File file2 = new File(
					path + "\\" + sucursal_carpeta + "\\" + rut.replace(".", "").replace("-", "") );

			if (!file2.exists()) {

				file2.mkdir();
			}

			//pathCorreo = pathCorreo + "\\" + sucursal.trim() + "\\" + rut.replace(".", "").replace("-", "") + "_"
			//		+ folio + "\\";

			cedula = form.getCedula().getBytes();
			contrato = form.getContrato().getBytes();
			certafp = form.getCertafp().getBytes();
			liqafc = form.getLiqafc().getBytes();
			certemp = form.getCertemp().getBytes();
			
			

			if (cedula.length > 0) {
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + rut.replace(".", "").split("-")[0] 
					 + "_Cedula." + Utils.getExtencion(form.getCedula().getOriginalFilename()), cedula);
			}
			if (contrato.length > 0) {
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + rut.replace(".", "").split("-")[0] 
					 + "_Contrato." + Utils.getExtencion(form.getContrato().getOriginalFilename()), contrato);
			}
			if (liqafc.length > 0) {
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + rut.replace(".", "").split("-")[0] 
					 + "_LiquidacionAFC." + Utils.getExtencion(form.getLiqafc().getOriginalFilename()), liqafc);
			}
			if (certemp.length > 0) {

				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + rut.replace(".", "").split("-")[0]
					 + "_CertificadoEmpresa." + Utils.getExtencion(form.getCertemp().getOriginalFilename()), certemp);
			}
			if (certafp.length > 0) {
				Utils.descargar(file2.getAbsolutePath().trim(), "\\" + rut.replace(".", "").split("-")[0] 
					 + "_CertificadoAFP." + Utils.getExtencion(form.getCertafp().getOriginalFilename()), certafp);
			}

			RegistroDSiniestro reg = (RegistroDSiniestro) request.getSession().getAttribute("registroDSiniestro");
			regService.save(reg);
			
			pathCorreo= pathCorreo + "\\" + sucursal_carpeta + "\\" + rut.replace(".", "").replace("-", "") + "\\";
			
			
			//Se envía mail a afiliado
			mailService.sendEmailClie(reg.getEmail(), "Ingreso de tramitación Siniestro La Araucana"
					, Utils.bodyClient(""), reg.getRut());
			
			//se envía mail a Ejecutivo
			if(correo!=null && !correo.equals("")){
				mailService.sendEmailEjec(correo, "Notificación tramitación seguro cesantía por Siniestro: " + rut.replace(".", "").replace("-", "")
						,
						Utils.getbodyEjec(rut, pathCorreo, String.valueOf(reg.getId()), reg.getTelefono(), reg.getEmail()));
			}
		} catch (

		Exception e) {
			// TODO: handle exception
			logger.error("Error files ", e);

			return "registro_error";
		}

		request.getSession().setAttribute("registroDSiniestro", null);
		request.getSession().setAttribute("rut", null);
		request.getSession().setAttribute("telefono", null);
		request.getSession().setAttribute("correo", null);
		request.getSession().setAttribute("sucursal", null);
		request.getSession().setAttribute("sucursalVO", null);
		request.getSession().setAttribute("nombre", null);
		request.getSession().setAttribute("email", null);
		request.getSession().invalidate();

		return "registro-exito";
	}

}
