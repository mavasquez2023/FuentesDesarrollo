package cl.laaraucana.cuentabancaria.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.Ostermiller.util.Base64;
import cl.araucana.core.util.Hex;

public class Utils {

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

}
