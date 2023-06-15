package cl.araucana.autoconsulta.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utiles {
	
	private static SimpleDateFormat formatoFechaWeb = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public static String getFechaHoy(){
		return formatoFechaWeb.format(new Date());
	}
	
	public static boolean IsRutValido(String rutEntrada){
		//rutEntrada=rutEntrada.replace(".", "").toUpperCase();
		rutEntrada = rutEntrada.replaceAll("\\.","");
		rutEntrada = rutEntrada.toUpperCase();
		
		if(PatronRut(rutEntrada) && rutEntrada.length()<= 10){
			String[] soloRut = rutEntrada.split("-");
			int rut = Integer.parseInt( soloRut[0] );
            char dv = soloRut[1].charAt(0);
            return ValidarRut(rut, dv);
		}
		return false;
	}
	
	 private static boolean ValidarRut(int rut, char dv)
	    {
	        int m = 0, s = 1;
	        for (; rut != 0; rut /= 10)
	        {
	            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
	        }
	        return dv == (char) (s != 0 ? s + 47 : 75);
	    }
	 
	 private static boolean PatronRut(String rutEntrada){
		
		 Pattern patron = Pattern.compile("\\b\\d{1,8}\\-[K|0-9]");
		 Matcher encaja = patron.matcher(rutEntrada);
		 if(encaja.find())
			 return true;
		 else
			 return false;
	 }
	 
	 public static char obtenerDigitoVerificador(int rut)
	    {
	        int m = 0, s = 1;
	        for (; rut != 0; rut /= 10)
	        {
	            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
	        }
	        char dv = (char)(s != 0 ? s + 47 : 75);
	        return dv;
	    }
	public static String  ValidaDVRut(String sRut) {
	    StringBuffer sb = new StringBuffer(sRut);
	    
		String tur = sb.reverse().toString();
	    int mult = 2;
	    int suma=0;

	    for (int i = 0; i < tur.length(); i++) { 
	       if (mult > 7) mult = 2; 
	    
	       suma = mult * Integer.parseInt(tur.substring( i, i )) + suma;
	       mult = mult + 1;
	    }
	    
	    int valor = 11 - (suma % 11);
	    

	    if (valor == 11) { 
	    	return ("0");
	    	
	      } else if (valor == 10) {
	       
	        return ("K");
	        
	      } else { 
	    	  
	        return "" + valor;
	    }
	} 
	
	/**
	 * Extrae parte entera del rut
	 * @param rut
	 * @return
	 * @throws Exception 
	 */
	public static long getLongRut(String rut) throws Exception{
		if(rut==null || rut.trim().equals("")) throw new Exception("Rut no válido");
		long rutLong = 0;
		rut = rut.replaceAll("[\\s\\.]", "");
		System.out.println(rut);
		String[] splitRut = rut.split("-");
		rutLong = Long.parseLong(splitRut[0]);
		return rutLong;
	}
	
	  /**
     * Le da formato a un rut, concatenándole puntos y guión.
     * @param rut Rut a formatear.
     * @return Un nuevo String, con el rut formateado.
     */
 public static String formatearRut(String rut){
	 	if(rut == null || rut.trim().length()==0) return "";
        int cont=0;
        String format;
        rut = rut.replaceAll("\\.", "");
        rut = rut.replaceAll("\\-", "");
        format = "-"+rut.substring(rut.length()-1);
        for(int i = rut.length()-2;i>=0;i--){
            format = rut.substring(i, i+1)+format;
            cont++;
            if(cont == 3 && i != 0){
                format = "."+format;
                cont = 0;
            }
        }
        return format;
    }
}
