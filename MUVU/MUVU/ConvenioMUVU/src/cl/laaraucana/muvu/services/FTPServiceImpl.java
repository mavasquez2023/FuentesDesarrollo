package cl.laaraucana.muvu.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.laaraucana.muvu.ftp.exceptions.ErrorMessage;
import cl.laaraucana.muvu.ftp.exceptions.FTPErrors;
import cl.laaraucana.muvu.util.Configuraciones;

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
					"El usuario=" + user + ", y el pass=**** no fueron válidos para la autenticación.");
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
	public void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors {

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
	public String downloadFileFromFTP(String ftpRelativePath, String name) throws FTPErrors {
		InputStream iStream=null;
		try {

			FTPFile[] files = this.ftpconnection.listFiles(ftpRelativePath);

			List<FTPFile> lfile = new ArrayList<FTPFile>();

			for (FTPFile file : files) {

				if (file.getName().equals(name.trim() + ".pdf")) {
					
					

					lfile.add(file);
				}
			}

			if (lfile.size() > 1) {

				logger.error("too many files...");
				return "99";
			}else if (lfile.size() == 0){
				logger.error("No hay archivo en ruta FTP");
				return "";
			}
			
			String path = Configuraciones.getConfig("carpeta.local");

			iStream = this.ftpconnection.retrieveFileStream(ftpRelativePath + lfile.get(0).getName());
		    FileUtils.copyInputStreamToFile(iStream, new File(path + "\\" + lfile.get(0).getName()));
			
		    
			return lfile.get(0).getName();

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
	public boolean getFileFromFTP(String ftpRelativePath, String name) throws FTPErrors {

		boolean exit = false;
		
		try {

			FTPFile[] files = this.ftpconnection.listFiles(ftpRelativePath);

			List<FTPFile> lfile = new ArrayList<FTPFile>();

			for (FTPFile file : files) {

				if (file.getName().equals(name.trim() + ".pdf")) {

				 lfile.add(file);
				 
				}
			}

			if (lfile.size() > 0) {

		       exit = true;

			}
			else {
				exit = false;
			}

		 
			return exit;

		} catch (IOException e1) {
			ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo descargar el archivo.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);

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
						new ErrorMessage(-8, "Ha ocurrido un error al realizar la desconexión del servidor FTP"));
			}
		}
	}

	@Override
	public String downloadFileErrorFromFTP(String ftpRelativePath, String localPath) throws FTPErrors {

		FileOutputStream fos;

		try {

			fos = new FileOutputStream(localPath);

			FTPFile[] files = this.ftpconnection.listFiles(ftpRelativePath);

			List<String> lfile = new ArrayList<String>();

			for (FTPFile file : files) {

				if (file.getName().contains("ERROCCAF")) {

					lfile.add(file.getName());
				}
			}

			String name = lfile.get(lfile.size() - 1);

			this.ftpconnection.retrieveFile(ftpRelativePath + name, fos);

			return name;

		} catch (IOException e1) {
			ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo descargar el archivo.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);

		}
	}
}
