package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaDetalleFiniquitoVO;
import cl.laaraucana.satelites.certificados.finiquito.VO.SalidaListaFiniquitoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.ClienteEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.EntradaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.SalidaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn.ClienteQueryContractHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn.VO.SalidaQueryContractHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.ClienteQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.EntradaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.SalidaListaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.SalidaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ServiciosFiniquitoSAP {

	/**
	 * 
	 * @param rut
	 * @param fechaFullEpo
	 * @return obtiene los creditos vigentes de banking
	 */
	public static SalidaListaFiniquitoVO obtenerCreditosVigentesBanking(String rut, String fechaFullEpo) {

		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.vigente.sap").trim());// 1:
																																// obtiene
																																// creditos
																																// vigentes.

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaFiniquitoVO salidaVO;

		try {

			String fechaSap = Utils.pasaFechaWEBaSAP(fechaFullEpo);
			salidaVO = mapearQueryCompContHeaderIn((SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO), fechaSap, rut);

		} catch (Exception e) {
			salidaVO = new SalidaListaFiniquitoVO();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}

		return salidaVO;
	}

	/**
	 * 
	 * @param entrada
	 * @param tipoCredito
	 * @param fechaFullEpo
	 * @return mapea los datos para finiquito
	 * @throws Exception
	 * @throws ParseException
	 */
	private static SalidaListaFiniquitoVO mapearQueryCompContHeaderIn(SalidaListaQueryCompContHeaderInVO entrada, String fechaFullEpo, String rut) throws ParseException, Exception {
		SalidaListaFiniquitoVO salidaVO = new SalidaListaFiniquitoVO();
		List<SalidaFiniquitoVO> listaCreditos = new ArrayList<SalidaFiniquitoVO>();
		SalidaFiniquitoVO credito = null;

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}

		// /////////////////////////////////////////////////////////////////////Quitar
		// count///////////////////////////////////////////////////////////////////////////////
		// int cont = 1;
		for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
			
			if("S".equalsIgnoreCase(detalle.getPayer()) || "X".equalsIgnoreCase(detalle.getPayer())){
			
				credito = new SalidaFiniquitoVO();
				StringBuffer folioBuffer = new StringBuffer(detalle.getContractNumber());
				credito.setFolio(folioBuffer.insert(3, "-").toString());
				credito.setCuotaHasta(CompletaUtil.quitaCerosIzqEntero(detalle.getInstallmentQuantity()));
				SalidaFiniquitoVO salidaContractHeaderVO = obtenerContractHeaderBk(detalle.getContractNumber(), rut);
				if (salidaContractHeaderVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
					credito.setGravamenes(salidaContractHeaderVO.getGravamenes());
					credito.setSaldoCapital(salidaContractHeaderVO.getSaldoCapital());
					// credito.setTipoCredito(salidaContractHeaderVO.getTipoCredito());
					// comendato por que se reemplaza por otro tipo
					if (salidaContractHeaderVO.getTipoCredito() != null) {
						/*Validando que queryCashFlow in funciona bien*/
	
						SalidaListaDetalleFiniquitoVO salidaDetalle = obtenerQueryContractCashflowIn(detalle.getContractNumber(), fechaFullEpo);
	
						if (salidaDetalle != null && salidaDetalle.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							credito.setCuotaDesde(salidaDetalle.getCuotaDesde());
							// credito.setListaDetalle(salidaDetalle);
						} else {
							salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
							salidaVO.setMensaje(salidaDetalle.getMensaje());
							return salidaVO;
						}
	
						// obtiene el número de cuota del periodo ingresado (periodo
						// actual)
	
						/*SalidaDetalleFiniquitoVO salidaDetallePeriodo = obtenerQueryContractCashflowInPeriodo(detalle.getContractNumber(), fechaFullEpo);
						if (!salidaDetallePeriodo.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							salidaVO.setCodigoError(salidaDetallePeriodo.getCodigoError());
							salidaVO.setMensaje("Problemas en obtenerQueryContractCashflowIn para el crédito "+detalle.getContractNumber()+ " mensaje: "+salidaDetalle.getMensaje());
							return salidaVO;
						}*/
	
						if (salidaContractHeaderVO.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap").trim())) {
							System.out.println("================================== es un credito educacional ");
							credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim());
							System.out.println("el numero de la cuota del periodo actual es " + salidaDetalle.getNroCuotaActual());
							SalidaListaDetalleFiniquitoVO salidaLoanBalance = obtenerQueryLoanBalance(detalle.getContractNumber(), salidaDetalle.getNroCuotaActual(), rut);
	
							if (salidaLoanBalance.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
								credito.setListaDetalle(salidaLoanBalance);
							} else {
								salidaVO.setCodigoError(salidaLoanBalance.getCodigoError());
								//salidaVO.setMensaje("Problemas en salidaLoanBalance para el crédito " + detalle.getContractNumber() + " mensaje: " + salidaLoanBalance.getMensaje());
								salidaVO.setMensaje(salidaLoanBalance.getMensaje());
								return salidaVO;
							}
	
							// credito.setListaDetalle(salidaDetalle);
	
						} else if (salidaContractHeaderVO.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial.sap").trim())) {
							// se cambia al estandar Especial
							credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim());
						} else {
							// se cambia al estandar Consumo
							credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.social").trim());
						}
					} else {
						salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
						salidaVO.setMensaje(salidaContractHeaderVO.getMensaje()+ "- Sin tipo de crédito");
						return salidaVO;
					}
				} else {
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje(salidaContractHeaderVO.getMensaje());
					return salidaVO;
				}
	
				SalidaFiniquitoVO salidaEarlyVO = obtenerDatosEarlyPayOff(detalle.getContractNumber(), fechaFullEpo);
				if (salidaEarlyVO != null && salidaEarlyVO.getCodigoError().equalsIgnoreCase(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
					credito.setGravamenes(salidaEarlyVO.getGravamenes());
					credito.setMontoTotal(salidaEarlyVO.getMontoTotal());
					credito.setSumaGCob(salidaEarlyVO.getSumaGCob());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Problemas en salidaEarlyVO "+salidaEarlyVO.getMensaje());
					return salidaVO;
				}
	
				listaCreditos.add(credito);
	
				/*cont++;
				if(cont>10){
					break;
				}*/
			}
		}

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	/**
	 * metodo que recupera el saldo capital para el finiquito
	 * 
	 * @throws Exception
	 * */
	public static SalidaFiniquitoVO obtenerContractHeaderBk(String acnum_ext, String rut) throws Exception {
		EntradaQueryContractHeaderInVO entradaVO = new EntradaQueryContractHeaderInVO();
		entradaVO.setAcnum_ext(acnum_ext);
		entradaVO.setRut(rut);
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////cambiar////////////////////////
		ClienteQueryContractHeaderIn clienteBanking = new ClienteQueryContractHeaderIn();
		SalidaFiniquitoVO salidaVO = null;
		// try {
		salidaVO = mapearQueryContractHeaderIn((SalidaQueryContractHeaderInVO) clienteBanking.call(entradaVO));

		/*} catch (Exception e) {
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
		}*/

		return salidaVO;
	}

	private static SalidaFiniquitoVO mapearQueryContractHeaderIn(SalidaQueryContractHeaderInVO entrada) {
		SalidaFiniquitoVO salidaVO = null;
		if (entrada != null && entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setSaldoCapital(Utils.stringToDouble(entrada.getRemainingBalance()));
			salidaVO.setGravamenes(Utils.stringToDouble(entrada.getArrearsAmount()));
			// salidaVO.setTipoCredito(entrada.getComercialLine());
			// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// cambiar por linea comercial////////////////
			salidaVO.setTipoCredito(entrada.getComercialLine());
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());

		} else {
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}

		return salidaVO;
	}

	/**
	 * metodo que recupera los gravamenes y el monto epo
	 * 
	 * @throws Exception
	 * */
	public static SalidaFiniquitoVO obtenerDatosEarlyPayOff(String idContrato, String fechaFullEpo) throws Exception {
		EntradaEarlyPayoffSimulationOUT entradaVO = new EntradaEarlyPayoffSimulationOUT();
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// cambiar los parametros enviados /////
		entradaVO.setIdContrato(idContrato);
		entradaVO.setFechaFullEpo(fechaFullEpo);
		entradaVO.setParametro("0");

		ClienteEarlyPayoffSimulationOUT clienteBanking = new ClienteEarlyPayoffSimulationOUT();
		SalidaFiniquitoVO salidaVO = null;
		// try {
		salidaVO = mapearEarlyPayOffSimulationOUT((SalidaEarlyPayoffSimulationOUT) clienteBanking.call(entradaVO));

		/*} catch (Exception e) {
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
		}*/

		return salidaVO;
	}

	private static SalidaFiniquitoVO mapearEarlyPayOffSimulationOUT(SalidaEarlyPayoffSimulationOUT entrada) {
		SalidaFiniquitoVO salidaVO = null;

		if (entrada != null && entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setMontoTotal(Utils.stringToDouble(entrada.getMontoEpo()));
			salidaVO.setGravamenes(Utils.stringToDouble(entrada.getArrearsAmount()));
			salidaVO.setSumaGCob(entrada.getUnpaidCharge().trim());
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		} else {
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}

		return salidaVO;
	}

	/**
	 * 
	 * @param folio
	 * @return obtiene el detalle del credito
	 */

	/*********************************************************
	 * detalle del credito
	 * 
	 * @throws Exception
	 *************************************************/

	public static SalidaListaDetalleFiniquitoVO obtenerQueryContractCashflowIn(String folio, String fechaFullEpo) throws Exception {
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		SalidaListaDetalleFiniquitoVO salidaVO = null;
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////7
		// cambiar los parametros enviados /////////
		entradaVO.setFolioCredito(folio);

		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		// try {
		salidaVO = mapearQueryContractCashflowIn((SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO), folio, fechaFullEpo);

		/*} catch (Exception e) {
			salidaVO = new SalidaListaDetalleFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}*/

		return salidaVO;
	}

	private static SalidaListaDetalleFiniquitoVO mapearQueryContractCashflowIn(SalidaListaQueryContractCashflowInVO entrada, String folio, String fechaFullEpo) throws ParseException {
		SalidaListaDetalleFiniquitoVO salidaVO = new SalidaListaDetalleFiniquitoVO();
		SalidaDetalleFiniquitoVO cuotas = null;
		List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();

		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}

		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleFiniquitoVO();
			cuotas.setnCuota(detalle.getNroCuota());
			cuotas.setMonto(Utils.stringToDouble(detalle.getTotalCuota()));
			cuotas.setEstadoCuota(detalle.getEstadoCuota().trim());
			cuotas.setMontoGravamen(Utils.stringToDouble(detalle.getMontoGravamenes()));
			cuotas.setFolio(folio);
			if ((salidaVO.getCuotaDesde() == null || salidaVO.getCuotaDesde().isEmpty()) && !ServiciosConst.RES_SERVICIOS.getString("cashFlow.estadoCuota.cancelada").trim().equals(cuotas.getEstadoCuota())) {
				salidaVO.setCuotaDesde(cuotas.getnCuota());
			}
			listaCuotas.add(cuotas);
		}

		// para obtener el numero de la cuota de hoy

		String periodoFini = Utils.getFechaSapaPeriodo(fechaFullEpo);
		String periodoDet = null;

		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			periodoDet = Utils.getFechaSapaPeriodo(detalle.getFechaVencCuota());
			if (periodoFini != null && periodoDet != null && periodoFini.equals(periodoDet)) {
				salidaVO.setNroCuotaActual(detalle.getNroCuota());
			}
		}

		salidaVO.setListaCuotas(listaCuotas);
		salidaVO.setLineaComercial(entrada.getLineaComercial());
		salidaVO.setCantidadTotalCuotas(entrada.getCantidadTotalCuotas());
		salidaVO.setNroCuenta(entrada.getCantidadTotalCuotas());

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}

	/*********************************************************
	 * obtiene datos del detalle por periodo
	 * 
	 * @throws Exception
	 * @throws ParseException
	 *************************************************/

	public static SalidaDetalleFiniquitoVO obtenerQueryContractCashflowInPeriodo(String folio, String fechaFullEpo) throws ParseException, Exception {
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		SalidaDetalleFiniquitoVO salidaVO = null;
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// cambiar los parametros enviados ///
		entradaVO.setFolioCredito(folio);

		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		// try {
		salidaVO = mapearQueryContractCashflowInPeriodo((SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO), folio, fechaFullEpo);

		/*} catch (Exception e) {
			salidaVO = new SalidaDetalleFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setCodigoError(e.getMessage());
			e.printStackTrace();
		}*/

		return salidaVO;
	}

	private static SalidaDetalleFiniquitoVO mapearQueryContractCashflowInPeriodo(SalidaListaQueryContractCashflowInVO entrada, String folio, String fechaFullEpo) throws ParseException {
		SalidaDetalleFiniquitoVO cuota = new SalidaDetalleFiniquitoVO();

		// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////cambiar
		String periodoFini = Utils.getFechaSapaPeriodo(fechaFullEpo);
		String periodoDet = null;
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)) {
			cuota.setCodigoError(entrada.getCodigoError());
			cuota.setMensaje(entrada.getMensaje());
			return cuota;
		}

		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			periodoDet = Utils.getFechaSapaPeriodo(detalle.getFechaVencCuota());
			if (periodoFini != null && periodoDet != null && periodoFini.equals(periodoDet)) {
				cuota = new SalidaDetalleFiniquitoVO();
				cuota.setCantidadTotalCuotas(entrada.getCantidadTotalCuotas());
				cuota.setnCuota(detalle.getNroCuota());
				cuota.setMonto(Utils.stringToDouble(detalle.getTotalCuota()));
				cuota.setEstadoCuota(detalle.getEstadoCuota());
				cuota.setMontoGravamen(Utils.stringToDouble(detalle.getMontoGravamenes()));
				cuota.setFechaVencimiento(detalle.getFechaVencCuota()); // //////////////////////////////////////////////////////////////////////////////estandarizar
																		// fecha
				cuota.setFolio(folio);
			}
		}
		cuota.setCodigoError(entrada.getCodigoError());
		cuota.setMensaje(entrada.getMensaje());

		return cuota;
	}

	public static SalidaListaDetalleFiniquitoVO obtenerQueryLoanBalance(String folio, String nroCuota, String rut) throws Exception {
		SalidaListaDetalleFiniquitoVO salidaVO = null;
		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////7
		// cambiar los parametros enviados /////////
		EntradaQueryLoanBalanceOut entradaVO = new EntradaQueryLoanBalanceOut();
		entradaVO.setFlgMoneda(ServiciosConst.RES_SERVICIOS.getString("loanBalance.flag.moneda").trim());
		// 0 para traer toda la lista de morosidad mas la actual.
		System.out.println("el número de la cuota es " + nroCuota);
		entradaVO.setNroCuota(nroCuota);
		entradaVO.setNroContrato(folio);
		entradaVO.setRut(rut);

		ClienteQueryLoanBalanceOut clienteWs = new ClienteQueryLoanBalanceOut();
		// try {

		salidaVO = mapearQueryLoanBalance((SalidaListaQueryLoanBalanceOut) clienteWs.call(entradaVO), folio);

		/*} catch (Exception e) {
			salidaVO = new SalidaListaDetalleFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}*/

		return salidaVO;
	}

	private static SalidaListaDetalleFiniquitoVO mapearQueryLoanBalance(SalidaListaQueryLoanBalanceOut entrada, String folio) {
		SalidaListaDetalleFiniquitoVO salidaVO = new SalidaListaDetalleFiniquitoVO();
		SalidaDetalleFiniquitoVO cuotas = null;
		List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();

		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR) || entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)) {
			return salidaVO;
		}

		for (SalidaQueryLoanBalanceOut detalle : entrada.getSalidaList()) {
			cuotas = new SalidaDetalleFiniquitoVO();
			cuotas.setnCuota(detalle.getNroCuota());
			cuotas.setMonto(Utils.stringToDouble(detalle.getMontoCuota()));
			cuotas.setMontoGravamen(Utils.stringToDouble(detalle.getMontoMora()));
			cuotas.setFolio(folio);
			listaCuotas.add(cuotas);
			break;
		}
		salidaVO.setListaCuotas(listaCuotas);
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}

}
