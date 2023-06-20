package cl.laaraucana.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class Utils {

	private static DateFormat formatoWeb = new SimpleDateFormat("dd/MM/yyyy");


	public static String fechaWeb(){
		return formatoWeb.format(new Date());
	}
	}
