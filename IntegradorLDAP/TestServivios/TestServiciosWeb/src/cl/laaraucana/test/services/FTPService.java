package cl.laaraucana.test.services;

import java.io.File;

import cl.laaraucana.test.ftp.FTPErrors;
import cl.laaraucana.test.vo.FtpVO;



public interface FTPService {
	
	void connectToFTP(FtpVO paramsFTP) throws FTPErrors;

	void putFileToFTP(File file, String ftpHostDir, String serverFilename) throws FTPErrors;

	void disconnectFTP() throws FTPErrors;

	public String getFileFromFTP(String ftpRelativePath, String name, String newname) throws FTPErrors;

	public int getFilesFromFolderFTP(String ftpRelativePath) throws FTPErrors;
	
	

	
}
