package cl.araucana.tgr.util;

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
	public static String[] getApellidosSeparados(String apellidos){			
			String exclusiones=";DE;DEL;DA;EL;LA;LOS;LAS;SAN;VON;DI;";
			String aux = "";
			String[] user = apellidos.split(" ");
			int numsep=0;
			for(int i=0;i<user.length; i++){
				if(!user[i].equals("")){
					if(exclusiones.indexOf(";"+user[i].toUpperCase()+";")==-1 && numsep<1){
						aux = aux + user[i]+";";
						numsep++;
					}else{
						aux = aux + user[i]+" ";
					}
				}
			}
			//paso1 = ";" + paso1.trim() + ";";
			String[] apepatymat = aux.split(";");
			return apepatymat;
	}
	public static boolean isRutValido(int rut, char dv){
		Rut rutemp= new Rut(rut);
		if(dv!=rutemp.getDV()){
			return false;
		}
		return true;
	}
	public static boolean isPeriodoValido(String anio, String mes){
		try {
			int periodo= Integer.parseInt(anio+mes);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
}
