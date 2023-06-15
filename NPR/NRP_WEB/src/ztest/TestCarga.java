package ztest;

import java.util.HashMap;

import cl.jfactory.planillas.carga.sap.impl.ProcesoCargaSAP;
import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestCarga {

	public static void main(String[] args) {
		//ProcesoCargaSAP.ejecutarCargaInformacionSAP("/home/clillo/nrp/tmp/NRP201807.txt","carga_SAP", "planillas");
	
		
		HashMap session = new HashMap();
		HashMap parameters = new HashMap();
		
		try {
			new WorkFlowHelper().ejecutarProcesoConsolidacion(session, parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
