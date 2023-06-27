package cl.laaraucana.RentasPrevired.services;

import java.io.File;
import java.util.List;

import cl.laaraucana.RentasPrevired.ftp.exceptions.FTPErrors;

public interface FTPService {
	
	void connectToFTP(String host, int port, String user, String pass) throws FTPErrors;

	void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors;

	void disconnectFTP() throws FTPErrors;

	public List<String> downloadFileFromFTP(String ftpRelativePath, String localPath) throws FTPErrors;

	public String downloadFileErrorFromFTP(String ftpRelativePath, String localPath) throws FTPErrors;
}
