package cl.araucana.cotcarserv.main.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;

/**
 * @version 	1.0
 * @author
 */
public class ComprobanteCertificadoAction extends DispatchAction

{


	private final static String SERVLET = "ExportPDF";
	protected Logger logger = Logger.getLogger(this.getClass());
    
    @SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	logger.info("<< Entro a generar PDF.");
    	ActionForward forward = new ActionForward(); // return value
    	Map<String, String> listamap=null;
    	HttpSession sesion = request.getSession();
    	String rutEmpresa="";
    	String razonSocial="";
    	try {
    		request.setAttribute("menu", "certificado");
    		String tipo= request.getParameter("tipo");
    		boolean repetido= sesion.getAttribute("repetido")==null ?false:true;
    		String accion = request.getParameter("accion");
    		String usuario= (String)sesion.getAttribute("usuario");
    		List lista= (List)sesion.getAttribute("certificado_PDF");
  
    		if(lista==null ){
    			forward = mapping.findForward("init");
    			return forward;
    		}

    		if(accion!= null && accion.equals("volver")){
    			forward = mapping.findForward("seleccion");
    			return forward;
    		}
    		
    		CotizacionesVO dataTra= (CotizacionesVO)lista.get(1);
			rutEmpresa= dataTra.getRutEmpresa() + "-" + dataTra.getDvEmpresa();
			razonSocial= dataTra.getRazonSocial();
			
			//Si es Masivo se cambian fechas
			if(tipo.equals("M") && !repetido){ 
				for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
					CotizacionesVO cotVO = (CotizacionesVO) iterator.next();
					if(cotVO.getRutTrabajador()!=0){
						cotVO.setFechaAfiliacion(Utils.pasaFechaSAPaWEB(cotVO.getFechaAfiliacion()));
						cotVO.setFechaDesvinculacion(Utils.pasaFechaSAPaWEB(cotVO.getFechaDesvinculacion()));
					}
				}
			}
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", rutEmpresa);
	    	hash.put("nombreEmpresa", razonSocial);
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("fechaEmision", Utils.fechaWeb());
	    	
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.titulo"));
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.firma"));
	    	
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	
	    	ReporteUtil ru = new ReporteUtil(lista, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.nombre"));
			sesion.setAttribute("repetido", "Si");
			//Insert Bitácora
			//insertBitacora(usuario, dataTra);
		
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
