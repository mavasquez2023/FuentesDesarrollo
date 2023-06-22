package cl.araucana.cotcarserv.main.actions;

import java.io.PrintStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.cotcarserv.dao.VO.CargasVO;
import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.main.forms.ConsultaCotizacionForm;
import cl.araucana.cotcarserv.servlets.EmpresasLDAP;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.GeneratorXLS;
import cl.laaraucana.satelites.Utils.RutUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.recursos.EnviarMail;
/**
 * @version 1.0
 * @author
 */
public class DescargaCotizacionesAction extends Action {
	protected Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		ActionForward forward = new ActionForward(); // return value
		Map<String, String> listamap=null;
		HttpSession sesion = request.getSession();
		sesion = request.getSession();
		request.setAttribute("menu", "cotizaciones");
		
		if(sesion.getAttribute("empresas")==null || sesion.getAttribute("rol")==null){
			forward = mapping.findForward("init");
			return forward;
		}
		int nummeses=1;
		List<String> listaPeriodos= new ArrayList<String>();
		String rutEmpresa = request.getParameter("rutEmpresa");
		String periodo = request.getParameter("periodo");
		String accion = request.getParameter("accion");
		int dia_corte= Integer.parseInt(CertificadoConst.RES_CERTIFICADOS.getString("dia.corte.cotizaciones"));
		
		if(Utils.obtenerDiaActual()<dia_corte){
			nummeses++;
		}
		String periodoEnProceso=Utils.obtenerPeriodoCualquiera(nummeses*-1);
		request.setAttribute("periodoProceso", Utils.pasaPeriodoASaWEB(periodoEnProceso));
		
		if(periodo==null){
			periodo=  periodoEnProceso;
		}else{
			periodo= periodo.split("/")[1].trim() + periodo.split("/")[0].trim();
		}
		request.setAttribute("periodo", Utils.pasaPeriodoASaWEB(periodo));
		for (int i = nummeses*-1; i > (nummeses*-1)-6; i--) {
			listaPeriodos.add(Utils.pasaPeriodoASaWEB(Utils.obtenerPeriodoCualquiera(i)));
		}
		request.setAttribute("listaPeriodos", listaPeriodos);	
	
		
		if(accion==null){
			accion="";
		}
				
		if(rutEmpresa==null || rutEmpresa.equals("") || accion.equals("menu")){
			forward = mapping.findForward("seleccion");
			return forward;
		}
		
		RutUtil rutentrada= new RutUtil();
		if(!rutentrada.IsRutValido(rutEmpresa)){
			request.setAttribute("error", "2");
			forward = mapping.findForward("success");
			return (forward);
		}
		String fecha=Utils.obtenerPeriodoCualquieraFrom(periodo, 1, true) + "-01";
		ParamVO paramVO= new ParamVO();
		paramVO.setRutEmpresa(rutentrada.obtenerParteNumerica(rutEmpresa));
		paramVO.setPeriodo(Integer.parseInt(periodo));
		paramVO.setFecha(fecha);

		
		logger.info("Ingreso a consulta cotizaciones, Periodo:" + periodo + ", RutEmpresa= " + rutEmpresa);

		try {
		
			ConsultaServicesDAO cotizaDAO= new ConsultaServicesDAO();
			logger.info("Consultando Cotizaciones Trabajadores");

			List<CotizacionesVO> listaCotizaciones= cotizaDAO.consultaCotizaciones(paramVO);
			
			logger.info("Cantidad registros retornados:" + listaCotizaciones.size());
			if(listaCotizaciones.size()>0){
				request.setAttribute("error", "0");
				String filename= (String)request.getParameter("filename");
				
				if (filename== null || filename.equals("")){
					filename= "No_Cotizados_" + periodo + "_" +  rutEmpresa + ".csv"; 
				}
				
				//Generando la salida
				//String pathfile= "/tmp/services/" + filename;
				//logger.info("Se generará salida en: " + pathfile);
				response.setHeader("Expires", "0");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				response.setContentType("application/text");
				response.setHeader("Content-Disposition", "inline; filename=" + filename);
				ServletOutputStream out = response.getOutputStream();
				//OutputStream out = new FileOutputStream(pathfile);
				PrintStream flujo= new PrintStream(out);
				GeneratorXLS xls= new GeneratorXLS(flujo);
				
				//Configurando columnas a desplegar y titulos de estas.
				String[] columnas={"periodo", "rutEmpresa", "dvEmpresa", "rutTrabajador", "dvTrabajador", "nombre", "apellidoPaterno", "apellidoMaterno", "fechaAfiliacion"};
				String[] titulos={"Periodo", "RUT Empresa", "Dv Empresa", "RUT Trabajador", "Dv Trabajador", "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha Afiliacion"};
				
				xls.generarCSVfromCollection(listaCotizaciones, columnas, titulos);
				logger.info("Archivo ha sido generado.");
				//Cerrando salida
				out.flush();
				out.close();
				
				request.setAttribute("error", "0");
				forward = null;
				/*
				logger.info("Enviando Propuesta por correo.");
				String sendto= "clillo007@gmail.com";
				String emails= "clillo007@gmail.com";
				//Enviar Correo con excel adjunto
				enviarMail(Formato.split(sendto, ";"), Formato.split(emails, ";"), periodo, pathfile );
				 */
			}else{
				request.setAttribute("error", "-1");
				request.setAttribute("mensaje", rutEmpresa);
				forward = mapping.findForward("success");
			}
		
			//sesion.setAttribute("salida", listaCargas);
			sesion.setAttribute("fechaEmision", Utils.getFechaCompleta());
			

			//Insert Bitácora
			//insertBitacora(idCertificado, rutEmpresa);

		} catch (Exception e) {
			// Rut no valido
			logger.error("Error en consulta Rut: " + paramVO.getRutEmpresa());
			request.setAttribute("title", "Sistema Error");
			request.setAttribute("errorMsg", "Error al consultar datos:" + e.getMessage());
			request.setAttribute("error", "1");
			forward = mapping.findForward("success");				
		}
		
		return (forward);

	}
		
	 public void insertBitacora(String id, String rutEmpresa){
	    	try {
				ConsultaServicesDAO dao= new ConsultaServicesDAO();
				Map param= new HashMap();
				param.put("id", id);
				param.put("accion", "CONSULTA");
				param.put("rutEmpresa", rutEmpresa);
				param.put("oficina","1");
				param.put("sucursal", "1");
				int ok_bita= dao.insertBitacora(param);
				logger.info("Consulta registrado en bitácora: " + ok_bita);
			} catch (Exception e) {
				logger.warn(">>Error al registrar en bitácora consulta informe");
			}
	    }
	 
//		Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
	    public void enviarMail( String[] mailDestinatarios, String[] mailEncargados, String periodo, String adjunto) {
	    	String subject="";
			try {			
				EnviarMail mail= new EnviarMail("aplica", "aplica");
				StringBuffer body= new  StringBuffer();
				subject= "Informe Cotizaciones Trabajadores" ;
				body.append("Estimado: se adjunta archivo con información de trabajadores no cotizados, periodo:" + periodo+ "<BR>");
				body.append("<br><br>");
				body.append("Saluda atte. a Ud. "+"<BR>");
				body.append("La Araucana - Soluciones Sociales.");
				mail.attach(adjunto);
				mail.mailTo("aplica@laaraucana.cl", mailDestinatarios, mailEncargados, null , subject, body.toString());

				System.out.println(".............. EMAIL GENERADO .................... " );

				}catch(Exception e) {
					System.out.println("CAI EN MAIL  " );
					e.printStackTrace();
				}
	 	 }
	    
}
