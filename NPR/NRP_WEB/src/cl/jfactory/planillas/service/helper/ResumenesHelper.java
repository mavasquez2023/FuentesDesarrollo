package cl.jfactory.planillas.service.helper;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.service.util.ConstantesUtiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class ResumenesHelper {


	public static JSONObject obtenerResumenesProceso(MiHashMap config) {
		JSONObject json = new JSONObject();

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(ConstantesUtiles.ID_CFG_IBATIS_CARGA);
		
		try {
			MiHashMap pars = new MiHashMap();
			pars.put("PERIODO", config.get("PERIODO"));
			pars.put("ORIGEN", config.get("CODIGO"));
			pars.put("CODIGO", config.get("CODIGO"));
			MiHashMap data = (MiHashMap) sqlMap.queryForObject("carga_SAP.obtenerResumenProceso", pars);
			if(data != null ){
				

				json.put("total_parciales", 0);
				json.put("registros_cuotones", 0);
				json.put("monto_cargados", 0);
				json.put("registros_iniciales", 0);
				json.put("registros_no_cargados", 0);
				json.put("monto_inicial", 0);
				json.put("registros_parciales", 0);
				json.put("monto_no_cargados", 0);
				json.put("monto_cuotones", 0);
				json.put("registros_cargados", 0);
				
				
				if( ((MiHashMap)data).get("TOTAL_PARCIALES")!= null){
					json.put("TOTAL_PARCIALES".toLowerCase(), ((MiHashMap)data).get("TOTAL_PARCIALES"));
				}
				if( ((MiHashMap)data).get("REGISTROS_CUOTONES")!= null){
					json.put("REGISTROS_CUOTONES".toLowerCase(), ((MiHashMap)data).get("REGISTROS_CUOTONES"));
				}
				if( ((MiHashMap)data).get("MONTO_CARGADOS")!= null){
					json.put("MONTO_CARGADOS".toLowerCase(), ((MiHashMap)data).get("MONTO_CARGADOS"));
				}
				if( ((MiHashMap)data).get("REGISTROS_INICIALES")!= null){
					json.put("REGISTROS_INICIALES".toLowerCase(), ((MiHashMap)data).get("REGISTROS_INICIALES"));
				}
				if( ((MiHashMap)data).get("REGISTROS_NO_CARGADOS")!= null){
					json.put("REGISTROS_NO_CARGADOS".toLowerCase(), ((MiHashMap)data).get("REGISTROS_NO_CARGADOS"));
				}
				if( ((MiHashMap)data).get("MONTO_INICIAL")!= null){
					json.put("MONTO_INICIAL".toLowerCase(), ((MiHashMap)data).get("MONTO_INICIAL"));
				}
				if( ((MiHashMap)data).get("REGISTROS_PARCIALES")!= null){
					json.put("REGISTROS_PARCIALES".toLowerCase(), ((MiHashMap)data).get("REGISTROS_PARCIALES"));
				}
				if( ((MiHashMap)data).get("MONTO_NO_CARGADOS")!= null){
					json.put("MONTO_NO_CARGADOS".toLowerCase(), ((MiHashMap)data).get("MONTO_NO_CARGADOS"));
				}
				if( ((MiHashMap)data).get("MONTO_CUOTONES")!= null){
					json.put("MONTO_CUOTONES".toLowerCase(), ((MiHashMap)data).get("MONTO_CUOTONES"));
				}
				if( ((MiHashMap)data).get("REGISTROS_CARGADOS")!= null){
					json.put("REGISTROS_CARGADOS".toLowerCase(), ((MiHashMap)data).get("REGISTROS_CARGADOS"));
				}
				
					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
}
