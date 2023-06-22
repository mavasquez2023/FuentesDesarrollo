package cl.laaraucana.muvu.services;

import java.util.Vector;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public interface SFTPService {
	
	public ChannelSftp connect(String host, int port, String username, String password) throws Exception;

	public void upload(String directory, String uploadFile, ChannelSftp sftp)  throws Exception;

	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) throws Exception; 

	public void delete(String directory, String deleteFile, ChannelSftp sftp) throws Exception;

	public Vector<String> listFiles(String directory, ChannelSftp sftp) throws SftpException;

	
}
