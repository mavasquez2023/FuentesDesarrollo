package cl.araucana.wscreditosocial.util;

import cl.araucana.core.util.Rut;

public class UtilXML {
	public static String generarCabecera(){
		 //return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>";
		 return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
	}
	
	public static String abrirCdata(){
		return "<![CDATA[";
	}
	
	public static String cerrarCdata(){
		return "]]>";
	}
	
	public static String generarTag(String nombre, String atributos, String valor){	
		return "<"+nombre+" "+atributos+">"+valor+"</"+nombre+">";
	}
	public static String generarError(String codigo, String descripcion){	
		return "<ERRORES><CODIGO>"+ codigo + "</CODIGO><DESC>" +  descripcion + "</DESC></ERRORES>";
	}

	
}
