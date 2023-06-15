package cl.laaraucana.satelites.certificados.creditovigente.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.creditovigente.ServicioCreditosVigentes;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

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
			String rut=(String) sesion.getAttribute("rut");
			String nombre= usuarioActual.getNombre();
			
			String uc = request.getParameter("uc");
			if(uc!=null){
				sesion.setAttribute("uc", uc);
			}
					
			logger.info("<< Ingreso a DetalleCreditoVigente");
			sesion.setAttribute("codError", "0");
			String folio = request.getParameter("folio_contrato");
			String fechaOtorgamiento = request.getParameter("fecha_otorgamiento");
			sesion.setAttribute("folio_contrato", folio);
			request.setAttribute("rut", rut);
			request.setAttribute("nombre", nombre);
			
			
			/*****************************nuevo sin ajax desde getDetalleCreditoVigenteAction********************************/
			SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
			SalidaCreditoVigenteVO credito = buscarCredito(folio, rut, request);
			
			String flagCredito = credito.getFlagTipoCredito();
			
			logger.info("el tipo de credido es "+flagCredito);
			logger.info("Ingreso al metodo cargarDetalleSAP con el folio: "+folio);
			salidaVO = ServicioCreditosVigentes.obtenerDetalleCreditosVigentesBanking(folio);
			
			
			sesion.setAttribute("listaCuotas", salidaVO.getListaCuotas());
			request.setAttribute("opcion", "1");//para saber en que metodo entrar en la pagina datosCredito.jsp
			request.setAttribute("listaCuotas", salidaVO.getListaCuotas());
			request.setAttribute("insolvencia", credito.getInsolvencia());
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
    	
    	HttpSession sesion = request.getSession();
    	
    	try {
			
	    	String rut = (String) sesion.getAttribute("rut");
	    	
	    	String folioContrato= (String)sesion.getAttribute("folio_contrato");
	    	if(folioContrato==null){
	    		folioContrato= request.getParameter("folio_contrato");
	    	}
	    	
	    	logger.info("<< Generar Certificado rut:" + rut + ", folio:" + folioContrato);
	    	
	    	UsuarioVO usuarioActual = (UsuarioVO)sesion.getAttribute("datosUsuario");
			if(usuarioActual == null){
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
	    	String nombreCompleto = usuarioActual.getNombre();
	    	
	    	if(rut.length() == 0 || nombreCompleto.length() == 0){
	    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
	    	}
	    	
	    	List<SalidaDetalleCreditoVigenteVO> listaCuotas = (List<SalidaDetalleCreditoVigenteVO>) sesion.getAttribute("listaCuotas");
	    	String cuotasPendientes = (String)sesion.getAttribute("cuotasPendientes");
	    	SalidaCreditoVigenteVO credito = (SalidaCreditoVigenteVO) sesion.getAttribute("credito");
	    	
	    	if(listaCuotas==null){
	    		credito = buscarCredito(folioContrato, rut, request);
	    		SalidaListaDetalleCreditoVigenteVO listaDetalle= ServicioCreditosVigentes.obtenerDetalleCreditosVigentesBanking(folioContrato);
	    		listaCuotas= listaDetalle.getListaCuotas();
	    		cuotasPendientes= String.valueOf(listaDetalle.getCuotasPendientes());
	    	}
	    	
	    	
	    	
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
	        valor.setVariable("Folio credito");
	        valor.setValor(folioContrato);
	        listaValores.add(valor);
	        
	        valor = new ValorValidableVO();
	        valor.setVariable("Cantidad de cuotas vigentes");
	        valor.setValor(String.valueOf(listaCuotas.size()));
	        listaValores.add(valor);
	        
	        //Se setea datos ejecutivo generó certificado
	        User userInfo = (User) sesion.getAttribute("userInfo");
	        if(userInfo!=null){
	        	valor = new ValorValidableVO();
	        	valor.setVariable("Ejecutivo");
	        	valor.setValor(userInfo.getFirstName() + " " + userInfo.getLastName() + ", RUT: " + userInfo.getID());
	        	listaValores.add(valor);
	        }
	        logger.info("Guardar Certificado para Validación");
	    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_VIGENTE);
	    	
	    	//Guardar certificado en SQLServer
	    	BitacoraVO bitacora= new BitacoraVO();
	    	bitacora.setIdCertificado(codValidacion);
	    	bitacora.setFolio(folioContrato);
	    	bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_CRED_VIGENTE_CUOTAS));
	    	bitacora.setRutDeudor(rut);
	    	bitacora.setNombreDeudor(nombreCompleto);
	    	bitacora.setTotalPagar(0);
	    	bitacora.setRutUsuario("");
    		bitacora.setNombreUsuario("");
    		if(userInfo!=null){
    			bitacora.setRutUsuario(userInfo.getID());
    			bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
    		}
    		logger.info("Guardar Certificado en Bitacora para Auditoria");
	    	BitacoraDAO.insertaBitacora(bitacora);
	    	
	    	
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rut", rut);
	    	hash.put("cuotasPendientes", cuotasPendientes);
	    	hash.put("nombreCompleto", nombreCompleto);
	    	hash.put("folio_contrato", folioContrato);
	    	
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.titulo"));
	    	
	    	hash.put("montoSolicitado",credito.getMontoSolicitado());
	    	hash.put("fechaOtorgamiento", credito.getFechaOtorgamiento());
	    	
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("codValidacion", codValidacion);
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.firma"));
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditos.detalle.vigente.jasper");
	    	
	    	logger.info("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
	    	
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
	
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.detalle.nombre"));
			
			
			//Almacenar certificado en Ftp
			FtpUtil ftpUtil = new FtpUtil();
			ftpUtil.subirArchivoURLFtp(bites, rut+CertificadoConst.TIPO_CERT_CRED_VIGENTE+codValidacion+".pdf");
			ftpUtil.desconectar();
			
			logger.info(">> Salida a servlet Reporte.");
		
    	} catch (Exception e) {
    		logger.warn("Error al generar el reporte: "+ e.getMessage());
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    		request.setAttribute("message", Constants.REPORT_DATA_ERROR);
    		return mapping.findForward("customError");
		}
		
        return mapping.findForward(SERVLET);
    }
    
    @SuppressWarnings("unchecked")
  	private SalidaCreditoVigenteVO buscarCredito(String folio, String rut, HttpServletRequest request) throws Exception{
      	HttpSession sesion = request.getSession();
      	
  		List<SalidaCreditoVigenteVO> lista = ((List<SalidaCreditoVigenteVO>) sesion.getAttribute("creditosVigentes"));
      	if( lista==null){
      		
      		SalidaListaCreditoVigenteVO salidaSAP = ServicioCreditosVigentes.obtenerCreditosVigentesBanking(rut, ServiciosConst.CREDITOS_CONDEUDA);
      		lista = new ArrayList<SalidaCreditoVigenteVO>();
      		lista .addAll(salidaSAP.getListaCreditos());
      	}
      	for (SalidaCreditoVigenteVO detalle : lista) {
  			if(detalle.getFolio().equals(folio)){
  				return detalle;
  			}
  		}
      	return null;
      }
}
