package cl.laaraucana.satelites.Utils.ftp;

import cl.laaraucana.satelites.Utils.Configuraciones;

public class FtpConfig {
	private String user = null;
	private String password= null;
	private String servidor = null;
	private int puerto =  21;
	private String directorio = null;
	private int timeout = 10;
	private int bufferSize = 1048576;//1 MB
	
	public FtpConfig() {
		user = Configuraciones.getFtpConfig("ftp.user");
		password= Configuraciones.getFtpConfig("ftp.password");
		servidor= Configuraciones.getFtpConfig("ftp.servidor");
		puerto= Integer.parseInt(Configuraciones.getFtpConfig("ftp.puerto"));
		directorio= Configuraciones.getFtpConfig("ftp.directorio");
		timeout= Integer.parseInt(Configuraciones.getFtpConfig("ftp.timeout"));
		String buffer = Configuraciones.getFtpConfig("ftp.bufferSize");
		if(buffer!=null)
			bufferSize = Integer.parseInt(buffer);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getDirectorio() {
		return directorio;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
}
