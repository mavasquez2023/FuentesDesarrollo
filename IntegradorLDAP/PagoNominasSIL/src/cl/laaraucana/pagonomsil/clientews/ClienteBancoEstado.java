package cl.laaraucana.pagonomsil.clientews;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.tempuri.W2W_IIISoap12Stub;
import org.tempuri.W2W_IIISoapStub;

import cl.laaraucana.pagnomsil.clientews.vo.EntradaWSBES;
import cl.laaraucana.pagnomsil.clientews.vo.SalidaWSBES;
import cl.laaraucana.pagonomsil.clientews.model.AbstractEntradaVO;
import cl.laaraucana.pagonomsil.clientews.model.AbstractSalidaVO;
import cl.laaraucana.pagonomsil.clientews.model.ConstantesRespuestasWS;
import cl.laaraucana.pagonomsil.utils.Configuraciones;


public class ClienteBancoEstado{

	protected Logger logger = Logger.getLogger(this.getClass());

	public AbstractSalidaVO call(AbstractEntradaVO entradaEncriptada) throws Exception {
		logger.debug("Inicio Web Service: BancoEstado");
		String ep = Configuraciones.getConfig("ep.BancoEstado");
		
		W2W_IIISoapStub stub = new W2W_IIISoapStub();
		stub._setProperty(W2W_IIISoap12Stub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Estableciendo conexión a Cliente Banco estado");
		
		EntradaWSBES entradaVO= (EntradaWSBES)entradaEncriptada;
		SalidaWSBES salidaVO= new SalidaWSBES();
		try {
			String respuestaEncriptada = stub.WS_W2WEntrada(entradaVO.getMensaje());
			salidaVO.setMensaje(respuestaEncriptada);
			salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("OK");
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio Banco estado: compruebe el servicio");
			return salidaVO;
		}
		
			
		logger.debug(">> Salida Web Service: BancoEstado");
		return salidaVO;

	}

}