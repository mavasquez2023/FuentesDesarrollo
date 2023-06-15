package cl.laaraucana.simat.utiles;

import java.io.IOException;

import cz.dhl.ftp.Ftp;
import cz.dhl.ftp.FtpConnect;
import cz.dhl.ftp.FtpFile;
import cz.dhl.io.CoFile;
import cz.dhl.io.CoLoad;
import cz.dhl.io.LocalFile;

public class Util_JvFTP {

	public FtpConnect cn;
	public Ftp cl;

	public void conectarJvFTP(String server, String user, String pass) {
		cn = FtpConnect.newConnect("ftp://" + server);
		// se ingresa el usuario
		cn.setUserName(user);
		// se ingresa la contraseña
		cn.setPassWord(pass);
		cl = new Ftp();
		try {
			// conectarse al host
			cl.connect(cn);
		} catch (IOException e) {
			System.out.println(e);
		}
	}//end conectar JvFTP

	public void desconectarJvFTP() {
		cl.disconnect();
	}//end desconectarJvFTP

	public void getDirActualJvFTP() {
		try {
			//directorio actual
			CoFile dir = new FtpFile(cl.pwd(), cl);
			CoFile fls[] = dir.listCoFiles();
			if (fls != null) {
				for (int n = 0; n < fls.length; n++) {
					System.out.println(fls[n].getName()
					//marcar los directorio con un slash "/"
							+ (fls[n].isDirectory() ? "/" : ""));
				}
			}
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}//end imprimirDirectorioActual

	public void nuevoDirJvFTP(String rutaDir) {
		cl.mkdir(rutaDir);
	}//end nuevoDirJvFTP

	public void subirArchivoJvFTP(String path_servidor, String nombre_archivo, String ubicacion_nueva) {
		CoFile archivoLocal = new LocalFile(ubicacion_nueva, nombre_archivo);
		System.out.println("Subiendo archivo en: " + ubicacion_nueva);
		CoFile archivoRemoto = new FtpFile(path_servidor + nombre_archivo, cl);
		CoLoad.copy(archivoRemoto, archivoLocal);
	}//end subirArchivoJvFTP

	public void bajarArchivoJvFTP(String path_servidor, String nombre_archivo, String ubicacion_nueva) {
		CoFile archivoLocal = new LocalFile(ubicacion_nueva, nombre_archivo);
		System.out.println("Descargando archivo en: " + ubicacion_nueva);
		CoFile archivoRemoto = new FtpFile(path_servidor + nombre_archivo, cl);
		CoLoad.copy(archivoLocal, archivoRemoto);
	}//end bajarArchivoJvFTP 

}//end class Util_jvFTP
