package cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.MessageHeader;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.WSInterface;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.EntradaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.SalidaListaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querybpstatus.vo.SalidaQueryBPVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class ClienteQueryBPStatus implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		SalidaListaQueryBPVO salidaVO = new SalidaListaQueryBPVO();
		EntradaQueryBPVO entradaVO = (EntradaQueryBPVO) entrada;
		
		String ep = Configuraciones.getConfig("ep.QueryBPStatus");
		String username = Constantes.getInstancia().SAP_USERNAME;
		String password= Constantes.getInstancia().SAP_PASSWORD;
		
		QueryBPStatusOUTBindingStub stub = new QueryBPStatusOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryBPStatusOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		QueryBPStatusRut query = new QueryBPStatusRut();
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Util.getFechaSAP());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(Constantes.getInstancia().SAP_USER);
		
		query.setRut(entradaVO.getRutCliente());
		query.setMessageHeader(messageHeader);

		QueryBPStatusResponse respuesta = new QueryBPStatusResponse();

		try {
			respuesta = stub.queryBPStatus(query);
		} catch (Exception e) {
			e.printStackTrace();
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
			salidaVO.setMensaje("Error en servicio QueryBPStatus: compruebe el servicio");
			logger.debug(salidaVO.getMensaje() + e);
			return salidaVO;
		}
		if (respuesta.getResultCode().equals(Constantes.getInstancia().WS_COD_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_SUCCESS);
			salidaVO.setMensaje("Servicio QueryBPStatus OK.");
			logger.debug(salidaVO.getMensaje());
		} else {
			if (respuesta.getResultCode().equals(Constantes.getInstancia().WS_COD_VACIO)) {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_VACIO);
				salidaVO.setMensaje("Servicio QueryBPStatus OK. No se encontró información.");
				logger.debug(salidaVO.getMensaje());
			} else {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
				String msg = respuesta.getLog()[0].getMESSAGE() + " (" + respuesta.getLog()[0].getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio QueryBPStatus: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		return salidaVO;
	}

	private SalidaListaQueryBPVO mapear(QueryBPStatusResponse response) {

		QueryBPStatus[] bp = response.getQueryBPStatus();

		SalidaListaQueryBPVO salidaLista = new SalidaListaQueryBPVO();
		ArrayList<SalidaQueryBPVO> lista = new ArrayList<SalidaQueryBPVO>();

		for (QueryBPStatus queryBPStatus : bp) {

			SalidaQueryBPVO afiliado = new SalidaQueryBPVO(queryBPStatus.getRut().trim(), // Rut
					queryBPStatus.getNombreCompleto().replaceAll(" +", " ").trim(), // NombreCompleto
					queryBPStatus.getFechaAfiliacion().trim(), // FechaAfiliacion
					queryBPStatus.getEstadoAfiliacion().trim(), // EstadoAfiliacion
					queryBPStatus.getRol().trim(), // Rol
					queryBPStatus.getRazonSocial().replaceAll(" +", " ").trim()// RazonSocial
			);
			lista.add(afiliado);
		}

		salidaLista.setLista(lista);

		return salidaLista;
	}

}
