package cl.araucana.wscreditosocial.util;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RES;
import com.lautaro.xi.BS.WEB_Mobile.DT_SimuladorSocial_RESDETALLE;
import com.lautaro.xi.BS.WEB_Mobile.SI_SimuladorSocial_OUTBindingStub;
import cl.araucana.wscreditosocial.vo.RequestWS;
import cl.araucana.wscreditosocial.vo.ResponseDetalleWS;
import cl.araucana.wscreditosocial.vo.ResponseWS;


public class SimuladorServicesUtil {
	protected static Logger logger = Logger.getLogger(SimuladorServicesUtil.class);
	protected static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	
	public static ResponseWS getResultadoSimulacion(RequestWS form) {
		DT_SimuladorSocial_RES salida=null;
		ResponseWS resultado = new ResponseWS();
		resultado.setLog(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
		
		try {
			DT_SimuladorSocial_REQ parametros= new DT_SimuladorSocial_REQ();
			parametros.setMONTO(new BigDecimal(form.getMonto()));
			parametros.setCUOTAS(String.valueOf(form.getCuotas()));
			parametros.setSUCURSAL(form.getSucursal());
			parametros.setTIPO_AFILIADO(form.getTipo_afiliado());
			String cesantia=form.getSeguro_cesantia();
			String tipo_seguro = Configuraciones.getConfig("tipo.seguro.cesantia");
			if(cesantia.equalsIgnoreCase("SI")){
				cesantia="X";
			}else{
				cesantia="";
				tipo_seguro="0";
			}
			parametros.setSEGURO_CESANTIA(cesantia);
			parametros.setTIPO_SEGURO(tipo_seguro);
			String desgravamen=form.getSeguro_desgravamen();
			if(desgravamen.equalsIgnoreCase("SI")){
				desgravamen="X";
			}else{
				desgravamen="";
			}
			parametros.setSEGURO_DESGRAVAMEN(desgravamen);
			
			//llamado al webservice
			salida = call(parametros);
			if(salida!=null){
				String code= salida.getRESULT_CODE();
				resultado.setLog(code);
				if(code.equals(ConstantesRespuestasWS.COD_RESPUESTA_SAP_OK)){
					logger.info("Leyendo resultado simulación");
					resultado.setMonto_cuota((int)salida.getMONTO_CUOTA().doubleValue());
					resultado.setTasa_int_mensual(salida.getTASA_INT_MENSUAL().doubleValue());
					resultado.setTasa_int_anual(salida.getTASA_INT_ANUAL().doubleValue());
					resultado.setCae(salida.getCAE().doubleValue());
					if(salida.getIMPUESTO()!=null){
						resultado.setImpuesto((int)salida.getIMPUESTO().doubleValue());
					}
					if(salida.getGASTO_NOTARIAL()!=null){
					resultado.setGasto_notarial((int)salida.getGASTO_NOTARIAL().doubleValue());
					}
					resultado.setCosto_total_credito((int)salida.getCTC().doubleValue());
					if(salida.getCOSTO_MENSUAL_DESGRAVAMEN()!=null){
						resultado.setCosto_mensual_desgravamen((int)salida.getCOSTO_MENSUAL_DESGRAVAMEN().doubleValue());
						resultado.setCosto_total_desgravamen((int)salida.getCOSTO_TOTAL_DESGRAVAMEN().doubleValue());
					}
					if(salida.getCOSTO_MENSUAL_CESANTIA()!=null){
						resultado.setCosto_mensual_cesantia((int)salida.getCOSTO_MENSUAL_CESANTIA().doubleValue());
						resultado.setCostos_total_cesantia((int)salida.getCOSTOS_TOTAL_CESANTIA().doubleValue());
					}
					Date fechavenc= salida.getFECHA_PRIMER_VENCIMIENTO();
					if(fechavenc!=null){
						String date = DATE_FORMAT.format(fechavenc);
						resultado.setFecha_primer_vencimiento(Integer.parseInt(date));
					}
				    
					List<ResponseDetalleWS> cuotas= new ArrayList<ResponseDetalleWS>();
					logger.info("Leyendo detalle de cuotas");
					for (int i = 0; i < salida.getDETALLE().length; i++) {
						DT_SimuladorSocial_RESDETALLE detalle= salida.getDETALLE(i);
						ResponseDetalleWS respdet= new ResponseDetalleWS();
						respdet.setMonto_cuota((int)detalle.getMONTO_CUOTA().doubleValue());
						respdet.setMonto_interes((int)detalle.getMONTO_INTERES().doubleValue());
						respdet.setNum_cuota(detalle.getNUM_CUOTA());
						respdet.setSaldo_capital((int)detalle.getSALDO_CAPITAL().doubleValue());
						respdet.setSeguro_cesantia((int)detalle.getSEGURO_CESANTIA().doubleValue());
						respdet.setSeguro_desgravamen((int)detalle.getSEGURO_DESGRAVAMEN().doubleValue());
						Date fechavenc_cuota= detalle.getFECHA_VENCIMIENTO();
					    String datecuota = DATE_FORMAT.format(fechavenc_cuota);
					    respdet.setFecha_vencimiento(Integer.parseInt(datecuota));
					    cuotas.add(respdet);
					}
					resultado.setDetalleCuotas(cuotas);
				}
			}
			
			
		} catch (Exception e) {
			logger.error("Error en servicio getResultadoSimulacion: " + e.getMessage());
			e.printStackTrace();
			return resultado;
		}
		
		return resultado;
	}
	
	public synchronized static DT_SimuladorSocial_RES call(DT_SimuladorSocial_REQ entrada) {
		DT_SimuladorSocial_RES respuesta=null;
		try {
			String ep = Configuraciones.getConfig("ep.SimuladorSocial");
			logger.info("Llamando a WS PI, url:" + ep);
			String user = Configuraciones.getConfig("SAP.user");
			String clave = Configuraciones.getConfig("SAP.password");
			
			SI_SimuladorSocial_OUTBindingStub stub = new SI_SimuladorSocial_OUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(SI_SimuladorSocial_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			respuesta = stub.SI_SimuladorSocial_OUT(entrada);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Problemas al invocar ws PI SimuladorSocial, mensaje= " + e.getMessage());
		} 
	
		return respuesta;
	}
}
