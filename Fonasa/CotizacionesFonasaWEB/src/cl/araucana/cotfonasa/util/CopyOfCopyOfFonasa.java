package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class CopyOfCopyOfFonasa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		String archivoEntrada= "C://CCAF112013.txt";
		String archivoSalida = "C://CC012012.txt";
		
		CopyOfCopyOfFonasa.ejecutaProceso(archivoEntrada, archivoSalida);
	}
	
	public static void ejecutaProceso(String archivoEntrada, String archivoSalida)
	{
		File f = new File(archivoEntrada);
		File fNew = new File(archivoSalida);
		BufferedReader entrada;
		int count =0;
		
		try {
			
		long time_start, time_end;
			
		entrada = new BufferedReader( new FileReader( f ) );
		String linea;
        FileWriter w = new FileWriter(fNew);
		
		BufferedWriter bw = new BufferedWriter(w);
		
		PrintWriter wr = new PrintWriter(bw);  
		String linea2="";
	
		time_start = System.currentTimeMillis();
		while(entrada.ready()){
		linea = entrada.readLine();
		

		    wr.println(linea);//escribimos en el archivo
		
		    count++;
		    if(count == 300000)
		    	break;
		}
        wr.close();
		
		bw.close();
		
		
		System.out.println("cantidad registros:"+count);
		
		time_end = System.currentTimeMillis();
		System.out.println("the task has taken "+ ( time_end - time_start )/1000 +" seconds");
		}catch (IOException e) {
		e.printStackTrace();
		}
		
	}
	
	

}
