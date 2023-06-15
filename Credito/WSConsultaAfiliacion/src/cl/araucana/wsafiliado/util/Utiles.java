/**
 * 
 */
package cl.araucana.wsafiliado.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import com.Ostermiller.util.Base64;
import cl.araucana.core.util.Hex;

/**
 * @author Claudio Lillo
 *
 */
public class Utiles {

	public static String generaToken(String arg0, String arg1){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		String token= Hex.encode(Base64.encode(arg0+":"+ arg1 + ":" + date));
		return token;
	}

	public static String[] decodeToken(String token)
    {
		String salida[]= new String[3];
		String data= Base64.decode(Hex.decode(token));
		StringTokenizer st = new StringTokenizer(data, ":");
		 if(st.countTokens() == 3)
	        {
	            salida[0] = st.nextToken(); //ip
	            salida[1] = st.nextToken(); //usuario
	            salida[2] = st.nextToken(); //fecha
	        }else{
	        	salida[0] ="";
	        	salida[1] ="";
	        	salida[2] ="";
	        }
        return salida;
        
    }
	
	public static String getFecha(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static String getPeriodo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static int getAnio(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}
	
	public static boolean validaFecha(String param){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		if(!date.equals(param) ){
			return false;
		}
		return true;
	}
	
	
	public static void main(String args[])
    {
		
    }
}
