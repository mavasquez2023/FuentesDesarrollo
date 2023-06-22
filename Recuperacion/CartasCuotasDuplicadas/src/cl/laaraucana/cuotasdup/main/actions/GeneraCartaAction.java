package cl.laaraucana.cuotasdup.main.actions;

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

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.main.dao.ConsultaTrabajadoresDAO;
import cl.laaraucana.cuotasdup.utils.ParamConfig;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;

/**
 * @version 	1.0
 * @author
 */
public class GeneraCartaAction extends DispatchAction

{


	private final static String SERVLET = "ExportPDF";
	protected Logger logger = Logger.getLogger(this.getClass());
    
    @SuppressWarnings("unchecked")
	public ActionForward imprimirReporte(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	logger.info("<< Entro a generar PDF.");
    	HttpSession sesion = request.getSession();
    	
    	try {
			
	    
	    	String rutEmpresa= (String)sesion.getAttribute("rutEmpresa");
	    	logger.info("Rut:" + rutEmpresa);
	    	String nombreEmpresa= (String)sesion.getAttribute("nombreEmpresa");
	    	String sucursal= (String)sesion.getAttribute("sucursal");
	    	String oficina= (String)sesion.getAttribute("oficina");
	    	String nomOficina= (String)sesion.getAttribute("nomOficina");
	    	List<CuotaVO> trabajadores= (List<CuotaVO>)sesion.getAttribute("trabajadores");
	    	String fechaEmision= (String)sesion.getAttribute("fechaEmision");
	    	String fechaCreacion= (String)sesion.getAttribute("fechaCreacion");
			
	    	if (rutEmpresa == null) {
				request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.SESION_EXPIRED);
				return mapping.findForward("customError");
			}
	    		    	
	    	if(rutEmpresa.length() == 0){
	    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				request.setAttribute("message", Constants.REPORT_DATA_ERROR);
				return mapping.findForward("customError");
	    	}
	    	

	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", rutEmpresa);
	    	hash.put("nombreEmpresa", nombreEmpresa);
	    	hash.put("sucursal", sucursal);
	    	hash.put("oficina", nomOficina);
	    	hash.put("fechaEmision", fechaEmision);
	    	hash.put("fechaCreacion", fechaCreacion);
	   
	    	hash.put("titulo", ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.titulo"));
	    	hash.put("imgPath", ParamConfig.RES_CONFIG.getString("certificados.imgPath"));
	    	hash.put("firma", ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.firma"));
	    	
	    	String ruta= ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(trabajadores, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", ParamConfig.RES_CONFIG.getString("certificado.cuotasdup.carta.nombre"));
			
			//Insert Bitácora
			ConsultaTrabajadoresDAO dao= new ConsultaTrabajadoresDAO();
			Map param= new HashMap();
			param.put("accion", "DESCARGA");
			param.put("rutEmpresa", rutEmpresa);
			param.put("oficina", oficina);
			param.put("sucursal", sucursal);
			dao.insertBitacora(param);
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
    
  
}
