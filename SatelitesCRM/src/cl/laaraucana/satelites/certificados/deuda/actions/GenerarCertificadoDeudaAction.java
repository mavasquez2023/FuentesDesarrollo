package cl.laaraucana.satelites.certificados.deuda.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.prepago.Utils.PrepagoUtil;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.deuda.forms.ListadoCreditosForm;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;

/**
 * @version 	1.0
 * @author
 */
public class GenerarCertificadoDeudaAction extends Action

{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	//private final static String FORWARD = "cargaListaCredito";
	private final static String SERVLET = "ExportPDF";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {

	ActionMessages errors = new ActionMessages();
	ActionForward forward = new ActionForward(); // return value
	HttpSession sesion = request.getSession();

	
	//List<SalidaCreditoPrepagoVO> listaCreditoSocial = null;
	//List<SalidaCreditoPrepagoVO> listaCreditoCes = null;
	//List<SalidaCreditoPrepagoVO> listaCreditoEspecial = null;
//	List<SalidaCreditoPrepagoVO> datos = null;
	List<SalidaCreditoPrepagoVO> listaPrepago = null;
	//List<SalidaCreditoPrepagoVO> listaCes = null;
	//List<SalidaCreditoPrepagoVO> listaEspecial = null;
	PrepagoUtil prepagoUtil = new PrepagoUtil();

	try {

		logger.debug("<< Ingreso a Action GenerarCertificadoDeuda");
		CertificadoPrepagoVO certificadoPrepago = (CertificadoPrepagoVO) sesion.getAttribute("certificadoDeuda");
		//SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = (SalidaListaCreditoPrepagoFoliosVO) sesion.getAttribute("listaCreditoPrepagoFoliosVO");
		SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = new SalidaListaCreditoPrepagoFoliosVO();
		//clillo 27-09-2017 se deja única tabla
		//List<SalidaDetalleCuotasEarlyPayOff2> pensionadosList = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		//List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		
		
		//List<SalidaDetalleCuotasEarlyPayOff2> pensionadosList = (List<SalidaDetalleCuotasEarlyPayOff2>) sesion.getAttribute("pensionadosList");
		//List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = (List<SalidaDetalleCuotasEarlyPayOff2>) sesion.getAttribute("trabajadoresList");
		ListadoCreditosForm creditosForm = (ListadoCreditosForm) form;
		
		if(certificadoPrepago == null || certificadoPrepago.getRut() == null || certificadoPrepago.getNombreCompleto() == null || certificadoPrepago.getRut().length()==0 || certificadoPrepago.getNombreCompleto().length()==0 || certificadoPrepago.getFechaAdmisibilidad()==null || certificadoPrepago.getFechaAdmisibilidad().length()==0 ){
			//Cuando faltan datos para llenar el certificado
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
		}
		
		String[] folios = creditosForm.getPrepago();
		
		List<SalidaCreditoPrepagoVO> listaCreditos = certificadoPrepago.getListaCreditos();
		boolean isVencido= true;
		int totalaPagar=0;
		if (folios!=null) {
			logger.debug("<<Crea lista de folios seleccionados");
			listaPrepago = new ArrayList<SalidaCreditoPrepagoVO>();
			String fechaUltimaCuota="";
			for (String string : folios) {
				for (SalidaCreditoPrepagoVO salidaCreditoPrepagoVO : listaCreditos) {
					if (salidaCreditoPrepagoVO.getFolio().equals(string)) {
						listaPrepago.add(salidaCreditoPrepagoVO);
						fechaUltimaCuota= salidaCreditoPrepagoVO.getHastaCuota();
						totalaPagar+= salidaCreditoPrepagoVO.getTotal();
						break;
					}
				}
			}
			
			listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditosDeudaConFolios(listaPrepago, true);
			//clillo 27-09-2017 se deja única tabla
			isVencido= contratoVencido(fechaUltimaCuota);
			//pensionadosList = prepagoUtil.getDetalleCuotasTrabajadorIndependiente(listaPrepago, false);
			//trabajadoresList = prepagoUtil.getDetalleCuotasTrabajador(listaPrepago);
			/*
			listaCreditoSocial= new ArrayList<SalidaCreditoPrepagoVO>();
			listaCreditoCes = new ArrayList<SalidaCreditoPrepagoVO>();
	    	listaCreditoEspecial = new ArrayList<SalidaCreditoPrepagoVO>();
	    	logger.debug("<<Separa lista en tipo de Creditos");
			listaCreditoSocial = prepagoUtil.getCreditosPrepagoSocialesList(listaPrepago);
			listaCreditoCes = prepagoUtil.getCreditosPrepagoCesList(listaPrepago);
			listaCreditoEspecial = prepagoUtil.getCreditosPrepagoEspecialList(listaPrepago);
			*/
			double total = totalaPagar;
			System.out.println("MONTO Total a Pagar : "+total);
	
//			certificadoPrepago.setCreditoSocial(listaCreditoSocial);
//			certificadoPrepago.setCreditoCes(listaCreditoCes);
//			certificadoPrepago.setCreditoEspecial(listaCreditoEspecial);
			certificadoPrepago.setSumaTotal(total);


	    	/*logger.debug("<<Traspasa Creditos a listas Fianles");
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
    
		}
		
		String rut = certificadoPrepago.getRut();
    	String nombreCompleto = certificadoPrepago.getNombreCompleto();
    	
		Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
        ValorValidableVO valor1 = new ValorValidableVO();
        valor1.setVariable("Cantidad de Creditos");
        valor1.setValor(String.valueOf(listaPrepago.size()));
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
        */
        ValorValidableVO valor4 = new ValorValidableVO();
        valor4.setVariable("Total a Pagar");
        valor4.setValor("$ " + String.valueOf(Utils.formateaDobleSinDecimal(certificadoPrepago.getSumaTotal())));
        listaValores.add(valor4);
        
               
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_LIQ_DEUDA);
		logger.debug("<< Entro a imprimirReporte.");
    	Map<String, Object> hash = new HashMap<String, Object>();
//    	hash.put("folio", certificadoPrepago.getFolio());
    	
    	hash.put("nombreCertificado", "Certificado de Liquidación Deuda (Ley 20.720)");
    	hash.put("logo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosdeuda.logo"));
    	hash.put("nombre", certificadoPrepago.getNombreCompleto());
    	hash.put("rut", certificadoPrepago.getRut());
    	hash.put("fechaAdmisibilidad", certificadoPrepago.getFechaAdmisibilidad());
    	//hash.put("MMYY1", certificadoPrepago.getFechaMMYY());
    	//hash.put("listaCes", listaCes);		  	
    	//hash.put("listaEspecial", listaEspecial);
    	hash.put("listaCreditoPrepagoFolios", listaCreditoPrepagoFoliosVO.getSalidaList());
    	//hash.put("fechasFuturasPagoAfi", trabajadoresList);
    	//clillo 27-09-2017 se deja única tabla
    	//hash.put("fechasFuturasPagoPen", pensionadosList);
    	//SalidaCreditoPrepagoFoliosVO totales = listaCreditoPrepagoFoliosVO.getTotales();
    	//hash.put("DDMM", certificadoPrepago.getFechaDDMM());
    	//hash.put("saldo", Utils.formateaDobleSinDecimal(certificadoPrepago.getSumaTotal()));
    	hash.put("fechaCreacion", "Fecha emisión "+Utils.dateToString2(new Date()));
    	//hash.put("fechaCreacion", certificadoPrepago.getFechaCompleta());
    	hash.put("codValidacion", codValidacion);
    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosdeuda.firma"));
    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
    	
		/**
		 * REQ-7962 Integración de servicios Banking con kiosco.
		 * Detecta que es invocado desde kiosco y envía PDF a imprimir directamente a impresora por defecto.
		 */
		if(request.getParameter("origen") != null && request.getParameter("origen").equals("kiosco")){
			request.setAttribute("datos", listaCreditoPrepagoFoliosVO.getSalidaList());
			request.setAttribute("hash", hash);
			RequestDispatcher rd = request.getRequestDispatcher("../certificadoPrepago/imprimirCertPrepago.jsp");
			rd.forward(request, response);
			return null;
		}else{
			//Se verifica si tiene cuotas morosas para determinar la plantilla a usar
	    	String plantilla = "certificado.creditosdeuda.jasper";
	    	//String plantilla = "certificado.creditosprepago.jasper";
	        String ruta = CertificadoConst.RES_CERTIFICADOS.getString(plantilla);
	        
	    	ReporteUtil ru = new ReporteUtil(listaCreditoPrepagoFoliosVO.getSalidaList(), hash, ruta);
	    	logger.debug("Set correcto datos reporte.");
	    	
	    	byte[] bites = ru.exportCompilePdf();
	    	logger.debug("Reporte Creado Exitosamente.");
	    	request.setAttribute("bites", bites);
	    	request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosdeuda.nombre"));
	    	logger.debug(">> Salida a servlet Reporte.");
	    	
			//Almacenar certificado en Ftp
			FtpUtil ftpUtil = new FtpUtil();
			ftpUtil.subirArchivoURLFtp(bites, certificadoPrepago.getRut()+CertificadoConst.TIPO_CERT_LIQ_DEUDA+codValidacion+".pdf");
			ftpUtil.desconectar();
		}
		// Fin modificación
    	
		forward = mapping.findForward(SERVLET);
	} catch (Exception e) {

	    // Report the error using the appropriate name and ID.
	    errors.add("name", new ActionMessage("id"));
	    e.printStackTrace();
	}

	// Finish with
	return (forward);
    }
	
	//formato fechaUltimaCuota "yyyy-MM-dd"
	//clillo 27-09-2017 
			private boolean contratoVencido(String fechaUltimaCuota){
				int dias=0;
				try {
					System.out.println("Fecha Ultima Cuota:" + fechaUltimaCuota );
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaActual = new Date(); 
					Date fechaHasta=dateFormat.parse(fechaUltimaCuota);
		 
					dias = (int) ((fechaActual.getTime()-fechaHasta.getTime())/86400000);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
				System.out.println("Hay "+dias+" dias de vencimiento");
				if(dias>0){
					return true;
				}
				return false;
			}
}
