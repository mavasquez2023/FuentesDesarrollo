package cl.laaraucana.copagocredito.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cl.laaraucana.copagocredito.model.CreditoVo;

public class Utils {

	public static String emailCliente(CreditoVo vo) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL link=blue vlink=\"#954F72\">");
		str.append(
				"                                                                                                                         ");
		str.append("<div class=WordSection1>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='margin-right:27.0pt;text-indent:1.65pt'>");
		str.append("<span lang=ES><img width=645 height=72");
		str.append(" src=\"cid:image\"></span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='margin-right:27.0pt;text-indent:1.65pt'>");
		str.append("<span lang=ES style='font-size:10.0pt;font-family:\"Arial\",sans-serif'>&nbsp;</span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><b><span style='font-size:14.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#1F497D;letter-spacing:1.0pt'>APROBACI�N &nbsp;");
		str.append("PROGRAMA COPAGO CR�DITO</span></b></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#262626'>Hemos recibido tu aprobaci�n </span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#262626'>Si tienes dudas, ingresa a tu&nbsp;");
		str.append("cuenta en nuestro sitio&nbsp;</span><span lang=ES>");
		str.append("<a href=\"http://www.laaraucana.cl\" target=\"_blank\"><span lang=ES-CL");
		str.append(" style='font-size:10.0pt;font-family:\"Arial\",sans-serif;color:#1155CC'>www.laaraucana.cl</span></a></span>");
		str.append("<span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;color:#262626'>&nbsp;o&nbsp;ll�manos&nbsp;");
		str.append("al 600 4228 100.</span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#262626'>Estamos Contigo, La Araucana</span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><b><span style='font-size:7.5pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#333333'>Aviso de Confidencialidad.</span></b>");
		str.append("<span style='font-size:7.5pt;font-family:\"Arial\",sans-serif;color:#333333'><br>");
		str.append("Este correo y la informaci�n contenida o adjunta al mismo es privada y&nbsp;");
		str.append("confidencial y va dirigida exclusivamente a su destinatario. La Araucana&nbsp;");
		str.append("informa a quien pueda haber recibido este correo por error que contiene&nbsp;");
		str.append("informaci�n confidencial cuyo uso, copia, reproducci�n o distribuci�n est�&nbsp;");
		str.append("expresamente prohibida. Si no es usted el destinatario del mismo y recibe este&nbsp;");
		str.append("correo por error, le rogamos lo ponga en conocimiento del emisor y proceda a su&nbsp;");
		str.append("eliminaci�n sin copiarlo, imprimirlo o utilizarlo de ning�n modo.&nbsp;</span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal><b><span lang=ES style='font-family:\"Arial\",sans-serif;");
		str.append("color:#1F497D;letter-spacing:1.0pt'><img border=0 width=642 height=72");
		str.append(" src=\"cid:image2\"></span></b></p>");
		str.append(
				"                                                                                                                         ");
		str.append("</div>");
		str.append(
				"                                                                                                                         ");
		str.append("</body>");

		return str.toString();
	}

	public static Map<String, Object> hoja1(CreditoVo vo) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map.put("texto", Utils.texto(vo.getNombreCliente(), vo.getRutCliente()));
		param_map.put("texto2", Utils.texto2());
		param_map.put("fecha", sdf.format(new Date()));
		param_map.put("rut", vo.getRutCliente().replace(".", "").split("-")[0]);

		return param_map;
	}

	public static String texto(String nombre, String rut) {

		StringBuilder str = new StringBuilder();

		str.append("El afiliado se�or(a) " + nombre + ", c�dula de identidad N� " + rut
				+ ", por este medio toma conocimiento que re�ne los siguientes ");
		str.append(
				"requisitos para optar al beneficio financiero incluido en el �Programa de ayuda para trabajadores vulnerables con cr�ditos ");
		str.append(
				"vigentes en La Araucana�, el que consiste en el copago de tres meses de cuota de su cr�dito vigente. El financiamiento se ");
		str.append(
				"le entregar� de forma mensual dentro de los primeros 5 d�as h�biles del mes siguiente de la recepci�n efectiva de la cuota ");
		str.append(
				"del cr�dito social y durante tres meses por un monto equivalente al 50% del valor cuota, con tope de $30.000 por evento, ");
		str.append(
				"conforme lo dispone el Programa de Prestaciones Adicionales de La Araucana C.C.A.F. para el a�o 2020. ");
		str.append("<br><br>");
		str.append("Requisitos para acceder al beneficio:");

		return str.toString();
	}

	public static String texto2() {

		StringBuilder str = new StringBuilder();

		str.append("1.	Ser afiliado a la CCAF La Araucana.<br><br>");
		str.append("2.	Mantener un cr�dito social vigente al d�a  con la CCAF La Araucana;<br><br>");
		str.append(
				"3.	Tener una renta imponible hasta los $400.000 o trabajadores con una renta imponible hasta $700.000 que hayan tenido una disminuci�n igual o superior al 20% de su renta.<br><br>");
		str.append(
				"4.	La evaluaci�n de la disminuci�n de la renta ser� la correspondiente a los meses de julio, agosto, septiembre del 2019 o bien, en caso de no poder acreditar renta en ese ");
		str.append(
				"periodo, ser�n analizados los meses de enero, febrero y marzo del 2020.<br><br>");
		str.append(
				"5.	Los trabajadores que soliciten el beneficio no podr�n estar acogidos a la ley de protecci�n del empleo.<br><br>");
		str.append("Si alguna de estas exigencias pierde su vigencia, el beneficio igualmente lo perder�.");

		return str.toString();
	}

}
