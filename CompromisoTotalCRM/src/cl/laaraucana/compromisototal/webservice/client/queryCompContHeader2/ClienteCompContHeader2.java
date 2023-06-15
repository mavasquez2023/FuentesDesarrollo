package cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2BindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader2;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse2;

import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader.VO.SalidaListaCompContHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.EntradaCompContHeader2VO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.SalidaCompContHeader2VO;
import cl.laaraucana.compromisototal.webservice.client.queryCompContHeader2.VO.SalidaListaCompContHeader2VO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class ClienteCompContHeader2 implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryCompContHeader2");
		EntradaCompContHeader2VO entradaVO = (EntradaCompContHeader2VO) entrada;

		String ep = Configuraciones.getConfig("ep.QueryCompContHeader2");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
		QueryCompContHeaderOUT2BindingStub stub = new QueryCompContHeaderOUT2BindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryCompContHeaderOUT2BindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");

		QueryCompactContract qcc = new QueryCompactContract();
		qcc.setRut(entradaVO.getRut().toUpperCase());
		qcc.setCreditStatus(entradaVO.getCreditStatus());// 1=vigentes:2=cancelados:3=todos

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));

		QueryCompactContractHeaderRequest compContHeaderRequest = new QueryCompactContractHeaderRequest();
		compContHeaderRequest.setMessageHeader(messageHeader);
		compContHeaderRequest.setQueryCompactContractHeader(qcc);

		QueryCompactContractHeaderResponse2 respuesta = new QueryCompactContractHeaderResponse2();

		SalidaListaCompContHeader2VO salidaListaBS = new SalidaListaCompContHeader2VO();
		try {

			respuesta = stub.queryCompContrHeader(compContHeaderRequest);
		} catch (Exception e) {
			salidaListaBS.setCodigoError(Codigo.ERROR);
			salidaListaBS.setMensaje("Error en Servicio QueryCompContHeader2: compruebe el servicio");
			logger.debug("Querycompcontract error = " + e.getMessage());
			return salidaListaBS;
		}

		if (respuesta.getResultCode().equals("3")) {
			salidaListaBS = mapeo(respuesta);
			salidaListaBS.setCodigoError(Codigo.OK);
			salidaListaBS.setMensaje("Retorna datos OK");
			logger.debug("QueryCompContHeader2 codigo error = " + salidaListaBS.getCodigoError());
		} else {
			if (respuesta.getResultCode().equals("1")) {
				// codigo error 1 por lista vacia.
				salidaListaBS.setCodigoError(Codigo.VACIO);
				salidaListaBS.setMensaje("Retorna lista vacia");
				logger.debug("QueryCompContHeader2 codigo error = " + salidaListaBS.getCodigoError());

			} else {
				// codigo error 1 por lista vacia.
				salidaListaBS.setCodigoError(Codigo.ERROR);
				salidaListaBS.setMensaje("Error en Servicio QueryCompContHeader2: "
						+ respuesta.getLog()[0].getMESSAGE() + "("
						+ respuesta.getLog()[0].getSYSTEM() + ")");
				logger.debug("QueryCompContHeader2 codigo error = " + salidaListaBS.getCodigoError());

			}
		}

		logger.debug("Salida WebService: QueryCompContHeader2");
		return salidaListaBS;

	}

	public SalidaListaCompContHeader2VO mapeo(QueryCompactContractHeaderResponse2 resp) throws RemoteException, ParseException {
		logger.debug("mapeo Querycompcont");
		SalidaListaCompContHeader2VO salidaListaBS = new SalidaListaCompContHeader2VO();
		ArrayList<SalidaCompContHeader2VO> listaMia = new ArrayList<SalidaCompContHeader2VO>();

		for (QueryCompactContractHeader2 compactcontract : resp.getQueryCompactContractHeader()) {
			 
			
			listaMia.add(new SalidaCompContHeader2VO(compactcontract.getContractNumber(),// contractNumber,
					compactcontract.getTerminated(),// terminated,
					compactcontract.getWithExtintion(),// whthExtintion,
					compactcontract.getRole(),// role,
					compactcontract.getPayer(),// payer,
					compactcontract.getContractAmount(),// contractAmount,
					Utils.dateToString(compactcontract.getContractBegin()),// contractBegin,
					Utils.dateToString(compactcontract.getContractEnd()),// contractEnd,
					compactcontract.getInstallmentAmount(),// installmentAmount,
					compactcontract.getInstallmentQuantity(),// installmentQuantity,
					compactcontract.getRepacta(),// repacta,
					compactcontract.getReprogramac(),// repacta,
					compactcontract.getContractCurrency(),// contractCurrency
					compactcontract.getBP_ANEXO(),
					compactcontract.getRUT_EMPRESA(),
					compactcontract.getNRO_INSCRIPCION()
			));

		}

		salidaListaBS.setDetalle(listaMia);
		return salidaListaBS;
	}

}
