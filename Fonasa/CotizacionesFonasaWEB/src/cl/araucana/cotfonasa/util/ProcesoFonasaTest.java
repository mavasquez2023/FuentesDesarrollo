package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import cl.araucana.cotfonasa.dao.AdminProcesosDAO;
import cl.araucana.cotfonasa.dao.ProcesoFonasaDAO;
import cl.araucana.cotfonasa.vo.OutputFonasaVO;



public class ProcesoFonasaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		String archivoEntrada= "C://CCAF112013_copia.txt";
		String archivoSalida = "C://CCAF112013_SAL.txt";
		
		//ProcesoFonasa.ejecutaProceso(archivoEntrada, archivoSalida);
	}
	
	public void ejecutaProceso(String archivoEntrada, String archivoSalida)
	{
		File f = new File(archivoEntrada);
		
		BufferedReader entrada;
		int count =0;
		AdminProcesosDAO dao = new AdminProcesosDAO();
		
		try {
			
		       long time_start, time_end;
			
		       entrada = new BufferedReader( new FileReader( f ) );
		       File fNew = new File(archivoSalida);
		       FileWriter w = new FileWriter(fNew);
		       BufferedWriter bw = new BufferedWriter(w);
		       PrintWriter wr = new PrintWriter(bw);  
		       String linea="";
		       String linea2="";
		       String usoSiste="SI";
		       String lineaNueva="";
		       
		       time_start = System.currentTimeMillis();
		       
		       
		       while(entrada.ready()){
			
		    	   linea = entrada.readLine();
		    	   String [] datos = linea.split(";");
		           String rutCotizante = datos[0];
		           String rutEmpleador = datos[2];
		           String periodo = datos[4];
		           
	               // consultamos si rut es afiliado. datos[0] es rut
		    	   
		    	   int resp = dao.buscaAfiliado(datos[0]);
		    	   
		    	   // si resp es mayo a cero, entonces el afiliado existe
		    	   if(resp > 0){
		    		   
		    		   int remuneracion = dao.getRemuneracionImponible(rutCotizante, rutEmpleador, periodo);
		    		   int cotizacion = dao.getMontoImponible(rutCotizante, rutEmpleador, periodo);
		    		   int pagSubMon = dao.getLicPagSubMon(rutCotizante, rutEmpleador, periodo);
		    		   
		    		   if(pagSubMon == -100){
		    			   usoSiste = "NO";
		    			   pagSubMon=0;
		    		   }
		    		   linea2= linea + ";" + remuneracion + ";" + cotizacion + ";" + usoSiste + ";" + pagSubMon; 
		    		   
		    	   }else{
		    		   // se escribe todo en cero y que no es usuario de sistema
		    		   linea2= linea + ";" + "0" + ";"  + "0" + ";" + "NO" + ";" + "0";
		    	   }
		
		    		   wr.println(linea2);//escribimos en el archivo
		
		    	   count++;
		
		       }// fin while
        
		       wr.close();
		
		bw.close();
		
		
		System.out.println("cantidad registros:"+count);
		
		time_end = System.currentTimeMillis();
		System.out.println("the task has taken "+ ( time_end - time_start )/1000 +" seconds");
		}catch (IOException e) {
		e.printStackTrace();
		}
		
	}
	
	public void ejecutaProceso2(String archivoEntrada, String archivoSalida)
	{
		//File f = new File(archivoEntrada);
		File fNew = new File(archivoSalida);
		BufferedReader entrada;
		int count =0;
       ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		
		try {
			
		       long time_start, time_end;
			
		     //  entrada = new BufferedReader( new FileReader( f ) );
		       FileWriter w = new FileWriter(fNew);
		       BufferedWriter bw = new BufferedWriter(w);
		       PrintWriter wr = new PrintWriter(bw);  
		       String linea="";
		       String linea2="";
		      // String usoSiste="SI";
		      // String lineaNueva="";
		       
		       time_start = System.currentTimeMillis();
		       
		       ArrayList datos = dao.getDatosOutput();
				Iterator it = datos.iterator();
		       
		       
		       while(it.hasNext()){
			
		    	   OutputFonasaVO out = (OutputFonasaVO)it.next();
		           
		    	   linea2 = out.getRutCotizante() + ";" + out.getDvCotizante() + ";" + out.getRutEmpleador() + ";" + out.getDvEmpleador() + ";" 
		    	           + out.getRemuneracionImponible() + ";" + out.getCotizacionImponible() + ";" + out.getUsoSistema() + ";" + out.getMontoPrestacion() ;
		    	        
		    	   
	               // consultamos si rut es afiliado. datos[0] es rut
		    	  
		    		   wr.println(linea2);//escribimos en el archivo
		
		    	   count++;
		
		       }// fin while
        
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
