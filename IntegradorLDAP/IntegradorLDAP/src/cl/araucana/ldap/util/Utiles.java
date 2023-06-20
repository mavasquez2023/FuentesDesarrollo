package cl.araucana.ldap.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cl.araucana.core.util.UserPrincipal;

public class Utiles {
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	
	public static int generarClave(String rutEmpresa,String email){
		double clave =  Math.random()*10000;
		return (int) clave;
	}

	public static Map decode(String credential)
    {	
		Map<String, String> credentials= new HashMap<String, String>();
		UserPrincipal userprincipal1 = UserPrincipal.decodeUserCredentials(credential);
		credentials.put("name", userprincipal1.getName());
		credentials.put("password", userprincipal1.getPassword());
        return credentials;
        
    }
	
	public static String encode(String usuario, String clave)
    {
		UserPrincipal userprincipal = new UserPrincipal(usuario, clave);
        return userprincipal.encode();
    }
	
	public static Map decode128(String credential)
    {
		Map<String, String> credentials= new HashMap<String, String>();
		UserPrincipal userprincipal1 = UserPrincipal.decodeUserCredentials128(credential);
		if(userprincipal1!=null){
			credentials.put("name", userprincipal1.getName());
			credentials.put("password", userprincipal1.getPassword());
		}
        return credentials;
        
    }
	
	public static String encode128(String usuario, String clave)
    {
		UserPrincipal userprincipal = new UserPrincipal(usuario, clave);
        return userprincipal.encode128();
    }
	
	public static String getFechaWeb() {
		return formatoSAP.format(new Date());
	}
	
	public static String getFechaSAP() {
		return formatoSAP.format(new Date());
	}
	
	public static String dateToString(Date fecha) {
		return formatoWeb.format(fecha);
	}
	
	public static String dateToStringSAP(Date fecha) {
		return formatoSAP.format(fecha);
	}
	
	public static String timeToString(Date fecha) {
		return formatoHora.format(fecha);
	}
	
	public static Date sumDays(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, sum);
		return cal.getTime();
	}
	
	public static Date sumMonths(Date fecha, int sum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MONTH, sum);
		return cal.getTime();
	}
	
	public static void main(String args[])
    {
		if (args[0].equals("128")){
			if(args.length == 3 )
	        {
	            System.out.println(encode128(args[1], args[2]));
	        } else
	        if(args.length == 2 )
	        {	
	            System.out.println(decode128(args[1]));
	        }
		}else{
			if(args.length == 2 )
	        {
	            System.out.println(encode(args[0], args[1]));
	        } else
	        if(args.length == 1 )
	        {	
	            System.out.println(decode(args[0]));
	        }
		}
        
        System.exit(0);
    }
	
}
