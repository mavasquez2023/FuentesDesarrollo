package cl.laaraucana.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {
	private static DateFormat fechaAs400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat fechaHoraAs400 = new SimpleDateFormat("yyyyMMddHHmmss");
	private static DateFormat horaAs400 = new SimpleDateFormat("HHmmss");

	public static String getFechaAs400(Date fecha) {
		return fechaAs400.format(fecha);
	}

	public static String getFechaCompletaAs400(Date fecha) {
		return fechaHoraAs400.format(fecha);
	}

	public static String getHoraAs400(Date fecha) {
		return horaAs400.format(fecha);
	}

	public static boolean isFechaSapValida(String date) {
		if (!date.matches("\\d{4}-\\d{2}-\\d{2}"))
			return false;
		int mes = Integer.parseInt(date.substring(5, 7));
		int anho = Integer.parseInt(date.substring(0, 4));

		if (mes > 12 || anho > 9999)
			return false;

		int dia = Integer.parseInt(date.substring(8, 10));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, anho);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		if (dia > calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			return false;
		return true;
	}
}
