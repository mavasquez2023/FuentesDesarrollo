package cl.liv.export.comun.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cl.liv.comun.utiles.PropertiesUtil;
 public class Mensajes { 
	static Logger logger = Logger.getLogger(Mensajes.class);
	static String rutaLog4j = PropertiesUtil.propertiesComun.getString("export.log4j")+"/log4j.properties";
	static boolean logConfigurado = true;
	static{
		
		try{
		 PropertyConfigurator.configure(rutaLog4j);
		 if(new File(rutaLog4j).exists()){
			 //logConfigurado = true;
		 }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
 	public static void error(String message){
 		logger.error(message);
 		out(message);
 	}
 	public static void debug(String message){
 		logger.debug(message);
 		out(message);
 	}
 	public static void info(String message){
 		logger.info(message);
 		out(message);
 	}
 	public static void out(String message){
 		if(!logConfigurado ){
 			System.out.println("[sysout] "+ message);
 		}
 	}
 	
 	
 }