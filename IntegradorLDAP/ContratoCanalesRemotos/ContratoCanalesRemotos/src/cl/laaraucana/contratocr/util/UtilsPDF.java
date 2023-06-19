package cl.laaraucana.contratocr.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class UtilsPDF {
	public static Map<String, Object> hoja1() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "CL"));

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("fechaCreacion", sdf.format(new Date()));

		return param_map;
	}

	public static Map<String, Object> hoja2(String rut, String nombre) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "CL"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "CL"));

		param_map.put("nombre", nombre);
		param_map.put("rut", rut);
		param_map.put("fechaCreacion", sdf.format(new Date()));

		return param_map;

	}
	
	public static String emailCliente(String nombre) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimado(a),&nbsp;<b>" + nombre +"</b></span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:12.0pt;line-height:normal;background:");
		str.append(
				"white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Se adjunta PDF con aceptación de términos y condiciones de &nbsp;");
		str.append("Contrato de Uso de canales remotos.");
		str.append("</span></p>");

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

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");

		str.append("</div>");

		str.append("</body>");

		return str.toString();
	}
}
