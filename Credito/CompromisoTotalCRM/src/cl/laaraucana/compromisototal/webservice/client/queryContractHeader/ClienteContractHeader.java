package cl.laaraucana.compromisototal.webservice.client.queryContractHeader;

import java.util.Date;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderResponse;

import cl.laaraucana.compromisototal.utils.Codigo;
import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO.EntradaContractHeaderVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractHeader.VO.SalidaContractHeaderVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;

public class ClienteContractHeader implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		logger.debug("entra a clienteContractHeader");	
		
		EntradaContractHeaderVO entradaVO = (EntradaContractHeaderVO) entrada;
		
		String ep = Configuraciones.getConfig("ep.QueryContractHeader");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");

		
		QueryContractHeaderOUTBindingStub stub = new QueryContractHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryContractHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");
		
		QueryContractHeader qch = new QueryContractHeader();
		qch.setACNUM_EXT(entradaVO.getAcNUM_EXT());
		qch.setRUT(entradaVO.getRut().toUpperCase());
		

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));


		QueryContractHeaderRequest contractHeaderRequest = new QueryContractHeaderRequest();
		contractHeaderRequest.setMessageHeader(messageHeader);
		contractHeaderRequest.setQueryContractHeader(qch);
		
		SalidaContractHeaderVO salida = new SalidaContractHeaderVO();
		QueryContractHeaderResponse resp= new QueryContractHeaderResponse();

		try {
			resp= stub.queryContractHeader(contractHeaderRequest);
		} catch (Exception e) {
			salida.setCodigoError(Codigo.ERROR);
			salida.setMensaje("Error en Servicio QueryContactHeader: compruebe el servicio");
			logger.debug("QueryContactHeader error = " + e.getMessage());
			return salida;
		}

		if (resp.getResultCode().equals("3")) {
			logger.debug("contract codigo error=" + 0);
			salida = mapeo(resp);
			salida.setCodigoError(Codigo.OK);
			salida.setMensaje("Retorna datos OK");

		} else {
		if (resp.getResultCode().equals("1")) {
			salida.setCodigoError(Codigo.VACIO);
			salida.setMensaje("El Servicio QueryContactHeader retorna datos vacio ("+ resp.getLog().getSYSTEM() + ")");	
		}else{
			salida.setCodigoError(Codigo.ERROR);
			salida.setMensaje("Error en Servicio QueryContactHeader: "+resp.getLog().getMESSAGE()+"("+resp.getLog().getSYSTEM()+")");
			logger.debug("error en la llamada");
			
		}

		}

		logger.debug("Salida WebService: contractHeader");
		return salida;
	}

	public SalidaContractHeaderVO mapeo(QueryContractHeaderResponse resp) {

		SalidaContractHeaderVO salidaContractHeader = new SalidaContractHeaderVO(resp.getQueryContractHeader().getContractNumber(),// contractNumbrer,
				resp.getQueryContractHeader().getComercialLine(),// comercialLine,
				Utils.dateToStringSAP(resp.getQueryContractHeader().getContractEnd()),// contractEnd,
				resp.getQueryContractHeader().getContractCurrency(),// contractCurrency,
				resp.getQueryContractHeader().getStatus(),// status,
				resp.getQueryContractHeader().getMonthlyInterestrate(),// monthlyInterestRate,
				resp.getQueryContractHeader().getLteAmount(),// lteAmount,
				resp.getQueryContractHeader().getNotarialCharge(),// notarialCharge,
				resp.getQueryContractHeader().getInterestAmount(),// interestAmount,
				resp.getQueryContractHeader().getLifeInsurance(),// lifeInsurance,
				resp.getQueryContractHeader().getUnemploymentinsur(),// unemploymentInsur,
				resp.getQueryContractHeader().getPhonoAsistance(),// phonoAsistance,
				resp.getQueryContractHeader().getUnpaidinstAmount(),// unpaidInstAmount,
				resp.getQueryContractHeader().getArrearsAmount(),// arrearsAmount,
				resp.getQueryContractHeader().getWaiverrate(),// waiverRate,
				resp.getQueryContractHeader().getUnpaidcharge(),// unpaidCharge,
				resp.getQueryContractHeader().getQuantityActiveinst(),// quantityActiveInst,
				resp.getQueryContractHeader().getQuantityUnpaidinst(),// quantityUnpaidInst,
				resp.getQueryContractHeader().getRemainingBalance(),// remainingBalance,
				resp.getQueryContractHeader().getCompanyRut()// companyRUT
		);

		return salidaContractHeader;
	}

}
