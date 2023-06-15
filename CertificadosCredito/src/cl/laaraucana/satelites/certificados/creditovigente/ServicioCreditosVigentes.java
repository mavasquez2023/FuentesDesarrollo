package cl.laaraucana.satelites.certificados.creditovigente;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaCreditoVigenteVO;
import cl.laaraucana.satelites.certificados.creditovigente.VO.SalidaListaDetalleCreditoVigenteVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.ClienteInfoProtesto;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.EntradaInfoProtestVO;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.SalidaInfoProtestVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

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
			credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(detalle.getContractBegin()));
			credito.setMontoCuota(Utils.formateaMontoSAPDouble(detalle.getInstallmentAmount()));
			credito.setPlazo(detalle.getInstallmentQuantity());
			credito.setTipoMoneda(detalle.getContractCurrency());//UF o CLP
			credito.setFlagTipoCredito("1");//1: indica que es credito de banking
			credito.setInsolvencia(detalle.getInsolvencia());
			
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
	
}
