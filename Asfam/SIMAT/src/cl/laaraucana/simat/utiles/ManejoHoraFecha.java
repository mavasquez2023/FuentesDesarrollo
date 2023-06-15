package cl.laaraucana.simat.utiles;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*
 * clase que permite manipular variables tipo date, sqlDate y timeStamp.
 * */
public class ManejoHoraFecha {
	
	private static String meses[] = {"Enero","Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public String getKeyFechaHora() {

		String fechaHora = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		fechaHora = dateFormat.format(new Date());
		return fechaHora;
	}

	public Timestamp getFechaHoraTimeStamp() throws ParseException {

		String fechaHora = null;
		//2013-08-27 15:58:34.214		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		fechaHora = dateFormat.format(new Date());
		Date date = null;
		date = dateFormat.parse(fechaHora);
		long time = date.getTime();
		return (new Timestamp(time));
	}

	public Timestamp getTimeStampDefault() throws ParseException {
		return (Timestamp.valueOf("1940-01-01 00:00:00"));
	}

	public boolean checkFechaDefault(String fecha) throws ParseException {
		boolean key = false;
		String aux = "";
		String arr;
		arr = fecha.substring(0, 19);
		aux = arr;
		if (aux.equals("1940-01-01 00:00:00")) {
			return (true);
		}
		if (aux.equals("2001-01-01 00:00:00")) {
			return (true);
		}
		if (aux.equals("0001-01-01 00:00:00")) {
			return (true);
		}
		if (aux == null) {
			return (true);
		}
		return (key);
	}

	public int getHoraEstadoProceso() throws ParseException {

		//String fechaHora=null;
		//155834		
		String Hora = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Hora = dateFormat.format(new Date());

		String aux = String.valueOf(Hora);
		int adaptacion = Integer.parseInt(aux);

		return adaptacion;
	}

	public java.sql.Date getFechaEstadoProceso() throws ParseException {

		//2013-08-27	
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return (sqlDate);
	}

	public java.sql.Date getFechaISO_sql(String fecha) throws ParseException {

		//yyyy-MM-dd
		java.sql.Date mifecha = null;
		if (fecha != null || fecha != "") {
			mifecha = java.sql.Date.valueOf(fecha);
		} else {
			mifecha = null;
		}
		//java.sql.Date sqlDate = new java.sql.Date(mifecha);

		return (mifecha);
	}

	public String getFechaDefault_UTC() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date(0)); //1970-01-01-00:00:00
	}

	public static Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

	public String getFechaString() throws ParseException {

		String fecha = null;
		//2013-08-27	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fecha = dateFormat.format(new Date());

		return (fecha);
	}
	
	public static String getPeriodoString(String periodo) throws ParseException {
		Date fecha = null;
		String texto ="";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		fecha = dateFormat.parse(periodo);
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(fecha);
		int month=0;
		try{
		  month=calendar.get(Calendar.MONTH);
		} catch(Exception ex){}
		
		texto = meses[month] + " del " + calendar.get(Calendar.YEAR);
		
		return texto;
	}

	public String getFechaStringNull(java.sql.Date mifecha) {

		String fecha = "";
		//2013-08-27
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			fecha = dateFormat.format(mifecha);
		} catch (Exception ex) {
			fecha = "";
		}

		return (fecha);
	}

	public String getSeparacionPeriodoMes(String periodo) {
		String temp = null;
		temp = periodo.substring(4, 6);
		return temp;
	}
	
	public String getSeparacionPeriodoMesNum(String periodo){
		String temp=null;
		temp=periodo.substring(4,6);		
		if(temp.substring(0, 1).equalsIgnoreCase("0")){
			temp=temp.substring(1, 2);
		}		
		return temp;
	}
	
	public String getSeparacionPeriodoAño(String periodo) {
		String temp = null;
		temp = periodo.substring(0, 4);
		return temp;
	}

}
