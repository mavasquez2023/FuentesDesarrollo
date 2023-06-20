package cl.laaraucana.mandato.services;

import java.io.File;

import cl.laaraucana.mandato.util.FTPErrors;
import cl.laaraucana.mandato.vo.FtpVO;


public interface FTPService {
	
	void connectToFTP() throws FTPErrors;

	void putFileToFTP(String serverFilename) throws FTPErrors;

	void disconnectFTP() throws FTPErrors;

	public String getFileFromFTP(String ftpRelativePath, String name, String newname) throws FTPErrors;

	public int getFilesFromFolderFTP(String ftpRelativePath) throws FTPErrors;
	
	

	
}
