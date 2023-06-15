package cl.laaraucana.satelites.certificados.planDePago.actions;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.planDePago.VO.DetallePlanDePagoVO;
import cl.laaraucana.satelites.certificados.prepago.Utils.ServiciosPrepagoSap;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
/*import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.EntradaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.SalidaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;*/

/**
 * @version 	1.0
 * @author
 */
public class GenerarPlanDePagoAction extends Action

{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private final static String SERVLET = "ExportPDF";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	//ActionMessages errors = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value
	//EntradaAfiliadoVO entradaAfiliadoVO = new EntradaAfiliadoVO();
	//EntradaEarlyPayoffSimulationOUT entradaParaEarlyPayOffVO = new EntradaEarlyPayoffSimulationOUT();
	//SalidaAfiliadoVO salidaAfiliadoVO = new SalidaAfiliadoVO();
	//SalidaEarlyPayoffSimulationOUT salidaEarlyPayoffSimulationOUT = new SalidaEarlyPayoffSimulationOUT();

	String rut="13020551-8";//TODO Eliminar RUT MULA
	String fechaHoy = Utils.fechaSAP();
	try {
		
		/*SalidaListaCreditoPrepagoVO listaBanking = ServiciosPrepagoSap.obtenerCreditosVigentesBanking(rut, fechaHoy);
		System.out.println("Algo de vuelta:    "+listaBanking);
//		entradaAfiliadoVO.setRut("13020551-8");//RUT de Banking
//		entradaParaEarlyPayOffVO.setIdContrato("032000000019");
//		entradaParaEarlyPayOffVO.setFechaFullEpo("2006-12-26");
//		//entradaParaEarlyPayOffVO.setFechaFullEpo("2013-07-13"); 2006-12-26
//		
//		ClienteQueryBPStatusOUT clienteAfiliadoBanking = new ClienteQueryBPStatusOUT();
//		salidaAfiliadoVO = (SalidaAfiliadoVO) clienteAfiliadoBanking.call(entradaAfiliadoVO);
//		logger.debug("Rut del Afiliado"+salidaAfiliadoVO.getRut());
//		logger.debug("Rut del Afiliado"+salidaAfiliadoVO.getNombreCompleto());
//
//		ClienteEarlyPayoffSimulationOUT clienteEarlyBanking = new ClienteEarlyPayoffSimulationOUT();
//		salidaEarlyPayoffSimulationOUT = (SalidaEarlyPayoffSimulationOUT)clienteEarlyBanking.call(entradaParaEarlyPayOffVO); 
//		logger.debug("REMAINING_BALANCE: "+salidaEarlyPayoffSimulationOUT.getRemainingBalance());
		
    	logger.debug("<< Entro a imprimirReporte.");
    	/*
    	Map<String, Object> hash = new HashMap<String, Object>();
    	
    	hash.put("folio", "001");
    	hash.put("MMYY1", "MMYY1");
    	hash.put("DDMM", "DDMM");
    	hash.put("saldo", "saldo");
    	hash.put("MMYY2", "MMYY2");
    	hash.put("fechaCreacion", Utils.getFechaCompleta());
    	hash.put("codValidacion", "Pendiente");
    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.firma"));
    	

    	String ruta=CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.jasper");
    	ReporteUtil ru = new ReporteUtil(datos, hash, ruta);
    	logger.debug("Set correcto datos reporte.");
    	
    	byte[] bites = ru.exportCompilePdf();
    	logger.debug("Reporte Creado Exitosamente.");
    	
		request.setAttribute("bites", bites);
		request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosvigentes.nombre"));
    	
		logger.debug(">> Salida a servlet Reporte.");
		*/
    	
    	DetallePlanDePagoVO detalle = new DetallePlanDePagoVO();
    	List<DetallePlanDePagoVO> lista = detalle.llenaPlanDePago(); //Lista Dummy
    	Map<String, Object> hash = null;
    	
    	String ruta = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.planDePago.jasper");
		String nombreArchivo = ResourceBundle.getBundle("cl.laaraucana.satelites.certificados.confCertificados").getString("certificado.planDePago.nombre");
    	
    	ReporteUtil ru = new ReporteUtil(lista, hash, ruta);
    	byte[] bites = ru.exportCompilePdf();
		
		request.setAttribute("bites", bites);
		request.setAttribute("nombreArchivo", nombreArchivo);

		forward =  mapping.findForward(SERVLET);
		
	} catch (Exception e) {
		forward = Utils.returnErrorForward(mapping, e, this.getClass());
	}

	
	return (forward);

    }
}
