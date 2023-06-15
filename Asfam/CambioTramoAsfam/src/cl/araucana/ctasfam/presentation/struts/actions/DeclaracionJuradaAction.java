package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import cl.araucana.ctasfam.business.service.impl.PropuestaRentasServiceImpl;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.ProcesoBashTO;
import cl.araucana.ctasfam.business.to.RentaproTO;
import cl.araucana.ctasfam.business.to.VerificadorTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.EmpresaComprobante;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class DeclaracionJuradaAction extends Action {
	Logger log = Logger.getLogger(this.getClass());
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();

		AceptaPropuestaForm form1 = (AceptaPropuestaForm) form;

		String ruts = form1.getRutt();
		String estado = form1.getEtapa();
		String rutdeclara[] = ruts.split("=");
		
		String rol="";
		String rute = null;
		String rut = null;
		Vector v = new Vector();
		AraucanaJdbcDao dao = new AraucanaJdbcDao();
		FlujoTO flujo = new FlujoTO();
		String mensaje = null;
		//List listaerror = new ArrayList();
		List listaerrors = new ArrayList();
		VerificadorTO ocase = new VerificadorTO();
		RentaproTO renta = new RentaproTO();
		List declaracionEmpresas = new ArrayList();

		Utils util = new Utils();

		String extension = null;

		//Properties Param = new Properties();
		//Param.load(getClass().getClassLoader().getResourceAsStream(
		//		"configuracion.properties"));
		Date hoy = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",
				new Locale("cl"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss", new Locale("cl"));
		String hora = sdf2.format(hoy);
		String fecha = sdf.format(hoy);
		String ext = fecha + hora;
		Encargado enc1 = new Encargado();
		enc1 = (Encargado) request.getSession().getAttribute("edocs_encargado");
		List registros = (List) request.getSession().getAttribute("registros");
		String rutenc = String.valueOf(enc1.getRut());
		String rutaArchivos = Parametros.getInstance().getParam().getCarpeta_respaldo();
		String rutaZip = Parametros.getInstance().getParam().getCarpeta_descompres();
		String periodo = Parametros.getInstance().getParam().getPeriodoProceso();
		String nombrearchivo = null;
		log.info("Procesando Declaración Jurada empresa: " + rutdeclara);
		
		String[] datosEmpresas = request.getParameterValues("option");

		boolean flagAceptoProp = false;
		
		request.setAttribute("datosEmpresas", datosEmpresas);
		Mejoras2016DaoImpl probashDao = new Mejoras2016DaoImpl();
		
		try {

			//System.out.println("estado= " + estado);
			rol = (String) request.getSession().getAttribute("rol");
			
			FormFile archivo = (FormFile) request.getSession().getAttribute(
					"file");

			if (archivo != null) {
				
				File file = new File(archivo.getFileName());
				log.info("Procesando archivo: " + archivo.getFileName());

				if (archivo.getFileSize() == 0) {
					mensaje = dao.getValores("003", "ERROR")[0];
					errors.add("name", new ActionMessage("id"));
				}

				extension = util.extencion(file);

				if (!extension.equalsIgnoreCase("zip")) {

					String tipo = "NORMAL";

					ocase.setTipo("nozip");
					ocase.setNombre(archivo.getFileName());

				} else {
/*
					ocase.setTipo("zip");
					ocase.setNombre(archivo.getFileName());

					request.getSession().setAttribute("edocs_encargado", enc1);

					File filer = util.creaArchivoas400(rutaArchivos, archivo,
							ext);
					System.out.println(filer.getAbsolutePath());
					File filez = util.creaArchivoas400(rutaZip, archivo, ext);
					
					System.out.println(filez.getAbsolutePath());
					if (util.unZip(filer.getAbsolutePath(), rutaZip + ext + "/")) {

						File zip = new File(filez.getAbsolutePath());
						if (zip.exists())
							zip.delete();

						//listaerrors = util.procesaFicherosgrava(rutaZip + "\\"
								+ ext, request);

					}
					*/
				}

				//System.out.println("filesize " + archivo.getFileSize());
				//System.out.println("filename " + archivo.getFileName());

			}
			
			//Setea datos cabecera
			String rutEmpresa = rutdeclara[0].split("-")[0];
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdhTime = new SimpleDateFormat("HH:mm:ss");

			
			int cantidad = probashDao.contarRegistrosAceptados(Integer.parseInt(rutEmpresa), Integer.parseInt(periodo));
			log.info("Registros en Propuesta:" + cantidad);	
			int intentos = probashDao.existBash(Integer.parseInt(rutEmpresa), Integer.parseInt(periodo));
			
			ProcesoBashTO procesoBashTO = new ProcesoBashTO();
			procesoBashTO.setRutaArchivo(archivo.getFileName());
			procesoBashTO.setEstado("T");
			procesoBashTO.setEmpresa(Integer.parseInt(rutEmpresa));
			procesoBashTO.setUsuarioSube("ENCEMP-ENI");
			procesoBashTO.setFechaSubida(sdfDate.format(new Date()));
			procesoBashTO.setHoraSubida(sdhTime.format(new Date()));
			procesoBashTO.setFechaProcesamiento(new Timestamp(new Date().getTime()));
			procesoBashTO.setRegistrosInformados(cantidad);
			procesoBashTO.setCantidadIntento(1);
			procesoBashTO.setOrigen("E");
			procesoBashTO.setPeriodo(Integer.parseInt(periodo));
			procesoBashTO.setRegistrosProcesados(0);
			procesoBashTO.setRegistrosNoInformados(0);
			log.info("Seteando cabecera AFP66F1, intentos previos=" + intentos);
			if(intentos==0){
				probashDao.insertBash(procesoBashTO);
			}else{
				intentos++;
				procesoBashTO.setCantidadIntento(intentos);
				probashDao.updateBash(procesoBashTO);
			}
			
			java.sql.Time time = new java.sql.Time(new Date().getTime());
				
			//Inserta registros por batch
			log.info("Insertando " + registros.size() + " registros en AFP64F1 para: " + rutEmpresa);
			boolean insert= probashDao.insertaArchivo(registros, Integer.parseInt(rutEmpresa), Integer.parseInt(periodo));
			
			if(insert){
				//Insertar registros No informados
				int noinformados=probashDao.insertBashNoInformados(Integer.parseInt(rutEmpresa));
				log.info("Registros no informados: " + noinformados);
				
				//Actualizar datos cabecera
				log.info("Actualiza cabecera, registros procesados: " + registros.size());
				procesoBashTO.setRegistrosProcesados(registros.size());
				procesoBashTO.setEstado("F");
				procesoBashTO.setFechaSubida(sdfDate.format(new Date()));
				procesoBashTO.setHoraSubida(sdhTime.format(new Date()));
				procesoBashTO.setFechaProcesamiento(new Timestamp(new Date().getTime()));
				procesoBashTO.setRegistrosNoInformados(noinformados);
				
				boolean update= probashDao.updateBash(procesoBashTO);
				//System.out.println("Se actualizó cabecera");
				
				//Actualiza Oficina Sucursal
				log.info("Actualizando Oficina Sucursal");
				update= probashDao.updateOficinaSucursal(Integer.parseInt(rutEmpresa), Integer.parseInt(periodo));
				//System.out.println("Se actualizó oficina sucursal");
				
				String dvEmpresa = rutdeclara[0].split("-")[1];
				flujo.setPeriodo(periodo);
				flujo.setRutempresa(rutEmpresa);
				flujo.setRutencargado(rutenc);
				flujo.setEtapa("4");
				flujo.setOperacion("ACEPTA DECLARACION");
				flujo.setNombrearchivo("");
				flujo.setCantregistros(0);
				flujo.setISAJKM94("CTADMIN");
				flujo.setISAJKM92("");
				dao.InsertaBitacora(flujo);
				flujo.setISAJKM92("CTADMIN");

				dao.updateFlujo(flujo);
				if (estado.equalsIgnoreCase("1")) {

					//dao.updateEstado(periodo, rutEmpresa, time);

				}
				if (archivo == null) {
					nombrearchivo = "";
					extension = "";
				} else {
					nombrearchivo = archivo.getFileName().substring(0,
							archivo.getFileName().lastIndexOf("."));
				}
				renta.setArchivo(nombrearchivo);
				renta.setCantarchivos("1");
				renta.setRutEmpresa(rutEmpresa);
				renta.setRutencargado(rutenc);
				char dvencargado = util.getDigito(rutenc);
				renta.setDvrutempresa(dvEmpresa);
				renta.setDvencargado(String.valueOf(dvencargado));
				renta.setEtapa("3");
				renta.setExtencion(extension);
				renta.setCantreg("0");
				renta.setMail1("");
				renta.setMail2("");
				renta.setMail3("");
				renta.setNombreencargado(enc1.getFullName());

				if (dao.InsertaRenta(renta)) {
					//System.out.println("insert Successful");
				}
			}else{
				mensaje = "No se pudo insertar los trabajadores";
				errors.add("name", new ActionMessage("id"));
			}
				
				

		} catch (Exception ex) {
			mensaje = "La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
			ex.printStackTrace();
			log.error("Error al grabar archivo de Declaración de Renta, mensaje: " + ex.getMessage() );
		}
		

		if (!errors.isEmpty()) {

			request.setAttribute("mensaje", mensaje);
			if(rol.equals("Ejecutivo")){
				forward= mapping.findForward("onErrorEjecutivo");
			}else{
				forward= mapping.findForward("onError");
			}
		} else {
//			if(flagAceptoProp){
//				forward = mapping.findForward("pretermino2");
//			}else{
				forward = mapping.findForward("pretermino");
//			}
			
		}

		return forward;

	}

}
