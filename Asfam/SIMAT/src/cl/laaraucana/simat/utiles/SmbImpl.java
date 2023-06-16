package cl.laaraucana.simat.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import jcifs.smb.SmbSession;

public class SmbImpl {

	/**
	 *
	 * @author Reza
	 */
	private UniAddress domain;
	private NtlmPasswordAuthentication authentication;

	/**
	 *
	 * @param address
	 * @param username
	 * @param password
	 * @throws java.lang.Exception
	 */
	public void login(String address, String username, String password) throws Exception {

		setDomain(UniAddress.getByName(address));
		setAuthentication(new NtlmPasswordAuthentication(address, username, password));
		SmbSession.logon(getDomain(), authentication);

	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public LinkedList getList(String path) throws Exception {
		LinkedList fList = new LinkedList();
		SmbFile f = new SmbFile(path, authentication);
		SmbFile[] fArr = f.listFiles();

		for (int a = 0; a < fArr.length; a++) {
			fList.add(fArr[a].getName());
			System.out.println(fArr[a].getName());
		}

		return fList;
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public boolean checkDirectory(String path) throws Exception {
		if (!isExist(path)) {
			System.out.println(path + " not exist");
			return false;
		}

		if (!isDir(path)) {
			System.out.println(path + " not a directory");
			return false;
		}

		return true;
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public boolean isExist(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);
		return sFile.exists();
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public boolean isDir(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);

		return sFile.isDirectory();
	}

	/**
	 *
	 * @param path
	 * @throws java.lang.Exception
	 */
	public void createDir(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);

		sFile.mkdir();
	}

	/**
	 *
	 * @param path
	 * @throws java.lang.Exception
	 */
	public void delete(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);
		sFile.delete();
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public long size(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);

		return sFile.length();
	}

	/**
	 *
	 * @param path
	 * @return
	 * @throws java.lang.Exception
	 */
	public String getFileName(String path) throws Exception {
		SmbFile sFile = new SmbFile(path, authentication);

		return sFile.getName();
	}

	/**
	 * @return the domain
	 */
	public UniAddress getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(UniAddress domain) {
		this.domain = domain;
	}

	/**
	 * @return the authentication
	 */
	public NtlmPasswordAuthentication getAuthentication() {
		return authentication;
	}

	/**
	 * @param authentication the authentication to set
	 */
	public void setAuthentication(NtlmPasswordAuthentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * 
	 * @param path
	 * @param archivo
	 * @throws Exception
	 */
	public void copyFile(String ruta, String nomArchDestino, File archivo) throws Exception {
		FileInputStream in = new FileInputStream(archivo);

		SmbFile sFile = new SmbFile(ruta + nomArchDestino, authentication);
		SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
		int c;
		while ((c = in.read()) != -1) {
			sfos.write(c);
		}
		in.close();
		sfos.close();
	}//END copyFile

	public void copyBuffer(String ruta, String nomArchDestino, File archivo) throws Exception {
		FileInputStream in = new FileInputStream(archivo);

		SmbFile sFile = new SmbFile(ruta + nomArchDestino, authentication);
		SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
		final byte[] buf = new byte[16 * 1024 * 1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			sfos.write(buf, 0, len);
		}
		in.close();
		sfos.close();
	}//END copyBuffer
	/*
	public void readerBuffer(String rutaDestino, String rutaOrigen) throws Exception{
		FileInputStream in = new FileInputStream(rutaOrigen);	    	
		SmbFile sFile = new SmbFile(rutaDestino, authentication);
		SmbFileInputStream sfis = new SmbFileInputStream(sFile);
	    final byte[] buf = new byte[16 * 1024 * 1024];
	    int len;
	    while ((len = sfis.read(buf)) > 0) {
	    	sfis.read(buf, 0, len);
	    }
		in.close();
		sfos.close();
	}//END copyBuffer
	*/
}
