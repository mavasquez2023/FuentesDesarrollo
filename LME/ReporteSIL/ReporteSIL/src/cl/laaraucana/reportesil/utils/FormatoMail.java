package cl.laaraucana.reportesil.utils;

public class FormatoMail {
	
	public static String obtenerTextoMailCliente(String nombre, String fechaDesde){
		String html = Configuraciones.getConfig("mail.formato.reportesil");
		html = html.replaceAll("#nombre#", nombre);
		html = html.replaceAll("#fecha_desde#", fechaDesde);
		
		return html;
	}

}
