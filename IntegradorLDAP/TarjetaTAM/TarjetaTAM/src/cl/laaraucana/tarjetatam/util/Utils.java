package cl.laaraucana.tarjetatam.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import cl.laaraucana.tarjetatam.vo.DatosTarjetaVo;


public class Utils {

	

	public static String emailCliente(DatosTarjetaVo vo) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimado(a),&nbsp;<b>" + vo.getNombre() +"</b></span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:12.0pt;line-height:normal;background:");
		str.append(
				"white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Te informamos que tu solicitud de mandato único para transferencia electrónica de&nbsp;");
		str.append("prestaciones y beneficios con&nbsp;folio&nbsp;<b>" + vo.getNumeroTarjeta() + "</b>, ha");
		str.append("&nbsp;sido ingresado exitósamente. En caso de existir un mandato previo,");
		str.append("&nbsp;se actualizará con el último recibido.");
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

	public static String getbodyEjec(DatosTarjetaVo vo) {

		StringBuilder str = new StringBuilder();
		
		str.append("<body lang=ES-CL>");

		str.append("<div class=WordSection1>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Estimado(a),&nbsp;<b>" + vo.getNombre() +"</b></span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:12.0pt;line-height:normal;background:");
		str.append(
				"white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>La solicitud de mandato único para transferencia electrónica de&nbsp;");
		str.append("prestaciones y beneficios con&nbsp;folio&nbsp;<b>" + vo.getNumeroTarjeta() + "</b>, ha");
		str.append("&nbsp;sido ingresado exitósamente a nuestros sistemas. En caso de haber");
		str.append("&nbsp;existido&nbsp;un mandato previo, este quedará revocado&nbsp;automáticamente");
		str.append("&nbsp;quedando&nbsp;en vigencia el nuevo mandato recibido.</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");

		str.append("</div>");

		str.append("</body>");
		
		//***

		
		return str.toString();
	}

	public static String cerosLeft(long number) {

		String aux = "00000000";

		aux = aux + String.valueOf(number);

		int len = aux.length();

		return aux.substring(len - 8, len);
	}
	

	public static String getAAAAMMDD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		return date;
	}
	
	public static String getFecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		return date;
	}

	public static String getPeriodo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String date = sdf.format(new Date());
		return date;
	}

	public static int getAnio() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	public static boolean validaFecha(String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		if (!date.equals(param)) {
			return false;
		}
		return true;
	}

	

 

}
