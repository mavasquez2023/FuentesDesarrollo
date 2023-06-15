package ztest;

import java.util.HashMap;

import cl.jfactory.planillas.service.helper.WorkFlowHelper;

public class TestCertificacion {

	public static void main(String[] args) {
		
		HashMap session = new HashMap();
		HashMap parameters = new HashMap();
		HashMap salida = new WorkFlowHelper().validaArchivosCargados(session, parameters);
		System.out.println("salida: "+ salida);
	}
	
}
