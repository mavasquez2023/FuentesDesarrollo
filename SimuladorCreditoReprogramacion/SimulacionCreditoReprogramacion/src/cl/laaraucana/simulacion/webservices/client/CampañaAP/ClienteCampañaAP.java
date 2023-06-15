package cl.laaraucana.simulacion.webservices.client.Campa�aAP;


import org.apache.log4j.Logger;
import com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_RES;
import com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUTBindingStub;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.WSInterface;
import cl.laaraucana.simulacion.webservices.client.Campa�aAP.VO.EntradaCampa�aVO;
import cl.laaraucana.simulacion.webservices.client.Campa�aAP.VO.SalidaCampa�aVO;
import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;


public class ClienteCampa�aAP implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: Campa�a Acuerdos de pago");
		String ep = Configuraciones.getConfig("ep.Campa�aAcuerdosPago");
		String username = Configuraciones.getConfig("SAP.user");
		String password= Configuraciones.getConfig("SAP.password");
		
		SalidaCampa�aVO salidaListaVO = new SalidaCampa�aVO();
		EntradaCampa�aVO entradaVO = (EntradaCampa�aVO) entrada;

		SI_CampAcuPagoCastigo_OUTBindingStub stub = new SI_CampAcuPagoCastigo_OUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(SI_CampAcuPagoCastigo_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion SI_CampAcuPagoCastigo_OUT seteados");

		DT_CampAcuPagoCastigo_REQ query = new DT_CampAcuPagoCastigo_REQ();
		query.setRUT(entradaVO.getRut());
		query.setCONTRATO(entradaVO.getContrato());
		logger.debug("Datos seteados al VO");


		DT_CampAcuPagoCastigo_RES respuesta = new DT_CampAcuPagoCastigo_RES();
		
		try {
			respuesta = stub.SI_CampAcuPagoCastigo_OUT(query);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio SI_CampAcuPagoCastigo_OUT: compruebe el servicio");
			return salidaListaVO;
		}
		

		if(respuesta!=null){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos CampAcuPagoCastigo_OUT OK. C�digo error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			String msg = "RUT o Contrato no v�lido";				
			salidaListaVO.setMensaje("Error en servicio CampAcuPagoCastigo_OUT: " + msg);
			logger.debug(salidaListaVO.getMensaje());
		}

		logger.debug(">> Salida Web Service: CampAcuPagoCastigo_OUT");
		return salidaListaVO;

	}

	private SalidaCampa�aVO mapear(DT_CampAcuPagoCastigo_RES response) {
		
		SalidaCampa�aVO info_campa�a = new SalidaCampa�aVO();
		info_campa�a.setCondonacionPagoTotal(response.getTOTAL().doubleValue());
		info_campa�a.setCondonacionConvenioPago(response.getCONVENIO().doubleValue());
		info_campa�a.setFechaVigencia(response.getVIGENCIA());

		return info_campa�a;
	}
}