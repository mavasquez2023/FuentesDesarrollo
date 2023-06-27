/**
 * 
 */
package cl.recursos;

/**
 * @author usist199
 *
 */
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FechaUtil {
	final static long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día
	
	public static String formatearFecha(String formato, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		String dateString = null;
		try{
			dateString = sdf.format(date);
		}catch(Exception e){
			//@Falta: poner el log4g
//			e.printStackTrace();
		}
		return dateString;
	}
	
	public static Date parsearFecha(String formato, String date){
		if(date==null || date.trim().equals("")) return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date timeStamp2Date(Timestamp dateTimestamp){
		if(dateTimestamp != null)
			return new Date(dateTimestamp.getTime());
		else
			return null;
	}
	
	public static Integer diferenciaFechaEnAnos(Date fechaAntes, Date fechaDespues){
		if(fechaAntes == null || fechaDespues == null){
			return null;
		}else{
			if(fechaAntes.after(fechaDespues)){
				return new Integer(0);
			}
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fechaAntes);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fechaDespues);
		
		// Cálculo de las diferencias.
		int anios = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int meses = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		int dias = cal2.get(Calendar.DAY_OF_MONTH) - cal1.get(Calendar.DAY_OF_MONTH);
		 
		// Aún no es el mes de su cumpleaños o es el mes pero no ha llegado el día		       
		if(meses < 0 || (meses==0 && dias < 0)) {
			anios--;
		}
		return new Integer(anios);
	}
	

	public static Integer calcularEdad(Date fechaNacimiento){
		return diferenciaFechaEnAnos(fechaNacimiento, new Date());
	}
	
	public static Date restarDias(Date fecha, Integer diasRestar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, diasRestar.intValue()*-1);
		return calendar.getTime();
	}
	
	public static Date restarMeses(Date fecha, Integer mesesRestar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, mesesRestar.intValue()*-1);
		return calendar.getTime();
	}
	
	public static Date sumarMeses(Date fecha, Integer mesesSumar){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.MONTH, mesesSumar.intValue());
		return calendar.getTime();
	}
	
	public static Integer getMes(Date fecha){
		if(fecha == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		return new Integer(cal.get(Calendar.MONTH)+1);
	}
	
	public static Integer getAno(Date fecha){
		if(fecha == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		return new Integer (cal.get(Calendar.YEAR));
	}
	
	public static String getDescripcionMes(Date fec){
		Calendar cal = Calendar.getInstance();
		cal.setTime(fec);
		return getDescripcionMes(new Integer(cal.get(Calendar.MONTH)+1));
	}
	
	public static String getDescripcionMes(Integer mes){
		String dMes = null;
		switch(mes.intValue()){
			case 1:	
				dMes = "ENERO";
				break;
			case 2:
				dMes = "FEBRERO";
				break;
			case 3:
				dMes = "MARZO";
				break;
			case 4:
				dMes = "ABRIL";
				break;
			case 5:
				dMes = "MAYO";
				break;
			case 6:
				dMes = "JUNIO";
				break;
			case 7:
				dMes = "JULIO";
				break;
			case 8:
				dMes = "AGOSTO";
				break;
			case 9:
				dMes = "SEPTIEMBRE";
				break;
			case 10:
				dMes = "OCTUBRE";
				break;
			case 11:
				dMes = "NOVIEMBRE";
				break;
			case 12:
				dMes = "DICIEMBRE";
				break;
		}
		return dMes;
	}
	
	public static void main(String [] args){

	}
	
	
}

