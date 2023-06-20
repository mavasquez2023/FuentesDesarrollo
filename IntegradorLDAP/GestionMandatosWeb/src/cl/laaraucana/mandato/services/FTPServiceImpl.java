package cl.laaraucana.mandato.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.laaraucana.mandato.util.Configuraciones;
import cl.laaraucana.mandato.util.ErrorMessage;
import cl.laaraucana.mandato.util.FTPErrors;

import java.io.*;


@Service
public class FTPServiceImpl implements FTPService {

	FTPClient ftpconnection;

	private Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);

	@Override
	public void connectToFTP() throws FTPErrors {
		String server= Configuraciones.getConfig("mandato.ftp.server");
		int puerto= Integer.parseInt(Configuraciones.getConfig("mandato.ftp.puerto"));
		String usuario= Configuraciones.getConfig("mandato.ftp.usuario");
		String clave= Configuraciones.getConfig("mandato.ftp.clave");
		int timeout= Integer.parseInt(Configuraciones.getConfig("mandato.ftp.timeout"));
		
		ftpconnection = new FTPClient();
		ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;

		try {
			ftpconnection.connect(server, puerto);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1,
					"No fue posible conectarse al FTP a través del host=" + server);
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		reply = ftpconnection.getReplyCode();

		if (!FTPReply.isPositiveCompletion(reply)) {

			try {
				ftpconnection.disconnect();
			} catch (IOException e) {
				ErrorMessage errorMessage = new ErrorMessage(-2,
						"No fue posible conectarse al FTP, el host=" + server + " entregó la respuesta=" + reply);
				logger.error(errorMessage.toString());
				throw new FTPErrors(errorMessage);
			}
		}

		try {
			ftpconnection.login(usuario, clave);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-3,
					"El usuario=" + usuario + ", y el pass=**** no fueron vÃ¡lidos para la autenticación.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		try {

			ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
			ftpconnection.setConnectTimeout(timeout);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		ftpconnection.enterLocalPassiveMode();
	}

	@Override
	public void putFileToFTP(String file) throws FTPErrors {
		String carpeta_ftp= Configuraciones.getConfig("mandato.ftp.carpeta");
		String file_ftp= file.substring(file.lastIndexOf("\\")+1);
		try {
			InputStream input = new FileInputStream(file);
			this.ftpconnection.storeFile(carpeta_ftp + file_ftp, input);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-5, "No se pudo subir el archivo al servidor.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

	}

	@Override
	public String getFileFromFTP(String ftpRelativePath, String name, String newname) throws FTPErrors {
		InputStream iStream=null;
		try {
			String path = Configuraciones.getConfig("carpeta.local");

			iStream = this.ftpconnection.retrieveFileStream(ftpRelativePath + name);
			if(newname== null){
				newname= name;
			}
			FileUtils.copyInputStreamToFile(iStream, new File(path + newname));
		    
			return path + name;

		} catch (IOException e1) {
			logger.error(e1.getMessage());
			return "-1";

		}
		finally{
			try {
				ftpconnection.disconnect();
				if(iStream!=null){
					iStream.close();
				}
			} catch (IOException e) {
				
			}
		}

	}
	
	@Override
	public int getFilesFromFolderFTP(String ftpRelativePath) throws FTPErrors {
		InputStream iStream=null;
		try {
				int numfiles=0;
				FTPFile[] files = this.ftpconnection.listFiles(ftpRelativePath);

				for (FTPFile file : files) {

					if (!file.isDirectory()){

						String path = Configuraciones.getConfig("carpeta.local");

						iStream = this.ftpconnection.retrieveFileStream(ftpRelativePath + file.getName());
						FileUtils.copyInputStreamToFile(iStream, new File(path + file.getName()));
						numfiles++;
					}
				}
	
		    
			return numfiles;

		} catch (IOException e1) {
			logger.error(e1.getMessage());
			return -1;

		}
		finally{
			try {
				ftpconnection.disconnect();
				if(iStream!=null){
					iStream.close();
				}
			} catch (IOException e) {
				
			}
		}

	}
	
	@Override
	public void disconnectFTP() throws FTPErrors {
		if (this.ftpconnection.isConnected()) {
			try {
				this.ftpconnection.logout();
				this.ftpconnection.disconnect();
			} catch (IOException f) {
				throw new FTPErrors(
						new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexiÃ³n del servidor FTP"));
			}
		}
	}

}
