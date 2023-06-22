package cl.laaraucana.cuotasdup.utils.ftp;

import cl.laaraucana.cuotasdup.utils.ParamConfig;



public class FtpConfig {
	private String user = null;
	private String password= null;
	private String servidor = null;
	private int puerto =  21;
	private String directorio = null;
	private int timeout = 10;
	private int bufferSize = 1048576;//1 MB
	
	public FtpConfig() {
		user =  ParamConfig.RES_CONFIG.getString("ftp.user");
		password=  ParamConfig.RES_CONFIG.getString("ftp.password");
		servidor=  ParamConfig.RES_CONFIG.getString("ftp.servidor");
		puerto= Integer.parseInt( ParamConfig.RES_CONFIG.getString("ftp.puerto"));
		directorio=  ParamConfig.RES_CONFIG.getString("ftp.directorio");
		timeout= Integer.parseInt( ParamConfig.RES_CONFIG.getString("ftp.timeout"));
		String buffer =  ParamConfig.RES_CONFIG.getString("ftp.bufferSize");
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
