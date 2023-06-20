package cl.laaraucana.reportesil.clientesws;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.reportesil.clientesws.model.AbstractEntradaVO;
import cl.laaraucana.reportesil.clientesws.model.AbstractSalidaVO;
import cl.laaraucana.reportesil.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.reportesil.clientesws.model.WSInterface;
import cl.laaraucana.reportesil.clientesws.vo.AnexoAfiliadoVO;
import cl.laaraucana.reportesil.clientesws.vo.EntradaInfoAfiliadoVO;
import cl.laaraucana.reportesil.clientesws.vo.SalidainfoAfiliadoVO;
import cl.laaraucana.reportesil.utils.Configuraciones;


import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RES;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_DEUD;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_PENS;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_TRAB;
import com.lautaro.xi.CRM.WEB_Mobile.SI_INFO_AFILIADO_OUTBindingStub;

public class ClienteInfoAfiliado implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: InfoAfiliado");
		String ep = Configuraciones.getConfig("ep.InfoAfiliado");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
		SalidainfoAfiliadoVO salidaListaVO = new SalidainfoAfiliadoVO();
		EntradaInfoAfiliadoVO entradaVO = (EntradaInfoAfiliadoVO) entrada;

		SI_INFO_AFILIADO_OUTBindingStub stub = new SI_INFO_AFILIADO_OUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(SI_INFO_AFILIADO_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion SI_INFO_AFILIADO_OUT seteados");

		DT_INFO_AFILIADO_REQ query = new DT_INFO_AFILIADO_REQ();
		query.setRUT_BP(entradaVO.getRut());
		logger.debug("Datos seteados al VO");


		DT_INFO_AFILIADO_RES respuesta = new DT_INFO_AFILIADO_RES();
		
		try {
			respuesta = stub.SI_INFO_AFILIADO_OUT(query);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio QueryBPStatus: compruebe el servicio");
			return salidaListaVO;
		}
		
		
//<==== Validacion Nueva	
		if(respuesta!=null){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos ClienteQueryBPStatusOUT OK. Código error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			String msg = "RUT no válido";				
			salidaListaVO.setMensaje("Error en servicio InfoAfiliado: " + msg);
			logger.debug(salidaListaVO.getMensaje());
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusOUT");
		return salidaListaVO;

	}

	private SalidainfoAfiliadoVO mapear(DT_INFO_AFILIADO_RES response) {
		
		SalidainfoAfiliadoVO info_afiliado = new SalidainfoAfiliadoVO();
		String nombre= response.getNOMBRE();
		nombre = nombre==null ?"":nombre;
		info_afiliado.setNombreCompleto(nombre);
		info_afiliado.setFechaNacimiento(response.getFECHA_NACIMIENTO());
		//Se setea Trabajador
		String trabajador= response.getTRABAJADOR();
		info_afiliado.setTrabajador(trabajador!=null&&trabajador.equals("X")?true:false);
		//Se setea Pensionado
		String pensionado= response.getPENSIONADO();
		info_afiliado.setPensionado(pensionado!=null&&pensionado.equals("X")?true:false);
		//Se setea Deudor Directo
		info_afiliado.setDeudordirecto(trabajador==null&&pensionado==null?true:false);

		return info_afiliado;
	}
}