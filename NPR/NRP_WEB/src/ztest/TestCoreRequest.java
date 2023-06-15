package ztest;

import java.util.HashMap;

import cl.liv.core.request.exception.RequestUtilException;
import cl.liv.core.request.impl.RequestImpl;
import cl.liv.core.request.tipos.TiposEntrada;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.core.request.tipos.TiposSalida;

public class TestCoreRequest {

	public static void main(String[] args) {
		
		try {
			HashMap pars = new HashMap();
			Object resultado = RequestImpl.procesarPeticion( pars , "obtenerArbolDeProcesos", TiposSalida.SALIDA_JSON, TiposEntrada.ENTRADA_ESTANDAR, TiposMetodoHttp.POST);
			System.out.println("resultado operacion : "+ resultado);
		
		} catch (RequestUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
