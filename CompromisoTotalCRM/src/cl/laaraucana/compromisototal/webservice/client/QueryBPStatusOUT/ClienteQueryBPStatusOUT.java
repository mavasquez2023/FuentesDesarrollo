package cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.MessageHeader;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;

import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class ClienteQueryBPStatusOUT implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryBPStatusOUT");

		SalidaListaAfiliadoVO salidaListaVO = new SalidaListaAfiliadoVO();
		EntradaAfiliadoVO entradaVO = (EntradaAfiliadoVO) entrada;
		logger.debug("llega rut " + entradaVO.getRut().toUpperCase());
		
		String ep = Configuraciones.getConfig("ep.QueryBPStatus");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
		QueryBPStatusOUTBindingStub stub = new QueryBPStatusOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryBPStatusOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		QueryBPStatusRut query = new QueryBPStatusRut();

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.dateToStringSAP(new Date()));
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));

		query.setRut(entradaVO.getRut().toUpperCase());
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO");

		QueryBPStatusResponse respuesta = new QueryBPStatusResponse();
		try {
			respuesta = stub.queryBPStatus(query);

		} catch (Exception e) {
			salidaListaVO.setCodigoError(Codigo.ERROR);
			salidaListaVO.setMensaje("Error en Servicio QueryBPStatus: compruebe el servicio");
			logger.debug("QueryBPStatus error = " + e.getMessage());
			return salidaListaVO;
		}

		logger.debug("codigo de error del servicio " + respuesta.getResultCode());

		if (respuesta.getResultCode().equals("3")) {
			salidaListaVO = mapear(respuesta);
			logger.debug("Llamada al ws ok");
			salidaListaVO.setCodigoError(Codigo.OK);
			salidaListaVO.setMensaje("Carga de Afiliado Banking OK");
		} else {
			if (respuesta.getResultCode().equals("1")) {
				salidaListaVO.setCodigoError(Codigo.VACIO);
				salidaListaVO.setMensaje("Error en Servicio QueryBPStatus: " + respuesta.getLog()[0].getMESSAGE() + "("
						+ respuesta.getLog()[0].getSYSTEM() + ")");
				logger.debug("error respuesta vacia.");
			} else {
				salidaListaVO.setCodigoError(Codigo.ERROR);
				salidaListaVO.setMensaje("Error en Servicio QueryBPStatus: " + respuesta.getLog()[0].getMESSAGE() + "("
						+ respuesta.getLog()[0].getSYSTEM() + ")");
				logger.debug("error respuesta con error");
			}

		}

		return salidaListaVO;

	}

	private SalidaListaAfiliadoVO mapear(QueryBPStatusResponse response) {
		QueryBPStatus[] bp = response.getQueryBPStatus();

		SalidaListaAfiliadoVO salidaLista = new SalidaListaAfiliadoVO();
		ArrayList<SalidaAfiliadoVO> lista = new ArrayList<SalidaAfiliadoVO>();

		for (QueryBPStatus queryBPStatus : bp) {

			SalidaAfiliadoVO afiliado = new SalidaAfiliadoVO(queryBPStatus.getRut(), // Rut
					queryBPStatus.getNombreCompleto(), // NombreCompleto
					queryBPStatus.getFechaAfiliacion(), // FechaAfiliacion
					queryBPStatus.getEstadoAfiliacion(), // EstadoAfiliacion
					queryBPStatus.getRol(), // Rol
					queryBPStatus.getRazonSocial()// RazonSocial

			);
			lista.add(afiliado);
		}

		salidaLista.setListaAfiliado(lista);

		return salidaLista;
	}
}
