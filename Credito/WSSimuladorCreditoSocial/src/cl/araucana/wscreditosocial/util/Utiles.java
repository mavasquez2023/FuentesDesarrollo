/**
 * 
 */
package cl.araucana.wscreditosocial.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import com.Ostermiller.util.Base64;
import cl.araucana.core.util.Hex;
import cl.araucana.core.util.Rut;

/**
 * @author Claudio Lillo
 *
 */
public class Utiles {

	public static String generaToken(String arg0){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		String token= Hex.encode(Base64.encode(arg0+":"+date));
		return token;
	}

	public static String[] decodeToken(String token)
    {
		String salida[]= new String[2];
		String data= Base64.decode(Hex.decode(token));
		StringTokenizer st = new StringTokenizer(data, ":");
		 if(st.countTokens() == 2)
	        {
	            salida[0] = st.nextToken(); //ip
	            salida[1] = st.nextToken(); //fecha
	        }else{
	        	salida[0] ="";
	        	salida[1] ="";
	        }
        return salida;
        
    }
	
	public static String getFecha(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	
	public static String[] getApellidosSeparados(String apellidos){			
		String exclusiones=";DE;DEL;DA;EL;LA;LOS;LAS;SAN;VON;DI;";
		String aux = "";
		String[] user = apellidos.split(" ");
		int numsep=0;
		for(int i=0;i<user.length; i++){
			if(!user[i].equals("")){
				if(exclusiones.indexOf(";"+user[i].toUpperCase()+";")==-1 && numsep<1){
					aux = aux + user[i]+";";
					numsep++;
				}else{
					aux = aux + user[i]+" ";
				}
			}
		}
		//paso1 = ";" + paso1.trim() + ";";
		String[] apepatymat = aux.split(";");
		return apepatymat;
	}
	public static boolean isRutValido(int rut, char dv){
		Rut rutemp= new Rut(rut);
		if(dv!=rutemp.getDV()){
			return false;
		}
		return true;
	}
	public static boolean isPeriodoValido(String anio, String mes){
		try {
			int periodo= Integer.parseInt(anio+mes);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	public static void main(String args[])
    {
		
    }
}
