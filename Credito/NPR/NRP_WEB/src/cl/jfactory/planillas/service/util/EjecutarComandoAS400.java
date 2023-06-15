package cl.jfactory.planillas.service.util;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CommandCall;

import cl.jfactory.planillas.service.helper.AlertasHelper;
import cl.liv.archivos.as400.impl.ProxyAS400;
import cl.liv.comun.utiles.logs.UtilLogErrores;

public class EjecutarComandoAS400 {

	
	public static boolean ejecutar(String comando){
		
		if(comando.contains(";;")){
			return ejecutar(comando.split(";;"));
		}
		
		UtilLogProcesos.debug("Ejecutando comando...");
		CommandCall command = new CommandCall(ProxyAS400.getInstancia().getAs400());
		 String commandStr = comando;
		   // Run the command.
		 UtilLogProcesos.debug("Ejecutando comando: " + commandStr);
		   try {
			   boolean success = command.run(commandStr);
			   UtilLogProcesos.debug(" Comando Ejecutado: " + commandStr);
			   return true;
		   }
		   catch(Exception e){
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
		   }
		   return false;
	}
	
	public static boolean ejecutar(AS400 conection,  String comando){
		
		if(comando.contains(";;")){
			return ejecutar(comando.split(";;"));
		}
		
		UtilLogProcesos.debug("Ejecutando comando con conexion ["+conection.getUserId()+"]...");
		CommandCall command = new CommandCall(conection);
		 String commandStr = comando;
		   // Run the command.
		 UtilLogProcesos.debug("Ejecutando comando: " + commandStr);
		   try {
			   boolean success = command.run(commandStr);
			   UtilLogProcesos.debug(" Comando Ejecutado: " + commandStr);
			   return true;
		   }
		   catch(Exception e){
				UtilLogErrores.error(e);
				AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
		   }
		   return false;
	}
	
	public static boolean ejecutar(String[] comandos ){

		boolean success = true;
		UtilLogProcesos.debug("Ejecutando comando...");
		CommandCall command = new CommandCall(ProxyAS400.getInstancia().getAs400());
		
		try{
			
			if(comandos != null && comandos.length > 0){
				for(int i=0; i< comandos.length; i++){
					if(comandos[i] != null && comandos[i].trim().length() > 0){
						String commandStr = comandos[i];
						success = command.run(commandStr);
						if(  !success ){
						   i = comandos.length;
						}
					}
				}
			}
		}
		catch(Exception e){

			UtilLogErrores.error(e);
			AlertasHelper.procesarAlertaError(EstadosWorkflow.ERROR_GENERICO, e);
		}
		   return success;
	}
	
	
}
