package cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.WSInterface;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanArrearsInfo;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPayment;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentOUTServiceStub;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentRequest;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentRequestOut;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContrPaymentResponseOut;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.LoanContract;
import cl.laaraucana.botonpago.web.webservices.autogenerado.loanContrPaymentRequest.web_mobile.MessageHeader;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.EntradaLoanPaymentVO;
import cl.laaraucana.botonpago.web.webservices.clientes.loanContrPaymentRequest.vo.SalidaLoanPaymentVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;

public class ClienteLoanContrPaymentReq implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		String ep = Configuraciones.getConfig("ep.LoanContractPayment");

		SalidaLoanPaymentVO salidaVO = new SalidaLoanPaymentVO();
		EntradaLoanPaymentVO entradaVO = (EntradaLoanPaymentVO) entrada;

		LoanContrPaymentOUTServiceStub stub = new LoanContrPaymentOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Constantes.getInstancia().SAP_USERNAME);
		auth.setPassword(Constantes.getInstancia().SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

		//Seteo los datos de ingreso Id Contrato y FechaFullEpo en la clase de EarlyPayoffContract
		LoanContrPayment datosPayment = new LoanContrPayment();
		datosPayment.setPVI_RUT(entradaVO.getPviRut());

		LoanContract datosContract = new LoanContract();
		datosContract.setAMOUNT(entradaVO.getAmount());
		datosContract.setCURRENCY(entradaVO.getCurrency());
		datosContract.setFOLIOTESORERIA(entradaVO.getFolioTesoreria());
		datosContract.setID(entradaVO.getId());
		datosContract.setINSTALLMENT(entradaVO.getInstallment());
		datosContract.setITEMNUMBER(entradaVO.getItemNumber());
		datosContract.setOFICINAPAGO(entradaVO.getOficinaPago());
		datosContract.setPITYPE(entradaVO.getPiType());
		datosContract.setPOSTINGDATE(entradaVO.getPostingDate());
		datosContract.setVALUEDATE(entradaVO.getValueDate());
		datosContract.setCOMPEXTERNO(entradaVO.getCompExterno());
		
		LoanArrearsInfo datosArrears = new LoanArrearsInfo();
		datosArrears.setAMOUNT(entradaVO.getArrearsAmount());
		datosArrears.setAMOUNTSOURCE(entradaVO.getArrearsAmountsource());
		datosArrears.setCONTABTYPE(entradaVO.getArrearsContabtype());
		datosArrears.setCURRENCY(entradaVO.getArrearsCurrency());
		datosArrears.setCURRENCYSOURCE(entradaVO.getArrearsCurrencysource());
		datosArrears.setID(entradaVO.getArrearsId());
		datosArrears.setISCRED(entradaVO.getArrearsIscred());

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Util.getFechaSAP());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(Constantes.getInstancia().SAP_USER);

		LoanContrPaymentRequest query = new LoanContrPaymentRequest();
		query.setLoanContrPayment(datosPayment);
		query.addLoanContract(datosContract);
		query.addArrearsInfo(datosArrears);
		query.setMessageHeader(messageHeader);

		LoanContrPaymentRequestOut requestOUT = new LoanContrPaymentRequestOut();
		requestOUT.setLoanContrPaymentRequestOut(query);

		LoanContrPaymentResponseOut respuesta = new LoanContrPaymentResponseOut();

		try {
			respuesta = stub.execLoanContrPayment(requestOUT);
		} catch (Exception e) {
			e.printStackTrace();
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
			salidaVO.setMensaje("Error en servicio LoanContractPayment: compruebe el servicio");
			logger.debug(salidaVO.getMensaje() + e);
			return salidaVO;
		}
		if (respuesta.getLoanContrPaymentResponseOut().getResultCode().equals(Constantes.getInstancia().WS_COD_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_SUCCESS);
			salidaVO.setMensaje("Servicio LoanContractPayment OK.");
			logger.debug(salidaVO.getMensaje());
		} else {
			if (respuesta.getLoanContrPaymentResponseOut().getResultCode().equals(Constantes.getInstancia().WS_COD_VACIO)) {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_VACIO);
				salidaVO.setMensaje("Servicio LoanContractPayment OK. No se encontró información.");
				logger.debug(salidaVO.getMensaje());
			} else {
				salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
				String msg = respuesta.getLoanContrPaymentResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getLoanContrPaymentResponseOut().getLog()[0].getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio LoanContractPayment: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		return salidaVO;
	}

	private SalidaLoanPaymentVO mapear(LoanContrPaymentResponseOut entrada) {
		SalidaLoanPaymentVO salidaVO = new SalidaLoanPaymentVO();

		return salidaVO;
	}
}
