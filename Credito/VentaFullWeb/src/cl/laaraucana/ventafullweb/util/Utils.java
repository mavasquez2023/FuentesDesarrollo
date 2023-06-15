package cl.laaraucana.ventafullweb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;

import cl.laaraucana.ventafullweb.vo.AgendaVo;
import cl.laaraucana.ventafullweb.vo.SalidaEvaluadorModeloAISVo;
import cl.laaraucana.ventafullweb.ws.ClienteFeriadosAnuales;

public class Utils {
	private static DateFormat formatoWebFull = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat formatoFechaBD = new SimpleDateFormat("dd-MM-yyyy");
	private static DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	
	public static Date stringToDateTime(String fecha) throws ParseException {
		return formatoWebFull.parse(fecha);
	}
	
	public static Date stringToDate(String fecha) throws ParseException {
		return formatoFecha.parse(fecha);
	}
	
	public static Date stringToDateBD(String fecha) throws ParseException {
		return formatoFechaBD.parse(fecha);
	}
	
	public static String getDateHour() throws ParseException {
		return formatoWebFull.format(new Date());
	}
	
	public static String getFecha() throws ParseException {
		return formatoFecha.format(new Date());
	}
	
	public static String getHora() throws ParseException {
		return formatoHora.format(new Date());
	}
	
	public static List<AgendaVo> obtieneAgenda(String fecha) {
		List<AgendaVo> agenda = new ArrayList<AgendaVo>();
		Date dt = new Date();
		if(fecha.length()!=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				dt = sdf.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar c = new GregorianCalendar(); 
		c.setTime(dt);
		int dia, mes, annio, semana;
		int numAgenda = 0;
		while(numAgenda<5) {
			dia = c.get(Calendar.DATE);
			mes = c.get(Calendar.MONTH) + 1;
			annio = c.get(Calendar.YEAR); 
			semana = c.get(Calendar.DAY_OF_WEEK);
			if(semana!=1 && semana!=7) {
				if(!verificaFeriado(dia, mes, annio)) {
					if(!esHoy(dia, mes, annio)) {
						AgendaVo diaAgendado = new AgendaVo();
						diaAgendado.setNombreDia(nombreDia(Integer.toString(semana)));
						diaAgendado.setNumDia(Integer.toString(dia));
						diaAgendado.setFecha(obtieneFecha(dia, mes, annio));
						agenda.add(diaAgendado);
						numAgenda++;
					}
				}
			} 
			c.add(Calendar.DATE, 1);
			dt = c.getTime();
		}
		return agenda;
	}
	
	
	public static boolean esHorarioHabil() {
		Calendar c = new GregorianCalendar(); 
		String semana;   
		int horai, minutoi;
		semana = Integer.toString(c.get(Calendar.DAY_OF_WEEK));
		if(verificaFeriado(c.get(Calendar.DATE), c.get(Calendar.MONTH), c.get(Calendar.YEAR))) {
			return false;
		}
		horai = c.get(Calendar.HOUR_OF_DAY);
		minutoi = c.get(Calendar.MINUTE);
		if(semana.equals("1") || semana.equals("7")) {
			return false;
		} else if(semana.equals("6")) {
			if(horai >= 9 && horai <= 16) {
				if(horai == 16 && minutoi > 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			if(horai >= 9 && horai <= 18) {
				if(horai == 18 && minutoi > 0) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}
	
	
	public static String nombreMes(String fecha) {
		String nombreMes = "";
		String sMes = fecha.substring(3, 5);
		switch(sMes) {
			case "01": nombreMes = "Enero"; break;
			case "02": nombreMes = "Febrero"; break;
			case "03": nombreMes = "Marzo"; break;
			case "04": nombreMes = "Abril"; break;
			case "05": nombreMes = "Mayo"; break;
			case "06": nombreMes = "Junio"; break;
			case "07": nombreMes = "Julio"; break;
			case "08": nombreMes = "Agosto"; break;
			case "09": nombreMes = "Septiembre"; break;
			case "10": nombreMes = "Octubre"; break;
			case "11": nombreMes = "Noviembre"; break;
			case "12": nombreMes = "Diciembre"; break;
		}
		return nombreMes;
	}
	
	
	public static String nombreFecha(String fecha) {
		Date dt = new Date();
		int dia, annio, semana;
		String sDia, sAnnio;
		String nombreFecha = "";
		if(fecha.length()!=0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				dt = sdf.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
			Calendar c = new GregorianCalendar(); 
			c.setTime(dt);
			dia = c.get(Calendar.DATE);
			annio = c.get(Calendar.YEAR); 
			semana = c.get(Calendar.DAY_OF_WEEK);
			sDia = dia<10?"0"+Integer.toString(dia):Integer.toString(dia);
			sAnnio = Integer.toString(annio);
			nombreFecha = nombreDia(Integer.toString(semana));
			nombreFecha = nombreFecha + " " + sDia;
			nombreFecha = nombreFecha + " de " + nombreMes(fecha);
			nombreFecha = nombreFecha + " del " + sAnnio;
			return nombreFecha;
		} else {
			return null;
		}
	}
	
	
	public static String fechaHoraActual() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | hh:mm:ss");
		return sdf.format(dt);
	}
	
	
	public static boolean verificaFeriado(int dia, int mes, int annio) {
		ClienteFeriadosAnuales clienteFeriadosAnuales = new ClienteFeriadosAnuales();
		String diaFec,mesFec,fecha;
		diaFec = dia<10?"0"+String.valueOf(dia):String.valueOf(dia);
		mesFec = mes<10?"0"+String.valueOf(mes):String.valueOf(mes);
		fecha = String.valueOf(annio) + "-" + mesFec + "-" + diaFec;
		if(clienteFeriadosAnuales.getValidaFeriados(String.valueOf(annio), fecha)) {
			return true;
		}
		return false;
	}
	
	
	private static boolean esHoy(int dia, int mes, int annio) {
		Date dt = new Date();
		Calendar c = new GregorianCalendar(); 
		c.setTime(dt);
		int diax = c.get(Calendar.DATE);
		int mesx = c.get(Calendar.MONTH) + 1;
		int anniox = c.get(Calendar.YEAR);
		if(dia==diax && mes==mesx && annio==anniox) {
			return true;
		}
		return false;
	}


	private static String obtieneFecha(int dia, int mes, int annio) {
		String sDia, sMes, sAnnio;
		sDia = dia<10?"0"+Integer.toString(dia):Integer.toString(dia);
		sMes = mes<10?"0"+Integer.toString(mes):Integer.toString(mes);
		sAnnio = Integer.toString(annio);
		return sDia + "/" + sMes + "/" + sAnnio;
	}	
	
	
	private static String nombreDia(String dayOfWeek) {
		String nomSemana = "";
		switch(dayOfWeek) {
			case "1": nomSemana = "Domingo"; break;
			case "2": nomSemana = "Lunes"; break;
			case "3": nomSemana = "Martes"; break;
			case "4": nomSemana = "Miercoles"; break;
			case "5": nomSemana = "Jueves"; break;
			case "6": nomSemana = "Viernes"; break;
			case "7": nomSemana = "Sabado"; break;
		}
		return nomSemana;
	}
	
	public static String getbodyAgenteYEjecutivo(String nroOfertaEnCurso, String nombreAfiliado, String rutAfiliado, String descEstadoOferta, String celular, String nombreAgenteEjecutivo) {

		StringBuilder str = new StringBuilder();
		
		str.append("<body style='width:100%;font-family:arial, ´helvetica neue´, helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0'>");
		str.append("  <div class='es-wrapper-color' style='background-color:#F6F6F6'><!--[if gte mso 9]>");
		str.append("			<v:background xmlns:v='urn:schemas-microsoft-com:vml' fill='t'>");
		str.append("				<v:fill type='tile' color='#f6f6f6'></v:fill>");
		str.append("			</v:background>");
		str.append("		<![endif]-->");
		str.append("   <table class='es-wrapper' width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6'>");
		str.append("     <tr>");
		str.append("      <td valign='top' style='padding:0;Margin:0'>");
		str.append("       <table class='es-content' cellspacing='0' cellpadding='0' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>");
		str.append("         <tr>");
		str.append("          <td align='center' style='padding:0;Margin:0'>");
		str.append("           <table class='es-content-body' cellspacing='0' cellpadding='0' bgcolor='#ffffff' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'><!--[if mso]><table style='width:560px' cellpadding='0' cellspacing='0'><tr><td style='width:180px' valign='top'><![endif]-->");
		str.append("               <table cellpadding='0' cellspacing='0' class='es-left' align='left' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left'>");
		str.append("                 <tr>");
		str.append("                  <td class='es-m-p0r es-m-p20b' valign='top' align='center' style='padding:0;Margin:0;width:180px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen logo
		str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagelogo\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='180'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table><!--[if mso]></td><td style='width:20px'></td><td style='width:360px' valign='top'><![endif]-->");
		str.append("               <table class='es-right' cellpadding='0' cellspacing='0' align='right' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right'>");
		str.append("                 <tr>");
		str.append("                  <td align='left' style='padding:0;Margin:0;width:360px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0;display:none'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table><!--[if mso]></td></tr></table><![endif]--></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td valign='top' align='center' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen header
		//str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagehead\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='560'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("           </table></td>");
		str.append("         </tr>");
		str.append("       </table>");
		str.append("       <table class='es-footer' cellspacing='0' cellpadding='0' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top'>");
		str.append("         <tr>");
		str.append("          <td align='center' style='padding:0;Margin:0'>");
		str.append("           <table class='es-footer-body' cellspacing='0' cellpadding='0' bgcolor='#ffffff' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'>");
		// Texto del correo
		str.append("Estimado (a) " + nombreAgenteEjecutivo + ",");
		str.append("						</p></td>");
		str.append("                     </tr>");
		str.append("                     <tr>");
		str.append("                      <td align='left' style='padding:40px;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("Le informamos que el afiliado " + nombreAfiliado + ", RUT " + rutAfiliado + " se encuentra evaluando una simulación web en el portal de La Araucana pero ya se encuentra creada la oferta " + nroOfertaEnCurso + " con el estado " + descEstadoOferta + ".</p>");
		str.append("						<p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("Le solicitamos que se comunique a la brevedad con el Afiliado al n° celular " + celular + ".</p>");
		str.append("						<p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p></td>");
		str.append("                     </tr>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'>");
		// Texto del correo
		str.append("Atentamente.");
		str.append("						</p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><strong>");
		// Texto del correo
		str.append("Caja de compensación La Araucana.");
		str.append("						</strong></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><strong></strong></p></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen footer
		str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagefooter\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='560'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='left' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("           </table></td>");
		str.append("         </tr>");
		str.append("       </table></td>");
		str.append("     </tr>");
		str.append("   </table>");
		str.append("  </div>");
		str.append(" </body>");
        
		return str.toString();
	}
	
	public static String getbodyAfiliado(String nombreAfiliado, String fechaSolicitud, String montoSimulado, String montoCuota, String cuotas) {

		StringBuilder str = new StringBuilder();
		
		str.append("<body style='width:100%;font-family:arial, ´helvetica neue´, helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0'>");
		str.append("  <div class='es-wrapper-color' style='background-color:#F6F6F6'><!--[if gte mso 9]>");
		str.append("			<v:background xmlns:v='urn:schemas-microsoft-com:vml' fill='t'>");
		str.append("				<v:fill type='tile' color='#f6f6f6'></v:fill>");
		str.append("			</v:background>");
		str.append("		<![endif]-->");
		str.append("   <table class='es-wrapper' width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#F6F6F6'>");
		str.append("     <tr>");
		str.append("      <td valign='top' style='padding:0;Margin:0'>");
		str.append("       <table class='es-content' cellspacing='0' cellpadding='0' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%'>");
		str.append("         <tr>");
		str.append("          <td align='center' style='padding:0;Margin:0'>");
		str.append("           <table class='es-content-body' cellspacing='0' cellpadding='0' bgcolor='#ffffff' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'><!--[if mso]><table style='width:560px' cellpadding='0' cellspacing='0'><tr><td style='width:180px' valign='top'><![endif]-->");
		str.append("               <table cellpadding='0' cellspacing='0' class='es-left' align='left' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left'>");
		str.append("                 <tr>");
		str.append("                  <td class='es-m-p0r es-m-p20b' valign='top' align='center' style='padding:0;Margin:0;width:180px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen logo
		str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagelogo\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='180'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table><!--[if mso]></td><td style='width:20px'></td><td style='width:360px' valign='top'><![endif]-->");
		str.append("               <table class='es-right' cellpadding='0' cellspacing='0' align='right' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right'>");
		str.append("                 <tr>");
		str.append("                  <td align='left' style='padding:0;Margin:0;width:360px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0;display:none'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table><!--[if mso]></td></tr></table><![endif]--></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td valign='top' align='center' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table width='100%' cellspacing='0' cellpadding='0' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen header
		str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagehead\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='560'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("           </table></td>");
		str.append("         </tr>");
		str.append("       </table>");
		str.append("       <table class='es-footer' cellspacing='0' cellpadding='0' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top'>");
		str.append("         <tr>");
		str.append("          <td align='center' style='padding:0;Margin:0'>");
		str.append("           <table class='es-footer-body' cellspacing='0' cellpadding='0' bgcolor='#ffffff' align='center' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px'>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'>");
		// Texto del correo
		str.append("Estimado (a) " + nombreAfiliado + ",");
		str.append("						</p></td>");
		str.append("                     </tr>");
		str.append("                     <tr>");
		str.append("                      <td align='left' style='padding:40px;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("Junto con saludar, te informamos que la simulación realizada hoy " + fechaSolicitud + "&nbsp;con un monto de $" + AfiliadoUtils.formateaVal(montoSimulado) + ", cuota de $" + AfiliadoUtils.formateaVal(montoCuota) + " y plazo de " + cuotas + " meses ha sido <b>aprobada<b/>.");
		str.append("						<br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("El monto de la cuota es referencial y puede variar en días siguientes. Esta oferta tiene vigencia de 7 días corridos.");
		str.append("						<br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("Serás contactado a la brevedad por nuestros Ejecutivos.");
		str.append("						<br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'>");
		// Texto del correo
		str.append("Ante cualquier duda o consulta, favor llama a nuestro contact center 600 4228 100.");
		str.append("						</p></td>");
		str.append("                     </tr>");
		str.append("                     <tr>");
		str.append("                      <td align='center' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'>Atentamente.</p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><strong>Caja de compensación La Araucana.</strong></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#30a1be;font-size:14px'><strong></strong><br></p></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		// Imagen footer
		str.append("                      <td align='center' style='padding:0;Margin:0;font-size:0px'><img class='adapt-img' src=\"cid:imagefooter\" alt style='display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic' width='560'></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("             <tr>");
		str.append("              <td align='left' style='padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px'>");
		str.append("               <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                 <tr>");
		str.append("                  <td align='center' valign='top' style='padding:0;Margin:0;width:560px'>");
		str.append("                   <table cellpadding='0' cellspacing='0' width='100%' style='mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px'>");
		str.append("                     <tr>");
		str.append("                      <td align='left' style='padding:0;Margin:0'><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p><p style='Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, ´helvetica neue´, helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px'><br></p></td>");
		str.append("                     </tr>");
		str.append("                   </table></td>");
		str.append("                 </tr>");
		str.append("               </table></td>");
		str.append("             </tr>");
		str.append("           </table></td>");
		str.append("         </tr>");
		str.append("       </table></td>");
		str.append("     </tr>");
		str.append("   </table>");
		str.append("  </div>");
		str.append(" </body>");
        
		return str.toString();
	}
	
	public static JSONObject formatJsonMotorAIS(JSONObject salidaOut, String codigoError, String respuestaXML) {

		int indexPoliticas     = respuestaXML.indexOf("POLITICAS");
		int indexReglasNegocio = respuestaXML.indexOf("REGLASNEGOCIO");
		
		String vectorPoliticas     = respuestaXML.substring((indexPoliticas + 10), (indexPoliticas + 19));
		String vectorReglasNegocio = respuestaXML.substring((indexReglasNegocio + 14), (indexReglasNegocio + 36));
		
		try {
			salidaOut.remove("POLITICAS");
			salidaOut.remove("REGLASNEGOCIO");
			salidaOut.put("CodigoError", codigoError);
			salidaOut.put("POLITICAS", vectorPoliticas);
			salidaOut.put("REGLASNEGOCIO", vectorReglasNegocio);
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		
		return salidaOut;
	}
	
	public static String getVectorPoliticaAIS(SalidaEvaluadorModeloAISVo respuestaMotorAIS) {
		String salida = "";
		
		String politicas = respuestaMotorAIS.getPoliticas();
		
		String[] politicasArray = politicas.split("");
		
		for (int i = 0; i < politicasArray.length; i++) {
			if(politicasArray[i].equals("1") && i == 0) {
				salida += "F_Fallecido ";
			} else if(politicasArray[i].equals("1") && i == 1) {
				salida += "F_NoAfiliado ";
			} else if(politicasArray[i].equals("1") && i == 2) {
				salida += "F_Edad ";
			} else if(politicasArray[i].equals("1") && i == 3) {
				salida += "F_Nacionalidad ";
			} else if(politicasArray[i].equals("1") && i == 4) {
				salida += "F_Cotizacion ";
			} else if(politicasArray[i].equals("1") && i == 5) {
				salida += "F_PersonaNatural ";
			} else if(politicasArray[i].equals("1") && i == 6) {
				salida += "F_Castigo ";
			} else if(politicasArray[i].equals("1") && i == 7) {
				salida += "F_Intercaja ";
			} else if(politicasArray[i].equals("1") && i == 8) {
				salida += "F_Funcionario ";
			}
		}
		
		return salida;
	}
	
	public static String getVectorReglaNegocioAIS(SalidaEvaluadorModeloAISVo respuestaMotorAIS) {
		String salida = "";
		
		String ReglasNegocio = respuestaMotorAIS.getReglasNegocio();
		
		String[] reglasNegocioArray = ReglasNegocio.split("");
		
		for (int i = 0; i < reglasNegocioArray.length; i++) {
	        if(reglasNegocioArray[i].equals("1") && i == 0) {
	            salida += "F_Insolvencia ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 1) {
	            salida += "F_Insolvencia2 ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 2) {
	            salida += "F_PEP ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 3) {
	            salida += "F_EMP_MANTENEDOR ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 4) {
	            salida += "F_AFI_MANTENEDOR ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 5) {
	            salida += "F_EMP_BLOQUEO ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 6) {
	            salida += "F_AFI_BLOQUEO ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 7) {
	            salida += "F_AFP ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 8) {
	            salida += "F_PBS ";
	        }  else if(reglasNegocioArray[i].equals("1") && i == 9) {
	            salida += "F_DESAFILIACION ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 10) {
	            salida += "F_REPRO_VIG ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 11) {
	            salida += "F_MORA ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 12) {
	            salida += "F_AFP_1 ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 13) {
	            salida += "F_LICENCIA ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 14) {
	            salida += "F_LICENCIA2 ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 15) {
	            salida += "F_RIESGOEMP ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 16) {
	            salida += "F_UCHILE ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 17) {
	            salida += "F_DEUDORDIRECTO ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 18) {
	            salida += "Var_Cotización ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 19) {
	            salida += "F_PUBLICO_AFECTO ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 20) {
	            salida += "F_PENSIONADO ";
	        } else if(reglasNegocioArray[i].equals("1") && i == 21) {
	            salida += "F_Antiguedad_Laboral ";
	        } 
	    }
		
		return salida;
	}
	
	public static OfertasVigentes_DT getOfertaVigente(OfertasVigentes_DT[] respuestaWS) {
		OfertasVigentes_DT ofertaVigente = null;
		
		for (OfertasVigentes_DT ofertasVigentes : respuestaWS) {	
			String idEstadoSolicitud = ofertasVigentes.getID_ESTADO_ACTUAL();
			
			if(idEstadoSolicitud.equals("E0019") || idEstadoSolicitud.equals("E0021") || idEstadoSolicitud.equals("E0045") || idEstadoSolicitud.equals("E0064") || idEstadoSolicitud.equals("E0099") || idEstadoSolicitud.equals("E0174")) {
				ofertaVigente = ofertasVigentes;
			}
			
		}
		
		return ofertaVigente;
	}
	
	public static String getProvincia(String comuna) {
		String provinciaAfiliado = null;
		
		provinciaAfiliado = comuna.substring(0, 3);		
		
		int provinciaInt = Integer.parseInt(provinciaAfiliado);
	    provinciaAfiliado = Integer.toString(provinciaInt);
		
		return provinciaAfiliado;
	}

}
