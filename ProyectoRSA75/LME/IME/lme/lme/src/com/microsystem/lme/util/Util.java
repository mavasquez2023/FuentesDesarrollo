/*
 * Created on 11-11-2011
 *
 */
package com.microsystem.lme.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.microsystem.lme.svc.exception.SvcException;

/**
 * @author microsystem
 *
 */
public class Util {

	//private static LoggerHelper logger = new LoggerHelper();
	private static Logger logger = Logger.getLogger(Util.class);
	private final static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat formatoFecha3 = new SimpleDateFormat("yyyyMM");
	private final static SimpleDateFormat formatoHora = new SimpleDateFormat("HHmmss");
	private final static SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private final static SimpleDateFormat formatoFechaFec = new SimpleDateFormat("dd/MM/yyyy");

	public static String formateaFechaHora(Date fechaHora){
		return formatoFechaHora.format(fechaHora);
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getToday() {
		Date now = new Date();
		return formatoFecha.format(now);
	}
	
	public static String getToday_dd_MM_yyy() {
		Date now = new Date();
		return formatoFechaFec.format(now);
	}

	public static String getHour() {
		Date now = new Date();
		return formatoHora.format(now);
	}

	public static String getPeriod() {
		Date now = new Date();
		return formatoFecha3.format(now);
	}

	private static String strFormat(String msg, Object[] paramArrayOfObject) {
		return MessageFormat.format(msg, paramArrayOfObject);
	}

	public static String obtieneSoloHoraDate(Date fechaCompleta) {
		return formatoHora.format(fechaCompleta);
	}

	public static String obtieneSoloFechaDate(Date fechaCompleta) {
		return formatoFecha.format(fechaCompleta);
	}

	public static String obtieneSoloHoraString(String fechaCompleta) {
		if (fechaCompleta != null && !fechaCompleta.equals("")) {
			return fechaCompleta.split("T")[1].replaceAll("\\:", "");
		}
		return "000000";
	}

	public static String obtieneSoloFechaString(String fechaCompleta) {
		if (fechaCompleta != null && !fechaCompleta.equals("")) {
			return fechaCompleta.split("T")[0].replaceAll("\\-", "");
		}
		return "19000101";
	}
	
	//REQ-8000001332 - elimina el establecimiento del timezone a GMT
	public static String obtieneSoloHora(Date fechaCompleta) {
		return formatoHora.format(fechaCompleta);
	}
	//REQ-8000001332 - elimina el establecimiento del timezone a GMT
	public static String obtieneSoloFecha(Date fechaCompleta) {
		return formatoFecha.format(fechaCompleta);
	}
	
	public static Date desplazarHora(Date fechaHoraIn, int desplazamiento){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaHoraIn);
		//calendar.setTimeZone(TimeZone.getTimeZone("Chile/Continental")); // NO SURTE EFECTO
		//System.out.println(calendar.getTimeZone());
		calendar.add(Calendar.HOUR, desplazamiento);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @param urlStr
	 * @param dir
	 * @param rutAfi
	 * @param rutEmp
	 * @return File name generated
	 * @throws SvcException
	 */
	public static String getUrl(String urlStr, String dir, String rutAfi, String rutEmp) throws SvcException {
		String nameFile = strFormat(Constants.NAME_IMG, new Object[] { rutAfi, rutEmp, getPeriod() });
		try {
			URL url = new URL(urlStr);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();

			int contentLength = connection.getContentLength();
			//String contentType = connection.getContentType();

			ByteArrayOutputStream tmpOut;
			if (contentLength != -1) {
				tmpOut = new ByteArrayOutputStream(contentLength);
			} else {
				tmpOut = new ByteArrayOutputStream(16384);
			}

			byte[] buf = new byte[512];
			while (true) {
				int len = in.read(buf);
				if (len == -1) {
					break;
				}
				tmpOut.write(buf, 0, len);
			}
			in.close();
			tmpOut.close();

			if (tmpOut.size() == 0) {
				throw new SvcException("Empty url :" + urlStr);
			} else if (tmpOut.toString().indexOf("404 - Not Found") != -1) {
				throw new SvcException("404 - Not Found :" + urlStr);
			}

			byte[] array = tmpOut.toByteArray();

			FileOutputStream fos = new FileOutputStream(dir + nameFile + "pdf");
			fos.write(array);
			fos.close();
			return dir + nameFile + "pdf";
		} catch (MalformedURLException e) {

			throw new SvcException(e.getMessage());
		} catch (IOException e) {

			throw new SvcException(e.getMessage());
		}

	}

	//
	public static String decodeUTF8(String str) {

		if (null == str)
			return "";
		try {
			byte bytes[] = str.getBytes("UTF-8");//
			str = new String(bytes, "UTF-8");//"UTF-8"
		} catch (UnsupportedEncodingException e) {
			//logger.logError(e.getClass() + "; "+ e.getMessage());
			logger.error(e.getClass() + "; " + e.getMessage());
		}

		return str;
	}

	public static String dv(BigInteger num) {
		long M = 0, S = 1, T = num.longValue();
		//int M = 0, S = 1, T = num;
		for (; T != 0; T /= 10)
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		char r = (char) (S != 0 ? S + 47 : 75);
		return String.valueOf(r);
	}

	public static String stringFormat(String msg, Object[] paramArrayOfObject) {
		return MessageFormat.format(msg, paramArrayOfObject);
	}

	public static void log(String message) {
		//logger.logInfo(message);
		logger.info(message);
	}

	public static void log(String msg, Object[] paramArrayOfObject) {
		log(stringFormat(msg, paramArrayOfObject));
	}

	public static String xmlError(String e) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
		xml += "<error>" + e + "</error>";
		return xml;
	}

	public static String jsonMsg(String e) {
		//String json = "{msg:"+e+"}";		
		return stringFormat("{msg:{0}}", new Object[] { e });
	}

	public static Date date(String formated, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(formated);
	}

	public static Calendar cal(String formated, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(simpleDateFormat.parse(formated));
		return cal;
	}

	public static String transformXmlXsl(String xml) {
		String xsl = "lme/JavaSource/lme/resources/zone.xsl";
		String res = "";
		try {
			//InputStream inputStream = null;
			//Classloader

			Source xmlSource = new StreamSource(xml);
			Source xsltSource = new StreamSource(new File(xsl));

			StringWriter cadenaSalida = new StringWriter();

			Result bufferResultado = new StreamResult(cadenaSalida);

			TransformerFactory factoriaTrans = TransformerFactory.newInstance();
			Transformer transformador = factoriaTrans.newTransformer(xsltSource);

			transformador.transform(xmlSource, bufferResultado);

			res = cadenaSalida.toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static long getTiempoRestante(long inicio) {
		return System.currentTimeMillis() - inicio;
	}

}
