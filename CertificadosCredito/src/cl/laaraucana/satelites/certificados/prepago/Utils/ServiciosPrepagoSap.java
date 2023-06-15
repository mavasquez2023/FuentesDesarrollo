package cl.laaraucana.satelites.certificados.prepago.Utils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.ClienteConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.EntradaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.SalidaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.ClienteEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.EntradaEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.ClienteQueryBPStatusOUT;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
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
	
	protected static Logger log = Logger.getLogger(ServiciosPrepagoSap.class);
	
	public static SalidaListaAfiliadoVO obtenerAfiliado(String rut) throws Exception {
		EntradaAfiliadoVO entradaVO = new EntradaAfiliadoVO();
		entradaVO.setRut(rut);
		log.info("Ingreso a obtenerAfiliado, rut: " + rut);
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
	
	public static SalidaListaCreditoPrepagoVO obtenerCreditosVigentesBanking(String rut, String fechaFullEpo, String flagTipoCredito, String flagTipoCertificado) throws Exception {

		log.info("Ingreso a ClienteQueryCompContHeaderIn");
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(flagTipoCredito);// 1: obtiene creditos vigentes.

		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditoPrepagoVO salidaVO= new SalidaListaCreditoPrepagoVO();

		SalidaListaQueryCompContHeaderInVO listaSalida = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO);
		
		if (listaSalida.getListaCreditos().size()>0 && listaSalida.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			
			log.info("Ingreso a mapearQueryCompContHeaderIn");
			salidaVO = mapearQueryCompContHeaderIn(rut, listaSalida, fechaFullEpo, flagTipoCertificado);
			log.info("Salida de mapearQueryCompContHeaderIn");
			
			log.info("Código final de error de créditos SAP: "+salidaVO.getCodigoError());
			log.info("Mensaje QueryCompContHeader saliendo de Mapear: "+salidaVO.getMensaje());
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
		log.info("Código de Error obtenerCreditosVigentesBanking saliendo de Mapear"+salidaVO.getCodigoError());
		log.info("Mensaje obtenerCreditosVigentesBanking saliendo de Mapear"+salidaVO.getMensaje());
		return salidaVO;
	}
	
	

	private static SalidaListaCreditoPrepagoVO mapearQueryCompContHeaderIn(String rut,SalidaListaQueryCompContHeaderInVO entrada, String fechaFullEpo, String flagTipoCertificado) throws Exception {
		
		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
		SalidaCreditoPrepagoVO credito = null;
		int contador = 0;
		int num_insolvencia=0;
		String mensaje="";
		log.info("comienza el mapeo de QueryCompContHeaderIn con codigo de error "+entrada.getCodigoError());
		if(entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			for (SalidaQueryCompContHeaderInVO detalle : entrada.getListaCreditos()) {
				//if("S".equalsIgnoreCase(detalle.getPayer()) || "X".equalsIgnoreCase(detalle.getPayer())){
				boolean insolvente= false;
				if(detalle.getInsolvencia()!=null && detalle.getInsolvencia().equals("X")){
					insolvente=true;
				}
				if(!flagTipoCertificado.equals(ServiciosConst.CERTIFICADO_DEUDA) &&  !insolvente || flagTipoCertificado.equals(ServiciosConst.CERTIFICADO_DEUDA) && insolvente){
					contador++;
					log.info("Comienzo de mapeo mapearQueryCompContHeaderIn : !! "+detalle.getContractNumber());
					credito = new SalidaCreditoPrepagoVO();
					credito.setFolio(detalle.getContractNumber());
					credito.setHastaCuota(CompletaUtil.quitaCerosIzqString(detalle.getInstallmentQuantity()));
					credito.setFechaOtorgamiento(detalle.getContractBegin());
					credito.setMontoOtorgado(Utils.stringToDouble(detalle.getContractAmount()));
					
					//agregado J-factory 04-12-2017
					String rol = detalle.getRole();
					rol= rol.equals("BCA010")?"Titular":"Aval";
					credito.setRol(rol);
					String pagador= detalle.getPayer();
					pagador= pagador.equals("X")?"Pagador":"No Pagador";
					credito.setPagador(pagador);
					//agregado J-factory 06-12-2017
					credito.setCastigo(detalle.getCastigo());
					
					credito.setCodigoError(entrada.getCodigoError());
					credito.setMensaje(entrada.getMensaje());

					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						log.info("Ingreso a obtenerCreditoPrepago");
						SalidaCreditoPrepagoVO salidaCompactHeaderInVO = obtenerCreditoPrepago(rut, detalle.getContractNumber());
						log.info("comienza el mapeo de salidaCompactHeader con codigo de error "+salidaCompactHeaderInVO.getCodigoError());
						if (salidaCompactHeaderInVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {

							credito.setMontoImpuestoLTE(salidaCompactHeaderInVO.getMontoImpuestoLTE());
							credito.setTipoCredito(salidaCompactHeaderInVO.getTipoCredito());
						}
						credito.setCodigoError(salidaCompactHeaderInVO.getCodigoError());
						credito.setMensaje(salidaCompactHeaderInVO.getMensaje());
					}


					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						log.info("Ingreso a EarlyPayOffSimulation");

						SalidaCreditoPrepagoVO salidaEarlyPayOff2 = obtenerDatosEarlyPayOff2(detalle.getContractNumber(), fechaFullEpo, flagTipoCertificado);
						//SalidaCreditoPrepagoVO salidaEarlyPayOff2 = obtenerDatosEarlyPayOff2Manual(detalle.getContractNumber(), fechaFullEpo, flagTipoCertificado);
						log.info("comienza el mapeo de salidaEarlyPayOff2 con codigo de error "+salidaEarlyPayOff2.getCodigoError());

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
							credito.setMontoCuotasEntransito(salidaEarlyPayOff2.getMontoCuotasEntransito());
							credito.setNumCuotasEnTransito(salidaEarlyPayOff2.getNumCuotasEnTransito());
							credito.setCuotasInformadas(salidaEarlyPayOff2.getCuotasInformadas());

							//agregado J-Factory 22-09-2017
							credito.setTotalDiferido(salidaEarlyPayOff2.getTotalDiferido());
							credito.setHastaCuota(salidaEarlyPayOff2.getHastaCuota());
							credito.setMontoEPO(salidaEarlyPayOff2.getMontoEPO());
							//agregado J-Factory 11-12-2019
							//credito.setTotal(salidaEarlyPayOff2.getMontoEPO());
							credito.setGravamenes(salidaEarlyPayOff2.getGravamenes());
							credito.setSaldoCapital(salidaEarlyPayOff2.getSaldoCapital());
							credito.setGastoCobranza(salidaEarlyPayOff2.getGastoCobranza());
							credito.setTotalCuotasInformadas(salidaEarlyPayOff2.getTotalCuotasInformadas());
							credito.setMontoCuota(salidaEarlyPayOff2.getMontoCuota());
							//agregado clillo 20-07-2018
							credito.setHonorarios(salidaEarlyPayOff2.getHonorarios());
							
							//agregado J-Factory 04-12-2019
							credito.setEstado(salidaEarlyPayOff2.getEstado());
							credito.setSaldoCapitalCondonado(salidaEarlyPayOff2.getSaldoCapitalCondonado());
							
							//modificado J-Factory 30-06-2020
							credito.setSaldoAdeudado(salidaEarlyPayOff2.getSaldoAdeudado());
							credito.setSaldoCapitalFuturo(salidaEarlyPayOff2.getSaldoCapitalFuturo());
							credito.setMontoFinalAdeudado(salidaEarlyPayOff2.getMontoFinalAdeudado());
							credito.setTotal(salidaEarlyPayOff2.getTotal());
							credito.setTasaInteres(salidaEarlyPayOff2.getTasaInteres());

						}
						credito.setCodigoError(salidaEarlyPayOff2.getCodigoError());
						credito.setMensaje(salidaEarlyPayOff2.getMensaje());
						log.info(credito.getMensaje());

					}

					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						log.info("Ingreso a obtenerDatosCashFlowIn");
						SalidaCreditoPrepagoVO salidaCashFlowIn = obtenerDatosCashFlowIn(detalle.getContractNumber(), credito.getMoroso(), credito.getTipoBp());

						log.info("comienza el mapeo de salidaCashFlow con codigo de error "+salidaCashFlowIn.getCodigoError());
						if (salidaCashFlowIn.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
							if (salidaCashFlowIn.getDesdeCuota() != null) {
								//credito.setDesdeCuota(salidaCashFlowIn.getDesdeCuota());
								credito.setNroCuotaActual(salidaCashFlowIn.getNroCuotaActual());
								//agregado J-Factory 4-12-2019
								credito.setCuotasPagadas(salidaCashFlowIn.getCuotasPagadas());
								credito.setCuotasMorosas(salidaCashFlowIn.getCuotasMorosas());
								credito.setCuotasFuturas(salidaCashFlowIn.getCuotasFuturas());
								credito.setPlazo(salidaCashFlowIn.getPlazo());
								//agregado J-Factory 11-12-2019
								double tasa_cond_gcob= 1.0;
								double tasa_cond_hon= 1.0;
								if(salidaCashFlowIn.getCondonacion()!= null){
									tasa_cond_gcob= 1-salidaCashFlowIn.getCondonacion().getCond_gasto_cobranza()/100;
									tasa_cond_hon= 1-salidaCashFlowIn.getCondonacion().getCond_honorarios()/100;
								}
								//modificado J-Factory 30-06-2020 ahora viene en servicio EarlyPay
								//credito.setSaldoAdeudado(salidaCashFlowIn.getSaldoAdeudado()+Math.round(credito.getGastoCobranza()*tasa_cond_gcob + credito.getHonorarios()*tasa_cond_hon));
								//credito.setSaldoCapitalFuturo(salidaCashFlowIn.getSaldoCapitalFuturo());
								//credito.setMontoFinalAdeudado(credito.getSaldoAdeudado()+credito.getMontoInteresDevengado()+salidaCashFlowIn.getSaldoCapitalFuturo()+credito.getMontoComisionPrepagos());
								//credito.setTotal(credito.getMontoEPO()-credito.getSaldoCapitalCondonado());
								
								//modificado J-Factory 08-07-2020 lo calcula SAP
								/*if(credito.getEstado().equals(Constants.CREDITO_CASTIGADO)){
									if(credito.getSaldoCapitalCondonado()>0){
										//credito.setTotal(credito.getTotal()+credito.getSaldoAdeudado());
										List<SalidaDetalleCuotasEarlyPayOff2> fechasPago= credito.getListaFechaPagar();
										for (Iterator iterator = fechasPago.iterator(); iterator.hasNext();) {
											SalidaDetalleCuotasEarlyPayOff2 totalFechasFuturas = (SalidaDetalleCuotasEarlyPayOff2) iterator.next();
											if(totalFechasFuturas.getIncluyeCuotasTransito().equals("") ){
												totalFechasFuturas.setTotalAPagar(totalFechasFuturas.getTotalAPagar() + credito.getSaldoAdeudado());
											}
										}
									}
								}*/
								
								credito.setCodigoError(salidaCashFlowIn.getCodigoError());
								credito.setMensaje(salidaCashFlowIn.getMensaje());

							} else {
								credito.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
								credito.setMensaje("Error: en \"QueryContractCashflowIn\" el credito no corresponde a la fecha actual.");
								log.info("Numero de creditos al caer el servicio: "+ contador+ ". Credito no corresponde a la fecha actual");
								//							return salidaVO;
							}
						} else {
							credito.setCodigoError(salidaCashFlowIn.getCodigoError());
							credito.setMensaje(salidaCashFlowIn.getMensaje());
							log.info(credito.getMensaje());
							log.info("Numero de creditos al caer el servicio: "+ contador);
							//						return salidaVO;
						}
					}

					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						log.info("Ingreso a obtenerFolioFormulario24");
						SalidaCreditoPrepagoVO salidaFolioForm24 = obtenerFolioFormulario24(detalle.getContractBegin());

						log.info("comienza el mapeo de salidaFolioForm24 con codigo de error "+salidaFolioForm24.getCodigoError());

						if(salidaFolioForm24.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
							credito.setFolioForm24(salidaFolioForm24.getFolioForm24());
						}
						credito.setCodigoError(salidaFolioForm24.getCodigoError());
						credito.setMensaje(salidaFolioForm24.getMensaje() + ",Folio:" + credito.getFolio());
						log.info(credito.getMensaje());

					}


					/*if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {

						//if(!credito.getTipoCredito().trim().equals(CertificadoConst.RES_CERTIFICADOS.getString("certificado.tipoCredito.educacionSuperior.sap"))){
							credito.setMontoCuota(Utils.stringToDouble(detalle.getInstallmentAmount())); //habilitar cuando el servicio traiga el campo
						}else{
							log.info(detalle.getContractNumber()+" "+ credito.getNroCuotaActual()+" "+ rut);
							SalidaCreditoPrepagoVO salidaLoanBalance = obtenerQueryLoanBalance(detalle.getContractNumber(), credito.getNroCuotaActual(), rut);
							log.info("comienza el mapeo de salidaLoanBalance con codigo de error "+salidaLoanBalance.getCodigoError());
							if(salidaLoanBalance != null){
								credito.setMontoCuota(salidaLoanBalance.getMontoCuota());
							}
							credito.setCodigoError(salidaLoanBalance.getCodigoError());
							credito.setMensaje(salidaLoanBalance.getMensaje());
							log.info(salidaVO.getMensaje());
						}
					}*/

					if (credito.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
						listaCreditos.add(credito);
					}
					/*}else{
					log.info("el contrato "+detalle.getContractNumber()+ " no es payer");
					if(credito!= null && !credito.getCodigoError().equals("0")){
						mensaje= credito.getMensaje() + "<BR>El cliente " + rut + " no es pagador del contrato "+detalle.getContractNumber();
					}else{
						mensaje= mensaje + "<BR>El cliente " + rut + " no es pagador del contrato "+detalle.getContractNumber();
					}

				}*/
				}else{
					//Con insolvencia
					num_insolvencia++;
				}
			}
			
		salidaVO.setListaCreditos(listaCreditos);
		}
		if (listaCreditos.size()==0 && num_insolvencia==0){
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
			salidaVO.setMensaje("No es posible mostrar información asociada a créditos. " + mensaje );
		}else if (listaCreditos.size()==0 && num_insolvencia>0 && !flagTipoCertificado.equals(ServiciosConst.CERTIFICADO_DEUDA)){
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
			salidaVO.setMensaje("No es posible generar el certificado porque el contrato tiene marca de insolvencia" );
		}else if (listaCreditos.size()==0 && num_insolvencia>0 && flagTipoCertificado.equals(ServiciosConst.CERTIFICADO_DEUDA)){
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
			salidaVO.setMensaje("No es posible generar el certificado porque el contrato NO tiene marca de insolvencia" );
		}else{
			log.info("Numero de creditos al finalizar: "+contador+" de "+entrada.getListaCreditos().size()+" creditos desde el Servicio CompContHeaderIn.");
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
		log.info("el número de la cuota es "+nroCuota);
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
		
		log.info("Ingreso a ClienteFolioFormulario24");
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
			log.info("Comienzo mapearFolioFormulario24");
			salidaVO = mapearFolioFormulario24((SalidaConsultaFolioFormulario24)clienteForm24.call(entradaVO));
			log.info("Salida mapearFolioFormulario24");
			
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
	
		log.info("Ingreso a ClienteQueryContractCashflowIn");
		QueryContractCashFlowRequest entradaVO = new QueryContractCashFlowRequest();
		entradaVO.setNroCuenta(idContrato);
		ClienteQueryContractCashflowIn clienteBanking = new ClienteQueryContractCashflowIn();
		SalidaCreditoPrepagoVO salidaVO = null;
		log.info("Salida de ClienteQueryContractCashflowIn");
		
//		try {
			log.info("Comienzo mapearQueryCashFlowIn");
			salidaVO = mapearQueryCashFlowIn((SalidaListaQueryContractCashflowInVO)clienteBanking.call(entradaVO), moroso, tipoBp);
			log.info("Salida mapearQueryCashFlowIn");
			
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
		int periodoActual = Integer.parseInt(periodo);
		//modificado J-Factory 30-06-2020 viene por SAP ahora
		//int saldoAdeudado=0;
		//int saldoCapitalFuturo=0;
		if (entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			
			for (SalidaQueryContractCashflowInVO cuota : entrada.getListaCuotas()) {
				//fechaVence en fomato AAAAMMDD
				String fechaVence=Utils.pasaFechaSAPaAs400(cuota.getFechaVencCuota());
				int periodoVencimientoCuota= Integer.parseInt(fechaVence.substring(0, 6));
				if(periodoVencimientoCuota==periodoActual){
					log.info("el valor cuota es "+cuota.getNroCuota());
					salidaVO.setNroCuotaActual(cuota.getNroCuota());
					//modificado J-Factory 30-06-2020 viene por SAP ahora
					//saldoAdeudado+=Utils.stringToDouble(cuota.getMontoCapital()) + Utils.stringToDouble(cuota.getMontoSeguros());
					//break;
				}
				//modificado J-Factory 30-06-2020 viene por SAP ahora
				/*else if(periodoVencimientoCuota<periodoActual){
					saldoAdeudado+= Utils.stringToDouble(cuota.getTotalCuota()) - Utils.stringToDouble(cuota.getMonto_pagado()) + Math.round(Utils.stringToDouble(cuota.getMontoGravamenes()) * (1-entrada.getCondonacion().getCond_gravamenes()/100));
				}else if(periodoVencimientoCuota>periodoActual){
					saldoCapitalFuturo+= Utils.stringToDouble(cuota.getMontoCapital());
				}*/
			}
			//modificado J-Factory 30-06-2020 viene por SAP ahora
			//salidaVO.setSaldoAdeudado(saldoAdeudado);
			//salidaVO.setSaldoCapitalFuturo(saldoCapitalFuturo);
			salidaVO.setCuotasPagadas(entrada.getCuotasPagadas());
			salidaVO.setCuotasMorosas(entrada.getCuotasMorosas());
			salidaVO.setCuotasFuturas(entrada.getCuotasFuturas());
			salidaVO.setPlazo(entrada.getCantidadTotalCuotas());
			salidaVO.setCondonacion(entrada.getCondonacion());
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		return salidaVO;
	}
	
	public static SalidaCreditoPrepagoVO obtenerCreditoPrepago(String rut,String acnum_ext) throws Exception {
		
		log.info("Ingreso a ClienteQueryContractHeaderIn");
		EntradaQueryContractHeaderInVO entradaVO = new EntradaQueryContractHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setAcnum_ext(acnum_ext);
		ClienteQueryContractHeaderIn clienteBanking = new ClienteQueryContractHeaderIn();
		SalidaCreditoPrepagoVO salidaVO = null;
		log.info("Salida de ClienteQueryContractHeaderIn");
		
//		try {
			log.info("Comienzo mapearQueryContractHeaderIn");
			salidaVO = mapearQueryContractHeaderIn((SalidaQueryContractHeaderInVO) clienteBanking.call(entradaVO));
			log.info("Salida mapearQueryContractHeaderIn");

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
	
	
public static SalidaCreditoPrepagoVO obtenerDatosEarlyPayOff2(String idContrato,String fechaFullEpo ,String parametro) throws Exception {
		
		log.info("Ingreso a ClienteEarlyPayoffSimulationOUT");
		EntradaEarlyPayOffSimulation2 entradaVO = new EntradaEarlyPayOffSimulation2();
		entradaVO.setContrato(idContrato);
		entradaVO.setTipoPrepago(parametro);
		entradaVO.setFechaFullEpo(fechaFullEpo);
		
		ClienteEarlyPayOffSimulation2 clienteEarly2 = new ClienteEarlyPayOffSimulation2();
		SalidaCreditoPrepagoVO salidaVO = null;
		log.info("Salida de ClienteEarlyPayoffSimulationOUT");
		
//		try {
			log.info("Ingreso a mapearEarlyPayOffSimulationOUT");
			salidaVO = mapearEarlyPayOffSimulation2((SalidaEarlyPayOffSimulation2) clienteEarly2.call(entradaVO));
			log.info("Salida de mapearEarlyPayOffSimulationOUT");

//		} catch (Exception e) {
//			salidaVO = null;
//			e.getStackTrace();
//		}

		return salidaVO;
	}

public static SalidaCreditoPrepagoVO obtenerDatosEarlyPayOff2Manual(String idContrato,String fechaFullEpo ,String parametro) throws Exception {
	
	log.info("Ingreso a ClienteEarlyPayoffSimulationOUT Manual");

	SalidaCreditoPrepagoVO salidaVO = null;
	log.info("Salida de ClienteEarlyPayoffSimulationOUT");
	
//	try {
		log.info("Ingreso a mapearEarlyPayOffSimulationOUT");
		SalidaEarlyPayOffSimulation2 entradaManual= new SalidaEarlyPayOffSimulation2();
		entradaManual.setCuotaDesde("28-02-2018");
		entradaManual.setCuotaHasta("31-03-2020");
		entradaManual.setCuotasInformadas("0");
		entradaManual.setCuotasMorosas("22");
		entradaManual.setEstadoCredito("4");
		entradaManual.setGastoCobranza("0");
		entradaManual.setGravamenes("0");
		entradaManual.setHonorarios("0");
		entradaManual.setLineaCredito("CA_NORM");
		entradaManual.setMontoComPrep("1523");
		entradaManual.setMontoCuota("45890");
		entradaManual.setMontoCuotasEnTransito("0");
		entradaManual.setMontoEPO("1164797");
		entradaManual.setMontoIntDVG("1326");
		entradaManual.setMontoIntMoroso("221717");
		entradaManual.setMontoSeguros("92856");	
		entradaManual.setMoroso("X");
		entradaManual.setNumCuotasEnTransito("");
		entradaManual.setSaldoCapital("847375");
		entradaManual.setSaldoCapitalCondonado("0");
		entradaManual.setSegCesantia("1338");
		entradaManual.setSegDesgravamen("2531");
		entradaManual.setTipoBp("Deudor Directo");
		entradaManual.setTotalCuotasInformadas("");
		entradaManual.setTotalDiferido("0");
		entradaManual.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
		
		ArrayList<SalidaDetalleCuotasEarlyPayOff2> lista = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		SalidaDetalleCuotasEarlyPayOff2 detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("17-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1164797);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("20-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165031);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("21-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165109);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("22-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165187);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("23-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165265);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("24-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165343);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("27-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165577);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("28-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165655);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("29-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165733);
		lista.add(detCuota);
		detCuota = new SalidaDetalleCuotasEarlyPayOff2();
		detCuota.setFechaDePago("30-01-2020");
		detCuota.setIncluyeCuotasTransito("X");
		detCuota.setTotalAPagar(1165810);
		lista.add(detCuota);
		entradaManual.setDetalleCuotas(lista);
		salidaVO = mapearEarlyPayOffSimulation2(entradaManual);
		log.info("Salida de mapearEarlyPayOffSimulationOUT");

//	} catch (Exception e) {
//		salidaVO = null;
//		e.getStackTrace();
//	}

	return salidaVO;
}

	private static SalidaCreditoPrepagoVO mapearEarlyPayOffSimulation2(SalidaEarlyPayOffSimulation2 entrada) {
	//	SalidaCreditoPrepagoVO salidaVO = null;
		SalidaCreditoPrepagoVO salidaVO = new SalidaCreditoPrepagoVO();
	
		if ( entrada.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
			salidaVO.setMontoSeguros(Utils.stringToDouble(entrada.getMontoSeguros()));
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
			salidaVO.setMontoCuotasEntransito(Utils.stringToDouble(entrada.getMontoCuotasEnTransito()));
			salidaVO.setNumCuotasEnTransito(entrada.getNumCuotasEnTransito());
			salidaVO.setCuotasInformadas(entrada.getCuotasInformadas());
			
			//agregado J-Factory 22-09-2017
			salidaVO.setMontoCuota(Utils.stringToDouble(entrada.getMontoCuota()).doubleValue());
			salidaVO.setTotalDiferido(Utils.stringToDouble(entrada.getTotalDiferido()).doubleValue());
			salidaVO.setMontoEPO(Utils.stringToDouble(entrada.getMontoEPO()).doubleValue());
			//salidaVO.setTotalCuotasInformadas(Utils.stringToDouble(entrada.getTotalCuotasInformadas()).doubleValue());
			salidaVO.setHastaCuota(entrada.getCuotaHasta());
			salidaVO.setGravamenes(Utils.stringToDouble(entrada.getGravamenes()).doubleValue());
			salidaVO.setGastoCobranza(Utils.stringToDouble(entrada.getGastoCobranza()).doubleValue());
			salidaVO.setSaldoCapital(Utils.stringToDouble(entrada.getSaldoCapital()).doubleValue());
			
			//agregado J-Factory 20-07-2018
			salidaVO.setHonorarios(Utils.stringToDouble(entrada.getHonorarios()).doubleValue());
			
			//agregado J-Factory 04-12-2019
			salidaVO.setEstado(entrada.getEstadoCredito());
			salidaVO.setSaldoCapitalCondonado(Utils.stringToDouble(entrada.getSaldoCapitalCondonado()));
			
			//agregado J-Factory 30-06-2020
			salidaVO.setSaldoAdeudado(Utils.stringToDouble(entrada.getSaldoAdeudado()));
			salidaVO.setSaldoCapitalFuturo(Utils.stringToDouble(entrada.getSaldoCapitalCuotasFuturas()));
			salidaVO.setMontoFinalAdeudado(Utils.stringToDouble(entrada.getMontoFinalAdeudado()));
			salidaVO.setTotal(Utils.stringToDouble(entrada.getTotalAPagar()));
			salidaVO.setTasaInteres(Utils.stringToDouble(entrada.getTasaInteres()));
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
