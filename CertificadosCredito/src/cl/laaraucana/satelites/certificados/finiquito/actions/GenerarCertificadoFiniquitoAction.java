package cl.laaraucana.satelites.certificados.finiquito.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;

/**
 * @version 	1.0
 * @author
 */
public class GenerarCertificadoFiniquitoAction extends Action

{

	protected Logger logger = Logger.getLogger(this.getClass());
	private final static String SERVLET = "ExportPDF";
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionForward forward = new ActionForward(); // return value

	try {
		//List<SalidaCreditoPrepagoVO> listaCreditoSocial = null;
		//List<SalidaCreditoPrepagoVO> listaCreditoCes = null;
		//List<SalidaCreditoPrepagoVO> listaCreditoEspecial = null;
		//List<SalidaCreditoPrepagoVO> datos = null;
		//List<SalidaCreditoPrepagoVO> listaCes = null;
		//List<SalidaCreditoPrepagoVO> listaEspecial = null;
		//PrepagoUtil prepagoUtil = new PrepagoUtil();
		
		HttpSession sesion = request.getSession();
		CertificadoPrepagoVO certificadoFiniquito = (CertificadoPrepagoVO)sesion.getAttribute("certificadoFiniquito");
		
		logger.debug("<< Ingreso a Action GenerarCertificadoPrepago");
		SalidaListaCreditoPrepagoFoliosVO listaCreditoFinVO = (SalidaListaCreditoPrepagoFoliosVO) sesion.getAttribute("listaCreditoFinFoliosVO");
		
		
		List<SalidaDetalleCuotasEarlyPayOff2> pensionadosList = (List<SalidaDetalleCuotasEarlyPayOff2>) sesion.getAttribute("pensionadosList");
		List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = (List<SalidaDetalleCuotasEarlyPayOff2>) sesion.getAttribute("trabajadoresList");
		
		if(certificadoFiniquito == null || certificadoFiniquito.getRut() == null || certificadoFiniquito.getNombreCompleto() == null || certificadoFiniquito.getRut().length()==0 || certificadoFiniquito.getNombreCompleto().length()==0){
			//Cuando faltan datos para llenar el certificado
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
		}
		
		String rut = certificadoFiniquito.getRut();
    	String nombreCompleto = certificadoFiniquito.getNombreCompleto();
    	
		Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
        ValorValidableVO valor = new ValorValidableVO();
        valor.setVariable("Cantidad de Creditos Sociales");
        valor.setValor(String.valueOf(certificadoFiniquito.getListaCreditos().size()));
        listaValores.add(valor);
        
        for (int i = 0; i < certificadoFiniquito.getListaCreditos().size(); i++) {
        	//Folio
        	valor = new ValorValidableVO();
			valor.setVariable("Folio credito "+(i+1));
			valor.setValor(certificadoFiniquito.getListaCreditos().get(i).getFolio());
			listaValores.add(valor);
			//Total a Pagar
			valor = new ValorValidableVO();
			valor.setVariable("Total a Pagar");
		    String totalapagar= Utils.formateaDobleSinDecimal(certificadoFiniquito.getListaCreditos().get(i).getTotal());
		    valor.setValor("$" + totalapagar);
		    listaValores.add(valor);
		} 
     
        
      //Se setea datos ejecutivo generó certificado
        User userInfo = (User) sesion.getAttribute("userInfo");
        if(userInfo!=null){
        	valor = new ValorValidableVO();
        	valor.setVariable("Ejecutivo");
        	valor.setValor(userInfo.getFirstName() + " " + userInfo.getLastName() + ", RUT: " + userInfo.getID());
        	listaValores.add(valor);
        }   
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_FINIQUITO);
    	
    	for (int i = 0; i < certificadoFiniquito.getListaCreditos().size(); i++) {
    		//Guardar certificado en SQLServer
    		BitacoraVO bitacora= new BitacoraVO();
    		bitacora.setIdCertificado(codValidacion);
    		bitacora.setFolio(certificadoFiniquito.getListaCreditos().get(i).getFolio());
    		bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_FINIQUITO));
    		bitacora.setRutDeudor(rut);
    		bitacora.setNombreDeudor(nombreCompleto);
    		bitacora.setRutUsuario("");
    		bitacora.setNombreUsuario("");
    		if(userInfo!=null){
    			bitacora.setRutUsuario(userInfo.getID());
    			bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
    		}
    		bitacora.setTotalPagar((int)certificadoFiniquito.getListaCreditos().get(i).getTotal());
    		BitacoraDAO.insertaBitacora(bitacora);
    	}
		logger.debug("<< Entro a imprimirReporte.");
    	Map<String, Object> hash = new HashMap<String, Object>();
//    	hash.put("folio", certificadoPrepago.getFolio());
    	hash.put("logo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.logoReducido"));
    	hash.put("nombre", certificadoFiniquito.getNombreCompleto());
    	hash.put("nombreEmpresa", certificadoFiniquito.getNombreEmpresa());
    	hash.put("rut", certificadoFiniquito.getRut());
    	hash.put("tipoAfiliado", certificadoFiniquito.getTipoAfiliado());
    	//hash.put("MMYY1", certificadoFiniquito.getFechaMMYY());
    	//hash.put("listaCes", listaCes);		  	
    	//hash.put("listaEspecial", listaEspecial);
    	hash.put("listaCreditoPrepagoFolios", listaCreditoFinVO.getSalidaList());
    	hash.put("fechasFuturasPagoAfi", trabajadoresList);
    	hash.put("fechasFuturasPagoPen", pensionadosList);
    	//SalidaCreditoPrepagoFoliosVO totales = listaCreditoFinVO.getTotales();
    	//hash.put("DDMM", certificadoFiniquito.getFechaDDMM());
    	//hash.put("saldo", Utils.formateaDobleSinDecimal(certificadoFiniquito.getSumaTotal()));
    	hash.put("fechaCreacion", "Fecha emisión: "+Utils.dateToString2(new Date()));
    	//hash.put("fechaCreacion", certificadoPrepago.getFechaCompleta());
    	hash.put("codValidacion", codValidacion);
    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.firma"));
    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
    	
    	System.out.println("listado es "+listaCreditoFinVO.getSalidaList());
    	String ruta=CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.jasper");
    	ReporteUtil ru = new ReporteUtil(listaCreditoFinVO.getSalidaList(), hash, ruta);
    	logger.debug("Set correcto datos reporte.");
    	
    	byte[] bites = ru.exportCompilePdf();
    	logger.debug("Reporte Creado Exitosamente.");
    	
		
		request.setAttribute("bites", bites);
		request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.finiquito.nombre"));
		
		logger.debug(">> Salida a servlet Reporte.");
		
		//Almacenar certificado en Ftp
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.subirArchivoURLFtp(bites, certificadoFiniquito.getRut()+CertificadoConst.TIPO_CERT_FINIQUITO+codValidacion+".pdf");
		ftpUtil.desconectar();
    	
		forward =  mapping.findForward(SERVLET);

	} catch (Exception e) {
		e.printStackTrace();
		forward = Utils.returnErrorForward(mapping, e, this.getClass());
	}
	

	// Finish with
	return (forward);

    }
    
}
