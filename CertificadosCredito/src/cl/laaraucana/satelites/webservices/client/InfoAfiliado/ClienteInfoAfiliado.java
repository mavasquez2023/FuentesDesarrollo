package cl.laaraucana.satelites.webservices.client.InfoAfiliado;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.AnexoAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.EntradaInfoAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.InfoAfiliado.VO.SalidainfoAfiliadoVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

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
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
		
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
			salidaListaVO.setMensaje("Error en servicio SI_INFO_AFILIADO_OUT: compruebe el servicio");
			return salidaListaVO;
		}
		
		
//<==== Validacion Nueva	
		if(respuesta!=null){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos Cliente SI_INFO_AFILIADO_OUT OK. Código error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			String msg = "RUT no válido";				
			salidaListaVO.setMensaje("Error en servicio InfoAfiliado: " + msg);
			logger.debug(salidaListaVO.getMensaje());
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: SI_INFO_AFILIADO_OUT");
		return salidaListaVO;

	}

	private SalidainfoAfiliadoVO mapear(DT_INFO_AFILIADO_RES response) {
		
		SalidainfoAfiliadoVO info_afiliado = new SalidainfoAfiliadoVO();
		info_afiliado.setNombreCompleto(response.getNOMBRE());
		info_afiliado.setFechaNacimiento(response.getFECHA_NACIMIENTO());
		//Se setea Trabajador
		String trabajador= response.getTRABAJADOR();
		info_afiliado.setTrabajador(trabajador!=null&&trabajador.equals("X")?true:false);
		//Se setea Pensionado
		String pensionado= response.getPENSIONADO();
		info_afiliado.setPensionado(pensionado!=null&&pensionado.equals("X")?true:false);
		//Se setea Deudor Directo
		info_afiliado.setDeudordirecto(trabajador==null&&pensionado==null?true:false);
		
		ArrayList<AnexoAfiliadoVO> lista_anexos = new ArrayList<AnexoAfiliadoVO>();
		
		//Anexos Trabajador
		if(response.getANEXO_TRAB()!=null){
			for (DT_INFO_AFILIADO_RESANEXO_TRAB anexoTra : response.getANEXO_TRAB()) {
				AnexoAfiliadoVO anexo= new AnexoAfiliadoVO();
				anexo.setCodigoAnexo(anexoTra.getPARTNER());
				anexo.setNombreAnexo(anexoTra.getNOMBRE());
				lista_anexos.add(anexo);
			}
		}
		if(response.getANEXO_PENS()!=null){
			//Anexos Pensionados
			for (DT_INFO_AFILIADO_RESANEXO_PENS anexoPen : response.getANEXO_PENS()) {
				AnexoAfiliadoVO anexo= new AnexoAfiliadoVO();
				if(anexoPen.getINSCRIPCION()!=null){
					anexo.setCodigoAnexo(anexoPen.getPARTNER()+"#"+anexoPen.getINSCRIPCION());
					anexo.setNombreAnexo(anexoPen.getNOMBRE()+"#"+anexoPen.getINSCRIPCION());
				}else{
					anexo.setCodigoAnexo(anexoPen.getPARTNER());
					anexo.setNombreAnexo(anexoPen.getNOMBRE());
				}
				
				lista_anexos.add(anexo);
			}
		}
		if(response.getANEXO_DEUD()!=null){
			//Anexos Deudor Directo
			for (DT_INFO_AFILIADO_RESANEXO_DEUD anexoDD : response.getANEXO_DEUD()) {
				AnexoAfiliadoVO anexo= new AnexoAfiliadoVO();
				anexo.setCodigoAnexo(anexoDD.getPARTNER());
				anexo.setNombreAnexo(anexoDD.getNOMBRE());
				lista_anexos.add(anexo);
			}
		}
		info_afiliado.setAnexos(lista_anexos);

		return info_afiliado;
	}
}