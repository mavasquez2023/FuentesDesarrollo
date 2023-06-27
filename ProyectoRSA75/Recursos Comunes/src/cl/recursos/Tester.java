

/*
 * @(#) LoggingTester.java    1.0 03-02-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.recursos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;




/*
 * @author Claudio Lillo
 *
 * @version 1.0
 */
public class Tester {
	public static void main(String[] args) {
		System.out.println((int)Math.ceil(0.4));
		System.out.println((int)Math.ceil((double)6/15));
		
		System.out.println((int)Math.ceil(12/15));
		System.out.println((int)Math.ceil(18/15));
		System.out.println((int)Math.ceil(32/15));
		System.out.println((int)Math.ceil(45/15));
		/*int cierre=11075;
		String cierrestr= String.valueOf(cierre);
		int cierre_aux= Integer.parseInt(cierrestr.substring(cierrestr.length() - 3));
		System.out.println("cierre1=" + cierre_aux);
		
		cierre=11076;
		Double fx= new Double((double)cierre/1000);
		cierre_aux=(int)(Math.round((fx.doubleValue() - fx.intValue())*1000));
		System.out.println("cierre2=" + cierre_aux);*/
		//Insertamos periodo de pago
		int anho = FechaUtil.getAno(new Date()).intValue();
		Date fechaAux = FechaUtil.restarMeses(new Date(), new Integer(1));
		String mesAux = FechaUtil.getDescripcionMes(FechaUtil.getMes(fechaAux));
		String mes = FechaUtil.getDescripcionMes(FechaUtil.getMes(new Date()));
	    
	    //Insertamos fecha
		String fecha = FechaUtil.formatearFecha("dd/MM/yy", new Date());
		String mesAnterior = "";
		int anhoAnterior = anho;
		if(mes=="1"){// Por si estamos en enero
			mesAnterior = FechaUtil.getDescripcionMes(new Integer(12));
			anhoAnterior--;
		}else{
			mesAnterior = FechaUtil.getDescripcionMes(new Integer(FechaUtil.getMes(new Date()).intValue()-1));
		}
		String dia = FechaUtil.formatearFecha("dd", new Date());
		
	}
	/*
	public static void main(String[] args) {

		long remu=1000000;
		long tasa=95;
		double porctasa= (double)tasa/100;
		System.out.println("porcentaje tasa=" + porctasa);
		System.out.println("ponderado=" + remu * porctasa/100);
		System.out.println("round=" + Math.round(remu * porctasa/100));
	}
	*/
/*
	public static void main(String[] args) {

		TimeZone.setDefault(new SimpleTimeZone(-14400000, "Chile/Continental", 
				Calendar.OCTOBER, 8, -Calendar.SUNDAY,
                0,
                Calendar.APRIL, 1, -Calendar.SUNDAY,
                0,
                3600000)
		);
		
		System.out.println(TimeZone.getDefault().getDisplayName());
		   // Make a new Date object. It will be initialized to the current time.
        Date now = new Date();
        Locale locale= new Locale("ES", "CL");
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss",  locale);

	 // Aplicando SimpleDateFormat
	  System.out.println("1 "+sdf.format(now) );

        // Next, try the default DateFormat
        System.out.println(" 2. " + DateFormat.getInstance().format(now));

        // And the default time and date-time DateFormats
        System.out.println(" 3. " +
            DateFormat.getDateTimeInstance().format(now));

        System.out.println(" 4. " + DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM, DateFormat.SHORT).format(now));
        System.out.println("5. " + DateFormat.getDateTimeInstance(
            DateFormat.LONG, DateFormat.LONG).format(now));
	}*/
}
