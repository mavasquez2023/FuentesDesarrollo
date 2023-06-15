package ztest;

import java.util.HashMap;

import org.json.JSONObject;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.liv.core.request.exception.RequestUtilException;
import cl.liv.core.request.impl.RequestImpl;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposSalida;

public class TestEstadoWF {

	public static void main(String[] args) {
		
		String ID_PETICION = "obtenerEstadoWorklow";
		HashMap pars = new HashMap();
		pars.put("email", "luisibacache@gmail.com");
		pars.put("token", "041ec6d4d967a2b97489b4c5cdaee7a9");
		pars.put("periodo", "201804");
		

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				System.out.println(salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
				System.out.println(salida);
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
				System.out.println(salida);
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
