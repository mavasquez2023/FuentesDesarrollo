package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.cotfonasa.vo.RespSpVO;

public class Util {
	
	public static boolean isNumeric(String cadena){
			try {
				Integer.parseInt(cadena);
				return true;
			} catch (NumberFormatException nfe){
				return false;
			}
		}
	
	  public static synchronized Date stringToDate(String fecha, String format) {
	        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(format);
	        Date fechaEnviar = null;
	        try {
	            fechaEnviar = formatoDelTexto.parse(fecha);
	            return fechaEnviar;
	        } catch (ParseException ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
	  
	public static String getMensajeExitosoMail(RespSpVO resp, String fecha, String nombreArchivo,String periodo)
	{
		
		String msje = "Estimado Usuario: Proceso para periodo :"+periodo+" ha finalizado Exitosamente! <br>" +
		              "Nombre Archivo: "+nombreArchivo+ "<br>"+
		              "Fecha Proceso: "+fecha+ "<br><br>"+
		              "<table border='2'>"+
		              "<tr><td>&nbsp;</td><td>Cantidad</td><td>Porcentaje</td> </tr>" +
		              "<tr><td>Total Registros</td><td>"+resp.getTotalRegistros()+"</td><td>"+resp.getTotalRegistrosPorc()+"%</td></tr>"+
		              "<tr><td>Total CCAF La Araucana</td><td>"+resp.getTotalAraucana()+"</td><td>"+resp.getTotalAraucanaPorc()+"%</td></tr>"+
		              "<tr><td>Registros con datos (La Araucana)</td><td>"+resp.getTotalConDatosArau()+"</td><td>"+resp.getTotalConDatosArauPorc()+"%</td></tr>"+
		              "</table>";
		
		
		return msje;
	}
	  
	public static String convertMilisegundos(long milisegundos){
		
		
		
		long hora,minuto,segundo;
		long restohora,restominuto,restosegundo;
				
		hora = milisegundos/3600000;
		restohora = milisegundos%3600000;
		
		minuto = restohora/60000;
		restominuto = restohora%60000;
		
		segundo = restominuto/1000;
		restosegundo = restominuto%1000;
		
		return String.valueOf(hora) + ":" + String.valueOf(minuto) + ":" + String.valueOf(segundo);
		
		//System.out.println(hora + ":" + minuto + ":" + segundo + "." + restosegundo);
		
		
	}
	
	public static boolean estadoMemoriaCritica(){
		int mb = 1024*1024;
		Runtime runtime = Runtime.getRuntime();
        
        System.out.println("##### Heap utilization statistics [MB] #####");
         
        /*
        //Print used memory
        System.out.println("Used Memory:"
            + (runtime.totalMemory() - runtime.freeMemory()) / mb);
 
        //Print free memory
        System.out.println("Free Memory:"
            + runtime.freeMemory() / mb);
         
        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);
 
        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);*/
        
        long memoriaLibre = runtime.freeMemory() / mb ;
        long memoriaTotal= runtime.totalMemory() / mb;
        long memoriaUsada =(runtime.totalMemory() - runtime.freeMemory()) / mb;
       // System.out.println("mem Usada:"+memoriaUsada + "< memoria total: "+ memoriaTotal );
        
        System.out.println("memoria libre:"+memoriaLibre + "> 5" );
        // memoria usada debe ser menor a la memoria total
        //return (  ((runtime.totalMemory() - runtime.freeMemory()) / mb) <  (runtime.totalMemory() / mb) );
        
        return (memoriaLibre >  5);
	}
	
	public static int deleteFileServer(String file)
	{
		
		File fichero = new File(file);
		
		if(fichero.delete())
			return 1;
		else
			return 0;
		
	}
	
	public static void procesoArchivo(String archivoEntrada, String archivoSalida) throws IOException
	{
		System.out.println("Se procesa Archivo de Salida, se quitan espacios");
		
		File f = new File(archivoEntrada);
		File fNew = new File(archivoSalida);
		BufferedReader entrada;
		int count =0;
		
		
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
			String [] datos = linea.split(";");
			linea2 = datos[1].replaceAll("\"", "").trim()+";"+datos[2].replaceAll("\"", "").trim()+";"+
			datos[3].replaceAll("\"", "").trim()+";"+datos[4].replaceAll("\"", "").trim()+";"+datos[5].replaceAll("\"", "").trim()+";"+datos[6].replaceAll("\"", "").trim()
			+";"+datos[7].replaceAll("\"", "").trim()+";"+datos[8].replaceAll("\"", "").trim()+";"+datos[9].replaceAll("\"", "").trim();
	
		    wr.println(linea2);//escribimos en el archivo
		
		    count++;
		}
		
        wr.close();
		bw.close();
		
		
	}
	
	public static boolean verificarRut(int rut, char dv) {
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10) {
		s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75);
	
	}
	
	public static boolean validarFecha(String fecha) {

		if (fecha == null)
		return false;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM"); //año-mes

		if (fecha.trim().length() != dateFormat.toPattern().length())
		return false;

		dateFormat.setLenient(false);

		try {
			dateFormat.parse(fecha.trim());
		}
		catch (ParseException pe) {
			return false;
		}
			return true;
		}
	
	

}
