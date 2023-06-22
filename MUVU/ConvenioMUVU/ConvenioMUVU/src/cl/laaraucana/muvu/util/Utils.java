package cl.laaraucana.muvu.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cl.laaraucana.muvu.entities.Bitacora;

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
	
	public static String emailCliente(String nombre, String url) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");
		str.append("<p class=MsoNormal style='mso-margin-top-alt:auto;text-align:center;'><img width=600 height=300 src=\"cid:image\"/></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:16.0pt;mso-bidi-font-size:");
		str.append("16.0pt;font-family:\"Arial\",\"sans-serif\"'>�Felicitaciones&nbsp;" + nombre);
		str.append("</span></b>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:16.0pt;mso-bidi-font-size:");
		str.append("16.0pt;font-family:\"Arial\",\"sans-serif\"'>T� inscripci�n al Programa de Vida Sana est� lista!");
		str.append("</span></b>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");

		str.append("<p class=MsoNormal><span style='mso-fareast-font-family:\"Arial\"'>. <o:p></o:p></span></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> �A partir de ahora empieza a moverte y gana!");
		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;width: 400px");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> Recuerda que debes sincronizar tu actividad f�sica con MUVU para que tu seguro est� vigente");
		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;width: 400px");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> Acepta los desaf�os que te proponemos y participa en concursos y sorteos que te contaremos a trav�s de la APP");
		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");

		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");

		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>Descarga archivo con t�rminos y condiciones<br> <a href='" + url + "'>" + url + "</a>");
		str.append("</p>");

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

		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image2\"/></p>");

		str.append("</div>");

		str.append("</body>");

		return str.toString();
	}
	
	public static String emailClienteAlta(String nombre) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");
		str.append("<p class=MsoNormal style='mso-margin-top-alt:auto;text-align:center;'><img width=600 height=300 src=\"cid:image\"/></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:16.0pt;mso-bidi-font-size:");
		str.append("16.0pt;font-family:\"Arial\",\"sans-serif\"'>�Hola&nbsp;" + nombre + ", tus seguros ya est�n activos!");
		str.append("</span></b>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");

		str.append("<p class=MsoNormal><span style='mso-fareast-font-family:\"Arial\"'>. <o:p></o:p></span></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;width: 400px");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> Recuerda que al participar en el Programa de Vida Sana, <b>La Araucana</b> te regala un <b>seguro de vida</b> que incluye la <b>protecci�n de da�o en tu pantalla de celular</b>");
		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;width: 400px");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> Adjuntamos el certificado con los detalles de coberturas y condiciones");
		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("16.0pt'><o:p></o:p></span></b></p>");

		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;width: 400px");
		str.append("background:white'>");
		str.append("<span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("14.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'>Revisa en nuestro sitio web todo lo que debes saber sobre el Programa de Vida Sana y suma beneficios con cada paso que das.");
		str.append("</span>");
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
		str.append("color:#222222'>Si tienes dudas cont�ctanos llamando al <b>600 4228 100</b>");
		str.append("</span></p>");

		//str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		//str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		//str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image2\"/></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'><o:p>&nbsp;</o:p></p>");
		
		str.append("<p class=MsoNormal align=center style='mso-margin-top-alt:auto;text-align:center;");
		str.append("background:white'>");
		str.append("<span style='font-size:8.0pt;mso-bidi-font-size:");
		str.append("8.0pt;font-family:\"Arial\",\"sans-serif\";color:#222222'> Los riesgos son cubiertos por Seguros de Vida Suramericana S.A. Sus caracter�sticas, coberturas y exclusiones se rigen por lo dispuesto en la p�liza respectiva, que se encuentra depositada en la Comisi�n  para el Mercado Financiero bajo el N� de POL 2 2013 0909, CAD 2 2013 1000 Y CAD 2 2013 1001. Los seguros son intermediados por Cono Sur Corredores de Seguros S.A., quien asume las obligaciones propias de los seguros que intermedia en dependencias de Caja de Compensaci�n de Asignaci�n Familiar La Araucana, sin responsabilidad ni injerencia para esta �ltima. La presente informaci�n s�lo representa un resumen de las coberturas y exclusiones, el detalle de �stas se encuentra en las condiciones generales antes mencionadas y en las condiciones particulares del seguro publicitado bajo la P�liza N� 8161 de Seguros de Vida Suramericana S.A. Las condiciones generales pueden ser consultadas a trav�s del sitio web de la CMF (www.cmfchile.cl), en la opci�n Mercado de Seguros/Dep�sito de P�lizas. Seguro exclusivo para trabajadores Dependientes Afiliados a Caja de Asignaci�n Familiar La Araucana, sujeto a evaluaci�n y aprobaci�n conforme a los antecedentes y pol�ticas de riesgo de la Caja. Vigencia de la oferta del seguro: mientras se mantenga vigente la P�liza colectiva contratada por Caja de Compensaci�n de Asignaci�n Familiar La Araucana y se cumplan los requisitos de asegurabilidad. Se deja constancia que Caja de Compensaci�n de Asignaci�n Familiar La Araucana no comercializa, no intermedia, ni promueve seguros.");

		str.append("</span>");
		str.append("<b style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;mso-bidi-font-size:");
		str.append("8.0pt'><o:p></o:p></span></b></p>");

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
