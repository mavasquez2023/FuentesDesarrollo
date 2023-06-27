package cl.araucana.spl.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;

import cl.araucana.spl.base.Constants;
import cl.araucana.spl.exceptions.PagoEnLineaException;

public class Utiles {
	
	/**
	 * Inicializa un vector según una cadena y separador
	 * @param cadena
	 * @param token
	 * @return
	 * @author malvarez / Builderhouse Ltda
	 * @since 21-04-2006
	 */
	public static Vector setVector(String cadena, String token) {
		java.util.StringTokenizer tk = new java.util.StringTokenizer(cadena);
		int j = 0;
		Vector ArrayCadena = new Vector();
		while (tk.hasMoreElements()) {
			ArrayCadena.insertElementAt(tk.nextToken(token), j);
			j++;
		}
		return ArrayCadena;
	}
	
	/**
	 * Da formato a una fecha string
	 * @param formato
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date getFechaParse(String formato, String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.parse(fecha);
	}
	
	/**
	 * Entrega a fecha actual del sistema.
	 * @return
	 */
	public static Date getFechaActual() {
		Calendar cal = Calendar.getInstance();
		Date fecha = cal.getTime();
		return fecha;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String join(String joinString, List ids) {
		StringBuffer out = new StringBuffer();
		for (Iterator it = ids.iterator(); it.hasNext(); ) {
			Object el = (Object) it.next();
			out.append(el);
			if (it.hasNext()) {
				out.append(joinString);
			}
		}
		return out.toString();
	}
	
	public static String join(String joinString, String separador, List ids) {
		StringBuffer out = new StringBuffer();
		for (Iterator it = ids.iterator(); it.hasNext(); ) {
			Object el = (Object) it.next();
			out.append(separador + el + separador);
			if (it.hasNext()) {
				out.append(joinString);
			}
		}
		return out.toString();
	}	
	
	public static void copyProperties(Object fuente, Object destino) throws PagoEnLineaException {
		try {
			//BeanUtils.copyProperties(destino, fuente);
			PropertyUtils.copyProperties(destino, fuente);
		} catch (IllegalAccessException e) {
			throw new PagoEnLineaException("Problemas copiando propiedades", e);
		} catch (InvocationTargetException e) {
			throw new PagoEnLineaException("Problemas copiando propiedades", e);
		} catch (NoSuchMethodException e) {
			throw new PagoEnLineaException("Problemas copiando propiedades", e);
		}
	}
	
	public static boolean estadoBciPagado(String s) {
		boolean pagado = false;
		try {
			Integer i = new Integer(s);
			pagado = estadoBciPagado(i);
		} catch (Exception e) {
			//
		}
		return pagado;
	}

	public static boolean estadoBciPagado(Integer i) {
		return  (i != null && estadoBciPagado(i.intValue()));
	}

	public static boolean estadoBciPagado(int i) {
		return Constants.BCI_ESTADO_CARGO_APLICADO == i;
	}

	public static String getNombreClase(String nombreCompleto) {
		int pos = nombreCompleto.lastIndexOf(".");
		String result = nombreCompleto.substring(pos+1, nombreCompleto.length());
		return result;
	}
	
	/**
	 * Transforma un InputStream a String
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String getString(InputStream in) throws IOException {
	    StringBuffer out = new StringBuffer();
	    byte[] b = new byte[4096];
	    for (int n; (n = in.read(b)) != -1;) {
	        out.append(new String(b, 0, n));
	    }
	    return out.toString();
	}
	
	/**
	 * Devuelve la representacion en String de un Map. 
	 * @param mapa
	 * @return
	 */
	public static String getString(Map mapa) {
		StringBuffer sb = new StringBuffer("");
		boolean segundo=false;
		Set set= mapa.keySet();
		Iterator iter = set.iterator();
		sb.append("[Map::").toString();
		while (iter.hasNext()) {
		    Object clave = iter.next();
		    Object valor = mapa.get(clave);
		    if (segundo)
		    	sb.append("|");
		    
		    sb.append(clave + "=" + valor);
		    segundo = true;
		}
		sb.append("]").toString();
		return sb.toString();
	}
	
	public static String calculaFechaContable(int dias) {
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias*-1); // numero de dias a añadir, o restar en caso de horas<0
		return formato.format(calendar.getTime()); // Devuelve el objeto Date con las nuevas horas añadidas

	}

}
