package cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaDatosAfiliacionAs400.ConsultaDatosAfiliacionAs400EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaDatosAfiliacionAs400.ConsultaDatosAfiliacionAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaDatosAfiliacionAs400.ConsultaDatosAfiliacionAs400SalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaDatosAfiliacionAs400.DetalleConsultaDatosAfiliacionAs400SalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.EntradaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosAfiliacionAs400.VO.SalidaListaConsultaDatosAfiliacionAs400;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaDatosAfiliacionAs400 implements WSInterface {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		SalidaListaConsultaDatosAfiliacionAs400 salidaVO = new SalidaListaConsultaDatosAfiliacionAs400();
//		try {
			
			
		EntradaConsultaDatosAfiliacionAs400 entradaVO = (EntradaConsultaDatosAfiliacionAs400) entrada;
		logger.debug("<< Ingreso a ClienteConsultaDatosAfiliacionAs400 con rut: "+entradaVO.getRut());
		
		ConsultaDatosAfiliacionAs400EntradaVO entradaWS = new ConsultaDatosAfiliacionAs400EntradaVO();
		entradaWS.setRut(entradaVO.getRut().toUpperCase());
		
		ConsultaDatosAfiliacionAs400PortProxy ws = new ConsultaDatosAfiliacionAs400PortProxy();
		ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.DatosAfiliacionAs400"));
		
//<=====	Codigo nuevo, con validacion
		ConsultaDatosAfiliacionAs400SalidaVO respuesta = new ConsultaDatosAfiliacionAs400SalidaVO();
		
		//Validar problemas de conexi�n.
		try {
			respuesta = ws.consultaDatosAfiliacionAs400(entradaWS);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio ConsultaDatosAfiliacionAs400: compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos ConsultaDatosAfiliacionAs400 OK. C�digo error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("ConsultaDatosAfiliacionAs400. El rut no se encuentra como afiliado. 2");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
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
		logger.debug("Retorno llamada a ws ClienteConsultaDatosAfiliacionAs400");
		logger.debug(">> Salida de ClienteConsultaDatosAfiliacionAs400");
		return salidaVO;
	}
	
	private SalidaListaConsultaDatosAfiliacionAs400 mapear(ConsultaDatosAfiliacionAs400SalidaVO entrada){
		logger.debug("Salida de mapeo de datos ClienteConsultaDatosAfiliacionAs400");
		SalidaListaConsultaDatosAfiliacionAs400 salidaVO = new SalidaListaConsultaDatosAfiliacionAs400();
		List<SalidaConsultaDatosAfiliacionAs400> afiliado = new ArrayList<SalidaConsultaDatosAfiliacionAs400>();
		SalidaConsultaDatosAfiliacionAs400 detalleAfiliado = null;
		
		salidaVO.setNombre(entrada.getNombre().replaceAll(" +", " ").trim());
		salidaVO.setTotalEmpresa(entrada.getTotalEmpresa());
		
		for (DetalleConsultaDatosAfiliacionAs400SalidaVO detalle : entrada.getDetalleEmpresa()) {
			detalleAfiliado = new SalidaConsultaDatosAfiliacionAs400();
			
			detalleAfiliado.setTipoAfiliado(detalle.getTipoAfiliado().trim());
			detalleAfiliado.setFechaAfiliacion(detalle.getFechaAfiliacion().trim());
			detalleAfiliado.setOficina(detalle.getOficina().trim());
			detalleAfiliado.setRutEmpresa(detalle.getRutEmpresa().trim());
			detalleAfiliado.setTipoEmpresa(detalle.getTipoEmpresa().trim());
			detalleAfiliado.setNombreEmpresa(detalle.getNombreEmpresa().replaceAll(" +", " ").trim());
			detalleAfiliado.setSucursal(detalle.getSucursal().trim());
			
			afiliado.add(detalleAfiliado);			
		}
		
		salidaVO.setDetalleEmpresa(afiliado);
		logger.debug("Salida de mapeo de datos ClienteConsultaDatosAfiliacionAs400");
		return salidaVO;
		
	}
	
}
