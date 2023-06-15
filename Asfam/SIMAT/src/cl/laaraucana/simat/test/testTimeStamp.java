package cl.laaraucana.simat.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cl.laaraucana.simat.utiles.ManejoHoraFecha;

public class testTimeStamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManejoHoraFecha mhf = new ManejoHoraFecha();
		try {

			System.out.println("fecha: " + mhf.getTimeStampDefault());
			System.out.println("fecha default: " + mhf.checkFechaDefault("1940-01-01 00:00:00.0"));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fechaStr = "2013-10-10 10:49:29.10000";
			Date fechaNueva = format.parse(fechaStr);

			System.out.println("format tp: " + format.format(fechaNueva)); // Prints 2013-10-10 10:49:29

			ManejoHoraFecha hfa = new ManejoHoraFecha();
			System.out.println("start test date cero");
			//			System.out.println("hfa_UTC: "+hfa.getFechaDefault_UTC());
			//			System.out.println("ParseFecha: "+hfa.ParseFecha("2039-01-01"));
			//			System.out.println("getFechaISO_sql: "+hfa.getFechaISO_sql("2039-01-01"));
			//			
			try {
				System.out.println(" try) getFechaISO_sql 0: " + hfa.getFechaISO_sql(""));
			} catch (Exception e) {
				System.out.println(" catch) getFechaISO_sql 0: " + hfa.getFechaISO_sql("2039-01-01"));
			}
			System.out.println("\n");

			System.out.println("");
			//El método getTime devuelve el número de 
			//milisegundos desde el 
			//1 de enero de 1970 00:00:00 GMT.
			Calendar c = Calendar.getInstance();
			c.set(1970, 0, 1);
			//			devuelve un Date
			System.out.println(c.getTime());
			//			getTime de Date
			System.out.println(c.getTime().getTime());
			//Thu Jan 01 19:02:49 CET 1970
			//Este sería el número más pequeño en positivo: 
			//64986488 (8 cifras) 
			c.set(1969, 11, 31);
			System.out.println(c.getTime());
			System.out.println(c.getTime().getTime());
			//Wed Dec 31 19:04:42 CET 1969
			//Si ponemos una fecha anterior, da nº negativos: 
			//-21339001 (8 cifras)

			c.set(2014, 5, 1);
			System.out.println(c.getTime());
			System.out.println(c.getTime().getTime());
			//Sun Jun 01 19:08:10 CEST 2014
			//1401642490920 (13 cifras)

			System.out.println("test: ");
			c.set(0001, 01, 01);
			System.out.println(c.getTime());
			System.out.println(c.getTime().getTime());
			System.out.println(c.YEAR);
			System.out.println(c.DAY_OF_MONTH);
			System.out.println(c.MONTH);
			//Tue Dec 31 19:06:53 CET 2999
			//32503658813708 (14 cifras)

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CATCH: " + e);
		}
	}

}
