package cl.laaraucana.silmsil.util;

import java.io.*;
import java.util.zip.*;

public class UtilZip {
	
	/**
	 * Genera archivo ZIP con el contenido indicado.
	 * rutasOrigen[]: corresponde a las rutas de cada archivo a incluir en el zip
	 * nombresOrigen[]: corresponde al nombre de cada archivo a incluir en el zip
	 * rutaDestino: ruta de salida(escritura) del del ZIP
	 * nombreDestino: nombre del archivo ZIP de salida.
	 * nombreCarpetas[]: son el nombre de las carpetas que generara dentro del ZIP
	 * */
	public boolean generarZip (String rutasOrigen[],String nombresOrigen[],String nombreCarpetas[], String rutaDestino,String nombreDestino){
		System.out.println("Comienza generarZip");
		boolean proceso = false;
		int len;
		try {
			//crear RUTA cambio 26-03-2015
			File directorio = new File(rutaDestino);
			directorio.mkdirs();
			
			FileOutputStream os = new FileOutputStream(rutaDestino+nombreDestino+".zip");
			System.out.println("(zip)la ruta es == "+rutaDestino);
			ZipOutputStream zos = new ZipOutputStream(os);
			byte b[] = new byte[2048];
//			String nuevaRuta = Util.cambiaBackSlash(ruta);			
			for (int i = 0; i<rutasOrigen.length ; i++) {
				//se generan todas las nominas
				String input =rutasOrigen[i]+nombresOrigen[i];				
				FileInputStream excel = new FileInputStream(input);
				ZipEntry entry = new ZipEntry(nombreCarpetas[i]+nombresOrigen[i]);
				zos.putNextEntry(new ZipEntry(entry));
				len = 0;
				while ((len = excel.read(b)) != -1) {
					zos.write(b, 0, len);
				}
				zos.closeEntry();
				excel.close();
			}
			zos.close();
			proceso = true;			
		} catch (Exception e) {
			proceso = false;
			e.printStackTrace();
		}
		
		System.out.println("Termina generarZip. "+proceso);
		return proceso;
	}
}
