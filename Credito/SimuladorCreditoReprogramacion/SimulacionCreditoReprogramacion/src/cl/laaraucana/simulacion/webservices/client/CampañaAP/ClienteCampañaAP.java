package cl.laaraucana.simulacion.webservices.client.CampañaAP;


import org.apache.log4j.Logger;
import com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_CampAcuPagoCastigo_RES;
import com.lautaro.xi.CRM.WEB_Mobile.SI_CampAcuPagoCastigo_OUTBindingStub;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.WSInterface;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.EntradaCampañaVO;
import cl.laaraucana.simulacion.webservices.client.CampañaAP.VO.SalidaCampañaVO;
import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;


public class ClienteCampañaAP implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: Campaña Acuerdos de pago");
		String ep = Configuraciones.getConfig("ep.CampañaAcuerdosPago");
		String username = Configuraciones.getConfig("SAP.user");
		String password= Configuraciones.getConfig("SAP.password");
		
		SalidaCampañaVO salidaListaVO = new SalidaCampañaVO();
		EntradaCampañaVO entradaVO = (EntradaCampañaVO) entrada;

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
			salidaListaVO.setMensaje("Carga de datos CampAcuPagoCastigo_OUT OK. Código error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			String msg = "RUT o Contrato no válido";				
			salidaListaVO.setMensaje("Error en servicio CampAcuPagoCastigo_OUT: " + msg);
			logger.debug(salidaListaVO.getMensaje());
		}

		logger.debug(">> Salida Web Service: CampAcuPagoCastigo_OUT");
		return salidaListaVO;

	}

	private SalidaCampañaVO mapear(DT_CampAcuPagoCastigo_RES response) {
		
		SalidaCampañaVO info_campaña = new SalidaCampañaVO();
		info_campaña.setCondonacionPagoTotal(response.getTOTAL().doubleValue());
		info_campaña.setCondonacionConvenioPago(response.getCONVENIO().doubleValue());
		info_campaña.setFechaVigencia(response.getVIGENCIA());

		return info_campaña;
	}
}