package com.microsystem.lme.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutUtil {
	
	public static boolean IsRutValido(String rutEntrada){
	
		rutEntrada= rutEntrada.replaceAll("\\.", "");
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
	 
	 public static int obtenerParteNumerica(String rut){
		 rut= rut.replaceAll("\\.", "");
		 int pos= rut.indexOf("-");
		 if(pos>-1){
			 return Integer.parseInt(rut.substring(0, pos));
		 }
		 return Integer.parseInt(rut);
	 }
}
