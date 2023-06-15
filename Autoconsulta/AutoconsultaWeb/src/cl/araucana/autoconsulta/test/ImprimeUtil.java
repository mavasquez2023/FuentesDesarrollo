package cl.araucana.autoconsulta.test;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;

public class ImprimeUtil {


	public static void main(String[] args) {
/*		LicenciaMedicaVO lic = new  LicenciaMedicaVO();
		lic.setCodigoFormaDePago("EFECTIVO");
		
		imprimirCampos(lic);*/
		
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
				if(campos[i].getName().indexOf("get")!=-1){
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm, new Class[] {Object.class});					
					log.debug(campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void imprimirCampos(Object objeto){
		try {
			Method campos[] = objeto.getClass().getMethods();
			System.out.println("Clase " + objeto.getClass().getName()+":");
			for(int i = 0; i<campos.length;i++){
				if(campos[i].getName().indexOf("get")!=-1){
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm, new Class[] {Object.class});					
					System.out.println(campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
