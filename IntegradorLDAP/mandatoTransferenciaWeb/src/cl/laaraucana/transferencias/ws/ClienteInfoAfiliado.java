package cl.laaraucana.transferencias.ws;

import java.util.ArrayList;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RES;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_DEUD;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_PENS;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RESANEXO_TRAB;
import com.lautaro.xi.CRM.WEB_Mobile.SI_INFO_AFILIADO_OUTBindingStub;

import cl.laaraucana.transferencias.util.Configuraciones;
import cl.laaraucana.transferencias.vo.AnexoAfiliadoVO;
import cl.laaraucana.transferencias.vo.SalidainfoAfiliadoVO;

 


 

public class ClienteInfoAfiliado implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	 
	public SalidainfoAfiliadoVO getDataAfiliado(String rut) throws AxisFault { 
	 
		
		SalidainfoAfiliadoVO salidaListaVO = new SalidainfoAfiliadoVO();
		
		String ep = Configuraciones.getConfig("ep.InfoAfiliado");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
	 

		SI_INFO_AFILIADO_OUTBindingStub stub = new SI_INFO_AFILIADO_OUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(SI_INFO_AFILIADO_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		DT_INFO_AFILIADO_REQ query = new DT_INFO_AFILIADO_REQ();
		query.setRUT_BP(rut);
		 


		DT_INFO_AFILIADO_RES respuesta = new DT_INFO_AFILIADO_RES();
		
		try {
			respuesta = stub.SI_INFO_AFILIADO_OUT(query);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio SI_INFO_AFILIADO_OUT: compruebe el servicio");
			return salidaListaVO;
		}
		
		if(respuesta!=null){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos Cliente SI_INFO_AFILIADO_OUT OK. C�digo error: 0");
		}else{
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			String msg = "RUT no v�lido";				
			salidaListaVO.setMensaje("Error en servicio InfoAfiliado: " + msg);
			logger.warn("Error en servicio InfoAfiliado, mensaje: " + salidaListaVO.getMensaje());
		}

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