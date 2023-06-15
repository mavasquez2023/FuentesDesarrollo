package cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.MessageHeader;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryBPStatusOUT implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryBPStatusOUT");

		SalidaListaAfiliadoVO salidaListaVO = new SalidaListaAfiliadoVO();
		EntradaAfiliadoVO entradaVO = (EntradaAfiliadoVO) entrada;

		String ep = Configuraciones.getConfig("ep.QueryBPStatus");
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
		
		QueryBPStatusOUTBindingStub stub = new QueryBPStatusOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryBPStatusOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		QueryBPStatusRut query = new QueryBPStatusRut();

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.dateToStringSAP(new Date()));
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);

		query.setRut(entradaVO.getRut().toUpperCase());
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO");

		QueryBPStatusResponse respuesta = new QueryBPStatusResponse();
		try {
			respuesta = stub.queryBPStatus(query);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio QueryBPStatus: compruebe el servicio");
			return salidaListaVO;
		}
		

		
//<==== Validacion Nueva	
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos ClienteQueryBPStatusOUT OK. Código error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaListaVO.setMensaje("ClienteQueryBPStatusOUT. El rut no se encuentra como afiliado. 2");
				logger.debug(salidaListaVO.getMensaje());
			}else{
				salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getLog()[0].getMESSAGE() + " (" + respuesta.getLog()[0].getSYSTEM() + ")";				
				salidaListaVO.setMensaje("Error en servicio QueryBPStatus: " + msg);
				logger.debug(salidaListaVO.getMensaje());
			}
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusOUT");
		return salidaListaVO;

	}

	private SalidaListaAfiliadoVO mapear(QueryBPStatusResponse response) {
		QueryBPStatus[] bp = response.getQueryBPStatus();

		SalidaListaAfiliadoVO salidaLista = new SalidaListaAfiliadoVO();
		ArrayList<SalidaAfiliadoVO> lista = new ArrayList<SalidaAfiliadoVO>();

		for (QueryBPStatus queryBPStatus : bp) {

			SalidaAfiliadoVO afiliado = new SalidaAfiliadoVO(
					queryBPStatus.getRut().trim(), // Rut
					queryBPStatus.getNombreCompleto().replaceAll(" +", " ").trim(), // NombreCompleto
					queryBPStatus.getFechaAfiliacion().trim(), // FechaAfiliacion
					queryBPStatus.getEstadoAfiliacion(), // EstadoAfiliacion
					queryBPStatus.getRol().trim(), // Rol
					queryBPStatus.getRazonSocial().replaceAll(" +", " ").trim()// RazonSocial

			);
			lista.add(afiliado);
		}

		salidaLista.setListaAfiliado(lista);

		return salidaLista;
	}
}