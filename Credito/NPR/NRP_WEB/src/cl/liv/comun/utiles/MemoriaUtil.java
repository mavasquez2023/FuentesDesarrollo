package cl.liv.comun.utiles;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import cl.liv.comun.utiles.logs.UtilLogMemoria;


public class MemoriaUtil {

	
	public static boolean isMemoriaCritica(){
		
		long memoriaCritica = new Long(PropertiesUtil.propertiesMemoria.getString("config.memoria.cantidad.critica")).longValue() * 1024 * 1024 ;
		
		if( Runtime.getRuntime().freeMemory() < memoriaCritica){
			UtilLogComun.debug("Memoria crítica superada.");
			UtilLogMemoria.debug("Memoria crítica superada["+((int)Runtime.getRuntime().freeMemory()/(1024*1024)) +" < "+((int)memoriaCritica/(1024*1024))+"]");
			return true;
		} 
		
		return false;
	}
	
	 static double usedMemory(Runtime runtime) {
	        long totalMemory = runtime.totalMemory();
	        long freeMemory = runtime.freeMemory();
	        double usedMemory = (double)(totalMemory - freeMemory) / (double)(1024 * 1024);
	        return usedMemory;
	    }

	    static double maxMemory(Runtime runtime) {
	        long maxMemory = runtime.maxMemory();
	        double memory = (double)maxMemory / (double)(1024 * 1024);
	        return memory;
	    }
	
	public static void main(String[] args) {
		
		NumberFormat f = new DecimalFormat("###,##0.0");
		Runtime runtime = Runtime.getRuntime();
		 double usedMemory = usedMemory(runtime);
	        double maxMemory = maxMemory(runtime);

		 String lineSeparator = System.getProperty("line.separator");
	     StringBuffer buffer = new StringBuffer();
		 buffer.append("Used memory: " + runtime.getRuntime().freeMemory() +" : "+ f.format(usedMemory) + "MB").append(lineSeparator);
	        buffer.append("Max available memory: " + f.format(maxMemory) + "MB").append(lineSeparator);
	        
	     System.out.println(buffer);   
	        
	}
}
