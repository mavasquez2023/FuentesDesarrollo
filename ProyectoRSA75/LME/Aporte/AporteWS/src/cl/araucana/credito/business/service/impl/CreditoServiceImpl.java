/**
 * 
 */
package cl.araucana.credito.business.service.impl;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.Log;
import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2BindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUT2ServiceLocator;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader2;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse2;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUTServiceLocator;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse;

import cl.araucana.aporte.common.util.ConfigUtil;
import cl.araucana.aporte.orqInput.bo.CreditoCallBO;
import cl.araucana.aporte.orqInput.bo.CreditoDetalleBO;
import cl.araucana.aporte.orqInput.bo.CreditoResultBO;
import cl.araucana.aporte.orqInput.bo.ErrorResultBO;
import cl.araucana.credito.business.service.CreditoService;



/**
 * @author usist199
 *
 */
public class CreditoServiceImpl implements CreditoService{
	private Logger log = Logger.getLogger(CreditoServiceImpl.class);
	private int totalMonto=0;
	public Object getContratosByRutDeudor(String rut, int tipo) throws Exception {
		 CreditoCallBO creditoCallBO = new CreditoCallBO();
		 CreditoResultBO creditoBO = new CreditoResultBO();
		 CreditoDetalleBO [] creditoDetalleBO=null;
		 String creditoGlsError="";
	     int creditoCodError=0;
	     
		try {
			log.info("Generando request paraa BS");
			QueryCompactContract queryCC= new QueryCompactContract(rut, String.valueOf(tipo));
			MessageHeader message= new MessageHeader();
			QueryCompactContractHeaderRequest requestQCCHO2= new QueryCompactContractHeaderRequest(message, queryCC);
			
			log.info("Recuperando parámetros autorización a BS");
			String endpoint= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.header.endpoint");
			String username= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.username");
			String password= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.password");
			//listaEmpleadores = empleadorDao.getEmpleadoresCRM(rut, endpoint, username, password);
			
			log.info("Invocando conexión a BS");
			QueryCompContHeaderOUT2ServiceLocator locatorQCCHO2= new QueryCompContHeaderOUT2ServiceLocator();
			if(endpoint==null || endpoint.equals("")){
				endpoint= locatorQCCHO2.getHTTP_PortAddress();
			}
			URL url= new URL(endpoint);
			QueryCompContHeaderOUT2BindingStub bindingQCCHO2= new QueryCompContHeaderOUT2BindingStub(url, locatorQCCHO2);
			bindingQCCHO2.setUsername(username);
			bindingQCCHO2.setPassword(password);
			
			log.info("Invocando request a BS");
			QueryCompactContractHeaderResponse2 responseQCCHR2=  bindingQCCHO2.queryCompContrHeader(requestQCCHO2);
			creditoCodError= Integer.parseInt(responseQCCHR2.getResultCode());
			if(creditoCodError==0){
				QueryCompactContractHeader2 detalleContratos[]=  responseQCCHR2.getQueryCompactContractHeader();
				int totalContratos=detalleContratos.length;
				if (totalContratos == 0){
					creditoDetalleBO = null;
				}	
				else{
					creditoDetalleBO = new CreditoDetalleBO [totalContratos];
				}
				log.info("Obteniendo información de contratos: " + totalContratos); 
				List totalCuotas= new ArrayList();
				for (int i = 0; i < totalContratos; i++) {
					String numeroContrato= detalleContratos[i].getContractNumber();
					List cuotasAPagar= getDetalleCredito(numeroContrato);
					totalCuotas.addAll(cuotasAPagar);
				}
				creditoDetalleBO= (CreditoDetalleBO[])totalCuotas.toArray();
				creditoBO.setCreditoDetalle(creditoDetalleBO);
				creditoBO.setNumRegistros(creditoDetalleBO.length);
				creditoBO.setMonto(totalMonto);
			}else{
				Log log[]= responseQCCHR2.getLog();
				for (int i = 0; i < log.length; i++) {
					creditoGlsError+= log[i].getMESSAGE() + "\n";
				}
			}
			creditoCallBO.setCredito(creditoBO);
			creditoCallBO.setCodError(creditoCodError);
			creditoCallBO.setGlsError(creditoGlsError);
		} catch (Exception e) {
			log.error("Codigo 0121: Ocurrio un problema al llamar al WS BS");
			e.printStackTrace();
		}
		return creditoCallBO;
	}

	public List getDetalleCredito(String numeroContrato ){
		final int PENDIENTE= 1;
		List cuotasAPagar= new ArrayList();
		try {
			log.info("Generando request paraa BS");
			MessageHeader message= new MessageHeader();
			QueryContractCashFlowRequest requestQCCFO= new QueryContractCashFlowRequest(message, numeroContrato);
			
			log.info("Recuperando parámetros autorización a BS");
			String endpoint= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.cashflow.endpoint");
			String username= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.username");
			String password= ConfigUtil.getValorRecursosDeAplicacion("bs.credito.password");
			
			log.info("Invocando conexión a BS");
			QueryContractCashFlowOUTServiceLocator locatorQCCFO= new QueryContractCashFlowOUTServiceLocator();
			if(endpoint==null || endpoint.equals("")){
				endpoint= locatorQCCFO.getHTTP_PortAddress();
			}
			URL url= new URL(endpoint);
			QueryContractCashFlowOUTBindingStub bindingQCCFO= new QueryContractCashFlowOUTBindingStub(url, locatorQCCFO);
			bindingQCCFO.setUsername(username);
			bindingQCCFO.setPassword(password);
			
			log.info("Invocando request a BS");
			QueryContractCashFlowResponse responseQCCFO= bindingQCCFO.queryContractCashFlow(requestQCCFO);
			DetalleCuotasCF detalleCF[]= responseQCCFO.getDetalleCuotas();
			
			for (int i = 0; i < detalleCF.length; i++) {
				if(Integer.parseInt(detalleCF[i].getEstadoCuota())==PENDIENTE){
					CreditoDetalleBO creditoDetalle = new CreditoDetalleBO();
					creditoDetalle.setCodigoOficina(Integer.parseInt(detalleCF[i].getOficinaPago()));
					creditoDetalle.setFolioCredito(Integer.parseInt(detalleCF[i].getFolioPago()));
					creditoDetalle.setNumCuota(detalleCF[i].getNroCuota().intValue());
					creditoDetalle.setTotalCoutas(responseQCCFO.getE_TOTAL_CUOTAS().intValue());
					creditoDetalle.setEstadoCouta(Integer.parseInt(detalleCF[i].getEstadoCuota()));
					creditoDetalle.setFechaVencimiento(getAAAAMMDD(detalleCF[i].getFechaVencCuota()));
					creditoDetalle.setLineaCredito(Integer.parseInt(responseQCCFO.getLineaComercial()));
					creditoDetalle.setValorCouta(Integer.parseInt(detalleCF[i].getTotalCuota()));
					totalMonto+= Integer.parseInt(detalleCF[i].getTotalCuota());
					creditoDetalle.setCapital(Integer.parseInt(detalleCF[i].getMontoCapital()));
					creditoDetalle.setSeguros(Integer.parseInt(detalleCF[i].getMontoSeguros()));
					creditoDetalle.setIntereses(Integer.parseInt(detalleCF[i].getMontoInteres()));
					creditoDetalle.setGravamenes(Integer.parseInt(detalleCF[i].getMontoGravamenes()));	
					creditoDetalle.setMultas(0);	
					creditoDetalle.setMontoAbonado(Integer.parseInt(detalleCF[i].getMontoAbono()));
					creditoDetalle.setMontoDescuento(0);
					cuotasAPagar.add(creditoDetalle);
				}
			}
			
		} catch (Exception e) {
			log.error("Codigo 0121: Ocurrio un problema al llamar al WS BS");
			e.printStackTrace();
		}
		
		
		return cuotasAPagar;
	}
	
    public static int getAAAAMMDD(Date date ) 
    { 
     Calendar cal = null;
     String salida=null;
     try {   
      DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
      cal=Calendar.getInstance();
      cal.setTime(date);
      salida = formatter.format(cal); 
      return Integer.parseInt(salida);
      }
      catch (Exception e)
      {
          System.out.println("Exception :"+e);
          return 20000101;
      }  
      
     }
}
