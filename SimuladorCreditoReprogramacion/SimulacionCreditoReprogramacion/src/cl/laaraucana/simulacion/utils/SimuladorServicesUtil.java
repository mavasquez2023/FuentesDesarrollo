package cl.laaraucana.simulacion.utils;


import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimAcuPago_RES;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_REQSTR_CONDONACION;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorRepro_RES;
import cl.laaraucana.simulacion.VO.ParametrosSimulacionVO;
import cl.laaraucana.simulacion.VO.ResultadoAcuerdo;
import cl.laaraucana.simulacion.VO.ResultadoSim;
import cl.laaraucana.simulacion.webservices.client.SimulaRepro.SimulaAcuerdoClient;
import cl.laaraucana.simulacion.webservices.client.SimulaRepro.SimulaReproClient;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.OficinaGastosNotarialClient;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaLista;


public class SimuladorServicesUtil {
	protected static Logger logger = Logger.getLogger(SimuladorServicesUtil.class);

	public static ResultadoSim getResultadoSimulacion(ParametrosSimulacionVO form) {
		DT_SimuladorRepro_RES salida=null;
		ResultadoSim resultado = new ResultadoSim();

		try {
			DT_SimuladorRepro_REQ parametros= new DT_SimuladorRepro_REQ();
			parametros.setRUT(form.getRutAfiliado());
			parametros.setNRO_CONTRATO(form.getContrato());
			parametros.setPLAZO(new BigInteger(form.getPlazo()));
			parametros.setMESES_DE_GRACIA(new BigInteger(form.getMesesGracia()));
			parametros.setOFICINA(form.getOficina());
			parametros.setTIPO_AFILIADO(form.getTipoAfiliado());
			parametros.setMONTO_ABONO(new BigDecimal(form.getMontoAbono()));
			parametros.setSEG_CES(form.getSeguroCesantia());
			parametros.setSEG_DESG(form.getSeguroDesgravamen());
			parametros.setTASA_INTERES(form.getTasaInteres());
			parametros.setPRODUCTO(form.getProductoReprogramacion());
			parametros.setANEXO(form.getAnexo());
			parametros.setNRO_INSCRIPCION(form.getNumeroInscripcion());
			parametros.setRENTA(new BigDecimal(form.getRenta()));
			DT_SimuladorRepro_REQSTR_CONDONACION condonacion= new DT_SimuladorRepro_REQSTR_CONDONACION();
			condonacion.setPORCEN_GC(new BigDecimal(form.getPorcentajeGastosCobranza()));
			condonacion.setPORCEN_GRV(new BigDecimal(form.getPorcentajeGravamenes()));
			condonacion.setPORCEN_INT(new BigDecimal(form.getPorcentajeIntereses()));
			condonacion.setPORCEN_CAPITAL(new BigDecimal(form.getPorcentajeCapital()));
			condonacion.setPORCEN_HONO(new BigDecimal(form.getPorcentajeHonorarios()));
			parametros.setSTR_CONDONACION(condonacion);
			
			SimulaReproClient client= new SimulaReproClient();
			resultado = (ResultadoSim)client.call(parametros);

		} catch (Exception e) {
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			resultado.setMensaje("Error en servicio getResultadoSimulacion: " + e.getMessage());
			logger.debug("Error en servicio getResultadoSimulacion: " + e.getMessage());
			e.printStackTrace();
			return resultado;
		}
		
		return resultado;
	}
	
	public static ResultadoAcuerdo getResultadoAcuerdosPago(ParametrosSimulacionVO form) {
		DT_SimAcuPago_RES salida=null;
		ResultadoAcuerdo resultado = new ResultadoAcuerdo();

		try {
			DT_SimAcuPago_REQ parametros= new DT_SimAcuPago_REQ();
			parametros.setRUT(form.getRutAfiliado());
			parametros.setCONTRATO(form.getContrato());
			parametros.setCUOTAS(new BigInteger(form.getPlazo()));
			parametros.setTIPO_AFILIADO(form.getTipoAfiliado());
			parametros.setMONTO_ABONO_INMEDIATO(new BigDecimal(form.getMontoAbono()));
			parametros.setPRODUCTO(form.getProductoReprogramacion());
			parametros.setPORC_COND_CAPITAL(new BigDecimal(form.getPorcentajeCapital()));
			
			SimulaAcuerdoClient client= new SimulaAcuerdoClient();
			resultado = (ResultadoAcuerdo)client.call(parametros);
			
		} catch (Exception e) {
			resultado.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			resultado.setMensaje("Error en servicio getResultadoAcuerdo: " + e.getMessage());
			logger.debug("Error en servicio getResultadoAcuerdo: " + e.getMessage());
			e.printStackTrace();
			return resultado;
		}
		
		return resultado;
	}
	
	public static OficinaGastosNotarialSalidaLista getOficinasGastos() throws Exception {
		OficinaGastosNotarialClient cliente = new OficinaGastosNotarialClient();
		return cliente.call();
	}
	
	public static void getTipoAfiliadoCRM(String rut) throws Exception {

	}
}
