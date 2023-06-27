package cl.araucana.wslme.common.util;

public class RutUtil {
	public static Boolean validaRut(Integer rut, String dv){
		if(dv.trim().length() <= 0 || dv.trim().length() > 1)
			return new Boolean(false);
		
		char digVer = dv.trim().toUpperCase().charAt(0);
		return validaRut(rut.intValue(), digVer);
    }
	
	public static Boolean validaRut(int rut, char dv)
    {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10)
        {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return new Boolean(dv == (char) (s != 0 ? s + 47 : 75));
    }
	
	public static String rutWSFormat(String rut){
		rut.length();
		for(int i = rut.length(); i < 12; i ++){
			rut = "0" + rut;
		}
		return rut;
	}
}
