/**
 * 
 */
package cl.laaraucana.muvu.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.muvu.services.ProcesosMuvuImpl;


/**
 * @author IBM Software Factory
 *
 */
public class UtilsFile {
	private static final Logger logger = Logger.getLogger(UtilsFile.class);
	public static List<String> leerArchivo(String pathfile) {
		
		BufferedReader f1;
		String buf = "";
		List<String> lista= new ArrayList<String>();
		try {
			
			//leyendo archivo, usando BufferedReader
			f1 = new BufferedReader(new FileReader(pathfile));
			int linea=1;
			while ((buf = f1.readLine()) != null) {
				if(linea>1 && buf.length()>0){
					lista.add(buf);
				}
				linea++;
			}
			f1.close();
		} catch (Exception e) {
			logger.warn("Error al leer archivo " + pathfile + "mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		return lista;
	}
}
