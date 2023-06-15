/*package cl.laaraucana.satelites.certificados.creditovigente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.ClienteConsultaCreditosPorRutEnAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.EntradaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaListaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.ClienteConsultaDatosPagosCreditosPorFolioAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.EntradaConsultaDatosPagosCreditosVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaListaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class ServicioCreditosVigentesOld {
	
	protected static Logger logger = Logger.getLogger(ServicioCreditosVigentesOld.class);
	
	*//**
	 * Llamada a servicio de consulta de créditos SAP banking, específicamente para créditos vigentes
	 * @param rut
	 * @return
	 * @throws Exception Si la llamada al servicio o el mapeo no fue exitoso
	 *//*
	public static SalidaListaCreditoVigenteVO obtenerCreditosVigentesBanking(String rut){
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito("1");//1: obtiene creditos vigentes.
		
		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		
		try {
			SalidaListaQueryCompContHeaderInVO respuesta = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO);
			logger.debug("Créditos vigentes: Llamada a banking exitosa.");
			salidaVO = mapearQueryCompContHeaderIn(respuesta);
			logger.debug("Créditos vigentes: Mapeo de datos exitoso.");
			
		} catch (Exception e) {
			//Setea codigo de error salida
			logger.debug("Error al obtener los datos de SAP: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}

		return salidaVO;
	}
	
	
	*//**
	 * @param entrada
	 * @return
	 * @throws Exception si no se pudo realizar correctamente el mapeo de datos
	 *//*
	private static SalidaListaCreditoVigenteVO mapearQueryCompContHeaderIn(SalidaListaQueryCompContHeaderInVO entrada) throws Exception{
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito=null;
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		
		if(entrada.getListaCreditos() == null){
			return salidaVO;
		}
		
		for(SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()){
			credito = new SalidaCreditoVigenteVO();
			
			//credito.setFolio(Utils.formatearFolio(detalle.getContractNumber()));
			credito.setFolio(detalle.getContractNumber());
			credito.setMontoSolicitado(Double.valueOf(detalle.getContractAmount().replace(".", "").replace(",", ".")));
			credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(detalle.getContractBegin()));
			credito.setMontoCuota(Double.valueOf(detalle.getInstallmentAmount().replace(".", "").replace(",", ".")));
			credito.setPlazo(detalle.getInstallmentQuantity());
			credito.setTipoMoneda(detalle.getContractCurrency());//UF o CLP
			credito.setFlagTipoCredito("1");//1: indica que es credito de banking
			
			listaCreditos.add(credito);
		}
				
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}
	
	public static SalidaListaDetalleCreditoVigenteVO obtenerDetalleCreditosVigentesBanking(String folio){
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		SalidaListaDetalleCreditoVigenteVO salidaVO=new SalidaListaDetalleCreditoVigenteVO();
		entradaVO.setFolioCredito(folio);
		
		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		try {			
			
			logger.debug("Obteniendo cuotas vigentes SAP para crédito folio: " + entradaVO.getFolioCredito());
			SalidaListaQueryContractCashflowInVO respuesta = (SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO);
			logger.debug("Llamada a cuotas vigentes correcta");
			
			salidaVO = mapearQueryContractCashflowIn(respuesta);
			logger.debug("Mapeo de cuotas vigentes correcto");
			
		} catch (Exception e) {
			logger.debug("Error al obtener las cuotas vigentes de SAP: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}
		
		return salidaVO;
	}
	
	private static SalidaListaDetalleCreditoVigenteVO mapearQueryContractCashflowIn(SalidaListaQueryContractCashflowInVO entrada) throws Exception{
		
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		SalidaDetalleCreditoVigenteVO cuotas =null;
		List<SalidaDetalleCreditoVigenteVO> listaCuotas = new ArrayList<SalidaDetalleCreditoVigenteVO>();
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		if(entrada.getListaCuotas() == null){
			return salidaVO;
		}
		
		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoVigenteVO();
			
			cuotas.setnCuota(detalle.getNroCuota());
			//Try's para formatear la fecha en formato dd-mm-yyy si falla mostrar la fecha tal cual viene.
			cuotas.setFecVencimiento(detalle.getFechaVencCuota());			
			cuotas.setOficina(detalle.getOficinaPago());
			cuotas.setDocPago(detalle.getFolioPago());
			cuotas.setEstPago(detalle.getEstadoPago());
			cuotas.setTipoMoneda(detalle.getMoneda());
			
			try{				
				cuotas.setMonto(Double.valueOf(detalle.getMontoAbono().replace(".", "").replace(",", ".")));
			}catch (Exception e) {
				throw new Exception("Error al obtener el monto.");
			}
			
			try{
				cuotas.setFecPago(Utils.pasaFechaSAPaWEB(detalle.getUltFechaPago()));
			}catch(Exception e){
				cuotas.setFecPago("");
			}
			
			listaCuotas.add(cuotas);
		}
		
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}
	
	public static SalidaListaCreditoVigenteVO obtenerConsultaCreditosPorRutEnAs400(String rut){
		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO(rut);
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		
		
		try {
			ClienteConsultaCreditosPorRutEnAs400 clienteWs = new ClienteConsultaCreditosPorRutEnAs400();	
			SalidaListaConsultaCreditosPorRutEnAs400VO respuesta = (SalidaListaConsultaCreditosPorRutEnAs400VO) clienteWs.call(entradaVO);
			logger.debug("Llamada al servicio AS400 correcta");
			
			salidaVO = mapearConsultaCreditosPorRutEnAs400(respuesta);			
			//salidaVO = mapearConsultaCreditosPorRutEnAs400();

			
		} catch (Exception e) {
			//Setea codigo de error salida
			logger.debug("Error al obtener los datos de AS400: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}
		
		return salidaVO;	
	}
	
	private static SalidaListaCreditoVigenteVO mapearConsultaCreditosPorRutEnAs400(SalidaListaConsultaCreditosPorRutEnAs400VO entrada) throws ParseException {
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito =null;
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		
		if(entrada.getListaCreditos() == null){
			return salidaVO;
		}
		
		for (SalidaConsultaCreditosPorRutEnAs400VO creditows : entrada.getListaCreditos()) {
			credito = new SalidaCreditoVigenteVO();
			
			if((creditows.getEstadoCredito().trim().toUpperCase().equals("VIGENTE") || 	creditows.getEstadoCredito().trim().toUpperCase().equals("MOROSO")))
			{	
				credito.setFolio(creditows.getFolioCredito());
				credito.setMontoSolicitado(Double.valueOf(creditows.getMontoSolicitado()));
				credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB(creditows.getFechaOtorgamiento()));
				credito.setMontoCuota(Double.valueOf(creditows.getMontoCuota()));
				credito.setPlazo(creditows.getCantidadCuotas());
				credito.setTipoProducto(creditows.getTipoProducto());
				credito.setFlagTipoCredito("0");//0: indica que es credito de As400				
				credito.setTipoMoneda("CLP");
				
				credito.setCuotasMorosas(creditows.getSumaCuotas());
				credito.setGastosCobranza(creditows.getSumaGCob());
				
				listaCreditos.add(credito);
			}
		}
		
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}
	
	
 	Dummy
 * private static SalidaListaCreditoVigenteVO mapearConsultaCreditosPorRutEnAs400() throws ParseException {
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito = null;
		
		salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
		salidaVO.setMensaje("");
		
		for (int i = 0 ; i<2;i++) {
			credito = new SalidaCreditoVigenteVO();

				credito.setFolio("123123");
				credito.setMontoSolicitado(Double.valueOf("1000000"));
				credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB("20131026"));
				credito.setMontoCuota(Double.valueOf("50000"));
				credito.setPlazo("24");
				credito.setTipoProducto("");
				credito.setFlagTipoCredito("0");//0: indica que es credito de As400				
				credito.setTipoMoneda("CLP");
				
				credito.setCuotasMorosas("5");
				credito.setGastosCobranza("15000");
				
				listaCreditos.add(credito);
		}
		
		salidaVO.setListaCreditos(listaCreditos);		
		return salidaVO;
	}
	
	*//**
	 * Retorna las cuotas para el folio
	 * @param folio
	 * @return
	 *//*
	public static SalidaListaDetalleCreditoVigenteVO obtenerDetalleConsultaCreditosPorRutEnAs400(String folio){
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		
		ClienteConsultaDatosPagosCreditosPorFolioAs400 cliente = new ClienteConsultaDatosPagosCreditosPorFolioAs400();
		EntradaConsultaDatosPagosCreditosVO entradaVO = new EntradaConsultaDatosPagosCreditosVO();
		if(folio.length()== 13){
			String split[] = folio.split("-");
			
			entradaVO.setOficinaProceso(split[0]);
			entradaVO.setFolio(split[1]);

			try {
				logger.debug("Consulta datos pago crédito folio: " + entradaVO.getFolio());
				SalidaListaConsultaDatosPagosCreditosPorFolioVO respuesta = (SalidaListaConsultaDatosPagosCreditosPorFolioVO) cliente.call(entradaVO);
				logger.debug("Llamada a servicio datos pagos creditos por folio correcta");
				
				salidaVO = mapearDetalleConsultaCreditosPorRutEnAs400(respuesta);
				logger.debug("Mapeo servicio datos pagos creditos por folio correcto");

			} catch (Exception e) {
				logger.debug("Error obtener los pagos del crédito AS400: " + e.getMessage());
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(e.getMessage());
			}
			
		}else{
			salidaVO.setCodigoError("5");
			salidaVO.setMensaje("Error en largo de folio");
		}
		
		return salidaVO;
	}
	
	private static SalidaListaDetalleCreditoVigenteVO mapearDetalleConsultaCreditosPorRutEnAs400(SalidaListaConsultaDatosPagosCreditosPorFolioVO entrada) throws ParseException {
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		List<SalidaDetalleCreditoVigenteVO> listaCuotas = new ArrayList<SalidaDetalleCreditoVigenteVO>();
		SalidaDetalleCreditoVigenteVO cuotas = null;
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		if(entrada.getListaCuotas() == null){
			return salidaVO;
		}
		
		for (SalidaConsultaDatosPagosCreditosPorFolioVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoVigenteVO();
			
			cuotas.setDocPago(detalle.getNumeroDocUltimoPago());
			System.out.println(cuotas.getDocPago());
			//Retorna glosa estado al pago
			cuotas.setEstPago(detalle.getGlosaEstadoPago());
			
			cuotas.setFecPago(Utils.pasaFechaASaWEB(detalle.getFechaUltimoPago()));
			cuotas.setFecVencimiento(Utils.pasaFechaASaWEB(detalle.getFechaVencimiento()));
			cuotas.setnCuota(detalle.getNumeroCuota());
			cuotas.setOficina(detalle.getOficinaUltimoPago());
			cuotas.setMonto(Double.valueOf(detalle.getMontoTotalAbonadoACuota()));
			
			cuotas.setTipoMoneda("CLP");
			
			listaCuotas.add(cuotas);
		}
		
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}
	
}
*/