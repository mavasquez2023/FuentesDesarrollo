package ztest;

import java.util.HashMap;

import org.json.JSONObject;

import cl.liv.core.request.exception.RequestUtilException;
import cl.liv.core.request.impl.RequestImpl;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposSalida;

public class PruebaConfiguradorHelper {
	public static void main(String[] args) {
		generico();
	}
	private static JSONObject generico(){
		
		
		String ID_PETICION = "buscarEmpresaHolding";
		HashMap pars = new HashMap();
		pars.put("codigo_a_buscar", "");
	

		try {
			int tipoSalida = TiposSalida.SALIDA_JSON;
			Object salida = RequestImpl.procesarPeticion(pars, ID_PETICION, tipoSalida, TiposEntrada.ENTRADA_JSON, TiposMetodoHttp.POST);

			if (tipoSalida == TiposSalida.SALIDA_JSON) {
				((JSONObject) salida).toString();
				return ((JSONObject) salida);
			} else if (tipoSalida == TiposSalida.SALIDA_XML) {
				((String) salida).toString();
			} else if (tipoSalida == TiposSalida.SALIDA_SOLO_DATA) {
				((String) salida).toString();
			}

		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
}
