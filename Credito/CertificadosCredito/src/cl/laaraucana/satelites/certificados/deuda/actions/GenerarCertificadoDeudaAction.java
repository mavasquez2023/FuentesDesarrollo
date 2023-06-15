package cl.laaraucana.satelites.certificados.deuda.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import cl.araucana.core.registry.User;
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
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
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

	List<SalidaCreditoPrepagoVO> listaPrepago = new ArrayList<SalidaCreditoPrepagoVO>();
	PrepagoUtil prepagoUtil = new PrepagoUtil();

	try {

		logger.debug("<< Ingreso a Action GenerarCertificadoDeuda");
		CertificadoPrepagoVO certificadoPrepago = (CertificadoPrepagoVO) sesion.getAttribute("certificadoDeuda");
		//SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = (SalidaListaCreditoPrepagoFoliosVO) sesion.getAttribute("listaCreditoPrepagoFoliosVO");
		SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = new SalidaListaCreditoPrepagoFoliosVO();
		//clillo 27-09-2017 se deja única tabla
		
		ListadoCreditosForm creditosForm = (ListadoCreditosForm) form;
		
		if(certificadoPrepago == null || certificadoPrepago.getRut() == null || certificadoPrepago.getNombreCompleto() == null || certificadoPrepago.getRut().length()==0 || certificadoPrepago.getNombreCompleto().length()==0 || certificadoPrepago.getFechaAdmisibilidad()==null || certificadoPrepago.getFechaAdmisibilidad().length()==0 ){
			//Cuando faltan datos para llenar el certificado
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
		}
		
		String folio = creditosForm.getPrepago()[0];
		//String liquidacion= (String)request.getSession().getAttribute("liquidacion");
		
		List<SalidaCreditoPrepagoVO> listaCreditos = certificadoPrepago.getListaCreditos();
		boolean isVencido= true;

		for (Iterator iterator = listaCreditos.iterator(); iterator.hasNext();) {
			SalidaCreditoPrepagoVO salidaCreditoPrepagoVO = (SalidaCreditoPrepagoVO) iterator
					.next();
			if(salidaCreditoPrepagoVO.getFolio().equals(folio)){
				String fechaUltimaCuota=salidaCreditoPrepagoVO.getHastaCuota();
				listaPrepago.add(salidaCreditoPrepagoVO);
				//double total = salidaCreditoPrepagoVO.getTotal();
				double total = salidaCreditoPrepagoVO.getSaldoCapital() + salidaCreditoPrepagoVO.getGravamenes() + salidaCreditoPrepagoVO.getMontoInteresDevengado();
				if(certificadoPrepago.getLiquidacion().equals("1")){
					total = salidaCreditoPrepagoVO.getSaldoCapital();
					salidaCreditoPrepagoVO.setLiquidacion(true);
				}
				listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditosDeudaConFolios(listaPrepago, true);
				isVencido= contratoVencido(fechaUltimaCuota);
				certificadoPrepago.setSumaTotal(total);
			}
		}
		
		String rut = certificadoPrepago.getRut();
    	String nombreCompleto = certificadoPrepago.getNombreCompleto();
    	
		Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	        
        ValorValidableVO valor = new ValorValidableVO();
        valor.setVariable("Folio Credito");
        valor.setValor(folio);
        listaValores.add(valor);
        
        valor = new ValorValidableVO();
        valor.setVariable("Total a Pagar");
        String totalapagar= Utils.formateaDobleSinDecimal(certificadoPrepago.getSumaTotal());
        valor.setValor("$ " + totalapagar);
        listaValores.add(valor);
        
        
        
      //Se setea datos ejecutivo generó certificado
        User userInfo = (User) sesion.getAttribute("userInfo");
        if(userInfo!=null){
        	valor = new ValorValidableVO();
        	valor.setVariable("Ejecutivo");
        	valor.setValor(userInfo.getFirstName() + " " + userInfo.getLastName() + ", RUT: " + userInfo.getID());
        	listaValores.add(valor);
        }       
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_LIQ_DEUDA);
    	
    	//Guardar certificado en SQLServer
    	BitacoraVO bitacora= new BitacoraVO();
    	bitacora.setIdCertificado(codValidacion);
    	bitacora.setFolio(folio);
    	bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_LIQ_DEUDA));
    	bitacora.setRutDeudor(rut);
    	bitacora.setNombreDeudor(nombreCompleto);
    	bitacora.setRutUsuario("");
		bitacora.setNombreUsuario("");
		if(userInfo!=null){
			bitacora.setRutUsuario(userInfo.getID());
			bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
		}
    	bitacora.setTotalPagar(Integer.parseInt(totalapagar.replaceAll("\\.", "")));
    	BitacoraDAO.insertaBitacora(bitacora);
    	
    	logger.debug("<< Entro a imprimirReporte.");
		Map<String, Object> hash = new HashMap<String, Object>();
//    	hash.put("folio", certificadoPrepago.getFolio());
    	
    	hash.put("nombreCertificado", "Certificado de Deuda Ley 20.720 Renegociación SIR");
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
			if(certificadoPrepago.getLiquidacion().equals("1")){
				plantilla = "certificado.creditosdeudaliq.jasper";
			}
	    	
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
