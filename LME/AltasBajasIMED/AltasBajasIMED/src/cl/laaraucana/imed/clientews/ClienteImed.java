package cl.laaraucana.imed.clientews;

import javax.xml.rpc.holders.StringHolder;

import org.apache.log4j.Logger;
import org.tempuri.WmImed_SrvAltaBeneficiario;
import org.tempuri.WmImed_SrvAltaCredenciales;
import org.tempuri.WmImed_SrvBajaBeneficiario;
import org.tempuri.WmImed_SrvBajaCredenciales;
import org.tempuri.WsAltaSoapStub;
import org.tempuri.WsBajaSoapStub;

import cl.laaraucana.imed.clientews.model.AbstractEntradaVO;
import cl.laaraucana.imed.clientews.model.AbstractSalidaVO;
import cl.laaraucana.imed.clientews.model.ConstantesRespuestasWS;
import cl.laaraucana.imed.clientews.model.WSInterface;
import cl.laaraucana.imed.clientews.vo.SalidaImedVO;
import cl.laaraucana.imed.config.Configuraciones;
import cl.laaraucana.imed.dao.vo.RegistroAltaBajaVO;
import cl.laaraucana.imed.utils.Utils;



public class ClienteImed implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO  callAlta(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: Imed Alta");
		//se define la salida
		SalidaImedVO salidaListaVO = new SalidaImedVO();
		
		String ep = Configuraciones.getConfig("ep.imed.alta");
		int codigoEntidad = Integer.parseInt(Configuraciones.getConfig("imed.credentials.codigoentidad"));
		String rutUsuario = Configuraciones.getConfig("imed.credentials.usuario");
		int clave= Integer.parseInt(Configuraciones.getConfig("imed.credentials.clave"));
		
		WmImed_SrvAltaCredenciales credenciales= new WmImed_SrvAltaCredenciales();
		credenciales.setCodigoEntidad(codigoEntidad);
		credenciales.setRutUsuario(rutUsuario);
		credenciales.setClave(clave);
				
		RegistroAltaBajaVO entradaVO = (RegistroAltaBajaVO) entrada;
		WmImed_SrvAltaBeneficiario beneficiario= new WmImed_SrvAltaBeneficiario();
		beneficiario.setRutBeneficiario(entradaVO.getRutBeneficiario());
		beneficiario.setApellidoPaterno(entradaVO.getApellidoPaterno());
		beneficiario.setApellidoMaterno(entradaVO.getApellidoMaterno());
		beneficiario.setNombres(entradaVO.getNombres());
		beneficiario.setDireccion(entradaVO.getDireccion());
		beneficiario.setComuna(entradaVO.getComuna());
		beneficiario.setFechaNacimiento(Utils.stringToDateAS(entradaVO.getFechaNacimiento()));
		beneficiario.setSexo(entradaVO.getSexo());
		beneficiario.setRelacion(entradaVO.getRelacion());
		beneficiario.setFechaIngreso(Utils.stringToDateAS(entradaVO.getFechaIngreso()));
		beneficiario.setFechaInicioVigencia(Utils.stringToDateAS(entradaVO.getFechaInicioVigencia()));
		beneficiario.setFechaFinVigencia(Utils.stringToDateAS(entradaVO.getFechaFinVigencia()));
		beneficiario.setCodigoPlan(entradaVO.getCodigoPlan());
		beneficiario.setRutTitular(entradaVO.getRutTitular());
		beneficiario.setAtr_1(entradaVO.getAtributo1());
		beneficiario.setAtr_2(entradaVO.getAtributo2());
		beneficiario.setAtr_3(entradaVO.getAtributo3());
		beneficiario.setAtr_4(entradaVO.getAtributo4());
		beneficiario.setAtr_5(entradaVO.getAtributo5());
				
		WsAltaSoapStub stub = new WsAltaSoapStub();
		stub._setProperty(WsAltaSoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Endpoint:" + ep);
		
		StringHolder estado= new StringHolder();
		StringHolder mensaje= new StringHolder();
		StringHolder codigoTransaccion= new StringHolder();
		try {
			logger.debug("Invocando consulta Altas, usuario:" + rutUsuario  + ", rut beneficiario:" + entradaVO.getRutBeneficiario());
			stub.wmImed_SrvAlta(credenciales, beneficiario, estado, mensaje, codigoTransaccion);
			salidaListaVO.setEstado(Integer.parseInt(estado.value));
			salidaListaVO.setMensaje(mensaje.value);
			salidaListaVO.setCodigoTransaccion(codigoTransaccion.value);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			
		} catch (Exception e) {
			logger.error("Error Servicio Alta Imed, mensaje= " + e.getMessage());
			e.printStackTrace();
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio Alta Imed: compruebe el servicio.");
			salidaListaVO.setEstado(1);
			return salidaListaVO;
		}

		logger.debug(">> Salida Web Service: Imed");
		return salidaListaVO;

	}

	
	@Override
	public AbstractSalidaVO callBaja(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: Imed Baja");
		String ep = Configuraciones.getConfig("ep.imed.baja");
		int codigoEntidad = Integer.parseInt(Configuraciones.getConfig("imed.credentials.codigoentidad"));
		String rutUsuario = Configuraciones.getConfig("imed.credentials.usuario");
		int clave= Integer.parseInt(Configuraciones.getConfig("imed.credentials.clave"));
		
		WmImed_SrvBajaCredenciales credenciales= new WmImed_SrvBajaCredenciales();
		credenciales.setCodigoEntidad(codigoEntidad);
		credenciales.setRutUsuario(rutUsuario);
		credenciales.setClave(clave);
				
		RegistroAltaBajaVO entradaVO = (RegistroAltaBajaVO) entrada;
		WmImed_SrvBajaBeneficiario beneficiario= new WmImed_SrvBajaBeneficiario();
		beneficiario.setCodigoPlan(entradaVO.getCodigoPlan());
		beneficiario.setFechaFinVigencia(Utils.stringToDateAS(entradaVO.getFechaFinVigencia()));
		beneficiario.setRutBeneficiario(entradaVO.getRutBeneficiario());
		beneficiario.setRutTitular(entradaVO.getRutTitular());
		
		WsBajaSoapStub stub = new WsBajaSoapStub();
		stub._setProperty(WsBajaSoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Endpoint:" + ep);
		
		SalidaImedVO salidaListaVO = new SalidaImedVO();
		StringHolder estado= new StringHolder();
		StringHolder mensaje= new StringHolder();
		StringHolder codigoTransaccion= new StringHolder();
		try {
			logger.debug("Invocando consulta Bajas Imed, usuario:" + rutUsuario  + ", rut beneficiario:" + entradaVO.getRutBeneficiario());
			stub.wmImed_SrvBaja(credenciales, beneficiario, estado, mensaje, codigoTransaccion);
			salidaListaVO.setEstado(Integer.parseInt(estado.value));
			salidaListaVO.setMensaje(mensaje.value);
			salidaListaVO.setCodigoTransaccion(codigoTransaccion.value);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			
		} catch (Exception e) {
			logger.error("Error Servicio Baja Imed, mensaje= " + e.getMessage());
			e.printStackTrace();
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio Baja Imed: compruebe el servicio");
			return salidaListaVO;
		}
		
		
		logger.debug(">> Salida Web Service: Baja Imed");
		return salidaListaVO;

	}
}