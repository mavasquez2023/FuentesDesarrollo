package cl.araucana.infcottra.main.actions;

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


import cl.araucana.infcottra.business.CotizacionesTrabajador;
import cl.araucana.infcottra.dao.VO.CotizacionVO;
import cl.araucana.infcottra.dao.VO.ParamVO;
import cl.araucana.infcottra.dao.VO.SalidaVO;
import cl.araucana.infcottra.main.dao.ConsultaCotizacionDAO;
import cl.araucana.infcottra.main.forms.ConsultaCotizacionForm;
import cl.araucana.infcottra.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 	1.0
 * @author
 */
public class GeneraInformeAction extends DispatchAction

{


	private final static String SERVLET = "ExportPDF";
	protected Logger logger = Logger.getLogger(this.getClass());
    
    @SuppressWarnings("unchecked")
	public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	logger.info("<< Entro a generar PDF.");
    	HttpSession sesion = request.getSession();
    	ConsultaCotizacionForm formCRM = (ConsultaCotizacionForm) form;
    	
    	String id="";
    	try {
			SalidaVO salida= (SalidaVO)sesion.getAttribute("salida");
			if(salida==null){
				String rutEmpresa = request.getParameter("rutEmpresa");
				String rutTrabajador = request.getParameter("rutTrabajador");
				CotizacionesTrabajador cotizacionesTra= new CotizacionesTrabajador();
				salida= cotizacionesTra.cotizacionesTrabajador(rutEmpresa, rutTrabajador);
			}
			
    		List<CotizacionVO> listaCotizaciones= salida.getCotizaciones();
    		if (listaCotizaciones == null) {
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
    		
    		CotizacionVO cotizaVO= salida.getParam();
	    	String rutEmpresa= cotizaVO.getRutEmpresa() + "-" + cotizaVO.getDvEmpresa();
	    	logger.info("Rut:" + rutEmpresa);
	    	
	    	
	    	int correlativo= ConsultaCotizacionDAO.getCorrelativo();
			id=CompletaUtil.llenaConCeros(String.valueOf(1), 3, true) + "-" + Utils.fechaSAP() + "_" + CompletaUtil.llenaConCeros(String.valueOf(correlativo), 4, true);

	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", cotizaVO.getRutEmpresa() + "-" + cotizaVO.getDvEmpresa());
	    	hash.put("nombreEmpresa", cotizaVO.getNombreEmpresa());
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("id", id);
	    	
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcottra.titulo"));
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcottra.firma"));
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcottra.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(listaCotizaciones, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcottra.nombre"));
			
			//Insert Bitácora
			insertBitacora(id, rutEmpresa);
		
			logger.debug(">> Salida a servlet Reporte.");
    	} catch (Exception e) {
    		logger.error("Error al generar el reporte: ", e);
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    		request.setAttribute("message", Constants.REPORT_DATA_ERROR);
    		return mapping.findForward("customError");
		}
		
        return mapping.findForward(SERVLET);
    }
    
    public void insertBitacora(String id, String rutEmpresa){
    	try {
			ConsultaCotizacionDAO dao= new ConsultaCotizacionDAO();
			Map param= new HashMap();
			param.put("id", id);
			param.put("accion", "DESCARGA");
			param.put("rutEmpresa", rutEmpresa);
			param.put("oficina","1");
			param.put("sucursal", "1");
			int ok_bita= dao.insertBitacora(param);
			logger.info("Consulta registrado en bitácora: " + ok_bita);
		} catch (Exception e) {
			logger.warn(">>Error al registrar en bitácora descarga del PDF");
		}
    }
}
