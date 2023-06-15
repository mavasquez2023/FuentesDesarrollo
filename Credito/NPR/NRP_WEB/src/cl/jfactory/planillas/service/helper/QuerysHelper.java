package cl.jfactory.planillas.service.helper;

import java.util.HashMap;

public class QuerysHelper {

	public static String[] obtenerQueryLimite(HashMap parametros){
		//String query = "select * from WF_NOMINAS.DATA_NOMINA where rut_empresa = '"+parametros.get("codigo_entidad").toString()+"' limit 0,4";
		
		String query = "select * from DATA_NOMINA where RUT_EMPRESA like '%700%'";
		return new String[]{null, query, null};
	}
	
}
