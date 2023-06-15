package ztest;

import java.io.IOException;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.liv.nrp.nominas.metodos.java.NominaResumenIPS;

public class TestMetodosJava {

	public static void main(String[] args) throws IOException {
		
		//new NominaResumenIPS().obtenerResumenesIPS(null);
		
		GeneradorNominasHelper.generar("", "codigo_entidad:61533000");
		
	}
	
}
