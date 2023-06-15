package ztest;

import org.json.JSONException;
import org.json.JSONObject;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestEliminacionFormato {
	

	static String CONFIG_IBATIS_SAP = "carga_sap_config";
	
	public static void main(String[] args) throws JSONException {
		
		//{ "codigo_entidad":"1234","nombre_nomina":"NOM_1234_uno"
		

		JSONObject  json = new JSONObject( "{ \"codigo_entidad\":\"1234\",\"nombre_nomina\":\"NOM_1234_uno\"} " );
		MiHashMap pars = new MiHashMap();
		pars.put("codigo", json.getString("codigo_entidad"));
		pars.put("nombre_nomina", json.getString("nombre_nomina"));
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap(CONFIG_IBATIS_SAP);
		try {
			int result = sqlMap.delete("carga_SAP.eliminarFormato", pars);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}

}
