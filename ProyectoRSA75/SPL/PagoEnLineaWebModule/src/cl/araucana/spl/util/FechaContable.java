package cl.araucana.spl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.core.util.AbsoluteDate;

public class FechaContable {
	private static final Logger logger = Logger.getLogger(FechaContable.class);
	
	/**
	 * Entrega la fecha contable correspondiente a una fecha dada.
	 *
	 */
	public Date getFechaContable(Date fecha) {
		AbsoluteDate absFecha = new AbsoluteDate(fecha);
		Calendar cal = Calendar.getInstance(); cal.setTime(fecha);
		AbsoluteDate contable = null;
		if (esFeriadoBancario(absFecha)) {
			contable = absFecha.getNextWorkday();
		} else if (absFecha.isWorkday()) {
			if (cal.get(Calendar.HOUR_OF_DAY) < 14) {
				contable = absFecha;
			} else {
				AbsoluteDate next = absFecha.getNextWorkday();
				if (esFeriadoBancario(next)) {
					contable = next.getNextWorkday();
				} else {
					contable = next;
				}
			}
		} else {
			AbsoluteDate next = absFecha.getNextWorkday();
			if (esFeriadoBancario(next)) {
				contable = next.getNextWorkday();
			} else {
				contable = next;
			}
		}
		return contable.getDate();
	}
	
	private boolean esFeriadoBancario(AbsoluteDate today) {
		AbsoluteDate tomorrow = today.getNextWorkday();

		boolean es = (today.isWorkday() && tomorrow.getYear() > today.getYear());
		if (logger.isDebugEnabled()) {
			logger.debug("   " + today + " feriado bancario? " + es);
		}
		return es;
	}
	
	private static void prueba(String fecha, String hora) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		FechaContable fc = new FechaContable();
		logger.debug("Contable de " + fecha + " " + hora + " == *** " + fc.getFechaContable(sdf.parse(fecha + " " + hora)));
	}
	
	public static void main(String[] args) throws ParseException {
		prueba("02-12-2007", "13:30");
		prueba("02-12-2007", "13:59");
		prueba("02-12-2007", "14:00");
		prueba("02-12-2007", "15:30");
		prueba("28-12-2007", "13:30");
		prueba("28-12-2007", "15:30");
		prueba("29-12-2007", "11:30");
		prueba("29-12-2007", "15:30");
		prueba("30-12-2007", "13:55");
		prueba("30-12-2007", "15:30");
		prueba("31-12-2007", "13:50");
		prueba("31-12-2007", "15:30");
		prueba("01-01-2007", "13:59");
		prueba("01-01-2007", "14:00");
		prueba("01-01-2007", "14:01");
		prueba("01-01-2007", "15:30");
	}
}
