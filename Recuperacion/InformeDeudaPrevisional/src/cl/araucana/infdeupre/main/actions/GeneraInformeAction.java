package cl.araucana.infdeupre.main.actions;

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

import cl.araucana.infdeupre.dao.VO.DeudaVO;
import cl.araucana.infdeupre.dao.VO.ParamVO;
import cl.araucana.infdeupre.dao.VO.SalidaVO;
import cl.araucana.infdeupre.main.dao.ConsultaDeudaDAO;
import cl.araucana.infdeupre.main.forms.ConsultaDeudaForm;
import cl.araucana.infdeupre.utils.CertificadoConst;
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
    	ConsultaDeudaForm formCRM = (ConsultaDeudaForm) form;
    	int total=0;
		if(formCRM!=null){
			total = formCRM.getTotal();
		}
    	String id="";
    	try {
			
    		SalidaVO salidaVO= (SalidaVO) sesion.getAttribute("salida");
    		ParamVO paramVO=null;
    		List<DeudaVO> listadeuda= null;
    		if (salidaVO == null) {
    			//se instancia e invoca DAO para obtener deuda empresa
    			ConsultaDeudaDAO deudaDAO= new ConsultaDeudaDAO();
    			int rutemp= Integer.parseInt(request.getParameter("rutEmpresa"));
				int oficina= Integer.parseInt(request.getParameter("oficina"));
				int sucursal= Integer.parseInt(request.getParameter("sucursal"));
				ParamVO paramConsulta= new ParamVO();
				paramConsulta.setRutEmpresa(rutemp);
				paramConsulta.setOficina(oficina);
				paramConsulta.setSucursal(sucursal);
				paramVO= deudaDAO.consultaSucursalDeudaEmpresa(paramConsulta);
				listadeuda= deudaDAO.consultaDeudaEmpresa(paramConsulta);
    		}else{
    			paramVO= salidaVO.getParam();
    			listadeuda= salidaVO.getDeuda();
    		}
    		if(listadeuda.get(0)!=null){
    			listadeuda.add(0, null);
    		}
	    	String rutEmpresa= paramVO.getRutEmpresa() + "-" + paramVO.getDvEmpresa();
	    	logger.info("Rut:" + rutEmpresa);
	    	
	    	int correlativo= ConsultaDeudaDAO.getCorrelativo();
	    	logger.info("correlativo:" + correlativo);
			id=CompletaUtil.llenaConCeros(String.valueOf(paramVO.getOficina()), 3, true) + "-" + Utils.fechaSAP() + "_" + CompletaUtil.llenaConCeros(String.valueOf(correlativo), 4, true);

	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutEmpresa", rutEmpresa);
	    	hash.put("nombreEmpresa", paramVO.getRazonSocial().trim());
	    	hash.put("sucursal", paramVO.getNombreSucursal());
	    	hash.put("oficina", paramVO.getNombreOficina());
	    	hash.put("fechaCreacion", Utils.getFechaCompleta());
	    	hash.put("id", id);
	    	hash.put("total", total);
	    	
	    	DeudaVO totalDeuda= new DeudaVO();
	    	totalDeuda.setCodigo("999");
	    	totalDeuda.setConcepto("TOTAL");
	    	totalDeuda.setMonto(total);
	    	listadeuda.add(totalDeuda);
	    		
	    	hash.put("titulo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infdeupre.titulo"));
	    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
	    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infdeupre.firma"));
	    	
	    	String ruta= CertificadoConst.RES_CERTIFICADOS.getString("certificado.infdeupre.jasper");
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(listadeuda, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.infdeupre.nombre"));
			listadeuda.remove(totalDeuda);
			
			//Insert Bitácora
			ConsultaDeudaDAO dao= new ConsultaDeudaDAO();
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
