package cl.jfactory.planillas.negocio.post.carga;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibm.as400.access.AS400;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.jfactory.planillas.service.util.EjecutarComandoAS400;
import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.comun.utiles.logs.UtilLog;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class PostCargaIPS implements IPostCarga{
	
	public static boolean IPSBPEjecutado = false;
	public static boolean IPSPBSEjecutado = false;
	
	public static void inicializar(){
		IPSBPEjecutado = false;
		IPSPBSEjecutado = false;
	}
	
	public boolean execute(final HashMap session,HashMap parametros, HashMap configs) {
		
		if(IPSBPEjecutado && IPSPBSEjecutado){
			UtilLogWorkflow.debug("post carga IPS iniciando");
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);		
			try {	
				for(int i=1; i<=3; i++){
	
						UtilLogWorkflow.debug("actualizando data previsional ["+i+"]");
						UtilLogWorkflow.debug("ejecutando -> "+ "carga_SAP.actualizacionDataPrevisional"+i);
						int resultado = sqlMap.update("carga_SAP.actualizacionDataPrevisional"+i);
						UtilLogWorkflow.debug("resultado update ["+resultado+"]");
						Thread.sleep(1000);
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			/*String comando = PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.comando.generacion.ips");
			UtilLogWorkflow.debug("post carga IPS comando a ejecutar -> "+ comando);
			if(comando != null && comando.length()>0){
				try {
					comando = UtilesComunes.reemplazarVariables(comando);
					UtilLogWorkflow.debug("post carga IPS inicializando conexión "+  PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"));
					AS400 conexion = new AS400(
							PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.server"), 
							PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.user"), 
							PropertiesUtil.propertiesArchivosAS400.getString("config.archivos.as400.password"));
					UtilLogWorkflow.debug("post carga IPS ejecutando... ");
					EjecutarComandoAS400.ejecutar(conexion,comando);
					UtilLogWorkflow.debug("post carga IPS ejecucion ok");
					conexion.disconnectAllServices();
					UtilLogWorkflow.debug("post carga IPS desconectando");
				}catch(Exception e) {
					
				}
			}*/
			UtilLogWorkflow.debug("post carga IPS terminando");
			inicializar();
			
			
		}
		
		return true;
	}

}
