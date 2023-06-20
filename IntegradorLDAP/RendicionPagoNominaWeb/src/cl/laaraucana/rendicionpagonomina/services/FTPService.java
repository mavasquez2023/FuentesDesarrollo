package cl.laaraucana.rendicionpagonomina.services;

import java.io.File;

import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;


public interface FTPService {
	
	void connectToFTP(String host, int port, String user, String pass) throws FTPErrors;

	void putFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors;

	void disconnectFTP() throws FTPErrors;

	public String getFileFromFTP(String ftpRelativePath, String name, String newname) throws FTPErrors;

	public int getFilesFromFolderFTP(String ftpRelativePath) throws FTPErrors;
	
	

	
}
