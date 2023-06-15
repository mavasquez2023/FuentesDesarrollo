package ztest;

import cl.jfactory.planillas.service.helper.ConfiguradorHelper;
import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.liv.comun.utiles.MiHashMap;

public class TestDisponibilizacion {

	public static void main(String[] args) {
		ConfiguradorHelper.nominasEnviadas = new MiHashMap();
		
		GeneradorNominasHelper.ejecutarProcesoEnvio();

		//UtilesComunes.variablesEstaticas.put("sys.YearMonth", "202006");

		/*
		boolean holding = false;
		
		if(!holding)
			GeneradorNominasHelper.enviarTodas("codigo_entidad:61533000");
		if(holding)
			GeneradorNominasHelper.enviarTodas("codigo_entidad:HO0473");
		*/
		//UtilesComunes.variablesEstaticas.put("sys.YearMonth", "202011");
		//GeneradorNominasHelper.enviarTodas("codigo_entidad:HO0016");
		//GeneradorNominasHelper.enviarTodas("codigo_entidad:HO0044");
		GeneradorNominasHelper.ejecutarProcesoEnvio();
		
		//GeneradorNominasHelper.enviarTodas("codigo_entidad:61313000");
		//GeneradorNominasHelper.enviarTodas("codigo_entidad:61533000");
		
		//GeneradorNominasHelper.ejecutarProcesoEnvio();
		
	}
	
}
