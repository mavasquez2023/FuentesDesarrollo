package cl.laaraucana.satelites.certificados.creditocancelado.actions;

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
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;

/**
 * @version 	1.0
 * @author
 */
public class SeleccionCreditoAction extends DispatchAction

{
	private final static String FORWARD = "cargaCredito";
	private final static String SERVLET = "ExportPDF";
	protected Logger logger = Logger.getLogger(this.getClass());
	
    public ActionForward cargarCredito(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionForward forward = new ActionForward(); // return value
	HttpSession sesion = request.getSession();
	
	try {
		UsuarioVO usuario = (UsuarioVO)sesion.getAttribute("datosUsuario");
	} catch (Exception e) {
		request.setAttribute("title", "Error: ");
		request.setAttribute("message", "Se ha terminado la sesión");
		return mapping.findForward("customError");
	}
	
	try {
		logger.debug("<< Ingreso a SeleccionCreditoCancelado");	
		SalidaCreditoCanceladoVO credito = buscarCredito(request.getParameter("folio"), request);
		logger.debug("Busqueda de credito OK");
		
		request.setAttribute("rut", sesion.getAttribute("rut"));
		request.setAttribute("nombre", sesion.getAttribute("nombre"));
		
		if(credito!=null){
			request.setAttribute("codError", "0");
		}else{
			request.setAttribute("codError", "1");
		}
		
		sesion.setAttribute("credito", credito);
		request.setAttribute("credito", credito);
		logger.debug("Seteo de requests OK");
		forward = mapping.findForward(FORWARD);

	} catch (Exception e) {
		logger.debug("Error: " + e.getMessage());
		request.setAttribute("title", "Error: ");
		request.setAttribute("message", "Error inesperado, intente nuevamente");
		return mapping.findForward("customError");
	}
	logger.debug(">> Salida SeleccionCreditoCancelado");
	return (forward);

    }
    
    @SuppressWarnings("unused")
	public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	HttpSession sesion = request.getSession();
    	logger.debug("<< Entro a imprimirReporte.");
    	
    	SalidaCreditoCanceladoVO creditoCancelado = (SalidaCreditoCanceladoVO) sesion.getAttribute("credito");
    	String nombreCompleto = (String) sesion.getAttribute("nombre");
    	
    	if(creditoCancelado!=null){		

	    	String rut = (String) sesion.getAttribute("rut");
	    	
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

	    	List<SalidaCreditoCanceladoVO> datos = new ArrayList<SalidaCreditoCanceladoVO>();
	    	datos.add(new SalidaCreditoCanceladoVO(null, 0, null, null, 0, null));
	    	
	    	if(creditoCancelado!=null){
	    		datos.add(creditoCancelado);
	    	}else{
	    		datos.add(null);
	    	}	    
	    	
	    	//Setear valores para almacenar en archivo at02f2
	        Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
	        ValorValidableVO valor = new ValorValidableVO();
	        valor.setVariable("Cantidad de creditos vigentes");
	        valor.setValor("1");
	        listaValores.add(valor);
	       
	    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_CRED_VIGENTE);
	    	   	
	    	
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rut", rut);
	    	hash.put("nombreCompleto", nombreCompleto);
	    	
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("codValidacion", codValidacion);
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.firma"));
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.jasper");
	    	
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
	    	
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.debug("Reporte Creado Exitosamente.");
	
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditoscancelados.nombre"));
			
			logger.debug(">> Salida a servlet Reporte.");
	    	
			return mapping.findForward(SERVLET);
			
    	}else{
    		return Utils.returnErrorForward(mapping, new Exception("No existe credito"), this.getClass());
    	}
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
