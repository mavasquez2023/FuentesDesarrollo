package ztest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.jfactory.planillas.negocio.post.carga.PostCargaSAP;
import cl.jfactory.planillas.negocio.post.carga.PostCargaSINACAFF;
import cl.jfactory.planillas.service.util.Utiles;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.UtilesComunes;
import cl.liv.persistencia.ibatis.impl.SqlMapLocator;

public class TestPostCarga {

	public static void main(String[] args) {

		UtilesComunes.variablesEstaticas.put("sys.YearMonth", "201905");
		
		System.out.println("periodo -> "+ Utiles.getPeriodoActual());
		
		SqlMapClient sqlMap = SqlMapLocator.getSqlMap("carga_sap_config");
		ArrayList origenes;
		try {
			MiHashMap pars = new MiHashMap();
			pars.put("codigo", "SINACAFF");
			
			
			
			//pars.put("codigo", "SINACAFF");
			origenes = (ArrayList) sqlMap.queryForList("carga_SAP.obtenerProcesosCarga", pars);
			
			if(pars.get("codigo").equals("SAP"))
				new PostCargaSAP().execute(new HashMap(), new HashMap(), (MiHashMap) origenes.get(0));
			else if(pars.get("codigo").equals("SINACAFF"))
				new PostCargaSINACAFF().execute(new HashMap(), new HashMap(), (MiHashMap) origenes.get(0));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
