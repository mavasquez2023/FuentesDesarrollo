package cl.araucana.infcotrec.main.actions;

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


import cl.araucana.infcotrec.dao.VO.PagosVO;
import cl.araucana.infcotrec.dao.VO.ParamVO;
import cl.araucana.infcotrec.dao.VO.SalidaVO;
import cl.araucana.infcotrec.main.dao.ConsultaPagosDAO;
import cl.araucana.infcotrec.main.forms.ConsultaPagosForm;
import cl.araucana.infcotrec.utils.CertificadoConst;
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
    	ConsultaPagosForm formCRM = (ConsultaPagosForm) form;
    	int total=0;
		if(formCRM!=null){
			total = formCRM.getTotal();
		}
    	String id="";
    	try {
			
    		SalidaVO salidaVO= (SalidaVO) sesion.getAttribute("salida");
    		ParamVO paramVO=null;
    		List<PagosVO> listadeuda= null;
    		if (salidaVO == null) {
    			//se instancia e invoca DAO para obtener deuda empresa
    			ConsultaPagosDAO deudaDAO= new ConsultaPagosDAO();
    			int rutemp= Integer.parseInt(request.getParameter("rutEmpresa"));
				int oficina= Integer.parseInt(request.getParameter("oficina"));
				int sucursal= Integer.parseInt(request.getParameter("sucursal"));
				String _periodo= request.getParameter("periodo");
				ParamVO paramConsulta= new ParamVO();
				paramConsulta.setRutEmpresa(rutemp);
				paramConsulta.setOficina(oficina);
				paramConsulta.setSucursal(sucursal);
				paramConsulta.setPeriodo(_periodo);
				paramVO= deudaDAO.consultaSucursalPagosEmpresa(paramConsulta);
				paramVO.setPeriodo(_periodo);
				listadeuda= deudaDAO.consultaPagosEmpresa(paramVO);
				//request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
				//request.setAttribute("message", Constants.SESION_EXPIRED);
				//return mapping.findForward("customError");
			}else{
				paramVO= salidaVO.getParam();
				listadeuda= salidaVO.getPagos();
			}
    		if(listadeuda.get(0)!=null){
    			listadeuda.add(0, null);
    		}
    		int cantMeses= Integer.parseInt(CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.meses"));
    		int meses=cantMeses;
			
	    	String rutEmpresa= paramVO.getRutEmpresa() + "-" + paramVO.getDvEmpresa();
	    	logger.info("Rut:" + rutEmpresa);
	    	
		    	
	    	
	    	int correlativo= ConsultaPagosDAO.getCorrelativo();
			id=CompletaUtil.llenaConCeros(String.valueOf(paramVO.getOficina()), 3, true) + "-" + Utils.fechaSAP() + "_" + CompletaUtil.llenaConCeros(String.valueOf(correlativo), 4, true);

	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", rutEmpresa);
	    	hash.put("nombreEmpresa", paramVO.getRazonSocial().trim());
	    	hash.put("sucursal", paramVO.getNombreSucursal());
	    	hash.put("oficina", paramVO.getNombreOficina());
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("id", id);
	    	hash.put("meses", meses);
	    	
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.titulo"));
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.firma"));
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(listadeuda, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infcotrec.nombre"));
			
			//Insert Bitácora
			ConsultaPagosDAO dao= new ConsultaPagosDAO();
			Map param= new HashMap();
			param.put("id", id);
			param.put("accion", "DESCARGA");
			param.put("rutEmpresa", rutEmpresa);
			param.put("oficina", paramVO.getOficina());
			param.put("sucursal", paramVO.getSucursal());
			int ok_bita= dao.insertBitacora(param);
			logger.info("Consulta registrado en bitácora: " + ok_bita);
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
