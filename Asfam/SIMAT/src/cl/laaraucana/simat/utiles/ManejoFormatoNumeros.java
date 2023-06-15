package cl.laaraucana.simat.utiles;

import java.text.DecimalFormat;

/*
 * 
 * */
public class ManejoFormatoNumeros {

	public String getFormatoDecimal(int number){
		DecimalFormat montoFormato = new DecimalFormat("#,###.##");		
		return montoFormato.format(number);
	}
	public String getFormatoDecimal(long number){
		//DecimalFormat montoFormato = new DecimalFormat("#,###.##");
		DecimalFormat montoFormato = new DecimalFormat("#,##0;-#,##0");
		return montoFormato.format(number);
	}
	
	public String getFormatoAbsoluto(long number){
		number=Math.abs(number);
		String ret=Long.toString(number);
		return ret;
	}
}//end ManejoFormatoNumeros
