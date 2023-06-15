package cl.jfactory.planillas.service.util;

public class Mensajes { 
	
 	public static void error(String message){
 		UtilLogWorkflow.debug("Error "+ message);
 	}
 	public static void debug(String message){
 		UtilLogWorkflow.debug("Debug "+ message);
 	}
 	public static void info(String message){
 		UtilLogWorkflow.debug("Info "+ message);
 	}
 	
 	public static void ex(Throwable ex){
 		//logger.error("Exception", ex);
 		ex.printStackTrace();
 	}
 	
 }