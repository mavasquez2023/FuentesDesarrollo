package ztest;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.liv.comun.utiles.MiHashMap;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestUpdatePagador {

	public static void main(String[] args) {
		

		SqlMapClient sqlMap = SqlMapLocator.getSqlMap("carga_sap_config");
		
		try {
			ArrayList data = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerPagadoresAval");
			
			System.out.println("registros: "+ data.size());
			
			for(int i=0; i< data.size(); i++){
				((MiHashMap)data.get(i)).put("RUT",  Integer.parseInt(((MiHashMap)data.get(i)).get("RUT_EMPRESA").toString().split("-")[0] ) +"");
				System.out.println((((MiHashMap)data.get(i)).get("RUT"))   );
				int result = sqlMap.update("carga_SAP.actualizarAval",data.get(i));

				System.out.println("["+i+"] -> "+result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
