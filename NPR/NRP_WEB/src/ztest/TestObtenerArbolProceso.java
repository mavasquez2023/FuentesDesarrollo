package ztest;

import java.util.HashMap;

import org.json.JSONException;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestObtenerArbolProceso {

	
	public static void main(String[] args) throws JSONException {
		
		
		Object data = new WorkFlowHelper().obtenerArbolDeProcesos(new HashMap(), new HashMap());
		
		System.out.println("data->"+ data);
	}
}
