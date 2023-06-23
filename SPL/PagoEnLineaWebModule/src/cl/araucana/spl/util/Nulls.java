package cl.araucana.spl.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

public class Nulls {
	public static final Date DATE = new GregorianCalendar(1990,0,1,0,0,0).getTime(); // 1-enero-1990
	public static final Integer INTEGER = new Integer(-1);
	public static final BigDecimal BIGDECIMAL = new BigDecimal(-1);
	public static String STRING = "*";

	public static boolean isNull(String s) {
		return s == null || s.equals(STRING);
	}
	public static boolean isNull(Date d) {
		return d == null || d.equals(DATE);
	}
	public static boolean isNotNull(String s) {
		return !isNull(s);
	}
	public static boolean isNotNull(Date d) {
		return !isNull(d);
	}
	public static boolean isNull(BigDecimal n) {
		return n == null || n.equals(BIGDECIMAL);
	}
	public static boolean isNotNull(BigDecimal n) {
		return !isNull(n);
	}
}
