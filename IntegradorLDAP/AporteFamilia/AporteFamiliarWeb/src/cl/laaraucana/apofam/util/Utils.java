package cl.laaraucana.apofam.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Utils {

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
	
	public static String emailCliente(String nombre) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");
		
		str.append("<p class=MsoNormal align=left style='mso-margin-top-alt:auto;text-align:left;");
		str.append("background:white'>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:16.0pt;mso-bidi-font-size:");
		str.append("16.0pt;font-family:\"Arial\",\"sans-serif\"'>Estimad@&nbsp;" + nombre);
		str.append("</span></b>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");
		
		str.append("<p class=MsoNormal align=left style='mso-margin-top-alt:auto;text-align:left;");
		str.append("background:white'>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:16.0pt;mso-bidi-font-size:");
		str.append("16.0pt;font-family:\"Arial\",\"sans-serif\"'>Se adjunta Certificado de Aporte Familiar Permanente solicitado a través de nuestro portal.");
		str.append("</span></b>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Ante consultas, puedes llamar al Call Center de La");
		str.append("&nbsp;Araucana&nbsp;<b>600 4228 100</b>&nbsp;</span></p>");

		//str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		//str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		//str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");

		str.append("</div>");

		str.append("</body>");

		return str.toString();
	}
	
	
	public static String getExtencion(String nombre) {

		int start = nombre.lastIndexOf(".");
		int end = nombre.length();

		String ext = nombre.substring(start + 1, end);

		return ext;

	}
	
}
