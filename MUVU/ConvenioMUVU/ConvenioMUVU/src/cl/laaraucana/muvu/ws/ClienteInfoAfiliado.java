package cl.laaraucana.muvu.ws;

import java.util.ArrayList;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import cl.laaraucana.muvu.util.Configuraciones;
import cl.laaraucana.muvu.vo.AnexoAfiliadoVO;
import cl.laaraucana.muvu.vo.SalidainfoAfiliadoVO;

import com.lautaro.xi.CRM.WEB_Mobile.*;
 

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
		//logger.debug("Datos autenticacion SI_INFO_AFILIADO_OUT seteados");

		DT_INFO_AFILIADO_REQ query = new DT_INFO_AFILIADO_REQ();
		query.setRUT_BP(rut);
		 


		DT_INFO_AFILIADO_RES respuesta = new DT_INFO_AFILIADO_RES();
		
		try {
			respuesta = stub.SI_INFO_AFILIADO_OUT(query);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio InfoAfiliado: compruebe el servicio");
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
		//logger.debug(">> Salida Web Service: QueryBPStatusOUT");
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