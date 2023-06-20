package cl.laaraucana.licenciascompin.services;

import java.io.File;

import cl.laaraucana.licenciascompin.ftp.exceptions.FTPErrors;

public interface FTPService {
	
	void connectToFTP(String host, int port, String user, String pass) throws FTPErrors;

	void uploadFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors;
	
	void uploadFileToFTP(String file, String ftpHostDir, String serverFilename) throws FTPErrors;

	void disconnectFTP() throws FTPErrors;

	public String downloadFileFromFTP(String ftpRelativePath, String localPath) throws FTPErrors;

	public String downloadFileErrorFromFTP(String ftpRelativePath, String localPath) throws FTPErrors;

	boolean getFileFromFTP(String ftpRelativePath, String name) throws FTPErrors;

	
}
