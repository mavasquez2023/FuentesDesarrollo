

/*
 * @(#) Selector.java    1.0 25-06-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.ondemand;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 25-06-2009 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author  Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class Selector implements Serializable {

	private static final long serialVersionUID = 1299050668663989299L;
	
	private static final boolean debug = Boolean.getBoolean("selector.debug");
	
	private static Map addedGranularityClasses = new TreeMap();
	
	private int granularity;
	private Object[] args = new Object[0];
	private int sample = Integer.MAX_VALUE;
	
	private Map granularities;
	
	private static synchronized Map loadGranularities(Class clazz) {
		
		if (!clazz.isInterface()) {
			throw new IllegalArgumentException("An interface was expected");
		}
		
		String className = clazz.getName();
		Map granularities =	(Map) addedGranularityClasses.get(className);
		
		if (granularities == null) {
			granularities = new TreeMap();
			
			Field[] fields = clazz.getFields();
			
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				
				if (field.getType().equals(int.class)) {
					try {
						String fieldName = field.getName();
						Integer intValue = new Integer(field.getInt(null));
						
						granularities.put(intValue, fieldName);
						
						if (debug) {
							System.err.println(
									  "add granularity for "
									+ className + ": "
									+ fieldName + " = "
									+ intValue);
						}
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {}
				}
			}
			
			addedGranularityClasses.put(className, granularities);
		}
		
		return granularities;
	}
	
	public Selector() {
		this(GranularityConstants.class);
	}
	
	public Selector(Class granularitiesInterface) {
		granularities = loadGranularities(granularitiesInterface);
	}
	
	public int getGranularity() {
		return granularity;
	}
	public void setGranularity(int granularity) {
		if (granularities.get(new Integer(granularity)) == null) {
			throw new IllegalArgumentException(
					"Unknown granularity " + granularity);
		}
		
		this.granularity = granularity;
	}

	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}

	public int getSample() {
		return sample;
	}
	public void setSample(int sample) {
		this.sample = sample;
	}
	
	public String getGranularityName() {
		return granularities.get(new Integer(granularity)).toString();
	}
	
	public String toString() {
		String sSample =
				(sample == Integer.MAX_VALUE)
						? ""
						: ("\nsample = " + sample);
		
		String sArgs = "";
		
		for (int i = 0; i < args.length; i++) {
			sArgs += "\"" + args[i] + "\"";
			
			if (i + 1 < args.length) {
				sArgs += ", ";
			}
		}
		
		return
				  "granularity=" + getGranularityName() + " "
				+ "(" + granularity + ")\n"
				+ "args=[" + sArgs + "]"
				+ sSample;
	}
	
	public static void main(String[] args) throws Exception {
		
		if (args.length < 2) {
			System.err.println("usage: <interfaceName> <v1> ... <vn>");
			System.exit(1);
		}
		
		Class granularityClazz = Class.forName(args[0]);
		
		for (int i = 1; i < args.length; i++) {
			int granularity = Integer.parseInt(args[i]);
			Selector selector = new Selector(granularityClazz);
			
			selector.setGranularity(granularity);
			
			System.out.println(selector);
		}
	}
}
