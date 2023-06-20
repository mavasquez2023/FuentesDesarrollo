package cl.laaraucana.mandatopublico.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import com.Ostermiller.util.Base64;
import cl.araucana.core.util.Hex;
import cl.laaraucana.mandatopublico.vo.DatosMandatoVo;

public class Utils {

	public static Map<String, Object> hoja1(DatosMandatoVo vo) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("numeroMandato", String.valueOf(vo.getIdMandato()));
		param_map.put("fechaMandato", sdf.format(new Date()));

		return param_map;
	}

	public static Map<String, Object> hoja2(DatosMandatoVo datos) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));

		param_map.put("nombreCompleto", datos.getNombre());
		param_map.put("numeroMandato", String.valueOf(datos.getIdMandato()));
		param_map.put("rut", datos.getRut());
		param_map.put("correo", datos.getEmail());
		param_map.put("celular", datos.getCelular());
		param_map.put("fijo", datos.getTelefono());
		param_map.put("tipoDeCuenta", datos.getNameCuenta());
		param_map.put("banco", datos.getNameBanco());
		param_map.put("numeroDeCuenta", datos.getCuenta());
		param_map.put("fecha2", sdf.format(datos.getFechaVigencia()));
		param_map.put("fechaMandato", sdf2.format(new Date()));

		return param_map;

	}
	
	public static Map<String, Object> hojaEjec(DatosMandatoVo datos) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));

		param_map.put("nombreCompleto", datos.getNombre());
		param_map.put("numeroMandato", String.valueOf(datos.getIdMandato()));
		param_map.put("rut", datos.getRut());
		param_map.put("correo", datos.getEmail());
		param_map.put("celular", datos.getCelular());
		param_map.put("fijo", datos.getTelefono());
		param_map.put("tipoDeCuenta", datos.getNameCuenta());
		param_map.put("banco", datos.getNameBanco());
		param_map.put("numeroDeCuenta", datos.getCuenta());
		param_map.put("fecha2", sdf.format(new Date()));
		param_map.put("fechaMandato", sdf2.format(new Date()));

		return param_map;

	}


	public static Map<String, Object> hoja2_1(DatosMandatoVo datos) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));

		param_map.put("nombreCompleto", datos.getNombre());
		param_map.put("numeroMandato", String.valueOf(datos.getIdMandato()));
		param_map.put("rut", datos.getRut());
		param_map.put("correo", datos.getEmail());
		param_map.put("celular", datos.getCelular());
		param_map.put("fijo", datos.getTelefono());
		param_map.put("tipoDeCuenta", datos.getNameCuenta());
		param_map.put("banco", datos.getNameBanco());
		param_map.put("numeroDeCuenta", datos.getCuenta());
		param_map.put("fecha2", sdf.format(new Date()));
		param_map.put("fechaMandato", sdf2.format(new Date()));

		return param_map;

	}

	public static Map<String, Object> hoja3(DatosMandatoVo datos, long idMandato) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));

		Map<String, Object> param_map = new HashMap<String, Object>();
		param_map.put("numeroMandato", String.valueOf(idMandato));
		param_map.put("fechaMandato", sdf.format(new Date()));

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));

		param_map.put("nombreCompleto", datos.getNombre());
		param_map.put("rut", datos.getRut());
		param_map.put("correo", datos.getEmail());
		param_map.put("celular", datos.getCelular());
		param_map.put("fijo", datos.getTelefono());
		param_map.put("tipoDeCuenta", datos.getNameCuenta());
		param_map.put("banco", datos.getNameBanco());
		param_map.put("numeroDeCuenta", datos.getCuenta());
		param_map.put("fecha2", sdf2.format(datos.getFechaRevocacion()));
		param_map.put("observaciones", datos.getObservaciones());
		
		return param_map;

	}

	public static String emailCliente(DatosMandatoVo vo) {

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
		str.append("prestaciones y beneficios con&nbsp;folio&nbsp;<b>" + vo.getIdMandato() + "</b>, ha");
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

	public static String getbodyEjec(DatosMandatoVo vo) {

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
		str.append("prestaciones y beneficios con&nbsp;folio&nbsp;<b>" + vo.getIdMandato() + "</b>, ha");
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
	
	
	
	
	public static boolean validarRut(String rut) {

		boolean validacion = false;
		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		}
		return validacion;
	}

 

}
