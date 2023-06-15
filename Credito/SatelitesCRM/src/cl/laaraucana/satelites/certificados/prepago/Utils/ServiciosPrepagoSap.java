package cl.laaraucana.satelites.certificados.prepago.Utils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.ClienteConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.EntradaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.SalidaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.ClienteEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.EntradaEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaEarlyPayOffSimulation2;
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

public class ServiciosPrepagoSap {
	
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
	
	public static SalidaListaCreditoPrepagoVO obtenerCreditosVigentesBanking(String rut, String fechaFullEpo,  String flagTipoCredito, String flagTipoCertificado) throws Exception {

		//System.out.println("Ingreso a ClienteQueryCompContHeaderIn");
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		
		entradaVO.setFlagTipoCredito(flagTipoCredito);

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoPrepagoVO salidaVO= new SalidaListaCreditoPrepagoVO();

		SalidaListaQueryCompContHeaderInVO listaSalida = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO);
		
		if (listaSalida.getListaCreditos().size()>0 && listaSalida.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			
			//System.out.println("Ingreso a mapearQueryCompContHeaderIn");
			salidaVO = mapearQueryCompContHeaderIn(rut, listaSalida, fechaFullEpo, flagTipoCertificado);
			//System.out.println("Salida de mapearQueryCompContHeaderIn");
			
			//System.out.println("Código final de error de créditos SAP: "+salidaVO.getCodigoError());
			//System.out.println("Mensaje QueryCompContHeader saliendo de Mapear: "+salidaVO.getMensaje());
//			if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
//				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
//				salidaVO.setMensaje("Obtener Detalle CreditosVigentes Banking OK");
//			}else if (salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)){
//				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
//				salidaVO.setMensaje("No es posible mostrar información asociada a créditos. Favor acercarse a su sucursal más cercana");
//			}
			
		} else if (listaSalida.getListaCreditos().size()==0 && listaSalida.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			salidaVO.setCodigoError(listaSalida.getCodigoError());
			salidaVO.setMensaje(listaSalida.getMensaje());
			
		}else{
			salidaVO.setCodigoError(listaSalida.getCodigoError());
			salidaVO.setMensaje(listaSalida.getMensaje());
		} 
		//System.out.println("Código de Error obtenerCreditosVigentesBanking saliendo de Mapear"+salidaVO.getCodigoError());
		//System.out.println("Mensaje obtenerCreditosVigentesBanking saliendo de Mapear"+salidaVO.getMensaje());
		return salidaVO;
	}
	
	

	private static SalidaListaCreditoPrepagoVO mapearQueryCompContHeaderIn(String rut,SalidaListaQueryCompContHeaderInVO entrada, String fechaFullEpo, String flagTipoCertificado) throws Exception {
		
		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
		SalidaCreditoPrepagoVO credito = null;
		int contador = 0;
		String mensaje="";
		//System.out.println("comienza el mapeo de QueryCompContHeaderIn con codigo de error "+entrada.getCodigoError());
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
				if("S".equalsIgnoreCase(detalle.getPayer()) || "X".equalsIgnoreCase(detalle.getPayer())){
					contador++;
					//System.out.println("Comienzo de mapeo mapearQueryCompContHeaderIn : !! "+detalle.getContractNumber());
					credito = new SalidaCreditoPrepagoVO();
					credito.setFolio(detalle.getContractNumber());
					credito.setHastaCuota(CompletaUtil.quitaCerosIzqString(detalle.getInstallmentQuantity()));
					credito.setFechaOtorgamiento(detalle.getContractBegin());
					credito.setMontoOtorgado(Utils.stringToDouble(detalle.getContractAmount()));
					
					credito.setCodigoError(entrada.getCodigoError());
					credito.setMensaje(entrada.getMensaje());
	
					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						//System.out.println("Ingreso a obtenerCreditoPrepago");
						SalidaCreditoPrepagoVO salidaCompactHeaderInVO = obtenerCreditoPrepago(rut, detalle.getContractNumber());
						//System.out.println("comienza el mapeo de salidaCompactHeader con codigo de error "+salidaCompactHeaderInVO.getCodigoError());
						if (salidaCompactHeaderInVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
	
							credito.setMontoImpuestoLTE(salidaCompactHeaderInVO.getMontoImpuestoLTE());
							credito.setTipoCredito(salidaCompactHeaderInVO.getTipoCredito());
						}
						credito.setCodigoError(salidaCompactHeaderInVO.getCodigoError());
						credito.setMensaje(salidaCompactHeaderInVO.getMensaje());
					}
					
					//Llamada a EARLYPAYOFF1
					/*if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						System.out.println("Ingreso a obtenerDatosEarlyPayOff");
						SalidaCreditoPrepagoVO salidaEarlyVO = obtenerDatosEarlyPayOff(detalle.getContractNumber(), fechaFullEpo, parametro);
						
						System.out.println("comienza el mapeo de salidaEarly con codigo de error "+salidaEarlyVO.getCodigoError());
						if (salidaEarlyVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							credito.setGravamenes(salidaEarlyVO.getGravamenes());
							credito.setGastosDeCobranza("$ "+Utils.formateaDobleSinDecimal(Double.valueOf(salidaEarlyVO.getGastosDeCobranza())));
							credito.setSaldoCapital(salidaEarlyVO.getSaldoCapital());
							credito.setTotal(salidaEarlyVO.getTotal());
						}
						credito.setCodigoError(salidaEarlyVO.getCodigoError());
						credito.setMensaje(salidaEarlyVO.getMensaje());
						System.out.println(credito.getMensaje());
					}*/
					
					//obtiene EARLYPAYOFF2
					
					
					
					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						System.out.println("Ingreso a EarlyPayOffSimulation");
						
						SalidaCreditoPrepagoVO salidaEarlyPayOff2 = obtenerDatosEarlyPayOff2(detalle.getContractNumber(), fechaFullEpo, flagTipoCertificado);
						
						//System.out.println("comienza el mapeo de salidaEarlyPayOff2 con codigo de error "+salidaEarlyPayOff2.getCodigoError());
						
						if(salidaEarlyPayOff2.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							
							credito.setMontoSeguros(salidaEarlyPayOff2.getMontoSeguros());
							credito.setMontoComisionPrepagos(salidaEarlyPayOff2.getMontoComisionPrepagos());
							credito.setMontoInteresMoroso(salidaEarlyPayOff2.getMontoInteresMoroso());
							credito.setMontoInteresDevengado(salidaEarlyPayOff2.getMontoInteresDevengado());
							credito.setCantidadCuotasMorosas(salidaEarlyPayOff2.getCantidadCuotasMorosas());
							credito.setListaFechaPagar(salidaEarlyPayOff2.getListaFechaPagar());
							
							String tipoBp = salidaEarlyPayOff2.getTipoBp();
							credito.setPrimaSegDesgravamen(salidaEarlyPayOff2.getPrimaSegDesgravamen());
							credito.setPrimaSegCesantia(salidaEarlyPayOff2.getPrimaSegCesantia());
							credito.setTipoBp(tipoBp);
							credito.setLineaCredito(salidaEarlyPayOff2.getLineaCredito());
							credito.setMoroso(salidaEarlyPayOff2.getMoroso());
							
							//nuevos
							credito.setDesdeCuota(salidaEarlyPayOff2.getDesdeCuota());
							credito.setCuotasEntransito(salidaEarlyPayOff2.getCuotasEntransito());
							credito.setNumCuotasEnTransito(salidaEarlyPayOff2.getNumCuotasEnTransito());
							credito.setCuotasInformadas(salidaEarlyPayOff2.getCuotasInformadas());
							
							//agregados clillo 22-09-2017
							credito.setTotalDiferido(salidaEarlyPayOff2.getTotalDiferido());
							credito.setHastaCuota(salidaEarlyPayOff2.getHastaCuota());
							credito.setMontoEPO(salidaEarlyPayOff2.getMontoEPO());
							credito.setTotal(salidaEarlyPayOff2.getMontoEPO());
							credito.setGravamenes(salidaEarlyPayOff2.getGravamenes());
							credito.setSaldoCapital(salidaEarlyPayOff2.getSaldoCapital());
							credito.setGastoCobranza(salidaEarlyPayOff2.getGastoCobranza());
							credito.setTotalCuotasInformadas(salidaEarlyPayOff2.getTotalCuotasInformadas());
							
							//agregados clillo 18-07-2018
							credito.setHonorarios(salidaEarlyPayOff2.getHonorarios());
							credito.setMontoSeguroDevengado(salidaEarlyPayOff2.getMontoSeguroDevengado());
							credito.setMontoSeguroMoroso(salidaEarlyPayOff2.getMontoSeguroMoroso());
							credito.setMontoSeguros(salidaEarlyPayOff2.getMontoSeguroDevengado() + salidaEarlyPayOff2.getMontoSeguroMoroso());
						
							/*Calendar cal = Calendar.getInstance();
							
							int diaActual = cal.get(Calendar.DAY_OF_MONTH);
							int diaHabil = getSiguienteDiaHabil(10);
							
							if("X".equalsIgnoreCase(credito.getMoroso())){
								credito.setCuotasInformadas("");
							}else{
								if("Pensionado".equalsIgnoreCase(tipoBp)){
									if(diaActual <= diaHabil){
										credito.setCuotasInformadas(Utils.obtenerPeriodoCualquiera(-1)+"-"+Utils.obtenerPeriodoCualquiera(0)+"-"+Utils.obtenerPeriodoCualquiera(1));
									}else{
										credito.setCuotasInformadas(Utils.obtenerPeriodoCualquiera(0)+"-"+Utils.obtenerPeriodoCualquiera(1));
									}
								}else{
									if(diaActual <= diaHabil){
										credito.setCuotasInformadas(Utils.obtenerPeriodoCualquiera(-1)+"-"+Utils.obtenerPeriodoCualquiera(0));
									}else{
										credito.setCuotasInformadas(Utils.obtenerPeriodoCualquiera(0));
									}
								}
							}*/
							//REQ-8000000314
							credito.setMontoCuota(salidaEarlyPayOff2.getMontoCuota());
						}
						credito.setCodigoError(salidaEarlyPayOff2.getCodigoError());
						credito.setMensaje(salidaEarlyPayOff2.getMensaje());
						//System.out.println(credito.getMensaje());
	
					}
					
					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						//System.out.println("Ingreso a obtenerDatosCashFlowIn");
						SalidaCreditoPrepagoVO salidaCashFlowIn = obtenerDatosCashFlowIn(detalle.getContractNumber(), credito.getMoroso(), credito.getTipoBp());
						
						//System.out.println("comienza el mapeo de salidaCashFlow con codigo de error "+salidaCashFlowIn.getCodigoError());
						if (salidaCashFlowIn.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							if (salidaCashFlowIn.getDesdeCuota() != null) {
								//credito.setDesdeCuota(salidaCashFlowIn.getDesdeCuota());
								credito.setNroCuotaActual(salidaCashFlowIn.getNroCuotaActual());
								
								credito.setCodigoError(salidaCashFlowIn.getCodigoError());
								credito.setMensaje(salidaCashFlowIn.getMensaje());
								
							} else {
								credito.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
								credito.setMensaje("Error: en \"QueryContractCashflowIn\" el credito no corresponde a la fecha actual.");
								System.out.println("Numero de creditos al caer el servicio: "+ contador+ ". Credito no corresponde a la fecha actual");
	//							return salidaVO;
							}
						} else {
							credito.setCodigoError(salidaCashFlowIn.getCodigoError());
							credito.setMensaje(salidaCashFlowIn.getMensaje());
							System.out.println(credito.getMensaje());
							System.out.println("Numero de creditos al caer el servicio: "+ contador);
	//						return salidaVO;
						}
					}
					
					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						//System.out.println("Ingreso a obtenerFolioFormulario24");
						SalidaCreditoPrepagoVO salidaFolioForm24 = obtenerFolioFormulario24(detalle.getContractBegin());
						
						//System.out.println("comienza el mapeo de salidaFolioForm24 con codigo de error "+salidaFolioForm24.getCodigoError());
						
						if(salidaFolioForm24.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							credito.setFolioForm24(salidaFolioForm24.getFolioForm24());
						}
						credito.setCodigoError(salidaFolioForm24.getCodigoError());
						credito.setMensaje(salidaFolioForm24.getMensaje());
						//System.out.println(credito.getMensaje());
	
					}
					
					
					/*if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						
						//if(!credito.getTipoCredito().trim().equals(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap"))){
							credito.setMontoCuota(Utils.stringToDouble(detalle.getInstallmentAmount())); //habilitar cuando el servicio traiga el campo
						}else{
							System.out.println(detalle.getContractNumber()+" "+ credito.getNroCuotaActual()+" "+ rut);
							SalidaCreditoPrepagoVO salidaLoanBalance = obtenerQueryLoanBalance(detalle.getContractNumber(), credito.getNroCuotaActual(), rut);
							System.out.println("comienza el mapeo de salidaLoanBalance con codigo de error "+salidaLoanBalance.getCodigoError());
							if(salidaLoanBalance != null){
								credito.setMontoCuota(salidaLoanBalance.getMontoCuota());
							}
							credito.setCodigoError(salidaLoanBalance.getCodigoError());
							credito.setMensaje(salidaLoanBalance.getMensaje());
							System.out.println(salidaVO.getMensaje());
						}
					}*/
					
					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						listaCreditos.add(credito);
					}
				}else{
					System.out.println("el contrato "+detalle.getContractNumber()+ " no es payer");
					if(credito!= null && !credito.getCodigoError().equals("0")){
						mensaje= credito.getMensaje() + "<BR>El cliente " + rut + " no es pagador del contrato "+detalle.getContractNumber();
					}else{
						mensaje= mensaje + "<BR>El cliente " + rut + " no es pagador del contrato "+detalle.getContractNumber();
					}
				}
			}
			
		salidaVO.setListaCreditos(listaCreditos);
		}
		if (listaCreditos.size()==0){
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
			if(mensaje.equals("")){
				mensaje= credito.getMensaje();
			}
			salidaVO.setMensaje("No es posible mostrar información asociada a créditos." + mensaje);
		}else{
			System.out.println("Numero de creditos al finalizar: "+contador+" de "+entrada.getListaCreditos().size()+" creditos desde el Servicio CompContHeaderIn.");
			salidaVO.setCodigoError(entrada.getCodigoError());
			salidaVO.setMensaje(entrada.getMensaje());
		}
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
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR) || entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO)){
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
	
	
	public static SalidaCreditoPrepagoVO obtenerDatosCashFlowIn(String idContrato, String moroso, String tipoBp) throws Exception{
	
		System.out.println("Ingreso a ClienteQueryContractCashflowIn");
		QueryContractCashFlowRequest entradaVO = new QueryContractCashFlowRequest();
		entradaVO.setNroCuenta(idContrato);
		ClienteQueryContractCashflowIn clienteBanking = new ClienteQueryContractCashflowIn();
		SalidaCreditoPrepagoVO salidaVO = null;
		System.out.println("Salida de ClienteQueryContractCashflowIn");
		
//		try {
			System.out.println("Comienzo mapearQueryCashFlowIn");
			salidaVO = mapearQueryCashFlowIn((SalidaListaQueryContractCashflowInVO)clienteBanking.call(entradaVO), moroso, tipoBp);
			System.out.println("Salida mapearQueryCashFlowIn");
			
//		} catch (Exception e) {
//			salidaVO = null;
//			e.printStackTrace();
//		}
		
		return salidaVO;
	}
	
	private static SalidaCreditoPrepagoVO mapearQueryCashFlowIn(SalidaListaQueryContractCashflowInVO entrada, String moroso, String tipoBp) throws Exception {
//		SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();
		salidaVO.setDesdeCuota("");
		String periodo = Utils.getFechaPeriodo();
		int periodoInt = Integer.parseInt(periodo);
		//if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			//entrada.setResultCode(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			for (SalidaQueryContractCashflowInVO listaCuotas : entrada.getListaCuotas()) {
				String fechaVence=Utils.pasaFechaSAPaAs400(listaCuotas.getFechaVencCuota());
				if(periodo.equals(fechaVence)){
					System.out.println("el valor cuota es "+listaCuotas.getNroCuota());
					salidaVO.setNroCuotaActual(String.valueOf(listaCuotas.getNroCuota()));
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
	
	
	public static SalidaCreditoPrepagoVO obtenerDatosEarlyPayOff(String idContrato, String fechaFullEpo, String parametro) throws Exception {
		
		System.out.println("Ingreso a ClienteEarlyPayoffSimulationOUT");
		EntradaEarlyPayoffSimulationOUT entradaVO = new EntradaEarlyPayoffSimulationOUT();
		entradaVO.setIdContrato(idContrato);
		entradaVO.setFechaFullEpo(fechaFullEpo);
		entradaVO.setParametro(parametro);
		
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
			salidaVO.setFolio(entrada.getContractId());
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
	
public static SalidaCreditoPrepagoVO obtenerDatosEarlyPayOff2(String idContrato,String fechaFullEpo ,String parametro) throws Exception {
		
		System.out.println("Ingreso a ClienteEarlyPayoffSimulationOUT");
		EntradaEarlyPayOffSimulation2 entradaVO = new EntradaEarlyPayOffSimulation2();
		entradaVO.setContrato(idContrato);
		entradaVO.setTipoPrepago(parametro);
		entradaVO.setFechaFullEpo(fechaFullEpo);
		
		ClienteEarlyPayOffSimulation2 clienteEarly2 = new ClienteEarlyPayOffSimulation2();
		SalidaCreditoPrepagoVO salidaVO = null;
		System.out.println("Salida de ClienteEarlyPayoffSimulationOUT");
		
//		try {
			System.out.println("Ingreso a mapearEarlyPayOffSimulationOUT");
			salidaVO = mapearEarlyPayOffSimulation2((SalidaEarlyPayOffSimulation2) clienteEarly2.call(entradaVO));
			System.out.println("Salida de mapearEarlyPayOffSimulationOUT");

//		} catch (Exception e) {
//			salidaVO = null;
//			e.getStackTrace();
//		}

		return salidaVO;
	}

	private static SalidaCreditoPrepagoVO mapearEarlyPayOffSimulation2(SalidaEarlyPayOffSimulation2 entrada) {
	//	SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();
	
		if ( entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			
			salidaVO.setMontoComisionPrepagos(Utils.stringToDouble(entrada.getMontoComPrep()));
			salidaVO.setMontoInteresMoroso(Utils.stringToDouble(entrada.getMontoIntMoroso()));
			salidaVO.setMontoInteresDevengado(Utils.stringToDouble(entrada.getMontoIntDVG()));
			salidaVO.setCantidadCuotasMorosas(Utils.stringToDouble(entrada.getCuotasMorosas()));
			salidaVO.setListaFechaPagar(entrada.getDetalleCuotas());
			
			//nuevos
			salidaVO.setPrimaSegDesgravamen(Utils.stringToDouble(entrada.getSegDesgravamen()));
			salidaVO.setPrimaSegCesantia(Utils.stringToDouble(entrada.getSegCesantia()));
			salidaVO.setTipoBp(entrada.getTipoBp());
			salidaVO.setLineaCredito(entrada.getLineaCredito());
			salidaVO.setMoroso(entrada.getMoroso());
			
			salidaVO.setDesdeCuota(entrada.getCuotaDesde());
			salidaVO.setCuotasEntransito(entrada.getCuotasEnTransito());
			salidaVO.setNumCuotasEnTransito(entrada.getNumCuotasEnTransito());
			salidaVO.setCuotasInformadas(entrada.getCuotasInformadas());
			salidaVO.setMontoCuota(Utils.stringToDouble(entrada.getMontoCuota()));
			
			//agregadas clillo 22-09-2017
			salidaVO.setMontoCuota(Utils.stringToDouble(entrada.getMontoCuota()).doubleValue());
            salidaVO.setTotalDiferido(Utils.stringToDouble(entrada.getTotalDiferido()).doubleValue());
            salidaVO.setMontoEPO(Utils.stringToDouble(entrada.getMontoEPO()).doubleValue());
            salidaVO.setTotalCuotasInformadas(Utils.stringToDouble(entrada.getTotalCuotasInformadas()).doubleValue());
            salidaVO.setHastaCuota(entrada.getCuotaHasta());
            salidaVO.setGravamenes(Utils.stringToDouble(entrada.getGravamenes()).doubleValue());
            salidaVO.setGastoCobranza(Utils.stringToDouble(entrada.getGastoCobranza()).doubleValue());
            salidaVO.setSaldoCapital(Utils.stringToDouble(entrada.getSaldoCapital()).doubleValue());

			salidaVO.setCuotasInformadas(entrada.getCuotasInformadas());
			
			//clillo 18-07-2018
			salidaVO.setHonorarios(Utils.stringToDouble(entrada.getHonorarios()).doubleValue());
			salidaVO.setMontoSeguroDevengado(Utils.stringToDouble(entrada.getMontoSeguroDevengado()).doubleValue());
			salidaVO.setMontoSeguroMoroso(Utils.stringToDouble(entrada.getMontoSeguroMoroso()).doubleValue());
			
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}


	public static int getSiguienteDiaHabil(int dia){
		
		
		Calendar calAhora = Calendar.getInstance();
		
		int diaActual = calAhora.get(Calendar.DAY_OF_MONTH);
		
		if(diaActual == dia){
			if(calAhora.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ){
				dia = dia+2;
			}
			if(calAhora.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				dia = dia+1;
			}
		}
		
		return dia;
		
				
	}
	
}
