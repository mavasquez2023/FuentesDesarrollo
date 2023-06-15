package cl.laaraucana.satelites.certificados.afiliacion.actions;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.finiquito.utils.UtilPrint;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;

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
			
			if(rut == null || nAfiliado == null || fAfiliacion == null || rut.length()==0 || nAfiliado.length()==0 || fAfiliacion.length()==0){
				//Cuando faltan datos para llenar el certificado
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
			}
			
			hash.put("rutAfiliado", rut);
			hash.put("nombreAfiliado", nAfiliado);
			hash.put("fechaEmision", Utils.getFechaCompleta());
			hash.put("fechaAfiliacion", fAfiliacion);
			
			// hash.put("imgPath",
			// ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.imgPath"));
			// hash.put("imgPath",
			// CertificadoConst.RES_CERTIFICADOS.getString("certificado.afiliacion.img"));
			hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.afiliacion.firma"));
			hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
			hash.put("logoReducido", CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.logoReducido"));

			/**
			 * REQ-7962 Integración de servicios Banking con kiosco.
			 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
			 */
			if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
				request.setAttribute("hash", hash);
				RequestDispatcher rd = request.getRequestDispatcher("imprimeCertAfi.jsp");
				rd.forward(request, response);
				return null;
			}else{
				String ruta = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.jasper");
				String nombreArchivo = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.afiliacion.nombre");

				ReporteUtil ru = new ReporteUtil(null, hash, ruta);
				logger.debug("Set correcto datos reporte.");
				byte[] bites = ru.exportCompilePdf();
				logger.debug("Reporte Creado Exitosamente.");
				request.setAttribute("bites", bites);
				request.setAttribute("nombreArchivo", nombreArchivo);
				
		    	//Almacenar certificado en Ftp
				FtpUtil ftpUtil = new FtpUtil();
				ftpUtil.subirArchivoURLFtp(bites, rut+CertificadoConst.TIPO_CERT_AFILIACION+".pdf");
			}
			// Fin modificación
			
			logger.debug(">> Salida a servlet Reporte.");

		} catch (Exception e) {
			request.setAttribute("title", "Error al generar el reporte: ");
			request.setAttribute("message", "Intente nuevamente");
			logger.error(e.getMessage());
			return mapping.findForward("customError");
		}

		return mapping.findForward("ExportPDF");
	}

}
