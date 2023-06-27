package com.microsystem.lme.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Created on 18-11-2011
 *
 */

/**
 * @author jcea
 *
 */
public class TestModules {

	public static void main(String[] args) throws IOException {
		//		//addJar();
		//		//test();
		//		//		Date now = new Date();
		//		//		SimpleDateFormat shf = new SimpleDateFormat("HHmmss");
		//
		//		//		String a = "?ï¿½ï¿½ï¿½ Santa Luc&iacute;a";//"GONZï¿½LEZ ï¿½Ñ¨";
		//
		//		String startDate = "-06-27/-2007";
		//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		//		Date startDate1 = null;
		//		try {
		//			startDate1 = df.parse(startDate);
		//		} catch (ParseException e) {
		//			// TODO Bloque catch generado automáticamente
		//			e.printStackTrace();
		//		}
		//		Calendar cal = Calendar.getInstance();
		//		cal.setTime(startDate1);
		//
		//		System.out.println(set(cal));

	}

	public static String set(Calendar c) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = "19000101";
		if (null != c) {
			d = sdf.format(c.getTime());
		}
		return d;
	}

	public static String dv(int num) {
		int M = 0, S = 1, T = num;
		for (; T != 0; T = T / 10) {
			S = (S + T % 10 * (9 - M++ % 6)) % 11;
		}
		char r = ' ';
		if (S != 0)
			r = (char) (S + 47);
		else
			r = (char) 75;
		return String.valueOf(r);
	}

	/*
	private static void mimeTypeTika() throws IOException {
		FileInputStream is = null;		
		//MemoriaPFC_pdf  ERROR
		//concur-casestudy_pdf application/pdf
		//app_plugins_zip ok
		//web_rar		application/x-rar-compressed						
		
		//mapping_Integracion_xls application/vnd.ms-excel
		//book_xlsx		application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
		
		//cv_rtf		application/rtf
		//LME_doc		application/msword
		//LME_odt		application/vnd.oasis.opendocument.text
		//LME_docx		application/vnd.openxmlformats-officedocument.wordprocessingml.document
		//diseno_docx	application/vnd.openxmlformats-officedocument.wordprocessingml.document
		
		//LME_jpg		image/jpeg
		//presto_png	image/png
		String path = "S:/tmp/LME_pdf";
	    try {
	      File f = new File(path);
	      is = new FileInputStream(f);

	      ContentHandler contenthandler = new BodyContentHandler();
	      Metadata metadata = new Metadata();
	      metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());
	      Parser parser = (Parser) new AutoDetectParser();
	      // OOXMLParser parser = new OOXMLParser();
	      
	      parser.parse(is, contenthandler, metadata, new ParseContext());
	      System.out.println("Mime: " + metadata.get("Content-Type"));//Metadata.CONTENT_TYPE
	//	      System.out.println("Title: " + metadata.get(Metadata.TITLE));
	//	      System.out.println("Author: " + metadata.get(Metadata.AUTHOR));
	      //System.out.println("content: " + contenthandler.toString());
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    finally {
	        if (is != null) is.close();
	    }
		
	}
	
	*/

	/**
	 * 
	 */
	private static void addJar() {
		try {
			URL url = (new File("C:/home/lme/saaj.jar")).toURI().toURL();

			Method metodo = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
			metodo.setAccessible(true);
			metodo.invoke(ClassLoader.getSystemClassLoader(), new Object[] { url });
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public static void test() {
		System.out.println("ClassPath:");
		//		for (URL url : ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs()) {
		//			System.out.println("\t" + url.getFile());
		//		}
		URL[] arr = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
		for (int i = 0; i < arr.length; i++) {
			System.out.println("\t" + arr[i].getFile());
		}
		//
	}

}
