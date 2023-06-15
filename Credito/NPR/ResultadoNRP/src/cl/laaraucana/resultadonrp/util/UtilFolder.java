/**
 * 
 */
package cl.laaraucana.resultadonrp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.ibm.as400.access.IFSFile;

/**
 * @author IBM Software Factory
 *
 */
public class UtilFolder {
	//final static File carpeta = new File("C:/tmp/NRP/201907");
	private int count=0;
	private int enumera=0;
	
	public  void listarFicherosPorCarpeta(final File carpeta) {
	    for (final File ficheroEntrada : carpeta.listFiles()) {
	        if (ficheroEntrada.isDirectory()) {
	            listarFicherosPorCarpeta(ficheroEntrada);
	        } else {
	            System.out.println(enumera + "-" + ficheroEntrada.getName());
	            enumera++;
	        }
	        if(count%1000==0){
	        	System.out.println("listados:"  +enumera);
	        }
	    }
	}
	
	public int contarFicherosPorCarpetaAS400(final IFSFile carpeta) {
	    try {
			for (final IFSFile ficheroEntrada : carpeta.listFiles()) {
			    if (ficheroEntrada.isDirectory()) {
			        contarFicherosPorCarpetaAS400(ficheroEntrada);
			    } else {
			    	if(ficheroEntrada.length()>0){
			    		count++;
			    	}
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return count;
	}
	
	public int contarFicherosPorCarpeta(final File carpeta) {
	    for (final File ficheroEntrada : carpeta.listFiles()) {
	        if (ficheroEntrada.isDirectory()) {
	            contarFicherosPorCarpeta(ficheroEntrada);
	        } else {
	        	if(ficheroEntrada.length()>0){
	        		count++;
	        	}
	        }
	    }
	    return count;
	}
	
	public static void leerArchivo(String carpeta, String filename){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File (carpeta + filename);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null)
				System.out.println(linea);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	}
}
