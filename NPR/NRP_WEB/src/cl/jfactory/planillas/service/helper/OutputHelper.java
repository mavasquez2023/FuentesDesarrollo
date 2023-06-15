package cl.jfactory.planillas.service.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class OutputHelper {

	public Boolean validarServer(Object session, Object parameters) {
		return Boolean.TRUE;
	}

	public HashMap procesarOutputAutenticacion(Object session, Object parameters) {
		HashMap output = new HashMap();
		
		if ((HashMap) ((HashMap) session).get("query_autenticacion") != null){
			((HashMap) ((HashMap) session).get("query_autenticacion")).put("token", "");
			return (HashMap) ((HashMap) session).get("query_autenticacion");
		}
		else {
			output.put("codigo_descripcion", "Error de Usuario");
			output.put("codigo_error", "S003");

			return output;
		}

	}
	
	public HashMap procesarDataPreventa(Object session, Object parameters) {
		HashMap output = new HashMap();

		
		output.put("encabezado", (HashMap) ((HashMap) session).get("obtener_cabecera"));
		output.put("detalle", (ArrayList) ((HashMap) session).get("obtener_detalle"));
		
		return output;
	}

	public HashMap procesarRegistroPosicion(Object session, Object parameters) {
		HashMap data = new HashMap();
		data.put("estado", Boolean.TRUE);
		data.put("local_relacionado", "");
		
		if( ((Boolean) ((HashMap) session).get("registrar_posicion")).equals(Boolean.TRUE)  && ((HashMap) session).get("obtener_local") != null){
			HashMap local =  (HashMap) ((HashMap) session).get("obtener_local");
			String localEncontrado = local.get("nombre") + " - " + local.get("ciudad");
			
			if(local != null){
				data.put("estado", ((HashMap) session).get("registrar_posicion"));
				data.put("local_relacionado", localEncontrado);
			}
			
		}
		return data;
	}
}
