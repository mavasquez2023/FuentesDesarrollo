package utilMonth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fecha {
	
	public Date aDate(String fecha){
		
		 
		  
	    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
	    String strFecha = fecha;
	    Date date = null;
	    try {

	        date = formatoDelTexto.parse(strFecha);

	    } catch (ParseException ex) {

	        ex.printStackTrace();

	    }

	    
	 
	
	
	return date;
}

	public Integer getFecha(String meses){
	
		try{	int m,a;
		switch(Integer.parseInt(meses)){
		case 0: a=0;m=1;break;
		case 1: a=1;m=0;break;
		case 2: a=2;m=0;break;
		case 3: a=3;m=0;break;
		case 4: a=4;m=0;break;
		case 5: a=5;m=0;break;
		default:a=1000;m=0;
		}
		int fecha;
		Date hoy = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd",new Locale("Cl"));
		java.sql.Date sqldate=new java.sql.Date(hoy.getTime());
		String date=sdf.format(sqldate);
		 
		fecha=a*100 + m;
		int year=Integer.parseInt(date.substring(0,4));
		int mes=Integer.parseInt(date.substring(5,7));
		int fechaActual=year*100 + mes;
		Integer fechaFinal=new Integer(fechaActual-fecha);
		System.out.println("Fecha Actual:" + fechaActual + ", Fecha Final:" + fechaFinal);	
		return fechaFinal;
		
		}catch(Exception ex)
		{ex.printStackTrace();}
		
		return null;
	}
	
	public Integer getFechaNow(){
		
		try{	
		
		
		Date hoy = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd",new Locale("Cl"));
		java.sql.Date sqldate=new java.sql.Date(hoy.getTime());
		String date=sdf.format(sqldate);
		 
		
		int year=Integer.parseInt(date.substring(0,4));
		int mes=Integer.parseInt(date.substring(5,7));
		Integer fechaActual=new Integer(year*100 + mes);
		
			
		return fechaActual;
		
		}catch(Exception ex)
		{ex.printStackTrace();}
		
		return null;
	}
	
	
	
	
	
}
