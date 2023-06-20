package cl.laaraucana.envioFormularioASFAM.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Utils {

	@SuppressWarnings("resource")
	public static void descargar(String path, String filename, byte[] bytes) throws IOException, FileNotFoundException {
		// Initialize a pointer
		// in file using OutputStream
		OutputStream os;

		os = new FileOutputStream(path + "\\" + filename);

		// Starts writing the bytes in it
		os.write(bytes);
		os.flush();
		os.close();

	}

	public static String getbodyEjec(String rut, String ruta, String reg, String telefono, String email, List<String> listaArchivos) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL link=blue vlink=\"#954F72\">");
		str.append("                                                                                           ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                           ");
		str.append("<p class=MsoNormal style='background:white'><span lang=ES style='font-family:");
		str.append("\"Arial\",sans-serif;color:#222222'>Estimad@, se ha recepcionado una solicitud de");
		str.append("&nbsp;tramitación de asignación familiar del afiliado&nbsp;" + rut + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Datos de contacto ingresado por el afiliado:<br>");
		str.append(telefono +"<br>" + email + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='background:white'><span lang=ES style='font-family:");
		str.append("\"Arial\",sans-serif;color:#222222'>Registro con identificador");
		str.append("&nbsp;interno de la bitácora :&nbsp;" + reg + "</span></p>");
		str.append("                                                                                           ");
		str.append("<p class=MsoNormal><span lang=ES style='font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Ruta carpeta para extraer los documentos:&nbsp;</span>");
		
		str.append("<span style='font-family:\"Arial\",sans-serif'>" + ruta +"</span>");
	    str.append("</span></p>");
		str.append("                                                                                           ");
		str.append("<p class=MsoNormal><span lang=ES style='font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Archivos subidos:&nbsp;<br>");
		for (Iterator iterator = listaArchivos.iterator(); iterator.hasNext();) {
			String archivo = (String) iterator.next();
			str.append( archivo  + "<br>");
		   
		}
		 str.append("</span></p>");
	    str.append("                                                                                           ");  
		str.append("<p class=MsoNormal><span class=MsoHyperlink><span lang=ES style='font-family:");
		str.append("\"Arial\",sans-serif'><span style='text-decoration:none'>&nbsp;</span></span></span></p>");
		str.append("                                                                                           ");
		str.append("<p class=MsoNormal><span class=MsoHyperlink><span lang=ES style='font-family:");
		str.append("\"Arial\",sans-serif'><img width=642 height=72 src=\"cid:image\"/></span></span></p>");
		str.append("                                                                                           ");
		str.append("</div>");
		str.append("                                                                                           ");
		str.append("</body>");

		return str.toString();
	}

	public static String bodyClient() {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL link=blue vlink=\"#954F72\">"); 
		str.append("                                                                                                   ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal>Hemos recibido tu solicitud y te contactaremos en caso de");
		str.append("&nbsp;requerir antecedentes.</p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal>Si tienes dudas, ingresa a tu cuenta en nuestro sitio&nbsp;<span");
		str.append("lang=ES><a href=\"http://www.laaraucana.cl\"><span lang=ES-CL>www.laaraucana.cl</span></a></span>");
		str.append("&nbsp;o llámanos al 600 4228 100. Desde celulares *8100</p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal>Estamos Contigo, La Araucana</p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal><img width=642 height=72 src=\"cid:image\"/></p>");
		str.append("                                                                                                   ");
		str.append("<p class=MsoNormal><span lang=ES>&nbsp;</span></p>");
		str.append("                                                                                                   ");
		str.append("</div>");
		str.append("                                                                                                   ");
		str.append("</body>");

		return str.toString();
	}

	public static Map<String, Object> hoja1(Object vo, String rut){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		
	//	param_map.put("texto", texto(vo.getNombre(), rut, vo.getFolioLicencia()));
		param_map.put("fecha", sdf.format(new Date()));
		
		return param_map;
	}
	
	public static String getExtencion(String nombre) {

		int start = nombre.lastIndexOf(".");
		int end = nombre.length();

		String ext = nombre.substring(start + 1, end);

		return ext;

	}
	
}
