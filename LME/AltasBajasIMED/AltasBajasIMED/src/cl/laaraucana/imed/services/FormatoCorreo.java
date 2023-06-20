/**
 * 
 */
package cl.laaraucana.imed.services;

/**
 * @author IBM Software Factory
 *
 */
public class FormatoCorreo {
	public static String getMailbody(int altas_ok, int altas_error,int bajas_ok, int bajas_error) {

		StringBuilder str = new StringBuilder();

	str.append("<div class=WordSection1> ");

	str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height: ");
	str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif; ");
	str.append("color:#222222'>Estimad@ usuario, se adjunta resultado de proceso mensual de cargas a IMED. ");
	str.append( "</span></p> ");
	
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>&nbsp;</span></p>");
	
	str.append("<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 style='margin-left:5.4pt;border-collapse:collapse;border:none'>");
	str.append("<tr style='height:17.9pt'><td width=463 colspan=2 style='width:347.3pt;border:solid #1F497D 1.0pt;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:17.9pt'>");
	str.append("<p class=MsoNormal style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;margin-left:1.7pt;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<b><span style='font-family:\"Arial\",\"sans-serif\";color:white'>Altas</span></b></p>");
	str.append("</td></tr>");
	str.append("<tr style='height:14.7pt'><td width=369 style='width:276.45pt;border:solid #1F497D 1.0pt;border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>Cantidad total de registros cargados correctamente </span></p></td>");
	str.append("<td width=94 style='width:70.85pt;border-top:none;border-left:none; border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt; padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt; text-align:center;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>" + altas_ok + "</span></p></td></tr>");
	str.append("<tr style='height:14.7pt'><td width=369 style='width:276.45pt;border:solid #1F497D 1.0pt;border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>Cantidad total de registros no cargados (con error) </span></p></td>");
	str.append("<td width=94 style='width:70.85pt;border-top:none;border-left:none; border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt; padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt; text-align:center;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>" + altas_error + "</span></p></td></tr>");
	str.append("</table>");
	
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>&nbsp;</span></p>");
	
	str.append("<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 style='margin-left:5.4pt;border-collapse:collapse;border:none'>");
	str.append("<tr style='height:17.9pt'><td width=463 colspan=2 style='width:347.3pt;border:solid #1F497D 1.0pt;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt;height:17.9pt'>");
	str.append("<p class=MsoNormal style='margin-top:0cm;margin-right:0cm;margin-bottom:0cm;margin-left:1.7pt;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<b><span style='font-family:\"Arial\",\"sans-serif\";color:white'>Bajas</span></b></p>");
	str.append("</td></tr>");
	str.append("<tr style='height:14.7pt'><td width=369 style='width:276.45pt;border:solid #1F497D 1.0pt;border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>Cantidad total de registros cargados correctamente </span></p></td>");
	str.append("<td width=94 style='width:70.85pt;border-top:none;border-left:none; border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt; padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt; text-align:center;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>" + bajas_ok + "</span></p></td></tr>");
	str.append("<tr style='height:14.7pt'><td width=369 style='width:276.45pt;border:solid #1F497D 1.0pt;border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>Cantidad total de registros no cargados (con error) </span></p></td>");
	str.append("<td width=94 style='width:70.85pt;border-top:none;border-left:none; border-bottom:solid #1F497D 1.0pt;border-right:solid #1F497D 1.0pt; padding:0cm 5.4pt 0cm 5.4pt;height:14.7pt'>");
	str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt; text-align:center;line-height:normal'>");
	str.append("<span style='color:black;font-family:\"Arial\",\"sans-serif\"'>" + bajas_error + "</span></p></td></tr>");
	str.append("</table>");	
	
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>&nbsp;</span></p>");
	
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>En caso de necesitar ejecutar nuevamente un proceso, favor comuníquese con Sistemas para soporte.</span></p>");
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>Proceso Altas: AltasBajasIMED/runImed?tipo=altas</span></p>");
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>Proceso Altas: AltasBajasIMED/runImed?tipo=bajas</span></p>");
	
	str.append("<p class=Default style='text-align:justify'><span style='font-size:11.0pt'>&nbsp;</span></p>");
	str.append("<p class=MsoNormal style='text-align:justify'><span style='position:absolute;z-index:-1;left:0px;margin-left:-4px;margin-top:12px;width:183px;height:56px'>");
	str.append("<img width=183 height=56 src=\"cid:image\"></span></p>");
	str.append("</div>");

		return str.toString();
	}
}
