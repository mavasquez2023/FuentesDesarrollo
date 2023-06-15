/**
 * 
 */
package liv.comun.utiles;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilesComunes
{
    static SimpleDateFormat formatoTimestamp = new SimpleDateFormat("yyyyMMdd hhmmss");
    static SimpleDateFormat formatoTimestampFull = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    static SimpleDateFormat formatoYear4 = new SimpleDateFormat("yyyy");
    static SimpleDateFormat formatoYear2 = new SimpleDateFormat("yy");
    static SimpleDateFormat formatoMonth2 = new SimpleDateFormat("MM");
    static SimpleDateFormat formatoYearMonth = new SimpleDateFormat("yyyyMM");
    static SimpleDateFormat formatoMonthYear = new SimpleDateFormat("MMyy");
    static SimpleDateFormat formatoMonthYearFull = new SimpleDateFormat("MMyyyy");
    public static HashMap variablesEstaticas = new HashMap();

    public UtilesComunes()
    {
    }

    public static String getMD5(String texto)
    {
        MessageDigest m;
        try{
        	m = MessageDigest.getInstance("MD5");
        	m.update(texto.getBytes(), 0, texto.length());
        	return (new StringBuffer(String.valueOf((new BigInteger(1, m.digest())).toString(16)))).toString();
        }catch(NoSuchAlgorithmException e){
        	e.printStackTrace();
        }
        return null;
    }

    public static String reemplazarVariables(String comando)
    {
        comando = comando.replaceAll("sys.periodo.anterior2", getVariable("sys.periodo.anterior2"));
        comando = comando.replaceAll("sys.periodo.anterior", getVariable("sys.periodo.anterior"));
        comando = comando.replaceAll("sys.periodo.siguiente", getVariable("sys.periodo.siguiente"));
        comando = comando.replaceAll("sys.periodo.MMAAAA.siguiente", getVariable("sys.periodo.MMAAAA.siguiente"));
        comando = comando.replaceAll("sys.month2.anterior", getVariable("sys.month2.anterior"));
        comando = comando.replaceAll("sys.timestamp", getVariable("sys.timestamp"));
        comando = comando.replaceAll("sys.date.full", getVariable("sys.date.full"));
        comando = comando.replaceAll("sys.YearMonth", getVariable("sys.YearMonth"));
        comando = comando.replaceAll("sys.month2", getVariable("sys.month2"));
        comando = comando.replaceAll("sys.year4", getVariable("sys.year4"));
        comando = comando.replaceAll("sys.year2", getVariable("sys.year2"));
        comando = comando.replaceAll("sys.mesDescriptivo", getVariable("sys.mesDescriptivo"));
        return comando;
    }

    public static String getVariable(String key)
    {
        if(key != null)
        {
            if(variablesEstaticas.get(key) != null)
                return variablesEstaticas.get(key).toString();
            Calendar gc = GregorianCalendar.getInstance();
            if(key.equals("sys.date.full"))
                return formatoTimestampFull.format(new Date());
            if(key.equals("sys.timestamp"))
                return formatoTimestamp.format(new Date());
            if(key.equals("sys.year4"))
                return formatoYear4.format(new Date());
            if(key.equals("sys.year2"))
                return formatoYear2.format(new Date());
            if(key.equals("sys.month2.anterior"))
            {
                gc.add(2, -1);
                return formatoMonth2.format(gc.getTime());
            }
            if(key.equals("sys.periodo.anterior"))
                return formatoYearMonth.format(gc.getTime());
            if(key.equals("sys.periodo.siguiente")){
            	gc.add(2, 1);
            	return formatoMonthYear.format(gc.getTime());
            }
            if(key.equals("sys.periodo.anterior2")) {
            	gc.add(2, -1);
                return formatoYearMonth.format(gc.getTime());
            }
            if(key.equals("sys.periodo.MMAAAA.siguiente")){
            	gc.add(2, 1);
            	return formatoMonthYearFull.format(gc.getTime());
            }
            if(key.equals("sys.month2"))
                return formatoMonth2.format(new Date());
            if(key.equals("sys.YearMonth"))
                return formatoYearMonth.format(new Date());
            if(key.equals("sys.mesDescriptivo"))
            {
                String _mes = formatoMonth2.format(new Date());
                int mes = Integer.parseInt(_mes);
                return obtenerDescripcionMes(mes);
            }
        }
        return null;
    }

    public static String obtenerDescripcionMes(int mes)
    {
        switch(mes)
        {
        case 1: // '\001'
            return "Enero";

        case 2: // '\002'
            return "Febrero";

        case 3: // '\003'
            return "Marzo";

        case 4: // '\004'
            return "Abril";

        case 5: // '\005'
            return "Mayo";

        case 6: // '\006'
            return "Junio";

        case 7: // '\007'
            return "Julio";

        case 8: // '\b'
            return "Agosto";

        case 9: // '\t'
            return "Septiembre";

        case 10: // '\n'
            return "Octubre";

        case 11: // '\013'
            return "Noviembre";

        case 12: // '\f'
            return "Diciembre";
        }
        return "";
    }
    
    public static String getCantidadFormateada(String texto)
    {
    	NumberFormat numberFormatter = new DecimalFormat("###,###");
		String result = numberFormatter.format(Double.parseDouble(texto));
        return result;
    }

    public static String getMontoFormateado(String texto)
    {
        return "$" + getCantidadFormateada(texto);
    }

}

