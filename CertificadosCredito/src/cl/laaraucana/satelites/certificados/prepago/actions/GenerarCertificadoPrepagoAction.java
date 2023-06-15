package cl.laaraucana.satelites.certificados.prepago.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.ibm.ObjectQuery.crud.util.Array;

import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.core.registry.User;
import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.ReporteUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.Utils.ftp.FtpUtil;
import cl.laaraucana.satelites.certificados.prepago.Utils.PrepagoUtil;
import cl.laaraucana.satelites.certificados.prepago.VO.CertificadoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoFoliosVO;
import cl.laaraucana.satelites.certificados.prepago.forms.ListadoCreditosForm;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.certificados.utils.CertificadoUtils;
import cl.laaraucana.satelites.dao.BitacoraDAO;
import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;

/**
 * @version 	1.0
 * @author
 */
public class GenerarCertificadoPrepagoAction extends Action

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

	List<SalidaCreditoPrepagoVO> datos = null;
	List<SalidaCreditoPrepagoVO> listaPrepago = new ArrayList<SalidaCreditoPrepagoVO>();
	PrepagoUtil prepagoUtil = new PrepagoUtil();

	try {

		CertificadoPrepagoVO certificadoPrepago = (CertificadoPrepagoVO) sesion.getAttribute("certificadoPrepago");
		logger.info("<< Ingreso a Generar CertificadoPrepago rut:" + certificadoPrepago.getRut());
		SalidaListaCreditoPrepagoFoliosVO listaCreditoPrepagoFoliosVO = new SalidaListaCreditoPrepagoFoliosVO();
		//clillo 27-09-2017 se deja única tabla
		List<SalidaDetalleCuotasEarlyPayOff2> trabajadoresList = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		
		ListadoCreditosForm creditosForm = (ListadoCreditosForm) form;
		
		if(certificadoPrepago == null || certificadoPrepago.getRut() == null || certificadoPrepago.getNombreCompleto() == null || certificadoPrepago.getRut().length()==0 || certificadoPrepago.getNombreCompleto().length()==0){
			//Cuando faltan datos para llenar el certificado
			request.setAttribute("title", Constants.REPORT_DATA_ERROR_TITLE);
			request.setAttribute("message", Constants.REPORT_DATA_ERROR);
			return mapping.findForward("customError");
		}
		
		String folio = creditosForm.getPrepago()[0];
		
		List<SalidaCreditoPrepagoVO> listaCreditos = certificadoPrepago.getListaCreditos();
		
		//se reinician valores condicionados
		certificadoPrepago.setCumpleAcuerdoPago(false);
		certificadoPrepago.setPrepagoCuoton(false);
		certificadoPrepago.setMontoCondonacion(0);
		
		for (Iterator iterator = listaCreditos.iterator(); iterator.hasNext();) {
			SalidaCreditoPrepagoVO salidaCreditoPrepagoVO = (SalidaCreditoPrepagoVO) iterator
					.next();
			if(salidaCreditoPrepagoVO.getFolio().equals(folio)){
				String fechaUltimaCuota=salidaCreditoPrepagoVO.getHastaCuota();
				listaPrepago.add(salidaCreditoPrepagoVO);
				double total = salidaCreditoPrepagoVO.getTotal();
				//double total = salidaCreditoPrepagoVO.getSaldoCapital() + salidaCreditoPrepagoVO.getGravamenes() + salidaCreditoPrepagoVO.getMontoInteresDevengado();
				
				boolean cumpleAP= salidaCreditoPrepagoVO.getSaldoCapitalCondonado()>0?true:false;
				certificadoPrepago.setCumpleAcuerdoPago(cumpleAP);
				
				if(salidaCreditoPrepagoVO.getEstado().equals(Constants.CREDITO_CASTIGADO)){
					listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditoAcuerdoPagoPDF(listaPrepago, cumpleAP);
				}else if(salidaCreditoPrepagoVO.getTipoCredito().equals(Constants.PREPAGO_CUOTON)){
					listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditoCutonPDF(listaPrepago);
					certificadoPrepago.setPrepagoCuoton(true);
					certificadoPrepago.setMontoCondonacion(salidaCreditoPrepagoVO.getSaldoCapitalCondonado());
				}else{
					listaCreditoPrepagoFoliosVO = prepagoUtil.getCreditoPrepagoPDF(listaPrepago);
					
				}
				//Se verifica si contrato está vencido, independiente del estado del crédito ya que podría ser castigado y vencido
				certificadoPrepago.setVencido(contratoVencido(fechaUltimaCuota));
				//isVencido= salidaCreditoPrepagoVO.getEstado().equals(CREDITO_VENCIDO)?true:false;
				trabajadoresList = prepagoUtil.getDetalleCuotasTrabajador(listaPrepago);
				certificadoPrepago.setSumaTotal(total);
				certificadoPrepago.setMontoCuotasTransito(Utils.formateaDobleSinDecimal(salidaCreditoPrepagoVO.getMontoCuotasEntransito()));
				certificadoPrepago.setRolPagador(salidaCreditoPrepagoVO.getRol() + " " + salidaCreditoPrepagoVO.getPagador());
				certificadoPrepago.setFolio(salidaCreditoPrepagoVO.getFolio());
				//certificadoPrepago.setCastigo(salidaCreditoPrepagoVO.getCastigo());
				//certificadoPrepago.setCastigo(salidaCreditoPrepagoVO.getEstado().equals(CREDITO_CASTIGADO)?"X":"");
				
				/*if(Integer.parseInt(salidaCreditoPrepagoVO.getCuotasMorosas())>0){
					certificadoPrepago.setMoroso("X");
				}*/
				//certificadoPrepago.setMoroso(salidaCreditoPrepagoVO.getEstado().equals(CREDITO_MOROSO)?"X":"");
				
				/*if(salidaCreditoPrepagoVO.getSaldoCapitalCondonado()>0){
					certificadoPrepago.setCumpleAcuerdoPago("X");
				}*/
				
				certificadoPrepago.setEstado(salidaCreditoPrepagoVO.getEstado());
			}
		}

		
		String rut = certificadoPrepago.getRut();
    	String nombreCompleto = certificadoPrepago.getNombreCompleto();
    	//String folio=certificadoPrepago.getListaCreditos().get(0).getFolio();
    	
		Collection<ValorValidableVO> listaValores = new ArrayList<ValorValidableVO>();
	    
		//Guardar parámetros adicionales para Validación Certificado
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
        logger.info("Guardar Certificado Prepago para Validación");
    	String codValidacion = CertificadoUtils.guardarCertificado(nombreCompleto, rut, listaValores, CertificadoConst.TIPO_CERT_PREPAGO);
    	
    	//Guardar certificado en SQLServer
    	BitacoraVO bitacora= new BitacoraVO();
    	bitacora.setIdCertificado(codValidacion);
    	bitacora.setFolio(folio);
    	bitacora.setTipoCertificado(String.valueOf(CertificadoConst.TIPO_CERT_PREPAGO));
    	bitacora.setRutDeudor(rut);
    	bitacora.setNombreDeudor(nombreCompleto);
    	bitacora.setRutUsuario("");
		bitacora.setNombreUsuario("");
		if(userInfo!=null){
			bitacora.setRutUsuario(userInfo.getID());
			bitacora.setNombreUsuario(userInfo.getFirstName() + " " + userInfo.getLastName());
		}
    	bitacora.setTotalPagar(Integer.parseInt(totalapagar.replaceAll("\\.", "")));
    	logger.info("Guardar Certificado Prepago para Audotoria (SQLServer)");
    	BitacoraDAO.insertaBitacora(bitacora);
    	
    	Map<String, Object> hash = new HashMap<String, Object>();

    	if(certificadoPrepago.isVencido()&& !certificadoPrepago.isPrepagoCuoton()){
    		hash.put("nombreCertificado", "Certificado de Deuda");
    	}else{
    		hash.put("nombreCertificado", "Certificado de Prepago de Crédito (Ley 20.130)");
    	}

    	hash.put("logo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.logo"));
    	hash.put("nombre", certificadoPrepago.getNombreCompleto());
    	hash.put("rut", certificadoPrepago.getRut());
    	hash.put("rolPagador", certificadoPrepago.getRolPagador());
    	hash.put("folio", certificadoPrepago.getFolio());
    	hash.put("montoCuotasTransito", certificadoPrepago.getMontoCuotasTransito());

    	hash.put("listaCreditoPrepagoFolios", listaCreditoPrepagoFoliosVO.getSalidaList());
    	hash.put("fechasFuturasPagoAfi", trabajadoresList);
    	hash.put("fechaCreacion", "Fecha emisión "+Utils.dateToString2(new Date()));
    	//hash.put("fechaCreacion", certificadoPrepago.getFechaCompleta());
    	hash.put("codValidacion", codValidacion);
    	hash.put("firma", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.firma"));
    	hash.put("imgPath", CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath"));
    	
    	//Se verifica si tiene cuotas morosas o castigo para determinar la plantilla a usar
    	String plantilla ="";
    	if(certificadoPrepago.getEstado().equals(Constants.CREDITO_CASTIGADO)){
    		plantilla= "certificado.creditosprepago.acuerdo.jasper";
    		if(certificadoPrepago.isCumpleAcuerdoPago()){
    			hash.put("textoNota2", "El Saldo Capital a condonar corresponde al monto acordado a extinguir, debido al cumplimiento integro y oportuno en el pago de las cuotas pactadas en el acuerdo.");
    			hash.put("textoNota3", " menos el Saldo Capital a condonar");
    		}else{
    			hash.put("textoNota2", "El Saldo Capital Cuotas Futuras considera el monto acordado a extinguir, debido al incumplimiento en el pago de las cuotas pactadas en el acuerdo.");
    			hash.put("textoNota3", "");
    		}
    	}else if(certificadoPrepago.isPrepagoCuoton()){
    		plantilla= "certificado.creditosprepago.cuoton.jasper";
    		if(certificadoPrepago.getMontoCondonacion()>0){
    			hash.put("textoNota2", "El Saldo Capital a condonar corresponde al monto acordado a extinguir, debido al cumplimiento integro y oportuno en el pago de las cuotas pactadas en el acuerdo.");
    			hash.put("textoNota3", "El Total a pagar corresponde al Total del Saldo Adeudado, Interés Devengado, Saldo Capital Cuotas Futuras y Comisión de Prepago menos el saldo Capital a Condonar.");
    		}else{
    			hash.put("textoNota2", "El Saldo Capital Cuotas Futuras considera el monto acordado a extinguir, debido al incumplimiento en el pago de las cuotas pactadas en el acuerdo.");
    			hash.put("textoNota3", "El Total a pagar corresponde al Total del Saldo Adeudado, Interés Devengado, Saldo Capital Cuotas Futuras y Comisión de Prepago.");
    		}
    	}else{
    		if(certificadoPrepago.getEstado().equals(Constants.CREDITO_MOROSO) || certificadoPrepago.getEstado().equals(Constants.CREDITO_VENCIDO)){
    			plantilla= "certificado.creditosprepago.moroso.jasper";
    		}else{
    			plantilla= "certificado.creditosprepago.vigente.jasper";
    		}
    	}
    	//plantilla = ((SalidaCreditoPrepagoFoliosVO)listaCreditoPrepagoFoliosVO.getSalidaList().get(13)).getValorFolio1().equals("0") ? "certificado.creditosprepago.vigente.jasper" : "certificado.creditosprepago.moroso.jasper";
    	//String plantilla = "certificado.creditosprepago.jasper";
        String ruta = CertificadoConst.RES_CERTIFICADOS.getString(plantilla);
    	
    	ReporteUtil ru = new ReporteUtil(listaCreditoPrepagoFoliosVO.getSalidaList(), hash, ruta);
    	logger.info("Set correcto datos reporte.");
    	
    	byte[] bites = ru.exportCompilePdf();
    	logger.info("Reporte Creado Exitosamente.");
    	
		request.setAttribute("bites", bites);
		request.setAttribute("nombreArchivo", CertificadoConst.RES_CERTIFICADOS.getString("certificado.creditosprepago.nombre"));
		logger.info(">> Salida a servlet Reporte.");
		
		//Almacenar certificado en Ftp
		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.subirArchivoURLFtp(bites, certificadoPrepago.getRut()+CertificadoConst.TIPO_CERT_PREPAGO+codValidacion+".pdf");
		ftpUtil.desconectar();
		
		
		forward = mapping.findForward(SERVLET);
	} catch (Exception e) {

	    // Report the error using the appropriate name and ID.
	    errors.add("name", new ActionMessage("id"));
	    e.printStackTrace();
	}

	// Finish with
	return (forward);
    }
	
	//formato fechaMorosidad "yyyy-MM-dd"
	/*private boolean contratoMoroso(int diasMorosidad, String fechaMorosidad){
		int dias=0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaActual = new Date(); 
			Date fechaMora=dateFormat.parse(fechaMorosidad);
 
			dias = (int) ((fechaActual.getTime()-fechaMora.getTime())/86400000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		System.out.println("Hay "+dias+" dias de morosidad");
		if(dias>90){
			return true;
		}
		return false;
	}*/
	
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
