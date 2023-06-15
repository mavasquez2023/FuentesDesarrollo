
package cl.laaraucana.simulacion.actions.simuladorrepro;

import java.util.Date;
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

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.simulacion.VO.ParametrosSimulacionVO;
import cl.laaraucana.simulacion.VO.ResultadoAcuerdo;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

/**
 * @version 	1.0
 * @author
 */
public class GeneraPDFReproAction extends DispatchAction

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

    		ResultadoSim salidaVO= (ResultadoSim) sesion.getAttribute("resultado");
    		ParametrosSimulacionVO paramVO= salidaVO.getParamEntrada();
    		
	    	Map<String, Object> hash = new HashMap<String, Object>();
	    	hash.put("rutCliente", paramVO.getRutAfiliado());
	    	hash.put("nombreCliente", paramVO.getNombreAfiliado());
	    	hash.put("tipoCliente", paramVO.getTipoAfiliado());
	    	hash.put("contrato", paramVO.getContrato());
	    	hash.put("tipoProducto", paramVO.getProductoReprogramacion());
	    	hash.put("mesesGracia", Integer.parseInt(paramVO.getMesesGracia()));
	    	hash.put("montoAdeudado", new Double(salidaVO.getMontoAdeudado()));
	    	hash.put("plazo",  Integer.parseInt(paramVO.getPlazo()));
	    	hash.put("interesMensual",  new Double(salidaVO.getTasaMensual()));
	    	hash.put("interesAnual", new Double(salidaVO.getTasaAnual()));
	    	hash.put("seguroDesgravamen", new Double(salidaVO.getSegDesg()));
	    	hash.put("seguroCesantia", new Double(salidaVO.getSegCes()));
	    	hash.put("dctoGravamenes", new Double(salidaVO.getGrvCondonado()));
	    	hash.put("dctoGastosCobranza", new Double(salidaVO.getGcCondonado()));
	    	hash.put("dctoIntereses", new Double(salidaVO.getIntCondonado()));
	    	hash.put("montoAbono", new Double(paramVO.getMontoAbono()));
	    	hash.put("rentaCliente", new Double(paramVO.getRenta()));
	    	hash.put("rentaCLiente", new Double(paramVO.getRenta()));
	    	hash.put("ptjeMaximoEndeudamiento", new Double(salidaVO.getPorcentajeMaximoEndeudamiento()));
	    	hash.put("ptjeEndeudamientoSimulado", new Double(salidaVO.getPorcentajeEndeudamientoSimulado()));
	    	hash.put("valorCuota",  new Double(salidaVO.getMontoCuota()));
	    	hash.put("cargaAnualEquivalente",  new Double(salidaVO.getCae()));
	    	hash.put("costoTotalCredito",  new Double(salidaVO.getCostoTotal()));
	    	hash.put("fechaPrimerVencimiento",  salidaVO.getFechaPrimerVencimiento());
	    	hash.put("fechaEmision", new Date());

	    	
	    	//cabecera PDF
	    	hash.put("titulo", ConstantesFormalizar.PDF_REPRO_TITULO);
	    	hash.put("imgPath", ConstantesFormalizar.PDF_SIMULACION_PATH_FIRMA);
	    	hash.put("firma", ConstantesFormalizar.PDF_SIMULACION_FIRMA);

	    	String ruta= ConstantesFormalizar.PDF_REPRO_JASPER;
	    	logger.debug("Set correcto datos reporte.");
	    	ReporteUtil ru = new ReporteUtil(null, hash, ruta);
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.info("Reporte Creado Exitosamente.");
			request.setAttribute("bites", bites);
			request.setAttribute("nombreArchivo", ConstantesFormalizar.PDF_REPRO_NOMBRE_SALIDA);


		
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
