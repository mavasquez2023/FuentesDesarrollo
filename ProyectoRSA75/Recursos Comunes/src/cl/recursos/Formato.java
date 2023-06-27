/*
 * Creado el 17-07-2006
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.recursos;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cl.araucana.core.util.AbsoluteDate;


/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class Formato {
	private static Locale locale= new Locale("es", "CL");
	private static String _patron= "[^A-Za-z _.-]";
//formatea <value> con cantidad de <decimales> en formato <locale>
public static String numDecimal(double value, int decimales) {
	StringBuffer patron = new StringBuffer();
	patron.append("#,##0.");
	for (int i = 0; i < decimales; i++)
		patron.append('0');

	NumberFormat nf = NumberFormat.getNumberInstance(locale);
	DecimalFormat df = (DecimalFormat)nf;
	df.applyPattern(patron.toString());
	return df.format(value);
}
//formatea número en miles en formato <locale>
public static String numEntero(double value) {

	NumberFormat nf = NumberFormat.getNumberInstance(locale);
	DecimalFormat df = (DecimalFormat)nf;
	df.applyPattern("#,##0");
	return df.format(value);
}
//formatea un número en $
public static String enPesos(double value) {

	NumberFormat nf = NumberFormat.getNumberInstance(locale);
	DecimalFormat df = (DecimalFormat)nf;
	df.applyPattern("$#,##0");
	return df.format(value);
}

//Rellena un texto con espacios a la derecha hasta completar <largo>
public static String padding(String text, int largo) {
	return padding(text, largo, ' ', 'D');
}
//Rellena un texto con caracteres a la derecha hasta completar <largo>
public static String paddingRigth(String text, int largo, char character) {
	return padding(text, largo, character, 'D');
}
//Rellena un texto con caracteres a la izquierda hasta completar <largo>
public static String paddingLeft(String text, int largo, char character) {
	return padding(text, largo, character, 'I');
}
//Rellena un numero con ceros a la izquierda hasta completar <largo>
public static String padding(long value, int largo) {
	String valueOf= String.valueOf(value);
	return padding(valueOf, largo, '0', 'I');
}

//Rellena un texto con caracteres a la derecha ('D') o izquierda ('I') hasta completar <largo>
private static String padding(String text, int largo, char character, char ubicacion) {
	String caracter= String.valueOf(character);
	String relleno= "";
	
	//se trunca el string en caso de venir mas largo que lo solicitado
	text=truncateText(text, largo);
	//Se generar los espacios necesarios
	for (int i=1;i<=largo - text.length();i++){
		relleno= relleno.concat(caracter);
	}
	if(ubicacion=='D'){
		//se concatena los caracteres a la derecha del string
		return text.concat(relleno);
	}else{
		return relleno.concat(text);
	}
}

//trunca un string <text> hasta <largo>
public static String truncateText(String text, int length){

	if(text != null && text.length() > length){
		text = text.substring(0,length);
	}
	return text;

}
//trunca un numero <number>  a la izquierda manteniendo <largo>
public static String truncateNumber(String number, int largo){

	if(number != null && number.length() > largo){
		number = number.substring(number.length()- largo);
	}
	return number;

}

public static String matchReplace(String entrada){
	Pattern patron = Pattern.compile(_patron);
	Matcher encaja = patron.matcher(entrada);
	if(!encaja.find()){
		return entrada;
	}else{
		String salida= encaja.replaceAll("");
		return salida;
	}
}

public static String cambiarCaracteres( String texto) {
			if (texto==null || texto.equals("")){
				return "";
			}
			// vocales con acentos varios
			texto = texto.replace('á','a');
			texto = texto.replace('é','e');
			texto = texto.replace('í','i');
			texto = texto.replace('ó','o');
			texto = texto.replace('ú','u');

			texto = texto.replace('à','a');
			texto = texto.replace('è','e');
			texto = texto.replace('ì','i');
			texto = texto.replace('ò','o');
			texto = texto.replace('ù','u');

			texto = texto.replace('â','a');
			texto = texto.replace('ê','e');
			texto = texto.replace('î','i');
			texto = texto.replace('ô','o');
			texto = texto.replace('û','u');

			texto = texto.replace('ä','a');
			texto = texto.replace('ë','e');
			texto = texto.replace('ï','i');
			texto = texto.replace('ö','o');
			texto = texto.replace('ü','u');

			texto = texto.replace('Á','A');
			texto = texto.replace('É','E');
			texto = texto.replace('Í','I');
			texto = texto.replace('Ó','O');
			texto = texto.replace('Ú','U');

			texto = texto.replace('À','A');
			texto = texto.replace('È','E');
			texto = texto.replace('Ì','I');
			texto = texto.replace('Ò','O');
			texto = texto.replace('Ù','U');

			texto = texto.replace('Ä','A');
			texto = texto.replace('Ë','E');
			texto = texto.replace('Ï','I');
			texto = texto.replace('Ö','O');
			texto = texto.replace('Ü','U');

			texto = texto.replace('Â','A');
			texto = texto.replace('Ê','E');
			texto = texto.replace('Î','I');
			texto = texto.replace('Ô','O');
			texto = texto.replace('Û','U');

			texto = texto.replace('ñ','n');
			texto = texto.replace('Ñ','N');

			texto= matchReplace(texto);

			return texto;
}

public static int cambiaStr2int( String texto) {
	Double d1 = Double.valueOf(texto.trim());
	return d1.intValue();
	//return Integer.parseInt(texto.trim());
}

public static double cambiaStr2double( String texto) {
	Double d1=Double.valueOf(texto.trim());
	return d1.doubleValue();
}

public static long cambiaStr2long( String texto) {
	//Long l1 = Long.valueOf(texto.trim());
	Double d1 = Double.valueOf(texto);
	return d1.longValue();
}

public static String[] split(String s, String delimiter) {
	List tokens = new ArrayList();
	int fromIndex = 0;
	int index;
	int length = s.length();
	int delimiterLength = delimiter.length();

	while (fromIndex < length) {
		index = s.indexOf(delimiter, fromIndex);
		if (index < 0) {
			tokens.add(s.substring(fromIndex));
			break;
		}
		tokens.add(s.substring(fromIndex, index).trim());
		fromIndex = index + delimiterLength;
	}
	return (String[]) tokens.toArray(new String[0]);
}

public static void main(String [] args){
	/*String value= "958763";
	String formato= Formato.padding(value, 9, '0', 'I');
	System.out.println("." + formato  + ".");
	formato= Formato.padding(value, 9, '0', 'D');
	System.out.println("." + formato  + ".");
	AbsoluteDate fecha= new AbsoluteDate();
	System.out.println("Fecha=" + fecha.getYear() + "" + Formato.paddingLeft(String.valueOf(fecha.getMonth()), 2, '0') + "" + Formato.paddingLeft(String.valueOf(fecha.getDay()), 2, '0'));
	*/
	//String numero="006,40";
	//System.out.println("numero entrada: " + numero);
	//System.out.println("numero saluda:" + numeric7coma2(numero, 7));
	
	String texto="aasáñyÉ asóÑsa_as 23ewe";
	texto= cambiarCaracteres(texto);
	System.out.println(matchReplace(texto));
}

public static String numeric7coma2(String numero, int largoFijo) {
	String resultado="";
	int numdecimales= 2;
	//Quita el .
	String numeroNew = numero.replaceAll("\\.", "");
	//numeroNew = numeroNew.replaceAll("x", "");
	String aux = numeroNew.replace(',', '.');
	Double.parseDouble(aux);
	int pos = numeroNew.indexOf(',');
	if(pos>0){
		String parteint= numeroNew.substring(0, pos);
		parteint= formatLargoFijo(parteint, "0", largoFijo-numdecimales );
		String partedec= numeroNew.substring(pos+1);
		partedec= formatLargoDec(partedec, "0", numdecimales );
		resultado= parteint + partedec;
	}else{
		resultado= formatLargoFijo(numeroNew, "0", largoFijo-numdecimales ) + "00";
	}
	
	return resultado;
}

/**
 * Devuelve un String con el largo indicado en el parametro, donde el dato queda a
 * la derecha del String y se rellena a la izquierda con el character pasado en el parametro
 * Ejemplo:
 * 			"001" = formatPattern(1, "0", 3);
 * @param dato
 * @param character
 * @param largo
 * @return String con el dato formateado en largo fijo y relleno a la izquierda con el character
 */
public static String formatLargoFijo(String dato, String character, int largo){

	String pattern = "";
	for(int x=0;x<largo;x++)
		pattern = pattern + character;
	
	if(dato==null) dato = "";
	
	String resultado = pattern + dato;
	
	return resultado.substring(dato.length());
	
}
public static String formatLargoDec(String dato, String character, int largo){

	String pattern = "";
	for(int x=0;x<largo;x++)
		pattern = pattern + character;
	
	if(dato==null) dato = "";
	
	String resultado = dato + pattern ;
	
	return resultado.substring(0, largo);
	
}

}
