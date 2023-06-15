package cl.laaraucana.satelites.certificados.creditovigente.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ibm.crypto.pkcs11impl.provider.Session;

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;

/**
 * @version 	1.0
 * @author
 */
public class DetalleCreditoAction extends DispatchAction

{

	private final static String FORWARD = "listaCuotas";
	private final static String SERVLET = "ExportPDF";
	protected Logger logger = Logger.getLogger(this.getClass());

	public ActionForward cargarDetalle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();

		try {
			
			/*****************************nuevo********************************/
			UsuarioVO usuarioActual = (UsuarioVO)sesion.getAttribute("datosUsuario");
			if(usuarioActual == null){
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
			/*****************************fin nuevo********************************/
			
			
			logger.debug("<< Ingreso a DetalleCreditoVigente");
			sesion.setAttribute("codError", "0");
			String folio = request.getParameter("folio_contrato");
			String fechaOtorgamiento = request.getParameter("fecha_otorgamiento");
			sesion.setAttribute("folio_contrato", folio);
			request.setAttribute("rut", sesion.getAttribute("rut"));
			request.setAttribute("nombre", sesion.getAttribute("nombre"));
			
			
			/*****************************nuevo sin ajax desde getDetalleCreditoVigenteAction********************************/
			SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
			SalidaCreditoVigenteVO credito = buscarCredito(folio, request);
			String flagCredito = credito.getFlagTipoCredito();
			
			System.out.println("el tipo de creidot es "+flagCredito);
			if(flagCredito.equals("0")){
				logger.debug("Ingreso al metodo cargarDetalleAs400 con el folio: "+folio);
				salidaVO = ServicioCreditosVigentes.obtenerDetalleConsultaCreditosPorRutEnAs400(folio, fechaOtorgamiento);
				
			}else
				if(flagCredito.equals("1")){			
				logger.debug("Ingreso al metodo cargarDetalleSAP con el folio: "+folio);
				salidaVO = ServicioCreditosVigentes.obtenerDetalleCreditosVigentesBanking(folio);
			}
			
			
			sesion.setAttribute("listaCuotas", salidaVO.getListaCuotas());
			request.setAttribute("opcion", "1");//para saber en que metodo entrar en la pagina datosCredito.jsp
			request.setAttribute("listaCuotas", salidaVO.getListaCuotas());
			request.setAttribute("codError", salidaVO.getCodigoError());
		    request.setAttribute("msg", salidaVO.getMensaje());
		    sesion.setAttribute("cuotasPendientes", String.valueOf(salidaVO.getCuotasPendientes()));
		    logger.debug("Codigo salida: "+salidaVO.getCodigoError()+", Mensaje:"+salidaVO.getMensaje());
			
		    sesion.setAttribute("credito", credito);
			request.setAttribute("credito", credito);
			
			/*****************************fin nuevo********************************/
			
			forward = mapping.findForward(FORWARD);
			logger.debug(">> Salida DetalleCreditoVigente");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("title", "Error: ");
			request.setAttribute("error", e.getMessage());
			return mapping.findForward("customError");
		}
		return (forward);
	}
    
    @SuppressWarnings("unchecked")
	public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	logger.debug("<< Entro a imprimirReporte.");
    	HttpSession sesion = request.getSession();
    	
    	try {
			
	    	String rut = (String) sesion.getAttribute("rut");
	    	String nombreCompleto = (String) sesion.getAttribute("nombre");
	    	
	    	if (sesion.getAttribute("datosUsuario") == null) {
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
	    		    	
	    	if(rut.length() == 0 || nombreCompleto.length() == 0){
	    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
	    	}
	    	
	    	List<SalidaDetalleCreditoVigenteVO> listaCuotas = (List<SalidaDetalleCreditoVigenteVO>) sesion.getAttribute("listaCuotas");
	    	String cuotasPendientes = (String)sesion.getAttribute("cuotasPendientes");
	    	
	    	List<SalidaDetalleCreditoVigenteVO> datos = new ArrayList<SalidaDetalleCreditoVigenteVO>();
	    	datos.add(new SalidaDetalleCreditoVigenteVO(null,null,0,null, 0,null,null));
	    	
	    	if(listaCuotas!=null && listaCuotas.size()!=0){
	    		datos.addAll(listaCuotas);
	    	}else{
	    		datos.add(null);
	    	}
	    	
	    	//Setear valores para almacenar en archivo at02f2
	        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
	        ValorValidableVO valor = new ValorValidableVO();
	        valor.setVariable("Cantidad de cuotas vigentes");
	        valor.setValor(String.valueOf(listaCuotas.size()));
	        listaValores.add(valor);
	               
	    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_VIGENTE);
	    	
	    
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rut", rut);
	    	hash.put("cuotasPendientes", cuotasPendientes);
	    	hash.put("nombreCompleto", nombreCompleto);
	    	hash.put("folio_contrato", sesion.getAttribute("folio_contrato"));
	    	
	    	SalidaCreditoVigenteVO credito = (SalidaCreditoVigenteVO) sesion.getAttribute("credito");
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.titulo"));
	    	
	    	hash.put("montoSolicitado",credito.getMontoSolicitado());
	    	hash.put("fechaOtorgamiento", credito.getFechaOtorgamiento());
	    	
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("codValidacion", codValidacion);
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.firma"));
	    	
			/**
			 * REQ-7962 Integración de servicios Banking con kiosco.
			 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
			 */
			if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
				//Eliminar item 0
				datos.remove(0);
				request.setAttribute("datos", datos);
				request.setAttribute("hash", hash);
				RequestDispatcher rd = request.getRequestDispatcher("../../imprimirCertCuotas.jsp");
				rd.forward(request, response);
				return null;
			}else{
		    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditos.detalle.vigente.jasper");
		    	logger.debug("Set correcto datos reporte.");
		    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
		    	byte[] bites = ru.exportCompilePdf();
		    	logger.debug("Reporte Creado Exitosamente.");
				request.setAttribute("bites", bites);
				request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.nombre"));
				
		    	//Almacenar certificado en Ftp
				FtpUtil ftpUtil = new FtpUtil();
				ftpUtil.subirArchivoURLFtp(bites, rut+CertificadoConst.TIPO_CERT_CRED_VIGENTE+codValidacion+".pdf");
				ftpUtil.desconectar();
			}
			// Fin modificación
			logger.debug(">> Salida a servlet Reporte.");
    	} catch (Exception e) {
    		logger.error("Error al generar el reporte: ", e);
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    		request.setAttribute("message", Constants.REPORT_DATA_ERROR);
    		return mapping.findForward("customError");
		}
		
        return mapping.findForward(SERVLET);
    }
    
    @SuppressWarnings("unchecked")
  	private SalidaCreditoVigenteVO buscarCredito(String folio, HttpServletRequest request) throws Exception{
      	HttpSession sesion = request.getSession();
      	
  		List<SalidaCreditoVigenteVO> lista = ((List<SalidaCreditoVigenteVO>) sesion.getAttribute("creditosVigentes"));
      	
      	for (SalidaCreditoVigenteVO detalle : lista) {
  			if(detalle.getFolio().equals(folio)){
  				return detalle;
  			}
  		}
      	return null;
      }
}
