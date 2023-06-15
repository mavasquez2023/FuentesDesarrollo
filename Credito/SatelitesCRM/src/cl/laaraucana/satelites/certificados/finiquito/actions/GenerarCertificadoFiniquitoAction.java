package cl.laaraucana.satelites.certificados.finiquito.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
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
		
		/*	List<SalidaCreditoPrepagoVO> listaCreditos = certificadoFiniquito.getListaCreditos();
		List<SalidaCreditoPrepagoVO> listaPrepago = null;
		
		listaPrepago = listaCreditos;
		
		String[] folios = creditosForm.getPrepago();
 
 if (folios!=null) {
			logger.debug("<<Crea lista de folios seleccionados");
			listaPrepago = new ArrayList<SalidaCreditoPrepagoVO>();
			for (String string : folios) {
				for (SalidaCreditoPrepagoVO salidaCreditoPrepagoVO : listaCreditos) {
					if (salidaCreditoPrepagoVO.getFolio().equals(string)) {
						listaPrepago.add(salidaCreditoPrepagoVO);
						break;
					}
				}
			}*/
		
		/*
			listaCreditoSocial= new ArrayList<SalidaCreditoPrepagoVO>();
			listaCreditoCes = new ArrayList<SalidaCreditoPrepagoVO>();
	    	listaCreditoEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
	    	logger.debug("<<Separa lista en tipo de Creditos");
			listaCreditoSocial = prepagoUtil.getCreditosPrepagoSocialesList(listaPrepago);
			listaCreditoCes = prepagoUtil.getCreditosPrepagoCesList(listaPrepago);
			listaCreditoEspecial = prepagoUtil.getCreditosPrepagoEspecialList(listaPrepago);
			double total = prepagoUtil.getSumaTotal();
			System.out.println("MONTO XXXX.XX : "+total);
	*/
//			certificadoPrepago.setCreditoSocial(listaCreditoSocial);
//			certificadoPrepago.setCreditoCes(listaCreditoCes);
//			certificadoPrepago.setCreditoEspecial(listaCreditoEspecial);
			//certificadoFiniquito.setSumaTotal(total);


		/*
	    	logger.debug("<<Traspasa Creditos a listas Fianles");
			if (!listaCreditoSocial.isEmpty()) {//!listaPrepago.isEmpty()
				logger.debug("<<Traspasa Creditos listaCreditoSocial en Lista FinalSocial");
				datos = new ArrayList<SalidaCreditoPrepagoVO>();
				
				
				//DUMMY
				//listaCreditoSocial.get(0).setListaFechaPagar(PrepagoUtil.getSalidaPagarExample());
				
				datos.add(new SalidaCreditoPrepagoVO("", "", "", "", 0, 0, "", 0, 0, 0,"", "", 0));
				datos.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoSocial);
				
				//DUMMY
				//datos.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoSocial);
			}
			
			if (!listaCreditoCes.isEmpty()) {//!listaPrepago.isEmpty()
				logger.debug("<<Traspasa Creditos listaCreditoCes en Lista FinalCES");
				listaCes = new ArrayList<SalidaCreditoPrepagoVO>();
//				listaCes.addAll((List<SalidaCreditoPrepagoVO>) listaPrepago);
				listaCes.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoCes);
			}
			
			//DUMMY
			//listaCes = new ArrayList<SalidaCreditoPrepagoVO>();
			//listaCes.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoSocial);
			
	    	
			if (!listaCreditoEspecial.isEmpty()) {//!listaPrepago.isEmpty()
				logger.debug("<<Traspasa Creditos listaCreditoEspecial en Lista FinalEspecial");
				listaEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
//				listaEspecial.addAll((List<SalidaCreditoPrepagoVO>) listaPrepago);
				listaEspecial.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoEspecial);
			}
			*/
			//DUMMY
			//listaEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
			//listaEspecial.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoSocial);
			//listaEspecial.addAll((List<SalidaCreditoPrepagoVO>) listaCreditoSocial);
    
		//}
		
		String rut = certificadoFiniquito.getRut();
    	String nombreCompleto = certificadoFiniquito.getNombreCompleto();
    	
		Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
        ValorValidableVO valor1 = new ValorValidableVO();
        valor1.setVariable("Cantidad de Creditos Sociales");
        valor1.setValor(String.valueOf(listaCreditoFinVO.getSalidaList().size()));
        listaValores.add(valor1);
        /*
        ValorValidableVO valor2 = new ValorValidableVO();
        valor2.setVariable("Cantidad de Creditos Educacion Superior");
        valor2.setValor(String.valueOf(listaCreditoCes.size()));
        listaValores.add(valor2);
        
        ValorValidableVO valor3 = new ValorValidableVO();
        valor3.setVariable("Cantidad de Creditos Especiales");
        valor3.setValor(String.valueOf(listaCreditoEspecial.size()));
        listaValores.add(valor3);
        
        ValorValidableVO valor4 = new ValorValidableVO();
        valor4.setVariable("Valor Total a Pagar");
        valor4.setValor(String.valueOf(Utils.formateaDobleSinDecimal(certificadoFiniquito.getSumaTotal())));
        listaValores.add(valor4);
        */       
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_FINIQUITO);
    	
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
    	
		/**
		 * REQ-7962 Integración de servicios Banking con kiosco.
		 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
		 */
		if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
			request.setAttribute("datos", listaCreditoFinVO.getSalidaList());
			request.setAttribute("hash", hash);
			RequestDispatcher rd = request.getRequestDispatcher("imprimirCertFin.jsp");
			rd.forward(request, response);
			return null;
		}else{
//	    	System.out.println("listado es "+listaCreditoFinVO.getSalidaList());
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
		}
		// Fin modificación
		forward =  mapping.findForward(SERVLET);
	} catch (Exception e) {
		e.printStackTrace();
		forward = Utils.returnErrorForward(mapping, e, this.getClass());
	}
	

	// Finish with
	return (forward);

    }
}
