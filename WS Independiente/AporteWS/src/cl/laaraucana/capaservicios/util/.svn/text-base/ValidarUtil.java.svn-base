package cl.laaraucana.capaservicios.util;

public class ValidarUtil {
	
	public static boolean isNumeric(String string)
	{
	    if (string == null)
	    {
	        return false;
	    }
	    string = string.trim();
	    final int len = string.length();
	    if (len == 0)
	    {
	        return false;
	    }
	    for (int i = 0; i < len; ++i)
	    {
	        if (!Character.isDigit(string.charAt(i)))
	        {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean isEmail(String email){
		if(email==null) return false;
		if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		return false;
	}
}
