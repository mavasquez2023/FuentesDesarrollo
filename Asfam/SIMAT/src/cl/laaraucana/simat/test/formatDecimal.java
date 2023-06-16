package cl.laaraucana.simat.test;

import java.text.DecimalFormat;

import cl.laaraucana.simat.entidades.ResultadoPaseContable;
import cl.laaraucana.simat.mannagerDB2.PaseContableManager;
import cl.laaraucana.simat.utiles.ManejoEspacios;

public class formatDecimal {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ManejoEspacios me = new ManejoEspacios();
		String aux = "-9999999";
		long number = 0;
		long numberB = -1;
		String adap = "";
		String result = "";

		number = Long.parseLong(aux);
		adap = String.valueOf(number);
		DecimalFormat montoFormato = new DecimalFormat("#,###.##");
		result = montoFormato.format(number);

		//ManejoFormatoNumeros mfn=new ManejoFormatoNumeros();	
		//result=mfn.getFormatoDecimal(number);

		System.out.println("number.: " + number);
		System.out.println("adap...: " + adap);
		System.out.println("result.: " + result);
		System.out.println("test sum: " + (number - numberB));
		
		///
//		PaseContableManager paseMgr = new PaseContableManager();
//		ResultadoPaseContable res = paseMgr.generarPaseContable();
//		System.out.println(res.getMensaje());
	}
}
