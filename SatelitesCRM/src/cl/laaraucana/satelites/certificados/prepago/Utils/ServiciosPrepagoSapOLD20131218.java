package cl.laaraucana.satelites.certificados.prepago.Utils;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.ClienteConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.EntradaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.SalidaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.ClienteEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.EntradaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.SalidaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.ClienteQueryBPStatusOUT;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
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

public class ServiciosPrepagoSapOLD20131218 {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public static SalidaListaAfiliadoVO obtenerAfiliado(String rut) throws Exception {
		EntradaAfiliadoVO entradaVO = new EntradaAfiliadoVO();
		entradaVO.setRut(rut);
		
		ClienteQueryBPStatusOUT cliente = new ClienteQueryBPStatusOUT();
		SalidaListaAfiliadoVO salidaVO = new SalidaListaAfiliadoVO();
//		try {
			salidaVO = (SalidaListaAfiliadoVO) cliente.call(entradaVO);
//		} catch (Exception e) {
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteQueryBPStatusOUT: " + e.getMessage());
//		}
		return salidaVO;
	}
	
	public static SalidaListaCreditoPrepagoVO obtenerCreditosVigentesBanking(String rut, String fechaFullEpo) throws Exception {

		System.out.println("Ingreso a ClienteQueryCompContHeaderIn");
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito("1");// 1: obtiene creditos vigentes.

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoPrepagoVO salidaVO= new SalidaListaCreditoPrepagoVO();

//		try {
			System.out.println("Ingreso a mapearQueryCompContHeaderIn");
			salidaVO = mapearQueryCompContHeaderIn(rut,(SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO), entradaVO.getFlagTipoCredito(), fechaFullEpo);
			System.out.println("Salida de mapearQueryCompContHeaderIn");

			if(salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Obtener Detalle CreditosVigentes Banking OK");
			}

//		} catch (Exception e) {
//			System.out.println("Error en mapearQueryCompContHeaderIn");
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje(e.getMessage());
//		}

		return salidaVO;
	}
	
	

	@SuppressWarnings("null")
	private static SalidaListaCreditoPrepagoVO mapearQueryCompContHeaderIn(String rut,SalidaListaQueryCompContHeaderInVO entrada, String tipoCredito, String fechaFullEpo) throws Exception {
		
		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
		SalidaCreditoPrepagoVO credito = null;
		int contador = 0;
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
				contador++;
				System.out.println("Comienzo de mapeo mapearQueryCompContHeaderIn : !! "+detalle.getContractNumber());
				credito = new SalidaCreditoPrepagoVO();
				credito.setFolio(detalle.getContractNumber());
				credito.setHastaCuota(detalle.getInstallmentQuantity());
				credito.setFechaOtorgamiento(detalle.getContractBegin());

				
				System.out.println("Ingreso a obtenerCreditoPrepago");
				SalidaCreditoPrepagoVO salidaCompactHeaderInVO = obtenerCreditoPrepago(rut,detalle.getContractNumber());
				if (salidaCompactHeaderInVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//					credito.setTasaImpuestoLTE(Utils.stringToDouble(salidaCompactHeaderInVO.getTasaImpuestoLTE()));
					credito.setMontoImpuestoLTE(salidaCompactHeaderInVO.getMontoImpuestoLTE());
					credito.setTipoCredito(salidaCompactHeaderInVO.getTipoCredito());
//					if(!credito.getTipoCredito().equals(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap"))){
//						credito.setSaldoCapital(salidaCompactHeaderInVO.getSaldoCapital());
//						credito.setMontoCuota(Utils.stringToDouble(detalle.getInstallmentAmount())); //habilitar cuando el servicio traiga el campo
//					}
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje(salidaCompactHeaderInVO.getMensaje());
					System.out.println(salidaVO.getMensaje());
					System.out.println("Numero de creditos al caer el servicio: "+contador);
					return salidaVO;
				}
				
				
				
				
				System.out.println("Ingreso a obtenerDatosEarlyPayOff");
				SalidaCreditoPrepagoVO salidaEarlyVO = obtenerDatosEarlyPayOff(detalle.getContractNumber(), fechaFullEpo);
				if (salidaEarlyVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
					credito.setGravamenes(salidaEarlyVO.getGravamenes());
					credito.setGastosDeCobranza(salidaEarlyVO.getGastosDeCobranza());
					credito.setSaldoCapital(salidaEarlyVO.getSaldoCapital());
					credito.setTotal(salidaEarlyVO.getTotal());
				} else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje(salidaEarlyVO.getMensaje());
					System.out.println(salidaVO.getMensaje());
					System.out.println("Numero de creditos al caer el servicio: "+contador);
					return salidaVO;
				}

				
				
				System.out.println("Ingreso a obtenerDatosCashFlowIn");
				SalidaCreditoPrepagoVO salidaCashFlowIn = obtenerDatosCashFlowIn(detalle.getContractNumber());
				if(salidaCashFlowIn.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
					if (salidaCashFlowIn.getDesdeCuota()!=null) {
//						credito.setDesdeCuota(1+String.valueOf(Integer.valueOf(credito.getHastaCuota())- Integer.valueOf(salidaCashFlowIn.getDesdeCuota())));
						credito.setDesdeCuota(salidaCashFlowIn.getDesdeCuota());
						credito.setNroCuotaActual(salidaCashFlowIn.getNroCuotaActual());
					}else{
						salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
						salidaVO.setMensaje("Error: en \"QueryContractCashflowIn\" el credito no corresponde a la fecha actual.");
						System.out.println("Numero de creditos al caer el servicio: "+contador+". Credito no corresponde a la fecha actual");
						return salidaVO;
					}
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje(salidaCashFlowIn.getMensaje());
					System.out.println(salidaVO.getMensaje());
					System.out.println("Numero de creditos al caer el servicio: "+contador);
					return salidaVO;
				}
							
	
				
				System.out.println("Ingreso a obtenerFolioFormulario24");
				SalidaCreditoPrepagoVO salidaFolioForm24 = obtenerFolioFormulario24(detalle.getContractBegin());
				if(salidaFolioForm24.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
					credito.setFolioForm24(salidaFolioForm24.getFolioForm24());
				}else{
					salidaVO.setCodigoError(salidaFolioForm24.getCodigoError());
					salidaVO.setMensaje(salidaFolioForm24.getMensaje());
					System.out.println(salidaVO.getMensaje());
					System.out.println("Numero de creditos al caer el servicio: "+contador);
					return salidaVO;
				}
				
				if(!credito.getTipoCredito().trim().equals(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap"))){
					credito.setMontoCuota(Utils.stringToDouble(detalle.getInstallmentAmount())); //habilitar cuando el servicio traiga el campo
				}else{
					
					SalidaCreditoPrepagoVO salidaLoanBalance = obtenerQueryLoanBalance(detalle.getContractNumber(), credito.getNroCuotaActual(), rut);
					
					if(salidaLoanBalance != null){
						credito.setMontoCuota(salidaLoanBalance.getMontoCuota());
					}else{
						salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
						salidaVO.setMensaje(salidaLoanBalance.getMensaje());
						System.out.println(salidaVO.getMensaje());
						System.out.println("Numero de creditos al caer el servicio: "+contador);
						return salidaVO;
					}
				}
				
				listaCreditos.add(credito);
			}
			
		salidaVO.setListaCreditos(listaCreditos);
		}
		System.out.println("Numero de creditos al finalizar: "+contador+" de "+entrada.getListaCreditos().size()+" creditos desde el Servicio CompContHeaderIn.");
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		return salidaVO;
	}
	
	
	
	public static SalidaCreditoPrepagoVO obtenerQueryLoanBalance(String folio, String nroCuota, String rut) throws Exception {
		SalidaCreditoPrepagoVO salidaVO = null;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////7 cambiar los parametros enviados /////////
		EntradaQueryLoanBalanceOut entradaVO = new EntradaQueryLoanBalanceOut();
		//DUMMY lo cambiare a dummy para ver que funcione.
		entradaVO.setFlgMoneda(ServiciosConst.RES_SERVICIOS.getString("loanBalance.flag.moneda").trim());
		//0 para traer toda la lista de morosidad mas la actual.
		System.out.println("el número de la cuota es "+nroCuota);
		entradaVO.setNroCuota(nroCuota);
		entradaVO.setNroContrato(folio);
		entradaVO.setRut(rut);

		ClienteQueryLoanBalanceOut clienteWs = new ClienteQueryLoanBalanceOut();
		//try {
			
			salidaVO = mapearQueryLoanBalance((SalidaListaQueryLoanBalanceOut) clienteWs.call(entradaVO), folio);



		return salidaVO;
	}
	
	
	private static SalidaCreditoPrepagoVO mapearQueryLoanBalance(SalidaListaQueryLoanBalanceOut entrada, String folio) {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();;
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR) || entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)){
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
			return salidaVO;
		}

		for (SalidaQueryLoanBalanceOut detalle : entrada.getSalidaList()) {
//			salidaVO = new SalidaCreditoPrepagoVO();
			salidaVO.setMontoCuota(Utils.stringToDouble(detalle.getMontoCuota()));
			break;
		}

		return salidaVO;
	}
	
	
	public static SalidaCreditoPrepagoVO obtenerFolioFormulario24(String fechaOtorgamiento) throws Exception {
		
		System.out.println("Ingreso a ClienteFolioFormulario24");
		EntradaConsultaFolioFormulario24 entradaVO = new EntradaConsultaFolioFormulario24();
//		try {
			entradaVO.setFechaOriginacionDeCredito(Utils.pasaFechaSAPaAs400(fechaOtorgamiento));
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
		ClienteConsultaFolioFormulario24 clienteForm24 = new ClienteConsultaFolioFormulario24();
		SalidaCreditoPrepagoVO salidaVO = null;
		
//		try {
//			salidaVO = new SalidaCreditoPrepagoVO();
			System.out.println("Comienzo mapearFolioFormulario24");
			salidaVO = mapearFolioFormulario24((SalidaConsultaFolioFormulario24)clienteForm24.call(entradaVO));
			System.out.println("Salida mapearFolioFormulario24");
			
//		} catch (Exception e) {
//			salidaVO = null;
//			e.printStackTrace();
//		}
		
		return salidaVO;
	}
	
	private static SalidaCreditoPrepagoVO mapearFolioFormulario24(SalidaConsultaFolioFormulario24 entrada) throws Exception {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();;
		if ( entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//			salidaVO = new SalidaCreditoPrepagoVO();
			salidaVO.setFolioForm24(entrada.getFolioFormulario24());
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
	
	public static SalidaCreditoPrepagoVO obtenerDatosCashFlowIn(String idContrato) throws Exception{
	
		System.out.println("Ingreso a ClienteQueryContractCashflowIn");
		EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		entradaVO.setFolioCredito(idContrato);
		ClienteQueryContractCashflowIn clienteBanking = new ClienteQueryContractCashflowIn();
		SalidaCreditoPrepagoVO salidaVO = null;
		System.out.println("Salida de ClienteQueryContractCashflowIn");
		
//		try {
			System.out.println("Comienzo mapearQueryCashFlowIn");
			salidaVO = mapearQueryCashFlowIn((SalidaListaQueryContractCashflowInVO)clienteBanking.call(entradaVO));
			System.out.println("Salida mapearQueryCashFlowIn");
			
//		} catch (Exception e) {
//			salidaVO = null;
//			e.printStackTrace();
//		}
		
		return salidaVO;
	}
	
	private static SalidaCreditoPrepagoVO mapearQueryCashFlowIn(SalidaListaQueryContractCashflowInVO entrada) throws Exception {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();
		String periodo = Utils.getFechaPeriodo();
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//			salidaVO = new SalidaCreditoPrepagoVO();
			for (SalidaQueryContractCashflowInVO listaCuotas : entrada.getListaCuotas()) {
				if(!listaCuotas.getEstadoCuota().toUpperCase().equals(ServiciosConst.RES_SERVICIOS.getString("cashFlow.estadoCuota.cancelada").toUpperCase())){
					salidaVO.setDesdeCuota(listaCuotas.getNroCuota());
					break;
				}
			}
			
			for (SalidaQueryContractCashflowInVO listaCuotas : entrada.getListaCuotas()) {
				String fechaVence=Utils.pasaFechaSAPaAs400(listaCuotas.getFechaVencCuota());
				if(periodo.equals(fechaVence.substring(0, 6))){
					salidaVO.setNroCuotaActual(listaCuotas.getNroCuota());
					break;
				}
			}
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
	public static SalidaCreditoPrepagoVO obtenerCreditoPrepago(String rut,String acnum_ext) throws Exception {
		
		System.out.println("Ingreso a ClienteQueryContractHeaderIn");
		EntradaQueryContractHeaderInVO entradaVO = new EntradaQueryContractHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setAcnum_ext(acnum_ext);
		ClienteQueryContractHeaderIn clienteBanking = new ClienteQueryContractHeaderIn();
		SalidaCreditoPrepagoVO salidaVO = null;
		System.out.println("Salida de ClienteQueryContractHeaderIn");
		
//		try {
			System.out.println("Comienzo mapearQueryContractHeaderIn");
			salidaVO = mapearQueryContractHeaderIn((SalidaQueryContractHeaderInVO) clienteBanking.call(entradaVO));
			System.out.println("Salida mapearQueryContractHeaderIn");

//		} catch (Exception e) {
//			salidaVO = null;
//			e.getStackTrace();
//		}
		return salidaVO;
	}
	
	
	private static SalidaCreditoPrepagoVO mapearQueryContractHeaderIn(SalidaQueryContractHeaderInVO entrada) {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();
		if ( entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//			salidaVO = new SalidaCreditoPrepagoVO();
			salidaVO.setMontoImpuestoLTE(Utils.stringToDouble(entrada.getLteAmount()));
			salidaVO.setSaldoCapital(Utils.stringToDouble(entrada.getUnpaidinstAmount()));
			salidaVO.setTipoCredito(entrada.getComercialLine());			
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
	
	public static SalidaCreditoPrepagoVO obtenerDatosEarlyPayOff(String idContrato, String fechaFullEpo) throws Exception {
		
		System.out.println("Ingreso a ClienteEarlyPayoffSimulationOUT");
		EntradaEarlyPayoffSimulationOUT entradaVO = new EntradaEarlyPayoffSimulationOUT();
		entradaVO.setIdContrato(idContrato);
		entradaVO.setFechaFullEpo(fechaFullEpo);
		
		ClienteEarlyPayoffSimulationOUT clienteBanking = new ClienteEarlyPayoffSimulationOUT();
		SalidaCreditoPrepagoVO salidaVO = null;
		System.out.println("Salida de ClienteEarlyPayoffSimulationOUT");
		
//		try {
			System.out.println("Ingreso a mapearEarlyPayOffSimulationOUT");
			salidaVO = mapearEarlyPayOffSimulationOUT((SalidaEarlyPayoffSimulationOUT) clienteBanking.call(entradaVO));
			System.out.println("Salida de mapearEarlyPayOffSimulationOUT");

//		} catch (Exception e) {
//			salidaVO = null;
//			e.getStackTrace();
//		}

		return salidaVO;
	}

	private static SalidaCreditoPrepagoVO mapearEarlyPayOffSimulationOUT(SalidaEarlyPayoffSimulationOUT entrada) {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();

		if ( entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//			salidaVO = new SalidaCreditoPrepagoVO();
			salidaVO.setGravamenes(Utils.stringToDouble(entrada.getArrearsAmount().trim()));
//			salidaVO.setGastosDeCobranza(Utils.stringToDouble(entrada.getUnpaidCharge().trim()));
			salidaVO.setGastosDeCobranza(entrada.getUnpaidCharge().trim());
			salidaVO.setSaldoCapital(Utils.stringToDouble(entrada.getRemainingBalance()));
			salidaVO.setTotal(Utils.stringToDouble(entrada.getMontoEpo().trim()));
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
}
