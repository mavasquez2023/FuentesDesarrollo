package cl.araucana.adminCpe.utils;
/*
* @(#) Renderer.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class Renderer implements com.bh.talon.util.Renderer 
{
	public static final java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");

	/**
	 * formato dec
	 */
	public String formatDec(double d) 
	{
		return "" + d;
	}
	/**
	 * formato int
	 */
	public String formatInt(long l) 
	{
		return "" + l;
	}
	/**
	 * formato moneda
	 */
	public String formatMoney(double d) 
	{
		return "" + d;
	}
	/**
	 * formato dec
	 */
	public String formatDec(Number n) 
	{
		if (n == null)
			return "";
		return n.toString();
	}
	/**
	 * formato int
	 */
	public String formatInt(Number n) 
	{
		if (n == null)
			return "";
		return n.toString();
	}
	/**
	 * formato moneda
	 */
	public String formatMoney(Number n) 
	{
		if (n == null)
			return "";
		return n.toString();
	}
	
	/**
	 * formato string
	 */
	public String formatString(String s) 
	{
		if (s == null)
			return "";
		return s;
	}
	/**
	 * formato string ex
	 */
	public String formatStringEx(String s) 
	{
		if (s == null)
			return "";
		return s;
	}
	/**
	 * formato date time
	 */
	public String formatDatetime(java.util.Date d) 
	{
		if (d == null)
			return "";
		return d.toString();
	}
	
	/**
	 * da formato de texto a una fecha java.sql.Date.
	 */
	public String formatDate(java.util.Date d) 
	{
		if (d == null)	return "";
		return dateFormat.format(d);
	}
	/**
	 * formato tiempo
	 */
	public String formatTime(java.util.Date d) 
	{
		if (d == null)
			return "";
		return d.toString();
	}
	/**
	 * pasea fecha
	 * @param s
	 * @return
	 * @throws java.text.ParseException
	 */
	public java.sql.Date parseDate(String s) throws java.text.ParseException 
	{
		if (s == null)
			return null;
		return new java.sql.Date(dateFormat.parse(s).getTime());
	}
}