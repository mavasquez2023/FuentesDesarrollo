package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;

/**
 * @version 1.0
 * @author
 */

public class CargaCertificadoInvAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession sesion = request.getSession();

		logger.debug(">> Entro a imprimirReporte.");

		try {

			Map<String, Object> hash = new HashMap<String, Object>();
			String rut = (String) sesion.getAttribute("rut");
			String nAfiliado = (String) sesion.getAttribute("nombreAfiliado");
			String fAfiliacion = (String) sesion.getAttribute("fechaAfiliacion");
			String fechaFinRol = (String) sesion.getAttribute("fechaTerminoRol");
			if(fechaFinRol==null){
				fechaFinRol= request.getParameter("fechaTerminoRol");
			}
			String nombreEmpresa = (String) sesion.getAttribute("nombreEmpresa");
			String rol= (String)sesion.getAttribute("rol");
			
			if(rut == null || nAfiliado == null || rut.length()==0 || nAfiliado.length()==0 ){
				//Cuando faltan datos para llenar el certificado
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
			}
			
			//Generando codigo validación
			User userInfo = (User) sesion.getAttribute("userInfo");
			String codValidacion= generaCodigoValidaon(userInfo, rut, nAfiliado, rol, fechaFinRol);
			
			//Guardar certificado en SQLServer
			BitacoraVO bitacora= new BitacoraVO();
			bitacora.setIdCertificado(codValidacion);
			bitacora.setFolio("");
			bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_AFILIACION));
			bitacora.setRutDeudor(rut);
			bitacora.setNombreDeudor(nAfiliado);
			bitacora.setTotalPagar(0);
			bitacora.setRutUsuario("");
			bitacora.setNombreUsuario("");
			if(userInfo!=null){
				bitacora.setRutUsuario(userInfo.getID());
				bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
			}
			logger.info("<< Guardar Bitacora para Auditoria (SQLServer)");
			if(!BitacoraDAO.insertaBitacora(bitacora)){
				logger.warn("No se ha insertado Certificado Afiliación en Bitacora SQLServer, Rut" + rut);
			}

			//Cargando parametros para jasper Report
			hash.put("rutAfiliado", rut);
			hash.put("nombreAfiliado", nAfiliado);
			hash.put("fechaEmision", Utils.getFechaCompleta());
			hash.put("fechaAfiliacion", fAfiliacion);
			hash.put("nombreEmpresa", nombreEmpresa);
			hash.put("fechaFinRol", fechaFinRol);
			hash.put("rol", rol);
			
			// hash.put("imgPath",
			// ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.imgPath"));
			// hash.put("imgPath",
			// CertificadoConst.RES_CERTIFICADOS.getString("certificado.afiliacion.img"));
			hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.afiliacion.firma"));
			hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
			hash.put("logoReducido", CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.logoReducido"));
			
			String ruta="";
			String nombreArchivo="";
			if(rol.equals("Trabajador")){
				ruta = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.jasper.inv");
			}else{
				ruta = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.pensionado.jasper.inv");
			}
			nombreArchivo = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.nombre");
			ReporteUtil ru = new ReporteUtil(null, hash, ruta);

			logger.debug("Set correcto datos reporte.");
			
			byte[] bites = ru.exportCompilePdf();
			logger.debug("Reporte Creado Exitosamente.");
			
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", nombreArchivo);
			
			//Almacenar certificado en Ftp
			FtpUtil ftpUtil = new FtpUtil();
			ftpUtil.subirArchivoURLFtp(bites, rut+CertificadoConst.TIPO_CERT_AFILIACION+".pdf");
			ftpUtil.desconectar();
			
			logger.debug(">> Salida a servlet Reporte.");

		} catch (Exception e) {
			request.setAttribute("title", "Error al generar el reporte: ");
			request.setAttribute("message", "Intente nuevamente");
			logger.error(e.getMessage());
			return mapping.findForward("customError");
		}

		return mapping.findForward("ExportPDF");
	}
	
	private String generaCodigoValidaon(User userInfo , String rutAfiliado, String nombreAfiliado, String tipoAfiliado, String fechaTerminoAfiliacion){
		String codValidacion=null;
		try{
			//Setear valores para almacenar en archivo at02f2
	        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
	        ValorValidableVO valor = new ValorValidableVO();
	        valor.setVariable("Tipo Afiliado");
	        valor.setValor(tipoAfiliado);
	        listaValores.add(valor);
	       
	        valor = new ValorValidableVO();
	        valor.setVariable("Fecha término Afiliación");
	        valor.setValor(fechaTerminoAfiliacion);
	        listaValores.add(valor);   
	        
	      //Se setea datos ejecutivo generó certificado
	        if(userInfo!=null){
	        	valor = new ValorValidableVO();
	        	valor.setVariable("Ejecutivo");
	        	valor.setValor(userInfo.getFirstName() + " " + userInfo.getLastName() + ", RUT: " + userInfo.getID());
	        	listaValores.add(valor);
	        }
	        
	        logger.info("<< Guardar Certificado para Validación");
	    	codValidacion = CertificadoUtils.guardarCertificado(nombreAfiliado, rutAfiliado, listaValores, CertificadoConst.TIPO_CERT_AFILIACION);
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return codValidacion;
	}
}
