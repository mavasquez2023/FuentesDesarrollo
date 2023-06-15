package cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT;

import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.MessageHeader;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRol;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUT;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRolReq;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.EntradaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.SalidaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusRolOUT.VO.SalidaListaAfiliadoRolVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtilSinAS400;

public class ClienteQueryBPStatusRolOUT implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryBPStatusRolOUT");

		SalidaListaAfiliadoRolVO salidaListaVO = new SalidaListaAfiliadoRolVO();
		EntradaAfiliadoRolVO entradaVO = (EntradaAfiliadoRolVO) entrada;

		String ep = Configuraciones.getConfig("ep.QueryBPStatusRol");
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
		
		QueryBPStatusRolOUTBindingStub stub = new QueryBPStatusRolOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryBPStatusRolOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		
		QueryBPStatusRolReq query = new QueryBPStatusRolReq();

		query.setRutBP(entradaVO.getRut().toUpperCase());
		logger.debug("Datos seteados al VO");
		
		QueryBPStatusRol[] respuesta =null;
		try {
			respuesta = stub.queryBPStatusRolOUT(query);
			salidaListaVO= mapear(respuesta, entradaVO.getRol());
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio QueryBPStatusRol: compruebe el servicio");
			return salidaListaVO;
		}
		
		if(respuesta==null){
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio QueryBPStatusrol: salida null");
			logger.debug(salidaListaVO.getMensaje());
		}
		

//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusRolOUT");
		return salidaListaVO;

	}

	private SalidaListaAfiliadoRolVO mapear(QueryBPStatusRol[] response, String rol) {

		SalidaListaAfiliadoRolVO salidaLista = new SalidaListaAfiliadoRolVO();
		ArrayList<SalidaAfiliadoRolVO> lista = new ArrayList<SalidaAfiliadoRolVO>();
		
		for (int i = 0; i < response.length; i++) {
			QueryBPStatusRol object= (QueryBPStatusRol)response[i];
			if(UsuarioServiceUtilSinAS400.obtenerTipoAfiliadoSap(object.getRol()).equals(rol)){
				SalidaAfiliadoRolVO salida= new SalidaAfiliadoRolVO();
				salida.setRutBP(object.getRutBP());
				salida.setRol(UsuarioServiceUtilSinAS400.obtenerTipoAfiliadoSap(object.getRol()));
				salida.setFechaInicioRol(object.getFechaInicioRol());
				salida.setFechaTerminoRol(object.getFechaFinRol());
				lista.add(salida);
				break;
			}
		}
		salidaLista.setListaAfiliado(lista);

		return salidaLista;
	}
}