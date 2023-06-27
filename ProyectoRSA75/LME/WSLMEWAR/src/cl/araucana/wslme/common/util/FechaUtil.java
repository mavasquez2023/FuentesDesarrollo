package cl.araucana.wslme.common.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class FechaUtil {
	public static Date restarMeses(Date fecha, Integer meses){
		GregorianCalendar  cal = new GregorianCalendar ();
		cal.setGregorianChange(fecha);
		cal.set(GregorianCalendar.MONTH, cal.get(GregorianCalendar.MONTH) - meses.intValue());
		return cal.getTime();
	}
	
	public static synchronized Timestamp getTimestamp(){
		return new Timestamp(new Date().getTime());
	}
}
