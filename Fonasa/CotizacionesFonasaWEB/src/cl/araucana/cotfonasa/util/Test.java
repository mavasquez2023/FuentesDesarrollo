package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.araucana.cotfonasa.dao.ProcesoFonasaDAO;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Test.ejecutaProceso2("112013");
		
		
		/*DateFormat dateFormat2 = new SimpleDateFormat("MMyyyy");
		
		Date date = new Date();
	   	
		String fechaNombreArchivo =dateFormat2.format(date);
	
		System.out.println("DATE format2:"+fechaNombreArchivo);*/
		
		System.out.println(Test.verificarRut(22654, '8'));
		
		//System.out.println(Test.validarFecha("201005"));


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

	public static void ejecutaProceso2(String periodo)
	{
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
		DateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String periodoBd = periodo.substring(2, 6)+ periodo.substring(0, 2); 
		
		System.out.println("periodo: "+periodoBd);
		
		String fechaInicio2=dateFormat2.format(date);
		String horaInicio = timeFormat.format(date);
		String horaInicioIns = timeFormat2.format(date);
		
		
		System.out.println("time format:"+timeFormat.format(date));
		System.out.println("DATE format2:"+dateFormat2.format(date));
		
        String archivoEntrada= "C:\\archivosAraucana\\entrada\\CCAF112013.txt";
		
		
		
		System.out.println("archivo entrada: "+archivoEntrada);
		
		
		File f = new File(archivoEntrada);
		//File fNew = new File(archivoErrores);
		
		ProcesoFonasaDAO dao = new ProcesoFonasaDAO();
		BufferedReader entrada;
		boolean flagErrores = false;
		
		int count =0;
		
		
		try {
			
			
			long time_start, time_end;
				
			entrada = new BufferedReader( new FileReader( f ) );
			String linea;
			int estado=1;
	       // FileWriter w = new FileWriter(fNew);
			//ArrayList inputs=  new ArrayList() ;
			//BufferedWriter bw = new BufferedWriter(w);
			//PrintWriter wr = new PrintWriter(bw);
			time_start = System.currentTimeMillis();
			
			int multiplicador=1;
			int counter=200000;
			int registros =0;
			while(entrada.ready()){
			
				//InputFonasaVO input = new InputFonasaVO();
				
				linea = entrada.readLine();
				
				String [] datos = linea.split(";");
				
				System.out.println("datos: "+datos[0]+";"+datos[1]+";"+datos[2]+";"+datos[3]+";"+datos[4]);
				System.out.println("registro: "+count);
				
				if(datos[0].equals("") || datos[0].equals(null) || datos[1].equals("") || datos[1].equals(null) ||
					datos[2].equals("") || datos[2].equals(null) || datos[3].equals("") || datos[3].equals(null)
					|| datos[4].equals("") || datos[4].equals( null	)){
					//wr.println(linea);
					flagErrores = true;
					
					System.out.print("flag"+flagErrores);
					estado=0;
				}
				
				
				
				
				Integer.parseInt(datos[0]);
				
				
				Integer.parseInt(datos[2]);
				
				
				Integer.parseInt(datos[4]);

				
				
			
			    //System.out.println("------- \n");

			count++;
			}
	        
			
			//bw.close();
			
		
			time_end = System.currentTimeMillis();
			
			
			System.out.println("the task has taken "+ ( time_end - time_start )/1000 +" seconds");
			
			
		}catch (IOException e) {
			System.out.println("error:"+e.getMessage());
		e.printStackTrace();
		}catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("error parse int"+e.getMessage());
		}
		finally {
 
              		
		}
		
	}

}
