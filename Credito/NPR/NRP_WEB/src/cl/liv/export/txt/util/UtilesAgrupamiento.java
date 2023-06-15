package cl.liv.export.txt.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.comun.utiles.MiHashMap;

public class UtilesAgrupamiento {
	public static ArrayList<HashMap<String, Object>> agrupar(GenerarTXT instancia, String key, MiHashMap parametrosGeneracion, ArrayList<HashMap<String, Object>> data,ArrayList<HashMap<String, Object>> dataAux, int i) {

		String[] sum = parametrosGeneracion.get("sum").toString().split(",");
		if( instancia.indices.get(key) == null  ){
			instancia.indices.put(key, ((HashMap)data.get(i)));
			dataAux.add((HashMap)instancia.indices.get(key));
		}
		else{
			for(int j=0; j< sum.length; j++){
				if(sum[j] != null && sum[j].length() >0){
					Integer valorAnterior = Integer.parseInt(((HashMap)instancia.indices.get(key)).get(sum[j]).toString());
					Integer nuevoValor = Integer.parseInt(  ((HashMap)data.get(i)).get(sum[j]).toString());
					Integer valorSumado = valorAnterior + nuevoValor;
					((HashMap)instancia.indices.get(key)).put(sum[j], valorSumado);
				}
			}
		}
		
		
		//Solo para ips novedades
		if(parametrosGeneracion.get("IPS_NOVEDADES")!=null) {
			String valorAnterior = (String)((HashMap)instancia.indices.get(key)).get("TIPREG");
			String valorActual = (String)((HashMap)data.get(i)).get("TIPREG");
			
			if(valorAnterior.equalsIgnoreCase(valorActual)) {
				((HashMap)instancia.indices.get(key)).put("TIPREG","2");
			}
			
			BigDecimal cuotaAnterior = (BigDecimal)((HashMap)instancia.indices.get(key)).get("CANCUO");
			BigDecimal cuotaActual = (BigDecimal)((HashMap)data.get(i)).get("CANCUO");
			
			if(cuotaAnterior.intValue() < cuotaActual.intValue() ) {
				((HashMap)instancia.indices.get(key)).put("CANCUO",cuotaActual);
			}
			
			
		}
		
		
		return dataAux;
	}
}
