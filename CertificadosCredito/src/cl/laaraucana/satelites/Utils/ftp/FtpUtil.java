package cl.laaraucana.satelites.Utils.ftp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	
	private static FtpConfig config = null;
	private FTPClient ftpClient;
	
	public FtpUtil() {
		if(config==null)
			config = new FtpConfig();
	}
	
	private FTPClient conectar() throws Exception{
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(config.getServidor());
			ftpClient.setDefaultPort(config.getPuerto());
			ftpClient.setDefaultTimeout(config.getTimeout());
			if (!ftpClient.login(config.getUser(), config.getPassword())) {
				ftpClient.logout();
				System.out.println("Problema al autenticarse en el servidor Ftp");
				return null;
			}
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return null;
			}
		} catch (Exception e) {
			throw new Exception("Error al conectarse al Ftp",e);
		}
		return ftpClient;
	}
	
	public boolean subirArchivoViaFtp(byte[] archivo, String nombreArchivo) {
		boolean res = false;
		try {
			ftpClient = conectar();
			// enter passive mode
			//ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(config.getDirectorio());
			//System.out.println("Current directory is " + ftpClient.printWorkingDirectory());

			ByteArrayInputStream bais = new ByteArrayInputStream(archivo);
			// subir archivo
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setBufferSize(config.getBufferSize());//1024 KB
			ftpClient.storeFile(nombreArchivo, bais);

			res = true;
			bais.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean subirArchivoURLFtp(byte[] archivo, String nombreArchivo){
		boolean res = false;
		try {
			URL url = new URL("ftp://" + config.getUser() + ":" + config.getPassword() + 
					"@" + config.getServidor()+ ":" + config.getPuerto() + config.getDirectorio()+nombreArchivo);
			System.out.println(url);
			
			URLConnection urlc = url.openConnection();
			OutputStream os = urlc.getOutputStream();
			os.write(archivo);
/*			ByteArrayInputStream bais = new ByteArrayInputStream(archivo);
			byte[] buffer = new byte[512];
			int count = 0;
			while((count = bais.read(buffer)) > 0) {
			    os.write(buffer, 0, count);
			}*/
			os.flush();
			os.close();
			//bais.close();
		} catch (Exception e) {
			res = false;
		}
		
		return res;
	}
	
	public List<Archivo> listarArchivos(String filtroExt, String directorioPrinc){
		List<Archivo> lista = new ArrayList<Archivo>();
		try {
			ftpClient = conectar();
			if(directorioPrinc!=null)
				ftpClient.changeWorkingDirectory(directorioPrinc);
			FTPFile[] files = ftpClient.listFiles();
			
			for (FTPFile ftpFile : files) {
				if(filtroExt!=null && ftpFile.getName().toUpperCase().endsWith(filtroExt.toUpperCase())){
					Archivo aux = new Archivo();
					aux.setNombre(ftpFile.getName());
					aux.setFechaModificacion(ftpFile.getTimestamp());
					aux.setTamano(ftpFile.getSize());
					lista.add(aux);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean eliminarArchivo(String nombreArchivo, String directorioPrinc){
		boolean res = false;
		try {
			FTPClient ftpClient = conectar();
			if(directorioPrinc!=null)
				ftpClient.changeWorkingDirectory(directorioPrinc);
			ftpClient.dele(nombreArchivo);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void desconectar(){
		try {
			if(ftpClient!=null){
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		FtpUtil test=new FtpUtil();
		
		File archivoa = new File("C:/Fondo de Pantalla  - La Araucana CCAF.jpg");
		byte[] baits = FileUtils.readFileToByteArray(archivoa);
		
		 FileInputStream fis = new FileInputStream("C:/Fondo de Pantalla  - La Araucana CCAF.jpg");
		test.subirArchivoURLFtp(baits, "TEST.jpg");
		List<Archivo> archivos = test.listarArchivos("PDF","");
		for (Archivo archivo : archivos) {
			System.out.println(archivo.getNombre());
			System.out.println(archivo.getFechaModificacion());
			System.out.println("===========");
		}
		test.desconectar();
		
	}
}

