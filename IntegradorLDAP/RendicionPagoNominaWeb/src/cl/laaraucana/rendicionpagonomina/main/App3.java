package cl.laaraucana.rendicionpagonomina.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;

public class App3 {

	public static void main(String[] args) throws IOException {
		String path = Configuraciones.getConfig("path.Nominas.recepcion");
		path= path.replaceAll("#banco#", "BES");
		String nameArchivoSalida = zipFiles(path, path + "decode_0.zip");
		unzip(path + "decode_0.zip", path);
	}
	
	public static void unzip(String filezip, String folderExtractTo) throws IOException {
		File zip = new File(filezip);
		File extractTo = new File(folderExtractTo);
		
	    ZipFile archive = new ZipFile(zip);
	    Enumeration<? extends ZipEntry> e = archive.entries();
	    ZipEntry salida;
	    while (e.hasMoreElements()) {
	        ZipEntry entry = e.nextElement();
	        File file = new File(extractTo, entry.getName());
	        if (entry.isDirectory()) {
	            file.mkdirs();
	        } else {
	            if (!file.getParentFile().exists()) {
	                file.getParentFile().mkdirs();
	            }
	            InputStream in = archive.getInputStream(entry);
	            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
	            IOUtils.copy(in, out);
	            in.close();
	            out.close();
	        }
	    }
	}
	
	public static String zipFiles(String folder, String filezip) {
		// cadena que contiene la ruta donde están los archivos .zip - c://folder//
		String directorioZip = folder;
        String nombreArchivo = "";
		// ruta donde están los archivos .zip
		File archivozip = new File(filezip);
		 
		// valida si existe el archivo zip
		if (archivozip.exists()) {
			// lista los archivos que hay dentro del directorio
			//File[] ficheros = carpetaExtraer.listFiles();
			//logger.debug("Número de ficheros encontrados: " + ficheros.length);

			// ciclo para recorrer todos los archivos .zip
			//for (int i = 0; i < ficheros.length; i++) {
				//logger.debug("Nombre del fichero: " + ficheros[i].getName());
				System.out.println("Descomprimiendo.....");
				try {
					// crea un buffer temporal para el archivo que se va descomprimir
					ZipInputStream zis = new ZipInputStream(new FileInputStream(filezip));

					ZipEntry salida;
					// recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de
					// nuevo en su archivo original
					while (null != (salida = zis.getNextEntry())) {
						System.out.println("Nombre del Archivo: " + salida.getName());
						nombreArchivo = salida.getName();
						FileOutputStream fos = new FileOutputStream(directorioZip + salida.getName());
						int leer;
						byte[] buffer = new byte[1024];
						while (0 < (leer = zis.read(buffer))) {
							fos.write(buffer, 0, leer);
						}
						fos.close();
						zis.closeEntry();
					}

				

				} catch (FileNotFoundException e) {
					System.out.println("Error file not found " +  e.getMessage());
				} catch (IOException e) {
					System.out.println("error zip IO " + e.getMessage());
				}
			//}
				System.out.println("Directorio de salida: " + directorioZip);
		} else {
			System.out.println("No se encontró el archivo Zip.");
		}
		return nombreArchivo;
	}
	public void zipFiles_old(String folder) {
		
		//cadena que contiene la ruta donde están los archivos .zip - c://folder//
		String directorioZip = folder;
		 
		//ruta donde están los archivos .zip
		File carpetaExtraer = new File(directorioZip);
		
		//valida si existe el directorio
		if (carpetaExtraer.exists()) {
			//lista los archivos que hay dentro  del directorio
			File[] ficheros = carpetaExtraer.listFiles();
			System.out.println("Número de ficheros encontrados: " + ficheros.length);
			
			//ciclo para recorrer todos los archivos .zip
			for (int i = 0; i < ficheros.length; i++) {
				System.out.println("Nombre del fichero: " + ficheros[i].getName());
				System.out.println("Descomprimiendo.....");
				try {
					//crea un buffer temporal para el archivo que se va descomprimir
					ZipInputStream zis = new ZipInputStream(new FileInputStream(directorioZip + ficheros[i].getName()));
 
					ZipEntry salida;
					//recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de nuevo en su archivo original 
					while (null != (salida = zis.getNextEntry())) {
						System.out.println("Nombre del Archivo: "+salida.getName());	
							FileOutputStream fos = new FileOutputStream(directorioZip + salida.getName());
							int leer;
							byte[] buffer = new byte[1024];
							while (0 < (leer = zis.read(buffer))) {
								fos.write(buffer, 0, leer);
							}
							fos.close();
							zis.closeEntry();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			System.out.println("Directorio de salida: " + directorioZip);
		} else {
			System.out.println("No se encontró el directorio..");
		}

	}

}
