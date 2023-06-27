package cl.laaraucana.RentasPrevired.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

public class Util {

	private static final Logger logger = Logger.getLogger(Util.class);

	public static File grabarFicheroTemporal(CommonsMultipartFile uploaded, String ruta) throws Exception {

		// File localFile = new File(System.getProperty("java.io.tmpdir") +
		// uploaded.getOriginalFilename());

		File localFile = new File(ruta + uploaded.getOriginalFilename());

		FileOutputStream os = null;

		try {

			os = new FileOutputStream(localFile);
			os.write(uploaded.getBytes());

		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return localFile;
	}

	public static String getTipe(String str) {

		int pos = str.lastIndexOf(".");

		str = str.substring(pos + 1, str.length());

		return str;
	}

	public static boolean validarRut(String rut) {

		boolean validacion = false;
		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		}
		return validacion;
	}

	public static boolean isError(String ruta) {

		try {

			File file = new File(ruta);

			@SuppressWarnings("unchecked")
			List<String> ln = FileUtils.readLines(file);

			String[] str = ln.get(0).split(";");
            logger.info(str);
			if (str[2].trim().equals("0")) {

				return false;
			} else {
				return true;
			}

		} catch (IOException e) {

			logger.error("Error el leer el fichero.", e);

			return true;
		}

	}

	public static User getUser(String userID) {
		UserRegistryConnection connection = null;
		try {
			connection = new UserRegistryConnection();

			return connection.getUser(userID);
		} catch (UserRegistryException e) {
			return null;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (UserRegistryException e) {
				}
			}
		}
	}

}
