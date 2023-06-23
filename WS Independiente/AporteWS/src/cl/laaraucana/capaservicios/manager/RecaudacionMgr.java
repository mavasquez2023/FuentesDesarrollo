package cl.laaraucana.capaservicios.manager;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import cl.laaraucana.config.Constantes;
import cl.laaraucana.sap.client.loancontractpayment.ClienteLoanContrPaymentReq;
import cl.laaraucana.sap.client.loancontractpayment.vo.EntradaLoanPaymentVO;
import cl.laaraucana.sap.client.loancontractpayment.vo.SalidaLoanPaymentVO;
import cl.laaraucana.capaservicios.persistencia.vo.CabeceraRendicion;
import cl.laaraucana.capaservicios.persistencia.vo.DetalleRendicion;
import cl.laaraucana.capaservicios.persistencia.vo.RespuestaVO;
import cl.laaraucana.capaservicios.util.ParametrosApp;
import cl.laaraucana.util.FechaUtil;
import cl.laaraucana.util.NumberUtil;
import cl.laaraucana.util.email.Email;

public class RecaudacionMgr {
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * Realiza la recuperacion de deudores en SAP
	 * @param detalle
	 * @param folioTesoreria
	 * @return
	 */
	public RespuestaVO ejecutarRecuperacionSap(List<DetalleRendicion> detalle,  CabeceraRendicion cabecera){
		
		RespuestaVO resp = null;
		String folioTesoreria = cabecera.getComprobante();
		String mensajes ="";
		int COD=0;
		List<RespuestaVO> listaResultados = new ArrayList<RespuestaVO>();
		
		ClienteLoanContrPaymentReq clienteRecSap = new ClienteLoanContrPaymentReq();
		List<String> errores = new ArrayList<String>();
		if(detalle.size()>0){
			log.debug("*****	INICIO RECUPERACIÓN SAP		*********");
			for (DetalleRendicion registro : detalle) {
				try {
					log.info("Procesar recuperacion para folio: "+registro.getFolioCredito()+"         ");
					
					EntradaLoanPaymentVO entrada = new EntradaLoanPaymentVO();
					//Formatear folio de crédito
					//String folio = registro.getFolioCredito().substring(registro.getFolioCredito().length()-12, registro.getFolioCredito().length());
					//registro.setFolioCredito(folio);
					
					entrada.setItemNumber("1");
					entrada.setPviRut(NumberUtil.quitaCerozIzq(registro.getRut()));
					entrada.setPiType(ParametrosApp.PI_TYPE);
					entrada.setFolioTesoreria(folioTesoreria);
					entrada.setId(registro.getFolioCredito());
					entrada.setInstallment("001");//Nro de cuota TODO
					entrada.setAmount(registro.getMontoPago());//Monto a pagar
					entrada.setCurrency(ParametrosApp.TIPO_MONEDA);//Tipo de moneda
					//entrada.setPostingDate(FechaUtil.pasaFechaAsASap(registro.getFechaContable()));
					//entrada.setValueDate(FechaUtil.pasaFechaAsASap(registro.getFechaPago()));//Fecha de pago
					entrada.setValueDate(FechaUtil.getFechaSAP());//Fecha de pago
					entrada.setPostingDate(FechaUtil.getFechaSAP());
					
					entrada.setOficinaPago(ParametrosApp.OFICINA_PAGO);
					//entrada.setCompExterno(cabecera.getNroOperacion());
					
					//Arrears
					//TODO eliminar por cambio
/*					entrada.setArrearsContabtype(ParametrosApp.CONTAB_TYPE_SAP);
					entrada.setArrearsId(registro.getFolioCredito());
					entrada.setArrearsAmount(registro.getMontoPago());
					entrada.setArrearsCurrency(ParametrosApp.TIPO_MONEDA);
					
					entrada.setArrearsAmountsource(registro.getMontoPago());
					entrada.setArrearsCurrencysource(ParametrosApp.TIPO_MONEDA);
					entrada.setArrearsIscred(ParametrosApp.ES_CREDITO_SAP);*/
					log.debug("Rut: " + entrada.getPviRut());
					log.debug("Folio cred: " + entrada.getId());
					log.debug("Folio Tes: " + entrada.getFolioTesoreria());
					log.debug("Comp. externo: " + entrada.getCompExterno());
					log.debug("Monto Pago: " + entrada.getAmount());
					log.debug("Oficina Pago: " + entrada.getOficinaPago());
					log.debug("Posting Date: " + entrada.getPostingDate() + ", Value Date: " + entrada.getValueDate());
					
					log.info("Ejecutar recuperacion para folio "+registro.getFolioCredito()+" en SAP...");
					SalidaLoanPaymentVO salidaLoan = (SalidaLoanPaymentVO) clienteRecSap.call(entrada);
					
					if(salidaLoan.getCodigoError().equals(Constantes.WS_COD_SUCCESS)){
						log.info("Recuperacion SAP OK, para folio credito: "+registro.getFolioCredito()+", fecha pago: "+registro.getFechaPago());
						registro.setEstadoRecup(ParametrosApp.COD_NOMINA_REC);
						resp = new RespuestaVO(Constantes.WS_COD_SUCCESS, "Recuperacion SAP OK, para folio credito: "+registro.getFolioCredito());
					}else{
						log.info("Ejecutar recuperacion SAP NOK");
						errores.add("Id. rendición: " + registro.getIdDetalleRendicion() + ", Folio: " + registro.getFolioCredito()+", E:" + salidaLoan.getMensaje());
						log.debug("Error al ejecutar recuperación para crédito: " + registro.getFolioCredito()+ ", en fecha pago:  "+registro.getFechaPago()+", mensaje:"+ salidaLoan.getMensaje());
						registro.setEstadoRecup(ParametrosApp.COD_NOMINA_ERROR_REC);
						resp = new RespuestaVO(Constantes.WS_COD_ERROR, "Error al ejecutar recuperación para crédito: " + registro.getFolioCredito()+ ",en fecha pago:  "+registro.getFechaPago()+", mensaje:"+ salidaLoan.getMensaje());
					}
					listaResultados.add(resp);
				} catch (Exception e) {
					registro.setEstadoRecup(ParametrosApp.COD_NOMINA_ERROR_REC);
					log.error("Error al ejecutar recuperación para crédito: " + registro.getFolioCredito() , e);
					resp = new RespuestaVO(Constantes.WS_COD_ERROR, "Excepcion al ejecutar recuperación para crédito: " + registro.getFolioCredito()+", E:"+e.getMessage());
					errores.add("Id. rendición: " + registro.getIdDetalleRendicion() + ", Folio: " + registro.getFolioCredito()+", E:"+e.getMessage());
				}
					
			}
			log.debug("*****	FIN RECUPERACIÓN SAP		*********");
		}else{
			errores.add("Error recaudacion SAP pago deudodes no afiliados. No existe detalle para la rendicion, folio de tesoreria: "+folioTesoreria);
			COD=5;
			mensajes = "Error recaudacion SAP pago deudodes no afiliados. No existe detalle para la rendicion, folio de tesoreria: "+folioTesoreria;
		}
			
		//enviarEmailNotificacion(errores);

		try {
			for (RespuestaVO r : listaResultados) {
				mensajes += (r.getCodigo().equals(Constantes.WS_COD_SUCCESS))? ("<p class='alert alert-success'>"+r.getMensaje()): ("<p class='alert alert-danger'>"+r.getMensaje());
				if(Integer.parseInt(r.getCodigo())> COD){
					COD=Integer.valueOf(r.getCodigo());
				}
			}
		} catch (Exception e) {
			return new RespuestaVO(listaResultados.get(0).getCodigo(), listaResultados.get(0).getMensaje());
		}
		
		resp = new RespuestaVO(String.valueOf(COD), mensajes);

		return resp;
	}
	
	/**
	 * Envia mensaje de notificacion de errores de proceso de recaudacion
	 * @param errores
	 * @return
	 */
	private void enviarEmailNotificacion(List<String> errores){
		if(errores==null || errores.size()==0) return;
		
		Email email = new Email(ParametrosApp.SESION_EMAIL);
		String mensaje = "Estimado(a): <br><br>Se produjeron los siguientes errores de procesamiento: <br><br>";
		mensaje+="<ul>";
		for (String error : errores) {
			mensaje +="<li>"+error+"</li>";
		}
		mensaje+="</ul>";
		try {
			email.sendEmail(ParametrosApp.EMAIL_NOTIF, "Error en proceso de recaudación SAP", mensaje, null);
		} catch (Exception e) {
			log.error("Error al enviar correo de notificación: "+ e.getMessage());
		}
	}
}
