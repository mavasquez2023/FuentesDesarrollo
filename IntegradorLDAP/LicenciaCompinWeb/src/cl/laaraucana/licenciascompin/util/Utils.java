package cl.laaraucana.licenciascompin.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.licenciascompin.entities.RegistroLicencias;

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

	public static String getbodyEjec(String folio, String rut, String ruta, String nReg, String telefono, String email) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");
		str.append("                                                                                                ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimad@, se ha recepcionado licencia folio&nbsp;" + folio + " de&nbsp;");
		str.append("Afiliado " + rut  + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Datos de contacto ingresado por el afiliado:<br>");
		str.append(telefono +"<br>" + email + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;'>");
		str.append("Registro con identificador &nbsp;interno");
		str.append(":&nbsp;" + nReg + "</span></p>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Ruta carpeta para extraer los&nbsp;");
		str.append("documentos:&nbsp;" + ruta + "</span></p>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");
		str.append("                                                                                                                          ");
		str.append("</div>");
		str.append("                                                                                                                          ");
		str.append("</body>");

		return str.toString();
	}
	
	public static String getbodyEjecDocP(String folio, String rut, String ruta, String nReg, String telefono, String email, String archivo) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");
		str.append("                                                                                                ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimad@, se ha recepcionado documentación pendiente licencia folio&nbsp;" + folio + " de&nbsp;");
		str.append("Afiliado " + rut  + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Nombre archivo: " + archivo);
		str.append("</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Datos de contacto ingresado por el afiliado:<br>");
		str.append(telefono +"<br>" + email + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;'>");
		str.append("Registro con identificador &nbsp;interno");
		str.append(":&nbsp;" + nReg + "</span></p>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Ruta carpeta para extraer los&nbsp;");
		str.append("documentos:&nbsp;" + ruta + "</span></p>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");
		str.append("                                                                                                                          ");
		str.append("</div>");
		str.append("                                                                                                                          ");
		str.append("</body>");

		return str.toString();
	}
	
	public static String bodyClient() {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL link=blue vlink=\"#954F72\">"); 
		str.append("                                                                                                                          ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Te informamos que hemos recibido tu solicitud para iniciar la gestión de licencia médica.");
		str.append("&nbsp;En caso de requerir más antecedentes te contactaremos.</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Si tienes dudas, ingresa a tu cuenta en servicio en línea en&nbsp;<a");
		str.append(" href=\"http://www.laaraucana.cl\" target=\"_blank\"><span style='color:#1155CC'>www.laaraucana.cl</span></a>&nbsp;o");
		str.append("&nbsp;llama al 600 4228 100.</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Atentamente,</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");
		str.append("                                                                                                                          ");
		str.append("</div>");
		str.append("                                                                                                                          ");
		str.append("</body>");

		return str.toString();
	}

	public static Map<String, Object> hoja1(RegistroLicencias vo, String rut){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		
		param_map.put("texto", texto(vo.getNombre(), rut, vo.getFolioLicencia()));
		param_map.put("fecha", sdf.format(new Date()));
		
		return param_map;
	}
	
	public static String getExtencion(String nombre) {

		int start = nombre.lastIndexOf(".");
		int end = nombre.length();

		String ext = nombre.substring(start + 1, end);

		return ext;

	}
	
	public static String texto(String nombre, String rut, String folio) {
		
		StringBuilder str = new StringBuilder();
		
		str.append("Yo " + nombre + " RUT " + rut + " autorizo expresamente a La Araucana C.C.A.F., a acceder a todos los datos contenidos en la licencia médica folio N° " + folio + ", incluyendo la información del diagnóstico, a fin que sea tramitada por la citada entidad, de conformidad a la Ley N° 19.628 sobre Protección de la Vida Privada.");
		
		return str.toString();
	}
}
