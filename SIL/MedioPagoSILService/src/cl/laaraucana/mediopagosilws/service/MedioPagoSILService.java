package cl.laaraucana.mediopagosilws.service;

import org.apache.log4j.Logger;

import cl.laaraucana.mediopagosilws.core.manager.MainManager;
import cl.laaraucana.mediopagosilws.service.vo.EntradaVO;
import cl.laaraucana.mediopagosilws.service.vo.SalidaVO;
import cl.laaraucana.mediopagosilws.utils.Constante;

public class MedioPagoSILService {
	Logger log = Logger.getLogger(this.getClass());

	public SalidaVO consultaMedioPago(EntradaVO entrada) {
		SalidaVO salida = new SalidaVO();
		log.info("Datos Entrada");
		log.info("-rutEmpresa:"+entrada.getRutEmpresa());
		log.info("-rutEmpresaDv:"+entrada.getRutEmpresaDv());
		log.info("-oficinaEmisora:"+entrada.getOficinaEmisora());
		log.info("-sucursal:"+entrada.getSucursal());
		String mensaje = MainManager.validaEntrada(entrada);
		if (mensaje != null) {
			salida.setCodigoRespuesta(Constante.CODIGO_RESPUESTA_ERROR);
			salida.setGlosaRespuesta("Error, datos ingresador no validos: " + mensaje);
			return salida;
		}

		salida = MainManager.consultaMedioPago(entrada);
		log.info("Datos Salida");
		log.info("-datos.medioPago:"+salida.getDatos().getMedioPago());
		log.info("-datos.oficinaPago:"+salida.getDatos().getOficinaPago());
		log.info("-codigoRespuesta:"+salida.getCodigoRespuesta());
		log.info("-glosaRespuesta:"+salida.getGlosaRespuesta());
		return salida;
	}
}
