package cl.laaraucana.satelites.certificados.creditocancelado.actions;

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

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.creditocancelado.ServicioCreditosCancelados;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaDetalleCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaListaDetalleCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.finiquito.utils.UtilPrint;
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
		logger.debug("<< Ingreso a DetalleCreditoCancelado");

		ActionForward forward = new ActionForward(); // return value
		HttpSession sesion = request.getSession();

		try {
			
			/*****************************nuevo********************************/
			UsuarioVO usuarioActual = (UsuarioVO)sesion.getAttribute("datosUsuario");
			if(usuarioActual == null){
				request.setAttribute("title", "Error al generar el certificado: ");
				request.setAttribute("message", "Se ha terminado la sesión");
				return mapping.findForward("customError");
			}
			/*****************************fin nuevo********************************/
			
			sesion.setAttribute("codError", "0");
			String folio = request.getParameter("folio_contrato");
			logger.debug("FOLIO A CONSULTAR: " + folio);
			sesion.setAttribute("folio_contrato", folio);
			request.setAttribute("rut", sesion.getAttribute("rut"));
			request.setAttribute("nombre", sesion.getAttribute("nombre"));
			
			
			/*****************************nuevo desde GetDetalleCreditoCanceladoAction********************************/
			
			SalidaListaDetalleCreditoCanceladoVO salidaVO= new SalidaListaDetalleCreditoCanceladoVO();
			SalidaCreditoCanceladoVO credito = buscarCredito(folio, request);
			String flagCredito = credito.getFlagTipoCredito();
			
			if(flagCredito.equals("0")){
				//folio="002-002049481";//TODO borrar el folio dummy
				logger.debug("Ingreso al metodo cargarDetalleAs400 con el folio: "+folio);
				salidaVO = ServicioCreditosCancelados.obtenerDetalleConsultaCreditosPorRutEnAs400(folio);
				
			}else
				if(flagCredito.equals("1")){
					//folio= "103000000022";//TODO descomentar folio dummy
					folio = folio.replace("-", "");
					logger.debug("Ingreso al metodo cargarDetalleSAP con el folio: "+folio);
					salidaVO = ServicioCreditosCancelados.obtenerDetalleCreditosCanceladosBanking(folio);
				}
			
			logger.debug(">> Salida llamada de nuestro clienteCuotasCreditoCancelado");
		    request.setAttribute("codError", salidaVO.getCodigoError());
		    request.setAttribute("msg", salidaVO.getMensaje());
			sesion.setAttribute("listaCuotas", salidaVO.getListaCuotas());
			request.setAttribute("opcion", "1");//para saber en que metodo entrar en la pagina datosCredito.jsp
			request.setAttribute("listaCuotas", salidaVO.getListaCuotas());
		    logger.debug("Codigo salida: "+salidaVO.getCodigoError()+", Mensaje:"+salidaVO.getMensaje());
		    
		    sesion.setAttribute("credito", credito);
			request.setAttribute("credito", credito);
			
		    /*****************************fin nuevo********************************/
			
			

			forward = mapping.findForward(FORWARD);
			logger.debug(">> Salida DetalleCreditoCancelado");
		} catch (Exception e) {
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
	    	List<SalidaDetalleCreditoCanceladoVO> listaCuotas = ((List<SalidaDetalleCreditoCanceladoVO>) sesion.getAttribute("listaCuotas"));
	    	
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
	    	
	    	List<SalidaDetalleCreditoCanceladoVO> datos = new ArrayList<SalidaDetalleCreditoCanceladoVO>();
	    	datos.add(new SalidaDetalleCreditoCanceladoVO(null,null,null,null,null,0,null));
	    	
	    	if(listaCuotas!=null && listaCuotas.size()!=0){
	    		datos.addAll(listaCuotas);
	    	}else{
	    		datos.add(null);
	    	}
	    	
	    	//Setear valores para almacenar en archivo at02f2
	        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
	        ValorValidableVO valor = new ValorValidableVO();
	        valor.setVariable("Cantidad de cuotas canceladas");
	        valor.setValor(String.valueOf(listaCuotas.size()));
	        listaValores.add(valor);
	               
	    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_CANCELADO_CUOTAS);
	    	
	    	    	
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rut", rut);
	    	hash.put("nombreCompleto", nombreCompleto);
	    	hash.put("folio_contrato", sesion.getAttribute("folio_contrato"));
	    	
	    	SalidaCreditoCanceladoVO credito = (SalidaCreditoCanceladoVO) sesion.getAttribute("credito");
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancalados.detalle.titulo"));
	    	
	    	hash.put("montoSolicitado",credito.getMontoSolicitado());
	    	hash.put("fechaOtorgamiento", credito.getFechaOtorgamiento());
	    	
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("codValidacion", codValidacion);
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.detalle.firma"));
	    	
	  
	    	
			/**
			 * REQ-7962 Integración de servicios Banking con kiosco.
			 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
			 */
			if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
				datos.remove(0);
				request.setAttribute("datos", datos);
				request.setAttribute("hash", hash);
				RequestDispatcher rd = request.getRequestDispatcher("../../imprimirCertCuotas.jsp");
				rd.forward(request, response);
				return null;
			}else{
			  	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditos.detalle.jasper");
		    	logger.debug("Set correcto datos reporte.");
		    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
		    	byte[] bites = ru.exportCompilePdf();
		    	logger.debug("Reporte Creado Exitosamente.");
				request.setAttribute("bites", bites);
				request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.detalle.nombre"));
				
		    	//Almacenar certificado en Ftp
				FtpUtil ftpUtil = new FtpUtil();
				ftpUtil.subirArchivoURLFtp(bites, rut+CertificadoConst.TIPO_CERT_CRED_CANCELADO+codValidacion+".pdf");
				ftpUtil.desconectar();
			}
			// Fin modificación

			logger.debug(">> Salida a servlet Reporte.");
		
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.debug("Error al generar el reporte: "+ e.getMessage());
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    		request.setAttribute("message", Constants.REPORT_DATA_ERROR_TITLE);
    		return mapping.findForward("customError");
		}
        return mapping.findForward(SERVLET);
    }
    
    private SalidaCreditoCanceladoVO buscarCredito(String folio, HttpServletRequest request){
    	HttpSession sesion = request.getSession();
    	
    	@SuppressWarnings("unchecked")
		List<SalidaCreditoCanceladoVO> lista = ((List<SalidaCreditoCanceladoVO>) sesion.getAttribute("creditosCancelados"));
    	
    	for (SalidaCreditoCanceladoVO detalle : lista) {
			if(detalle.getFolio().equals(folio)){
				return detalle;
			}
		}
    	return null;
    }
}
