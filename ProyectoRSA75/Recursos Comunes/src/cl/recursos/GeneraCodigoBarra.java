package cl.recursos;

import com.lowagie.Ean128JpegFile;

/**
 * @author Usist24
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */

public class GeneraCodigoBarra {

	public static void main(String[] args) {
		String base= "81000000000000";
		int rangoini=1220000;
		int rangofin=1250000;
		System.out.println("Inicio generación Código de Barra, desde:" + rangoini + ", hasta:" + rangofin);
		try {
				for(int i=rangoini; i<rangofin;i++){
					String numstr= String.valueOf(i);
					String codigo= base.substring(0, base.length()-numstr.length()) + numstr;
					Ean128JpegFile.CreateEan128(codigo, "/tmp/codigobarras/");
				}
				System.out.println("Fin generación Código de Barra");
		} catch(Exception e) {
				System.out.println("ERROR AL GENERAR CODIGO de BARRAS");
				e.printStackTrace();
		}
	}
	/*
	 try {	
			long rangoini=Long.parseLong(args[0]);
			long rangofin=Long.parseLong(args[1]);
			String carpeta= args[2];
			System.out.println("Inicio generación Código de Barra, desde:" + rangoini + ", hasta:" + rangofin + ", se genererán en la carpeta local:" + args[2]);
			for(long i=rangoini; i<rangofin;i++){
				String codigo= String.valueOf(i);
				System.out.println("Generando CB:" + codigo + ".");
				Ean128JpegFile.CreateEan128(codigo, carpeta);
			}
				System.out.println("Fin generación Código de Barra, ahora debe copiar las imagenes a la carpeta 'imagenes' en Domino");
		} catch(Exception e) {
				System.out.println("ERROR AL GENERAR CODIGO de BARRAS");
				System.out.println("parámetros válidos: <codigo inicial> <codigo final> <carpeta temporal>");
				System.out.println("ejemplo: 81000001080700 81000001090000 /tmp/codigobarra/");
				e.printStackTrace();
		}
	  
	 */
}
