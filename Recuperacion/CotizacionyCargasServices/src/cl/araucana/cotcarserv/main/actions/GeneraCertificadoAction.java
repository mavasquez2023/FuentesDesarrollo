package cl.araucana.cotcarserv.main.actions;

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


import cl.araucana.cotcarserv.dao.VO.CotizacionesVO;
import cl.araucana.cotcarserv.dao.VO.EmpresaVO;
import cl.araucana.cotcarserv.dao.VO.ParamVO;
import cl.araucana.cotcarserv.main.dao.ConsultaServicesDAO;
import cl.araucana.cotcarserv.main.forms.ConsultaCotizacionForm;
import cl.araucana.cotcarserv.servlets.EmpresasLDAP;
import cl.araucana.cotcarserv.utils.CertificadoConst;
import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;

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
    	
    	
    	try {
    		request.setAttribute("menu", "certificado");
    		if(sesion.getAttribute("empresas")==null || sesion.getAttribute("rol")==null){
    			forward = mapping.findForward("init");
    			return forward;
    		}
    		
    		String accion= request.getParameter("accion");
    		String rutEmpresa= request.getParameter("rutEmpresa");
    		if(accion==null || accion.equals("menu") || rutEmpresa==null || rutEmpresa.equals("")){
    			forward = mapping.findForward("seleccion");
    			return forward;
    		}
    		
    		Map<String, String> indiceEmpresas= (TreeMap<String, String>)sesion.getAttribute("empresas");
    		String razonSocial= indiceEmpresas.get(rutEmpresa);
    		request.setAttribute("rutEmpresa", rutEmpresa);
			request.setAttribute("razonSocial", razonSocial);
			
			String usuario= (String)sesion.getAttribute("usuario");
    		String rutTrabajador= request.getParameter("rutTrabajador");
    		
    		if (rutTrabajador == null || rutTrabajador.equals("")) {
				return mapping.findForward("success");
			}else{
				rutTrabajador= rutTrabajador.replaceAll("\\.", "");
			}
    		
    		int rutemp_aux= Integer.parseInt(rutEmpresa.split("-")[0]);
    		int ruttra_aux= Integer.parseInt(rutTrabajador.split("-")[0]);
    		
    		ConsultaServicesDAO consultaDAO= new ConsultaServicesDAO();
			logger.info("Consultando Cargas Trabaajador");
			ParamVO param= new ParamVO();
			param.setRutEmpresa(rutemp_aux);
			param.setRutTrabajador(ruttra_aux);
			
			CotizacionesVO dataTra= consultaDAO.consultaCertificadoTrabajador(param);
			if(dataTra==null){
				request.setAttribute("error", "-1");
				return mapping.findForward("success");
			}
	    	logger.info("Rut:" + rutEmpresa);
	    	dataTra.setRutEmpresa(rutemp_aux);
	    	dataTra.setDvEmpresa(rutEmpresa.split("-")[1]);
	    	dataTra.setRazonSocial(razonSocial);

	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", rutEmpresa);
	    	hash.put("nombreEmpresa", dataTra.getRazonSocial());
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("fechaEmision", Utils.fechaWeb());
	    	
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.titulo"));
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.firma"));
	    	
	    	List<CotizacionesVO> lista= new ArrayList<CotizacionesVO>();
	    	lista.add(new CotizacionesVO());
	    	lista.add(dataTra);
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(lista, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.cotcarserv.nombre"));
			
			//Insert Bitácora
			insertBitacora(usuario, dataTra);
		
			logger.debug(">> Salida a servlet Reporte.");
    	} catch (Exception e) {
    		logger.error("Error al generar el reporte: ", e);
    		request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
    		request.setAttribute("message", Constants.REPORT_DATA_ERROR);
    		return mapping.findForward("customError");
		}

        return mapping.findForward(SERVLET);
    }
    
    public void insertBitacora(String usuario, CotizacionesVO traVO){
    	try {
			ConsultaServicesDAO dao= new ConsultaServicesDAO();
			Map param= new HashMap();
			param.put("usuario", usuario);
			param.put("accion", "DESCARGA");
			param.put("rutTrabajador", traVO.getRutTrabajador());
			param.put("dvTrabajador", traVO.getDvTrabajador());
			param.put("rutEmpresa", traVO.getRutEmpresa());
			param.put("dvEmpresa", traVO.getDvEmpresa());
			param.put("fechaAfiliacion", traVO.getFechaAfiliacion());
			param.put("fechaAfiliacion", traVO.getFechaAfiliacion());
			param.put("fechaDesvinculacion",traVO.getFechaDesvinculacion());
			param.put("origen", "E");
			int ok_bita= dao.insertBitacora(param);
			logger.info("Descarga registrado en bitácora: " + ok_bita);
		} catch (Exception e) {
			logger.warn(">>Error al registrar en bitácora descarga del PDF");
		}
    }
}
