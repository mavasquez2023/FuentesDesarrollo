package cl.araucana.spring.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cl.araucana.spring.entities.Formulario;

public class Utils {

	public static File grabarFicheroTemporal(CommonsMultipartFile uploaded) throws Exception {
		File localFile = new File(System.getProperty("java.io.tmpdir") + uploaded.getOriginalFilename());
		FileOutputStream os = null;

		try {

			os = new FileOutputStream(localFile);
			os.write(uploaded.getBytes());

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return localFile;
	}

	public static String getTipe(String str) {

		int pos = str.lastIndexOf(".");

		str = str.substring(pos + 1, str.length());

		return str;
	}

	public static String mensajeCliente(Formulario form) {

		StringBuffer str = new StringBuffer();

		str.append("<body lang=ES-CL link=blue vlink=purple >");

		str.append("<table style='width:100%' >");
		str.append("<tr align='center' bottom='middle'>");
		str.append("<td>");
		str.append("<p class=MsoNormal align=right style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal'><span style='position:relative;z-index:");
		str.append("1'><span style='left:0px;position:relative;left:0px;top:0px;width:640px;");
		str.append("height:72px;'><img width=640 height=95");
		str.append(" src=\"cid:image\"/></span></span></p>");
		str.append("<table  width=640>");
		str.append("<tr>");
		str.append("<td>");

		//str.append("<p><span style='font-size:2.0pt;font-family: \"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<br clear=ALL>");

		str.append(
				"<p align=right style='line-height:115%;margin-bottom:0cm;margin-bottom:.0001pt;line-height:normal'><span");
		str.append(" style='font-size:10.0pt;line-height:115%;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#404040;letter-spacing:1.0pt'>Fecha y hora de solicitud</span><br/>");

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("<b><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#1F497D;background:white'>" + format1.format(new Date())
				+ " | " + format2.format(new Date()) + " hrs.</span></b></p>");

	
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		
		
		str.append("<p align='center' style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal;text-autospace:none'><b><span");
		str.append(" style='font-size:16.0pt;font-family:\"Arial\",\"sans-serif\";color:#1F497D;");
		str.append("letter-spacing:1.0pt'>SOLICITUD DE CLAVE EMPRESA</span></b></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:12.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#595959;background:white'>&nbsp;</span></b></p>");

		str.append("<p class=MsoNormal style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;");
		str.append("margin-bottom:.0001pt;line-height:normal;text-autospace:");
		str.append("none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#262626;background:white'>Estimado(a) </span></b></p>");

		str.append("<p class=MsoNormal style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;");
		str.append("margin-bottom:.0001pt;line-height:normal;text-autospace:");
		str.append("none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";color:#262626'>Hemos");
		str.append("&nbsp;recibido su solicitud de clave empresa, con los siguientes datos:</span></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:4.0pt'><td></td></tr>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt;'>DATOS SOLICITANTE</span></b></p>");
		str.append("<tr style='height:2.0pt'><td></td></tr>");
		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:-16.9999pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:0.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<div style='margin-left:35.4pt;'>");

		str.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=640");
		str.append(" style='border-collapse:collapse;'>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRut() + form.getDv()) + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNombre() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO");
		str.append(" PATERNO</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApePat() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO ");
		str.append("MATERNO</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApeMat() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>TELÉFONO</span></p>");

		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getTelefono() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>CELULAR");
		str.append("</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getCelular() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>EMAIL</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getEmail() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");

		// str.append("</div>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:4.0pt'><td></td></tr>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt;'>DATOS EMPRESA</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:-16.9999pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:0.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:2.0pt'><td></td></tr>");
		// str.append("<div style='margin-left:35.4pt;'>");

		str.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=640");
		str.append(" style='border-collapse:collapse'>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT ");
		str.append("EMPRESA</span></p>");
		str.append("</td>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRutemp() + form.getDvEmp()) + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE ");
		str.append("EMPRESA</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNombre() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>TELÉFONO ");
		str.append("EMPRESA</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>");

		if (form.getTelEmp() == null) {
			str.append("");
		} else {
			str.append(form.getTelEmp());

		}
		str.append("</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>EMAIL ");
		str.append("EMPRESA</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getEmailEmp() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");

		// str.append("</div>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:4.0pt'><td></td></tr>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt;'>DATOS REPRESENTANTE LEGAL</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:-16.9999pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:0.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr height='1px'><td></td></tr>");
		// str.append("<div style='margin-left:35.4pt;'>");

		str.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=640");
		str.append(" style='border-collapse:collapse'>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT ");
		str.append("REP. LEGAL</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRutRepr() + form.getDvRepr()) + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE ");
		str.append("REP. LEGAL</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNomRepr() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO ");
		str.append("PATERNO REP. LEGAL</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append("height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApePatRepr() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("<tr style='height:17.0pt'>");
		str.append("<td width=229 style='width:171.95pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO ");
		str.append("MATERNO REP. LEGAL</span></p>");
		str.append("</td>");
		str.append("<td width=319 style='width:239.15pt;padding:0cm 5.4pt 0cm 5.4pt;height:17.0pt'>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApeMatRepr() + "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");

		// str.append("</div>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:2.0pt'><td></td></tr>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt;'>MENSAJE</span></b></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:-16.9999pt;line-height:");
		str.append(
				"normal;text-autospace:none;margin-left:35.4pt;'><b><span style='font-size:0.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<div style='margin-left:35.4pt;'>");

		str.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=640");
		str.append(" style='border-collapse:collapse'>");
		str.append("<tr style='height:44.45pt'>");
		str.append("<td width=600 valign=top style='width:411.1pt;background:#E2E2E2;padding:");
		str.append("0cm 5.4pt 0cm 5.4pt;height:44.45pt'>");
		str.append("<p class=MsoNormal style='margin-top:12.0pt;margin-right:0cm;margin-bottom:");
		str.append("0cm;margin-left:0cm;margin-bottom:.0001pt;line-height:normal;text-autospace:");
		str.append("none'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>" + form.getMensaje()
				+ "</span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");

		// str.append("</div>");

		str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal;text-autospace:none'><span");
		str.append(" style='font-size:8.0pt;font-family:\"Arial\",\"sans-serif\";color:#595959;");
		str.append("letter-spacing:1.0pt'>");
		str.append("Este e-mail es generado automáticamente, por favor no responder.</span></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'></span></p>");

		// str.append("<p class=MsoNormal align=center
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:center;line-height:normal;text-autospace:none'><span");
		// str.append("style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><b><span style='text-align:justify;font-size:8.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append(
				"color:#333333'>Aviso de Confidencialidad</span></b><span style='font-size:8.0pt;text-align:justify;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#333333'>&nbsp;<br>");
		str.append("Este correo y la información contenida o adjunta al mismo es privada y");
		str.append("&nbsp;confidencial y va dirigida exclusivamente a su destinatario. La Araucana");
		str.append("&nbsp;informa a quien pueda haber recibido este correo por error que contiene");
		str.append("&nbsp;información confidencial cuyo uso, copia, reproducción o distribución está");
		str.append("&nbsp;expresamente prohibida. Si no es usted el destinatario del mismo y recibe este");
		str.append("&nbsp;correo por error, le rogamos lo ponga en conocimiento del emisor y proceda a su");
		str.append("&nbsp;eliminación sin copiarlo, imprimirlo o utilizarlo de ningún modo.&nbsp;</span></p>");

		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"normal;text-autospace:none'><span style='font-size:6.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt;text-align:center;'><img width=640 height=76");
		str.append(" src=\"cid:image2\"/></span></b></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("</body>");

		return str.toString();
	}

	public static String mensajeSucursal(Formulario form, String nombreArchivo) {

		StringBuffer str = new StringBuffer();

		str.append("<body lang=ES-CL link=blue vlink=purple>");
		str.append("<table style='width:100%' >");
		str.append("<tr align='center' bottom='middle'>");
		str.append("<td>");
		// str.append("<div style='text-align:center'");
		str.append("<p class=MsoNormal align=right style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal'><span style='position:relative;z-index:");
		str.append("1'><span style='left:0px;position:relative;left:0px;top:-20px;width:645px;");
		str.append("height:72px'><img width=640 height=95");
		str.append(" src=\"cid:image\"></span>");
		// str.append("</span><span
		// style='font-size:6.5pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");
		str.append("<table  width=640>");
		str.append("<tr>");
		str.append("<td>");
		str.append("<p><span style='font-size:2.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");
		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><span
		// style='font-size:6.5pt;font-family:");
		// str.append("\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><span
		// style='font-size:6.5pt;font-family:");
		// str.append("\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><span
		// style='font-size:6.5pt;font-family:");
		// str.append("\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><span
		// style='font-size:6.5pt;font-family:");
		// str.append("\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><span
		// style='font-size:6.5pt;font-family:");
		// str.append("\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");

		// str.append("<br clear=ALL>");

		str.append(
				"<p align=right style='line-height:115%;margin-bottom:0cm;margin-bottom:.01pt;line-height:normal'><span");
		str.append(" style='font-size:10.0pt;line-height:115%;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#404040;letter-spacing:1.0pt'>Fecha y hora de solicitud</span><br/>");

		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("<b><span style='font-size:10.0pt;margin-bottom:0cm;margin-bottom:.01pt;line-height:normal;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#1F497D;background:white'>" + format1.format(new Date())
				+ " | " + format2.format(new Date()) + " hrs.</span></b></p>");

		// str.append("<p align=right style='line-height:115%'><span");
		// str.append("
		// style='font-size:10.0pt;line-height:115%;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#404040;letter-spacing:1.0pt'>Fecha y hora de</span></p>");

		// str.append("<p class=MsoNormal align=right
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:right;line-height:normal'><b><span
		// style='font-size:10.0pt;");
		// str.append("font-family:\"Arial\",\"sans-serif\";color:#1F497D;background:white'>"
		// + fecha.format(new Date()) + " | ");
		// str.append("" + hora.format(new Date()) + " hrs.</span></b></p>");

		// str.append("<p class=MsoNormal align=center
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:center;line-height:normal;text-autospace:none'><b><span");
		// str.append("style='font-size:12.0pt;font-family:\"MyriadPro-Black\",\"sans-serif\";color:#1F497D;");
		// str.append("letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		str.append("<p class=MsoNormal align='center' style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal;text-autospace:none'><b><span");
		str.append(" style='font-size:16.0pt;font-family:\"Arial\",\"sans-serif\";color:#1F497D;");
		str.append("letter-spacing:1.0pt'>SOLICITUD DE CLAVE EMPRESA</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:12.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#595959;background:white'>&nbsp;</span></b></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#262626'>Se ha generado una solicitud de clave empresa, con los");
		str.append(" siguientes datos:</span></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");
		str.append("<tr style='height:4.0pt'><td></td></tr>");
		str.append("<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width=640");
		str.append(" style='margin-left:0pt;border-collapse:collapse;border:none'>");
		str.append(" <tr style='height:15.35pt'>");
		str.append("  <td width=650 colspan=2 style='width:487.35pt;border:solid #1F497D 1.0pt;");
		str.append("  background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:15.35pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:white'>DATOS DEL SOLICITANTE</span></b></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:#262626'>RUT</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRut() + form.getDv()) + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:#262626'>NOMBRE</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNombre() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:#262626'>APELLIDO PATERNO</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApePat() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:#262626'>APELLIDO MATERNO</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApeMat() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>TELÉFONO</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getTelefono() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>CELULAR");
		str.append("  </span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getCelular() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>EMAIL</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getEmail() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:15.35pt'>");
		str.append("  <td width=650 colspan=2 style='width:487.35pt;border:solid #1F497D 1.0pt;");
		str.append("  border-top:none;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:15.35pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:white'>DATOS DE LA EMPRESA </span></b></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT");
		str.append(" EMPRESA</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRutemp() + form.getDvEmp()) + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE");
		str.append(" EMPRESA</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNomEmp() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>TELÉFONO");
		str.append(" EMPRESA</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>");
		if (form.getTelEmp() == null) {

			str.append("");
		} else {

			str.append(form.getTelEmp());
		}
		str.append("</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>EMAIL");
		str.append(" EMPRESA</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getEmailEmp() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:15.35pt'>");
		str.append("  <td width=650 colspan=2 style='width:487.35pt;border:solid #1F497D 1.0pt;");
		str.append("  border-top:none;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:15.35pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><b><span style='font-size:11.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:white'>DATOS DEL REPRESENTANTE LEGAL </span></b></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT");
		str.append(" REP. LEGAL</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ FormatearRUT(form.getRutRepr() + form.getDvRepr()) + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE");
		str.append(" REP. LEGAL</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getNomRepr() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO");
		str.append(" PATERNO REP. LEGAL</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApePatRepr() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=236 style='width:177.2pt;border:solid #1F497D 1.0pt;border-top:");
		str.append("  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>APELLIDO");
		str.append(" MATERNO REP. LEGAL</span></p>");
		str.append("  </td>");
		str.append("  <td width=414 style='width:310.15pt;border-top:none;border-left:none;");
		str.append("  border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt;");
		str.append("  padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getApeMatRepr() + "</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:15.35pt'>");
		str.append("  <td width=650 colspan=2 style='width:487.35pt;border:solid #1F497D 1.0pt;");
		str.append("  border-top:none;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:15.35pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal'><b><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("  color:white'>MENSAJE</span></b></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append(" <tr style='height:14.15pt'>");
		str.append("  <td width=650 colspan=2 style='width:487.35pt;border:solid #1F497D 1.0pt;");
		str.append("  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.15pt'>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>"
				+ form.getMensaje() + "</span></p>");
		str.append("  <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(
				"  normal;text-autospace:none'><span style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");
		str.append("  </td>");
		str.append(" </tr>");
		str.append("</table>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:black;letter-spacing:1.0pt'>Archivo Adjunto:
		// (Sol_Clave_</span><span");
		// str.append("style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";color:black'>"
		// + nombreArchivo +")</span></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		// str.append("<p class=MsoNormal
		// style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		// str.append("normal;text-autospace:none'><b><span
		// style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";");
		// str.append("color:#1F497D;letter-spacing:1.0pt'>&nbsp;</span></b></p>");

		str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;line-height:normal;text-autospace:none'><span");
		str.append(" style='font-size:8.0pt;font-family:\"Arial\",\"sans-serif\";color:#595959;");
		str.append("letter-spacing:1.0pt'>Este e-mail es generado automáticamente, por favor no");
		str.append("&nbsp;responder.</span></p>");

		// str.append("<p class=MsoNormal align=center
		// style='margin-bottom:0cm;margin-bottom:.0001pt;");
		// str.append("text-align:center;line-height:normal;text-autospace:none'><span");
		// str.append("style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\"'>&nbsp;</span></p>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt'><img width=640 height=76");
		str.append(" src=\"cid:image2\"></span></b></p>");
		// str.append("</div>");
		str.append("</td>");
		str.append("</tr>");
		str.append("</table>");
		str.append("</body>");

		return str.toString();
	}

	public static String FormatearRUT(String rut) {

		int cont = 0;
		String format;
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");
		format = "-" + rut.substring(rut.length() - 1);
		for (int i = rut.length() - 2; i >= 0; i--) {
			format = rut.substring(i, i + 1) + format;
			cont++;
			if (cont == 3 && i != 0) {
				format = "." + format;
				cont = 0;
			}
		}
		return format;
	}
}
