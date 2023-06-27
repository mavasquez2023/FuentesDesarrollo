package cl.laaraucana.integracion.util;

public class UtilXML {
	public static String generarCabecera(){
		 return "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>";
		
	}
	
	public static String abrirCdata(){
		return "<![CDATA[";
	}
	
	public static String cerrarCdata(){
		return "]]>";
	}
	
	public static String abrirTag(String nombre){
		return "<"+nombre+">";
	}
	public static String abrirTag(String nombre,String atributos){
		return "<"+nombre+" "+atributos+">";
	}
	public static String cerrarTag(String nombre){
		return "</"+nombre+">";
	}
	public static String generarTag(String nombre, String atributos, String valor){	
		return "<"+nombre+" "+atributos+">"+valor+"</"+nombre+">";
	}
	public static String generarTag2(String nombre, String atributos){	
		return "<"+nombre+" "+atributos+"/>";
	}
	
}
