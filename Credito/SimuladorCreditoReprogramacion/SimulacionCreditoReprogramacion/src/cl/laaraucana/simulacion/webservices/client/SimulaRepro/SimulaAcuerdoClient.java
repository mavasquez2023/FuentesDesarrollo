package cl.laaraucana.simulacion.webservices.client.SimulaRepro;




import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_RES;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimAcuPago_OUTBindingStub;

import cl.laaraucana.simulacion.VO.ResultadoAcuerdo;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;


public class SimulaAcuerdoClient {

	protected Logger logger = Logger.getLogger(this.getClass());

	public AbstractSalidaVO call(DT_SimAcuPago_REQ entrada) {
		DT_SimAcuPago_RES respuesta=null;
		ResultadoAcuerdo resultado = null;
		try {
			logger.debug("Inicio Web Service: Simula Acuerdos Pago Client");
			String ep = Configuraciones.getConfig("ep.SimuladorAcuerdosPago");
			String user = Configuraciones.getConfig("SAP.user");
			String clave = Configuraciones.getConfig("SAP.password");
			
			SI_SimAcuPago_OUTBindingStub stub = new SI_SimAcuPago_OUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(SI_SimAcuPago_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			respuesta = stub.SI_SimAcuPago_OUT(entrada);
		}catch (Exception e) {
			e.printStackTrace();
			resultado = new ResultadoAcuerdo();
			if(e.getMessage().indexOf("Invalid date")>-1){
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);
				resultado.setMensaje("Monto Abono supera Capital Adeudado");
			}else{
				resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				resultado.setMensaje("Problemas al invocar ws Acuerdo Pago, mensaje= " + e.getMessage());
			}
			logger.error("Problemas al invocar ws Acuerdo Pago, mensaje= " + e.getMessage());
		} 
		if(respuesta!=null){
			resultado= mapear(respuesta);
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			resultado.setMensaje("Carga de datos Cliente Acuerdo Pago OK. Código error: 0");
		}
		return resultado;
	}
	
	private ResultadoAcuerdo mapear(DT_SimAcuPago_RES salida){
		ResultadoAcuerdo resultado = new ResultadoAcuerdo();
		if(salida!=null){
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			resultado.setCae(String.valueOf(salida.getCAE()));
			resultado.setCapitalAdeudado(String.valueOf(salida.getCAPITAL_ADEUDADO()));
			resultado.setCuotasxPagar(String.valueOf(salida.getCUOTAS_X_PAGAR()));
			resultado.setTasaInteresMensual(String.valueOf(salida.getTASA_INT_MENSUAL()));
			resultado.setCapitalComprometido(String.valueOf(salida.getCAPITAL_COMPROMETIDO()));
			resultado.setCapitalCondonado(String.valueOf(salida.getCAPITAL_A_CONDONAR()));
			resultado.setCostoTotal(String.valueOf(salida.getCTC()));
			resultado.setFechaPrimerVencimiento(salida.getFECHA_1ER_VTO());
			resultado.setMontoCuota(String.valueOf(salida.getVALOR_CUOTA_MENS()));
			
		}
		return resultado;
	}
	
	public static void main(String[] args) throws Exception {

		
	}
	
}
