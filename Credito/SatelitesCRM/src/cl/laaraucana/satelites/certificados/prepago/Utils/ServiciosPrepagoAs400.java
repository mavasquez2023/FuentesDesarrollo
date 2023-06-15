package cl.laaraucana.satelites.certificados.prepago.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.laaraucana.satelites.Utils.CompletaUtil;
import cl.laaraucana.satelites.Utils.Constants;
import cl.laaraucana.satelites.Utils.UtilCuota;
import cl.laaraucana.satelites.Utils.UtilRepactarTabla;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaCreditoPrepagoVO;
import cl.laaraucana.satelites.certificados.prepago.VO.SalidaListaCreditoPrepagoVO;
import cl.laaraucana.satelites.main.VO.RepactarTablaVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.ClienteCreditoARepactarAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.EntradaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaListaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.ClienteConsultaCreditosPorRutEnAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.EntradaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaListaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.ClienteConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.EntradaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.SalidaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class ServiciosPrepagoAs400 {

	public static SalidaListaCreditoPrepagoVO obtenerCreditosPrepagoAs400(String rut) throws Exception  {

		SalidaListaCreditoPrepagoVO salidaVO = new SalidaListaCreditoPrepagoVO();
		SalidaListaConsultaCreditosPorRutEnAs400VO salidaPorRutVO = new SalidaListaConsultaCreditosPorRutEnAs400VO();
		SalidaListaConsultaCreditosPorRutEnAs400VO salidaPorRutWs = new SalidaListaConsultaCreditosPorRutEnAs400VO();
		SalidaListaCreditoARepactarVO salidaRepactarVO= new SalidaListaCreditoARepactarVO();
		
		System.out.println("Ingreso a ServiciosPrepagoAs400");
		
		//Recupera los datos del servicioClienteCreditoARepactarAs400
		
		ClienteCreditoARepactarAs400 creditosARepactar = new ClienteCreditoARepactarAs400();
		
		EntradaCreditoARepactarVO entradaRepactaWS = new EntradaCreditoARepactarVO();
		entradaRepactaWS.setRut(Utils.obtenerValorAnteriorA(rut, "-"));
		entradaRepactaWS.setFechaColocacion(Utils.getFechaHoyAs400());
		entradaRepactaWS.setPeriodoRepactacion(Utils.getFechaPeriodo());
		
		System.out.println("LLamada a ws CreditosAS400 con rut de entrada :"+entradaRepactaWS.getRut());
		
		salidaRepactarVO = (SalidaListaCreditoARepactarVO) creditosARepactar.call(entradaRepactaWS);
		
		if(salidaRepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			//Recupera los datos del servicio ClienteCreditoPorRutEnAs400
			ClienteConsultaCreditosPorRutEnAs400 creditosPorRut = new ClienteConsultaCreditosPorRutEnAs400();
			
			EntradaConsultaCreditosPorRutEnAs400VO entradaPorRut = new EntradaConsultaCreditosPorRutEnAs400VO();
			entradaPorRut.setRut(rut);
			System.out.println("LLamada a ws CreditosAS400 con rut de entrada :"+entradaPorRut.getRut());
			
			salidaPorRutWs = (SalidaListaConsultaCreditosPorRutEnAs400VO) creditosPorRut.call(entradaPorRut);
			salidaPorRutVO = PrepagoUtil.getPorRutVigentesRepactar(salidaPorRutWs);
		}
			
		if(salidaRepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS) && 
				salidaPorRutVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(salidaRepactarVO,salidaPorRutVO);
			if(salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				if(salidaVO.getListaCreditos().size() == 0){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
					salidaVO.setMensaje("ServiciosPrepagoAs400 retorna lista vacia");
					System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("Carga de creditos ServiciosPrepagoAs400 OK");
					System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
				}
			}else{
				if(salidaVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR)){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("ServiciosPrepagoAs400 inconcistencia en los datos. "+ salidaVO.getMensaje());
					System.out.println("Codigo de error: "+salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje(salidaVO.getMensaje());
					System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
				}
			}
		}else{
			if(!salidaRepactarVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(salidaRepactarVO.getMensaje());
				System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
			}else if (!salidaPorRutVO.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(salidaPorRutVO.getMensaje());
				System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje(Constants.DATA_INCONSISTENCY);
				System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
			}
		}
		return salidaVO;
	}
		
	private static SalidaListaCreditoPrepagoVO mapear(	SalidaListaCreditoARepactarVO salidaRepactarVO, 
														SalidaListaConsultaCreditosPorRutEnAs400VO salidaPorRutVO) throws Exception{
		System.out.println("Ingreso a mapeo de datos ClienteCreditoPrepagoAs400");
		
		SalidaCreditoPrepagoVO credito=null;
		SalidaListaCreditoPrepagoVO salidaVO= null;
		List<SalidaCreditoPrepagoVO> listaCreditos = new ArrayList<SalidaCreditoPrepagoVO>();
		SalidaConsultaFolioFormulario24 formFolio24 = new SalidaConsultaFolioFormulario24();
		
		for(SalidaCreditoARepactarVO creditoRepacta : salidaRepactarVO.getListaCreditos()){
			if(	!creditoRepacta.getEstadoCredito().equalsIgnoreCase("09") &&
				!creditoRepacta.getEstadoCredito().equalsIgnoreCase("08")&&
				!creditoRepacta.getEstadoCredito().equalsIgnoreCase("07")){
				String folioRepacta = creditoRepacta.getFolio();
				for(SalidaConsultaCreditosPorRutEnAs400VO vigenteSalida : salidaPorRutVO.getListaCreditos()){
					
					if("S".equalsIgnoreCase(vigenteSalida.getRolPagador()) || "X".equalsIgnoreCase(vigenteSalida.getRolPagador())){
					
						String folioVigente = vigenteSalida.getFolioCredito();
						folioVigente=folioVigente.substring(4);
						if(folioRepacta.equals(folioVigente)){
							credito = new SalidaCreditoPrepagoVO();
							RepactarTablaVO tablaRepacta = new RepactarTablaVO();
							tablaRepacta = UtilRepactarTabla.getDesdePrima(creditoRepacta);
							
							//Retorna los datos desde el servicio de Créditos Vigentes "ConsultaCreditosPorRutEntradaVO"
							credito.setFolio(vigenteSalida.getFolioCredito());
							credito.setHastaCuota(CompletaUtil.quitaCerosIzqString(vigenteSalida.getCantidadCuotas()));
							credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB(vigenteSalida.getFechaOtorgamiento()));	
							
							credito.setMontoOtorgado(Double.valueOf(vigenteSalida.getMontoSolicitado()));
							
							credito.setMontoImpuestoLTE(Double.valueOf(vigenteSalida.getMontoImpuestos()));
							credito.setSaldoCapital(UtilRepactarTabla.getSaldoCapitalPrepago(Double.valueOf(vigenteSalida.getMontoAdaudado()), creditoRepacta));
							credito.setEstadoCredito(vigenteSalida.getEstadoCredito().trim());
							credito.setTipoCredito(vigenteSalida.getTipoProducto().trim());
							//nuevo
							credito.setLineaCredito(vigenteSalida.getTipoCredito().trim());
							credito.setTipoBp(vigenteSalida.getTipoAfiliado());
							
							
							//Retorna los datos desde el servicio de Créditos Vigentes "ConsultaCreditosARepactarPorRutSalidaVO"
							credito.setDesdeCuota(Utils.formateaDobleSinDecimal(tablaRepacta.getCuotaDesde()));
							credito.setMontoCuota(Double.valueOf(creditoRepacta.getMontoCuota())+UtilCuota.getSeguroPartidasAbiertas(vigenteSalida.getFolioCredito(), vigenteSalida.getFechaOtorgamiento()));
							credito.setGravamenes(UtilRepactarTabla.getGravamenesCredito(vigenteSalida.getFolioCredito()));			
							
							String gastoCobranza = "$ "+Utils.formateaDobleSinDecimal(Double.valueOf(vigenteSalida.getSumaGCob()));
							credito.setGastosDeCobranza(gastoCobranza);
							credito.setSumaCuotas(Utils.formateaDobleSinDecimal(Double.valueOf(vigenteSalida.getSumaCuotas())));
							credito.setTotal(Double.valueOf(vigenteSalida.getMontoAdaudado())+UtilRepactarTabla.getTotalSinMontoAdeudado(tablaRepacta, creditoRepacta,vigenteSalida.getFolioCredito()));
							
							//Retorna los datos desde el servicio de "FolioFormulario24" 
							formFolio24=folioForm24(vigenteSalida.getFechaOtorgamiento());
							if(formFolio24.getCodigoError().equals(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS)){
								credito.setFolioForm24(formFolio24.getFolioFormulario24());
								System.out.println("Folio Formulario 24: " + formFolio24.getFolioFormulario24());
							}else{
								salidaVO=new SalidaListaCreditoPrepagoVO();;
								salidaVO.setCodigoError(formFolio24.getCodigoError());
								salidaVO.setMensaje(formFolio24.getMensaje());
								System.out.println("Codigo error: "+salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
								return salidaVO;
							}
							
							listaCreditos.add(credito);
							break;
						}
					}
				}
			}
		}
		salidaVO= new SalidaListaCreditoPrepagoVO();
		salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
		salidaVO.setMensaje("Mapeo correcto a Prepago");
		salidaVO.setListaCreditos(listaCreditos);
		System.out.println(salidaVO.getCodigoError()+". "+salidaVO.getMensaje());
		return salidaVO;
	}
	/**Ingresando una fecha retorna un String con el dato de "FolioFormulario24"*/
	private static SalidaConsultaFolioFormulario24 folioForm24 (String fecha) throws Exception{
		EntradaConsultaFolioFormulario24 entradaForm24 = new EntradaConsultaFolioFormulario24();
		SalidaConsultaFolioFormulario24 salidaForm24 = new SalidaConsultaFolioFormulario24();
		ClienteConsultaFolioFormulario24 clienteForm24 = new ClienteConsultaFolioFormulario24();
		entradaForm24.setFechaOriginacionDeCredito(fecha);
		salidaForm24 = (SalidaConsultaFolioFormulario24) clienteForm24.call(entradaForm24);
		System.out.println("Codigo ERROR del Formulario24: "+salidaForm24.getCodigoError());
		return salidaForm24;
	}
	
	
	

}
