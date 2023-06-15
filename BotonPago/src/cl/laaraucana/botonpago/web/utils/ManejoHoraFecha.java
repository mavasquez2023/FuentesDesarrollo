package cl.laaraucana.botonpago.web.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManejoHoraFecha {

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
		}
		//java.sql.Date sqlDate = new java.sql.Date(mifecha);

		return (mifecha);
	}

	public String getFechaString() throws ParseException {

		String fecha = null;
		//2013-08-27	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		fecha = dateFormat.format(new Date());

		return (fecha);
	}

	/**
	 * metodo que retorna la fecha actual del sistema
	 * en formato yyyyMMdd
	 * **/
	public static String getFechaStringFormatDecimal() throws ParseException {

		String fecha = null;
		//20130827	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		fecha = dateFormat.format(new Date());

		return (fecha);
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

	public String getSeparacionPeriodoAño(String periodo) {
		String temp = null;
		temp = periodo.substring(0, 4);
		return temp;
	}
}
