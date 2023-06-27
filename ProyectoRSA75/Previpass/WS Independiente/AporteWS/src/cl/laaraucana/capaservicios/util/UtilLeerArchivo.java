package cl.laaraucana.capaservicios.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UtilLeerArchivo {
	static File tempFile;
	
	public static ArrayList<String> leerArchivo(String nomArchivo) {
		ArrayList<String> lineas = new ArrayList<String>();
		BufferedReader entrada = null;
		tempFile = new File(nomArchivo);
		try {
			entrada = new BufferedReader(new FileReader(tempFile));
			while (entrada.ready()) {
				lineas.add(entrada.readLine());
			}
			entrada.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return lineas;
	}
	
	
	public static byte[] readFileToBites(String ruta) {
		byte[] bitesArchivo = null;
		File archivo = new File(ruta);
		
		if(archivo.exists()){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileInputStream fis = null;
			try {
				baos.reset();
				fis = new FileInputStream(archivo);
				BufferedInputStream bis = new BufferedInputStream(fis);
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = bis.read(buffer)) >= 0) {
					baos.write(buffer, 0, bytesRead);
				}
				baos.flush();
				bitesArchivo = baos.toByteArray();
			} catch (IOException e) {
				try {
					fis.close();
					baos.close();
				} catch (Exception e2) {
				}
			}
		}
		return bitesArchivo;
	}
}
