package cl.laaraucana.licenciascompinemp.ws;


import org.apache.log4j.Logger;

import cl.laaraucana.licenciascompinemp.vo.SalidainfoAfiliadoVO;

import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;



public class ClientBPQueryStatus {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public SalidainfoAfiliadoVO obtenerEstadoAfiliacionCRM(String rutAfiliado, String rutEmpresa) {
		int estado=0;
		SalidainfoAfiliadoVO salida= new SalidainfoAfiliadoVO();
		QueryBPStatusResponse respuesta= call(rutAfiliado);
		if(respuesta!=null && respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			for (int i = 0; i < respuesta.getQueryBPStatus().length; i++) {
				QueryBPStatus empresa= respuesta.getQueryBPStatus()[i];
				if(empresa.getRut().equals(rutEmpresa)){
					salida.setNombreCompleto(empresa.getNombreCompleto());
					if( empresa.getEstadoAfiliacion().equals("ACTIVO")){
						estado= 1;
						break;
					}
					estado= -1;
					//break;
				}
			}
		}else{
			estado=-9;
		}
		salida.setEstado(estado);
		return salida;
	}
	
	public QueryBPStatusResponse call(String rut) {
		QueryBPStatusResponse respuesta=null;
		try {
			QueryBPStatusRut entrada= new QueryBPStatusRut();
			entrada.setRut(rut);
			String ep = Configuraciones.getConfig("ep.QueryBPStatus");
			String user =  Configuraciones.getConfig("servicios.sap.username");
			String clave =  Configuraciones.getConfig("servicios.sap.pass");
			
			QueryBPStatusOUTBindingStub stub = new QueryBPStatusOUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(QueryBPStatusOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			respuesta = stub.queryBPStatus(entrada);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Problemas al invocar ws BPQueryStatus, mensaje= " + e.getMessage());
		} 
	
		return respuesta;
	}
	
	
	
}
