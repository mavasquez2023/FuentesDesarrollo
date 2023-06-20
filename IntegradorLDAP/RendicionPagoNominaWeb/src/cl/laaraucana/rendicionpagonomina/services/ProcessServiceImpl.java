package cl.laaraucana.rendicionpagonomina.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;

@Service
public class ProcessServiceImpl implements ProcessService {

	private static final Logger logger = Logger.getLogger(ProcessServiceImpl.class);

	@Override
	public String zipFiles(String folder, String filezip) {
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
				logger.debug("Descomprimiendo.....");
				try {
					// crea un buffer temporal para el archivo que se va descomprimir
					ZipInputStream zis = new ZipInputStream(new FileInputStream(filezip));

					ZipEntry salida;
					// recorre todo el buffer extrayendo uno a uno cada archivo.zip y creándolos de
					// nuevo en su archivo original
					while (null != (salida = zis.getNextEntry())) {
						logger.info("Nombre del Archivo: " + salida.getName());
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
					logger.error("Error file not found ", e);
				} catch (IOException e) {
					logger.warn("error zip IO ");
				}
			//}
			logger.debug("Directorio de salida: " + directorioZip);
		} else {
			logger.debug("No se encontró el archivo Zip.");
		}
		return nombreArchivo;
	}

	public void zipDir(List<String> srcFiles, String path) throws Exception {

		FileOutputStream fos = new FileOutputStream(path + "\\" + "files.zip");
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		for (String srcFile : srcFiles) {
			File fileToZip = new File(srcFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			fis.close();
		}
		zipOut.close();
		fos.close();

	}

	public void decodeBase64(String data, String filename, String path) {

		try {

			byte[] retBytes = Base64.decodeBase64(data.getBytes());

			File f = new File(path + filename);
			f.createNewFile();

			// Initialize a pointer
			// in file using OutputStream
			OutputStream os = new FileOutputStream(path + filename);

			// Starts writing the bytes in it
			os.write(retBytes);
			logger.debug("Successfully" + "data byte inserted");

			// Close the file
			os.close();

		} catch (Exception e) {
			logger.error("Error processing data ", e);
		}
	}

	public String encodeBase64(byte[] data) {

		byte[] retBytes = null;

		try {

			retBytes = Base64.encodeBase64(data);

		} catch (Exception e) {
			logger.error("Error processing data ", e);
		}

		return new String(retBytes, Charset.forName("UTF-8"));
	}

}
