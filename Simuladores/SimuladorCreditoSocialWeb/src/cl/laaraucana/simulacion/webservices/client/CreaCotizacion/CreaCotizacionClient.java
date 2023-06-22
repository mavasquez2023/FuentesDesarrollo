package cl.laaraucana.simulacion.webservices.client.CreaCotizacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.legacy.MessageHeader;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacion;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionOUTServiceStub;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionOut;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionRequest;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionRequestOut;
import cl.laaraucana.simulacion.webservices.autogenerado.CreaCotizacion.web_mobile.CreaCotizacionResponseOut;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionEntradaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.CreaCotizacionSalidaVO;
import cl.laaraucana.simulacion.webservices.client.CreaCotizacion.VO.TasasInteresVO;

public class CreaCotizacionClient {

	protected Logger logger = Logger.getLogger(this.getClass());

	public CreaCotizacionSalidaVO call(CreaCotizacionEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: CreaCotizacion");
		String ep = Configuraciones.getConfig("ep.CreaCotizacion");

		CreaCotizacionSalidaVO salida = new CreaCotizacionSalidaVO();

		CreaCotizacionOUTServiceStub stub = new CreaCotizacionOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("SAP.user"));
		auth.setPassword(Configuraciones.getConfig("SAP.password"));
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION("");
		messageHeader.setHOST("");
		messageHeader.setINTERNALORGANIZATION("");
		messageHeader.setUSER("");

		CreaCotizacion cotizacion = new CreaCotizacion();

		cotizacion.setTIPO_EJECUCION(entrada.getTipoEjecucion());// 'S'= Simular
																	// 'C'=
																	// Crear
																	// Cotiz
		cotizacion.setTIPO_AFILIADO(entrada.getTipoAfiliado());// ZFSO=Pensionados,
																// ZFS1=Afiliados,
																// ZFS2=Independiente

		
		cotizacion.setRUT(entrada.getRut());
		cotizacion.setRUT_EMPLEADOR(entrada.getRutEmpleador());
		cotizacion.setSAP_EMPLEADOR(entrada.getSapEmpleador());
		cotizacion.setNO_INSCRIPCION(entrada.getNroInscripcion());
		cotizacion.setMONTO_SOLICITADO(entrada.getMontoSolicitado());
		cotizacion.setCUOTAS(entrada.getCuotas());

		cotizacion.setPRODUCTO(entrada.getProducto());// CA_ESP=Especial,
														// CA_NORM=Social
		cotizacion.setMONEDA(entrada.getMoneda());// CLP / UF

		cotizacion.setOFICINA_VENTA(entrada.getOficinaVenta());
		cotizacion.setCANAL_VENTA(entrada.getCanalVenta());
		if(entrada.getTipoAfiliado().equals("ZFSO")) {
			cotizacion.setSEGURO_CESANTIA("N");
		} else {
			cotizacion.setSEGURO_CESANTIA(entrada.getSeguroCesantia());
		}
		cotizacion.setSECTOR_VENTA(entrada.getSectorVenta());

		// Datos cotizacion
		cotizacion.setCALLE(entrada.getCalle());
		cotizacion.setNUMERO(entrada.getNumero());
		cotizacion.setNUM_DEPARTAMENTO(entrada.getNroDpto());
		cotizacion.setCOMUNA(entrada.getComuna());
		cotizacion.setREGION(entrada.getRegion());
		cotizacion.setNUMERO_TEL_FIJO(entrada.getNroTelFijo());
		cotizacion.setNUMERO_TEL_MOVIL(entrada.getNroTelMovil());
		cotizacion.setEMAIL(entrada.getEmail());
		cotizacion.setCONTACTO(entrada.getContacto());

		CreaCotizacionRequestOut requestOut = new CreaCotizacionRequestOut();
		CreaCotizacionRequest request = new CreaCotizacionRequest();
		request.setCreaCotizacion(cotizacion);
		request.setMessageHeader(messageHeader);
		requestOut.setCreaCotizacionRequestOut(request);
		try {
			CreaCotizacionResponseOut resp = stub.creaCotizacionIN(requestOut);
			if (resp.getCreaCotizacionResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
				salida = mapear(resp);
				salida.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			} else {
				salida.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				salida.setMensaje(resp.getCreaCotizacionResponseOut().getLog()[0].getMESSAGE());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salida.setMensaje("Error en servicio CreaCotizacion " + e.getMessage());
		}

		return salida;
	}

	private CreaCotizacionSalidaVO mapear(CreaCotizacionResponseOut servOut) {
		CreaCotizacionSalidaVO salida = new CreaCotizacionSalidaVO();
		CreaCotizacionOut cot = servOut.getCreaCotizacionResponseOut().getCreaCotizacion();

		salida.setBusinessPartner(cot.getBUSINESS_PARTNER());
		salida.setNroCotizacion(cot.getNUMERO_COTIZACION());
		salida.setTasaIntMensual(cot.getTASA_INT_MENSUAL());
		salida.setTasaIntAnual(cot.getTASA_INT_ANUAL());
		salida.setSegDesgravamen(cot.getSEG_DESGRAVAMEN());
		salida.setSegCesantia(cot.getSEG_CESANTIA());
		salida.setLte(cot.getLTE());
		salida.setGastosNotariales(cot.getGASTOS_NOTARIALES());
		salida.setCumpleCondiciones(cot.getCUMPLE_CONDICIONES());
		salida.setMontoCuotaMax(cot.getMONTO_CUOTA_MAX());

		List<TasasInteresVO> tasasInteresList = new ArrayList<TasasInteresVO>();

		TasasInteresVO tasa;
		if (cot.getTasaInteres() != null) {
			for (int i = 0; i < cot.getTasaInteres().length; i++) {
				tasa = new TasasInteresVO();
				tasa.setMeses(cot.getTasaInteres()[i].getMESES());
				tasa.setPorcentaje(cot.getTasaInteres()[i].getPORCENTAJE());
				tasasInteresList.add(tasa);
				// tasasInteres[i].setMeses(cot.getTasaInteres()[i].getMESES());
				// tasasInteres[i].setPorcentaje(cot.getTasaInteres()[i].getPORCENTAJE());
			}
		}
		salida.setTasasInteresList(tasasInteresList);

		return salida;
	}
}
