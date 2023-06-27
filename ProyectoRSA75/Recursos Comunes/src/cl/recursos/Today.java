/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import cl.araucana.core.util.AbsoluteDate;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class Today {
	private static String delimFecha = "/-.";
	private static int[] meses = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static Locale locale= new Locale("es", "CL");
	
	public static String fecha(String fecha) {
		return formatFecha(fecha, "-");
	}
	//Formatea fecha a dd<sep>MM<sep>aaaa
	public static String formatFecha(String fecha, String separador) {
		StringBuffer salida = new StringBuffer();
		
		// Si la fecha viene aaaa-MM-dd.
		if (delimFecha.indexOf(fecha.charAt(4)) >= 0)
			salida.append(fecha.substring(8)).append(separador).append(fecha.substring(5, 7)).append(separador).append(fecha.substring(0, 4));
		else
			if (delimFecha.indexOf(fecha.charAt(2)) >= 0) {
				
				// Si la fecha viene dd-MM-aaaa.
				salida.append(fecha.substring(0, 2)).append(separador).append(fecha.substring(3, 5)).append(separador).append(fecha.substring(6));
			}
			else {
				
				// Si la fecha viene aaaaMMdd.
				if (Integer.parseInt(fecha.substring(4)) < 1300)
					salida.append(fecha.substring(6)).append(separador).append(fecha.substring(4, 6)).append(separador).append(fecha.substring(0, 4));
				
				// Si la fecha viene ddMMaaaa.
				else
					salida.append(fecha.substring(0, 2)).append(separador).append(fecha.substring(2, 4)).append(separador).append(fecha.substring(4));
			}
		
		// Retorno.
		return salida.toString();
	}
	
	public static String getAAAAMMDD(char sep) {
		return getDate("yyyy", "MM", "dd", String.valueOf(sep));
	}
	
	public static String getAAAAMMDD() {
		return getDate("yyyy", "MM", "dd", "");
	}
	
	public static String getDDMMAAAA(char sep) {
		return getDate("dd", "MM", "yyyy", String.valueOf(sep));
	}
	
	public static String getDDMMAAAA() {
		return getDate("dd", "MM", "yyyy", "");
	}
	
	public static String getAAAAMM() {
		return getDate("yyyy", "MM", "", "");
	}
	
	public static String getDDdeMesdeAAAA(){
		/*String fecha= getDate("dd", "MMMM", "yyyy", "-");
		fecha= fecha.substring(0, 4).toUpperCase() + fecha.substring(4);
		fecha = fecha.replaceAll("-", sep);
		*/
		DateFormat fdate= DateFormat.getDateInstance(DateFormat.LONG, locale);
		return fdate.format(new Date());
	}
	
	public static String getFecha_Hora(){
		String fecha= getDate("dd", "MM", "yy", "/");
		String hora= getDate("HH", "mm", "ss", ":");
        return fecha + ", " + hora;	
	}
	
	public static String getHHMMSS(char sep) {
		String hora= getDate("HH", "mm", "ss", String.valueOf(sep));
		return hora;
	}
	
	public static String getHHMMSS() {
		String hora= getDate("HH", "mm", "ss", "");
		return hora;
	}
	
	public static String getTimeStamp() {
		StringBuffer tstmp= new StringBuffer();
		Calendar cal =Calendar.getInstance();
		int miliseg= (cal.get(Calendar.MILLISECOND));	//se capturan los milisegundos
		tstmp.append(getAAAAMMDD('-')).append('-').append(getHHMMSS('.')).append('.').append(miliseg);
		return tstmp.toString();
	}
	
	public static String getTodayMillisec() {
		return new java.util.Date().getTime() + "";
	}
	
	private static String getDate(String param1, String param2, String param3, String sep){
		StringBuffer date= new StringBuffer();
		//Se define formato que va ha tener el date
		date.append(param1).append(sep).append(param2);
		if(!param3.equals("")){
			date.append(sep).append(param3);
		}
		        	
		//Se define la Localidad para asegurar fecha en español
		Locale local= new Locale("es", "CL");
		//Se instancia date como formato de fecha
		DateFormat formato=new SimpleDateFormat(date.toString(), local);
		//Se recupera una instancia de la clase Calendar
		Calendar cal =Calendar.getInstance(local);
		//Se obtiene la hora y fecha actual
		Date timezone= cal.getTime();
		//Se formatea la hora-fecha de acuerdo al formato definido
		String fecha=formato.format(timezone);
		return fecha;
	}
	
	public static boolean isBYear(double anio){
		try{
			double r = (anio/4) - Math.round(anio/4);
			if(r == 0)
				return true;
			else
				return false;
		}catch( Exception e) {
			System.out.println("CAI EN VALIDAR AÑO BISIESTO");        	
			e.printStackTrace();
			return false;
		}
	}
	
	//Retorna los días del mes especificado
	public static int diasMes(int mes){
		mes= mes - 1;
		if (mes==1){
			Calendar c = Calendar.getInstance();
			double year= (double) c.get(Calendar.YEAR);
			if (isBYear(year)){
				meses[mes]= 29;
			}
		}
		return meses[mes];
	}
	
	public static boolean validaFecha(String dat){
		try{
			int dd=0, mm=0, aa=0;
			if (dat.trim().equals(""))
				return true;
			else {
				if (dat.length() != 10) 
					return false;
				else{
					String dia = dat.substring(0, 2);
					String mes = dat.substring(3, 5);
					String ano = dat.substring(6, 10);
					try {
						dd = Integer.parseInt(dia);
						mm = Integer.parseInt(mes);
						aa = Integer.parseInt(ano);
					} catch (Exception e) {
						System.out.println("CAI EN Validar Fecha, no numerico");    						
						return false;
					}
				}
				String anoper= getAAAAMMDD().substring(0, 4);
				if (aa < 1900 || aa > Integer.parseInt(anoper)+1)
					return false;
				if (mm < 1 || mm > 12) 
					return false;
				if (mm == 2){
					if ((isBYear(aa) && dd > 29) || (!isBYear(aa) && dd > 28)) 
						return false;
				}
				if (mm == 1|| mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10  || mm == 12) {
					if (dd < 1 || dd > 31) 
						return false;
				}
				if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
					if (dd < 1 || dd > 30) 
						return false;
				}
				String sep="/-. ";
				if (sep.indexOf(dat.substring(2,3))==-1 || sep.indexOf(dat.substring(5,6))==-1){
					return false;
				}
				return true;
			} //end else
		}catch( Exception e) {
			System.out.println("CAI EN VALIDAR FECHA");        	
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String [] args){
		//String fecha= Today.getDDdeMesdeAAAA();
		//String fecha= "31-12-2012";
		//String fechadep= fecha.substring(6) + fecha.substring(3, 5) + fecha.substring(0, 2);
		//System.out.println(new AbsoluteDate(fecha));
		try {
			/*String xmlZonaD= "<ano_mes_rem_ant>2013-04Z</ano_mes_rem_ant><periodo_renta>2013-05Z</periodo_renta>"+
			"<fecha_desde_liquidacion>2013-06-26Z</fecha_desde_liquidacion>"+
		     "<fecha_hasta_liquidacion>2013-07-25Z</fecha_hasta_liquidacion>"+
		      "<fecha_pago_probable>2013-10-24Z</fecha_pago_probable>";
			xmlZonaD = xmlZonaD.replaceAll("Z</ano","</ano").replaceAll("Z</periodo", "</periodo").replaceAll("Z</fecha", "</fecha");
			System.out.println(xmlZonaD);
			*/
			double random= Math.random()* 2;
			System.out.println(random);
			int order = (int)Math.floor(random);
	      	System.out.println("Order:" + order);
			
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
	}
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
	private SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
	
	protected static Date date(String formated, String format) throws Exception{
    	if(null==formated || "0".equals(formated.trim()))formated="19000101";    	
    	
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(formated);
    }

    private static Calendar cal(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT0"));
        calendar.setTime(date);
        return calendar;
    }
    private Calendar cal(String date) {
        Calendar calendar = Calendar.getInstance();
        if(date.equals("190001"))date+="01";
        try {
			calendar.setTime(sdf.parse(date));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return calendar;
    }
}

