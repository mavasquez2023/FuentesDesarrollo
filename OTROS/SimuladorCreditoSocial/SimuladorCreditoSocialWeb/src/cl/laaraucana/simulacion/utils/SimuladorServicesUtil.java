package cl.laaraucana.simulacion.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.simulacion.VO.ParametrosSimulacionVO;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.CreaCotizacionClient;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionEntradaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionSalidaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.TasasInteresVO;
import cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.ClienteQuerySimilationWeb;
import cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO.PaymentOptionsEntradaVO;
import cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO.QuerySimulationEntradaVO;
import cl.laaraucana.simulacion.webservices.client.QuerySimulationWeb.VO.QuerySimulationSalidaVO;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.OficinaGastosNotarialClient;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaLista;

public class SimuladorServicesUtil {
	protected static Logger logger = Logger.getLogger(SimuladorServicesUtil.class);

	public static ResultadoSim getResultadoSimulacion(ParametrosSimulacionVO parametros, String tipoCredito) {
		CreaCotizacionSalidaVO salidaCreaCotizacion;
		ResultadoSim resultado = new ResultadoSim();
		String tipoSimulacionCotiza;
		String tipoSimulacionQuerySimulation;
		if (ConstantesFormalizar.TIPO_PRO_SIMULACION_ESPECIAL.equalsIgnoreCase(tipoCredito)) {
			tipoSimulacionCotiza = ConstantesFormalizar.TIPO_PRO_SIMULACION_ESPECIAL;
			tipoSimulacionQuerySimulation = ConstantesFormalizar.TIPO_PRO_QUERY_SIMULATION_ESPECIAL;
		} else {
			tipoSimulacionCotiza = ConstantesFormalizar.TIPO_PRO_SIMULACION_SOCIAL;
			tipoSimulacionQuerySimulation = ConstantesFormalizar.TIPO_PRO_QUERY_SIMULATION_SOCIAL;
		}
		try {
			salidaCreaCotizacion = getCreaCotizacion(parametros, tipoSimulacionCotiza);
			if (!salidaCreaCotizacion.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				resultado.setMensaje(salidaCreaCotizacion.getMensaje());
				logger.debug("Error en servicio creaCotizacion: " + salidaCreaCotizacion.getMensaje());
				return resultado;
			}
		} catch (Exception e) {
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			resultado.setMensaje("Error en servicio creaCotizacion: " + e.getMessage());
			logger.debug("Error en servicio creaCotizacion: " + e.getMessage());
			e.printStackTrace();
			return resultado;
		}
		if (!parametros.isSeguroDesgravamen()) {
			salidaCreaCotizacion.setSegDesgravamen("0");
		}
		QuerySimulationSalidaVO salidaQuerySimulation;
		try {
			salidaQuerySimulation = getQuerySimWeb(parametros, salidaCreaCotizacion, tipoSimulacionQuerySimulation);
			if (!salidaQuerySimulation.getCodigoError().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				resultado.setMensaje(salidaQuerySimulation.getMensaje());
				logger.debug("Error en servicio QuerySimulation: " + salidaQuerySimulation.getMensaje());
				return resultado;
			}
		} catch (Exception e) {
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			resultado.setMensaje("Error en servicio QuerySimulation: " + e.getMessage());
			logger.debug("Error en servicio QuerySimulation: " + e.getMessage());
			e.printStackTrace();
			return resultado;
		}
		resultado.setQuerySimulation(salidaQuerySimulation);
		resultado.setCreaCotizacion(salidaCreaCotizacion);
		resultado.setCuotas(parametros.getCuotas());
		resultado.setFechaPrimerVencimiento(FormalizarUtil.getFechaPrimerVenc(parametros.getTipoAfiliado()));
		resultado.setMontoSolicitado(parametros.getMonto().replace(".", ""));
		resultado.setTipoProducto(tipoSimulacionCotiza);
		resultado.setOficinaDesc(parametros.getOficinaDesc());
		resultado.setOficina(parametros.getOficina());
		resultado.setRut(parametros.getRutAfiliado());
		resultado.setNombreAfiliado(parametros.getNombreAfiliado());
		resultado.setSegCesantia(parametros.getSeguroCesantia());
		if (ConstantesFormalizar.TIPO_PRO_SIMULACION_ESPECIAL.equalsIgnoreCase(tipoSimulacionCotiza)) {
			resultado.setTipoProductoStr(ConstantesFormalizar.DESPLIEGUE_ESPECIAL);
		} else {
			resultado.setTipoProductoStr(ConstantesFormalizar.DESPLIEGUE_SOCIAL);
		}
		resultado.setTipoAfiliado(parametros.getTipoAfiliado());
		resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
		return resultado;
	}

	private static CreaCotizacionSalidaVO getCreaCotizacion(ParametrosSimulacionVO parametros, String tipoSimulacionCotiza)
		throws Exception {
		System.out.println("se ingresan parametros de simulacion");
		CreaCotizacionEntradaVO entrada = new CreaCotizacionEntradaVO();
		entrada.setTipoEjecucion(ConstantesFormalizar.TIPO_EJECUCION_SIMULAR);
		entrada.setTipoAfiliado(parametros.getTipoAfiliado());
		entrada.setRut(parametros.getRutAfiliado());
		entrada.setRutEmpleador(parametros.getRutEmpleador());
		entrada.setSapEmpleador(parametros.getSapEmpleador());
		entrada.setMontoSolicitado(parametros.getMonto().replace(".", ""));
		entrada.setCuotas(parametros.getCuotas());
		entrada.setProducto(tipoSimulacionCotiza);
		entrada.setMoneda(ConstantesFormalizar.TIPO_MONEDA_PESOS);
		entrada.setOficinaVenta(parametros.getOficina());
		entrada.setCanalVenta(ConstantesFormalizar.CANAL_VENTA);
		entrada.setSeguroCesantia(parametros.getSeguroCesantia());
		entrada.setSectorVenta(ConstantesFormalizar.SECTOR_VENTA);
		// crearCotizacion
		CreaCotizacionClient client = new CreaCotizacionClient();
		CreaCotizacionSalidaVO salidaCreaCotizacion = client.call(entrada);
		// 8000002197 reemplaza tasa mensual SAP por la tasa ingresada en el formulario de simulacion
		salidaCreaCotizacion.setTasaIntMensual(parametros.getTasaMensual());
		salidaCreaCotizacion.setTasaIntAnual(String.valueOf(Float.parseFloat(parametros.getTasaMensual()) * 12));
		return salidaCreaCotizacion;
	}

	public static QuerySimulationSalidaVO getQuerySimWeb(ParametrosSimulacionVO parametros, CreaCotizacionSalidaVO salidaCreaCotizacion,
		String tipoSimulacionQuerySimulation) throws Exception {
		QuerySimulationEntradaVO entrada = new QuerySimulationEntradaVO();
		// paymentOptions
		List<PaymentOptionsEntradaVO> paymentOptionsEntradaList = new ArrayList<PaymentOptionsEntradaVO>();
		List<TasasInteresVO> tasaInteresList = salidaCreaCotizacion.getTasasInteresList();
		PaymentOptionsEntradaVO payment;
		for (TasasInteresVO tasasInteresVO : tasaInteresList) {
			payment = new PaymentOptionsEntradaVO();
			if (!"0".equalsIgnoreCase(tasasInteresVO.getMeses()) && tasasInteresVO.getMeses() != null) {
				System.out.println("la tasa de interes es " + tasasInteresVO.getMeses());
				payment.setAgTerm(UtilProperties.getValorProperties("escala." + tasasInteresVO.getMeses()));
				//payment.setInterestRate(tasasInteresVO.getPorcentaje());
				// 8000002197 reemplaza tasa mensual SAP por la tasa ingresada en el formulario de simulacion
				payment.setInterestRate(parametros.getTasaMensual());
				paymentOptionsEntradaList.add(payment);
			}
		}
		entrada.setPaymentOptionsEntradaList(paymentOptionsEntradaList);
		entrada.setOrgId(ConstantesFormalizar.ORIGEN_ID);
		//entrada.setStartDate("2013-02-01");
		//entrada.setEndDate("2016-12-31");
		entrada.setStartDate(Utils.getFechaHoySAP());
		// ASSEMBLY 8000001778: if ZFSO then calcular fecha con 1 mes de desfase
		int cuotas = Integer.parseInt(parametros.getCuotas());
		if (parametros.getTipoAfiliado().equals("ZFSO")) {
			cuotas++;
		}
		entrada.setEndDate(Utils.getFechaHoySAPMasMeses(cuotas));
		entrada.setProductId(tipoSimulacionQuerySimulation); // cambiar por properties
		entrada.setCreditAmount(parametros.getMonto().replace(".", ""));
		//entrada.setInterestRate(salidaCreaCotizacion.getTasaIntMensual()); //es interes mensual
		// 8000002197 reemplaza tasa mensual SAP por la tasa ingresada en el formulario de simulacion
		entrada.setInterestRate(parametros.getTasaMensual());
		entrada.setAmountZnot(salidaCreaCotizacion.getGastosNotariales());
		entrada.setAmountZlte(salidaCreaCotizacion.getLte());
		entrada.setAmountZsde(salidaCreaCotizacion.getSegDesgravamen());
		entrada.setAmountZsce(salidaCreaCotizacion.getSegCesantia());
		if (ConstantesFormalizar.COD_PENSIONADO.equalsIgnoreCase(parametros.getTipoAfiliado())) {
			entrada.setPensionado("X");
		} else {
			entrada.setPensionado(" ");
		}
		ClienteQuerySimilationWeb cliente = new ClienteQuerySimilationWeb();
		QuerySimulationSalidaVO salidaQuerySimulation = cliente.call(entrada);
		return salidaQuerySimulation;
	}

	public static OficinaGastosNotarialSalidaLista getOficinasGastos() throws Exception {
		OficinaGastosNotarialClient cliente = new OficinaGastosNotarialClient();
		return cliente.call();
	}
}
