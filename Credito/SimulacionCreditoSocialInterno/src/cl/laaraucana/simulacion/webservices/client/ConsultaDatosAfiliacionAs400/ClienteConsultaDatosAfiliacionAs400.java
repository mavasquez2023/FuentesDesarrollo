package cl.laaraucana.simulacion.webservices.client.ConsultaDatosAfiliacionAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion.ConsultaDatosAfiliacionAs400EntradaVO;
import cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion.ConsultaDatosAfiliacionAs400PortProxy;
import cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion.ConsultaDatosAfiliacionAs400SalidaVO;
import cl.laaraucana.simulacion.webservices.autogenerado.consultadatosafiliacion.DetalleConsultaDatosAfiliacionAs400SalidaVO;
import cl.laaraucana.simulacion.webservices.WSInterface;
import cl.laaraucana.simulacion.webservices.client.ConsultaDatosAfiliacionAs400.VO.EntradaConsultaDatosAfiliacionAs400;
import cl.laaraucana.simulacion.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaConsultaDatosAfiliacionAs400;
import cl.laaraucana.simulacion.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaListaConsultaDatosAfiliacionAs400;
import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class ClienteConsultaDatosAfiliacionAs400 implements WSInterface {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		SalidaListaConsultaDatosAfiliacionAs400 salidaVO = new SalidaListaConsultaDatosAfiliacionAs400();
//		try {
			
			
		EntradaConsultaDatosAfiliacionAs400 entradaVO = (EntradaConsultaDatosAfiliacionAs400) entrada;
		logger.debug("<< Ingreso a ClienteConsultaDatosAfiliacionAs400 con rut: "+entradaVO.getRut());
		
		ConsultaDatosAfiliacionAs400EntradaVO entradaWS = new ConsultaDatosAfiliacionAs400EntradaVO();
		entradaWS.setRut(entradaVO.getRut());
		
		ConsultaDatosAfiliacionAs400PortProxy ws = new ConsultaDatosAfiliacionAs400PortProxy();
		ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.DatosAfiliacionAs400"));
		
//<=====	Codigo nuevo, con validacion
		ConsultaDatosAfiliacionAs400SalidaVO respuesta = new ConsultaDatosAfiliacionAs400SalidaVO();
		
		//Validar problemas de conexión.
		try {
			respuesta = ws.consultaDatosAfiliacionAs400(entradaWS);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio ConsultaDatosAfiliacionAs400: compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos ConsultaDatosAfiliacionAs400 OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("ConsultaDatosAfiliacionAs400. El rut no se encuentra como afiliado. 2");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ConsultaDatosAfiliacionAs400: "+ respuesta.getLog().getId());
				logger.debug(salidaVO.getMensaje());
			}
		}
//<=====	Codigo nuevo, con validacion



//<=====	Codigo antiguo, sin validacion
		
//		logger.debug("<< LLamamos al ws ClienteConsultaDatosAfiliacionAs400");
//		salidaVO = mapear(ws.consultaDatosAfiliacionAs400(entradaWS));
//		logger.debug(">> Salida al ws ClienteConsultaDatosAfiliacionAs400");
//		
//		
//		salidaVO.setCodigoError("0");
//		salidaVO.setMensaje("Carga de datos de afiliado en AS400 OK");
//		
//		} catch (Exception e) {
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("error "+e.getMessage());
//		}
//<=====	Codigo antiguo, sin validacion
		//logger.debug("Retorno llamada a ws ClienteConsultaDatosAfiliacionAs400");
		logger.debug(">> Salida de ClienteConsultaDatosAfiliacionAs400");
		return salidaVO;
	}
	
	private SalidaListaConsultaDatosAfiliacionAs400 mapear(ConsultaDatosAfiliacionAs400SalidaVO entrada){
		SalidaListaConsultaDatosAfiliacionAs400 salidaVO = new SalidaListaConsultaDatosAfiliacionAs400();
		List<SalidaConsultaDatosAfiliacionAs400> afiliado = new ArrayList<SalidaConsultaDatosAfiliacionAs400>();
		SalidaConsultaDatosAfiliacionAs400 detalleAfiliado = null;
		
		salidaVO.setNombre(entrada.getNombre());
		salidaVO.setTotalEmpresa(entrada.getTotalEmpresa());
		
		for (DetalleConsultaDatosAfiliacionAs400SalidaVO detalle : entrada.getDetalleEmpresa()) {
			detalleAfiliado = new SalidaConsultaDatosAfiliacionAs400();
			
			detalleAfiliado.setTipoAfiliado(detalle.getTipoAfiliado());
			detalleAfiliado.setFechaAfiliacion(detalle.getFechaAfiliacion());
			detalleAfiliado.setOficina(detalle.getOficina());
			detalleAfiliado.setRutEmpresa(detalle.getRutEmpresa());
			detalleAfiliado.setTipoEmpresa(detalle.getTipoEmpresa());
			detalleAfiliado.setNombreEmpresa(detalle.getNombreEmpresa());
			detalleAfiliado.setSucursal(detalle.getSucursal());
			
			afiliado.add(detalleAfiliado);			
		}
		
		salidaVO.setDetalleEmpresa(afiliado);
		logger.debug("Salida de mapeo de datos ClienteConsultaDatosAfiliacionAs400");
		return salidaVO;
		
	}
	
}
