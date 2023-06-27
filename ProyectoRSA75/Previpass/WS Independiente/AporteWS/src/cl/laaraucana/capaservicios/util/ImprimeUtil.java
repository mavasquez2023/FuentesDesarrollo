package cl.laaraucana.capaservicios.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class ImprimeUtil {
	
	/**
	 * Imprime los valores de todos los atributos de la clase
	 * Los métodos deben comenzar por "get"
	 */
	public static void imprimirCampos(Object objeto){
		
		if(objeto == null) return;
		
		try {
			Method campos[] = objeto.getClass().getMethods();
			System.out.println("Clase " + objeto.getClass().getName()+":");
			for(int i = 0; i<campos.length;i++){
				if(campos[i].getName().contains("get")){
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm);					
					System.out.print(campos[i].getName());
					System.out.print(": " + mi.invoke(objeto, new Object[0]) + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Realiza la misma tarea, pero utilizando log4j
	 * @param objeto
	 * @param log
	 */
	
	public static void imprimirCampos(Object objeto, Logger log){
		try {
			Method campos[] = objeto.getClass().getMethods();
			log.debug("Clase " + objeto.getClass().getName()+":");
			for(int i = 0; i<campos.length;i++){
				if(campos[i].getName().contains("get")){
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm);					
					log.debug(campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	public static void imprimirHash( HashMap hashMap){
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
		Map.Entry e = (Map.Entry)it.next();
		System.out.println(e.getKey() + " " + e.getValue());
		}
	}
}
