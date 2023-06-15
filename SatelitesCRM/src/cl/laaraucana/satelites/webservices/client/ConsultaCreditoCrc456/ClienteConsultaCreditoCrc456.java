package cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCRC456EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCRC456SalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCRC456ServiceStub;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCrc456;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCrc456E;
import cl.laaraucana.satelites.integracion.ConsultaCreditoCRC456.ConsultaCreditoCrc456ResponseE;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.EntradaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoCrc456.VO.SalidaConsultaCreditoCrc456;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaCreditoCrc456 {
	
protected Logger logger = Logger.getLogger(this.getClass());
	
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		logger.debug("Inicio Web Service: ClienteConsultaCreditoCrc456");
		String ep = Configuraciones.getConfig("ep.CreditosCRC456");
		
		SalidaConsultaCreditoCrc456 salidaVO = new SalidaConsultaCreditoCrc456();

		EntradaConsultaCreditoCrc456 entradaVO =  (EntradaConsultaCreditoCrc456) entrada;
		logger.debug("Cliente instanciado");
		
		ConsultaCreditoCRC456ServiceStub stub = new ConsultaCreditoCRC456ServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
	
		ConsultaCreditoCRC456EntradaVO entradaWS = new ConsultaCreditoCRC456EntradaVO();
		entradaWS.setFolio(entradaVO.getFolio());
		entradaWS.setOficina(entradaVO.getOficina());
		entradaWS.setFechaFiniquito(entradaVO.getFechaFiniquito());
		
		ConsultaCreditoCrc456 ingresoDatos = new ConsultaCreditoCrc456();
		ingresoDatos.setArg0(entradaWS);
		logger.debug("Campos seteados en ConsultaCreditoCrc456");
		

		ConsultaCreditoCrc456E query = new ConsultaCreditoCrc456E();

		query.setConsultaCreditoCrc456(ingresoDatos);

		logger.debug("Datos seteados al VO");
	
		ConsultaCreditoCrc456ResponseE requestOUT = new ConsultaCreditoCrc456ResponseE();
		logger.debug("RequestOUT seteado OK");


		ConsultaCreditoCRC456SalidaVO salidaWS = new ConsultaCreditoCRC456SalidaVO();
		
		try {
			requestOUT = stub.consultaCreditoCrc456(query);
			salidaWS = requestOUT.getConsultaCreditoCrc456Response().get_return();
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio ClienteConsultaCreditoCrc456: compruebe el servicio" );
			return salidaVO;
		}		
		
		if(salidaWS.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(salidaWS);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de ClienteConsultaCreditoCrc456. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en el servicio ClienteConsultaCreditoCrc456: "+salidaWS.getLog().getId());
			logger.debug(salidaVO.getMensaje());	
		}

		logger.debug(">> Salida Web Service: ClienteConsultaCreditoCrc456");	
		return salidaVO;
		
	}
	
	public SalidaConsultaCreditoCrc456 mapear (ConsultaCreditoCRC456SalidaVO salidaWS){
		SalidaConsultaCreditoCrc456 salida = new SalidaConsultaCreditoCrc456();
		salida.setCodigoCredito(salidaWS.getCodigoCredito());
		salida.setCuotaImpagaDesde(salidaWS.getCuotaImpagaDesde());
		salida.setCuotaVigenteDesde(salidaWS.getCuotaVigenteDesde());
		salida.setCuotaVigenteHasta(salidaWS.getCuotaVigenteHasta());
		salida.setdVRutAval1(salidaWS.getDVRutAval1());
		salida.setdVRutAval2(salidaWS.getDVRutAval2());
		salida.setdVRutDeudor(salidaWS.getDVRutDeudor());
		salida.setDvRutEmpresa(salidaWS.getDvRutEmpresa());
		salida.setEstadoCredito(salidaWS.getEstadoCredito());
		salida.setFechaCancela(salidaWS.getFechaCancela());
		salida.setFechaOtorgamiento(salidaWS.getFechaCancela());
		salida.setLineaCredito(salidaWS.getLineaCredito());
		salida.setMontoCuota(salidaWS.getMontoCuota());
		salida.setMontoDeudaCapital(salidaWS.getMontoDeudaCapital());
		salida.setMontoImpuestoLTE(salidaWS.getMontoImpuestoLTE());
		salida.setMontoNominal(salidaWS.getMontoNominal());
		salida.setMontoNominalAcumulado(salidaWS.getMontoNominalAcumulado());
		salida.setMontoReajustado(salidaWS.getMontoReajustado());
		salida.setMontoSaldoCredito(salidaWS.getMontoSaldoCredito());
		salida.setNombreDeudor(salidaWS.getNombreDeudor());
		salida.setResultCode(salidaWS.getResultCode());
		salida.setRutAval1(salidaWS.getRutAval1());
		salida.setRutAval2(salidaWS.getRutAval2());
		salida.setRutDeudor(salidaWS.getRutDeudor());
		salida.setRutEmpresa(salidaWS.getRutEmpresa());
		salida.setSituacionDeudor(salidaWS.getSituacionDeudor());
		salida.setTasaCredito(salidaWS.getTasaCredito());
		salida.setTipoAfiliado(salidaWS.getTipoAfiliado());
		salida.setTipoOperacion(salidaWS.getTipoOperacion());
		salida.setTotalCuotas(salidaWS.getTotalCuotas());
		return salida;
	}
}
