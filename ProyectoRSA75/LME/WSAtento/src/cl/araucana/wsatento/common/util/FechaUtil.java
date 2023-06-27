package cl.araucana.wsatento.common.util;

import java.util.Date;

public class FechaUtil {

	public static Integer diferenciasDeFechasEnHoras(Date fechaInicial, Date fechaFinal) {

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double horas = Math.floor(diferencia / (1000 * 60 * 60));
        return new Integer(((int) horas));
    }
	
//	public static void main(String [] args){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		
//		try {
//			diferenciasDeFechasEnHoras(df.parse("2013-04-12 13:00:15"), df.parse("2013-04-12 14:00:15"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
}
