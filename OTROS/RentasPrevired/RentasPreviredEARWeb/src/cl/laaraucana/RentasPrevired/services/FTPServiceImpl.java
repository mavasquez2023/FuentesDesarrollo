package cl.laaraucana.RentasPrevired.services;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import cl.laaraucana.RentasPrevired.ftp.exceptions.ErrorMessage;
import cl.laaraucana.RentasPrevired.ftp.exceptions.FTPErrors;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FTPServiceImpl implements FTPService {

	/**
	 * FTP connection handler
	 */
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

	/**
	 * Method that allow upload file to FTP
	 * 
	 * @param file
	 *            File object of file to upload
	 * @param ftpHostDir
	 *            FTP host internal directory to save file
	 * @param serverFilename
	 *            Name to put the file in FTP server.
	 * @throws FTPErrors
	 *             Set of possible errors associated with upload process.
	 */
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

	/**
	 * Method for download files from FTP.
	 * 
	 * @param ftpRelativePath
	 *            Relative path of file to download into FTP server.
	 * @param copytoPath
	 *            Path to copy the file in download process.
	 * @throws FTPErrors
	 *             Set of errors associated with download process.
	 */

	@Override
	public List<String> downloadFileFromFTP(String ftpRelativePath, String localPath) throws FTPErrors {

		FileOutputStream fos;

		try {

			FTPFile[] files = this.ftpconnection.listFiles(ftpRelativePath);
			
			List<String> namesFiles= new ArrayList<String>();

			

			for (FTPFile file : files) {
				

				if (file.getName().contains("RESPCCAF")) {
					
					fos = new FileOutputStream(localPath + file.getName());
					
					namesFiles.add(file.getName());

					this.ftpconnection.retrieveFile(ftpRelativePath + file.getName(), fos);
				}
			}

			return namesFiles;
			
		} catch (IOException e1) {
			ErrorMessage errorMessage = new ErrorMessage(-7, "No se pudo descargar el archivo.");
			logger.error(errorMessage.toString());
			throw new FTPErrors(errorMessage);

		}

	}

	/**
	 * Method for release the FTP connection.
	 * 
	 * @throws FTPErrors
	 *             Error if unplugged process failed.
	 */
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

			// lfile.sort(String.CASE_INSENSITIVE_ORDER);

			Collections.sort(lfile);

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
