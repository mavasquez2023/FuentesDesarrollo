/**
 * 
 */
package cl.laaraucana.benef.utils;

import cl.laaraucana.benef.vo.BeneficioVO;

/**
 * @author IBM Software Factory
 *
 */
public class FormatoCorreo {
	public static String bodyClient(BeneficioVO beneficio ) {

		StringBuilder str = new StringBuilder();

		str.append("<body lang=ES-CL link=blue vlink=blue>");

		str.append("<div class=WordSection1>");

		str.append("<br>");
		str.append("<p class=MsoNormal align=right style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:left;line-height:normal'><span style='position:relative;z-index:");
		str.append("1;left:0px;top:-20px;width:645px;height:72px'><img width=645 height=72 ");
		str.append("src=\"cid:image\" /></span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal align=center style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;text-autospace:none'><b><span style='font-size:14.0pt;");
		str.append("line-height:115%;font-family:\"MyriadPro-Black\",\"sans-serif\";color:#1F497D;");
		str.append("letter-spacing:1.0pt'>BENEFICIO CENA ANIVERSARIO DE MATRIMONIO </span></b></p>");

		str.append("<p class=MsoNormal align=center style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:center;text-autospace:none'><b><span style='font-size:14.0pt;");
		str.append("line-height:115%;font-family:\"MyriadPro-Black\",\"sans-serif\";color:#1F497D;");
		str.append("letter-spacing:1.0pt'>ID: " + beneficio.getCodigoBeneficio() + "</span></b></p>");
		str.append("<br><br>");
		
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><b><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626;background:white'>Estimado(a) </span></b><span ");
		str.append("style='font-size:10.0pt;font-family:\"Arial\",\"sans-serif\";color:#262626;");
		str.append("background:white'>" + beneficio.getNombre() + " " + beneficio.getApellidoPaterno()+ "<b> </b></span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Sabemos lo importante que es ");
		str.append("para ustedes el haber cumplido un año más juntos a su pareja. </span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Compartimos vuestra alegría y ");
		str.append("le hacemos llegar nuestras Felicitaciones en su Aniversario de Matrimonio.</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Por este motivo, es muy grato ");
		str.append("para La Araucana, permitirnos entregar el beneficio de Aniversario, consistente ");
		str.append("en una Cena en cualquier Hotel Ibis del país.</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;text-align:");
		str.append("justify;line-height:normal;text-autospace:none'><span style='font-size:10.0pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#262626'>Recuerde concretar la cena ");
		str.append("realizando la reserva en cualquier Hotel Ibis del país, presentando esta solicitud.</span></p>");

		str.append("<br clear=ALL>");

		str.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=643 ");
		str.append("style='width:17.0cm;margin-left:5.4pt;border-collapse:collapse'>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=643 colspan=2 style='width:17.0cm;background:#1F497D;padding:0cm 5.4pt 0cm 5.4pt; ");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append(" text-align:center;line-height:normal;text-autospace:none'><b><span ");
		str.append(" style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\";color:white'>ANTECEDENTES ");
		str.append(" BENEFICIO</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>ID ");
		str.append(" BENEFICIO</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>" + beneficio.getCodigoBeneficio() + "</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>FECHA ");
		str.append(" BENEFICIO</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>" + beneficio.getFechaGeneracionBeneficio() + "</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>RUT</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>" + beneficio.getRutBeneficiario()+ "-"+beneficio.getDvBeneficiario() + "</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>NOMBRE</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>");
		str.append( beneficio.getNombre() + " " + beneficio.getApellidoPaterno() + " </span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>SUCURSAL</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;background:#E2E2E2;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>" + beneficio.getSucursalBeneficio() + "</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>E-MAIL</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;padding:0cm 5.4pt 0cm 5.4pt;height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'><a ");
		str.append(" href=\"mailto:" + beneficio.getEmail() + "\">" + beneficio.getEmail() + "</a> </span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("<tr style='height:20.5pt'>");
		str.append(" <td width=236 style='width:177.2pt;background:#D9D9D9;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>CELULAR</span></p>");
		str.append(" </td>");
		str.append(" <td width=406 style='width:304.75pt;background:#D9D9D9;padding:0cm 5.4pt 0cm 5.4pt;");
		str.append(" height:20.5pt'>");
		str.append(" <p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append(" normal;text-autospace:none'><b><span style='font-size:9.0pt;font-family:\"Arial\",\"sans-serif\"'>" + beneficio.getCelular() + "</span></b></p>");
		str.append(" </td>");
		str.append("</tr>");
		str.append("</table>");

		str.append("<p class=MsoNormal align=center style='margin-bottom:0cm;margin-bottom:.0001pt;");
		str.append("text-align:left;line-height:normal;text-autospace:none'><span ");
		str.append("style='font-size:7.5pt;font-family:\"Arial\",\"sans-serif\";color:#595959;");
		str.append("letter-spacing:1.0pt'>Este e-mail es generado automáticamente, por favor no ");
		str.append("responder.</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='width:640px;margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-align:justify;text-autospace:none'><b><span style='font-size:7.5pt;font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#333333'>Aviso de Confidencialidad</span></b><span style='font-size:7.5pt;");
		str.append("font-family:\"Arial\",\"sans-serif\";color:#333333'>&nbsp;<br>");
		
		str.append("Este correo y la información contenida o adjunta al mismo es privada y ");
		str.append("confidencial y va dirigida exclusivamente a su destinatario. La Araucana ");
		str.append("informa a quien pueda haber recibido este correo por error que contiene ");
		str.append("información confidencial cuyo uso, copia, reproducción o distribución está ");
		str.append("expresamente prohibida. Si no es usted el destinatario del mismo y recibe este ");
		str.append("correo por error, le rogamos lo ponga en conocimiento del emisor y proceda a su ");
		str.append("eliminación sin copiarlo, imprimirlo o utilizarlo de ningún modo.&nbsp;</span></p>");
		str.append("<br>");
		
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;text-autospace:none'><b><span style='font-family:\"Arial\",\"sans-serif\";");
		str.append("color:#1F497D;letter-spacing:1.0pt'><img border=0 width=642 height=72 ");
		str.append("src=\"cid:image2\" /></span></b></p>");

		str.append("</div>");

		str.append("</body>");

		return str.toString();
	}
}
