package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class ServiciosFiniquitoSAP21082013 {

	/**
	 * 
	 * @param rut
	 * @param fechaFullEpo
	 * @return obtiene los creditos vigentes de banking
	 */
	public static SalidaListaFiniquitoVO obtenerCreditosVigentesBanking(String rut, String fechaFullEpo) {

		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.vigente.sap").trim());// 1: obtiene creditos vigentes.

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
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}
		

		///////////////////////////////////////////////////////////////////////Quitar count///////////////////////////////////////////////////////////////////////////////
		//int cont = 1;
		for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
			credito = new SalidaFiniquitoVO();
			credito.setFolio(detalle.getContractNumber());
			credito.setCuotaHasta(detalle.getInstallmentQuantity());
			SalidaFiniquitoVO salidaContractHeaderVO = obtenerContractHeaderBk(detalle.getContractNumber(), rut);
			if (salidaContractHeaderVO != null && salidaContractHeaderVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				credito.setSaldoCapital(salidaContractHeaderVO.getSaldoCapital());
				//credito.setTipoCredito(salidaContractHeaderVO.getTipoCredito()); comendato por que se reemplaza por otro tipo
				if (salidaContractHeaderVO.getTipoCredito() != null) {
					if (salidaContractHeaderVO.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap").trim())) 
					{
						System.out.println("================================== es un credito educacional ");
						credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior").trim());
						SalidaListaDetalleFiniquitoVO salidaDetalle = obtenerQueryContractCashflowIn(detalle.getContractNumber());
						SalidaDetalleFiniquitoVO salidaDet = obtenerQueryContractCashflowInPeriodo(detalle.getContractNumber(), fechaFullEpo);
						if (salidaDetalle != null && salidaDetalle.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							//credito.setCuotaDesde(String.valueOf(Integer.parseInt(credito.getCuotaHasta()) - Integer.parseInt(salidaDet.getnCuota())));
							//credito.setCuotaHasta(salidaDet.getCantidadTotalCuotas());
							credito.setCuotaDesde(String.valueOf(Integer.parseInt(salidaDet.getCantidadTotalCuotas()) - Integer.parseInt(salidaDet.getnCuota())));
							credito.setListaDetalle(salidaDetalle);
						}else{
							salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
							salidaVO.setMensaje("Problemas en obtenerQueryContractCashflowIn para el crédito educación superior");
							return salidaVO;
						}
						System.out.println("================================== el tipo de credito es  "+credito.getTipoCredito());
					} else if (salidaContractHeaderVO.getTipoCredito().trim().equalsIgnoreCase(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial.sap").trim())) {
						// se cambia al estandar Especial
						credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.especial").trim());
						//llenar cuota desde
						SalidaDetalleFiniquitoVO salidaDet = obtenerQueryContractCashflowInPeriodo(detalle.getContractNumber(), fechaFullEpo);
						if(salidaDet != null && salidaDet.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							//credito.setCuotaDesde(String.valueOf(Integer.parseInt(credito.getCuotaHasta()) - Integer.parseInt(salidaDet.getnCuota())));
							//credito.setCuotaHasta(salidaDet.getCantidadTotalCuotas());
							credito.setCuotaDesde(String.valueOf(Integer.parseInt(salidaDet.getCantidadTotalCuotas()) - Integer.parseInt(salidaDet.getnCuota())));
						}else{
							salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
							salidaVO.setMensaje("Problemas en obtenerQueryContractCashflowInPeriodo para el crédito especial");
							return salidaVO;
						}
					} else {
						// Consumo
						credito.setTipoCredito(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.social").trim());
						SalidaDetalleFiniquitoVO salidaDet = obtenerQueryContractCashflowInPeriodo(detalle.getContractNumber(), fechaFullEpo);
						if(salidaDet != null && salidaDet.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS) && salidaDet.getnCuota() != null){
							//credito.setCuotaDesde(String.valueOf(Integer.parseInt(credito.getCuotaHasta()) - Integer.parseInt(salidaDet.getnCuota())));
							//credito.setCuotaHasta(salidaDet.getCantidadTotalCuotas());
							credito.setCuotaDesde(String.valueOf(Integer.parseInt(salidaDet.getCantidadTotalCuotas()) - Integer.parseInt(salidaDet.getnCuota())));
						}else{
							salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
							salidaVO.setMensaje("Problemas en obtenerQueryContractCashflowInPeriodo para el crédito sociak");
							return salidaVO;
						}
					}
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("No trae tipo de crédito");
					return salidaVO;
				}
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Problemas en QueryContractHeaderIn");
				return salidaVO;
			}

			SalidaFiniquitoVO salidaEarlyVO = obtenerDatosEarlyPayOff(detalle.getContractNumber(), fechaFullEpo);
			if (salidaEarlyVO != null && salidaEarlyVO.getCodigoError().equalsIgnoreCase(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				credito.setGravamenes(salidaEarlyVO.getGravamenes());
				credito.setMontoTotal(salidaEarlyVO.getMontoTotal());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Problemas en salidaEarlyVO");
				return salidaVO;
			}
			
			listaCreditos.add(credito);

			/*cont++;
			if(cont>10){
				break;
			}*/
		}
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

	/**
	 * metodo que recupera el saldo capital para el finiquito
	 * @throws Exception 
	 * */
	public static SalidaFiniquitoVO obtenerContractHeaderBk(String acnum_ext, String rut) throws Exception {
		EntradaQueryContractHeaderInVO entradaVO = new EntradaQueryContractHeaderInVO();
		entradaVO.setAcnum_ext(acnum_ext);
		entradaVO.setRut(rut);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////cambiar////////////////////////
		ClienteQueryContractHeaderIn clienteBanking = new ClienteQueryContractHeaderIn();
		SalidaFiniquitoVO salidaVO = null;
		//try {
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
			//salidaVO.setTipoCredito(entrada.getComercialLine());
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// cambiar por linea comercial////////////////
			salidaVO.setTipoCredito(entrada.getComercialLine()); 
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			
		}else{
			salidaVO = new SalidaFiniquitoVO();
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}

		return salidaVO;
	}

	/**
	 * metodo que recupera los gravamenes y el monto epo
	 * @throws Exception 
	 * */
	public static SalidaFiniquitoVO obtenerDatosEarlyPayOff(String idContrato, String fechaFullEpo) throws Exception {
		EntradaEarlyPayoffSimulationOUT entradaVO = new EntradaEarlyPayoffSimulationOUT();
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// cambiar los parametros enviados /////
		entradaVO.setIdContrato(idContrato);
		entradaVO.setFechaFullEpo(fechaFullEpo);

		ClienteEarlyPayoffSimulationOUT clienteBanking = new ClienteEarlyPayoffSimulationOUT();
		SalidaFiniquitoVO salidaVO = null;
		//try {
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
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}else{
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
	
	/********************************************************* detalle del credito 
	 * @throws Exception *************************************************/

	public static SalidaListaDetalleFiniquitoVO obtenerQueryContractCashflowIn(String folio) throws Exception {
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		SalidaListaDetalleFiniquitoVO salidaVO = null;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////7 cambiar los parametros enviados /////////
		entradaVO.setFolioCredito(folio);

		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		//try {
			
			salidaVO = mapearQueryContractCashflowIn((SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO), folio);


		/*} catch (Exception e) {
			salidaVO = new SalidaListaDetalleFiniquitoVO();
			salidaVO.setCodigoError("1");
			salidaVO.setMensaje(e.getMessage());
			e.printStackTrace();
		}*/

		return salidaVO;
	}

	

	private static SalidaListaDetalleFiniquitoVO mapearQueryContractCashflowIn(SalidaListaQueryContractCashflowInVO entrada, String folio) {
		SalidaListaDetalleFiniquitoVO salidaVO = new SalidaListaDetalleFiniquitoVO();
		SalidaDetalleFiniquitoVO cuotas = null;
		List<SalidaDetalleFiniquitoVO> listaCuotas = new ArrayList<SalidaDetalleFiniquitoVO>();
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}

		for (SalidaQueryContractCashflowInVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleFiniquitoVO();
			cuotas.setnCuota(detalle.getNroCuota());
			cuotas.setMonto(Utils.stringToDouble(detalle.getTotalCuota()));
			cuotas.setEstadoCuota(detalle.getEstadoCuota());
			cuotas.setMontoGravamen(Utils.stringToDouble(detalle.getMontoGravamenes()));
			cuotas.setFolio(folio);
			listaCuotas.add(cuotas);
		}
		salidaVO.setListaCuotas(listaCuotas);
		salidaVO.setLineaComercial(entrada.getLineaComercial());
		salidaVO.setCantidadTotalCuotas(entrada.getCantidadTotalCuotas());
		salidaVO.setNroCuenta(entrada.getCantidadTotalCuotas());
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}
	
	/********************************************************* obtiene datos del detalle por periodo 
	 * @throws Exception 
	 * @throws ParseException *************************************************/
	
	public static SalidaDetalleFiniquitoVO obtenerQueryContractCashflowInPeriodo(String folio, String fechaFullEpo) throws ParseException, Exception {
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		SalidaDetalleFiniquitoVO salidaVO = null;
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// cambiar los parametros enviados ///
		entradaVO.setFolioCredito(folio);

		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		//try {
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
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////cambiar
		String periodoFini = Utils.getFechaSapaPeriodo(fechaFullEpo);
		String periodoDet = null;
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
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
				cuota.setFechaVencimiento(detalle.getFechaVencCuota()); ////////////////////////////////////////////////////////////////////////////////estandarizar fecha
				cuota.setFolio(folio);
			}
		}
		cuota.setCodigoError(entrada.getCodigoError());
		cuota.setMensaje(entrada.getMensaje());

		return cuota;
	}
	
	
	

}
