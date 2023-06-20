package cl.laaraucana.EnvioASFAMEmpresa.ws;


import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatus;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;
import cl.laaraucana.EnvioASFAMEmpresa.util.Configuraciones;
import cl.laaraucana.EnvioASFAMEmpresa.vo.SalidainfoAfiliadoVO;





public class ClientBPQueryStatus {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public SalidainfoAfiliadoVO obtenerEstadoAfiliacionCRM(String rutAfiliado, String rutEmpresa) {
		int estado=0;
		SalidainfoAfiliadoVO salida= new SalidainfoAfiliadoVO();
		logger.info("Buscando status empresas rutafi: " + rutAfiliado + ", ingresado por rutemp: " + rutEmpresa);
		QueryBPStatusResponse respuesta= call(rutAfiliado);
		if(respuesta!=null && respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			for (int i = 0; i < respuesta.getQueryBPStatus().length; i++) {
				QueryBPStatus empresa= respuesta.getQueryBPStatus()[i];
				logger.info("Empresa:" + empresa.getRut() + ", estado: " + empresa.getEstadoAfiliacion());
				if(empresa.getRut().equals(rutEmpresa)){
					salida.setNombreCompleto(empresa.getNombreCompleto());
					if( empresa.getEstadoAfiliacion().equals("ACTIVO")){
						estado= 1;
						break;
					}else{
						if(estado!=1){
							estado= -1;
						}
					}
				}
			}
		}else{
			estado=-9;
		}
		salida.setEstado(estado);
		logger.info("QueryBPStatus RUT " + rutAfiliado +", estado (1=Activo): "  + estado);
		return salida;
	}
	
	public QueryBPStatusResponse call(String rut) {
		QueryBPStatusResponse respuesta=null;
		try {
			QueryBPStatusRut entrada= new QueryBPStatusRut();
			entrada.setRut(rut);
			logger.debug("Inicio Web Service: QueryBPStatus");
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
