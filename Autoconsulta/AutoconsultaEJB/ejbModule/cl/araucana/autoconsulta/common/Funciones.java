
/*
 * @(#) Funciones.java  1.0 30-01-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.autoconsulta.common;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Funciones {
	private static DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat formatoFechaNro = new SimpleDateFormat("ddMMyyyy");
	
	private static DateFormat formatoAS400 = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat formatoPeriodo = new SimpleDateFormat("yyyyMM");
	
	/**
	 * Convierte fecha desde formato ddMMyyyy a dd/MM/yyyy
	 * @param fecha
	 * @return
	 * @throws ParseException 
	 * @throws ParseException
	 */
	
	public static String agregaSlash(String fecha) throws ParseException{
		return formatoFecha.format(formatoFechaNro.parse(fecha));
	}
	
	/**
	 * Convierte fecha con formato yyyy/MM/dd a Date
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static Date parseFechaWebToDate(String fecha) throws ParseException{
		Date fechaIngreso = formatoFecha.parse(fecha);
		return fechaIngreso;
	}
	
	/**
	 * Formatea Fecha a dd/MM/yyyy
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateToWeb(Date fecha) throws ParseException{
		String fechaIngreso = formatoFecha.format(fecha);
		return fechaIngreso;
	}
	
	public static String llenaConCeros(String dato, int largoFinal) {
		if(dato == null){
			dato = "";
		}
		String salida = dato;
		//if (!isEmpty(dato)) {
			for (int i = dato.length(); i < largoFinal; i++) {
					salida = "0" + salida;
			}
		//}
		return salida;
	}
	
	/**
	 * Convierte fecha desde formato dd/MM/yyyy a yyyyMMdd
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public static String pasaFechaWebToAS(String fecha) throws ParseException{
		return formatoAS400.format(formatoFecha.parse(fecha));
	}
	
	/**
	 * Convierte fecha desde formato yyyyMMdd a dd/MM/yyyy
	 * @param fecha
	 * @return
	 * @throws ParseException 
	 */
	public static String pasaFechaAsToWeb(String fecha) throws ParseException{
		return formatoFecha.format(formatoAS400.parse(fecha));
	}
	
/**
 * Convierte fecha periodo yyyyMM
 * @param fecha
 * @return
 */
	public static String getPeriodo(Date fecha){
		String periodo = formatoPeriodo.format(fecha);
		return periodo;
	}
	
	
	
	public static String replace(String data, String find, String replace)
	{
		int i = 1;
		int j = 0;
		int k = 1;
		int m = find.length();
		String salida = new String();
		if(find.equalsIgnoreCase(""))
			return data;
		for(i = inStr(i, data, find); i > 0; i = inStr(i, data, find))
		{
			salida = String.valueOf(salida) + String.valueOf(String.valueOf(mid(data, k, i - k)) + String.valueOf(replace));
			j++;
			i += m;
			k = i;
		}

		salida = String.valueOf(salida) + String.valueOf(mid(data, k));
		return salida;
	}
	
public static String rellenaCerosIzq(String valor2,int total)
{

	String valor = "";
	if(valor2!=null)valor=valor2;
	String strValor = "";
	int i=0;
	String espacios = "";
	int blancos = total - valor.length();
	
	for (i=1;i<=blancos;i++)
	{
		espacios = espacios + "0";
	}

	strValor = espacios + valor;

	return strValor;
}

public static String mid(String data, int desde)
	{
		int idesde = desde - 1;
		if(data == null)
			return "";
		if(idesde >= data.length())
			return "";
		if(idesde < 0)
			return "";
		else
			return data.substring(idesde);
	}

	public static String mid(String data, int desde, int hasta)
	{
		int iend = (desde + hasta) - 1;
		int idesde = desde - 1;
		if(data == null || hasta == 0)
			return "";
		if(desde < 0 || hasta < 0)
			return "";
		if(idesde >= data.length())
			return "";
		if(iend > data.length())
			return data.substring(idesde);
		else
			return data.substring(idesde, iend);
	}
	
	public static int inStr(String data, String data1)
   {
	   return inStr(1, data, data1);
   }

   public static int inStr(int desde, String data, String data1)
   {
	   return inStr(desde, data, data1, 1);
   }

   public static int inStr(int desde, String data, String data1, int CompareMethod)
   {
	   int idesde = desde - 1;
	   if(data == null || desde <= 0 || desde > data.length())
		   return 0;
	   if(data1 == null)
		   return desde;
	   if(CompareMethod == 1)
		   return data.toLowerCase().indexOf(data1.toLowerCase(), idesde) + 1;
	   else
		   return data.indexOf(data1, idesde) + 1;
   }
   

	/** ***************************************************************************************
	 * Método			:	getFechaNowYYYY_MM_DD
	 * Descripción		:	Entrega la fecha actual en formato YYYY_MM_DD
	 * Entrada			:	
	 * Salida			:	String
	 * ****************************************************************************************
	 */
	public String getFechaNowYYYY_MM_DD(String Separador)
	{
	    String fecha = new String();
	    Date fechaActual= new Date();
	    
	    String dia = Integer.toString(fechaActual.getDate());
	    String mes = Integer.toString(fechaActual.getMonth() + 1);
	    String ano = Integer.toString(fechaActual.getYear() + 1900);
	    if(dia.length()<2) dia = "0" + dia; 
	    if(mes.length()<2) mes = "0" + mes; 
	     
	    fecha= ano + Separador + mes + Separador + dia;
	    
		return fecha;
	}
	
	/** ***************************************************************************************
	 * Método			:	getHoraNowHH_MM_SS
	 * Descripción		:	Entrega la hora actual en formato HH_MM_SS
	 * Entrada			:	
	 * Salida			:	String
	 * ****************************************************************************************
	 */
	public String getHoraNowHH_MM_SS(String Separador)
	{
	    String hora = new String("");
	    
	    Calendar cal = new GregorianCalendar();    
		
		String horas24 	= Integer.toString(cal.get(Calendar.HOUR_OF_DAY));     // 0..23
		String minutos 	= Integer.toString(cal.get(Calendar.MINUTE));          // 0..59
		String segundos	= Integer.toString(cal.get(Calendar.SECOND));          // 0..59
		
	    hora = horas24 + Separador + minutos + Separador + segundos;
	    
		return hora;
	}
    
	public String formatFecha(Date fecha, String stringFormateo) 
    {       
    	SimpleDateFormat df = new SimpleDateFormat(stringFormateo); 
        String sFecha = df.format(fecha);       
        
        return sFecha;
    }
	
	/**************************************************************
	 * NOMBRE FUNCION 	: invierteFecha
	 * DESCRIPCION		: RETORNA FECHA EN UN FORMATO INDICADO COMO 
	 * 					  PARAMETRO
	 * ************************************************************/
	    
	public static String invierteFecha(String valor, String formatoInicial, String formatoFinal)
	{

        String fechaS = valor;
        String fechaInvertida="";

        try{
	        if (fechaS!=null && !fechaS.equals("")){
		        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(formatoInicial);
		        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(formatoFinal);        
		
		        Date fecha = simpleDateFormat1.parse(fechaS);
		        fechaInvertida = simpleDateFormat2.format(fecha);
	        }
	        else{
	        	fechaInvertida="";
	        }
        
	        return fechaInvertida;
        }
        
        catch (Exception e){
        	System.err.println("[ERROR:FUNCTION]: Funciones: invierteFecha: "+e.getMessage());
	        return fechaInvertida;        
        }
    }
	public static String verificaNull(String cadena)
	{ 	String valor = new String("");
		if (cadena == null || cadena.equals("null"))
		{	valor = "";	
		}
		else
		{	valor = cadena.trim();
		}
		
		return valor;
	}
	
	public static String NullToCero(String cadena)
	{ 	String valor = new String("");
		if (cadena == null)
		{	valor = "0";	
		}
		else
		{	valor = cadena.trim();
		}
		
		return valor;
	}
	public static java.math.BigDecimal NullToCeroBig(java.math.BigDecimal cadena)
	{ 	
		java.math.BigDecimal valor=new java.math.BigDecimal("0"); 
		if (cadena == null)
		{	
			valor = new java.math.BigDecimal("0");
		}else{
			valor=cadena;
		}
		
		return valor;
	}
	public static Vector getArreglo(String svalor, char separador)
	{
		String valor = svalor + separador;
		Vector vecDat = new Vector();
		StringBuffer strDat = new StringBuffer ("");
		int i;
		int j;

		for(i=0,j=0;i<valor.length();i++)
		{
			if(valor.charAt(i) != separador)
			{	
				strDat.append(valor.charAt(i));
			}
			else
			{
				vecDat.insertElementAt(strDat.toString(),j);
				strDat = new StringBuffer ("");
				j++;
			}
		}		
		
		return vecDat;
	}
    public static String[] split(String data, String delimiter)
    {
        int i = 1;
        int j = 0;
        int k = 1;
        int m = delimiter.length();
        for(i = inStr(i, data, delimiter); i > 0; i = inStr(i, data, delimiter))
        {
            j++;
            i += m;
        }

        String x[];
        if(j > 0)
        {
            x = new String[j + 1];
            i = 1;
            j = 0;
            k = 1;
            for(i = inStr(i, data, delimiter); i > 0; i = inStr(i, data, delimiter))
            {
                x[j] = mid(data, k, i - k);
                j++;
                i += m;
                k = i;
            }

            x[j] = mid(data, k);
        }
        else
        {
            x = new String[1];
            x[0] = data;
        }
        return x;
    }
	public static Date convertStringToDate(String cadena, SimpleDateFormat df) throws Exception
	{			
		try {	
		
			Date fech = df.parse(cadena);			
			return fech;
		} 

		catch (Exception e) {
			throw new Exception(e.getMessage());			
		}
	}
	
	public static Vector getDatosArray(String svalor, char separador)
	{
		Vector vecDat= new Vector();
    	Collection colObs= (Collection)Funciones.getArreglo(svalor,separador);
    	Iterator itValor=colObs.iterator();
    	
    	String dato="";
    	int i=0;
		while(itValor.hasNext()){
    		dato=(String)itValor.next();
    		vecDat.add(i,dato);
    		i++;
		}
		return vecDat;
	}	
	
	public static Vector getArregloConSep(String svalor, char separador)
	{
		String valor = svalor;
		Vector vecDat = new Vector();
		StringBuffer strDat = new StringBuffer ("");
		int i;
		int j;

		for(i=0,j=0;i<valor.length();i++)
		{
			if(valor.charAt(i) != separador)
			{	
				strDat.append(valor.charAt(i));
			}
			else
			{
				vecDat.insertElementAt(strDat.toString(),j);
				strDat = new StringBuffer ("");
				j++;
			}
		}		
		
		return vecDat;
	}	
	
	public static String formatoMiles(String monto2)
	{
		String montoNuevo="";
		String monto = "";
		StringBuffer strCom = new StringBuffer ("");
		StringBuffer strCom2 = new StringBuffer ("");

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);	
        nf.setMinimumFractionDigits(2);
        
		int i=0;
		if(monto2 != null && !monto2.equals("")){
			String montoS = monto2.trim();
			for(i=0;i<montoS.length();i++)
			{
					if( montoS.charAt(i) == ',' )
					{	
						strCom.append(".");
					}
					else
					{
						strCom.append(montoS.trim().charAt(i));
					}
			}
			monto = strCom.toString();
			
			if(monto != null && !monto.equals("")){
				Double z =new Double(monto.trim());
				double valorDoble = z.doubleValue();	
				montoNuevo=nf.format(valorDoble);
			}

			for(i=0;i<montoNuevo.length();i++)
			{
					if( montoNuevo.charAt(i) == ',' )
					{	
						strCom2.append(".");
					}
					else
					{
						if( montoNuevo.charAt(i) == '.' )
						{	
							strCom2.append(",");
						}
						else
						{
							strCom2.append(montoNuevo.trim().charAt(i));
						}
					}
			}
			monto = strCom2.toString();

		}

		return monto;	
	}
	public static String getFechaNowYYYYMMDD(String Separador)
	{
        String fecha = new String();
        Date fechaActual= new Date();
        
        String dia = Integer.toString(fechaActual.getDate());
        String mes = Integer.toString(fechaActual.getMonth() + 1);
        String ano = Integer.toString(fechaActual.getYear() + 1900);
        if(dia.length()<2) dia = "0" + dia; 
        if(mes.length()<2) mes = "0" + mes; 
         
        fecha= ano + Separador + mes + Separador + dia;
        
		return fecha;
	}
	
//	public static String getTipoCustodisDes(String id){
//		if (id.equals("1")){
//			return "Pagare Protestado";
//		}
//		return "";
//	}	
	
	public static Integer getInteger(String cadena){
		if (cadena==null)
			return new Integer(0);		
		else
			return new Integer(cadena.trim());
	}
	public static String concatFono(String fono1,String fono2,String fono3){
		String fono=fono1;
		if(!fono2.equals("0")){
			fono= fono + "," + fono2;
		}
		if(!fono3.equals("0")){
			fono= fono + "," + fono3;
		}
		
		return fono;
	}
	
	public static String validaCampoFecha(String valor){
		if (valor==null || valor.equals("0001-01-01") || valor.equals("01-01-0001")|| valor.equals("1-01-01"))
			return "";		
		else
			return valor;
	}
	public static String validaCampo(String valor){
		if (valor==null)
			return "";		
		else
			return valor.trim();
	}	
}
