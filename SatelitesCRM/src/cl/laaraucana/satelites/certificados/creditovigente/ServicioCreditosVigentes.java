package cl.laaraucana.satelites.certificados.creditovigente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;

import cl.laaraucana.satelites.Utils.UtilCuota;
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
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.ClienteInfoProtesto;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.EntradaInfoProtestVO;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.SalidaInfoProtestVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ServicioCreditosVigentes {

	protected static Logger logger = Logger.getLogger(ServicioCreditosVigentes.class);

	/**
	 * Llamada a servicio de consulta de créditos SAP banking, específicamente
	 * para créditos vigentes
	 * 
	 * @param rut
	 * @return
	 * @throws Exception Si la llamada al servicio o el mapeo no fue exitoso
	 */
	public static SalidaListaCreditoVigenteVO obtenerCreditosVigentesBanking(String rut, String flagTipoCredito) {
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(flagTipoCredito);//1: obtiene creditos vigentes.

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();

		try {
			SalidaListaQueryCompContHeaderInVO respuesta = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO);
			logger.debug("Créditos vigentes: Llamada a banking exitosa.");
			salidaVO = mapearQueryCompContHeaderIn(respuesta);
			logger.debug("Créditos vigentes: Mapeo de datos exitoso.");

		} catch (Exception e) {
			e.printStackTrace();
			//Setea codigo de error salida
			logger.debug("Error al obtener los datos de SAP: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error: " + e.getMessage());
		}

		return salidaVO;
	}

	/**
	 * @param entrada
	 * @return
	 * @throws Exception si no se pudo realizar correctamente el mapeo de datos
	 */
	private static SalidaListaCreditoVigenteVO mapearQueryCompContHeaderIn(SalidaListaQueryCompContHeaderInVO entrada) throws Exception {
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito = null;

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		if (entrada.getListaCreditos() == null) {
			return salidaVO;
		}
		
		for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
			credito = new SalidaCreditoVigenteVO();

			//credito.setFolio(Utils.formatearFolio(detalle.getContractNumber()));
			credito.setFolio(detalle.getContractNumber());
			credito.setMontoSolicitado(Utils.formateaMontoSAPDouble(detalle.getContractAmount()));
			try {
				credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(detalle.getContractBegin()));
			} catch (Exception e) {
				credito.setFechaOtorgamiento("00-00-0000");
			}
			credito.setMontoCuota(Utils.formateaMontoSAPDouble(detalle.getInstallmentAmount()));
			credito.setPlazo(detalle.getInstallmentQuantity());
			credito.setTipoMoneda(detalle.getContractCurrency());//UF o CLP
			credito.setFlagTipoCredito("1");//1: indica que es credito de banking
			
			if (detalle.getRole() == null) {
				credito.setRolAsociado(detalle.getRole());
			}else if (detalle.getRole().trim().equals("BCA010")) {
				credito.setRolAsociado("Titular");
			} else if (detalle.getRole().trim().equals("ZAVAL1")) {
				credito.setRolAsociado("Aval 1");
			} else if (detalle.getRole().trim().equals("ZAVAL2")) {
				credito.setRolAsociado("Aval 2");
			}
			
			//Se agrega consulta a servicio InfoProtestos para obtener gastos de cobranza
			ClienteInfoProtesto infoProtesto = new ClienteInfoProtesto();
			EntradaInfoProtestVO infoProtestoIn = new EntradaInfoProtestVO();
			infoProtestoIn.setContractNumber(detalle.getContractNumber());
			SalidaInfoProtestVO respInfoProtestos = (SalidaInfoProtestVO) infoProtesto.call(infoProtestoIn);
			
			if(respInfoProtestos.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				credito.setGastosCobranza(Utils.formateaMontoSAP(respInfoProtestos.getAmount()));
			}else{
				throw new Exception("No se pudo obtener gastos de cobranza");
			}
			
			listaCreditos.add(credito);
		}

		salidaVO.setListaCreditos(listaCreditos);
		return salidaVO;
	}

	public static SalidaListaDetalleCreditoVigenteVO obtenerDetalleCreditosVigentesBanking(String folio) {
		//EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		QueryContractCashFlowRequest entradaVO= new QueryContractCashFlowRequest();
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		entradaVO.setNroCuenta(folio);

		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		try {

			logger.debug("Obteniendo cuotas vigentes SAP para crédito folio: " + entradaVO.getNroCuenta());
			//SalidaListaQueryContractCashflowInVO respuesta = (SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO);
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

	private static SalidaListaDetalleCreditoVigenteVO mapearQueryContractCashflowIn(SalidaListaQueryContractCashflowInVO entrada) throws Exception {

		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		SalidaDetalleCreditoVigenteVO cuotas = null;
		List<SalidaDetalleCreditoVigenteVO> listaCuotas = new ArrayList<SalidaDetalleCreditoVigenteVO>();

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		if (entrada.getListaCuotas() == null) {
			return salidaVO;
		}
		int cuotasPendientes=0;
		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoVigenteVO();
			/*if (ServiciosConst.RES_SERVICIOS.getString("cashFlow.estadoCuota.cancelada").equalsIgnoreCase(detalle.getEstadoCuota().trim())) {
				continue;
			}*/
			cuotas.setnCuota(detalle.getNroCuota());
			//Try's para formatear la fecha en formato dd-mm-yyy si falla mostrar la fecha tal cual viene.
			cuotas.setFecVencimiento(detalle.getFechaVencCuota());
			//cuotas.setOficina(detalle.getOficinaPago());
			//cuotas.setDocPago(detalle.getFolioPago());
			//cuotas.setEstPago(detalle.getEstadoPago());
			cuotas.setEstCuota(detalle.getEstadoCuota());
			cuotas.setTipoMoneda(detalle.getMoneda());

			try {
				cuotas.setMonto(Double.valueOf(detalle.getTotalCuota().replace(".", "").replace(",", "."))-Double.valueOf(detalle.getMontoAbono().replace(".", "").replace(",", ".")));
			} catch (Exception e) {
				throw new Exception("Error al obtener el monto.");
			}

			try {
				//cuotas.setFecPago(Utils.pasaFechaSAPaWEB(detalle.getUltFechaPago()));
			} catch (Exception e) {
				//cuotas.setFecPago("");
			}
			
			if(!detalle.getEstadoCuota().equals("CANCELADA")){
				cuotasPendientes++;
			}
			listaCuotas.add(cuotas);
		}

		salidaVO.setListaCuotas(listaCuotas);
		salidaVO.setCuotasPendientes(cuotasPendientes);
		
		return salidaVO;
	}

	public static SalidaListaCreditoVigenteVO obtenerConsultaCreditosPorRutEnAs400(String rut) {
		EntradaConsultaCreditosPorRutEnAs400VO entradaVO = new EntradaConsultaCreditosPorRutEnAs400VO(rut);
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();

		try {
			ClienteConsultaCreditosPorRutEnAs400 clienteWs = new ClienteConsultaCreditosPorRutEnAs400();
			SalidaListaConsultaCreditosPorRutEnAs400VO respuesta = (SalidaListaConsultaCreditosPorRutEnAs400VO) clienteWs.call(entradaVO);
			logger.debug("Llamada al servicio AS400 correcta");

			salidaVO = mapearConsultaCreditosPorRutEnAs400(respuesta, Long.parseLong(rut.substring(0, rut.length() - 2)));
			//salidaVO = mapearConsultaCreditosPorRutEnAs400(); dummy

		} catch (Exception e) {
			//Setea codigo de error salida
			logger.debug("Error al obtener los datos de AS400: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}

		return salidaVO;
	}

	private static SalidaListaCreditoVigenteVO mapearConsultaCreditosPorRutEnAs400(SalidaListaConsultaCreditosPorRutEnAs400VO entrada, long rut) throws ParseException {
		SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
		List<SalidaCreditoVigenteVO> listaCreditos = new ArrayList<SalidaCreditoVigenteVO>();
		SalidaCreditoVigenteVO credito = null;

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		if (entrada.getListaCreditos() == null) {
			return salidaVO;
		}

		for (SalidaConsultaCreditosPorRutEnAs400VO creditows : entrada.getListaCreditos()) {
			credito = new SalidaCreditoVigenteVO();
			
			if ((creditows.getEstadoCredito().trim().toUpperCase().equals("VIGENTE") || creditows.getEstadoCredito().trim().toUpperCase().equals("MOROSO"))) {
				credito.setFolio(creditows.getFolioCredito());
				credito.setMontoSolicitado(Double.valueOf(creditows.getMontoSolicitado()));
				credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB(creditows.getFechaOtorgamiento()));
				credito.setMontoCuota(Double.valueOf(creditows.getMontoCuota()) + UtilCuota.getSeguroPartidasAbiertas(creditows.getFolioCredito(), creditows.getFechaOtorgamiento()));
				credito.setPlazo(creditows.getCantidadCuotas());
				credito.setTipoProducto(creditows.getTipoProducto());
				credito.setFlagTipoCredito("0");//0: indica que es credito de As400				
				credito.setTipoMoneda("CLP");

				//Se agrega rol asociado
				String rol = Long.parseLong(creditows.getRolAsociado()) == rut ? "Titular" : "Aval";
				credito.setRolAsociado(rol);

				credito.setCuotasMorosas(creditows.getSumaCuotas());
				credito.setGastosCobranza(creditows.getSumaGCob());

				listaCreditos.add(credito);
			}
		}

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	/*
	 * Dummy private static SalidaListaCreditoVigenteVO
	 * mapearConsultaCreditosPorRutEnAs400() throws ParseException {
	 * SalidaListaCreditoVigenteVO salidaVO = new SalidaListaCreditoVigenteVO();
	 * List<SalidaCreditoVigenteVO> listaCreditos = new
	 * ArrayList<SalidaCreditoVigenteVO>(); SalidaCreditoVigenteVO credito =
	 * null;
	 * salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
	 * salidaVO.setMensaje(""); for (int i = 0 ; i<2;i++) { credito = new
	 * SalidaCreditoVigenteVO(); credito.setFolio("123123");
	 * credito.setMontoSolicitado(Double.valueOf("1000000"));
	 * credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB("20131026"));
	 * credito.setMontoCuota(Double.valueOf("50000")); credito.setPlazo("24");
	 * credito.setTipoProducto(""); credito.setFlagTipoCredito("0");//0: indica
	 * que es credito de As400 credito.setTipoMoneda("CLP");
	 * credito.setCuotasMorosas("5"); credito.setGastosCobranza("15000");
	 * listaCreditos.add(credito); } salidaVO.setListaCreditos(listaCreditos);
	 * return salidaVO; }
	 */

	/**
	 * Retorna las cuotas para el folio
	 * 
	 * @param folio
	 * @return
	 */
	public static SalidaListaDetalleCreditoVigenteVO obtenerDetalleConsultaCreditosPorRutEnAs400(String folio, String fechaOtorgamiento) {
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();

		ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 clientePA = new ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400();
		ClienteConsultaDatosPagosCreditosPorFolioAs400 clienteDP = new ClienteConsultaDatosPagosCreditosPorFolioAs400();
		if (folio.length() == 13) {
			
			SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO respPartidasAbiertas = null;
			try {
				EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO entradaPA = new EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
				System.out.println("el folio es " + folio);
				entradaPA.setFolioCredito(folio);
				entradaPA.setTipoCredito(" ");
				logger.debug("Consulta datos ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 folio: " + entradaPA.getFolioCredito());
				respPartidasAbiertas = (SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO) clientePA.call(entradaPA);			
				logger.debug("Mapeo servicio ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 por folio correcto");

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Error obtener los ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 AS400: " + e.getMessage());
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(e.getMessage());
				return salidaVO;
			}
			
			//Llamada al servicio ConsultaDatosPagosCreditos, para obtener el monto abono
			HashMap<Integer, Double> montoAbonoCuotas = null;
			try {
				EntradaConsultaDatosPagosCreditosVO entradaDP = new EntradaConsultaDatosPagosCreditosVO();
				String split[] = folio.split("-");
				entradaDP.setOficinaProceso(split[0]);
				entradaDP.setFolio(split[1]);
				SalidaListaConsultaDatosPagosCreditosPorFolioVO respDatosPagos = (SalidaListaConsultaDatosPagosCreditosPorFolioVO) clienteDP.call(entradaDP);
				montoAbonoCuotas = mapearMontoAbonoDatosPagos(respDatosPagos);
				logger.debug("Llamada a servicio datos pagos creditos por folio correcta");

			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Error obtener los ConsultaDatosPagosCreditos AS400: " + e.getMessage());
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(e.getMessage());
				return salidaVO;
			}
			
			try {
				salidaVO = mapearDetalleConsultaCreditosPorRutEnAs400(respPartidasAbiertas,montoAbonoCuotas , fechaOtorgamiento);
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Error al mapear los datos desde los servicios AS400: " + e.getMessage());
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(e.getMessage());
				return salidaVO;
			}
		} else {
			salidaVO.setCodigoError("5");
			salidaVO.setMensaje("Error en largo de folio");
		}

		return salidaVO;
	}

	private static SalidaListaDetalleCreditoVigenteVO mapearDetalleConsultaCreditosPorRutEnAs400(SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO entrada,HashMap<Integer, Double> montoAbonoCuotas , String fechaOtorgamiento)
			throws Exception {
		SalidaListaDetalleCreditoVigenteVO salidaVO = new SalidaListaDetalleCreditoVigenteVO();
		List<SalidaDetalleCreditoVigenteVO> listaCuotas = new ArrayList<SalidaDetalleCreditoVigenteVO>();
		SalidaDetalleCreditoVigenteVO cuotas = null;

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		if (entrada.getListaCuotas() == null) {
			return salidaVO;
		}

		for (SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoVigenteVO();

			//cuotas.setDocPago(detalle.getNumeroDocUltimoPago());
			//System.out.println(cuotas.getDocPago());
			//Retorna glosa estado al pago
			//cuotas.setEstPago(detalle.getGlosaEstadoPago());

			//cuotas.setFecPago(Utils.pasaFechaASaWEB(detalle.getFechaUltimoPago()));
			cuotas.setFecVencimiento(Utils.pasaFechaASaWEB(detalle.getFechaVencimiento()));
			cuotas.setnCuota(detalle.getNumeroCuota());
			//cuotas.setOficina(detalle.getOficinaUltimoPago());
			//			double montoAmortizado = Utils.stringToDouble(detalle.getMontoCapitalAmortizado());
			//			double montoInteres = Utils.stringToDouble(detalle.getMontoInteres());
			//			double montoseguro = Utils.stringToDouble(detalle.getMontoSeguros());
			//			cuotas.setMonto(montoAmortizado + montoInteres + montoseguro);
			Double montoAbono = montoAbonoCuotas.get(Integer.parseInt(detalle.getNumeroCuota()))!=null ? montoAbonoCuotas.get(Integer.parseInt(detalle.getNumeroCuota())) : 0;
			
			cuotas.setMonto(UtilCuota.getMontoCuotaDetalleVigente(detalle, fechaOtorgamiento)-montoAbono);
			String estadoCuota = detalle.getEstadoCuota();
			try {
				estadoCuota = ServiciosConst.RES_SERVICIOS.getString(estadoCuota.toUpperCase());
			} catch (Exception e) {
				estadoCuota = detalle.getEstadoCuota();
			}
			cuotas.setEstCuota(estadoCuota);
			cuotas.setTipoMoneda("CLP");

			listaCuotas.add(cuotas);
		}

		salidaVO.setListaCuotas(listaCuotas);

		return salidaVO;
	}
	
	private static HashMap<Integer, Double> mapearMontoAbonoDatosPagos(SalidaListaConsultaDatosPagosCreditosPorFolioVO respDatosPagos){
		HashMap<Integer, Double> cuotas = new HashMap<Integer, Double>();
			
		for(SalidaConsultaDatosPagosCreditosPorFolioVO cuota:respDatosPagos.getListaCuotas()){
			cuotas.put(Integer.valueOf(cuota.getNumeroCuota()), Double.parseDouble(cuota.getMontoTotalAbonadoACuota()));
		}
		return cuotas;
	}
	
}
