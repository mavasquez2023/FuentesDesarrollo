package cl.laaraucana.rendicionpagonomina.services;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFileFilter;

import cl.laaraucana.rendicionpagonomina.ftp.exceptions.FTPErrors;
import cl.laaraucana.rendicionpagonomina.vo.FTPConfigurationVO;


public interface FTPServicePluss {
	
	/* connection methods */
	FTPClient connectToFTP(FTPConfigurationVO config) throws FTPErrors;

	void disconnectFTP(FTPClient conn) throws FTPErrors;

	/* search and get methods */
	public File getFileFromFTP(FTPConfigurationVO config, String fileName) throws FTPErrors;
	
	public String[] searchFilesFromFTP(FTPConfigurationVO config, String nameLogic) throws FTPErrors;
	
	public boolean existFileInFTP(FTPClient conn, String path, FTPFileFilter filter) throws FTPErrors;

	/* put method  */
	int putFileToFTP(FTPConfigurationVO config, File file, String fileName, boolean overwrite) throws FTPErrors;
	
	public int putFileToFTP(FTPConfigurationVO config, InputStream inputStreamFile, String fileName, boolean overwrite) throws FTPErrors;
	
	int moveFileInFTP(FTPConfigurationVO config, String originFileName, String destinationFileName, boolean overwrite) throws FTPErrors;
	
	/* delete method  */
	int deleteFileInFTP(FTPClient ftpconnection, String path, String fileName) throws FTPErrors;
	
	int deleteFileInFTP(FTPConfigurationVO config, String path, String fileName) throws FTPErrors;
	
	
	

	
}
