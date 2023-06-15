package cl.laaraucana.simulacion.webservices.client.SimulaRepro;




import java.text.DecimalFormat;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_RES;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUTProxy;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUTService;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorRepro_OUTServiceLocator;

import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;


public class SimulaReproClient {

	protected Logger logger = Logger.getLogger(this.getClass());
	private static DecimalFormat formateador2 = new DecimalFormat("###,###.##");
	
	public AbstractSalidaVO call(DT_SimuladorRepro_REQ entrada) {
		DT_SimuladorRepro_RES respuesta=null;
		ResultadoSim resultado=null;
		try {
			logger.debug("Inicio Web Service: SimulaReproClient");
			String ep = Configuraciones.getConfig("ep.SimuladorReprogramacion");
			String user = Configuraciones.getConfig("SAP.user");
			String clave = Configuraciones.getConfig("SAP.password");
			
			SI_SimuladorRepro_OUTBindingStub stub = new SI_SimuladorRepro_OUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(SI_SimuladorRepro_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			respuesta = stub.SI_SimuladorRepro_OUT(entrada);
		} catch (Exception e) {
			e.printStackTrace();
			resultado = new ResultadoSim();
			if(e.getMessage().indexOf("Invalid date")>-1){
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);
				resultado.setMensaje("Monto Abono supera Capital Adeudado");
			}else{
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				resultado.setMensaje("Problemas al invocar ws Reprogramacion, mensaje= " + e.getMessage());
			}
			logger.error("Problemas al invocar ws Reprogramacion, mensaje= " + e.getMessage());
		} 
		if(respuesta!=null){
			resultado= mapear(respuesta);
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			resultado.setMensaje("Carga de datos Cliente Reprogramacion OK. Código error: 0");
		}
	
		return resultado;
	}
	
	private ResultadoSim mapear(DT_SimuladorRepro_RES salida){
		ResultadoSim resultado = new ResultadoSim();
		if(salida!=null){
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			resultado.setCae(String.valueOf(salida.getCAE()));
			resultado.setTasaMensual(String.valueOf(salida.getTASA_INT_MENSUAL()));
			resultado.setTasaAnual(formateador2.format(salida.getTASA_INT_MENSUAL().doubleValue() *12).replaceAll(",", "."));
			resultado.setCostoTotal(String.valueOf(salida.getCOSTO_TOTAL()));
			resultado.setFechaPrimerVencimiento(salida.getFECHA_PRIMER_VTO());
			resultado.setGcCondonado(String.valueOf(salida.getGC_CONDONADO().doubleValue() + salida.getHONO_CONDONADO().doubleValue()));
			resultado.setGrvCondonado(String.valueOf(salida.getGRV_CONDONADO()));
			resultado.setIntCondonado(String.valueOf(salida.getINT_CONDONADO()));
			resultado.setMontoAdeudado(String.valueOf(salida.getMONTO_ADEUDADO()));
			resultado.setMontoCuota(String.valueOf(salida.getMONTO_CUOTA()));
			resultado.setSegCes(String.valueOf(salida.getSEG_CES()));
			resultado.setSegDesg(String.valueOf(salida.getSEG_DESG()));
			resultado.setPorcentajeMaximoEndeudamiento(String.valueOf(salida.getPORC_MAX_ENDEUD()));
			resultado.setPorcentajeEndeudamientoSimulado(String.valueOf(salida.getPORC_ENDEUD_SIM()));
			
		}
		return resultado;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		DTSimuladorReproREQ req = new DTSimuladorReproREQ();
		SISimuladorReproOUTService services= new SISimuladorReproOUTService(new URL("http://araqacipit.laaraucana.local:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=WEB_Mobile&receiverParty=&receiverService=&interface=SI_SimuladorRepro_OUT&interfaceNamespace=http://lautaro.com/xi/BS/WEB-Mobile"));
		URL url = new URL("http://araqacipit.laaraucana.local:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=WEB_Mobile&receiverParty=&receiverService=&interface=SI_SimuladorRepro_OUT&interfaceNamespace=http://lautaro.com/xi/BS/WEB-Mobile");
		
		Service service = SISimuladorReproOUTService.create(url, new QName("MT_SimuladorRepro_REQ"));
		SISimuladorReproOUTService hello = service.getPort(SISimuladorReproOUTService.class);
		
		Map<String, Object> req_ctx = ((BindingProvider)services).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://araqacipit.laaraucana.local:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=WEB_Mobile&receiverParty=&receiverService=&interface=SI_SimuladorRepro_OUT&interfaceNamespace=http://lautaro.com/xi/BS/WEB-Mobile");

        Map<String, List<String>> headers = new HashMap();
        headers.put("Username", Collections.singletonList("u_integrador"));
        headers.put("Password", Collections.singletonList("Soporte2012"));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
		
		//new SISimuladorReproOUTService().
		*/
      
       //hello.getHTTPPort().siSimuladorReproOUT(req);
		System.setProperty(LogFactory.FACTORY_PROPERTY, LogFactory.FACTORY_DEFAULT);
		
		SI_SimuladorRepro_OUTProxy proxy= new SI_SimuladorRepro_OUTProxy();
		
		SI_SimuladorRepro_OUTService service= new SI_SimuladorRepro_OUTServiceLocator();
		
		DT_SimuladorRepro_REQ req = new DT_SimuladorRepro_REQ();
		
		SI_SimuladorRepro_OUTBindingStub stub = new SI_SimuladorRepro_OUTBindingStub();
		stub.setUsername("u_integrador");
		stub.setPassword("Soporte2012");
		
		stub.SI_SimuladorRepro_OUT(req);
		
	}
	
}
