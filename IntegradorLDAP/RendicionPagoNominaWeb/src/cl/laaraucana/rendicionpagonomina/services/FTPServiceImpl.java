package cl.laaraucana.rendicionpagonomina.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.laaraucana.rendicionpagonomina.ftp.exceptions.ErrorMessage;
import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;
import cl.laaraucana.rendicionpagonomina.utils.Configuraciones;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FTPServiceImpl implements FTPService {

	FTPClient ftpconnection;

	private Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);

	@Override
	public void connectToFTP(String host, int port, String user, String pass) throws FTPErrors {

		ftpconnection = new FTPClient();
		ftpconnection.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;

		try {
			ftpconnection.connect(host, port);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-1,
					"No fue posible conectarse al FTP a través del host=" + host);
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		reply = ftpconnection.getReplyCode();

		if (!FTPReply.isPositiveCompletion(reply)) {

			try {
				ftpconnection.disconnect();
			} catch (IOException e) {
				ErrorMessage errorMessage = new ErrorMessage(-2,
						"No fue posible conectarse al FTP, el host=" + host + " entregó la respuesta=" + reply);
				logger.error(errorMessage.toString());
				throw new FTPErrors(errorMessage);
			}
		}

		try {
			ftpconnection.login(user, pass);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-3,
					"El usuario=" + user + ", y el pass=**** no fueron vÃ¡lidos para la autenticación.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		try {

			ftpconnection.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			ErrorMessage errorMessage = new ErrorMessage(-4, "El tipo de dato para la transferencia no es válido.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);
		}

		ftpconnection.enterLocalPassiveMode();
	}

	@Override
	public void putFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors {

		try {
			InputStream input = new FileInputStream(file);
			this.ftpconnection.storeFile(ftpHostDir + serverFilename, input);
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
