package cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF;
import com.lautaro.xi.BS.WEB_Mobile.PORC_CONDONACION;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse;


import cl.laaraucana.compromisototal.utils.Configuraciones;
import cl.laaraucana.compromisototal.utils.Utils;
import cl.laaraucana.compromisototal.webservice.WSInterface;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.CondonacionCashFlowVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.SalidaCashFlowVO;
import cl.laaraucana.compromisototal.webservice.client.queryContractCashFlow.VO.SalidaListaCashFlowVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractEntradaVO;
import cl.laaraucana.compromisototal.webservice.models.AbstractSalidaVO;


public class ClienteContractCashFlow implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());


	public QueryContractCashFlowResponse call(QueryContractCashFlowRequest entrada) throws Exception {
		logger.debug("Inicio Web Service: Banking");
		QueryContractCashFlowResponse respuesta=null;
		
		String ep = Configuraciones.getConfig("ep.QueryContractCashFlow");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
				
		QueryContractCashFlowOUTBindingStub stub= new 	QueryContractCashFlowOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		
		stub._setProperty(QueryContractCashFlowOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));
		
		entrada.setMessageHeader(messageHeader);
		try {
		respuesta= stub.queryContractCashFlow(entrada);

		} catch (Exception e) {
			logger.error("QueryContactCashFlow error = " + e.getMessage());
			e.printStackTrace();
		}

		return respuesta;

	}
	
	public void mapeoCOndonacion(QueryContractCashFlowResponse resp){
		PORC_CONDONACION condonacion= resp.getPorc_Condonacion();
		
		
	}
	
	public CondonacionCashFlowVO mapeoCondonacion(QueryContractCashFlowResponse resp){
		PORC_CONDONACION condonacion= new PORC_CONDONACION();
		condonacion= resp.getPorc_Condonacion();
		CondonacionCashFlowVO condonacionCFVO= new CondonacionCashFlowVO();
		if(condonacion!=null){
				if(condonacion.getCONDONAC_GRV()!=null){
					condonacionCFVO.setCond_gravamenes(condonacion.getCONDONAC_GRV().doubleValue());
				}
				if(condonacion.getCONDONAC_GC()!=null){
					condonacionCFVO.setCond_gasto_cobranza(condonacion.getCONDONAC_GC().doubleValue()); 
				}
				if(condonacion.getCONDONAC_HONO()!=null){
					condonacionCFVO.setCond_honorarios(condonacion.getCONDONAC_HONO().doubleValue());
				}
		}
		return condonacionCFVO;
	}
	
	public SalidaListaCashFlowVO mapeo(QueryContractCashFlowResponse resp) throws ParseException {

		SalidaListaCashFlowVO detSalidaListaBS = new SalidaListaCashFlowVO();
		ArrayList<SalidaCashFlowVO> listaMia = new ArrayList<SalidaCashFlowVO>();

		for (DetalleCuotasCF contractCshFlow : resp.getDetalleCuotas()) {
			String capital_restante=contractCshFlow.getCapitalRestante();
			if(capital_restante.indexOf("-")>-1){
				capital_restante= "-" + capital_restante.substring(0, capital_restante.length()-1).trim();
			}
			String ult_fec_pag="";
			String ult_fec_con_pag="";
			String via_cot_des="";
			if(contractCshFlow.getEstadoCuota().equals("CANCELADA")){
				if(contractCshFlow.getUltFechaPago()!=null){
					ult_fec_pag= Utils.dateToString(contractCshFlow.getUltFechaPago());
				}
				if(contractCshFlow.getULT_FECHA_CONT_PAGO()!=null){
					ult_fec_con_pag= Utils.dateToString(contractCshFlow.getULT_FECHA_CONT_PAGO());
				}
				via_cot_des= contractCshFlow.getVIA_COTIZ_DESCRIPCION();
			}else{
				System.out.println("Estado cuota:" + contractCshFlow.getEstadoCuota());
			}
			listaMia.add(new SalidaCashFlowVO(contractCshFlow.getFechaVencCuota(),// fechaVencCuota
					String.valueOf(contractCshFlow.getNroCuota()), // nroCuota
					contractCshFlow.getEstadoCuota().toLowerCase(), // estadoCuota
					Utils.stringToDouble(contractCshFlow.getMontoCapital()), // montoCapital
					Utils.stringToDouble(contractCshFlow.getMontoInteres()), // montoInteres
					Utils.stringToDouble(contractCshFlow.getMontoServAdic()), // montoServAdic
					Utils.stringToDouble(contractCshFlow.getMontoSeguros()), // montoSeguro
					Utils.stringToDouble(contractCshFlow.getTotalCuota()), // totalCuota
					Utils.stringToDouble(contractCshFlow.getMontoGravamenes()),// montoGravamenes
					Utils.stringToDouble(Utils.logicaMontoAbonoSAP(contractCshFlow.getEstadoCuota(), contractCshFlow.getTotalCuota(), contractCshFlow.getMonto_pagado())), // montoAbono o Pagado
					ult_fec_pag, // ultFechaPago
					contractCshFlow.getOficinaPago(), // oficinaPago
					contractCshFlow.getFolioPago(), // folioPago
					contractCshFlow.getTransactionType(),// transactionType
					contractCshFlow.getEstadoPago(),// estadoPago
					contractCshFlow.getMoneda(),// moneda
					Utils.stringToDouble(capital_restante),// capital restante)
					ult_fec_con_pag, // ultFechaContPago
					via_cot_des //Forma de Pago
			));
		}

		detSalidaListaBS.setListaBs(listaMia);

		return detSalidaListaBS;
	}

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
