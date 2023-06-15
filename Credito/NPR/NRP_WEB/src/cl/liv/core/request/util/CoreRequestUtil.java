package cl.liv.core.request.util;

import java.util.HashMap;

public class CoreRequestUtil {

	public static Object obtenerResultadoProceso(Object session, String idProceso){
		try{
			return    ((HashMap)session).get(idProceso);
		}
		catch(Exception e){
			
		}
		return null;
	}
}
