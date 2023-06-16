package cl.ccaf.previpass.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LectorArchivo {


	public static ArrayList  getContenidoArchivoString(String file) {
		ArrayList resultado = new ArrayList();
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File(file);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null){
				resultado.add( linea.replaceAll("\"", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;
	}
}
