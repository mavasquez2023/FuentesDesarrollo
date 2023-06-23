package cl.araucana.tesoreria.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.log4j.Logger;

public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);
	public static String readFile(String filename) throws Exception {
		return readFile(filename, true, null);
	}
	public static String readFile(String filename, boolean newLine, String charset) throws Exception {
		File aFile = com.schema.util.FileUtils.getGlobalFile(filename);
		return readFile(aFile, newLine, charset);
	}
	public static String readFile(File aFile, boolean newLine, String charset) throws Exception {
		StringBuffer contents = new StringBuffer();
		BufferedReader input = null;
		InputStreamReader bis = null;
		FileReader fReader = null;
		FileInputStream fis = null;
		if (charset == null) {
			fReader = new FileReader(aFile);
			input = new BufferedReader(fReader);
		} else {
			logger.debug("setea charset a " + charset);
			fis = new FileInputStream(aFile);
			bis = new InputStreamReader(fis, charset);
			input = new BufferedReader(bis);
		}
		try {
			String line = null;
			while ((line = input.readLine()) != null) {
				contents.append(line);
				if (newLine)
					contents.append(System.getProperty("line.separator"));
			}
		} finally {
			try {
				fReader.close();
			} catch (Exception ex) {
				logger.debug("Se omite: " + ex);
			}
			try {
				fis.close();
			} catch (Exception ex) {
				logger.debug("Se omite: " + ex);
			}
			try {
				bis.close();
			} catch (Exception ex) {
				logger.debug("Se omite: " + ex);
			}
			try {
				input.close();
			} catch (Exception ex) {
				logger.debug("Se omite: " + ex);
			}
		}
		return contents.toString();
	}
	public static void saveFile(String filename, String data) throws Exception {
		FileOutputStream file = new FileOutputStream(filename, false);
		file.write(data.getBytes());
		file.flush();
		file.close();
	};
	public static void deleteFiles(String dirPath, String startPattern, Date maxDate) {
		try {
			File dir = new File(dirPath);
			if (!dir.exists())
				logger.debug("Path " + dirPath + " no existe");
			else {
				String[] info = dir.list();
				if (info != null && info.length > 0) {
					logger.debug("Directorio: " + dirPath + " con " + info.length + " archivos");
					for (int i = 0; i < info.length; i++) {
						try {
							File n = new File(dirPath + dir.separator + info[i]);
							if (n.isFile()) {
								if (info[i].startsWith(startPattern))
									if (n.lastModified() < maxDate.getTime()) {
										logger.debug("Elimina " + info[i] + " del " + new Date(n.lastModified()));
										n.delete();
									}
							}
						} catch (Exception e) {
							logger.error("Error al borrar File " + info[i], e);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Error en deleteFiles", ex);
		}
	}
	public static void deleteFiles(String dirPath, String startPattern) {
		try {
			File dir = new File(dirPath);
			if (!dir.exists())
				logger.debug("Path " + dirPath + " no existe");
			else {
				String[] info = dir.list();
				if (info != null && info.length > 0) {
					logger.debug("Directorio: " + dirPath + " con " + info.length + " archivos");
					for (int i = 0; i < info.length; i++) {
						try {
							File n = new File(dirPath + dir.separator + info[i]);
							if (n.isFile()) {
								if (info[i].startsWith(startPattern)) {
									logger.debug("Elimina " + info[i] + " del " + new Date(n.lastModified()));
									n.delete();
								}
							}
						} catch (Exception e) {
							logger.error("Error al borrar File " + info[i], e);
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error("Error en deleteFiles", ex);
		}
	}
}
