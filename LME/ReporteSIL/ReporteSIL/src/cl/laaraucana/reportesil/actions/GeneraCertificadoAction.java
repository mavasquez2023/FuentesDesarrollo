package cl.laaraucana.reportesil.actions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



import cl.laaraucana.reportesil.dao.ConsultaServicesDAO;
import cl.laaraucana.reportesil.dao.vo.FormularioCalculoSILVO;
import cl.laaraucana.reportesil.dao.vo.ResumenLicenciaVO;
import cl.laaraucana.reportesil.services.CreaReporteService;
import cl.laaraucana.reportesil.services.CreaReporteServiceImpl;
import cl.laaraucana.reportesil.utils.Configuraciones;
import cl.laaraucana.reportesil.utils.EnviaMail;
import cl.laaraucana.reportesil.utils.FormatoMail;
import cl.laaraucana.reportesil.utils.ReporteUtil;
import cl.laaraucana.reportesil.utils.Utils;
import cl.recursos.EnviarMail;

/**
 * @version 	1.0
 * @author
 */
public class GeneraCertificadoAction extends DispatchAction

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
    	String forwardrsp=null;
    	
    	try {
    		List<FormularioCalculoSILVO> listaFormularios=null;
    		CreaReporteService reportService= new CreaReporteServiceImpl();
    		ResumenLicenciaVO resumen= (ResumenLicenciaVO)sesion.getAttribute("licencia");
    		if(resumen.getEstado().equals("AUTORIZADA")){
    			logger.info("Licencia Autorizada, se completan datos de formulario");
    			listaFormularios= reportService.completarFormulario(resumen);
    		}else{
    			FormularioCalculoSILVO fomuSIL= new FormularioCalculoSILVO();
    			fomuSIL.setCabeceraLicencia(resumen);
    			listaFormularios= new ArrayList<FormularioCalculoSILVO>();
    			listaFormularios.add(fomuSIL);
    		}
    		logger.info("Se genera Reporte");
    		if(listaFormularios.size()>0){
    			String rutaPDF= reportService.generarReport(request, response, listaFormularios);

    			logger.info("Ruta PDF: "+ rutaPDF);
    			if(!resumen.getEmail().equals("") && rutaPDF!=null && !rutaPDF.equals("")){
    				String texto= FormatoMail.obtenerTextoMailCliente(resumen.getNombre(), resumen.getFechaDesdeStr());
    				String subject= Configuraciones.getConfig("mail.subject.reportesil");

    				logger.info("Se envía reporte a mail : "+ resumen.getEmail());
    				EnviaMail.enviarMail(subject, resumen.getEmail(), null, texto, rutaPDF);
    			}
    		}else{
    			forwardrsp="sinpagos";
    		}
			logger.debug(">> Salida a servlet Reporte.");
    	} catch (Exception e) {
    		logger.error("Error al generar el reporte: ", e);
    		request.setAttribute("title", "Error al generar el reporte");
    		request.setAttribute("errorMsg", e.getMessage());
    		return mapping.findForward("error");
		}

        return mapping.findForward(forwardrsp);
    }
    
    public void insertBitacora(String usuario, String data){
    	try {
			ConsultaServicesDAO dao= new ConsultaServicesDAO();
			Map param= new HashMap();
			param.put("usuario", usuario);
			param.put("accion", "DESCARGA");
			param.put("origen", "E");
			//int ok_bita= dao.insertBitacora(param);
			logger.info("Descarga registrado en bitácora: ");
		} catch (Exception e) {
			logger.warn(">>Error al registrar en bitácora descarga del PDF");
		}
    }
}
