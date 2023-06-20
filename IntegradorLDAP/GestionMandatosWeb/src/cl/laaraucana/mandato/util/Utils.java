package cl.laaraucana.mandato.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
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
import cl.laaraucana.mandato.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandato.vo.DatosMandatoVo;

public class Utils {
	private static DateFormat formatoSAP = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	
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
	
	public static String emailClienteMandatoRechazo(MandatoAS400Vo vo) {

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
		str.append("color:#222222'>Su mandato de transferencia registrado en  La Araucana, ha presentado  el siguiente error, &nbsp;");
		str.append("el cual fue informado por el banco al momento de realizar el pago de su beneficio.&nbsp;");
		str.append("</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");

		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Observación: " + vo.getObservaciones()  + "</span></p>");
		
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>&nbsp;</span></p>");
		
		str.append("<p class=MsoNormal style='margin-bottom:0cm;margin-bottom:.0001pt;line-height:");
		str.append("normal;background:white'><span style='font-size:12.0pt;font-family:\"Arial\",sans-serif;");
		str.append("color:#222222'>Favor revisar la información de su mandato y actualizar mediante la subscripción &nbsp;");
		str.append("de un nuevo mandato si corresponde en nuestro portal www.laaucana.cl.&nbsp;");
		str.append("</span></p>");
		
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
	
	public static String cerosLeft(long number) {

		String aux = "00000000";

		aux = aux + String.valueOf(number);

		int len = aux.length();

		return aux.substring(len - 8, len);
	}
	
	//webservice ********************
	
	@Resource
	private WebServiceContext wsCtx;

	public String isValidToken(HttpServletRequest request, String token) {
		String usuario = "";
		try {
			String[] data = decodeToken(token);
			String remoteip = request.getRemoteAddr();

			String tokenFactory = TokenFactory.getInstance().getToken(data[1]);
			String dataTokenFacory[] = decodeToken(tokenFactory);

			String fecha = dataTokenFacory[2];
			usuario = dataTokenFacory[1];
			if (fecha != null) {
				if (validaFecha(fecha)) {
					if (remoteip.equals(data[0])) {
						return usuario;
					}
				} else {
					TokenFactory.getInstance().delToken(data[0]);
				}
			}
			/*
			 * MessageContext mctx = wsCtx.getMessageContext(); String remoteip=
			 * ((HttpServletRequest)(wsCtx.getMessageContext().get(MessageContext.
			 * SERVLET_REQUEST))).getRemoteAddr(); String username =
			 * mctx.get("userid").toString(); String password =
			 * mctx.get("password").toString(); if(username.equals("WSAraucanaTGR")){
			 * if(password.equals("srv12345")){ return true; } }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String generaToken(String arg0, String arg1) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date());
		String token = Hex.encode(Base64.encode(arg0 + ":" + arg1 + ":" + date));
		return token;
	}

	public static String[] decodeToken(String token) {
		String salida[] = new String[3];
		String data = Base64.decode(Hex.decode(token));
		StringTokenizer st = new StringTokenizer(data, ":");
		if (st.countTokens() == 3) {
			salida[0] = st.nextToken(); // ip
			salida[1] = st.nextToken(); // usuario
			salida[2] = st.nextToken(); // fecha
		} else {
			salida[0] = "";
			salida[1] = "";
			salida[2] = "";
		}
		return salida;

	}
	public static String dateToString(Date fecha) {
		return formatoSAP.format(fecha);
	}
	
	public static String timeToString(Date fecha) {
		return formatoHora.format(fecha);
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

	@SuppressWarnings("resource")
	public static void descargar(String path, String filename, byte[] bytes) throws IOException, FileNotFoundException {
		// Initialize a pointer
		// in file using OutputStream
		OutputStream os;

		os = new FileOutputStream(path + filename);

		// Starts writing the bytes in it
		os.write(bytes);
		os.flush();
		os.close();
	}

}
