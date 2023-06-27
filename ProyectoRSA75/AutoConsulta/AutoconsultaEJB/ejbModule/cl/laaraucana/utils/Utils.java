package cl.laaraucana.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Utils {
	protected static final Logger LOG = Logger.getLogger(Utils.class);
	
	public static Date toDate(String fecha) {
		if (fecha.length()==8) {
			new Date(Integer.parseInt(fecha.substring(0, 3)), 
					Integer.parseInt(fecha.substring(4, 5)), 
					Integer.parseInt(fecha.substring(6, 7)));
		}
		return new Date();
	} 

	public static Date convertirStringADate(String pattern, String strDate)
	{
		Date date = null;
		if (null != strDate) 
		{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			
			try 
			{
				if (strDate.length()==5) {
					strDate = "0" + strDate;
				}
/*				if (strDate.length()==6) {
					int iAno = Integer.parseInt((String)(strDate.charAt(0) + strDate.charAt(1))); 
				}
				TODO:
				*/
				date = format.parse( strDate );
			} catch (ParseException pe) 
			{
			    LOG.log(Level.ERROR, "No se pudo parsear fecha [ " + strDate + " ]");
			}
		}
		return date;
	}

}
