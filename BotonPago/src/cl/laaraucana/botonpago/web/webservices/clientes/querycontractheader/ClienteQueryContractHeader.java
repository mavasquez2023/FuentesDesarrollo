package cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader;

import java.util.Date;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.ContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderResponse;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.WSInterface;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.vo.EntradaContractHeaderVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractheader.vo.SalidaContractHeaderVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class ClienteQueryContractHeader implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		SalidaContractHeaderVO salidaVO = new SalidaContractHeaderVO();
		EntradaContractHeaderVO entradaVO = (EntradaContractHeaderVO) entrada;
		
		String ep = Configuraciones.getConfig("ep.QueryContractHeader");
		String username = Constantes.getInstancia().SAP_USERNAME;
		String password= Constantes.getInstancia().SAP_PASSWORD;
		
		QueryContractHeaderOUTBindingStub stub = new QueryContractHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryContractHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");

		QueryContractHeaderRequest query = new QueryContractHeaderRequest();
		QueryContractHeader entradaWs = new QueryContractHeader();
		entradaWs.setRUT(entradaVO.getRut());
		entradaWs.setACNUM_EXT(entradaVO.getAcnum_ext());

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(Constantes.getInstancia().SAP_USER);

		query.setMessageHeader(messageHeader);
		query.setQueryContractHeader(entradaWs);

		QueryContractHeaderResponse respuesta = new QueryContractHeaderResponse();

		try {
			respuesta = stub.queryContractHeader(query);
		} catch (Exception e) {
			salidaVO.setCodigoError(Constantes.getInstancia().getInstancia().APP_COD_ERROR);
			salidaVO.setMensaje("Error en servicio QueryContractHeader: compruebe el servicio");
			logger.debug(salidaVO.getMensaje() + e);
			return salidaVO;
		}

		if (respuesta.getResultCode().equals(Constantes.getInstancia().getInstancia().WS_COD_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().getInstancia().APP_COD_SUCCESS);
			salidaVO.setMensaje("Servicio QueryContractHeader OK.");
			logger.debug(salidaVO.getMensaje());
		} else {
			// codigo error 1 por lista vacia.
			if (respuesta.getResultCode().equals(Constantes.getInstancia().getInstancia().WS_COD_VACIO)) {
				salidaVO.setCodigoError(Constantes.getInstancia().getInstancia().APP_COD_VACIO);
				salidaVO.setMensaje("Servicio QueryContractHeader OK. No se encontró información.");
				logger.debug(salidaVO.getMensaje());
			} else {
				salidaVO.setCodigoError(Constantes.getInstancia().getInstancia().APP_COD_ERROR);
				String msg = respuesta.getLog().getMESSAGE() + " (" + respuesta.getLog().getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio QueryContractHeader: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		return salidaVO;
	}

	private SalidaContractHeaderVO mapear(QueryContractHeaderResponse entrada) {

		SalidaContractHeaderVO credito = new SalidaContractHeaderVO();
		if (entrada != null) {
			ContractHeader header = entrada.getQueryContractHeader();
			credito.setContractNumber(header.getContractNumber().trim());
			credito.setComercialLine(header.getComercialLine().trim());
			credito.setContractEnd(Util.dateToStringSAP(header.getContractEnd()));
			credito.setContractCurrency(header.getContractCurrency());
			credito.setStatus(header.getStatus().trim());
			credito.setMonthlyInterestrate(header.getMonthlyInterestrate());
			credito.setLteAmount(header.getLteAmount());
			credito.setNotarialCharge(header.getNotarialCharge());
			credito.setInterestAmount(header.getInterestAmount());
			credito.setLifeInsurance(header.getLifeInsurance());
			credito.setUnemploymentinsur(header.getUnemploymentinsur());
			credito.setPhonoAsistance(header.getPhonoAsistance());
			credito.setUnpaidinstAmount(header.getUnpaidinstAmount());
			credito.setArrearsAmount(Util.formatNumeroStringSap(header.getArrearsAmount()));
			credito.setWaiverrate(header.getWaiverrate());
			credito.setUnpaidcharge(Util.formatNumeroStringSap(header.getUnpaidcharge()));
			credito.setQuantityActiveinst(header.getQuantityActiveinst());
			credito.setQuantityUnpaidinst(Util.formatNumeroStringSap(header.getQuantityUnpaidinst()));
			credito.setRemainingBalance(Util.formatNumeroStringSap(header.getRemainingBalance()));
			credito.setCompanyRut(header.getCompanyRut());
		}

		return credito;

	}

}
