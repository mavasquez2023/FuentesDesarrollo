package cl.laraucana.saldoafavor.ws;


import org.apache.log4j.Logger;

import cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoOut;
import cl.laaraucana.pagoenexceso.delegate.ConsultaPagoEnExcesoPortBindingStub;
import cl.laaraucana.saldoafavor.utils.Configuraciones;
import cl.laaraucana.saldoafavor.vo.AbstractSalidaVO;
import cl.laaraucana.saldoafavor.vo.SalidaSaldoaFavorVO;
 


 

public class ClientePagoenExceso {

	protected Logger logger = Logger.getLogger(this.getClass());

	public AbstractSalidaVO call(String rut) throws Exception {
		logger.info("Inicio Web Service: Pago en Exceso");
		String ep = Configuraciones.getConfig("ep.pagoenexceso");

		SalidaSaldoaFavorVO salidaListaVO = new SalidaSaldoaFavorVO();
		//EntradaSinacofiVO entradaVO = (EntradaSinacofiVO) entrada;

		ConsultaPagoEnExcesoPortBindingStub stub = new ConsultaPagoEnExcesoPortBindingStub();

		stub._setProperty(ConsultaPagoEnExcesoPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.info("Endpoint:" + ep);

		ConsultaPagoEnExcesoOut respuesta = new ConsultaPagoEnExcesoOut();
		
		try {
			logger.info("Invocando consulta Pagos En Exceso,  rut:" + rut);
			respuesta = stub.consultarPagoEnExceso(rut);
		} catch (Exception e) {
			logger.error("Error al consultar Pagos En Exceso, mensaje= " + e.getMessage());
			e.printStackTrace();
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio Pagos En Exceso: compruebe el servicio");
			return salidaListaVO;
		}

		if(respuesta!=null){
			logger.info("Respuesta exitosa Pagos En Exceso, se mapea resultado");
			mapear(salidaListaVO, respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos Pagos En Exceso OK.");
		}else{
			String msg = "Error en servicio Pagos En Exceso, respuesta null";				
			salidaListaVO.setMensaje(msg);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			logger.warn(salidaListaVO.getMensaje());
		}

		logger.info(">> Salida Web Service: Sinacofi");
		return salidaListaVO;

	}

	private SalidaSaldoaFavorVO mapear(SalidaSaldoaFavorVO salidaSFVO, ConsultaPagoEnExcesoOut response) {
		if(response.getPagoEnExceso()!=null){
			salidaSFVO.setFechaCreacion(response.getPagoEnExceso().getFechaCreacion());
			salidaSFVO.setMonto(response.getPagoEnExceso().getMonto());
		}
		return salidaSFVO;
	}
}