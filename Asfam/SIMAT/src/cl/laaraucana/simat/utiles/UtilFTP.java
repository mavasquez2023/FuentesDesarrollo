package cl.laaraucana.simat.utiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class UtilFTP {

	/**
	* Metodo para subir un archivo local a un servidor FTP
	* tenemos que pasarle los datos de:
	* ip o hostname del servidor
	* usuario
	* password
	* la ruta completa del fichero local a subir
	* la ruta en donde queramos colocar el archivo en el servidor FTP
	* ej: uploadFileByFTP("servidor", "usuario", "password", "c:\\prueba.txt", "/mi_dir/prueba.txt");
	*/
	public static boolean uploadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
		System.out.println("####################################################################################");
		System.out.println("####################################################################################");
		System.out.println("FTP : " + " server " + server + ", us " + user + ", pass " + pass + ", local " + localPath + ", remote " + remotePath);
		System.out.println("####################################################################################");
		System.out.println("####################################################################################");

		try {
			System.out.println("LocalPath = " + localPath);
			System.out.println("RemotePathUp = " + remotePath);

			URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");

			URLConnection urlc = url.openConnection();

			OutputStream os = urlc.getOutputStream();
			BufferedReader br = new BufferedReader(new FileReader(localPath));

			int c;

			while ((c = br.read()) != -1) {
				os.write(c);
			}
			os.flush();
			os.close();
			br.close();

			return true;

		} catch (Exception ex) {
			System.out.println("Fallo subida archivo en FTP. " + " server " + server + ", us " + user + ", pass " + pass + ", local " + localPath + ", remote " + remotePath);
			ex.printStackTrace();
			return false;
		}
	}

	/**
	* Metodo para descargar un archivo en un servidor FTP a un archivo local
	* tenemos que pasarle los datos de:
	* ip o hostname del servidor
	* usuario
	* password
	* la ruta completa del fichero local donde se escribiran los datos
	* la ruta del archivo que queramos bajar en el FTP
	* ej: downloadFileByFTP("servidor", "usuario", "password", "c:\\prueba.txt", "/mi_dir/prueba.txt");
	*/
	public static boolean downloadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
		System.out.println("####################################################################################");
		System.out.println("####################################################################################");
		System.out.println("FTP : " + " server " + server + ", us " + user + ", pass " + pass + ", local " + localPath + ", remote " + remotePath);
		System.out.println("####################################################################################");
		System.out.println("####################################################################################");

		try {

			System.out.println("LocalPath = " + localPath);
			System.out.println("RemotePathUp = " + remotePath);

			URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");

			URLConnection urlc = url.openConnection();
			InputStream is = urlc.getInputStream();
			BufferedWriter bw = new BufferedWriter(new FileWriter(localPath));

			int c;

			while ((c = is.read()) != -1) {
				bw.write(c);
			}
			is.close();
			bw.flush();
			bw.close();
			return true;

		} catch (Exception ex) {
			System.out.println("Fallo subida archivo en FTP. " + " server " + server + ", us " + user + ", pass " + pass + ", local " + localPath + ", remote " + remotePath);
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return false;
		}
	}

}
