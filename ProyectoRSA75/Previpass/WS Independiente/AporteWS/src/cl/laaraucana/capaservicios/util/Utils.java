package cl.laaraucana.capaservicios.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("unused")
public class Utils {
  private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
  private static DateFormat formatoWeb = new SimpleDateFormat("dd-MM-yyyy");
  // private static DateFormat formatoWeb2 = new SimpleDateFormat("dd/MM/yyyy");
  private static DateFormat horas = new SimpleDateFormat("HH:mm:ss");
  private static DateFormat horasAS400 = new SimpleDateFormat("HHmmss");
  private static DateFormat formatoSAPFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static DateFormat formatoWebFull = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
  private static SimpleDateFormat fechaCompleta = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
  private static SimpleDateFormat fechaSAP = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("ES"));
  private static SimpleDateFormat fechaPrepago1 = new SimpleDateFormat("MMMM' del 'yyyy", new Locale("ES"));
  private static DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
  private static DateFormat formatoAs400Periodo = new SimpleDateFormat("yyyyMM");
  private static DateFormat formatoAs400Full = new SimpleDateFormat("yyyyMMdd HHmmss");

  private static DecimalFormat formateador = new DecimalFormat("###,###");
  private static DecimalFormat formateador2 = new DecimalFormat("###,###.#####");

  public static String pasaFechaSAPaWEB(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoSAP.parse(date);
    formatStr = formatoWeb.format(parsedUtilDate);
    return formatStr;
  }
  
  /**
   * Convierte fecha a formato SAP 'yyyy-MM-dd', si el valor de entrada es nulo, retona el string enviado por parametro
   * @param date
   * @param nulo
   * @return
   * @throws ParseException
   */
  public static String pasaFechaSAPaWEBNulo(String date, String nulo) throws ParseException {
	  if(date == null) return nulo;
	    java.util.Date parsedUtilDate;
	    String formatStr = "";
	    parsedUtilDate = formatoSAP.parse(date);
	    formatStr = formatoWeb.format(parsedUtilDate);
	    return formatStr;
	  }

  /**
   * Convierte fecha de tipo java.util.Date a fecha web (String)
   * @param date
   * @return
   */
  public static String pasaDateToFechaWeb(Date date){
	  if(date==null) return null;
	  String formatStr = "";
	  formatStr = formatoWeb.format(date);
	  return formatStr;
  }
  
  public static String pasaDB2DateToFechaWeb(String date){
	  if(date==null) return null;
	  String formatStr = "";
	  formatStr = formatoWeb.format(date);
	  return formatStr;
  }
 
  /**
   * Formato fecha as400 "20090612" a formato Web "12-06-2009"
   * */
  public static String pasaFechaASaWEB(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoAs400.parse(date);
    formatStr = formatoWeb.format(parsedUtilDate);
    return formatStr;
  }
  public static String pasaWebToAs400(String fecha) throws ParseException{
	  Date parsedUtilDate;
	  parsedUtilDate = formatoWeb.parse(fecha);
	  String formatStr = formatoAs400.format(parsedUtilDate);
	  return formatStr;
  }

  public static String dateToString(Date fecha) {
	  return formatoWeb.format(fecha);
  }
  
  /*
   * public static String dateToString2(Date fecha){ return
   * formatoWeb2.format(fecha); }
   */
  public static Date stringToDate(String fecha) throws ParseException {
	  if(fecha == null) return null;
	  return formatoWeb.parse(fecha);
  }
  public static String pasaFechaWEBaSAP(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoWeb.parse(date);
    formatStr = formatoSAP.format(parsedUtilDate);
    return formatStr;
  }

  public static String dateToStringFull(Date fecha) {
    return formatoWebFull.format(fecha);
  }

  public static Date stringToDateFull(String fecha) throws ParseException {
    return formatoWebFull.parse(fecha);
  }

  public static Date sumDays(Date fecha, int sum) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.DATE, sum);
    return cal.getTime();
  }

  public static Date sumMonths(Date fecha, int sum) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.MONTH, sum);
    return cal.getTime();
  }

  public static Date sumHours(Date fecha, int sum) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.HOUR, sum);
    return cal.getTime();
  }

  public static Date sumSeconds(Date fecha, int sum) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);
    cal.add(Calendar.SECOND, sum);
    return cal.getTime();
  }

  public static String getFechaCompleta() {
    return fechaCompleta.format(new Date());
  }

  public static String fechaSAP() {
    return fechaSAP.format(new Date());
  }

  public static String getFechaPrepago1() {
    return fechaPrepago1.format(new Date());
  }

  /*
   * public static String parseFechaAs400(String fecha){ //formato fecha as400
   * 20090612 try { Date date = formatoAs400.parse(fecha); return
   * formatoWeb2.format(date); } catch (ParseException e) { return fecha;
   * //fecha tiene otro formato, retornar la original } }
   */

  /**
   * Utilizado para Certificado de Prepago y Finiquito. Formato fecha Web
   * "12-06-2009" a formato as400 "20090612"
   * */
  public static String pasaFechaWEBaAs400(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoWeb.parse(date);
    formatStr = formatoAs400.format(parsedUtilDate);
    return formatStr;
  }

  /**
   * Utilizado para Certificado de Prepago y Finiquito. Toma la fecha actual y
   * cambia a formato Año Mes de para As400 "yyyyMM"
   * */
  public static String getFechaPeriodo() {
    return formatoAs400Periodo.format(new Date());
  }

  /**
   * 
   * @param date
   * @return retorna fecha en formato yyyyMM enviandole el formato dd-MM-yyyy
   * @throws ParseException
   */
  public static String getFechaWEBaPeriodo(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoWeb.parse(date);
    formatStr = formatoAs400Periodo.format(parsedUtilDate);
    return formatStr;
  }

  /**
   * 
   * @param date
   * @return retorna el perido yyyyMM desde una fecha pasada en formato
   *         yyyy-MM-dd
   * @throws ParseException
   */

  public static String getFechaSapaPeriodo(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = fechaSAP.parse(date);
    formatStr = formatoAs400Periodo.format(parsedUtilDate);
    return formatStr;
  }

  /**
   * Se realizaron cambios en simulacion debido a que no venian separadores de
   * miles y los decimales con punto
   * 
   * @param str
   * @return
   */
  public static Double stringToDouble(String str) {
    String parametro = str;
    Double numero = 0.0;

    if (parametro != null) {
      parametro = parametro.trim();
      // parametro = parametro.replace(".", "").replace(",", ".");
      numero = new Double(parametro);
    }

    return numero;
  }

  /**
   * Devuelve fecha actual en formatoAs400 "yyyyMMdd"
   * */
  public static String getFechaHoyAs400() {
    return formatoAs400.format(new Date());
  }

  public static String getHoraAs400() {
    return horasAS400.format(new Date());
  }

  public static Date stringToHorasAS400(String hora) throws ParseException {
    return horasAS400.parse(hora);
  }

  public static Date stringToFullAs400(String fecha) throws ParseException {
	  formatoAs400Full.setTimeZone(TimeZone.getTimeZone("Chile/Continental"));
    return formatoAs400Full.parse(fecha);
  }

  /**
   * Toma una fecha en formato SAP "yyyy-MM-dd" y la devuelve en formatoAs400
   * "yyyyMMdd"
   * */
  public static String pasaFechaSAPaAs400(String date) throws ParseException {
    java.util.Date parsedUtilDate;
    String formatStr = "";
    parsedUtilDate = formatoSAP.parse(date);
    formatStr = formatoAs400.format(parsedUtilDate);
    return formatStr;
  }

  public static String formatearFolio(String entrada) {

    if (entrada.length() > 3) {
      StringBuffer folio = new StringBuffer(entrada);
      folio.insert(3, "-");
      entrada = folio.toString();
    }

    return entrada;
  }

  /**
   * Obtiene el valor anterior de algun String cortado por algun parametro
   * enviado
   * */
  public static String obtenerValorAnteriorA(String entrada, String corte) {
    String salida = entrada;
    if (entrada.contains(corte)) {
      String[] array = entrada.split(corte);
      if (salida.length() >= 1) {
        salida = array[0];
      }
    }
    return salida;
  }

  public static String obtenerValorSiguienteA(String entrada, String corte) {
    String salida = entrada;
    if (entrada.contains(corte)) {
      String[] array = entrada.split(corte);
      if (salida.length() >= 2) {
        salida = array[1];
      }
    }
    return salida;
  }

  /**
   * Pasa un tipo doble a String, con divisor de "." para milies y sin
   * decimales. Ej.: doble: "9903836.0" a String: "9.903.836"
   */
  public static String formateaDobleSinDecimal(double doble) {
    String total;
    total = formateador.format(doble);
    return total;
  }
  
  /**
   * Pasa un tipo doble a String, con divisor de "." para milies y sin
   * decimales. Ej.: doble: "9903836.0" a String: "9.903.836"
   */
  public static String formateaIntSinDecimal(long nro) {
    String total;
    total = formateador.format(nro);
    return total;
  }

  /**
   * Pasa un tipo doble a String, con divisor de "." para milies y sin
   * decimales. Ej.: doble: "9903836.56" a String: "9.903.836,56"
   */
  public static String formateaDobleConDecimal(double doble) {
    String total;
    total = formateador2.format(doble);
    return total;
  }

  /**
   * retorna la fecha de hoy en formato yyyy-MM-dd
   * 
   * @return
   */
  public static String getFechaHoySAP() {
    return formatoSAP.format(new Date());
  }

  /**
   * retorna la fecha en formato yyyy-MM-dd sumandole la cantidad de meses y el
   * ultimo dia del mes
   * 
   * @param meses
   * @return
   */
  public static String getFechaHoySAPMasMeses(int meses) {
    Calendar cal = new GregorianCalendar();
    cal.add(Calendar.MONTH, +meses);
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    Date anterior = cal.getTime();
    return formatoSAP.format(anterior);
  }

  public static String rellenarCampos(String entrada, String nombre, int largo, String caracter) throws RuntimeException {
    String espacios = "";

    if (entrada == null) {
      throw new RuntimeException("Campo '" + nombre + "' no inicializado");
    } else if (entrada.length() > largo) {
      throw new RuntimeException("Campo " + nombre + " es mayor de " + largo + " caracteres. (" + entrada.length() + ")");
    } else {

      if (entrada.length() < largo) {
        for (int i = 0; i < (largo - entrada.length()); i++) {
          espacios += caracter;
        }
        if (caracter.equals("0"))
          return espacios + entrada;
        else
          return entrada + espacios;
      }
    }
    return entrada;
  }

  public static String rellenarCampos(String entrada, int largo, String caracter) throws Exception {
    String espacios = "";
    if (entrada.length() <= largo) {
      for (int i = 0; i < (largo - entrada.length()); i++) {
        espacios += caracter;
      }
      if (caracter.equals("0"))
        return espacios + entrada;
      else
        return entrada + espacios;
    } else {
      throw new Exception("Error al rellenar campo. se excede el largo maximo");
    }
  }
  
  /**
   * Elimina nros decimales
   * @param nro: El nro de entrada no debe tener separador de miles y utilizar punto (.) para separar decimales
   * Si el parametro es nulo, retorna 0
   * @return parte entera del numero
   */
  public static String quitarDecimalesString(String nro){
	  if(nro==null)return "0";
	  
	  String salida = nro;
	  int pos = nro.indexOf(".");
	  if(pos != -1){
		  salida = nro.substring(0, pos);
	  }
	  
	  return salida;
  }
  
  /**
   * Quita ceros a la izquierda de un String, NO valida que el string sea nu número.
   * @param nro
   * @return
   */
  public static String quitarCerosIzq(String nro){
	  return nro.replaceFirst("0*", "");
  }
  
  private static long stringToLong(String nro){
	  nro = nro.replace(".","").replace(",",".") ;
	  return Long.parseLong(nro); 
  }
  
  /**
   * Formatea frases convirtiendo las iniciales de las palabras a mayúscula
   * @param init
   * @return
   */
  public static String toCamelCase(String init) {
	    if (init==null || init.trim().isEmpty())
	        return " ";

	    StringBuilder ret = new StringBuilder(init.length());

	    for (String word : init.split(" ")) {
	        if (!word.isEmpty()) {
	            ret.append(word.substring(0, 1).toUpperCase());
	            ret.append(word.substring(1).toLowerCase());
	        }
	        if (!(ret.length()==init.length()))
	            ret.append(" ");
	    }

	    return ret.toString();
	}
  
  public static String formatearNro(long nro){
	  String n = String.valueOf(nro);
	  String salida = "";
	  int largo = n.length();
	  long cant = (largo/3)+1;
	  for(int i =0;i<cant;i++){
		  if(largo-3<0){
			  return n.substring(0,(largo % 3)) + salida;
		  }
		  else if(largo-3==0){
			  return salida= n.substring(0,largo)+ salida;
		  }else{
			  salida = "." + n.substring(largo - 3,largo) + salida;
		  }
		  largo-=3;
	  }
	return salida;
  }
  
  /**
   * Valida si la variable es nula, caso en que retona cero ('0')
   * @param var
   * @return
   */
  public static String reemplazaNuloPorCero(String var){
	  if(var == null) return "0";
	  else return var;
  }
  
  /**
   * Valida si la variable es nula, caso en que retona espacio (' ')
   * @param var
   * @return
   */
  public static String reemplazaNuloPorEspacio(String var){
	  if(var == null) return " ";
	  else return var;
  }
}
