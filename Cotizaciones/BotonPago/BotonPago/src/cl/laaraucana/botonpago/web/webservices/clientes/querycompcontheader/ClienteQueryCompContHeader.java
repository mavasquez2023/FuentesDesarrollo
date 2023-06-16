package cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.WSInterface;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.EntradaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.SalidaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycompcontheader.vo.SalidaListaCompContHeaderVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class ClienteQueryCompContHeader implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		SalidaListaCompContHeaderVO salidaVO = new SalidaListaCompContHeaderVO();
		EntradaCompContHeaderVO entradaVO = (EntradaCompContHeaderVO) entrada;
		
		String ep = Configuraciones.getConfig("ep.QueryComCompContHeader");
		String username = Constantes.getInstancia().SAP_USERNAME;
		String password= Constantes.getInstancia().SAP_PASSWORD;
		
		QueryCompContHeaderOUTBindingStub stub = new QueryCompContHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryCompContHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");

		QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
		QueryCompactContract entradaWs = new QueryCompactContract();

		entradaWs.setRut(entradaVO.getRut());
		entradaWs.setCreditStatus(entradaVO.getFlagTipoCredito());

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(Constantes.getInstancia().SAP_USER);

		query.setMessageHeader(messageHeader);
		query.setQueryCompactContractHeader(entradaWs);

		QueryCompactContractHeaderResponse respuesta = new QueryCompactContractHeaderResponse();

		try {
			respuesta = stub.queryCompContrHeader(query);
		} catch (Exception e) {
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
			salidaVO.setMensaje("Error en servicio QueryCompContHeader: compruebe el servicio");
			logger.debug(salidaVO.getMensaje() + e);
			return salidaVO;
		}

		if (respuesta.getResultCode().equals(Constantes.getInstancia().WS_COD_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_SUCCESS);
			salidaVO.setMensaje("Servicio QueryCompContHeader OK. Se obtienen créditos.");
			logger.debug(salidaVO.getMensaje());
		} else {
			// codigo error 1 por lista vacia.
			if (respuesta.getResultCode().equals(Constantes.getInstancia().WS_COD_VACIO)) {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_VACIO);
				salidaVO.setMensaje("Servicio QueryCompContHeader OK. El rut no contiene créditos.");
				logger.debug(salidaVO.getMensaje());
			} else {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
				String msg = respuesta.getLog()[0].getMESSAGE() + " (" + respuesta.getLog()[0].getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		return salidaVO;
	}

	private SalidaListaCompContHeaderVO mapear(QueryCompactContractHeaderResponse entrada) {

		SalidaListaCompContHeaderVO salidaVO = new SalidaListaCompContHeaderVO();
		ArrayList<SalidaCompContHeaderVO> listaCreditos = new ArrayList<SalidaCompContHeaderVO>();
		SalidaCompContHeaderVO credito = null;

		for (QueryCompactContractHeader creditoWs : entrada.getQueryCompactContractHeader()) {
			credito = new SalidaCompContHeaderVO();

			credito.setContractAmount(Util.formatNumeroStringSap(creditoWs.getContractAmount()));
			credito.setContractBegin(Util.dateToStringSAP(creditoWs.getContractBegin()));
			credito.setContractCurrency(creditoWs.getContractCurrency());
			credito.setContractEnd(Util.dateToStringSAP(creditoWs.getContractEnd()));
			credito.setContractNumber(creditoWs.getContractNumber());
			credito.setInstallmentAmount(Util.formatNumeroStringSap(creditoWs.getInstallmentAmount()));
			credito.setInstallmentQuantity(Util.formatNumeroStringSap(creditoWs.getInstallmentQuantity()));
			credito.setPayer(creditoWs.getPayer());
			credito.setRepacta(creditoWs.getRepacta());
			credito.setReprogramac(creditoWs.getReprogramac());
			credito.setRole(creditoWs.getRole());
			credito.setTerminated(creditoWs.getTerminated());
			credito.setWithExtintion(creditoWs.getWithExtintion());

			listaCreditos.add(credito);
		}
		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}

}
