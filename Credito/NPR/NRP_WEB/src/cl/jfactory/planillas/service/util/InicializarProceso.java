package cl.jfactory.planillas.service.util;

import java.io.File;
import java.util.HashMap;

import org.json.JSONException;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.logs.UtilLogErrores;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class InicializarProceso {

	public static void inicializar(HashMap parametros ) throws JSONException{
		
		String pathCompleto = PropertiesUtil.workflowProperties.getString("workflow.planillas.repositorio");
		
		String pathPeriodoActual = pathCompleto+Utiles.getPeriodoActual();
		
		File directorio = new File(pathPeriodoActual);
		
		if(!directorio.exists()){
			HashMap session = new HashMap();
			HashMap parameters = new HashMap();
			parameters.put("usuario", "Proceso Automatizado");
			new WorkFlowHelper().generaEstructuraWorklow(session, parameters);
			SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
			try {
				MiHashMap pars = new MiHashMap();
				pars.put("periodo", Utiles.getPeriodoActual());

				int deleteMagallanes = sqlMap.delete("carga_SAP.deleteDataMagallanes",pars);
				UtilLogWorkflow.info("data eliminada Mag: "+ deleteMagallanes);
				int deleteMirror = sqlMap.delete("carga_SAP.deleteDataMirror",pars);
				UtilLogWorkflow.info("data eliminada: "+ deleteMirror);
				int deleteNominas = sqlMap.delete("carga_SAP.deleteDataNomina",pars);
				UtilLogWorkflow.info("data eliminada: "+ deleteNominas);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
			}	

		}
		else{
			UtilLogProcesos.debug("Estructura ya fue creada");
		}
		
	}
	
	public static void main(String[] args) throws JSONException {
		inicializar(null);
	}
	
}
