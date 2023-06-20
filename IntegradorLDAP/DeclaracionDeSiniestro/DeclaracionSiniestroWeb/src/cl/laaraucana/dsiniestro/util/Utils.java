package cl.laaraucana.dsiniestro.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.dsiniestro.entities.RegistroDSiniestro;

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

	public static String getbodyEjec(String rut, String ruta, String nReg, String telefono, String email) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");
		str.append("                                                                                                ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimad@, se ha recepcionado a tramitación seguro de cesantía asociado a siniestro Ley protección empleo");
		str.append("</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Datos de contacto ingresado por el afiliado: RUT (" + rut + ")<br>");
		str.append("teléfono:" + telefono +"<br>");
		str.append("correo:" + email + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;'>");
		str.append("Registro con identificador interno en bitácora");
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
	
	public static String getbodyEjecDocP(String rut, String ruta, String nReg, String telefono, String email, String archivo) {

		StringBuilder str = new StringBuilder();
		
		str.append("<body lang=ES-CL>");
		str.append("                                                                                                ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimad@, se ha recepcionado documentación pendiente&nbsp;");
		str.append("para la tramitación seguro de cesantía asociado a siniestro Ley protección empleo</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Nombre archivo: " + archivo);
		str.append("</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Datos de contacto ingresado por el afiliado: RUT (" + rut + ") <br>");
		str.append("teléfono:" + telefono +"<br>");
		str.append("correo:" + email + "</span></p>");
		str.append("<br>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;'>");
		str.append("Registro con identificador interno en bitácora");
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
	
	public static String bodyClient(String documento) {

		StringBuilder str = new StringBuilder();
		str.append("<body lang=ES-CL link=blue vlink=blue>");

		str.append("<div class=WordSection1>");

		str.append("<p class=MsoNormal align=left style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:left;line-height:normal'><span style='position:relative;z-index:");
		str.append("1;left:0px;top:-20px;width:645px;height:72px'><img width=645 height=72 ");
		str.append("src=\"cid:image\" /></span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal align=left style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:left;text-autospace:none'><b><span style='font-size:14.0pt;");
		str.append("line-height:115%;font-family:\"MyriadPro-Black\",\"sans-serif\";color:#1F497D;");
		str.append("letter-spacing:1.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TRAMITACIÓN SINIESTRO LA ARAUCANA </span></b></p>");
		str.append("<br>");
		
		if(documento.equals("")){
			str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
			str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
			str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Hemos recibido tu solicitud y te contactaremos en caso de");
			str.append("&nbsp;requerir antecedentes. </span></p>");
			
		}else{
			str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
			str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
			str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Hemos recibido tu documento (" + documento + ") y te contactaremos en caso de");
			str.append("&nbsp;requerir antecedentes. </span></p>");
		}
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Si tienes dudas, favor comunícate al 22&nbsp;431&nbsp;4680");
		str.append("</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-align:justify;text-autospace:none'><b><span style='font-size:7.5pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#333333'>Aviso de Confidencialidad</span></b><span style='font-size:7.5pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#333333'>&nbsp;<br>");
		
		str.append("Este correo y la información contenida o adjunta al mismo es privada y ");
		str.append("confidencial y va dirigida exclusivamente a su destinatario. La Araucana ");
		str.append("informa a quien pueda haber recibido este correo por error que contiene ");
		str.append("información confidencial cuyo uso, copia, reproducción o distribución está ");
		str.append("expresamente prohibida. Si no es usted el destinatario del mismo y recibe este ");
		str.append("correo por error, le rogamos lo ponga en conocimiento del emisor y proceda a su ");
		str.append("eliminación sin copiarlo, imprimirlo o utilizarlo de ningún modo.&nbsp;</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt'><img border=0 width=642 height=72 ");
		str.append("src=\"cid:image2\" /></span></b></p>");

		str.append("</div>");

		str.append("</body>");
/*		
		str.append("<body lang=ES-CL link=blue vlink=\"#954F72\">"); 
		str.append("                                                                                                                          ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Hemos recibido tu solicitud y te contactaremos en caso de");
		str.append("&nbsp;requerir antecedentes.</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Si tienes dudas, ingresa a tu cuenta en nuestro sitio&nbsp;<a");
		str.append(" href=\"http://www.laaraucana.cl\" target=\"_blank\"><span style='color:#1155CC'>www.laaraucana.cl</span></a>&nbsp;o");
		str.append("&nbsp;llámanos al 600 4228 100.</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estamos Contigo, La Araucana</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>");
		str.append("                                                                                                                          ");
		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");
		str.append("                                                                                                                          ");
		str.append("</div>");
		str.append("                                                                                                                          ");
		str.append("</body>");
*/
		return str.toString();
	}

	public static Map<String, Object> hoja1(RegistroDSiniestro vo, String rut){
		
		Map<String, Object> param_map = new HashMap<String, Object>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		
		param_map.put("texto", texto(vo.getNombre(), rut, ""));
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
