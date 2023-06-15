package ztest;

import java.util.HashMap;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;
import cl.jfactory.planillas.service.util.ProcesoNoInvalidanteUtil;

public class TestProcesosNoInvalidantes {

	public static void main(String[] args) {
		
		HashMap parameters = new HashMap();
		parameters.put("procesos", "MAG;IPS_BP;IPS_PBS");
		parameters.put("procesos", "MAG");
		parameters.put("procesos", "IPS_PBS");
		//HashMap data = new WorkFlowHelper().ejecutarProcesosNoInvalidantes(new HashMap(), parameters);
		//HashMap data = new WorkFlowHelper().obtenerProcesosNoInvalidantes(new HashMap(), parameters);
		
		//System.out.println("data->"+ data);
		
		//ProcesoNoInvalidanteUtil.guardarInicioProceso("IPS_BP");
		ProcesoNoInvalidanteUtil.guardarTerminoProceso("IPS_BP");
		
	}
	
}
