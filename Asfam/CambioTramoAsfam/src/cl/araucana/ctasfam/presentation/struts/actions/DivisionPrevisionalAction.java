package cl.araucana.ctasfam.presentation.struts.actions;

 

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping; 
import org.apache.struts.actions.DispatchAction;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
 

public class DivisionPrevisionalAction extends DispatchAction {
	private static final Log log = LogFactory
			.getLog(DivisionPrevisionalAction.class);

	AraucanaJdbcDao dao=new AraucanaJdbcDao();
	
	public ActionForward homeDivisionPrevisional(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AceptaPropuestaForm acepta=new AceptaPropuestaForm();
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String proceso=Param.getProperty("PROCESO");
		
		  acepta.setProceso(proceso);
		  
		
		 
		
		request.setAttribute("proceso", acepta);
		
		return mapping.findForward("showHome");
	}

/*	public ActionForward consultarEstadoAfiliados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FlujoTO flujo=  new FlujoTO();
		 Properties Carpetas = new Properties();
		  Carpetas.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		  String periodo=Carpetas.getProperty("PERIODO");
		  Encargado enc =new Encargado();
		  enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			DynaActionForm dForm = (DynaActionForm) form;
			Integer oficina = (Integer) dForm.get("oficina");
			String empresa = (String) dForm.get("empresa");
			Integer sucursal = (Integer) dForm.get("sucursal");
	       
	        
			dForm.set("afiliados_propuesta", null);
			dForm.set("afiliados_propuesta_informados", null);
			dForm.set("afiliados_propuesta_propuesta", null);
			dForm.set("afiliados_propuesta_size", null);
			if (oficina != null && empresa != null && sucursal != null) {
				
				
				String[] rutEmpresa = empresa.split("-");
				
			
				
				List afiliadosInformados = service.getPropuestaRentasService()
						.obtenerAfiliadosPropuestaInformados(
								oficina.intValue(),
								Integer.parseInt(rutEmpresa[0]), rutEmpresa[1],
								sucursal.intValue());
				List afiliadosPropuesta = service.getPropuestaRentasService()
						.obtenerAfiliadosPropuesta(oficina.intValue(),
								Integer.parseInt(rutEmpresa[0]), rutEmpresa[1],
								sucursal.intValue());
				if (afiliadosInformados != null) {
					dForm.set("afiliados_propuesta", afiliadosInformados);
					dForm.set("afiliados_propuesta_informados", new Integer(afiliadosInformados.size()));
					if(afiliadosPropuesta != null) {
						dForm.set("afiliados_propuesta_propuesta", new Integer(afiliadosPropuesta.size()));
					} else {
						dForm.set("afiliados_propuesta_propuesta", new Integer(0));
					}
				} else {
					dForm.set("afiliados_propuesta_size", new Integer(0));
				}
			} else {
				dForm.set("oficina", null);
				dForm.set("empresa", null);
				dForm.set("sucursal", null);
				dForm.set("afiliados_propuesta", null);
				dForm.set("afiliados_propuesta_informados", null);
				dForm.set("afiliados_propuesta_propuesta", null);
				dForm.set("afiliados_propuesta_size", null);
			}
			
			 flujo.setRutencargado(String.valueOf(enc.getRut()));
			 flujo.setEtapa("4");
			 flujo.setRutempresa(empresa);
			 flujo.setPeriodo(periodo);
			 flujo.setCantregistros(0);
			 flujo.setOperacion("ACEPTA DECLARACION");
			 flujo.setISAJKM92("");
			 flujo.setISAJKM94("CTADMIN");
			 flujo.setNombrearchivo("");
			 
			// dao.InsertaBitacora(flujo);
		} catch (Exception e) {
			String mensaje="La sesión expiró o el sistema encontro una excepción";
			request.setAttribute("mensaje", mensaje);
			log.error("Error: " + e.getLocalizedMessage(), e);
			return mapping.findForward("onError");
		}
		return mapping.findForward("consulta1");
	}

	public ActionForward consultarPropuestaWeb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		 
		String proceso=Param.getProperty("PROCESO");
		AceptaPropuestaForm aceptar= new AceptaPropuestaForm();
		aceptar.setProceso(proceso);

		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			DynaActionForm dForm = (DynaActionForm) form;
			String empresa = (String) dForm.get("empresa");
			if ("POST".equalsIgnoreCase(request.getMethod()) && empresa != null) {
				String[] rutEmpresa = empresa.split("-");
				if (service.empresaTieneArchivosPropuesta(rutEmpresa[0])) {
					List archivos = service.obtenerArchivosPropuesta(rutEmpresa[0]);
					for (Iterator iter = archivos.iterator(); iter.hasNext();) {
						ArchivoPrpuesta file = (ArchivoPrpuesta) iter.next();
						if (file.getExiste()) {
							file.setUrlDescarga(service
									.prepareDescargaPropuesta(rutEmpresa[0],
											file.getFormato()));
						}
					}
					
					System.out.println("archivos " + archivos.size());
					dForm.set("afiliados_propuesta", archivos);
				} else {
					dForm.set("afiliados_propuesta_size", new Integer(0));
				}
			} else {
				dForm.set("oficina", null);
				dForm.set("empresa", null);
				dForm.set("sucursal", null);
				dForm.set("afiliados_propuesta", null);
				dForm.set("afiliados_propuesta_informados", null);
				dForm.set("afiliados_propuesta_propuesta", null);
				dForm.set("afiliados_propuesta_size", null);
			}
		} catch (Exception e) {
			log.error("Error: " + e.getLocalizedMessage(), e);
			return mapping.findForward("onError");
		}
		request.setAttribute("proceso", aceptar);
		return mapping.findForward("consulta2");
	}

	public ActionForward descargaArchivos(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionMessages errors=new ActionMessages();
		String mensaje=null;
		try {
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			String rutEmpresa = request.getParameter("rutEmpresa");
			String formato = request.getParameter("formato");
			String ruta = service.getRutaArchivo(rutEmpresa) + "." + formato;
			int zipBufferSize = Integer.parseInt(service
					.getApplicationProperties().getProperty(
							"initialZipBufferSize"));
			ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(
					zipBufferSize);
			byte[] buf = new byte[zipBufferSize];
			ZipOutputStream out = new ZipOutputStream(bufferedOutput);
			try {
				FileInputStream in = new FileInputStream(ruta);
				String temp = ruta.substring(6);
				out.putNextEntry(new ZipEntry(temp));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			} catch (FileNotFoundException e) {
				mensaje="La sesión expiró o el sistema encontro una excepción";
				errors.add("name", new ActionMessage("id"));
				log.error("Error: archivo no encontrado, "
						+ e.getLocalizedMessage(), e);
			}
			out.close();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "inline; filename="
					+ service.getApplicationProperties().getProperty(
							"zippedDocPrefix") + "_" + rutEmpresa + ".zip");
			response.setContentLength(bufferedOutput.size());
			OutputStream output = response.getOutputStream();
			bufferedOutput.writeTo(output);
			output.close();
			bufferedOutput.close();
		} catch (IOException e) {
			log.error("Error: " + e.getLocalizedMessage(), e);
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("onError");
		} catch (Exception e) {
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
			mensaje="La sesión expiró o el sistema encontro una excepción";
			log.error("Error: " + e.getLocalizedMessage(), e);
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("onError");
		}
		return null;
	}*/
}