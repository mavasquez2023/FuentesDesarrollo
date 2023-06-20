package cl.araucana.lme.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class testBoolean {

	public static void main(String[] args) throws Exception {

		//		Date anoMesRemAnt = date("0", "yyyyMM");
		//		System.out.println(anoMesRemAnt);
		
		Calendar fecConsulta = Calendar.getInstance();
		//System.out.println(fecConsulta.get);
		
	}

	protected static Date date(String formated, String format) throws ParseException {
		if (null == formated || "0".equals(formated.trim()))
			formated = "19000101";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(formated);
	}
}
