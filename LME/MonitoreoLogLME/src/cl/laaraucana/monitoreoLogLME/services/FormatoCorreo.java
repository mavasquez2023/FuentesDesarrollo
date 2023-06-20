/**
 * 
 */
package cl.laaraucana.monitoreoLogLME.services;

/**
 * @author IBM Software Factory
 *
 */
public class FormatoCorreo {
	public static String getMailbody() {

		StringBuilder str = new StringBuilder();

		str.append("<div class=WordSection1> ");
	
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height: ");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif; ");
		str.append("color:#222222'>Problema con applicacion LME ");
		str.append( "</span></p> ");
		str.append("</div>");
	
		return str.toString();
	}
}
