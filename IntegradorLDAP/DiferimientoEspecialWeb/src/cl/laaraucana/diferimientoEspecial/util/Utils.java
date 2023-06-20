package cl.laaraucana.diferimientoEspecial.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cl.laaraucana.diferimientoEspecial.model.CreditoVo;


public class Utils {

	public static String emailCliente(CreditoVo vo) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL>");
		str.append("                                                                                                             ");
		str.append("<div class=WordSection1>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#0B5394'>Estimado(a),&nbsp;<b>" + vo.getNombreCliente() + "</b></span></p>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:12.0pt;line-height:normal;background:                                ");
		str.append("white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;color:#0B5394'>&nbsp;</span></p>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#0B5394'>Tu solicitud de diferimiento ha&nbsp;sido ingresado exitósamente");
		str.append("&nbsp;a nuestros sistemas.</span></p>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif");
		str.append("color:#0B5394'>&nbsp;</span></p>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#0B5394'>Se adjunta PDF de aprobación con detalle de folios y cuotas");
		str.append("&nbsp;diferidas.</span></p>                                                                                        ");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#0B5394'>&nbsp;</span></p>");
		str.append("                                                                                                             ");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#0B5394'>Ante consultas, puedes llamar al Call Center de");
		str.append("&nbsp;La&nbsp;ARAUCANA&nbsp;<b>600 4228 100</b>&nbsp;</span></p>");
		str.append("                                  ");
		str.append("<p class=MsoNormal>&nbsp;</p>");
		str.append("                                  ");
		str.append("<p class=MsoNormal><img width=198 height=58 src=\"cid:image\"/></p>");
		str.append("                                  ");
		str.append("</div>");
		str.append("                                  ");
		str.append("</body>");

		return str.toString();
	}

	public static Map<String, Object> hoja1(CreditoVo vo) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map.put("texto", Utils.texto(vo.getNombreCliente(), vo.getRutCliente()));
		param_map.put("fecha", sdf.format(new Date()));
		param_map.put("rut", vo.getRutCliente().replace(".", "").split("-")[0]);

		return param_map;
	}

	public static String texto(String nombre, String rut) {

		StringBuilder str = new StringBuilder();

		str.append("El afiliado " + nombre +", RUT " + rut +  ", autoriza a C.C.A.F. La  Araucana a diferir el "); 
		str.append("vencimiento del crédito identificado en la tabla adjunta, a continuación del vencimiento");
		str.append(" de la última cuota pactada, debido al no descuento realizado por el empleador producto de un error operativo.");
		 

		return str.toString();
	}
	
 
	public static String seriex(String serie) {

		int len = serie.length();

		serie = serie.substring(0, len - 3);

		return serie + "XXX";
	}

}
