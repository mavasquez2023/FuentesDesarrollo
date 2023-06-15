package ztest;

import java.util.HashMap;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestConsolidacionPorProceso {

	public static void main(String[] args) {
		
		HashMap session = new HashMap();
		HashMap parameters = new HashMap();
		//parameters.put("codigo_proceso", "MAG");
		HashMap salida = null;
		try {
			if(true) return;
			salida = new WorkFlowHelper().ejecutarProcesoConsolidacion(session, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("salida: "+ salida);
	}
	
}
