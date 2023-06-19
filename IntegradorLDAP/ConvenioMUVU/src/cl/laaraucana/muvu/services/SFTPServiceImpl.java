/**
 * 
 */
package cl.laaraucana.muvu.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author IBM Software Factory
 *
 */
@Service
public class SFTPServiceImpl implements SFTPService {

	/**
	 * Conéctese al servidor sftp
	 * 
	 * @param host
	 * @param port
	 * @param nombre
	 *            de usuario nombre de usuario
	 * @param contraseña
	 *            contraseña
	 * @return
	 */
	@Override
	public ChannelSftp connect(String host, int port, String username, String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {

		}
		return sftp;
	}

	/**
	 * subir archivos
	 * 
	 * @param directorio
	 *            directorio cargado
	 * @param uploadFile
	 *            El archivo a cargar
	 * @param sftp
	 */
	@Override
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * descargar archivo
	 * 
	 * @param directorio
	 *            de descarga del directorio
	 * @param downloadFile
	 * @param saveFile
	 *            existe en la ruta local
	 * @param sftp
	 */
	@Override
	public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			FileOutputStream fop = new FileOutputStream(file);
			sftp.get(downloadFile, fop);
			fop.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Borrar archivos
	 * 
	 * @param directorio
	 *            para eliminar el directorio donde se encuentra el archivo
	 * @param deleteFile
	 *            El archivo a eliminar
	 * @param sftp
	 */
	@Override
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar los archivos en el directorio
	 * 
	 * @param directory
	 *            El directorio a ser listado
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	@Override
	public Vector<String> listFiles(String directory, ChannelSftp sftp) throws SftpException {
		return sftp.ls(directory);
	}

}
