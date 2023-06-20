package cl.laaraucana.mandatocesantia.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cl.laaraucana.mandatocesantia.model.CesantiaVo;

public class Utils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES", "MX"));
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm", new Locale("ES", "MX"));
	
	public static String emailCliente(CesantiaVo vo) {

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
		str.append("font-family:\"Arial\",sans-serif;color:#1F497D;letter-spacing:1.0pt'>AUTORIZACION MANDATO CESANTÍA &nbsp;");
		str.append("</span></b></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#262626'>Hemos recibido tu autorización </span></p>");
		str.append(
				"                                                                                                                         ");
		str.append("<p class=MsoNormal style='text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",sans-serif;color:#262626'>Si tienes dudas, ingresa a tu&nbsp;");
		str.append("cuenta en nuestro sitio&nbsp;</span><span lang=ES>");
		str.append("<a href=\"http://www.laaraucana.cl\" target=\"_blank\"><span lang=ES-CL");
		str.append(" style='font-size:10.0pt;font-family:\"Arial\",sans-serif;color:#1155CC'>www.laaraucana.cl</span></a></span>");
		str.append("<span style='font-size:10.0pt;font-family:\"Arial\",sans-serif;color:#262626'>&nbsp;o&nbsp;llámanos&nbsp;");
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
		str.append("Este correo y la información contenida o adjunta al mismo es privada y&nbsp;");
		str.append("confidencial y va dirigida exclusivamente a su destinatario. La Araucana&nbsp;");
		str.append("informa a quien pueda haber recibido este correo por error que contiene&nbsp;");
		str.append("información confidencial cuyo uso, copia, reproducción o distribución está&nbsp;");
		str.append("expresamente prohibida. Si no es usted el destinatario del mismo y recibe este&nbsp;");
		str.append("correo por error, le rogamos lo ponga en conocimiento del emisor y proceda a su&nbsp;");
		str.append("eliminación sin copiarlo, imprimirlo o utilizarlo de ningún modo.&nbsp;</span></p>");
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

	public static Map<String, Object> hoja1(CesantiaVo vo) {


		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map.put("fechaMandato", sdf2.format(new Date()));
		param_map.put("nombre", vo.getNombreCliente());
		param_map.put("rut", vo.getRutCliente() + "-" +  vo.getDvCliente());

		return param_map;
	}
	
	public static Map<String, Object> hoja2(CesantiaVo vo) {

		Map<String, Object> param_map = new HashMap<String, Object>();

		

		param_map.put("nombreCompleto", vo.getNombreCliente());
		param_map.put("rut", vo.getRutCliente() + "-" + vo.getDvCliente());
		param_map.put("correo", vo.getEmail());
		param_map.put("celular", vo.getCelular());
		param_map.put("fijo", vo.getTelefono());
		param_map.put("fechaMandato", sdf2.format(new Date()));
		if(vo.getFecha()==null){
			param_map.put("fecha2", sdf.format(new Date()));
		}else{
			param_map.put("fecha2", sdf.format(vo.getFecha()));
		}
		return param_map;

	}
	


}
