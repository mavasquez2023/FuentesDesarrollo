package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.laaraucana.silmsil.util.UtilProcesar;


public class TestDate {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		//String date = sdf.format(new Date()); 
		
		//System.out.println("->" + date);
		
		//yyyy-MM-dd
		String fecha="20140109";
		java.sql.Date mifecha =null;
		if(fecha!=null || fecha !=""){
			mifecha =  java.sql.Date.valueOf(fecha);
		}	
		//java.sql.Date sqlDate = new java.sql.Date(mifecha);
		
		System.out.println("mi fecha: "+mifecha);
		*/
		
		System.out.println("hr1: "+UtilProcesar.getHours());
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSS");
		String h ="";
		
		int vc=0;
		for(vc=0;vc<10;vc++){
			h = sdf.format(new Date());
			System.out.println("hr"+vc+": "+h);
		}
	}

}
