package cl.laaraucana.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class ImprimeUtil {

	/**
	 * Imprime los valores de todos los atributos de la clase
	 * Los métodos deben comenzar por "get"
	 */
	public static void imprimirCampos(Object objeto) {
		try {
			Method campos[] = objeto.getClass().getMethods();
			System.out.println("Clase " + objeto.getClass().getName() + ":");
			for (int i = 0; i < campos.length; i++) {
				if (campos[i].getName().contains("get")) {
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

	public static void imprimirCampos(Object objeto, Logger log) {
		try {
			Method campos[] = objeto.getClass().getMethods();
			log.debug("Clase " + objeto.getClass().getName() + ":");
			for (int i = 0; i < campos.length; i++) {
				if (campos[i].getName().contains("get")) {
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
	public static void imprimirHash(HashMap hashMap) {
		Iterator it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}

	public static String retornarCamposString(Object objeto) {
		String salida = "";
		try {
			Method campos[] = objeto.getClass().getMethods();
			System.out.println("Clase " + objeto.getClass().getName() + ":");
			for (int i = 0; i < campos.length; i++) {
				if (campos[i].getName().contains("get")) {
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm);

					try {
						String campo = (String) mi.invoke(objeto, new Object[0]);
						salida += campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]) + " \n";
						//objeto.getClass().cast(java.util.List.class);
						/*						List<String> items = (List<String>) mi.invoke(objeto, new Object[0]);
												for(String item : items){
													salida+=campos[i].getName()+": " + item + ", ";
												}*/
					} catch (Exception e) {
						imprimirCampos(mi.invoke(objeto, new Object[0]));
					}

					//System.out.print(campos[i].getName());
					//System.out.print(": " + mi.invoke(objeto, new Object[0]) + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	public static String imprimirLista(List lista) {
		String salida = "";
		for (Object campo : lista) {
			salida += "\t " + retornarCamposObjeto(campo);
			System.out.println("\t " + retornarCamposObjeto(campo));
		}
		return salida;
	}

	public static String retornarCamposObjeto(Object objeto) {
		String salida = "";
		try {
			Method campos[] = objeto.getClass().getMethods();
			//System.out.println("Clase " + objeto.getClass().getName()+":");
			for (int i = 0; i < campos.length; i++) {
				if (campos[i].getName().contains("get")) {
					String nm = campos[i].getName();
					Method mi = objeto.getClass().getMethod(nm);

					try {
						salida += imprimirLista((List) mi.invoke(objeto, new Object[0]));
						System.out.println("Lista");
					} catch (Exception e) {
						System.out.println("Objeto");
						salida += campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]) + " \n";
						System.out.println(campos[i].getName() + ": " + mi.invoke(objeto, new Object[0]) + " \n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;

	}

}
