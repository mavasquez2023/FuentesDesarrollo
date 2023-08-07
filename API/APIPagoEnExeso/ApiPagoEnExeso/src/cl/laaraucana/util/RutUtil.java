package cl.laaraucana.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RutUtil {

  public static boolean IsRutValido(String rutEntrada) {
	 if(rutEntrada == null) return false;
	 System.out.println("Rut entrada 1 : " + rutEntrada);
    // rutEntrada=rutEntrada.replace(".", "").toUpperCase();
    rutEntrada = rutEntrada.replaceAll("\\.", "");
    System.out.println("Rut entrada 2 : " + rutEntrada);
    rutEntrada = rutEntrada.toUpperCase();
    System.out.println("Rut entrada 3 : " + rutEntrada);
    System.out.println(" VALOR RUT : " + PatronRut(rutEntrada)  + " --- "  +  rutEntrada.length());
    if (PatronRut(rutEntrada) && rutEntrada.length() <= 10) {
      String[] soloRut = rutEntrada.split("-");
      System.out.println("Rut entrada 3 : " + soloRut);
      int rut = Integer.parseInt(soloRut[0]);
      char dv = soloRut[1].charAt(0);
      System.out.println("Rut entrada 4 : " + ValidarRut(rut, dv));
      return ValidarRut(rut, dv);
      
    }
    return false;
  }

  private static boolean ValidarRut(int rut, char dv) {
    int m = 0, s = 1;
    for (; rut != 0; rut /= 10) {
      s = (s + rut % 10 * (9 - m++ % 6)) % 11;
    }
    return dv == (char) (s != 0 ? s + 47 : 75);
  }

  private static boolean PatronRut(String rutEntrada) {

    Pattern patron = Pattern.compile("\\b\\d{1,8}\\-[K|0-9]");
    Matcher encaja = patron.matcher(rutEntrada);
    if (encaja.find())
      return true;
    else
      return false;
  }

  public static char obtenerDigitoVerificador(int rut) {
    int m = 0, s = 1;
    for (; rut != 0; rut /= 10) {
      s = (s + rut % 10 * (9 - m++ % 6)) % 11;
    }
    char dv = (char) (s != 0 ? s + 47 : 75);
    return dv;
  }

  /**
   * Recibe un rut con guión y dv y retorna el valor en Long
   * 
   * @param rut
   */
  public static long getLongRut(String rut) {
    long rutL = 0;
    try {
      rut = rut.replaceAll("[.|,]", "");
      rutL = Long.parseLong(rut.substring(0, rut.length() - 2));
    } catch (Exception e) {
      // TODO: handle exception
    }

    return rutL;
  }

  public static String getDv(String rut) {
    String dv = rut.substring(rut.length() - 1, rut.length());
    return dv.toUpperCase();
  }

  /**
   * Le da formato a un rut, concatenándole puntos y guión.
   * 
   * @param rut
   *          Rut a formatear.
   * @return Un nuevo String, con el rut formateado.
   */
  public static String formatearRut(String rut) {
    int cont = 0;
    String format;
    rut = rut.replace(".", "");
    rut = rut.replace("-", "");
    format = "-" + rut.substring(rut.length() - 1);
    for (int i = rut.length() - 2; i >= 0; i--) {
      format = rut.substring(i, i + 1) + format;
      cont++;
      if (cont == 3 && i != 0) {
        format = "." + format;
        cont = 0;
      }
    }
    return format;
  }

  public static String formatRutAs400(String rut) throws NullPointerException {
    if (IsRutValido(rut)) {
      return rut.split("-")[0];
    } else {
      throw new NullPointerException("Rut es invalido o no cumple el siguiente formato XXXXXXXX-X");
    }
  }
}
